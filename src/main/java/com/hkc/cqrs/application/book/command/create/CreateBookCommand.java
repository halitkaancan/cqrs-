package com.hkc.cqrs.application.book.command.create;


import an.awesome.pipelinr.Command;
import com.hkc.cqrs.application.author.rules.AuthorBusinessRules;
import com.hkc.cqrs.application.author.service.AuthorService;
import com.hkc.cqrs.application.book.mapper.BookMapper;
import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand implements Command<CreatedBookResponse> {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    private UUID authorId;

    @Component
    @RequiredArgsConstructor
    public static class CreateBookCommandHandler implements  Command.Handler<CreateBookCommand,CreatedBookResponse>{

        private final BookRepository bookRepository;
        private final AuthorService authorService ;
        private final AuthorBusinessRules authorBusinessRules ;

        @Override
        public CreatedBookResponse handle(CreateBookCommand createdBookCommand) {
//            mapstruct ile auto-generated
//            Author author = authorService.findById(createdBookCommand.authorId);
//            Genelde business rules entitiler ike olan ilişkimizde yeterli olsa da
//            servis ihtiyaç durumunnda entegre edilebilir!
            authorBusinessRules.authorWithIdCanNotBeNull(createdBookCommand.authorId);
            //oto mapping
            BookMapper mapper = BookMapper.INSTANCE;
            Book book = mapper.convertCreateCommandToBook(createdBookCommand);
//            book.setAuthor(author); mapstruct ile auto-generated
            bookRepository.save(book);

            return mapper.convertBookToCreateBookResponse(book);
        }
    }

}
