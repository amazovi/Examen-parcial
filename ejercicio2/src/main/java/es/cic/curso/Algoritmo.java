package es.cic.curso;

public class Algoritmo {

    //Hago el método para calcular el valor de un nodo
    public int calcularValorFinal(Nodo nodo) {
        int valor = nodo.getValor();
        String nombre = nodo.getNombre();

        //Verifico si es un valor par o no lo es 
        if (valor % 2 == 0) { // Es par
            int longitudNombre = nombre != null ? nombre.length() : 0;
            return valor + longitudNombre;
        } else { // Es impar
            return valor;
        }
    }

    // Hago un método para procesar y gestionar nodos para calcular el valor final
    public void procesarNodos(Nodo[] nodos) {
        for (Nodo nodo : nodos) {
            int valorFinal = calcularValorFinal(nodo);
            System.out.println("Valor final del nodo con valor " + nodo.getValor() + " es " + valorFinal);
        }
    }
}
