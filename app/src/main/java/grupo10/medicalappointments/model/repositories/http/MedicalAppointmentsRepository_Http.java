package grupo10.medicalappointments.model.repositories.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import grupo10.medicalappointments.model.Promise;
import grupo10.medicalappointments.model.entities.Doctor;
import grupo10.medicalappointments.model.entities.MedicalAppointment;
import grupo10.medicalappointments.model.exceptions.NotFoundException;
import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;
import okhttp3.MediaType;

public class MedicalAppointmentsRepository_Http implements MedicalAppointmentsRepository {
    private final String url = "/function-medical-appointments-data?model=appointments";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final SimpleDateFormat saveDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private HttpConnection connection = new HttpConnection("https://us-central1-deep-byte-396300.cloudfunctions.net");

    @Override
    public Promise add(MedicalAppointment appointment) {
        return new Promise((accept, reject) -> {
            try {
                String appointmentJson = new JSONStringer()
                        .object()
                        .key("name").value(appointment.getName())
                        .key("lastname").value(appointment.getLastname())
                        .key("identification").value(appointment.getIdentification())
                        .key("phone").value(appointment.getPhone())
                        .key("date").value(saveDateFormat.format(appointment.getDate()))
                        .key("doctor_id").value(appointment.getDoctor())
                        .endObject()
                        .toString();

                connection.post(url, appointmentJson, MediaType.parse("application/json"))
                        .then(response -> {
                            if (response.getCode() >= 400 ){
                                reject.run(new Exception("Registration Failed With Code (" + response.getCode() + "): " + response.getBody()));
                            }
                            accept.run(appointment);
                        });

            } catch (JSONException e) {
                reject.run(e);
            }
        });
    }

    @Override
    public Promise<MedicalAppointment> getNextByIdentificationNumber(String id) {
        // Implementación temporal, para tener tiempo a completar la tarea.
        // Obtiene todos los doctores y retorna el que coincida con la ID
        return new Promise<>(
                (accept, reject) -> connection.get(url)
                        .then(responseData -> {
                            try {
                                JSONArray foundAppointments = new JSONArray(responseData.getBody());
                                for (int index = 0; index < foundAppointments.length(); index++){
                                    JSONObject foundObject = foundAppointments.getJSONObject(index);
                                    String foundIdentification = foundObject.getString("identification");
                                    if(foundIdentification.equals(id)){
                                        MedicalAppointment appointment = new MedicalAppointment();
                                        appointment.setId(foundObject.getInt("id"));
                                        appointment.setName(foundObject.getString("name"));
                                        appointment.setLastname(foundObject.getString("lastname"));
                                        appointment.setIdentification(foundIdentification);
                                        appointment.setPhone(foundObject.getString("phone"));
                                        appointment.setDate(dateFormat.parse(
                                                foundObject.getString("date").replace(".000Z", "")
                                        ));
                                        appointment.setDoctor(foundObject.getInt("doctor_id"));
                                        accept.run(appointment);
                                        return;
                                    }
                                }
                                reject.run(new NotFoundException());
                            } catch (JSONException|ParseException e) {
                                reject.run(e);
                            }
                        }).catched(reject)
        );
    }
}
