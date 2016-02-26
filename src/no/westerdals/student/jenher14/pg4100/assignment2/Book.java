package no.westerdals.student.jenher14.pg4100.assignment2;

public class Book
{
	private String author;
	private String title;
	private String ISBN;
	private int pages;
	private int released;

	public Book (String author, String title, String ISBN, int pages, int released)
	{
		setAuthor(author);
		setTitle(title);
		setISBN(ISBN);
		setPages(pages);
		setReleased(released);
	}

	public void setAuthor (String author)
	{
		this.author = author;
	}

	public void setTitle (String title)
	{
		this.title = title;
	}

	public void setISBN (String ISBN)
	{
		this.ISBN = ISBN;
	}

	public void setPages (int pages)
	{
		this.pages = pages;
	}

	public void setReleased (int released)
	{
		this.released = released;
	}

	public String getAuthor ()
	{
		return author;
	}
	public String getTitle ()
	{
		return title;
	}
	public String getISBN ()
	{
		return ISBN;
	}
	public int getPages ()
	{
		return pages;
	}
	public int getReleased ()
	{
		return released;
	}
}
