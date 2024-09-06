package org.apache.coyote.http11.component;

public class ResponseLine {

    private final Version version;
    private final StatusCode statusCode;

    public ResponseLine(final Version version, final StatusCode statusCode) {
        this.version = version;
        this.statusCode = statusCode;
    }

    public String getResponseText() {
        return version.getResponseText() + " " + statusCode.getResponseText();
    }
}