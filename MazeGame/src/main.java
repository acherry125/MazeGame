/**
 * Created by AlexC on 10/26/2015.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javalib.impworld.World;
import javalib.worldimages.LineImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import tester.*;

public class main {

    public static void main(String [] args) {
        MazeWorld maze50x30 = new MazeWorld(50, 30);
        maze50x30.bigBang(1000, 600, .000001);
    }

}
