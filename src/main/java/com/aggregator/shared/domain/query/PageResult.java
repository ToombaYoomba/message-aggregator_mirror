package com.aggregator.shared.domain.query;

import java.util.List;

public record PageResult<T>(List<T> items, long totalElements, int totalPages) {}
