package org.scilab.forge.jlatexmath;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class ColorAtom extends Atom implements Row {
    public static Map<String, Color> Colors;
    private final Color background;
    private final Color color;
    private final RowAtom elements;

    static {
        Colors = new HashMap();
        initColors();
    }

    public ColorAtom(Atom r2, Color r3, Color r4) {
        this.elements = new RowAtom(r2);
        this.background = r3;
        this.color = r4;
    }

    public ColorAtom(Color r3, Color r4, ColorAtom r5) {
        this.elements = new RowAtom(r5.elements);
        if (r3 != null) goto L5;
        r3 = r5.background;
    L5:
        this.background = r3;
        if (r4 != null) goto L8;
        r4 = r5.color;
    L8:
        this.color = r4;
    }

    private static Color convColor(float r2, float r3, float r4, float r5) {
        float r52 = 1.0f - r5;
        return new Color((1.0f - r2) * r52, (1.0f - r3) * r52, r52 * (1.0f - r4));
    }

    public static Color getColor(String r10) {
        if (r10 == null) goto L49;
        String r102 = r10.trim();
        if (r102.length() < 1) goto L49;
        if (r102.charAt(0) != '#') goto L10;
        return Color.decode(r102);
    L10:
        if (r102.indexOf(44) == (-1)) goto L12;
    L13:
        StringTokenizer r0 = new StringTokenizer(r102, ";,");
        int r5 = r0.countTokens();
        if (r5 != 3) goto L34;
        String r103 = r0.nextToken().trim();     // Catch: NumberFormatException -> L31
        String r52 = r0.nextToken().trim();     // Catch: NumberFormatException -> L31
        String r02 = r0.nextToken().trim();     // Catch: NumberFormatException -> L31
        float r6 = Float.parseFloat(r103);     // Catch: NumberFormatException -> L31
        float r7 = Float.parseFloat(r52);     // Catch: NumberFormatException -> L31
        float r8 = Float.parseFloat(r02);     // Catch: NumberFormatException -> L31
        if (r6 != ((int) r6)) goto L30;
        if (r7 != ((int) r7)) goto L30;
        if (r8 != ((int) r8)) goto L30;
        if (r103.indexOf(46) != (-1)) goto L30;
        if (r52.indexOf(46) != (-1)) goto L30;
        if (r02.indexOf(46) != (-1)) goto L30;
        return new Color((int) Math.min(255.0f, Math.max(0.0f, r6)), (int) Math.min(255.0f, Math.max(0.0f, r7)), (int) Math.min(255.0f, Math.max(0.0f, r8)));
    L30:
        return new Color(Math.min(1.0f, Math.max(0.0f, r6)), Math.min(1.0f, Math.max(0.0f, r7)), Math.min(1.0f, Math.max(0.0f, r8)));
    L32:
        return Color.black;
    L34:
        if (r5 == 4) goto L52;
    L39:
        Color r03 = Colors.get(r102.toLowerCase());
        if (r03 == null) goto L43;
        return r03;
    L43:
        if (r102.indexOf(46) == (-1)) goto L47;
        float r04 = Math.min(1.0f, Math.max(Float.parseFloat(r102), 0.0f));     // Catch: NumberFormatException -> L50
        return new Color(r04, r04, r04);
    L47:
        return Color.decode("#".concat(r102));
    L52:
        return convColor(Math.min(1.0f, Math.max(0.0f, Float.parseFloat(r0.nextToken().trim()))), Math.min(1.0f, Math.max(0.0f, Float.parseFloat(r0.nextToken().trim()))), Math.min(1.0f, Math.max(0.0f, Float.parseFloat(r0.nextToken().trim()))), Math.min(1.0f, Math.max(0.0f, Float.parseFloat(r0.nextToken().trim()))));
    L38:
        return Color.black;
    L12:
        if (r102.indexOf(59) == (-1)) goto L39;
    L49:
        return Color.black;
    }

    private static void initColors() {
        Colors.put("black", Color.black);
        Colors.put("white", Color.white);
        Colors.put(PluginsConstants.Settings.RED, Color.red);
        Colors.put("green", Color.green);
        Colors.put("blue", Color.blue);
        Colors.put("cyan", Color.cyan);
        Colors.put("magenta", Color.magenta);
        Colors.put("yellow", Color.yellow);
        Colors.put("greenyellow", convColor(0.15f, 0.0f, 0.69f, 0.0f));
        Colors.put("goldenrod", convColor(0.0f, 0.1f, 0.84f, 0.0f));
        Colors.put("dandelion", convColor(0.0f, 0.29f, 0.84f, 0.0f));
        Colors.put("apricot", convColor(0.0f, 0.32f, 0.52f, 0.0f));
        Colors.put("peach", convColor(0.0f, 0.5f, 0.7f, 0.0f));
        Colors.put("melon", convColor(0.0f, 0.46f, 0.5f, 0.0f));
        Colors.put("yelloworange", convColor(0.0f, 0.42f, 1.0f, 0.0f));
        Colors.put("orange", convColor(0.0f, 0.61f, 0.87f, 0.0f));
        Colors.put("burntorange", convColor(0.0f, 0.51f, 1.0f, 0.0f));
        Colors.put("bittersweet", convColor(0.0f, 0.75f, 1.0f, 0.24f));
        Colors.put("redorange", convColor(0.0f, 0.77f, 0.87f, 0.0f));
        Colors.put("mahogany", convColor(0.0f, 0.85f, 0.87f, 0.35f));
        Colors.put("maroon", convColor(0.0f, 0.87f, 0.68f, 0.32f));
        Colors.put("brickred", convColor(0.0f, 0.89f, 0.94f, 0.28f));
        Colors.put("orangered", convColor(0.0f, 1.0f, 0.5f, 0.0f));
        Colors.put("rubinered", convColor(0.0f, 1.0f, 0.13f, 0.0f));
        Colors.put("wildstrawberry", convColor(0.0f, 0.96f, 0.39f, 0.0f));
        Colors.put("salmon", convColor(0.0f, 0.53f, 0.38f, 0.0f));
        Colors.put("carnationpink", convColor(0.0f, 0.63f, 0.0f, 0.0f));
        Colors.put("magenta", convColor(0.0f, 1.0f, 0.0f, 0.0f));
        Colors.put("violetred", convColor(0.0f, 0.81f, 0.0f, 0.0f));
        Colors.put("rhodamine", convColor(0.0f, 0.82f, 0.0f, 0.0f));
        Colors.put("mulberry", convColor(0.34f, 0.9f, 0.0f, 0.02f));
        Colors.put("redviolet", convColor(0.07f, 0.9f, 0.0f, 0.34f));
        Colors.put("fuchsia", convColor(0.47f, 0.91f, 0.0f, 0.08f));
        Colors.put("lavender", convColor(0.0f, 0.48f, 0.0f, 0.0f));
        Colors.put("thistle", convColor(0.12f, 0.59f, 0.0f, 0.0f));
        Colors.put("orchid", convColor(0.32f, 0.64f, 0.0f, 0.0f));
        Colors.put("darkorchid", convColor(0.4f, 0.8f, 0.2f, 0.0f));
        Colors.put("purple", convColor(0.45f, 0.86f, 0.0f, 0.0f));
        Colors.put("plum", convColor(0.5f, 1.0f, 0.0f, 0.0f));
        Colors.put("violet", convColor(0.79f, 0.88f, 0.0f, 0.0f));
        Colors.put("royalpurple", convColor(0.75f, 0.9f, 0.0f, 0.0f));
        Colors.put("blueviolet", convColor(0.86f, 0.91f, 0.0f, 0.04f));
        Colors.put("periwinkle", convColor(0.57f, 0.55f, 0.0f, 0.0f));
        Colors.put("cadetblue", convColor(0.62f, 0.57f, 0.23f, 0.0f));
        Colors.put("cornflowerblue", convColor(0.65f, 0.13f, 0.0f, 0.0f));
        Colors.put("midnightblue", convColor(0.98f, 0.13f, 0.0f, 0.43f));
        Colors.put("navyblue", convColor(0.94f, 0.54f, 0.0f, 0.0f));
        Colors.put("royalblue", convColor(1.0f, 0.5f, 0.0f, 0.0f));
        Colors.put("cerulean", convColor(0.94f, 0.11f, 0.0f, 0.0f));
        Colors.put("processblue", convColor(0.96f, 0.0f, 0.0f, 0.0f));
        Colors.put("skyblue", convColor(0.62f, 0.0f, 0.12f, 0.0f));
        Colors.put("turquoise", convColor(0.85f, 0.0f, 0.2f, 0.0f));
        Colors.put("tealblue", convColor(0.86f, 0.0f, 0.34f, 0.02f));
        Colors.put("aquamarine", convColor(0.82f, 0.0f, 0.3f, 0.0f));
        Colors.put("bluegreen", convColor(0.85f, 0.0f, 0.33f, 0.0f));
        Colors.put("emerald", convColor(1.0f, 0.0f, 0.5f, 0.0f));
        Colors.put("junglegreen", convColor(0.99f, 0.0f, 0.52f, 0.0f));
        Colors.put("seagreen", convColor(0.69f, 0.0f, 0.5f, 0.0f));
        Colors.put("forestgreen", convColor(0.91f, 0.0f, 0.88f, 0.12f));
        Colors.put("pinegreen", convColor(0.92f, 0.0f, 0.59f, 0.25f));
        Colors.put("limegreen", convColor(0.5f, 0.0f, 1.0f, 0.0f));
        Colors.put("yellowgreen", convColor(0.44f, 0.0f, 0.74f, 0.0f));
        Colors.put("springgreen", convColor(0.26f, 0.0f, 0.76f, 0.0f));
        Colors.put("olivegreen", convColor(0.64f, 0.0f, 0.95f, 0.4f));
        Colors.put("rawsienna", convColor(0.0f, 0.72f, 1.0f, 0.45f));
        Colors.put("sepia", convColor(0.0f, 0.83f, 1.0f, 0.7f));
        Colors.put("brown", convColor(0.0f, 0.81f, 1.0f, 0.6f));
        Colors.put("tan", convColor(0.14f, 0.42f, 0.56f, 0.0f));
        Colors.put("gray", convColor(0.0f, 0.0f, 0.0f, 0.5f));
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r2) {
        r2.isColored = true;
        TeXEnvironment r22 = r2.copy();
        Color r0 = this.background;
        if (r0 == null) goto L5;
        r22.setBackground(r0);
    L5:
        Color r02 = this.color;
        if (r02 == null) goto L9;
        r22.setColor(r02);
    L9:
        return this.elements.createBox(r22);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.elements.getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.elements.getRightType();
    }

    @Override // org.scilab.forge.jlatexmath.Row
    public void setPreviousAtom(Dummy r1) {
        this.elements.setPreviousAtom(r1);
    }
}
