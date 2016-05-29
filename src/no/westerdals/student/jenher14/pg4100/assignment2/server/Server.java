package no.westerdals.student.jenher14.pg4100.assignment2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server
{
	public Server ()
	{
		try (DBHandlerBooklist db = new DBHandlerBooklist())
		{
			ArrayList<Book> books = db.getBooksFromDB();
			System.out.println("Got books from DB.");

			ServerSocket server = new ServerSocket(1994);
			System.out.println("Server started.");

			while (true)
			{
				Socket request = server.accept();
				ClientContact contact = new ClientContact (request, books);
				new Thread(contact).run();
				System.out.println("Started a quiz with a client.");
			}
		}
		catch (SQLException sql) { sql.printStackTrace(); }
		catch (IOException io) { io.printStackTrace(); }
	}

	public static void main (String[] args)
	{
		new Server();
	}
}
