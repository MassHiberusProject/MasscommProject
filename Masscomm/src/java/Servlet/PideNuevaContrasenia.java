/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageContrasenia;
import com.masscomm.common.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PideNuevaContrasenia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cod = request.getParameter("cc");
        List<Usuario> idusers = ManageContrasenia.existeCodigo(cod);
        if (idusers == null || idusers.isEmpty()) {
            response.sendRedirect("CambioContrasenia?msg=error");
        } else {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("iduser", idusers.get(0));
            response.sendRedirect("CambioContrasenia");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
