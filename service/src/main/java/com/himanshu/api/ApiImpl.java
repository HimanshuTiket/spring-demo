package com.himanshu.api;

import com.himanshu.dto.NewApi;
import com.himanshu.error.BadRequestError;
import com.himanshu.helpers.Helpers;
import com.himanshu.model.api.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiImpl implements ApiService {

    private ApiRepository apiRepository;

    @Autowired
    public ApiImpl(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public List<Api> getApis() throws Exception {

        final List<Api> apis = apiRepository.findAll();

        return apis;
    }

    @Override
    public Map<String, String> addNewApi(NewApi newApi) throws Exception {

        Api newApiDocument = Api.build(new ObjectId().toString().toUpperCase(), newApi.getMethod(), newApi.getUrl(), newApi.getHeaders(), newApi.getBody());

        apiRepository.save(newApiDocument);

        final Map<String, String> responseMap = new HashMap<>();

        responseMap.put("message", "Api successfully registered !");

        return responseMap;
    }

    @Override
    public Map<String, String> deleteApi(String id) throws Exception {

        if(!Helpers.validateId(id)) throw new BadRequestError("Invalid Api Id");

        Optional<Api> existingApi = apiRepository.findById(id);

        if(existingApi.isEmpty()) throw new BadRequestError("Invalid Api Id");

        apiRepository.deleteById(id);

        Map<String, String> responseMap = new HashMap<>();

        responseMap.put("message", "Api successfully deleted !");

        return responseMap;
    }
}
