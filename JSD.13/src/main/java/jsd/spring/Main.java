package jsd.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        CustomerController controller = context.getBean(CustomerController.class);
        controller.add("Quan","0982496005");
        controller.add("Nam","0123456789");
        System.out.println(controller.index());
        controller.remove("Nam");
        System.out.println(controller.index());
    }
}
