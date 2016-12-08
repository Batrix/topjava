package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealDBImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealDBImpl mealDB;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealDB = new MealDBImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to mealList");
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getALL");
            request.setAttribute("mealList", MealsUtil.getListWithExceeded(mealDB.getAll(), MealsUtil.CALORIES));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (Objects.equals(action, "update")) {
            Meal meal = mealDB.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        } else if (Objects.equals(action, "create")) {
            Meal meal = new Meal(LocalDateTime.now().withSecond(0).withNano(0), "", 1000);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        } else if (Objects.equals(action, "delete")) {
            LOG.info("Delete {}", request.getParameter(request.getParameter("id")));
            mealDB.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(request.getParameter("id").isEmpty() ? null : Integer.parseInt(request.getParameter("id")),
                LocalDateTime.parse(request.getParameter("datetime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        mealDB.save(meal);
        LOG.info(meal.getId() == null ? "Create {}" : "Update {}", meal);
        response.sendRedirect("meals");
    }
}
