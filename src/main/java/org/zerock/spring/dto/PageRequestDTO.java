package org.zerock.spring.dto;


import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1; //기본값
    private int size = 10; //기본값
    private int getSkip() {
        return (page-1) * size;
    }
}
