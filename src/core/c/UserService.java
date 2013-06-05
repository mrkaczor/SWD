package core.c;

import core.m.User;
import core.m.exceptions.UserAuthenticationException;
import core.m.exceptions.UserRegistrationException;
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
    public static UserService getInstance() {
        return InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {

        private static final UserService p_instance = new UserService();
    }
    // </editor-fold>

    private UserService() {
        _loggedUser = null;
    }

    public User getLoggedUser() {
        return _loggedUser;
    }

    public void logIn(User user) throws UserAuthenticationException {
        User tmpUser = loadUserProfile(user.getName());
        if (tmpUser != null && checkUserPassword(tmpUser, user.getPassword())) {
            _loggedUser = tmpUser;
        } else {
            throw new UserAuthenticationException("Błędny login lub hasło!");
        }
    }

    public void logOut() {
        if (_loggedUser != null) {
            try {
                saveUserProfile(_loggedUser);
                _loggedUser = null;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void signIn(User newUser) throws UserRegistrationException {
        User tmpUser = loadUserProfile(newUser.getName());
        if (tmpUser != null) {
            throw new UserRegistrationException("Użytkownik o podanej nazwie już istnieje!");
        } else if (validateUser(tmpUser)) {
            try {
                saveUserProfile(newUser);
                _loggedUser = newUser;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            throw new UserRegistrationException("Dane użytkownika nie spełniają wymaganych kryteriów!");
        }
    }

    private boolean checkUserPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void loadUserProfile() {
        if (_loggedUser != null) {
            _loggedUser = loadUserProfile(_loggedUser.getName());
        }
    }

    private User loadUserProfile(String username) {
        User u = null;
        try {
            FileInputStream fileIn = new FileInputStream(USER_PATH + username + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }

    public void saveUserProfile() throws IOException {
        if (_loggedUser != null) {
            saveUserProfile(_loggedUser);
        }
    }

    private void saveUserProfile(User user) throws IOException {
        File userData = new File(USER_PATH + user.getName() + ".ser");
        userData.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(userData);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(user);
        out.close();
        fileOut.close();
    }

    private boolean validateUser(User user) {
        //TODO code user validation logic here
        return true;
    }
}
