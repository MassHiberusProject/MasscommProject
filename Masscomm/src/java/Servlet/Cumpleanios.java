/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.Fondo;
import com.masscomm.common.ManageCumpleanios;
import com.masscomm.common.ManageFondo;
import com.masscomm.persistence.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Cumpleanios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        
        
        
        List<com.masscomm.common.Cumpleanios> cumpleanios = ManageCumpleanios.list();
        Fondo fondo = new Fondo();
        List<Fondo> fondos = ManageFondo.list();
        for(int i=0; i<fondos.size(); i++){
            if(fondos.get(i).getTipo().compareTo("C")==0){
                fondo = fondos.get(i);
            }
        }
        
        
        request.setAttribute("fondo", fondo.getNombre());
        request.setAttribute("cumpleanios", cumpleanios);
        RequestDispatcher rd = request.getRequestDispatcher("cumpleanios.jsp");
        rd.forward(request, response);
            
    }
}
