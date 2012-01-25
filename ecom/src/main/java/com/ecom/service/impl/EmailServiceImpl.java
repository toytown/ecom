package com.ecom.service.impl;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.ecom.domain.Language;
import com.ecom.service.interfaces.EmailService;

/**
 * Provides an implementation of the <tt>EmailService</tt> interface
 * 
 * @author chaikou.balde
 *
 */
public class EmailServiceImpl implements EmailService {
    
    /**
     * These string values are part of the 'message bundle' templates
     * */
    private static final String TEMPLATE_MSG_SUBJECT    = "subject.";
    private static final String TEMPLATE_MSG_BODY_ONE   = "body.part.one";
    private static final String TEMPLATE_MSG_BODY_TWO   = "body.part.two.";
    private static final String TEMPLATE_MSG_FOOTER     = "body.footer.";
    
    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);
    
    private JavaMailSender mailSender;
    
    private String senderEmailAddress;
    
    private String defaultEmailRecipient;
    
    private String redirectMailsToDefault;
    
    public EmailServiceImpl() {
    }

    /**
     * @return the mailSender
     */
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    /**
     * @return the senderEmail
     */
    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    /**
     * @param senderEmail the senderEmail to set
     */
    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    /**
     * @return the defaultEmailRecipient
     */
    public String getDefaultEmailRecipient() {
        return defaultEmailRecipient;
    }

    /**
     * @param defaultEmailRecipient the defaultEmailRecipient to set
     */
    public void setDefaultEmailRecipient(String defaultEmailRecipient) {
        this.defaultEmailRecipient = defaultEmailRecipient;
    }


    /**
     * @see com.wirecard.ib.core.services.interfaces.EmailService#getEmailBodyTemplate(com.wirecard.ib.model.Language, java.lang.String, java.util.Map)
     */
    @Override
    public String getEmailBodyTemplate(Language lang, String templateInitials, Map<String, Object> messageArguments) {
        Locale currentLocale = new Locale(lang.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle(templateInitials,currentLocale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
     
        formatter.applyPattern(bundle.getString(TEMPLATE_MSG_BODY_ONE));
        
        StringBuffer msgBuffer = new StringBuffer();
        msgBuffer.append(formatter.format(messageArguments.values().toArray()));
        
        // Invoicing parties have different names in DB and template 
        String partyName = (String)messageArguments.get("invoicingParty");
        
        msgBuffer.append(bundle.getString(TEMPLATE_MSG_BODY_TWO + partyName));
        msgBuffer.append(bundle.getString(TEMPLATE_MSG_FOOTER + partyName));

        return msgBuffer.toString();
    }
    
    /**
     * @see com.wirecard.ib.core.services.interfaces.EmailService#getEmailHeaderTemplate(com.wirecard.ib.model.Language, java.lang.String, java.util.Map)
     */
    @Override
    public String getEmailHeaderTemplate(Language lang, String templateInitials, Map<String, Object> messageArguments) {
        Locale currentLocale = new Locale(lang.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle(templateInitials,currentLocale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        String partyName = (String)messageArguments.get("invoicingParty");
        formatter.applyPattern(bundle.getString(TEMPLATE_MSG_SUBJECT + partyName));
        String msgSubject = formatter.format(messageArguments.values().toArray());
        
        return msgSubject;
    }
    
    /**
     * @see com.wirecard.ib.core.services.interfaces.EmailService#sendEmail(java.lang.String, java.lang.Object)
     */
    @Override
    public void sendEmail(final String recipient, final String from, 
            final String subject, final String emailBody) {
  
        sendEmailMessage(recipient, from, subject, emailBody, null);
        logger.info("Sent email to receiver " + recipient);
    }
    
    /**
     * @see com.wirecard.ib.core.services.interfaces.EmailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Object)
     */
    @Override
    public void sendEmail(final String recipient, final String from, 
            final String subject, final String emailBody, final List<String> attachmentFileName) {
        
        sendEmailMessage(recipient, from, subject, emailBody, attachmentFileName);
        logger.info("Sent email with attachment [" +attachmentFileName+ "] to receiver " + recipient);
    }

    /**
     * Helper method used to send the email message
     * */
    private void sendEmailMessage(final String recipientLists, final String from,
            final String subject, final String emailBody, final List<String> attachmentFileLocations) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                String[] recipientArr = new String[] {};

                if (getRedirectMailsToDefault() != null && getRedirectMailsToDefault().equalsIgnoreCase("true")) {
                    recipientArr = splitRecipientsIfRequired(getDefaultEmailRecipient());
                } else {
                    recipientArr = splitRecipientsIfRequired(recipientLists);
                }

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(recipientArr);
                message.setFrom(from);
                message.setText(emailBody);
                message.setSubject(subject);

                if (attachmentFileLocations != null) {

                    for (String fileLocation : attachmentFileLocations) {
                        FileSystemResource file = new FileSystemResource(new File(fileLocation));
                        message.addAttachment(file.getFilename(), file);
                    }
                }
            }
        };

        this.mailSender.send(preparator);

    }

    /**
     * Splits comma separated recipient list in array
     * @param commaSeparatedRecipientList
     * @return
     */
    public String[] splitRecipientsIfRequired(String commaSeparatedRecipientList) {

        if (commaSeparatedRecipientList != null && commaSeparatedRecipientList.contains(",")) {
            return commaSeparatedRecipientList.split(","); 
        } else {
            return new String[] { commaSeparatedRecipientList };
        }   
    }
    
    public String getRedirectMailsToDefault() {
        return redirectMailsToDefault;
    }

    public void setRedirectMailsToDefault(String redirectMailsToDefault) {
        this.redirectMailsToDefault = redirectMailsToDefault;
    }
    
}
