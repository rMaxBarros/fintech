package br.com.fiap.fintech.model;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {

    private String email;
    private String senha;

    public Usuario(String email, String senha) {
        super();
        this.email = email;
        setSenha(senha);
    }

    private void setSenha(String senha) {
        try {
            this.senha = CriptografiaUtils.criptografar(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
}
