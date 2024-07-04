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
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int altitude;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long reward;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> post;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Recommend> recommend;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Complain> complain;


    public void subReward(long reward){
        this.reward -= reward;
    }

    public void addReward(long reward){
        this.reward += reward;
    }

    public void addAltitude(int altitude){
        this.altitude += altitude;
    }

    @Builder
    public Member(String nickname){
        this.nickname = nickname;
    }
}
