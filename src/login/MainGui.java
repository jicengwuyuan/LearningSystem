package login;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UIComponent.DynamicText;
import UIComponent.InputText;
import UIComponent.MyReminder;
import UIComponent.Password;
import UIComponent.TextButton;
import UIComponent.UIStyle;

/**
 * 
 * login frame
 */
public class MainGui {
	/**
	 * current frame
	 */
	private JFrame jFrame;

	/**
	 * can change the y position
	 */
	private int yOfferset = 100;

	/**
	 * userName InputText
	 */
	private InputText userName;
	/**
	 * Password
	 */
	private Password pass;
	/**
	 * textTip,will show when change password
	 */
	private DynamicText textTip;
	/**
	 * Password,will show when change password
	 */
	private Password nextpass;

	/**
	 * content
	 */
	private JPanel content;
	/**
	 * Login button
	 */
	private TextButton Login;
	/**
	 * regist button
	 */
	private TextButton regist;
	/**
	 * submit button
	 */
	private TextButton submit;
	/**
	 * changeRegist button
	 */
	private TextButton changeRegist;
	/**
	 * changePass button
	 */
	private TextButton changePass;

	public MainGui() {
		new UIStyle();
		UIStyle.barHeight = 40;
		jFrame = new JFrame();
		// Sets the operation that will happen by default whenthe user initiates a
		// "close" on this frame.
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel();
		content.setLayout(null);
		jFrame.add(content);
		jFrame.setBounds((int) ((UIStyle.ScreenWidth - UIStyle.width) / 2),
				(int) (UIStyle.ScreenHeight - UIStyle.height) / 2, (int) UIStyle.width, (int) UIStyle.height);
		//jf.setUndecorated(true);
		jFrame.setVisible(true);
		// init userName InputText
		userName = new InputText(250, 35, 15, true, UIStyle.width / 2, UIStyle.height / 2 - 30 - yOfferset, "UserName",
				false);
		content.add(userName);
		// init pass
		pass = new Password(UIStyle.width / 2, UIStyle.height / 2 + 20 - yOfferset, 250, 35);
		content.add(pass);

		// init textTip
		textTip = new DynamicText(UIStyle.width / 2 - 124, UIStyle.height / 2 - 55, "left", Color.WHITE,
				UIStyle.BLUE_BUTTRESS, "Input New password", 180, 16, UIStyle.SMALL_FONT);
		content.add(textTip);
		textTip.setVisible(false);
		// init nextpass
		nextpass = new Password(UIStyle.width / 2, UIStyle.height / 2 + 80 - yOfferset, 250, 35);
		content.add(nextpass);
		nextpass.setVisible(false);

		content.setBackground(Color.WHITE);
		// init Login
		Login = new TextButton(UIStyle.width / 2, UIStyle.height / 2 + 120 - yOfferset, Color.decode("#6EE6B1"),
				Color.black, "Login", 120, 35, "normal", true);
		content.add(Login);
		// init regist
		regist = new TextButton(UIStyle.width / 2, UIStyle.height / 2 + 130 - yOfferset, Color.decode("#6EE6B1"),
				Color.black, "Regist", 120, 35, "normal", true);
		content.add(regist);
		regist.setVisible(false);
		// init submit
		submit = new TextButton(UIStyle.width / 2, UIStyle.height / 2 + 130 - yOfferset, Color.decode("#6EE6B1"),
				Color.black, "Submit", 120, 35, "normal", true);
		content.add(submit);
		submit.setVisible(false);
		// init changeRegist
		changeRegist = new TextButton(UIStyle.width / 2 - 70, UIStyle.height / 2 + 170 - yOfferset,
				Color.decode("#f8fafb"), Color.black, "Change to Regist", 120, 16, "small", false);
		content.add(changeRegist);
		// init changePass
		changePass = new TextButton(UIStyle.width / 2 + 70, UIStyle.height / 2 + 170 - yOfferset,
				Color.decode("#f8fafb"), Color.black, "Change Passwrod", 120, 16, "small", false);
		content.add(changePass);
		//add click listenr on button
		addClickListener();
		content.repaint();
	}

	/**
	 * set mouse click listener
	 * 
	 * @param changeRegist button
	 * @param Login        button
	 */
	private void addClickListener() {
		changeRegist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//Controls the hiding and displaying of Component.
				Login.setVisible(false);
				regist.setVisible(true);
				submit.setVisible(false);

				changeRegist.setVisible(false);
				changePass.setVisible(false);
				userName.setText("");
				pass.setText("");
			}
		});

		changePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				//Controls the hiding and displaying of Component.
				Login.setVisible(false);
				regist.setVisible(false);
				submit.setVisible(true);
				changeRegist.setVisible(false);
				changePass.setVisible(false);

				textTip.setVisible(true);
				nextpass.setVisible(true);
				userName.setText("");
				pass.setText("");
				nextpass.setText("");
			}
		});

		Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String textUser = userName.getText();
				String textPass = pass.getText();
				//adjust the input content
				if (textUser.equals("UserName") || textPass.equals("Password") || textUser.length() == 0
						|| textPass.length() == 0) {
					new MyReminder(content).WRONG("Username and password can not be empty");
					return;
				}
				//read all user
				List<UserInfo> readUsers = FileUtils.readUsers();
				//find the user through the password
				Optional<UserInfo> optional = readUsers.stream()
						.filter(user -> user.getUserName().equals(textUser) && user.getPassword().equals(textPass))
						.findFirst();
				//can not find the user
				if (!optional.isPresent()) {
					new MyReminder(content).WRONG("Username or password is error");
				} else {
					//when find the user
					UserInfo loginUser = optional.get();
					System.out.println(loginUser);
					new MyReminder(content).OK("Login success");
					//hide all component
					for (Component component : content.getComponents()) {
						component.setVisible(false);
					}
					//add log out button on interface
					TextButton logOut = new TextButton(UIStyle.width / 2, UIStyle.height / 2 + 130 - yOfferset,
							Color.decode("#6EE6B1"), Color.black, "logOut", 120, 35, "normal", true);
					TextButton logOut2 = new TextButton(UIStyle.width / 2, UIStyle.height / 2 + 170 - yOfferset,
					        Color.decode("#6EE6B1"), Color.black, "Exit", 120, 35, "normal", true);
					content.add(logOut);
					content.add(logOut2);
					//log out listener
					logOut.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							super.mouseClicked(e);
							jFrame.dispose();
							FileUtils.removeUser(loginUser);
							new MainGui();
						}
					});
					logOut2.addMouseListener(new MouseAdapter() {
					    @Override
					    public void mouseClicked(MouseEvent e) {
					        super.mouseClicked(e);
					        jFrame.dispose();
					        new MainGui();
					    }
					});
				}
			}
		});
		regist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String textUser = userName.getText();
				String textPass = pass.getText();
				//adjust the input content
				if (textUser.equals("UserName") || textPass.equals("Password") || textUser.length() == 0
						|| textPass.length() == 0) {
					new MyReminder(content).WRONG("Username and password can not be empty");
					return;
				}
				//regist user into json file
				UserInfo userInfo = new UserInfo(textUser, textPass);
				UserInfo user = FileUtils.addUser(userInfo);
				//when the user is exist
				if (user.getId() == -1) {
					new MyReminder(content).WRONG("Username is exist");
				} else {
					//regist success,go to login
					new MyReminder(content).OK("Regist success,Please go to login.");
					Login.setVisible(true);
					regist.setVisible(false);
					changeRegist.setVisible(true);
					changePass.setVisible(true);
					userName.setText("");
					pass.setText("");
					nextpass.setText("");
				}
			}
		});
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String textUser = userName.getText();
				String textPass = pass.getText();
				String textNextPass = nextpass.getText();
				//adjust the inout conent
				if (textUser.equals("UserName") || textPass.equals("Password") || textNextPass.equals("Password")
						|| textUser.length() == 0 || textPass.length() == 0 || textNextPass.length() == 0) {
					new MyReminder(content).WRONG("Username and password can not be empty");
					return;
				}
				//update the user password
				UserInfo userInfo = new UserInfo(textUser, textPass);
				UserInfo user = FileUtils.UpdateUser(userInfo, textNextPass);
				if (user == null) {
					//if can not find the user,show error message
					new MyReminder(content).WRONG("Username or password is error");
				} else {
					//change password success
					new MyReminder(content).OK("Change password success,Please go to login.");
					Login.setVisible(true);
					regist.setVisible(false);
					submit.setVisible(false);

					textTip.setVisible(false);
					nextpass.setVisible(false);
					changeRegist.setVisible(true);
					changePass.setVisible(true);
					userName.setText("");
					pass.setText("");
					nextpass.setText("");
				}
			}
		});
	}

	public static void main(String[] args) {
		new MainGui();
	}

}
