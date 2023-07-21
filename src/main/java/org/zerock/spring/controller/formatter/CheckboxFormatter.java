package org.zerock.spring.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter<T> : T타입으로 parse해주고 print해주는 인터페이스
 *
 * checkbox on -> true,  null(미체크는 null) -> false
 */
public class CheckboxFormatter implements Formatter<Boolean> {
    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
//        return text.equals("on") ? true : false;   // + 책은 if문처리
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
