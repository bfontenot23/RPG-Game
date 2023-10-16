import java.util.Random;

public class Player extends Character{

    private int empower;


    public Player(String name)
    {
        super(100,50,0,0);
        Random numGen = new Random();
        this.setSpd(numGen.nextInt(0,50));
        this.setEmpower(0);
        this.setName(name);
        this.setFled(false);
    }

    public int getEmpower() {
        return empower;
    }

    public void setEmpower(int empower) {
        this.empower = empower;
    }


    /*
     * basicAttack - initiates a basic attack action
     * @param enemy - the target enemy object
     */
    public void basicAttack(Enemy enemy)
    {
        Random numGen = new Random();
        if(numGen.nextInt(0,100)<80)
        {
            int min = 5+this.getEmpower(), max = 10+2*this.getEmpower();
            int damage = numGen.nextInt(min, max) - enemy.getDef();
            if(damage<0) damage=0;
            int enemyHP = enemy.getHP() - damage;
            enemy.setDef(0);
            enemy.setHP(enemyHP);
            System.out.printf("%s: Hit %s for %d damage!\n",this.getName(),enemy.getName(),damage);
        }
        else
        {
            if(enemy.getDef()!=9) enemy.setDef((enemy.getDef()+3));
            System.out.printf("%s: Tried to attack %s but missed.\n",this.getName(), enemy.getName());
        }

        if(enemy.getHP()<=0)
        {
            enemy.setFled(true);
            System.out.printf("%s: Is unable to continue fighting and runs away.\n",enemy.getName());
        }
    }

    /*
     * fireball - initiates a fireball action
     * @param enemy - the target enemy object
     */
    public void fireball(Enemy enemy)
    {
        Random numGen = new Random();
        if(this.getMP()>=6)
        {
            this.setMP(this.getMP()-6);
            int min = 10+this.getEmpower(), max = 25+2*this.getEmpower();
            int damage = numGen.nextInt(min, max) - enemy.getDef();
            if(damage<0) damage=0;
            int enemyHP = enemy.getHP() - damage;
            enemy.setDef(0);
            enemy.setHP(enemyHP);
            System.out.printf("%s: Cast a fireball at %s for %d damage!\n",this.getName(),enemy.getName(),damage);
        }
        else System.out.printf("%s: Tried to cast Fireball but didn't realize they didn't have enough MP!\n",this.getName());

        if(enemy.getHP()<=0)
        {
            enemy.setFled(true);
            System.out.printf("%s: Is unable to continue fighting and runs away.\n",enemy.getName());
        }
    }

    /*
     * heal - initiates a heal action
     */
    public void heal()
    {
        Random numGen = new Random();
        if(this.getMP()>=8)
        {
            this.setMP(this.getMP()-8);
            int min = 20+this.getEmpower(), max = 30+2*this.getEmpower();
            int heal = numGen.nextInt(min, max);
            this.setHP(this.getHP()+heal);
            System.out.printf("%s: Healed themselves for %d HP!",this.getName(),heal);
        }
        else System.out.printf("%s: Tried to cast Heal but didn't realize they didn't have enough MP!\n",this.getName());
    }

    /*
     * empower - initiates an empower action
     */
    public void empower()
    {
        if(this.getMP()>=10)
        {
            this.setMP(this.getMP()-10);
            this.setEmpower(this.getEmpower()+1);
            System.out.printf("%s: Powers themselves up!\n",this.getName());
        }
        else System.out.printf("%s: Tried to cast Empower but didn't realize they didn't have enough MP!\n",this.getName());
    }

    /*
     * flee - initiates a flee action
     */
    public void flee()
    {
        this.setFled(true);
        System.out.printf("%s: Has run away from battle!\n",this.getName());
    }
}
