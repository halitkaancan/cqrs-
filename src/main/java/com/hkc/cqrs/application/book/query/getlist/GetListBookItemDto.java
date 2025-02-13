package com.hkc.cqrs.application.book.query.getlist;

import an.awesome.pipelinr.repack.org.checkerframework.checker.units.qual.N;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListBookItemDto {

    private UUID id;
    private String name;
}
