package servlets.user.lot;

import app.models.Lot;
import app.models.User;
import app.service.LotService;
import app.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Objects;

@MultipartConfig
public class AddLotServlet extends HttpServlet {
    public AddLotServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter writer = response.getWriter();
        try {
            if (session == null) throw new Exception("You are not authorised!");
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
            Timestamp timestamp = Timestamp.valueOf(dateTime.replace("T"," "));
            String finishTime = timestamp.toString();

            Part filePart = request.getPart("img_file");
            String imgFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();

            // Создание объекта 'Lot lot', заполнение полей.
            Lot lot = new Lot();
            User user = (User)session.getAttribute("user");
            if (user == null) throw new Exception("User is not defined!");
            lot.setLotName(name);
            lot.setBid(Integer.parseInt(bid));
            lot.setSellerId(user.getId());
            lot.setBuyerId(user.getId());
            lot.setCondition(condition);
            lot.setFinishTime(finishTime);
            lot.setImageName(imgFileName);
            lot.setImageInputStream(fileContent);

            // Добавление лота в БД.
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();
            lotService.addLot(lot);

            response.sendRedirect(request.getContextPath() + "/main");
        } catch (Exception e) {
            writer.println("Error: cannot add new lot");
            writer.println(e);
        }
        finally {
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        if(request.getSession(false) == null){
            writer.println("You are not authorised!");
            writer.close();
            return;
        }
        getServletContext().getRequestDispatcher("/usr/lot/add.jsp").
                forward(request, response);
    }
}
