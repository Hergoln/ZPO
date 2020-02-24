package com.company.lab10pkg;

import javassist.*;

public class MetaBetaProgramming
{
    public static void doStuff(String[] args)
    {
        try{

            ClassPool pool = ClassPool.getDefault();
            // Here might be neccessery to point out exact location of this package
            pool.insertClassPath("..\\lab10pkg");
            Loader loader = new Loader(pool);

            // Point
            CtClass cPoint = pool.get("Point");
            CtMethod pointM = cPoint.getDeclaredMethod("move");
            pointM.setBody("{ System.out.println(\"first arg = \" + $1 + \";" +
                    " second argument = \" + $2);\n" +
                    " this.x += $2;\n" +
                    " this.y += $1;\n" +
                    "}");

            Class point = loader.loadClass("Point");
            point.getDeclaredMethod("main", new Class[] { String[].class })
                    .invoke(null, new Object[] { args });

            // Factorial
            CtClass cFact = pool.get("Factorial");
            CtMethod[] factM = cFact.getDeclaredMethods();
            for (CtMethod m : factM)
            {
                String flowName = m.getName()+"flow";
                m.useCflow(flowName);
                if(m.getModifiers() == Modifier.STATIC)
                {
                    m.insertBefore("if($cflow("+flowName+") == 1) System.out.println(\"method " + m.getName() + " is recursive\"); ");
                    System.out.println(m.getName());
                }
            }

            Class factorial = loader.loadClass("Factorial");
            factorial.getDeclaredMethod("main", new Class[] { String[].class })
                    .invoke(null, new Object[] { args });
        }
        catch (Exception exc)
        {
            System.out.println(exc);
        }
    }
}
