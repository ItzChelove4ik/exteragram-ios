package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/exteragram/messenger/components/CheckBoxRow;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "label", _UrlKt.FRAGMENT_ENCODE_SET, "checked", _UrlKt.FRAGMENT_ENCODE_SET, "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "checkBox", "Lorg/telegram/ui/Components/CheckBox2;", "onCheckedChange", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCheckedChange", "()Lkotlin/jvm/functions/Function1;", "setOnCheckedChange", "(Lkotlin/jvm/functions/Function1;)V", "value", "isChecked", "()Z", "setChecked", "(Z)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public final class CheckBoxRow extends LinearLayout {
    private final CheckBox2 checkBox;
    private Function1<? super Boolean, Unit> onCheckedChange;

    public static void $r8$lambda$3vuXnORUzuzxWlFKMBwpGVji5CU(CheckBoxRow r2, View r3) {
        r2.checkBox.setChecked(!r3.isChecked(), true);
        Function1<? super Boolean, Unit> r32 = r2.onCheckedChange;
        if (r32 == null) goto L6;
        r32.invoke(Boolean.valueOf(r2.checkBox.isChecked()));
        return;
    }

    @JvmOverloads
    public CheckBoxRow(Context r11, CharSequence r12, boolean r13, Theme.ResourcesProvider r14) {
        super(r11);
        CheckBox2 r0 = new CheckBox2(r11, 21, r14);
        r0.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r0.setDrawUnchecked(true);
        r0.setChecked(r13, false);
        r0.setDrawBackgroundAsArc(10);
        this.checkBox = r0;
        setOrientation(0);
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f));
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r14), 18, 18));
        ScaleStateListAnimator.apply(this, 0.05f, 1.2f);
        FrameLayout r132 = new FrameLayout(r11);
        r132.addView(r0, LayoutHelper.createFrame(21, 21.0f, 17, 0.0f, 0.0f, 0.0f, 0.0f));
        addView(r132, LayoutHelper.createLinear(24, 24, 16, 0, 0, 6, 0));
        TextView r133 = new TextView(r11);
        r133.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14));
        r133.setTextSize(1, 14.0f);
        r133.setTypeface(AndroidUtilities.regular());
        r133.setText(r12);
        addView(r133, LayoutHelper.createLinear(-2, -2, 16));
        setOnClickListener(new CheckBoxRow$$ExternalSyntheticLambda0(this));
    }

    public /* synthetic */ CheckBoxRow(Context r1, CharSequence r2, boolean r3, Theme.ResourcesProvider r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 4) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 8) == 0) goto L8;
        r4 = null;
    L8:
        this(r1, r2, r3, r4);
    }

    public final Function1<Boolean, Unit> getOnCheckedChange() {
        return this.onCheckedChange;
    }

    public final boolean isChecked() {
        return this.checkBox.isChecked();
    }

    public final void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public final void setOnCheckedChange(Function1<? super Boolean, Unit> r1) {
        this.onCheckedChange = r1;
    }
}
