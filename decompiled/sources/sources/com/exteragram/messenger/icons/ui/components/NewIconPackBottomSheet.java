package com.exteragram.messenger.icons.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.icons.ui.IconPacksEditorActivity;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.HashMap;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineEditText;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class NewIconPackBottomSheet extends BottomSheet {
    private OutlineEditText authorField;
    private ButtonWithCounterView doneButton;
    private OutlineEditText nameField;
    private final IconPack packToEdit;
    private final BaseFragment parentFragment;
    private OutlineEditText versionField;

    /* renamed from: com.exteragram.messenger.icons.ui.components.NewIconPackBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends IconPacksEditorActivity {
        final /* synthetic */ NewIconPackBottomSheet this$0;

        public AnonymousClass1(NewIconPackBottomSheet r1, IconPack r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            LaunchActivity r0 = LaunchActivity.instance;
            if (r0 == null) goto L5;
            IconPickerController.setActive(r0, true);
        L5:
            super.onBecomeFullyVisible();
        }
    }

    public static /* synthetic */ void $r8$lambda$8Mc7FlHDruD07uIc1DNmABDPb6A(NewIconPackBottomSheet r0) {
        r0.lambda$showStorageError$8();
    }

    public static /* synthetic */ boolean $r8$lambda$BYtrHHfOrmFfWMznjkqv5h6qepQ(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$C63674BOoPMkDhEgGBkqiBkBeS4(NewIconPackBottomSheet r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$JvZzduwCC40Ht9unCDw82yTGqXo(NewIconPackBottomSheet r0, String r1, String r2, String r3) {
        r0.lambda$doOnDone$7(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$LDFWLnQnTHzAianobhavjPHwWdE(NewIconPackBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$Tq9kV7g5zlReF6g5001RqZU1zhk(NewIconPackBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Y6pR0UkF8zocumbBTlNdVHZtKrY(NewIconPackBottomSheet r0, String r1, IconPack r2) {
        r0.lambda$doOnDone$6(r1, r2);
    }

    /* renamed from: $r8$lambda$aqiBQu7kbmuThiu9571-4P8xaCc, reason: not valid java name */
    public static /* synthetic */ boolean m1459$r8$lambda$aqiBQu7kbmuThiu95714P8xaCc(NewIconPackBottomSheet r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$3(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$djmibVI-BsoL7L_4HL42UmKBesE, reason: not valid java name */
    public static /* synthetic */ CharSequence m1460$r8$lambda$djmibVIBsoL7L_4HL42UmKBesE(NewIconPackBottomSheet r0, CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
        return r0.lambda$createView$1(r1, r2, r3, r4, r5, r6);
    }

    public NewIconPackBottomSheet(BaseFragment r2, Context r3) {
        this(r2, r3, null);
    }

    public NewIconPackBottomSheet(BaseFragment r2, Context r3, IconPack r4) {
        super(r3, true);
        this.packToEdit = r4;
        fixNavigationBar();
        this.waitingKeyboard = true;
        this.smoothKeyboardAnimationEnabled = true;
        this.parentFragment = r2;
        setCustomView(createView(getContext()));
        if (r4 != null) goto L5;
        int r22 = R.string.NewIconPack;
    L6:
        setTitle(LocaleController.getString(r22), true);
        return;
    L5:
        r22 = R.string.EditIconPackInfo;
        goto L6
    }

    private void doOnDone() {
        if (this.nameField.getEditText().length() != 0) goto L6;
        VibratorUtils.vibrate();
        AndroidUtilities.shakeView(this.nameField);
        return;
    L6:
        String r0 = this.nameField.getEditText().getText().toString().trim();
        String r1 = this.authorField.getEditText().getText().toString().trim();
        String r2 = this.versionField.getEditText().getText().toString().trim();
        if (r2.isEmpty() == false) goto L10;
        r2 = "1.0";
    L10:
        if (r0.isEmpty() == false) goto L13;
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.NameCannotBeEmpty)).show();
        return;
    L13:
        Utilities.globalQueue.postRunnable(new NewIconPackBottomSheet$$ExternalSyntheticLambda6(this, r0, r1, r2));
    }

    private /* synthetic */ CharSequence lambda$createView$1(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
        int r42 = 64 - (r4.length() - (r6 - r5));
        int r32 = r3 - r2;
        if (r42 >= r32) goto L6;
        VibratorUtils.vibrate();
        AndroidUtilities.shakeView(this.nameField);
    L6:
        if (r42 > 0) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        if (r42 < r32) goto L11;
        return null;
    L11:
        int r43 = r42 + r2;
        if (Character.isHighSurrogate(r1.charAt(r43 - 1)) == false) goto L17;
        r43 = r43 - 1;
        if (r43 != r2) goto L17;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        return r1.subSequence(r2, r43);
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.authorField.requestFocus();
        this.authorField.getEditText().setSelection(this.authorField.getEditText().length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$createView$3(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.versionField.requestFocus();
        this.versionField.getEditText().setSelection(this.versionField.getEditText().length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$createView$4(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        this.doneButton.callOnClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        doOnDone();
    }

    private /* synthetic */ void lambda$doOnDone$6(String r2, IconPack r3) {
        IconManager.INSTANCE.setActiveCustomPack(r2);
        dismiss();
        ExteraConfig.setEditingIconPackId(r3.getId());
        this.parentFragment.presentFragment(new AnonymousClass1(this, r3));
    }

    private /* synthetic */ void lambda$doOnDone$7(String r10, String r11, String r12) {
        if (this.packToEdit == null) goto L13;
        String r2 = this.packToEdit.getId();
        if (r11.isEmpty() == false) goto L8;
        r11 = LocaleController.getString(R.string.PluginNoAuthor);
    L8:
        if (IconManager.INSTANCE.saveIconPackMetadata(new IconPack(r2, r10, r11, r12, this.packToEdit.getIcons(), this.packToEdit.getPreinstalledMap(), null)) == true) goto L11;
        showStorageError();
        return;
    L11:
        AndroidUtilities.runOnUIThread(new NewIconPackBottomSheet$$ExternalSyntheticLambda7(this));
        return;
    L13:
        String r1 = "custom." + UUID.randomUUID().toString();
        if (r11.isEmpty() == false) goto L16;
        r11 = LocaleController.getString(R.string.PluginNoAuthor);
    L16:
        IconPack r0 = new IconPack(r1, r10, r11, r12, new HashMap(), null, null);
        if (IconManager.INSTANCE.saveIconPackMetadata(r0) == true) goto L20;
        showStorageError();
        return;
    L20:
        AndroidUtilities.runOnUIThread(new NewIconPackBottomSheet$$ExternalSyntheticLambda8(this, r1, r0));
    }

    private /* synthetic */ void lambda$showStorageError$8() {
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.IconPackErrorStorage)).show();
    }

    private void showStorageError() {
        AndroidUtilities.runOnUIThread(new NewIconPackBottomSheet$$ExternalSyntheticLambda9(this));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public View createView(Context r18) {
        ScrollView r2 = new ScrollView(r18);
        LinearLayout r3 = new LinearLayout(r18);
        r3.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        r3.setOrientation(1);
        r2.addView(r3, LayoutHelper.createScroll(-1, -2, 51));
        r3.setOnTouchListener(new NewIconPackBottomSheet$$ExternalSyntheticLambda0());
        FrameLayout r5 = new FrameLayout(r18);
        r3.addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        OutlineEditText r7 = new OutlineEditText(r18);
        this.nameField = r7;
        r7.getEditText().setInputType(49152);
        this.nameField.getEditText().setFilters(new InputFilter[]{new NewIconPackBottomSheet$$ExternalSyntheticLambda1(this)});
        this.nameField.getEditText().setImeOptions(5);
        this.nameField.setHint(LocaleController.getString(R.string.PackName));
        if (this.packToEdit == null) goto L5;
        this.nameField.getEditText().setText(this.packToEdit.getName());
    L5:
        r5.addView(this.nameField, LayoutHelper.createFrame(-1, 58.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        this.nameField.getEditText().setOnEditorActionListener(new NewIconPackBottomSheet$$ExternalSyntheticLambda2(this));
        OutlineEditText r4 = new OutlineEditText(r18);
        this.authorField = r4;
        r4.setBackground(null);
        this.authorField.getEditText().setInputType(49152);
        this.authorField.getEditText().setImeOptions(5);
        this.authorField.setHint(LocaleController.getString(R.string.AuthorNameOptional));
        if (this.packToEdit == null) goto L8;
        this.authorField.getEditText().setText(this.packToEdit.getAuthor());
    L8:
        r5.addView(this.authorField, LayoutHelper.createFrame(-1, 58.0f, 51, 0.0f, 68.0f, 0.0f, 0.0f));
        this.authorField.getEditText().setOnEditorActionListener(new NewIconPackBottomSheet$$ExternalSyntheticLambda3(this));
        OutlineEditText r42 = new OutlineEditText(r18);
        this.versionField = r42;
        r42.setBackground(null);
        this.versionField.getEditText().setInputType(49152);
        this.versionField.getEditText().setImeOptions(6);
        this.versionField.setHint(LocaleController.getString(R.string.Version));
        IconPack r43 = this.packToEdit;
        OutlineEditText r72 = this.versionField;
        if (r43 == null) goto L11;
        r72.getEditText().setText(this.packToEdit.getVersion());
    L12:
        r5.addView(this.versionField, LayoutHelper.createFrame(-1, 58.0f, 51, 0.0f, 136.0f, 0.0f, 0.0f));
        this.versionField.getEditText().setOnEditorActionListener(new NewIconPackBottomSheet$$ExternalSyntheticLambda4(this));
        ButtonWithCounterView r44 = new ButtonWithCounterView(r18, this.resourcesProvider);
        this.doneButton = r44;
        r44.setRound();
        ButtonWithCounterView r1 = this.doneButton;
        if (this.packToEdit != null) goto L15;
        int r45 = R.string.Create;
    L16:
        r1.setText(LocaleController.getString(r45), false);
        this.doneButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.doneButton.setOnClickListener(new NewIconPackBottomSheet$$ExternalSyntheticLambda5(this));
        r3.addView(this.doneButton, LayoutHelper.createLinear(-1, 48, 0.0f, 16.0f, 0.0f, 16.0f));
        return r2;
    L15:
        r45 = R.string.Save;
        goto L16
    L11:
        r72.getEditText().setText("1.0");
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        OutlineEditText r0 = this.nameField;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getEditText() == null) goto L9;
        this.nameField.getEditText().requestFocus();
        this.nameField.getEditText().setSelection(this.nameField.getEditText().length());
        AndroidUtilities.showKeyboard(this.nameField.getEditText());
        return;
    }
}
