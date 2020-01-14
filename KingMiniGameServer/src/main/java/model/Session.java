package model;

public class Session {

    private final int userId;
    private final long validUntil;

    public Session(int userId, long validUntil) {
        this.userId = userId;
        this.validUntil = validUntil;
    }

    public int getUserId() {
        return userId;
    }

    public boolean validSession(long now) {
        return (now < validUntil);
    }
}
