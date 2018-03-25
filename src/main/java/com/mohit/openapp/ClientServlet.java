package com.mohit.openapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		controller(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	void controller(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("Reqyest received keys " + request.getParameterMap().keySet());

		String method = request.getParameter("method");
		System.out.println("Request received for" + method);
		if (method == "registeruser") {
			registerUser(request, response);
		}

		if (method == "getAllUsers") {
			getAllUsers(request, response);
		}

	}

	public static void main(String[] args) throws Exception {

	}

	private static String getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		String result = "{}";
		try {
			con = ConnectionFactory.getConnection();
			Statement smt = con.createStatement();
			String sql = request.getParameter("query");
			System.out.println("Executing sql " + sql);
			ResultSet rs = smt.executeQuery("select sql_row_to_json('" + sql + "')");
			if (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(result);
		return result;
	}

	private static String registerUser(HttpServletRequest request, HttpServletResponse response) {
		String result = "SUCCESS";
		try {
			PrintWriter out;
			out = response.getWriter();
			System.out.println("inside registerUser");

			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				out.write("##########");
				out.write("paramName" + paramName);
				System.out.println("paramName" + paramName);
				String[] paramValues = request.getParameterValues(paramName);
				for (int i = 0; i < paramValues.length; i++) {
					String paramValue = paramValues[i];
					out.write("paramValue = " + paramValue);
					System.out.println("paramValue = " + paramValue);

				}
			}

			User user = new User();
			user.setUserid(request.getParameter("userid"));
			user.setUsername(request.getParameter("username"));
			user.setGender(request.getParameter("gender"));
			user.setCity(request.getParameter("city"));
			user.setAddress(request.getParameter("address"));
			user.setPassword(request.getParameter("password"));

			String strdob = request.getParameter("dob");
			System.out.println("strdob" + strdob);

			user.setDob(new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy")).parse(strdob).getTime()));
			user.setProfileid(Long.parseLong(request.getParameter("profileid")));

			System.out.println(user);
			DBUtils.saveUserInDB(user);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
