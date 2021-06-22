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

import static ru.appline.logic.utils.JsonHelper.getJsonErrorMessage;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jObj = gson.fromJson(String.valueOf(RequestReader.read(request)), JsonObject.class);
        PrintWriter pw = response.getWriter();

        request.setCharacterEncoding("UTF-8");

        int id = jObj.get("id").getAsInt();
        if (!model.getFromList().containsKey(id)) {
            pw.print(gson.toJson(getJsonErrorMessage(ErrorText.INVALID_ID)));

        } else {
            model.getFromList().remove(id);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(gson.toJson(model.getFromList()));
        }
    }
}
