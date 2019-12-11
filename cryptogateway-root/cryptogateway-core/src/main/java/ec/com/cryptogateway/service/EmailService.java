package ec.com.cryptogateway.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cryptogateway.vo.request.MailVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Email Service
 * 
 * @author cdomenech
 *
 */
@Slf4j
@Service
public class EmailService implements IEmailService{

    @Autowired
    private JavaMailSender mailSender;
    
    public static final Integer NUMBER_THREADS = 20;
    
    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(NUMBER_THREADS); 

    
    /**
     * Send ASynchronous Email 
     * 
     * @param to
     * @param subject
     * @param body
     * @throws Exception
     */
    public void sendASynchronousMail(String to, String subject, String body) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    mailSender.send(message);
                }catch(Exception e){
                    log.error("Exception occur while sendASynchronousMail {}: ",e);
                }
            }
        });
    }
    
    /**
     * Send Simple email
     * 
     */
    @Override
    public void sendMail(MailVO mailVO) {
        try {
            sendASynchronousMail(mailVO.getTo(), mailVO.getSubject(),mailVO.getContent());
        } catch (Exception e) {
            log.error("Exception occur while sendMail {}: ",e);
        }
        
    }

    @Override
    public void sendMailWithAttachment(MailVO mailVO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendMailWithInlineResources(MailVO mailVO) {
        // TODO Auto-generated method stub
        
    }

}
