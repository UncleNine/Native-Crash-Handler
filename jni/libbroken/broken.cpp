
#include "broken.h"

#include <jni.h>
#include <android/log.h>
#include <assert.h>

extern "C" {

static jclass applicationClass = 0;
static jobject applicationObject = 0;
static JavaVM *javaVM;

int internal1(int a);
int internal2(int a);
int internal3(int a);
int internal4(int a);
int internal5(int a);

int internal1(int a){
	return internal2(a+1);
}

int internal2(int a){
	return internal3(a+1);
}

int internal3(int a){
	return internal4(a+1);
}

int internal4(int a){
	return internal5(a+1);
}

int internal5(int a){
	assert(a==0);
	return a+1;
}

JNIEXPORT jboolean JNICALL Java_com_main_nativecrashhandler_Broken_test(JNIEnv * env, jobject obj) {
	internal1(1);
	return 0;
}


}
