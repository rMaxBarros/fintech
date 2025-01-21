<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastro de Transações</title>
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
        CADASTRO DE TRANSAÇÕES
      </div>

      <c:if test="${not empty sucesso}">
        <div class="alert alert-success ms-2 me-2 mt-2">${sucesso}</div>
      </c:if>

      <c:if test="${not empty erro}">
        <div class="alert alert-danger ms-2 me-2 mt-2">${erro}</div>
      </c:if>

      <div class="card-body">
        <form action="transacoes?acao=cadastrar" method="post">
          <div class="form-group mb-2">
            <label for="id-nome">Nome da transação</label>
            <input type="text" name="nome" id="id-nome" class="form-control">
          </div>
          <div class="form-group mb-2">
            <label for="id-valor">Valor da transação</label>
            <input type="text" name="valor" id="id-valor" class="form-control">
          </div>
          <div class="form-group mb-2">
            <label for="id-data">Data da transação</label>
            <input type="date" name="data" id="id-data" class="form-control">
          </div>

          <div class="form-group">
            <label
                    for="id-tipos">Tipos de Transação
            </label>
            <select
                    name="tipos"
                    id="id-tipos"
                    class="form-control"
            >
              <option value="0">Selecione</option>

              <c:forEach items="${tipos}" var="c">
                <option value="${c.codigoTipo }" >${c.nomeTipo }</option>
              </c:forEach>

            </select>
          </div>

          <input type="submit" value="Cadastrar movimentação" class="btn btn-primary mt-3">
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Inclusão do footer-->
<%@include file="footer.jsp"%>
</body>
</html>