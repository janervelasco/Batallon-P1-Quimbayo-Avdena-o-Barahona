package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BatallonTest {

    private static final Logger LOG = Logger.getLogger(BatallonTest.class.getName());

    @DisplayName("Prueba assertEquals")
    @Test
    void testInicial(){
        LOG.info("Iniciando prueba Batallon...");

        int resultado = 1 + 2;

        assertEquals(3,resultado);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNotEquals")
    @Test
    void testNotEquals(){
        LOG.info("Iniciando prueba Batallon...");

        String cadena = "Hola a todos";

        assertNotEquals("Adios",cadena);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNull")
    @Test
    void testNull(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = null;

        assertNull(batallon);

        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertNotNull")
    @Test
    void testNotNull(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);

        assertNotNull(batallon);

        LOG.info(batallon.toString());
        LOG.info("Finalizando prueba Batallon...");
    }

    @DisplayName("Prueba assertTrue/assertFalse")
    @Test
    void testFalseTrue(){
        LOG.info("Iniciando prueba Batallon...");

        Batallon batallon = new Batallon("01", "Cacique", "03", "Calarca", 3);

        assertTrue(batallon.getNumero() < 5);
        assertFalse(batallon.getNombre().equals("Calarca cacique"));

        LOG.info(batallon.toString());
        LOG.info("Finalizando prueba Batallon...");
    }


    //@BeforeEach
    void ejecutarAntes(){
        LOG.warning("Ejecucion del BeforeEach");
    }

//    @AfterEach
    void ejecutarDespues(){
        LOG.warning("Ejecucion del AfterEach");
    }



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
