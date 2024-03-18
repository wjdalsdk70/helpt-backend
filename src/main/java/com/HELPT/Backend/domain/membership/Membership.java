package com.HELPT.Backend.domain.membership;

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
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int membershipid;

    private int userid;

    private int gymid;

    private int attendancedate;

    private Date startDate;

    private Date endDate;

}
