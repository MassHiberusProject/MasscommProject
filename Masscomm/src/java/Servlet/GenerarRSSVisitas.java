/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.Fondo;
import com.masscomm.common.ManageFondo;
import com.masscomm.common.ManageVisitas;
import com.masscomm.common.Visitas;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerarRSSVisitas extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");

        try {
            Date now = new Date();
            now.setHours(0);
            now.setMinutes(0);
            now.setSeconds(0);
            
            System.out.println("Dia: " + Integer.toString(now.getDate()));
            System.out.println("Mes: " + Integer.toString(now.getMonth() + 1));

            List<Visitas> visitas = ManageVisitas.listDate(Integer.toString(now.getDate()), Integer.toString(now.getMonth() + 1));
            System.out.println("Tamaño array visitas: " + visitas.size());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document document = null;

            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();

            String ip = InetAddress.getLocalHost().getHostAddress();
            int puerto = request.getServerPort();
            String rutaVisita = "/img/";
            String rutafondoVisita = "/fondo/";
            String path = request.getContextPath();

            Element root = (Element) document.createElement("visitas");
            document.appendChild(root);

            List<Fondo> fondos = ManageFondo.list();
            Fondo fondoVisitas = null;
            for (Fondo f : fondos) {
                if (f.getTipo().compareTo("V") == 0) {
                    fondoVisitas = f;
                }
            }

            for (Visitas v : visitas) {
                Element item = (Element) document.createElement("item");
                root.appendChild(item);

                Element nombre = (Element) document.createElement("nombre");
                item.appendChild(nombre);
                nombre.appendChild(document.createTextNode(v.getNombre() + " " + v.getApellidos()));

                Element cargo = (Element) document.createElement("cargo");
                item.appendChild(cargo);
                cargo.appendChild(document.createTextNode(v.getCargo()));

                Element empresa = (Element) document.createElement("empresa");
                item.appendChild(empresa);
                empresa.appendChild(document.createTextNode(v.getEmpresa()));

                Element logo = (Element) document.createElement("logo");
                item.appendChild(logo);
                if (v.getLogo() == null) {
                    logo.appendChild(document.createTextNode(""));
                } else {
                    logo.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutaVisita + v.getLogo()));
                }

                Element foto = (Element) document.createElement("foto");
                item.appendChild(foto);
                if (v.getFoto() == null) {
                    foto.appendChild(document.createTextNode(""));
                } else {
                    foto.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutaVisita + v.getFoto()));
                }

                Element fondo = (Element) document.createElement("fondo");
                item.appendChild(fondo);
                fondo.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutafondoVisita + fondoVisitas.getNombre()));
            }

            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(document);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenerarRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
