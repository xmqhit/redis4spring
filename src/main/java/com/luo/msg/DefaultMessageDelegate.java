package com.luo.msg;

import java.io.Serializable;
import java.util.Map;

public class DefaultMessageDelegate  implements MessageDelegate {

	public void handleMessage(String message) {
		System.out.println("you:"+message);
	}

	public void handleMessage(Map message) {
		System.out.println(message);
	}

	public void handleMessage(byte[] message) {
	   System.out.println(new String(message));
	}

	public void handleMessage(Serializable message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	public void handleMessage(Serializable message, String channel) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

}
