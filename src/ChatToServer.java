import java.io.IOException;
import java.net.ServerSocket;


public class ChatToServer extends ServerSocket
{

	private static final int PORT = 12346;

	public ChatToServer() throws IOException
	{
		super(PORT);
	}

	public static void main(String[] args)
	{
		try {
			new ChatToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
