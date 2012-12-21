package fr.jb.guava;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testGetLetters() throws Exception {
		Set<Character> letters = multiSetFun.getLetters("voici quelques mots de ma part");
		Assert.assertTrue(letters.contains(new Character('i')));
		Assert.assertFalse(letters.contains(new Character('y')));
	}
	
	@Test
	public void testGetLettersFromNoWords() throws Exception {
		Set<Character> letters = multiSetFun.getLetters("");
		Assert.assertFalse(letters.contains(new Character('a')));
		letters = multiSetFun.getLetters(null);
		Assert.assertFalse(letters.contains(new Character('a')));
	}
	
	@Test
	public void testCountAllLetters() throws Exception {
		String words = "voici quelques mots de ma part";
		Assert.assertEquals(words.length(), multiSetFun.countLetters(words));
	}

}
