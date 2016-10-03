/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.Fondo;
import com.masscomm.common.ManageCumpleanios;
import com.masscomm.common.ManageFondo;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author claencina
 */
public class ListaCumpleanios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<com.masscomm.common.Cumpleanios> cumpleanios = ManageCumpleanios.list();
        String nameFondo = "";
        List<Fondo> fondos = ManageFondo.list();
        for (Fondo fondo : fondos) {
            if (fondo.getTipo().compareTo("C") == 0) {
                nameFondo = fondo.getNombre();
                break;
            }
        }
        String param = request.getParameter("msg");
        if (param != null ) {
            if(param.compareTo("ok") == 0){
            request.setAttribute("msg", "El cumpleaños ha sido añadido correctamente");
            }else if (param.compareTo("okEdit") == 0){
                request.setAttribute("msg", "El cumpleaños ha sido modificado correctamente");
            }else if (param.compareTo("okDelete") == 0){
                request.setAttribute("msg", "El cumpleaños ha sido eliminado correctamente");
            }else if (param.compareTo("err") == 0){
                request.setAttribute("error", "Error al intentar eliminar el cumpleaños");
            }
        }

        request.setAttribute("fondo", nameFondo);
        request.setAttribute("cumpleanios", cumpleanios);
        RequestDispatcher rd = request.getRequestDispatcher("cumpleanios.jsp");
        rd.forward(request, response);

    }
}
