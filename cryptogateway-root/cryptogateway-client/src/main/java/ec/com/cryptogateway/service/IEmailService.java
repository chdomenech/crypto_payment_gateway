package ec.com.cryptogateway.service;

import cryptogateway.vo.request.MailVO;

public interface IEmailService {
    
    /**
     * Send email
     * 
     * 
     * @param to
     * @param subject
     * @param body
     */
    public void sendMail(MailVO mailVO);
  
    
    /**
     * Send email with attachment
     * 
     * @param to
     * @param subject
     * @param body
     * @param fileToAttach
     */
    public void sendMailWithAttachment(MailVO mailVO);

    /**
     *  Send email with inline images
     * 
     * @param to
     * @param subject
     * @param fileToAttach
     */
    public void sendMailWithInlineResources(MailVO mailVO);
    
}
