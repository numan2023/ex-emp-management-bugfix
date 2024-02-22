package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
    private SecurityService securityService;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	// public void insert(Administrator administrator) {
	// 	administratorRepository.insert(administrator);
	// }
	// パスワードをハッシュ化してから登録するように変更
	public void insert(Administrator administrator) {
		String rawPassword = administrator.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		administrator.setPassword(encodedPassword);
		administratorRepository.insert(administrator);
	}

	/**
	 * メールアドレスから管理者情報を取得します.
	 * @param mailAddress
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator findByMailAddress(String mailAddress) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		return administrator;
	}

	/**
	 * ログインをします.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	// public Administrator login(String mailAddress, String password) {
	// 	Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	// 	return administrator;
	// }
	// パスワードをハッシュ化してからログイン(findByMailAddressAndPasswordメソッド)するように変更
	public Administrator login(String mailAddress, String password) {
    // メールアドレスで管理者を検索
    Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
    String encodedPassword = securityService.encodePassword(password);
    // 管理者が存在し、パスワードが一致する場合にはその管理者を返す
    if (administrator != null && securityService.isPasswordMatch(encodedPassword, administrator.getPassword())) {
        return administrator;
    } else {
        return null;
    }
	}
}
