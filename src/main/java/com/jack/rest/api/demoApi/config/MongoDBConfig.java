package com.jack.rest.api.demoApi.config;



import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository){
        return strings -> {
            usersRepository.save(new Users(1 ,"Mayank", "Moradabad", "Jack@123", "mayank@gmail.com"));
            usersRepository.save(new Users(2,"jack", "bangalore", "maya@123", "jack@gamil.com"));
        };
    }
}
