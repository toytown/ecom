package com.ecom.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
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

/**
 * Servlet implementation class Geolookup
 */
public class Geolookup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplicationContext applicationContext = null;
    
    private GeoLocationService geoLocationService;
    

    public Geolookup() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        geoLocationService = (GeoLocationService) applicationContext.getBean("geoLocationService");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String term = request.getParameter("term");
        Gson gson = new Gson();
        String json = gson.toJson(findCityorZip(term));
        out.println(json);
    }

    public Set<String> findCityorZip(String term) {
        
        if (Strings.isEmpty(term)) {
            return null;
        }

        Set<String> choices = new HashSet<String>(10);
        Iterator<GeoLocation> iter = geoLocationService.findByZipOrCity(term).iterator();
        while (iter.hasNext()) {
            GeoLocation geoLoc = iter.next();

            if (choices.size() == 10) {
                break;
            }

            choices.add(geoLoc.getZipAndCity());
        }

      
        return choices;        
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
