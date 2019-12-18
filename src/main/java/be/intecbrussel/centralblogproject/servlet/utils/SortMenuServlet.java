package be.intecbrussel.centralblogproject.servlet.utils;

import be.intecbrussel.centralblogproject.model.Post;
import be.intecbrussel.centralblogproject.service.VisitorServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/postsort")
public class SortMenuServlet extends HttpServlet {
    // Servlet instance variables are not thread safe, but considering this is a constant there's less immediate danger.
    final int FACTOR = 6;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirecting doGet to doPost is considered bad practice and has no purpose in this situation.
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VisitorServicesImpl visitorServices = new VisitorServicesImpl();
        HttpSession session = req.getSession();

        /* Sorting the list to be showed on homepage.
         * Two Session Attributes is needed for the check.(PostTOShow and multiplier)
         * We Check First wich Parameter is trigger from the Index.Jsp.
         * If no trigger,the list wil reset and the multiplier also to 1
         * If trigger is happend Than we Set the actual session postList in the the visitorServices
         * and the sort is start and back set to the session Atribute
         * */

        /*
         * Can this be more efficient and cleaner by having the dropdown sort menu link to seperate servlets?
         * */

        int multiplier = (Integer) session.getAttribute("multiplier");
        List<Post> sessionPostList = (List<Post>) session.getAttribute("postsToShow");
        visitorServices.setPosts(sessionPostList);

        if (req.getParameter("showmore") != null) {
            multiplier++;
            List<Post> postList = visitorServices.getSixMorePosts(multiplier);
            session.setAttribute("postsToShow", postList);
            session.setAttribute("multiplier", multiplier);
        } else if (req.getParameter("mostpopular") != null) {
            List<Post> postList = visitorServices.sortPostsByPopularity(multiplier * FACTOR, sessionPostList);
            session.setAttribute("postsToShow", postList);
        } else if (req.getParameter("date") != null) {
            List<Post> postList = visitorServices.sortPostsByDate(multiplier * FACTOR, sessionPostList);
            session.setAttribute("postsToShow", postList);
        } else if (req.getMethod().equals("GET")) {
            multiplier = 1;
            session.setAttribute("postsToShow", visitorServices.getSixMorePosts(multiplier));
            session.setAttribute("multiplier", multiplier);
        }

        req.getRequestDispatcher("WEB-INF/pages/home/index.jsp").forward(req, resp);
    }
}
