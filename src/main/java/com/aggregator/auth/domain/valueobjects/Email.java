package com.aggregator.auth.domain.valueobjects;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final String value;

    public Email(String value) {
        if (value == null || value.length() > 255 || !PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format or length");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
