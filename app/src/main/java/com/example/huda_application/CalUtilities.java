package com.example.huda_application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalUtilities {

    public static LocalDate selectedDate;

    // Function formats the date input from the add event form
    public static String dateFormatted(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    // Function formats the time input from the add event form
    public static String timeFormatted(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss ");
        return time.format(formatter);
    }

    // function formats the month year display in the weekly and monthly calendar
    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

//     Function declares and displays the days in the month
    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date)
    {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        // gets the first day of the week
        LocalDate firstOfMonth = CalUtilities.selectedDate.withDayOfMonth(1);
        // returns an integer between 0 - 7
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++)
        {
            // if i is less than the first day of the week or
            // greater than the no. days in month + days of week
            // display a blank square
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add(null);
            }
            // otherwise display the number of the day and day of the week
            else
            {
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate)
    {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        while (current.isBefore((endDate)))
        {
            days.add(current);
            current = current.plusDays(1);
        }
        return days;
    }

    private static LocalDate sundayForDate(LocalDate current)
    {
        // setting oneWeekAgo to a week previous
        LocalDate oneWeekAgo = current.minusWeeks(1);

        // while current is after a week ago
        while(current.isAfter(oneWeekAgo))
        {
            // if the day of the week is sunday return sunday
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }
        return null;
    }

}
