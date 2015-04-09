package com.camjacv.scanstone.adapter;

import java.util.List;

public class Settings {
	private String imageFilePath;
	private String resultImageFilePath;
	
	private boolean doAutoSegment;
	private boolean doZoneText;
	private double textZoningOverlap; //default is 0.5
	private List<Double> zoningThresholds; //how strict to be on mlp's judgement at each level of pyramid
	private boolean segmentText;
	private boolean useLuminanceForGrayscale;
	private boolean removeArtwork;
	private boolean removeArtworkUseGraphCut;
	private boolean doOcr;
	private boolean doOCRValidation;
	private boolean ocrCorrectRotation;
	private boolean checkAccuracy;
	private boolean doRegionImages;
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	public String getResultImageFilePath() {
		return resultImageFilePath;
	}
	public void setResultImageFilePath(String resultImageFilePath) {
		this.resultImageFilePath = resultImageFilePath;
	}
	public boolean isDoAutoSegment() {
		return doAutoSegment;
	}
	public void setDoAutoSegment(boolean doAutoSegment) {
		this.doAutoSegment = doAutoSegment;
	}
	public boolean isDoZoneText() {
		return doZoneText;
	}
	public void setDoZoneText(boolean doZoneText) {
		this.doZoneText = doZoneText;
	}
	public double getTextZoningOverlap() {
		return textZoningOverlap;
	}
	public void setTextZoningOverlap(double textZoningOverlap) {
		this.textZoningOverlap = textZoningOverlap;
	}
	public List<Double> getZoningThresholds() {
		return zoningThresholds;
	}
	public void setZoningThresholds(List<Double> zoningThresholds) {
		this.zoningThresholds = zoningThresholds;
	}
	public boolean isSegmentText() {
		return segmentText;
	}
	public void setSegmentText(boolean segmentText) {
		this.segmentText = segmentText;
	}
	public boolean isUseLuminanceForGrayscale() {
		return useLuminanceForGrayscale;
	}
	public void setUseLuminanceForGrayscale(boolean useLuminanceForGrayscale) {
		this.useLuminanceForGrayscale = useLuminanceForGrayscale;
	}
	public boolean isRemoveArtwork() {
		return removeArtwork;
	}
	public void setRemoveArtwork(boolean removeArtwork) {
		this.removeArtwork = removeArtwork;
	}
	public boolean isRemoveArtworkUseGraphCut() {
		return removeArtworkUseGraphCut;
	}
	public void setRemoveArtworkUseGraphCut(boolean removeArtworkUseGraphCut) {
		this.removeArtworkUseGraphCut = removeArtworkUseGraphCut;
	}
	public boolean isDoOcr() {
		return doOcr;
	}
	public void setDoOcr(boolean doOcr) {
		this.doOcr = doOcr;
	}
	public boolean isDoOCRValidation() {
		return doOCRValidation;
	}
	public void setDoOCRValidation(boolean doOCRValidation) {
		this.doOCRValidation = doOCRValidation;
	}
	public boolean isOcrCorrectRotation() {
		return ocrCorrectRotation;
	}
	public void setOcrCorrectRotation(boolean ocrCorrectRotation) {
		this.ocrCorrectRotation = ocrCorrectRotation;
	}
	public boolean isCheckAccuracy() {
		return checkAccuracy;
	}
	public void setCheckAccuracy(boolean checkAccuracy) {
		this.checkAccuracy = checkAccuracy;
	}
	public boolean isDoRegionImages() {
		return doRegionImages;
	}
	public void setDoRegionImages(boolean doRegionImages) {
		this.doRegionImages = doRegionImages;
	}
}
