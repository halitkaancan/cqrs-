package com.hkc.cqrs.application.book.command.update;

import an.awesome.pipelinr.Command;
import com.hkc.cqrs.core.pipelines.auth.AuthorizedRequest;
import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookCommand implements Command<UpdatedBookResponse>{

    @NotNull
    private UUID id;
    @NotBlank
    private String name;

    @Component
    @RequiredArgsConstructor
    public static class UpdateBookCommandHandler implements Command.Handler<UpdateBookCommand,UpdatedBookResponse> , AuthorizedRequest {

        private final BookRepository bookRepository;

        @Override
        public UpdatedBookResponse handle(UpdateBookCommand updateBookCommand) {
//            Book book = new Book();
//            book.setName(updateBookCommand.getName());
//            bookRepository.save(book);
//
//            return new UpdatedBookResponse(book.getId(), book.getName());

            Book existingBook = bookRepository.findById(updateBookCommand.getId()).orElseThrow(() ->
                    new RuntimeException("Book not found with id: " + updateBookCommand.getId()));
            existingBook.setName(updateBookCommand.getName());

            bookRepository.save(existingBook);

            return  new UpdatedBookResponse(existingBook.getId(), existingBook.getName());
        }

        @Override
        public List<String> getRequiredRoles() {
            return List.of("Admin","Book.Update");
        }
    }
}
