package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

/* loaded from: classes6.dex */
public class ChatLoadingCell extends FrameLayout {
    private int backgroundHeight;
    private FrameLayout frameLayout;
    private RadialProgressView progressBar;
    private Theme.ResourcesProvider resourcesProvider;
    private float viewTop;

    /* renamed from: org.telegram.ui.Cells.ChatLoadingCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private final RectF rect;
        final /* synthetic */ ChatLoadingCell this$0;

        public AnonymousClass1(ChatLoadingCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.this$0.applyServiceShaderMatrix();
            r7.drawRoundRect(this.rect, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), ChatLoadingCell.m6632$$Nest$mgetThemedPaint(this.this$0, "paintChatActionBackground"));
            if (this.this$0.hasGradientService() == false) goto L5;
            r7.drawRoundRect(this.rect, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), ChatLoadingCell.m6632$$Nest$mgetThemedPaint(this.this$0, "paintChatActionBackgroundDarken"));
        L5:
            super.dispatchDraw(r7);
        }
    }

    /* renamed from: -$$Nest$mgetThemedPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6632$$Nest$mgetThemedPaint(ChatLoadingCell r0, String r1) {
        return r0.getThemedPaint(r1);
    }

    public ChatLoadingCell(Context r3, View r4, Theme.ResourcesProvider r5) {
        super(r3);
        this.resourcesProvider = r5;
        AnonymousClass1 r42 = new AnonymousClass1(this, r3);
        this.frameLayout = r42;
        r42.setWillNotDraw(false);
        addView(this.frameLayout, LayoutHelper.createFrame(36, 36, 17));
        RadialProgressView r43 = new RadialProgressView(r3, r5);
        this.progressBar = r43;
        r43.setSize(AndroidUtilities.dp(28.0f));
        this.progressBar.setProgressColor(getThemedColor(Theme.key_chat_serviceText));
        this.frameLayout.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
    }

    private void applyServiceShaderMatrix(int r1, int r2, float r3, float r4) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L6;
        r0.applyServiceShaderMatrix(r1, r2, r3, r4);
        return;
    L6:
        Theme.applyServiceShaderMatrix(r1, r2, r3, r4);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public void applyServiceShaderMatrix() {
        applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, getX(), this.viewTop);
    }

    public boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.hasGradientService();
    L7:
        return Theme.hasGradientService();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
    }

    public void setProgressVisible(boolean r1) {
        FrameLayout r0 = this.frameLayout;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 4;
        goto L6
    }

    public void setVisiblePart(float r2, int r3) {
        if (this.viewTop == r2) goto L5;
        invalidate();
    L5:
        this.viewTop = r2;
        this.backgroundHeight = r3;
    }
}
