import java.util.ArrayList;

public class Ronda {
    
    private  int numero;

    private Categoria categoria;

    private ArrayList<Pregunta> preguntas;

    private ArrayList<Respuesta> respuestas;

    private Premio premio;

    public Ronda(int numero, Categoria categoria, ArrayList<Pregunta> preguntas, Premio premio) {
        this.numero = numero;
        this.categoria = categoria;
        this.preguntas = preguntas;
        //this.respuestas = respuestas;
        this.premio = premio;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pregunta preguntaAleatoria(ArrayList<Pregunta> preguntas){
        int numeroAleatorio = (int)(Math.random()*5+0);
        Pregunta pregunta = preguntas.get(numeroAleatorio);
        return pregunta;
    }

}
