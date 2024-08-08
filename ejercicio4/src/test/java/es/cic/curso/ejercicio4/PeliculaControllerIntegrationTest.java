package es.cic.curso.ejercicio4;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
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
import es.cic.curso.ejercicio4.repository.PeliculaRepository;
import es.cic.curso.ejercicio4.service.PeliculaService;


@SpringBootTest
@AutoConfigureMockMvc
class PeliculaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    private Pelicula pelicula;
    private Director director;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Limpiar la base de datos de prueba
        peliculaRepository.deleteAll();

        // Crear una película de prueba
        pelicula = new Pelicula();
        
        pelicula.setTitulo("Inception");
        pelicula.setAno(2010);

        // Guardar la película en la base de datos de prueba
        pelicula= peliculaRepository.save(pelicula);

          // Configura los mocks
          when(peliculaService.findById(pelicula.getId())).thenReturn(pelicula); 
          when(peliculaService.findAll()).thenReturn(List.of(pelicula));
          when(peliculaService.save(any(Pelicula.class))).thenAnswer(invocation -> invocation.getArgument(0));
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
        mockMvc.perform(MockMvcRequestBuilders.get("/api/peliculas/" + pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Inception"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2010));
    }

   
    @Test
    void createPelicula_ShouldCreateAndReturnPelicula() throws Exception {
        Pelicula nuevaPelicula = new Pelicula();
     
        nuevaPelicula.setTitulo("Dunkirk");
        nuevaPelicula.setAno(2017);
        nuevaPelicula.setDirector(director);

        mockMvc.perform(post("/api/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaPelicula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Dunkirk"))
                .andExpect(jsonPath("$.ano").value(2017));
    }

    @Test
    void updatePelicula_ShouldUpdateAndReturnPelicula() throws Exception {
        pelicula.setTitulo("Inception Updated");

        mockMvc.perform(put("/api/peliculas/" + pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Inception Updated"))
                .andExpect(jsonPath("$.ano").value(2010));
    }

    @Test
    void deletePelicula_ShouldReturnNoContent() throws Exception {
        // Asegúrate de que la película existe antes de intentar eliminarla
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/peliculas/" + pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verifica que la película ha sido eliminada
        mockMvc.perform(MockMvcRequestBuilders.get("/api/peliculas/" + pelicula.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

   
}