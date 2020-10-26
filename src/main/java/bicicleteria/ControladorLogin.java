package bicicleteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorLogin extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void procesarEntrada(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String user  = request.getParameter("usuario");
        String psswd = request.getParameter("password");
        ModeloLogin m = new ModeloLogin();
        m.addExceptionListener(new ExceptionListener());
        Persona p = m.autenticar(user, psswd);
        RequestDispatcher v = null;
        try {
            request.setAttribute("recursos", p.getRecursos());
            request.setAttribute("nombre", p.getNombre());
            v = request.getRequestDispatcher(p.getVista());
        } catch (NullPointerException ex) {
            request.setAttribute("mensajeError", "No existe usuario/contraseña");
            v = request.getRequestDispatcher("error.jsp");
        } finally {
            v.forward(request, response);
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
            throws ServletException, IOException {
        procesarEntrada(request, response);
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
            throws ServletException, IOException {
        procesarEntrada(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private class ExceptionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String exception = e.getActionCommand();
                request.setAttribute("mensajeError", exception);
                RequestDispatcher v =  request.getRequestDispatcher("error.jsp");
                v.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
