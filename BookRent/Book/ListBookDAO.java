package book;

import java.util.LinkedList;
import java.util.List;

public class ListBookDAO implements BookDAO{

	private List<BookVO> bookList = new LinkedList<BookVO>();
	private int bookSeq = 111; // 책번호 1씩 증가
	
	@Override
	public boolean insertBook(BookVO book) {
		book.setBookNo(bookSeq++);
		bookList.add(book);
		return true;
	}

	@Override
	public BookVO selectBook(int bookNo) {
		for (BookVO book : bookList) {
			if (book.getBookNo() == bookNo)
				return book;
		}
		return null;
	}

	@Override
	public List<BookVO> selectAllBooks() {
		return bookList;
	}

	@Override
	public boolean updateBook(BookVO newbook) {
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookNo() == newbook.getBookNo()) {
				bookList.set(i, newbook);
			return true;
		}
	}
		return false;
}

	@Override
	public boolean deleteBook(int bookNo) {
		
		for (BookVO book : bookList) {
		if (book.getBookNo() == bookNo) {
			bookList.remove(book);
			return true;
		    }
		}
		return false;
	}

}
