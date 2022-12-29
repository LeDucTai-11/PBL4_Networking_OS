package client;

public class ThreadClient extends Thread {
	private ClientFrame client; 
	public ThreadClient(ClientFrame client) {
		this.client = client;
	}
	
	public void run() {
		String input = "";
		try {
			while(true) {
				input = this.client.dis.readUTF();
				new PrintCalendar(input);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			this.client.frame.dispose();
		}
	}
}
