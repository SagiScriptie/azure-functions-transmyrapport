package org.sogeti.functions.exportTransaction.httptriggers;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TableOutput;
import org.sogeti.functions.exportTransaction.models.ExportTransaction;

import java.util.Optional;
import java.util.UUID;

/**
 * Azure Functions (ExportTransaction) with HTTP Trigger.
 * Endpoint: https://azure-function-1650971708752.azurewebsites.net/api/add-export-transaction
 */
public class AddExportTransactionHttpTriggerFunction {
    @FunctionName("addExportTransaction")
    public HttpResponseMessage run(
            @HttpTrigger(name = "addExportTransaction", methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS, route = "add-export-transaction")
                    HttpRequestMessage<Optional<ExportTransaction>> httpRequestMessage,
            @TableOutput(name = "ExportTransaction", tableName = "ExportTransaction",
                    connection = "AzureWebJobsStorage")
                    OutputBinding<ExportTransaction> exportTransactionOutputBinding,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        final Optional<ExportTransaction> requestBody = httpRequestMessage.getBody();
        if (requestBody.isPresent()) {
            ExportTransaction exportTransaction = new ExportTransaction(
                    String.valueOf(UUID.randomUUID()), //random id value for Partitionkey
                    String.valueOf(UUID.randomUUID()), //random id value for Rowkey
                    requestBody.get().getOrigin(),
                    requestBody.get().getDestination(),
                    requestBody.get().getTransportationBy(),
                    requestBody.get().getProductCategory(),
                    requestBody.get().getProductName(),
                    requestBody.get().getVatRate(),
                    requestBody.get().getQuantity(),
                    requestBody.get().getCost()
            );
            exportTransactionOutputBinding.setValue(exportTransaction);
            context.getLogger().info("Java Table Output function write a new entity with: "
                    + exportTransactionOutputBinding.getValue());

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


