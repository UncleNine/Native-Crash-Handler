package com.main.nativecrashhandler;

public class Broken {
	private native boolean test();
	public boolean testWrapper(){
		return test();
	}
}
