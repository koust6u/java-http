package org.apache.coyote.http11.component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RequestHeaders extends Headers {

    public RequestHeaders(final String plaintext) {
        Stream.of(plaintext.split(PARAMETER_DELIMITER))
                .filter(paramText -> !paramText.isBlank())
                .forEach(this::consume);
    }

    private void consume(final String param) {
        Objects.requireNonNull(param);
        // TODO: 다중 값 허용
        final var pair = List.of(param.split(KEY_VALUE_DELIMITER, 2));
        if (pair.size() < 2) {
            throw new IllegalArgumentException("해더 올바르지 않은 파라미터 갯수");
        }
        super.values.put(pair.get(0).toLowerCase(), pair.get(1));
    }

    public String get(final String key) {
        return super.values.get(key.toLowerCase());
    }
}