package com.tosin.notez.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.access-key}")
    private String awsAccessKey;

    @Value("${aws.secret}")
    private String awsSecret;


    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(){

        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecret));
    }

    @Bean
    public AmazonS3 s3(){

        return AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider())
                .withRegion(awsRegion).build();
    }

}
