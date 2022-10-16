package com.price.comparision.ecom.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.price.comparision.ecom.exception.GeneralBusinessException;
import com.price.comparision.ecom.exception.InvalidOtpException;
import com.price.comparision.ecom.model.Email;
import com.price.comparision.ecom.model.EmailAuthRequest;
import com.price.comparision.ecom.model.ErrorServiceResponse;
import com.price.comparision.ecom.model.OtpAuth;
import com.price.comparision.ecom.model.Role;
import com.price.comparision.ecom.model.UpdatePasswordRequest;
import com.price.comparision.ecom.model.User;
import com.price.comparision.ecom.repository.OtpAuthRepo;
import com.price.comparision.ecom.repository.RoleDao;
import com.price.comparision.ecom.repository.UserDao;
import com.price.comparision.ecom.util.CommonUtil;


@Service
public class UserServiceImpl {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private OtpAuthRepo otpAuthRepo;
	
	public User registerNewUser(User user) {
	   
	    EmailAuthRequest eaq = new EmailAuthRequest(user.getEmail(),user.getToken());
	    
	    if(this.verifyOtp(eaq)) {
	        Role role = roleDao.findById("User").get();
	        Set<Role> roles = new HashSet<Role>();
	        roles.add(role);
	        user.setRoles(roles);
	        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
	        
	        userDao.save(user);
	        
	        return userDao.save(user);
	    }else {
	        throw new InvalidOtpException();
	    }	
	}

	
	public String getEncodedPassword(String password) {
		return commonUtil.getEncodedPassword(password);
	}
	
	public boolean sendOtp(String email) {
		
		try {
			List<OtpAuth> oa= otpAuthRepo.getauthToken(email);
			
			if(!oa.isEmpty()) {
				otpAuthRepo.deleteAll(oa);
			}
			
			Email e = new Email();
			final String otp = commonUtil.generateOtp();
			e.setRecipient(email);
			e.setSubject("Pick The BEST: OTP Verification");
			String messageBody = "The OTP to confirm you pick the best account is  "
					+ otp;
			e.setMsgBody(messageBody);
			
			otpAuthRepo.save(new OtpAuth(e.getRecipient(), commonUtil.getEncodedPassword(otp)));
			
			return emailService.sendMail(e);
			
		}catch(Exception ex) {
			throw new GeneralBusinessException(new ErrorServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR,"failed to send email"));
		}
		
	}
	
	public boolean verifyOtp(EmailAuthRequest authRequest) {
		
		try {
			List<OtpAuth> oa= otpAuthRepo.getauthToken(authRequest.getEmail());
			
			if(oa.isEmpty()) {
				return false;
			}
			
			return commonUtil.getDecodedPassword(authRequest.getToken(), oa.get(0).getToken());
		}catch(Exception ex) {
			return false;
		}
			
	}
	
	public boolean updatePassword(UpdatePasswordRequest authRequest) {
		List<User> users = userDao.findUserByEmail(authRequest.getEmail());
		
		if(users.isEmpty()) {
			return false;
		}else {
			
			EmailAuthRequest ereq = new EmailAuthRequest(authRequest.getEmail(), authRequest.getToken());
			
			if(this.verifyOtp(ereq)) {
				String password = commonUtil.getEncodedPassword(authRequest.getPassword());
				User u = users.get(0);
				u.setUserPassword(password);
				userDao.save(u);
				List<OtpAuth> oa= otpAuthRepo.getauthToken(authRequest.getEmail());
				otpAuthRepo.deleteAll(oa);
				return true;
			}
			
		}
		
		return false;
	}
	
	
}
