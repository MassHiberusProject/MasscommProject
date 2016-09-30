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
import com.masscomm.common.Cumpleanios;
import com.masscomm.common.ManageCumpleanios;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.Item;
import org.apache.commons.digester.rss.RSSDigester;
import org.apache.commons.digester.rss.Image;

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

        if (cod != null) {
            StringTokenizer st = new StringTokenizer(cod, ",");
            String aux;
            while (st.hasMoreTokens()) {
                aux = st.nextToken();
                codigos.add(aux);
            }
        }

        List<Cumpleanios> cumpleanios = new ArrayList<Cumpleanios>();
        for (int i = 0; i < codigos.size(); i++) {
            int co = Integer.parseInt(codigos.get(i).trim());
            Cumpleanios c = ManageCumpleanios.read(co);
            cumpleanios.add(c);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z");
        String today = formatter.format(new Date());

        Channel newChannel = new Channel();
        newChannel.setCopyright("(c)Real Gagnon 2007");
        newChannel.setDescription("Useful Java code examples");
        newChannel.setLink("http://www.rgagnon.com/howto.html");
        newChannel.setLanguage("es");
        newChannel.setPubDate(today);

        List<Item> listaItems = new ArrayList<Item>();
        List<Image> listaImages = new ArrayList<Image>();
        

        for (int i = 0; i < cumpleanios.size(); i++) {
            Item item = new Item();
            item.setTitle("Real's HowTo");
            item.setLink("http://www.rgagnon.com/java-details/");
            item.setDescription("Cool java snippet!");
            
            Image im = new Image();
            im.setURL("http://localhost:8081/Masscomm/img/"+cumpleanios.get(i).getImagen());
            listaItems.add(item);
            listaImages.add(im);
            
        }

        
        
        newChannel.setPubDate(today);
       
        for (int i = 0; i < listaItems.size(); i++) {
            newChannel.addItem(listaItems.get(i));
        }
        for (int i = 0; i < listaImages.size(); i++) {
             newChannel.setImage(listaImages.get(i));
        }

        try {
            FileOutputStream fout = new FileOutputStream("C:/Users/claencina/Desktop/feed.xml");
            newChannel.render(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
