package ua.com.alevel.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {

    protected List<SortData> generateSortDataList(Map<String, String> fieldColumnMap, String sort, String order) {
        List<SortData> sortDataList = new ArrayList<>();
        fieldColumnMap.forEach((column, field) -> {
            SortData sortData = new SortData();
            sortData.setColumnName(column);
            sortData.setFieldName(field);
            if (StringUtils.isNotBlank(field)) {
                if (field.equals(sort)) {
                    sortData.setSortable(true);
                    sortData.setOrder(order);
                } else {
                    sortData.setSortable(false);
                }
            }
            sortDataList.add(sortData);
        });
        return sortDataList;
    }

    @Getter
    @Setter
    @ToString
    public static class SortData {

        private String columnName;
        private String fieldName;
        private String order;
        private boolean sortable;
    }
}
