package com.bes.boot.sample.extension;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class FakeValidator extends WebMvcConfigurationSupport {
    @Override
    public Validator mvcValidator() {
        return null;
    }
}
