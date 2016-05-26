package no.westerdals.student.jenher14.pg4100.assignment2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientContact implements Runnable
{
	private Socket socket;
	private ArrayList<Book> books;

	public ClientContact (Socket socket, ArrayList<Book> books)
	{
		this.socket = socket;
		this.books = books;
	}

	@Override
	public void run ()
	{
		try
		{
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.flush();
			DataInputStream input = new DataInputStream(socket.getInputStream());

			output.writeUTF("Hello, do you want to participate in the book-author QUIZ? y/n");
			output.flush();

			while (true)
			{
				String yn = input.readUTF().toLowerCase();
				if (yn.startsWith("n"))
					break;

				Book book = books.get((int) (Math.random() * books.size()));
				Quiz quiz = new Quiz(book);
				output.writeUTF(quiz.getQuestion());
				output.flush();

				String ClientAnswer = input.readUTF();
				boolean correct = false;
				for (String QuizAnswer: quiz.getAcceptableAnswers())
				{
					if (QuizAnswer.equalsIgnoreCase(ClientAnswer))
					{
						correct = true;
						break;
					}
				}
				if (correct)
					output.writeUTF("GJ dude, 100% correct!");
				else
					output.writeUTF("You suck dude, correct answer was " + book.getAuthor() + ".");
				output.flush();
				output.writeUTF("Do you want to continue? y/n");
				output.flush();
			}

			output.writeUTF("GG dude, hope to see you again soon!");
			output.flush();
		}
		catch (IOException e) {}
	}
}
