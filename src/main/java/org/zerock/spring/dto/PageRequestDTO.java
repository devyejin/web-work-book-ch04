package org.zerock.spring.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min; // <-- 제약조건(Exception 발생할만 한 요소를 사전 방지)
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

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

    private String link;

    //검색관련 필드
    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    //query에서 expression을 쓸 수 없으니까 skip 수를 DTO에서 제공
    public int getSkip() {
        return (page-1) * size;
    } //책은 10인데 10보단 size가 적합한듯


    public String getLink() {

            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            link = builder.toString();


        //이 정보들을 어디서 가져오지?했는데 form데이터
        if(finished) {
            builder.append("&finished=on");
        }

        if(types != null && types.length > 0) {
            for(int i=0; i < types.length; i++) {
                builder.append("&tyes="+types[i]);
            }
        }

        if(keyword != null) {
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
            }
        }

        if(from != null) {
            builder.append("&from="+from.toString());
        }

        if(to != null) {
            builder.append("&to="+to.toString());
        }

        return builder.toString();

    }

    public boolean checkType(String type) {
        if(types == null || types.length == 0) {
            return false;
        }

        return Arrays.stream(types).anyMatch(type::equals); // types배열안에 든 요소 type이 일치하는게 있는지 확인
        //여기서 누구랑 비교한다는거지?싶었는데 호출하는 대상에서 있으니까
    }

}
