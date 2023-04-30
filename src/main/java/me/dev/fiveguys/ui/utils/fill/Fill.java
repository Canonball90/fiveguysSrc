package me.dev.fiveguys.ui.utils.fill;



import me.dev.fiveguys.ui.utils.shape.AbstractShape;

import java.awt.*;

public interface Fill {
    Color colorAt(AbstractShape abstractShape, float x, float y);
}
