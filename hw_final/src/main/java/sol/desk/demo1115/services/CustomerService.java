package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sol.desk.demo1115.models.Customers;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    boolean authenticate(String customerId, String customer_login_password);

    List<Customers> findAll();
    Page<Customers> findAll(Pageable pageable);
    Customers findByCustomerId(String customerId);
    Customers findByPhoneNum(String phoneNum);
    Customers findByCustomerEmail(String customerEmail);
    Customers findById(Long id);
    Customers create(Customers customers);
    Customers edit(Customers customers);
    void deleteById(Long id);

}
