package com.example.umc9th_chapter4.domain.test.controller;

import com.example.umc9th_chapter4.domain.test.converter.TestConverter;
import com.example.umc9th_chapter4.domain.test.dto.res.TestResDTO;
import com.example.umc9th_chapter4.domain.test.service.query.TestQueryService;
import com.example.umc9th_chapter4.global.apiPayload.ApiResponse;
import com.example.umc9th_chapter4.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @Operation(
            summary = "테스트 API By 포롱 (개발중)",
            description = "테스트 데이터를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("This is Test!")
        );
    }

    @Operation(
            summary = "예외 처리 테스트 API By 포롱 (개발중)",
            description = "flag 파라미터에 따라 예외 처리를 테스트합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 flag 값")
    })
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ) {

        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("This is Test!"));
    }
}
