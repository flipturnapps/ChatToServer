package com.flipturnapps.chattoserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.flipturnapps.kevinLibrary.helper.Numbers;


public class ChatToServer extends ServerSocket implements Runnable
{

	private static final int PORT = 12346;
	private ArrayList<Client> clients;
	private Thread thread;
	private HashMap<String, String> keysMap;
	public ChatToServer() throws IOException
	{
		super(PORT);
		thread = new Thread(this);
		thread.start();
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
		while(true)
		{
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			Socket s = null;
			try 
			{
				s = this.accept();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			out("New client.");
			try
			{
				this.clients.add(new Client(s,this));
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void out(String string)
	{
		System.out.println(string);
	}

	public void clientMessage(String line, Client client)
	{
		if(line.startsWith("regen"))
			regenerateKeys(line.split(":")[1],line.split(":")[2]);
		else
			client.getWriter().println(getKeyByPhoneNum(line));
	}

	private String getKeyByPhoneNum(String num) 
	{
		HashMap<String, String> map = ititializeMap();
		if(map.containsKey(num))
			return map.get(num);
		else
			return generateAndStoreNewKey(num,map);
	}


	private String generateAndStoreNewKey(String num,HashMap<String,String> map) 
	{
		String newKey = getRandomString() + getRandomString();
		map.remove(num);
		map.put(num, newKey);
		return newKey;
	}
	private String getRandomString()
	{
		return ((Math.random()*Math.random()*10000)+"").replace('.', (char) Numbers.random('A', 'Z'));
	}

	private HashMap<String, String> ititializeMap() 
	{
		if(keysMap == null)
			keysMap = new HashMap<String,String>();
		return keysMap;
	}

	private void regenerateKeys(String num1, String num2)
	{
		HashMap<String, String> map = ititializeMap();
		generateAndStoreNewKey(num1, map);
		generateAndStoreNewKey(num2, map);
	}

}
