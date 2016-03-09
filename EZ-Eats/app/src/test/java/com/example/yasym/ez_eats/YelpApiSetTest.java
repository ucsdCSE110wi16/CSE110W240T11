package com.example.yasym.ez_eats;

import com.example.yasym.ez_eats.Yelp.Yelp;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static junit.framework.Assert.assertTrue;

/**
 * Created by simon on 3/9/16.
 */
public class YelpApiSetTest {
    private Map<String, String> params;
    private Map<String, String> invalidParams;
    private Class yelpClass;

    private static final String TERM = "Donuts";
    private static final boolean DEALS = true;
    private static final int LIMIT = 10;
    private static final int OFFSET = 10;
    private static final int RADIUS = 10;

    private static final int INVALID_LIMIT = -10;
    private static final int INVALID_OFFSET = -10;
    private static final int INVALID_RADIUS = -10;

    private static final String PARAMS_FIELD = "params";

    @Before
    public void setupApi() throws Exception {
        Yelp api = new Yelp(null);
        api.setTerm(TERM).setDeals(DEALS).setLimit(LIMIT).setOffset(OFFSET).setRadius(RADIUS);
        params = getParams(api);
        Yelp invalidApi = new Yelp(null);
        invalidApi.setLimit(INVALID_LIMIT).setOffset(INVALID_OFFSET).setRadius(INVALID_RADIUS);
        invalidParams = getParams(invalidApi);
    }

    private Map<String, String> getParams(Yelp api) throws Exception {
        Field f = api.getClass().getDeclaredField(PARAMS_FIELD);
        f.setAccessible(true);
        return (Map<String, String>)f.get(api);
    }

    @Test
    public void testSetters() throws Exception {
        assertTrue(params.get("term").equals(TERM));
        assertTrue(params.get("deals_filter").equals(String.valueOf(DEALS)));
        assertTrue(params.get("offset").equals(String.valueOf(OFFSET)));
        assertTrue(params.get("radius_filter").equals(String.valueOf(RADIUS)));
        assertTrue(params.get("limit").equals(String.valueOf(LIMIT)));
    }

    @Test
    public void testSettersInvalid() throws Exception {
        assertTrue(params.containsKey("offset"));
        assertTrue(params.containsKey("radius_filter"));
        assertTrue(params.containsKey("limit"));
    }
}
