package systemy.bankowe.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import systemy.bankowe.util.MapUtil;

public class MapUtilTest {

    private static final String SEPARATOR = "#@";
    private static final String MAP_ENTRY_KEY_FORMAT = "(key=%s; value=%s)";

    @Test
    public void shouldReturnEmptyString() throws Exception {
        // given

        // when
        String result = MapUtil.toString(new HashMap<String, String>());

        // then
        assertEquals("", result);
    }

    @Test
    public void shouldReturnEmptyString_withSeparator() throws Exception {
        // given

        // when
        String result = MapUtil.toString(new HashMap<String, String>(), SEPARATOR);

        // then
        assertEquals("", result);
    }

    @Test
    public void shouldReturnFirstElement() throws Exception {
        // given
        String value = "napis";
        String key = "key";
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);

        // when
        String result = MapUtil.toString(map);

        // then
        assertEquals(String.format(MAP_ENTRY_KEY_FORMAT, key, value), result);
    }

    @Test
    public void shouldReturnFirstElement_withSeparator() throws Exception {
        // given
        String value = "napis";
        String key = "key";
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);

        // when
        String result = MapUtil.toString(map, SEPARATOR);

        // then
        assertEquals(String.format(MAP_ENTRY_KEY_FORMAT, key, value), result);
    }
    
    @Test
    public void shouldReturnEntriesSeperateBySeparator() throws Exception {
        // given
        String expected = "";
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 5; ++i) {
            String key = "key" + i;
            String value = "value" + i;
            map.put(key, value);
            expected += String.format(MAP_ENTRY_KEY_FORMAT, key, value);
            if (i < 4) {
                expected += SEPARATOR;
            }
        }
        
        // when
        String result = MapUtil.toString(map, SEPARATOR);
        
        // then
        assertThat(result.split(SEPARATOR)).contains(expected.split(SEPARATOR));
    }
    
    @Test
    public void shouldReturnEntriesSeperateBySpace() throws Exception {
        // given
        String separator = " ";
        String expected = "";
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 5; ++i) {
            String key = "key" + i;
            String value = "value" + i;
            map.put(key, value);
            expected += String.format(MAP_ENTRY_KEY_FORMAT, key, value);
            if (i < 4) {
                expected += separator;
            }
        }
        
        // when
        String result = MapUtil.toString(map, separator);
        
        // then
        assertThat(result.split(separator)).contains(expected.split(separator));
    }
}
