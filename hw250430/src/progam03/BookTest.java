package progam03;

import java.util.Arrays;
import java.util.Comparator;

public class BookTest {

	public static void main(String[] args) {
		Book[] books = {
				new Book(15000),
				new Book(50000),
				new Book(10000)
		};
		
		
		Arrays.sort(books, new Comparator<Book>(){
			@Override
			public int compare(Book b1, Book b2) {
				return Integer.compare(b1.price, b2.price);
			}
		});
		
	
		for (Book book : books) {
			System.out.println(book);
		}
	}
}


