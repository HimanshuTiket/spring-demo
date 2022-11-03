package com.himanshu.api;

import com.himanshu.model.api.Api;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiRepository extends MongoRepository<Api, String> {
}
