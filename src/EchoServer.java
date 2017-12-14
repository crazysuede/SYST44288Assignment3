import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class EchoServerV2
{

	
	public static void main (String[] args) 
	{

		ServerSocket sock = null;//create sock available for entire method
		try
		{
			ArrayList<Connection> connThreads = new ArrayList<Connection>();
			System.out.println("main started");

			int id = 0;
			boolean running = true;
			sock = new ServerSocket(7000);
			while (running == true)//as long as server is running
			{
				
				connThreads.add(id, (new Connection(id,sock.accept())));//create a new thread with connection
				connThreads.get(id).start();//start the thread allowing the loop to continue with Thread in background
				
				id++;//When new connections are made increment the id so they all have unique IDs
				
			}
		}
		catch (IOException ioe)
		{
			System.err.println(ioe);//output errors
		}
		finally
		{
			try
			{
				sock.close();//Once loop ends, close the ServerSocket
			}
			catch (IOException ioe)
			{
				System.err.println(ioe);//output errors
			}
		}

	}

}

class Connection extends Thread
{
	int port;
	int id;
	Socket client;
	public Connection(int idIn, Socket socketIn)
	{
		client = socketIn;//get Socket from main thread and use it for future connections
		id = idIn;//each thread has its own ID to keep track of them
	}
	@Override
	public void run()//method that threads will call
	{
		try 
		{
			String message = "";// Message received
			System.out.println("Connection successful! ID: " + id);// if successful
			
			while (!client.isClosed()) 
			{
				byte [] buffer = new byte[512];
				InputStream input = client.getInputStream();
				while ((input.read(buffer)) != -1) 
				{
					message = new String(buffer).trim();
					//System.out.println("Server read:" + message);
					byte [] bufferOut = message.getBytes();
					client.getOutputStream().write(bufferOut);
					client.getOutputStream().flush();
				}


			}
			client.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally
		{
			System.out.println("Connection " + id + " has finished");
		}
	}


}
