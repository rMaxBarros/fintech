<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Edição de Transações</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<!-- Inclusão do header-->
<%@include file="header.jsp"%>

<!-- Inclusão do corpo da página de resumo dos cadastros-->
<div class="container">

  <div class="mt-5 ms-5 me-5">

    <div class="card mb-3">
      <div class="card-header">
        ATUALIZAR TRANSAÇÕES
      </div>

      <c:if test="${not empty sucesso}">
        <div class="alert alert-success ms-2 me-2 mt-2">${sucesso}</div>
      </c:if>

      <c:if test="${not empty erro}">
        <div class="alert alert-danger ms-2 me-2 mt-2">${erro}</div>
      </c:if>

      <div class="card-body">
        <form action="transacoes?acao=editar" method="post">

          <input type="hidden" value="${transacao.codigo}" name="codigo">

          <div class="form-group mb-2">
            <label for="id-nome">Nome da transação</label>
            <input type="text" name="nome" id="id-nome" class="form-control" value="${transacao.nome}">
          </div>

          <div class="form-group mb-2">
            <label for="id-valor">Valor da transação</label>
            <input type="text" name="valor" id="id-valor" class="form-control" value="${transacao.valor}">
          </div>

          <div class="form-group mb-2">
            <label for="id-data">Data da transação</label>
            <input type="date" name="data" id="id-data" class="form-control" value="${transacao.dataTransacao}">
          </div>

          <div class="form-group">
            <label for="id-tipos">Tipo</label>
            <select name="tipos" id="id-tipos" class="form-control">
              <option value="0">Selecione</option>
              <c:forEach items="${ tipos }" var="c">
                <c:if test="${ c.codigoTipo == transacao.tipoTransacao.codigoTipo }">
                  <option value="${ c.codigoTipo }" selected>${ c.nomeTipo }</option>
                </c:if>
                <c:if test="${ c.codigoTipo != transacao.tipoTransacao.codigoTipo }">
                  <option value="${ c.codigoTipo }">${ c.nomeTipo }</option>
                </c:if>
              </c:forEach>
            </select>
          </div>

          <input type="submit" value="Salvar" class="btn btn-primary mt-3">
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Inclusão do footer-->
<%@include file="footer.jsp"%>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>