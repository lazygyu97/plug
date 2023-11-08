package com.sparta.plug.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "이름 또는 별명을 입력해주세요.")
    @Size(min=2,max=8,message = "이름(별명)은 최소 2글자에서 최대 8글자로 설정 할 수 있습니다.")
    private String nickName;

    @NotBlank(message = "아이디를 입력해주세요.") //정규식 -> chat gpt
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "아이디는 3~16자의 영문 대소문자, 숫자, 특수문자(_,-)만 사용할 수 있습니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    private boolean admin = false;
    private String adminToken = "";
}