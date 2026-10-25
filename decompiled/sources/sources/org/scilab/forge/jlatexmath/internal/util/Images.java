package org.scilab.forge.jlatexmath.internal.util;

import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.image.BufferedImage;

/* loaded from: classes5.dex */
public final class Images {
    public static double DISTANCE_THRESHOLD = 40.0d;

    static {
    }

    private Images() {
    }

    public static double distance(BufferedImage r13, BufferedImage r14) {
        if (r13.getWidth() == r14.getWidth()) goto L5;
        return -1.0d;
    L5:
        if (r13.getHeight() != r14.getHeight()) goto L18;
        int r0 = r13.getWidth();
        int r1 = r13.getHeight();
        double r3 = 0.0d;
        int r5 = 0;
    L7:
        if (r5 >= r1) goto L13;
        int r6 = 0;
    L9:
        if (r6 >= r0) goto L11;
        Color r7 = new Color(r13.getRGB(r6, r5));
        Color r8 = new Color(r14.getRGB(r6, r5));
        r3 = r3 + (((sqr(r7.getRed() - r8.getRed()) + sqr(r7.getBlue() - r8.getBlue())) + sqr(r7.getGreen() - r8.getGreen())) + sqr(r7.getAlpha() - r8.getAlpha()));
        r6 = r6 + 1;
        goto L9
    L11:
        r5 = r5 + 1;
        goto L7
    L13:
        return Math.sqrt((r3 / r1) / r0);
    L18:
        return -1.0d;
    }

    private static double sqr(double r0) {
        return r0 * r0;
    }
}
