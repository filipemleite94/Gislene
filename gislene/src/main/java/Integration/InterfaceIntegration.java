package Integration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InterfaceIntegration")
public class InterfaceIntegration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InterfaceIntegration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topicName = request.getParameter("topicName");
		String ret;
		if (topicName!=null&&topicName.equalsIgnoreCase("opa"))ret = "{\"topic:\"{\"name\":\"JAVA\",\"tutorial\":[{\"name\":\"equals method\"}]}}";
		else ret = "{\"topic:\"{\"name\":\"C++\",\"tutorial\":[{\"name\":\"equals method\"}]}}";
		response.setContentType("text/html");
		response.getWriter().write(ret);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
