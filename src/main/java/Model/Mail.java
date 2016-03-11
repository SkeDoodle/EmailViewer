package Model;

import java.util.Date;

/**
 *
 * @author Thomas
 */
public class Mail {

    private String from;
    private String subject;
    private String toList;
    private String ccList;
    private Date sentDate;
    private String contentType;
    private String messageContent;

    /**
     *
     * @param from
     * @param subject
     * @param sentDate
     */
    public Mail(String from, String subject, Date sentDate) {
        this.from = from;
        this.subject = subject;
        this.sentDate = sentDate;
    }

    /**
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     *
     * @return
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return
     */
    public String getToList() {
        return toList;
    }

    /**
     *
     * @return
     */
    public String getCcList() {
        return ccList;
    }

    /**
     *
     * @return
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *
     * @return
     */
    public String getMessageContent() {
        return messageContent;
    }
}
