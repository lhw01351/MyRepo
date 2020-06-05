package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Rent;
import sol.desk.demo1115.repositories.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RentServiceStub implements RentService{

    @Autowired
    private RentRepository rentRepository;

    private List<Rent> rents = new ArrayList<Rent>();

    @Override
    public List<Rent> findAll() {
        return this.rents;
    }

    @Override
    public Rent startRent(Rent rent) {
        rent.setBicycleRentId(this.rents.stream().mapToLong(p -> p.getBicycleRentId()).max().getAsLong() + 1);
        this.rents.add(rent);
        return rent;
    }

    @Override
    public Rent findByBicycleRentId(Long bicycleRentId) {
        System.out.println("bicycleRentId in RentServiceStub: "+bicycleRentId);
        return this.rents.stream().filter(p -> Objects.equals(p.getBicycleRentId(), bicycleRentId)).findFirst().orElse(null);
    }

    @Override
    public void returnCycle(Rent rent) {
        this.rentRepository.returnBicycle(rent.getRentPeriod(), rent.getRentPrice(), rent.getReturnDateTime(), rent.isUsingNow());
    }
}
