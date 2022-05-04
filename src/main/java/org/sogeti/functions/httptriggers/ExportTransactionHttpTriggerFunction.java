package org.sogeti.functions.httptriggers;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TableOutput;
import org.sogeti.functions.models.ExportTransaction;

import java.util.UUID;

/**
 * Azure Functions (ExportTransaction) with HTTP Trigger.
 * Endpoint: https://azure-function-1650971708752.azurewebsites.net/api/export-transaction
 */
public class ExportTransactionHttpTriggerFunction {
    @FunctionName("addExportTransaction")
    public HttpResponseMessage run(
            @HttpTrigger(name = "postExportTransaction", methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS, route = "export-transaction")
                    HttpRequestMessage<ExportTransaction> httpRequestMessage,
            @TableOutput(name = "ExportTransaction", tableName = "ExportTransaction",
                    connection = "AzureWebJobsStorage")
                    OutputBinding<ExportTransaction> exportTransactionOutputBinding,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        //TODO add try/catch block
        final ExportTransaction requestBody = httpRequestMessage.getBody();
        if (requestBody != null) {
            ExportTransaction exportTransaction = new ExportTransaction(
                    String.valueOf(UUID.randomUUID()), //random id value for Partitionkey
                    String.valueOf(UUID.randomUUID()), //random id value for Rowkey
                    requestBody.getOrigin(),
                    requestBody.getDestination(),
                    requestBody.getTransportationBy(),
                    requestBody.getProductCategory(),
                    requestBody.getProductName(),
                    requestBody.getVatRate(),
                    requestBody.getQuantity(),
                    requestBody.getCost()
            );
            exportTransactionOutputBinding.setValue(exportTransaction);

            return httpRequestMessage.createResponseBuilder(HttpStatus.CREATED)
                    .header("Content-type", "Application/json")
                    .body(exportTransactionOutputBinding.getValue())
                    .build();
        }
        return httpRequestMessage.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Please pass in a valid export transaction")
                .build();
    }
}


