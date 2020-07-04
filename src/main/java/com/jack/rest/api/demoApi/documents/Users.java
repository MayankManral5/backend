package com.jack.rest.api.demoApi.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Document(collection = "test")
public class Users {

    @Id
    @NotBlank(message = "must give user id")
    private Integer id;
    @NotNull
    @Size(min=2, message="The User Name must have contain two character")
    private String name;
    @NotNull
    @Size(min=4, max = 10, message="Address contain min two or maximum ten character")
    private String address;
    @NotNull
    @Size(min=4, max=8, message = "Password contain minimum 4 and maximum 8 character")
    @JsonIgnore
    private String password;
    @NotBlank(message = "must provide email")
    @Email(message = "Email should be right email format")
    private String email;

    public Users(){

    }

    public Users(Integer id, String name, String address, String password, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
