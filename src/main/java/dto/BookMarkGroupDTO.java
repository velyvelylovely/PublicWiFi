package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkGroupDTO {
    private int id;
    private String name;
    private int order;
    private String register_dttm;
    private String update_dttm;
}
