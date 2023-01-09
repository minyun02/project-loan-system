package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.*;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)//ExtendWith 애너테이션이 import가 안되는 현상이 있었는데 gradle에 testImplementation이 누락되었었다.
public class CounselServiceTest {//CounselService에대한 유닛 테스트를 진행한다.

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper; //각각 다른 오브젝트를 매핑해주는 유틸 역할을 하기 위해서 스파이 처리

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                .name("윤길동")
                .cellPhone("010-1111-2222")
                .email("abc@de.v")
                .memo("전세 대출 상담을 받고싶습니다.")
                .zipCode("01234")
                .address("서울시 서울구 서울동")
                .addressDetail("서울아파트 101동 101호")
                .build();

        Request request = Request.builder()
                .name("윤길동")
                .cellPhone("010-1111-2222")
                .email("abc@de.v")
                .memo("전세 대출 상담을 받고싶습니다.")
                .zipCode("01234")
                .address("서울시 서울구 서울동")
                .addressDetail("서울아파트 101동 101호")
                .build();
        //모킹처리
        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();
        //mocking처리
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistCounselId() {
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void Should_ReturnUpdateResponseOfExistCounselEntity_When_RequestUpdateExistCounselInfo() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Member Yun")
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }
}
