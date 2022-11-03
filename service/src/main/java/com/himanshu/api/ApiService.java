package com.himanshu.api;

import com.himanshu.dto.NewApi;
import com.himanshu.model.api.Api;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ApiService {
    public List<Api> getApis() throws Exception;

    public Map<String, String> addNewApi(NewApi newApi) throws Exception;

    public Map<String, String> deleteApi(String id) throws Exception;
}
