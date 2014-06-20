package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Book;

public interface IBookDAO {
	
	public int getRsCount(Book book);

	public boolean deltet(int ID);

	public boolean insert(Book book);

	public ArrayList<Book> search(Book book,int pageSize, int currentPage);

	public boolean update(Book book);
}
