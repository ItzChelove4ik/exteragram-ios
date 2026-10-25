package ru.noties.jlatexmath.awt.geom;

/* loaded from: classes7.dex */
public class RoundRectangle2D {

    public static class Float {
        public float archeight;
        public float arcwidth;
        public float height;
        public float width;
        public float x;
        public float y;

        public Float(float r1, float r2, float r3, float r4, float r5, float r6) {
            this.x = r1;
            this.y = r2;
            this.width = r3;
            this.height = r4;
            this.arcwidth = r5;
            this.archeight = r6;
        }

        public String toString() {
            return "Float{x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", arcwidth=" + this.arcwidth + ", archeight=" + this.archeight + '}';
        }
    }

    public RoundRectangle2D() {
    }
}
