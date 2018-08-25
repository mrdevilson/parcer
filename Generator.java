package com.company;

import java.io.*;
import java.util.ArrayList;

public class Generator {

    static ArrayList<String> keywordslist = new ArrayList<String>(); // список для keywords
    static ArrayList<String> inurllist = new ArrayList<String>(); // список для inurl
    public static PrintWriter pw;

    public static void Generator()
    {
        String line; // для считывания из файла keys.txt
        BufferedReader BRkeywords = null; // для считывания из файла keywords
        BufferedReader BRinurl = null; //для считывания из файла inurl



        //Проверяем наличие файла keywords
        try {
            File keys = new File("keywords.txt");  //файл со словами. Если файла нет, создаем
            if (!keys.exists()) {
                keys.createNewFile();
                System.out.println("Файл keywords.txt не найден. Сейчас я его создал, заполните его");
            }
        } catch (IOException e) {System.out.print("Error: " + e);}

        //Проверяем наличие файла keywords
        try {
            File inurl = new File("inurl.txt");  //файл с ключами. Если файла нет, создаем
            if (!inurl.exists()) {
                inurl.createNewFile();
                System.out.println("Файл inurl.txt не найден. Сейчас я его создал, заполните его");
            }
        } catch (IOException e) {System.out.print("Error: " + e);}

        // Заполняем список keywords
        try
        {
            BRkeywords = new BufferedReader(new FileReader("keywords.txt"));
            while ((line = BRkeywords.readLine()) != null)
            {
                keywordslist.add(line);
                System.out.println(line);
            }
        } catch (IOException e)
        {
            System.out.print("\n Error: " + e);
        }
        finally {
                try {
                        BRkeywords.close();
                    }
                catch (Exception e)
                {
                    System.out.print(e);
                }
        }

        // Заполняем спиок inurl
        try
        {
            BRinurl = new BufferedReader(new FileReader("inurl.txt"));
            while ((line = BRinurl.readLine()) != null)
            {
                inurllist.add(line);
                System.out.println(line);
            }
        } catch (IOException e)
        {
            System.out.print("\n Error: " + e);
        }
        finally {
            try {
                BRinurl.close();
            }
            catch (Exception e)
            {
                System.out.print(e);
            }
        }


        try {
            File file = new File("generated.txt");
            pw = new PrintWriter(file);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException E)
        {System.out.print(E);}

        //цикл с вызовом метода генерации ссылок

        for (int i = 0; i < keywordslist.size(); i++)
        {
            for (int j = 0; j < inurllist.size(); j++)
            {
                // вызвать метод для поиска в бинге
                bing(keywordslist.get(i), inurllist.get(j));
            }
        }
        pw.close();

        //метод  для генерирования и записи ссылок

    }
    public static void bing (String a, String b)
    {
        for (int i = 0; i<10; i++)
        {
            String c = "https://www.bing.com/search?q=" + a + "+inanchor:" + b + "&first=+" + i + "1";
            System.out.println(c);
            pw.print(c + "\n");
        }

    }


}
