package com.example.springbootexam.service;

import com.example.springbootexam.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootexam.model.User;

import java.util.List;
@Service
public class UserService {
   private final UserDAO userDAO;
   @Autowired
   public UserService (UserDAO userDAO){
       this.userDAO=userDAO;
   }
    public User insert(User user){return userDAO.insert(user);}
    public List<User> findAll(){return userDAO.findAll();}
    public User updateOccupation(String newOccupation, int id){return userDAO.updateOccupation(newOccupation, id);}
    public List<User> searchByOccupation(String occupation){return userDAO.searchByOccupation(occupation);}
    public void deleteByUsername(String username){userDAO.deleteByUsername(username);}
    public void deleteById(int id){userDAO.deleteById(id);}
    public User getById(int id){return userDAO.getById(id);}
}
