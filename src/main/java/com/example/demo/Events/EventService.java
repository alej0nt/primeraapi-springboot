package com.example.demo.Events;
import java.util.List;
import com.example.demo.Users.Usuario;
import com.example.demo.Users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public EventService(EventRepository eventRepository, UsuarioRepository usuarioRepository) {
        this.eventRepository = eventRepository;
        this.usuarioRepository = usuarioRepository;
        initData();
    }

    private void initData(){
        eventRepository.save(new Event("Fiesta", "15-06-2025"));
        eventRepository.save(new Event("Matrimonio", "05-04-2025"));
    }

    //Agregar evento
    public Event save(Event event){
        return eventRepository.save(event);
    }

    //Eliminar evento
    public void remove(String idEvento){
        eventRepository.delete(idEvento);
    }

    //Editar evento
    public Event update(Event event){
        Event eventToUpdate = eventRepository.findById(event.getIdEvento());
        if (eventToUpdate != null){
            Event updatedEvent = eventRepository.update(event);
            return updatedEvent;
        }
        return null;
    }

    //Listar eventos
    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public Event findById(String idEvento){
        Event eventToFind = eventRepository.findById(idEvento);
        if (eventToFind != null){
            return eventToFind;
        }
        return null;
    }

    public List<Event> findByFilters(String nombreDeEvento, String fechaDeEvento){
        return eventRepository.findByFilters(nombreDeEvento, fechaDeEvento);
    }

    //Agregar participantes
    public void addAsistente(Persona persona, String idEvento){
        Event event = eventRepository.findById(idEvento);
        if (event != null){
            eventRepository.addAsistente(persona.getNombre(), persona.getEmail(), idEvento);
        }
    }

    //Eliminar participantes
    public void removeAsistente(String authToken, String idPersona, String idEvento){
        Event event = eventRepository.findById(idEvento);
        Usuario usuario = usuarioRepository.findByAuthToken(authToken);
        if (event != null && usuario != null){
            eventRepository.removeAsistente(idPersona, idEvento);
        }
    }
}
