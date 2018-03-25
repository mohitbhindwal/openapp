 <%@page import="com.mohit.openapp.ClientServlet"%>
<%@page import="com.sun.security.ntlm.Client"%>
<html>
   <body>
      <centre>
      <form action = "query.jsp" method = "POST">
      <textarea cols="100" rows="15" name="textquery" /><%= request.getParameter("textquery") %></textarea><br/>
          <input type = "submit" value = "Submit" />
          <input type = "submit" value = "JSON" />
          
      </form>
       <% 
          if( request.getParameter("textquery")!=null)
          response.getWriter().write(ClientServlet.query( request.getParameter("textquery")));
       
           %>
      </centre>
   </body>
</html>


