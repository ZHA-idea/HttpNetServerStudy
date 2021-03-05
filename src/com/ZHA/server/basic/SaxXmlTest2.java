package com.ZHA.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlTest2 {
    //SAX解析
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //固定写法
        //1、调用SAX库
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、获取解析器
        SAXParser parser = factory.newSAXParser();
        //3、新建处理器
        PersonHandler handler = new PersonHandler();
        //4、加载Document注册处理器
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/ZHA/server/basic/p.xml"),handler);
        //5、数据拿出来
        List<person> persons = handler.getPersons();
        System.out.println("size="+persons.size());

    }

}
class PersonHandler extends DefaultHandler{

    public List<person> getPersons() {
        return persons;
    }

    private List<person> persons;//这个list在handler里面
    private person p;
    private String tag = "";
    @Override
    public void startDocument() throws SAXException {
        System.out.println("----Started SAX----");
        persons = new ArrayList<person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("解析开始");
        System.out.println("--->"+qName);
        if(qName.equals("person")){
            p = new person();
        }
        tag = qName;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length);//把ch字符数组从start取length个，存在String里面
        System.out.println("-->"+content);
        if (tag!=null){
            if(tag.equals("name")) {
                p.setName(content);
        }
            if(tag.equals("age")){
                p.setAge(content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("解析结束");
        System.out.println("--->"+qName);
        tag = null;
        if(qName.equals("person")) {
            System.out.println(p.name+";"+p.age);
            persons.add(p);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("----End of SAX----");

    }

}
