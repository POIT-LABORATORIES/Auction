package servlets;

import app.models.Lot;
import app.service.LotService;
import app.service.ServiceFactory;
import app.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class MainServlet extends HttpServlet {

    public MainServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        try{
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LotService lotService = serviceFactory.getLotService();
            request.setAttribute("lots", lotService.getAllActiveLots());
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        } catch (Exception e) {
            writer.println(e);
        } finally{
            writer.close();
        }
    }
}
