package model.dto;

/**
 * Clase RespuestaDTO que se utiliza para encapsular la respuesta de una
 * operación. Proporciona información sobre si la operación fue exitosa, un
 * mensaje de respuesta y opcionalmente, un objeto de datos adicional (que puede
 * ser de cualquier tipo).
 *
 * Esta clase se usa para unificar el formato de las respuestas en los
 * controladores o servicios que devuelven información al cliente o a otras
 * capas de la aplicación.
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class RespuestaDTO {

    /**
     * Indica si la operación fue exitosa o no.
     */
    private boolean success;

    /**
     * Mensaje de respuesta que proporciona información adicional sobre la
     * operación.
     */
    private String message;

    /**
     * Objeto de datos adicional que puede contener cualquier tipo de
     * información relacionada con la operación (por ejemplo, una entidad, una
     * lista, etc.).
     */
    private Object data;

    /**
     * Constructor que inicializa la respuesta con el estado de éxito y un
     * mensaje.
     *
     * @param success true si la operación fue exitosa, false si fue fallida.
     * @param message mensaje de respuesta que proporciona detalles adicionales
     * sobre la operación.
     */
    public RespuestaDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Constructor que inicializa la respuesta con el estado de éxito, un
     * mensaje y un objeto de datos.
     *
     * @param success true si la operación fue exitosa, false si fue fallida.
     * @param message mensaje de respuesta que proporciona detalles adicionales
     * sobre la operación.
     * @param data objeto de datos adicional asociado a la respuesta (puede ser
     * cualquier tipo de objeto).
     */
    public RespuestaDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * Obtiene el estado de éxito de la operación.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Establece el estado de éxito de la operación.
     *
     * @param success true si la operación fue exitosa, false si fue fallida.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Obtiene el mensaje de la respuesta.
     *
     * @return el mensaje de respuesta como una cadena de texto.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de la respuesta.
     *
     * @param message mensaje de respuesta que proporciona detalles adicionales
     * sobre la operación.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtiene el objeto de datos adicional asociado con la respuesta.
     *
     * @return el objeto de datos que puede ser cualquier tipo de objeto.
     */
    public Object getData() {
        return data;
    }

    /**
     * Establece el objeto de datos adicional asociado con la respuesta.
     *
     * @param data el objeto de datos que puede ser cualquier tipo de objeto.
     */
    public void setData(Object data) {
        this.data = data;
    }
}
