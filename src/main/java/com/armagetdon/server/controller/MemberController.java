package com.armagetdon.server.controller;

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
}


