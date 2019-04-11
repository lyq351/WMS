package com.jk.wms.util.getvcode;



import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetValidateCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 8244305529216943035L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OutputStream os = response.getOutputStream();
        VCodeGenerator vg = new VCodeGenerator(os);
        String vcode = vg.drawCode();
        os.close();

        request.getSession().setAttribute("vcode", vcode);
    }

}
