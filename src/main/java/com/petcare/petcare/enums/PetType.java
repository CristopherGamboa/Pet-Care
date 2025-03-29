package com.petcare.petcare.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetType {
    DOG(1, "Dog"),
    CAT(2, "Cat"),
    OTHER(3, "Other");
    

    private static final Map<Integer, PetType> CODE_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(PetType::getCode, Function.identity()));

    private final int code;
    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

    public static Optional<PetType> fromCode(int code) {
        return Optional.ofNullable(CODE_MAP.get(code));
    }

    public static Optional<PetType> fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(type -> type.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst();
    }
}
