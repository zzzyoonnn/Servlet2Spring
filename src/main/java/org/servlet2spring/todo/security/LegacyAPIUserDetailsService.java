package org.servlet2spring.todo.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class LegacyAPIUserDetailsService {//implements UserDetailsService {
//
//  // 주입
//  private final APIUserRepository apiUserRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<APIUser> result = apiUserRepository.findById(username);
//
//    APIUser apiUser = result.orElseThrow(() -> new UsernameNotFoundException(username));
//
//    log.info("-----------------------------APIUserDetailsService apiUser-----------------------------");
//
//    APIUserDTO apiUserDTO = new APIUserDTO(
//            apiUser.getMid(),
//            apiUser.getMpw(),
//            List.of(new SimpleGrantedAuthority("ROLE_USER"))
//    );
//
//    log.info(apiUserDTO);
//
//    return apiUserDTO;
//  }
}
