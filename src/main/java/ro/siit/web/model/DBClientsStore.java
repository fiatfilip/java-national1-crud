package ro.siit.web.model;

import ro.siit.web.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBClientsStore implements Store{

    private Connection connection;
    public DBClientsStore(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/national1?user=postgres&password=postgres");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getClient(UUID clientId) {
        try {
            PreparedStatement getClient = connection.prepareStatement("SELECT * from clients WHERE id = ?");
            getClient.setObject(1, clientId);
            ResultSet clientResultSet = getClient.executeQuery();

            if (clientResultSet.next()){
                Client client =
                        new Client(UUID.fromString(clientResultSet.getObject(1).toString()),
                                clientResultSet.getString(2),
                                clientResultSet.getString(3));

                return client;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void addClient(Client client) {
        try {
            PreparedStatement insertClientStatement = connection.prepareStatement("INSERT INTO clients VALUES (?, ?, ?)");
            insertClientStatement.setObject(1, client.getId());
            insertClientStatement.setString(2, client.getName());
            insertClientStatement.setString(3, client.getPhoneNumber());
            insertClientStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateClient(UUID clientId, Client updatedClient) {
        try {
            PreparedStatement insertClientStatement = connection.prepareStatement("UPDATE clients SET name = ?, phone_number = ? WHERE id = ?");
            insertClientStatement.setString(1, updatedClient.getName());
            insertClientStatement.setString(2, updatedClient.getPhoneNumber());
            insertClientStatement.setObject(3, clientId);
            insertClientStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteClient(UUID clientId) {
        try {
            PreparedStatement getClient = connection.prepareStatement("DELETE from clients WHERE id = ?");
            getClient.setObject(1, clientId);
            getClient.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement getClients = connection.createStatement();
            ResultSet clientsResultSet = getClients.executeQuery("SELECT * from clients");
            while(clientsResultSet.next()){
                Client client =
                        new Client(UUID.fromString(clientsResultSet.getObject(1).toString()),
                                clientsResultSet.getString(2),
                                clientsResultSet.getString(3));
                clients.add(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }
}
