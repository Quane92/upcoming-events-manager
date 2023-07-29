package com.zawartkawoj.upcomingevents.service;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.entity.AccountDto;
import com.zawartkawoj.upcomingevents.entity.Role;
import com.zawartkawoj.upcomingevents.exceptions.EmailAlreadyExistsException;
import com.zawartkawoj.upcomingevents.repository.AccountRepository;
import com.zawartkawoj.upcomingevents.repository.RoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addAccountThroughRegistration(AccountDto accountDto) throws EmailAlreadyExistsException {
        boolean emailExists = accountRepository.findByEmail(accountDto.getEmail()) != null;
        if (emailExists) {
            throw new EmailAlreadyExistsException("Email already exists in database");
        } else {
            Role userRole = roleRepository.findRoleByName("USER");
            Account accountToSave = new Account(
                    accountDto.getEmail(),
                    accountDto.getFirstName(),
                    accountDto.getLastName(),
                    passwordEncoder.encode(accountDto.getPassword()));
            accountToSave.getRoles().add(userRole);
            accountRepository.save(accountToSave);
        }
    }

    public Account findByEmail(String name) {
        return accountRepository.findByEmail(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = findByEmail(username);
        UserDetails usertest =  User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .build();
        return usertest;
    }
}
