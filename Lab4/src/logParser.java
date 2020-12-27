import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logParser
{
    public static void main(String args[]) throws Exception
    {
        final Pattern connection  = Pattern.compile( "(\\d*)\\/(\\d*)\\/(\\d*)\\s(\\d*:\\d*:\\d*).*\\bto\\b:\\s(\\w*)" );
        final Pattern server_version = Pattern.compile("\\#\\#\\s(.*)\\s\\[");
        final Pattern agent_version = Pattern.compile("agent ver:(.*)");

        String PATH = "";
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a file path: ");
            PATH = scanner.nextLine();

            try(BufferedReader br = new BufferedReader(new FileReader(PATH)))
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    // тут ставим ряд матчеров для парсинга
                    Matcher datematcher = connection.matcher(line);
                    Matcher serverver = server_version.matcher(line);
                    Matcher agentver = agent_version.matcher(line);

                    if(datematcher.find())
                    {
                        String year = datematcher.group(1).trim();
                        String month = datematcher.group(2).trim();
                        String day = datematcher.group(3).trim();
                        String time = datematcher.group(4).trim();
                        String date = day + '.' + month + '.' + year;

                        String user = datematcher.group(5).trim();

                        System.out.println("Connection occurred; Datetime: " + date + " " + time + " User: " + user);
                    }

                    if(serverver.find())
                    {
                        String version = serverver.group(1).trim();
                        System.out.println("Server version found; version: " + version);
                    }

                    if(agentver.find())
                    {
                        String version = agentver.group(1).trim();
                        System.out.println("Agent version found; version: " + version);
                    }

//                    System.out.println(line);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
