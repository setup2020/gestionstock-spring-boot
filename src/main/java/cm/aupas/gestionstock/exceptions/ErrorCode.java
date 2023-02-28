package cm.aupas.gestionstock.exceptions;


public enum ErrorCode {
    ERROR_404(404),
    ERROR_422(422),
    ERROR_401(401),
    ERROR_403_(403),

    ERROR_500(500);


    private int code;
    ErrorCode(int i) {
        code=i;
    }
    public int getCode(){
        return code;
    }
}