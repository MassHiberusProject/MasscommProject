/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.masscomm.common.ManageContrasenia;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BorradorPeticionesContrasenia implements ServletContextListener {

    private ScheduledExecutorService planificador;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        planificador = Executors.newSingleThreadScheduledExecutor();
        planificador.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int res= ManageContrasenia.delete();
                if(res!=-1){
                     Logger.getLogger(BorradorPeticionesContrasenia.class.getName()).log(Level.SEVERE, "Se está ejecutando el borrado de peticiones obsoletas {0}", res);
                }else{
                     Logger.getLogger(BorradorPeticionesContrasenia.class.getName()).log(Level.SEVERE, "No se ha ejecutado el borrado de peticiones obsoletas");
                }
            }
        }, 0, 30, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        planificador.shutdownNow();
    }
}
