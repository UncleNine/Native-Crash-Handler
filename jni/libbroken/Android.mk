MODULE_PATH := $(call my-dir)

###################
# demo broken lib #
###################
include $(CLEAR_VARS)
LOCAL_PATH := $(MODULE_PATH)

LOCAL_MODULE := broken

# Include self headers
LOCAL_C_INCLUDES += $(LOCAL_PATH)

LOCAL_CFLAGS := $(MY_PJSIP_FLAGS) -rdynamic -g -funwind-tables

JNI_SRC_DIR := $(LOCAL_PATH)

LOCAL_SRC_FILES := broken.cpp 

LOCAL_LDLIBS += -llog -ldl 

LOCAL_STATIC_LIBRARIES += libgcc

#include $(BUILD_STATIC_LIBRARY)
include $(BUILD_SHARED_LIBRARY)

