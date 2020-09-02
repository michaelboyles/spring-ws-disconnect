This is a reproducible example for the Spring issue tracker.

## Problem

Websocket traffic is not considered as session activity. If an application has active websocket traffic, but infrequent or no HTTP
traffic, the WebSocket will still be terminated. This seems to happen after `server.servlet.session.timeout * 2`.

[This question on StackOverflow](https://stackoverflow.com/questions/50587573/) shares the same problem. It has 3000 views, so
clearly this behaviour is tripping people up, even if it's not a bug.

In our application, we send a few HTTP requests, establish a websocket, and then all future requests go via the websocket.

## Building and running the example

Nothing unusual here.

 1. Run `mvn package`
 2. Run com.example.App main
 3. Open [http://localhost:8080/index.html](http://localhost:8080/index.html)
 4. Credentials for login screen are: user / password
 5. Wait for around 2 minutes (`server.servlet.session.timeout` is set to 1 minute)
 
The websocket will be disconnected, despite heartbeating with the server every second.

## Workaround

We had already implemented application-level heartbeats on the websocket connection (in addition to the heartbeating that already
happens at the protocol level).

The solution for us was to move the heartbeats from the websocket to HTTP. Every 30 seconds or so, the browser will send a small
HTTP request to the server.

This is enough for Spring to consider the HTTP session active, and it will not terminate the websocket until the HTTP heartbeats
have stopped.
