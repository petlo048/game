package model;


public class HighScore implements Comparable<HighScore> {

    public int getUserId() {
        return userId;
    }

    private final int userId;
    private final int points;

    public HighScore(int userID, int points) {
        this.userId = userID;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }


    @Override
    public String toString() {
        return String.format("%s-%s", this.userId, this.points);
    }

    @Override
    public int compareTo(HighScore that) {
        return (this.points > that.points ? -1 :
                (this.points == that.points ? 0 : 1));
    }
}
