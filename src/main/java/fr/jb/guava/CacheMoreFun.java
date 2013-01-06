package fr.jb.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheMoreFun {

	private Cache<String, Integer> cache;
	private int count;

	public CacheMoreFun() {
		cache = CacheBuilder.newBuilder().maximumSize(10).build();
		count = 0;
	}

	public Integer get(final String key) throws ExecutionException {
		return cache.get(key, new Callable<Integer>() {
			@Override
			public Integer call() {
				count++;
				return key.length();
			}
		});
	}

	public int getCount() {
		return count;
	}

	public Cache<String, Integer> getCache() {
		return cache;
	}
}
