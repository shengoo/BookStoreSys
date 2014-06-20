package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Admin;

public interface IAdminDAO {
	public boolean deleteById(int id);

	public boolean insert(Admin admin);

	public ArrayList<Admin> search(String username);
	
	public Admin login(String username,String password);

	public boolean update(Admin admin);
	
	public boolean change(int id,String password);

	public Admin getById(int id);
}
