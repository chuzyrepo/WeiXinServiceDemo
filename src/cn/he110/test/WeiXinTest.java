package cn.he110.test;

import cn.he110.domain.AccessToken;
import cn.he110.util.WeiXinUtil;

public class WeiXinTest {

	public static void main(String[] args) {
		AccessToken token = WeiXinUtil.getAccessToken();
		System.out.println("Ʊ��"+token.getToken());
		System.out.println("��Чʱ��"+token.getExpiresIn());
	}

}
