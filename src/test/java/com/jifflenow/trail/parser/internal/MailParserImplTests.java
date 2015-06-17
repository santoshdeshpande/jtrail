package com.jifflenow.trail.parser.internal;

import com.jifflenow.trail.parser.Calendar;
import com.jifflenow.trail.parser.MailParser;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MailParserImplTests {

    @Test
    public void emptyCalendarShouldBeReturnedForEmptyParams() {
        MailParser parser = new MailParserImpl();
        Calendar calendar = parser.parse(new HashMap<>());
        assertThat(calendar, equalTo(new Calendar()));
    }

    @Test
    public void emptyCalendarShouldBeReturnedForNoData() {
        Map<String,String> data = new HashMap<>();
        data.put("attachment","");
        MailParser parser = new MailParserImpl();
        Calendar calendar = parser.parse(data);
        assertThat(calendar, equalTo(new Calendar()));
    }

    @Test
    public void invalidUidReturnsEmptyCalendar() throws URISyntaxException, IOException {
        Map<String,String> params = getAsParams("yahoo_invalid_uid_length.ics", "", "Oracle Events <events+oracle+oow2013+3232+23123123213@events.jifflenow.com>", "Larry Elison, Oracle <larry.elison@oracle.com>", "uid");
        MailParser parser = new MailParserImpl();
        Calendar calendar = parser.parse(params);
        assertThat(calendar, equalTo(new Calendar()));
    }

    private Map<String,String> getAsParams(String icsFile, String html, String from, String to, String mode) {
        Map<String,String> params = new HashMap<>();
        params.put("html", html);
        params.put("from", from);
        params.put("to", to);
        params.put("mode",mode);
        params.put("attachment", readCalendarFile(icsFile));
        return params;

    }


    private String readCalendarFile(String fileName) {
        URL url = ClassLoader.getSystemClassLoader().getResource("calendars/"+fileName);
        if(url != null)
            try {
                return new String(Files.readAllBytes(Paths.get(url.toURI())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return null;
    }
}
