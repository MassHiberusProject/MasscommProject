/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageVisitas;
import com.masscomm.common.Visitas;
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

public class EditarVisita extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            Visitas visita = ManageVisitas.read(ident);
            if (visita != null) {
                request.setAttribute("contador", 0);
                request.setAttribute("visita", visita);
            } else {
                request.setAttribute("error", "Error al intentar editar la visita");
            }
            RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error al intentar editar la visita");
            RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
            rd.forward(request, response);
        }
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
                String cargo = null;
                FileItem foto = null;
                FileItem logo = null;
                String fech = null;
                String id = null;

                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    String campoN = item.getFieldName();
                    if (item.isFormField()) {
                        switch (campoN) {
                            case "nombre":
                                nombre = item.getString("UTF-8");
                                break;
                            case "apellidos":
                                apellidos = item.getString("UTF-8");
                                break;
                            case "empresa":
                                empresa = item.getString("UTF-8");
                                break;
                            case "cargo":
                                cargo = item.getString("UTF-8");
                                break;
                            case "fecha":
                                fech = item.getString("UTF-8");
                                break;
                            case "id":
                                id = item.getString("UTF-8");
                                break;
                            default:
                                break;
                        }
                    } else if (campoN.equals("foto") && item.getName() != null && item.getName().trim().length() > 0) {
                        foto = item;
                    } else if (campoN.equals("logo") && item.getName() != null && item.getName().trim().length() > 0) {
                        logo = item;
                    }
                }

                Integer ident = Integer.parseInt(id);
                Visitas visita = ManageVisitas.read(ident);

                if (visita.getFoto() != null) {
                    request.setAttribute("foto_id", visita.getFoto());
                }
                if (visita.getLogo() != null) {
                    request.setAttribute("logo_id", visita.getLogo());
                }

                request.setAttribute("nombre", nombre);
                request.setAttribute("apellidos", apellidos);
                request.setAttribute("empresa", empresa);
                request.setAttribute("cargo", cargo);
                request.setAttribute("id", ident);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = null;

                if (fech != null && !fech.isEmpty()) {
                    try {
                        fecha = formato.parse(fech);
                        request.setAttribute("fech", fecha);

                    } catch (ParseException ex) {
                        request.setAttribute("contador", 1);
                        request.setAttribute("error_insert", "La fecha introducida no es correcta");
                        RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
                        rd.forward(request, response);
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
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0
                            && ex.compareToIgnoreCase("jpeg") != 0 && ex.compareToIgnoreCase("bmp") != 0
                            && ex.compareToIgnoreCase("gif") != 0 && ex.compareToIgnoreCase("tiff") != 0) {
                        contador++;
                        request.setAttribute("error_foto", "El fichero correspondiente a la fotografía no es una imagen");
                    }
                }
                if (logo != null) {
                    int i = logo.getName().lastIndexOf(".");
                    String ex = "";
                    if (i != -1) {
                        ex = logo.getName().substring(i + 1);
                    }
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0
                            && ex.compareToIgnoreCase("jpeg") != 0 && ex.compareToIgnoreCase("bmp") != 0
                            && ex.compareToIgnoreCase("gif") != 0 && ex.compareToIgnoreCase("tiff") != 0) {
                        contador++;
                        request.setAttribute("error_logo", "El fichero correspondiente al logotipo no es una imagen");
                    }
                }
                if (contador != 0) {
                    request.setAttribute("contador", contador);

                    RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
                    rd.forward(request, response);
                } else {
                    if (foto != null) {
                        String ruta = "/img";
                        String path = request.getRealPath(ruta);
                        File f1 = new File(path + "/" + visita.getFoto());
                        f1.delete();

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
                        visita.setFoto(nom);
                    }
                    if (logo != null) {
                        String ruta = "/img";
                        String path = request.getRealPath(ruta);
                        File f1 = new File(path + "/" + visita.getLogo());
                        f1.delete();

                        String nom = "";
                        String nomF = "";

                        nom += logo.getName();
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
                        logo.write(f);
                        visita.setLogo(nom);
                    }

                    visita.setNombre(nombre);
                    visita.setApellidos(apellidos);
                    visita.setEmpresa(empresa);
                    visita.setCargo(cargo);
                    visita.setFecha(fecha);

                    ManageVisitas.update(visita);
                    response.sendRedirect("ListaVisitas?msg=okEdit");
                }
            } catch (FileUploadException ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_insert", "No ha sido posible editar la visita");
                RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_insert", "No ha sido posible editar la visita");
                RequestDispatcher rd = request.getRequestDispatcher("editarVisita.jsp");
                rd.forward(request, response);
            }
        }
    }
}
