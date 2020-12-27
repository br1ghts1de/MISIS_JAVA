import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iniParser
{
    final Pattern  _section  = Pattern.compile( "\\s*\\[([^]]*)\\]\\s*" );
    final Pattern  _keyValue = Pattern.compile( "\\s*([^=]*)=(.*)" );
    final Map<String, Map<String, String>>  _entries  = new HashMap<>();
    final ByteArrayOutputStream _iniContent = new ByteArrayOutputStream();
    String path_to_file;

    public iniParser(String path) throws Exception
    {
        path_to_file = path;
        load();
        System.out.println(_entries);
    }


    private void load() throws Exception
    {
        try(BufferedReader br = new BufferedReader(new FileReader(path_to_file)))
        {
            String line;
            String section = null;
            while((line = br.readLine()) != null)
            {
                Matcher m = _section.matcher(line);
                if( m.matches())
                    section = m.group(1).trim();
                else
                if(section != null)
                {
                    m = _keyValue.matcher(line);
                    if(m.matches())
                    {
                        String key = m.group(1).trim();
                        String value = m.group(2).trim();
                        Map<String, String> kv = _entries.get(section);
                        if(kv == null)
                            _entries.put(section, kv = new HashMap<>());
                        kv.put(key, value);
                    }
                }
            }
        }
    }
}
