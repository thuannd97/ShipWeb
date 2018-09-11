package com.thuannd.utils;

public enum RoleEnum {
	ADMIN(1, "ADMIN"), SHOP(2, "SHOP"), SHIPPER(3, "SHIPPER");
	private int roleId;
	private String roleName;

	RoleEnum(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
