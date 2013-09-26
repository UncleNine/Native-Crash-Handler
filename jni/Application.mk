JNI_DIR := $(call my-dir)

# Create a Local.mk file if you need to customize values below

# enable this for release build, no debug and optimizations
#APP_OPTIM        := release
# debug build, minimal optimization, debug information (larger binary)
APP_OPTIM := debug

APP_ABI := armeabi armeabi-v7a x86
# The following targets are not supported yet... 
# x86

#############################################################
# Do not change behind this line the are flags for pj build #
# Only build pjsipjni and ignore openssl                    #
# Include for local development
_local_mk := $(strip $(wildcard $(JNI_DIR)/Local.mk))
ifdef _local_mk
include $(JNI_DIR)/Local.mk
#$(call __ndk_info,Uses local settings)
#else
#$(call __ndk_info,No local settings... build all in release mode !)
endif

APP_MODULES := libcutils libgccdemangle localcorkscrew libnativecrashhandler libbroken
APP_PLATFORM := android-9
APP_STL := stlport_shared


