package model.dto;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class RespuestaDTO {
    private boolean success; // comprobacion de operacion exitosa o erronea
    private String message; // mensaje de respuesta
    private Object data; // objeto universsidad o estudiante

    // Constructor
    public RespuestaDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RespuestaDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

