package com.comtalk.dto;

public class User2Group {
	private User user;
	private Group group;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "User2Group [user=" + user + ", group=" + group + "]";
	}
	
}
