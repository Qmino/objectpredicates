package com.qmino.objectpredicates;

import java.time.LocalDateTime;
import java.util.Date;

// A bit weird, to test different date types
public record Clock(Date configureTime, LocalDateTime alarmTime) {
}
