/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageUsuario;
import com.masscomm.common.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author pmayor
 */
public class AnadirUsuario extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usr");
        String contrasenia = request.getParameter("pwd");

        String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
        
        Usuario user = new Usuario(usuario, contraseniaEncriptada);
        int ok = ManageUsuario.save(user);
        if(ok != -1){
            response.sendRedirect("../index.jsp");
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
            rd.forward(request, response);
        }
    }
}
