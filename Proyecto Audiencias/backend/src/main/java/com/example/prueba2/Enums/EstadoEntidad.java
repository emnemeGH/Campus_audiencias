package com.example.prueba2.Enums;

public enum EstadoEntidad {
    // Valores para audiencias
    PROGRAMADA("Programada"),
    DEMORADA("Demorada"),
    SUSPENDIDA("Suspendida"),

    // Valores para auditoridades (aut_tipo)
    JUEZ("juez"),
    FISCAL("fiscal"),
    DEFENSOR("defensor");

    private final String valor;

    EstadoEntidad(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    // Método para obtener el Enum desde un String
    public static EstadoEntidad fromString(String text) {
        for (EstadoEntidad estado : EstadoEntidad.values()) {
            if (estado.valor.equalsIgnoreCase(text)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("No existe el estado: " + text);
    }
}
