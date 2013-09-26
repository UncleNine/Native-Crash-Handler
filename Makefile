all : libraries
	# Build native library SUCCESSFUL

libraries : 
	# Build main libraries using android ndk
	#@(ndk-build -j$(shell nproc))
	@(ndk-build -j$(shell nproc) NDK_DEBUG=1)
	
jni/%/sources :
	@($(MAKE) $(MFLAGS) -C $(subst /sources,,$@) init)
	
## Patches against remote projects
jni/%/.patched_sources : 
	@($(MAKE) $(MFLAGS) -C $(subst /.patched_sources,,$@) patch)


clean :
	# NDK clean
	@(ndk-build clean)
