package com.company;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parcer
{
    public static BufferedReader BRgenerated = null;
    public static String line;
    static ArrayList<String> generatedlist = new ArrayList<String>(); // список для generated.txt
    static ArrayList<String> matches = new ArrayList<String>(); //список с совпадениями
    public static String source = "";
    public static PrintWriter pw1;





    public static String getURLSource(String url) throws IOException //для отправки запроса
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }
    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }


    public static void regex(String link)
    {
        Pattern p = Pattern.compile("<a\\s+href=\\\"(https?)([^\\\"]+)\\\"");
        Matcher m = p.matcher(link);
        //if (m.find())
        //{
         //   for (int i=0; i < m.groupCount(); i++)
           // {
              //  matches.add(m.group(i));
            //}
       // }
        while (m.find())
        {
            matches.add(m.group(1) + m.group(2));
        }

    }


    public static void Parcer() {

        try {
            File keys = new File("parced.txt");  //файл со ссылками отпарсенными
            if (!keys.exists()) {
                keys.createNewFile();
                System.out.println("Файл parced.txt теперь создан");
            }
        } catch (IOException e) {System.out.print("Error: " + e);}

        try {
            File file = new File("parced.txt");
            pw1 = new PrintWriter(file);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException E)
        {System.out.print(E);}



        //считываем ссылки
        try
        {
            BRgenerated = new BufferedReader(new FileReader("generated.txt"));
            while ((line = BRgenerated.readLine()) != null)
            {
                generatedlist.add(line);
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

        for (int i=0; i < generatedlist.size(); i++) // перебираем все ссылки из фала  generated.txt
        {
            try
            {
                source = getURLSource(generatedlist.get(i));
                regex(source);
                for(int j=0; j < matches.size(); j++) // выбираем все ссылки со страницы и заносим в файл
                {
                    pw1.print(matches.get(j) + "\n");
                }
                matches.clear();

            }
            catch (IOException e)
            {
                System.out.print(e);
                pw1.close();
            }
            source = null;
            //<a\s+href=\"(https?)([^\"]+)\"
        }
        System.out.print(source);
        pw1.close();




    }
}
