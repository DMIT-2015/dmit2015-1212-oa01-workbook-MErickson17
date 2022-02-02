package dmit2015.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;

/**
 *  curl -k -i -X GET http://localhost:8080/dmit2015-1212-jaxrs-demo/webapi/helloworld -H 'Accept: text/plain'
 *  curl -k -i -X GET http://localhost:8080/dmit2015-1212-jaxrs-demo/webapi/helloworld -H 'Accept: text/html'
 *  curl -k -i -X GET http://localhost:8080/dmit2015-1212-jaxrs-demo/webapi/helloworld -H 'Accept: application/json'
 *
 *  curl -k -i -X GET http://localhost:8080/dmit2015-1212-jaxrs-demo/webapi/helloworld/image --output hello.png
 *  Above: -H (header) path is not needed because there is only one method under that path;
 *  the output just lets us download it; don't need to do this to test.
 */
@RequestScoped // this resource is created for one request; object is disposed after the response reaches the client
@Path("/helloworld") // to access methods in here, need to provide a path of helloworld
public class HelloWorldResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN) //@Produces("text/plain")
    public Response helloWorldText() {
        String message = "Hello World from JAX-RS!";
        return Response.ok(message).build();
    }
    @GET
    @Produces(MediaType.TEXT_HTML) // "text/html"
    public Response helloWorldHtml() {
        String message = "<p>Hello World from <strong>JAX-RS</strong>";
        return Response.ok(message).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)   // "application/json"
    public Response helloWorldJson() {
        String message = "{\"message\":\"Hello World from JAX-RS\"}"; // a json object {}; key-value pairs
        return Response.ok(message).build();
    }
    @Path("/image")
    @GET
    @Produces("image/png")
    public Response helloImage(@Context HttpServletRequest request) {
//        InputStream is = getClass().getResourceAsStream("/images/hello_world.png");
//        return Response
//                .ok(is)
//                .header("Content-Disposition","attachment; filename=hello_world.png")
//                .build();

        File imageFile = new File("/home/user2015/Pictures/world.png");
        return Response
                .ok(imageFile)
                .header("Content-Disposition","attachment; filename=world.png")
                .build();
    }
}