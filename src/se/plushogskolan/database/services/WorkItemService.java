package se.plushogskolan.database.services;

import se.plushogskolan.database.repository.ServiceException;
import se.plushogskolan.database.repository.UserRepository;
import se.plushogskolan.database.repository.WorkItemRepository;
import se.plushogskolan.database.repository.mysql.MySQLUserRepository;
import se.plushogskolan.database.model.User;
import se.plushogskolan.database.model.WorkItem;

import java.util.List;
import java.util.UUID;

public class WorkItemService {

	WorkItemRepository wir;

	public WorkItemService(WorkItemRepository wir) {
		this.wir = wir;
	}

	public void addWorkItem(WorkItem workitem) {
		WorkItem item = new WorkItem(workitem.getTitle(), workitem.getStatus(),
				workitem.getUserId(), workitem.getIssueId());
		wir.addWorkItem(item);
	}

	public void changeStatus(String id, String status) {
		wir.changeStatus(id, status);
	}

	public void delete(String id) {
		wir.delete(id);
	}

	public void assignItemToUser(String itemId, String userId) {
		UserRepository userRepository= new MySQLUserRepository();
		UserService us = new UserService(userRepository);
		User user=us.getUserById(userId);
		if(user.getStatus().equals("inactive")){
			throw new ServiceException("Assign workitem to user failed, user is inactive");
		}
		List<WorkItem> items = this.wir.getAllByUser(userId);
		if (items.size() < 5) {
			this.wir.assignItemToUser(itemId, userId);
		}else{
			throw new ServiceException("Assign workitem to user failed, user has already 5 workitems.");
		}
	}

	public List<WorkItem> getAllByStatus(String status) {
		return this.wir.getAllByStatus(status);
	}

	public List<WorkItem> getAllByTeam(String teamId) {
		return this.wir.getAllByTeam(teamId);
	}

	public List<WorkItem> getAllByUser(String id) {
		return this.wir.getAllByUser(id);
	}
	
	public WorkItem getById(String id){
		return this.wir.getById(id);
	}
}
