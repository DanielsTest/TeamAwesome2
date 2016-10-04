package se.plushogskolan.database.model;

import java.util.UUID;

public final class WorkItem {
    private final String id;
    private final String title;
    private final String status;
    private final String userId;
    private final String issueId;
    
	public WorkItem(String id, String title, String status, String userId, String issueId) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.userId = userId;
		this.issueId = issueId;
	}

	public WorkItem(String title, String status, String userId, String issueId) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.status = status;
		this.userId = userId;
		this.issueId = issueId;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public String getUserId() {
		return userId;
	}

	public String getIssueId() {
		return issueId;
	}
	
	@Override
	public String toString() {
		return "WorkItem["+id+", "+title+"]";
	}
}
