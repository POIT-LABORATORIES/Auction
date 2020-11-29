package servlets;

import app.models.Lot;
import app.service.LotService;
import app.service.ServiceFactory;
import app.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;

@MultipartConfig
public class Controller extends HttpServlet {
    public Controller(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("lot_name");
        String bid = request.getParameter("bid");
        int condition;
        if (Objects.equals(request.getParameter("condition"), "New")){
            condition = 0;
        } else{
            condition = 1;
        }

        //YYYY-MM-DD HH:MM:SS
        SimpleDateFormat timeStamp = new SimpleDateFormat(request.getParameter("finish_time"));
        String finishTime = request.getParameter("finish_time");


        Part filePart = request.getPart("img_file");
        //String filePath = Paths.get(filePart.getSubmittedFileName()).toFile().getAbsolutePath();
        String imgFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        // Создание объекта 'Lot lot', заполнение полей.
        Lot lot = new Lot();



        // Получение LotServlet, вызов метода 'createLot' с передачей
        // fileContent
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LotService lotService = serviceFactory.getLotService();



        /*
        try(PrintWriter writer = response.getWriter()){
            writer.println(name);
            writer.println(bid);
            writer.println(condition);
            writer.println(finishTime);
        }
        */

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/main").forward(request, response);
    }
}
