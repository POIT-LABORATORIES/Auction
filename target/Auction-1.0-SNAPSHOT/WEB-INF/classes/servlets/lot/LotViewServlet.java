package servlets.lot;

import app.models.Lot;
import app.models.User;
import app.service.LotService;
import app.service.ServiceFactory;
import app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class LotViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        PrintWriter writer = response.getWriter();
        try{
            if (session == null)
                throw new Exception("You are not registered to perform this action!");
            User user = (User)session.getAttribute("user");

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();
            UserService userService = serviceFactory.getUserService();

            Lot lot = lotService.getLotById(
                    Integer.parseInt(request.getParameter("lot_id")));
            if (user.equals(userService.getUserById(lot.getSellerId()))){
                throw new Exception("You cannot place bids on your own lot");
            }

            Timestamp finishDate = Timestamp.valueOf(lot.getFinishTime());
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            if (finishDate.compareTo(currentDate) > 0)
                throw new Exception("This lot is not active");

            int newBid = Integer.parseInt(request.getParameter("bid"));
            int currentBid = lot.getBid();
            if (newBid <= currentBid)
                throw new Exception("New bid should be greater than the previous");
            // ОБНОВЛЕНИЕ У ЛОТА СТАВКИ, ПОКУПАТЕЛЯ, КОЛИЧЕСТВА СТАВОК.
            lot.setBid(newBid);
            lot.setBuyerId(user.getId());
            lot.setBidQuantity(lot.getBidQuantity() + 1);
            lotService.editLot(lot);
            response.sendRedirect(request.getContextPath() + "/main");
        } catch (Exception e) {
            writer.println(e);
        } finally{
            writer.close();
        }
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
            int lotId = Integer.parseInt(requestString.substring(1));

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();
            UserService userService = serviceFactory.getUserService();

            Lot lot = lotService.getLotById(lotId);
            request.setAttribute("lot", lot);
            request.setAttribute("seller", userService.getUserById(lot.getSellerId()));
            request.setAttribute("buyer", userService.getUserById(lot.getBuyerId()));
            request.getRequestDispatcher("/LotView.jsp").
                    forward(request, response);
        } catch (Exception e) {
            writer.println(e);
        } finally{
            writer.close();
        }
    }
}
