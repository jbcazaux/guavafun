package fr.jb.guava;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashBiMap;


public class BiMapFunTest {

	private HashBiMap<String, String> bimap;
	
	@Before
	public void before(){
		bimap = HashBiMap.<String, String>create(8);
		
		bimap.put("A", "Alpha");
		bimap.put("B", "Beta");
		bimap.put("C", "Charly");
		bimap.put("D", "Delta");
	}
	
	@Test
	public void testGetKeyFromValue(){
		Assert.assertEquals("A", bimap.inverse().get("Alpha"));
		Assert.assertEquals("B", bimap.inverse().get("Beta"));
		Assert.assertEquals("C", bimap.inverse().get("Charly"));
		Assert.assertEquals("D", bimap.inverse().get("Delta"));
	}
	
	@Test
	public void testGetNullFromNonExistingValue(){
		Assert.assertEquals(null, bimap.inverse().get("Tango"));
	}
	
	@Test
	public void testGetKeyFromValueAfterPutingInInverseMap(){
		bimap.inverse().put("Echo", "E");
		Assert.assertEquals("E", bimap.inverse().get("Echo"));
		Assert.assertEquals("Echo", bimap.get("E"));
	}
}
