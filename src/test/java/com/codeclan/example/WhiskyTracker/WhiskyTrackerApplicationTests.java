package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canWhiskyFindByYear(){
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(2017);
		assertEquals(1, foundWhiskies.size());
		assertEquals("Viking Honour", foundWhiskies.get(0).getName());

	}

	@Test
	public void canFindDistilleryByRegion(){
		List<Distillery> foundDistilleries = distilleryRepository.findByRegion("Island");
		assertEquals(3, foundDistilleries.size());
		assertEquals("Highland Park", foundDistilleries.get(0).getName());
	}

	@Test
	public void canFindWhiskyByDistilleryAndAge() {
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskyByDistilleryAndAge(distilleryRepository.getById(5L), 12);
		assertEquals("Highland Park", foundWhiskies.get(0).getName());
	}

	@Test
	public void canFindWhiskyByDistilleryRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findWhiskyByDistilleryRegion("Island");
		assertEquals("Viking Honour", foundWhiskies.get(0).getName());
		assertEquals(6, foundWhiskies.size());


	}



}
