package servlets.user.lot;

import app.models.Lot;
import app.models.User;
import app.service.LotService;
import app.service.ServiceFactory;
import app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Objects;

@MultipartConfig
public class LotServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        PrintWriter writer = response.getWriter();
        try{
            if (session == null) throw new Exception("You are not authorised!");
            if (action == null) throw new Exception("No action set!");
            if(action.equals("update")){
                updateLot(request, response);
                response.sendRedirect(request.getContextPath() + "/main");
            }
        } catch (Exception e) {
            writer.println("Error: cannot update lot");
            writer.println(e);
        }
        finally {
            writer.close();
        }
    }

    private void updateLot(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LotService lotService = serviceFactory.getLotService();

        int id = Integer.parseInt (request.getParameter("lot_id"));
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
        Timestamp timestamp = Timestamp.valueOf(
                dateTime.replace("T"," "));
        String finishTime = timestamp.toString();


        Part filePart = request.getPart("img_file");
        String imgFileName;
        InputStream fileContent;
        Lot dbLot = lotService.getLotById(id);
        // Если новой картинки не указано.
        if (filePart.getSubmittedFileName().equals("")){
            imgFileName = dbLot.getImageName();
            fileContent = dbLot.getImageInputStream();
        } else{
            imgFileName = Paths.get(filePart.getSubmittedFileName()).
                    getFileName().toString();
            fileContent = filePart.getInputStream();
        }

        // Создание объекта 'Lot lot', заполнение полей.
        Lot lot = new Lot();
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) throw new Exception("User is not defined!");
        lot.setLotId(id);
        lot.setLotName(name);
        lot.setCondition(condition);
        lot.setStatus(dbLot.getStatus());
        lot.setBid(Integer.parseInt(bid));
        lot.setBidQuantity(dbLot.getBidQuantity());
        lot.setImageName(imgFileName);
        lot.setImageInputStream(fileContent);
        lot.setSellerId(user.getId());
        lot.setBuyerId(dbLot.getBuyerId());
        lot.setStartTime(dbLot.getStartTime());
        lot.setFinishTime(finishTime);

        // Обновление лота в БД.
        lotService.editLot(lot);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // Получение строки запроса.
        String requestString = request.getPathInfo();
        PrintWriter writer = response.getWriter();
        try{
            if (requestString == null) throw new Exception("Invalid request path");
            // Вырезание первого слеша.
            requestString = requestString.substring(1);
            // Первая подстрока - действие.
            String action = requestString.substring(0, requestString.indexOf('/'));
            // Вторая подстрока - id лота.
            int lotId = Integer.parseInt (
                    requestString.substring(requestString.indexOf('/') + 1));

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();

            switch(action){
                case "remove":
                    lotService.deleteLot(lotId);
                    request.getRequestDispatcher("/main").
                            forward(request, response);
                    break;
                case "update":
                    request.setAttribute("lot", lotService.getLotById(lotId));
                    request.getRequestDispatcher("/usr/lot/update.jsp").
                            forward(request, response);
                    break;
                case "view":
                    UserService userService = serviceFactory.getUserService();
                    Lot lot = lotService.getLotById(lotId);
                    request.setAttribute("lot", lot);
                    request.setAttribute("seller", userService.getUserById(lot.getSellerId()));
                    request.setAttribute("buyer", userService.getUserById(lot.getBuyerId()));
                    request.getRequestDispatcher("/LotView.jsp").
                            forward(request, response);
                    break;
            }
        } catch (Exception e) {
            writer.println(e);
        } finally{
            writer.close();
        }
    }
}
