package Fighter;

public class Athlete {

    private final int weight;
    private final int height;
    private final int age;
    private final int experience;
    private String name;

    private int wins = 0;
    private int loses = 0;
    private int ties = 0;

    private int point = 0;
    private int category1 = 0;
    private int category2 = 0;

    Athlete(int weight, int height, int age, int experience) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.experience = experience;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getLoses() {
        return loses;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getTies() {
        return ties;
    }

    public void resetNoOfMatches() {
        wins = 0;
        loses = 0;
        ties = 0;
    }

    public void resetPoints() {
        point = 0;
        category1 = 0;
        category2 = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setCategory1(int category1) {
        this.category1 = category1;
    }

    public int getCategory1() {
        return category1;
    }

    public void setCategory2(int category2) {
        this.category2 = category2;
    }

    public int getCategory2() {
        return category2;
    }
}
