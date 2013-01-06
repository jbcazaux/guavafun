package fr.jb.guava;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheFunTest {

	private LoadingCache<String, Integer> cache;
	private int count;

	@Before
	public void before() {
		cache = CacheBuilder.newBuilder().maximumSize(10)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(String key) throws Exception {
						count++;
						return key.length();
					}
				});
		count = 0 ;
	}

	@Test
	public void testGetFromEmptyCache() throws Exception {
		Assert.assertEquals(new Integer(6), cache.get("coucou"));
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testGetTwiceFromEmptyCache() throws Exception {
		Assert.assertEquals(new Integer(6), cache.get("coucou"));
		Assert.assertEquals(1, count);
		Assert.assertEquals(new Integer(6), cache.get("coucou"));
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testGetFromExistingCache() throws Exception {
		cache.put("magic", 42);
		Assert.assertEquals(new Integer(42), cache.get("magic"));
		Assert.assertEquals(0, count);
	}
	

}
