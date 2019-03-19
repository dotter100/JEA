//package Stocks.Controlleres;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//
//import java.util.HashSet;
//
//import static java.util.Arrays.asList;
//import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
//
//@ApplicationScoped
//public class TestIdentityStore implements IdentityStore {
//
//    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
//        if (usernamePasswordCredential.compareTo("test", "secret")) {
//            return new CredentialValidationResult("test", new HashSet<>(asList("ADMIN", "USER")));
//        }
//
//        return INVALID_RESULT;
//    }
//}
