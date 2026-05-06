package com.nhuhuy05.haibazo_round_2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {
    @NotBlank(message = "review must not be blank")
    String review;

    @NotNull(message = "bookId must not be null")
    Integer bookId;
}
