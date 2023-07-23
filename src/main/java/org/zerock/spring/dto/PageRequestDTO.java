package org.zerock.spring.dto;


import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter //RequestParam DTO로 받아줄 때 내부에서 Setter 사용하나봄, 이거 빼먹었다가 요청처리 못함
@ToString
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1; //기본값

    @Builder.Default
    @Positive
    @Min(value=10)
    @Max(value=100)
    private int size = 10; //기본값
    private int getSkip() {
        return (page-1) * size;
    }
}
