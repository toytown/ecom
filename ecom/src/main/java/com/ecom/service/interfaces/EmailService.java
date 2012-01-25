package com.ecom.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ecom.domain.Language;

/**
 * The <tt>EmailService</tt> is an interface which provide email features like 
 * sending any kind of email with or without attachment  
 * 
 * @author chaikou.balde
 */
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
     * Sends an email message 
     * 
     * @param emailMessage An <tt>EmailMessage</tt> object which includes all email related 
     *        information to allow this email to be sent
     */
    //public void sendEmail(EmailMessage emailMessage);

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
    public String getEmailBodyTemplate(Language lang, String templateInitials, Map<String, Object> messageArguments);
    
    /**
     * Retrieves the content of the email message based on a template.  
     */
    public String getEmailHeaderTemplate(Language lang, String templateInitials, Map<String, Object> messageArguments);

    /**
     * @return the senderEmailAddress
     */
    public String getSenderEmailAddress();

    /**
     * @param senderEmail the senderEmailAddress to set
     */
    public void setSenderEmailAddress(String senderEmailAddress);
    
    /**
     * @return the defaultEmailRecipient
     */
    public String getDefaultEmailRecipient();

    /**
     * @param defaultEmailRecipient the defaultEmailRecipient to set
     */
    public void setDefaultEmailRecipient(String defaultEmailRecipient);

}
