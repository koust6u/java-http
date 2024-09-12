package org.apache.coyote.http11.component.handler;

import org.apache.coyote.http11.component.request.HttpRequest;
import org.apache.coyote.http11.component.response.HttpResponse;

public interface HttpHandler {

    String getPath();

    HttpResponse handle(HttpRequest request);
}
