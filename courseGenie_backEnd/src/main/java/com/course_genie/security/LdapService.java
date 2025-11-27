package com.course_genie.security;

import com.course_genie.user.*;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LdapService {

    private final LdapTemplate ldapTemplate;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserDTOMapper userDTOMapper;

    public LdapService(LdapTemplate ldapTemplate, UserRepository userRepository, JwtUtil jwtUtil, UserDTOMapper userDTOMapper) {
        this.ldapTemplate = ldapTemplate;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userDTOMapper = userDTOMapper;
    }

    public UserDTO authenticate(String username, String password) {
        try {
            if (ldapTemplate.authenticate("ou=Users", "(uid=" + username + ")", password)) {
                User user = userRepository.findByUserName(username).orElse(new User());
                if (user.getUserId() == null) {
                    Attributes attributes = searchUser(username);
                    user.setEmail(attributes.get("mail").toString().replaceAll("^mail:\\s*", ""));
                    user.setFirstName(attributes.get("givenName").toString().replaceAll("^givenName:\\s*", ""));
                    user.setLastName(attributes.get("sn").toString().replaceAll("^sn:\\s*", ""));
                    user.setUserName(username);

                    // Assign default role for new user
                    Set<String> roles = new HashSet<>();
                    roles.add("ROLE_USER");
                    user.setRoles(roles);

                    userRepository.save(user);
                }
                UserDTO userDTO = userDTOMapper.apply(user);
                userDTO.setJwtToken(jwtUtil.generateToken(username, user.getRoles()));
                return userDTO;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Attributes searchUser(String username) {
        try {
            LdapQuery query = LdapQueryBuilder.query().base("ou=Users").where("uid").is(username);
            List<Attributes> results = ldapTemplate.search(query, (AttributesMapper<Attributes>) attributes -> attributes);
            return results.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}