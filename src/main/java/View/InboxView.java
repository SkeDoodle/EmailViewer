package View;

import Model.Inbox;
import Model.Mail;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.joda.time.DateTime;

/**
 *
 * @author Thomas
 */
public class InboxView extends JPanel {

    /**
     *
     * @param inbox
     */
    public InboxView(Inbox inbox) {

        inbox.getEmails();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        for (Mail mail : inbox.mailList) {

            Date date = mail.getSentDate();
            String dateToDisplay;

            if (new DateTime(date).isBefore(DateTime.now().minusDays(1))) {
                //display Month and Day
                dateToDisplay = new SimpleDateFormat("MMM dd").format(date);
            } else {

                //display hours and minutes
                dateToDisplay = new SimpleDateFormat("HH:mm").format(date);
            }

            JLabel from = new JLabel(mail.getFrom());
            JLabel subject = new JLabel(mail.getSubject());
            JLabel sentDate = new JLabel(dateToDisplay);

            JPanel MailElementsPanel = new JPanel();

            MailElementsPanel.setLayout(new BoxLayout(MailElementsPanel, BoxLayout.LINE_AXIS));
            MailElementsPanel.add(from);
            MailElementsPanel.add(Box.createHorizontalGlue());
            MailElementsPanel.add(subject);
            MailElementsPanel.add(Box.createHorizontalGlue());
            MailElementsPanel.add(sentDate);

            MailElementsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(MailElementsPanel);

        }
    }
}
