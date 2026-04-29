package com.nhuhuy05.haibazo_round_2.dto.request;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    String name;
    String authorId;
}
