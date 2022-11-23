package com.sfu.group6.hungrycow.MapLoader;

import com.sfu.group6.hungrycow.maploader.MapLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MapLoaderTest {
    private static final int[][] expectedData = {
            {3, 3, 3, 3},
            {3, 1, 17, 3},
            {3, 0, 0, 18},
            {3, 3, 3, 3}
    };

    private final MapLoader fixture = new MapLoader();

    private int[][] mapLoaderData = new int[4][4];

    @BeforeEach
    void setup() throws IOException {
        mapLoaderData = fixture.loadBoard("/maps/mapTest.txt",
                                          4,
                                          4);
    }

    @Test
    void shouldLoadMapWithSameHeight() {
        assertThat(mapLoaderData.length).isEqualTo(expectedData.length);
    }

    @Test
    void shouldLoadMapWithSameWidth() {
        for (int y = 0; y < expectedData.length; y++) {
            assertThat(mapLoaderData[y].length).isEqualTo(expectedData[y].length);
        }
    }

    @Test
    void shouldLoadMapWithSameBarrierData() {
        for (int y = 0; y < expectedData[0].length; y++) {
            assertThat(mapLoaderData[y][0]).isEqualTo(expectedData[y][0]);
        }
        assertThat(mapLoaderData[0][1]).isEqualTo(expectedData[0][1]);
        assertThat(mapLoaderData[3][1]).isEqualTo(expectedData[3][1]);

        assertThat(mapLoaderData[0][2]).isEqualTo(expectedData[0][2]);
        assertThat(mapLoaderData[3][2]).isEqualTo(expectedData[3][2]);

        for (int x = 0; x < expectedData[3].length; x++) {
            assertThat(mapLoaderData[x][3]).isEqualTo(expectedData[x][3]);
        }
    }

    @Test
    void shouldLoadMapWithSamePlayerSpot() {
        assertThat(mapLoaderData[1][1]).isEqualTo(expectedData[1][1]);
    }

    @Test
    void shouldLoadMapWithSameStartSpot() {
        assertThat(mapLoaderData[1][2]).isEqualTo(expectedData[1][2]);
    }

    @Test
    void shouldLoadMapWithSameEmptySpot() {
        assertThat(mapLoaderData[2][1]).isEqualTo(expectedData[2][1]);
        assertThat(mapLoaderData[2][2]).isEqualTo(expectedData[2][2]);
    }

    @Test
    void shouldLoadMapWithSameEndSpot() {
        assertThat(mapLoaderData[2][3]).isEqualTo(expectedData[2][3]);
    }
}
