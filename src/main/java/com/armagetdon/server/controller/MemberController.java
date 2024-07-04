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

    @PostMapping("/tmp")
    public ApiResponse tmp(){
        memberService.tmp();
        return ApiResponse.onSuccess();
    }

    @GetMapping("/mypage/{memberId}")
    public ApiResponse getMyPage(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    @GetMapping("/reward/{memberId}")
    public ApiResponse getReward(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getReward(memberId));
    }

    @PatchMapping("/reward")
    public ApiResponse patchReward(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        memberService.patchReward(patchRewardDto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/rank/{memberId}")
    public ApiResponse getRank(@PathVariable(name = "memberId") Long memberId){
        return ApiResponse.onSuccess(memberService.getRank(memberId));
    }

}
