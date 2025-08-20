package org.servlet2spring.todo.security;


import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.servlet2spring.todo.domain.APIUser;
import org.servlet2spring.todo.dto.APIUserDTO;
import org.servlet2spring.todo.repository.APIUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {

  // 주입
  private final APIUserRepository apiUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<APIUser> result = apiUserRepository.findById(username);

    APIUser apiUser = result.orElseThrow(() -> new UsernameNotFoundException(username));

    log.info("-----------------------------APIUserDetailsService apiUser-----------------------------");

    APIUserDTO apiUserDTO = new APIUserDTO(
            apiUser.getMid(),
            apiUser.getMpw(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
    );

    log.info(apiUserDTO);

    return apiUserDTO;
  }
}
