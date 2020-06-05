package sol.desk.demo1115.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sol.desk.demo1115.models.Rent;

import java.util.Date;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {

    @Query(value = "select * from rent", nativeQuery = true)
    List<Rent> findAll();

    /*@Modifying
    @Query(value = "insert into rent(rent_date_time, using_now, bicycle_id,customer_id) values(:rent_date_time, :using_now, :bicycle_id,:customer_id)", nativeQuery = true)
    void startRent(@Param("rent_date_time") Date rentDateTime, @Param("using_now") boolean usingNow,
                   @Param("bicycle_id") int bicycleId, @Param("customer_id") String customerId);*/

    @Modifying
    @Query(value = "insert into rent(rent_date_time, using_now, bicycle_id,customer_id) values(:rent_date_time, :using_now, :bicycle_id,:customer_id)", nativeQuery = true)
    void startRent(@Param("rent_date_time") Date rentDateTime, @Param("using_now") boolean usingNow,
                   @Param("bicycle_id") int bicycleId, @Param("customer_id") long customerId);

    Rent findByBicycleRentId(Long bicycleRentId);

    @Transactional
    @Modifying
    @Query(value = "update rent set rent_period=:#{#rent_period}, rent_price=:#{#rent_price}, return_date_time=:#{#return_date_time}, using_now=:#{#using_now} where bicycle_rent_id=:#{#bicycle_rent_id}", nativeQuery = true)
    void returnBicycle(@Param("rent_period") long rentPeriod, @Param("rent_price") int rentPrice,
                       @Param("return_date_time") Date returnDateTime, @Param("using_now") boolean usingNow);

}
