/**
 * Created by vitaliy on 24.05.16.
 */

function deleteUser(button) {
    var id = button.id;
    $.ajax({
        url: "deletUser",
        contentType: "application/json",
        data: id,
        type: "DELETE",
        success: function () {
            alert("User Delet");
            allUser();

        },
        error: function (str, status, error) {
            alert("My Error : " + status + ", "+ error);
        }
    });
}

function allUser() {
    $.getJSON("users", function (data) {
        $("#user").find("tr:not(:first)").remove();
        for (var i in data){
            $("#user").append("<tr><td>" + data[i].id + "</td>" +
                "<td>" + data[i].name + "</td>" +
                "<td>" + data[i].password + "</td>" +
                "<td>" + data[i].mail + "</td>"+
                "<td>" + "<input type='button' id=" + data[i].id +" value='Delete' onclick='deleteUser(this)'/>"+
                "<input type='button' id=" + data[i].id +" value='Edit' onclick='editUser(this)'/></td></tr>");
        }

    });
}

function addUser() {
   
    var user = {
        id: $("#userID").val(),
        name : $("#userName").val(),
        password : $("#userPassword").val(),
        mail : $("#userMail").val()
    };

    $.ajax({
        url: "addUser",
        contentType: "application/json",
        data: JSON.stringify(user),
        type: "POST",
        success: function () {
            allUser();
            $("#user").show();
           /* alert("New" + user.name + "save");*/

        },
        error: function (str, status, error) {
            alert("My Error : " + status + ", "+ error);
        }
    });
}

function editUser(button) {
    $("#btn-addUser").val("Save User");
    var id = button.id;
    $.ajax({
        url: "editUser",
        contentType: "application/json",
        data: id,
        type: "POST",
        success: function (data) {
            $("#panel-newUser").show();
            $("#userID").val(data.id);
            $("#userName").val(data.name);
            $("#userPassword").val(data.password);
            $("#userMail").val(data.mail);
        },
        error: function (str, status, error) {
            alert("My Error : " + status + ", "+ error);
        }
    });
}

function viwImeg() {
    $.getJSON("showImages", function (data) {
        $("#imeg-test").find("div:not(:first)").remove();
        for (var i in data){
            $("#imeg-test").append(
                "<div><p>" + data[i].id + "</p>"+
                "<p>" + data[i].name + "</p>"+
                "<image src= data:image/jpeg;base64," + data[i].imeg + "></image></div>"
            );
        }
    });

}

function searchUser() {
    $("#serch-result").text("");
    $.getJSON("serchUser",
        {CHARS: $("#serch-input").val()},
        function (data) {

            for (var i in data){
                $("#serch-result").append("<p>" + data[i].name + "</p>" +
                    "<p>" + data[i].mail + "</p>" );
            }

        });
}

