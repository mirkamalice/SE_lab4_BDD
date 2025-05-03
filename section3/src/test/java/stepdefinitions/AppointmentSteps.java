
package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class AppointmentSteps {

    private LocalDateTime appointmentTime;
    private LocalDateTime doctorAvailableUntil;
    private String patientType;
    private String insuranceStatus;
    private String result;

    @Given("Doctor {string} is available until {string} in department {string}")
    public void doctorIsAvailable(String doctorName, String availableUntil, String department) {
        this.doctorAvailableUntil = LocalDateTime.parse(availableUntil + "T23:59:59");
    }

    @When("Patient of type {string} with insurance status {string} requests an appointment at {string}")
    public void patientRequestsAppointment(String type, String insurance, String time) {
        this.patientType = type;
        this.insuranceStatus = insurance;
        this.appointmentTime = LocalDateTime.parse(time);

        if (appointmentTime.isAfter(doctorAvailableUntil)) {
            result = "Rejected";
            return;
        }

        if (patientType.equals("Emergency")) {
            result = "Approved";
        } else {
            if (insuranceStatus.equals("Approved")) {
                result = "Approved";
            } else {
                result = "Rejected";
            }
        }
    }

    @Then("The appointment status should be {string}")
    public void verifyResult(String expected) {
        assertEquals(expected, result);
    }
}
