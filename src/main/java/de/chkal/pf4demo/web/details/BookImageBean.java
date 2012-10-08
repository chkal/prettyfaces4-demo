package de.chkal.pf4demo.web.details;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.Parameter;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;

import de.chkal.pf4demo.service.BookImageService;
import de.chkal.pf4demo.web.utils.ResponseUtils;

@Named
@RequestScoped
@Join(path = "/images/book/{isbn}.jpg", to = "/faces/home.xhtml")
public class BookImageBean
{

   @Parameter
   private Long isbn;

   @Inject
   private BookImageService bookImageService;

   @RequestAction
   @Deferred
   public void getImage() throws IOException
   {

      // load image from the database
      byte[] image = bookImageService.getBookImage(isbn);

      // send 404 for unknown books
      if (image == null) {
         ResponseUtils.sendError(404);
         return;
      }

      FacesContext facesContext = FacesContext.getCurrentInstance();
      HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

      // set content type and send image
      response.setContentType("image/jpg");
      response.getOutputStream().write(image);
      response.flushBuffer();

      // end JSF lifecycle
      facesContext.responseComplete();

   }

   public Long getIsbn()
   {
      return isbn;
   }

   public void setIsbn(Long isbn)
   {
      this.isbn = isbn;
   }

}
