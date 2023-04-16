package it.revo.revoservice;

import it.revo.revoservice.entity.WeekDays;
import it.revo.revoservice.repository.WeekDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class Main {
    @Autowired
    WeekDayRepository weekDayRepository;


    public static void main(String[] args) {
        String choiceDayName = "toq";
        List<Integer> day = new ArrayList<>();
        if (choiceDayName.equals("toq")) {
            day = Arrays.asList(1, 3, 5);
        } else if (choiceDayName.equals("juft")) {
            day = Arrays.asList(2, 4, 6);
        } else {
            day = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        }
        Main main = new Main();
        main.a();
//        Calendar cal = Calendar.getInstance();
//        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        System.out.println("Maximum DAY:" + day);
//        System.out.println(Calendar.getInstance().getActualMaximum(3));
//        Calendar c = Calendar.getInstance();   // this takes current date
//        c.set(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(c.getTime());
//        today.add(Calendar.DAY_OF_MONTH, -1);
//        java.util.Date previousDay = today.getTime();
//        ToDate = sdfFile1.format(newjava.sql.Date(previousDay.getTime()));
//        today.add(Calendar.DATE, 1);
//        java.util.Date nextDay = today.getTime();
//        FromDate = sdfFile1.format(new java.sql.Date(nextDay.getTime()));
//        ZoneId zoneId = ZoneId.of("America/Montreal");  // Or 'ZoneOffset.UTC'.
//        ZonedDateTime now = ZonedDateTime.now(zoneId);
//        Month month = now.getMonth();
//        int monthNumber = month.getValue(); // Answer to the Question.
//        String monthName = month.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
//        System.out.println(monthName);
//        System.out.println(monthNumber);
//        LocalDate today = LocalDate.now();
//        int month = today.getMonthValue();
//        System.out.println(month);
//        Date date = new Date();
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        int month = localDate.getMonthValue();
//        System.out.println(month);
//        java.util.Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int month = cal.get(Calendar.MONTH);
//        System.out.println(month);
//        Date date = new Date();
//
////        System.out.println(Instant.now());
//        System.out.println(Calendar.getInstance());
//        System.out.println(Calendar.MARCH);
//
//
//        System.out.println(date);
//        System.out.println(date.getDate());
//        System.out.println(date.getMonth());
//        date.setMonth(Calendar.MARCH);
//        System.out.println(Calendar.getInstance().getMaximum(1));
//        System.out.println(date.getMonth());
//
//        System.out.println(Locale.getDefault());
//
//        System.out.println(Calendar.getInstance());
    }

    public void a() {
        Date date = new Date();
        int sana = date.getDate() - 1;
        int kun = date.getDay() - 1;
        //        System.out.println(date.getDate() - 1);
//        System.out.println(date.getDay() - 1);
//        date.setDate(14);
//        System.out.println(date.getDay() - 1);

        for (int i = sana; i < 30; i++) {
            if (i == sana && kun % 2 == 1) {
                System.out.println("sana " + sana);
                System.out.println("kun " + weekDayRepository.findById(sana));
            }
            date.setDate(i + 1);
            sana = date.getDate();
            kun = date.getDay();
        }
    }
}
