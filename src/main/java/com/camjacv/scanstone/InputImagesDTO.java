package com.camjacv.scanstone;

public class InputImagesDTO
{
	private String fileName;
	private String filePath;
	
	InputImagesDTO(String fileName, String filePath)
	{
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public String getFileName() {
		return fileName;
	}
	public String getFilePath() {
		return filePath;
	}
}
