package security;

public interface PasswordEncoder{
    String encode(String rawpassword);
    boolean matches(String rawpassword, String encodedPassword);
}