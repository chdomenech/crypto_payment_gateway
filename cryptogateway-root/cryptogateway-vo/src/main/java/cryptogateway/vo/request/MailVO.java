package cryptogateway.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailVO {

    private String from;
    private String to;
    private String subject;
    private String content;    
    private String fileToAttach;
}
