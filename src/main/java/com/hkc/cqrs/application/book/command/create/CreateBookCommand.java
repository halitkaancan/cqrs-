package com.hkc.cqrs.application.book.command.create;


import an.awesome.pipelinr.Command;
import com.hkc.cqrs.application.book.mapper.BookMapper;
import com.hkc.cqrs.core.pipelines.auth.AuthenticatedRequest;
import com.hkc.cqrs.domain.entity.Book;
import com.hkc.cqrs.persistence.book.BookRepository;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand implements Command<CreatedBookResponse> , AuthenticatedRequest {

    //@NotBlank
    private String name;

    @Component
    @RequiredArgsConstructor
    public static class CreateBookCommandHandler implements  Command.Handler<CreateBookCommand,CreatedBookResponse>{

        private final BookRepository bookRepository;

        @Override
        public CreatedBookResponse handle(CreateBookCommand createdBookCommand) {


            //manual mapping
            //Mapping -> CreatedBookCommand -> Book
//            Book book = new Book();
//            book.setName(createdBookCommand.getName());
//            bookRepository.save(book);

            //oto mapping
            BookMapper mapper = BookMapper.INSTANCE;
            Book book = mapper.convertCreateCommandToBook(createdBookCommand);

            return mapper.convertBookToCreateBookResponse(book);
        }
    }

}
