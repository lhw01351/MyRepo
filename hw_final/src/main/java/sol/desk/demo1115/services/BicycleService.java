package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sol.desk.demo1115.models.Bicycle;

import java.util.List;

public interface BicycleService {
    List<Bicycle> findAll();
    Page<Bicycle> findAll(Pageable pageable);
    List<Bicycle> find10cycles();
    Bicycle addBicycle(Bicycle bicycle);
    void deleteByBicycleId(int bicycleId);
    Bicycle findByBicycleId(Long bicycleId);

}
