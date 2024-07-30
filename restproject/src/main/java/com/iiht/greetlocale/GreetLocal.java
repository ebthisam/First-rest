package com.iiht.greetlocale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetLocal {

    @GetMapping("/hello/{headers}")
    public ResponseEntity<String> greetings(@RequestHeader HttpHeaders headers) {
        Locale locale = getLocaleFromHeaders(headers);
        String greeting = getGreeting(locale);
        return ResponseEntity.status(HttpStatus.OK).body(greeting);
    }

    private Locale getLocaleFromHeaders(HttpHeaders headers) {
        if (headers.getAcceptLanguageAsLocales() != null && !headers.getAcceptLanguageAsLocales().isEmpty()) {
            return headers.getAcceptLanguageAsLocales().get(0);
        } else {
            // If no Accept-Language header is provided, default to Locale.US
            return Locale.US;
        }
    }

    private String getGreeting(Locale locale) {
        switch (locale.getLanguage()) {
            case "en":
                if (locale.getCountry().equals("UK")) {
                    return "Hello";
                }
                break;
            case "fr":
                return "Bonjour";
            case "ind":
                return "Namasthe";
            default:
                return "Hello";
        }
        return "Hello";
    }
}
