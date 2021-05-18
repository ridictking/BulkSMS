package com.esd.sercom.bulksms.service.usermanagement;

import com.esd.sercom.bulksms.dao.UserEntityRepo;
import com.esd.sercom.bulksms.exceptions.BadRequestException;
import com.esd.sercom.bulksms.exceptions.NotFoundException;
import com.esd.sercom.bulksms.model.DTO.*;
import com.esd.sercom.bulksms.model.entity.UserEntity;
import com.esd.sercom.bulksms.service.telcoapiaproxy.TelcoApiProxyClient;
import com.esd.sercom.bulksms.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserEntityRepo userEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final TelcoApiProxyClient telcoApiProxyClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public UserServiceImpl(UserEntityRepo userEntityRepo, PasswordEncoder passwordEncoder, TelcoApiProxyClient telcoApiProxyClient) {
        this.userEntityRepo = userEntityRepo;
        this.passwordEncoder = passwordEncoder;
        this.telcoApiProxyClient = telcoApiProxyClient;
    }

    @Override
    public UserDetails newUser(UserDetails userDetails) {
        logger.info("User registration details : " + userDetails.toString());
        //Check if User is present
        UserDetails user = this.getUser(userDetails.getEmail());
        if(user != null) throw new BadRequestException("User Already exist");
        UserEntity userToBeRegistered = new UserEntity(userDetails);
        userToBeRegistered.setCorrelationId(UUID.randomUUID().toString());
        UserEntity userEntity = userEntityRepo.save(userToBeRegistered);
        String jwt = Utilities.createJWT(userEntity.getEmail(),36000000,null);
        String body = this.createUserMail(userDetails, "");
        EmailModel emailModel = new EmailModel();
        emailModel.setFrom("bulksms-noreply@9mobile.com.ng");
        emailModel.setSubject("Please confirm your new 9mobile Bulksms account");
        //emailModel.setBody("Please follow the link " + jwt);
        emailModel.setBody(body);
        emailModel.setTo(userDetails.getEmail());
        telcoApiProxyClient.sendEmail(emailModel);
        return userDetails;
    }

    @Override
    public UserDetails updateUser(UserDetails userDetails) {
        UserDetails user = this.getUser(userDetails.getEmail());
        if(user == null) throw new NotFoundException("User does not exist");
        if(StringUtils.hasText(userDetails.getPassword()))
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userEntityRepo.save(new UserEntity(user));
        userDetails.setPassword(null);
        return userDetails;
    }

    @Override
    public void createPassword(CreatePassword password){
        if(!password.getPassword().equals(password.getConfirmPassword())) throw new NotFoundException("Password doesn't match");
        UserDetails user = this.getUser(password.getEmail());
        if(user == null) throw new NotFoundException("User does not exist");
        user.setPassword(password.getPassword());
        this.updateUser(user);
    }

    @Override
    public UserDetails forgotPassword(String email) {
        if(!StringUtils.hasText(email)) throw new BadRequestException("Please provide your email address");
        UserDetails user = this.getUser(email);
        if(user == null) throw new NotFoundException("User does not exist");
        String jwt = Utilities.createJWT(user.getEmail(),36000000,null);
        EmailModel emailModel = new EmailModel();
        emailModel.setFrom("bulksms-noreply@9mobile.com.ng");
        emailModel.setSubject("9mobile Bulksms - Password change");
        emailModel.setBody("Please follow the link to change your password " + jwt);
        emailModel.setTo(user.getEmail());
        telcoApiProxyClient.sendEmail(emailModel);
        return null;
    }

    private String createUserMail(UserDetails entity, String url){
        String mail = "<html>\n" +
                "  <head />\n" +
                "  <body>\n" +
                "    <p style=\"font-size:12pt;font-family:'Segoe UI'\">"+"Dear"+ entity.getFirstName() +", "+entity.getLastName()+ "</p>\n" +
                "    <p style=\"font-size:12pt;font-family:'Segoe UI'\">\n" +
                "          Your account has been created. Please follow the link below to visit the 9Mobile developer portal and claim it:\n" +
                "        </p>\n" +
                "    <p style=\"font-size:12pt;font-family:'Segoe UI'\">\n" +
                "      <a href="+ url+ " >Confirm Url</a>\n" +
                "    </p>\n" +
                "    <p style=\"font-size:12pt;font-family:'Segoe UI'\">Best,</p>\n" +
                "    <p style=\"font-size:12pt;font-family:'Segoe UI'\">The 9Mobile API Team</p>\n" +
                "  </body>\n" +
                "</html>";
        return mail;
    }
    @Override
    public UserDetails login(LoginDetails login) {
        UserDetails user = this.getUser(login.getEmail());
        if(user == null) throw new NotFoundException("User does not exist");
        boolean matches = passwordEncoder.matches(user.getPassword(), login.getPassword());
        if(!matches)
            throw new BadRequestException("Authentication failed");
        return user;
    }

    @Override
    public UserDetails changePassword(ChangePasswordDetails userChangePasswordDetails) {
        UserDetails user = this.getUser(userChangePasswordDetails.getEmail());
        if(user == null) throw new NotFoundException("User does not exist");
        boolean matches = passwordEncoder.matches(user.getPassword(), userChangePasswordDetails.getOldPassword());
        if(!matches)
            throw new BadRequestException("Authentication failed");
        String newPassword = passwordEncoder.encode(userChangePasswordDetails.getNewPassword());
        user.setPassword(newPassword);
        userEntityRepo.save(new UserEntity(user));
        user.setPassword(null);
        return user;
    }

    @Override
    public void deleteUser(String email) {
        UserDetails user = this.getUser(email);
        if(user == null) throw new NotFoundException("User does not exist");
        user.setUserState("Deactivated");
        userEntityRepo.save(new UserEntity(user));
    }

    @Override
    public UserDetails getUser(String email) {
        Optional<UserEntity> user = userEntityRepo.findByEmail(email);
        return user.map(UserDetails::new).orElse(null);
    }
}
