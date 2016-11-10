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
import java.io.File;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class EliminarCumpleanios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            Cumpleanios c = ManageCumpleanios.read(ident);
            if (c != null) {
                String nombreFoto = c.getImagen();
                ManageCumpleanios.delete(c);
                Cumpleanios cumpleEmpty = ManageCumpleanios.read(ident);
                if (cumpleEmpty == null) {
                    String ruta = "/img";
                    String path = request.getRealPath(ruta);
                    File f = new File(path + "/" + nombreFoto);
                    f.delete();
                    response.sendRedirect("ListaCumpleanios?msg=okDelete");
                } else {
                    response.sendRedirect("ListaCumpleanios?msg=err");
                }
            } else {
                response.sendRedirect("ListaCumpleanios?msg=err");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("ListaCumpleanios?msg=err");
        }
    }

}
