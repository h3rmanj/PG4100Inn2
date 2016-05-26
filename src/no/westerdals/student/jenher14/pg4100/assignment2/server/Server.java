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

			ServerSocket server = new ServerSocket(1994);

			while (true)
			{
				Socket request = server.accept();
				ClientContact contact = new ClientContact (request, books);
				new Thread(contact).run();
			}
		}
		catch (SQLException sql) { sql.printStackTrace(); }
		catch (IOException io) { io.printStackTrace(); }
	}

	public static void main (String[] args)
	{
		try { new Server(); }
		catch (Exception e) {}
	}


}
