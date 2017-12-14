import java.io.*;
import java.net.*;

public class EchoServer {

	public static void main(String[] args) {
		try 
		{
			String message = "";// Message received
			ServerSocket sock = new ServerSocket(7000);// port to listen on
			// accept connection
			System.out.println("Connection successful");// if successful
			
			while (!sock.isClosed()) 
			{
				Socket client = sock.accept();
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
			sock.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally
		{
			System.out.println("Server has finished");
		}

	}

}
