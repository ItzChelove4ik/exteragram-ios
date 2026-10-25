package org.telegram.ui.Components.Paint.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CornerPath;
import org.telegram.ui.Components.EditTextBoldCursor;

/* loaded from: classes7.dex */
public class EditTextOutline extends EditTextBoldCursor {
    public RectF framePadding;
    private boolean isFrameDirty;
    private float lastFrameRoundRadius;
    private RectF[] lines;
    private Bitmap mCache;
    private Canvas mCanvas;
    private int mFrameColor;
    private int mStrokeColor;
    private float mStrokeWidth;
    private boolean mUpdateCachedBitmap;
    private Paint paint;
    private CornerPath path;
    private TextPaint textPaint;

    public EditTextOutline(Context r3) {
        super(r3);
        this.mCanvas = new Canvas();
        this.textPaint = new TextPaint(1);
        this.paint = new Paint(1);
        this.path = new CornerPath();
        this.mStrokeColor = 0;
        setInputType(getInputType() | 655360);
        this.mUpdateCachedBitmap = true;
        this.isFrameDirty = true;
        setFrameRoundRadius(AndroidUtilities.dp(16.0f));
        this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void setFrameRoundRadius(float r3) {
        if (Math.abs(this.lastFrameRoundRadius - r3) <= 0.1f) goto L6;
        Paint r0 = this.paint;
        this.lastFrameRoundRadius = r3;
        r0.setPathEffect(new CornerPathEffect(r3));
        return;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r13) {
        int r1 = 0;
        if (this.mCache == null) goto L18;
        if (this.mStrokeColor == 0) goto L18;
        if (this.mUpdateCachedBitmap == false) goto L16;
        int r7 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int r0 = getMeasuredHeight();
        Editable r5 = getText();
        this.mCanvas.setBitmap(this.mCache);
        this.mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        float r3 = this.mStrokeWidth;
        if (r3 > 0.0f) goto L12;
        r3 = (float) Math.ceil(getTextSize() / 11.5f);
    L12:
        this.textPaint.setStrokeWidth(r3);
        this.textPaint.setColor(this.mStrokeColor);
        this.textPaint.setTextSize(getTextSize());
        this.textPaint.setTypeface(getTypeface());
        this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Layout.Alignment r32 = Layout.Alignment.ALIGN_NORMAL;
        if (getLayout() == null) goto L15;
        r32 = getLayout().getAlignment();
    L15:
        StaticLayout r4 = new StaticLayout(r5, this.textPaint, r7, r32, 1.0f, 0.0f, true);
        this.mCanvas.save();
        this.mCanvas.translate(getPaddingLeft(), ((((r0 - getPaddingTop()) - getPaddingBottom()) - r4.getHeight()) / 2.0f) + getPaddingTop());
        r4.draw(this.mCanvas);
        this.mCanvas.restore();
        this.mUpdateCachedBitmap = false;
    L16:
        r13.drawBitmap(this.mCache, 0.0f, 0.0f, this.textPaint);
    L18:
        if (this.mFrameColor == 0) goto L92;
        r13.save();
        r13.translate(getPaddingLeft(), getPaddingTop());
        this.paint.setColor(this.mFrameColor);
        Layout r02 = getLayout();
        if (r02 != null) goto L23;
        super.onDraw(r13);
        return;
    L23:
        RectF[] r33 = this.lines;
        if (r33 != null) goto L26;
    L27:
        this.lines = new RectF[r02.getLineCount()];
        this.isFrameDirty = true;
    L29:
        if (this.isFrameDirty == false) goto L53;
        this.isFrameDirty = false;
        int r34 = 0;
    L32:
        if (r34 >= r02.getLineCount()) goto L46;
        RectF[] r72 = this.lines;
        if (r72[r34] != null) goto L36;
        r72[r34] = new RectF();
    L36:
        this.lines[r34].set(r02.getLineLeft(r34), r02.getLineTop(r34), r02.getLineRight(r34), r02.getLineBottom(r34));
        float r73 = this.lines[r34].width();
        float r8 = AndroidUtilities.dp(1.0f);
        RectF[] r82 = this.lines;
        if (r73 <= r8) goto L39;
        r82[r34].inset((-getTextSize()) / 3.0f, 0.0f);
        this.lines[r34].top += AndroidUtilities.dpf2(1.2f);
        this.lines[r34].bottom += AndroidUtilities.dpf2(1.0f);
        this.lines[r34].left = Math.max(-getPaddingLeft(), this.lines[r34].left);
        this.lines[r34].right = Math.min(getWidth() - getPaddingLeft(), this.lines[r34].right);
    L40:
        if (r34 <= 0) goto L44;
        int r83 = r34 - 1;
        if (this.lines[r83].width() <= 0.0f) goto L44;
        RectF[] r74 = this.lines;
        r74[r83].bottom = r74[r34].top;
    L44:
        r34 = r34 + 1;
        goto L32
    L39:
        RectF r75 = r82[r34];
        r75.left = r75.right;
        goto L40
    L46:
        if (this.framePadding != null) goto L48;
        this.framePadding = new RectF();
    L48:
        this.framePadding.left = getMeasuredWidth();
        this.framePadding.top = getMeasuredHeight();
        RectF r03 = this.framePadding;
        r03.bottom = 0.0f;
        r03.right = 0.0f;
        int r04 = 0;
    L49:
        int r35 = this.lines.length;
        RectF r76 = this.framePadding;
        if (r04 >= r35) goto L52;
        r76.left = Math.min(r76.left, getPaddingLeft() + this.lines[r04].left);
        RectF r36 = this.framePadding;
        r36.top = Math.min(r36.top, getPaddingTop() + this.lines[r04].top);
        RectF r37 = this.framePadding;
        r37.right = Math.max(r37.right, getPaddingLeft() + this.lines[r04].right);
        RectF r38 = this.framePadding;
        r38.bottom = Math.max(r38.bottom, getPaddingTop() + this.lines[r04].bottom);
        r04 = r04 + 1;
        goto L49
    L52:
        float r05 = getMeasuredWidth();
        RectF r39 = this.framePadding;
        r76.right = r05 - r39.right;
        r39.bottom = getMeasuredHeight() - this.framePadding.bottom;
    L53:
        this.path.rewind();
        float r06 = getTextSize() / 3.0f;
        float r310 = 1.5f * r06;
        int r52 = 1;
    L54:
        RectF[] r77 = this.lines;
        if (r52 >= r77.length) goto L84;
        RectF r84 = r77[r52 - 1];
        RectF r78 = r77[r52];
        if (r84.width() < AndroidUtilities.dp(1.0f)) goto L83;
        if (r78.width() < AndroidUtilities.dp(1.0f)) goto L83;
        if (Math.abs(r84.left - r78.left) >= r310) goto L64;
        float r9 = Math.min(r78.left, r84.left);
        r84.left = r9;
        r78.left = r9;
        boolean r92 = true;
    L66:
        if (Math.abs(r84.right - r78.right) >= r310) goto L68;
        float r93 = Math.max(r78.right, r84.right);
        r84.right = r93;
        r78.right = r93;
        r92 = true;
    L68:
        if (r92 == false) goto L83;
        int r79 = r52;
    L70:
        if (r79 < 1) goto L83;
        RectF[] r85 = this.lines;
        RectF r94 = r85[r79 - 1];
        RectF r86 = r85[r79];
        if (r94.width() < AndroidUtilities.dp(1.0f)) goto L82;
        if (r86.width() < AndroidUtilities.dp(1.0f)) goto L82;
        if (Math.abs(r94.left - r86.left) >= r310) goto L80;
        float r10 = Math.min(r86.left, r94.left);
        r94.left = r10;
        r86.left = r10;
    L80:
        if (Math.abs(r94.right - r86.right) >= r310) goto L82;
        float r102 = Math.max(r86.right, r94.right);
        r94.right = r102;
        r86.right = r102;
    L82:
        r79 = r79 - 1;
        goto L70
    L64:
        r92 = false;
    L83:
        r52 = r52 + 1;
    L84:
        RectF[] r311 = this.lines;
        if (r1 >= r311.length) goto L91;
        if (r311[r1].width() == 0.0f) goto L90;
        this.path.addRect(this.lines[r1], Path.Direction.CW);
    L90:
        r1 = r1 + 1;
        goto L84
    L91:
        this.path.closeRects();
        setFrameRoundRadius(r06);
        r13.drawPath(this.path, this.paint);
        r13.restore();
    L93:
        super.onDraw(r13);
        return;
    L26:
        if (r33.length == r02.getLineCount()) goto L29;
    L92:
        this.framePadding = null;
        goto L93
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (r1 <= 0) goto L10;
        if (r2 <= 0) goto L10;
        this.mUpdateCachedBitmap = true;
        this.isFrameDirty = true;
        Bitmap r32 = this.mCache;
        if (r32 == null) goto L8;
        r32.recycle();
    L8:
        this.mCache = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);
        return;
    L10:
        this.mCache = null;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        this.mUpdateCachedBitmap = true;
        this.isFrameDirty = true;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int r1) {
        return super.onTextContextMenuItem(r1);
    }

    public void setFrameColor(int r7) {
        int r0 = this.mFrameColor;
        if (r0 != 0) goto L6;
        if (r7 == 0) goto L6;
        setPadding(AndroidUtilities.dp(19.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(7.0f));
        setCursorColor(-1);
    L9:
        this.mFrameColor = r7;
        if (r7 == 0) goto L19;
        float r72 = AndroidUtilities.computePerceivedBrightness(r7);
        if (r72 != 0.0f) goto L15;
        r72 = Color.red(this.mFrameColor) / 255.0f;
    L15:
        if (r72 <= 0.87d) goto L17;
        setTextColor(-16777216);
    L18:
        this.isFrameDirty = true;
        goto L19
    L17:
        setTextColor(-1);
    L19:
        this.mUpdateCachedBitmap = true;
        invalidate();
        return;
    L6:
        if (r0 == 0) goto L9;
        if (r7 != 0) goto L9;
        setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        setCursorColor(-1);
        goto L9
    }

    @Override // android.widget.TextView
    public void setGravity(int r1) {
        super.setGravity(r1);
        this.mUpdateCachedBitmap = true;
        this.isFrameDirty = true;
        invalidate();
    }

    public void setStrokeColor(int r1) {
        this.mStrokeColor = r1;
        this.mUpdateCachedBitmap = true;
        invalidate();
    }

    public void setStrokeWidth(float r1) {
        this.mStrokeWidth = r1;
        this.mUpdateCachedBitmap = true;
        invalidate();
    }
}
