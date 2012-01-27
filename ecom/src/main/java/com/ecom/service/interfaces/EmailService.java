package com.ecom.service.interfaces;

import java.util.List;

import com.ecom.domain.Language;


public interface EmailService {
    
    /**
     * Sends an email message 
     * 
     * @param reciepient The language in which the email will be be sent
     * @param from The body (content) of the email message
     * @param subject The subject of the email message to be sent
     * @param emailBody The body (content) of the email message
     * 
     * */
    public void sendEmail(String recipient, String from, String subject, String emailBody);
    

    /**
     * Sends an email message with an attached file  
     * 
     * @param reciepient The language in which the email will be be sent
     * @param from The body (content) of the email message
     * @param subject The subject of the email message to be sent
     * @param emailBody The body (content) of the email message
     * @param attachmentFileNameLocations a List of file names as they 
     *        will appear in the mail attachements
     * 
     * */
    public void sendEmail(String recipient, String from, String subject, String emailBody, 
            List<String> attachmentFileNameLocations);

    /**
     * Retrieves the content of the email message based on a template.  
     * 
     * @param lang The template to be used based on the language
     * @param templateInitials The initial name of the template to be used 
     * @param messageArguments The arguments parameters to be passed to the template file
     * 
     * @return A String array containing the arguments of the subject the arguments of the body of the message 
     * */
    public String getEmailBodyTemplate(Language lang, String templateInitials, Object[] messageArguments);
    
    /**
     * Retrieves the content of the email message based on a template.  
     */
    public String getEmailHeaderTemplate(Language lang, String templateInitials, Object[] messageArguments);

    

    
    public boolean validateEmail(String email);

}
