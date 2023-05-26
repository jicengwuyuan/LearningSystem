package login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;

public class FileUtils {

	/**
	 * the file to store the user
	 */
	private static final String USER_JSON_FILE = "userinfos.json";
	/**
	 * read all users through the JSON file
	 * @return
	 */
	public static List<UserInfo> readUsers() {
		try {
			//read the file
			BufferedReader reader = new BufferedReader(new FileReader(USER_JSON_FILE));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			//convert to list
			List<UserInfo> parseArray = JSONArray.parseArray(sb.toString(), UserInfo.class);
			return parseArray;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<UserInfo>();
	}
	/**
	 * change the user's password
	 * @param userInfo
	 * @param newPassword
	 * @return
	 */
	public static UserInfo UpdateUser(UserInfo userInfo,String newPassword) {
		List<UserInfo> readUsers = readUsers();
		//find the user first
		List<UserInfo> list = readUsers.stream().filter(user->user.getUserName().equals(userInfo.getUserName()) 
				&& user.getPassword().equals(userInfo.getPassword())).collect(Collectors.toList());
		if (list.size() == 0) {
			//can not find the user;
			return null;
		}
		//change the password
		list.get(0).setPassword(newPassword);
		try {
			//restore the users
			BufferedWriter writer = new BufferedWriter(new FileWriter(USER_JSON_FILE));
			writer.append(JSONArray.toJSONString(readUsers));
			writer.flush();
			writer.close();
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * add a user into json file
	 * @param userInfo
	 * @return
	 */
	public static UserInfo addUser(UserInfo userInfo) {
		List<UserInfo> readUsers = readUsers();
		// find the user
		List<UserInfo> list = readUsers.stream().filter(user->user.getUserName().equals(userInfo.getUserName())).collect(Collectors.toList());
		if (list.size() > 0) {
			//the user is exist,set the id to -1;
			userInfo.setId(-1);
			return userInfo;
		}
		//set the first user's id to 1
		if (readUsers.size() == 0) {
			userInfo.setId(1);
		}else {
			//next user's id will increase by 1
			int maxId = readUsers.stream().mapToInt(user->user.getId()).max().getAsInt();
			userInfo.setId(maxId+1);
		}
		//add the user into list
		readUsers.add(userInfo);
		try {
			//save the users into json file
			BufferedWriter writer = new BufferedWriter(new FileWriter(USER_JSON_FILE));
			writer.append(JSONArray.toJSONString(readUsers));
			writer.flush();
			writer.close();
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * remove user
	 * @param userInfo
	 */
    public static void removeUser(UserInfo userInfo) {
        List<UserInfo> readUsers = readUsers();
        //remove the user from file
        readUsers.removeIf(user->user.getUserName().equals(userInfo.getUserName()));
        try {
            //save the users into json file
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_JSON_FILE));
            writer.append(JSONArray.toJSONString(readUsers));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

}
