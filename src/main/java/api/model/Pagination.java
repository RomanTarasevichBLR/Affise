package api.model;

import lombok.Data;

@Data
public class Pagination {
    private Integer per_page;
    private Integer total_count;
    private Integer page;
    private Integer next_page;
    private Integer prev_page;
}
