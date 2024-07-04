package com.armagetdon.server.service;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.enums.Level;
import com.armagetdon.server.dto.response.MemberRes;
import com.armagetdon.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.dto.MemberRequestDto;
import com.armagetdon.server.dto.MemberResponseDto;
import jakarta.transaction.Transactional;

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

    @Transactional
    //tmp create Member
    public void tmp(){
        Member member = new Member("nickName");
        memberRepository.save(member);
    }

    public MemberResponseDto.MyPageDto getMyPage(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));

        int altitude = member.getAltitude() * 1000;
        Level level = Level.getLevelByAltitude(altitude);
        int leftAltitude = level.getLeftAltitude(altitude);

        return MemberResponseDto.MyPageDto.from(member.getNickname(), member.getReward(), level.getName(), leftAltitude);
    }

    public MemberResponseDto.RewardDto getReward(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));
        return MemberResponseDto.RewardDto.from(member.getReward());
    }

    @Transactional
    public void patchReward(MemberRequestDto.PatchRewardDto patchRewardDto){
        Member member = memberRepository.findById(patchRewardDto.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));

        // get reward
        long reward = patchRewardDto.getReward();
        long existingReward = member.getReward();

        // if reward is invalid, throw error
        if (reward > existingReward || reward < 0){
            throw new GeneralException(ErrorStatus._INVALID_REWARD);
        }

        // patch reward
        member.subReward(reward);
    }

    public MemberResponseDto.RankDto getRank(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));
        return MemberResponseDto.RankDto.from();
    }
}