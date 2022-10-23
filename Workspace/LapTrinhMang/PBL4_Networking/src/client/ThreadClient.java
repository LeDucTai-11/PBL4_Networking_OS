package client;

public class ThreadClient extends Thread {
	private ClientFrame client; 
	public ThreadClient(ClientFrame client) {
		this.client = client;
	}
	
	public void run() {
		String input = "";
		try {
			input = this.client.dis.readUTF();
			new PrintCalendar(input);
			this.client.soc.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
