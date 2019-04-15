/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.io.IOException;
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
        } else if (acao.equalsIgnoreCase("alterar") || acao.equalsIgnoreCase("alterar2")){
            alterarCliente(request, response);
        } else if (acao.equalsIgnoreCase("inserirNovo")){
            inserirNovoCliente(request, response);
        } else if (acao.equalsIgnoreCase("alterar_senha")){
            alterarSenhaCliente(request, response);
        } else if (acao.equalsIgnoreCase("telaalterar2")) {
            carregaTelaAlterarCliente(request, response);
        }
        
    }
    
    public void alterarClienteTela(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("cod_cliente"));
        ClienteBean clienteBean;
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteBean = clienteAcessoDados.verificaUsuario(codigo);
        request.setAttribute("cod_cliente", clienteBean.getCod_cliente());
        request.setAttribute("nome", clienteBean.getNome());
        request.setAttribute("email", clienteBean.getEmail());
        request.setAttribute("endereco", clienteBean.getEndereco());
        request.setAttribute("cep", clienteBean.getCep());
        request.setAttribute("cidade", clienteBean.getCidade());
        request.setAttribute("uf", clienteBean.getUf());
        request.setAttribute("contato", clienteBean.getContato());
        request.setAttribute("data_nascimento", clienteBean.getData_nascimento());
        request.setAttribute("sexo", clienteBean.getSexo());
        RequestDispatcher requestDispactcher = request.getRequestDispatcher("admin_cliente_alterar.jsp");
        requestDispactcher.forward(request, response);
    }
    
     public void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("cod_cliente"));
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteAcessoDados.deletarCliente(codigo);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void inserirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setNome(request.getParameter("nome"));
        clienteBean.setEmail(request.getParameter("email"));
        clienteBean.setEndereco(request.getParameter("endereco"));
        clienteBean.setCep(request.getParameter("cep"));
        clienteBean.setCidade(request.getParameter("cidade"));
        clienteBean.setUf(request.getParameter("uf"));
        clienteBean.setContato(request.getParameter("contato"));
        clienteBean.setData_nascimento(request.getParameter("data_nascimento"));
        clienteBean.setSexo(request.getParameter("sexo").charAt(0));
        clienteBean.setSenha(request.getParameter("senha"));
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteAcessoDados.inserirCliente(clienteBean);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void alterarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setCod_cliente(Integer.parseInt(request.getParameter("cod_cliente")));
        clienteBean.setNome(request.getParameter("nome"));
        clienteBean.setEmail(request.getParameter("email"));
        clienteBean.setEndereco(request.getParameter("endereco"));
        clienteBean.setCep(request.getParameter("cep"));
        clienteBean.setCidade(request.getParameter("cidade"));
        clienteBean.setUf(request.getParameter("uf"));
        clienteBean.setContato(request.getParameter("contato"));
        clienteBean.setData_nascimento(request.getParameter("data_nascimento"));
        clienteBean.setSexo(request.getParameter("sexo").charAt(0));
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteAcessoDados.alterarCliente(clienteBean);
        response.sendRedirect("AdminServlet?acao=cliente");
    }
     
     public void inserirNovoCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setNome(request.getParameter("nome"));
        clienteBean.setEmail(request.getParameter("email"));
        clienteBean.setEndereco(request.getParameter("endereco"));
        clienteBean.setCep(request.getParameter("cep"));
        clienteBean.setCidade(request.getParameter("cidade"));
        clienteBean.setUf(request.getParameter("uf"));
        clienteBean.setContato(request.getParameter("contato"));
        clienteBean.setData_nascimento(request.getParameter("data_nascimento"));
        clienteBean.setSexo(request.getParameter("sexo").charAt(0));
        clienteBean.setSenha(request.getParameter("senha"));
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteAcessoDados.inserirCliente(clienteBean);
        response.sendRedirect("login.jsp");
     }
    
    public void alterarSenhaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sessao = request.getSession(false);
        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setCod_cliente(Integer.parseInt(sessao.getAttribute("cod_cliente")+""));
        clienteBean.setSenha(request.getParameter("senha"));
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteAcessoDados.alterarSenhaCliente(clienteBean);
        response.sendRedirect("cliente_alterar.jsp");
    }
    
    public void carregaTelaAlterarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession(false);
            
        int codigo = Integer.parseInt(sessao.getAttribute("cod_cliente")+"");
        ClienteBean clienteBean;
        ClienteDAO clienteAcessoDados = new ClienteDAO();
        clienteBean = clienteAcessoDados.verificaUsuario(codigo);
        request.setAttribute("cod_cliente", clienteBean.getCod_cliente());
        request.setAttribute("nome", clienteBean.getNome());
        request.setAttribute("email", clienteBean.getEmail());
        request.setAttribute("endereco", clienteBean.getEndereco());
        request.setAttribute("cep", clienteBean.getCep());
        request.setAttribute("cidade", clienteBean.getCidade());
        request.setAttribute("uf", clienteBean.getUf());
        request.setAttribute("contato", clienteBean.getContato());
        request.setAttribute("data_nascimento", clienteBean.getData_nascimento());
        request.setAttribute("sexo", clienteBean.getSexo());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("cliente_alterar.jsp");
        requestDispatcher.forward(request, response);
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
