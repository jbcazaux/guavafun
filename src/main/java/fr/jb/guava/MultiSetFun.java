package fr.jb.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;

public class MultiSetFun {

	public int countLetters(String words, char l){
		
		Multiset<Character> wordsMultiset = HashMultiset.<Character>create();
		wordsMultiset.addAll(toList(words));
		
		return wordsMultiset.count(l);
	}
	
	public Set<Character> getLetters(String words){
		Multiset<Character> wordsMultiset = HashMultiset.<Character>create();
		wordsMultiset.addAll(toList(words));
		return wordsMultiset.elementSet();
	}
	
	public Object countLetters(String words) {
		Multiset<Character> wordsMultiset = HashMultiset.<Character>create();
		wordsMultiset.addAll(toList(words));
		return wordsMultiset.size();
	}
	
	private List<Character> toList(String s){
		if (s == null){
			return ImmutableList.of();
		}
		List<Character> l = new ArrayList<>();
		for(char c : s.toCharArray()) {
		    l.add(Character.valueOf(c));
		}
		return l;
	}

	
}
