package ua.com.alevel.web.dto.response;

public abstract class ResponseDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseDto(Long id) {
        this.id = id;
    }

    public ResponseDto() { }
}
