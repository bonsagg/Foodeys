package eu.jugcologne.foodeys.rest.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import eu.jugcologne.foodeys.rest.model.AutocompleteResponse;

@Provider
@Produces({ "application/json" })
public class AutocompleteResponseWriter implements MessageBodyWriter<AutocompleteResponse> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	return true;
    }

    @Override
    public long getSize(AutocompleteResponse t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	return -1;
    }

    @Override
    public void writeTo(AutocompleteResponse ar, Class<?> type, Type genericType, Annotation[] annotations, MediaType mt, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException, WebApplicationException {
	if (mt.getType().equals("application") && mt.getSubtype().equals("json")) {
	    StringBuffer buffer = new StringBuffer();

	    buffer = buffer.append("{");
	    buffer = buffer.append("\"options\":");
	    buffer = buffer.append("[");

	    String prefix = "";
	    for (String s : ar.getOptions()) {
		buffer.append(prefix);
		buffer = buffer.append("\"");
		prefix = ",";
		buffer.append(s);
		buffer = buffer.append("\"");
	    }

	    buffer = buffer.append("]");
	    buffer = buffer.append("}");

	    try (PrintStream printStream = new PrintStream(out)) {
		printStream.print(buffer.toString());
	    }

	    return;
	}

	throw new UnsupportedOperationException("Not supported MediaType: " + mt);
    }
}