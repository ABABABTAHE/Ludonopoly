package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper; 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonFile {

    public static String ToJSON(int  position, String version) {
        String pos = String.valueOf(position);
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map;
        String filepathJSON = "/versions/" + version + ".json";
        String outputJSON = null;

        try {
            InputStream inputStream = JsonFile.class.getResourceAsStream(filepathJSON);//json file take read korar jonno
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); {
                String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                map = mapper.readValue(contents, Map.class);//json string ke map e convert kore
                outputJSON = (String) map.get(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputJSON;
    }
}

