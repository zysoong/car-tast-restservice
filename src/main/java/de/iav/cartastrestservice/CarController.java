package de.iav.cartastrestservice;


import jakarta.annotation.ManagedBean;
import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/car")
public class CarController {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCars() {
        //return this.listOfCars;
        return "Hi! Nothing happens here. ";
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Car addCar(@QueryParam("brand") String brand, @QueryParam("type") String type, @QueryParam("color") String color){
        return new Car(brand, type, color);
    }

    @POST
    @Path("/addjs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Car addCarByJson(Car carToAdd){
        return carToAdd;
    }

}
