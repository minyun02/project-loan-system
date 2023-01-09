package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.*;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService{
    private final CounselRepository counselRepository;

    //DTO <-> Entity로 변환해주는 modelmapper를 사용한다.
    private final ModelMapper modelMapper;
    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class); //Counsel entity를 request와 맵핑해준다.
        counsel.setAppliedAt(LocalDateTime.now()); //신청 받은 날짜를 오늘로 세팅해준다.

        Counsel created = counselRepository.save(counsel);

        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return modelMapper.map(counsel, Response.class);
    }
}
