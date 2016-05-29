/**
 * Herman Jensen
 * PG4100
 * Innlevering 2
 */

package no.westerdals.student.jenher14.pg4100.assignment2.server;

import no.westerdals.student.jenher14.pg4100.assignment2.util.ConnectToDB;
import java.sql.*;
import java.util.ArrayList;

public class DBHandlerBooklist implements AutoCloseable
{
    private final String SERVER = "localhost";
    private final String DATABASE = "pg4100innlevering2";
    private final String USER = "root";
    private final String PASSWORD = "thorsteinerhomo";

    private Connection con;
    private PreparedStatement pstmtBooks;

    public DBHandlerBooklist () throws SQLException
    {
        try (ConnectToDB connectToDB = new ConnectToDB(SERVER, DATABASE, USER, PASSWORD))
        {
            con = connectToDB.getConnection();
            pstmtBooks = con.prepareStatement("SELECT * FROM booklist");
        }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    public ArrayList<Book> getBooksFromDB () throws SQLException
    {
        ResultSet rs = pstmtBooks.executeQuery();

        ArrayList<Book> books = new ArrayList<>();
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

        return books;
    }

    public void close () throws SQLException
    {
        con.close();
        pstmtBooks.close();
    }
}
