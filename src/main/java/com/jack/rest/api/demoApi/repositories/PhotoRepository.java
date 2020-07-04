package com.jack.rest.api.demoApi.repositories;

import com.jack.rest.api.demoApi.documents.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
