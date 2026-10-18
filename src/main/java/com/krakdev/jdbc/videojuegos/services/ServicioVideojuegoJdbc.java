package com.krakdev.jdbc.videojuegos.services;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {
    private VideojuegoJdbc jdbc = new VideojuegoJdbc();

    public Videojuego crear(Videojuego v) throws SQLException {
        return jdbc.insertar(v);
    }

    public List<Videojuego> listar() throws SQLException {
        return jdbc.listar();
    }

    public Videojuego buscarPorCodigo(String codigo) throws SQLException {
        return jdbc.buscarPorCodigo(codigo);
    }

    public Videojuego actualizar(Videojuego v) throws SQLException {
        return jdbc.actualizar(v);
    }

    public boolean eliminar(String codigo) throws SQLException {
        return jdbc.eliminar(codigo);
    }
}