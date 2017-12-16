import org.junit.Test;
import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.TestServer;
import play.test.WSTestClient;

import java.util.concurrent.CompletionStage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class RateLimitTest {

    @Test
    public void rateLimitReached() {
        TestServer server = testServer(3333);
        running(server, () -> {
            try (WSClient ws = WSTestClient.newClient(3333)) {
                boolean rateLimitNoReach = true;
                for (int i = 1; i <= 3; i++) {
                    CompletionStage<WSResponse> completionStage = ws.url("/users/uuid123").get();
                    WSResponse response = completionStage.toCompletableFuture().get();
                    rateLimitNoReach = rateLimitNoReach && (response.getStatus() == 200);
                }
                assertEquals(false, rateLimitNoReach);

            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
        });
    }

    @Test
    public void rateLimitNotReached() {
        TestServer server = testServer(3333);
        running(server, () -> {
            try (WSClient ws = WSTestClient.newClient(3333)) {
                CompletionStage<WSResponse> completionStage = ws.url("/users/uuid123").get();
                WSResponse response = completionStage.toCompletableFuture().get();
                assertEquals(200, response.getStatus());
                assertTrue(Integer.valueOf(response.getHeaders().get("X-Remaining-Ratelimit").get(0)) == 9);
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
        });
    }
}
