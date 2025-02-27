package com.armagetdon.server.service;

import com.armagetdon.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NicknameService {

    private final MemberRepository memberRepository;
    private final Random random = new Random();
    private List<String> adjectives;
    private List<String> nouns;

    @PostConstruct
    public void init() {
        // Initialize adjectives and nouns directly in the code
        adjectives = Arrays.asList(
                "가냘픈", "가는", "가엾은", "가파른", "같은", "거센", "거친", "검은", "게으른", "고달픈",
                "고른", "고마운", "고운", "고픈", "곧은", "괜찮은", "구석진", "굳은", "굵은", "귀여운",
                "그런", "그른", "그리운", "기다란", "기쁜", "깊은", "깎아지른", "깨끗한", "나쁜", "나은",
                "난데없는", "날랜", "날카로운", "낮은", "너그러운", "너른", "널따란", "넓은", "네모난",
                "노란", "높은", "누런", "눅은", "느닷없는", "느린", "늦은", "다른", "더러운", "더운",
                "덜된", "동그란", "돼먹잖은", "둥그런", "둥근", "뒤늦은", "드문", "딱한", "때늦은",
                "뛰어난", "뜨거운", "막다른", "많은", "매운", "멋진", "메마른", "메스꺼운", "모난",
                "못난", "못된", "못생긴", "무거운", "무딘", "무른", "무서운", "미운", "반가운",
                "밝은", "밤늦은", "보드라운", "보람찬", "부드러운", "부른", "붉은", "비싼", "빠른",
                "빨간", "뻘건", "뼈저린", "뽀얀", "뿌연", "새로운", "서툰", "섣부른", "설운",
                "성가신", "수줍은", "쉬운", "스스러운", "슬픈", "시원찮은", "싫은", "쌀쌀맞은",
                "쏜살같은", "쓰디쓴", "쓰린", "아니꼬운", "아닌", "아름다운", "아쉬운", "아픈",
                "안된", "안쓰러운", "안타까운", "않은", "알맞은", "약빠른", "약은", "얇은",
                "얕은", "어두운", "어려운", "어린", "언짢은", "엄청난", "없는", "여문",
                "열띤", "예쁜", "올바른", "옳은", "외로운", "우스운", "의심쩍은", "이른",
                "익은", "있는", "작은", "잘난", "잘빠진", "잘생긴", "재미있는", "적은",
                "젊은", "점잖은", "조그만", "좁은", "좋은", "주제넘은", "줄기찬", "즐거운",
                "지나친", "지혜로운", "질긴", "짓궂은", "짙은", "짧은", "케케묵은", "탐스러운",
                "턱없는", "푸른", "하나같은", "한결같은", "흐린", "희망찬", "힘겨운",
                "힘찬", "군침싹도는", "망가져가는", "현란한", "미친척하는", "뻑이가는",
                "부유한", "책임감쩌는", "돼지같은", "표독스러운", "삭아보이는"
        );

        nouns = Arrays.asList(
                "치킨", "바나나", "쌍둥이", "피자", "메뚜기", "고구마", "김치", "두부", "바보",
                "감자", "도넛", "곱창", "전학생", "여학생", "장군", "소년", "소녀", "수박",
                "양말", "선풍기", "고래", "멸치", "문어", "햄스터", "초콜릿", "젤리", "풍선",
                "화장지", "냉장고", "토스터", "선생님", "부장님", "직장인", "강아지", "고양이",
                "뚱보", "날다람쥐", "모기", "파리", "두꺼비", "청개구리", "라면", "빵", "수박씨",
                "코코넛", "토마토", "당근", "브로콜리", "컵라면", "스파게티", "국수", "떡볶이",
                "김밥", "만두", "삼겹살", "치즈", "감자칩", "튀김", "피망", "옥수수", "호떡",
                "버섯", "팥빙수", "케이크", "도넛", "빼빼로", "포도", "수박바", "귤", "사과",
                "참외", "배추", "양배추", "헬리콥터", "우주선", "로켓", "탱크", "유령",
                "좀비", "마법사", "마녀", "괴물", "해적", "닌자", "우주인"
        );
    }

    public String generateNickname() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        return adjective + " " + noun;
    }
}
