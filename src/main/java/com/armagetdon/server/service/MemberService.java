package com.armagetdon.server.service;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.dto.response.MemberRes;
import com.armagetdon.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final NicknameService nicknameService;

    public MemberRes join() {
        String nickname;
        do {
            nickname = nicknameService.generateNickname();
        } while (memberRepository.existsByNickname(nickname));

        Member member = Member.builder()
                .nickname(nickname)
                .altitude(0)
                .reward(0L)
                .post(new ArrayList<>())
                .recommend(new ArrayList<>())
                .complain(new ArrayList<>())
                .build();

        memberRepository.save(member);

        return MemberRes.of(member);
    }
}

