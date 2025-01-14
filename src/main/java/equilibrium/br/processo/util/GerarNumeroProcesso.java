package equilibrium.br.processo.util;

import java.security.SecureRandom;

public class GerarNumeroProcesso {
  
    public static String gerarMatriculaProcesso() {
        SecureRandom random = new SecureRandom();  
        StringBuilder senha = new StringBuilder();
        
        // Gerar uma numero de 6 dígitos
        for (int i = 0; i < 6; i++) {
            // Gera um número aleatório entre 0 e 9
            int numeroAleatorio = random.nextInt(10);
            senha.append(numeroAleatorio); 
        }
        
        return senha.toString();
    }
}
