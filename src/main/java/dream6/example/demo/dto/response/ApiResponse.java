package dream6.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ApiResponse {

    private Integer status;

    private LocalDateTime responseTime;

    private Object data;
    private String message;

    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }

    public Integer getStatus() {
        return this.status;
    }

    public LocalDateTime getResponseTime() {
        return this.responseTime;
    }

    public Object getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setResponseTime(final LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiResponse)) {
            return false;
        } else {
            ApiResponse other = (ApiResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$responseTime = this.getResponseTime();
                Object other$responseTime = other.getResponseTime();
                if (this$responseTime == null) {
                    if (other$responseTime != null) {
                        return false;
                    }
                } else if (!this$responseTime.equals(other$responseTime)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApiResponse;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $responseTime = this.getResponseTime();
        result = result * 59 + ($responseTime == null ? 43 : $responseTime.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getStatus();
        return "ApiResponse(status=" + var10000 + ", responseTime=" + this.getResponseTime() + ", data=" + this.getData() + ", message=" + this.getMessage() + ")";
    }

    public ApiResponse() {
    }

    public ApiResponse(final Integer status, final LocalDateTime responseTime, final Object data, final String message) {
        this.status = status;
        this.responseTime = responseTime;
        this.data = data;
        this.message = message;
    }

    public static class ApiResponseBuilder {
        private Integer status;
        private LocalDateTime responseTime;
        private Object data;
        private String message;

        ApiResponseBuilder() {
        }

        public ApiResponseBuilder status(final Integer status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder responseTime(final LocalDateTime responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public ApiResponseBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this.status, this.responseTime, this.data, this.message);
        }

        public String toString() {
            return "ApiResponse.ApiResponseBuilder(status=" + this.status + ", responseTime=" + this.responseTime + ", data=" + this.data + ", message=" + this.message + ")";
        }
    }

}

