package ro.siit.web.servlet;

import ro.siit.web.entity.Client;
import ro.siit.web.model.ClientsStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/clients"})
public class ClientManagement extends HttpServlet {
    private ClientsStore store;
    @Override
    public void init() {
        store = new ClientsStore();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";

        switch (action){
            case "ADD":
                req.getRequestDispatcher("/jsps/add.jsp").forward(req, resp);
                break;
            case "EDIT":
                String id = req.getParameter("id");
                Client client = store.getClient(UUID.fromString(id));
                req.setAttribute("client", client);
                req.getRequestDispatcher("/jsps/edit.jsp").forward(req, resp);
                break;
            case "DELETE":
                id = req.getParameter("id");
                store.deleteClient(UUID.fromString(id));
                forwardToList(req, resp);
                break;
            default:
                forwardToList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = (action != null) ? action : "list";

        switch (action){
            case "ADD":
                String name = req.getParameter("name");
                String phone = req.getParameter("phoneNr");
                store.addClient(new Client(name, phone));
                forwardToList(req, resp);
                break;
            case "EDIT":
                String id = req.getParameter("id");
                name = req.getParameter("name");
                phone = req.getParameter("phoneNr");
                store.updateClient(UUID.fromString(id), new Client(name, phone));
                forwardToList(req, resp);
                break;
            default:
                forwardToList(req, resp);
        }
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", store.getClients());
        req.getRequestDispatcher("/jsps/list.jsp").forward(req, resp);
    }
}
