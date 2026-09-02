package com.github.maximovj.msapiloans.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    description = "DTO para retornar un préstamo",
    title = "Respuesta de Préstamo",
    example = """
        {
          "id": 1001,
          "userId": 12345,
          "amount": 5000.00,
          "income": 2500.00,
          "loanTerm": 36,
          "loanType": "PERSONAL",
          "email": "cliente@ejemplo.com",
          "phoneNumber": "+521234567890",
          "apr": 12.5,
          "totalInterest": 987.65,
          "monthlyPayment": 185.80,
          "status": "APPROVED",
          "rejectionReason": null,
          "createdAt": "2026-09-01T10:30:00",
          "updatedAt": "2026-09-01T15:45:30"
        }
        """
)
public class LoanResponse {

    @Schema(
        description = "ID único del préstamo",
        example = "1001",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "1"
    )
    private Long id;

    @Schema(
        description = "ID del usuario asociado al préstamo",
        example = "12345",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "1"
    )
    private Long userId;

    @Schema(
        description = "Monto total del préstamo",
        example = "5000.00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "0.01",
        maximum = "1000000.00"
    )
    private BigDecimal amount;

    @Schema(
        description = "Ingreso mensual del solicitante",
        example = "2500.00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "0.01"
    )
    private BigDecimal income;

    @Schema(
        description = "Plazo del préstamo en meses",
        example = "36",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "3",
        maximum = "120"
    )
    private Integer loanTerm;

    @Schema(
        description = "Tipo de préstamo",
        example = "PERSONAL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        allowableValues = {"PERSONAL", "AUTO", "HIPOTECARIO", "EDUCATIVO", "EMPRESARIAL"}
    )
    private String loanType;

    @Schema(
        description = "Correo electrónico del solicitante",
        example = "cliente@ejemplo.com",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        maxLength = 100
    )
    private String email;

    @Schema(
        description = "Número de teléfono del solicitante con código de país",
        example = "+521234567890",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        pattern = "^\\+?[0-9]{10,15}$",
        minLength = 10,
        maxLength = 15
    )
    private String phoneNumber;

    @Schema(
        description = "Tasa Anual Equivalente (TAE) del préstamo en porcentaje",
        example = "12.5",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "0.01",
        maximum = "100.00"
    )
    private BigDecimal apr;

    @Schema(
        description = "Total de intereses a pagar durante todo el plazo del préstamo",
        example = "987.65",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "0.00"
    )
    private BigDecimal totalInterest;

    @Schema(
        description = "Monto del pago mensual a realizar",
        example = "185.80",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        minimum = "0.01"
    )
    private BigDecimal monthlyPayment;

    @Schema(
        description = "Estado actual del préstamo",
        example = "APPROVED",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        allowableValues = {"PENDING", "APPROVED", "REJECTED", "CANCELLED", "ACTIVE", "PAID", "DEFAULTED"},
        enumAsRef = true
    )
    private String status;

    @Schema(
        description = "Motivo del rechazo en caso de que la solicitud sea denegada",
        example = "Ingreso insuficiente para el monto solicitado",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        readOnly = true,
        nullable = true,
        maxLength = 500
    )
    private String rejectionReason;

    @Schema(
        description = "Fecha y hora de creación del préstamo",
        example = "2026-09-01T10:30:00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        format = "date-time",
        type = "string"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @Schema(
        description = "Fecha y hora de la última actualización del préstamo",
        example = "2026-09-01T15:45:30",
        requiredMode = Schema.RequiredMode.REQUIRED,
        readOnly = true,
        format = "date-time",
        type = "string"
    )
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;
}