public class Merchant implements Seller {
    @Override
    public String sell(Goods goods, Characters hero) {
        String result = "";
        if (goods == Goods.POTION) {
            if (hero.getGold() >= 10) {
                hero.setGold(hero.getGold() - 10);
                hero.setHealth(hero.getHealth() + 30);
                result = String.format("%s Приобрел зелье! Теперь у вас %d здоровья", hero.getName(), hero.getHealth());
            } else {
                result = "У тебя не хватает денег";
            }
        }
        return result;
    }

    public enum Goods {
        POTION
    }
}