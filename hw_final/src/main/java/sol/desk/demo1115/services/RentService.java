package sol.desk.demo1115.services;

import sol.desk.demo1115.models.Rent;

import java.util.List;

public interface RentService {
    List<Rent> findAll();
    Rent startRent(Rent rent);
    Rent findByBicycleRentId(Long bicycleRentId);
    void returnCycle(Rent rent);
}
