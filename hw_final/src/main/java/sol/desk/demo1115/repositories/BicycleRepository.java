package sol.desk.demo1115.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sol.desk.demo1115.models.Bicycle;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Integer> {

    @Query(value = "select * from bicycle", nativeQuery = true)
    List<Bicycle> findAll();

    @Modifying
    @Query(value = "insert into bicycle(bicycle_address, bicycle_latitude, bicycle_longitude, bicycle_model, bicycle_price, bicycle_reg_date)" +
            " values(:bicycle_address, :bicycle_latitude, :bicycle_longitude, :bicycle_model, :bicycle_price, :bicycle_reg_date)", nativeQuery = true)
    void addBicycle(@Param("bicycle_address") String bicycleAddress, @Param("bicycle_latitude") double bicycleLatitude,
                    @Param("bicycle_longitude") double bicycleLongitude, @Param("bicycle_model") double bicycleModel,
                    @Param("bicycle_price") double bicyclePrice, @Param("bicycle_reg_date") Date bicycleRegDate);

    @Query(value = "select * from bicycle", nativeQuery = true)
    List<Bicycle> find10cycles(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value="delete from bicycle where bicycle_Id=:bicycle_Id", nativeQuery = true)
    void deleteByBicycleId(@Param("bicycle_Id") int bicycleId);

    //이거 필요?
    @Query(value = "select * from bicycle", nativeQuery = true)
    Bicycle findByBicycleId(Long bicycleId);
}
