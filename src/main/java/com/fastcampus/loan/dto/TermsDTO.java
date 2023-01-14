package com.fastcampus.loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TermsDTO implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {

        private String name;

        private String termsDetailUrl;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long termsId;

        private String name;

        private String termsDetailUrl;

        private LocalDateTime createAt;

        private LocalDateTime updatedAt;

    }
}
