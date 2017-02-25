package com.mycomp.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/customer/picture")
public class S3PictureController {
	
	@Autowired
	private S3Service  s3Service;
	
@PostMapping
public void saveFileToS3( @RequestParam(value="image", required=true) MultipartFile multipartFile )	{
		
		s3Service.saveFileToS3(multipartFile);
		
	}
		
}
