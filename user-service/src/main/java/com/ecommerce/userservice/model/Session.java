package com.ecommerce.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.bind.support.SessionStatus;
import java.util.*;





@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session extends BaseModel{
    private String token;
    private  Date expiringAt;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private SessionsStatus sessionStatus;

}
