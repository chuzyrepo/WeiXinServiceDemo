package cn.he110.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.he110.domain.News;
import cn.he110.domain.NewsMessage;
import cn.he110.domain.TextMessage;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	
	/**
	 * xml����תΪmap
	 * @author chuzeyu
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for (Element e : list) {
			map.put(e.getName(),e.getText());
		}
		ins.close();
		return map;
	}
	
	/**
	 * ���ı���Ϣ����תΪxml����
	 * @author chuzeyu
	 * @param textMessage
	 * @return String
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	/**
	 * ���˵�
	 * @author chuzeyu
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ���Ĺ�ע���밴�ղ˵����в�����\n\n");
		sb.append("1������΢��\n");
		sb.append("2����㿴��\n\n");
		sb.append("3������Ѭ��\n\n");
		sb.append("�ظ��������˲˵���");
		return sb.toString();
		
	}
	/**
	 * ��װ�ı���Ϣ�ظ���
	 * @author chuzeyu
	 * */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	/**
	 * ѡ��˵�1�ظ�
	 * @author chuzeyu
	 * */
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("���ʣ�����ô������");
		return sb.toString();
	}
	/**
	 * ѡ��˵�2�ظ�
	 * @author chuzeyu
	 * */
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("����Ϲ��ʲô�أ����������΢�ţ�\n\n");
		sb.append("�������ӣ��˼�Сȭȭ�����ؿڣ����������ӣ�����\n\n");
		sb.append("������ʾ�ظ�1����΢�ţ�����");
		return sb.toString();
	}
	
	
	/**
	 * ��ͼ����Ϣ����תΪxml����
	 * @author chuzeyu
	 * @param textMessage
	 * @return String
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new News().getClass());
		return xStream.toXML(newsMessage);
	}
	
	/**
	 * ��װͼ����Ϣ�ظ���
	 * @author chuzeyu
	 * */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		News news = new News();
		news.setTitle("������");
		news.setDescription("�����������䲻�䣡����");
		news.setPicUrl("http://203.195.163.143/WeiXinServiceDemo/image/ninpeima.png");
		news.setUrl("www.baidu.com");
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		
		return message;
	}
}
