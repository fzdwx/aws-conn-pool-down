package demo.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aws config.
 */
@Configuration
@EnableConfigurationProperties(AwsProperties.class)
public class AwsConfiguration {

    @Bean
    public AmazonS3 amazonS3(@Autowired AwsProperties ossProperties) {
        final ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxConnections(200);
        clientConfiguration.setConnectionTimeout(ClientConfiguration.DEFAULT_CONNECTION_TIMEOUT * 10);

        final AwsClientBuilder.EndpointConfiguration configuration = new AwsClientBuilder.EndpointConfiguration(
                ossProperties.getEndpoint(), ossProperties.getRegion());
        final AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
                ossProperties.getSecretKey());
        final AWSCredentialsProvider provider = new AWSStaticCredentialsProvider(awsCredentials);

        return AmazonS3Client.builder().withEndpointConfiguration(configuration)
                .withClientConfiguration(clientConfiguration).withCredentials(provider)
                .disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
    }
}