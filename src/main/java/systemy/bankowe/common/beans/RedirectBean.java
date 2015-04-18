package systemy.bankowe.common.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.security.SpringSecurityContextUtil;

/**
 * Bean przekierowujący zalogowanego użytkownika na opdowiednią stronę.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class RedirectBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -5659482197872768296L;
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    /**
     * Mapowanie przekierowań.
     */
    private Map<String, String> redirects;
    /**
     * Interfejs wspomagający pracę z Spring Security.
     */
    private SpringSecurityContextUtil springSecurityContextUtil;
    /**
     * Rola roota.
     */
    private String rootRole;
    /**
     * Prefix jaki ma zozstać dodany do url.
     */
    private String prefix;

    /**
     * Zwraca url strony, na którą ma być przekierowany użytkownik.
     * 
     * @return url.
     */
    public String getRedirectUrl() {
        String url = "";

        List<String> roles = springSecurityContextUtil.getLoggedOnUserRolesAsStringList();
        if (roles.contains(rootRole)) {
            url = redirects.get(rootRole);
        }
        else {
            if (roles.isEmpty()) {
                throw new IllegalStateException("Zalogowany użytkownik nie posiada żadnych uprawnień.");
            }
            else {
                url = redirects.get(roles.get(0));
            }
        }

        LOGGER.debug("getRedirectUrl|URL: {}", prefix + url);
        return prefix + url;
    }

    // SETTERY
    public void setRedirects(final Map<String, String> redirects) {
        this.redirects = redirects;
    }

    public void setSpringSecurityContextUtil(final SpringSecurityContextUtil springSecurityContextUtil) {
        this.springSecurityContextUtil = springSecurityContextUtil;
    }

    public void setRootRole(final String rootRole) {
        this.rootRole = rootRole;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}