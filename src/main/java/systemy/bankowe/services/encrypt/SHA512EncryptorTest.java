package systemy.bankowe.services.encrypt;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import pl.wkr.fluentrule.api.FluentExpectedException;
import systemy.bankowe.annotations.LoggerFactory;
import systemy.bankowe.annotations.LoggerPostProcessor;
import systemy.bankowe.services.encrypt.SHA512Encryptor;

@RunWith(JUnitParamsRunner.class)
public class SHA512EncryptorTest {
    @Rule
    public FluentExpectedException exception = FluentExpectedException.none();
    
    private SHA512Encryptor sut;
    
    @Before
    public void init() {
        sut = new SHA512Encryptor();
        LoggerFactory factory = when(mock(LoggerFactory.class).getLogger(any())).thenReturn(mock(Logger.class)).getMock();
        new LoggerPostProcessor(factory).postProcessBeforeInitialization(sut, null);
    }

    @Test
    @Parameters
    public void shouldReturnSHA512Hash(String value, String expectedResult) throws Exception {
        // given
        
        // when
        String result = sut.encrypt(value);
        
        // then
        assertEquals(result, expectedResult);
    }
    
    public Object[] parametersForShouldReturnSHA512Hash() {
        return $(
                    $("asd", "E54EE7E285FBB0275279143ABC4C554E5314E7B417ECAC83A5984A964FACBAAD68866A2841C3E83DDF125A2985566261C4014F9F960EC60253AEBCDA9513A9B4"),
                    $("To jest przykładowy tekst", "015EFCD1B2A07D91B871502AC166805192B7802031E5A2F0B053D94BC54287D4A8242A141D07F4995149C2B1E4D7E6FD98E19F9209F664B7776C39F3E9EA43B2")
                 );
    }
    
    @Test
    @Parameters
    public void shouldThrowIllegalArgumentException(String value) throws Exception {
        // given
        exception.expect(IllegalArgumentException.class);
        
        // when
        sut.encrypt(value);
        
        // then
    }
    
    public Object[] parametersForShouldThrowIllegalArgumentException() {
        return $(
                    $(""),
                    $("     "),
                    $("   "),
                    $("              ")
                 );
    }
    
    @Test
    public void shouldHashHas128Characters() throws Exception {
        // given
        String string = "Przykładowy napis";
        
        // when
        String result = sut.encrypt(string);
        
        // then
        assertEquals(128, result.length());
    }
    
    @Test
    public void shouldHasContainsOnlyUppercaseLetters() throws Exception {
        // given
        String string = "Przykładowy napis";
        String pattern = "[A-Z0-9]{128}";
        
        // when
        String result = sut.encrypt(string);
        
        // then
        assertTrue(result.matches(pattern));
    }
}
