package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BatallonTest {

 // 1. Validar la creación de los diferentes tipos de vehículos
    @Test
    public void testValidarCreacionVehiculos() {
        TransporteTropas transporte = new TransporteTropas("T-01", "Camión de tropas", 2020,
                12000, EstadoOperativo.DISPONIBLE, 25);
        Blindado blindado = new Blindado("B-01", "Vehículo blindado", 2021,
                8000, EstadoOperativo.DISPONIBLE, 4);
        Apoyo apoyo = new Apoyo("A-01", "Ambulancia", 2022,
                5000, EstadoOperativo.DISPONIBLE, TipoFuncion.MEDICO);

        // Validar atributos de TransporteTropas
        assertEquals("T-01", transporte.getId());
        assertEquals("Camión de tropas", transporte.getModelo());
        assertEquals(2020, transporte.getAnioFabricacion());
        assertEquals(12000.0, transporte.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, transporte.getEstadoOperativo());
        assertEquals(25, transporte.getCapacidadSoldados());

        // Validar atributos de Blindado
        assertEquals("B-01", blindado.getId());
        assertEquals("Vehículo blindado", blindado.getModelo());
        assertEquals(2021, blindado.getAnioFabricacion());
        assertEquals(8000.0, blindado.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, blindado.getEstadoOperativo());
        assertEquals(4, blindado.getNivelBlindaje());

        // Validar atributos de Apoyo
        assertEquals("A-01", apoyo.getId());
        assertEquals("Ambulancia", apoyo.getModelo());
        assertEquals(2022, apoyo.getAnioFabricacion());
        assertEquals(5000.0, apoyo.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, apoyo.getEstadoOperativo());
        assertEquals(TipoFuncion.MEDICO, apoyo.getTipoFuncion());
    }

    private Batallon batallon;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        batallon = new Batallon("01", "Cacique", "03", "Calarcá", 3);
    }

    // 2. Agregar un vehículo militar correctamente
    @Test
    public void testAgregarVehiculoCorrectamente() {
        TransporteTropas transporte = new TransporteTropas("T-01", "Camión de tropas", 2020,
                12000, EstadoOperativo.DISPONIBLE, 25);

        boolean resultado = batallon.registrarVehiculo(transporte);

        assertTrue(resultado);
        assertEquals(1, batallon.getListaVehiculos().size());
        assertTrue(batallon.getListaVehiculos().contains(transporte));
    }

    // 3. No permitir registrar vehículos con ID repetido
    @Test
    public void testNoPermitirVehiculoConIdRepetido() {
        TransporteTropas transporte1 = new TransporteTropas("T-01", "Camión 1", 2020,
                12000, EstadoOperativo.DISPONIBLE, 25);
        Blindado blindadoRepetido = new Blindado("T-01", "Blindado con ID repetido", 2021,
                8000, EstadoOperativo.DISPONIBLE, 4);

        assertTrue(batallon.registrarVehiculo(transporte1));
        boolean resultadoRepetido = batallon.registrarVehiculo(blindadoRepetido);

        assertFalse(resultadoRepetido);
        assertEquals(1, batallon.getListaVehiculos().size());
    }
}
// 4. Buscar vehículos existentes e inexistentes
@Test
public void testBuscarVehiculosExistentesEInexistentes() {
    Apoyo apoyo = new Apoyo("A-01", "Ambulancia", 2022,
            5000, EstadoOperativo.DISPONIBLE, TipoFuncion.MEDICO);
    batallon.registrarVehiculo(apoyo);

    // Nota: Verifica que el método en tu clase Batallon se llame buscarVehiculo
    Vehiculo encontrado = batallon.buscarVehiculo("A-01");
    Vehiculo inexistente = batallon.buscarVehiculo("ID-999");

    assertNotNull(encontrado);
    assertEquals("A-01", encontrado.getId());
    assertNull(inexistente);
}

// 5. Eliminar un vehículo registrado
@Test
public void testEliminarVehiculoRegistrado() {
    Blindado blindado = new Blindado("B-01", "Vehículo blindado", 2021,
            8000, EstadoOperativo.DISPONIBLE, 4);
    batallon.registrarVehiculo(blindado);

    // Nota: Verifica que el método en tu clase Batallon se llame eliminarVehiculo
    boolean eliminado = batallon.eliminarVehiculo("B-01");

    assertTrue(eliminado);
    assertNull(batallon.buscarVehiculo("B-01"));
    assertEquals(0, batallon.getListaVehiculos().size());
}

// 6. Actualizar correctamente la información de un vehículo
@Test
public void testActualizarInformacionVehiculo() {
    TransporteTropas transporte = new TransporteTropas("T-01", "Camión de tropas", 2020,
            12000, EstadoOperativo.DISPONIBLE, 25);
    batallon.registrarVehiculo(transporte);

    Vehiculo vehiculoRegistrado = batallon.buscarVehiculo("T-01");
    boolean kmActualizado = vehiculoRegistrado.setKilometraje(15500);
    boolean estadoActualizado = vehiculoRegistrado.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO);

    assertTrue(kmActualizado);
    assertTrue(estadoActualizado);
    assertEquals(15500.0, batallon.buscarVehiculo("T-01").getKilometraje());
    assertEquals(EstadoOperativo.EN_MANTENIMIENTO, batallon.buscarVehiculo("T-01").getEstadoOperativo());
}