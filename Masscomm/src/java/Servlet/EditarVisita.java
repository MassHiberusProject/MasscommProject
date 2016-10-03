/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageVisitas;
import com.masscomm.common.Visitas;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pmayor
 */
public class EditarVisita extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            Visitas visita = ManageVisitas.read(ident);
            if (visita != null) {
                java.util.Date fecha = new Date();
                request.setAttribute("fecha", fecha);
                request.setAttribute("contador", 0);

                request.setAttribute("visita", visita);
            } else {
                request.setAttribute("error", "Error al intentar editar la visita");
            }
            RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al intentar editar la visita");
            RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
