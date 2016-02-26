package no.westerdals.student.jenher14.pg4100.assignment2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	public static void main (String[] args)
	{
		try
		{
			Socket socket = new Socket("localhost", 1994);
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.flush();
			DataInputStream input = new DataInputStream(socket.getInputStream());

			while(!socket.isInputShutdown())
			{
				System.out.println(input.readUTF());
				String answer = new Scanner(System.in).nextLine();
				output.writeUTF(answer);
				output.flush();
				System.out.println(input.readUTF());
				if (answer.startsWith("n"))
					break;
				answer = new Scanner(System.in).nextLine();
				output.writeUTF(answer);
				output.flush();
				System.out.println(input.readUTF());
			}
		}
		catch (IOException e) {}
	}
}