
Feature: Managing patient appointments based on type, insurance, and doctor availability

  Scenario Outline: Check appointment status
    Given Doctor "<doctorName>" is available until "<availableUntil>" in department "<department>"
    When Patient of type "<patientType>" with insurance status "<insuranceStatus>" requests an appointment at "<appointmentTime>"
    Then The appointment status should be "<expectedResult>"

    Examples:
      | patientType | insuranceStatus | doctorName  | department | availableUntil | appointmentTime       | expectedResult |
      | Normal      | Approved        | Dr. Razavi  | Cardiology | 2023-12-31     | 2023-10-10T10:00      | Approved       |
      | Emergency   | Rejected        | Dr. Hosseini| Pediatrics | 2023-11-15     | 2023-11-20T09:30      | Rejected       |
      | Normal      | Rejected        | Dr. Mohammadi| Orthopedics| 2024-01-10     | 2023-12-25T14:00      | Rejected       |
