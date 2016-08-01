package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        if (mealList == null || startTime == null || endTime == null || mealList.size() == 0) return null;
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal meal : mealList) {
            if (map.get(meal.getDateTime().toLocalDate()) != null) {
                map.put(meal.getDateTime().toLocalDate(), map.get(meal.getDateTime().toLocalDate()) + meal.getCalories());
            } else {
                map.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            }
        }
        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay && TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                mealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
            }
        }
        return mealWithExceeds;
    }
}
