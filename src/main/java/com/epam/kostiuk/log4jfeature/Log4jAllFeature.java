package com.epam.kostiuk.log4jfeature;

import com.epam.kostiuk.log4jfeature.appender.StreamLogAppender;
import com.epam.kostiuk.log4jfeature.levels.CustomLevel;
import org.apache.log4j.Logger;

public class Log4jAllFeature {

    public static final Logger LOGGER = Logger.getLogger(Log4jAllFeature.class);

    static
    {
        System.setProperty("mail.smtp.auth", "true");
        System.setProperty("mail.smtp.socketFactory.port", "465");
        System.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        System.setProperty("mail.smtp.socketFactory.fallback", "false");
        System.setProperty("mail.smtp.user","olegkostiuk11@gmail.com");
        System.setProperty("mail.smtp.starttls.enable","true");
        System.setProperty("mail.transport.protocol", "smtp");
        System.setProperty("mail.smtp.starttls.enable", "true");
        System.setProperty("mail.smtp.host", "smtp.gmail.com");
        System.setProperty("mail.smtp.port", "465");
        System.setProperty("mail.smtp.quitwait", "false");
    }

    public static void main(String[] args) {


        LOGGER.trace("Trace level message");
        LOGGER.debug("Debug level message");
        LOGGER.info("Info level message");
        LOGGER.log(CustomLevel.CUSTOM, "My custom level message");
        LOGGER.warn("Warn level message");
        LOGGER.error("Error level message");
        LOGGER.fatal("Fatal level message");

        //Sys out just shows that all logs also is aggregated in custom stream appender, and we can use it.
        System.out.println(new String(StreamLogAppender.getCurrentStreamAppender().toByteArray()));
    }
}
