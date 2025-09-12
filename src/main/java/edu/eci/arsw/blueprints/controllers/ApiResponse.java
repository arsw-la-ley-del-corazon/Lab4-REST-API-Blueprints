package edu.eci.arsw.blueprints.controllers;

/**
 * Envoltura estándar para respuestas API.
 *
 * @param code código de estado de la operación (normalmente HTTP)
 * @param message mensaje humano legible
 * @param data carga útil de la respuesta
 * @param <T> tipo de la carga útil
 */
public record ApiResponse<T>(int code, String message, T data) {}
