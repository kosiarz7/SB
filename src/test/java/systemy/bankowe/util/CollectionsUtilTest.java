package systemy.bankowe.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import systemy.bankowe.util.CollectionsUtil;


public class CollectionsUtilTest {
    
    private static final String SEPARATOR = "@#";
    @Test
    public void shouldReturnEmptyString() throws Exception {
        // given
        
        // when
        String result = CollectionsUtil.toString(new ArrayList<String>());
        
        // then
        assertEquals("", result);
    }
    
    @Test
    public void shouldReturnEmptyString_withSeparator() throws Exception {
        // given
        
        // when
        String result = CollectionsUtil.toString(new ArrayList<String>(), SEPARATOR);
        
        // then
        assertEquals("", result);
    }
    
    @Test
    public void shouldReturnFirstElement() throws Exception {
        // given
        String elem = "napis";
        List<String> strings = new ArrayList<>();
        strings.add(elem);
        
        // when
        String result = CollectionsUtil.toString(strings);
        
        // then
        assertEquals(elem, result);
    }
    
    @Test
    public void shouldReturnFirstElement_withSeparator() throws Exception {
        // given
        String elem = "napis";
        List<String> strings = new ArrayList<>();
        strings.add(elem);
        
        // when
        String result = CollectionsUtil.toString(strings, SEPARATOR);
        
        // then
        assertEquals(elem, result);
    }
    
    @Test
    public void shouldReturnValuesSeperateBySeparator() throws Exception {
        // given
        String expected = "";
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            String tmp = "napis" + i;
            strings.add(tmp);
            expected += tmp;
            if (i < 4) {
                expected += SEPARATOR;
            }
        }
        
        // when
        String result = CollectionsUtil.toString(strings, SEPARATOR);
        
        // then
        assertEquals(expected, result);
    }
    
    @Test
    public void shouldReturnValuesSeperateBySpace() throws Exception {
        // given
        String expected = "";
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            String tmp = "napis" + i;
            strings.add(tmp);
            expected += tmp;
            if (i < 4) {
                expected += " ";
            }
        }
        
        // when
        String result = CollectionsUtil.toString(strings);
        
        // then
        assertEquals(expected, result);
    }
}