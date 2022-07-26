package org.springframework.samples.petclinic.ImpactAnalyzer.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HTMLJSONFileParser {
    private String HTML_JSON_FILE_PATH;
    private List<HTMLElement> htmlElements;

    public HTMLJSONFileParser(String HTMLJSONFilePath){
        this.HTML_JSON_FILE_PATH = HTMLJSONFilePath;
        this.htmlElements = new ArrayList<HTMLElement>();
    }
    public List<HTMLElement> getHTMLElements() {
        return htmlElements;
    }
    public void setElements(){
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
            //Parsing the contents of the JSON file
            JSONArray jsonArray = null;
            try {
                jsonArray = (JSONArray) jsonParser.parse(new FileReader(HTML_JSON_FILE_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int counter = 0;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                HTMLElement htmlElement = new HTMLElement();
                JSONObject jsonObject = iterator.next();
                String tag = (String) jsonObject.get("tag");
                if(!tag.equals(""))
                    htmlElement.setTag(tag);
                String xpath = (String) jsonObject.get("xpath");
                if(!xpath.equals(""))
                    htmlElement.setXpath(xpath);
                String id = (String) jsonObject.get("id");
                if(!id.equals(""))
                    htmlElement.setId(id);
                String className = (String) jsonObject.get("class");;
                if(!className.equals(""))
                    htmlElement.setClassName(className);
                String value = (String) jsonObject.get("value");
                if(!xpath.equals(""))
                    htmlElement.setValue(value);
//                String name = (String) jsonObject.get("name");
//                if(!name.equals(""))
//                    htmlElement.setName(name);

                counter++;


                    htmlElements.add(htmlElement);


            }
    }
}
