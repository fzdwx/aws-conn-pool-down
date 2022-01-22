package demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = AwsProperties.PREFIX)
public class AwsProperties {

    /**
     * config prefix
     */
    public static final String PREFIX = "aws";

    /**
     * enable
     */
    private boolean enable = true;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * 自定义域名
     * customDomain
     * private String customDomain;
     * <p>
     * /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle {http://endpoint/bucketname} false
     * supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style
     * 模式{http://bucketname.endpoint}
     */
    private Boolean pathStyleAccess = true;

    /**
     * region
     */
    private String region;

    /**
     * Access key
     */
    private String accessKey;

    /**
     * Secret key
     */
    private String secretKey;

    /**
     * default bucket name
     */
    private String bucketName;

}