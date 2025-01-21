<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fintech - Max Barros</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<!-- Inclusão do header-->
<%@include file="header.jsp"%>

<!-- Inclusão do corpo da página de resumo dos cadastros-->
<div class="container text-center">

    <div class="mt-5 ms-5 me-5">
        <h1>Seja bem vindo(a) ao Fintech!</h1>
    </div>

    <div class="container">
        <div class="card w-50 mt-5 ms-3">
            <div class="card-body">
                <h5 class="card-title"> <img src="./images/navbar-fintech.svg" class="" alt=""> Seja bem vindo(a) ao Fintech!</h5>
                <p class="card-text">Aqui você encontra as melhores formas de cadastrar todas as suas informações de finanças e controla tudo em um único lugar.</p>
                <a href="transacoes?acao=abrir-form-tipo" class="btn btn-primary">Ir para a tela de cadastro</a>
            </div>
        </div>
    </div>

</div>

<!-- Inclusão do footer-->
<%@include file="footer.jsp"%>
</body>
</html>