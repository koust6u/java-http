package org.apache.coyote.http11.component.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.apache.coyote.http11.component.request.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginHandlerTest {

    @Test
    @DisplayName("로그인에 성공하면 index.html로 302 redirect하는 HttpResponse를 반환한다.")
    void return_http_response_that_has_302_status_code_with_location_index_html() {
        // given
        final var request = new HttpRequest(
                String.join("\r\n",
                        "GET /login?account=gugu&password=password HTTP/1.1 ",
                        "Host: http://localhost:8080",
                        "")
        );
        final var loginHandler = new LoginHandler(request);

        // when
        final var response = loginHandler.handle();

        // then
        assertThat(response.getResponseText())
                .isEqualTo(String.join("\r\n",
                        "HTTP/1.1 302 FOUND ",
                        "Location: http://localhost:8080/index.html"
                        , "\r\n"));
    }

    @Test
    @DisplayName("없는 아이디로 로그인 요청 시 예외를 발생시킨다.")
    void throw_exception_when_login_with_does_not_exist_account() {
        // given
        final var request = new HttpRequest(
                String.join("\r\n",
                        "GET /login?account=gugu1&password=password HTTP/1.1 ",
                        "Host: http://localhost:8080",
                        "")
        );
        final var loginHandler = new LoginHandler(request);

        // when & then
        assertThatThrownBy(loginHandler::handle)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("틀린 비밀번호로 로그인 요청 시 예외를 발생시킨다.")
    void throw_exception_when_login_with_incorrect_password() {
        // given
        final var request = new HttpRequest(
                String.join("\r\n",
                        "GET /login?account=gugu1&password=password1 HTTP/1.1 ",
                        "Host: http://localhost:8080",
                        "")
        );
        final var loginHandler = new LoginHandler(request);

        // when & then
        assertThatThrownBy(loginHandler::handle)
                .isInstanceOf(IllegalArgumentException.class);
    }
}