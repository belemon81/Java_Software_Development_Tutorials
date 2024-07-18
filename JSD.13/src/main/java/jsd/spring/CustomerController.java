package jsd.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerController {
    @Autowired
    private CustomerManager customerManager;

    public void add(String name, String phone) {
        customerManager.addCustomer(new Customer(name, phone));
    }

    public void remove(String name) {
        Customer customer = customerManager.findCustomerByName(name);
        if (customer != null) {
            customerManager.removeCustomer(customer);
        }
    }

    public String index() {
        StringBuilder sb = new StringBuilder();
        for (Customer c : customerManager.getCustomers()) {
            sb.append(c.toString()).append("; ");
        }
        return sb.toString();
    }
}
