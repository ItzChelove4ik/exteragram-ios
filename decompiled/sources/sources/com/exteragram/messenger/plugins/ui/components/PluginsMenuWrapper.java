package com.exteragram.messenger.plugins.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.MenuItemRecord;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eB7\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u0010J\u0016\u0010\u0017\u001a\u00020\u00182\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006!"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginsMenuWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "swipeBackLayout", "Lorg/telegram/ui/Components/PopupSwipeBackLayout;", "existingItems", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;", "menuType", _UrlKt.FRAGMENT_ENCODE_SET, "contextData", _UrlKt.FRAGMENT_ENCODE_SET, "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Lorg/telegram/ui/Components/PopupSwipeBackLayout;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "type", "(Lorg/telegram/ui/Components/PopupSwipeBackLayout;Ljava/lang/String;Ljava/util/Map;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "menuItemsContainer", "Landroid/widget/LinearLayout;", "getMenuItemsContainer", "()Landroid/widget/LinearLayout;", "swipeBack", "getSwipeBack", "rebuildMenu", _UrlKt.FRAGMENT_ENCODE_SET, "createScrollView", "Landroid/widget/ScrollView;", "context", "Landroid/content/Context;", "createGap", "Landroid/view/View;", "closeMenu", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public class PluginsMenuWrapper {
    public static final Companion Companion = null;
    public static final int GAP_ITEM_HEIGHT = 8;
    public static final int ITEM_HEIGHT = 48;
    public static final int SUBTITLE_ITEM_HEIGHT = 56;
    private final Map<String, Object> contextData;
    private final LinearLayout menuItemsContainer;
    private final String menuType;
    private final Theme.ResourcesProvider resourcesProvider;
    private final LinearLayout swipeBack;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginsMenuWrapper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ITEM_HEIGHT", _UrlKt.FRAGMENT_ENCODE_SET, "SUBTITLE_ITEM_HEIGHT", "GAP_ITEM_HEIGHT", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"com/exteragram/messenger/plugins/ui/components/PluginsMenuWrapper$createScrollView$1", "Landroid/widget/ScrollView;", "alphaFloat", "Lorg/telegram/ui/Components/AnimatedFloat;", "topShadowDrawable", "Landroid/graphics/drawable/Drawable;", "wasCanScrollVertically", _UrlKt.FRAGMENT_ENCODE_SET, "onNestedScroll", _UrlKt.FRAGMENT_ENCODE_SET, "target", "Landroid/view/View;", "dxConsumed", _UrlKt.FRAGMENT_ENCODE_SET, "dyConsumed", "dxUnconsumed", "dyUnconsumed", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.components.PluginsMenuWrapper$createScrollView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ScrollView {
        final /* synthetic */ Context $context;
        private final AnimatedFloat alphaFloat;
        private Drawable topShadowDrawable;
        private boolean wasCanScrollVertically;

        public AnonymousClass1(Context r4) {
            this.$context = r4;
            super(r4);
            this.alphaFloat = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedFloat r0 = this.alphaFloat;
            if (canScrollVertically(-1) == false) goto L5;
            float r1 = 1.0f;
        L6:
            float r02 = r0.set(r1) * 0.5f;
            if (r02 > 0.0f) goto L10;
            return;
        L10:
            if (this.topShadowDrawable != null) goto L12;
            this.topShadowDrawable = ContextCompat.getDrawable(this.$context, R.drawable.header_shadow);
        L12:
            Drawable r12 = this.topShadowDrawable;
            if (r12 == null) goto L16;
            r12.setBounds(0, getScrollY(), getWidth(), getScrollY() + r12.getIntrinsicHeight());
            r12.setAlpha((int) (255.0f * r02));
            r12.draw(r6);
            return;
        L16:
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
            super.onNestedScroll(r1, r2, r3, r4, r5);
            boolean r12 = canScrollVertically(-1);
            if (this.wasCanScrollVertically == r12) goto L6;
            invalidate();
            this.wasCanScrollVertically = r12;
            return;
        }
    }

    /* renamed from: $r8$lambda$XEMT-hYu43swUbGxk9Em701P8vM, reason: not valid java name */
    public static void m1613$r8$lambda$XEMThYu43swUbGxk9Em701P8vM(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static void $r8$lambda$tg0JpuvOFeaM8GYGV_b7zAyhkxs(PluginsMenuWrapper r0, MenuItemRecord r1, View r2) {
        r0.closeMenu();
        r1.executeClick(r0.contextData);
    }

    static {
        Companion = new Companion(null);
    }

    public PluginsMenuWrapper(PopupSwipeBackLayout r7, String r8, Map<String, ? extends Object> r9, Theme.ResourcesProvider r10) {
        this(r7, null, r8, r9, r10);
    }

    public PluginsMenuWrapper(PopupSwipeBackLayout r7, List<MenuItemRecord> r8, String r9, Map<String, ? extends Object> r10, Theme.ResourcesProvider r11) {
        this.menuType = r9;
        this.contextData = r10;
        this.resourcesProvider = r11;
        Context r92 = r7.getContext();
        LinearLayout r102 = new LinearLayout(r92);
        r102.setOrientation(1);
        this.swipeBack = r102;
        ActionBarMenuSubItem r1 = new ActionBarMenuSubItem(r92, true, false, r11);
        r1.setItemHeight(44);
        r1.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
        AnimatedEmojiSpan.TextViewEmojis r112 = r1.getTextView();
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 0;
    L7:
        if (LocaleController.isRTL == false) goto L9;
        int r4 = AndroidUtilities.dp(40.0f);
    L10:
        r112.setPadding(r3, 0, r4, 0);
        r1.setOnClickListener(new PluginsMenuWrapper$$ExternalSyntheticLambda1(r7));
        r102.addView(r1, LayoutHelper.createLinear(-1, -2));
        LinearLayout r72 = new LinearLayout(r92);
        r72.setOrientation(1);
        this.menuItemsContainer = r72;
        ScrollView r93 = createScrollView(r92);
        r93.addView(r72);
        r102.addView(r93);
        rebuildMenu(r8);
        return;
    L9:
        r4 = 0;
        goto L10
    L5:
        r3 = AndroidUtilities.dp(40.0f);
        goto L7
    }

    private final View createGap() {
        ActionBarPopupWindow.GapView r0 = new ActionBarPopupWindow.GapView(this.menuItemsContainer.getContext(), this.resourcesProvider);
        r0.setDividerVisible(false);
        return r0;
    }

    private final ScrollView createScrollView(Context r1) {
        return new AnonymousClass1(r1);
    }

    public void closeMenu() {
    }

    public final LinearLayout getMenuItemsContainer() {
        return this.menuItemsContainer;
    }

    public final LinearLayout getSwipeBack() {
        return this.swipeBack;
    }

    public final void rebuildMenu(List<MenuItemRecord> r10) {
        this.menuItemsContainer.removeAllViews();
        if (r10 != null) goto L5;
        r10 = PluginsController.Companion.getInstance().getMenuItemsForLocation(this.menuType, this.contextData);
    L5:
        Context r0 = this.menuItemsContainer.getContext();
        this.menuItemsContainer.addView(createGap(), LayoutHelper.createLinear(-1, 8));
        Iterator<MenuItemRecord> r102 = r10.iterator();
        int r2 = 0;
    L7:
        if (r102.hasNext() == false) goto L18;
        MenuItemRecord r3 = r102.next();
        String r5 = r3.getText();
        if (r5 == null) goto L7;
        if (r5.length() == 0) goto L7;
        ActionBarMenuSubItem r52 = new ActionBarMenuSubItem(r0, false, false, this.resourcesProvider);
        r52.setTextAndIcon(r3.getText(), r3.getIconResId());
        r52.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r52.setOnClickListener(new PluginsMenuWrapper$$ExternalSyntheticLambda0(this, r3));
        if (TextUtils.isEmpty(r3.getSubtext()) == true) goto L16;
        r52.setSubtext(r3.getSubtext());
        int r6 = 56;
        r52.setItemHeight(56);
    L17:
        this.menuItemsContainer.addView(r52, LayoutHelper.createLinear(-1, r6));
        r2 = r2 + r6;
        r52.setTag(r3);
        goto L7
    L16:
        r6 = 48;
        goto L17
    L18:
        int r103 = AndroidUtilities.dp(436.0f);
        Object r9 = this.menuItemsContainer.getParent();
        LinearLayout.LayoutParams r1 = null;
        if ((r9 instanceof View) == false) goto L21;
        View r92 = (View) r9;
    L22:
        if (r92 != null) goto L24;
        return;
    L24:
        ViewGroup.LayoutParams r02 = r92.getLayoutParams();
        if ((r02 instanceof LinearLayout.LayoutParams) == false) goto L28;
        r1 = (LinearLayout.LayoutParams) r02;
    L28:
        if (r1 != null) goto L30;
        r1 = LayoutHelper.createLinear(-1, -2);
    L30:
        if (r2 > r103) goto L32;
    L34:
        r103 = -2;
    L35:
        r1.height = r103;
        r92.setLayoutParams(r1);
        return;
    L32:
        if (Math.abs(r2 - r103) <= 112) goto L34;
    L21:
        r92 = null;
        goto L22
    }
}
