package com.sfu.group6.hungrycow.MapLoader;

import com.sfu.group6.hungrycow.ui.MapLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MapLoaderTest {
// test
    private final MapLoader fixture = new MapLoader();

    private final int[][] expectedData = new int[4][4];
    private int[][] mapLoaderData = new int[4][4];

    @BeforeEach
    void setup() throws IOException {
        // Should set all spaces to 0
        for (int y = 0; y  < expectedData.length; y++)
        {
            for (int x = 0; x < expectedData[y].length; x++)
            {
                expectedData[x][y] = 3;
            }
        }
        // Setting individual spaces to player, start space, end space, empty space
        expectedData[1][1] = 1;
        expectedData[1][2] = 17;
        expectedData[2][1] = 0;
        expectedData[2][2] = 0;
        expectedData[2][3] = 18;

        mapLoaderData = fixture.loadBoard("/maps/mapTest.txt",4,4);
    }

    @Test
    void shouldLoadMapWithSameHeight()
    {
        assertThat(mapLoaderData.length).isEqualTo(expectedData.length);
    }

    @Test
    void shouldLoadMapWithSameWidth()
    {
        for (int y = 0; y  < expectedData.length; y++)
        {
            assertThat(mapLoaderData[y].length).isEqualTo(expectedData[y].length);
        }
    }

    @Test
    void shouldLoadMapWithSameBarrierData() {
        for (int y = 0; y < expectedData[0].length; y++)
        {
            assertThat(mapLoaderData[y][0]).isEqualTo(expectedData[y][0]);
        }
        assertThat(mapLoaderData[0][1]).isEqualTo(expectedData[0][1]);
        assertThat(mapLoaderData[3][1]).isEqualTo(expectedData[3][1]);

        assertThat(mapLoaderData[0][2]).isEqualTo(expectedData[0][2]);
        assertThat(mapLoaderData[3][2]).isEqualTo(expectedData[3][2]);

        for (int x = 0; x < expectedData[3].length; x++)
        {
            assertThat(mapLoaderData[x][3]).isEqualTo(expectedData[x][3]);
        }
    }

    @Test
    void shouldLoadMapWithSamePlayerSpot()
    {
        assertThat(mapLoaderData[1][1]).isEqualTo(expectedData[1][1]);
    }

    @Test
    void shouldLoadMapWithSameStartSpot()
    {
        assertThat(mapLoaderData[1][2]).isEqualTo(expectedData[1][2]);
    }

    @Test
    void shouldLoadMapWithSameEmptySpot()
    {
        assertThat(mapLoaderData[2][1]).isEqualTo(expectedData[2][1]);
        assertThat(mapLoaderData[2][2]).isEqualTo(expectedData[2][2]);
    }

    @Test
    void shouldLoadMapWithSameEndSpot()
    {
        assertThat(mapLoaderData[2][3]).isEqualTo(expectedData[2][3]);
    }





}
