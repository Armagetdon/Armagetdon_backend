package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.domain.enums.Level;
import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.repository.PostRepository;
import com.armagetdon.server.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService{

    private final PostRepository postRepository;

    private final MemberService memberService;
    private final RecommendRepository recommendRepository;

    @Override
    public PostResponseDTO.detailResultDTO getDetail(Long memberId, Long postId) {
        Member loginMember = memberService.getMember(memberId);
        Post post = getPost(postId);

        return PostConverter.toDetailPostDTO(
                post,
                loginMember.getMember_id().equals(post.getMember().getMember_id()),
                checkRecommend(loginMember, post));    }

    @Override
    public PagingResponse<PostResponseDTO.listResultDTO> getList(Long memberId, int page, int size) {
        Member loginMember = memberService.getMember(memberId);
        Page<Post> pagePost = postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));

        List<PostResponseDTO.listResultDTO> posts = pagePost.stream()
                .map(post -> PostConverter.toListPostDTO(
                        post,
                        checkRecommend(loginMember, post),
                        Level.getLevelByAltitude(post.getMember().getAltitude()).getName()))
                .toList();

        return PostConverter.toPagingResponse(pagePost, posts);    }

    @Override
    public PagingResponse<PostResponseDTO.listResultDTO> getMineList(Long memberId, int page, int size) {
        Member loginMember = memberService.getMember(memberId);
        Page<Post> pagePost = postRepository.findAllByMemberOrderByComplainDesc(loginMember, PageRequest.of(page, size));

        List<PostResponseDTO.listResultDTO> posts = pagePost.stream()
                .map(post -> PostConverter.toListPostDTO(
                        post,
                        true,
                        Level.getLevelByAltitude(post.getMember().getAltitude()).getName()))
                .toList();
        return PostConverter.toPagingResponse(pagePost, posts);    }

    @Override
    public List<PostResponseDTO.popularResultDTO> getPopularList() {
        return postRepository.findTop5ByOrderByPostRecommendCountDesc()
                .stream().map(PostConverter::toPopularResultDTO)
                .toList();
    }

    private boolean checkRecommend(Member member, Post post) {
        Optional<Recommend> optionalRecommend = recommendRepository.findByMemberAndPost(member, post);
        return optionalRecommend.isPresent() && optionalRecommend.get().is_active();
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
    }

}