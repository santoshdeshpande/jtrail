package com.jifflenow.trail.parser;


import java.util.Map;

public interface MailParser {
    Calendar parse(Map<String, String> params);
}
