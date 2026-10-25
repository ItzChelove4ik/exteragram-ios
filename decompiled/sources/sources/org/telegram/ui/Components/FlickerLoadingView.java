package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.text.TextPaint;
import android.view.View;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class FlickerLoadingView extends View implements Theme.Colorable {
    private Paint backgroundPaint;
    private int color0;
    private int color1;
    private int colorKey1;
    private int colorKey2;
    private int colorKey3;
    FlickerLoadingView globalGradientView;
    private LinearGradient gradient;
    private int gradientWidth;
    private Paint headerPaint;
    private boolean ignoreHeightCheck;
    private boolean isSingleCell;
    private int itemsCount;
    private long lastUpdateTime;
    private Matrix matrix;
    private float memberRequestButtonWidth;
    private int paddingLeft;
    private int paddingTop;
    private Paint paint;
    private int parentHeight;
    private int parentWidth;
    private float parentXOffset;
    float[] randomParams;
    private RectF rectF;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean showDate;
    private int skipDrawItemsCount;
    private int totalTranslation;
    private boolean useHeaderOffset;
    private int viewType;

    public FlickerLoadingView(Context r2) {
        this(r2, null);
    }

    public FlickerLoadingView(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.paint = new Paint();
        this.headerPaint = new Paint();
        this.rectF = new RectF();
        this.showDate = true;
        this.colorKey1 = Theme.key_actionBarDefaultSubmenuBackground;
        this.colorKey2 = Theme.key_listSelector;
        this.colorKey3 = -1;
        this.itemsCount = 1;
        this.resourcesProvider = r3;
        this.matrix = new Matrix();
    }

    private float checkRtl(float r2) {
        if (LocaleController.isRTL == true) goto L5;
        return r2;
    L5:
        return getMeasuredWidth() - r2;
    }

    private void checkRtl(RectF r3) {
        if (LocaleController.isRTL == false) goto L6;
        r3.left = getMeasuredWidth() - r3.left;
        r3.right = getMeasuredWidth() - r3.right;
        return;
    }

    private int getCellHeight(int r6) {
        switch(getViewType()) {
            case 1: goto L69;
            case 2: goto L67;
            case 3: goto L65;
            case 4: goto L65;
            case 5: goto L63;
            case 6: goto L61;
            case 7: goto L55;
            case 8: goto L53;
            case 9: goto L51;
            case 10: goto L49;
            case 11: goto L47;
            case 12: goto L45;
            case 13: goto L4;
            case 14: goto L4;
            case 15: goto L43;
            case 16: goto L41;
            case 17: goto L4;
            case 18: goto L61;
            case 19: goto L39;
            case 20: goto L4;
            case 21: goto L37;
            case 22: goto L35;
            case 23: goto L41;
            case 24: goto L29;
            case 25: goto L27;
            case 26: goto L25;
            case 27: goto L4;
            case 28: goto L23;
            case 29: goto L21;
            case 30: goto L19;
            case 31: goto L17;
            case 32: goto L15;
            case 33: goto L13;
            case 34: goto L11;
            case 35: goto L9;
            case 36: goto L7;
            default: goto L4;
        };
    L4:
        return 0;
    L7:
        return AndroidUtilities.dp(108.0f);
    L9:
        return AndroidUtilities.dp(112.0f);
    L11:
        return AndroidUtilities.dp(140.0f);
    L13:
        return AndroidUtilities.dp(58.0f);
    L15:
        return AndroidUtilities.dp(56.0f) + 1;
    L17:
        return AndroidUtilities.dp(48.0f) + 1;
    L19:
        return AndroidUtilities.dp(32.0f);
    L21:
        return AndroidUtilities.dp(60.0f) + 1;
    L23:
        return AndroidUtilities.dp(58.0f);
    L25:
        return AndroidUtilities.dp(50.0f) + 1;
    L27:
        return AndroidUtilities.dp(51.0f);
    L29:
        if (SharedConfig.useThreeLinesLayout == false) goto L31;
        int r5 = 76;
    L33:
        return AndroidUtilities.dp(r5 + 1);
    L31:
        r5 = 64;
        goto L33
    L35:
        return AndroidUtilities.dp(60.0f);
    L37:
        return AndroidUtilities.dp(58.0f);
    L39:
        return AndroidUtilities.dp(58.0f);
    L41:
        return AndroidUtilities.dp(50.0f);
    L43:
        return AndroidUtilities.dp(107.0f);
    L45:
        return AndroidUtilities.dp(103.0f);
    L47:
        return AndroidUtilities.dp(36.0f);
    L49:
        return AndroidUtilities.dp(58.0f);
    L51:
        return AndroidUtilities.dp(66.0f);
    L53:
        return AndroidUtilities.dp(61.0f);
    L55:
        if (SharedConfig.useThreeLinesLayout == false) goto L57;
        int r52 = 78;
    L59:
        return AndroidUtilities.dp(r52 + 1);
    L57:
        r52 = 72;
        goto L59
    L61:
        return AndroidUtilities.dp(64.0f);
    L63:
        return AndroidUtilities.dp(80.0f);
    L65:
        return AndroidUtilities.dp(56.0f);
    L67:
        return ((r6 - (AndroidUtilities.dp(2.0f) * (getColumnsCount() - 1))) / getColumnsCount()) + AndroidUtilities.dp(2.0f);
    L69:
        return AndroidUtilities.dp(78.0f) + 1;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public int getAdditionalHeight() {
        return 0;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getColumnsCount() {
        return 2;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public int getViewType() {
        return this.viewType;
    }

    @Override // android.view.View
    public void onDraw(Canvas r45) {
        Paint r1 = this.paint;
        if (this.globalGradientView != null) goto L5;
    L8:
        Paint r7 = r1;
        if (getViewType() != 34) goto L11;
    L14:
        this.parentXOffset = -getX();
    L15:
        updateColors();
        updateGradient();
        int r12 = this.paddingTop;
        if (this.useHeaderOffset == false) goto L25;
        int r9 = r12 + AndroidUtilities.dp(32.0f);
        int r13 = this.colorKey3;
        if (r13 < 0) goto L20;
        this.headerPaint.setColor(getThemedColor(r13));
    L20:
        float r4 = getMeasuredWidth();
        float r5 = AndroidUtilities.dp(32.0f);
        if (this.colorKey3 < 0) goto L23;
        Paint r6 = this.headerPaint;
    L24:
        r45.drawRect(0.0f, 0.0f, r4, r5, r6);
        Canvas r2 = r45;
        r12 = r9;
    L26:
        int r19 = 0;
        int r52 = 1;
        if (getViewType() != 7) goto L43;
    L29:
        if (r12 > getMeasuredHeight()) goto L400;
        int r3 = getCellHeight(getMeasuredWidth());
        r2.drawCircle(checkRtl(AndroidUtilities.dp(10.0f) + r4), (r3 >> 1) + r12, AndroidUtilities.dp(28.0f), r7);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(16.0f) + r12, AndroidUtilities.dp(148.0f), r12 + AndroidUtilities.dp(24.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(38.0f) + r12, AndroidUtilities.dp(268.0f), AndroidUtilities.dp(46.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        if (SharedConfig.useThreeLinesLayout == false) goto L34;
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(54.0f) + r12, AndroidUtilities.dp(220.0f), AndroidUtilities.dp(62.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
    L34:
        if (this.showDate == false) goto L36;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(16.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
    L36:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r32 = r19 + 1;
        if (this.isSingleCell == false) goto L41;
        if (r32 >= this.itemsCount) goto L400;
    L41:
        r19 = r32;
    L400:
        invalidate();
        return;
    L43:
        if (getViewType() != 24) goto L59;
    L45:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl(AndroidUtilities.dp(10.0f) + r3), (AndroidUtilities.dp(10.0f) + r12) + r3, AndroidUtilities.dp(14.0f), r7);
        r2.save();
        r2.translate(0.0f, -AndroidUtilities.dp(4.0f));
        this.rectF.set(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(16.0f) + r12, AndroidUtilities.dp(148.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        this.rectF.set(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(38.0f) + r12, AndroidUtilities.dp(268.0f), AndroidUtilities.dp(46.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        if (SharedConfig.useThreeLinesLayout == false) goto L50;
        this.rectF.set(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(54.0f) + r12, AndroidUtilities.dp(220.0f), AndroidUtilities.dp(62.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
    L50:
        if (this.showDate == false) goto L52;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(16.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
    L52:
        r2.restore();
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r33 = r19 + 1;
        if (this.isSingleCell == false) goto L57;
        if (r33 >= this.itemsCount) goto L400;
    L57:
        r19 = r33;
        goto L45
    L59:
        if (getViewType() != 18) goto L73;
        int r10 = r12;
    L62:
        if (r10 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(9.0f)) + r1), AndroidUtilities.dp(32.0f) + r10, AndroidUtilities.dp(25.0f), r7);
        if ((r19 % 2) != 0) goto L66;
        int r14 = 52;
    L67:
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(20.0f) + r10, AndroidUtilities.dp(r14 + 76), AndroidUtilities.dp(28.0f) + r10);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        this.rectF.set(AndroidUtilities.dp(r14 + 84), AndroidUtilities.dp(20.0f) + r10, AndroidUtilities.dp(r14 + 168), AndroidUtilities.dp(28.0f) + r10);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(42.0f) + r10, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(50.0f) + r10);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r7);
        Canvas r62 = r2;
        Paint r63 = r7;
        r62.drawLine(AndroidUtilities.dp(76.0f), getCellHeight(getMeasuredWidth()) + r10, getMeasuredWidth(), getCellHeight(getMeasuredWidth()) + r10, r63);
        r2 = r62;
        r10 = r10 + getCellHeight(getMeasuredWidth());
        int r15 = r19 + 1;
        if (this.isSingleCell == false) goto L72;
        if (r15 >= this.itemsCount) goto L400;
    L72:
        r19 = r15;
        r7 = r63;
        goto L62
    L66:
        r14 = 72;
        goto L67
    L73:
        Paint r34 = r7;
        if (getViewType() != 19) goto L89;
        int r72 = r12;
    L77:
        if (r72 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(9.0f)) + r1), AndroidUtilities.dp(29.0f) + r72, AndroidUtilities.dp(20.0f), r34);
        if ((r19 % 2) != 0) goto L81;
        int r16 = 92;
    L82:
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(16.0f) + r72, AndroidUtilities.dp(r16 + 76), AndroidUtilities.dp(24.0f) + r72);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(38.0f) + r72, AndroidUtilities.dp(240.0f), AndroidUtilities.dp(46.0f) + r72);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        Canvas r43 = r2;
        Paint r64 = r34;
        r43.drawLine(AndroidUtilities.dp(76.0f), getCellHeight(getMeasuredWidth()) + r72, getMeasuredWidth(), getCellHeight(getMeasuredWidth()) + r72, r64);
        r2 = r43;
        r34 = r64;
        r72 = r72 + getCellHeight(getMeasuredWidth());
        int r17 = r19 + 1;
        if (this.isSingleCell == false) goto L87;
        if (r17 >= this.itemsCount) goto L400;
    L87:
        r19 = r17;
        goto L77
    L81:
        r16 = 128;
        goto L82
    L89:
        if (getViewType() != 1) goto L102;
    L91:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl(AndroidUtilities.dp(9.0f) + r4), (AndroidUtilities.dp(78.0f) >> 1) + r12, AndroidUtilities.dp(25.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(20.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(42.0f) + r12, AndroidUtilities.dp(260.0f), AndroidUtilities.dp(50.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L95;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(20.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L95:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r42 = r19 + 1;
        if (this.isSingleCell == false) goto L100;
        if (r42 >= this.itemsCount) goto L400;
    L100:
        r19 = r42;
        goto L91
    L102:
        if (getViewType() == 2) goto L379;
        if (getViewType() == 27) goto L379;
        if (getViewType() != 3) goto L120;
    L109:
        if (r12 > getMeasuredHeight()) goto L400;
        this.rectF.set(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f) + r12, AndroidUtilities.dp(52.0f), AndroidUtilities.dp(48.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(12.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(34.0f) + r12, AndroidUtilities.dp(260.0f), AndroidUtilities.dp(42.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L113;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(12.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L113:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r44 = r19 + 1;
        if (this.isSingleCell == false) goto L118;
        if (r44 >= this.itemsCount) goto L400;
    L118:
        r19 = r44;
        goto L109
    L120:
        if (getViewType() != 4) goto L133;
    L122:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl(AndroidUtilities.dp(12.0f) + r4), (AndroidUtilities.dp(6.0f) + r12) + r4, AndroidUtilities.dp(44.0f) >> 1, r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(12.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(34.0f) + r12, AndroidUtilities.dp(260.0f), AndroidUtilities.dp(42.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L126;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(12.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L126:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r46 = r19 + 1;
        if (this.isSingleCell == false) goto L131;
        if (r46 >= this.itemsCount) goto L400;
    L131:
        r19 = r46;
        goto L122
    L133:
        if (getViewType() != 5) goto L146;
    L135:
        if (r12 > getMeasuredHeight()) goto L400;
        this.rectF.set(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(11.0f) + r12, AndroidUtilities.dp(62.0f), AndroidUtilities.dp(63.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(12.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(34.0f) + r12, AndroidUtilities.dp(268.0f), AndroidUtilities.dp(42.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(68.0f), AndroidUtilities.dp(54.0f) + r12, AndroidUtilities.dp(188.0f), AndroidUtilities.dp(62.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L139;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(12.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L139:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r47 = r19 + 1;
        if (this.isSingleCell == false) goto L144;
        if (r47 >= this.itemsCount) goto L400;
    L144:
        r19 = r47;
        goto L135
    L146:
        if (getViewType() == 6) goto L369;
        if (getViewType() == 10) goto L369;
        if (getViewType() != 29) goto L161;
    L153:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(9.0f)) + r4), (AndroidUtilities.dp(64.0f) >> 1) + r12, AndroidUtilities.dp(23.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(17.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(25.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(39.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(47.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r48 = r19 + 1;
        if (this.isSingleCell == false) goto L159;
        if (r48 >= this.itemsCount) goto L400;
    L159:
        r19 = r48;
        goto L153
    L161:
        if (getViewType() != 33) goto L171;
    L163:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(13.0f)) + r4), (AndroidUtilities.dp(58.0f) >> 1) + r12, AndroidUtilities.dp(23.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(72.0f), AndroidUtilities.dp(17.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(25.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(72.0f), AndroidUtilities.dp(39.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(47.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r49 = r19 + 1;
        if (this.isSingleCell == false) goto L169;
        if (r49 >= this.itemsCount) goto L400;
    L169:
        r19 = r49;
        goto L163
    L171:
        if (getViewType() != 30) goto L181;
    L173:
        if (r12 > getMeasuredHeight()) goto L400;
        r12 = r12 + getCellHeight(getMeasuredWidth());
        this.rectF.set(0.0f, r12, getMeasuredWidth(), r12);
        checkRtl(this.rectF);
        r2.drawRect(this.rectF, r34);
        int r410 = r19 + 1;
        if (this.isSingleCell == false) goto L179;
        if (r410 >= this.itemsCount) goto L400;
    L179:
        r19 = r410;
        goto L173
    L181:
        if (getViewType() != 8) goto L194;
    L183:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(11.0f)) + r4), (AndroidUtilities.dp(64.0f) >> 1) + r12, AndroidUtilities.dp(23.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(17.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(25.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(39.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(47.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L187;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(20.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L187:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r411 = r19 + 1;
        if (this.isSingleCell == false) goto L192;
        if (r411 >= this.itemsCount) goto L400;
    L192:
        r19 = r411;
        goto L183
    L194:
        if (getViewType() != 9) goto L207;
    L196:
        if (r12 > getMeasuredHeight()) goto L400;
        int r412 = getCellHeight(getMeasuredWidth());
        r2.drawCircle(checkRtl(AndroidUtilities.dp(35.0f)), (r412 >> 1) + r12, AndroidUtilities.dp(32.0f) / 2, r34);
        this.rectF.set(AndroidUtilities.dp(72.0f), AndroidUtilities.dp(16.0f) + r12, AndroidUtilities.dp(268.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(72.0f), AndroidUtilities.dp(38.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(46.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L200;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(16.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L200:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r413 = r19 + 1;
        if (this.isSingleCell == false) goto L205;
        if (r413 >= this.itemsCount) goto L400;
    L205:
        r19 = r413;
        goto L196
    L207:
        if (getViewType() != 11) goto L217;
        int r414 = 0;
    L210:
        if (r12 > getMeasuredHeight()) goto L400;
        this.rectF.set(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(14.0f), (getMeasuredWidth() * 0.5f) + AndroidUtilities.dp(this.randomParams[0] * 40.0f), AndroidUtilities.dp(14.0f) + AndroidUtilities.dp(8.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(18.0f), AndroidUtilities.dp(14.0f), (getMeasuredWidth() - (getMeasuredWidth() * 0.2f)) - AndroidUtilities.dp(this.randomParams[0] * 20.0f), AndroidUtilities.dp(14.0f) + AndroidUtilities.dp(8.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        r414 = r414 + 1;
        if (this.isSingleCell == false) goto L210;
        if (r414 < this.itemsCount) goto L210;
    L217:
        if (getViewType() != 12) goto L228;
        int r18 = r12 + AndroidUtilities.dp(14.0f);
    L220:
        if (r18 > getMeasuredHeight()) goto L400;
        int r415 = getMeasuredWidth() / 4;
        int r53 = 0;
    L222:
        if (r53 >= 4) goto L224;
        float r73 = (r415 * r53) + (r415 / 2.0f);
        r2.drawCircle(r73, (AndroidUtilities.dp(7.0f) + r18) + (AndroidUtilities.dp(56.0f) / 2.0f), AndroidUtilities.dp(28.0f), r34);
        float r8 = ((AndroidUtilities.dp(7.0f) + r18) + AndroidUtilities.dp(56.0f)) + AndroidUtilities.dp(16.0f);
        RectF r92 = AndroidUtilities.rectTmp;
        r92.set(r73 - AndroidUtilities.dp(24.0f), r8 - AndroidUtilities.dp(4.0f), r73 + AndroidUtilities.dp(24.0f), r8 + AndroidUtilities.dp(4.0f));
        r2.drawRoundRect(r92, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r53 = r53 + 1;
        goto L222
    L224:
        r18 = r18 + getCellHeight(getMeasuredWidth());
        if (this.isSingleCell == false) goto L220;
    L228:
        if (getViewType() != 13) goto L237;
        float r110 = getMeasuredHeight() / 2.0f;
        RectF r416 = AndroidUtilities.rectTmp;
        r416.set(AndroidUtilities.dp(40.0f), r110 - AndroidUtilities.dp(4.0f), getMeasuredWidth() - AndroidUtilities.dp(120.0f), AndroidUtilities.dp(4.0f) + r110);
        r2.drawRoundRect(r416, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.backgroundPaint != null) goto L232;
        Paint r417 = new Paint(1);
        this.backgroundPaint = r417;
        r417.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground));
    L232:
        int r418 = 0;
    L234:
        if (r418 >= 3) goto L400;
        r2.drawCircle(((getMeasuredWidth() - AndroidUtilities.dp(56.0f)) + AndroidUtilities.dp(13.0f)) + (AndroidUtilities.dp(12.0f) * r418), r110, AndroidUtilities.dp(13.0f), this.backgroundPaint);
        r2.drawCircle(((getMeasuredWidth() - AndroidUtilities.dp(56.0f)) + AndroidUtilities.dp(13.0f)) + (AndroidUtilities.dp(12.0f) * r418), r110, AndroidUtilities.dp(12.0f), r34);
        r418 = r418 + 1;
        goto L234
    L237:
        if (getViewType() != 14) goto L239;
    L355:
        int r111 = AndroidUtilities.dp(12.0f);
        int r419 = AndroidUtilities.dp(77.0f);
        int r65 = AndroidUtilities.dp(4.0f);
        float r74 = AndroidUtilities.dp(21.0f);
        float r82 = AndroidUtilities.dp(41.0f);
    L357:
        if (r111 >= getMeasuredWidth()) goto L400;
        if (this.backgroundPaint != null) goto L361;
        this.backgroundPaint = new Paint(r52);
    L361:
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        RectF r102 = AndroidUtilities.rectTmp;
        int r132 = r111 + r419;
        r102.set(AndroidUtilities.dp(4.0f) + r111, AndroidUtilities.dp(4.0f), r132 - AndroidUtilities.dp(4.0f), getMeasuredHeight() - AndroidUtilities.dp(4.0f));
        r2.drawRoundRect(r102, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r34);
        if (getViewType() != 14) goto L364;
        float r103 = AndroidUtilities.dp(8.0f) + r65;
        float r142 = r111;
        float r11 = (AndroidUtilities.dp(22.0f) + r65) + r142;
        int r162 = r52;
        this.rectF.set(r11, r103, r11 + r82, r103 + r74);
        RectF r54 = this.rectF;
        r2.drawRoundRect(r54, r54.height() * 0.5f, this.rectF.height() * 0.5f, this.backgroundPaint);
        float r104 = r103 + (AndroidUtilities.dp(4.0f) + r74);
        float r143 = r142 + (AndroidUtilities.dp(5.0f) + r65);
        this.rectF.set(r143, r104, r143 + r82, r104 + r74);
        RectF r55 = this.rectF;
        r2.drawRoundRect(r55, r55.height() * 0.5f, this.rectF.height() * 0.5f, this.backgroundPaint);
    L367:
        r2.drawCircle(r111 + (r419 / 2), getMeasuredHeight() - AndroidUtilities.dp(20.0f), AndroidUtilities.dp(8.0f), this.backgroundPaint);
        r111 = r132;
        r52 = r162;
        goto L357
    L364:
        r162 = r52;
        if (getViewType() != 17) goto L367;
        float r56 = AndroidUtilities.dp(5.0f);
        float r112 = AndroidUtilities.dp(32.0f);
        float r122 = r111 + ((r419 - r112) / 2.0f);
        r102.set(r122, AndroidUtilities.dp(21.0f), r112 + r122, r14 + AndroidUtilities.dp(32.0f));
        r2.drawRoundRect(r102, r56, r56, this.backgroundPaint);
        goto L367
    L239:
        if (getViewType() == 17) goto L355;
        if (getViewType() != 15) goto L256;
        int r420 = AndroidUtilities.dp(23.0f);
        int r66 = AndroidUtilities.dp(4.0f);
    L245:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(12.0f)) + r420), (AndroidUtilities.dp(8.0f) + r12) + r420, r420, r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(74.0f), AndroidUtilities.dp(12.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(20.0f) + r12);
        checkRtl(this.rectF);
        float r83 = r66;
        r2.drawRoundRect(this.rectF, r83, r83, r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(74.0f), AndroidUtilities.dp(36.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(42.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, r83, r83, r34);
        if (this.memberRequestButtonWidth <= 0.0f) goto L249;
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(73.0f), AndroidUtilities.dp(62.0f) + r12, (this.paddingLeft + AndroidUtilities.dp(73.0f)) + this.memberRequestButtonWidth, AndroidUtilities.dp(94.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, r83, r83, r34);
    L249:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r75 = r19 + 1;
        if (this.isSingleCell == false) goto L254;
        if (r75 >= this.itemsCount) goto L400;
    L254:
        r19 = r75;
        goto L245
    L256:
        if (getViewType() != 16) goto L258;
    L344:
        int r421 = 0;
    L346:
        if (r12 > getMeasuredHeight()) goto L354;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(8.0f)) + r5), AndroidUtilities.dp(24.0f) + r12, AndroidUtilities.dp(18.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(58.0f), AndroidUtilities.dp(20.0f) + r12, getWidth() - AndroidUtilities.dp(53.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r34);
        if (r421 >= 4) goto L350;
        r2.drawCircle(checkRtl((getWidth() - AndroidUtilities.dp(12.0f)) - r5), AndroidUtilities.dp(24.0f) + r12, AndroidUtilities.dp(12.0f), r34);
    L350:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        r421 = r421 + 1;
        if (this.isSingleCell == false) goto L346;
        if (r421 < this.itemsCount) goto L346;
    L354:
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(20.0f) + r12, getWidth() - AndroidUtilities.dp(8.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(36.0f) + r12, getWidth() - AndroidUtilities.dp(53.0f), r12 + AndroidUtilities.dp(44.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), r34);
        goto L400
    L258:
        if (getViewType() == 23) goto L344;
        int r422 = this.viewType;
        if (r422 != 21) goto L271;
    L263:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl(AndroidUtilities.dp(20.0f) + r4), (AndroidUtilities.dp(58.0f) >> 1) + r12, AndroidUtilities.dp(46.0f) >> 1, r34);
        this.rectF.set(AndroidUtilities.dp(74.0f), AndroidUtilities.dp(16.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(74.0f), AndroidUtilities.dp(38.0f) + r12, AndroidUtilities.dp(260.0f), AndroidUtilities.dp(46.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r423 = r19 + 1;
        if (this.isSingleCell == false) goto L269;
        if (r423 >= this.itemsCount) goto L400;
    L269:
        r19 = r423;
        goto L263
    L271:
        if (r422 != 22) goto L281;
    L273:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl(AndroidUtilities.dp(20.0f) + r4), (AndroidUtilities.dp(6.0f) + r12) + r4, AndroidUtilities.dp(48.0f) >> 1, r34);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(16.0f) + r12, AndroidUtilities.dp(140.0f), AndroidUtilities.dp(24.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(38.0f) + r12, AndroidUtilities.dp(260.0f), AndroidUtilities.dp(46.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r424 = r19 + 1;
        if (this.isSingleCell == false) goto L279;
        if (r424 >= this.itemsCount) goto L400;
    L279:
        r19 = r424;
        goto L273
    L281:
        if (r422 != 25) goto L291;
    L283:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(AndroidUtilities.dp(17.0f) + r4, (AndroidUtilities.dp(6.0f) + r12) + r4, AndroidUtilities.dp(38.0f) >> 1, r34);
        this.rectF.set(AndroidUtilities.dp(76.0f), AndroidUtilities.dp(21.0f) + r12, AndroidUtilities.dp(220.0f), AndroidUtilities.dp(29.0f) + r12);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r425 = r19 + 1;
        if (this.isSingleCell == false) goto L289;
        if (r425 >= this.itemsCount) goto L400;
    L289:
        r19 = r425;
        goto L283
    L291:
        if (r422 != 26) goto L305;
    L293:
        if (r12 > getMeasuredHeight()) goto L400;
        int r426 = AndroidUtilities.dp(21.0f) >> 1;
        if (LocaleController.isRTL == false) goto L297;
        int r67 = (getMeasuredWidth() - AndroidUtilities.dp(21.0f)) - r426;
    L298:
        r2.drawCircle(r67, (AndroidUtilities.dp(16.0f) + r12) + r426, r426, r34);
        this.rectF.set(AndroidUtilities.dp(60.0f), AndroidUtilities.dp(21.0f) + r12, AndroidUtilities.dp(190.0f), AndroidUtilities.dp(29.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(16.0f), AndroidUtilities.dp(21.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(62.0f), AndroidUtilities.dp(29.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r427 = r19 + 1;
        if (this.isSingleCell == false) goto L303;
        if (r427 >= this.itemsCount) goto L400;
    L303:
        r19 = r427;
        goto L293
    L297:
        r67 = AndroidUtilities.dp(21.0f) + r426;
        goto L298
    L305:
        if (getViewType() != 28) goto L318;
    L307:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(10.0f)) + r4), (AndroidUtilities.dp(58.0f) >> 1) + r12, AndroidUtilities.dp(24.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(17.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(25.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(39.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(47.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L311;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(20.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L311:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r428 = r19 + 1;
        if (this.isSingleCell == false) goto L316;
        if (r428 >= this.itemsCount) goto L400;
    L316:
        r19 = r428;
        goto L307
    L318:
        if (getViewType() != 31) goto L328;
    L320:
        if (r12 > getMeasuredHeight()) goto L400;
        int r429 = getCellHeight(getMeasuredWidth());
        float r93 = r12;
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(18.0f), ((r429 - AndroidUtilities.dp(22.0f)) / 2.0f) + r93, this.paddingLeft + AndroidUtilities.dp(40.0f), ((AndroidUtilities.dp(22.0f) + r429) / 2.0f) + r93);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(58.0f), ((r429 - AndroidUtilities.dp(8.0f)) / 2.0f) + r93, Math.min(this.paddingLeft + AndroidUtilities.dp(132.0f), getMeasuredWidth() - AndroidUtilities.dp(19.0f)), r93 + ((AndroidUtilities.dp(8.0f) + r429) / 2.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + r429;
        int r430 = r19 + 1;
        if (this.isSingleCell == false) goto L326;
        if (r430 >= this.itemsCount) goto L400;
    L326:
        r19 = r430;
        goto L320
    L328:
        if (getViewType() != 32) goto L338;
    L330:
        if (r12 > getMeasuredHeight()) goto L400;
        int r431 = getCellHeight(getMeasuredWidth());
        float r84 = r12;
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(10.0f), ((r431 - AndroidUtilities.dp(32.0f)) / 2.0f) + r84, this.paddingLeft + AndroidUtilities.dp(42.0f), ((AndroidUtilities.dp(32.0f) + r431) / 2.0f) + r84);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(64.0f), (((r431 - AndroidUtilities.dp(14.0f)) - AndroidUtilities.dp(10.0f)) / 2.0f) + r84, Math.min(this.paddingLeft + AndroidUtilities.dp(118.0f), getMeasuredWidth() - AndroidUtilities.dp(19.0f)), (((r431 - AndroidUtilities.dp(14.0f)) + AndroidUtilities.dp(10.0f)) / 2.0f) + r84);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(64.0f), (((AndroidUtilities.dp(14.0f) + r431) - AndroidUtilities.dp(8.0f)) / 2.0f) + r84, Math.min(this.paddingLeft + AndroidUtilities.dp(144.0f), getMeasuredWidth() - AndroidUtilities.dp(19.0f)), r84 + (((AndroidUtilities.dp(14.0f) + r431) + AndroidUtilities.dp(8.0f)) / 2.0f));
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        r12 = r12 + r431;
        int r432 = r19 + 1;
        if (this.isSingleCell == false) goto L336;
        if (r432 >= this.itemsCount) goto L400;
    L336:
        r19 = r432;
        goto L330
    L338:
        if (getViewType() != 34) goto L340;
    L343:
        this.rectF.set(this.paddingLeft, this.paddingTop, getMeasuredWidth() - this.paddingLeft, getMeasuredHeight() - this.paddingTop);
        this.rectF.inset(AndroidUtilities.dp(3.33f), AndroidUtilities.dp(4.0f));
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), r34);
        goto L400
    L340:
        if (getViewType() == 35) goto L343;
        if (getViewType() != 36) goto L400;
    L369:
        if (r12 > getMeasuredHeight()) goto L400;
        r2.drawCircle(checkRtl((this.paddingLeft + AndroidUtilities.dp(9.0f)) + r4), (AndroidUtilities.dp(64.0f) >> 1) + r12, AndroidUtilities.dp(23.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(17.0f) + r12, this.paddingLeft + AndroidUtilities.dp(260.0f), AndroidUtilities.dp(25.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        this.rectF.set(this.paddingLeft + AndroidUtilities.dp(68.0f), AndroidUtilities.dp(39.0f) + r12, this.paddingLeft + AndroidUtilities.dp(140.0f), AndroidUtilities.dp(47.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
        if (this.showDate == false) goto L373;
        this.rectF.set(getMeasuredWidth() - AndroidUtilities.dp(50.0f), AndroidUtilities.dp(20.0f) + r12, getMeasuredWidth() - AndroidUtilities.dp(12.0f), AndroidUtilities.dp(28.0f) + r12);
        checkRtl(this.rectF);
        r2.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), r34);
    L373:
        r12 = r12 + getCellHeight(getMeasuredWidth());
        int r433 = r19 + 1;
        if (this.isSingleCell == false) goto L378;
        if (r433 >= this.itemsCount) goto L400;
    L378:
        r19 = r433;
    L379:
        int r85 = (getMeasuredWidth() - (AndroidUtilities.dp(2.0f) * (getColumnsCount() - 1))) / getColumnsCount();
        if (getViewType() != 27) goto L382;
        int r94 = (int) (r85 * 1.25f);
    L383:
        int r105 = r12;
        int r113 = 0;
    L385:
        if (r105 >= getMeasuredHeight()) goto L387;
    L388:
        int r123 = 0;
    L390:
        if (r123 >= getColumnsCount()) goto L397;
        if (r113 == 0) goto L393;
    L395:
        Paint r68 = r34;
        r45.drawRect((AndroidUtilities.dp(2.0f) + r85) * r123, r105, r1 + r85, r105 + r94, r68);
    L396:
        r123 = r123 + 1;
        r34 = r68;
        goto L390
    L393:
        if (r123 >= this.skipDrawItemsCount) goto L395;
        r68 = r34;
        goto L396
    L397:
        Paint r69 = r34;
        r105 = r105 + (AndroidUtilities.dp(2.0f) + r94);
        r113 = r113 + 1;
        if (this.isSingleCell == false) goto L402;
        if (r113 >= 2) goto L400;
    L402:
        r34 = r69;
        goto L385
    L387:
        if (this.isSingleCell == false) goto L400;
    L382:
        r94 = r85;
        goto L383
    L23:
        r6 = r7;
        goto L24
    L25:
        r2 = r45;
        goto L26
    L11:
        if (getViewType() == 35) goto L14;
        if (getViewType() != 36) goto L15;
    L5:
        if (getParent() == null) goto L7;
        View r114 = (View) getParent();
        this.globalGradientView.setParentSize(r114.getMeasuredWidth(), r114.getMeasuredHeight(), -getX());
    L7:
        r1 = this.globalGradientView.paint;
        goto L8
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        if (this.isSingleCell == false) goto L17;
        int r0 = this.itemsCount;
        if (r0 > 1) goto L7;
    L10:
        if (r0 > 1) goto L12;
    L15:
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(getCellHeight(View.MeasureSpec.getSize(r5)) + getAdditionalHeight(), TLObject.FLAG_30));
        return;
    L12:
        if (View.MeasureSpec.getSize(r6) <= 0) goto L15;
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r6), getCellHeight(View.MeasureSpec.getSize(r5)) * this.itemsCount) + getAdditionalHeight(), TLObject.FLAG_30));
        return;
    L7:
        if (this.ignoreHeightCheck == false) goto L10;
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec((getCellHeight(View.MeasureSpec.getSize(r5)) * this.itemsCount) + getAdditionalHeight(), TLObject.FLAG_30));
        return;
    L17:
        super.onMeasure(r5, r6);
    }

    public void setColors(int r1, int r2, int r3) {
        this.colorKey1 = r1;
        this.colorKey2 = r2;
        this.colorKey3 = r3;
        invalidate();
    }

    public void setGlobalGradientView(FlickerLoadingView r1) {
        this.globalGradientView = r1;
    }

    public void setIgnoreHeightCheck(boolean r1) {
        this.ignoreHeightCheck = r1;
    }

    public void setIsSingleCell(boolean r1) {
        this.isSingleCell = r1;
    }

    public void setItemsCount(int r1) {
        this.itemsCount = r1;
    }

    public void setMemberRequestButton(boolean r3) {
        TextPaint r0 = new TextPaint(1);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextSize(AndroidUtilities.dp(14.0f));
        float r1 = AndroidUtilities.dp(34.0f);
        if (r3 == false) goto L6;
        int r32 = R.string.AddToChannel;
    L7:
        this.memberRequestButtonWidth = r1 + r0.measureText(LocaleController.getString(r32));
        return;
    L6:
        r32 = R.string.AddToGroup;
        goto L7
    }

    public void setPaddingLeft(int r1) {
        this.paddingLeft = r1;
        invalidate();
    }

    public void setPaddingTop(int r1) {
        this.paddingTop = r1;
        invalidate();
    }

    public void setParentSize(int r1, int r2, float r3) {
        this.parentWidth = r1;
        this.parentHeight = r2;
        this.parentXOffset = r3;
    }

    public void setUseHeaderOffset(boolean r1) {
        this.useHeaderOffset = r1;
    }

    public void setViewType(int r6) {
        this.viewType = r6;
        if (r6 != 11) goto L7;
        Random r62 = new Random();
        this.randomParams = new float[2];
        int r1 = 0;
    L5:
        if (r1 >= 2) goto L7;
        this.randomParams[r1] = Math.abs(r62.nextInt() % 1000) / 1000.0f;
        r1 = r1 + 1;
    L7:
        invalidate();
    }

    public void showDate(boolean r1) {
        this.showDate = r1;
    }

    public void skipDrawItemsCount(int r1) {
        this.skipDrawItemsCount = r1;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        FlickerLoadingView r1 = this.globalGradientView;
        if (r1 == null) goto L6;
        r1.updateColors();
        return;
    L6:
        int r12 = getThemedColor(this.colorKey1);
        int r2 = getThemedColor(this.colorKey2);
        if (this.color1 == r2) goto L9;
    L12:
        this.color0 = r12;
        this.color1 = r2;
        int r3 = this.viewType;
        if (r3 != 34) goto L15;
    L27:
        this.gradientWidth = AndroidUtilities.displaySize.x;
    L29:
        if (this.isSingleCell == true) goto L36;
        int r32 = this.viewType;
        if (r32 == 13) goto L36;
        if (r32 == 14) goto L36;
        if (r32 == 17) goto L36;
        this.gradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.gradientWidth, new int[]{r2, r12, r12, r2}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
    L37:
        this.paint.setShader(this.gradient);
        return;
    L36:
        this.gradient = new LinearGradient(0.0f, 0.0f, this.gradientWidth, 0.0f, new int[]{r2, r12, r12, r2}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        goto L37
    L15:
        if (r3 == 35) goto L27;
        if (r3 == 36) goto L27;
        if (this.isSingleCell == true) goto L26;
        if (r3 == 13) goto L26;
        if (r3 == 14) goto L26;
        if (r3 == 17) goto L26;
        this.gradientWidth = AndroidUtilities.dp(600.0f);
    L26:
        this.gradientWidth = AndroidUtilities.dp(200.0f);
        goto L29
    L9:
        if (this.color0 != r12) goto L12;
    }

    public void updateGradient() {
        FlickerLoadingView r0 = this.globalGradientView;
        if (r0 == null) goto L6;
        r0.updateGradient();
        return;
    L6:
        long r02 = SystemClock.elapsedRealtime();
        long r2 = Math.abs(this.lastUpdateTime - r02);
        if (r2 <= 17) goto L10;
        r2 = 16;
    L10:
        if (r2 >= 4) goto L12;
        r2 = 0;
    L12:
        int r4 = this.parentWidth;
        if (r4 != 0) goto L15;
        r4 = getMeasuredWidth();
    L15:
        int r5 = this.viewType;
        if (r5 != 34) goto L18;
    L21:
        r4 = Math.max(r4, AndroidUtilities.displaySize.x);
    L22:
        int r52 = this.parentHeight;
        if (r52 != 0) goto L25;
        r52 = getMeasuredHeight();
    L25:
        this.lastUpdateTime = r02;
        if (this.isSingleCell == false) goto L28;
    L38:
        int r03 = (int) (this.totalTranslation + ((r2 * r4) / 400.0f));
        this.totalTranslation = r03;
        if (r03 < (r4 * 2)) goto L41;
        this.totalTranslation = (-this.gradientWidth) * 2;
    L41:
        this.matrix.setTranslate(this.totalTranslation + this.parentXOffset, 0.0f);
    L42:
        LinearGradient r04 = this.gradient;
        if (r04 == null) goto L46;
        r04.setLocalMatrix(this.matrix);
        return;
    L46:
        return;
    L28:
        if (this.viewType == 13) goto L38;
        if (getViewType() == 14) goto L38;
        if (getViewType() == 17) goto L38;
        int r05 = (int) (this.totalTranslation + ((r2 * r52) / 400.0f));
        this.totalTranslation = r05;
        if (r05 < (r52 * 2)) goto L37;
        this.totalTranslation = (-this.gradientWidth) * 2;
    L37:
        this.matrix.setTranslate(this.parentXOffset, this.totalTranslation);
        goto L42
    L18:
        if (r5 == 35) goto L21;
        if (r5 != 36) goto L22;
        goto L21
    }
}
