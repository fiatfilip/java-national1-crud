package ro.siit.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.siit.web.entity.Client;
import ro.siit.web.model.ClientsStore;
import ro.siit.web.model.DBClientsStore;
import ro.siit.web.model.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet(urlPatterns = {"/clients"})
public class ClientManagement extends HttpServlet {
    private Store store;
    private String uploadPath;
    static final Logger logger = LogManager.getLogger(ClientManagement.class.getName());
    @Override
    public void init() {
        store = new DBClientsStore();
        uploadPath = getServletContext().getRealPath("") + File.separator + "UPLOAD_DIRECTORY";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
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
                Part avatar =  req.getPart("avatar");
                UUID clientId = UUID.randomUUID();
                String avatarName = avatar.getSubmittedFileName();
                String storedAvatarName = clientId + avatarName.substring(avatarName.indexOf("."));
                avatar.write(uploadPath + File.separator + storedAvatarName);

                store.addClient(new Client(UUID.randomUUID(), name, phone, avatarName));
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
