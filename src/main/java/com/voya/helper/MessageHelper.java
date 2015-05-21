/**
 * 
 */
package com.voya.helper;

import java.io.IOException;

import com.github.jknack.handlebars.Options;

/**
 * @author i707259
 *
 */
public class MessageHelper {

	public MessageHelper() {
	}

	public CharSequence sayHello(Object name, Options options)
			throws IOException {
		return "Hello " + name + "!!!, How are you doing? This message is printed through a custom helper.";
	}

}
