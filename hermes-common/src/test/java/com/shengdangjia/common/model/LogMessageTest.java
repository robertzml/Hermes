package com.shengdangjia.common.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogMessageTest {

    @Test
    void testToString() {
        LogMessage m = new LogMessage(5, "test", "toString", "that's it");
        System.out.println(m);
    }
}