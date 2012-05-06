package com.ecom.service.impl;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ecom.domain.Language;
import com.ecom.service.interfaces.EmailService;

/**
 * Provides an implementation of the <tt>EmailService</tt> interface
 * 
 * @author Prasanna.Tuladhar
 *
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
    
    /**
     * These string values are part of the 'message bundle' templates
     * */
    private static final String TEMPLATE_MSG_SUBJECT    = "subject";
    private static final String TEMPLATE_MSG_BODY   = "body";
    private static final String TEMPLATE_MSG_FOOTER     = "footer";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
    
    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);
    
    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;
    
    
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
     * @see com.wirecard.ib.core.services.interfaces.EmailService#getEmailBodyTemplate(com.wirecard.ib.model.Language, java.lang.String, java.util.Map)
     */
    @Override
    public String getEmailBodyTemplate(Language lang, String templateInitials, Object[] messageArguments) {
        Locale currentLocale = new Locale(lang.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle(templateInitials,currentLocale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
     
        formatter.applyPattern(bundle.getString(TEMPLATE_MSG_BODY));
        
        StringBuffer msgBuffer = new StringBuffer();
        msgBuffer.append(formatter.format(messageArguments));
        msgBuffer.append(bundle.getString(TEMPLATE_MSG_FOOTER));

        return msgBuffer.toString();
    }
    
    /**
     * @see com.wirecard.ib.core.services.interfaces.EmailService#getEmailHeaderTemplate(com.wirecard.ib.model.Language, java.lang.String, java.util.Map)
     */
    @Override
    public String getEmailHeaderTemplate(Language lang, String templateInitials, Object[] messageArguments) {
        Locale currentLocale = new Locale(lang.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle(templateInitials,currentLocale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(bundle.getString(TEMPLATE_MSG_SUBJECT));
        String msgSubject = formatter.format(messageArguments);
        
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

                recipientArr = splitRecipientsIfRequired(recipientLists);

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
    
    public boolean validateEmail(String email){

        // Match the given string with the pattern
        Matcher m = EMAIL_PATTERN.matcher(email);

        // check whether match is found
        boolean matchFound = m.matches();

        StringTokenizer st = new StringTokenizer(email, ".");
        String lastToken = null;
        while (st.hasMoreTokens()) {
           lastToken = st.nextToken();
        }

        if (matchFound && lastToken.length() >= 2
           && email.length() - 1 != lastToken.length()) {

           // validate the country code
           return true;
        }
        else return false;
     }

    
}
