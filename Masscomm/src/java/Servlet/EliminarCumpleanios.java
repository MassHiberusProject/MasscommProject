/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageCumpleanios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masscomm.common.Cumpleanios;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author claencina
 */
public class EliminarCumpleanios extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String idS = request.getParameter("id");
        
        Integer id = Integer.parseInt(idS);
        Cumpleanios c = new Cumpleanios();
        c = ManageCumpleanios.read(id);
        ManageCumpleanios.delete(c);
        
        response.sendRedirect("Cumpleanios");

    }


}
