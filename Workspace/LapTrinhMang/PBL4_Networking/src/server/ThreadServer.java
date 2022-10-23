package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServer extends Thread {
	public Server server;
	public Socket incoming;
	public DataInputStream dis;
	public DataOutputStream dos;
	public ThreadServer(Server server,Socket incoming) {
		this.server = server;
		this.incoming = incoming;
		try {
			dis = new DataInputStream(incoming.getInputStream());
			dos = new DataOutputStream(incoming.getOutputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void run() {
		String input = "";
		try {
			input = dis.readUTF();
			if(isNumber(input) == false) {
				incoming.close();
			}
			System.out.println("Input: "+input);
			dos.writeUTF(CalendarController.printCalendar(Integer.parseInt(input)));
		}catch(IOException ex) {
			try {
				incoming.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean isNumber(String s) {
		boolean check = true;
		try {
			int number = Integer.parseInt(s);
		}catch(Exception e) {
			check = false;
		}
		return check;
	}
}
