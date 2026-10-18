package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {
    private static final Logger logger = LoggerFactory.getLogger(VideojuegoJdbc.class);

    public Videojuego insertar(Videojuego v) throws SQLException {
        String sql = "INSERT INTO videojuegos (codigo, nombre, plataforma, precio, disponible, genero) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getCodigo());
            ps.setString(2, v.getNombre());
            ps.setString(3, v.getPlataforma());
            ps.setDouble(4, v.getPrecio());
            ps.setBoolean(5, v.isDisponible());
            ps.setString(6, v.getGenero());
            
            ps.executeUpdate();
            logger.info("Videojuego insertado correctamente: " + v.getCodigo());
            return v;
        } catch (SQLException e) {
            logger.error("Error al insertar videojuego: " + e.getMessage());
            throw e;
        }
    }

    
}