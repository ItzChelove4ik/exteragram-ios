package androidx.appcompat.widget;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: classes3.dex */
class AppCompatProgressBarHelper {
    private static final int[] TINT_ATTRS = null;
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    public static class Api23Impl {
        public static void transferLayerProperties(LayerDrawable r1, LayerDrawable r2, int r3) {
            r2.setLayerGravity(r3, r1.getLayerGravity(r3));
            r2.setLayerWidth(r3, r1.getLayerWidth(r3));
            r2.setLayerHeight(r3, r1.getLayerHeight(r3));
            r2.setLayerInsetLeft(r3, r1.getLayerInsetLeft(r3));
            r2.setLayerInsetRight(r3, r1.getLayerInsetRight(r3));
            r2.setLayerInsetTop(r3, r1.getLayerInsetTop(r3));
            r2.setLayerInsetBottom(r3, r1.getLayerInsetBottom(r3));
            r2.setLayerInsetStart(r3, r1.getLayerInsetStart(r3));
            r2.setLayerInsetEnd(r3, r1.getLayerInsetEnd(r3));
        }
    }

    static {
        TINT_ATTRS = new int[]{R.attr.indeterminateDrawable, R.attr.progressDrawable};
    }

    public AppCompatProgressBarHelper(ProgressBar r1) {
        this.mView = r1;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable tileifyIndeterminate(Drawable r7) {
        if ((r7 instanceof AnimationDrawable) == false) goto L10;
        AnimationDrawable r72 = (AnimationDrawable) r7;
        int r0 = r72.getNumberOfFrames();
        AnimationDrawable r1 = new AnimationDrawable();
        r1.setOneShot(r72.isOneShot());
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        Drawable r4 = tileify(r72.getFrame(r2), true);
        r4.setLevel(10000);
        r1.addFrame(r4, r72.getDuration(r2));
        r2 = r2 + 1;
        goto L6
    L8:
        r1.setLevel(10000);
        return r1;
    L10:
        return r7;
    }

    public Bitmap getSampleTile() {
        return this.mSampleTile;
    }

    public void loadFromAttributes(AttributeSet r4, int r5) {
        TintTypedArray r42 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), r4, TINT_ATTRS, r5, 0);
        Drawable r52 = r42.getDrawableIfKnown(0);
        if (r52 == null) goto L5;
        this.mView.setIndeterminateDrawable(tileifyIndeterminate(r52));
    L5:
        Drawable r53 = r42.getDrawableIfKnown(1);
        if (r53 == null) goto L8;
        this.mView.setProgressDrawable(tileify(r53, false));
    L8:
        r42.recycle();
    }

    public Drawable tileify(Drawable r8, boolean r9) {
        if ((r8 instanceof LayerDrawable) == false) goto L19;
        LayerDrawable r82 = (LayerDrawable) r8;
        int r92 = r82.getNumberOfLayers();
        Drawable[] r0 = new Drawable[r92];
        int r2 = 0;
        int r3 = 0;
    L5:
        if (r3 >= r92) goto L14;
        int r4 = r82.getId(r3);
        Drawable r5 = r82.getDrawable(r3);
        if (r4 != 16908301) goto L9;
    L12:
        boolean r42 = true;
    L13:
        r0[r3] = tileify(r5, r42);
        r3 = r3 + 1;
        goto L5
    L9:
        if (r4 == 16908303) goto L12;
        r42 = false;
        goto L13
    L14:
        LayerDrawable r7 = new LayerDrawable(r0);
    L15:
        if (r2 >= r92) goto L17;
        r7.setId(r2, r82.getId(r2));
        Api23Impl.transferLayerProperties(r82, r7, r2);
        r2 = r2 + 1;
        goto L15
    L17:
        return r7;
    L19:
        if ((r8 instanceof BitmapDrawable) == false) goto L28;
        BitmapDrawable r83 = (BitmapDrawable) r8;
        Bitmap r02 = r83.getBitmap();
        if (this.mSampleTile != null) goto L23;
        this.mSampleTile = r02;
    L23:
        ShapeDrawable r22 = new ShapeDrawable(getDrawableShape());
        r22.getPaint().setShader(new BitmapShader(r02, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
        r22.getPaint().setColorFilter(r83.getPaint().getColorFilter());
        if (r9 == true) goto L26;
        return r22;
    L26:
        return new ClipDrawable(r22, 3, 1);
    L28:
        return r8;
    }
}
