package com.company;

import java.io.*;
import java.util.ArrayList;

public class DupCuter {
    public static PrintWriter pw2;
    public static BufferedReader BRgenerated = null;
    public static String line;
    static ArrayList<String> parcedlist = new ArrayList<String>(); // список для parced.txt
    static int count = 0;

    public static void DupCuter()
    {

        try {
            File clearparce = new File("clearparced.txt");  //файл со ссылками отпарсенными и очищенными
            if (!clearparce.exists()) {
                clearparce.createNewFile();
                System.out.println("Файл clearparced.txt теперь создан");
            }
        } catch (IOException e) {
            System.out.print("Error: " + e);
        }

        try {
            File file = new File("clearparced.txt");
            pw2 = new PrintWriter(file);
            if (!file.exists())
            {
                file.createNewFile();
            }
        } catch (IOException E)
        {
            System.out.print(E);
        }


        try
        {
            BRgenerated = new BufferedReader(new FileReader("parced.txt"));
            while ((line = BRgenerated.readLine()) != null)
            {
                parcedlist.add(line);
                System.out.println(line);
            }
        } catch (IOException e)
        {
            System.out.print("\n Error: " + e);
        }
        finally {
            try {
                BRgenerated.close();
            }
            catch (Exception e)
            {
                System.out.print(e);
            }
        }


        for ( int i = 0; i < parcedlist.size(); i++ )
        {
            for ( int j = 0; j < parcedlist.size(); j++ )
            {
                if ( i == j )
                {
                    // ничего не делать
                }

                else if ( parcedlist.get( j ).equals( parcedlist.get( i ) ) )
                {
                    parcedlist.remove( j );
                    count++;
                }
            }
        }
        for (int k=0; k < parcedlist.size(); k++)
        {
            pw2.print(parcedlist.get(k) + "\n");
        }
        pw2.close();
        System.out.print(count);

    }
}
