/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masscomm.common.Cumpleanios;
import com.masscomm.common.Fondo;
import com.masscomm.common.ManageCumpleanios;
import com.masscomm.common.ManageFondo;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.net.InetAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerarRSS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        try {
            Date now = new Date();
            now.setHours(0);
            now.setMinutes(0);
            now.setSeconds(0);

            List<Cumpleanios> cumples = ManageCumpleanios.listDate(Integer.toString(now.getDate()), Integer.toString(now.getMonth() + 1));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document document = null;

            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();

            String ip = InetAddress.getLocalHost().getHostAddress();
            int puerto = request.getServerPort();
            String rutaCumple = "/img/";
            String rutafondoCumple = "/fondo/";
            String path = request.getContextPath();            

            Element root = (Element) document.createElement("cumpleanios");
            document.appendChild(root);

            List<Fondo> fondos = ManageFondo.list();
            Fondo fondoCumpleanios = null;
            for (Fondo f : fondos) {
                if (f.getTipo().compareTo("C") == 0) {
                    fondoCumpleanios = f;
                }
            }

            for (Cumpleanios c : cumples) {
                Element item = (Element) document.createElement("item");
                root.appendChild(item);

                Element nombre = (Element) document.createElement("nombre");
                item.appendChild(nombre);
                nombre.appendChild(document.createTextNode(c.getNombre() + " " + c.getApellidos()));

                Element empresa = (Element) document.createElement("empresa");
                item.appendChild(empresa);
                empresa.appendChild(document.createTextNode(c.getEmpresa()));

                Element img = (Element) document.createElement("img");
                item.appendChild(img);
                if (c.getImagen() == null) {
                    img.appendChild(document.createTextNode(""));
                } else {
                    img.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutaCumple + c.getImagen()));
                }

                Element fondo = (Element) document.createElement("fondo");
                item.appendChild(fondo);
                fondo.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutafondoCumple + fondoCumpleanios.getNombre()));
            }

            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(document);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenerarRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
