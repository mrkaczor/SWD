package core.c;

import core.m.Decision;
import core.m.Profile;
import core.m.exceptions.UserAuthenticationException;
import core.m.exceptions.UserRegistrationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import sun.security.util.Password;

/**
 *
 * @author mrkaczor
 */
public class ProfilesService {

    private final static String USER_PATH = "users\\";
    private Profile _loggedUser;

    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static ProfilesService getInstance() {
        return InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {

        private static final ProfilesService p_instance = new ProfilesService();
    }
    // </editor-fold>

    private ProfilesService() {
        _loggedUser = null;
    }

    public Profile getCurrentProfile() {
        return _loggedUser;
    }

    public void createProfile(Profile newProfile) throws UserRegistrationException {
        Profile tmpUser = loadProfile(newProfile.getName());
        if (tmpUser != null) {
            throw new UserRegistrationException("Użytkownik o podanej nazwie już istnieje!");
        } else  {
            try {
                saveUserProfile(newProfile);
                _loggedUser = newProfile;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
//    public void loadProfile() {
//        if (_loggedUser != null) {
//            _loggedUser = loadProfile(_loggedUser.getName());
//        }
//    }

    public Profile loadProfile(String profileName) {
        Profile u = null;
        try {
            FileInputStream fileIn = new FileInputStream(USER_PATH + profileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u = (Profile) in.readObject();
            in.close();
            fileIn.close();
            _loggedUser = u;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }

    public void saveProfile() throws IOException {
        if (_loggedUser != null) {
            saveUserProfile(_loggedUser);
        }
    }

    private void saveUserProfile(Profile user) throws IOException {
        File userData = new File(USER_PATH + user.getName() + ".ser");
        userData.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(userData);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(user);
        out.close();
        fileOut.close();
    }
    
    public String[] getProfilesNames()
    {
        String[] result = new String[0];
        List<String> profiles = new LinkedList<>();
        for (File file : new File(USER_PATH).listFiles()) {
            if(file.isFile()){
                if(file.getName().toLowerCase().endsWith(".ser")){
                    profiles.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
                }
            }
        }
        return (String[]) profiles.toArray(result);
    }
}
