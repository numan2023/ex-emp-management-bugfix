package com.example.service;

import com.example.repository.AdministratorRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.domain.Administrator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByMailAddress(email);
        if (administrator == null) {
            throw new UsernameNotFoundException("Administrator not found");
        }
        return new User(administrator.getMailAddress(), administrator.getPassword(), new ArrayList<>());
    }
}