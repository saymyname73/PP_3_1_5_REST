$('document').ready(function () {

    // EDIT --------------------------------------------------
    let idEdit;

    $('.table .editBtn').click(function (event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user) {
            $('#idEdit').val(user.id);
            $('#nameEdit').val(user.name);
            $('#snEdit').val(user.surname);
            $('#ageEdit').val(user.age);
            $('#emailEdit').val(user.email);
            $('#roleEdit').val(user.roles);
            idEdit = user.id;
        });

        $('#editModal').modal();
    });

    $('#val1').submit(function (e) {
        e.originalEvent.currentTarget.action = $(this).attr('action') + idEdit;
    });

// DELETE ---------------------------------------------------
    let idDel;

    $('.table .delBtn').click(function (event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user) {
            $('#idDelete').val(user.id);
            $('#nameDelete').val(user.name);
            $('#snDelete').val(user.surname);
            $('#ageDelete').val(user.age);
            $('#emailDelete').val(user.email);
            $('#roleDelete').val(user.roles);
            idDel = user.id;
        });

        $('#deleteModal').modal();
    });

    $('#val2').submit(function (e) {
        e.originalEvent.currentTarget.action = $(this).attr('action') + idDel;
    });
});