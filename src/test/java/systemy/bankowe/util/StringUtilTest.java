package systemy.bankowe.util;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import systemy.bankowe.util.StringUtil;


@RunWith(JUnitParamsRunner.class)
public class StringUtilTest {
    
    @Test
    @Parameters
    public void shouldReturnPassedExpectedResult(String value, boolean expectedResult) throws Exception {
        // given
        
        // when
        boolean result = StringUtil.isEmpty(value);

        // then
        assertEquals(expectedResult, result);
    }
    
    public Object[] parametersForShouldReturnPassedExpectedResult() {
        return $(
                    $(null, true),
                    $("", true),
                    $("  ", true),
                    $("         ", true),
                    $("       ", true),
                    $("         ", true),
                    $("       ", true),
                    $(" a ", false),
                    $("     d", false),
                    $("d ", false),
                    $("         d ", false)
                 );
    }
}
