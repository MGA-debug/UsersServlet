package ru.appline.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.utils.ErrorText;
import ru.appline.logic.utils.RequestReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static ru.appline.logic.utils.JsonHelper.getJsonErrorMessage;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<Integer, User> users = model.getFromList();
        JsonObject jObj = gson.fromJson(String.valueOf(RequestReader.read(request)), JsonObject.class);
        PrintWriter pw = response.getWriter();
        int id = jObj.get("id").getAsInt();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        if (id < 0 || id > users.size()) {
            pw.print(gson.toJson(getJsonErrorMessage(ErrorText.INVALID_ID)));

        } else if (id == 0) {
            response.getWriter().print(gson.toJson(users));

        } else {
            User user = users.get(jObj.get("id").getAsInt());
            response.getWriter().print(gson.toJson(user));
        }
    }
}
