package ua.com.alevel.facade;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.dto.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Integer id);
    void delete(Integer id);
    RES findById(Integer id);
    List<RES> findAll();
}
