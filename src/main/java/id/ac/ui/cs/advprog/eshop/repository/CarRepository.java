package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;

    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        if(car.getId() == null){
            UUID uuid = UUID.randomUUID();
            car.setId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar) {
        Car car = findById(id);
        if (car != null) {
            car.setName(updatedCar.getName());
            car.setCarColor(updatedCar.getCarColor());
            car.setQuantity(updatedCar.getQuantity());
            return car;
        }
        return null;
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getId().equals(id));
    }
}

