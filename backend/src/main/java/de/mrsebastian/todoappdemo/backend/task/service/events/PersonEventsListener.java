package de.mrsebastian.todoappdemo.backend.task.service.events;

import de.mrsebastian.todoappdemo.backend.person.service.events.PersonDeleteEvent;
import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonEventsListener {

    private final TaskService taskService;

    @EventListener
    public void handlePersonDeletedEvent(final PersonDeleteEvent personDeleteEvent) {
        taskService.removePersonFromTasks(personDeleteEvent.personId());
    }
}
