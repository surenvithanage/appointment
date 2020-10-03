package com.web.assignment.appointment;

import com.web.assignment.appointment.mapping.User;
import com.web.assignment.appointment.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppointmentApplicationTests {

	@Mock
	UserRepository dao;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllUsersTest()
	{
		List<User> list = new ArrayList<User>();
		User empOne = new User("suren","vithanage","0769213912","surenanthonyvithanage@gmail.com");
		User empTwo = new User("su","vith","0769213912","suvithanage@gmail.com");

		list.add(empOne);
		list.add(empTwo);

		when(dao.findAll()).thenReturn(list);

		//test
		List<User> empList = dao.findAll();

		assertEquals(3, empList.size());
		verify(dao, times(1)).findAll();
	}


	@Test
	public void getUserByIdTest()
	{
		when(dao.findById((long) 1)).thenReturn(java.util.Optional.of(new User("su", "vith", "0769213912", "suvithanage@gmail.com")));

		Optional<User> emp = dao.findById(Long.valueOf(1));

		assertEquals("Lokesh", emp.get().getFname());
		assertEquals("Gupta", emp.get().getLname());
		assertEquals("user@email.com", emp.get().getEmail());
	}

	@Test
	public void createUserTest()
	{
		User emp = new User("suren","vithanage","0769213912","surenanthonyvithanage@gmail.com");

		dao.save(emp);

		verify(dao, times(1)).save(emp);
	}
}
