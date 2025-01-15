package com.example.springbootexam.service;

import com.example.springbootexam.model.Client;
import com.example.springbootexam.repository.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientDAO clientDAO;
    @Autowired
    public ClientService (ClientDAO clientDAO){
        this.clientDAO=clientDAO;
    }
    public Client insert(Client client){return clientDAO.insert(client);}
    public List<Client> findAll(){return clientDAO.findAll();}
    public Client getById(int id){return clientDAO.getById(id);}
    public void deleteById(int id){clientDAO.deleteById(id);}
    public Client updateClientName(String newClientName, int id){return clientDAO.updateClientName(newClientName, id);}
    public Client updatePhoneNumber(String newNumber, int id){return clientDAO.updatePhoneNumber(newNumber, id);}
    public List<Client> searchClient(String client){return clientDAO.searchCart(client);}
}
