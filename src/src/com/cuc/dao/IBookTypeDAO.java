package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.BookType;

public interface IBookTypeDAO {

	public boolean deltet(int ID);

	public boolean insert(BookType bookType);

	public ArrayList<BookType> searchAll();

	public ArrayList<BookType> search(BookType bookType);

	public boolean update(BookType bookType);
}
