package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Arrays;
import java.util.List;
import kotlin.text.Regex$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public abstract class Brush {
    public static List<Brush> BRUSHES_LIST;

    public static class Arrow extends Brush {
        public Arrow() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getDefaultColor() {
            return -30208;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getDefaultWeight() {
            return 0.25f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_arrow;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getSmoothThicknessRate() {
            return 0.25f;
        }
    }

    public static class Blurer extends Brush {
        public Blurer() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAlpha() {
            return 1.0f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getDefaultWeight() {
            return 1.0f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_blur;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getPreviewScale() {
            return 0.35f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public String getShaderName(int r1) {
            if (r1 != 0) goto L4;
            return "blitWithMaskBlurer";
        L4:
            if (r1 != 1) goto L6;
            return "compositeWithMaskBlurer";
        L6:
            if (r1 == 2) goto L9;
            return null;
        L9:
            return "brush";
        }
    }

    public static class Elliptical extends Brush {
        public Elliptical() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAlpha() {
            return 0.3f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAngle() {
            return (float) Math.toRadians(0.0d);
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getDefaultColor() {
            return -10742;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getDefaultWeight() {
            return 0.5f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_marker;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getOverrideAlpha() {
            return 0.45f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getPreviewScale() {
            return 0.4f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getScale() {
            return 1.5f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getSpacing() {
            return 0.04f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getStampResId() {
            return R.drawable.paint_elliptical_brush;
        }
    }

    public static class Eraser extends Brush {
        public Eraser() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAlpha() {
            return 1.0f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getDefaultWeight() {
            return 1.0f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_eraser;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getPreviewScale() {
            return 0.35f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public String getShaderName(int r1) {
            if (r1 != 0) goto L4;
            return "blitWithMaskEraser";
        L4:
            if (r1 != 1) goto L6;
            return "compositeWithMaskEraser";
        L6:
            if (r1 == 2) goto L9;
            return null;
        L9:
            return "brush";
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public boolean isEraser() {
            return true;
        }
    }

    public static class Neon extends Brush {
        public Neon() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAlpha() {
            return 0.7f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getDefaultColor() {
            return -13318311;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getDefaultWeight() {
            return 0.5f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_neon;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getPreviewScale() {
            return 0.2f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getScale() {
            return 1.45f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public String getShaderName(int r1) {
            if (r1 != 0) goto L4;
            return "blitWithMaskLight";
        L4:
            if (r1 != 1) goto L6;
            return "compositeWithMaskLight";
        L6:
            if (r1 == 2) goto L9;
            return null;
        L9:
            return "brushLight";
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getSpacing() {
            return 0.07f;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getStampResId() {
            return R.drawable.paint_neon_brush;
        }
    }

    public static class Radial extends Brush {
        public Radial() {
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getDefaultColor() {
            return -47814;
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public int getIconRes() {
            return R.raw.photo_pen;
        }
    }

    public static abstract class Shape extends Brush {
        public static List<Shape> SHAPES_LIST;

        public static class Arrow extends Shape {
            public Arrow() {
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getFilledIconRes() {
                return R.drawable.photo_arrowshape;
            }

            @Override // org.telegram.ui.Components.Paint.Brush
            public int getIconRes() {
                return R.drawable.photo_arrowshape;
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public String getShapeName() {
                return LocaleController.getString(R.string.PaintArrow);
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getShapeShaderType() {
                return 4;
            }
        }

        public static class Bubble extends Shape {
            public Bubble() {
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getFilledIconRes() {
                return R.drawable.msg_msgbubble2;
            }

            @Override // org.telegram.ui.Components.Paint.Brush
            public int getIconRes() {
                return R.drawable.msg_msgbubble;
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public String getShapeName() {
                return LocaleController.getString(R.string.PaintBubble);
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getShapeShaderType() {
                return 3;
            }
        }

        public static class Circle extends Shape {
            public Circle() {
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getFilledIconRes() {
                return R.drawable.photo_circle_fill;
            }

            @Override // org.telegram.ui.Components.Paint.Brush
            public int getIconRes() {
                return R.drawable.photo_circle;
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public String getShapeName() {
                return LocaleController.getString(R.string.PaintCircle);
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getShapeShaderType() {
                return 0;
            }
        }

        public static class Rectangle extends Shape {
            public Rectangle() {
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getFilledIconRes() {
                return R.drawable.photo_rectangle_fill;
            }

            @Override // org.telegram.ui.Components.Paint.Brush
            public int getIconRes() {
                return R.drawable.photo_rectangle;
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public String getShapeName() {
                return LocaleController.getString(R.string.PaintRectangle);
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getShapeShaderType() {
                return 1;
            }
        }

        public static class Star extends Shape {
            public Star() {
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getFilledIconRes() {
                return R.drawable.photo_star_fill;
            }

            @Override // org.telegram.ui.Components.Paint.Brush
            public int getIconRes() {
                return R.drawable.photo_star;
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public String getShapeName() {
                return LocaleController.getString(R.string.PaintStar);
            }

            @Override // org.telegram.ui.Components.Paint.Brush.Shape
            public int getShapeShaderType() {
                return 2;
            }
        }

        static {
            SHAPES_LIST = Arrays.asList(new Shape[]{new Circle(), new Rectangle(), new Star(), new Bubble(), new Arrow()});
        }

        public Shape() {
        }

        public static Shape make(int r3) {
            if (r3 >= 0) goto L4;
        L7:
            Regex$$ExternalSyntheticBUOutline0.m("Shape type must be in range from 0 to ", SHAPES_LIST.size() - 1, ", but got ", r3);
            return null;
        L4:
            if (r3 > SHAPES_LIST.size()) goto L7;
            return SHAPES_LIST.get(r3);
        }

        @Override // org.telegram.ui.Components.Paint.Brush
        public float getAlpha() {
            return 1.0f;
        }

        public abstract int getFilledIconRes();

        @Override // org.telegram.ui.Components.Paint.Brush
        public String getShaderName(int r1) {
            if (r1 != 0) goto L4;
            return "shape";
        L4:
            if (r1 != 1) goto L6;
            return "shape";
        L6:
            if (r1 == 2) goto L9;
            return null;
        L9:
            return "brush";
        }

        public abstract String getShapeName();

        public abstract int getShapeShaderType();
    }

    static {
        BRUSHES_LIST = Arrays.asList(new Brush[]{new Radial(), new Arrow(), new Elliptical(), new Neon(), new Blurer(), new Eraser()});
    }

    public Brush() {
    }

    public float getAlpha() {
        return 0.85f;
    }

    public float getAngle() {
        return 0.0f;
    }

    public int getDefaultColor() {
        return -16777216;
    }

    public float getDefaultWeight() {
        return 0.25f;
    }

    public abstract int getIconRes();

    public float getOverrideAlpha() {
        return 1.0f;
    }

    public float getPreviewScale() {
        return 0.4f;
    }

    public float getScale() {
        return 1.0f;
    }

    public String getShaderName(int r1) {
        if (r1 != 0) goto L4;
        return "blitWithMask";
    L4:
        if (r1 != 1) goto L6;
        return "compositeWithMask";
    L6:
        if (r1 == 2) goto L9;
        return null;
    L9:
        return "brush";
    }

    public float getSmoothThicknessRate() {
        return 1.0f;
    }

    public float getSpacing() {
        return 0.15f;
    }

    public Bitmap getStamp() {
        BitmapFactory.Options r0 = new BitmapFactory.Options();
        r0.inScaled = false;
        return BitmapFactory.decodeResource(ApplicationLoader.applicationContext.getResources(), getStampResId(), r0);
    }

    public int getStampResId() {
        return R.drawable.paint_radial_brush;
    }

    public boolean isEraser() {
        return false;
    }
}
