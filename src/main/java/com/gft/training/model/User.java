package com.gft.training.model;

public class User {

  public User(String name, int age) {
      this.name = name;
      this.age = age;
  }
  
  @Override
  public String toString() {
    return "User{" +
        "age = " + age +
        ", name = '" + name + '\'' +
        '}';
  }

  private int age;
  private String name;

  public void setAge(int age) {
    this.age = age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public String getName() {
    return this.name;
  }

  
}