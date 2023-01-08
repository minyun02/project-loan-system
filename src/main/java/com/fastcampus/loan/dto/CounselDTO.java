package com.fastcampus.loan.dto;

import lombok.*;

import java.time.LocalDateTime;

public class CounselDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    //@Setter//request를 보낼때 값을 set할 이유가 없어서 주석처리
    public static class Request {
        private Long counselId;
        private String name;
        private String cellPhone;
        private String email;
        private String memo;
        private String address;
        private String addressDetail;
        private String zipCode;
        private LocalDateTime appliedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder //응답받은 엔티티를 변환하는 과정에서 사용한다.
    @Getter
    @Setter
    public static class Response {
        private Long counselId;

        private String name;

        private String cellPhone;

        private String email;

        private String memo;

        private String address;

        private String addressDetail;

        private String zipCode;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
