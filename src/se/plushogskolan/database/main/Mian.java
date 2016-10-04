package se.plushogskolan.database.main;

import java.rmi.server.UID;
import java.util.List;

import se.plushogskolan.database.model.Issue;
import se.plushogskolan.database.model.Team;
import se.plushogskolan.database.model.User;
import se.plushogskolan.database.model.WorkItem;
import se.plushogskolan.database.repository.IssueRepository;
import se.plushogskolan.database.repository.RepositoryException;
import se.plushogskolan.database.repository.TeamRepository;
import se.plushogskolan.database.repository.UserRepository;
import se.plushogskolan.database.repository.WorkItemRepository;
import se.plushogskolan.database.repository.mysql.MySQLIssueRepository;
import se.plushogskolan.database.repository.mysql.MySQLTeamRepository;
import se.plushogskolan.database.repository.mysql.MySQLUserRepository;
import se.plushogskolan.database.repository.mysql.MySQLWorkItemRepository;
import se.plushogskolan.database.services.IssueService;
import se.plushogskolan.database.services.TeamService;
import se.plushogskolan.database.services.UserService;
import se.plushogskolan.database.services.WorkItemService;

public class Mian {

public static void main(String args[]) {
		//Daniels Test line!!!!
		//Daniels Test line 2!!!
		
		IssueRepository issueRepository = new MySQLIssueRepository();
		TeamRepository teamRepository = new MySQLTeamRepository();
		UserRepository userRepository = new MySQLUserRepository();
		WorkItemRepository workItemRepository = new MySQLWorkItemRepository();
		
		UserService us=new UserService(userRepository);
		User user=new User("fn", "ln", "usernamefvvvvvvm", null, "active");
		us.addUser(user);
		
//		TeamService ts=new TeamService(teamRepository);
//		Team team=new Team("teamname","active");
//		ts.addTeam(team);
//		
//		IssueService is=new IssueService(issueRepository, workItemRepository);
//		Issue issue=new Issue("description");
//		is.createIssue(issue);
//		
//		WorkItemService wis=new WorkItemService(workItemRepository);
//		WorkItem wi=new WorkItem("title", "Done", "af7f2834-68b6-40b6-92c7-5378d0b55fef",null);
//		wis.addWorkItem(wi);
//		
//		Issue issue2=new Issue("hu");
//		is.createIssue(issue2);
		//is.assignToWorkItem(issue2, "42672bff-6e1a-4b3d-bd2b-b1fa83ccb12b");
		
//		is.assignToWorkItem(issue, "42672bff-6e1a-4b3d-bd2b-b1fa83ccb12b");
		
		
		
//		try {
//			issueRepository.createIssueAndAssignToWorkItem("first issue", 1);
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			teamRepository.addUserToTeam(1, 2);
//		} catch (RepositoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		workItemRepository.addWorkItem(new WorkItem(5, "workItemTest", "done", 2, 1));
		
//		UserService userService = new UserService(new MySQLUserRepository());
		
//		User user = new User("Hans", "Hansson", "hsfsfsfs20r00", null, "active");
//		userService.addUser(user);
//		
//		User user2 = userService.getUserById("1");
//		System.out.println(user2.getFirstname() + " " + user2.getLastname());
//		
//		User user3 = userService.getUserByUsername("johanjohan");
//		System.out.println(user3.getFirstname() + " " + user3.getLastname());
//		
//		List<User> allUsersInTeam = userService.getAllUsersInTeam(1);
//		System.out.println(allUsersInTeam.size());
		
//		userService.deactivateUser("imant83");
		
//		User newUser = new User("imaaan", "xxxxxxxxxxxx", "newusername2", 1, "active");
//		userService.updateUser(newUser, "hansson2000");
	}
}
