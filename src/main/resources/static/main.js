$('document').ready(function () {

    const head = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    };

    let users_url = '/admin/users';
    let info_url = '/admin/info';

    //HEAD---------------------------------------------

    $(async function () {

        let response = await fetch(info_url);
        let json;

        if (response.ok) {
            json = await response.json();
        } else {
            alert("Error HTTP: " + response.status);
        }

        $('#mailHead').text(json.email);
        $('#adminId').text(json.id);
        $('#adminName').text(json.name);
        $('#adminSN').text(json.surname);
        $('#adminAge').text(json.age);
        $('#adminEmail').text(json.email);

        for (i in json.roles) {
            $('#rolesHead, #adminRoles').append('<a>' + json.roles[i].name.slice(5) + ' ' + '</a>');
        }

        await getTableWithUsers();
    });

    //USERS TABLE---------------------------------------------

    async function getTableWithUsers() {
        $('#nav-table tbody').empty();

        await fetch(users_url)
            .then(res => res.json())
            .then(users => {
                users.forEach(user => {
                    let roles = "";
                    for (j in user.roles) {
                        roles += user.roles[j].name.slice(5) + ' ';
                    }
                    let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.age}</td> 
                            <td>${user.email}</td>
                            <td>${roles}</td>
                            <td>
                                <button type="button" data-userid="${user.id}" data-action="edit" class="btn btn-info text-white editBtn" 
                                data-toggle="modal" data-target="#editModal">Edit</button>
                            </td>
                            <td>
                                <button type="button" data-userid="${user.id}" data-action="delete" class="btn btn-danger text-white delBtn" 
                                data-toggle="modal" data-target="#deleteModal">Delete</button>
                            </td>
                        </tr>
                )`;
                    $('#users').append(tableFilling);
                });
            });
    }

    //SERIALIZE USER ---------------------------------------------

    let userJ = {};

    function serializeForm(formNode) {
        const {elements} = formNode
        Array.from(elements)
            .filter((item) => !!item.name)
            .forEach((element) => {

                if (element.name === 'roles') {
                    let vall = element.value;

                    userJ[element.name] = [{
                        "id": vall
                    }];
                } else {
                    userJ[element.name] = element.value;
                }
            });
    }

    //SEND ADD FORM ------------------------------------------------

    const applicantForm = document.getElementById('newUser')
    applicantForm.addEventListener('submit', handleFormSubmit);

    async function handleFormSubmit(event) {
        event.preventDefault()
        serializeForm(applicantForm)
        const resp = await fetch('/admin/create', {
            method: 'POST', body: JSON.stringify(userJ), headers: head
        });
        if (resp.ok) {
            await getTableWithUsers();
            $('#profile').removeClass('show active')
            $('#nav-table').addClass('show active')
            $('#newBtn').removeClass('active')
            $('#usersBtn').addClass('active')
        } else {
            let body = await resp.json();
            alert("Error HTTP: " + body.status);
        }
    }

    //FILLING MODAL FORM ------------------------------------------------

    let buttonUserId;

    $("#users").on('click', 'button', function (event) {
        let targetButton = $(event.target);
        buttonUserId = targetButton.attr('data-userid');
        $.getJSON('/admin/' + buttonUserId, function (user) {
            $('#idEdit, #idDelete').val(user.id);
            $('#nameEdit, #nameDelete').val(user.name);
            $('#snEdit, #snDelete').val(user.surname);
            $('#ageEdit, #ageDelete').val(user.age);
            $('#emailEdit, #emailDelete').val(user.email);
            $('#roleEdit, #roleEdit').val(user.roles);
        });
    });

    //SEND EDIT FORM ------------------------------------------------

    const editForm = document.getElementById('val1')
    editForm.addEventListener('submit', editFormSubmit);

    async function editFormSubmit(event) {
        event.preventDefault()
        serializeForm(editForm)
        const resp = await fetch('/admin/edit/' + buttonUserId, {
            method: 'PATCH', body: JSON.stringify(userJ), headers: head
        });
        if (resp.ok) {
            await getTableWithUsers();
            $('#editModal').modal('hide');
        } else {
            let body = await resp.json();
            alert("Error HTTP: " + body.status);
        }
    }

    //SEND DELETE FORM ------------------------------------------------

    const deleteForm = document.getElementById('val2')
    deleteForm.addEventListener('submit', deleteFormSubmit);

    async function deleteFormSubmit(event) {
        event.preventDefault()
        serializeForm(deleteForm)
        const resp = await fetch('/admin/delete/' + buttonUserId, {
            method: 'DELETE', headers: head
        });
        if (resp.ok) {
            await getTableWithUsers();
            $('#deleteModal').modal('hide');
        } else {
            let body = await resp.json();
            alert("Error HTTP: " + body.status);
        }
    }
});
