package com.bharath.useradmin.dao;

import com.bharath.useradmin.dto.User;
import com.bharath.useradmin.util.IDGenerator;

public class UserDAO {
	
	public int create(User user){
		int id = IDGenerator.generateID();
		//Save the user object to the db
		return id;
		
		
	}

}
