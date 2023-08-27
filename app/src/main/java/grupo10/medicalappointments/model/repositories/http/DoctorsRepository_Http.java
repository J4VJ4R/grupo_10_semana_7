package grupo10.medicalappointments.model.repositories.http;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DoctorsRepository_Http implements DoctorsRepository {
    private final String url = "/function-medical-appointments-data?model=doctors";
    private HttpConnection connection = new HttpConnection("https://us-central1-deep-byte-396300.cloudfunctions.net");

    @Override
    public Promise add(Doctor doctor) {
        return new Promise((accept, reject) -> {
            try {
                String doctorJson = new JSONStringer()
                        .object()
                        .key("name").value(doctor.getName())
                        .key("specialty").value(doctor.getSpecialty())
                        .endObject()
                        .toString();

                connection.post(url, doctorJson, MediaType.parse("application/json"))
                        .then(response -> accept.run(doctor));

            } catch (JSONException e) {
                reject.run(e);
            }
        });
    }

    @Override
    public Promise<Stream<Doctor>> getAll() {
        return new Promise<>((accept, reject) -> connection
                .get("/function-medical-appointments-data?model=doctors")
                .then(data -> {
                    try {
                        List<Doctor> doctorsResult = new ArrayList<>();
                        JSONArray doctorsJson = new JSONArray(data.getBody());
                        for (int index = 0; index < doctorsJson.length(); index++) {
                            JSONObject doctorJson = doctorsJson.getJSONObject(index);
                            int id = doctorJson.getInt("id");
                            String name = doctorJson.getString("name");
                            String specialty = doctorJson.getString("specialty");
                            Doctor doctor = new Doctor(name, specialty);
                            doctor.setId(id);

                            doctorsResult.add(doctor);
                        }
                        accept.run(doctorsResult.stream());
                    } catch (JSONException e) {
                        reject.run(e);
                    }
                }));
    }


    @Override
    public Promise<Doctor> getById(int id) {
        // Implementación temporal, para tener tiempo a completar la tarea.
        // Obtiene todos los doctores y retorna el que coincida con la ID
        return new Promise<>(
                (accept, reject) -> getAll()
                        .then(doctors -> {
                            Doctor foundDoctor = doctors.filter(d -> d.getId() == id)
                                    .findFirst()
                                    .orElse(null);

                            if (foundDoctor != null) {
                                accept.run(foundDoctor);
                            } else {
                                reject.run(new NotFoundException());
                            }
                        }).catched(reject)
        );
    }
}
