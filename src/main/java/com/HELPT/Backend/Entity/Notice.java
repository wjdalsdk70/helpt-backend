package com.HELPT.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;

    private int gymId;

    private String title;

    private String content;

    private Date createAt;
}
