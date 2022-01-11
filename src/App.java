import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        String opcionPrimerMenu;
        Scanner teclado = new Scanner(System.in);

        do {

            System.out.println("---------------------------------");
            System.out.println("CONCURSO DE PREGUNTAS.");
            System.out.println("");
            System.out.println("1. EMPEZAR CONCURSO.");
            System.out.println("2. VER HISTORICOS.");
            System.out.println("3. SALIR.");
            System.out.println("---------------------------------");
            
            opcionPrimerMenu = teclado.nextLine();
            
            switch (opcionPrimerMenu) {
                case "1":

                System.out.println("---------------------------------");
                    System.out.print("INGRESE SU NOMBRE: ");
                    String nombreParticipante = teclado.nextLine();

                    Participante participante = new Participante(nombreParticipante,0);
                    
                    ArrayList<Categoria> categorias = Categoria.getCategoriasDB();

                    for (int i=1; i <= categorias.size(); i++) {

                        Categoria categoria = categorias.get(i-1);

                        Premio premio = Premio.getPremioDB(categoria.getIdPremio());

                        ArrayList<Pregunta> preguntas = Pregunta.getPreguntasDB(categoria.getId());
                        
                        Ronda ronda = new Ronda(i, categoria, preguntas, premio);
                        System.out.println("--------------------------------------------------------");
                        System.out.println("RONDA N° " + ronda.getNumero());
                        System.out.println("");
                        System.out.println("CATEGORIA: N° " + ronda.getCategoria().getId() +", NIVEL: " + ronda.getCategoria().getNombre() + ", PREMIO: " + ronda.getPremio().getMonto() + "$");
                        System.out.println("");

                        Pregunta pregunta = ronda.preguntaAleatoria(preguntas);

                        System.out.println("--------------------------------------------------------");
                        System.out.println(pregunta.getPregunta());
                        System.out.println("--------------------------------------------------------");

                        ArrayList<Respuesta> respuestas = Respuesta.getRespuestasDB(pregunta.getId());
                        int respuestaCorrecta = 0;
                        for (int j = 0 ; j < respuestas.size(); j++) {
                            Respuesta respuesta = respuestas.get(j);
                            System.out.println(j+1 + ". " + respuesta.getRespuesta());
                            if (respuesta.getCorrecta() == 1){
                                respuestaCorrecta = j+1;
                            }
                        }
                        System.out.println("");
                        System.out.println("5. TERMINAR EL JUEGO");
                        String respuestaParticipante = teclado.nextLine();

                        if (Integer.parseInt(respuestaParticipante) == respuestaCorrecta){
                            participante.sumarAlAcumulado(ronda.getPremio().getMonto());
                            System.out.println("--------------------------------------------------------");
                            System.out.println("RESPUESTA CORRECTA, ACUMULADO: " + participante.getMontoAcumulado() + "$");
                            System.out.println("--------------------------------------------------------");

                            if( i == categorias.size() ){
                                participante.guardarRegistro(participante.getNombre(), participante.getMontoAcumulado());
                                System.out.println("--------------------------------------------------------");
                                System.out.println("HAS GANADO EL JUEGO");
                                System.out.println("--------------------------------------------------------");
                                opcionPrimerMenu = "0";
                            }

                        }else{
                            participante.guardarRegistro(participante.getNombre(), participante.getMontoAcumulado());
                            System.out.println("--------------------------------------------------------");
                            System.out.println("===>>> JUEGO FINALIZADO, SE HA GANADO " + participante.getMontoAcumulado() + "$ <<<===");
                            System.out.println("--------------------------------------------------------");

                            opcionPrimerMenu = "0";
                            break;
                        }
                    }

                    break;

                case "2":
                    ArrayList<Participante> participantes = Participante.getParticipantesDB();

                    System.out.println("---------------------------------");
                    System.out.println("HISTORICOS");
                    System.out.println("---------------------------------");
                    for (int i = 0 ; i < participantes.size(); i++) {
                        Participante partic = participantes.get(i);
                        System.out.println(i+1 + ". NOMBRE: " + partic.getNombre() + " | MONTO GANADO: " + partic.getMontoAcumulado() + "$");
                    }
                    System.out.println("---------------------------------");
                    opcionPrimerMenu = "0";
                    break;
                    
                case "3":
                System.out.println("---------------------------------");
                System.out.println("ADIOS, VUELVE PRONTO!");
                System.out.println("---------------------------------");
                    break;

                default:
                    opcionPrimerMenu = "0";
                    System.out.println("---------------------------------");
                    System.out.println("OPCION INVALIDA");
                    System.out.println("---------------------------------");
                    System.out.println("---------------------------------");
                    System.out.println("SELECCIONE UNA OPCION CORRECTA");
                    System.out.println("---------------------------------");
            }

        } while (Integer.parseInt(opcionPrimerMenu) < 1 || Integer.parseInt(opcionPrimerMenu) > 3 );

        teclado.close();
    }
}
