
package org.esca.app.util;

import java.security.SecureRandom;

public class GeradorSenha {
    private final String pPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    
    public String gerarSenha() {
        StringBuilder senha = new StringBuilder();

        while (true) {
            senha.setLength(0); // Limpar a senha anterior

            // Gerar uma senha aleatória com letras maiúsculas, minúsculas, dígitos e caracteres especiais
            for (int i = 0; i < 12; i++) {
                char ch = gerarCaractereAleatorio();
                senha.append(ch);
            }

            // Verificar se a senha atende aos critérios
            if (senha.toString().matches(pPassword)) {
                return senha.toString();
            }
        }
    }
    
    private char gerarCaractereAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789#?!@$%^&*-";
        int index = (int) (Math.random() * caracteres.length());
        return caracteres.charAt(index);
    }

}
