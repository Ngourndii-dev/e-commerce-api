package com.example.springbootexam.repository;


import java.util.List;

public interface CrudOperation <T>{
   T insert(T t);
   List<T> findAll();
   T getById(int id);
   void deleteById(int id);
}
