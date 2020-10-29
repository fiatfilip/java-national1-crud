package ro.siit.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ro.siit.web.entity.Client;
import ro.siit.web.model.ClientsStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@WebServlet(urlPatterns = {"/clients/api"})
public class ClientManagementApi extends HttpServlet {
    private ClientsStore store;
    @Override
    public void init() {
        store = new ClientsStore();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = store.getClients();
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonClients = gson.toJson(clients);
        resp.getWriter().write(jsonClients);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));

        String json = null;
        if(br != null){
            json = br.readLine();
        }
        System.out.println(json);

        Gson gson = new Gson();
        Client client = gson.fromJson(json, Client.class);

        Client newClient = new Client(client.getName(), client.getPhoneNumber());

        resp.setContentType("application/json");
        String jsonClients = gson.toJson(newClient);
        resp.getWriter().write(jsonClients);
    }
}
