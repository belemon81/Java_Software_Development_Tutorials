package jsd.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("jsd.spring")
@EnableAspectJAutoProxy
public class Config {
    @Bean
    public List<Customer> customerList() {
        return new ArrayList<>();
    }
}
