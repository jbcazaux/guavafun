package fr.jb.guava;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class MultiSetFunTest {

	private MultiSetFun multiSetFun;

	@Before
	public void before() {
		multiSetFun = new MultiSetFun();
	}

	@Test
	public void testCountLetters() throws Exception {
		int countI = multiSetFun.countLetters("voici quelques mots de ma part", 'i');
		Assert.assertEquals(2, countI);
	}
	@Test
	public void testCountLettersReturns0() throws Exception {
		int countY = multiSetFun.countLetters("voici quelques mots de ma part", 'y');
		Assert.assertEquals(0, countY);
	}
	@Test
	public void testCountLettersNoWords() throws Exception {
		int countA = multiSetFun.countLetters("", 'a');
		Assert.assertEquals(0, countA);
	}
	
	@Test
	public void testCountLettersNullWords() throws Exception {
		int countA = multiSetFun.countLetters(null, 'a');
		Assert.assertEquals(0, countA);
	}
	
	

}
