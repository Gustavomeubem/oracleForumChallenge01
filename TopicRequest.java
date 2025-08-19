package com.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRequest(
    @NotBlank @Size(max = 200) String title,
    @NotBlank @Size(max = 1000) String problem,
    @NotBlank @Size(max = 5000) String solution
) {}
