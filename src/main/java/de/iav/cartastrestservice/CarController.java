package de.iav.cartastrestservice;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/car")
public class CarController {


    private final CarRepo carRepo;

    @Inject public CarController(CarRepo cp){ this.carRepo = cp;}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCars() {
        //return this.listOfCars;
        return "Hi! Nothing happens here. " + String.valueOf(this.carRepo.size());
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String addCar(@QueryParam("brand") String brand, @QueryParam("type") String type, @QueryParam("color") String color){
        this.carRepo.add(new Car(brand, type, color));
        return String.valueOf(this.carRepo.size());
    }

    @POST
    @Path("/addjs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Car addCarByJson(Car carToAdd){
        return carToAdd;
    }

}
