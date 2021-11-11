package ua.com.alevel.crud.service;

import ua.com.alevel.crud.dao.CarDao;
import ua.com.alevel.crud.dao.CarModelDao;
import ua.com.alevel.crud.dao.CarTypeDao;
import ua.com.alevel.crud.dto.CarDto;
import ua.com.alevel.crud.entity.Car;
import ua.com.alevel.crud.entity.CarType;

public class CarService {

    private CarDao carDao;
    private CarModelDao carModelDao;
    private CarTypeDao carTypeDao;

    public void addCarToGarage(CarDto carDto) {
        Car car = new Car();
        car.setHight(carDto.getHight());
        car.setWidth(carDto.getWidth());
        if (carDto.getCarType().getId() == null) {
            CarType carType = new CarType();
            carType.setName(carDto.getCarType().getName());
            carType = carTypeDao.create(carType);
            car.setCarType(carType);
        }
        carDao.create(car);
    }
}
