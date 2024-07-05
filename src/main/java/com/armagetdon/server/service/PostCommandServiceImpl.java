package com.armagetdon.server.service;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.domain.enums.Level;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.dto.response.YoutubeDetail;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostImageRepository;
import com.armagetdon.server.repository.PostRepository;

import com.armagetdon.server.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final MemberRepository memberRepository;
    private final YoutubeService youtubeService;
    private final RecommendRepository recommendRepository;

    @Override
    @Transactional
    public Post createPost(PostRequestDTO.createPostDTO request) throws GeneralSecurityException, IOException {
        PostImage postImage = postImageRepository.findById(request.getPost_image_id())
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));
        Member member = memberRepository.findById(request.getMember_id())
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));
        YoutubeDetail youtubeDetail = youtubeService.getYoutubeDetails(request.getYoutube_url());

        Post newPost = Post.builder()
                .post_image_id(postImage)
                .youtube_url(request.getYoutube_url())
                .thumbnail_url(youtubeDetail.getThumbnailUrl())
                .member(member)
                .title(youtubeDetail.getTitle())
                .build();

        // reward 지급
        member.addReward(10);
        // 고도 상승
        member.addAltitude(1000);

        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public void deletePost(Long post_id) {
        Post deletedPost = postRepository.findById(post_id).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(deletedPost);
    }

    @Override
    public List<PostResponseDTO.listResultDTO> inquiryList() {
        Random random = new Random();
;
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(post -> PostConverter.toListPostDTO(
                        post,
                        isRecommend(post, post.getMember()),
                        Level.getLevelByAltitude(post.getMember().getAltitude()).getName(),
                        recommendRepository.countByPostAndIsActiveTrue(post).intValue()))
                .toList();
    }

    @Override
    public PostResponseDTO.detailResultDTO inquiryDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
        return PostConverter.toDetailPostDTO(
                post,
                isRecommend(post, post.getMember()),
                post.getMember().getMember_id().equals(1L),
                recommendRepository.countByPostAndIsActiveTrue(post).intValue());
    }

    @Override
    public List<PostResponseDTO.popularResultDTO> getPopularList() {
        Pageable topFive = PageRequest.of(0, 5);
        return postRepository.findTop5PostsByRecommendCount(topFive).stream()
                .map(post -> PostResponseDTO.popularResultDTO.builder()
                        .postId(post.getPost_id())
                        .thumbnailUrl(post.getThumbnail_url())
                        .build())
                .toList();
    }

    @Override
    public List<PostResponseDTO.listResultDTO> getMineList(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        List<Post> postList = postRepository.findAllByMember(member);
        return postList.stream()
                .map(post -> PostConverter.toListPostDTO(
                        post,
                        isRecommend(post, post.getMember()),
                        Level.getLevelByAltitude(post.getMember().getAltitude()).getName(),
                        recommendRepository.countByPostAndIsActiveTrue(post).intValue()))
                .toList();
    }

    private boolean isRecommend(Post post, Member member){
        Optional<Recommend> optionalRecommend = recommendRepository.findByPostAndMember(post, member);
        return optionalRecommend.isPresent() && optionalRecommend.get().isActive();
    }
}