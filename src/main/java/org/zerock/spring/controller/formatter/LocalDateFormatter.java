package org.zerock.spring.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Formatter 인터페이스는 Spring Frmework에서 지원하는 라이브러리
 * param은 다 String이기때문에 특정 타입의 경우 formatter가 필요
 *
 * arguments Locale은 지역
 *
 * XXXFormatter 를 servlet-context(스프링빈컨테이너)에 등록해야되는데, formatter의 경우 다른 빈과달리 좀 복잡
 * FormattingConversion-ServiceFactoryBean객체를 스피링 븐으로 등록하고, 이 안에다가 LocalDateFormatter를 추가해야함
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
