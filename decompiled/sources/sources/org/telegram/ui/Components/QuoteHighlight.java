package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.view.View;
import android.view.ViewParent;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public class QuoteHighlight extends Path {
    public final ChatMessageCell cell;
    private int cornerPathEffectSize;
    private float currentOffsetX;
    private float currentOffsetY;
    public final int end;
    public final int id;
    private Rect lastRect;
    private float minX;
    public final Paint paint;
    private final CornerPath path;
    public final boolean poll;
    public byte[] pollOptionId;
    public final ArrayList<Integer> quotesToExpand;
    private final ArrayList<Rect> rectangles;
    public final int start;
    private final AnimatedFloat t;
    public final boolean todo;

    public static class Rect {
        public float bottom;
        public boolean first;
        public boolean last;
        public float left;
        public float nextBottom;
        public float prevTop;
        public float right;
        public float top;

        private Rect() {
        }

        public /* synthetic */ Rect(QuoteHighlightIA r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$4waOk2vMbE18qeSW6UP2-HXYHDU, reason: not valid java name */
    public static /* synthetic */ void m12275$r8$lambda$4waOk2vMbE18qeSW6UP2HXYHDU(ChatMessageCell r1) {
        if (r1 == null) goto L5;
        r1.invalidate();
    L5:
        if ((r1.getParent() instanceof View) == false) goto L8;
        ((View) r1.getParent()).invalidate();
        return;
    }

    public static /* synthetic */ void $r8$lambda$FrmBRmhxAimyxX4zFmww8ORlVME(ChatMessageCell r1) {
        if (r1 == null) goto L5;
        r1.invalidate();
    L5:
        if ((r1.getParent() instanceof View) == false) goto L8;
        ((View) r1.getParent()).invalidate();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void $r8$lambda$ndzqcitrdwbkbHWU4Usl4NiPxIo(View r0, ViewParent r1) {
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        if ((r1 instanceof View) == false) goto L8;
        ((View) r1).invalidate();
        return;
    }

    public QuoteHighlight(View r15, ViewParent r16, int r17, ArrayList<MessageObject.TextLayoutBlock> r18, int r19, int r20, float r21) {
        Paint r3 = new Paint(1);
        this.paint = r3;
        this.path = new CornerPath();
        this.rectangles = new ArrayList();
        this.quotesToExpand = new ArrayList();
        this.cell = null;
        this.t = new AnimatedFloat(0.0f, new QuoteHighlight$$ExternalSyntheticLambda0(r15, r16), 350, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.id = r17;
        this.start = r19;
        this.end = r20;
        this.todo = false;
        this.poll = false;
        if (r18 == null) goto L52;
        int r7 = AndroidUtilities.dp(4.0f);
        this.cornerPathEffectSize = r7;
        r3.setPathEffect(new CornerPathEffect(r7));
        int r32 = 0;
        boolean r6 = false;
    L7:
        if (r32 >= r18.size()) goto L43;
        MessageObject.TextLayoutBlock r72 = r18.get(r32);
        if (r72 != null) goto L12;
    L41:
        r32 = r32 + 1;
        goto L7
    L12:
        if (r19 > r72.charactersEnd) goto L41;
        int r8 = r72.charactersOffset;
        if (r20 < r8) goto L41;
        int r82 = Math.max(0, r19 - r8);
        int r9 = r72.charactersOffset;
        int r92 = Math.min(r20 - r9, r72.charactersEnd - r9);
        float r11 = -r21;
        this.currentOffsetX = r11;
        if (r72.code == true) goto L19;
    L21:
        this.currentOffsetY = r72.textYOffset(r18) + r72.padTop;
        if (r72.quote == false) goto L24;
        float r112 = AndroidUtilities.dp(10.0f);
    L25:
        this.minX = r112;
        if (r6 == false) goto L28;
    L31:
        r6 = true;
    L32:
        StaticLayout r113 = r72.textLayout;
        if (r6 == false) goto L35;
        r113.getSelectionPath(r82, r92, this);
    L37:
        if (r72.quoteCollapse == false) goto L41;
        if (r72.collapsed() == false) goto L41;
        this.quotesToExpand.add(Integer.valueOf(r72.index));
        goto L41
    L35:
        getSelectionPath(r113, r82, r92);
        goto L37
    L28:
        if (AndroidUtilities.isRTL(r72.textLayout.getText()) == true) goto L31;
        r6 = false;
        goto L32
    L24:
        r112 = 0.0f;
        goto L25
    L19:
        if (r72.quote == true) goto L21;
        this.currentOffsetX = r11 + AndroidUtilities.dp(10.0f);
        goto L21
    L43:
        if (this.rectangles.size() <= 0) goto L51;
        Rect r0 = this.rectangles.get(0);
        ArrayList<Rect> r14 = this.rectangles;
        Rect r142 = r14.get(r14.size() - 1);
        r0.first = true;
        r0.top -= AndroidUtilities.dp(0.66f);
        r142.last = true;
        r142.bottom += AndroidUtilities.dp(0.66f);
        return;
    L51:
        return;
    }

    public QuoteHighlight(ChatMessageCell r12, int r13, int r14) {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.path = new CornerPath();
        this.rectangles = new ArrayList();
        this.quotesToExpand = new ArrayList();
        this.cell = r12;
        this.t = new AnimatedFloat(0.0f, new QuoteHighlight$$ExternalSyntheticLambda1(r12), 350, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.id = r13;
        int r122 = -r14;
        this.start = r122;
        this.end = r122;
        this.todo = true;
        this.poll = false;
        int r132 = AndroidUtilities.dp(4.0f);
        this.cornerPathEffectSize = r132;
        r0.setPathEffect(new CornerPathEffect(r132));
    }

    public QuoteHighlight(ChatMessageCell r12, int r13, byte[] r14) {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.path = new CornerPath();
        this.rectangles = new ArrayList();
        this.quotesToExpand = new ArrayList();
        this.cell = r12;
        this.t = new AnimatedFloat(0.0f, new QuoteHighlight$$ExternalSyntheticLambda2(r12), 350, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.id = r13;
        this.pollOptionId = r14;
        this.start = 0;
        this.end = 0;
        this.todo = false;
        this.poll = true;
        int r132 = AndroidUtilities.dp(4.0f);
        this.cornerPathEffectSize = r132;
        r0.setPathEffect(new CornerPathEffect(r132));
    }

    private void getSelectionPath(Layout r9, int r10, int r11) {
        if (r10 == r11) goto L26;
        if (r11 >= r10) goto L6;
        r11 = r10;
        r10 = r11;
    L6:
        int r0 = r9.getLineForOffset(r10);
        int r1 = r9.getLineForOffset(r11);
        int r2 = r0;
    L7:
        if (r2 > r1) goto L31;
        int r3 = r9.getLineStart(r2);
        int r4 = r9.getLineEnd(r2);
        if (r4 == r3) goto L25;
        if ((r3 + 1) == r4) goto L14;
    L16:
        if (r2 != r0) goto L19;
        if (r10 <= r3) goto L19;
        float r32 = r9.getPrimaryHorizontal(r10);
    L20:
        if (r2 != r1) goto L23;
        if (r11 >= r4) goto L23;
        float r42 = r9.getPrimaryHorizontal(r11);
    L24:
        addRect(Math.min(r32, r42), r9.getLineTop(r2), Math.max(r32, r42), r9.getLineBottom(r2));
    L23:
        r42 = r9.getLineRight(r2);
    L19:
        r32 = r9.getLineLeft(r2);
        goto L20
    L14:
        if (Character.isWhitespace(r9.getText().charAt(r3)) == false) goto L16;
    L25:
        r2 = r2 + 1;
        goto L7
    L31:
        return;
    }

    public void addRect(float r4, float r5, float r6, float r7) {
        if (r4 < r6) goto L5;
        return;
    L5:
        float r42 = Math.max(this.minX, r4);
        float r62 = Math.max(this.minX, r6);
        float r0 = this.currentOffsetX;
        float r43 = r42 + r0;
        float r1 = this.currentOffsetY;
        float r52 = r5 + r1;
        float r63 = r62 + r0;
        Rect r02 = new Rect(null);
        r02.left = r43 - AndroidUtilities.dp(3.0f);
        r02.right = r63 + AndroidUtilities.dp(3.0f);
        r02.top = r52;
        r02.bottom = r7 + r1;
        Rect r44 = this.lastRect;
        if (r44 == null) goto L8;
        float r64 = r44.bottom;
        r44.nextBottom = (r64 + r52) / 2.0f;
        r02.prevTop = (r64 + r52) / 2.0f;
    L8:
        this.rectangles.add(r02);
        this.lastRect = r02;
    }

    @Override // android.graphics.Path
    public void addRect(float r1, float r2, float r3, float r4, Path.Direction r5) {
        addRect(r1, r2, r3, r4);
    }

    public boolean done() {
        if (this.t.get() < 1.0f) goto L6;
        return true;
    L6:
        return false;
    }

    public void draw(Canvas r11, float r12, float r13, android.graphics.Rect r14, float r15) {
        float r0 = this.t.set(1.0f);
        r11.save();
        int r3 = 0;
        if (this.poll == false) goto L9;
        int r122 = AndroidUtilities.lerp(AndroidUtilities.dp(4.0f), 0, r0);
        if (this.cornerPathEffectSize == r122) goto L7;
        Paint r132 = this.paint;
        this.cornerPathEffectSize = r122;
        r132.setPathEffect(new CornerPathEffect(r122));
    L7:
        this.path.rewind();
        int r123 = this.cell.getPollIndex(this.pollOptionId);
        RectF r133 = AndroidUtilities.rectTmp;
        r133.set(this.cell.getBackgroundDrawableLeft(), this.cell.getPollButtonTop(r123), this.cell.getBackgroundDrawableRight(), this.cell.getPollButtonBottom(r123));
        AndroidUtilities.lerp(r14, r133, r0, r133);
        this.path.addRect(r133, Path.Direction.CW);
        this.path.closeRects();
    L27:
        int r124 = this.paint.getAlpha();
        this.paint.setAlpha((int) (r124 * r15));
        r11.drawPath(this.path, this.paint);
        this.paint.setAlpha(r124);
        r11.restore();
        return;
    L9:
        if (this.todo == false) goto L14;
        int r125 = AndroidUtilities.lerp(AndroidUtilities.dp(4.0f), 0, r0);
        if (this.cornerPathEffectSize == r125) goto L13;
        Paint r134 = this.paint;
        this.cornerPathEffectSize = r125;
        r134.setPathEffect(new CornerPathEffect(r125));
    L13:
        this.path.rewind();
        int r126 = this.cell.getTodoIndex(-this.start);
        RectF r135 = AndroidUtilities.rectTmp;
        r135.set(this.cell.getBackgroundDrawableLeft(), this.cell.getPollButtonTop(r126), this.cell.getBackgroundDrawableRight(), this.cell.getPollButtonBottom(r126));
        AndroidUtilities.lerp(r14, r135, r0, r135);
        this.path.addRect(r135, Path.Direction.CW);
        this.path.closeRects();
        goto L27
    L14:
        r11.translate(r12, r13);
        this.path.rewind();
    L16:
        if (r3 >= this.rectangles.size()) goto L26;
        Rect r1 = this.rectangles.get(r3);
        CornerPath r4 = this.path;
        float r5 = AndroidUtilities.lerp(r14.left - r12, r1.left, r0);
        if (r1.first == false) goto L20;
        float r2 = r14.top - r13;
    L21:
        float r6 = AndroidUtilities.lerp(r2, r1.top, r0);
        float r7 = AndroidUtilities.lerp(r14.right - r12, r1.right, r0);
        if (r1.last == false) goto L24;
        float r22 = r14.bottom - r13;
    L25:
        r4.addRect(r5, r6, r7, AndroidUtilities.lerp(r22, r1.bottom, r0), Path.Direction.CW);
        r3 = r3 + 1;
        goto L16
    L24:
        r22 = r1.nextBottom;
        goto L25
    L20:
        r2 = r1.prevTop;
        goto L21
    L26:
        this.path.closeRects();
        goto L27
    }

    public float getT() {
        return this.t.set(1.0f);
    }
}
