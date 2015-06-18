import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatToServer extends ServerSocket implements Runnable
{

	private static final int PORT = 12346;
	private ArrayList<Client> clients;
	public ChatToServer() throws IOException
	{
		super(PORT);
	}

	public static void main(String[] args)
	{
		try 
		{
			new ChatToServer();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		out("Server initalized");
		clients = new ArrayList<Client>();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Socket s = null;
		try {
			s = this.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.clients.add(new Client(s));
	}

	private void out(String string)
	{
		System.out.println(string);
	}

}
