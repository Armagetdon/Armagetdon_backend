package com.armagetdon.server.domain;

import com.armagetdon.server.domain.common.BaseEntity;
import com.armagetdon.server.domain.enums.complain_type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Complain extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complain_id;

    @Enumerated(EnumType.STRING)
    private complain_type complain_type;

    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
