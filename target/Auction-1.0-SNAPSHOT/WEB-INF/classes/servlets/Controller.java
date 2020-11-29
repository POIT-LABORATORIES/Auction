package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Objects;

@MultipartConfig
public class Controller extends HttpServlet {
    public Controller(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getPart("img_file");
        String name = request.getParameter("lot_name");
        String bid = request.getParameter("bid");
        int condition;
        if (Objects.equals(request.getParameter("condition"), "New")){
            condition = 0;
        } else{
            condition = 1;
        }
        String finishTime = request.getParameter("finish_time");

        try(PrintWriter writer = response.getWriter()){
            writer.println(name);
            writer.println(bid);
            writer.println(condition);
            writer.println(finishTime);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/main").forward(request, response);
    }
}
