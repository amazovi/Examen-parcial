package test.java.es.cic.curso;

public class AlgoritmoTest {


    @Test
    public void testCalcularValorFinalPar() {
        Nodo nodo = new Nodo(8, "hola");
        Algoritmo algoritmo = new Algoritmo();
        int resultado = algoritmo.calcularValorFinal(nodo);
        // Esperamos 8 (valor del nodo) + 4 (longitud de "hola") = 12
        assertEquals(12, resultado);
    }

    @Test
    public void testCalcularValorFinalImpar() {
        Nodo nodo = new Nodo(5, null);
        Algoritmo algoritmo = new Algoritmo();
        int resultado = algoritmo.calcularValorFinal(nodo);
        // Esperamos 5 ya que es impar y no se suma la longitud de ningún nombre
        assertEquals(5, resultado);
    }

    @Test
    public void testCalcularValorFinalParSinNombre() {
        Nodo nodo = new Nodo(10, null);
        Algoritmo algoritmo = new Algoritmo();
        int resultado = algoritmo.calcularValorFinal(nodo);
        // Esperamos 10 (valor del nodo) + 0 (longitud de null) = 10
        assertEquals(10, resultado);
    }

    @Test
    public void testProcesarNodos() {
        Nodo nodo1 = new Nodo(8, "hola");
        Nodo nodo2 = new Nodo(5, null);
        Nodo nodo3 = new Nodo(10, "mundo");
        Nodo[] nodos = {nodo1, nodo2, nodo3};

        // Usamos un OutputStream para capturar la salida
        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outputStream));

        Algoritmo algoritmo = new Algoritmo();
        algoritmo.procesarNodos(nodos);

        String salidaEsperada = "Valor final del nodo con valor 8 es 12\n" +
                                "Valor final del nodo con valor 5 es 5\n" +
                                "Valor final del nodo con valor 10 es 14\n";
        
        assertEquals(salidaEsperada, outputStream.toString());
    }
}