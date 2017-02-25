package com.mycomp.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	
	@Autowired
    private AmazonS3Client s3Client;

    private static final String S3_BUCKET_NAME = "gundraju.com";


    /**
     * Save image to S3 and return CustomerImage containing key and public URL
     * 
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public void saveFileToS3(MultipartFile multipartFile)  {

        try{
            File fileToUpload = convert(multipartFile);
            String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();

            /* save file */
            s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));

            /* get signed URL (valid for one year) */
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(DateTime.now().plusWeeks(1).toDate());

            URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest); 
            System.out.println();

        }
        catch(Exception ex){   
            throw new RuntimeException("An error occurred saving file to S3", ex);
        }  
    }
    
    private File convert(MultipartFile file) throws Exception
    {    
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile(); 
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(file.getBytes());
        fos.close(); 
        return convFile;
    }

}
