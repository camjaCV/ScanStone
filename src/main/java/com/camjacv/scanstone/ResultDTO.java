package com.camjacv.scanstone;

public class ResultDTO
{
	private String invertedImagePath;
	private String normalImagePath;
	private double computeTime;
	
	/**
	 * @return the invertedImagePath
	 */
	public String getInvertedImagePath() {
		return invertedImagePath;
	}
	/**
	 * @param invertedImagePath the invertedImagePath to set
	 */
	public void setInvertedImagePath(String invertedImagePath) {
		this.invertedImagePath = invertedImagePath;
	}
	/**
	 * @return the normalImagePath
	 */
	public String getNormalImagePath() {
		return normalImagePath;
	}
	/**
	 * @param normalImagePath the normalImagePath to set
	 */
	public void setNormalImagePath(String normalImagePath) {
		this.normalImagePath = normalImagePath;
	}
	public double getComputeTime() {
		return computeTime;
	}
	public void setComputeTime(double computeTime) {
		this.computeTime = computeTime;
	}
}
