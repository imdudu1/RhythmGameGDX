package kr.bugfix.game.system;

import java.util.Random;

public class GameUtils
{
    private Random random;
    private static GameUtils instance = null;

    private GameUtils() {
        random = new Random();
    }

    public int getRandomInt(int end)
    {
        return random.nextInt(end);
    }

    public static GameUtils getInstance()
    {
        if (GameUtils.instance == null)
        {
            GameUtils.instance = new GameUtils();
        }
        return GameUtils.instance;
    }
}