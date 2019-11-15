package com.alessandro.calimero.lib;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TelegramToTopicMatcherTest {

    @Test
    @Order(1)
    void addMatch() {
        TelegramToTopicMatcher.addMatch("hello", "1.001", "1/1/0", "2/1/1");
        TelegramToTopicMatcher.addMatch("ciao", "1.002", "1/2/0", "2/2/1");
    }

    @Test
    @Order(2)
    void getDptFromCommandTopic() {
        TelegramToTopicMatcher.getDptFromCommandTopic("hello/1/1/0").ifPresent(s -> assertEquals(s, "1.001"));
        TelegramToTopicMatcher.getDptFromCommandTopic("ciao/1/2/0").ifPresent(s -> assertEquals(s, "1.002"));
        assertEquals(TelegramToTopicMatcher.getDptFromCommandTopic("ciao/1/6/0"), Optional.empty());
    }

    @Test
    @Order(3)
    void getStateTopicFromStateDp() {
        TelegramToTopicMatcher.getStateTopicFromStateDp("2/1/1").ifPresent(s -> assertEquals(s, "hello/2/1/1"));
        TelegramToTopicMatcher.getStateTopicFromStateDp("2/2/1").ifPresent(s -> assertEquals(s, "ciao/2/2/1"));
        assertEquals(TelegramToTopicMatcher.getStateTopicFromStateDp("2/6/1"), Optional.empty());
    }
}