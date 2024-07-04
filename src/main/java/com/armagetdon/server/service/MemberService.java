package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.dto.MemberRequestDto;
import com.armagetdon.server.dto.MemberResponseDto;
import com.armagetdon.server.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    //tmp create Member
    public void tmp(){
        Member member = new Member("nickName");
        memberRepository.save(member);
    }

    public MemberResponseDto.MyPageDto getMyPage(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));
        return MemberResponseDto.MyPageDto.from(member.getNickname(), member.getReward(), member.getAltitude());
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
