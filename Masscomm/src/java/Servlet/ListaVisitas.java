/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.Fondo;
import com.masscomm.common.ManageFondo;
import com.masscomm.common.ManageVisitas;
import com.masscomm.common.Visitas;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pmayor
 */
public class ListaVisitas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Visitas> visitas = ManageVisitas.list();
        String nameFondo = null;
        List<Fondo> fondos = ManageFondo.list();
        for (Fondo fondo : fondos) {
            if (fondo.getTipo().compareTo("V") == 0) {
                nameFondo = fondo.getNombre();
                break;
            }
        }

        String param = request.getParameter("msg");
        if (param != null ) {
            if(param.compareTo("ok") == 0){
            request.setAttribute("msg", "La visita ha sido añadida correctamente");
            }else if (param.compareTo("okEdit") == 0){
                request.setAttribute("msg", "La visita ha sido modificada correctamente");
            }else if (param.compareTo("okDelete") == 0){
                request.setAttribute("msg", "La visita ha sido eliminada correctamente");
            }else if (param.compareTo("err") == 0){
                request.setAttribute("error", "Error al intentar eliminar la visita");
            }
        }
        
        request.setAttribute("fondo", nameFondo);
        request.setAttribute("visitas", visitas);
        RequestDispatcher rd = request.getRequestDispatcher("visitas.jsp");
        rd.forward(request, response);
    }

}
