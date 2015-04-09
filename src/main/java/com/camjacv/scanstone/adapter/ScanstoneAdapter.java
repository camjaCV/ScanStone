package com.camjacv.scanstone.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanstoneAdapter
{
	private static final Logger logger = LoggerFactory.getLogger(ScanstoneAdapter.class);
	public native String indexHeadstone(Settings settings);
	
	static {
		try{
			logger.warn("HeadstoneIndexer loading");
			System.loadLibrary("HeadstoneIndexer");
			logger.warn("HeadstoneIndexer loaded successfully");
		}
		catch (Exception e)
		{
			logger.error("in static block, error loading class", e);
		}
	}
	
	public static void main(String[] args)
	{
		Settings settings = new Settings();
		new ScanstoneAdapter().indexHeadstone(settings);
	}
	
	public String callNativeFuntion(Settings settings)
	{
		logger.warn("in callNativeFunction method");
		return new ScanstoneAdapter().indexHeadstone(settings);
	}
}