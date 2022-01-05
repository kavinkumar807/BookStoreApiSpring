package com.codesimple.bookstore.common;


import com.codesimple.bookstore.config.JwtInterceptor;
import com.codesimple.bookstore.dto.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@Deprecated
public class CustomWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    @Deprecated
    private JwtInterceptor jwtInterceptor;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){

        //sort
        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("order-by");

        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver(sortResolver);
        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);
        Pageable defaultPageable = PageRequest.of(0, 5);
        pageResolver.setFallbackPageable(defaultPageable);

        argumentResolvers.add(pageResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
    }

    @Bean
    //@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
    @RequestScope
    public RequestMeta getRequestMeta(){
        return new RequestMeta();
    }

    @Bean
    public JwtInterceptor jwtInterceptors(){
        return new JwtInterceptor(getRequestMeta());
    }
}
