package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public final static int daytimePort = 5000;
	public Server() {
		ServerSocket theServer;
		Socket theConnection;
		try {
			theServer = new ServerSocket(daytimePort);
			while(true) {
				theConnection = theServer.accept();
				System.out.println("Have connection!");
				new ThreadServer(this, theConnection).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		new Server();
	}
}
