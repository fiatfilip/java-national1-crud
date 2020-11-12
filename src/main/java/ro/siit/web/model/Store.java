package ro.siit.web.model;

import ro.siit.web.entity.Client;

import java.util.List;
import java.util.UUID;

public interface Store {
    Client getClient(UUID clientId);
    void addClient(Client client);
    void updateClient(UUID clientId, Client updatedClient);
    void deleteClient(UUID clientId);
    List<Client> getClients();
}
