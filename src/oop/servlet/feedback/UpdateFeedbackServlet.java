package oop.servlet.feedback;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Feedback;
import oop.services.FeedbackIMPL;
import oop.services.IFeedback;

/**
 * Servlet implementation class UpdateFeedbackServlet
 */
public class UpdateFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFeedbackServlet() {
        super();
    }

	/**
	 * This method called by ListFeedbacks.jsp by EditFeedback button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String feedbackID = request.getParameter("feedbackID");
		
		IFeedback iFeedback = new FeedbackIMPL();
		Feedback feedback = iFeedback.getFeedbackByID(feedbackID);
		
		request.setAttribute("feedback", feedback);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/EditFeedback.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by EditFeedback.jsp by Update button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		
		IFeedback iFeedback = new FeedbackIMPL();
		Feedback feedback = new Feedback();
		
		String feedbackID = request.getParameter("feedbackID");
		
		//Set parameters to object parameters
		feedback.setFeedbackID(feedbackID);
		feedback.setCustomerID(request.getParameter("customerID"));
		feedback.setDate(request.getParameter("date"));
		feedback.setMessage(request.getParameter("message"));
		
		iFeedback.updateFeedback(feedbackID, feedback);
		
		request.setAttribute("feedback", feedback);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFeedbacks.jsp");
		dispatcher.forward(request, response);
	}
	

}
