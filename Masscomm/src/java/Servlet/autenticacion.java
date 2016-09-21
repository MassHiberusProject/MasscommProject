/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageUsuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author claencina
 */
public class Autenticacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usr");
        String contrasenia = request.getParameter("pwd");

        String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);

        List ok = ManageUsuario.comprueba(usuario, contraseniaEncriptada);
        if (!ok.isEmpty()) {
            List rol = ManageUsuario.getRol(usuario);
            if (!rol.isEmpty()) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("user", usuario);
                String role = (String) rol.get(0);
                if (role.compareTo("usuario") == 0) {
                    response.sendRedirect("index.jsp");
                } else if (role.compareTo("administrador") == 0) {
                    response.sendRedirect("admin/ListaUsuarios");
                } else {
                    request.setAttribute("error", "No tiene acceso a la aplicación. Contacte con el administrador");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("error", "No tiene acceso a la aplicación. Contacte con el administrador");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error", "El nombre de usuario o la contraseña es incorrecto");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else {
            String user = (String) sesion.getAttribute("user");
            if (user == null) {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                List rol = ManageUsuario.getRol(user);
                if (!rol.isEmpty()) {
                    String role = (String) rol.get(0);
                    if (role.compareTo("usuario") == 0) {
                        response.sendRedirect("index.jsp");
                    } else if (role.compareTo("administrador") == 0) {
                        response.sendRedirect("admin/ListaUsuarios");
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }
}
