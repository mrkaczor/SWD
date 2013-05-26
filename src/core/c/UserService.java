/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.c;

import core.m.User;
import core.m.exceptions.UserAuthenticationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.security.util.Password;

/**
 *
 * @author mrkaczor
 */
public class UserService {
    private final static String USER_PATH = "users\\";
    private User _loggedUser;

    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static UserService getInstance()
    {
      return InstanceHolder.p_instance;
    }

    private static final class InstanceHolder
    {
      private static final UserService p_instance = new UserService();
    }
    // </editor-fold>
    
    private UserService() {
        _loggedUser = null;
    }
    
    public void logIn(User user) throws UserAuthenticationException {
        User tmpUser = loadUserProfile(user.getName());
        if(tmpUser != null) {
            
        } else {
            throw new UserAuthenticationException("Błędny login lub hasło!");
        }
    }
    
    public void logOut() {
        if(_loggedUser != null) {
            saveUserProfile(_loggedUser);
        }
    }
    
    public void signIn(User newUser) {
        User tmpUser = loadUserProfile(newUser.getName());
        if(tmpUser == null && validateUser(tmpUser)) {
            saveUserProfile(newUser);
            _loggedUser = newUser;
        } else {
            //throw new UserAuthenticationException("Błędny login lub hasło!");
        }
    }
    
    private boolean checkUserPassword(User user, Password password) {
        //TODO code password check logic here
        return true;
    }
    
    private User loadUserProfile(String username) {
        User u = null;
        try {
            FileInputStream fileIn = new FileInputStream(USER_PATH+username+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("elo - "+ex.getMessage());
        }
        return u;
    }
    
    private void saveUserProfile(User user) {
        try {
            //FileOutputStream fileOut = new FileOutputStream(USER_PATH+user.getName()+".ser");
            File userData = new File(USER_PATH+user.getName()+".ser");
            userData.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(userData);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private boolean validateUser(User user) {
        //TODO code user validation logic here
        return true;
    }
}
