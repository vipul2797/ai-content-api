package com.ai.ai_content_api.controller;

import com.ai.ai_content_api.service.AiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(final AiService aiService) {
        this.aiService = aiService;
    }

    // 🔹 Generate Content
    @PostMapping("/generate")
    public Map<String, String> generate(@RequestBody final Map<String, String> request) {
        final String prompt = request.get("prompt");

        if (isInvalid(prompt)) {
            return errorResponse("Prompt cannot be empty");
        }

        final String result = aiService.generate(prompt);
        return successResponse(result);
    }

    // 🔹 Improve Content
    @PostMapping("/improve")
    public Map<String, String> improve(@RequestBody final Map<String, String> request) {
        final String prompt = request.get("prompt");

        if (isInvalid(prompt)) {
            return errorResponse("Prompt cannot be empty");
        }

        final String finalPrompt = "Improve this professionally:\n" + prompt;
        final String result = aiService.generate(finalPrompt);

        return successResponse(result);
    }

    // 🔹 Summarize Content
    @PostMapping("/summarize")
    public Map<String, String> summarize(@RequestBody final Map<String, String> request) {
        final String prompt = request.get("prompt");

        if (isInvalid(prompt)) {
            return errorResponse("Prompt cannot be empty");
        }

        final String finalPrompt = "Summarize this:\n" + prompt;
        final String result = aiService.generate(finalPrompt);

        return successResponse(result);
    }

    // 🔹 Helper: Validation
    private boolean isInvalid(final String value) {
        return value == null || value.trim().isEmpty();
    }

    // 🔹 Helper: Success Response
    private Map<String, String> successResponse(final String response) {
        return Map.of(
                "status", "success",
                "response", response
        );
    }

    // 🔹 Helper: Error Response
    private Map<String, String> errorResponse(final String message) {
        return Map.of(
                "status", "error",
                "response", message
        );
    }
}