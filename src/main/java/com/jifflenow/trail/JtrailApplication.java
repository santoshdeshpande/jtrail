package com.jifflenow.trail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class JtrailApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JtrailApplication.class, args);
        parse();
    }

    private static String parse() {
//        String message = "ATTENDEE;PARTSTAT=ACCEPTED;CN=\"Gemini Ganesan/try154869\"\n" +
//                " ;SENT-BY=\"mailto:gemini.ganesan@try154869.notes.na.collabserv.com\"\n" +
//                " ;RSVP=FALSE:mailto:gemini.ganesan@try154869.notes.na.collabserv.com";
//        Pattern ptrn = Pattern.compile("ATTENDEE(:|;)(.*?\\r?\\n)(\\s.*?\\r?\\n)*",Pattern.MULTILINE);
        String message = "ATTENDEE;PARTSTAT=ACCEPTED;CN=\"Gemini Ganesan/try154869\"\n" +
                " ;SENT-BY=\"mailto:gemini.ganesan@try154869.notes.na.collabserv.com\"\n" +
                " ;RSVP=FALSE:mailto:gemini.ganesan@try154869.notes.na.collabserv.com\"\n" ;
        System.out.println(message);
        Pattern ptrn = Pattern.compile("ATTENDEE[;|:]?(.*?\r?\n(\\s.*\r?\n)+)");
        Matcher matcher = ptrn.matcher(message);
        boolean result = matcher.matches();
        System.out.println(result + "   " + matcher.groupCount());
        if(result) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(1).replace("\n ",""));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
        }
        return message;
    }
}
