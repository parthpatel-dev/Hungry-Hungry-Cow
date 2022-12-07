package com.sfu.group6.hungrycow.ui.tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class InanimateResourceLoaderUtil {
    private static final int NUM_RESOURCES = 29;

    private InanimateResourceLoaderUtil() {
        /* Utility class */
    }

    public static Tile[] getTileImages() {
        Tile[] tiles = new Tile[NUM_RESOURCES];
        try {
            tiles[0] = new Tile();
            tiles[0].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/field.png")));


            tiles[1] = new Tile();
            tiles[1].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_down.png")));

            tiles[2] = new Tile();
            tiles[2].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/enemy_left_1.png")));

            tiles[3] = new Tile();
            tiles[3].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/tall_tree.png")));

            tiles[4] = new Tile();
            tiles[4].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/circle_tree.png")));

            tiles[5] = new Tile();
            tiles[5].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/dead_tree.png")));

            tiles[6] = new Tile();
            tiles[6].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/rock.png")));

            tiles[7] = new Tile();
            tiles[7].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/pond.png")));

            tiles[8] = new Tile();
            tiles[8].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_down.png")));

            tiles[9] = new Tile();
            tiles[9].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_middle.png")));

            tiles[10] = new Tile();
            tiles[10].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_down_left_corner.png")));

            tiles[11] = new Tile();
            tiles[11].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_down_right_corner.png")));

            tiles[12] = new Tile();
            tiles[12].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_up_left_corner.png")));

            tiles[13] = new Tile();
            tiles[13].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/fence_up_right_corner.png")));

            tiles[14] = new Tile();
            tiles[14].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/grass.png")));

            tiles[15] = new Tile();
            tiles[15].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/milk.png")));

            tiles[16] = new Tile();
            tiles[16].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/punishment.png")));

            tiles[17] = new Tile();
            tiles[17].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/field.png")));

            tiles[18] = new Tile();
            tiles[18].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/field.png")));

            tiles[19] = new Tile();
            tiles[19].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/crop1.png")));

            tiles[20] = new Tile();
            tiles[20].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/crop2.png")));

            tiles[21] = new Tile();
            tiles[21].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/crop3.png")));

            tiles[22] = new Tile();
            tiles[22].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/crop4.png")));

            tiles[23] = new Tile();
            tiles[23].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/light_grass.png")));

            tiles[24] = new Tile();
            tiles[24].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/tiles/heavy_grass.png")));

            tiles[25] = new Tile();
            tiles[25].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/mama_sheep.png")));

            tiles[26] = new Tile();
            tiles[26].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/baby_sheep.png")));

            tiles[27] = new Tile();
            tiles[27].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/mama_chicken.png")));

            tiles[28] = new Tile();
            tiles[28].image
                    = ImageIO.read(Objects.requireNonNull(InanimateResourceLoaderUtil.class.getResourceAsStream("/animate/baby_chicken.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tiles;
    }
}
