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

        String name = request.getParameter("user");
        Usuario user = ManageUsuario.read(name);
        if (user != null) {
            ManageUsuario.delete(user);
            Usuario userEmpty = ManageUsuario.read(name);
            if(userEmpty == null || userEmpty.getUsername().isEmpty()){
                request.setAttribute("msg", "El usuario ha sido eliminado correctamente");
            }else{
                request.setAttribute("error", "Error al intentar eliminar el usuario");
            }
        } else {
            request.setAttribute("error", "Error al intentar eliminar el usuario");
        }
        response.sendRedirect("ListaUsuarios");
    }
}
