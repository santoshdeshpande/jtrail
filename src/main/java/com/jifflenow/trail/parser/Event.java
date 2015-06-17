package com.jifflenow.trail.parser;

import java.util.Objects;

public class Event {

    private String company;

    private String event;

    private String meetingId;

    private String userType;

    private String uuid;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event1 = (Event) o;
        return Objects.equals(company, event1.company) &&
                Objects.equals(event, event1.event) &&
                Objects.equals(meetingId, event1.meetingId) &&
                Objects.equals(userType, event1.userType) &&
                Objects.equals(uuid, event1.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, event, meetingId, userType, uuid);
    }
}
