import org.junit.Test;
import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.*;
import static play.test.Helpers.*;
import java.util.concurrent.CompletionStage;
import static org.junit.Assert.assertEquals;

public class IPStrictActionTest{

    @Test
    public void pingTest(){
        TestServer server = testServer(3333);
        running(server, () -> {
            try (WSClient ws = WSTestClient.newClient(3333)) {
                CompletionStage<WSResponse> completionStage = ws.url("/users/uuid123").addHeader("Authorization", "Bearer accesstokenadmintest123").get();
                WSResponse response = completionStage.toCompletableFuture().get();
                assertEquals(200, response.getStatus());
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
            }
        });
    }
}
