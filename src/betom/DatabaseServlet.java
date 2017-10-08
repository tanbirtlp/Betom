package betom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
        
		String n=request.getParameter("name");  
        String p=request.getParameter("phone");
        String em=request.getParameter("email");
        String m=request.getParameter("message"); 
		
		
		
		boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
		
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "betomdB";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "123456";
        
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);

            pst = conn.prepareStatement("insert into incommingMsg values(?,?,?,?)");
            
            pst.setString(1, n);
            pst.setString(2, p);
            pst.setString(3, em);
            pst.setString(4, m);
            
            
            
            
            /*pst.setString(1, name);
            */

            rs = pst.executeQuery();
            status = rs.next();
            
            if(status != false){
                out.println("<br>Record has been inserted");
                }
                else{
                out.println("failed to insert the data");
                }
            

        }
        
        catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		/*PrintWriter pw = response.getWriter();
		String connectionURL = "jdbc:mysql://localhost/zulfiqar";
		Connection connection;*/
		
		
		
		
		
		
	}

}
