package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.StudentDao;
import daoimpl.StudentDaoImpl;
import models.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StudentDao studentDaoImpl = new StudentDaoImpl();

	/**
	 * Default constructor.
	 */
	public StudentServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println("Action  "+action);
		
		Integer result = null;
		Gson gson = new Gson();
		
		Student student = null;
		String studentJson = null;

		switch (action) {
		case "create":
			
			student = new Student();
			
			student.setName(request.getParameter("name"));
			student.setEmail(request.getParameter("email"));
			student.setContact(request.getParameter("contact"));
			student.setGender(request.getParameter("gender"));
			student.setCountry(request.getParameter("country"));
			
			result = studentDaoImpl.addStudent(student);
			
			Student student2 = studentDaoImpl.getStudentById(studentDaoImpl.getStudentIdByName(student.getName()));
			response.setContentType("javascript/json");
			studentJson = gson.toJson(student2);
			
			response.getWriter().print(studentJson);

			break;
		case "update":
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Update ID : "+id);
			student = studentDaoImpl.getStudentById(id); 
			
			student.setName(request.getParameter("name"));
			student.setEmail(request.getParameter("email"));
			student.setContact(request.getParameter("contact"));
			student.setGender(request.getParameter("gender"));
			student.setCountry(request.getParameter("country"));
			
			result = studentDaoImpl.updateStudent(student);
			response.setContentType("javascript/json");
			studentJson = gson.toJson(student);
			
			response.getWriter().print(studentJson);
			
			
			break;
		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
			result = studentDaoImpl.deleteStudent(id);
			
			
			
			break;
		case "getAll":
			
			response.setContentType("javascript/json");
			List<Student> allStudent = studentDaoImpl.getAllStudent();
			
			
			String jsonList = gson.toJson(allStudent);
			
			response.getWriter().print(jsonList);
			
			
			break;
			
			
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
