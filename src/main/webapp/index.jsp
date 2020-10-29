<html>
    <head>
        <script>
            let getClients = function (){
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        const resp = JSON.parse(this.responseText);
                        const clientsList = document.getElementById("clients")
                        for(let entry in resp){
                            const listItem = document.createElement("li");
                            listItem.textContent = resp[entry].name + " " + resp[entry].phoneNumber
                            deleteLink = document.createElement("a");
                            deleteLink.text = "Delete"
                            deleteLink.addEventListener('click', deleteClient, resp[entry].id)
                            listItem.appendChild(deleteLink)
                            clientsList.appendChild(listItem)
                        }
                    }
                };
                xhttp.open("GET", "clients/api", true);
                xhttp.send();
            }

            function addClient(){
                const name= document.getElementById("name").value;
                const phoneNumber = document.getElementById("phoneNumber").value;
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        const resp = JSON.parse(this.responseText);
                        const clientsList = document.getElementById("clients")
                        const listItem = document.createElement("li");
                        listItem.textContent = resp.name + " " + resp.phoneNumber
                        deleteLink = document.createElement("a");
                        deleteLink.text = "Delete"
                        deleteLink.addEventListener('click', deleteClient, resp.id)
                        listItem.appendChild(deleteLink)
                        clientsList.appendChild(listItem)
                    }
                };
                xhttp.open("POST", "clients/api", true);

                reqbody = {
                    name,
                    phoneNumber,
                }

                xhttp.send(JSON.stringify(reqbody));

                return false;
            }

            function deleteClient(id){
                alert(id)
            }
        </script>
    </head>
    <body onload="getClients()">
        <form onsubmit="event.preventDefault(); addClient();" id="form" method="post" enctype="multipart/form-data">
            <label>Name</label>
            <input type="text" id="name">
            <label>Phone number</label>
            <input type="text" id="phoneNumber">
            <input type="submit" value="ADD">
        </form>
        <ul id="clients">

        </ul>
    </body>
</html>
