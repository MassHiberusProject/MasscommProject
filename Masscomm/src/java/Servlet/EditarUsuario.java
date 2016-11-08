/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageUsuario;
import com.masscomm.common.Rol;
import com.masscomm.common.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class EditarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("user");
        try {
            int id = Integer.parseInt(name);
            Usuario user = ManageUsuario.read(id);
            if (user != null) {
                request.setAttribute("rols", ManageUsuario.listRol());                
                request.setAttribute("roles", ManageUsuario.listRolUser(id));
                request.setAttribute("usuario", user);
            } else {
                request.setAttribute("error", "Error al intentar editar el usuario");
            }
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al intentar editar el usuario");
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        }
    }

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
        String[] rol = request.getParameterValues("rol");
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        request.setAttribute("rols", ManageUsuario.listRol());

        try {

            int ident = Integer.parseInt(id);

            boolean correcto = true;

            if (!contrasenia.isEmpty() && !contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
                request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
                correcto = false;
            }
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
                correcto = false;
            }
            if (rol == null) {
                request.setAttribute("errorrol", "Debe seleccionar al menos un rol");
                correcto = false;
            }
            if (ManageUsuario.existeNameNotme(usuario, ident)) {
                request.setAttribute("errorname", "Ya existe un usuario con ese nombre");
                correcto = false;
            }
            if (ManageUsuario.existeEmailNotme(mail, ident)) {
                request.setAttribute("erroremail", "Ya existe un usuario con ese correo electrónico");
                correcto = false;
            }
            if (correcto) {

                Usuario user = ManageUsuario.read(ident);
                if (user != null) {
                    user.setUsername(usuario);
                    user.setEmail(mail);
                    if (!contrasenia.isEmpty()) {
                        String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
                        user.setPassword(contraseniaEncriptada);
                    }
                    Set <Rol> rls=new HashSet<Rol>();
                    Rol role=new Rol();
                    for (String r : rol) {
                        try {
                            int idRol = Integer.parseInt(r);
                            role = ManageUsuario.readRol(idRol); 
                            rls.add(role);
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Error al intentar añadir el usuario");
                            RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
                            rd.forward(request, response);
                        }
                    }
                    user.setRols(rls);
                    ManageUsuario.update(user);
                    response.sendRedirect("ListaUsuarios?msg=okEdit");
                } else {
                    request.setAttribute("errorconf", "Error al intentar editar el usuario");
                    RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
                    rd.forward(request, response);
                }

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
                rd.forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorconf", "Error al intentar editar el usuario");
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        }
    }

}
