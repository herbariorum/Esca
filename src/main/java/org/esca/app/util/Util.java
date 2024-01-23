package org.esca.app.util;


import org.esca.app.auth.dominio.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Util {
    private final String pPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    Pattern regex;

    public boolean isValidPwd(String pwd) {
        this.regex = Pattern.compile(pPassword);
        Matcher m = this.regex.matcher(pwd);
        return m.matches();
    }

    public String gerarPassword(String pwd) {
        return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String pwd, Usuarios f) {
        boolean matched = BCrypt.checkpw(pwd, f.getPassword());
        return matched;
    }

    public LocalDate dateToLocalDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = null;
        try {
             ld = LocalDate.parse(value, formatter);
        } catch (Exception e) {
            // Trate a exceção (por exemplo, imprima uma mensagem de erro ou faça algo apropriado para sua aplicação)

        }
        return ld;
    }
    
    
}
