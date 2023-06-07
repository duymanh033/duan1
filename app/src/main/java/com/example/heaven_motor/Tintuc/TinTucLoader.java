package com.example.heaven_motor.Tintuc;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TinTucLoader {
    List<TinTuc> tinTucList = new ArrayList<TinTuc>();
    TinTuc tinTuc;
    String textContent;

    public List<TinTuc> getTinTucList(InputStream inputStream) {
        // nội dung tự viết , tham khảo ví dụ product
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // truyền nguồn dữ liệu
            parser.setInput(inputStream, null);
            // xác định event type
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // viết code xử lý ở đây
                String tagName = parser.getName();
                //Log.d("zzzzz", "Tag name =  " + tagName +
                        //", Độ sâu của thẻ = " + parser.getDepth() + ", event = " + eventType);


                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        // bắt đầu vào 1 thẻ
                        if (tagName.equalsIgnoreCase("item")) {
                            tinTuc = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textContent = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(tinTuc != null){
                            //
                            if(tagName.equalsIgnoreCase("item")){
                                tinTucList.add(tinTuc);
                            }

                            if (tagName.equalsIgnoreCase("title")){
                                tinTuc.setTitle( textContent );
                            }

                            if (tagName.equalsIgnoreCase("link"))
                            {
                                tinTuc.setDescription( textContent );
                            }

                        }


                        break;
                    default:
                        //Log.d("zzzz", "eventType khác: " + eventType + ", tag = " + tagName);
                        break;
                }


                // viết lệnh chuyển event type để vòng lặp không bị treo
                // để ở cuối cùng của lệnh while
                eventType = parser.next();
            }


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tinTucList;
    }
}
