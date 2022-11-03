package com.himanshu.api;

import com.himanshu.model.api.Api;
import com.himanshu.dto.NewApi;
import com.himanshu.dto.ApiDeleteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/get-apis")
    public ResponseEntity getAllApis() throws Exception {
        final List<Api> responseList = apiService.getApis();

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PostMapping("/add-api")
    public ResponseEntity addNewApi(@Valid @RequestBody NewApi newApi) throws Exception{

        Map<String, String> responseMap = apiService.addNewApi(newApi);

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @DeleteMapping("/delete-api")
    public ResponseEntity deletedApi(@Valid @RequestBody ApiDeleteRequest apiDeleteRequest) throws Exception {

        Map<String, String> responseMap = apiService.deleteApi(apiDeleteRequest.getId());

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
