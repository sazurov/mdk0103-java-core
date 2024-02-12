package base.play;
import java.util.Random;
class Play {
    private int kingHealth = 150;
    private int gold = 300;
    private Unit[] units = {
            new Unit("Рыцарь", 80, 30),
            new Unit("Лучник", 40, 50)
    };

    private Enemy[] enemies = {
            new Enemy("Враг 1", 10, 5, 0),
            new Enemy("Враг 2", 20, 10, 50),
            new Enemy("Враг 3", 100, 50, 100)
    };

    private Random random = new Random();

    public void init() {
        battleLoop();
    }

    private void battleLoop() {
        while (kingHealth > 0 && units.length > 0 && enemies.length > 0) {
            Unit unit = units[random.nextInt(units.length)];
            Enemy enemy = enemies[random.nextInt(enemies.length)];

            System.out.println("Битва между " + unit.getName() + " и " + enemy.getName());

            while (unit.getHealth() > 0 && enemy.getHealth() > 0) {
                enemy.setHealth(enemy.getHealth() - unit.getDmg());
                System.out.println(unit.getName() + " атакует " + enemy.getName() + ". Здоровье врага: " + enemy.getHealth());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (enemy.getHealth() <= 0) {
                    System.out.println(enemy.getName() + " погибает!");
                    gold += enemy.netWorth;
                    removeEnemy(enemy);
                    break;
                }

                unit.setHealth(unit.getHealth() - enemy.getDmg());
                System.out.println(enemy.getName() + " атакует " + unit.getName() + ". Здоровье юнита: " + unit.getHealth());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (unit.getHealth() <= 0) {
                    System.out.println(unit.getName() + " погибает!");
                    removeUnit(unit);
                    break;
                }
            }
        }

        if (kingHealth <= 0 || units.length == 0) {
            System.out.println("Король умирает, игра окончена.");
        }
    }

    private void removeUnit(Unit unit) {
        Unit[] newArr = new Unit[units.length - 1];
        int index = 0;
        for (Unit u : units) {
            if (u != unit) {
                newArr[index++] = u;
            }
        }
        units = newArr;
    }

    private void removeEnemy(Enemy enemy) {
        Enemy[] newArr = new Enemy[enemies.length - 1];
        int index = 0;
        for (Enemy e : enemies) {
            if (e != enemy) {
                newArr[index++] = e;
            }
        }
        enemies = newArr;
    }
}

class Unit {
    private int health;
    private int dmg;
    private String name;

    public String getName() {
        return name;
    }

    public Unit(String name, int health, int dmg) {
        this.name = name;
        this.health = health;
        this.dmg = dmg;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

class Enemy extends Unit {
    int netWorth;

    public Enemy(String name, int health, int dmg, int netWorth) {
        super(name, health, dmg);
        this.netWorth = netWorth;
    }
}

class Main {
    public static void main(String[] args) {
    Play play = new Play();
    play.init();
    }
}
