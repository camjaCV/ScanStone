package com.camjacv.scanstone;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.OPTIONS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.camjacv.scanstone.adapter.ScanstoneAdapter;
import com.camjacv.scanstone.adapter.Settings;

@Controller
public class HIController 
{
	private static final Logger logger = LoggerFactory.getLogger(HIController.class);
	@Autowired
	HIService hiService;
	
	@RequestMapping(value="inputImages", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getInputImages()
	{
		List<String> imagePaths = hiService.getInputImages();
		return new ResponseEntity<List<String>>(imagePaths, HttpStatus.OK);
	}
	@RequestMapping(value="binarize", method=RequestMethod.POST)
	public ResponseEntity<HIResult> getBinarizedImage(@RequestBody String imagePath, @RequestParam boolean doOcr, @RequestParam boolean doRegionImages, @RequestParam boolean doAutoSegment, HttpServletRequest request)
	{
		boolean grantAccess = false;
		try {
			grantAccess = hiService.trackUser(getIpAddress(request));
		} catch (Exception e) {
			throw new RuntimeException("There was an issue recording this visit, unable to grant access", e);
		}
		if (!grantAccess)
		{
			return new ResponseEntity<HIResult>(HttpStatus.TOO_MANY_REQUESTS);
		}
		ScanstoneAdapter testCall = new ScanstoneAdapter();
		
		//Set up the settings
		Settings settings = new Settings();
		settings.setImageFilePath(imagePath);
		settings.setDoOcr(doOcr);
		settings.setDoRegionImages(doRegionImages);
		settings.setDoAutoSegment(doAutoSegment);
		HIResult hiResult = hiService.getBinarizedImage(settings);
		return new ResponseEntity<HIResult>(hiResult, HttpStatus.OK);
	}
//	@RequestMapping(value="transcribe", method=RequestMethod.POST)
//	public ResponseEntity<HIResult> getTranscription(@RequestBody String imagePath, HttpServletRequest request)
//	{
//		boolean grantAccess = false;
//		try {
//			grantAccess = hiService.trackUser(getIpAddress(request));
//		} catch (Exception e) {
//			throw new RuntimeException("There was an issue recording this visit, unable to grant access", e);
//		}
//		if (!grantAccess)
//		{
//			return new ResponseEntity<HIResult>(HttpStatus.TOO_MANY_REQUESTS);
//		}
//		HIResult hiResult = hiService.getBinarizedImage(imagePath);
//		return new ResponseEntity<HIResult>(hiResult, HttpStatus.OK);
//	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public ResponseEntity<String> getBinarizedImage(@RequestBody MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException
	{
		boolean grantAccess = false;
		try {
			grantAccess = hiService.trackUser(getIpAddress(request));
		} catch (Exception e) {
			throw new RuntimeException("There was an issue recording this visit, unable to grant access", e);
		}
		
		if (!grantAccess)
		{
			return new ResponseEntity<String>(HttpStatus.TOO_MANY_REQUESTS);
		}
		String imagePath = hiService.storeUploadedImage(file);
		return new ResponseEntity<String>(imagePath, HttpStatus.OK);
	}
	
	private String getIpAddress(HttpServletRequest request)
	{
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (null == ipAddress)
		{
			ipAddress = request.getRemoteAddr();
		}
		logger.info("request received from ip: " + ipAddress);
		return ipAddress;
	}
}
