package es.cic.curso.ejercicio4;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.ejercicio4.models.Director;
import es.cic.curso.ejercicio4.models.Pelicula;
import es.cic.curso.ejercicio4.service.PeliculaService;

@SpringBootTest
@AutoConfigureMockMvc
public class PeliculaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pelicula pelicula;
    private Director director;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializa los datos de prueba
        director = new Director();  
        director.setId(1L);  
        director.setNombre("Christopher Nolan");
        director.setAno(1970);
        
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Inception");
        pelicula.setAno(2010);
        pelicula.setDirector(director);

        // Configura los mocks
        when(peliculaService.findById(1L)).thenReturn(pelicula);
        when(peliculaService.findAll()).thenReturn(List.of(pelicula));
        when(peliculaService.save(any(Pelicula.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @AfterEach
    void tearDown() {
        // No se necesita limpiar la base de datos, ya que usamos mocks
    }

    @Test
    void getAllPeliculas_ShouldReturnListOfPeliculas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/peliculas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Inception"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ano").value(2010));
    }

    @Test
    void getPeliculaById_ShouldReturnPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Inception"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2010));
    }

    @Test
    void createPelicula_ShouldCreateAndReturnPelicula() throws Exception {
        Pelicula nuevaPelicula = new Pelicula(2L, "Dunkirk", 2017, director);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaPelicula)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Dunkirk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2017));
    }

    @Test
    void updatePelicula_ShouldUpdateAndReturnPelicula() throws Exception {
        pelicula.setTitulo("Inception Updated");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Inception Updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2010));
    }

    @Test
    void deletePelicula_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}