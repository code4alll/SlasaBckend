package com.ecommerce.slasa.Enums;

public enum Roles {

	ADMIN("Admin"),
	USER("User");

    private String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
    
    public static Roles fromString(String roleName) {
        for (Roles role : Roles.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant found for roleName: " + roleName);
    }

    @Override
    public String toString() {
        return roleName;
    }
}
