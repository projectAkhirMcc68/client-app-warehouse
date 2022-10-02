$(document).ready(function () {
    $('#table-pengajuan').DataTable({
        ajax: {
            url: '/pengajuan/getAll/',
            dataSrc: '',
            datatype:'json'
        },
        columns: [{
                data: 'id'
            },
            {
                data:'tanggal'
            },
            {
                data: 'user.username'
            },
            {
                data: 'status.name'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailPengajuanModal"
                    onclick="getById(${data.id}), getHistory(${data.id})"><i class="bi bi-card-heading"></i>
                </button>
                
                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updatePengajuanModal"
                onclick="beforeUpdate(${data.id}),getStatus()"><i class="bi bi-pencil-square"></i></button>`;
                }
            }
        ]
    });
})

function getById(id) {
    $.ajax({
        url: "pengajuan/" + id,
        method: "GET",
        dataType: "JSON",
        success: function (result) {
            $('#pengajuan-id').text(`${result.id}`)
            $('#pengajuan-tanggal').text(`${result.tanggal}`)
            $('#pengajuan-user').text(`${result.user.username}`)
            $('#pengajuan-status').text(`${result.status.name}`)
            $('#pengajuan-quantity').text(`${result.quantity}`)
            console.log(`${result.barang[0].id}`)
        }
    
    });
}


function getHistory(id) {
    $.ajax({
        url:"/history/getId/"+id,
        method:'GET',
        dataType:'JSON',
        success:function(result){
            var text ="";
            $.each(result,function(key,val) {
                text +=  `<tr>
                            <td>${key+1}</td>
                            <td>${val.date}</td>
                            <td>${val.status.name}</td>
                        </tr>`

            })
            $("#pengajuan-history").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
}


function getStatus(){
    $.ajax({
        url:"/status/getAll",
        method:'GET',
        dataType:'JSON',
        success:function(result){
            var text = `<option selected disabled>Select Status</option>`;
            $.each(result,function(key,val) {
                text += `<option id="dropDown" value="${val.id}">${val.name}</option>`
            })
            $("#updateStatusId").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
    
}

function getUser(){
    $.ajax({
        url:"/user/getAll",
        method:'GET',
        dataType:'JSON',
        success:function(result){
            var text = `<option selected disabled>Select User</option>`;
            $.each(result,function(key,val) {
                text += `<option id="dropDown" value="${val.id}">${val.username}</option>`
            })
            $("#createUserId").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
    
}

function getBarang(){
    $.ajax({
        url:"/barang/getAll",
        method:'GET',
        dataType:'JSON',
        success:function(result){
            var text = `<option selected disabled>Select Barang</option>`;
            $.each(result,function(key,val) {
                text += `<option id="dropDown" value="${val.id}">${val.nama}</option>`
            })
            $("#createBarangId").html(text);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }
    })
    
}

// start create
$('#btnCreatePengajuan').click(function (){
    let id = $('#createId').val()
    let date =$('#createDate').val()
    let userId =$('#createUserId').val()
    let status = $('#createStatusId').val()
    let quantity = $('#createQuantity').val()
    let barang = $('#createBarangId').val()
    $.ajax({
        type: "POST",
        url: "/pengajuan/",
        dataType: "Json",
        beforeSend: setCSRFToken(),
        contentType:"application/json",
        data:JSON.stringify({
            tanggal:date,
            userId:userId,
            statusId:1,
            quantity:quantity,
            barangId:barang
            
        }),
        success: function (response) {
            console.log("succees create")
            $('#createPengajuan').modal('hide')
            $('#table-pengajuan').DataTable().ajax.reload()
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
// end create

//Start proses update
function beforeUpdate(id) {
    $.ajax({
        url: "pengajuan/"+id,
        type:"GET",
        dataType: "JSON",
        success: function (result) {
            $('#updateId').val(`${result.id}`)
            $('#updateDate').val(`${result.tanggal}`)
            $('#updateUsername').val(`${result.user.username}`)
            $('#updateUserId').val(`${result.user.id}`)
            $('#updateQuantity').val(`${result.quantity}`)
            $('#updateBarangId').val(`${result.barang[0].id}`)
        }
    });
}

$('#btnUpdatePengajuan').click(function () { 
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
    let date =$('#updateDate').val()
    let userId =$('#updateUserId').val()
    let status = $('#updateStatusId').val()
    let quantity = $('#updateQuantity').val()
    let barang = $('#updateBarangId').val()
    $.ajax({
        url:"/pengajuan/"+id,
        method:"PUT",
        dataType:'json',
        beforeSend: setCSRFToken(),
        contentType:"application/json",
        data:JSON.stringify({
            id:id,
            tanggal:date,
            userId:userId,
            statusId:status,
            quantity:quantity,
            barangId:barang
        }),
        success:function(result){
            console.log("success");
            $('#updatePengajuanModal').modal('hide')
            $('#table-pengajuan').DataTable().ajax.reload()
        }
    })
    if (result.dismiss === Swal.DismissReason.timer) {
        console.log('I was closed by the timer')
    }
    })
})

// end proses update



function deletePengajuan(id){
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
                url: "pengajuan/" + id,
                method: "DELETE",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                success: function (result) {
                    $('#table-pengajuan').DataTable().ajax.reload()
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
