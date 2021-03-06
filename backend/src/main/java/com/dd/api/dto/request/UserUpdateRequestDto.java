package com.dd.api.dto.request;

import com.dd.db.enums.Code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("UserUpdateRequestDto")
@NoArgsConstructor
@Getter
public class UserUpdateRequestDto {
	
	@ApiModelProperty(name="유저 정보 - 전화번호")
	private String phone;
	@ApiModelProperty(name="유저 정보 - 보호자 전화번호")
	private String parentPhone;
	@ApiModelProperty(name="유저 정보 - 이메일")
	private String userEmail;
	@ApiModelProperty(name="유저 정보 - 주소")
	private String address;
	@ApiModelProperty(name="유저 정보 - 상세 주소")
	private String addressDetail;
	
	@ApiModelProperty(name="소속 정보 - 학년코드")
	private Code gradeCode;
	@ApiModelProperty(name="소속 정보 - 반코드")
	private Code classCode;
	@ApiModelProperty(name="소속 정보 - 번호")
	private Integer studentNo;
}
