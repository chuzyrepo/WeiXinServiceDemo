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
	 * xml类型转为map
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
	 * 对文本消息对象转为xml类型
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
	 * 主菜单
	 * @author chuzeyu
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单进行操作：\n\n");
		sb.append("1、加我微信\n");
		sb.append("2、随便看看\n\n");
		sb.append("3、接收熏陶\n\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
		
	}
	/**
	 * 组装文本消息回复体
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
	 * 选择菜单1回复
	 * @author chuzeyu
	 * */
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("请问，您配么？？？");
		return sb.toString();
	}
	/**
	 * 选择菜单2回复
	 * @author chuzeyu
	 * */
	
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("你在瞎看什么呢？还不快加我微信！\n\n");
		sb.append("你若不加，人家小拳拳捶你胸口！！！嘤嘤嘤！！！\n\n");
		sb.append("友情提示回复1加我微信！！！");
		return sb.toString();
	}
	
	
	/**
	 * 对图文消息对象转为xml类型
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
	 * 组装图文消息回复体
	 * @author chuzeyu
	 * */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		News news = new News();
		news.setTitle("您配吗？");
		news.setDescription("吴彦祖问您配不配！！！");
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
