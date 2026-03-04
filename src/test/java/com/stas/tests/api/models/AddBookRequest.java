package com.stas.tests.api.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookRequest {
    private String userId;
    private List<Isbn> collectionOfIsbns;
}
