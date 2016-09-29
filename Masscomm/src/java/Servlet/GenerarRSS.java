/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author claencina
 */
public class GenerarRSS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        String cod = request.getParameter("ids");

        List<String> codigos = new ArrayList<String>();

        if (cod!= null) {
            StringTokenizer st = new StringTokenizer(cod, ",");
            String aux;
            while (st.hasMoreTokens()) {
                aux = st.nextToken();
                codigos.add(aux);
            }
        }
    }
}
