package oop.servlet.feedback;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.services.FeedbackIMPL;
import oop.services.IFeedback;

/**
 * Servlet implementation class DeleteFeedbackServlet
 */
public class DeleteFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFeedbackServlet() {
        super();
    }

	/**
	 * This method called by AdminPanel.jsp by Show Feedbacks button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Admin_FeedbackList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by ListFeedbackes.jsp by Show Delete Feedback button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String feedbackID = request.getParameter("feedbackID");
		
		IFeedback iFeedback = new FeedbackIMPL();
		
		iFeedback.removeFeedback(feedbackID);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFeedbacks.jsp");
		dispatcher.forward(request, response);
	}

}
