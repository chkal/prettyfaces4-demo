package de.chkal.pf4demo.web.utils;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {

    public static void sendError(int code) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.sendError(code);
            context.responseComplete();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
