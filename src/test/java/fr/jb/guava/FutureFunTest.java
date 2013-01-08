package fr.jb.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import fr.jb.guava.domain.User;

public class FutureFunTest {

	private CountDownLatch latch;
	private CountDownLatch latch2;
	private boolean flag;
	
	@Before
	public void before(){
		latch = new CountDownLatch(1);
		latch2 = new CountDownLatch(2);
		flag = false;
	}
	
	@Test
	public void testFutureWithListener() throws Exception {
		ListeningExecutorService executorService = MoreExecutors
				.listeningDecorator(Executors.newFixedThreadPool(10));

		ListenableFuture<User> futureTask = executorService
				.submit(new Callable<User>() {
					@Override
					public User call() throws InterruptedException {
						return new User("login", "mail");
					}
				});
		
		futureTask.addListener(new Runnable() {
			@Override
			public void run() {
				flag = true;
				latch.countDown();
			}
		}, executorService);
		
		latch.await();
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void testFutureExceptionWithListener() throws Exception {
		ListeningExecutorService executorService = MoreExecutors
				.listeningDecorator(Executors.newFixedThreadPool(10));

		ListenableFuture<User> futureTask = executorService
				.submit(new Callable<User>() {
					@Override
					public User call() throws InterruptedException {
						return new User(null, "mail");
					}
				});
		
		futureTask.addListener(new Runnable() {
			@Override
			public void run() {
				flag = true;
				latch.countDown();
			}
		}, executorService);
		
		latch.await();
		Assert.assertTrue(flag);
		
	}

	@Test
	public void testFuture() throws InterruptedException {

		ListeningExecutorService executorService = MoreExecutors
				.listeningDecorator(Executors.newFixedThreadPool(10));

		ListenableFuture<User> createUserFuture = executorService
				.submit(new Callable<User>() {
					@Override
					public User call(){
						latch.countDown();
						return new User("login", "mail");
					}
				});

		Futures.addCallback(createUserFuture, new FutureCallback<User>() {
			public void onSuccess(User user) {
				latch2.countDown();
			}

			public void onFailure(Throwable thrown) {
				Assert.fail();
			}
		});

		Futures.addCallback(createUserFuture, new FutureCallback<User>() {
			public void onSuccess(User user) {
				latch2.countDown();
			}

			public void onFailure(Throwable thrown) {
				Assert.fail();
			}
		});
		

		latch2.await();
		Assert.assertEquals(0, latch.getCount());
		Assert.assertEquals(0, latch2.getCount());
	}
	
	@Test
	public void testFutureWithException() throws InterruptedException {

		ListeningExecutorService executorService = MoreExecutors
				.listeningDecorator(Executors.newFixedThreadPool(10));

		ListenableFuture<User> createUserFuture = executorService
				.submit(new Callable<User>() {
					@Override
					public User call(){
						latch.countDown();
						return new User(null, "mail");
					}
				});

		Futures.addCallback(createUserFuture, new FutureCallback<User>() {
			public void onSuccess(User user) {
				Assert.fail();
			}

			public void onFailure(Throwable thrown) {
				latch2.countDown();
			}
		});

		Futures.addCallback(createUserFuture, new FutureCallback<User>() {
			public void onSuccess(User user) {
				Assert.fail();
			}

			public void onFailure(Throwable thrown) {
				latch2.countDown();
			}
		});
		

		latch2.await();
		Assert.assertEquals(0, latch.getCount());
		Assert.assertEquals(0, latch2.getCount());
	}
}
