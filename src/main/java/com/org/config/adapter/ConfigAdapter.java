package com.org.config.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class ConfigAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
        pageResolver.setFallbackPageable(new PageRequest(0,10));
        argumentResolvers.add(pageResolver);
    }
}
