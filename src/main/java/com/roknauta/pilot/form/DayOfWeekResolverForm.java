package com.roknauta.pilot.form;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class DayOfWeekResolverForm {

    private int year;
    private int month;
    private int dayOfMonth;
    private String dayOfWeek;

}
