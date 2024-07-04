package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.dto.MemberRequestDto;
import com.armagetdon.server.dto.response.MemberRes;
import com.armagetdon.server.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Member", description = "member 관련 API")
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 가입 및 닉네임 랜덤 생성 기능 API", description = "회원 가입 시 실행하는 API")
    @PostMapping("")
    public MemberRes joinMember() {
        return memberService.join();
    }

    @Operation(summary = "리워드 추가 API", description = "리워드 증가시키는 API")
    @PatchMapping("/tmp/reward")
    public ApiResponse tmp(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        // tmp increase reward
        memberService.tmp(patchRewardDto);
        return ApiResponse.onSuccess();
    }

    @Operation(summary = "고도 증가 API", description = "고도 증가시키는 API")
    @PatchMapping("/tmp/altitude")
    public ApiResponse tmp_altitude(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        // tmp increase altitude
        memberService.tmp_altitude(patchRewardDto);
        return ApiResponse.onSuccess();
    }

    @Operation(summary = "마이페이지 조회 API", description = "마이페이지 조회시 실행하는 API")
    @GetMapping("/mypage/{memberId}")
    public ApiResponse getMyPage(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    @Operation(summary = "리워드 조회 API", description = "리워드 조회 시 실행하는 API")
    @GetMapping("/reward/{memberId}")
    public ApiResponse getReward(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getReward(memberId));
    }

    @Operation(summary = "리워드 감소 API", description = "리워드 값 감소 시 실행하는 API")
    @PatchMapping("/reward")
    public ApiResponse patchReward(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        memberService.patchReward(patchRewardDto);
        return ApiResponse.onSuccess();
    }

    @Operation(summary = "랭킹 조회 API", description = "랭킹 조회하는 API")
    @GetMapping("/rank/{memberId}")
    public ApiResponse getRank(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getRank(memberId));
    }

}
