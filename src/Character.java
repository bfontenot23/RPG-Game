public class Character {
    private int HP,MP,def,spd;
    private String name;
    private boolean fled;

    public Character(int HP, int MP, int def, int spd)
    {
        this.HP = HP;
        this.MP = MP;
        this.def = def;
        this.spd = spd;
        this.fled = false;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFled() {
        return fled;
    }

    public void setFled(boolean fled) {
        this.fled = fled;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }


    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

}
