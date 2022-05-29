
import dao.DBConnection;
import domain.Voter;
import handler.XMLHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();
        DBConnection.getConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new handler.XMLHandler();
        parser.parse(new File(fileName), handler);
        XMLHandler.getData();
        System.out.println(System.currentTimeMillis() - start);


    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();
            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();

            DBConnection.countVoter(name, birthDay);
        }

    }

    public static StringBuilder prefixExplore(long usage) {
        StringBuilder builder = new StringBuilder();
        usage = usage / 8;
        int prefixNum = 0;
        for (; usage > 1024; ) {
            usage = usage / 1024;
            prefixNum++;
        }
        builder.append(usage);
        builder.append(" ");
        switch (prefixNum) {
            case 1:
                builder.append("Килобайт");
                break;
            case 2:
                builder.append("Мегабайт");
                break;
            case 3:
                builder.append("Гигабайт");
                break;
        }
        return builder;
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}