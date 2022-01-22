package demo.event;

import lombok.Data;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/1/22 9:27
 */
@Data
public class DownFileEvent {

    public static final String TOPIC = "downWxConcatFile";

    /**
     * need download file url.
     */
    private String fileUrl;
}