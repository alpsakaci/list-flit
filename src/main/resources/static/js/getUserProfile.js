$(document).ready(function() {
    $.ajax({
        url: "/me"
    }).then(function(data) {
	   $('#profilePicture').attr("src", data.images[0].url );
    });
});
