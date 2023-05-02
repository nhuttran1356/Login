package service;

import model.UserModel;
import repository.LoginRepository;

import java.util.List;

public class LoginService {

    private LoginRepository loginRepository = new LoginRepository();

    //Nhận tham số tương tự UserRepository hoặc hơn
    public boolean checkLogin(String email, String password){
        List<UserModel> list = loginRepository.findByEmailAndPassword(email,password);
        return list.size() > 0; // return true
    }
}
