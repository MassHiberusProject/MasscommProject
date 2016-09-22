package Servlet;


import com.masscomm.common.ManageUsuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pmayor
 */
public class ComprobarSesion {

    public static void comprueba(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                        request.setAttribute("error", "No tiene acceso a la aplicación. Contacte con el administrador");
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "No tiene acceso a la aplicación. Contacte con el administrador");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }
}
