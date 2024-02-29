public abstract class Characters implements Fighter {
    private String name;
    private int health;
    private int power;
    private int skill;
    private int gold;
    private int experience;


    public Characters(String name, int health, int power, int skill, int gold, int experience) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.skill = skill;
        this.gold = gold;
        this.experience = experience;
    }

    @Override
    public int attack() {
        if (skill * 3 > getRandomValue()) {
            return power;
        } else if (skill * 3 == getRandomValue()) {
            return power * 2;
        } else return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int strength) {
        this.power = strength;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, health);
    }
}

