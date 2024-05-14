package com.HELPT.Backend.domain.notice;

import com.HELPT.Backend.domain.notice.dto.NoticeRequest;
import com.HELPT.Backend.domain.notice.dto.NoticeResponse;
import com.HELPT.Backend.global.error.CustomException;
import com.HELPT.Backend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeResponse> findNoticeByMember(Long gymId)
    {
        List<Notice> noticeList = noticeRepository.findAllByGymId(gymId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        return noticeList.stream().map(NoticeResponse::new).toList();
    }

    public List<NoticeResponse> findNoticeByManager(Long gymId)
    {
        List<Notice> noticeList = noticeRepository.findAllByGymId(gymId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        return noticeList.stream().map(notice -> new NoticeResponse(notice, notice.getNoticeId())).toList();
    }

    public Boolean uploadNotice(NoticeRequest noticeRequest)
    {
        Notice newNotice = Notice.builder()
                .gymId(noticeRequest.getGymId())
                .title(noticeRequest.getTitle())
                .content(noticeRequest.getContent())
                .createAt(noticeRequest.getCreateAt())
                .build();

        noticeRepository.save(newNotice);

        return Boolean.TRUE;
    }

    public Boolean deleteNotice(Long noticeId)
    {
        noticeRepository.deleteById(noticeId);

        return Boolean.TRUE;
    }

    public Boolean modifyNotice(Long noticeId, NoticeRequest noticeRequest)
    {
        String newTitle = noticeRequest.getTitle();
        String newContent = noticeRequest.getContent();

        Notice findNotice = noticeRepository.findById(noticeId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        findNotice.setTitle(newTitle);
        findNotice.setContent(newContent);

        return Boolean.TRUE;
    }

}
