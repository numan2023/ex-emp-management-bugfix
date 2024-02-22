package com.example.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 管理者情報を表すドメイン.
 * 
 * @author igamasayuki
 * 
 */
public class Administrator implements UserDetails{
	/** id(主キー) */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

	/**
	 * 引数無しのコンストラクタ.
	 */
	public Administrator() {
	}

	/**
	 * 初期化用コンストラクタ.
	 * 
	 * @param id          id(主キー)
	 * @param name        名前
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 */
	public Administrator(Integer id, String name, String mailAddress, String password) {
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // この例では、ユーザーには特定の権限がないと仮定しています。
        // 必要に応じて、適切な権限を返すようにこのメソッドを修正してください。
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return mailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        // この例では、アカウントが有効であると仮定しています。
        // 必要に応じて、適切な値を返すようにこのメソッドを修正してください。
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // この例では、アカウントがロックされていないと仮定しています。
        // 必要に応じて、適切な値を返すようにこのメソッドを修正してください。
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // この例では、資格情報が有効であると仮定しています。
        // 必要に応じて、適切な値を返すようにこのメソッドを修正してください。
        return true;
    }

    @Override
    public boolean isEnabled() {
        // この例では、ユーザーが有効であると仮定しています。
        // 必要に応じて、適切な値を返すようにこのメソッドを修正してください。
        return true;
    }

}
