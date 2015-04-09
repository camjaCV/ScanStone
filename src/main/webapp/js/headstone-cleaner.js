var hiModule = angular.module('headstone-cleaner', ['angularFileUpload']);

hiModule.directive('headstoneCleaner', ['$http', '$upload', function($http, $upload){
	return {
		restrict: 'A',
		replace: true,
		template: '<div>' +
					'<div class="description">' +
						'<div style="text-align: center">' +
							'<h2>Headstone Cleaner</h2>' +
						'</div>' +
						'<div ng-show="showDescription" align="left">' +
							'<h3>Demo</h3>' +
							'This is a demo of the core piece of my research while studying at BYU. To use, upload an image or choose from images that are already on the server. Don\'t have images of headstones lying around on your hard drive? Go to <a  target="_blank" href="http://billiongraves.com/">BillionGraves</a> to find one to upload.' +
							'<h3>Publication</h3> The details of this software is described in more detail in the publication: <blockquote><a target="_blank" href="http://proceedings.spiedigitallibrary.org/proceeding.aspx?articleid=1568640">Christiansen, Cameron S., and William A. Barrett. "Data acquisition from cemetery headstones." IS&T/SPIE Electronic Imaging. International Society for Optics and Photonics, 2013</a></blockquote> The abstract is as follows: <blockquote>Data extraction from engraved text is discussed rarely, and nothing in the open literature discusses data extraction from cemetery headstones. Headstone images present unique challenges such as engraved or embossed characters (causing inner-character shadows), low contrast with the background, and significant noise due to inconsistent stone texture and weathering. Current systems for extracting text from outdoor environments (billboards, signs, etc.) make assumptions (i.e. clean and/or consistently-textured background and text) that fail when applied to the domain of engraved text. The ability to extract the data found on headstones is of great historical value. This paper describes a novel and efficient feature-based text zoning and segmentation method for the extraction of noisy text from a highly textured engraved medium. This paper also demonstrates the usefulness of constraining a problem to a specific domain. The transcriptions of images zoned and segmented through the proposed system have a precision of 55% compared to 1% precision without zoning, a 62% recall compared to 39%, and an error rate of 78% compared to 8303%.</blockquote>' +
							'<h3>Output</h3>The output of the process displays the images that have been zoned and segmented to remove stone texture and non-text engravings. Two images are produced as a number of images contains text that is dark-on-light in one region and light-on-dark in the other. For examples of such headstones, try the pre-uploaded images <a href="#" ng-click="changeSelectedImage(\'004.JPG\')">004.JPG</a>, <a href="#" ng-click="changeSelectedImage(\'006.JPG\')">006.JPG</a>, or <a href="#" ng-click="changeSelectedImage(\'024.JPG\')">024.JPG</a>. A portion of my research involved using contextual knowldge to correct OCR errors. However, this is not included in this demo to put emphasis on the core piece of work as well as to encourage the user to pass the image through their own OCR implementation.<br /><br />To view the full resolution image, right click on the image to save, or view in a new window' + 
							'<h3>Contact</h3>For further information or questions please contact me at <a href="' + 'mail' + 'to:' + '{{username}}' + '@' + '{{hostname}}' + '">' + '{{linktext}}' + '</a>.' +
							'<br /><br /><b></b>' +
							'<br /><br />' + 
							
						'</div>' +
						'<a href="#" ng-click="toggleDescription(true)" ng-show="!showDescription">(Show description)</a>' +
						'<a href="#" ng-click="toggleDescription(false)" ng-show="showDescription">(Hide description)</a>' +
					'</div>' +
					'<div id="headstoneCleaner" class="demo">' +
						'<div id="results">' +
							'<span class="warn" ng-show="processing"> Processing Image... </span><br /><br />' +
							'<div class="originalImage">' +
								'Chosen Image: <br />' +
								'<img id="original" class="originalImage" /></a>' +
							'</div>' +
							'<div ng-show="hasResult">' +
								'<div class="resultImage">' +
									'<img id="normal" class="resultImage" />' +
								'</div>' +
								'<div class="resultImage">' +
									'<img id="inverted" class="resultImage" />' +
								'</div>' +
							'</div>' +
							'<div ng-show="hasResult && doRegionImages">' +
								'<div class="resultImage">' +
									'<img id="regionNormal" class="resultImage" />' +
								'</div>' +
								'<div class="resultImage">' +
									'<img id="regionInverted" class="resultImage" />' +
								'</div>' +
							'</div>' +
							'<div ng-if="result.processingTime">Processing time: <span ng-bind="result.processingTime" /> seconds</div><br /><br />' +
							'<div ng-if="result.ocr && hasResult">Transcription: <span ng-bind="result.ocr" /><br /><br /></div>' +
//							'<div ng-if="result.ABBYYOcr && hasResult">ABBYY Transcription: <span ng-bind="result.ABBYYOcr" /><br /><br /></div>' +
						'</div>' +
						'<div>' +
							'<input type="radio" ng-model="inputType" value="upload">upload image</input>' +
							'<input type="radio" ng-model="inputType" value="available">select a pre-uploaded image</input>' +
						'</div>' +
						'<hr />' +
						'<div id="chooseImageDiv" ng-show="inputType===\'available\'">' +
							'<select ng-model="result.inputImage" size="10" ng-options="availableImage as availableImage for availableImage in availableImages" ng-change="selectInputImage()">' +
							'</select>' +
						'</div>' +
						'<div id="uploadImageDiv" ng-show="inputType===\'upload\'">' +
							'<input type="file" ng-file-select="onFileSelect($files)" accept="image/jpeg,image/png"/>' +
						'</div>' +
						'<hr />' +
						'<div>' +
							'<input type="checkbox" id="doAutoSegment" name="doAutoSegment" ng-model="doAutoSegment" />Do initial auto segmentation<br />' +
							'<input type="checkbox" id="doOcrCheckbox" name="doOcr" ng-model="doOcr" />Do OCR<br />' +
//							'<input type="checkbox" id="doABBYYOcrCheckbox" name="doABBYYOcr" ng-model="doABBYYOcr" />Do ABBYY OCR<br />' +
							'<input type="checkbox" id="doRegionImages" name="doRegionImages" ng-model="doRegionImages" />Get Text Region Images<br />' +
							'<input type="button" id="submitButton" ng-click="submitPhoto()" ng-disabled="processing" value="submit" />' +
						'</div>' +
					'</div>' +
				'</div>'
,
		link: function(scope, element, attrs){
			var inputImageDir = "data/input-images";
			scope.result = {};
			scope.input = {};
			scope.hasResult = false;
			scope.inputType = "upload";
			scope.showDescription = true;
			scope.processing = false;
			scope.doOcr = false;
			scope.doRegionImages = false;
			scope.doAutoSegment = true;
			scope.submitPhoto = function()
			{
				scope.showDescription = false;
				scope.processing = true;
				$http.post('/headstone-cleaner/rest/binarize?doOcr=' + scope.doOcr + '&doRegionImages=' + scope.doRegionImages + '&doAutoSegment=' + scope.doAutoSegment, scope.result.inputImage).
			    success(function(result, status, headers, config) {
			    	scope.result = result;
			    	scope.result.inputImage = result.originalPath.substr(result.originalPath.lastIndexOf('/') + 1);
			    	scope.result.processingTime = Math.round((result.duration * 0.001) * 1000) / 1000;
			    	scope.processing = false;
					scope.hasResult = true;
					document.images.original.src = result.originalPath;
			    	document.images.normal.src = result.binarizedNormalPath;
			    	document.images.inverted.src = result.binarizedInvertedPath;
			    	document.images.regionNormal.src = result.regionNormalPath;
			    	document.images.regionInverted.src = result.regionInvertedPath;
			    }).
			    error(function(data, status, headers, config) {
			    	if (status === 429)
			    	{
			    		alert("You have exceeded the number of images that you can run this demo, please try again tomorrow, or contact me for further options");
			    	}
			    	else
			    	{
				    	alert("There was an error making the request");
			    	}
			    	scope.processing = false;
					scope.hasResult = false;
			    });
			};
			
			scope.onFileSelect = function($files){
				var file = $files[0];
		    	scope.result.processingTime = null;
		    	scope.hasResult = false;
		    	
				scope.upload = $upload.upload({
					url: "/headstone-cleaner/rest/upload",
					file: file
				}).success(function(data, status, headers, config){
			    	document.images.original.src = data;
			    	scope.result.inputImage = data.substr(data.lastIndexOf('/') + 1);
				}).error(function(data, status, headers, config){
			    	if (status === 429)
			    	{
			    		alert("You have exceeded the number of images that you can run this demo, please try again tomorrow, or contact me for further options");
			    	}
			    	else
			    	{
			    		alert("There was an error uploading the image");
			    	}
			    	scope.processing = false;
					scope.hasResult = false;
				});
			};
			
			scope.getAvailableImages = function(){
				$http.get('/headstone-cleaner/rest/inputImages').
				success(function(data, status, headers, config) {
					scope.availableImages = data;
				}).
				error(function(data, status, headers, config) {
		    		alert("There was an error getting the available images");
				});
			};
			
			scope.selectInputImage = function(){
				document.images.original.src = inputImageDir + "/" + scope.result.inputImage;
				scope.hasResult = false;
				scope.result.processingTime = null;
			};
			
			scope.toggleDescription = function(show){
				scope.showDescription = show;
			};
			
			scope.changeSelectedImage = function(optionValue){
				scope.inputType = 'available';
				scope.hasResult = false;
				scope.result.inputImage = optionValue;
				document.images.original.src = inputImageDir + "/" + scope.result.inputImage;
				scope.submitPhoto();
			};
			
			scope.username = "cam";
			scope.hostname = "cameronchristiansen";
			scope.linktext = scope.username + "@" + scope.hostname;
			
			scope.getAvailableImages();
		}
	};
}]);