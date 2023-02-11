package com.api.djobs.user.domain;

import com.api.djobs.common.BaseEntity;
import com.api.djobs.oauth.domain.ProviderType;
import com.api.djobs.user.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Audited(withModifiedFlag = true)
@Builder
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String username;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    private String oauthId;

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
