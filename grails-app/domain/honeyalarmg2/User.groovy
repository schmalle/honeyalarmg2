package honeyalarmg2

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String pwbackup
	String twitterName
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired



	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof User && other.username == username)
	}

	@Override
	String toString() {
		username
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}



	def beforeInsert() {

		pwbackup = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {

		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		pwbackup blank: false
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}
}
