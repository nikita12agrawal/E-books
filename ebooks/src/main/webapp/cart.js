$(document).ready(function(){
    $('#btn').attr("disabled",true);
    $('.check').change(function(){
        $('#btn').attr('disabled',$('.check:checked').length==0);
    });
});

