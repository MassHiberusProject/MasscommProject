/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageUsuario;
import com.masscomm.common.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pmayor
 */
public class EliminarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int idUser = -1;
        try {
            String id = request.getParameter("id");
            idUser = Integer.parseInt(id);
        } catch (Exception e) {
            //Mensaje error
            response.sendRedirect("ListaUsuarios");
            return;
        }
        Usuario user = ManageUsuario.read(idUser);
        if (user!=null) {
            ManageUsuario.delete(user);
        } else {
            //Mensaje error
        }
        response.sendRedirect("ListaUsuarios");
    }
}
