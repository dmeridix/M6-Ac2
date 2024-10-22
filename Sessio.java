public class Sessio {

    public static void main(String[] args) {
        while (true) {
            System.out.println(
                "Introdueix el número de l'acció que vols fer?\n 1 --> Generar un nou encàrrec\n 2 --> Mostrar un encàrrec\n 3 --> Sortir");
            System.out.println("Selecciona una de les tres opcions (1, 2 o 3): ");
            int input = Integer.parseInt(Utils.readLine());
            encarrec enc = new encarrec();
            while (input != 1 && input != 2 && input != 3) {
                System.out.println("Opcio incorrecta, has d'escriure 1, 2 o 3");
                System.out.println(
                        "Introdueix el numero de l'accio que vols fer?\n 1 --> Generar un nou encàrrec \n 2 --> Mostrar un encàrrec.\n 3 --> sortir");
                System.out.println("Selecciona una de les tres opcions (1, 2 o 3): ");
                input = Integer.parseInt(Utils.readLine());
            }
            switch (input) {
                case 1:
                    enc.generarEncarrec();
                    break;
                case 2:
                    enc.mostrarEncarrec();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
            System.out.println("Vols fer una altra acció ? ");
            String resposta = Utils.readLine().toLowerCase();

            if (Utils.respostaABoolean(resposta) != true) {
                System.out.println("Sortint...");
                break;
            }
        }
    }
}
