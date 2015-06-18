package com.flipturnapps.chattoserver;
import java.io.BufferedReader;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.helper.FlushWriter;


public class Client implements Runnable
{
	private FlushWriter writer;
	private BufferedReader reader;
	private Socket socket;
	public Client(Socket s) 
	{
		
	}

	@Override
	public void run() 
	{
		
	}

}
