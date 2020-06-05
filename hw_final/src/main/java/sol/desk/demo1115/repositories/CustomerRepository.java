package sol.desk.demo1115.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sol.desk.demo1115.models.Customers;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

    Customers findByCustomerId(String customerId);

    Customers findByCustomerEmail(String customerEmail);

    Customers findByPhoneNum(String customerPhoneNum);

}


