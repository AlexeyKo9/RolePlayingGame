import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {

    private static BufferedReader br;
    private static Characters player = null;
    private static BattleScene battleScene = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                commitSell();
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
            case "торговать": {
                command("1");
            }
            case "в город": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void commitSell() {
        Merchant merchant = new Merchant();
        System.out.println("Приветствую путник! Пока могу предложить только зелье.");
        String result = merchant.sell(Merchant.Goods.POTION, player);
        System.out.println(result);
        System.out.println("Осталось золота: " + player.getGold());
        System.out.println("Желаете продолжить торговлю или вернуться в город? (торговать/в город)");
    }

    private static Characters createMonster() {
        int random = (int) (Math.random() * 10);
        if (random <= 3) return new Goblin(
                "Гоблин",
                50,
                20,
                10,
                100,
                20
        );
        else if (random > 3 && random <= 7) return new Skeleton(
                "Скелет",
                25,
                15,
                20,
                100,
                10
        );
        else return new Dragon(
                    "Дракон",
                    100,
                    30,
                    30,
                    200,
                    50
            );
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

}