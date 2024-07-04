package com.armagetdon.server.domain;

import com.armagetdon.server.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String youtubeUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    //
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Recommend> recommend;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Complain> complain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PostImage postImage;

    @ColumnDefault("0")
    private int postRecommendCount;
}
