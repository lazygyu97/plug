package com.sparta.plug.entity;

public enum UserRoleEnum {
    //권한은 유저 또는 관리자
    // 뉴스 스포츠 뉴스, 연애뉴스 정치뉴스~
    // 카드 회사 결제 시스템
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}