/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.Fondo;
import com.masscomm.common.ManageCumpleanios;
import com.masscomm.common.ManageFondo;
import com.masscomm.common.ManageVisitas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class AnadirFondoVisita extends HttpServlet {

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

                FileItem fondo = null;
                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    String campoN = item.getFieldName();
                    if (campoN.equals("fondo") && item.getName() != null && item.getName().trim().length() > 0) {
                        fondo = item;
                    }
                }

                if (fondo != null) {
                    int i = fondo.getName().lastIndexOf(".");
                    String ex = "";
                    if (i != -1) {
                        ex = fondo.getName().substring(i + 1);
                    }
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0
                            && ex.compareToIgnoreCase("jpeg") != 0 && ex.compareToIgnoreCase("bmp") != 0
                            && ex.compareToIgnoreCase("gif") != 0 && ex.compareToIgnoreCase("tiff") != 0) {
                        contador++;
                        request.setAttribute("error_foto", "El fichero seleccionado para el fondo no es una imagen");
                    }
                }
                if (contador != 0) {
                    request.setAttribute("contador", contador);
                    request.setAttribute("visitas", ManageVisitas.list());

                    String nameFondo = "";
                    List<Fondo> fondos = ManageFondo.list();
                    for (Fondo fond : fondos) {
                        if (fond.getTipo().compareTo("V") == 0) {
                            nameFondo = fond.getNombre();
                            break;
                        }
                    }

                    request.setAttribute("fondo", nameFondo);

                    RequestDispatcher rd = request.getRequestDispatcher("visitas.jsp");
                    rd.forward(request, response);
                } else if (fondo != null) {
                    String ruta = "/fondo";
                    String path = request.getRealPath(ruta);
                    String nom = "";
                    String nomF = "";

                    nom += fondo.getName();
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
                    fondo.write(f);

                    Fondo fon = new Fondo();
                    fon.setNombre(nom);
                    fon.setTipo("V");

                    Fondo fondoBD = new Fondo();
                    List<Fondo> fondos = ManageFondo.list();
                    for (int i = 0; i < fondos.size(); i++) {
                        if (fondos.get(i).getTipo().compareTo("V") == 0) {
                            fondoBD = fondos.get(i);
                        }
                    }

                    int estado = ManageFondo.save(fon);

                    if (estado != -1) {
                        if (fondoBD != null) {
                            File f1 = new File(path + "/" + fondoBD.getNombre());
                            f1.delete();
                            ManageFondo.delete(fondoBD);
                        }
                        response.sendRedirect("ListaVisitas");
                    } else {
                        request.setAttribute("contador", 1);
                        request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el fondo");
                        RequestDispatcher rd = request.getRequestDispatcher("visitas.jsp");
                        rd.forward(request, response);
                    }
                }

            } catch (FileUploadException ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el fondo");
                RequestDispatcher rd = request.getRequestDispatcher("visitas.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el fondo");
                RequestDispatcher rd = request.getRequestDispatcher("visitas.jsp");
                rd.forward(request, response);
            }
        }

    }
}
