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
                data: 'nama_barang'
            },
            {
                data: 'kategory'
            },
            {
                data: 'stock'
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
    url: "barang/getId"+id,
    method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#barang-id').text(`${result.id}`)
            $('#barang-name').text(`${result.nama_barang}`)
            $('#barang-kategory').text(`${result.kategory}`)
            $('#barang-stock').text(`${result.stock}`)
            $('#barang-harga').text(`${result.harga}`)
            $('#barang-tanggal').text(`${result.tanggal}`)
        }
    });
}
    

function beforeUpdate(id){
    $.ajax({
        url:"barang/getId/"+id,
        method:"GET",
        datatype:'JSON',
        success: function(result){
            $('#updateId').val(`${result.id}`)
            $('#updateNamaBarang').val(`${result.nama_barang}`)
            $('#updateKategory').val(`${result.kategory}`)
            $('#updateStock').val(`${result.stock}`)
            $('#updateHarga').val(`${result.harga}`)
            $('#updateTanggal').val(`${result.tanggal}`)
        }
    });
}    
    



// function beforeUpdate(id){

// }