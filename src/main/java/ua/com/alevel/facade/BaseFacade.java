package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.web.dto.request.RequestDto;
import ua.com.alevel.web.dto.response.ResponseDto;

public interface BaseFacade <RES extends ResponseDto, REQ extends BaseEntity> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
}
