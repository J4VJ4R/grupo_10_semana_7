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
import java.util.stream.Stream;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.exceptions.SaveFailedException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DoctorsRepository_Http implements DoctorsRepository {
    @Override
    public void add(Doctor doctor) throws SaveFailedException {

    }

    @Override
    public Promise<Stream<Doctor>> getAll() {
        return  new Promise<>((accept, reject) -> {
            List<Doctor> doctorsResult = new ArrayList<>();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://us-central1-deep-byte-396300.cloudfunctions.net/function-medical-appointments-data?model=doctors")
                    .build();

            executor.execute(() -> {
                try (Response response = client.newCall(request).execute()){
                    String result = response.body().string();

                    handler.post(() -> {
                        try{
                            JSONArray doctorsJson = new JSONArray(result);
                            for (int index = 0; index < doctorsJson.length(); index++){
                                JSONObject doctorJson = doctorsJson.getJSONObject(index);
                                int id = doctorJson.getInt("id");
                                String name = doctorJson.getString("name");
                                String specialty = doctorJson.getString("specialty");
                                Doctor doctor = new Doctor(name, specialty);
                                doctor.setId(id);

                                doctorsResult.add(doctor);
                            }
                            accept.run(doctorsResult.stream());
                        }catch (JSONException e) {
                            reject.run(e);
                        }
                    });
                } catch (IOException e){
                    reject.run(e);
                }
            });
        });
    }



    @Override
    public Doctor getById(int id) throws NotFoundException {
        return null;
    }
}
