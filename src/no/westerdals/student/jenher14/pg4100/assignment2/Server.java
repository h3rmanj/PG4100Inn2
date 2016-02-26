package no.westerdals.student.jenher14.pg4100.assignment2;

import no.westerdals.student.jenher14.pg4100.util.ConnectToDB;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Server
{
	public Server () throws Exception
	{
		ArrayList<Book> books = getBooksFromDB();

		ServerSocket server = new ServerSocket(1994);

		while (true)
		{
			Socket request = server.accept();
			ClientContact contact = new ClientContact (request, books);
			new Thread(contact).run();
		}
	}

	public static void main (String[] args)
	{
		try { new Server(); }
		catch (Exception e) {}
	}

	private ArrayList<Book> getBooksFromDB ()
	{
		ArrayList<Book> books = new ArrayList<>();

		try
		{
			ConnectToDB connectToDB = new ConnectToDB("localhost", "pg4100innlevering2", "root", "thorsteinerhomo");
			Connection con = connectToDB.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bokliste");

			while (rs.next())
			{
				String author = rs.getString("author");
				String title = rs.getString("title");
				String ISBN = rs.getString("ISBN");
				int pages = rs.getInt("pages");
				int released = rs.getInt("released");

				Book book = new Book(author, title, ISBN, pages, released);
				books.add(book);
			}
		}
		catch (Exception e) { e.printStackTrace(); System.exit(0); }

		return books;
	}
}
