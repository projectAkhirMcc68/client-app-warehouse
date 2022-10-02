$(document).ready(function () {
    $('#table-user').DataTable({
        ajax: {
            url: '/user/getAll/',
            dataSrc: '',
            datatype: 'JSON'
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'username'
            },
            {
                data: 'password'
            },
            {
                data: 'roles[0].name'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `                   
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateUser"
                    onclick="beforeUpdate(${data.id}),getRole()"><i class="bi bi-pencil-square"></i></button>
                    
                    <button type="button" class="btn btn-danger" onclick="deleteRole(${data.id})"><i class="bi bi-trash3-fill"></i></button>
                    `;
                }
            }
    ]
    });
})

function getRole(){
    $.ajax({
        url:"/role/getAll",
        method:'GET',
        dataType:'JSON',
        success:function(result){
            var text = `<option selected disabled>Select Status</option>`;
            $.each(result,function(key,val) {
                text += `<option id="dropDown" value="${val.id}">${val.name}</option>`
            })
            $("#updateRole").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
    
}

// function getById(id){
//     $.ajax({
//     url: "role/getId/"+id,
//     method:"GET",
//         datatype:'JSON',
//         success: function(result){
//             $('#role-id').text(`${result.id}`)
//             $('#role-name').text(`${result.name}`)
//         }
//     });
// }
    

function beforeUpdate(id){
    $.ajax({
        url:"user/"+id,
        method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#updateId').val(`${result.id}`)
            $('#updateUsername').val(`${result.username}`)
            $('#updatePassword').val(`${result.password}`)
            $('#updateRole').val(`${result.roles[0].name}`)
        }
    });
}    

$("#update-user").click(function () {
    let id = $('#updateId').val();
    let name = $('#updateUsername').val();
    let pass = $('#updatePassword').val();
    let role = $('#updateRole').val();
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't update this role!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "user/" + id,
                method: "PUT",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    username: name,
                    password:pass,
                    roleId:role
                }),
                success: function (result) {
                    $('#updateUser').modal('hide')
                    $('#table-user').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Role has been Updated',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            });
        }
    })
})
    
function deleteRole(id){
    Swal.fire({
        title: 'Are you sure?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
        $.ajax({
                url: "/role/" + id,
                method: "DELETE",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                success: function (result) {
                    $('#table-role').DataTable().ajax.reload()
                    let timerInterval
                        Swal.fire({
                        title: 'Delted!!',
                        timer: 1000,
                        timerProgressBar: true,
                        didOpen: () => {
                            Swal.showLoading()
                            timerInterval = setInterval(() => {
                            }, 100)
                        },
                        willClose: () => {
                            clearInterval(timerInterval)
                        }
                        }).then((result) => {
                           /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            console.log('I was closed by the timer')
                        }
                        })
                }
            })
        }
    })
}
