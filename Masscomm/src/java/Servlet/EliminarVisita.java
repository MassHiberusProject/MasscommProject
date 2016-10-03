/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageVisitas;
import com.masscomm.common.Visitas;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pmayor
 */
public class EliminarVisita extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            Visitas visita = ManageVisitas.read(ident);
            if (visita != null) {
                String nombreFoto = visita.getFoto();
                String nombreLogo = visita.getLogo();
                ManageVisitas.delete(visita);
                Visitas visitaEmpty = ManageVisitas.read(ident);
                if (visitaEmpty == null) {
                    String ruta = "/img";
                    String path = request.getRealPath(ruta);
                    File f1 = new File(path + "/" + nombreFoto);
                    File f2 = new File(path + "/" + nombreLogo);
                    f1.delete();
                    f2.delete();
                    response.sendRedirect("ListaVisitas?msg=okDelete");
                } else {
                    response.sendRedirect("ListaVisitas?msg=err");
                }
            } else {
                response.sendRedirect("ListaVisitas?msg=err");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("ListaVisitas?msg=err");
        }
    }

}
