import java.util.Random;
public class BattleManager {

    private final Random numGen = new Random();
    private Player[] players;
    private Enemy[] enemies;
    private Character[] order;
    private int current;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public Character[] getOrder() {
        return order;
    }

    public void setOrder(Character[] order) {
        this.order = order;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    /*
     * start - begins the battle sequence and initializes turn order
     * @param players - array of participating players
     * @param enemies - array of participating enemies
     */
    public void start(Player[] players, Enemy[] enemies)
    {
        this.setCurrent(0);
        this.setPlayers(players);
        this.setEnemies(enemies);
        this.setOrder(new Character[getPlayers().length+getEnemies().length]);
        int slot = 0;

        //sort based on speed
        for(int i=0;i<=50;i++)
        {
            for(int j=0;j<getEnemies().length;j++)
            {
                //check enemies first
                if(enemies[j].getSpd()==i)
                {
                    order[slot] = enemies[j];
                    slot++;
                }
            }
            for(int j=0;j<getPlayers().length;j++)
            {
                //then players
                if(players[j].getSpd()==i)
                {
                    order[slot] = players[j];
                    slot++;
                }
            }
        }

        System.out.println("Battle Start!");
        System.out.println("Turn order:");
        for(int i=0;i<order.length;i++)
        {
            if(order[i] instanceof Player) System.out.printf("%d: %-20sHP: %d\tMP: %d\n",i+1,order[i].getName(),order[i].getHP(),(order[i]).getMP());
            else System.out.printf("%d: %-20sHP: %d\n",i+1,order[i].getName(),order[i].getHP());
        }

        this.next();
    }


    /*
     * next - initiates the next turn in the battle sequence (turn count tracked outside of function)
     */
    public void next()
    {
        if(!order[this.getCurrent()].isFled()) System.out.printf("\nIt is %s's turn!\n",order[this.getCurrent()].getName());

        if(order[this.getCurrent()] instanceof Player && !order[this.getCurrent()].isFled())
        {
            System.out.println("Choose an action:\n1. Basic Attack\n2. Fireball\n3. Heal\n4. Empower\n5. Flee");
            int choice = Game.inputRange(1,5);
            switch(choice)
            {
                case 1:
                    System.out.println("Select a target:");
                    for(int i=0;i<getEnemies().length;i++)
                    {
                        System.out.printf("%d. %-15sHP: %d\n",i+1,this.getEnemies()[i].getName(),this.getEnemies()[i].getHP());
                    }
                    choice = Game.inputRange(1,getEnemies().length);
                    ((Player)order[this.getCurrent()]).basicAttack(this.getEnemies()[choice-1]);
                    break;
                case 2:
                    System.out.println("Select a target:");
                    for(int i=0;i<getEnemies().length;i++)
                    {
                        System.out.printf("%d. %-15sHP: %d\n",i+1,this.getEnemies()[i].getName(),this.getEnemies()[i].getHP());
                    }
                    choice = Game.inputRange(1,this.getEnemies().length);
                    ((Player)order[this.getCurrent()]).fireball(this.getEnemies()[choice-1]);
                    break;
                case 3:
                    ((Player)order[this.getCurrent()]).heal();
                    break;
                case 4:
                    ((Player)order[this.getCurrent()]).empower();
                    break;
                case 5:
                    ((Player)order[this.getCurrent()]).flee();
                    break;
            }
        }
        else if(!order[this.getCurrent()].isFled())
        {
            if(this.getPlayers().length>1) ((Enemy)order[this.getCurrent()]).attack(this.getPlayers()[numGen.nextInt(0,this.getPlayers().length)]);
            else ((Enemy)order[this.getCurrent()]).attack(this.getPlayers()[0]);
        }

        this.cleanUp();

        if(!this.hasEnded())
        {
            System.out.println("End of turn stats:");
            for(int i=0;i<order.length;i++)
            {
                if(order[i] instanceof Player && !order[i].isFled()) System.out.printf("%d: %-20sHP: %d\tMP: %d\n",i+1,order[i].getName(),order[i].getHP(),(order[i]).getMP());
                else if (!order[i].isFled()) System.out.printf("%d: %-20sHP: %d\n",i+1,order[i].getName(),order[i].getHP());
            }
            this.setCurrent(this.getCurrent()+1);
            if(this.getCurrent()>order.length-1) this.setCurrent(0);
            this.next();
        }
    }

    /*
     * hasEnded - test to see if the conditions needed to end a battle are true (either side runs out of fighters) and end the battle if so
     * @return - true if either condition is met
     */
    public boolean hasEnded()
    {
        if(this.getPlayers().length == 0)
        {
            System.out.println("Game Over!  You have no more remaining fighters.");
            return true;
        }

        if(this.getEnemies().length == 0)
        {
            System.out.println("You Win!  There are no more enemies who can fight.");
            return true;
        }

        return false;
    }

    /*
     * cleanUp - sorts through all entity list variables (players, enemies, order) to clean up the list
     */
    public void cleanUp()
    {
        int playersFled=0;
        for(int i=0;i<this.getPlayers().length;i++)
        {
            if(this.getPlayers()[i].isFled()) playersFled++;
        }

        int enemiesFled=0;
        for(int i=0;i<this.getEnemies().length;i++)
        {
            if(this.getEnemies()[i].isFled()) enemiesFled++;
        }

        Player[] tempP = new Player[this.getPlayers().length-playersFled];
        boolean removed = false;
        for(int i=0;i<this.getPlayers().length;i++)
        {
            if(!this.getPlayers()[i].isFled() && !removed) tempP[i]=this.getPlayers()[i];
            else if(!this.getPlayers()[i].isFled()) tempP[i-1]=this.getPlayers()[i];
            else removed = true;
        }
        this.setPlayers(tempP);

        Enemy[] tempE = new Enemy[this.getEnemies().length-enemiesFled];
        removed = false;
        for(int i=0;i<this.getEnemies().length;i++)
        {
            if(!this.getEnemies()[i].isFled() && !removed) tempE[i]=this.getEnemies()[i];
            else if(!this.getEnemies()[i].isFled()) tempE[i-1]=this.getEnemies()[i];
            else removed = true;
        }
        this.setEnemies(tempE);

        Character[] tempO = new Character[order.length-playersFled-enemiesFled];
        removed = false;
        for(int i=0;i<order.length;i++)
        {
            if(!order[i].isFled() && !removed) tempO[i]=order[i];
            else if(!order[i].isFled()) tempO[i-1]=order[i];
            else removed = true;
        }
        order = tempO;
    }


}
