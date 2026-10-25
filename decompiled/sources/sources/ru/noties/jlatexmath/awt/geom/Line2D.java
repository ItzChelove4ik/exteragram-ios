package ru.noties.jlatexmath.awt.geom;

/* loaded from: classes7.dex */
public class Line2D {

    public static class Float {
        public double x1;
        public double x2;
        public double y1;
        public double y2;

        public Float() {
        }

        public Float(float r10, float r11, float r12, float r13) {
            setLine(r10, r11, r12, r13);
        }

        public void setLine(double r1, double r3, double r5, double r7) {
            this.x1 = r1;
            this.y1 = r3;
            this.x2 = r5;
            this.y2 = r7;
        }

        public String toString() {
            return "Float{x1=" + this.x1 + ", y1=" + this.y1 + ", x2=" + this.x2 + ", y2=" + this.y2 + '}';
        }
    }

    public Line2D() {
    }
}
