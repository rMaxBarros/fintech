package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.TipoTransacaoDao;
import br.com.fiap.fintech.dao.TransacaoDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.TipoTransacao;
import br.com.fiap.fintech.model.Transacao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/transacoes")
public class TransacaoServlet extends HttpServlet {

    private TransacaoDao dao;
    private TipoTransacaoDao tipoDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }

    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));

        try {
            dao.removerTransacao(codigo);
            req.setAttribute("sucesso", "Transação removida.");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir.");
        }
        listar(req,resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double valor = Double.parseDouble(req.getParameter("valor"));
        LocalDate dataTransacao = LocalDate.parse(req.getParameter("data"));

        int codigoTipo = Integer.parseInt(req.getParameter("tipos"));

        TipoTransacao tipo = new TipoTransacao();
        tipo.setCodigoTipo(codigoTipo);

        Transacao transacao = new Transacao(0, nome, valor, dataTransacao);
        transacao.setTipoTransacao(tipo);

        try {
            dao.cadastrarTransacao(transacao);
            req.setAttribute("sucesso", "Transação cadastrada com sucesso.");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar transação.");
        }

        //req.getRequestDispatcher("cadastro-transacao.jsp").forward(req, resp);
        abrirFormTipo(req,resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate dataTransacao = LocalDate.parse(req.getParameter("data"));

            int cogigoTipo = Integer.parseInt(req.getParameter("tipos"));

            TipoTransacao tipo = new TipoTransacao();
            tipo.setCodigoTipo(cogigoTipo);

            Transacao transacao = new Transacao(codigo, nome, valor, dataTransacao);
            transacao.setTipoTransacao(tipo);
            dao.atualizarTransacao(transacao);

            req.setAttribute("sucesso", "Transação atualizada!");

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }

        listar(req,resp);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getTransacaoDao();
        tipoDao = DaoFactory.getTipoTransacaoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-transacao":
                abrirForm(req, resp);
                break;
            case "abrir-form-tipo":
                abrirFormTipo(req, resp);
                break;

        }
    }

    private void abrirFormTipo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        carregarOpcoesTipo(req);
        req.getRequestDispatcher("cadastro-transacao.jsp").forward(req, resp);
    }

    private void carregarOpcoesTipo(HttpServletRequest req) {
        List<TipoTransacao> lista = tipoDao.listar();
        req.setAttribute("tipos", lista);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Transacao transacao = dao.buscar(id);
        req.setAttribute("transacao", transacao);
        carregarOpcoesTipo(req);
        req.getRequestDispatcher("editar-transacao.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transacao> lista = dao.listar();
        req.setAttribute("transacoes", lista);

        req.getRequestDispatcher("lista-transacao.jsp").forward(req, resp);
    }
}
