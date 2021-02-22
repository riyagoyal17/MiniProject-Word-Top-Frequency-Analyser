package tech.codingclub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tech.codingclub.utility.FileUtility;
import tech.codingclub.utility.TaskManager;

import java.util.*;

public class TopKeywordAnalyzer implements Runnable
{
    private final String filePath;

    public TopKeywordAnalyzer(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public void run()
    {

        ArrayList<String> data = FileUtility.readFileAsList(filePath);

        HashMap<String,Integer> keywordcounter = new HashMap<String,Integer>();

        for(String row : data)
        {
            String[] keywords = row.split(" ");

            for(String keyword: keywords)
            {
                String word = keyword.toLowerCase();

                if(!keywordcounter.containsKey(word))
                {
                    keywordcounter.put(word,1);
                }

                else
                {
                    Integer count = keywordcounter.get(word);
                    count+=1;
                    keywordcounter.put(word,count);
                }
            }
        }


        ArrayList<KeywordCount>keywordCountArrayList = new ArrayList<KeywordCount>();

        for(String keyword: keywordcounter.keySet())
        {
            keywordCountArrayList.add(new KeywordCount(keyword,keywordcounter.get(keyword)));
        }

        Collections.sort(keywordCountArrayList, new Comparator<KeywordCount>() {
            @Override
            public int compare(KeywordCount o1, KeywordCount o2) {

                if(o2.count == o1.count)
                {
                    return(o1.keyword.compareTo(o2.keyword));
                }
                return(o2.count - o1.count);


            }


        });


       for(KeywordCount keywordcount: keywordCountArrayList)
        {
            System.out.println(keywordcount.keyword + " " +keywordcount.count);
        }


        String json = new Gson().toJson(keywordCountArrayList);
        System.out.println(json);

        String convertJson = "{\"keyword\":\"Hello GSON\",\"count\":1000}";

        KeywordCount keywordcount = new Gson().fromJson(convertJson,KeywordCount.class);
        System.out.println(keywordcount.keyword + " : " + keywordcount.count);

        String convertJsonArray = "{\"keyword\":\"Hello GSON\",\"count\":1000} , {\\\"keyword\\\":\\\"Hello GSON- 1\\\",\\\"count\\\":10}\"";

        ArrayList<KeywordCount> convertedKeywordCount = new Gson().fromJson(convertJsonArray,new TypeToken<KeywordCount>(){}.getType());

        for(KeywordCount keywordcout : convertedKeywordCount)
        {
            System.out.println(keywordcout.keyword + " : " + keywordcout.count);
        }
    }

    public static void main(String[] args) {

        String filepath = "E:\\fsd-coding mafia\\techcodingmafia\\data\\practice\\File\\IndianNationalAnthem.txt";
        TaskManager taskManager = new TaskManager(1);
        taskManager.waitTillQueueIsEmptyAndAddTask(new TopKeywordAnalyzer(filepath));

    }


}
