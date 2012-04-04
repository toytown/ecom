package com.ecom.web.components.autocomplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Geolookup
 */
public class Geolookup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int count;

    private List<String> name;

    private String data = "Aman, Albela, Ali, Ankit, Azam, Aryan," +
            "Bhumi, Bharat, Bhaskar, Bigul, Chaman, Chaturvedi, Chatrapati," +
            "Chitra, Deno, Digboi, Deepak, Doremon, Elepahant, Eagle, Erose," +
            "Funny, Fun, Fruit, Galgotia, Ghosle, Gautam, Grish, Honda, Hari," +
            "Harsh, Irfan, India, Indu, John, Johny, Jyotsna, Karunya, Kirti," +
            "Koshla, Lion, Laugh, Leg, Lotus, Mohan, Marshal, Maurisus, Monaco," +
            "Neil, Nelson, Nurul, Omang, Oman, Ozone, Orient, Pawan, Puri, Pushkar," +
            "Quraishi, Qutar, Quarter, Ravindra, Rajesh," +
            "Rohit, Roshan, Sunil, Surat, Sah, Saurya, Trilok, Tiwari, Top, Torch" +
            "UK, USA, Uzbekistan, Vasant, Varun, Vipul, Vaidya, Wasim, Waquar," +
            "Xenon, X-Mas, Yemen, Yen, Yokohama, Zero, Zambia,Zimbabwe";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Geolookup() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config)
    {
        name = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");

        while (st.hasMoreTokens()) {
            name.add(st.nextToken().trim());
        }
        count = name.size();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        String nm = request.getParameter("term");
        String val = "[{\'label\': \'TEST-01\'}, {\'label\': \'TEST-02\'}, {\'label\': \'TEST-03\'}]";
        out.println(val);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    public List<String> getData(String nm) {
        String country = null;
        nm = nm.toLowerCase();
        List<String> equal = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            country = name.get(i).toLowerCase();
            if (country.startsWith(nm)) {
                equal.add(name.get(i));
            }
        }
        return equal;
    }
    
    protected String createJson(final Iterable<String> choices)
    {

        StringBuilder json = new StringBuilder();
        json.append('[');
        for (String choice : choices)
        {
            if (json.length() > 1)
            {
                json.append(',');
            }
            json.append('"').append(choice).append('"');
        }
        json.append(']');

        return json.toString();
    }    
}
