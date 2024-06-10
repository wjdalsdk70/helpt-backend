//package com.HELPT.Backend.domain.gymadmission;
//
//import com.HELPT.Backend.domain.gym.entity.Gym;
//import com.HELPT.Backend.domain.gym.repository.GymRepository;
//import com.HELPT.Backend.domain.member.Member;
//import com.HELPT.Backend.domain.member.MemberRepository;
//import com.HELPT.Backend.domain.membership.Membership;
//import com.HELPT.Backend.domain.membership.MembershipRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class GymAdmissionServiceTest {
//    @Mock
//    private MemberRepository memberRepository;
//
//    @Mock
//    private GymRepository gymRepository;
//
//    @Mock
//    private GymAdmissionRepository gymAdmissionRepository;
//
//    @Mock
//    private MembershipRepository membershipRepository;
//
//    @InjectMocks
//    private GymAdmissionService gymAdmissionService;
//
//    private Member member;
//    private Gym gym;
//    private GymAdmission gymAdmission;
//    private Membership membership;
//    private LocalDate endDate;
//
//    @BeforeEach
//    void setUp() {
//        member = Member.builder()
//                .userId(1L)
//                .userName("이정민")
//                .build();
//        gym = Gym.builder()
//                .id(1L)
//                .build();
//        gymAdmission = GymAdmission.builder()
//                .id(1L)
//                .member(member)
//                .gym(gym)
//                .build();
//        endDate = LocalDate.of(2024, 12, 31);
////        membership = Membership.builder()
////                .id
////                .member(member)
////                .gym(gym)
////                .build();
//
//    }
//    @Test
//    void findGymAdmissionList_Success(){
//        // given
//        List<GymAdmission> gymAdmissions = Arrays.asList(gymAdmission);
//        when(gymAdmissionRepository.findByGymId(anyLong())).thenReturn(gymAdmissions);
//        GymAdmissionResponse expectedResponse = GymAdmissionResponse.toDto(gymAdmission, gymAdmission.getMember());
//
//        // when
//        List<GymAdmissionResponse> actualResponses = gymAdmissionService.findGymAdmissions(1L);
//
//        // then
//        assertNotNull(actualResponses, "The returned list should not be null");
//        assertFalse(actualResponses.isEmpty(), "The returned list should not be empty");
//        assertEquals(1, actualResponses.size(), "The size of returned list should match the expected list size");
//        assertEquals(expectedResponse.getGymAdmissionId(), actualResponses.get(0).getGymAdmissionId(), "The id of GymAdmissionResponse should match");
//        assertEquals(expectedResponse.getUserName(), actualResponses.get(0).getUserName(), "The memberId of GymAdmissionResponse should match");
//    }
//
//    @Test
//    void addGymAdmission_Success() {
//        // given
//        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
//        when(gymRepository.findById(any())).thenReturn(Optional.of(gym));
//        when(gymAdmissionRepository.save(any(GymAdmission.class))).thenReturn(gymAdmission);
//
//        // when
//        GymAdmission savedAdmission = gymAdmissionService.addGymAdmission(gym.getId(), member.getUserId());
//
//        // then
//        assertNotNull(savedAdmission);
//        assertNotNull(savedAdmission.getGym());
//        assertNotNull(savedAdmission.getMember());
//        assertEquals(gym.getId(), savedAdmission.getGym().getId());
//        assertEquals(member.getUserId(), savedAdmission.getMember().getUserId());
//
//        // Verify using ArgumentCaptor
//        ArgumentCaptor<GymAdmission> captor = ArgumentCaptor.forClass(GymAdmission.class);
//        verify(gymAdmissionRepository).save(captor.capture());
//        GymAdmission captured = captor.getValue();
//        assertNotNull(captured.getGym());
//        assertNotNull(captured.getMember());
//        assertEquals(gym.getId(), captured.getGym().getId());
//        assertEquals(member.getUserId(), captured.getMember().getUserId());
//    }
//
//    @Test
//    void addGymAdmission_MemberNotFound_ThrowsException() {
//        // given
//        when(memberRepository.findById(any())).thenReturn(Optional.empty());
//
//        // when
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            gymAdmissionService.addGymAdmission(gym.getId(), member.getUserId());
//        });
//
//        // then
//        assertEquals("Member not found", exception.getMessage());
//    }
//
//    @Test
//    void addGymAdmission_GymNotFound_ThrowsException() {
//        // given
//        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
//        when(gymRepository.findById(any())).thenReturn(Optional.empty());
//
//        // when
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            gymAdmissionService.addGymAdmission(gym.getId(), member.getUserId());
//        });
//
//        // then
//        assertEquals("Gym not found", exception.getMessage());
//    }
//
//    @Test
//    void approveGymAdmission() {
//        // given
//        when(gymAdmissionRepository.findById(1L)).thenReturn(Optional.of(gymAdmission));
//        when(membershipRepository.save(any(Membership.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // when
//        Membership result = gymAdmissionService.approveGymAdmission(1L,endDate);
//
//        // then
//        assertNotNull(result, "Membership should not be null");
//        assertEquals(gym.getId(), result.getGymId(), "Gym ID should match");
//        assertEquals(member.getUserId(), result.getUserId(), "User ID should match");
//        assertEquals(endDate, result.getEndDate(), "End date should match");
//        verify(membershipRepository).save(any(Membership.class));
//    }
//
//    @Test
//    void rejectGymAdmission() {
//        // given
//        Long gymAdmissionId = 1L;
//
//        // when
//        gymAdmissionService.rejectGymAdmission(gymAdmissionId);
//
//        // then
//        verify(gymAdmissionRepository).deleteById(gymAdmissionId);
//    }
//}