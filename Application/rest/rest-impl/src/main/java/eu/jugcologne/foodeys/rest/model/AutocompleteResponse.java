package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class AutocompleteResponse implements Serializable {
    private static final long serialVersionUID = 6244555924752842821L;
    private String[] options;

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}