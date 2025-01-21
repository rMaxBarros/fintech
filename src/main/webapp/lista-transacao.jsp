<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Transações</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<!-- Inclusão do header-->
<%@include file="header.jsp" %>

<!-- Inclusão do corpo da página de resumo dos cadastros-->
<div class="container text-center">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                Movimentações cadastradas
            </div>
            <div class="card-body">
                <h5 class="card-title">Aqui você encontra todas as suas movimentações detalhadamente.</h5>
                <p class="card-text">Matenha o controle de suas finanças!</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Nome da transação</th>
                        <th class="class-end">Valor</th>
                        <th class="class-center">Data</th>
                        <th>Tipo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${transacoes}" var="transacao">
                        <tr>
                            <td>${transacao.nome}</td>
                            <td class="class-end">${transacao.valor}</td>
                            <td class="class-center">
                                <fmt:parseDate
                                        value="${transacao.dataTransacao}"
                                        pattern="yyyy-MM-dd"
                                        var="dataFabricacaoFmt"/>

                                <fmt:formatDate
                                        value="${dataFabricacaoFmt}"
                                        pattern="dd/MM/yyyy"/>
                            </td>

                            <td>${transacao.tipoTransacao.nomeTipo}</td>
                            <!-- Botão de editar-->
                            <td class="text-center">
                                <c:url value="transacoes" var="link">
                                    <c:param name="acao"
                                             value="abrir-form-transacao"/> <!-- http://servidor?acao=abrir-form-transacao -->
                                    <c:param name="codigo" value="${transacao.codigo}"/>
                                </c:url>
                                <a href="${link}" class="btn btn-primary">Editar</a>

                                <!-- Botão excluir -->
                                <button
                                        type="button"
                                        class="btn btn-danger"
                                        data-bs-toggle="modal"
                                        data-bs-target="#excluirModal"
                                        onclick="codigoExcluir.value = ${transacao.codigo}"
                                >
                                    Excluir
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="transacoes?acao=abrir-form-tipo" class="btn btn-primary">Cadastrar movimentação</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div
        class="modal fade"
        id="excluirModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="exampleModalLabel">
                    Confirmar Exclusão
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <h4>Você confirma a exclusão desta transação?</h4>
                <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
            </div>
            <div class="modal-footer">

                <form action="transacoes" method="post">
                    <input
                            type="hidden"
                            name="acao"
                            value="excluir">
                    <input
                            type="hidden"
                            name="codigoExcluir"
                            id="codigoExcluir">
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Não
                    </button>
                    <button
                            type="submit"
                            class="btn btn-danger">
                        Sim
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<%--    fim modal--%>

<!-- Inclusão do footer-->
<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>