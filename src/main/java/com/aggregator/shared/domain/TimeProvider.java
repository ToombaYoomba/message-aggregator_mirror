package com.aggregator.shared.domain;

import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDateTime now();
}
