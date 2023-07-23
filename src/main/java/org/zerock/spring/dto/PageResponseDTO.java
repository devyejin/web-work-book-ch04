package org.zerock.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 응답을 내려줄 때 List<TodoDTO> 데이터
 * 하단에 버튼 구성을 하기 위해
 * -> page (현재 요청 페이지)
 * -> size (요청 데이터 수)
 * -> total (전체 데이터 수)
 * -> start (요청페이지가 2x 3x일때 시작값이 21 31 등으로 변경되니까)
 * -> end
 *
 *
 */

@ToString
@Getter
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    private int start; //시작 페이지 번호
    private int end;

    private boolean prev; //이전 페이지 존재 여부
    private boolean next; //다음 페이지 존재 여부

    private List<E> dtoList;

    /**
     * 응답을 내려줄 때 사용하는 page, size 값이 요청에서 온 값이니까 그걸 이용
     */
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList,
                           int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) Math.ceil(this.page / 10.0 ) * 10;
        this.start = this.end - 9;

        int last = (int) Math.ceil(this.total/(double)this.size); //마지막 버튼

        this.end = (end > last ) ? last : end;

        this.prev = this.start > 1;
        this.next =  total > this.end * this.size;

    }

}
