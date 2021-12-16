package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataTableResponse<E> {

    private int page;
    private int size;
    private String sort;
    private String order;
    private long totalItems;
    private List<E> items;
}
