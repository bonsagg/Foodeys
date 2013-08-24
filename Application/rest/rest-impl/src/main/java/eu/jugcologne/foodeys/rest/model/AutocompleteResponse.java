package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class AutocompleteResponse implements Serializable {
    private static final long serialVersionUID = 6244555924752842821L;
    private List<String> options;

    public AutocompleteResponse() {
    }

    public AutocompleteResponse(List<String> options) {

        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}