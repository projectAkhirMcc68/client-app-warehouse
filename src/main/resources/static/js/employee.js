$(document).ready(function () {
    $('#table-employee').DataTable({
        ajax: {
            url: '/employee/getAll/',
            dataSrc: '',
            datatype:'json'
        },
        columns: [{
                data: 'id'
            },
            {
                data:'fullName'
            },
            {
                data: 'email'
            },
            {
                data: 'dateOfBirth'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailEmployeeModal"
                    onclick="getById(${data.id})"><i class="bi bi-card-heading"></i>
                </button>
                
                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateEmployeeModal"
                onclick="beforeUpdate(${data.id})"><i class="bi bi-pencil-square"></i></button>

                <button type="button" class="btn btn-danger" onclick="deleteEmployee(${data.id})"><i class="bi bi-trash3-fill"></i></button>
                `;
                }
            }
        ]
    });
})

function getById(id) {
    $.ajax({
        url: "employee/"+id,
        // method:"GET",
        type:"GET",
        dataType: "JSON",
        success: function (result) {
            $('#employee-id').text(`${result.id}`)
            $('#employee-fullName').text(`${result.fullName}`)
            $('#employee-email').text(`${result.email}`)
            $('#employee-date').text(`${result.dateOfBirth}`)
            $('#employee-username').text(`${result.user.username}`)
            
        }
    });
}

function beforeUpdate(id) {
    $.ajax({
        url: "employee/"+id,
        type:"GET",
        dataType: "JSON",
        success: function (result) {
            $('#updateId').val(`${result.id}`)
            $('#updateFullName').val(`${result.fullName}`)
            $('#updateEmail').val(`${result.email}`)
            $('#updateDate').val(`${result.dateOfBirth}`)
            
        }
    });
}

// function getByIdRole(id) {
//     $.ajax({
//         url: "role/getId/"+id,
//         method:"GET",
//         dataType: "JSON",
//         success: function (result) {
//             $('#employee-role').text(`${result.name}`)
//             console.log(`${result.name}`)
            
//         }
//     });
// }



function getRole() {
    $.ajax({
        url:"/role/getAll",
        method:'GET',
        dataType:'JSON',
        success:function(result){
            console.log(result)
            var text = `<option selected disabled>Select your region</option>x`;
            $.each(result,function(key,val) {
                text += `<option id="dropDown" value="${val.id}">${val.name}</option>`
            })
            $("#employeeRole").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
    
}

$('#create-employee').click(function (){
    var name =$('#employeeFullName').val()
    var email = $('#employeeEmail').val()
    var dateOfBirth = $('#employeeDate').val()
    var jenkel = $('#employeeJenisKelamin').val()
    var username = $('#employeeUsername').val()
    var password = $('#employeePassword').val()
    var roleId = $('#employeeRole').val()
    $.ajax({
        type: "POST",
        url: "employee/",
        dataType: "Json",
        beforeSend: setCSRFToken(),
        contentType:"application/json",
        data:JSON.stringify({
            fullName:name,
            email:email,
            dateOfBirth:dateOfBirth,
            jenisKelamin:jenkel,
            username:username,
            password:password,
            roleId : roleId
        }),
        success: function (response) {
            console.log("succees create")
            $('#createEmployee').modal('hide')
            $('#table-employee').DataTable().ajax.reload()
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: 'Region has been created',
                            showConfirmButton: false,
                            timer: 1500
                        })
        }
    });
})

$('#updateEmployee').click(function () { 
    let timerInterval
    Swal.fire({
    title: 'Updated!!',
    html: 'I will close in <b></b> milliseconds.',
    timer: 1000,
    timerProgressBar: true,
    didOpen: () => {
        Swal.showLoading()
        const b = Swal.getHtmlContainer().querySelector('b')
        timerInterval = setInterval(() => {
        b.textContent = Swal.getTimerLeft()
        }, 100)
    },
    willClose: () => {
        clearInterval(timerInterval)
    }
    }).then((result) => {
    let id = $('#updateId').val()
    let name =$('#updateFullName').val()
    let email = $('#updateEmail').val()
    let date = $('#updateDate').val()
    // let jenkel = $('#updateJenkel').val()
    $.ajax({
        url:"/employee/"+id,
        method:"PUT",
        dataType:'json',
        beforeSend: setCSRFToken(),
        contentType:"application/json",
        data:JSON.stringify({
            fullName:name,
            email:email,
            dateOfBirth:date,
            // jenisKelamin:jenkel
        }),
        success:function(result){
            console.log("success");
            $('#updateEmployeeModal').modal('hide')
            $('#table-employee').DataTable().ajax.reload()
        }
    })
    if (result.dismiss === Swal.DismissReason.timer) {
        console.log('I was closed by the timer')
    }
    })
})


function deleteEmployee(id){
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
                url: "employee/" + id,
                method: "DELETE",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                success: function (result) {
                    $('#table-employee').DataTable().ajax.reload()
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
    
