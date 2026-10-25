package com.exteragram.messenger.plugins.ui.components;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ScaleStateListAnimator;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eJ\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0014J0\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0014J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\tH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "itemSpacing", _UrlKt.FRAGMENT_ENCODE_SET, "lineSpacing", "setRequirements", _UrlKt.FRAGMENT_ENCODE_SET, Common.ASSET_REQUIREMENTS, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onLayout", "changed", _UrlKt.FRAGMENT_ENCODE_SET, "l", "t", "r", "b", "getThemedColor", PluginsConstants.Settings.KEY, "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginRequirementsView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginRequirementsView.kt\ncom/exteragram/messenger/plugins/ui/components/PluginRequirementsView\n+ 2 View.kt\nandroidx/core/view/ViewKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,156:1\n297#2:157\n297#2:158\n1#3:159\n*S KotlinDebug\n*F\n+ 1 PluginRequirementsView.kt\ncom/exteragram/messenger/plugins/ui/components/PluginRequirementsView\n*L\n83#1:157\n116#1:158\n*E\n"})
/* loaded from: classes.dex */
public final class PluginRequirementsView extends ViewGroup {
    public static final Companion Companion = null;
    private static final Regex EXTRAS_PATTERN = null;
    private static final Regex NAME_PATTERN = null;
    private final int itemSpacing;
    private final int lineSpacing;
    private final Theme.ResourcesProvider resourcesProvider;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginRequirementsView$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NAME_PATTERN", "Lkotlin/text/Regex;", "EXTRAS_PATTERN", "requirementName", _UrlKt.FRAGMENT_ENCODE_SET, "requirement", "requirementUrl", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final String requirementName(String r3) {
            String r2 = StringsKt.trim(r3).toString();
            MatchResult r32 = PluginRequirementsView.access$getNAME_PATTERN$cp().matchAt(r2, 0);
            if (r32 == null) goto L15;
            String r33 = r32.getValue();
            if (r33 == null) goto L16;
            MatchResult r22 = PluginRequirementsView.access$getEXTRAS_PATTERN$cp().matchAt(r2, r33.length());
            if (r22 == null) goto L10;
            String r23 = r22.getValue();
        L11:
            if (r23 != null) goto L14;
            r23 = _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            return r33.concat(r23);
        L10:
            r23 = null;
            goto L11
        L16:
            return r2;
        L15:
            return r2;
        }

        @JvmStatic
        public final String requirementUrl(String r4) {
            String r3 = StringsKt.trim(r4).toString();
            if (StringsKt.contains$default(r3, "://", false, 2, null) == false) goto L5;
            return null;
        L5:
            MatchResult r32 = PluginRequirementsView.access$getNAME_PATTERN$cp().matchAt(r3, 0);
            if (r32 == null) goto L12;
            String r33 = r32.getValue();
            if (r33 == null) goto L12;
            return "https://pypi.org/project/" + r33 + "/";
        L12:
            return null;
        }
    }

    /* renamed from: $r8$lambda$AK1KYC4qaDW5pgXJL-eqVBXRMjA, reason: not valid java name */
    public static void m1612$r8$lambda$AK1KYC4qaDW5pgXJLeqVBXRMjA(String r0, TextView r1, View r2) {
        String r02 = Companion.requirementUrl(r0);
        if (r02 == null) goto L6;
        Browser.openUrl(r1.getContext(), r02);
        return;
    }

    static {
        Companion = new Companion(null);
        NAME_PATTERN = new Regex("[A-Za-z0-9][A-Za-z0-9._-]*");
        EXTRAS_PATTERN = new Regex("\\[[A-Za-z0-9,._\\s-]*]");
    }

    @JvmOverloads
    public PluginRequirementsView(Context r3) {
        this(r3, null, 2, null);
    }

    @JvmOverloads
    public PluginRequirementsView(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.resourcesProvider = r2;
        this.itemSpacing = AndroidUtilities.dp(4.0f);
        this.lineSpacing = AndroidUtilities.dp(4.0f);
    }

    public /* synthetic */ PluginRequirementsView(Context r1, Theme.ResourcesProvider r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    public static final /* synthetic */ Regex access$getEXTRAS_PATTERN$cp() {
        return EXTRAS_PATTERN;
    }

    public static final /* synthetic */ Regex access$getNAME_PATTERN$cp() {
        return NAME_PATTERN;
    }

    private final int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @JvmStatic
    public static final String requirementName(String r1) {
        return Companion.requirementName(r1);
    }

    @JvmStatic
    public static final String requirementUrl(String r1) {
        return Companion.requirementUrl(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        int r112 = r11 - r9;
        int r82 = getPaddingLeft();
        int r92 = getPaddingTop();
        int r102 = getChildCount();
        int r0 = 0;
        int r1 = 0;
    L3:
        if (r0 >= r102) goto L12;
        View r2 = getChildAt(r0);
        if (r2.getVisibility() == 8) goto L11;
        int r3 = r2.getMeasuredWidth();
        int r4 = r2.getMeasuredHeight();
        if ((r82 + r3) <= (r112 - getPaddingRight())) goto L10;
        r82 = getPaddingLeft();
        r92 = r92 + (r1 + this.lineSpacing);
        r1 = 0;
    L10:
        r2.layout(r82, r92, r82 + r3, r92 + r4);
        r82 = r82 + (r3 + this.itemSpacing);
        r1 = Math.max(r1, r4);
    L11:
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onMeasure(int r14, int r15) {
        int r0 = View.MeasureSpec.getSize(r14);
        int r1 = View.MeasureSpec.getMode(r14);
        int r2 = (r0 - getPaddingLeft()) - getPaddingRight();
        int r3 = getPaddingTop();
        int r4 = getChildCount();
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
    L3:
        if (r6 >= r4) goto L12;
        View r10 = getChildAt(r6);
        if (r10.getVisibility() == 8) goto L11;
        measureChild(r10, r14, r15);
        int r11 = r10.getMeasuredWidth();
        int r102 = r10.getMeasuredHeight();
        if ((r9 + r11) <= r2) goto L10;
        r3 = r3 + (r7 + this.lineSpacing);
        r7 = 0;
        r9 = 0;
    L10:
        r9 = r9 + (r11 + this.itemSpacing);
        r7 = Math.max(r7, r102);
        r8 = Math.max(r8, r9 - this.itemSpacing);
    L11:
        r6 = r6 + 1;
        goto L3
    L12:
        int r32 = r3 + (r7 + getPaddingBottom());
        if (r1 == 1073741824) goto L16;
        r0 = (r8 + getPaddingRight()) + getPaddingLeft();
    L16:
        setMeasuredDimension(r0, r32);
    }

    public final void setRequirements(List<String> r7) {
        removeAllViews();
        List<String> r0 = r7;
        if (r0 != null) goto L5;
    L13:
        setVisibility(8);
        return;
    L5:
        if (r0.isEmpty() == true) goto L13;
        setVisibility(0);
        Iterator<String> r72 = r7.iterator();
    L9:
        if (r72.hasNext() == false) goto L11;
        String r02 = r72.next();
        TextView r1 = new TextView(getContext());
        r1.setText(r02);
        r1.setTextSize(1, 12.0f);
        int r2 = getThemedColor(Theme.key_featuredStickers_addButton);
        r1.setTextColor(r2);
        r1.setTypeface(AndroidUtilities.regular());
        r1.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), ColorUtils.setAlphaComponent(r2, 30)));
        r1.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f));
        ScaleStateListAnimator.apply(r1);
        r1.setOnClickListener(new PluginRequirementsView$$ExternalSyntheticLambda0(r02, r1));
        addView(r1);
        goto L9
    L11:
        requestLayout();
    }
}
