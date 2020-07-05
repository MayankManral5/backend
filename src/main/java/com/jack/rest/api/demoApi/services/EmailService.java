package com.jack.rest.api.demoApi.services;

import com.jack.rest.api.demoApi.documents.Email;
import com.jack.rest.api.demoApi.documents.Response;

public interface EmailService {
    Response sendEmail(Email email);
}
