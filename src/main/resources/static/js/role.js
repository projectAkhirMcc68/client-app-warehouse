$(document).ready(function () {
    $('#table-role').DataTable({
        ajax: {
            url: '/role/getAll/',
            dataSrc: '',
            datatype: 'JSON'
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'name'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailRoleModal"
                    onclick="getById(${data.id})"><i class="bi bi-card-heading"></i>
                    </button>
                    
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateroleModal"
                    onclick="beforeUpdate(${data.id})"><i class="bi bi-pencil-square"></i></button>
                    
                    <button type="button" class="btn btn-danger" onclick="deleteRole(${data.id})"><i class="bi bi-trash3-fill"></i></button>
                    `;
                }
            }
    ]
    });
})

function getById(id){
    $.ajax({
    url: "role/getId/"+id,
    method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#role-id').text(`${result.id}`)
            $('#role-name').text(`${result.name}`)
        }
    });
}
    

function beforeUpdate(id){
    $.ajax({
        url:"role/getId/"+id,
        method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#updateId').val(`${result.id}`)
            $('#updateRoleName').val(`${result.name}`)
        }
    });
}    

$('#create-role').click(function () {
    let roleName = $('#roleName').val();
    $.ajax({
        url: "/role",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify({
            name: roleName,
        }),
        success: function (result) {
            $('#createRole').modal('hide')
            $('#table-role').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Role has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    });
})

$("#update-role").click(function () {
    let id = $('#updateId').val();
    let name = $('#updateRoleName').val();
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
                url: "role/" + id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify({
                    name: name,
                }),
                success: function (result) {
                    $('#updateroleModal').modal('hide')
                    $('#table-role').DataTable().ajax.reload()
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

