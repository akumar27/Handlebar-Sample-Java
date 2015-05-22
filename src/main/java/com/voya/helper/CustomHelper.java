/**
 * 
 */
package com.voya.helper;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;

/**
 * @author i707259
 *
 */
public class CustomHelper {

	public CustomHelper() {
	}

	public CharSequence process_module(Object templateToProcess, Object data, Options options)
			throws IOException {
		/*
		 * if (!text1.equals(text2)) { return
		 * options.inverse(Context.newContext(options.context, text1)); } else {
		 * return options.fn(Context.newContext(options.context, text1)); }
		 */
	
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree("{\"config\": " + data + "}");
		Context context = Context.newBuilder(jsonNode)
				.resolver(JsonNodeValueResolver.INSTANCE).build();
		String response = options.handlebars.compile("" + templateToProcess).apply(context);
		//System.out.println("response: " + response);
		return response;		
	}

}
