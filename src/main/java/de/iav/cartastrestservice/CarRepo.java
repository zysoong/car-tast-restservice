package de.iav.cartastrestservice;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Named;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CarRepo {

    private final List<Car> listOfCar;

    public CarRepo() {
        this.listOfCar = new ArrayList<>();
    }

    public void add(Car c){this.listOfCar.add(c);}

    public int size(){return this.listOfCar.size();}

    @Override
    public String toString() {
        return "CarRepo{" +
                "listOfCar=" + listOfCar +
                '}';
    }
}
