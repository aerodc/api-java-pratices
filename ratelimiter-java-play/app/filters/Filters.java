package filters;

import play.http.DefaultHttpFilters;

import javax.inject.Inject;

public class Filters extends DefaultHttpFilters {

    @Inject
    public Filters(GlobalRatelimitFilter globalRatelimitFilter){
        super(globalRatelimitFilter);
    }
}
