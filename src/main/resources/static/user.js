$('document').ready(async function () {

    let response = await fetch('/user/info');
    let json;

    if (response.ok) {
        json = await response.json();
    } else {
        alert("Error HTTP: " + response.status);
    }

    $('#userId').text(json.id);
    $('#userName').text(json.name);
    $('#userSn').text(json.surname);
    $('#userAge').text(json.age);
    $('#userEmail').text(json.email);
    $('#mailHead').text(json.email);

    for (i in json.roles) {
        $('#rolesHead').append('<a>' + json.roles[i].name.slice(5) + ' ' + '</a>');
        $('#userRoles').append('<a>' + json.roles[i].name.slice(5) + ' ' + '</a>');
    }
});