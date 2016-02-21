package honeyalarmg2

import grails.transaction.Transactional
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class SHA256EncoderService
        implements org.springframework.security.authentication.encoding.PasswordEncoder {

    protected MessageDigestPasswordEncoder sha256PasswordEncoder;
    protected BCryptPasswordEncoder bcryptPasswordEncoder;

    public String encodePassword(String rawPass, Object salt) {
        return bcryptPasswordEncoder.encodePassword(rawPass, null);
    }

    public boolean isPasswordValid(String encPass,
                                   String rawPass, Object salt) {

        return false;
    }

    /**
     * Dependency injection for the bcrypt password encoder
     * @param encoder the encoder
     */
    public void setBcryptPasswordEncoder(BCryptPasswordEncoder encoder) {
        bcryptPasswordEncoder = encoder;
    }

    /**
     * Dependency injection for the SHA-256 password encoder
     * @param encoder the encoder
     */
    public void setSha256PasswordEncoder(
            MessageDigestPasswordEncoder encoder) {
        sha256PasswordEncoder = encoder;
    }
}