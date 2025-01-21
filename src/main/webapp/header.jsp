<nav class="navbar navbar-expand-lg mb-5" style="background-color: #F4F7F7;">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="./images/navbar-fintech.svg" alt="Logo" width="30" height="24"
                 class="d-inline-block align-text-top">
            Fintech
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="transacoes?acao=abrir-form-tipo">Cadastro</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="transacoes?acao=listar">Resumo</a>
                </li>
            </ul>

            <c:if test="${empty user}">

                <span class="navbar-text text-danger" style="margin-right:10px">
                        ${erro}
                </span>

                <form class="form-inline my-lg-0" action="login" method="post">
                    <div class="row">
                        <div class="col">
                            <input
                                    class="form-control mr-sm-2"
                                    type="text"
                                    name="email"
                                    placeholder="E-mail">
                        </div>
                        <div class="col">
                            <input
                                    class="form-control mr-sm-2"
                                    type="password"
                                    name="senha"
                                    placeholder="Senha">
                        </div>
                        <div class="col">
                            <button class="btn btn-outline-success my-2 my-sm-0"
                                    type="submit">Entrar
                            </button>
                        </div>
                    </div>
                </form>

            </c:if>

            <c:if test="${not empty user }">
               <span class="navbar-text">
                   ${user}
                   <a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
               </span>
            </c:if>

        </div>
    </div>
</nav>