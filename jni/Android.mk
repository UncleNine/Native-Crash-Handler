LOCAL_PATH := $(call my-dir)
JNI_PATH := $(LOCAL_PATH)


USE_FIXED_POINT := 0
ifeq ($(TARGET_ARCH_ABI),$(filter $(TARGET_ARCH_ABI),armeabi armeabi-v7a))
USE_FIXED_POINT := 1
endif

# Include all submodules declarations
include $(JNI_PATH)/libcutils/Android.mk
include $(JNI_PATH)/libgccdemangle/Android.mk
include $(JNI_PATH)/libcorkscrew/Android.mk
include $(JNI_PATH)/libnativecrashhandler/Android.mk

# just a testing library, not really needed...
include $(JNI_PATH)/libbroken/Android.mk