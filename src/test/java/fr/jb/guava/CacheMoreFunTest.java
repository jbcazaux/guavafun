package fr.jb.guava;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CacheMoreFunTest {
	private CacheMoreFun cache;

	@Before
	public void before() {
		cache = new CacheMoreFun();
	}

	@Test
	public void testGetFromEmptyCache() throws Exception {
		Assert.assertEquals(Integer.valueOf(6), cache.get("coucou"));
		Assert.assertEquals(1, cache.getCount());
	}
	
	@Test
	public void testGetTwiceFromEmptyCache() throws Exception {
		Assert.assertEquals(Integer.valueOf(6), cache.get("coucou"));
		Assert.assertEquals(1, cache.getCount());
		Assert.assertEquals(Integer.valueOf(6), cache.get("coucou"));
		Assert.assertEquals(1, cache.getCount());
	}

	@Test
	public void testGetFromExistingCache() throws Exception {
		cache.getCache().put("magic", 42);
		Assert.assertEquals(new Integer(42), cache.get("magic"));
		Assert.assertEquals(0, cache.getCount());
	}
}
