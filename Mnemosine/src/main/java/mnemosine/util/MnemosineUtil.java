package mnemosine.util;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.rest.LogLevel;

public class MnemosineUtil {
    public static ApplicationTokenCredentials buildCredentials(String clientId, String tenantId, String secret) {
        return new ApplicationTokenCredentials(clientId, tenantId, secret, AzureEnvironment.AZURE);
    }

    public static Azure buildAzure(ApplicationTokenCredentials credentials, String subscriptionId) {
        return Azure.configure()
                .withLogLevel(LogLevel.NONE)
                .authenticate(credentials)
                .withSubscription(subscriptionId);
    }
}
