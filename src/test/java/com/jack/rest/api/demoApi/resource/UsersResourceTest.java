package com.jack.rest.api.demoApi.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.exception.ExceptionResponse;
import com.jack.rest.api.demoApi.repositories.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersResource.class)
public class UsersResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersResource usersResource;

    List<Users> users = Arrays.asList(new Users(1, "jack", "bangalore", "1234", "jack@gmail.com")
    , new Users(2, "mayank", "delhi", "4321", "mayank@gmail.com"));

    Users usr = new Users(1, "alex", "tokyo", "1234", "alex@gmail.com");


    @Test
    public void testGatAll() throws Exception{
        Mockito.when(usersResource.getAll()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{id:1,name:jack,address:bangalore,email:jack@gmail.com}," +
                "{id:2,name:mayank,address:delhi,email:mayank@gmail.com}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

        Mockito.verify(usersResource).getAll();

    }

    @Test
    public void testGetUser() throws Exception{
        Mockito.when(usersResource.getUser( Mockito.anyInt())).thenReturn(Optional.of(usr));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users" + "/{id}", 1 ).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1, name:alex, address:tokyo, email:alex@gmail.com}";

        JSONAssert.assertEquals(expected, result.getResponse()
        .getContentAsString(), false);

        Mockito.verify(usersResource).getUser(1);

    }

    @Test
    public void deleteUser() throws Exception {

        Mockito.doNothing().when(usersResource).deleteUser(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users"+"/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(usersResource).deleteUser(1);

    }

    @Test
    public void updateUser() throws Exception{

    }

    @Test
    public void createUser() throws Exception{
        String uri = "/users";
        Users users = new Users(3, "jack", "mbd", "1111", "jack@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(users);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println("###########################"+status);
        assertEquals(200, status);

    }

}
