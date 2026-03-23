package pro.ms.auth.event.email;

import pro.ms.auth.event.type.UserCreatedEvent;

public interface EmailEvents {

    void onUserCreated(UserCreatedEvent event);

}
