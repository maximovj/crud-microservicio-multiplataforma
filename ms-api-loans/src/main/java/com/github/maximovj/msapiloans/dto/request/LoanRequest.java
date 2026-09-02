package com.github.maximovj.msapiloans.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
    description = "DTO para la creación de un préstamo",
    title = "Solicitud de Préstamo",
    example = """
        {
          "userId": 12345,
          "amount": 5000.00,
          "income": 2500.00,
          "loanTerm": 36,
          "loanType": "PERSONAL",
          "email": "cliente@ejemplo.com",
          "phoneNumber": "+521234567890"
        }
        """
)
public class LoanRequest {

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser positivo")
    @Schema(
        description = "ID del usuario que solicita el préstamo",
        example = "12345",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "1"
    )
    private Long userId;

    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 10 dígitos enteros y 2 decimales")
    @Schema(
        description = "Monto total del préstamo solicitado",
        example = "5000.00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "0.01",
        maximum = "1000000.00"
    )
    private BigDecimal amount;

    @NotNull(message = "El ingreso mensual es obligatorio")
    @Positive(message = "El ingreso debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El ingreso debe tener máximo 10 dígitos enteros y 2 decimales")
    @Schema(
        description = "Ingreso mensual del solicitante",
        example = "2500.00",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "0.01"
    )
    private BigDecimal income;

    @NotNull(message = "El plazo del préstamo es obligatorio")
    @Min(value = 3, message = "El plazo mínimo es de 3 meses")
    @Max(value = 120, message = "El plazo máximo es de 120 meses (10 años)")
    @Schema(
        description = "Plazo del préstamo en meses",
        example = "36",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minimum = "3",
        maximum = "120",
        defaultValue = "24"
    )
    private Integer loanTerm;

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    @Pattern(
        regexp = "^(PERSONAL|AUTO|HIPOTECARIO|EDUCATIVO|EMPRESARIAL)$",
        message = "El tipo de préstamo debe ser: PERSONAL, AUTO, HIPOTECARIO, EDUCATIVO o EMPRESARIAL"
    )
    @Schema(
        description = "Tipo de préstamo solicitado",
        example = "PERSONAL",
        requiredMode = Schema.RequiredMode.REQUIRED,
        allowableValues = {"PERSONAL", "AUTO", "HIPOTECARIO", "EDUCATIVO", "EMPRESARIAL"},
        defaultValue = "PERSONAL"
    )
    private String loanType;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    @Schema(
        description = "Correo electrónico del solicitante",
        example = "cliente@ejemplo.com",
        requiredMode = Schema.RequiredMode.REQUIRED,
        maxLength = 100
    )
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(
        regexp = "^\\+?[0-9]{10,15}$",
        message = "El número de teléfono debe tener entre 10 y 15 dígitos y puede incluir el prefijo '+'"
    )
    @Schema(
        description = "Número de teléfono del solicitante con código de país",
        example = "+521234567890",
        requiredMode = Schema.RequiredMode.REQUIRED,
        pattern = "^\\+?[0-9]{10,15}$",
        minLength = 10,
        maxLength = 15
    )
    private String phoneNumber;

    @DecimalMin(value = "0.0", inclusive = false, message = "La TAE debe ser mayor a 0")
    @DecimalMax(value = "100.0", message = "La TAE no puede exceder el 100%")
    @Schema(
        description = "Tasa Anual Equivalente (TAE) del préstamo en porcentaje",
        example = "12.5",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        minimum = "0.01",
        maximum = "100.00",
        defaultValue = "15.0"
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal apr;

    @DecimalMin(value = "0.0", inclusive = true, message = "El interés total no puede ser negativo")
    @Schema(
        description = "Total de intereses a pagar durante todo el plazo del préstamo",
        example = "987.65",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        minimum = "0.00"
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalInterest;

    @DecimalMin(value = "0.0", inclusive = false, message = "El pago mensual debe ser mayor a 0")
    @Schema(
        description = "Monto del pago mensual a realizar",
        example = "185.80",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        minimum = "0.01"
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal monthlyPayment;

    @Schema(
        description = "Estado actual de la solicitud de préstamo",
        example = "PENDING",
        allowableValues = {"PENDING", "APPROVED", "REJECTED", "CANCELLED"},
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        hidden = true
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    @Schema(
        description = "Motivo del rechazo en caso de que la solicitud sea denegada",
        example = "Ingreso insuficiente para el monto solicitado",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        maxLength = 500
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String rejectionReason;

    @Schema(
        description = "Fecha y hora de creación de la solicitud",
        example = "2026-09-01T10:30:00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        hidden = true
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(
        description = "Fecha y hora de la última actualización de la solicitud",
        example = "2026-09-01T15:45:30",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        hidden = true
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}