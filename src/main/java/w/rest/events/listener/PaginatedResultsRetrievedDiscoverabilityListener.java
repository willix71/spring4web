package w.rest.events.listener;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;

import w.rest.events.PaginatedResultsRetrievedEvent;
import w.util.LinkUtil;

@Component
class PaginatedResultsRetrievedDiscoverabilityListener implements ApplicationListener<PaginatedResultsRetrievedEvent> {

    private static final String PAGE = "page";
    private static final String SIZE = "size";
    
    public PaginatedResultsRetrievedDiscoverabilityListener() {
        super();
    }

    // API

    @Override
    public final void onApplicationEvent(final PaginatedResultsRetrievedEvent ev) {
        Preconditions.checkNotNull(ev);

        addLinkHeaderOnPagedResourceRetrieval(ev.getUriBuilder(), ev.getResponse(), ev.getPageSize(), ev.getPage(), ev.getTotalPages());
    }

    void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final int pageSize, final int page, final int totalPages) {
        final StringBuilder linkHeader = new StringBuilder();
        
        if (hasNextPage(page, totalPages)) {
        	appendCommaIfNecessary(linkHeader);
        	linkHeader.append(LinkUtil.createLinkHeader(constructPageUri(uriBuilder, page+1, pageSize), LinkUtil.REL_NEXT));
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(LinkUtil.createLinkHeader(constructPageUri(uriBuilder, totalPages, pageSize), LinkUtil.REL_LAST));
        }
        if (hasPreviousPage(page)) {
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(LinkUtil.createLinkHeader(constructPageUri(uriBuilder, page-1, pageSize), LinkUtil.REL_PREV));
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(LinkUtil.createLinkHeader(constructPageUri(uriBuilder, 0, pageSize), LinkUtil.REL_FIRST));
        }
        
        if (linkHeader.length() > 0) {
            response.addHeader(HttpHeaders.LINK, linkHeader.toString());
        }
    }

    final String constructPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page).replaceQueryParam(SIZE, size).build().encode().toUriString();
    }
    
    final boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1; // 0 based
    }

    final boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    final void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }
}
