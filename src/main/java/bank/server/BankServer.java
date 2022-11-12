package bank.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankServer {
    public static final String HOST = "rmi://localhost";
    public static final int PORT = Registry.REGISTRY_PORT;

    public static void main(String[] args) throws Exception {
        // Object and stub creation (export)
        BankOps robj = new BankOpsImpl();
        // Register and bind object in rmiregistry
        // Server passes a copy of the stub to rmiregistry
        Registry registry = LocateRegistry.createRegistry(PORT);
        String rmiObjectName = "BANKOPS";
        registry.bind(rmiObjectName, robj);
    }
}
