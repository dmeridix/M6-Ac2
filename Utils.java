import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("S'ha cridat massa cops Entrada.readLine()");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean respostaABoolean(String resposta) {
        if (null == resposta) {
            return false;
        }
        resposta = resposta.toLowerCase();
        if (resposta.equals("s") || resposta.equals("y")) {
            return true;
        }
        if (resposta.equals("sí") || resposta.equals("yes")) {
            return true;
        }
        if (resposta.equals("si") || resposta.equals("vale") || resposta.equals("yeah")) {
            return true;    
        }
        return false;
    }
}
