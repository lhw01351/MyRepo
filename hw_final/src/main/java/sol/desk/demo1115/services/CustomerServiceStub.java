package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Customers;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceStub implements CustomerService {

    @Override
    public boolean authenticate(String customerId, String customerLoginPassword) {
        return Objects.equals(customerId, customerLoginPassword);
    }

    @Override
    public List<Customers> findAll() {
        return null;
    }

    @Override
    public Customers findById(Long id) {
        return null;
    }

    @Override
    public Customers create(Customers customers) {
        return null;
    }

    @Override
    public Customers edit(Customers customers) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public Customers findByCustomerId(String customerId) {
        return null;
    }

    @Override
    public Customers findByCustomerEmail(String customerEmail) {
        return null;
    }

    @Override
    public Customers findByPhoneNum(String phoneNum) {
        return null;
    }

    @Override
    public Page<Customers> findAll(Pageable pageable) {
        return null;
    }


}
