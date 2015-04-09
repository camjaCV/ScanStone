package com.camjacv.scanstone;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.camjacv.scanstone.adapter.Settings;

public interface HIService {
	List<String> getInputImages();
	HIResult getBinarizedImage(Settings settings);
	String storeUploadedImage(MultipartFile uploadedFile) throws IllegalStateException, IOException;
	boolean trackUser(String ipAddress) throws IOException, ParseException;
}
