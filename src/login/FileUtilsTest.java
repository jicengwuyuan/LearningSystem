package login;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

class FileUtilsTest {

    @org.junit.jupiter.api.Test
    void addUser() {

        List<UserInfo> readUsers = FileUtils.readUsers();
        UserInfo userInfo = new UserInfo("test", "123");
        FileUtils.addUser(userInfo);
        readUsers = FileUtils.readUsers();
        Optional<UserInfo> optional = readUsers.stream()
                .filter(user -> user.getUserName().equals("test") && user.getPassword().equals("123"))
                .findFirst();
        assertTrue(optional.isPresent());

    }

    @org.junit.jupiter.api.Test
    void updateUser() {
        UserInfo userInfo = new UserInfo("test", "123");
        UserInfo user = FileUtils.UpdateUser(userInfo, "321");
        List<UserInfo> readUsers = FileUtils.readUsers();
        System.out.println(readUsers);

    }


    @org.junit.jupiter.api.Test

    void removeUser() {
        List<UserInfo> readUsers = FileUtils.readUsers();
        Optional<UserInfo> optional = readUsers.stream()
                .filter(user -> user.getUserName().equals("test") && user.getPassword().equals("321"))
                .findFirst();
        assertTrue(optional.isPresent());
        System.out.println("Remove:");
        System.out.println(optional.get());
        FileUtils.removeUser(optional.get());
        readUsers = FileUtils.readUsers();
        optional = readUsers.stream()
                .filter(user -> user.getUserName().equals("test") && user.getPassword().equals("321"))
                .findFirst();
        assertTrue(!optional.isPresent());
    }

    @org.junit.jupiter.api.Test
    void readUsers() {
        List<UserInfo> readUsers = FileUtils.readUsers();
        assertNotNull(readUsers);
        System.out.println(readUsers);
    }

}
