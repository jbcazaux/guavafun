package fr.jb.guava;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import fr.jb.guava.domain.User;

public class IterablesFun {

	List<User> users = new ArrayList<>();
	
	@Before
	public void before(){
		users.add(new User("login1", "login1@email.fr"));
		users.add(new User("login2", null));
		users.add(new User("login3", "login3@email.fr"));
	}
	
	@Test
	public void testFilter(){
		
		Predicate<User> haveMail = new Predicate<User>() {
			  public boolean apply(User user) {
			    return user.getEmail() != null && user.getEmail().matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
			  }
		};
		
		FluentIterable<User> usersWithEmail = FluentIterable.<User>from(users).filter(haveMail);
		Assert.assertEquals(2, usersWithEmail.size());
	}
	
	@Test
	public void testTransform(){
		Function<User, User> createDefaultEmail = new Function<User, User>() {
			@Override
			public User apply(final User from) {
				if (from.getEmail() == null){
					return new User(from.getLogin(), from.getLogin() + "@email.fr");
				}
				return from;
			}
		};
		
		FluentIterable<User> usersWithEmail = FluentIterable.<User>from(users).transform(createDefaultEmail);
		Assert.assertEquals(3, usersWithEmail.size());
		for (User user : usersWithEmail) {
			Assert.assertNotNull(user.getEmail());
		}
	}
	
	
}
