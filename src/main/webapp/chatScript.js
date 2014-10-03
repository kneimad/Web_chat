var req;
var isIE;

function getUsers() {
        var url = "ChatServlet?action=getUsers";
        req = initRequest();
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseXML);
        }
    }
}

function parseMessages(responseXML) {

    if (responseXML == null) {
        return false;
    } else {

        var users = responseXML.getElementsByTagName("users")[0];
        document.getElementById("users").value = users;

//        if (users.childNodes.length > 0) {
//            for (loop = 0; loop < users.childNodes.length; loop++) {
//                var user = users.childNodes[loop];
//                var name = user.getElementsByTagName("user")[0];
//                appendComposer(name.childNodes[0].nodeValue);
//            }
//        }
    }
}