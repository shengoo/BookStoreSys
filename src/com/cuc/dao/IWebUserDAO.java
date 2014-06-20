package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.WebUser;

public interface IWebUserDAO {

	public boolean delete(WebUser webUser);

	public boolean insert(WebUser webUser);

	public ArrayList<WebUser> search(String webuser);

	public boolean update(WebUser webUser);
	
	public WebUser login(String webuser,String password);
	
	public boolean sameuser(String webuser);
}
