package com.himanshu.scheduler;

import com.himanshu.error.BadRequestError;
import com.himanshu.helpers.Helpers;
import com.himanshu.model.api.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SchedulerHelpers {
    private SchedulerHelpers() {}

    public static ExchangeFilterFunction errorHandler(){
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> {

                            Map<String, String> errMap = new HashMap<>();

                            errMap.put("status", String.valueOf(clientResponse.rawStatusCode()));
                            errMap.put("message", errorBody);

                            return Mono.error(new BadRequestError(errMap));
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
    public static String callApi(Api api){
            Mono<String> res = WebClient.builder()
                    .build()
                    .method(Helpers.getHttpMethod(api.getMethod()))
                    .uri(api.getUrl())
                    .headers(httpHeaders -> {
                        if( api.getHeaders() == null || api.getHeaders().isEmpty()) return;

                        api.getHeaders().forEach((key, val) -> {
                            httpHeaders.set(key, val);
                        });
                    })
                    .body(BodyInserters.fromValue(Objects.requireNonNullElse(api.getBody(), new HashMap<>())))
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new Exception("Bad Request !")))
                    .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new Exception("Internal Server Error !")))
                    .bodyToMono(String.class);

            String responseBody = res.block();

            return responseBody;
    }

    public static void  logApiResults( Api api, Map<String, String> message){

        final String logMessage = api.getMethod() +  " " + api.getUrl() + " : Status '" + message.get("status") + "' result " + message.get("result") ;

        System.out.println(logMessage);
    }
}
