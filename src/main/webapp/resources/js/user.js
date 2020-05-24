let contextPath;

$(document).ready(function () {
    contextPath = $('meta[name="context-path"]').attr('content');
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });
    $('#btn-register').click(function (event) {
        registerNewUser($(this), event);
    })
    $('.users').on('click', '.btn-activate', function () {
        activateUser($(this));
    })
    $('.users').on('click', '.btn-deactivate', function () {
        deactivateUser($(this));
    })
    $('#btn-update').click(function (event) {
        event.preventDefault();
        updateUserData();
    });
    $('#btn-change-password').click(function (event) {
        event.preventDefault();
        changePassword();
    });
});

function registerNewUser(button, event) {
    event.preventDefault();

    let administrator = $('#administrator').length > 0;
    let user = {
        userName: $('#email').val(),
        firstName: $('#first-name').val(),
        lastName: $('#last-name').val(),
        password: $('#password').val(),
        password2: $('#password2').val(),
        administrator: administrator
    }

    $.ajax({
        url: contextPath+(administrator ? '/register/administrator' : '/register/user'),
        method: 'POST',
        data: JSON.stringify(user),
        contentType: 'application/json'
    }).done(function (result) {
        if (result === 'OK') {
            if (administrator) {
                window.location.href = '/user/administrators';
            } else {
                window.location.href = '/user/postRegister';
            }
        } else {
            alert(result);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log('Wystąpił błąd podczas rejestracji nowego użytkownika');
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
    });
}

function activateUser(element) {
    activateDeactivateUser(element, true);
}

function deactivateUser(element) {
    activateDeactivateUser(element, false);
}

function activateDeactivateUser(element, activate) {
    let userId = element.closest('.users-container').data('id');
    let url = contextPath+(activate ? '/user/activate/' : "/user/deactivate/");

    $.ajax({
        url: url+userId,
        method: 'POST'
    }).done(function (result) {
        if (result === 'OK') {
            if (activate) {
                element.removeClass('btn-activate');
                element.addClass('btn-deactivate');
                element.text('Deaktywuj');
                element.closest('.users-container').find('.active-user').text('true');
            } else {
                element.removeClass('btn-deactivate');
                element.addClass('btn-activate');
                element.text('Aktywuj');
                element.closest('.users-container').find('.active-user').text('false');
            }
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log('Wystąpił błąd podczas rejestracji nowego użytkownika');
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
    });
}

function updateUserData() {
    let userId = $('#user-id').val();
    let user = {
        id: userId,
        firstName: $('#first-name').val(),
        lastName: $('#last-name').val()
    }

    $.ajax({
        url: contextPath+'/user/change/'+userId,
        method: 'POST',
        data: JSON.stringify(user),
        contentType: 'application/json'
    }).done(function (result) {
        if (result === 'OK') {
            $('#logged-user-name').text($('#first-name').val());
            alert('Dane zostały uaktualnione');
        } else {
            alert(result);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
        alert('Błąd podczas zapisu danych. Proszę spróbować później');
    });
}

function changePassword() {
    let userId = $('#user-id').val();
    let newPassword = {
        userId: userId,
        currentPassword: $('#current-password').val(),
        newPassword: $('#new-password').val(),
        newPassword2: $('#new-password2').val()
    };

    $.ajax({
        url: contextPath+'/user/changePassword/'+userId,
        method: 'POST',
        data: JSON.stringify(newPassword),
        contentType: 'application/json'
    }).done(function (result) {
        if (result === 'OK') {
            alert('Hasło zostało zmienione');
        } else {
            alert(result);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
        alert('Błąd podczas zapisu danych. Proszę spróbować później');
    });
}
