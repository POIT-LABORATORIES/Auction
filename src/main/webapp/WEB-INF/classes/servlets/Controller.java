package servlets;

import app.models.Lot;
import app.service.LotService;
import app.service.ServiceFactory;
import app.service.UserService;
import app.service.exception.ServiceException;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

@MultipartConfig
public class Controller extends HttpServlet {
    public Controller(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        try {
            String name = request.getParameter("lot_name");
            String bid = request.getParameter("bid");
            int condition;
            if (Objects.equals(request.getParameter("condition"), "New")){
                condition = 0;
            } else{
                condition = 1;
            }



            //YYYY-MM-DD HH:MM:SS
            String dateTime = request.getParameter("finish_time") + ":00.00";
            //SimpleDateFormat timeStamp = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss[.....]");
            //Date parsedDate = timeStamp.parse(request.getParameter("finish_time").replace("T"," "));
            //Date parsedDate = timeStamp.parse(dateTime.replace("T"," "));
            //timestamp = new Timestamp(parsedDate.getTime());

            //String datetimeLocal = request.getParameter("finish_time");
            Timestamp timestamp = Timestamp.valueOf(dateTime.replace("T"," "));
            String finishTime = timestamp.toString();




            Part filePart = request.getPart("img_file");
            String imgFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();



            // Создание объекта 'Lot lot', заполнение полей.
            Lot lot = new Lot();
            lot.setLotName(name);
            lot.setBid(Integer.parseInt(bid));
            lot.setCondition(condition);
            lot.setFinishTime(finishTime);
            lot.setImageName(imgFileName);
            lot.setImageInputStream(fileContent);


            // Получение LotServlet, вызов метода 'createLot' с передачей
            // fileContent
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();
            lotService.addLot(lot);

            //response.sendRedirect(request.getContextPath() + "/main");

            //
            List<Lot> lots = lotService.getAllActiveLots();
            request.setAttribute("lots", lots);
            request.getRequestDispatcher("/usr/lots.jsp").forward(request, response);

            /*
            writer.println(lot.getLotName());
            writer.println(lot.getBid());
            writer.println(lot.getCondition());
            writer.println(lot.getFinishTime());
            */
        } catch (ServiceException e) {
            writer.println("Error: cannot add new lot");
            writer.println(e);
        }
        finally {
            writer.close();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/main").forward(request, response);
    }
}
