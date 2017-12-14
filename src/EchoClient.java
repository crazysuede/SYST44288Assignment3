import java.net.*;
import java.util.Scanner;
import java.io.*;

public class EchoClient {

	public static void main(String[] args) {

		// String serverIP = args[0];
		Scanner scanner = new Scanner(System.in);
		String message = "";
		Socket sock = null;
		String exitValue = ".";

		try {
			sock = new Socket("127.0.0.1", 7000);// create Socket
			while (!sock.isClosed()) {
				OutputStream output = sock.getOutputStream();// get the datastream from socket
				message = scanner.nextLine();
				byte[] buffer = message.getBytes();// create buffer

				// output.write(buffer);// write buffer into outputstream
				output.write(buffer, 0, message.length());// close socket in order to send message
				output.flush();

				InputStream input = sock.getInputStream();
				input.read(buffer);
				message = new String(buffer).trim();
				System.out.println("Reply:" + message);

				if (message.equals(exitValue))
					
				{
					sock.close();
				}
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		} finally {
			scanner.close();
			System.out.println("Client has closed");
			try {
				sock.close();
			} catch (IOException ioe) {

			}

		}
	}
}
