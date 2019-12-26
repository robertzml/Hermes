package com.shengdangjia.hermesgateway.filter;

import com.shengdangjia.common.model.ErrorCode;
import com.shengdangjia.common.model.ResponseData;
import com.shengdangjia.common.utility.RestHelper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
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

public class IdTokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (StringUtils.isEmpty(token)) {
            var resp = exchange.getResponse();
            resp.setStatusCode(HttpStatus.OK);
            resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");

            ResponseData d = RestHelper.makeResponse(null, ErrorCode.NEED_AUTHORIZATION);
            var s = d.toJsonString();

            DataBuffer buffer = resp.bufferFactory().wrap(s.getBytes(StandardCharsets.UTF_8));
            return resp.writeWith(Mono.just(buffer));
        }
        else {
            try {
                HttpClient client = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(5))
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://192.168.1.121:10000/authentication-service/auth/id?token=" + token))
                        .timeout(Duration.ofSeconds(5))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                var resbody = response.body();
                System.out.println(resbody);

            } catch (IOException e) {
                System.out.println("io execption" + e.toString());
            } catch (InterruptedException e) {
                System.out.println("interrupted execption" + e.toString());
            }
        }

        return chain.filter(exchange);
    }

    /**
     * filter 顺序
     * @return -100
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
