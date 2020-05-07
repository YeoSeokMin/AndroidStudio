
public class ChatMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatServer chatServer = new ChatServer(7070);
		
		chatServer.mainLoop();
	}
}
