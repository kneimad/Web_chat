//jQuery - AJAX

$(document).ready(function() {
    //getUsers(); // or add tag <body onload = "getUsers();" in HTML page>
    // Update interval for the users list - 3 seconds
    setInterval(getUsers,30000);
    // Update interval for the message history - 1 seconds
    setInterval(getHistory,1000);
    // If the user is closing the tab
    $(window).on("onbeforeunload", onclose);
    // Set focus on the text field
    $('#chattext').focus();
    // Send message button click
    $('#sendmsg').click(sendClick);
    // Event for the Enter key press
    $('#chattext').keypress(function (e) {
    var key = e.which;
    if(key == 13)
      {
        sendClick();
        return false;
      }
    });
});

// Event when the user close window or tab without logged out the chat
function onclose() {
    alert( "-------- You are leaving the chat -----------" );
    $('#exit').click();
}
// Send user massage to the Servlet, clean the input field and focus it
function sendClick(){
$.ajax({
 	    url: "ChatServlet?action=sendMessage",
 	    method: 'GET',
 	    data: {message: $('#chattext').val()},
 	    dataType: 'text/html',
	});
	$('#chattext').val("");
	$('#chattext').focus();
}
/* Send request to the Servlet to get list of users, parse the
 * XML response and put it to the UserList field
*/
function getUsers(){
	$.ajax({
 	    url: "ChatServlet?action=getUsers",
 	    method: 'GET',
 	    data: {},
 	    dataType: 'xml',
 	    success: function(responseXML) {
 		if (!responseXML) {
  			return;
 		}
        //console.log($(responseXML).find('users'));
        //console.log($(responseXML).find('user'));
        //console.log($(responseXML).find('user').text());
        //console.log(responseXML);
        //console.log($.parseXML(responseXML));

		var users = $(responseXML).find('user');
		$('#userList').val(" ");
		var login = $('#login').val();
		var text = "";
		//console.log(login);

        users.each(function() {
            if ($(this).text() == login )
            {
                text += ("-> " + $(this).text() + "\n");
            }
            else text += ($(this).text() + "\n");
        });
        $('#userList').val(text);
	}
	});
}
/* Send request to the Servlet to get list of users' messages, parse the
 * XML response and put it to the History field
*/
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
        //console.log($(responseXML).find('userMessages'));
        //console.log($(responseXML).find('userMessage'));
        //console.log($(responseXML).find('userMessage').text());
        //console.log(responseXML);
        //console.log($.parseXML(responseXML));

		var userMessage = $(responseXML).find('userMessage');
		$('#msgList').val("");
		var login = $('#login').val();
		var text = "";

        userMessage.each(function() {
            //console.log( $(this).find('name').text() );
            if ( ($(this).find('name')).text() == login )
            {
                text += ("-> " + $(this).text() + "\n");
            }
            else text += ($(this).text() + "\n");
        });
        $('#msgList').val(text);
	}
	});
}
