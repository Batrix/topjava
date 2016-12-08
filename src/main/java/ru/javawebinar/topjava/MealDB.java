package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealDB {

    void save(Meal meal);

    Meal get(int id);

    void delete(int id);

    Collection<Meal> getAll();
}
