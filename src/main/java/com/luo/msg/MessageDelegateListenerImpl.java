package com.luo.msg;

import java.io.UnsupportedEncodingException;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class  MessageDelegateListenerImpl implements MessageListener   {

	public void onMessage(Message msg, byte[] arg) {
		 try {
			System.out.println("me:"+new String(msg.getBody(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
