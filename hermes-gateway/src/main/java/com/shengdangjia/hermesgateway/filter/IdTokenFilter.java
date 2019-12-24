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

import java.nio.charset.StandardCharsets;

public class IdTokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (StringUtils.isEmpty(token)) {
            var resp = exchange.getResponse();
            resp.setStatusCode(HttpStatus.OK);
            resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");

            ResponseData d = RestHelper.makeResponse("无验证", ErrorCode.ERROR);
            var s = RestHelper.toJsonString(d);

            DataBuffer buffer = resp.bufferFactory().wrap(s.getBytes(StandardCharsets.UTF_8));
            return resp.writeWith(Mono.just(buffer));
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
