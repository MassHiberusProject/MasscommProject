/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author claencina
 */
public class Im extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int idIm = Integer.parseInt(id);
        OutputStream out;
/*
        try {
            response.setContentType("image/png");
            out = response.getOutputStream();
                out.write(ManageImagen.read(idIm).getImage());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }
}
