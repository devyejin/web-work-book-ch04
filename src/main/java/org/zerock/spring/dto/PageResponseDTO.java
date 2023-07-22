package org.zerock.spring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 페이지글 구성할 떄 필요한걸 한번에 응답해주기 (요청시 mapper 쿼리는 2번나가는걸)
 * 1. TodoDTO목록
 * 2. 전체 데이터 수 (하단에 페이지 번호 구성하기 위해서)
 * 3. 페이지 번호의 처리를 위한 데이터들( 시작 페이지번호, 끝 페이지 번호)
 */

@Getter
@Setter
@ToString
public class PageResponseDTO<E> {
    private int page; //현재 페이지 번호
    private int size; //한 페이지당 보여지는 데이터 수
    private int totol; //전체 데이터 수

    private int start; //시작 페이지 번호
    private int end; //끝 페이지 번호
    private boolean prev; //이전 페이지 존재 여부
    private boolean next; //다음 페이지 존재 여부
    private List<E> dtoList; //조회된 데이터

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                          List<E> dtoList,
                          int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.totol = total;

        this.dtoList = dtoList;

        // 3 -> 3/10 -> 0.3 -> 1 -> 1*10 -> 10
        // 18 -> 18/10 -> 1.8 -> 2 -> 2*10 -> 20
        this.end = (int) (Math.ceil((this.page)/10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil((this.totol)/(double)size)) * 10;

        this.end = (end > last) ? last : end;

        this.prev = this.start > 1;
        this.next = total > this.size; //출력될 데이터가 남으면 next버튼 보이기
    }
}
