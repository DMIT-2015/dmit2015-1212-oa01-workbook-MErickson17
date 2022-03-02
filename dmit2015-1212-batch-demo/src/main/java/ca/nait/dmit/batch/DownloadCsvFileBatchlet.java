package ca.nait.dmit.batch;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.batch.runtime.BatchStatus;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Named
public class DownloadCsvFileBatchlet extends AbstractBatchlet {

    @Inject
    private JobContext _jobContext;

    @Inject
    @BatchProperty(name = "csv_file_uri") // get location from our job properties
    private String _csvFileUri;

    @Inject
    @BatchProperty(name = "file_download_path") // get location from our job properties
    private String _fileDownloadPath;

    @Override
    public String process() throws Exception {
        String batchStatus = BatchStatus.COMPLETED.toString();

        HttpClient client = HttpClient.newHttpClient();
        //final String downloadUriString = "https://data.edmonton.ca/api/views/akzz-54k3/rows.csv?accessType=DOWNLOAD"; // don't want to hard code this location.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_csvFileUri))
                .build();
        Path downloadPath = Path.of(_fileDownloadPath);	// On a Windows double back-slash such as C:\\DMIT2015\\Downloads
        HttpResponse<Path> response = client.send(request,
                HttpResponse.BodyHandlers.ofFileDownload(downloadPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE));
//        System.out.println("Status code: " + response.statusCode());
//        System.out.println("\n Body: " + response.body());
        if(response.statusCode() != Response.Status.OK.getStatusCode())
        {
            batchStatus = BatchStatus.FAILED.toString();
        }

        return batchStatus;
    }
}
