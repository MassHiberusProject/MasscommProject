/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masscomm.common.Cumpleanios;
import com.masscomm.common.ManageCumpleanios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author claencina
 */
public class EditarCumpleanios extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesion = request.getSession();
        

        String idS = request.getParameter("id");
        Integer id = Integer.parseInt(idS);
        sesion.setAttribute("id", idS);
        Cumpleanios c = new Cumpleanios();
        c = ManageCumpleanios.read(id);
        
        request.setAttribute("cumpleanios", c);
       // response.sendRedirect("editarCumpleanios.jsp");
        
        RequestDispatcher rd = request.getRequestDispatcher("editarCumpleanios.jsp");
        rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String empresa = request.getParameter("empresa");
        String foto = request.getParameter("foto");
        String fech = request.getParameter("fecha");
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        if(fech!=null){
            try {
                fecha = formato.parse(fech);
            } catch (ParseException ex) {
                Logger.getLogger(EditarCumpleanios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        HttpSession sesion = request.getSession();
        String idS = (String) sesion.getAttribute("id");
        Integer id = Integer.parseInt(idS);
        
        Cumpleanios c = ManageCumpleanios.read(id);
        
        c.setNombre(nombre);
        c.setApellidos(apellidos);
        c.setEmpresa(empresa);
        c.setFecha(fecha);
        ManageCumpleanios.update(c);
        
        
        response.sendRedirect("Cumpleanios");
        

    }
    
   

}
