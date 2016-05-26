package control;

import dao.ImegDao;
import dao.UserDAO;
import model.Imeg;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by vitaliy on 21.05.16.
 */
@RestController
public class ControllerRest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ImegDao imegDao;


    @RequestMapping(value = "users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> users() {
        System.out.println("Controll users +++++++++");
        List<User> userList = userDAO.allUser();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST, consumes = "application/json")
    public String addUser(@RequestBody User user) {
        userDAO.saveOrUpdate(user);
        return "OK";
    }

    @RequestMapping(value = "deletUser", method = RequestMethod.DELETE, consumes = "application/json")
    public String deletUser(@RequestBody int id) {
        System.out.println("Controll delete +++++++++");
        userDAO.delete(id);
        return "OK";
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST, produces = "application/json")
    public User editUser(@RequestBody int id) {
        System.out.println("Controll editUser +++++++++");
        User user = userDAO.get(id);
        return user;
    }

    @RequestMapping(value = "showImages", method = RequestMethod.GET, produces = "application/json")
    public List<Imeg> showAllImeg(){
        System.out.println("ControllerRest.showAllImeg *********");
        List<Imeg> allImeg = imegDao.allImeg();
        return allImeg;
    }

    @RequestMapping(value = "serchUser", method = RequestMethod.GET, produces = "application/json")
    public List<User> serchUser(@RequestParam("CHARS") String chars){
        System.out.println("ControllerRest.serchUser ************");
        List<User> userList = userDAO.serchUserName(chars);
        if (chars.length() < 2){
            return null;
        }
       /* for (User temp : userList){
            System.out.println(temp.getName() + " - " + temp.getMail() + "********");
        }*/
        return userList;
    }

}
