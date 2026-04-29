package com.nhuhuy05.haibazo_round_2.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
    Integer id;
    String bookName;
    String authorName;
    String review;
}
