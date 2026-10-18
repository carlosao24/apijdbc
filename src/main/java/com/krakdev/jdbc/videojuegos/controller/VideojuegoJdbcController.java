package com.krakdev.jdbc.videojuegos.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.krakdev.jdbc.videojuegos.services.ServicioVideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {

    @Autowired
    private ServicioVideojuegoJdbc servicio;

    @PostMapping
    public ResponseEntity<Object> insertar(@RequestBody Videojuego v) {
        try {
            Videojuego nuevo = servicio.crear(v);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        try {
            List<Videojuego> lista = servicio.listar();
            return ResponseEntity.ok(lista);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar: " + e.getMessage());
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Object> buscarPorCodigo(@PathVariable String codigo) {
        try {
            Videojuego v = servicio.buscarPorCodigo(codigo);
            if (v != null) {
                return ResponseEntity.ok(v);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la búsqueda: " + e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Object> actualizar(@PathVariable String codigo, @RequestBody Videojuego v) {
        try {
            v.setCodigo(codigo);
            Videojuego actualizado = servicio.actualizar(v);
            return ResponseEntity.ok(actualizado);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> eliminar(@PathVariable String codigo) {
        try {
            boolean eliminado = servicio.eliminar(codigo);
            if (eliminado) {
                return ResponseEntity.ok("Videojuego eliminado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado para eliminar");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar: " + e.getMessage());
        }
    }
}