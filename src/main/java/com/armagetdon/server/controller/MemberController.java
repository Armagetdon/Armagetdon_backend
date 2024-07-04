package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.dto.MemberRequestDto;
import com.armagetdon.server.dto.response.MemberRes;
import com.armagetdon.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public MemberRes joinMember() {
        return memberService.join();
    }

    @PatchMapping("/tmp/reward")
    public ApiResponse tmp(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        // tmp increase reward
        memberService.tmp(patchRewardDto);
        return ApiResponse.onSuccess();
    }

    @PatchMapping("/tmp/altitude")
    public ApiResponse tmp_altitude(@RequestBody MemberRequestDto.PatchRewardDto patchRewardDto){
        // tmp increase altitude
        memberService.tmp_altitude(patchRewardDto);
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
