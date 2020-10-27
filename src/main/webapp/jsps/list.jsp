<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
            <c:forEach var="client" items="${requestScope.clients}">
                <tr>
                    <td>
                       <c:out value="${client.name}"/>
                    </td>
                    <td>
                        <c:out value="${client.phoneNumber}"/>
                    </td>
                    <td>
                        Edit | Delete
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
