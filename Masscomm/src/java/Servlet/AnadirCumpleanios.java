/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageCumpleanios;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author claencina
 */
public class AnadirCumpleanios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        java.util.Date fecha = new Date();
        request.setAttribute("fecha", fecha);
        request.setAttribute("contador", 0);

        RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int contador = 0;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                String nombre = null;
                String apellidos = null;
                String empresa = null;
                FileItem foto = null;
                String fech = null;

                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    String campoN = item.getFieldName();
                    if (item.isFormField()) {
                        if (campoN.equals("nombre")) {
                            nombre = item.getString("UTF-8");
                        } else if (campoN.equals("apellidos")) {
                            apellidos = item.getString("UTF-8");
                        } else if (campoN.equals("empresa")) {
                            empresa = item.getString("UTF-8");
                        } else if (campoN.equals("fecha")) {
                            fech = item.getString("UTF-8");
                        }
                    } else if (campoN.equals("foto") && item.getName() != null && item.getName().trim().length() > 0) {
                        foto = item;

                    }
                }

                com.masscomm.common.Cumpleanios c = new com.masscomm.common.Cumpleanios();

                request.setAttribute("nombre", nombre);
                request.setAttribute("apellidos", apellidos);
                request.setAttribute("empresa", empresa);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = null;

                if (fech!=null && !fech.isEmpty()) {
                    try {
                        fecha = formato.parse(fech);
                        request.setAttribute("fech", fecha);
                        c.setFecha(fecha);

                    } catch (ParseException ex) {
                        request.setAttribute("contador", 1);
                        request.setAttribute("error_insert", "La fecha introducida no es correcta");
                        RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    request.setAttribute("f", fecha);
                    fecha = new Date();
                    request.setAttribute("fech", fecha);
                }

                if (foto != null) {
                    int i = foto.getName().lastIndexOf(".");
                    String ex = "";
                    if (i != -1) {
                        ex = foto.getName().substring(i + 1);
                    }
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0
                            && ex.compareToIgnoreCase("jpeg") != 0 && ex.compareToIgnoreCase("bmp") != 0
                            && ex.compareToIgnoreCase("gif") != 0 && ex.compareToIgnoreCase("tiff") != 0) {
                        contador++;
                        request.setAttribute("error_foto", "El fichero no es una imagen");
                    }
                }
                if (contador != 0) {
                    request.setAttribute("contador", contador);

                    RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
                    rd.forward(request, response);
                } else {
                    if (foto != null) {
                        String ruta = "/img";
                        String path = request.getRealPath(ruta);
                        String nom = "";
                        String nomF = "";

                        nom += foto.getName();
                        String auxN = nom;

                        File f = new File(path + "/" + nom);

                        int ex = 0;
                        while (f.exists()) {
                            ex++;
                            StringTokenizer st = new StringTokenizer(auxN, ".");
                            List<String> n = new ArrayList<String>();
                            String aux;
                            while (st.hasMoreTokens()) {
                                aux = st.nextToken();
                                n.add(aux);
                            }
                            String nombreFoto = n.get(0);
                            String extension = n.get(1);

                            nomF = nombreFoto + ex;
                            String no = "";
                            no = nomF + "." + extension;

                            f = new File(path + "/" + no);
                            nom = no;
                        }
                        foto.write(f);

                        c.setImagen(nom);
                    }

                    c.setNombre(nombre);

                    if (apellidos != null && !apellidos.isEmpty()) {
                        c.setApellidos(apellidos);
                    }
                    if (empresa != null && !empresa.isEmpty()) {
                        c.setEmpresa(empresa);
                    }

                    int ok = ManageCumpleanios.save(c);
                    if (ok != -1) {
                        response.sendRedirect("ListaCumpleanios?msg=ok");
                    } else {
                        request.setAttribute("contador", 1);
                        request.setAttribute("error_insert", "No ha sido posible insertar el nuevo cumpleaños");
                        RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
                        rd.forward(request, response);
                    }
                }

            } catch (FileUploadException ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_insert", "No ha sido posible insertar el nuevo cumpleaños");
                RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_insert", "No ha sido posible insertar el nuevo cumpleaños");
                RequestDispatcher rd = request.getRequestDispatcher("anadirCumpleanios.jsp");
                rd.forward(request, response);
            }

        }

    }

}
