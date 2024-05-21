package coffeeShop.services;

import coffeeShop.models.Customer;
import coffeeShop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Boolean isUniqueEmail(String email){
        boolean isUnique = true;
        List<Customer> customers = this.customerRepository.findAll();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    public void add(String name, String email) {
            Customer customer = new Customer(name, email);
            this.customerRepository.save(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
