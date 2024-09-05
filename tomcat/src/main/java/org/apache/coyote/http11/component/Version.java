package org.apache.coyote.http11.component;

import java.util.List;

public class Version {
    private static final String DELIMITER = "[/.]";

    private final int major;
    private final int minor;

    public Version(final String plaintext) {
        final var elements = List.of(plaintext.split(DELIMITER));
        validateProtocol(elements);
        validateVersion(Integer.parseInt(elements.get(1)), Integer.parseInt(elements.get(2)));
        this.major = Integer.parseInt(elements.get(1));
        this.minor = Integer.parseInt(elements.get(2));
    }

    private void validateProtocol(final List<String> elements) {
        if (elements.getFirst().equals("HTTP") && elements.size() == 3) {
            return;
        }
        throw new IllegalArgumentException("올바르지 않은 버전 정보");

    }

    private void validateVersion(final int major, final int minor) {
        if (major == 1 && (minor == 0 || minor == 1)) {
            return;
        }
        if (major == 2 && minor == 0) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 Http version");
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }
}
