
package dmit2015.repository;

import common.jpa.AbstractJpaRepository;
import dmit2015.entity.TodoItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class TodoItemRepository extends AbstractJpaRepository<TodoItem, Long> {

    public TodoItemRepository() {
        super(TodoItem.class);
    }

    public void update(TodoItem updatedTodoItem) {
        Optional<TodoItem> optionalTodoItem = findOptional(updatedTodoItem.getId());
        if (optionalTodoItem.isPresent()) {
            TodoItem existingTodoItem = optionalTodoItem.get(); // make a copy of the todo item
            existingTodoItem.setName(updatedTodoItem.getName());
            existingTodoItem.setComplete(updatedTodoItem.isComplete());
            // next call the update method in our super class
            super.update(existingTodoItem); //when an update request is sent in, make sure it is an item in our system and copy over the values we are allowed to change
        }
    }
}

