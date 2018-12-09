package com.example.helloworld;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.People;
import com.example.services.PeopleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {

	@Autowired
	PeopleService ser;

//	@Test
//	public void delTest() {
//		ser.delByName("keepal");
//	}
	
//	@Test
//	public void updateTest() {
//		ser.updateByName("keepal", "23");
//	}
	
	@Test
	public void getTest() {
		List<People> ls = ser.findByName("keepal");
		System.err.println(ls);
	}

}
