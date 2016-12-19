/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import business.Operation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xiaowen Li
 */
public class CalcSrv extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        // get the information
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operator = request.getParameter("operator");

        PrintWriter out = response.getWriter();

        // validate the data
        if (num1 == null || num1.trim().length() == 0)
        {
            out.print("The number 1 cannot be empty. Please enter number 1.");
            RequestDispatcher rd1 = request.getRequestDispatcher("/myhome.html");
            rd1.include(request, response);
        } else if (num2 == null || num2.trim().length() == 0) // trim() is to remove spaces
        {
            out.print("The number 2 cannot be empty. Please enter number 2.");
            RequestDispatcher rd2 = request.getRequestDispatcher("/myhome.html");
            rd2.include(request, response);
        } else if (operator.trim().length() == 0) // trim() is to remove spaces
        {
            out.print("The operator cannot be empty. Please select operator.");
            RequestDispatcher rd3 = request.getRequestDispatcher("/myhome.html");
            rd3.include(request, response);
        } else
        {
            Operation o = new Operation(Integer.parseInt(num1), Integer.parseInt(num2), operator);

            ArrayList<String> myPrev = null;
            
            // implement session tracking
            HttpSession mySession = request.getSession();

            myPrev = (ArrayList<String>) mySession.getAttribute("prev");
            
            if (myPrev == null)
            {
                myPrev = new ArrayList<String>();
            }
            
            synchronized (myPrev)
            {
                myPrev.add(o.getN1() + " " + o.getOperator() + " " + o.getN2() + " = " + o.calculate());
                mySession.setAttribute("prev", myPrev);
            }
            if (myPrev != null)
            {
                out.print("<h3>The previous operations performed</h3><p>");
                for (int i = 0; i < myPrev.size() - 1; i++)
                {
                    out.println("<p>" + myPrev.get(i) + "</p>");
                }
                out.print("----------------------------------------------------");
            }
            out.print("<h3>The recent operations performed</h3>"
                    + o.getN1() + " " + o.getOperator() + " " + o.getN2() + " = " + o.calculate());
            out.print("<p><a href='myhome.html'>Another operation</a></p>");


        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
