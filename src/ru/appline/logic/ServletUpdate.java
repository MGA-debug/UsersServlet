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

@WebServlet(urlPatterns = "/update")
public class ServletUpdate extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jObj = gson.fromJson(String.valueOf(RequestReader.read(request)), JsonObject.class);
        PrintWriter pw = response.getWriter();

        request.setCharacterEncoding("UTF-8");

        int id = jObj.get("id").getAsInt();
        if (id < 1 || id > model.getFromList().size()) {
            pw.print(gson.toJson(getJsonErrorMessage(ErrorText.INVALID_ID)));

        } else {
            String name = jObj.get("name").getAsString();
            String surname = jObj.get("surname").getAsString();
            double salary = jObj.get("salary").getAsDouble();

            User.updateInfo(id, name, surname, salary);

            response.setContentType("application/json;charset=utf-8");
            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
