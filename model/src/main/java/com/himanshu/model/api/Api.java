package com.himanshu.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Document(collection = "apis")
public class Api {
    private String id;
    private String method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> body;
}
