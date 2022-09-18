package edu.miu.springdataday3.aspect.offensive;

import edu.miu.springdataday3.entity.User;
import edu.miu.springdataday3.repo.OffensiveWordRepo;
import edu.miu.springdataday3.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Transactional
public class WaaOffensiveWords {

    private final HttpServletRequest httpServletRequest;
    private final UserRepo userRepo;

    private final OffensiveWordRepo offensiveWordRepo;

    @Before("@annotation(edu.miu.springdataday3.aspect.offensive.OffensiveFilter)")
    public void offensiveCount(JoinPoint joinPoint) throws IOException {

        User usr = getUserDetails();
        OffensiveCount offensiveCount1 = getOffensiveCount(usr);
        if( offensiveCount1.getCount() == 5 && offensiveCount1.getDateTime().isBefore(LocalDateTime.now()) ){
            offensiveCount1.setCount(0);
        }

        String str = httpServletRequest.getParameter("name");
        if( str != null && str.contains("springing") ){
            offensiveCount1.setCount(offensiveCount1.getCount()+1);
            if( offensiveCount1.getCount() == 5 ){
                offensiveCount1.setDateTime(LocalDateTime.now().plus(10, ChronoUnit.SECONDS));
                throw new RuntimeException(" Max Bad word limit. You need to wait for "+ offensiveCount1.getDateTime() +"min");
            }
        }
    }

    public OffensiveCount getOffensiveCount(User user){
        Optional<OffensiveCount> offensiveCountOptional = offensiveWordRepo.findByUser(user);
        if(!offensiveCountOptional.isPresent()){
            OffensiveCount newCount = new OffensiveCount();
            newCount.setCount(0);
            newCount.setUser(user);
            offensiveWordRepo.save(newCount);
            return newCount;
        }
        return offensiveCountOptional.get();
    }


    public User getUserDetails(){
        Object getUser = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (getUser instanceof UserDetails) {
            username = ((UserDetails)getUser). getUsername();
        } else {
            username = getUser.toString();
        }
        return userRepo.findByEmail(username);
    }
}
