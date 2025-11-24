enum UserRole {
    ADMIN("所有权限"),
    USER("读写权限"),
    GUEST("只读权限");
    private String permissions;
    UserRole(String permissions) {
        this.permissions = permissions;
    }
    public String getPermissions() {
        return permissions;
    }
}
public class t18 {
    public static void main(String[] args) {
        for(UserRole role:UserRole.values()){
            System.out.println(role.name()+": "+role.getPermissions());
        }
    }
}