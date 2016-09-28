/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masscomm.common.Cumpleanios;
import com.masscomm.common.Imagen;
import com.masscomm.common.ManageCumpleanios;
import com.masscomm.common.ManageImagen;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;

/**
 *
 * @author claencina
 */
public class EditarCumpleanios extends HttpServlet {

    private int contador;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        contador = 0;

        HttpSession sesion = request.getSession();

        String idS = request.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(idS);
        } catch (Exception e) {
            response.sendRedirect("Cumpleanios");
            return;
        }
        
        sesion.setAttribute("id", idS);
        Cumpleanios c = new Cumpleanios();
        c = ManageCumpleanios.read(id);
        if (c != null) {
            java.util.Date fecha = new Date();
            request.setAttribute("fecha", fecha);
            request.setAttribute("contador", contador);

            request.setAttribute("cumpleanios", c);

            RequestDispatcher rd = request.getRequestDispatcher("editarCumpleanios.jsp");
            rd.forward(request, response);
        }else{
            response.sendRedirect("Cumpleanios");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        contador = 0;

        HttpSession sesion = request.getSession();
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
                String idS = (String) sesion.getAttribute("id");
                Integer id = Integer.parseInt(idS);

                Cumpleanios c = ManageCumpleanios.read(id);
                if (c.getImage_id() != null) {
                    request.setAttribute("foto_id", c.getImage_id().getImage_id());
                }

                request.setAttribute("nombre", nombre);
                request.setAttribute("apellidos", apellidos);
                request.setAttribute("empresa", empresa);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = null;

                if (fech.toString().length() != 0) {

                    try {
                        fecha = formato.parse(fech);
                        request.setAttribute("fech", fecha);

                    } catch (ParseException ex) {
                        Logger.getLogger(EditarCumpleanios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    fecha = new Date();
                    request.setAttribute("fech", fecha);
                }

                if (foto != null) {
                    int i = foto.getName().lastIndexOf(".");
                    String ex = "";
                    if (i != -1) {
                        ex = foto.getName().substring(i + 1);
                    }
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0) {
                        contador++;
                        request.setAttribute("error_foto", "El fichero no es una imagen");
                    }
                }
                if (contador != 0) {
                    request.setAttribute("contador", contador);

                    RequestDispatcher rd = request.getRequestDispatcher("editarCumpleanios.jsp");
                    rd.forward(request, response);
                } else {

                    if (foto != null) {
                        String ruta = "/img";
                        String rutaI = request.getRealPath(ruta);
                        foto.write(new File(rutaI + "/" + foto.getName()));

                        File im = new File(rutaI + "/" + foto.getName());
                        Imagen imagen = null;
                        if (c.getImage_id() != null) {
                            Integer idIB = c.getImage_id().getImage_id();
                            Imagen imagenB = new Imagen();
                            imagenB = ManageImagen.read(idIB);
                            ManageImagen.delete(imagenB);
                        }

                        int cod = ManageImagen.save(im);
                        imagen = ManageImagen.read(cod);

                        c.setImage_id(imagen);

                    }

                    c.setNombre(nombre);
                    c.setApellidos(apellidos);
                    c.setEmpresa(empresa);
                    c.setFecha(fecha);

                    ManageCumpleanios.update(c);
                    response.sendRedirect("Cumpleanios");
                }

            } catch (FileUploadException ex) {
                Logger.getLogger(EditarCumpleanios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(EditarCumpleanios.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
