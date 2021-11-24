package com.example.demo.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserRepository userRepository;
    public ApiController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    //создать пользователя
    // curl -X POST http://localhost:8080/user  -H 'Content-Type: application/json' -d '{"username": "Max", "password": "abc", "age": "20")
    @PostMapping("user/{repeatPassword}")
    public User addUser(@RequestBody User user, @PathVariable String repeatPassword) {
        if (!repeatPassword.equals(user.getPassword())) {
        throw new BadRequestException();}
        Optional<User> userData =
                userRepository.findById(user.getId());
        if (userData.isPresent()) {  throw new ConflictException();}
        else {
            return userRepository.save(new User(user.getUsername(),
                    user.getPassword(),user.getAge()));
        }
    }
    //удалить пользователя по id
    // curl -X DELETE  http://localhost:8080/user?id=1 'Content-Type: text/plain'
    @DeleteMapping("users/{id}")
    public HttpStatus deleteTutorial(@PathVariable("id") long id) {
        Optional<User> UserData =
                userRepository.findById(id);
        if (UserData.isPresent()) {
            userRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
    }
    //обновить пользователя по id
    //curl -X PUT  http://localhost:8080/users/{id}  -H 'Content-Type: application/json' -d '{"username": "Max", "password": "abc", "age": "20")
    @PutMapping("users/{id}/{repeatPassword}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user, @PathVariable String repeatPassword) {
        if (!repeatPassword.equals(user.getPassword())) {
            throw new BadRequestException();}
        Optional<User> UserData =
                userRepository.findById(id);
        if (UserData.isPresent()) {
            User _user = UserData.get();
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            _user.setAge(user.getAge());
            return userRepository.save(_user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    //выдать пользователя по id
    //curl -X GET  http://localhost:8080/user/{id} 'Content-Type: text/plain'
    @GetMapping("users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        Optional<User> userData =
                userRepository.findById(id);
        if (userData.isPresent()) {
            return userData.get();
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //выдать список пользователей c параметром возраста
    //curl -X GET  http://localhost:8080/users?age=20
    @GetMapping("users")
    public List<User> getUsers(@RequestParam ("age") Integer age) {
        List<String> usersNames = new ArrayList<>();
        List<User> users = new ArrayList<User>();
        if (age == null) {
          return  userRepository.findAll();

        } else {
            return (List<User>) userRepository.findByAge(age-5,age+5);
        }
    }
}


