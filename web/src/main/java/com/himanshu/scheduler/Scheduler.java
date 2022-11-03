package com.himanshu.scheduler;

import com.himanshu.api.ApiRepository;
import com.himanshu.model.api.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class Scheduler {

    private ApiRepository apiRepository;

    @Autowired
    public Scheduler(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Scheduled(cron = "* * * * * *")
    public void cronJobSch() {
        final List<Api> apiList = apiRepository.findAll();

        apiList.forEach((api) -> {
            CompletableFuture
                    .supplyAsync(() -> {
                        try {

                            Map<String, String> response = new HashMap<>();

                            response.put("status", "Success");
                            response.put("result", SchedulerHelpers.callApi(api));

                            return response;
                        }catch(Exception err){
                            Map<String, String> response = new HashMap<>();

                            response.put("status", "Failure");
                            response.put("result", err.getMessage());

                            return response;
                        }
                    })
                    .thenAccept(result -> {
                        SchedulerHelpers.logApiResults(api, result);
                    });
        });





    }
}
