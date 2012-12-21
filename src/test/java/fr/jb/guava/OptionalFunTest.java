package fr.jb.guava;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OptionalFunTest {

	OptionalFun optionalFun;
	
	@Before
	public void before(){
		HashMap<String, String> m = new HashMap<>();
		m.put("junit", "4.8.1");
		m.put("guava", "14.0");
		
		optionalFun = new OptionalFun(m);
	}
	
	@Test
	public void testNeverNull(){
		Assert.assertNotNull(optionalFun.getDependenciesVersion("not existing dep"));
	}
	
	@Test
	public void testGetvalue(){
		Assert.assertEquals("4.8.1", optionalFun.getDependenciesVersion("junit").get());
	}
}
