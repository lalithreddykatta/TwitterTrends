var ws = new WebSocket("ws://10.10.188.164:8080/");

ws.onopen = function() {
    alert("Opened!");
    ws.send("Trump");
};

ws.onmessage = function (evt) {
    alert("Message: " + evt.data);
};

ws.onclose = function() {
    alert("Closed!");
};

ws.onerror = function(err) {
    alert("Error: " + err);
};
