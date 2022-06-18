import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestMerger {
    private static long suiteStartTime;
    private long testStartTime;
    Merger sut = new Merger();

    @BeforeAll
    public static void before() {
        System.out.println("Tests started");
        suiteStartTime = System.currentTimeMillis();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Tests complete: " + (System.currentTimeMillis() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Test started");
        testStartTime = System.currentTimeMillis();
        sut = new Merger();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.currentTimeMillis() - testStartTime));
    }

    @Test
    public void testMergeNormal() {
        ArrayList<Integer> mass1 = new ArrayList<>(Arrays.asList(45, 31, 24, 22, 20, 17, 14, 13, 12, 10));
        ArrayList<Integer> mass2 = new ArrayList<>(Arrays.asList(31, 18, 15, 12, 10, 8, 6, 4, 2, 1));
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(45, 31, 31, 24, 22, 20, 18, 17, 15, 14));

        ArrayList<Integer> result = sut.merge(mass1, mass2);
        assertEquals(expected, result);
    }

    @Test
    public void testMergeResultType() {
        ArrayList<Integer> mass1 = new ArrayList<>(Arrays.asList(45, 31, 24, 22, 20, 17, 14, 13, 12, 10));
        ArrayList<Integer> mass2 = new ArrayList<>(Arrays.asList(31, 18, 15, 12, 10, 8, 6, 4, 2, 1));

        assertInstanceOf(ArrayList.class, sut.merge(mass1, mass2));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testMergeLessPlayers(ArrayList<Integer> mass1, ArrayList<Integer> mass2) {

        var expected = NoSuchElementException.class;

        assertThrows(expected, () -> sut.merge(mass1, mass2));
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(new ArrayList<>(Arrays.asList(45, 31, 24)),
                        new ArrayList<>(Arrays.asList(31, 18, 15, 12))),
                Arguments.of(new ArrayList<>(Arrays.asList(0)),
                        new ArrayList<>(Arrays.asList(0))));
    }
}

