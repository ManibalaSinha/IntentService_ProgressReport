package com.example.android.intentservice.parsers;

import com.example.android.intentservice.model.DataItem;
//import com.example.android.restful.model.DataItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyXMLParser {

    public static DataItem[] parseFeed(String content) {

        try {

            boolean inItemTag = false;
            String currentTagName = "";
            DataItem currentItem = null;
            List<DataItem> itemList = new ArrayList<>();
//XmlPullParser is a streaming parser, it reads through XML content & notifies use of call back methods of certain events, including when it read start and end tag of element
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            //content that parsing to parser object
            parser.setInput(new StringReader(content));
//parsing for eventType
            int eventType = parser.getEventType();
//go to XML document on web services until End_document
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    // START_TAG means beginning element
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("item")) {
                            inItemTag = true;
                            currentItem = new DataItem();
                            itemList.add(currentItem);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            inItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        String text = parser.getText();
                        if (inItemTag && currentItem != null) {
                            try {
                                switch (currentTagName) {
                                    case "itemName":
                                        currentItem.setItemName(text);
                                        break;
                                    case "description":
                                        currentItem.setDescription(text);
                                        break;
                                    case "category":
                                        currentItem.setCategory(text);
                                        break;
                                    case "price":
                                        currentItem.setPrice(Double.parseDouble(text));
                                        break;
                                    case "sort":
                                        currentItem.setSort(Integer.parseInt(text));
                                        break;
                                    case "image":
                                        currentItem.setImage(text);
                                    default:
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
//until moves to parser next event
                eventType = parser.next();

            } // end while loop
//converting list into array
            DataItem[] dataItems = new DataItem[itemList.size()];
            return itemList.toArray(dataItems);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}


