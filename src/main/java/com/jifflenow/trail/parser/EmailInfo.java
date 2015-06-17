package com.jifflenow.trail.parser;

import java.util.Objects;

public class EmailInfo {
    private String rawFrom;

    private String from;

    private String rawTo;

    private String to;

    public String getRawFrom() {
        return rawFrom;
    }

    public void setRawFrom(String rawFrom) {
        this.rawFrom = rawFrom;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRawTo() {
        return rawTo;
    }

    public void setRawTo(String rawTo) {
        this.rawTo = rawTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailInfo emailInfo = (EmailInfo) o;
        return Objects.equals(rawFrom, emailInfo.rawFrom) &&
                Objects.equals(from, emailInfo.from) &&
                Objects.equals(rawTo, emailInfo.rawTo) &&
                Objects.equals(to, emailInfo.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawFrom, from, rawTo, to);
    }
}
