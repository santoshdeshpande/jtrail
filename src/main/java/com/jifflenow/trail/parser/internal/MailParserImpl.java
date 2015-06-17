package com.jifflenow.trail.parser.internal;

import com.jifflenow.trail.parser.Calendar;
import com.jifflenow.trail.parser.EmailInfo;
import com.jifflenow.trail.parser.MailParser;

import java.util.Base64;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailParserImpl implements MailParser {

    private Map<String, String> params;
    private String eventCalendar;
    private String prodId;

    @Override
    public Calendar parse(Map<String, String> params) {
        this.params = params;
        if(params.size() == 0)
            return new Calendar();
        String attachment = params.getOrDefault("attachment","");
        if(attachment.trim().equals(""))
            return new Calendar();
        this.eventCalendar = extractEventCalendar();
        this.prodId = getProductId();
        EmailInfo uid = decodeUID();
        if(uid == null) {
            return new Calendar();
        }
//        System.out.println(this.prodId);
        return null;
    }

    private String extractEventCalendar() {
        Pattern pattern = Pattern.compile("BEGIN:VEVENT\r?\n(.*\r?\n)+END:VEVENT\r?\n", Pattern.MULTILINE|Pattern.DOTALL); //(.*\n)*?END:VEVENT\r?\n)");
        String attachment = params.get("attachment");
        Matcher matcher = pattern.matcher(attachment);
        String eventInfo = null;
        while(matcher.find()) {
            eventInfo = matcher.group(1);
        }
        return eventInfo;
    }

    private String getProductId() {
        Pattern pattern = Pattern.compile("PRODID:(.*?)\r?\n", Pattern.DOTALL);
        String attachment = params.get("attachment");
        Matcher matcher = pattern.matcher(attachment);
        String prodId = null;
        while(matcher.find()) {
            prodId = matcher.group(1);
        }
        return prodId;
    }

    private String getUID() {
        Pattern pattern = Pattern.compile("UID:((.*?)\r?\n(\\s.*?\r?\n)*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(this.eventCalendar);
        if (matcher.find()) {
            String uid = matcher.group(1).replaceAll("\n ","");
            System.out.println(uid);
            return uid;
        }
        return null;
    }

    private EmailInfo decodeUID() {
        String uid = getUID();
        int mod = uid.length() % 4;
        if(mod != 0) {
            String repeated  = new String(new char[mod]).replace("\0", "=");
            uid += repeated;
        }
        try {
            String decoded = new String(Base64.getDecoder().decode(uid));
            return new EmailInfo();
        } catch(IllegalArgumentException i) {
            return null;
        }
    }
}
