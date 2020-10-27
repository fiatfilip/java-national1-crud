<%@ page import="ro.siit.web.entity.Client" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <style>
            table, td {
                border: 1px solid black;
            }
            table {
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <th>Name</th>
                <th>Phone number</th>
                <th></th>
            </tr>
            <% for(Client client: (List<Client>)request.getAttribute("clients")){ %>
                <tr>
                    <td>
                       <%= client.getName()%>
                    </td>
                    <td>
                        <%=client.getPhoneNumber()%>
                    </td>
                    <td>
                        Edit | Delete
                    </td>
                </tr>
            <%} %>
        </table>
    </body>
</html>
