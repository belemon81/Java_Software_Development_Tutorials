package jsd.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerManager {

    @Autowired
    private List<Customer> customerList;

//    public CustomerManager(List<Customer> customerList) {
//        this.customerList = customerList;
//    }

    public boolean addCustomer(Customer c) {
        return customerList.add(c);
    }

    public boolean removeCustomer(Customer c) {
        return customerList.remove(c);
    }

    public Customer findCustomerByName(String name) {
        return customerList.stream().filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
//        for (Customer c : customerList) {
//            if (c.getName().equals(name)) return c;
//        }
//        return null;
    }

    public Customer[] getCustomers() {
//        return (Customer[]) customerList.toArray();
        return customerList.toArray(new Customer[0]);
    }
}
