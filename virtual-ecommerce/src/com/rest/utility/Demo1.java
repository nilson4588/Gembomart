package com.rest.utility;

class Demo1  {

	public static void main(String args[]) {

		String blankString = "";
		
		System.out.println(blankString.length());

		if(blankString == null || blankString.length() == 0)
		    System.out.println("This string is null or empty");
		else
		    System.out.println("This string is neither null nor empty");
	}
}