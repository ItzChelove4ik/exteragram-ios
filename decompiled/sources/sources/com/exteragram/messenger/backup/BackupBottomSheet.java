package com.exteragram.messenger.backup;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class BackupBottomSheet extends BottomSheet {
    private final int difference;
    private final BaseFragment fragment;

    public static /* synthetic */ void $r8$lambda$15Z87Gdk5tGJZnAgjsii_8sJsS4(BackupBottomSheet r0, File r1, BaseFragment r2, View r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fcBBE281ySmtS3ipIwjWbdFjZcM(BackupBottomSheet r0) {
        r0.lambda$showIfPossible$2();
    }

    /* renamed from: $r8$lambda$qpxUSpu98Mu0S-2CVDwlPPGDGnM, reason: not valid java name */
    public static /* synthetic */ void m1137$r8$lambda$qpxUSpu98Mu0S2CVDwlPPGDGnM(BackupBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public BackupBottomSheet(BaseFragment r19, File r20) {
        super(r19.getParentActivity(), false, r19.getResourceProvider());
        this.fragment = r19;
        Activity r3 = r19.getParentActivity();
        int r4 = PreferencesUtils.getInstance().getDiff(r20);
        this.difference = r4;
        fixNavigationBar();
        FrameLayout r6 = new FrameLayout(r3);
        LinearLayout r7 = new LinearLayout(r3);
        r7.setOrientation(1);
        r6.addView(r7);
        StickerImageView r9 = new StickerImageView(r3, this.currentAccount);
        r9.setStickerPackName("exteraGramPlaceholders");
        r9.setStickerNum(6);
        r9.getImageReceiver().setAutoRepeat(1);
        r9.getImageReceiver().setAutoRepeatCount(1);
        r7.addView(r9, LayoutHelper.createLinear(144, 144, 1, 0, 16, 0, 0));
        TextView r92 = new TextView(r3);
        r92.setGravity(1);
        r92.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r92.setTextSize(1, 20.0f);
        r92.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM));
        r92.setText(LocaleController.getString(R.string.ImportTitle));
        r7.addView(r92, LayoutHelper.createFrame(-1, -2.0f, 0, 40.0f, 20.0f, 40.0f, 0.0f));
        TextView r93 = new TextView(r3);
        r93.setGravity(1);
        r93.setTextSize(1, 14.0f);
        r93.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r93.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("ImportChanges", r4, new Object[0])));
        r7.addView(r93, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 15.0f, 21.0f, 8.0f));
        ButtonWithCounterView r42 = new ButtonWithCounterView(r3, true, this.resourcesProvider);
        r42.setRound();
        r42.setText(LocaleController.getString(R.string.ImportConfirm), false);
        r42.setOnClickListener(new BackupBottomSheet$$ExternalSyntheticLambda0(this, r20, r19));
        r7.addView(r42, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 15.0f, 16.0f, 8.0f));
        ButtonWithCounterView r1 = new ButtonWithCounterView(r3, false, this.resourcesProvider);
        r1.setRound();
        r1.setNeutral();
        r1.setText(LocaleController.getString(R.string.CancelConfirm), false);
        r1.setOnClickListener(new BackupBottomSheet$$ExternalSyntheticLambda1(this));
        r7.addView(r1, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 0.0f, 16.0f, 0.0f));
        ScrollView r12 = new ScrollView(r3);
        r12.addView(r6);
        setCustomView(r12);
    }

    public BackupBottomSheet(BaseFragment r3, MessageObject r4) {
        this(r3, new File(ChatUtils.getInstance().getPathToMessage(r4)));
    }

    private /* synthetic */ void lambda$new$0(File r2, BaseFragment r3, View r4) {
        dismiss();
        PreferencesUtils.getInstance().importSettings(r2, r3.getParentActivity(), r3.getParentLayout());
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.SettingsImported)).show();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$showIfPossible$2() {
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.SameSettings)).show();
    }

    public void showIfPossible() {
        if (this.difference <= 0) goto L6;
        show();
        return;
    L6:
        AndroidUtilities.runOnUIThread(new BackupBottomSheet$$ExternalSyntheticLambda2(this));
    }
}
