package org.zerock.spring.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min; // <-- 제약조건(Exception 발생할만 한 요소를 사전 방지)
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default //<-- 생성될 때 기본값을 갖도록 하는 롬복 어노테이션
    @Positive
    @Min(value = 1)
    private int page = 1; //현재 페이지


    @Builder.Default
    @Positive
    @Min(value=10)
    @Max(value=100)
    private int size = 10; //한 페이지당 보여주는 데이터 수

    //query에서 expression을 쓸 수 없으니까 skip 수를 DTO에서 제공
    public int getSkip() {
        return (page-1) * size;
    } //책은 10인데 10보단 size가 적합한듯



}