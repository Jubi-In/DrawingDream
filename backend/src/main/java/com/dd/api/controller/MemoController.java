package com.dd.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dd.api.dto.request.MemoRegistRequestDto;
import com.dd.api.dto.request.MemoUpdateRequestDto;
import com.dd.api.dto.response.MemoResponseDto;
import com.dd.api.service.MemoService;
import com.dd.common.model.BaseResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "메모 위젯 API", tags = { "Memo" })
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memo")
public class MemoController {

	private final MemoService memoService;
	
	@PostMapping
	@ApiOperation(value = "메모 등록하기", notes="<strong>로그인한 회원이 작성한 메모를 등록한다.</strong><br/>")
	@ApiResponses({
		@ApiResponse(code=201, message="메모가 정상적으로 등록되었습니다."),
		@ApiResponse(code=401, message="인증되지 않은 사용자입니다."),
		@ApiResponse(code=409, message="메모등록을 실패했습니다.")
	})
	public ResponseEntity<? extends BaseResponseDto> regist(
		@ApiIgnore @RequestHeader("Authorization") String accessToken, 
		@RequestBody @ApiParam(value = "등록할 메모", required = true) MemoRegistRequestDto memoRegistRequestDto){
		if(memoService.createMemo(accessToken, memoRegistRequestDto) != null)
			return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
		return ResponseEntity.status(409).body(BaseResponseDto.of(409, "Fail"));
	}
	
	@GetMapping("/{memoId}")
	@ApiOperation(value = "메모 불러오기", notes="<strong>memoID에 해당하는 메모를 불러온다.</strong>")
	@ApiResponses({
		@ApiResponse(code=201, message="메모을 정상적으로 조회하였습니다."),
		@ApiResponse(code=401, message="인증되지 않은 사용자입니다."),
		@ApiResponse(code=409, message="메모조회를 실패했습니다.")
	})
	public ResponseEntity<MemoResponseDto> getMemo(
			@PathVariable("memoId") @RequestBody @ApiParam(value = "조회할 메모ID", required = true) UUID memoId){
		return ResponseEntity.status(200).body(memoService.getMemo(memoId));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "메모 목록 불러오기", notes="<strong>로그인한 유저가 작성한 메모 리스트를 불러온다.</strong>")
	@ApiResponses({
		@ApiResponse(code=201, message="메모 목록을 정상적으로 조회하였습니다."),
		@ApiResponse(code=401, message="인증되지 않은 사용자입니다."),
		@ApiResponse(code=409, message="메모조회를 실패했습니다.")
	})
	public ResponseEntity<List<MemoResponseDto>> getMemoList(
			@ApiIgnore @RequestHeader("Authorization") String accessToken ) {
		return ResponseEntity.status(200).body(memoService.getMemoList(accessToken));
	}
	
	@PutMapping
	@ApiOperation(value = "메모 수정하기", notes="<strong>작성한 메모를 수정한다.</strong>")
	@ApiResponses({
		@ApiResponse(code=201, message="메모가 정상적으로 수정되었습니다."),
		@ApiResponse(code=401, message="인증되지 않은 사용자입니다."),
		@ApiResponse(code=409, message="메모수정을 실패했습니다.")
	})
	public ResponseEntity<? extends BaseResponseDto> update(
		@RequestBody @ApiParam(value = "등록할 메모", required = true) MemoUpdateRequestDto memoUpdateRequestDto){
		if(memoService.updateMemo(memoUpdateRequestDto) != null)
			return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
		return ResponseEntity.status(409).body(BaseResponseDto.of(409, "Fail"));
	}
	
	@PutMapping("/delete/{memoId}")
	@ApiOperation(value = "메모 삭제하기", notes="<strong>작성한 메모를 삭제한다.</strong>")
	@ApiResponses({
		@ApiResponse(code=201, message="메모가 정상적으로 삭제되었습니다."),
		@ApiResponse(code=401, message="인증되지 않은 사용자입니다."),
		@ApiResponse(code=409, message="메모삭제를 실패했습니다.")
	})
	public ResponseEntity<? extends BaseResponseDto> delete(
			@PathVariable("memoId") @RequestBody @ApiParam(value = "삭제할 메모ID ", required = true) UUID memoId){
		if(memoService.deleteMemo(memoId) != null)
			return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
		return ResponseEntity.status(409).body(BaseResponseDto.of(409, "Fail"));
	}
}
