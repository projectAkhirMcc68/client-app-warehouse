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
                
                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateCountry"
                onclick="beforeUpdate(${data.id}),getRegionUpdate()"><i class="bi bi-pencil-square"></i></button>

                <button type="button" class="btn btn-danger" onclick="deletePengajuan(${data.id})"><i class="bi bi-trash3-fill"></i></button>
                `;
                }
            }
        ]
    });
})

function getById(id) {
    $.ajax({
        url: "pengajuan/getId/" + id,
        method: "GET",
        dataType: "JSON",
        success: function (result) {
            $('#pengajuan-id').text(`${result.id}`)
            $('#pengajuan-tanggal').text(`${result.tanggal}`)
            $('#pengajuan-user').text(`${result.user.username}`)
            $('#pengajuan-status').text(`${result.status.name}`)
        }
    });
}


function getHistory(id) {
    $.ajax({
        url:"/history/getId/"+id,
        method:'GET',
        dataType:'JSON',
        success:function(result){
            console.log(result)
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

// $.ajax({
//     url: "/history/getId"+id,
// }).done((result)=>{
//     console.log(result.results);
//     var text = "";
//     $.each(result.results,function(key,val){
//         text += `<tr>
//                     <td>${key+1}</td>
//                     <td>${val.name}</td>
//                     <td><button data-toggle="modal" onclick="detailPoke('${val.url}')" data-target="#modalPoke" class="btn btn-warning">Detail</button></td>
//                 </tr>`;
//     })
//     $("#tbodyPoke").html(text);
// }).fail((error)=>{
//     console.log(error);
// });

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
