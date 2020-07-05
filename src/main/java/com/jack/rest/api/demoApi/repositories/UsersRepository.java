package com.jack.rest.api.demoApi.repositories;


import com.jack.rest.api.demoApi.documents.Users;
import org.apache.catalina.LifecycleState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, Integer> {

}
