package com.kapasiya.em.dto;

import com.kapasiya.em.enums.Status;
import com.kapasiya.em.config.EnumValidator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDto {

    @NotNull(message = "Status cannot be null")
    @EnumValidator(target = Status.class, message = "Invalid status value")
    private String status;

}
