package com.w2m.superheros.helper;

import java.util.List;

public class Utils {
	
	public static void validateIsNullOrEmpty(List<?> list, RuntimeException runtimeException) {
		if(validateDataStructure(list, l -> isNullOrEmpty((List<?>)l))) throw runtimeException; 
	}
	
	public static void validateIsNull(Object object, RuntimeException runtimeException) {
		if(validateDataStructure(object, o -> o==null)) throw runtimeException; 
	}
	
	private static boolean validateDataStructure(Object object, Validation validation) {
		return validation.isSatisfiedBy(object);
	}
	
	private static boolean isNullOrEmpty(List<?> list) {
		return list==null || list.isEmpty();
	}

	public static void validateNullOrBlank(RuntimeException runtimeException, String ... strings) {
		for(var string : strings) {
			if(string==null || string.isBlank())
				throw runtimeException;
		}
		
		
	}

}
