package fr.jb.guava;

import java.util.HashMap;

import com.google.common.base.Optional;

public class OptionalFun {
	
	private HashMap<String, String> depMap;

	public OptionalFun(HashMap<String, String> map){
		this.depMap = map;
	}
	
	public Optional<String> getDependenciesVersion(String dependency){
		
		return Optional.fromNullable(depMap.get(dependency));
	}
	
}
