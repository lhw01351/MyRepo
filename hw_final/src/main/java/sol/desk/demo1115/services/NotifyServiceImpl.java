package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService{

    public static final String NOTIFY_MSG_SESSION_KEY="siteNotificationMessages";

    public enum NotificationMessageType{
        INFO,
        ERROR
    }

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addInfoMessage(String message) {
        addNotificationMessage(NotificationMessageType.INFO, message);
    }

    @Override
    public void addErrorMessage(String massage) {

    }

    private void addNotificationMessage(NotificationMessageType info, String message) {
            List<NotifycationMessage> notifyMessage =
                    (List<NotifycationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
    }

    public class NotifycationMessage{
        NotificationMessageType type;
        String text;
        public  NotifycationMessage(NotificationMessageType type, String text){
            this.type= type;
            this.text=text;
        }
    }

}
