package teatech.collegemanagement.util;

public class Constant {

    public static enum RESPONSE {
        APPROVED("00","Approved"),
        FAILED("0X","Failed"),
        HTTP_NOT_FOUND("X4","There is No Resource Path"),
        HTTP_INTERNAL_ERROR("X5","Service Internal Error"),
        INVALID_TRANSACTION("12","Invalid Transaction"),
        WRONG_FORMAT_DATA("30","Format Data Salah"),
        ;

        private String code, description;

        RESPONSE(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
