package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomerServiceJpa implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean authenticate(String customer_id, String customer_login_password) {
        return false;
    }

    @Override
    public List<Customers> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Page<Customers> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customers findByCustomerId(String customer_id) {
        return this.customerRepository.findByCustomerId(customer_id);
    }
    @Override
    public Customers findByPhoneNum(String phoneNum) {
        return this.customerRepository.findByPhoneNum(phoneNum);
    }
    @Override
    public Customers findByCustomerEmail(String customerEmail) {
        return this.customerRepository.findByCustomerEmail(customerEmail);
    }

    @Override
    public Customers findById(Long id) {
        return this.customerRepository.getOne(id);
    }

    /*@Override
    public Customers create(Customers customers) {
        customers.setCustomer_login_password(bCryptPasswordEncoder.encode(customers.getCustomer_login_password()));
        return this.customerRepository.save(customers);
    }*/

    @Override
    public Customers create(Customers customers) {
        customers.setCustomer_login_password(bCryptPasswordEncoder.encode(customers.getCustomer_login_password()));
        customers.setCustomer_login_password_check(bCryptPasswordEncoder.encode(customers.getCustomer_login_password_check()));
        customers.setRental_password(bCryptPasswordEncoder.encode(customers.getRental_password()));
        return this.customerRepository.save(customers);
    }

    @Override
    public Customers edit(Customers customers) {
        return this.customerRepository.save(customers);
    }

    @Override
    public void deleteById(Long id) {
        this.customerRepository.deleteById(id);

    }

}
