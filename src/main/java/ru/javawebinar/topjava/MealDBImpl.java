package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDBImpl implements MealDB {
    private Map<Integer, Meal> dataBase = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public MealDBImpl() {
        MealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public void save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
        }
        dataBase.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return dataBase.get(id);
    }

    @Override
    public void delete(int id) {
        dataBase.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return dataBase.values();
    }
}
