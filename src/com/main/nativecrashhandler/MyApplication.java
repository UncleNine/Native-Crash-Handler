package com.main.nativecrashhandler;

import android.app.Application;
import android.util.Log;
import com.github.nativehandler.NativeCrashHandler;

public class MyApplication extends Application {
	private static final String THIS_FILE="MyApplication";
    public static final String STD_LIB_NAME = "stlport_shared";
	public static final String CRASH_NAME = "nativecrashhandler";
	public static final String CORK_NAME = "localcorkscrew";
	public static final String BROKEN_NAME = "broken";
	
	@Override
    public void onCreate() {
        super.onCreate();      
        Log.e(THIS_FILE, "onCreated");
        // At first load crash handler
        loadCrashHandler();
        
        // Now load our broken lib
        System.loadLibrary(BROKEN_NAME);
        
        // Everything is OK
        Log.v(THIS_FILE, "Application started");
    }
	
	/**
	 * Loads crash handler native libraries
	 */
	public void loadCrashHandler(){
		try {
            // Try to load the natives
			// At first load STL port library.
        	Log.v(THIS_FILE, "Initializing native crash handler");
        	System.loadLibrary(STD_LIB_NAME);
        	
        	// do we have libcorkscrew? If we have Android without native one
        	try {
	        	if (!isCompatible(16)){
	        		System.loadLibrary(CORK_NAME);
	        		Log.v(THIS_FILE, "Local libcorkscrew loaded");
	        	}
        	} catch(Exception e){
        		Log.v(THIS_FILE, "Problem with loading local libcorcscrew");
        	}
        	
        	// And finally load native part of crash handler
            System.loadLibrary(CRASH_NAME);
            Log.v(THIS_FILE, "Native crash libs just loaded");
            
            // Register for native crash handler
            new NativeCrashHandler().registerForNativeCrash(this);
            Log.v(THIS_FILE, "Native crash handler initialized");
        } catch (UnsatisfiedLinkError e) {
            // If it fails we probably are running on a special hardware
            Log.e(THIS_FILE, "We have a problem with the current stack.... Not yet Implemented", e);
        } catch (Exception e) {
            Log.e(THIS_FILE, "We have a problem with the current stack....", e);
        }
	}
	
	public static boolean isCompatible(int apiLevel) {
        return android.os.Build.VERSION.SDK_INT >= apiLevel;
    }
}
