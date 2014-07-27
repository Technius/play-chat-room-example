(function() {
	var messages = $("#messages");
	var input = $("#chat-input");
	var name_input = $("#username-input");
	var username;
	
	function addMessage(message) {
		var scroll = messages[0].scrollTop == messages[0].scrollTopMax ? true : false;
		var li = $('<li class="list-group-item"></li>');
		li.text(message);
		messages.append(li);
		if (scroll) messages.scrollTop(messages[0].scrollHeight);
	}
	
	var socket = null;
	var endpoint = "ws://" + location.host + "/chat";
	
	function connect() {
		if (socket != null) addMessage("Attempting to reconnect...");
		var socket2 = new WebSocket(endpoint);
		
		socket2.onopen = function() {
			socket = socket2;
			socket.send(JSON.stringify({
				username: username
			}));
			addMessage("Connected to server!");
		};
		
		socket2.onmessage = function(message) {
			var data = JSON.parse(message.data);
			var name = data.username ? data.username + ":" : "[System]";
			addMessage(name + " " + data.message);
		};
		
		socket2.onerror = function() {
			addMessage("Failed to connect to server.");
		};
		
		socket2.onclose = function() {
			if (socket != null) addMessage("Lost connection to server");
			window.setTimeout(connect, 2000);
		};
	}
	
	$("#send-btn").click(function() {
		if (socket.readyState == WebSocket.OPEN) {
			var msg = input.val().trim();
			if (msg.length == 0) {
				addMessage("Please enter a message!");
				return;
			}
			
			socket.send(JSON.stringify({
				message: msg
			}));
			
			input.val("");
		}
	});
	
	input.keypress(function(event) {
		if (event.which == 13) $("#send-btn").click();
	});
	
	var name_dialog = $("#username-dialog").modal({
		keyboard: false,
		backdrop: "static"
	});
	
	$("#username-confirm").click(function() {
		username = name_input.val().trim();
		if (username.length == 0) return;
		
		name_dialog.modal("hide");
		input.focus();
		addMessage("Welcome to Play Chat Room Example!");
		connect();
	});
	
	name_input.keypress(function(event) {
		if (event.which == 13) $("#username-confirm").click();
	});
	
	name_input.focus();
	
})();