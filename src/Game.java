import java.util.Scanner;

public class Game {

    static Scanner in = new Scanner(System.in);
    static BattleManager battleManager = new BattleManager();

    /*
     * inputRange - allows user to input an integer between the two given integers.
     * @param lower - defines the lower bound of the input range
     * @param upper - defines the upper bound of the input range
     * @return - a user defined integer between the specified bounds
     */
    public static int inputRange(int lower, int upper)
    {
        int val = 0;
        boolean done = false;
        do {
            if(upper != Integer.MAX_VALUE)
            {
                if(in.hasNextInt()) {
                    val = in.nextInt();
                    if (val <lower || val > upper) {
                        System.out.printf("[!]Please enter between %d and %d\n",lower,upper);
                    }
                    else {
                        done = true;
                    }
                }
                else {
                    System.out.println("[!]Please enter an integer");
                    in.next();
                }
            }
            else
            {
                if(in.hasNextInt()) {
                    val = in.nextInt();
                    if (val < lower) {
                        System.out.printf("[!]Please enter %d or above\n",lower);
                    }
                    else {
                        done = true;
                    }
                }
                else {
                    System.out.println("[!]Please enter an integer");
                    in.next();
                }
            }
        } while (!done);

        return val;
    }

    /*
     * inputString - allows user to input a string
     * @return - a user defined string
     */
    public static String inputString()
    {
        String str = "";
        boolean done = false;
        do {
            if(in.hasNextLine()) {
                str = in.nextLine();
                if(!str.equals(""))
                {
                    done=true;
                }
            }
        } while(!done);

        return str;
    }

    public static void main(String[] args)
    {
        int numPlayers, numEnemies;

        System.out.println("How many players do you want?");
        numPlayers = inputRange(1, Integer.MAX_VALUE);
        System.out.println("How many enemies do you want?");
        numEnemies = inputRange(1, Integer.MAX_VALUE);

        Player[] players = new Player[numPlayers];
        Enemy[] enemies = new Enemy[numEnemies];

        for(int i=0;i<players.length;i++)
        {
            String name;
            System.out.printf("What will the name of player %d be?\n",i+1);
            name = inputString();
            players[i] = new Player(name);
        }

        for(int i=0;i<enemies.length;i++)
        {
            enemies[i] = new Enemy();
        }

        battleManager.start(players,enemies);
        in.close();
    }
}
