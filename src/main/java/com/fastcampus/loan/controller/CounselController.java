package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.CounselDTO.*;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

    private final CounselService counselService;

    @PostMapping
    public ResponseDTO create(@RequestBody Request request) {
        return ok(counselService.create(request));//동일한 응답값을 내려주기 위한 메서드
    }

    @GetMapping("/{counselId}")
    public ResponseDTO<Response> get(@PathVariable Long counselId) {
        return ok(counselService.get(counselId));
    }

    @PutMapping("/{counselId}")
    public ResponseDTO<Response> update(@PathVariable Long counselId, @RequestBody Request request) {
        return ok(counselService.update(counselId, request));
    }
}
