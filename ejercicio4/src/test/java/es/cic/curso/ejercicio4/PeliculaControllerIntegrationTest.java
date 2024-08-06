package es.cic.curso.ejercicio4;




import org.hibernate.engine.internal.Collections;
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
import es.cic.curso.ejercicio4.repository.DirectorRepository;
import es.cic.curso.ejercicio4.repository.PeliculaRepository;
import es.cic.curso.ejercicio4.service.DirectorService;
import es.cic.curso.ejercicio4.service.PeliculaService;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc

public class PeliculaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Pelicula pelicula;
    private Director director;

   @BeforeEach
   void setUp() throws Exception {
    
    // Configura la película
    pelicula = new Pelicula(1L, "Inception", 2010, director);
    pelicula = peliculaRepository.save(pelicula);
}


    @AfterEach
    void tearDown() {
        // Limpia la base de datos eliminando todas las películas y directores
        peliculaRepository.deleteAll();

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