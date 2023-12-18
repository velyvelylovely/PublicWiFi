package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDTO {
    private int id;
    private int groupNo;
    private String mgrNo;
    private String register_dttm;
}
