package model;


public class Score implements Comparable<Score> {

    private final int points;
    private final int level;
    private int userId;

    public Score(int userID, int points, int level) {
        this.userId = userID;
        this.points = points;
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", this.userId, this.points);
    }

    @Override
        public int compareTo(Score that) {
            return (this.points > that.points ? -1 :
                    (this.points == that.points ? 0 : 1));
        }

}
