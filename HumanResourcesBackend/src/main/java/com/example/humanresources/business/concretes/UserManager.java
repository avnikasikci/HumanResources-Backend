package com.example.humanresources.business.concretes;

import com.example.humanresources.business.abstracts.UserService;
import com.example.humanresources.business.enums.EnumCollection;
import com.example.humanresources.core.dataAccess.UserDao;
import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.*;
import com.example.humanresources.dataAccess.abstracts.CandidateDao;
import com.example.humanresources.dataAccess.abstracts.EmployerDao;
import com.example.humanresources.dataAccess.abstracts.StaffDao;
import com.example.humanresources.entities.dtos.UserDto;
import com.example.humanresources.entities.dtos.UserLoginDto;
import com.example.humanresources.entities.dtos.UserLoginReturnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private CandidateDao candidateDao;
	private EmployerDao employerDao;
	private StaffDao staffDao;




	//	@Autowired
//	public UserManager(UserDao userDao) {
//		super();
//		this.userDao = userDao;
//	}
	@Autowired
	public UserManager(UserDao userDao,CandidateDao candidateDao,EmployerDao employerDao,StaffDao staffDao) {
		this.userDao = userDao;
		this.candidateDao=candidateDao;
		this.employerDao=employerDao;
		this.staffDao=staffDao;
	}


	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi");
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı güncellendi");
	}

	@Override
	public DataResult<User> findByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email)
				,"Kullanıcı bulundu");
	}

	@Override
	public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
		User user = this.userDao.findByEmail(userLoginDto.getEmail());
		if(user==null){
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı email girdiniz");
		}else if(!user.getPassword().equals(userLoginDto.getPassword())){
			return new ErrorDataResult<UserLoginReturnDto>("Hatalı şifre girdiniz");
		}else if(!user.isMailVerify()){
			return new ErrorDataResult<UserLoginReturnDto>("Giriş yapmak için email onayı yapmanız gerekmektedir");
		}
		UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
		userLoginReturnDto.setId(user.getId());
		userLoginReturnDto.setEmail(user.getEmail());

		if(this.candidateDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(1);
			userLoginReturnDto.setName(this.candidateDao.getById(user.getId()).getFirstName()+" "+this.candidateDao.getById(user.getId()).getLastName());
		}else if(this.employerDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(2);
			userLoginReturnDto.setName(this.employerDao.getById(user.getId()).getCompanyName());
		}else if(this.staffDao.existsById(user.getId())){
			userLoginReturnDto.setUserType(3);
			userLoginReturnDto.setName(this.staffDao.getById(user.getId()).getFirstName()+" "+this.staffDao.getById(user.getId()).getLastName());
		}else {
			return new ErrorDataResult<UserLoginReturnDto>("Bir hata oluştu");
		}

		return new SuccessDataResult<UserLoginReturnDto>(userLoginReturnDto,"Giriş yapıldı");
	}

	@Override
	public DataResult<List<User>> getVerifyedUsers() {
		return new SuccessDataResult<List<User>>(this.userDao.findByMailVerifyTrue(),"Data listelendi");
	}
	@Override
	public DataResult<List<User>> getAll() {
		var data=this.userDao.findAll();
		return new SuccessDataResult<List<User>>(data,"Data listelendi");
	}
	@Override
	public DataResult<List<UserDto>> getAllDto() {
		var dataUserList=this.userDao.findAll();
		List<UserDto> result = new List<UserDto>() {
			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator<UserDto> iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return new Object[0];
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return null;
			}

			@Override
			public boolean add(UserDto userDto) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends UserDto> c) {
				return false;
			}

			@Override
			public boolean addAll(int index, Collection<? extends UserDto> c) {
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return false;
			}

			@Override
			public void clear() {

			}

			@Override
			public UserDto get(int index) {
				return null;
			}

			@Override
			public UserDto set(int index, UserDto element) {
				return null;
			}

			@Override
			public void add(int index, UserDto element) {

			}

			@Override
			public UserDto remove(int index) {
				return null;
			}

			@Override
			public int indexOf(Object o) {
				return 0;
			}

			@Override
			public int lastIndexOf(Object o) {
				return 0;
			}

			@Override
			public ListIterator<UserDto> listIterator() {
				return null;
			}

			@Override
			public ListIterator<UserDto> listIterator(int index) {
				return null;
			}

			@Override
			public List<UserDto> subList(int fromIndex, int toIndex) {
				return null;
			}
		};

		for (User entityUser : dataUserList) {
			result.add(this.convertToDto(entityUser));
		}
		
		return new SuccessDataResult<List<UserDto>>(result,"Data listelendi");
	}
	private UserDto convertToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setMailVerify(user.isMailVerify());//mailVerify
		userDto.setUserType(user.getUserType());
		userDto.setUserTypeStr("user");
		return userDto;
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Listelendi");
	}

}
