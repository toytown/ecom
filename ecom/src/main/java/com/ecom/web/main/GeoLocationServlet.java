package com.ecom.web.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.util.string.Strings;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ecom.domain.GeoLocation;
import com.ecom.service.interfaces.GeoLocationService;
import com.google.gson.Gson;

public class GeoLocationServlet extends HttpServlet {

    private ApplicationContext applicationContext = null;

    private GeoLocationService geoLocationService = null;
    
    
    @Override
    public void init() {
    
        if (applicationContext == null){
            System.out.println("setting context in get");
            applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        }
        
        if (applicationContext != null && applicationContext.containsBean("geoLocationService")){
            this.geoLocationService = (GeoLocationService) applicationContext.getBean("geoLocationService");
        }        
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse  response)
            throws IOException, ServletException {
        
            String cityOrzip = request.getParameter("city");
            List<String> returnList = new ArrayList<String>();
            
            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (Strings.isEmpty(cityOrzip)) {
                returnList = Collections.emptyList();                
            }

            Set<String> choices = new HashSet<String>(10);
            Iterator<GeoLocation> iter = geoLocationService.findByZipOrCity(cityOrzip).iterator();

            while (iter.hasNext()) {
                GeoLocation geoLoc = iter.next();

                if (choices.size() == 10) {
                    break;
                }

                choices.add(geoLoc.getCity());
            }

            returnList.addAll(choices);
            
            String json = gson.toJson(choices);
            System.out.println("JSON ---> " + json);
            response.getWriter().write(json);

            
        }

        public  void doPost(HttpServletRequest request, HttpServletResponse  response)
            throws IOException, ServletException {

            doGet(request, response);
        }

    
}
