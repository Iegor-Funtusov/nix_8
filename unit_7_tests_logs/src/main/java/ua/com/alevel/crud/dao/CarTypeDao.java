package ua.com.alevel.crud.dao;

import ua.com.alevel.crud.entity.CarType;

public class CarTypeDao {

    public CarType create(CarType carType) {
        return new CarType();
    }
}
