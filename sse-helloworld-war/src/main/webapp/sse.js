var source;

function connect() {
	source = new EventSource(document.getElementById("sseURI").value);
    source.onopen = function(event) {
    	console.log(event);
    };
    
    source.addEventListener('add-message', function(e) {
  		append(e.data);
	}, false);
	source.onerror = function(event) {
        console.log(event);
    };
	source.addEventListener('error', function(e) {
  		if (e.readyState == EventSource.CLOSED) {
    		console.log(e);
    	}
	}, false);
}

function append(data) {
	var newElement = document.createElement("div");
    newElement.textContent = data;
    var sseMessagesNode = document.getElementById("sseMessages");
    sseMessagesNode.appendChild(newElement);
    sseMessagesNode.scrollTop = sseMessagesNode.scrollHeight;
}

function disConnect() {
    source.close();
}

function clearMessages() {
    var sseMessagesNode = document.getElementById("sseMessages");
    while (sseMessagesNode.firstChild) {
        sseMessagesNode.removeChild(sseMessagesNode.firstChild);
    }
}