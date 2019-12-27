package admin.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "servicecontrol")
@Component
public class ServiceControl {

    @ReadOperation
    public Object read() {
        return "服务控制";
    }

}
