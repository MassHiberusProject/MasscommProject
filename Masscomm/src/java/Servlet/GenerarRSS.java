/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masscomm.common.Cumpleanios;
import com.masscomm.common.ManageCumpleanios;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author claencina
 */
public class GenerarRSS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            
            Date now = new Date();
            now.setHours(0);
            now.setMinutes(0);
            now.setSeconds(0);
            List<Cumpleanios> cumples = ManageCumpleanios.listDate(now);
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "cumpleanios", null);
            document.setXmlVersion("1.0");
            
            //Main Node
            Element raiz = document.getDocumentElement();
            
            for (Cumpleanios cumpleanio : cumples) {
                //Item Node
                Element itemNode = document.createElement("item");
                //Nombre Node
                Element nombreNode = document.createElement("nombre");
                Text nodeNombreValue = document.createTextNode(cumpleanio.getNombre());
                nombreNode.appendChild(nodeNombreValue);
                //Apellidos Node
                Element apellNode = document.createElement("apellidos");
                Text nodeApellValue = document.createTextNode(cumpleanio.getApellidos());
                apellNode.appendChild(nodeApellValue);
                //Empresa Node
                Element empresaNode = document.createElement("empresa");
                Text nodeEmpresaValue = document.createTextNode(cumpleanio.getEmpresa());
                empresaNode.appendChild(nodeEmpresaValue);
                //Fecha Node
                Element fechaNode = document.createElement("fecha");
                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                String convertido = fecha.format(cumpleanio.getFecha());
                Text nodeFechaValue = document.createTextNode(convertido);
                fechaNode.appendChild(nodeFechaValue);
                //Imagen Node
                Element imageNode = document.createElement("empresa");
                Text nodeImageValue = document.createTextNode(cumpleanio.getEmpresa());
                imageNode.appendChild(nodeImageValue);
                
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(nombreNode);
                itemNode.appendChild(apellNode);
                itemNode.appendChild(empresaNode);
                itemNode.appendChild(fechaNode);
                
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("cumpleanios" + ".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenerarRSS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GenerarRSS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GenerarRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
