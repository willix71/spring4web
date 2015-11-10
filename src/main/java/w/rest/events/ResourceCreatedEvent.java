package w.rest.events;


import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceCreatedEvent extends ApplicationEvent {
	private static final long serialVersionUID = -1680494731266864169L;

	private final HttpServletResponse response;
    private final long idOfNewResource;

    public ResourceCreatedEvent(final Object source, final HttpServletResponse response, final long idOfNewResource) {
        super(source);

        this.response = response;
        this.idOfNewResource = idOfNewResource;
    }

    // API

    public HttpServletResponse getResponse() {
        return response;
    }

    public long getIdOfNewResource() {
        return idOfNewResource;
    }

}
