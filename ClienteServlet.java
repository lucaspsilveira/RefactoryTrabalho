/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClienteBean;

/**
 *
 * @author lucas
 */
public class ClienteServlet extends HttpServlet {

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
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao.equalsIgnoreCase("excluir")) {
            excluirCliente(request, response);
        } else if (acao.equalsIgnoreCase("telaalterar")) {
            alterarClienteTela(request, response);
        } else if (acao.equalsIgnoreCase("inserir")){
            inserirCliente(request, response);
        } else if (acao.equalsIgnoreCase("alterar")){
            alterarCliente(request, response);
        } else if (acao.equalsIgnoreCase("inserirNovo")){
            inserirNovoCliente(request, response);
        } else if (acao.equalsIgnoreCase("alterar_senha")){
            HttpSession sessao = request.getSession(false);
            ClienteBean c = new ClienteBean();
            c.setCod_cliente(Integer.parseInt(sessao.getAttribute("cod_cliente")+""));
            c.setSenha(request.getParameter("senha"));
            ClienteDAO dao = new ClienteDAO();
            dao.alterarSenhaCliente(c);
            response.sendRedirect("cliente_alterar.jsp");
        } else if (acao.equalsIgnoreCase("telaalterar2")) {
            HttpSession sessao = request.getSession(false);
            
            int codigo = Integer.parseInt(sessao.getAttribute("cod_cliente")+"");
            ClienteBean c;
            ClienteDAO dao = new ClienteDAO();
            c = dao.verificaUsuario(codigo);
            request.setAttribute("cod_cliente", c.getCod_cliente());
            request.setAttribute("nome", c.getNome());
            request.setAttribute("email", c.getEmail());
            request.setAttribute("endereco", c.getEndereco());
            request.setAttribute("cep", c.getCep());
            request.setAttribute("cidade", c.getCidade());
            request.setAttribute("uf", c.getUf());
            request.setAttribute("contato", c.getContato());
            request.setAttribute("data_nascimento", c.getData_nascimento());
            request.setAttribute("sexo", c.getSexo());
            RequestDispatcher rd = request.getRequestDispatcher("cliente_alterar.jsp");
            rd.forward(request, response);
        } else if (acao.equalsIgnoreCase("alterar2")){
            ClienteBean c = new ClienteBean();
            c.setCod_cliente(Integer.parseInt(request.getParameter("cod_cliente")));
            c.setNome(request.getParameter("nome"));
            c.setEmail(request.getParameter("email"));
            c.setEndereco(request.getParameter("endereco"));
            c.setCep(request.getParameter("cep"));
            c.setCidade(request.getParameter("cidade"));
            c.setUf(request.getParameter("uf"));
            c.setContato(request.getParameter("contato"));
            c.setData_nascimento(request.getParameter("data_nascimento"));
            c.setSexo(request.getParameter("sexo").charAt(0));
            ClienteDAO dao = new ClienteDAO();
            dao.alterarCliente(c);
            response.sendRedirect("index.jsp");
        }
        
    }
    
    public void alterarClienteTela(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("cod_cliente"));
            ClienteBean c;
            ClienteDAO dao = new ClienteDAO();
            c = dao.verificaUsuario(codigo);
            request.setAttribute("cod_cliente", c.getCod_cliente());
            request.setAttribute("nome", c.getNome());
            request.setAttribute("email", c.getEmail());
            request.setAttribute("endereco", c.getEndereco());
            request.setAttribute("cep", c.getCep());
            request.setAttribute("cidade", c.getCidade());
            request.setAttribute("uf", c.getUf());
            request.setAttribute("contato", c.getContato());
            request.setAttribute("data_nascimento", c.getData_nascimento());
            request.setAttribute("sexo", c.getSexo());
            RequestDispatcher rd = request.getRequestDispatcher("admin_cliente_alterar.jsp");
            rd.forward(request, response);
    }
    
     public void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("cod_cliente"));
        ClienteDAO dao = new ClienteDAO();
        dao.deletarCliente(codigo);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void inserirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean c = new ClienteBean();
        c.setNome(request.getParameter("nome"));
        c.setEmail(request.getParameter("email"));
        c.setEndereco(request.getParameter("endereco"));
        c.setCep(request.getParameter("cep"));
        c.setCidade(request.getParameter("cidade"));
        c.setUf(request.getParameter("uf"));
        c.setContato(request.getParameter("contato"));
        c.setData_nascimento(request.getParameter("data_nascimento"));
        c.setSexo(request.getParameter("sexo").charAt(0));
        c.setSenha(request.getParameter("senha"));
        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(c);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void alterarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean c = new ClienteBean();
        c.setCod_cliente(Integer.parseInt(request.getParameter("cod_cliente")));
        c.setNome(request.getParameter("nome"));
        c.setEmail(request.getParameter("email"));
        c.setEndereco(request.getParameter("endereco"));
        c.setCep(request.getParameter("cep"));
        c.setCidade(request.getParameter("cidade"));
        c.setUf(request.getParameter("uf"));
        c.setContato(request.getParameter("contato"));
        c.setData_nascimento(request.getParameter("data_nascimento"));
        c.setSexo(request.getParameter("sexo").charAt(0));
        ClienteDAO dao = new ClienteDAO();
        dao.alterarCliente(c);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void inserirNovoCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean c = new ClienteBean();
        c.setNome(request.getParameter("nome"));
        c.setEmail(request.getParameter("email"));
        c.setEndereco(request.getParameter("endereco"));
        c.setCep(request.getParameter("cep"));
        c.setCidade(request.getParameter("cidade"));
        c.setUf(request.getParameter("uf"));
        c.setContato(request.getParameter("contato"));
        c.setData_nascimento(request.getParameter("data_nascimento"));
        c.setSexo(request.getParameter("sexo").charAt(0));
        c.setSenha(request.getParameter("senha"));
        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(c);
        response.sendRedirect("login.jsp");
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
            throws ServletException, IOException {
        processRequest(request, response);
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

}
