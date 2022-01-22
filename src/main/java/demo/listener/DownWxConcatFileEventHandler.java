package demo.listener;

import cn.hutool.http.HttpUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import demo.config.AwsProperties;
import demo.event.DownFileEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = DownFileEvent.TOPIC, consumerGroup = "wx-down-file-group", consumeThreadMax = 10)
public class DownWxConcatFileEventHandler implements RocketMQListener<DownFileEvent> {

    private static final String CONTENT_TYPE = "application/octet-stream";

    private final AmazonS3 amazonS3;
    private final AwsProperties awsProperties;

    @Override
    public void onMessage(final DownFileEvent event) {
        final String bucketName = awsProperties.getBucketName();
        final String key = RandomStringUtils.random(11);

        // get file,remote link
        // the file maybe 700MB,1G...
        final String fileUrl = event.getFileUrl();

        final InputStream inputStream = HttpUtil.createGet(fileUrl).execute().bodyStream();

        // upload
        amazonS3.putObject(bucketName, key, inputStream, meta(inputStream));
    }

    @SneakyThrows
    public static ObjectMetadata meta(InputStream inputStream) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentType(CONTENT_TYPE);
        objectMetadata.setContentLength(inputStream.available());

        return objectMetadata;
    }
}