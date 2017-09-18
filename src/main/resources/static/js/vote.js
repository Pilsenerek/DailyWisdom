//Initialize all functions
$(document).ready(function() {
	handleVote();
});

function handleVote(){
    $('[data-vote]').on("click", function (e) {
    	console.log('perform voting...');
        e.preventDefault();
         $.ajax({
            type: "GET",
            url: $(this).attr('href'),
            success: function (data) {
            	console.log('success');
            	autoFillData(data);
            },
            error: function () {
            	console.log('Implement service error');
            }
        });
    });
};

function autoFillData(data) {
	data = $('<div></div>').append($.parseHTML(data)).find( '[data-fill-out]' ).contents();
    $('[data-fill-in=vote]').html(data);
}