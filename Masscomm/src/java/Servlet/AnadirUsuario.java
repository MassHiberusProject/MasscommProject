/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageUsuario;
import com.masscomm.common.RolesUsuarios;
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
        request.setAttribute("usr", usuario);
        String contrasenia = request.getParameter("pwd");
        request.setAttribute("pwd", contrasenia);
        String mail = request.getParameter("mail");
        request.setAttribute("mail", mail);

        if (!contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
            request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
            }
            RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
            rd.forward(request, response);
        } else{
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
                RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
                rd.forward(request, response);
            } else {
                String rol = request.getParameter("rol");
                String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);

                Usuario user = new Usuario(usuario, contraseniaEncriptada, mail);
                RolesUsuarios role = new RolesUsuarios(user, rol);
                int ok = ManageUsuario.save(user, role);
                if (ok!=-1) {
                    request.setAttribute("msg", "El usuario ha sido añadido correctamente");
                    response.sendRedirect("ListaUsuarios");
                } else {
                    request.setAttribute("error", "Error al intentar añadir el usuario");
                    RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
        rd.forward(request, response);
    }
}
