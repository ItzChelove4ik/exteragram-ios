package com.exteragram.messenger.plugins.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProgressButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0012J \u0010\u001d\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010J\u000e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0016H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "textView", "Lorg/telegram/ui/ActionBar/SimpleTextView;", "subtitleView", "actionButton", "Lorg/telegram/ui/Components/ProgressButton;", "info", "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;", "delegate", "Lorg/telegram/messenger/Utilities$Callback;", "needDivider", _UrlKt.FRAGMENT_ENCODE_SET, "onMeasure", _UrlKt.FRAGMENT_ENCODE_SET, "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setNeedDivider", PluginsConstants.Settings.TYPE_DIVIDER, "set", "setLoading", "loading", "getThemedColor", PluginsConstants.Settings.KEY, "Companion", "Factory", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ViewConstructor"})
@SourceDebugExtension({"SMAP\nRequiredPluginCell.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequiredPluginCell.kt\ncom/exteragram/messenger/plugins/ui/components/RequiredPluginCell\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,295:1\n1#2:296\n*E\n"})
/* loaded from: classes4.dex */
public final class RequiredPluginCell extends LinearLayout {
    private static final int BUTTON_GAP_DP = 12;
    private static final int BUTTON_HEIGHT_DP = 28;
    private static final int BUTTON_RIGHT_PADDING = 18;
    public static final Companion Companion = null;
    private static final int HEIGHT_DP = 64;
    private static final int LEFT_PADDING = 21;
    private static final float LINE_GAP = 4.0f;
    private final ProgressButton actionButton;
    private Utilities.Callback<RequiredPluginInfo> delegate;
    private RequiredPluginInfo info;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SimpleTextView subtitleView;
    private final SimpleTextView textView;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "HEIGHT_DP", _UrlKt.FRAGMENT_ENCODE_SET, "LEFT_PADDING", "LINE_GAP", _UrlKt.FRAGMENT_ENCODE_SET, "BUTTON_RIGHT_PADDING", "BUTTON_HEIGHT_DP", "BUTTON_GAP_DP", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J4\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J4\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\b\u001a\u0004\u0018\u00010\u0019H\u0016J\u0019\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0096\u0002J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0016¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Factory;", "Lorg/telegram/ui/Components/UItem$UItemFactory;", "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell;", "<init>", "()V", "createView", "context", "Landroid/content/Context;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "currentAccount", _UrlKt.FRAGMENT_ENCODE_SET, "classGuid", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "bindView", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/view/View;", PluginsConstants.Settings.ITEM, "Lorg/telegram/ui/Components/UItem;", PluginsConstants.Settings.TYPE_DIVIDER, _UrlKt.FRAGMENT_ENCODE_SET, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "equals", "a", "b", "contentsEquals", "isClickable", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory extends UItem.UItemFactory<RequiredPluginCell> {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\tH\u0007¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginCell$Factory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "asRequirement", "Lorg/telegram/ui/Components/UItem;", "info", "Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;", "delegate", "Lorg/telegram/messenger/Utilities$Callback;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final UItem asRequirement(RequiredPluginInfo r2, Utilities.Callback<RequiredPluginInfo> r3) {
                UItem r1 = UItem.ofFactory(Factory.class);
                r1.id = r2.id.hashCode();
                r1.text = r2.contentKey();
                r1.object = r2;
                r1.object2 = r3;
                return r1;
            }
        }

        static {
            Companion = new Companion(null);
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @JvmStatic
        public static final UItem asRequirement(RequiredPluginInfo r1, Utilities.Callback<RequiredPluginInfo> r2) {
            return Companion.asRequirement(r1, r2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof RequiredPluginCell) == false) goto L13;
            RequiredPluginCell r12 = (RequiredPluginCell) r1;
            Object r0 = r2.object;
            Utilities.Callback<RequiredPluginInfo> r52 = null;
            if ((r0 instanceof RequiredPluginInfo) == false) goto L7;
            RequiredPluginInfo r02 = (RequiredPluginInfo) r0;
        L8:
            Object r22 = r2.object2;
            if ((r22 instanceof Utilities.Callback) == false) goto L11;
            r52 = (Utilities.Callback) r22;
        L11:
            r12.set(r02, r52);
            r12.setNeedDivider(r3);
            return;
        L7:
            r02 = null;
            goto L8
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r2, UItem r3) {
            if (r2.id == r3.id) goto L5;
            return false;
        L5:
            if (TextUtils.equals(r2.text, r3.text) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RequiredPluginCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new RequiredPluginCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static void $r8$lambda$uedXg2VNTw6uGtLndaaVv098w8A(RequiredPluginCell r0, View r1) {
        RequiredPluginInfo r12 = r0.info;
        if (r12 == null) goto L8;
        Utilities.Callback<RequiredPluginInfo> r02 = r0.delegate;
        if (r02 == null) goto L9;
        r02.run(r12);
        return;
    L9:
        return;
    }

    static {
        Companion = new Companion(null);
    }

    public RequiredPluginCell(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        this.resourcesProvider = r14;
        setOrientation(0);
        setGravity(16);
        setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(18.0f), 0);
        SimpleTextView r1 = new SimpleTextView(r13);
        r1.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r1.setTextSize(16);
        int r3 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        r1.setGravity(r2);
        r1.setImportantForAccessibility(2);
        this.textView = r1;
        SimpleTextView r5 = new SimpleTextView(r13);
        r5.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r5.setTextSize(13);
        if (LocaleController.isRTL == false) goto L9;
        r3 = 5;
    L9:
        r5.setGravity(r3);
        r5.setImportantForAccessibility(2);
        this.subtitleView = r5;
        LinearLayout r22 = new LinearLayout(r13);
        r22.setOrientation(1);
        r22.addView(r1, LayoutHelper.createLinear(-1, -2));
        r22.addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, LINE_GAP, 0.0f, 0.0f));
        ProgressButton r12 = new ProgressButton(r13);
        this.actionButton = r12;
        ScaleStateListAnimator.apply(r12, 0.05f, 1.5f);
        r12.setOnClickListener(new RequiredPluginCell$$ExternalSyntheticLambda0(this));
        ViewGroup.LayoutParams r132 = LayoutHelper.createLinear(0, -2, 1.0f, 16);
        boolean r142 = LocaleController.isRTL;
        if (r142 == false) goto L12;
        float r7 = 0.0f;
    L13:
        if (r142 == false) goto L15;
        float r9 = 12.0f;
    L16:
        ViewGroup.LayoutParams r143 = LayoutHelper.createLinear(-2, 28, 16, r7, 0.0f, r9, 0.0f);
        if (LocaleController.isRTL == false) goto L20;
        addView(r12, r143);
        addView(r22, r132);
        return;
    L20:
        addView(r22, r132);
        addView(r12, r143);
        return;
    L15:
        r9 = 0.0f;
        goto L16
    L12:
        r7 = 12.0f;
        goto L13
    L5:
        r2 = 3;
        goto L6
    }

    private final int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L11;
        Paint r02 = r0.getPaint("paintDivider");
        if (r02 == null) goto L11;
    L10:
        Paint r6 = r02;
        if (LocaleController.isRTL == false) goto L16;
        float r03 = 0.0f;
    L15:
        float r2 = r03;
        float r3 = getMeasuredHeight() - 1;
        int r04 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L20;
        int r1 = AndroidUtilities.dp(21.0f);
    L21:
        r8.drawLine(r2, r3, r04 - r1, getMeasuredHeight() - 1, r6);
        return;
    L20:
        r1 = 0;
        goto L21
    L16:
        r03 = AndroidUtilities.dp(21.0f);
    L11:
        r02 = Theme.dividerPaint;
        goto L10
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public final void set(RequiredPluginInfo r6, Utilities.Callback<RequiredPluginInfo> r7) {
        if (r6 != null) goto L4;
        return;
    L4:
        this.info = r6;
        this.delegate = r7;
        this.textView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setText(r6.title());
        SimpleTextView r72 = this.subtitleView;
        if (r6.isSatisfied() == false) goto L7;
        int r0 = Theme.key_windowBackgroundWhiteGrayText;
    L8:
        r72.setTextColor(getThemedColor(r0));
        this.subtitleView.setText(r6.subtitle());
        int r73 = getThemedColor(Theme.key_featuredStickers_addButton);
        this.actionButton.setText(r6.actionText());
        boolean r62 = r6.isSatisfied();
        ProgressButton r02 = this.actionButton;
        if (r62 == false) goto L11;
        r02.setTextColor(r73);
        this.actionButton.setBackground(null);
        this.actionButton.setClickable(false);
        this.actionButton.setFocusable(false);
    L12:
        requestLayout();
        return;
    L11:
        r02.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.actionButton.setProgressColor(getThemedColor(Theme.key_featuredStickers_buttonProgress));
        this.actionButton.setBackground(Theme.AdaptiveRipple.createRect(r73, getThemedColor(Theme.key_featuredStickers_addButtonPressed), new float[]{14.0f}));
        this.actionButton.setClickable(true);
        this.actionButton.setFocusable(true);
        goto L12
    L7:
        r0 = Theme.key_text_RedRegular;
        goto L8
    }

    public final void setLoading(boolean r2) {
        this.actionButton.setDrawProgress(r2, true);
    }

    public final void setNeedDivider(boolean r1) {
        this.needDivider = r1;
        setWillNotDraw(!r1);
        invalidate();
    }
}
