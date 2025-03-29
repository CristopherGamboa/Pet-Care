package com.petcare.petcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum InvoiceStatus {
    CANCELLED(-1, "Cancelled"),
    PENDING(0, "Pending"),
    PAID(1, "Paid");

    private static final Map<Integer, InvoiceStatus> CODE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(InvoiceStatus::getCode, Function.identity()));

    private final int code;
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

    public static Optional<InvoiceStatus> fromCode(int code) {
        return Optional.ofNullable(CODE_MAP.get(code));
    }

    public static Optional<InvoiceStatus> fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(status -> status.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst();
    }
}
