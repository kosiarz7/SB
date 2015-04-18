package systemy.bankowe.services.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * Uprawnienie użytkownika w formacie obsługiwanym przez Springa.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
class Authority implements GrantedAuthority {

    /**
     * UID.
     */
    private static final long serialVersionUID = 5766377254866218375L;
    /**
     * Nazwa uprawnienia.
     */
    private String authority;

    /**
     * Kosntruktor.
     * 
     * @param authority nazwa uprawnienia.
     */
    public Authority(final String authority) {
        this.authority = authority;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[authority=" + authority + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAuthority() {
        return authority;
    }
}
