package com.example.demo.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface  UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
    User findByAge(Integer start, Integer end);
}
