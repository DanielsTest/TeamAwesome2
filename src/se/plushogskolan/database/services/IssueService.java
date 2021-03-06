package se.plushogskolan.database.services;

import java.util.List;

import se.plushogskolan.database.model.Issue;
import se.plushogskolan.database.model.WorkItem;
import se.plushogskolan.database.repository.IssueRepository;
import se.plushogskolan.database.repository.RepositoryException;
import se.plushogskolan.database.repository.ServiceException;
import se.plushogskolan.database.repository.WorkItemRepository;

public class IssueService {
	private IssueRepository issueRepository;
	private WorkItemRepository workItemRepository;

	public IssueService(IssueRepository issueRepository,WorkItemRepository workItemRepository) {
		this.issueRepository = issueRepository;
		this.workItemRepository = workItemRepository;
	}

	public void createIssue(Issue issue) {
		try {
			if (!issueRepository.exists(issue)) {
				issueRepository.createIssue(issue);
				
			} else {
				throw new ServiceException("Create issue failed. Issue with same description already exists.");
			}
		} catch (RepositoryException e) {
			throw new ServiceException("Could not create new issue", e);
		}
	}

	
	public boolean assignToWorkItem(Issue issue, String itemid) {
		
		try {
		  WorkItem item = workItemRepository.getById(itemid);
		  if (item.getStatus().equals("Done")){ 
			issueRepository.assignToWorkItem(issue, itemid);
			workItemRepository.changeStatus(itemid,"Unstarted");
			return true;
		  }else{
			  return false;
		  }			
		} catch (RepositoryException e) {
			throw new ServiceException("Could not create new issue", e);
		}
		
	}
    
	public void updateIssue(Issue issue, String new_description) {
		try {
			if (!issueRepository.exists(issue)) {
				issueRepository.updateIssue(issue, new_description);
				
			} else
				throw new ServiceException("Update issue failed. Issue with same description already exists.");
		} catch (RepositoryException e) {
			throw new ServiceException("Could not update issue with id:" + issue.getId(), e);
		}
	}

	public List<WorkItem> getAllItemsWithIssue() {
		try {
			return issueRepository.getAllItemsWithIssue();
		} catch (RepositoryException e) {
			throw new ServiceException("Could not get all items with issue", e);
		}
	}
	
	
}
