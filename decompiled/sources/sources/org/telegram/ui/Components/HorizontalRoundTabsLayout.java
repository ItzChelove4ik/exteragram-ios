package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.UItem;

/* loaded from: classes7.dex */
public class HorizontalRoundTabsLayout extends HorizontalScrollView {
    private static final RectF tmpRect = null;
    private boolean accent;
    private final Paint bgPaint;
    private final Path clipPath;
    private final Path clipPath2;
    public final LinearLayout linearLayout;
    private final Theme.ResourcesProvider resourcesProvider;
    private int selectedIndex;
    private final AnimatedFloat selectorEndX;
    private final AnimatedFloat selectorStartX;
    private final TextPaint textPaint;

    public static final class Factory extends UItem.UItemFactory<HorizontalRoundTabsLayout> {
        public static /* synthetic */ void $r8$lambda$fia7JWtwHAnhE1ryYsQYOVrfwjE(UItem r0, HorizontalRoundTabsLayout r1, int r2) {
            ((Utilities.Callback2) r0.object2).run(Integer.valueOf(r2), r1);
        }

        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            HorizontalRoundTabsLayout r12 = (HorizontalRoundTabsLayout) r1;
            r12.setTabs((ArrayList) r2.object, new HorizontalRoundTabsLayout$Factory$$ExternalSyntheticLambda0(r2, r12));
            r12.setSelectedIndex(r2.intValue, false);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public HorizontalRoundTabsLayout createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new HorizontalRoundTabsLayout(r1, r5);
        }
    }

    public static class RoundTabView extends View {
        private Text text;

        public RoundTabView(Context r1) {
            super(r1);
            setDrawingCacheEnabled(false);
        }

        @Override // android.view.View
        public void draw(Canvas r4) {
            super.draw(r4);
            this.text.draw(r4, (getMeasuredWidth() - this.text.getWidth()) / 2.0f, getMeasuredHeight() / 2.0f);
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((Math.round(this.text.getWidth()) + getPaddingLeft()) + getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(Math.max((Math.round(this.text.getHeight()) + getPaddingTop()) + getPaddingBottom(), AndroidUtilities.dp(26.0f)), TLObject.FLAG_30));
        }

        public void setText(Text r1) {
            this.text = r1;
        }
    }

    /* renamed from: $r8$lambda$1g-pvD3sewMqrC8f3u_szPuDqwM, reason: not valid java name */
    public static /* synthetic */ void m10913$r8$lambda$1gpvD3sewMqrC8f3u_szPuDqwM(HorizontalRoundTabsLayout r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$It_oNPoRiweXMmboX6uJHylvTHg(HorizontalRoundTabsLayout r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$wLppqeoxz1TjlZh65NqDBvpeTBU(HorizontalRoundTabsLayout r0, int r1, MessagesStorage.IntCallback r2, View r3) {
        r0.lambda$setTabs$2(r1, r2, r3);
    }

    static {
        tmpRect = new RectF();
    }

    public HorizontalRoundTabsLayout(Context r5, Theme.ResourcesProvider r6) {
        super(r5);
        this.bgPaint = new Paint(1);
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        this.clipPath = new Path();
        this.clipPath2 = new Path();
        this.resourcesProvider = r6;
        LinearLayout r62 = new LinearLayout(r5);
        this.linearLayout = r62;
        r62.setLayerType(0, null);
        r62.setOrientation(0);
        addView(r62, LayoutHelper.createScroll(-1, -1, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES));
        r0.setTextSize(AndroidUtilities.dp(13.0f));
        r0.setTypeface(AndroidUtilities.bold());
        AnimatedFloat r52 = new AnimatedFloat(new HorizontalRoundTabsLayout$$ExternalSyntheticLambda0(this));
        this.selectorStartX = r52;
        r52.setDuration(180);
        AnimatedFloat r53 = new AnimatedFloat(new HorizontalRoundTabsLayout$$ExternalSyntheticLambda1(this));
        this.selectorEndX = r53;
        r53.setDuration(180);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    private /* synthetic */ void lambda$new$0() {
        invalidate();
        this.linearLayout.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= this.linearLayout.getChildCount()) goto L6;
        this.linearLayout.getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$new$1() {
        invalidate();
        this.linearLayout.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= this.linearLayout.getChildCount()) goto L6;
        this.linearLayout.getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$setTabs$2(int r4, MessagesStorage.IntCallback r5, View r6) {
        this.selectedIndex = r4;
        this.selectorStartX.set(r6.getLeft(), false);
        this.selectorEndX.set(r6.getRight(), false);
        r5.run(r4);
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r12) {
        RectF r0 = tmpRect;
        r0.set(this.selectorStartX.getValue(), 0.0f, this.selectorEndX.getValue(), getMeasuredHeight());
        this.clipPath.rewind();
        Path r1 = this.clipPath;
        float r3 = AndroidUtilities.dp(13.0f);
        float r4 = AndroidUtilities.dp(13.0f);
        Path.Direction r10 = Path.Direction.CW;
        r1.addRoundRect(r0, r3, r4, r10);
        this.clipPath.close();
        this.clipPath2.rewind();
        this.clipPath2.addRect(0.0f, 0.0f, this.linearLayout.getMeasuredWidth(), getMeasuredHeight(), r10);
        this.clipPath2.addRoundRect(r0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), Path.Direction.CCW);
        this.clipPath2.close();
        Paint r02 = this.bgPaint;
        if (this.accent == false) goto L5;
        int r13 = Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), 0.1f);
    L6:
        r02.setColor(r13);
        r12.drawPath(this.clipPath, this.bgPaint);
        this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider));
        r12.save();
        r12.clipPath(this.clipPath2);
        super.dispatchDraw(r12);
        r12.restore();
        TextPaint r03 = this.textPaint;
        if (this.accent == false) goto L9;
        int r14 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
    L10:
        r03.setColor(r14);
        r12.save();
        r12.clipPath(this.clipPath);
        int r04 = 0;
    L12:
        if (r04 >= this.linearLayout.getChildCount()) goto L20;
        View r15 = this.linearLayout.getChildAt(r04);
        RectF r2 = tmpRect;
        if (r2.right < r15.getLeft()) goto L19;
        if (r2.left > r15.getRight()) goto L19;
        r12.save();
        r12.translate(r15.getLeft(), r15.getTop());
        r15.draw(r12);
        r12.restore();
    L19:
        r04 = r04 + 1;
        goto L12
    L20:
        r12.restore();
        return;
    L9:
        r14 = Theme.getColor(Theme.key_chats_nameArchived, this.resourcesProvider);
        goto L10
    L5:
        r13 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider) & 520093695;
        goto L6
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        setSelectedIndex(this.selectedIndex, false);
    }

    public void setAccent(boolean r1) {
        this.accent = r1;
    }

    public void setSelectedIndex(int r4, boolean r5) {
        this.selectedIndex = r4;
        this.selectorStartX.set(this.linearLayout.getChildAt(r4).getLeft(), !r5);
        this.selectorEndX.set(this.linearLayout.getChildAt(r4).getRight(), !r5);
    }

    public void setTabs(ArrayList<CharSequence> r8, MessagesStorage.IntCallback r9) {
        this.linearLayout.removeAllViews();
        int r0 = 0;
    L4:
        if (r0 >= r8.size()) goto L9;
        CharSequence r1 = r8.get(r0);
        RoundTabView r2 = new RoundTabView(getContext());
        r2.setOnClickListener(new HorizontalRoundTabsLayout$$ExternalSyntheticLambda2(this, r0, r9));
        r2.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(5.0f));
        LinearLayout.LayoutParams r3 = LayoutHelper.createLinear(-2, -2);
        if (r0 >= (r8.size() - 1)) goto L8;
        r3.rightMargin = AndroidUtilities.dp(4.0f);
    L8:
        r2.setText(new Text(r1, this.textPaint));
        this.linearLayout.addView(r2, r3);
        r0 = r0 + 1;
        goto L4
    }
}
