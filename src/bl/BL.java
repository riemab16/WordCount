package bl;

import java.util.HashMap;
import java.util.LinkedList;

public class BL {

    private String text;
    private String inputName;

    public BL(String text, String inputName) {
        this.text = text;
        this.inputName = inputName;
    }

    public HashMap<String, Integer> countWords() {
        String[] parts = text.split(" ");
        HashMap<String, Integer> map = new HashMap();
        for (String word : parts) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        
        LinkedList<String> keys = new LinkedList<String>();
        for (String key : map.keySet()) {
            if(map.get(key) < 2){
                keys.add(key);
            }
        }
        for(String key : keys){
            map.remove(key);
        }
        return map;
    }

    public String getFilename() {
        return inputName;
    }

}

