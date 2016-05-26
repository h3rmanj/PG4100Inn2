package no.westerdals.student.jenher14.pg4100.assignment2.server;

import java.util.ArrayList;
import java.util.List;

public class Quiz
{
	String question;
	ArrayList<String> acceptableAnswers;

	public Quiz (Book book)
	{
		acceptableAnswers = new ArrayList<>();
		question = "Who wrote " + book.getTitle();
		acceptableAnswers.add(book.getAuthor());
		String[] author = book.getAuthor().split(", ");
		acceptableAnswers.add(author[1] + " " + author[0]);
	}

	public String getQuestion ()
	{
		return question;
	}

	public List<String> getAcceptableAnswers ()
	{
		return acceptableAnswers;
	}
}