import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.TestServer;
import play.test.WSTestClient;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.testServer;

public class RateLimitTest {

    private TestServer server;

    private WSClient ws;

    @Before
    public void setUp() throws Exception {
        server = testServer(3333);
        server.start();
        ws = WSTestClient.newClient(3333);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void rateLimitNotReach() throws ExecutionException, InterruptedException {
        CompletionStage<WSResponse> completionStage = ws.url("/users/uuid123").get();
        WSResponse response = completionStage.toCompletableFuture().get();
        assertEquals(OK, response.getStatus());
    }

    @Test
    public void rateLimitReach(){

    }
}
