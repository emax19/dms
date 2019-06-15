$(function() {

    $('.click-button').on('click', function(e) {
        id = $(this).data().id;
        method = $(this).data().method;
        relativeUrl = $(this).data().url;
        $.ajax({
                url: `/device/${id}/action-executor`,
                type: 'POST',
                data: {method, relativeUrl}
            })
            .done(function(){alert('request was sent')})
            .fail(function(){alert('request was rejected')})
    })
})