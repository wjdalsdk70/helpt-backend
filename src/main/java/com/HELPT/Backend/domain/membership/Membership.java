package com.HELPT.Backend.domain.membership;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    private Long userId;

    private Long gymId;

    private int attendanceDate;

    private LocalDate startDate;

    private LocalDate endDate;

    public void setAttendanceDate(int attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
