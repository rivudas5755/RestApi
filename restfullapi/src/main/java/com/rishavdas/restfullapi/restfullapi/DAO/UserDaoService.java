package com.rishavdas.restfullapi.restfullapi.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rishavdas.restfullapi.restfullapi.Bean.User;

@Component
public class UserDaoService {

	public static List<User> users = new ArrayList<User>();
	
	private static int count = 3;
	
	static {
		users.add(new User(1,"Rishav Das", new Date()));
		users.add(new User(2,"Arindam Das", new Date()));
		users.add(new User(3,"Poushali Mukherjee", new Date()));
	}
	
	public User findById(int id)
	{
		for(User user: users)
		{
			if(user.getId()==id)
				return user;
		}
		
		return null;
	}
	
	public List<User> findAll()
	{
		
		return users;
	}
	
	public User save(User user)
	{
		if(user.getId() == 0)
			user.setId(++count);
		
		users.add(user);
		return  user;
	}
	
	public boolean deleteUser(int id)
	{
		for(User user: users)
		{
			if(user.getId() == id) 
			{
				users.remove(user);
				return true;
			}	
		}
		return false;
		
	}
	
}
