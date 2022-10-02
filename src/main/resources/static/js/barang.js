$(document).ready(function () {
    $('#table-barang').DataTable({
        ajax: {
            url: '/barang/getAll/',
            dataSrc: '',
            datatype: 'JSON'
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'nama'
            },
            {
                data: 'kategori'
            },
            {
                data: 'stok'
            },
            {
                data: 'harga'
            },
            {
                data: 'tanggal'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detailBarangModal"
                    onclick="getById(${data.id})"><i class="bi bi-card-heading"></i>
                    </button>
                    
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateBarang"
                    onclick="beforeUpdate(${data.id})"><i class="bi bi-pencil-square"></i></button>
                    
                    <button type="button" class="btn btn-danger" onclick="deleteBarang(${data.id})"><i class="bi bi-trash3-fill"></i></button>
                    `;
                }
            }
    ]
    });
})

function getById(id){
    $.ajax({
    url: "barang/"+id,
    method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#barang-id').text(`${result.id}`)
            $('#barang-name').text(`${result.nama}`)
            $('#barang-kategory').text(`${result.kategori}`)
            $('#barang-stock').text(`${result.stok}`)
            $('#barang-harga').text(`${result.harga}`)
            $('#barang-tanggal').text(`${result.tanggal}`)
        }
    });
}
    
 

$('#create-Barang').click(function () {
    let barangName = $('#barangName').val()
    let barangKategory = $('#barangKategory').val()
    let barangStock = $('#barangStock').val()
    let barangHarga = $('#barangHarga').val()
    let barangTanggal = $('#barangTanggal').val()
    $.ajax({
        url: "/barang/",
        method: "POST",
        dataType: "JSON",
        beforeSend: setCSRFToken(),
        contentType: "application/json",
        data: JSON.stringify({
            nama: barangName,
            kategori: barangKategory,
            stok: barangStock,
            harga: barangHarga,
            tanggal: barangTanggal
        }),
        success: function (result) {
            $('#createBarang').modal('hide')
            $('#table-barang').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Barang has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    });
})

function beforeUpdate(id){
    $.ajax({
        url:"barang/"+id,
        method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#updateId').val(`${result.id}`)
            $('#updateNama').val(`${result.nama}`)
            $('#updateKategori').val(`${result.kategori}`)
            $('#updateStok').val(`${result.stok}`)
            $('#updateHarga').val(`${result.harga}`)
            $('#updateTanggal').val(`${result.tanggal}`)
        }
    });
}   

$("#update-barang").click(function () {
    let id = $('#updateId').val();
    let name = $('#updateNama').val();
    let kategory = $('#updateKategori').val();
    let stock = $('#updateStok').val();
    let harga = $('#updateHarga').val();
    let tanggal = $('#updateTanggal').val();
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't update this barang!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/barang/"+id,
                method: "PUT",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    nama: name,
                    kategori: kategory,
                    stok: stock,
                    harga: harga,
                    tanggal: tanggal
                }),
                success: function (result) {
                    $('#updateBarang').modal('hide')
                    $('#table-barang').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Barang has been Updated',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            });
        }
    })
})
    
function deleteBarang(id){
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
                url: "/barang/" + id,
                method: "DELETE",
                dataType: "JSON",
                beforeSend: setCSRFToken(),
                success: function (result) {
                    $('#table-barang').DataTable().ajax.reload()
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
