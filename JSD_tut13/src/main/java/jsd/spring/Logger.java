package jsd.spring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
//    @After("execution(* getCustomers())")
//    public void afterGettingCustomer() {
//        System.out.println("Got customer!");
//    }

    @Pointcut("execution(* addCustomer(..))")
    public void pc() {
        System.out.println("!");
    }

    @Before("pc()")
    public void prepareForAddingCustomer() {
        System.out.println("A new customer is adding...");
    }

}
