<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/94fe3f643e.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <style>
            /*
            table, td {
                border: 1px solid black;
            }
            table {
                border-collapse: collapse;
            }*/
        </style>
    </head>
    <body>
        <div class="myClass">Something</div>
        <div class="container">
            <a href="?action=ADD" style="color: aquamarine;font-weight: bold">ADD</a>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Phone number</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody></tbody>
                <c:forEach var="client" items="${requestScope.clients}">
                    <tr>
                        <td>
                           <c:out value="${client.name}"/>
                        </td>
                        <td>
                            <c:out value="${client.phoneNumber}"/>
                        </td>
                        <td>
                            <a href="?action=EDIT&id=${client.id}"><i class="far fa-edit"></i></a> |
                            <a href="?action=DELETE&id=${client.id}"><i class="far fa-minus-square"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
