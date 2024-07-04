package com.armagetdon.server.service;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.enums.Level;
import com.armagetdon.server.dto.response.MemberRes;
import com.armagetdon.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    //tmp increase reward
    public void tmp(MemberRequestDto.PatchRewardDto patchRewardDto){
        Member member = memberRepository.findById(patchRewardDto.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));
        long reward = patchRewardDto.getReward();
        member.addReward(reward);
    }

    @Transactional
    // tmp increase altitude
    public void tmp_altitude(MemberRequestDto.PatchRewardDto patchRewardDto){
        Member member = memberRepository.findById(patchRewardDto.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));
        int altitude = Math.toIntExact(patchRewardDto.getReward());
        member.addAltitude(altitude);
    }


    public MemberResponseDto.MyPageDto getMyPage(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_EXIST_MEMBER));

        int altitude = member.getAltitude();
        Level level = Level.getLevelByAltitude(altitude);
        int leftAltitude = level.getLeftAltitude(altitude)/1000;

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
        int memberRank = -1;

        Pageable pageable = PageRequest.of(0, 800, Sort.by("altitude").descending());
        Page<Member> allMemberRankPage = memberRepository.findAll(pageable);
        List<Member> allMemberRankList = allMemberRankPage.getContent();

        // member rank list
        List<Member> memberRankList = new ArrayList<>();
        for (int i = 0; i < allMemberRankList.size() && i < 100; i++) {
            Member m = allMemberRankList.get(i);
            memberRankList.add(m);
        }

        // find member's rank
        for (int i = 0; i < allMemberRankList.size(); i++) {
            Member m = allMemberRankList.get(i);
            if (m.getMember_id().equals(memberId)){
                memberRank = i + 1;
                break;
            }
        }

        if (memberRank == -1)
            throw new GeneralException(ErrorStatus._NOT_EXIST_MEMBER);

        return MemberResponseDto.RankDto.from(memberRankList, member, memberRank);
    }
}