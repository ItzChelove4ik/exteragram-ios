package org.telegram.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;

/* loaded from: classes6.dex */
public class ChangeNameActivity extends BaseFragment {
    private View doneButton;
    private EditTextBoldCursor firstNameField;
    private OutlineTextContainerView firstNameFieldContainer;
    private EditTextBoldCursor lastNameField;
    private OutlineTextContainerView lastNameFieldContainer;
    private Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.ChangeNameActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChangeNameActivity this$0;

        public AnonymousClass1(ChangeNameActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 == 1) goto L9;
            return;
        L9:
            if (ChangeNameActivity.m7137$$Nest$fgetfirstNameField(this.this$0).getText().length() == 0) goto L13;
            ChangeNameActivity.m7139$$Nest$msaveName(this.this$0);
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChangeNameActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextBoldCursor {
        final /* synthetic */ ChangeNameActivity this$0;

        public AnonymousClass2(ChangeNameActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public Theme.ResourcesProvider getResourcesProvider() {
            return ChangeNameActivity.m7138$$Nest$fgetresourcesProvider(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.ChangeNameActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        final /* synthetic */ ChangeNameActivity this$0;

        public AnonymousClass3(ChangeNameActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public Theme.ResourcesProvider getResourcesProvider() {
            return ChangeNameActivity.m7138$$Nest$fgetresourcesProvider(this.this$0);
        }
    }

    /* renamed from: $r8$lambda$5XRdQ0AZLDihKvnfPZso6KR-eOQ, reason: not valid java name */
    public static /* synthetic */ void m7133$r8$lambda$5XRdQ0AZLDihKvnfPZso6KReOQ(ChangeNameActivity r0) {
        r0.lambda$onTransitionAnimationEnd$6();
    }

    public static /* synthetic */ void $r8$lambda$YlgqWn0WW5WvC0PDJR9FBhzhHWU(ChangeNameActivity r0, View r1, boolean r2) {
        r0.lambda$createView$3(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$gcnxyxS84kCDz_EqMdnOhnbbsJo(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$kjZYU-WV-yUL1StTiQZWCwNrTQU, reason: not valid java name */
    public static /* synthetic */ boolean m7134$r8$lambda$kjZYUWVyUL1StTiQZWCwNrTQU(ChangeNameActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nSyX5xWXH1OTBnxb6IoHcHSWBns(ChangeNameActivity r0, View r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$pS9F0lhhmHWlSakvmJ-EiwSRYtQ, reason: not valid java name */
    public static /* synthetic */ void m7135$r8$lambda$pS9F0lhhmHWlSakvmJEiwSRYtQ(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$zxUXIZqKV78mE2nc11neM-biNB0, reason: not valid java name */
    public static /* synthetic */ boolean m7136$r8$lambda$zxUXIZqKV78mE2nc11neMbiNB0(ChangeNameActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$4(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetfirstNameField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m7137$$Nest$fgetfirstNameField(ChangeNameActivity r0) {
        return r0.firstNameField;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m7138$$Nest$fgetresourcesProvider(ChangeNameActivity r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$msaveName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7139$$Nest$msaveName(ChangeNameActivity r0) {
        r0.saveName();
    }

    public ChangeNameActivity(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    private /* synthetic */ void lambda$createView$1(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.firstNameFieldContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L9:
        this.doneButton.performClick();
        return true;
    L5:
        if (r2 == 6) goto L9;
        return false;
    }

    private /* synthetic */ void lambda$createView$3(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.lastNameFieldContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$4(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        this.doneButton.performClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$onTransitionAnimationEnd$6() {
        EditTextBoldCursor r0 = this.firstNameField;
        if (r0 == null) goto L6;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.firstNameField);
        return;
    }

    private void saveName() {
        TLRPC.User r0 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.lastNameField.getText() != null) goto L7;
        return;
    L7:
        if (this.firstNameField.getText() == null) goto L25;
        String r1 = this.firstNameField.getText().toString();
        String r2 = this.lastNameField.getText().toString();
        String r3 = r0.first_name;
        if (r3 != null) goto L12;
    L18:
        TL_account.updateProfile r32 = new TL_account.updateProfile();
        r32.flags = 3;
        r32.first_name = r1;
        r0.first_name = r1;
        r32.last_name = r2;
        r0.last_name = r2;
        TLRPC.User r02 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        if (r02 == null) goto L21;
        r02.first_name = r32.first_name;
        r02.last_name = r32.last_name;
    L21:
        UserConfig.getInstance(this.currentAccount).saveConfig(true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new ChangeNameActivity$$ExternalSyntheticLambda6());
        return;
    L12:
        if (r3.equals(r1) == false) goto L18;
        String r33 = r0.last_name;
        if (r33 == null) goto L18;
        if (r33.equals(r2) == false) goto L18;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r26) {
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_avatar_actionBarSelectorBlue, this.resourcesProvider), false);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.EditName));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).getClientUserId()));
        if (r2 != null) goto L5;
        r2 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
    L5:
        LinearLayout r4 = new LinearLayout(r26);
        this.fragmentView = r4;
        r4.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        ((LinearLayout) this.fragmentView).setOrientation(1);
        this.fragmentView.setOnTouchListener(new ChangeNameActivity$$ExternalSyntheticLambda1());
        OutlineTextContainerView r5 = new OutlineTextContainerView(r26);
        this.firstNameFieldContainer = r5;
        r5.setText(LocaleController.getString(R.string.FirstName));
        r4.addView(this.firstNameFieldContainer, LayoutHelper.createLinear(-1, -2, 1, 24, 24, 24, 0));
        AnonymousClass2 r52 = new AnonymousClass2(this, r26);
        this.firstNameField = r52;
        r52.setTextSize(1, 18.0f);
        EditTextBoldCursor r53 = this.firstNameField;
        int r8 = Theme.key_windowBackgroundWhiteBlackText;
        r53.setTextColor(Theme.getColor(r8, this.resourcesProvider));
        this.firstNameField.setBackground(null);
        this.firstNameField.setMaxLines(1);
        this.firstNameField.setLines(1);
        this.firstNameField.setSingleLine(true);
        this.firstNameField.setInputType(49152);
        this.firstNameField.setImeOptions(5);
        EditTextBoldCursor r54 = this.firstNameField;
        int r12 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r54.setCursorColor(Theme.getColor(r12, this.resourcesProvider));
        this.firstNameField.setCursorWidth(1.5f);
        this.firstNameField.setCursorSize(AndroidUtilities.dp(20.0f));
        EditTextBoldCursor r55 = this.firstNameField;
        if (LocaleController.isRTL == false) goto L8;
        int r15 = 5;
    L9:
        r55.setGravity(r15);
        this.firstNameField.setOnFocusChangeListener(new ChangeNameActivity$$ExternalSyntheticLambda2(this));
        int r56 = AndroidUtilities.dp(16.0f);
        this.firstNameField.setPadding(r56, r56, r56, r56);
        this.firstNameFieldContainer.addView(this.firstNameField, LayoutHelper.createFrame(-1, -2.0f));
        this.firstNameFieldContainer.attachEditText(this.firstNameField);
        this.firstNameField.setOnEditorActionListener(new ChangeNameActivity$$ExternalSyntheticLambda3(this));
        OutlineTextContainerView r11 = new OutlineTextContainerView(r26);
        this.lastNameFieldContainer = r11;
        r11.setText(LocaleController.getString(R.string.LastName));
        r4.addView(this.lastNameFieldContainer, LayoutHelper.createLinear(-1, -2, 1, 24, 24, 24, 0));
        AnonymousClass3 r42 = new AnonymousClass3(this, r26);
        this.lastNameField = r42;
        r42.setTextSize(1, 18.0f);
        this.lastNameField.setTextColor(Theme.getColor(r8, this.resourcesProvider));
        this.lastNameField.setBackground(null);
        this.lastNameField.setMaxLines(1);
        this.lastNameField.setLines(1);
        this.lastNameField.setSingleLine(true);
        this.lastNameField.setInputType(49152);
        this.lastNameField.setImeOptions(5);
        this.lastNameField.setCursorColor(Theme.getColor(r12, this.resourcesProvider));
        this.lastNameField.setCursorWidth(1.5f);
        this.lastNameField.setCursorSize(AndroidUtilities.dp(20.0f));
        EditTextBoldCursor r1 = this.lastNameField;
        if (LocaleController.isRTL == false) goto L12;
        int r112 = 5;
    L13:
        r1.setGravity(r112);
        this.lastNameField.setOnFocusChangeListener(new ChangeNameActivity$$ExternalSyntheticLambda4(this));
        this.lastNameField.setPadding(r56, r56, r56, r56);
        this.lastNameFieldContainer.addView(this.lastNameField, LayoutHelper.createFrame(-1, -2.0f));
        this.lastNameFieldContainer.attachEditText(this.lastNameField);
        this.lastNameField.setOnEditorActionListener(new ChangeNameActivity$$ExternalSyntheticLambda5(this));
        if (r2 == null) goto L17;
        this.firstNameField.setText(r2.first_name);
        EditTextBoldCursor r13 = this.firstNameField;
        r13.setSelection(r13.length());
        this.lastNameField.setText(r2.last_name);
    L17:
        return this.fragmentView;
    L12:
        r112 = 3;
        goto L13
    L8:
        r15 = 3;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        EditTextBoldCursor r8 = this.firstNameField;
        int r9 = ThemeDescription.FLAG_TEXTCOLOR;
        int r17 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r8, r9, null, null, null, null, r17));
        EditTextBoldCursor r92 = this.firstNameField;
        int r10 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r15 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r92, r10, null, null, null, null, r15));
        EditTextBoldCursor r102 = this.firstNameField;
        int r11 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r16 = Theme.key_windowBackgroundWhiteInputField;
        r1.add(new ThemeDescription(r102, r11, null, null, null, null, r16));
        EditTextBoldCursor r2 = this.firstNameField;
        int r36 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r41 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r1.add(new ThemeDescription(r2, r36, null, null, null, null, r41));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r41));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == true) goto L6;
        this.firstNameField.requestFocus();
        AndroidUtilities.showKeyboard(this.firstNameField);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r3, boolean r4) {
        if (r3 == false) goto L5;
        AndroidUtilities.runOnUIThread(new ChangeNameActivity$$ExternalSyntheticLambda0(this), 100);
        return;
    }
}
