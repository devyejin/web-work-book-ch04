package org.zerock.spring.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;

    @NotBlank
    private String title;

    @Future
    private LocalDate dueDate;

    private boolean finished;

    @NotBlank
    private String writer; // 새로 추가
}
