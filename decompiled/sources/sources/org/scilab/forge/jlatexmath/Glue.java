package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class Glue {
    private static final int[][][] glueTable = null;
    private static Glue[] glueTypes;
    private final String name;
    private final float shrink;
    private final float space;
    private final float stretch;

    static {
        GlueSettingsParser r0 = new GlueSettingsParser();
        glueTypes = r0.getGlueTypes();
        glueTable = r0.createGlueTable();
    }

    public Glue(float r1, float r2, float r3, String r4) {
        this.space = r1;
        this.stretch = r2;
        this.shrink = r3;
        this.name = r4;
    }

    private Box createBox(TeXEnvironment r5) {
        TeXFont r0 = r5.getTeXFont();
        float r52 = r0.getQuad(r5.getStyle(), r0.getMuFontId());
        return new GlueBox((this.space / 18.0f) * r52, (this.stretch / 18.0f) * r52, (this.shrink / 18.0f) * r52);
    }

    public static Box get(int r2, int r3, TeXEnvironment r4) {
        if (r2 <= 7) goto L5;
        r2 = 0;
    L5:
        if (r3 <= 7) goto L8;
        r3 = 0;
    L8:
        return glueTypes[glueTable[r2][r3][r4.getStyle() / 2]].createBox(r4);
    }

    public String getName() {
        return this.name;
    }
}
