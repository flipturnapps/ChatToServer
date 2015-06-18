package com.flipturnapps.chattoserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.helper.FlushWriter;


public class Client implements Runnable
{
	private FlushWriter writer;
	private BufferedReader reader;
	private Socket socket;
	private Thread thread;
	private ChatToServer server;
	public Client(Socket s, ChatToServer server) throws IOException 
	{
		this.setSocket(s);		
		this.setWriter(new FlushWriter(this.getSocket().getOutputStream()));		
		this.setReader(new BufferedReader(new InputStreamReader(this.socket.getInputStream())));
		this.setServer(server);
		thread = new Thread(this);
		thread.start();
	}

	private void setServer(ChatToServer server) 
	{
	 this.server = server;		
	}
	public ChatToServer getServer()
	{
		return server;
	}

	@Override
	public void run() 
	{
		while(!socket.isClosed())
		{
			String line = this.readLineFromReader();
			if(line != null)
				server.clientMessage(line,this);
				
		}
	}

	private String readLineFromReader() 
	{
		try 
		{
			return reader.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public FlushWriter getWriter() 
	{
		return writer;
	}

	private void setWriter(FlushWriter writer) 
	{
		this.writer = writer;
	}

	public Socket getSocket() 
	{
		return socket;
	}

	private void setSocket(Socket socket) 
	{
		this.socket = socket;
	}

	public BufferedReader getReader() 
	{
		return reader;
	}

	private void setReader(BufferedReader reader) 
	{
		this.reader = reader;
	}

}
