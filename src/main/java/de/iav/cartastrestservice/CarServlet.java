package de.iav.cartastrestservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ApplicationPath;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/v0/car/*")
public class CarServlet extends HttpServlet {

    private final CarRepo carRepo;

    @Inject
    public CarServlet(CarRepo cp){ this.carRepo = cp;}

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // http://localhost:8080/carshop/api/v0/car
        if (request.getPathInfo() == null)
        {
            response.getWriter().println("Nothing to get");
            return;
        }

        // http://localhost:8080/carshop/api/v0/car/example?id=3&name=ab
        if (request.getPathInfo().equals("/example"))
        {
            response.getWriter().println("Hi. Called from V0 API.");
            response.getWriter().println(request.getQueryString()); // id=3&name=ab
            response.getWriter().println(request.getParameter("name")); // ab -> Vorsicht! Wenn der Parameter nicht existiert, wird null zurückgegeben
            response.getWriter().println(request.getRequestURI()); // /carshop/api/v0/car/example
            response.getWriter().println(request.getContextPath()); // /carshop
            response.getWriter().println(request.getServletPath()); // /api/v0/car
            response.getWriter().println(request.getRequestURL());  //http://localhost:8080/carshop/api/v0/car/fjklwjr
            response.getWriter().println(request.getPathInfo()); // /example
        }

        // http://localhost:8080/carshop/api/v0/car/all
        else if (request.getPathInfo().equals("/all"))
        {
            response.getWriter().println(this.carRepo);
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // Convert request body JSON Object to StringBuilder: reading the response buffer line by line
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        reader.close();

        // Add ObjectMapper from Jackson Databind to parse JSON String to Java POJO Object
        ObjectMapper mapper = new ObjectMapper();


        try {
            // Read JSON String from the StringBuilder (request body) above, Parse the JSON String to Java POJO Object
            Car parsedCar = mapper.readValue(requestBody.toString(), Car.class);

            // Add the object to repository
            this.carRepo.add(parsedCar);

            // String of the given object as response
            response.setContentType("application/json");
            response.getWriter().write(mapper.writeValueAsString(parsedCar));

            // Also give the list of all cars as response
            //response.getWriter().write();

        } catch (UnrecognizedPropertyException e) {
            response.setStatus(400);
            response.getWriter().write("Invalid request body");
        }
    }

}
