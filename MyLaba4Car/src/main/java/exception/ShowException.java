package exception;

import org.hibernate.HibernateError;

public class ShowException {
    public static void showNotice(HibernateError e) {
        System.err.println("Ошибка: " + e.getMessage());

        Throwable cause = e.getCause();
        if (cause != null) {
            System.err.println("Причина: " + cause.getMessage());
        }
    }
}
