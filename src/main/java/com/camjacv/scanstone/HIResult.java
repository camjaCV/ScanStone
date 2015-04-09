package com.camjacv.scanstone;

public class HIResult {
	private Long duration;
	private String originalPath;
	private String binarizedNormalPath;
	private String binarizedInvertedPath;
	private String regionNormalPath;
	private String regionInvertedPath;
	private String zonedImagePath;
	private String ocr;
	
	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 * @return the binarizedNormalPath
	 */
	public String getBinarizedNormalPath() {
		return binarizedNormalPath;
	}
	/**
	 * @param binarizedNormalPath the binarizedNormalPath to set
	 */
	public void setBinarizedNormalPath(String binarizedNormalPath) {
		this.binarizedNormalPath = binarizedNormalPath;
	}
	/**
	 * @return the binarizedInvertedPath
	 */
	public String getBinarizedInvertedPath() {
		return binarizedInvertedPath;
	}
	/**
	 * @param binarizedInvertedPath the binarizedInvertedPath to set
	 */
	public void setBinarizedInvertedPath(String binarizedInvertedPath) {
		this.binarizedInvertedPath = binarizedInvertedPath;
	}
	/**
	 * @return the originalPath
	 */
	public String getOriginalPath() {
		return originalPath;
	}

	/**
	 * @param originalPath the originalPath to set
	 */
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
	public String getZonedImagePath() {
		return zonedImagePath;
	}
	public void setZonedImagePath(String zonedImagePath) {
		this.zonedImagePath = zonedImagePath;
	}
	public String getOcr() {
		return ocr;
	}
	public void setOcr(String ocr) {
		this.ocr = ocr;
	}
	public String getRegionNormalPath() {
		return regionNormalPath;
	}
	public void setRegionNormalPath(String regionNormalPath) {
		this.regionNormalPath = regionNormalPath;
	}
	public String getRegionInvertedPath() {
		return regionInvertedPath;
	}
	public void setRegionInvertedPath(String regionInvertedPath) {
		this.regionInvertedPath = regionInvertedPath;
	}
}
