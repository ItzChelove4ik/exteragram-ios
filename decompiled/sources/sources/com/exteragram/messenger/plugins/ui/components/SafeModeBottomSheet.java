package com.exteragram.messenger.plugins.ui.components;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/SafeModeBottomSheet;", "Lorg/telegram/ui/ActionBar/BottomSheet;", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeModeBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeModeBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/SafeModeBottomSheet\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n1#2:116\n*E\n"})
/* loaded from: classes4.dex */
public final class SafeModeBottomSheet extends BottomSheet {
    public static void $r8$lambda$OB8JQSwdEyFfmR8jRXIGBMuo7io(SafeModeBottomSheet r1, View r2) {
        r1.dismiss();
        SharedPreferences.Editor r12 = ExteraConfig.getEditor();
        ExteraConfig.setPluginsSafeMode(false);
        Unit r0 = Unit.INSTANCE;
        r12.putBoolean("pluginsSafeMode", false).apply();
        PluginsController.Companion.getInstance().restart(false);
    }

    public SafeModeBottomSheet(BaseFragment r14) {
        super(r14.getParentActivity(), false, r14.getResourceProvider());
        Activity r142 = r14.getParentActivity();
        fixNavigationBar();
        FrameLayout r0 = new FrameLayout(r142);
        LinearLayout r1 = new LinearLayout(r142);
        r1.setOrientation(1);
        r0.addView(r1);
        StickerImageView r4 = new StickerImageView(r142, this.currentAccount);
        r4.setStickerPackName("exteraGramPlaceholders");
        r4.setStickerNum(10);
        r4.getImageReceiver().setAutoRepeat(1);
        r1.addView(r4, LayoutHelper.createLinear(144, 144, 1, 0.0f, 16.0f, 0.0f, 0.0f));
        TextView r42 = new TextView(r142);
        r42.setGravity(1);
        r42.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r42.setTextSize(1, 20.0f);
        r42.setTypeface(AndroidUtilities.bold());
        r42.setText(LocaleController.getString(R.string.PluginsSafeMode));
        r1.addView(r42, LayoutHelper.createFrame(-1, -2.0f, 0, 40.0f, 20.0f, 40.0f, 0.0f));
        TextView r43 = new TextView(r142);
        r43.setGravity(1);
        r43.setTypeface(AndroidUtilities.regular());
        r43.setTextSize(1, 14.0f);
        r43.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r43.setText(LocaleController.getString(R.string.PluginsSafeModeInfo));
        r1.addView(r43, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 8.0f, 21.0f, 0.0f));
        ButtonWithCounterView r44 = new ButtonWithCounterView(r142, true, this.resourcesProvider);
        r44.setRound();
        r44.setText(LocaleController.getString(R.string.Disable), false);
        r44.setOnClickListener(new SafeModeBottomSheet$$ExternalSyntheticLambda0(this));
        r1.addView(r44, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 28.0f, 16.0f, 16.0f));
        setCustomView(r0);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public /* bridge */ /* synthetic */ void setLastVisible(boolean r1) {
        super.setLastVisible(r1);
    }
}
