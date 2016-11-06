package app.media.opp.partytonight;

import android.util.Base64;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a dd.MM.yy");
        System.out.println(simpleDateFormat.format(new Date()));
//        assertEquals(Base64.encode("myemail.ru1".getBytes(), Base64.DEFAULT), "bXllbWFpbC5ydTox");
    }
}