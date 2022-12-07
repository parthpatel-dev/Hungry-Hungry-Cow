package com.sfu.group6.hungrycow.ui;

import com.sfu.group6.hungrycow.ui.tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * AnimateHandler loads the animation sprites for animate entities.
 */
public class AnimateResourceLoaderUtil {
    private static final int NUM_RESOURCES = 14;

    private AnimateResourceLoaderUtil() {
        /* Hide constructor as this is a utility class */
    }

    public static Tile[] getAnimateImage() {
        Tile[] tileAnimate = new Tile[NUM_RESOURCES];
        try {
            tileAnimate[0] = new Tile();
            tileAnimate[0].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_up.png")));
            tileAnimate[1] = new Tile();
            tileAnimate[1].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_down.png")));
            tileAnimate[2] = new Tile();
            tileAnimate[2].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_left_1.png")));
            tileAnimate[3] = new Tile();
            tileAnimate[3].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_left_2.png")));
            tileAnimate[4] = new Tile();
            tileAnimate[4].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_right_1.png")));
            tileAnimate[5] = new Tile();
            tileAnimate[5].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/cow_right_2.png")));
            tileAnimate[6] = new Tile();
            tileAnimate[6].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/enemy_left_1.png")));
            tileAnimate[7] = new Tile();
            tileAnimate[7].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/enemy_left_2.png")));
            tileAnimate[8] = new Tile();
            tileAnimate[8].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/enemy_right_1.png")));
            tileAnimate[9] = new Tile();
            tileAnimate[9].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/animate/enemy_right_2.png")));
            tileAnimate[10] = new Tile();
            tileAnimate[10].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/tiles/exit_closed.png")));
            tileAnimate[11] = new Tile();
            tileAnimate[11].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/tiles/exit_open.png")));
            tileAnimate[12] = new Tile();
            tileAnimate[12].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/tiles/milk.png")));
            tileAnimate[13] = new Tile();
            tileAnimate[13].image
                    = ImageIO.read(Objects.requireNonNull(AnimateResourceLoaderUtil.class.getResourceAsStream("/tiles/grass.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tileAnimate;
    }
}
