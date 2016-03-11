package Controller;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Sukru
 *
 */
public class Output {

 
    private final File configFile = new File("smtp.properties");
    private Properties configProps;
    
    
    /**
     *
     * @return
     * @throws IOException
     */
    public Properties loadProperties() throws IOException {
        
        Properties defaultProps = new Properties();
        configProps = new Properties(defaultProps);

        // Charge les propriétés du fichier 
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            configProps.load(inputStream);
        }

        return configProps;
    }

    /**
     *
     * @param host
     * @param port
     * @param user
     * @param pass
     * @throws IOException
     */
    public void saveProperties(String host, String port, String user, String pass) throws IOException {

        configProps.setProperty("mail.smtp.host", host);
        configProps.setProperty("mail.smtp.port", port);
        configProps.setProperty("mail.user", user);
        configProps.setProperty("mail.password", pass);
        configProps.setProperty("mail.smtp.starttls.enable", "true");
        configProps.setProperty("mail.smtp.auth", "true");
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "host setttings");

    }

}
