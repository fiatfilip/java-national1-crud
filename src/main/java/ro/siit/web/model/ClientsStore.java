package ro.siit.web.model;

import ro.siit.web.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientsStore implements Store{

    private List<Client> clients = new ArrayList<>();

    public ClientsStore(){
        this.clients.add(new Client("Filip Fiat", "07456303070"));
        this.clients.add(new Client("Jean Claude Van-Dame", "0301456730"));
    }

    public Client getClient(UUID clientId){
        for (Client client: clients) {
            if(client.getId().equals(clientId)){
                return client;
            }
        }
        return null;
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public void updateClient(UUID clientId, Client updatedClient){
        for (Client client: clients) {
            if(client.getId().equals(clientId)){
                client.setName(updatedClient.getName());
                client.setPhoneNumber(updatedClient.getPhoneNumber());
                break;
            }
        }
    }

    public void deleteClient(UUID clientId){
        Client clientToDelete = null;
        for (Client client: clients) {
            if(client.getId().equals(clientId)){
                clientToDelete = client;
                break;
            }
        }

        if(clientToDelete != null) {
            clients.remove(clientToDelete);
        }
    }

    public List<Client> getClients() {
        List<Client> clientsList = new ArrayList<>();
        clientsList.addAll(clients);
        return clientsList;
    }
}
