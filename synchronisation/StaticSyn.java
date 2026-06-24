// Java program of multithreading
// with static synchronized

class Display
{
    public static synchronized void wish(String name)
    {
        for(int i=0;i<3;i++)
        {
            System.out.print("Good Morning: ");
            System.out.println(name);
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException e)
            {
            }
        }
    }
}

class StaticSynThread extends Thread{
    Display d;
    String name;
    StaticSynThread(Display d,String name)
    {
        this.d=d;
        this.name=name;
    }
    public void run()
    {
        d.wish(name);
    }
}

class StaticSynMain{
    public static void main(String arg[])
    {
        Display d1=new Display();
        Display d2=new Display();
        StaticSynThread t1=new StaticSynThread(d1,"Dhoni");
        StaticSynThread t2=new StaticSynThread(d2,"Yuvraj");
        t1.start();
        t2.start();
    }
}
