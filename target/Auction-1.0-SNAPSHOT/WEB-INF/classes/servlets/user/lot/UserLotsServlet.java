package servlets.user.lot;

import app.models.User;
import app.service.LotService;
import app.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLotsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            getServletContext().getRequestDispatcher("/main").
                    forward(request, response);
        }
        PrintWriter writer = response.getWriter();
        try{
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();

            User user = (User)session.getAttribute("user");
            if (user == null) throw new Exception("Error: the user is not defined");
            request.setAttribute("lots", lotService.getUserLots(user.getId()));
            getServletContext().getRequestDispatcher("/usr/lot/lots.jsp").
                    forward(request, response);
        } catch (Exception e) {
            writer.println(e);
        } finally{
            writer.close();
        }
    }
}
