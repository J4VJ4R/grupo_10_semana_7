package grupo10.medicalappointments.model.repositories.http;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.Doctor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpConnection {
    private String baseUrl;

    public class HttpResponseData {
        private int code;

        private String body;

        public HttpResponseData(int code, String body) {
            this.code = code;
            this.body = body;
        }

        public int getCode() {
            return code;
        }

        public String getBody() {
            return body;
        }
    }

    OkHttpClient client = new OkHttpClient();

    public HttpConnection(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private Promise<HttpResponseData> runRequest(Request request){
        return new Promise<>((accept, reject) -> {

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                try (Response response = client.newCall(request).execute()) {
                    int code = response.code();
                    String body = response.body().string();

                    handler.post(
                            () -> accept.run(new HttpResponseData(code, body))
                    );
                } catch (IOException e) {
                    reject.run(e);
                }
            });
        });
    }

    public Promise<HttpResponseData> get(String url) {
        Request request = new Request.Builder()
                .url(baseUrl + url)
                .build();

        return runRequest(request);
    }

    public Promise<HttpResponseData> post(String url, String body, MediaType mediaType) {
        Request request = new Request.Builder()
                .url(baseUrl + url)
                .method("POST", RequestBody.create(body, mediaType))
                .build();

        return runRequest(request);
    }
}
