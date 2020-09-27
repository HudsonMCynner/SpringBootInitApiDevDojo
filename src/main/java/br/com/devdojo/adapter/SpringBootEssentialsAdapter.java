package br.com.devdojo.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class SpringBootEssentialsAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageRequest pageRequest = PageRequest.of( 0, 10, Sort.Direction.ASC, "id");
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        phmar.setFallbackPageable(pageRequest);
        argumentResolvers.add(phmar);

    }
}
