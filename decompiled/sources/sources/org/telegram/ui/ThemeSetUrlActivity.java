package org.telegram.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.ThemePreviewMessagesCell;
import org.telegram.ui.Cells.ThemesHorizontalListCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class ThemeSetUrlActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private TextInfoPrivacyCell checkInfoCell;
    private int checkReqId;
    private Runnable checkRunnable;
    private TextSettingsCell createCell;
    private TextInfoPrivacyCell createInfoCell;
    private boolean creatingNewTheme;
    private View divider;
    private View doneButton;
    private EditTextBoldCursor editText;
    private HeaderCell headerCell;
    private TextInfoPrivacyCell helpInfoCell;
    private boolean ignoreCheck;
    private TLRPC.TL_theme info;
    private CharSequence infoText;
    private String lastCheckName;
    private boolean lastNameAvailable;
    private LinearLayout linearLayoutTypeContainer;
    private EditTextBoldCursor linkField;
    private ThemePreviewMessagesCell messagesCell;
    private EditTextBoldCursor nameField;
    private AlertDialog progressDialog;
    private Theme.ThemeAccent themeAccent;
    private Theme.ThemeInfo themeInfo;

    /* renamed from: org.telegram.ui.ThemeSetUrlActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ThemeSetUrlActivity this$0;

        public AnonymousClass1(ThemeSetUrlActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 != 1) goto L10;
            ThemeSetUrlActivity.m20861$$Nest$msaveTheme(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ThemeSetUrlActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ ThemeSetUrlActivity this$0;

        public AnonymousClass2(ThemeSetUrlActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.ThemeSetUrlActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ ThemeSetUrlActivity this$0;

        public AnonymousClass3(ThemeSetUrlActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            if (ThemeSetUrlActivity.m20855$$Nest$fgetcreatingNewTheme(this.this$0) == false) goto L5;
            return;
        L5:
            int r52 = ThemeSetUrlActivity.m20859$$Nest$fgetlinkField(this.this$0).length();
            ThemeSetUrlActivity r0 = this.this$0;
            if (r52 <= 0) goto L12;
            String r53 = "https://" + r0.getMessagesController().linkPrefix + "/addtheme/" + ThemeSetUrlActivity.m20859$$Nest$fgetlinkField(this.this$0).getText();
            String r02 = LocaleController.formatString("ThemeHelpLink", R.string.ThemeHelpLink, new Object[]{r53});
            int r1 = r02.indexOf(r53);
            SpannableStringBuilder r2 = new SpannableStringBuilder(r02);
            if (r1 < 0) goto L10;
            r2.setSpan(new LinkSpan(this.this$0, r53), r1, r53.length() + r1, 33);
        L10:
            ThemeSetUrlActivity.m20856$$Nest$fgethelpInfoCell(this.this$0).setText(TextUtils.concat(new CharSequence[]{ThemeSetUrlActivity.m20858$$Nest$fgetinfoText(this.this$0), "\n\n", r2}));
            return;
        L12:
            ThemeSetUrlActivity.m20856$$Nest$fgethelpInfoCell(r0).setText(ThemeSetUrlActivity.m20858$$Nest$fgetinfoText(this.this$0));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (ThemeSetUrlActivity.m20857$$Nest$fgetignoreCheck(this.this$0) == false) goto L5;
            return;
        L5:
            ThemeSetUrlActivity r0 = this.this$0;
            ThemeSetUrlActivity.m20860$$Nest$mcheckUrl(r0, ThemeSetUrlActivity.m20859$$Nest$fgetlinkField(r0).getText().toString(), false);
        }
    }

    /* renamed from: org.telegram.ui.ThemeSetUrlActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ThemesHorizontalListCell {
        final /* synthetic */ ThemeSetUrlActivity this$0;
        final /* synthetic */ BottomSheet.Builder val$builder;

        public AnonymousClass4(ThemeSetUrlActivity r1, Context r2, BaseFragment r3, int r4, ArrayList r5, ArrayList r6, BottomSheet.Builder r7) {
            this.this$0 = r1;
            this.val$builder = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Cells.ThemesHorizontalListCell
        public void updateRows() {
            this.val$builder.getDismissRunnable().run();
        }
    }

    public static class LinkMovementMethodMy extends LinkMovementMethod {
        private LinkMovementMethodMy() {
        }

        public /* synthetic */ LinkMovementMethodMy(ThemeSetUrlActivityIA r1) {
            this();
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView r2, Spannable r3, MotionEvent r4) {
            boolean r1 = super.onTouchEvent(r2, r3, r4);     // Catch: Exception -> L10
            if (r4.getAction() != 1) goto L5;
        L8:
            Selection.removeSelection(r3);     // Catch: Exception -> L10
            return r1;
        L5:
            if (r4.getAction() == 3) goto L8;
            return r1;
        L10:
            e = move-exception;
            FileLog.e(e);
            return false;
        }
    }

    public class LinkSpan extends ClickableSpan {
        final /* synthetic */ ThemeSetUrlActivity this$0;
        private String url;

        public LinkSpan(ThemeSetUrlActivity r1, String r2) {
            this.this$0 = r1;
            this.url = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.url));     // Catch: Exception -> L6
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L11;
            BulletinFactory.createCopyLinkBulletin(this.this$0).show();     // Catch: Exception -> L6
            return;
        L11:
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: $r8$lambda$-0FOCggOLKH4gIBacDSw1g68P0s, reason: not valid java name */
    public static /* synthetic */ boolean m20850$r8$lambda$0FOCggOLKH4gIBacDSw1g68P0s(ThemeSetUrlActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$-22HLOBl8-tkkN9Nhjz8yChk370, reason: not valid java name */
    public static /* synthetic */ void m20851$r8$lambda$22HLOBl8tkkN9Nhjz8yChk370(ThemeSetUrlActivity r0, String r1, TLRPC.TL_error r2) {
        r0.lambda$checkUrl$6(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$9F2QPjxo3d7hGo8cmeoLMEQrv2k(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ boolean $r8$lambda$DWn6sZJY_TsiSUvdfL784DYXAkY(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$JX_tGvMSsaGrtkyPg00ipO7ljAs(ThemeSetUrlActivity r0, TLRPC.TL_theme r1) {
        r0.lambda$saveTheme$10(r1);
    }

    /* renamed from: $r8$lambda$LXgT_wP-VPAMcRFpYW0PgPXyOUY, reason: not valid java name */
    public static /* synthetic */ void m20852$r8$lambda$LXgT_wPVPAMcRFpYW0PgPXyOUY(DialogInterface r0) {
    }

    /* renamed from: $r8$lambda$LsdpuVxP_-4OqfC1rgO7O4Pi5ok, reason: not valid java name */
    public static /* synthetic */ void m20853$r8$lambda$LsdpuVxP_4OqfC1rgO7O4Pi5ok(ThemeSetUrlActivity r0, int r1, DialogInterface r2) {
        r0.lambda$saveTheme$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SYWw9PWKsJs547yQTVP1crI7ro0(ThemeSetUrlActivity r0, String r1) {
        r0.lambda$checkUrl$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$U54OVUasmjggMQKfti7knrC6rj0(ThemeSetUrlActivity r0, TL_account.updateTheme r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$saveTheme$12(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$bpaTEfimMi7JwCA-85429ymtGEg, reason: not valid java name */
    public static /* synthetic */ void m20854$r8$lambda$bpaTEfimMi7JwCA85429ymtGEg(ThemeSetUrlActivity r0, TLRPC.TL_error r1, TL_account.updateTheme r2) {
        r0.lambda$saveTheme$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$g1e0FhwlJa1Wpq5LW4gcmRz9ONU(ThemeSetUrlActivity r0, Context r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iP0Y7eLk3sHgR_ED7s2qGDd2DR4(ThemeSetUrlActivity r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$checkUrl$7(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$l3nJtjBqMroz5gY69FZraIop4f8(ThemeSetUrlActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nkUxuD7DsyiMNGeUPVtWyZrt_rE(ThemeSetUrlActivity r0, View r1, boolean r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcreatingNewTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20855$$Nest$fgetcreatingNewTheme(ThemeSetUrlActivity r0) {
        return r0.creatingNewTheme;
    }

    /* renamed from: -$$Nest$fgethelpInfoCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextInfoPrivacyCell m20856$$Nest$fgethelpInfoCell(ThemeSetUrlActivity r0) {
        return r0.helpInfoCell;
    }

    /* renamed from: -$$Nest$fgetignoreCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20857$$Nest$fgetignoreCheck(ThemeSetUrlActivity r0) {
        return r0.ignoreCheck;
    }

    /* renamed from: -$$Nest$fgetinfoText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m20858$$Nest$fgetinfoText(ThemeSetUrlActivity r0) {
        return r0.infoText;
    }

    /* renamed from: -$$Nest$fgetlinkField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m20859$$Nest$fgetlinkField(ThemeSetUrlActivity r0) {
        return r0.linkField;
    }

    /* renamed from: -$$Nest$mcheckUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20860$$Nest$mcheckUrl(ThemeSetUrlActivity r0, String r1, boolean r2) {
        return r0.checkUrl(r1, r2);
    }

    /* renamed from: -$$Nest$msaveTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20861$$Nest$msaveTheme(ThemeSetUrlActivity r0) {
        r0.saveTheme();
    }

    public ThemeSetUrlActivity(Theme.ThemeInfo r2, Theme.ThemeAccent r3, boolean r4) {
        this.themeInfo = r2;
        this.themeAccent = r3;
        if (r3 == null) goto L5;
        TLRPC.TL_theme r0 = r3.info;
    L6:
        this.info = r0;
        if (r3 == null) goto L9;
        int r22 = r3.account;
    L10:
        this.currentAccount = r22;
        this.creatingNewTheme = r4;
        return;
    L9:
        r22 = r2.account;
        goto L10
    L5:
        r0 = r2.info;
        goto L6
    }

    private boolean checkUrl(String r7, boolean r8) {
        Runnable r0 = this.checkRunnable;
        if (r0 == null) goto L7;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.checkRunnable = null;
        this.lastCheckName = null;
        if (this.checkReqId == 0) goto L7;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkReqId, true);
    L7:
        this.lastNameAvailable = false;
        if (r7 != null) goto L10;
    L44:
        if (r7 != null) goto L46;
    L67:
        if (r8 == false) goto L69;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.SetUrlInvalidShort));
    L70:
        return false;
    L69:
        setCheckText(LocaleController.getString(R.string.SetUrlInvalidShort), Theme.key_text_RedRegular);
        goto L70
    L46:
        if (r7.length() < 5) goto L67;
        if (r7.length() <= 64) goto L54;
        if (r8 == false) goto L52;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.SetUrlInvalidLong));
    L53:
        return false;
    L52:
        setCheckText(LocaleController.getString(R.string.SetUrlInvalidLong), Theme.key_text_RedRegular);
        goto L53
    L54:
        if (r8 == true) goto L66;
        TLRPC.TL_theme r82 = this.info;
        if (r82 == null) goto L60;
        String r83 = r82.slug;
        if (r83 == null) goto L60;
    L62:
        if (r7.equals(r83) == false) goto L65;
        setCheckText(LocaleController.formatString("SetUrlAvailable", R.string.SetUrlAvailable, new Object[]{r7}), Theme.key_windowBackgroundWhiteGreenText);
        return true;
    L65:
        setCheckText(LocaleController.getString(R.string.SetUrlChecking), Theme.key_windowBackgroundWhiteGrayText8);
        this.lastCheckName = r7;
        ThemeSetUrlActivity$$ExternalSyntheticLambda6 r84 = new ThemeSetUrlActivity$$ExternalSyntheticLambda6(this, r7);
        this.checkRunnable = r84;
        AndroidUtilities.runOnUIThread(r84, 300);
    L60:
        r83 = _UrlKt.FRAGMENT_ENCODE_SET;
    L66:
        return true;
    L10:
        if (r7.startsWith("_") == false) goto L12;
    L42:
        setCheckText(LocaleController.getString(R.string.SetUrlInvalid), Theme.key_text_RedRegular);
        return false;
    L12:
        if (r7.endsWith("_") == true) goto L42;
        int r2 = 0;
    L16:
        if (r2 >= r7.length()) goto L44;
        char r3 = r7.charAt(r2);
        if (r2 != 0) goto L25;
        if (r3 < '0') goto L25;
        if (r3 > '9') goto L25;
        if (r8 == false) goto L23;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.SetUrlInvalidStartNumber));
    L24:
        return false;
    L23:
        setCheckText(LocaleController.getString(R.string.SetUrlInvalidStartNumber), Theme.key_text_RedRegular);
    L25:
        if (r3 < '0') goto L28;
        if (r3 > '9') goto L28;
    L41:
        r2 = r2 + 1;
    L28:
        if (r3 < 'a') goto L32;
        if (r3 <= 'z') goto L41;
    L32:
        if (r3 < 'A') goto L36;
        if (r3 <= 'Z') goto L41;
    L36:
        if (r3 == '_') goto L41;
        if (r8 == false) goto L39;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.SetUrlInvalid));
    L40:
        return false;
    L39:
        setCheckText(LocaleController.getString(R.string.SetUrlInvalid), Theme.key_text_RedRegular);
        goto L40
    }

    private /* synthetic */ void lambda$checkUrl$6(String r4, TLRPC.TL_error r5) {
        this.checkReqId = 0;
        String r1 = this.lastCheckName;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.equals(r4) == false) goto L17;
        if (r5 != null) goto L8;
    L14:
        setCheckText(LocaleController.formatString("SetUrlAvailable", R.string.SetUrlAvailable, new Object[]{r4}), Theme.key_windowBackgroundWhiteGreenText);
        this.lastNameAvailable = true;
        return;
    L8:
        if ("THEME_SLUG_INVALID".equals(r5.text) == false) goto L10;
    L12:
        setCheckText(LocaleController.getString(R.string.SetUrlInUse), Theme.key_text_RedRegular);
        this.lastNameAvailable = false;
        return;
    L10:
        if ("THEME_SLUG_OCCUPIED".equals(r5.text) == true) goto L12;
    }

    private /* synthetic */ void lambda$checkUrl$7(String r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ThemeSetUrlActivity$$ExternalSyntheticLambda13(this, r1, r3));
    }

    private /* synthetic */ void lambda$checkUrl$8(String r4) {
        TL_account.createTheme r0 = new TL_account.createTheme();
        r0.slug = r4;
        r0.title = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.document = new TLRPC.TL_inputDocumentEmpty();
        this.checkReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ThemeSetUrlActivity$$ExternalSyntheticLambda12(this, r4), 2);
    }

    private /* synthetic */ boolean lambda$createView$1(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        AndroidUtilities.hideKeyboard(this.nameField);
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L8;
        View r0 = this.doneButton;
        if (r0 == null) goto L10;
        r0.performClick();
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$createView$3(View r1, boolean r2) {
        TextInfoPrivacyCell r0 = this.helpInfoCell;
        if (r2 == false) goto L6;
        r0.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ThemeCreateHelp2)));
        return;
    L6:
        r0.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ThemeCreateHelp)));
    }

    private /* synthetic */ void lambda$createView$5(Context r18, View r19) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        BottomSheet.Builder r7 = new BottomSheet.Builder(getParentActivity(), false);
        r7.setApplyBottomPadding(false);
        LinearLayout r9 = new LinearLayout(r18);
        r9.setOrientation(1);
        TextView r1 = new TextView(r18);
        r1.setText(LocaleController.getString(R.string.ChooseTheme));
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r1.setTextSize(1, 20.0f);
        r1.setTypeface(AndroidUtilities.bold());
        r9.addView(r1, LayoutHelper.createLinear(-1, -2, 51, 22, 12, 22, 4));
        r1.setOnTouchListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda5());
        r7.setCustomView(r9);
        ArrayList r5 = new ArrayList();
        int r0 = Theme.themes.size();
        int r12 = 0;
    L6:
        if (r12 >= r0) goto L14;
        Theme.ThemeInfo r3 = Theme.themes.get(r12);
        TLRPC.TL_theme r4 = r3.info;
        if (r4 != null) goto L10;
    L12:
        r5.add(r3);
    L13:
        r12 = r12 + 1;
        goto L6
    L10:
        if (r4.document != null) goto L12;
    L14:
        AnonymousClass4 r02 = new AnonymousClass4(this, r18, this, 2, r5, new ArrayList(), r7);
        r9.addView(r02, LayoutHelper.createLinear(-1, 148, 0.0f, 7.0f, 0.0f, 1.0f));
        r02.scrollToCurrentTheme(this.fragmentView.getMeasuredWidth(), false);
        showDialog(r7.create());
    }

    private /* synthetic */ void lambda$saveTheme$10(TLRPC.TL_theme r5) {
        this.progressDialog.dismiss();     // Catch: Exception -> L4
        this.progressDialog = null;     // Catch: Exception -> L4
    L6:
        Theme.setThemeUploadInfo(this.themeInfo, this.themeAccent, r5, this.currentAccount, false);
        finishFragment();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$saveTheme$11(TLRPC.TL_error r3, TL_account.updateTheme r4) {
        this.progressDialog.dismiss();     // Catch: Exception -> L4
        this.progressDialog = null;     // Catch: Exception -> L4
    L6:
        AlertsCreator.processError(this.currentAccount, r3, this, r4, new Object[0]);
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$saveTheme$12(TL_account.updateTheme r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.TL_theme) == false) goto L6;
        AndroidUtilities.runOnUIThread(new ThemeSetUrlActivity$$ExternalSyntheticLambda10(this, (TLRPC.TL_theme) r3));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new ThemeSetUrlActivity$$ExternalSyntheticLambda11(this, r4, r2));
    }

    private /* synthetic */ void lambda$saveTheme$13(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    private void saveTheme() {
        if (checkUrl(this.linkField.getText().toString(), true) == true) goto L6;
        return;
    L6:
        if (getParentActivity() != null) goto L9;
        return;
    L9:
        if (this.nameField.length() != 0) goto L12;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.Theme), LocaleController.getString(R.string.ThemeNameInvalid));
        return;
    L12:
        boolean r0 = this.creatingNewTheme;
        TLRPC.TL_theme r2 = this.info;
        if (r0 == false) goto L16;
        String r02 = r2.title;
        String r03 = r2.slug;
        AlertDialog r04 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r04;
        r04.setOnCancelListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda7());
        this.progressDialog.show();
        Theme.ThemeInfo r05 = this.themeInfo;
        TLRPC.TL_theme r22 = this.info;
        String r3 = this.nameField.getText().toString();
        r22.title = r3;
        r05.name = r3;
        TLRPC.TL_theme r06 = this.themeInfo.info;
        r06.slug = this.linkField.getText().toString();
        Theme.saveCurrentTheme(this.themeInfo, true, true, true);
        return;
    L16:
        String r07 = r2.slug;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r07 != null) goto L19;
        r07 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        String r23 = r2.title;
        if (r23 == null) goto L23;
        r1 = r23;
    L23:
        String r24 = this.linkField.getText().toString();
        String r4 = this.nameField.getText().toString();
        if (r07.equals(r24) == true) goto L26;
    L29:
        this.progressDialog = new AlertDialog(getParentActivity(), 3);
        TL_account.updateTheme r08 = new TL_account.updateTheme();
        TLRPC.TL_inputTheme r12 = new TLRPC.TL_inputTheme();
        TLRPC.TL_theme r5 = this.info;
        r12.id = r5.id;
        r12.access_hash = r5.access_hash;
        r08.theme = r12;
        r08.format = "android";
        r08.slug = r24;
        int r13 = r08.flags;
        r08.title = r4;
        r08.flags = r13 | 3;
        int r09 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r08, new ThemeSetUrlActivity$$ExternalSyntheticLambda8(this, r08), 2);
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r09, this.classGuid);
        this.progressDialog.setOnCancelListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda9(this, r09));
        this.progressDialog.show();
        return;
    L26:
        if (r1.equals(r4) == false) goto L29;
        finishFragment();
    }

    private void setCheckText(String r3, int r4) {
        boolean r0 = TextUtils.isEmpty(r3);
        TextInfoPrivacyCell r1 = this.checkInfoCell;
        if (r0 == false) goto L10;
        r1.setVisibility(8);
        boolean r32 = this.creatingNewTheme;
        TextInfoPrivacyCell r42 = this.helpInfoCell;
        if (r32 == false) goto L8;
        r42.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        return;
    L8:
        r42.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        return;
    L10:
        r1.setVisibility(0);
        this.checkInfoCell.setText(r3);
        this.checkInfoCell.setTag(Integer.valueOf(r4));
        this.checkInfoCell.setTextColorByKey(r4);
        boolean r33 = this.creatingNewTheme;
        TextInfoPrivacyCell r43 = this.helpInfoCell;
        if (r33 == false) goto L14;
        r43.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider_top, Theme.key_windowBackgroundGrayShadow));
        return;
    L14:
        r43.setBackgroundDrawable(null);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r24) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        boolean r2 = this.creatingNewTheme;
        ActionBar r4 = this.actionBar;
        if (r2 == false) goto L5;
        r4.setTitle(LocaleController.getString(R.string.NewThemeTitle));
    L6:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.doneButton = this.actionBar.createMenu().addItem(1, LocaleController.getString(R.string.Done).toUpperCase());
        LinearLayout r22 = new LinearLayout(r24);
        this.fragmentView = r22;
        r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        LinearLayout r23 = (LinearLayout) this.fragmentView;
        r23.setOrientation(1);
        this.fragmentView.setOnTouchListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda0());
        LinearLayout r42 = new LinearLayout(r24);
        this.linearLayoutTypeContainer = r42;
        r42.setOrientation(1);
        this.linearLayoutTypeContainer.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r23.addView(this.linearLayoutTypeContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r43 = new HeaderCell(r24, 23);
        this.headerCell = r43;
        r43.setText(LocaleController.getString(R.string.Info));
        this.linearLayoutTypeContainer.addView(this.headerCell);
        EditTextBoldCursor r44 = new EditTextBoldCursor(r24);
        this.nameField = r44;
        r44.setTextSize(1, 18.0f);
        EditTextBoldCursor r45 = this.nameField;
        int r8 = Theme.key_windowBackgroundWhiteHintText;
        r45.setHintTextColor(Theme.getColor(r8));
        EditTextBoldCursor r46 = this.nameField;
        int r9 = Theme.key_windowBackgroundWhiteBlackText;
        r46.setTextColor(Theme.getColor(r9));
        this.nameField.setMaxLines(1);
        this.nameField.setLines(1);
        EditTextBoldCursor r47 = this.nameField;
        if (LocaleController.isRTL == false) goto L9;
        int r10 = 5;
    L10:
        r47.setGravity(r10 | 16);
        this.nameField.setBackgroundDrawable(null);
        this.nameField.setPadding(0, 0, 0, 0);
        this.nameField.setSingleLine(true);
        this.nameField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(128)});
        this.nameField.setInputType(163872);
        this.nameField.setImeOptions(6);
        this.nameField.setHint(LocaleController.getString(R.string.ThemeNamePlaceholder));
        this.nameField.setCursorColor(Theme.getColor(r9));
        this.nameField.setCursorSize(AndroidUtilities.dp(20.0f));
        this.nameField.setCursorWidth(1.5f);
        this.linearLayoutTypeContainer.addView(this.nameField, LayoutHelper.createLinear(-1, 50, 23.0f, 0.0f, 23.0f, 0.0f));
        this.nameField.setOnEditorActionListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda1(this));
        AnonymousClass2 r48 = new AnonymousClass2(this, r24);
        this.divider = r48;
        this.linearLayoutTypeContainer.addView(r48, new LinearLayout.LayoutParams(-1, 1));
        LinearLayout r49 = new LinearLayout(r24);
        r49.setOrientation(0);
        this.linearLayoutTypeContainer.addView(r49, LayoutHelper.createLinear(-1, 50, 23.0f, 0.0f, 23.0f, 0.0f));
        EditTextBoldCursor r14 = new EditTextBoldCursor(r24);
        this.editText = r14;
        r14.setText(getMessagesController().linkPrefix + "/addtheme/");
        this.editText.setTextSize(1, 18.0f);
        this.editText.setHintTextColor(Theme.getColor(r8));
        this.editText.setTextColor(Theme.getColor(r9));
        this.editText.setMaxLines(1);
        this.editText.setLines(1);
        this.editText.setEnabled(false);
        this.editText.setBackgroundDrawable(null);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setSingleLine(true);
        this.editText.setInputType(163840);
        this.editText.setImeOptions(6);
        r49.addView(this.editText, LayoutHelper.createLinear(-2, 50));
        EditTextBoldCursor r5 = new EditTextBoldCursor(r24);
        this.linkField = r5;
        r5.setTextSize(1, 18.0f);
        this.linkField.setHintTextColor(Theme.getColor(r8));
        this.linkField.setTextColor(Theme.getColor(r9));
        this.linkField.setMaxLines(1);
        this.linkField.setLines(1);
        this.linkField.setBackgroundDrawable(null);
        this.linkField.setPadding(0, 0, 0, 0);
        this.linkField.setSingleLine(true);
        this.linkField.setInputType(163872);
        this.linkField.setImeOptions(6);
        this.linkField.setHint(LocaleController.getString(R.string.SetUrlPlaceholder));
        this.linkField.setCursorColor(Theme.getColor(r9));
        this.linkField.setCursorSize(AndroidUtilities.dp(20.0f));
        this.linkField.setCursorWidth(1.5f);
        r49.addView(this.linkField, LayoutHelper.createLinear(-1, 50));
        this.linkField.setOnEditorActionListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda2(this));
        this.linkField.addTextChangedListener(new AnonymousClass3(this));
        if (this.creatingNewTheme == false) goto L13;
        this.linkField.setOnFocusChangeListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda3(this));
    L13:
        TextInfoPrivacyCell r410 = new TextInfoPrivacyCell(r24);
        this.checkInfoCell = r410;
        int r52 = R.drawable.greydivider_bottom;
        int r7 = Theme.key_windowBackgroundGrayShadow;
        r410.setBackgroundDrawable(Theme.getThemedDrawableByKey(r24, r52, r7));
        this.checkInfoCell.setVisibility(8);
        this.checkInfoCell.setBottomPadding(0);
        r23.addView(this.checkInfoCell, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r411 = new TextInfoPrivacyCell(r24);
        this.helpInfoCell = r411;
        r411.getTextView().setMovementMethod(new LinkMovementMethodMy(null));
        this.helpInfoCell.getTextView().setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection));
        boolean r412 = this.creatingNewTheme;
        TextInfoPrivacyCell r53 = this.helpInfoCell;
        if (r412 == false) goto L16;
        r53.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ThemeCreateHelp)));
    L17:
        r23.addView(this.helpInfoCell, LayoutHelper.createLinear(-1, -2));
        boolean r413 = this.creatingNewTheme;
        TextInfoPrivacyCell r54 = this.helpInfoCell;
        if (r413 == false) goto L20;
        r54.setBackgroundDrawable(Theme.getThemedDrawableByKey(r24, R.drawable.greydivider, r7));
        ThemePreviewMessagesCell r414 = new ThemePreviewMessagesCell(r24, this.parentLayout, 1);
        this.messagesCell = r414;
        r23.addView(r414, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r415 = new TextSettingsCell(r24);
        this.createCell = r415;
        r415.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.createCell.setText(LocaleController.getString(R.string.UseDifferentTheme), false);
        r23.addView(this.createCell, LayoutHelper.createLinear(-1, -2));
        this.createCell.setOnClickListener(new ThemeSetUrlActivity$$ExternalSyntheticLambda4(this, r24));
        TextInfoPrivacyCell r416 = new TextInfoPrivacyCell(r24);
        this.createInfoCell = r416;
        r416.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.UseDifferentThemeInfo)));
        this.createInfoCell.setBackgroundDrawable(Theme.getThemedDrawableByKey(r24, R.drawable.greydivider_bottom, r7));
        r23.addView(this.createInfoCell, LayoutHelper.createLinear(-1, -2));
    L21:
        TLRPC.TL_theme r1 = this.info;
        if (r1 == null) goto L25;
        this.ignoreCheck = true;
        this.nameField.setText(r1.title);
        EditTextBoldCursor r12 = this.nameField;
        r12.setSelection(r12.length());
        this.linkField.setText(this.info.slug);
        EditTextBoldCursor r13 = this.linkField;
        r13.setSelection(r13.length());
        this.ignoreCheck = false;
    L25:
        return this.fragmentView;
    L20:
        r54.setBackgroundDrawable(Theme.getThemedDrawableByKey(r24, R.drawable.greydivider_bottom, r7));
        goto L21
    L16:
        SpannableStringBuilder r417 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ThemeSetUrlHelp));
        this.infoText = r417;
        r53.setText(r417);
        goto L17
    L9:
        r10 = 3;
        goto L10
    L5:
        r4.setTitle(LocaleController.getString(R.string.EditThemeTitle));
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.themeUploadedToServer) goto L16;
        Theme.ThemeInfo r42 = (Theme.ThemeInfo) r6[0];
        Theme.ThemeAccent r52 = (Theme.ThemeAccent) r6[1];
        if (r42 == this.themeInfo) goto L7;
        return;
    L7:
        if (r52 != this.themeAccent) goto L33;
        AlertDialog r43 = this.progressDialog;
        if (r43 == null) goto L34;
        r43.dismiss();     // Catch: Exception -> L12
        this.progressDialog = null;     // Catch: Exception -> L12
    L14:
        Theme.applyTheme(this.themeInfo, false);
        finishFragment();
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    L34:
        return;
    L33:
        return;
    L16:
        if (r4 != NotificationCenter.themeUploadError) goto L36;
        Theme.ThemeInfo r44 = (Theme.ThemeInfo) r6[0];
        Theme.ThemeAccent r53 = (Theme.ThemeAccent) r6[1];
        if (r44 == this.themeInfo) goto L20;
        return;
    L20:
        if (r53 != this.themeAccent) goto L38;
        AlertDialog r45 = this.progressDialog;
        if (r45 == null) goto L39;
        r45.dismiss();     // Catch: Exception -> L25
        this.progressDialog = null;     // Catch: Exception -> L25
        return;
    L25:
        e = move-exception;
        FileLog.e(e);
        return;
    L39:
        return;
    L38:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        LinearLayout r4 = this.linearLayoutTypeContainer;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r10 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.headerCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r23 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.createInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r23));
        int r32 = Theme.key_windowBackgroundWhiteGrayText4;
        r1.add(new ThemeDescription(this.createInfoCell, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.helpInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r23));
        r1.add(new ThemeDescription(this.helpInfoCell, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.checkInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r23));
        r1.add(new ThemeDescription(this.checkInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.checkInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText8));
        r1.add(new ThemeDescription(this.checkInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGreenText));
        int r9 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.createCell, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r9));
        r1.add(new ThemeDescription(this.createCell, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.createCell, ThemeDescription.FLAG_SELECTORWHITE, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r9));
        EditTextBoldCursor r11 = this.linkField;
        int r12 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r20 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r11, r12, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.linkField, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.nameField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.nameField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.nameField, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r20));
        View r3 = this.divider;
        Paint r6 = Theme.dividerPaint;
        int r92 = Theme.key_divider;
        r1.add(new ThemeDescription(r3, 0, null, r6, null, null, r92));
        r1.add(new ThemeDescription(this.divider, ThemeDescription.FLAG_BACKGROUND, null, Theme.dividerPaint, null, null, r92));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected));
        ThemePreviewMessagesCell r112 = this.messagesCell;
        Drawable[] r15 = Theme.chat_msgInDrawable.getShadowDrawables();
        int r17 = Theme.key_chat_inBubbleShadow;
        r1.add(new ThemeDescription(r112, 0, null, null, r15, null, r17));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, Theme.chat_msgInMediaDrawable.getShadowDrawables(), null, r17));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubble));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient1));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient2));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient3));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected));
        ThemePreviewMessagesCell r33 = this.messagesCell;
        Drawable[] r7 = Theme.chat_msgOutDrawable.getShadowDrawables();
        int r152 = Theme.key_chat_outBubbleShadow;
        r1.add(new ThemeDescription(r33, 0, null, null, r7, null, r152));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, Theme.chat_msgOutMediaDrawable.getShadowDrawables(), null, r152));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_messageTextIn));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_messageTextOut));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckDrawable}, null, Theme.key_chat_outSentCheck));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckReadDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheckRead));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgOutCheckReadSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckReadSelected));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inReplyLine));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outReplyLine));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inReplyNameText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outReplyNameText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inReplyMessageText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outReplyMessageText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inTimeText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outTimeText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_inTimeSelectedText));
        r1.add(new ThemeDescription(this.messagesCell, 0, null, null, null, null, Theme.key_chat_outTimeSelectedText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.themeUploadedToServer);
        getNotificationCenter().addObserver(this, NotificationCenter.themeUploadError);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.themeUploadedToServer);
        getNotificationCenter().removeObserver(this, NotificationCenter.themeUploadError);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == false) goto L5;
    L7:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        return;
    L5:
        if (this.creatingNewTheme == false) goto L7;
        this.linkField.requestFocus();
        AndroidUtilities.showKeyboard(this.linkField);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == true) goto L4;
        return;
    L4:
        if (this.creatingNewTheme == true) goto L8;
        this.linkField.requestFocus();
        AndroidUtilities.showKeyboard(this.linkField);
        return;
    }
}
