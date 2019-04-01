package Stocks.Controlleres;

import javax.annotation.security.*;
import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;


@ApplicationScoped
@ApplicationPath("idk")

//@BasicAuthenticationMechanismDefinition(realmName = "fileRealm")
public class Application extends javax.ws.rs.core.Application {
}
