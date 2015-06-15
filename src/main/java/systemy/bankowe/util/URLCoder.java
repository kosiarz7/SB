package systemy.bankowe.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLCoder {
    private URLCoder() {
        
    }
    
    private static final String ENCODING = "UTF-8";
    
    public static String encode(String msg) throws UnsupportedEncodingException {
        return URLEncoder.encode(msg, ENCODING);
    }
    
    public static String decode(String encodedMsg) throws UnsupportedEncodingException {
        return URLDecoder.decode(encodedMsg, ENCODING);
    }
}
