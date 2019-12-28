package com.shengdangjia.hermesgateway.filter;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class IdTokenFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        var resp = exchange.getResponse();

        if (StringUtils.isEmpty(token)) {
            return failAuth(resp, RestHelper.makeResponse(null, ErrorCode.NEED_AUTHORIZATION));
        } else {
            ResponseData authResult = sendAuth(token);
            if (authResult.errorCode == 0) {
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .header("Authorization", authResult.result.toString())
                        .build();

                return chain.filter(exchange.mutate().request(request).build());
            } else {
                ResponseData d = RestHelper.makeResponse(null, authResult.errorCode, authResult.message);
                return failAuth(resp, d);
            }
        }
    }

    /**
     * 发送id token 认证
     * 认证成功后获取 access token
     * @param token id token
     * @return 认证结果
     */
    private ResponseData sendAuth(String token) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:10000/authentication-service/auth/id?token=" + token))
                    .header("from", "hermes-gateway")
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();

            return ResponseData.fromJsonString(body);
        } catch (IOException e) {
            System.out.println("io execption" + e.toString());
            return RestHelper.makeResponse(e.toString(), ErrorCode.NETWORK_ERROR);

        } catch (InterruptedException e) {
            System.out.println("interrupted execption" + e.toString());
            return RestHelper.makeResponse(e.toString(), ErrorCode.NETWORK_ERROR);
        }
    }

    /**
     * 处理失败返回
     *
     * @param response
     * @param data
     * @return
     */
    private Mono<Void> failAuth(ServerHttpResponse response, ResponseData data) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        var json = data.toJsonString();
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }

    /**
     * filter 顺序
     *
     * @return -50
     */
    @Override
    public int getOrder() {
        return -50;
    }
}
