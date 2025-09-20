package com.course_genie.security;

import com.course_genie.professor.*;
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
    private final ProfessorRepository professorRepository;
    private final JwtUtil jwtUtil;
    private final ProfessorDTOMapper professorDTOMapper;

    public LdapService(LdapTemplate ldapTemplate, ProfessorRepository professorRepository, JwtUtil jwtUtil, ProfessorDTOMapper professorDTOMapper) {
        this.ldapTemplate = ldapTemplate;
        this.professorRepository = professorRepository;
        this.jwtUtil = jwtUtil;
        this.professorDTOMapper = professorDTOMapper;
    }

    public ProfessorDTO authenticate(String username, String password) {
        try {
            if (ldapTemplate.authenticate("ou=Users", "(uid=" + username + ")", password)) {
                Professor professor = professorRepository.findByUserName(username).orElse(new Professor());
                if (professor.getProfessorId() == null) {
                    Attributes attributes = searchUser(username);
                    professor.setEmail(attributes.get("mail").toString().replaceAll("^mail:\\s*", ""));
                    professor.setFirstName(attributes.get("givenName").toString().replaceAll("^givenName:\\s*", ""));
                    professor.setLastName(attributes.get("sn").toString().replaceAll("^sn:\\s*", ""));
                    professor.setUserName(username);

                    // Assign default role for new user
                    Set<String> roles = new HashSet<>();
                    roles.add("ROLE_USER");
                    professor.setRoles(roles);

                    professorRepository.save(professor);
                }
                ProfessorDTO professorDTO = professorDTOMapper.apply(professor);
                professorDTO.setJwtToken(jwtUtil.generateToken(username, professor.getRoles()));
                return professorDTO;
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