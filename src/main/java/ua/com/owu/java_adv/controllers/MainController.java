package ua.com.owu.java_adv.controllers;

import org.springframework.web.bind.annotation.*;
import ua.com.owu.java_adv.models.Car;
import ua.com.owu.java_adv.models.User;

import java.util.List;
import java.util.ArrayList;


@RestController
public class MainController {

    private List<User> users = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();

    public MainController() {
        users.add(new User(1, "kokos", true));
        users.add(new User(2, "ananas", true));
        users.add(new User(3, "banan", false));
        users.add(new User(4, "tomat", false));
        users.add(new User(5, "potatos", true));
        users.add(new User(6, "mango", false));

        cars.add(new Car(1, "audi", 350, 15000, 2019));
        cars.add(new Car(2, "mazda", 200, 9000, 2012));
        cars.add(new Car(3, "honda", 290, 12500, 2017));
        cars.add(new Car(4, "bmw", 320, 14500, 2020));
        cars.add(new Car(5, "renault", 270, 11000, 2016));
        cars.add(new Car(6, "toyota", 310, 17000, 2021));
        cars.add(new Car(7, "nissan", 250, 9000, 2015));
        cars.add(new Car(8, "ford", 280, 10500, 2018));
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/users")
    public List<User> users() {
        return users;
    }

    @GetMapping("/users/{id}")
    public User user(@PathVariable("id") int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @GetMapping("/user")
    public User getUserByParams(@RequestParam("userId") int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

    @PostMapping("/users")
    public List<User> saveUser(@RequestBody User user) {
        if (user != null) {
            users.add(user);
        } else {
            throw new RuntimeException("no user");
        }
        return users;
    }

    @PutMapping("/users/{id}")
    public List<User> putUser(@PathVariable("id") int id, @RequestBody User user) {
        User findUser = users.stream().filter(item -> item.getId() == id).findFirst().get();
        int indexOf = users.indexOf(findUser);
        users.set(indexOf, user);
        return users;
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable("id") int id) {
        users.removeIf(user -> user.getId() == id);
        return users;
    }


    

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return cars;
    }

    @GetMapping("/cars/{id}")
    public Car getById(@PathVariable("id") int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst().get();
    }

    @PostMapping("/cars")
    public List<Car> postCar(@RequestBody() Car car) {
        cars.add(car);
        return cars;
    }

    @PutMapping("/cars/{id}")
    public List<Car> putCar(@PathVariable("id") int id, @RequestBody() Car car) {
        Car findCar = cars.stream().filter(item -> item.getId() == id).findFirst().get();
        int indexCar = cars.indexOf(findCar);
        cars.set(indexCar, car);
        return cars;
    }

    @DeleteMapping("/cars/{id}")
    public List<Car> deleteCar (@PathVariable("id") int id){
        cars.removeIf(car -> car.getId() == id);
        return cars;
    }
}
