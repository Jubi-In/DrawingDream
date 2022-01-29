package com.dd.api.service;

import java.util.UUID;

import com.dd.api.dto.request.SubCommentRegisterRequestDto;
import com.dd.api.dto.response.SubCommentGetListWrapperResponseDto;

public interface SubCommentService {

//	void registerSubComment(String accessToken, SubCommentRegisterRequestDto subCommentRegisterRequestDto);
	void registerSubComment(SubCommentRegisterRequestDto subCommentRegisterRequestDto);
//	boolean deleteSubComment(String accessToken, UUID commentId)
	boolean deleteSubComment(UUID commentId);
//	SubCommentGetListWrapperResponseDto getSubCommentList(String accessToken, UUID commentId);
	SubCommentGetListWrapperResponseDto getSubCommentList(UUID commentId);
	String getLoginIdFromToken(String accessToken);

}
