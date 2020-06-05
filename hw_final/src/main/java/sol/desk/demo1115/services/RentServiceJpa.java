package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Rent;
import sol.desk.demo1115.repositories.RentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class RentServiceJpa implements RentService{

    @Autowired
    private RentRepository rentRepository;

    @Override
    public List<Rent> findAll() {
        return this.rentRepository.findAll();
    }

    @Override
    public Rent startRent(Rent rent) {
        return this.rentRepository.save(rent);
    }

    @Override
    public Rent findByBicycleRentId(Long bicycleRentId) {
        return this.rentRepository.findByBicycleRentId(bicycleRentId);
    }

    @Override
    public void returnCycle(Rent rent) {
        Optional <Rent> rentDb = Optional.ofNullable(this.rentRepository.findByBicycleRentId(rent.getBicycleRentId()));
        Rent rentUpdate = rentDb.get();
        rentUpdate.setRentPeriod(rent.getRentPeriod());
        rentUpdate.setReturnDateTime(rent.getReturnDateTime());
        rentUpdate.setRentPrice(rent.getRentPrice());
        rentUpdate.setUsingNow(rent.isUsingNow());
        rentRepository.returnBicycle(rent.getRentPeriod(), rent.getRentPrice(), rent.getReturnDateTime(), rent.isUsingNow());
    }
}
