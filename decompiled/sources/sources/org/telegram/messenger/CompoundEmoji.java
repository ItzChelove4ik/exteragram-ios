package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Emoji;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes.dex */
public class CompoundEmoji {
    private static Paint placeholderPaint;
    public static List<String> skinTones;

    /* loaded from: classes5.dex */
    public static class CompoundEmojiDrawable extends Emoji.EmojiDrawable {
        private static Paint paint;
        private static Rect rect;
        private DrawableInfo left;
        private AnimatedFloat leftUpdateT;
        private DrawableInfo newLeft;
        private DrawableInfo newRight;
        private View parent;
        private DrawableInfo right;
        private AnimatedFloat rightUpdateT;

        public static /* synthetic */ void $r8$lambda$wtKsVicO0i8S7Q4H_tTuOqwo9qM(CompoundEmojiDrawable r0) {
            r0.invalidate();
        }

        static {
            paint = new Paint(2);
            rect = new Rect();
        }

        public CompoundEmojiDrawable(DrawableInfo r1, DrawableInfo r2) {
            this.left = r1;
            this.right = r2;
        }

        private void drawDrawableInfo(Canvas r4, DrawableInfo r5, Rect r6, float r7) {
            Bitmap r3 = r5.getBitmap();
            if (r3 != null) goto L5;
            return;
        L5:
            if (r5.placeholder == false) goto L7;
            Paint r52 = CompoundEmoji.m3945$$Nest$sfgetplaceholderPaint();
        L9:
            if (r7 >= 1.0f) goto L11;
            int r1 = r52.getAlpha();
            r52.setAlpha((int) (r1 * r7));
        L12:
            r4.drawBitmap(r3, null, r6, r52);
            if (r7 >= 1.0f) goto L17;
            r52.setAlpha(r1);
            return;
        L17:
            return;
        L11:
            r1 = 255;
            goto L12
        L7:
            r52 = paint;
            goto L9
        }

        private void invalidate() {
            View r0 = this.parent;
            if (r0 == null) goto L5;
            r0.invalidate();
        L5:
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r14) {
            if (isLoaded() == true) goto L7;
            preload();
            Emoji.placeholderPaint.setColor(this.placeholderColor);
            Rect r13 = getBounds();
            r14.drawCircle(r13.centerX(), r13.centerY(), r13.width() * 0.4f, Emoji.placeholderPaint);
            return;
        L7:
            if (this.fullSize == false) goto L9;
            Rect r0 = getDrawRect();
        L11:
            if (r14.quickReject(r0.left, r0.top, r0.right, r0.bottom, Canvas.EdgeType.AA) == false) goto L13;
            return;
        L13:
            if (this.newLeft != null) goto L15;
            drawDrawableInfo(r14, this.left, r0, 1.0f);
        L22:
            if (this.newRight != null) goto L24;
            drawDrawableInfo(r14, this.right, r0, 1.0f);
            return;
        L24:
            if (this.rightUpdateT != null) goto L26;
            this.rightUpdateT = new AnimatedFloat(0.0f, new CompoundEmoji$CompoundEmojiDrawable$$ExternalSyntheticLambda0(this), 0, 320, CubicBezierInterpolator.EASE_OUT);
        L26:
            float r142 = this.rightUpdateT.set(1.0f);
            drawDrawableInfo(r14, this.newRight, r0, Math.min(1.0f, 1.5f * r142));
            drawDrawableInfo(r14, this.right, r0, 1.0f - r142);
            if (r142 < 1.0f) goto L33;
            this.right = this.newRight;
            this.newRight = null;
            return;
        L33:
            return;
        L15:
            if (this.leftUpdateT != null) goto L17;
            this.leftUpdateT = new AnimatedFloat(0.0f, new CompoundEmoji$CompoundEmojiDrawable$$ExternalSyntheticLambda0(this), 0, 320, CubicBezierInterpolator.EASE_OUT);
        L17:
            float r143 = this.leftUpdateT.set(1.0f);
            drawDrawableInfo(r14, this.newLeft, r0, Math.min(1.0f, r143 * 1.5f));
            drawDrawableInfo(r14, this.left, r0, 1.0f - r143);
            if (r143 < 1.0f) goto L22;
            this.left = this.newLeft;
            this.newLeft = null;
            goto L22
        L9:
            r0 = getBounds();
            goto L11
        }

        public Rect getDrawRect() {
            Rect r0 = getBounds();
            int r1 = r0.centerX();
            int r02 = r0.centerY();
            Rect r2 = rect;
            boolean r4 = this.fullSize;
            if (r4 == false) goto L5;
            int r3 = Emoji.bigImgSize;
        L6:
            r2.left = r1 - (r3 / 2);
            Rect r22 = rect;
            if (r4 == false) goto L9;
            int r32 = Emoji.bigImgSize;
        L10:
            r22.right = r1 + (r32 / 2);
            Rect r12 = rect;
            if (r4 == false) goto L13;
            int r23 = Emoji.bigImgSize;
        L14:
            r12.top = r02 - (r23 / 2);
            Rect r13 = rect;
            if (r4 == false) goto L17;
            int r42 = Emoji.bigImgSize;
        L18:
            r13.bottom = r02 + (r42 / 2);
            return rect;
        L17:
            r42 = Emoji.drawImgSize;
            goto L18
        L13:
            r23 = Emoji.drawImgSize;
            goto L14
        L9:
            r32 = Emoji.drawImgSize;
            goto L10
        L5:
            r3 = Emoji.drawImgSize;
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // org.telegram.messenger.Emoji.EmojiDrawable
        public boolean isLoaded() {
            if (this.left.isLoaded() == true) goto L5;
            return false;
        L5:
            if (this.right.isLoaded() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.messenger.Emoji.EmojiDrawable
        public void preload() {
            if (isLoaded() == true) goto L6;
            this.left.load();
            this.right.load();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void update(int r4, int r5) {
            if (this.left.skin == r4) goto L11;
            DrawableInfo r0 = this.newLeft;
            if (r0 == null) goto L7;
            this.left = r0;
        L7:
            this.newLeft = this.left.updateSkin(r4);
            AnimatedFloat r42 = this.leftUpdateT;
            if (r42 == null) goto L11;
            r42.set(0.0f, true);
        L11:
            if (this.right.skin == r5) goto L18;
            DrawableInfo r43 = this.newRight;
            if (r43 == null) goto L15;
            this.right = r43;
        L15:
            this.newRight = this.right.updateSkin(r5);
            AnimatedFloat r44 = this.rightUpdateT;
            if (r44 == null) goto L18;
            r44.set(0.0f, true);
        L18:
            invalidate();
        }
    }

    /* loaded from: classes5.dex */
    public static class DrawableInfo {
        private static final SparseArray<Bitmap> bitmaps = null;
        private static final ArrayList<Integer> loading = null;
        int emoji;
        int hash;
        int place;
        boolean placeholder;
        int skin;

        public static /* synthetic */ void $r8$lambda$Lz0lpXHZKdCBy3UUD4RuWiOuadE(DrawableInfo r0) {
            r0.lambda$load$0();
        }

        static {
            bitmaps = new SparseArray();
            loading = new ArrayList();
        }

        public DrawableInfo(int r2, int r3, int r4) {
            if (r3 != (-2)) goto L5;
            this.placeholder = true;
            r3 = -1;
        L5:
            this.emoji = r2;
            Integer r22 = Integer.valueOf(r2);
            this.skin = r3;
            Integer r32 = Integer.valueOf(r3);
            this.place = r4;
            this.hash = Objects.hash(new Object[]{r22, r32, Integer.valueOf(r4)});
        }

        private /* synthetic */ void lambda$load$0() {
            Bitmap r0 = Emoji.loadBitmap("emoji/compound/" + this.emoji + "_" + this.skin + "_" + this.place + ".png");
            if (r0 == null) goto L5;
            bitmaps.put(this.hash, r0);
            Runnable r02 = Emoji.invalidateUiRunnable;
            AndroidUtilities.cancelRunOnUIThread(r02);
            AndroidUtilities.runOnUIThread(r02);
        L5:
            loading.remove(Integer.valueOf(this.hash));
        }

        public Bitmap getBitmap() {
            return bitmaps.get(this.hash);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean isLoaded() {
            if (bitmaps.indexOfKey(this.hash) < 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void load() {
            if (isLoaded() == true) goto L9;
            ArrayList<Integer> r0 = loading;
            if (r0.contains(Integer.valueOf(this.hash)) == true) goto L10;
            r0.add(Integer.valueOf(this.hash));
            Utilities.globalQueue.postRunnable(new CompoundEmoji$DrawableInfo$$ExternalSyntheticLambda0(this));
            return;
        L10:
            return;
        }

        public DrawableInfo updateSkin(int r3) {
            if (this.skin != r3) goto L6;
            return this;
        L6:
            return new DrawableInfo(this.emoji, r3, this.place);
        }
    }

    /* renamed from: -$$Nest$sfgetplaceholderPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m3945$$Nest$sfgetplaceholderPaint() {
        return placeholderPaint;
    }

    static {
        skinTones = Arrays.asList(new String[]{"🏻", "🏼", "🏽", "🏾", "🏿"});
        placeholderPaint = new Paint(2);
    }

    public CompoundEmoji() {
    }

    public static String applyColor(String r4, String r5) {
        if (isHandshake(r4) != null) goto L6;
        return r4;
    L6:
        if (r5 != null) goto L9;
        return "🤝";
    L9:
        if (r5.contains("\u200d") == false) goto L20;
        String[] r42 = r5.split("\u200d");
        StringBuilder r52 = new StringBuilder("🫱");
        int r0 = r42.length;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 < 1) goto L13;
        String r02 = r42[0];
    L14:
        r52.append(r02);
        r52.append("\u200d🫲");
        if (r42.length < 2) goto L17;
        r1 = r42[1];
    L17:
        r52.append(r1);
        return r52.toString();
    L13:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L20:
        return "🤝".concat(r5);
    }

    public static CompoundEmojiDrawable getCompoundEmojiDrawable(String r1) {
        return getCompoundEmojiDrawable(r1, null, null);
    }

    public static CompoundEmojiDrawable getCompoundEmojiDrawable(String r3, Integer r4, Integer r5) {
        CompoundEmojiDrawable r0 = null;
        if (r3 != null) goto L5;
        return null;
    L5:
        Pair<Integer, Integer> r32 = isHandshake(r3);
        if (r32 != null) goto L8;
    L16:
        return r0;
    L8:
        if (r4 != null) goto L11;
        r4 = (Integer) r32.first;
    L11:
        DrawableInfo r1 = new DrawableInfo(0, r4.intValue(), 0);
        if (r5 != null) goto L15;
        r5 = (Integer) r32.second;
    L15:
        r0 = new CompoundEmojiDrawable(r1, new DrawableInfo(0, r5.intValue(), 1));
        goto L16
    }

    public static int getSkinTone(String r2) {
        if (r2 != null) goto L6;
        return -1;
    L6:
        return skinTones.indexOf(r2.substring(r2.length() - 2));
    }

    public static boolean isCompound(String r0) {
        if (getCompoundEmojiDrawable(r0) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static Pair<Integer, Integer> isHandshake(String r8) {
        int r3 = -1;
        if (r8.startsWith("🤝") == true) goto L5;
    L13:
        int r0 = -1;
    L14:
        String[] r82 = r8.split("\u200d");
        if (r82.length == 2) goto L17;
        return null;
    L17:
        if (r82[0].startsWith("🫱") == true) goto L19;
        return null;
    L19:
        if (r82[1].startsWith("🫲") == true) goto L21;
        return null;
    L21:
        if (r82[0].length() == 2) goto L27;
        if (r82[0].length() != 4) goto L38;
        r0 = getSkinTone(r82[0]);
        if (r0 >= 0) goto L27;
        return null;
    L38:
        return null;
    L27:
        if (r82[1].length() == 2) goto L33;
        if (r82[1].length() != 4) goto L40;
        r3 = getSkinTone(r82[1]);
        if (r3 >= 0) goto L33;
        return null;
    L40:
        return null;
    L33:
        return new Pair(Integer.valueOf(r0), Integer.valueOf(r3));
    L5:
        if (r8.length() == 2) goto L12;
        if (r8.length() != 4) goto L13;
        r0 = getSkinTone(r8);
        if (r0 < 0) goto L14;
        r3 = r0;
    L12:
        return new Pair(Integer.valueOf(r3), Integer.valueOf(r3));
    }

    public static void setPlaceholderColor(int r3) {
        placeholderPaint.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
    }
}
