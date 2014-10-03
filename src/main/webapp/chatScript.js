//jQuery - AJAX

$(document).ready(function() {
    // Update interval for the users list - 3 seconds
    setInterval(getUsers,8000);
    // Update interval for the message history - 1 seconds
    setInterval(getHistory,5000);
    // If the user is closing the tab
    $(window).on("onbeforeunload", onclose);
    // Send message button click
    $('#sendmsg').click(sendClick);
});

function onclose() {
    alert( "-------- You are leaving the chat -----------" );
    $('#exit').click();
}

function sendClick(){
$.ajax({
 	    url: "ChatServlet?action=sendMessage",
 	    method: 'GET',
 	    data: {message: $('#chattext').val()},
 	    dataType: 'text/html'
	});
	//$('.text_field').text(""); //?????????????????????????????????$
	$('#chattext').text("");
}

function getUsers(){
	$.ajax({
 	    url: "ChatServlet?action=getUsers",
 	    method: 'GET',
 	    data: {},
 	    dataType: 'xml',
 	    success: function(responseXML) {
 		if (!responseXML) {
  			return false;
 		}
        //console.log($(responseXML).find('users'));
        //console.log($(responseXML).find('user'));
        //console.log($(responseXML).find('user').text());
        //console.log(responseXML);
        //console.log($.parseXML(responseXML));

		var users = $(responseXML).find('user');
		$('.users').text("");
        users.each(function() {
            $('.users').append("<span>"+ $(this).text() + "</br></span>");
        });
	}
	});
}

function getHistory(){
	$.ajax({
 	    url: "ChatServlet?action=getHistory",
 	    method: 'GET',
 	    data: {},
 	    dataType: 'xml',
 	    success: function(responseXML) {
 		if (!responseXML) {
  			return false;
 		}
        //console.log($(responseXML).find('users'));
        //console.log($(responseXML).find('user'));
        //console.log($(responseXML).find('user').text());
        //console.log(responseXML);
        //console.log($.parseXML(responseXML));

		var userMessage = $(responseXML).find('userMessage');
		$('.history').text("");
        users.each(function() {
            $('.history').append("<span>"+ $(this).text() + "</br></span>");
        });
	}
	});
}
