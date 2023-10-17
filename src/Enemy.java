import java.io.FileNotFoundException;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
public class Enemy extends Character{

    public Enemy()
    {
        super(80,0,0,0);
        Random numGen = new Random();
        this.setSpd(numGen.nextInt(0,50+1));
        try {
            generateName();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Please move the file \"names.txt\" to \"./src/names.txt.\"");
        }
    }



    /*
     * generateName - Generates a random name from the file names.txt and automatically assigns the generated name to the object.
     */
    public void generateName() throws FileNotFoundException {
        File file = new File("./src/names.txt");
        Scanner reader = new Scanner(file);
        Random numGen = new Random();
        String name="";
        for(int i= 0;i<=numGen.nextInt(0,95+1);i++)
        {
            name = reader.nextLine();
        }
        this.setName(name);
    }

    /*
     * attack - initiates an attack action
     * @param player - the target player object
     */
    public void attack(Player player)
    {
        Random numGen = new Random();
        for(int i=0;i<2;i++)
        {
            if(numGen.nextInt(0,100+1)<50)
            {
                int damage = numGen.nextInt(3,7+1) - player.getDef();
                if(damage<0) damage=0;
                int playerHP = player.getHP() - damage;
                player.setDef(0);
                player.setHP(playerHP);
                System.out.printf("%s: Hit %s for %d damage!\n",this.getName(),player.getName(),damage);
            }
            else
            {
                if(player.getDef()!=5) player.setDef((player.getDef()+1));
                System.out.printf("%s: Tried to attack %s but missed.\n",this.getName(), player.getName());
            }
        }

        if(player.getHP()<=0)
        {
            player.setFled(true);
            System.out.printf("%s: Is unable to continue fighting and runs away.\n",player.getName());
        }
    }
}
