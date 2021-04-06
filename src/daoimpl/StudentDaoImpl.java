package daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import dao.StudentDao;
import models.Student;
import util.Database;

public class StudentDaoImpl implements StudentDao{
	
	Connection conn = Database.getConnection();

	@Override
	public Integer addStudent(Student student) {
		Integer row = null;
        try {
            
            
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student(`name`,email,contact,gender,country) VALUES(?,?,?,?,?)");
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getContact());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getCountry());
           
           
                        
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
	}

	@Override
	public Integer updateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getStudentIdByName(String studentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

}
