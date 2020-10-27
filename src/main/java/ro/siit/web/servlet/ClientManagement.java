package ro.siit.web.servlet;

import ro.siit.web.entity.Client;
import ro.siit.web.model.ClientsStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            default:
                req.setAttribute("clients", store.getClients());
                req.getRequestDispatcher("/jsps/list.jsp").forward(req, resp);
        }
    }
}
