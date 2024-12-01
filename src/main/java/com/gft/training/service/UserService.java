package com.gft.training.service;

import java.util.Map;
import com.gft.training.repository.LocalRepository;
import com.gft.training.exception.*;
import com.gft.training.model.User;

public class UserService {
    private final static LocalRepository repository = LocalRepository.getInstance();
    private int nextId = 1; 

    // Add a new user
    public void addUser(String name, int age) throws UserValidationException {
        // Validar nombre
        if (name == null || name.trim().isEmpty()) {
            throw new UserValidationException("Name cannot be empty");
        }

        // Validar edad
        if (age < 0) {
            throw new UserValidationException("Age must be a positive number");
        }

        // Crear y guardar el usuario
        User user = new User(name, age);
        repository.save(nextId++, user);

        System.out.println("\n User " + name + " (age: " + age + ") successfully added");
    }


    // Get a user
    public User getUser(int id) {
        return repository.find(id);
    }

    // List all users
    public void listUsers() {
        System.out.println("\nList of existing users:");
        Map<Integer, User> users = repository.findAll();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", " + entry.getValue());
        }
    }

    // Delete a user and reorganise ids
    public void deleteUser(int id) throws NoUserException {
        User user = repository.find(id);
        if (user == null) {
            throw new NoUserException("User not found");
        }
        repository.delete(id);

        // Get rest of users and reorganise keys
        Map<Integer, User> users = repository.findAll();
        repository.clear();
        int newId = 1;

        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            repository.save(newId++, entry.getValue());
        }

        // Update next value
        nextId = newId;
        System.out.println("\n User " + user.getName() + " successfully deleted");
    }

    public Map<Integer, User> getAllUsers() {
        return repository.findAll();
    }
}
