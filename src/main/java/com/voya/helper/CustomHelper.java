/**
 * 
 */
package com.voya.helper;

import java.io.IOException;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Options;

/**
 * @author i707259
 *
 */
public class CustomHelper {

	public CustomHelper() {
	}

	public CharSequence if_eq(Object text1, Object text2, Options options)
			throws IOException {
		if (!text1.equals(text2)) {
			return options.inverse(Context.newContext(options.context, text1));
		} else {
			return options.fn(Context.newContext(options.context, text1));
		}
	}

}
