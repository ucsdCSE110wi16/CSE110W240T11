package com.example.yasym.ez_eats;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by ymno1 on 3/9/2016.
 */
public class SetGetTest {
    @Test
     public void testSetGetThreoshold() throws Exception {
        int testValue = 10;

        QuestionActivity.setThreshold(testValue);
        assertEquals(QuestionActivity.getThreshold(), testValue);
    }

    @Test
    public void testSetGetTerm() throws Exception {
        String testTerm = "sandwich";

        QuestionActivity.setTerm(testTerm);
        assertEquals(QuestionActivity.getTerm(), testTerm);
    }
}
