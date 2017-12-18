package com.javarush.task.task13.task1328;

abstract public class AbstractRobot implements Attackable, Defensable
{
    private int hitCount;
    public String name;

    public String getName()
    {
        return name;
    }

    public BodyPart attack()
    {
        BodyPart attackedBodyPart = null;
        hitCount = hitCount + 1;
        System.out.println("Atack" + hitCount);
        if (hitCount == 1)
        {
            attackedBodyPart =  BodyPart.ARM;
        } else if (hitCount == 2)
        {
            attackedBodyPart =  BodyPart.HEAD;
        } else if (hitCount == 3)
        {
            attackedBodyPart =  BodyPart.LEG;
        } else if (hitCount == 4){
            attackedBodyPart = BodyPart.CHEST;
        }
        return attackedBodyPart;
    }

    public BodyPart defense()
    {
        BodyPart defencedBodyPart = null;
        //hitCount = hitCount + 1;

        System.out.println("Defens" + hitCount);
        if (hitCount == 1)
        {
            defencedBodyPart =  BodyPart.HEAD;
        } else if (hitCount == 2)
        {
            defencedBodyPart =  BodyPart.LEG;
        } else if (hitCount == 3)
        {
            defencedBodyPart =  BodyPart.ARM;
        } else if (hitCount == 4) {
            defencedBodyPart = BodyPart.CHEST;
        }
        return defencedBodyPart;
    }
}