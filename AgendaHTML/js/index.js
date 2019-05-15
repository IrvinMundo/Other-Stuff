var contacts = [];
/*for(contact of contacts){
    console.log(contact);
}*/
    function initialize(){
        var addLi = document.getElementById("addOption").addEventListener("click", showhide);
        var deleteLi = document.getElementById("deleteOption").addEventListener("click", showhide);
        var editLi = document.getElementById("editOption").addEventListener("click", showhide);
        var searchLi = document.getElementById("searchOption").addEventListener("click", showhide);
        var displayLi = document.getElementById("displayOption").addEventListener("click", showhide);

    }

    function showhide(event){
        let id = "";
        const targetid = event.target.getAttribute('id');
        if( targetid == "addOption"){
            id = "add";
        } else if (targetid == "deleteOption") {
            id = "delete";
            displayContacts(contacts,"deleted");
        } else if (targetid == "editOption") {
            id = "edit"
            displayContacts(contacts,"edit");
        } else if (targetid == "searchOption") {
            id = "search";
        } else {
            id = "display";
            displayContacts(contacts,id);
        }

        if (document.getElementById) {
            var selected_div = document.getElementById(id);
            var hideable_divs = document.getElementsByClassName("hideable");
            console.log(hideable_divs.length);
            for(let i = 0; i < hideable_divs.length; i++) {
                hideable_divs[i].style.display = 'none';
            }
            selected_div.style.display = "block";
        }

        const section = event.target.getAttribute('href')
        console.log(section);
        window.scrollTo(section.offsetTop);
        return false;

    }

    function addContact () {

        let name = document.getElementById('nameAdd');
        let parental = document.getElementById('parentalAdd');
        let maternal = document.getElementById('maternalAdd');
        let telephone = document.getElementById('telephoneAdd');
        let address = document.getElementById('addressAdd');

        let  contact = [];
        contact[0] = name.value;
		contact[1] = parental.value;
		contact[2] = maternal.value;
		contact[3] = telephone.value;
		contact[4] = address.value;

        contacts.push(contact);
        contacts.sort();
		console.log(contacts);

		alert("The contact " + contact[0] + " " + contact[1] + " " + contact[2] + " has been added");
        name.innerHTML = "";
        parental.innerHTML = "";
        maternal.innerHTML = "";
        telephone.innerHTML = "";
        address.innerHTML = "";

	}

	function deleteContact (contact) {
        let index = indexofContact(contact);
        const el = contacts.splice(index, 1);
        console.log(el);
		return contacts;
	}

    function prepareEditContact (contact) {

        let editedDiv = document.getElementById("edited");
        editedDiv.style.display = 'none';

        let editedForm = document.getElementById("editForm");
        editedDiv.style.display = 'block';


        let index = indexofContact(contact);
        let indexEdit = document.getElementById("indexEdit");
        indexEdit.innerHTML = index;

        let name= document.getElementById("nameEdit");
        name.innerHTML = contacts[index][0];

        let parental= document.getElementById("parentalEdit");
        parental.innerHTML = contacts[index][1];

        let maternal= document.getElementById("maternalEdit");
        maternal.innerHTML = contacts[index][2];

        let telephone= document.getElementById("telephoneEdit");
        telephone.innerHTML = contacts[index][3];

        let address= document.getElementById("addressEdit");
        address.innerHTML = contacts[index][4];
    }
	function editContact () {

        let  editedcontact = [];
        editedcontact[0] = document.getElementById('nameEdit').value;
        editedcontact[1] = document.getElementById('parentalEdit').value;
        editedcontact[2] = document.getElementById('maternalEdit').value;
        editedcontact[3] = document.getElementById('telephoneEdit').value;
        editedcontact[4] = document.getElementById('addressEdit').value;

        const index = parseInt(document.getElementById('indexEdit'));
        contacts[index] = editedcontact;
	}

	function searchContact (contacts) {
        let  contact = [];
        contact[0] = document.getElementById('nameSearch').value;
		contact[1] = document.getElementById('parentalSearch').value;
		contact[2] = document.getElementById('maternalSearch').value;
		contact[3] = document.getElementById('telephoneSearch').value;
		contact[4] = document.getElementById('addressSearch').value;
        let searchedContacts = [];
        for ( c of contacts) {
            if(c[0].includes(contact[0]) || c[1].includes(contact[1]) || c[2].includes(contact[2]) || c[3].includes(contact[3]) || c[4].includes(contact[4]) ) {
                searchedContacts.append(c);
            }
        }
        displayContacts(searchedContacts, "searched");
	}

    function showContacts (contact) {
        let index = indexofContact(contact);
        let name= document.getElementById("nameShow");
        name.innerHTML = contacts[index][0];
        let parental= document.getElementById("parentalShow");
        parental.innerHTML = contacts[index][1];
        let maternal= document.getElementById("maternalShow");
        maternal.innerHTML = contacts[index][2];
        let telephone= document.getElementById("telephoneShow");
        telephone.innerHTML = contacts[index][3];
        let address= document.getElementById("addressShow");
        address.innerHTML = contacts[index][4];

    }

    function indexofContact(searchedContact) {
        let index = 0;
        for ( let contact of contacts ) {
            if ( contact === searchedContact ) {
                return index;
            }
            index++;
        } return -1;
    }

	function displayContacts (partialContacts, div_selected) {
		// This method prints the whole contacts [] [], which contains all the contacts
        // Obtener la referencia del elemento body
        let body = document.getElementById(div_selected);
        // Crea un elemento <table> y un elemento <tbody>
        let tabla   = document.createElement("table");
        tabla.id = `table_${div_selected}`;
        let tblHead = document.createElement("thead");
        // Crea las hileras de la tabla
        let hileraHead = document.createElement("tr");
        const headers = ["Name", "Parternal", "Maternal", "Telephone", "Address", "Delete", "Edit", "Show"];
        for (let h of headers) {
            // Crea un elemento <td> y un nodo de texto, haz que el nodo de
            // texto sea el contenido de <td>, ubica el elemento <td> al final
            // de la hilera de la tabla
            var celda = document.createElement("th");
            var textoCelda = document.createTextNode(h);
            celda.appendChild(textoCelda);
            hileraHead.appendChild(celda);
        }
        tblHead.appendChild(hileraHead);
        tabla.appendChild(tblHead);
        console.log(partialContacts)
        var tblBody = document.createElement("tbody");

        // Crea las celdas
        for (let contact of partialContacts) {
            // Crea las hileras de la tabla
            let hilera = document.createElement("tr");
            console.log(contact.length);
            for (let c of contact) {
                // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                // texto sea el contenido de <td>, ubica el elemento <td> al final
                // de la hilera de la tabla
                const celda = document.createElement("td");
                const textoCelda = document.createTextNode(c);
                celda.appendChild(textoCelda);
                hilera.appendChild(celda);
                console.log(c);

            }

            if(div_selected == "deleted" || div_selected == "display") {
                const celdaDelete = document.createElement("td");
                let bttnDelete = document.createElement("input");
                bttnDelete.id = 'button';
                bttnDelete.type = "button";
                bttnDelete.value = "Delete";
                bttnDelete.onclick = deleteContact(contact);
                celdaDelete.appendChild(bttnDelete);
                hilera.appendChild(celdaDelete);
            }



            if(div_selected == "edited" || div_selected == "display") {
                const celdaEdit = document.createElement("td");
                let bttnEdit = document.createElement("input");
                bttnEdit.id = 'button';
                bttnEdit.type = "button";
                bttnEdit.value = "Edit";
                bttnEdit.onclick = prepareEditContact(contact);
                celdaEdit.appendChild(bttnEdit);
                hilera.appendChild(celdaEdit);
            }

            if(div_selected == "display") {
                const celdaShow = document.createElement("td");
                let bttnShow = document.createElement("input");
                bttnShow.id = 'button';
                bttnShow.type = "button";
                bttnShow.value = "Show";
                bttnShow.onclick = showContacts(contact);
                celdaShow.appendChild(bttnShow);
                hilera.appendChild(celdaShow);
                console.log(hilera);
            }
            // agrega la hilera al final de la tabla (al final del elemento tblbody)
            tblBody.appendChild(hilera);
        }

        // posiciona el <tbody> debajo del elemento <table>
        tabla.appendChild(tblBody);
        // appends <table> into <body>
        body.appendChild(tabla);
        let goBack = document.createElement("input");
        goBack.type = "button";
        goBack.value = "Go back";
        goBack.onclick = toHome(tabla.id);
        body.appendChild(goBack);
        // modifica el atributo "border" de la tabla y lo fija a "2";
        tabla.setAttribute("border", "2");

    }

    function toHome(the_div) {
        console.log(the_div.id);
        if (the_div.id != "add" && the_div.id != "show") {
            console.log("entro");
            let removeTab = document.getElementById('the_div');
            let parentTab = removeTab.parentElement;
            parentTab.removeChild(removeTab);
        }

        let hideable_divs = document.getElementsByClassName("hideable");
        console.log(hideable_divs.length);

        for(let i = 0; i < hideable_divs.length; i++) {
            hideable_divs[i].style.display = 'none';
        }

        const section = document.getElementById("home");
        console.log(section);
        window.scrollTo(section.offsetTop);
    }
