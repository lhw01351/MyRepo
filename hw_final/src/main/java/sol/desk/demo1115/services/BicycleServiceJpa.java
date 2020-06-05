package sol.desk.demo1115.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Bicycle;
import sol.desk.demo1115.repositories.BicycleRepository;

import java.util.List;

@Service
@Primary
public class BicycleServiceJpa implements BicycleService{

    @Autowired
    private BicycleRepository bicycleRepository;

    @Override
    public List<Bicycle> findAll() {
        return this.bicycleRepository.findAll();
    }

    @Override
    public Page<Bicycle> findAll(Pageable pageable) {
        return this.bicycleRepository.findAll(pageable);
    }

    @Override
    public List<Bicycle> find10cycles() {
        return this.bicycleRepository.find10cycles(PageRequest.of(0,10));
    }

    @Override
    public Bicycle addBicycle(Bicycle bicycle) {
        return this.bicycleRepository.save(bicycle);
    }

    @Override
    public void deleteByBicycleId(int bicycleId) {
        this.bicycleRepository.deleteByBicycleId(bicycleId);
    }

    @Override
    public Bicycle findByBicycleId(Long bicycleId) {
        return this.bicycleRepository.findByBicycleId(bicycleId);
    }
}
