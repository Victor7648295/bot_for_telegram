$('#modal-delete').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let dataUrl = button.attr('data-url');
    let dataName = button.attr('data-name');
    let dataId = button.attr('data-id');
    $('#idModalDelete').attr('href', dataUrl);
        $('#idTextDelete').text("Do you want to delete the city " + dataName + ' ?');
        $('#idButtonDelete').removeAttr('disabled');
});

$('#modal-update').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let dataId = button.attr('data-id');
    let dataName = button.attr('data-name');
    $('#idModalUpdate').val(dataId).attr('field', dataId);
    $('#idTextUpdate').text("Do you want to update the city " + dataName + ' ?');
});
