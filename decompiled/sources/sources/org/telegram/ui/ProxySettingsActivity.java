package org.telegram.ui;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.proxy.ProxyController;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.QRCodeBottomSheet;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.utils.proxy.ProxySettings;
import org.telegram.utils.proxy.WebProxyTransport;

/* loaded from: classes6.dex */
public class ProxySettingsActivity extends BaseFragment {
    private boolean addingNewProxy;
    private ClipboardManager.OnPrimaryClipChangedListener clipChangedListener;
    private ClipboardManager clipboardManager;
    private SharedConfig.ProxyInfo currentProxyInfo;
    private int currentType;
    private ActionBarMenuItem doneItem;
    private HeaderCell headerCell;
    private boolean ignoreOnTextChange;
    private OutlineTextContainerView[] inputFieldContainers;
    private EditTextBoldCursor[] inputFields;
    private LinearLayout inputFieldsContainer;
    private FrameLayout inputFieldsSection;
    private LinearLayout linearLayout2;
    private TextSettingsCell pasteCell;
    private String[] pasteFields;
    private String pasteString;
    private int pasteType;
    private ScrollView scrollView;
    private ShadowSectionCell[] sectionCell;
    private TextSettingsCell shareCell;
    private ValueAnimator shareDoneAnimator;
    private boolean shareDoneEnabled;
    private float shareDoneProgress;
    private float[] shareDoneProgressAnimValues;
    private TextInfoPrivacyCell sponsorInfoCell;
    private RadioCell[] typeCell;

    /* renamed from: org.telegram.ui.ProxySettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ProxySettingsActivity this$0;

        public AnonymousClass1(ProxySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r9) {
            if (r9 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        L6:
            boolean r0 = true;
            if (r9 == 1) goto L9;
            return;
        L9:
            if (this.this$0.getParentActivity() == null) goto L52;
            String r92 = ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[0].getText().toString().trim();
            if (ProxySettingsActivity.m18387$$Nest$fgetaddingNewProxy(this.this$0) == false) goto L14;
            String r2 = null;
        L16:
            if (ProxySettingsActivity.m18389$$Nest$fgetcurrentType(this.this$0) != 2) goto L18;
            boolean r3 = true;
        L19:
            SharedConfig.ProxyInfo r5 = ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0);
            ProxySettings.Builder r6 = ProxySettings.builder().setType(ProxySettings.intToType(ProxySettingsActivity.m18389$$Nest$fgetcurrentType(this.this$0))).setAddress(ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[1].getText().toString());
            if (r3 == false) goto L22;
            int r32 = 0;
        L23:
            ProxySettings.Builder r33 = r6.setPort(r32);
            int r4 = ProxySettingsActivity.m18389$$Nest$fgetcurrentType(this.this$0);
            String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r4 != 0) goto L26;
            String r42 = ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[3].getText().toString();
        L27:
            ProxySettings.Builder r34 = r33.setUser(r42);
            if (ProxySettingsActivity.m18389$$Nest$fgetcurrentType(this.this$0) != 0) goto L30;
            String r43 = ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[4].getText().toString();
        L31:
            ProxySettings.Builder r35 = r34.setPassword(r43);
            if (ProxySettingsActivity.m18389$$Nest$fgetcurrentType(this.this$0) == 0) goto L35;
            r62 = ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[5].getText().toString();
        L35:
            r5.settings = r35.setSecret(r62).build();
            ProxyController r36 = ProxyController.getInstance();
            SharedPreferences r44 = MessagesController.getGlobalMainSettings();
            if (ProxySettingsActivity.m18387$$Nest$fgetaddingNewProxy(this.this$0) == false) goto L38;
        L41:
            ProxySettingsActivity r52 = this.this$0;
            ProxySettingsActivity.m18392$$Nest$fputcurrentProxyInfo(r52, r36.saveProxy(ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(r52), r2, r92));
            if (ProxySettingsActivity.m18387$$Nest$fgetaddingNewProxy(this.this$0) == false) goto L45;
            r36.setCurrentProxy(ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0));
        L45:
            if (ProxySettingsActivity.m18387$$Nest$fgetaddingNewProxy(this.this$0) == false) goto L47;
        L48:
            SharedPreferences.Editor r93 = r44.edit();
            r93.putBoolean("proxy_enabled", r0);
            ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0).settings.toSharedPreferences(r93);
            ConnectionsManager.setProxySettings(r0, ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0).settings);
            r93.apply();
        L49:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
            this.this$0.finishFragment();
            return;
        L47:
            if (r36.getCurrentProxy() != ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0)) goto L49;
        L38:
            if (r44.getBoolean("proxy_enabled", false) == true) goto L41;
            r0 = false;
            goto L41
        L30:
            r43 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L31
        L26:
            r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L27
        L22:
            r32 = Utilities.parseInt(ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[2].getText().toString());
            goto L23
        L18:
            r3 = false;
            goto L19
        L14:
            r2 = ProxySettingsActivity.m18388$$Nest$fgetcurrentProxyInfo(this.this$0).settings.getLink();
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.ProxySettingsActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ ProxySettingsActivity this$0;

        public AnonymousClass2(ProxySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            ProxySettingsActivity.m18395$$Nest$mupdateActionBarTitle(this.this$0);
            ProxySettingsActivity r2 = this.this$0;
            ProxySettingsActivity.m18396$$Nest$mupdateFieldContainerState(r2, 0, ProxySettingsActivity.m18391$$Nest$fgetinputFields(r2)[0].hasFocus(), true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ProxySettingsActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ ProxySettingsActivity this$0;

        public AnonymousClass3(ProxySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            ProxySettingsActivity r32 = this.this$0;
            ProxySettingsActivity.m18396$$Nest$mupdateFieldContainerState(r32, 1, ProxySettingsActivity.m18391$$Nest$fgetinputFields(r32)[1].hasFocus(), true);
            ProxySettingsActivity.m18394$$Nest$mcheckShareDone(this.this$0, true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ProxySettingsActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ ProxySettingsActivity this$0;

        public AnonymousClass4(ProxySettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r10) {
            if (ProxySettingsActivity.m18390$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            EditTextBoldCursor r102 = ProxySettingsActivity.m18391$$Nest$fgetinputFields(this.this$0)[2];
            int r1 = r102.getSelectionStart();
            String r2 = r102.getText().toString();
            StringBuilder r3 = new StringBuilder(r2.length());
            int r5 = 0;
        L7:
            if (r5 >= r2.length()) goto L12;
            int r6 = r5 + 1;
            String r52 = r2.substring(r5, r6);
            if ("0123456789".contains(r52) == false) goto L11;
            r3.append(r52);
        L11:
            r5 = r6;
            goto L7
        L12:
            ProxySettingsActivity.m18393$$Nest$fputignoreOnTextChange(this.this$0, true);
            int r53 = Utilities.parseInt(r3.toString());
            if (r53 < 0) goto L20;
            if (r53 > 65535) goto L20;
            if (r2.equals(r3.toString()) == false) goto L20;
            if (r1 < 0) goto L25;
            r102.setSelection(Math.min(r1, r102.length()));
        L25:
            ProxySettingsActivity.m18393$$Nest$fputignoreOnTextChange(this.this$0, false);
            ProxySettingsActivity r103 = this.this$0;
            ProxySettingsActivity.m18396$$Nest$mupdateFieldContainerState(r103, 2, ProxySettingsActivity.m18391$$Nest$fgetinputFields(r103)[2].hasFocus(), true);
            ProxySettingsActivity.m18394$$Nest$mcheckShareDone(this.this$0, true);
            return;
        L20:
            if (r53 >= 0) goto L22;
            r102.setText(MVEL.VERSION_SUB);
            goto L25
        L22:
            if (r53 <= 65535) goto L24;
            r102.setText("65535");
            goto L25
        L24:
            r102.setText(r3.toString());
            goto L25
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ProxySettingsActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements Transition.TransitionListener {
        final /* synthetic */ ProxySettingsActivity this$0;
        final /* synthetic */ Runnable val$onTransitionEnd;

        public AnonymousClass5(ProxySettingsActivity r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onTransitionEnd = r2;
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            this.val$onTransitionEnd.run();
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }
    }

    public class SimpleFieldTextWatcher implements TextWatcher {
        private final int field;
        final /* synthetic */ ProxySettingsActivity this$0;

        private SimpleFieldTextWatcher(ProxySettingsActivity r1, int r2) {
            this.this$0 = r1;
            this.field = r2;
        }

        public /* synthetic */ SimpleFieldTextWatcher(ProxySettingsActivity r1, int r2, ProxySettingsActivityIA r3) {
            this(r1, r2);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            ProxySettingsActivity r32 = this.this$0;
            ProxySettingsActivity.m18396$$Nest$mupdateFieldContainerState(r32, this.field, ProxySettingsActivity.m18391$$Nest$fgetinputFields(r32)[this.field].hasFocus(), true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$DGNUFkWykf79oE7GtZURtVztSBs(ProxySettingsActivity r0, ValueAnimator r1) {
        r0.lambda$setShareDoneEnabled$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$JixV3Sywd5e9q3rAsBTscRTKFVw(ProxySettingsActivity r0, Context r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OD1QC_4Xy0h1sk5ShdUNbwQcHhQ(ProxySettingsActivity r0, View r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gq870PldOQFISZAwanlFUZNrjtc(ProxySettingsActivity r0) {
        r0.lambda$createView$3();
    }

    public static /* synthetic */ boolean $r8$lambda$nicxNy6HrkXxkKJNd2SoG_bcnbc(ProxySettingsActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qt1kf1FZweWADtS7u50MtO5uzPs(ProxySettingsActivity r0) {
        r0.updatePasteCell();
    }

    public static /* synthetic */ void $r8$lambda$shYeSnp4U7fniekGq2QMYXB6dzY(ProxySettingsActivity r0) {
        r0.lambda$getThemeDescriptions$7();
    }

    /* renamed from: $r8$lambda$skdlFzB3zg9KH933ra-tz24dUoU, reason: not valid java name */
    public static /* synthetic */ void m18386$r8$lambda$skdlFzB3zg9KH933ratz24dUoU(ProxySettingsActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$ueO50vyXR2Y8etK7epK8SIUY9Jc(ProxySettingsActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: -$$Nest$fgetaddingNewProxy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18387$$Nest$fgetaddingNewProxy(ProxySettingsActivity r0) {
        return r0.addingNewProxy;
    }

    /* renamed from: -$$Nest$fgetcurrentProxyInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedConfig.ProxyInfo m18388$$Nest$fgetcurrentProxyInfo(ProxySettingsActivity r0) {
        return r0.currentProxyInfo;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18389$$Nest$fgetcurrentType(ProxySettingsActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18390$$Nest$fgetignoreOnTextChange(ProxySettingsActivity r0) {
        return r0.ignoreOnTextChange;
    }

    /* renamed from: -$$Nest$fgetinputFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m18391$$Nest$fgetinputFields(ProxySettingsActivity r0) {
        return r0.inputFields;
    }

    /* renamed from: -$$Nest$fputcurrentProxyInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18392$$Nest$fputcurrentProxyInfo(ProxySettingsActivity r0, SharedConfig.ProxyInfo r1) {
        r0.currentProxyInfo = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18393$$Nest$fputignoreOnTextChange(ProxySettingsActivity r0, boolean r1) {
        r0.ignoreOnTextChange = r1;
    }

    /* renamed from: -$$Nest$mcheckShareDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18394$$Nest$mcheckShareDone(ProxySettingsActivity r0, boolean r1) {
        r0.checkShareDone(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBarTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18395$$Nest$mupdateActionBarTitle(ProxySettingsActivity r0) {
        r0.updateActionBarTitle();
    }

    /* renamed from: -$$Nest$mupdateFieldContainerState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18396$$Nest$mupdateFieldContainerState(ProxySettingsActivity r0, int r1, boolean r2, boolean r3) {
        r0.updateFieldContainerState(r1, r2, r3);
    }

    public ProxySettingsActivity() {
        this.sectionCell = new ShadowSectionCell[4];
        this.typeCell = new RadioCell[3];
        this.currentType = -1;
        this.pasteType = -1;
        this.shareDoneProgress = 1.0f;
        this.shareDoneProgressAnimValues = new float[2];
        this.shareDoneEnabled = true;
        this.clipChangedListener = new ProxySettingsActivity$$ExternalSyntheticLambda0(this);
        this.currentProxyInfo = new SharedConfig.ProxyInfo(ProxySettings.EMPTY);
        this.addingNewProxy = true;
    }

    public ProxySettingsActivity(SharedConfig.ProxyInfo r2) {
        this.sectionCell = new ShadowSectionCell[4];
        this.typeCell = new RadioCell[3];
        this.currentType = -1;
        this.pasteType = -1;
        this.shareDoneProgress = 1.0f;
        this.shareDoneProgressAnimValues = new float[2];
        this.shareDoneEnabled = true;
        this.clipChangedListener = new ProxySettingsActivity$$ExternalSyntheticLambda0(this);
        this.currentProxyInfo = r2;
    }

    private void checkShareDone(boolean r6) {
        if (this.shareCell != null) goto L5;
        return;
    L5:
        if (this.doneItem == null) goto L30;
        EditTextBoldCursor[] r0 = this.inputFields;
        boolean r1 = true;
        EditTextBoldCursor r2 = r0[1];
        if (r2 != null) goto L9;
        return;
    L9:
        if (r0[2] != null) goto L12;
        return;
    L12:
        if (this.currentType != 2) goto L22;
        if (TextUtils.isEmpty(WebProxyTransport.normalizeHost(r2.getText().toString())) == false) goto L16;
    L18:
        r1 = false;
    L19:
        setShareDoneEnabled(r1, r6);
        return;
    L16:
        if (WebProxyTransport.isValidSecret(this.inputFields[5].getText().toString()) == false) goto L18;
    L22:
        if (r2.length() != 0) goto L24;
    L26:
        r1 = false;
    L27:
        setShareDoneEnabled(r1, r6);
        return;
    L24:
        if (Utilities.parseInt(this.inputFields[2].getText().toString()) == 0) goto L26;
    }

    private Uri getProxyUriFromText(String r9) {
        String[] r8 = {"https://t.me/socks?", "http://t.me/socks?", "t.me/socks?", "tg://socks?", "https://t.me/proxy?", "http://t.me/proxy?", "t.me/proxy?", "tg://proxy?"};
        int r1 = 0;
    L4:
        if (r1 >= 8) goto L16;
        int r2 = r9.indexOf(r8[r1]);
        if (r2 >= 0) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        String r82 = r9.substring(r2).trim();
        int r92 = r82.indexOf(32);
        if (r92 < 0) goto L11;
        r82 = r82.substring(0, r92);
    L11:
        if (r82.startsWith("t.me/") == false) goto L14;
        r82 = "https://".concat(r82);
    L14:
        return Uri.parse(r82);
    L16:
        return null;
    }

    private /* synthetic */ void lambda$createView$0(View r2) {
        setProxyType(((Integer) r2.getTag()).intValue(), true);
    }

    private /* synthetic */ void lambda$createView$1(View r2, boolean r3) {
        int r22 = ((Integer) r2.getTag()).intValue();
        updateFieldContainerState(r22, r3, true);
        if (r22 != 5) goto L6;
        updateSecretVisibility(r3);
        return;
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r2, int r3, KeyEvent r4) {
        if (r3 != 5) goto L9;
        int r22 = ((Integer) r2.getTag()).intValue() + 1;
        EditTextBoldCursor[] r1 = this.inputFields;
        if (r22 >= r1.length) goto L7;
        r1[r22].requestFocus();
    L7:
        return true;
    L9:
        if (r3 != 6) goto L12;
        finishFragment();
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$createView$3() {
        AndroidUtilities.hideKeyboard(this.inputFieldsContainer.findFocus());
        int r1 = 1;
    L4:
        if (r1 >= this.pasteFields.length) goto L18;
        int r2 = this.pasteType;
        if (r2 == 0) goto L8;
    L10:
        if (r2 == 1) goto L12;
    L16:
        this.inputFields[r1].setText(null);
        updateFieldContainerState(r1, this.inputFields[r1].hasFocus(), false);
    L17:
        r1 = r1 + 1;
        goto L4
    L12:
        if (r1 == 3) goto L16;
        if (r1 == 4) goto L16;
    L8:
        if (r1 == 5) goto L10;
    }

    private /* synthetic */ void lambda$createView$4(View r6) {
        if (this.pasteType == (-1)) goto L31;
        int r0 = 0;
    L5:
        String[] r1 = this.pasteFields;
        if (r0 >= r1.length) goto L24;
        int r2 = this.pasteType;
        if (r2 == 0) goto L10;
    L12:
        if (r2 == 1) goto L14;
    L18:
        String r12 = r1[r0];
        EditTextBoldCursor[] r22 = this.inputFields;
        if (r12 == null) goto L21;
        r22[r0].setText(r12);
    L22:
        updateFieldContainerState(r0, this.inputFields[r0].hasFocus(), false);
    L23:
        r0 = r0 + 1;
        goto L5
    L21:
        r22[r0].setText(null);
        goto L22
    L14:
        if (r0 == 3) goto L23;
        if (r0 != 4) goto L18;
    L10:
        if (r0 != 5) goto L12;
    L24:
        EditTextBoldCursor r62 = this.inputFields[1];
        r62.setSelection(r62.length());
        setProxyType(this.pasteType, true, new ProxySettingsActivity$$ExternalSyntheticLambda7(this));
        return;
    }

    private /* synthetic */ void lambda$createView$5(Context r10, View r11) {
        String r6 = ProxyController.getInstance().buildShareLink(this.currentProxyInfo, this.inputFields[0].getText().toString().trim());
        if (TextUtils.isEmpty(r6) == false) goto L5;
        return;
    L5:
        QRCodeBottomSheet r3 = new QRCodeBottomSheet(r10, LocaleController.getString(R.string.ShareQrCode), r6, LocaleController.getString(R.string.QRCodeLinkHelpProxy), true);
        r3.setCenterImage(SvgHelper.getBitmap(AndroidUtilities.readRes(R.raw.qr_dog), AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f), false));
        showDialog(r3);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$7() {
        if (this.shareCell == null) goto L14;
        ValueAnimator r0 = this.shareDoneAnimator;
        if (r0 != null) goto L7;
    L8:
        TextSettingsCell r02 = this.shareCell;
        if (this.shareDoneEnabled == false) goto L12;
        int r1 = Theme.key_windowBackgroundWhiteBlueText4;
    L13:
        r02.setTextColor(Theme.getColor(r1));
        goto L14
    L12:
        r1 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L13
    L7:
        if (r0.isRunning() == false) goto L8;
    L14:
        OutlineTextContainerView[] r3 = this.inputFieldContainers;
        if (r3 == null) goto L22;
        int r03 = r3.length;
        int r12 = 0;
    L17:
        if (r12 >= r03) goto L26;
        OutlineTextContainerView r2 = r3[r12];
        if (r2 == null) goto L21;
        r2.updateColor();
    L21:
        r12 = r12 + 1;
        goto L17
    L26:
        return;
    }

    private /* synthetic */ void lambda$setShareDoneEnabled$6(ValueAnimator r4) {
        this.shareDoneProgress = AndroidUtilities.lerp(this.shareDoneProgressAnimValues, r4.getAnimatedFraction());
        this.shareCell.setTextColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4), this.shareDoneProgress));
        this.doneItem.setAlpha((this.shareDoneProgress / 2.0f) + 0.5f);
    }

    private void setProxyType(int r2, boolean r3) {
        setProxyType(r2, r3, null);
    }

    private void setProxyType(int r9, boolean r10, Runnable r11) {
        if (this.currentType == r9) goto L51;
        this.currentType = r9;
        TransitionManager.endTransitions(this.linearLayout2);
        if (r10 == false) goto L10;
        TransitionSet r1 = new TransitionSet().addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1)).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(250);
        if (r11 == null) goto L9;
        r1.addListener(new AnonymousClass5(this, r11));
    L9:
        TransitionManager.beginDelayedTransition(this.linearLayout2, r1);
    L10:
        int r112 = this.currentType;
        int r12 = 0;
        if (r112 != 2) goto L13;
        boolean r2 = true;
    L14:
        TextInfoPrivacyCell r3 = this.sponsorInfoCell;
        int r7 = 8;
        if (r112 != 0) goto L17;
        r3.setVisibility(8);
        this.inputFieldContainers[5].setVisibility(8);
        this.inputFieldContainers[4].setVisibility(0);
        this.inputFieldContainers[3].setVisibility(0);
    L21:
        OutlineTextContainerView r113 = this.inputFieldContainers[2];
        if (r2 == true) goto L25;
        r7 = 0;
    L25:
        r113.setVisibility(r7);
        if (r2 == false) goto L28;
        this.inputFields[2].setText(String.valueOf(443));
    L28:
        int r92 = 0;
    L29:
        RadioCell[] r114 = this.typeCell;
        if (r92 >= r114.length) goto L36;
        RadioCell r115 = r114[r92];
        if (this.currentType != r92) goto L34;
        boolean r32 = true;
    L35:
        r115.setChecked(r32, r10);
        r92 = r92 + 1;
        goto L29
    L34:
        r32 = false;
        goto L35
    L36:
        OutlineTextContainerView r93 = this.inputFieldContainers[1];
        if (r93 == null) goto L46;
        LinearLayout.LayoutParams r94 = (LinearLayout.LayoutParams) r93.getLayoutParams();
        if (r94 == null) goto L46;
        if (r2 == true) goto L44;
        r12 = AndroidUtilities.dp(8.0f);
    L44:
        if (r94.rightMargin == r12) goto L46;
        r94.rightMargin = r12;
        this.inputFieldContainers[1].setLayoutParams(r94);
    L46:
        checkShareDone(r10);
        return;
    L17:
        if (r2 == false) goto L19;
        int r116 = 8;
    L20:
        r3.setVisibility(r116);
        this.inputFieldContainers[5].setVisibility(0);
        this.inputFieldContainers[4].setVisibility(8);
        this.inputFieldContainers[3].setVisibility(8);
        goto L21
    L19:
        r116 = 0;
        goto L20
    L13:
        r2 = false;
        goto L14
    }

    private void setShareDoneEnabled(boolean r5, boolean r6) {
        if (this.shareDoneEnabled == r5) goto L29;
        ValueAnimator r0 = this.shareDoneAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L9:
        float r02 = 0.0f;
        float r1 = 1.0f;
        if (r6 == false) goto L15;
        float[] r62 = this.shareDoneProgressAnimValues;
        r62[0] = this.shareDoneProgress;
        if (r5 == false) goto L14;
        r02 = 1.0f;
    L14:
        r62[1] = r02;
        this.shareDoneAnimator.start();
    L27:
        this.shareCell.setEnabled(r5);
        this.doneItem.setEnabled(r5);
        this.shareDoneEnabled = r5;
        return;
    L15:
        if (r5 == false) goto L17;
        r02 = 1.0f;
    L17:
        this.shareDoneProgress = r02;
        TextSettingsCell r63 = this.shareCell;
        if (r5 == false) goto L21;
        int r03 = Theme.key_windowBackgroundWhiteBlueText4;
    L22:
        r63.setTextColor(Theme.getColor(r03));
        ActionBarMenuItem r64 = this.doneItem;
        if (r5 == true) goto L26;
        r1 = 0.5f;
    L26:
        r64.setAlpha(r1);
        goto L27
    L21:
        r03 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L22
    L7:
        if (r6 == false) goto L9;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.shareDoneAnimator = r04;
        r04.setDuration(200);
        this.shareDoneAnimator.addUpdateListener(new ProxySettingsActivity$$ExternalSyntheticLambda8(this));
        goto L9
    }

    private void updateActionBarTitle() {
        if (this.actionBar != null) goto L5;
        return;
    L5:
        EditTextBoldCursor[] r0 = this.inputFields;
        if (r0 == null) goto L13;
        if (r0.length <= 0) goto L13;
        EditTextBoldCursor r02 = r0[0];
        if (r02 == null) goto L13;
        String r03 = r02.getText().toString().trim();
    L16:
        ActionBar r2 = this.actionBar;
        if (TextUtils.isEmpty(r03) == false) goto L19;
        r03 = LocaleController.getString(R.string.ProxyDetails);
    L19:
        r2.setTitle(r03);
        return;
    L13:
        if (this.currentProxyInfo == null) goto L15;
        r03 = ProxyController.getInstance().getName(this.currentProxyInfo);
        goto L16
    L15:
        r03 = null;
        goto L16
    }

    private void updateFieldContainerState(int r4, boolean r5, boolean r6) {
        OutlineTextContainerView[] r0 = this.inputFieldContainers;
        if (r0 == null) goto L29;
        EditTextBoldCursor[] r1 = this.inputFields;
        if (r1 == null) goto L30;
        if (r4 >= 0) goto L8;
        return;
    L8:
        if (r4 < r0.length) goto L10;
        return;
    L10:
        if (r0[r4] == null) goto L33;
        EditTextBoldCursor r02 = r1[r4];
        if (r02 != null) goto L15;
        return;
    L15:
        if (r02.getText() != null) goto L17;
    L19:
        boolean r03 = false;
    L20:
        OutlineTextContainerView r3 = this.inputFieldContainers[r4];
        float r42 = 0.0f;
        if (r5 == false) goto L23;
        float r2 = 1.0f;
    L24:
        if (r5 == true) goto L26;
        if (r03 == true) goto L26;
    L27:
        r3.animateSelection(r2, r42, r6);
        return;
    L26:
        r42 = 1.0f;
        goto L27
    L23:
        r2 = 0.0f;
        goto L24
    L17:
        if (this.inputFields[r4].length() <= 0) goto L19;
        r03 = true;
        goto L20
    L33:
        return;
    L30:
        return;
    }

    private void updatePasteCell() {
        ClipData r0 = this.clipboardManager.getPrimaryClip();
        String r1 = null;
        if (r0 == null) goto L8;
        if (r0.getItemCount() <= 0) goto L8;
        r1 = r0.getItemAt(0).coerceToText(this.fragmentView.getContext()).toString();     // Catch: Exception -> L49
    L8:
        if (TextUtils.equals(r1, this.pasteString) == true) goto L55;
        this.pasteType = -1;
        this.pasteString = r1;
        this.pasteFields = new String[this.inputFields.length];
        if (r1 == null) goto L40;
        Uri r12 = getProxyUriFromText(r1);
        if (r12 == null) goto L40;
        String r4 = r12.getPath();
        String r5 = r12.getHost();
        if (TextUtils.equals(r5, "socks") == false) goto L17;
    L30:
        this.pasteType = 0;
    L32:
        if (this.pasteType == (-1)) goto L40;
        this.pasteFields[0] = r12.getQueryParameter("title");
        this.pasteFields[1] = r12.getQueryParameter("server");
        this.pasteFields[2] = r12.getQueryParameter("port");
        int r42 = this.pasteType;
        String[] r52 = this.pasteFields;
        if (r42 != 0) goto L36;
        r52[3] = r12.getQueryParameter("user");
        this.pasteFields[4] = r12.getQueryParameter("pass");
    L38:
        if (this.pasteType != 2) goto L40;
        this.pasteFields[2] = String.valueOf(443);
        goto L40
    L36:
        r52[5] = r12.getQueryParameter("secret");
        goto L38
    L17:
        if (TextUtils.equals(r4, "/socks") == true) goto L30;
        if (TextUtils.equals(r5, "proxy") == false) goto L22;
    L29:
        this.pasteType = 1;
        goto L32
    L22:
        if (TextUtils.equals(r4, "/proxy") == true) goto L29;
        if (TextUtils.equals(r5, "webproxy") == false) goto L27;
    L28:
        this.pasteType = 2;
        goto L32
    L27:
        if (TextUtils.equals(r4, "/webproxy") == false) goto L32;
    L40:
        int r13 = this.pasteType;
        TextSettingsCell r43 = this.pasteCell;
        if (r13 == (-1)) goto L46;
        if (r43.getVisibility() == 0) goto L52;
        this.pasteCell.setVisibility(0);
        this.sectionCell[2].setVisibility(0);
        return;
    L52:
        return;
    L46:
        if (r43.getVisibility() == 8) goto L54;
        this.pasteCell.setVisibility(8);
        this.sectionCell[2].setVisibility(8);
        return;
    L54:
        return;
    }

    private void updateSecretVisibility(boolean r4) {
        EditTextBoldCursor[] r0 = this.inputFields;
        if (r0 == null) goto L13;
        EditTextBoldCursor r02 = r0[5];
        if (r02 == null) goto L14;
        int r03 = r02.getSelectionStart();
        EditTextBoldCursor r2 = this.inputFields[5];
        if (r4 == false) goto L10;
        PasswordTransformationMethod r42 = null;
    L11:
        r2.setTransformationMethod(r42);
        EditTextBoldCursor r3 = this.inputFields[5];
        r3.setSelection(Math.max(0, Math.min(r03, r3.length())));
        return;
    L10:
        r42 = PasswordTransformationMethod.getInstance();
        goto L11
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r25) {
        this.actionBar.setTitle(LocaleController.getString(R.string.ProxyDetails));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        INavigationLayout r3 = this.parentLayout;
        if (r3 != null) goto L5;
    L7:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r32 = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f));
        this.doneItem = r32;
        r32.setContentDescription(LocaleController.getString(R.string.Done));
        FrameLayout r33 = new FrameLayout(r25);
        this.fragmentView = r33;
        r33.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.linearLayout2 = new SectionsScrollView.SectionsLinearLayout(r25);
        SectionsScrollView r5 = new SectionsScrollView(r25, this.linearLayout2, this.resourceProvider);
        this.scrollView = r5;
        this.actionBar.setAdaptiveBackground(r5);
        this.scrollView.setFillViewport(true);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor(Theme.key_actionBarDefault));
        r33.addView(this.scrollView, LayoutHelper.createFrame(-1, -1.0f));
        this.linearLayout2.setOrientation(1);
        this.scrollView.addView(this.linearLayout2, new FrameLayout.LayoutParams(-1, -2));
        int r34 = 6;
        this.inputFields = new EditTextBoldCursor[6];
        ProxySettingsActivity$$ExternalSyntheticLambda1 r52 = new ProxySettingsActivity$$ExternalSyntheticLambda1(this);
        int r6 = 0;
    L8:
        RadioCell[] r10 = this.typeCell;
        if (r6 >= r10.length) goto L25;
        r10[r6] = new RadioCell(r25);
        this.typeCell[r6].setBackground(Theme.getSelectorDrawable(true));
        this.typeCell[r6].setTag(Integer.valueOf(r6));
        if (r6 != 0) goto L13;
        int r102 = R.string.UseProxySocks5;
    L16:
        RadioCell r11 = this.typeCell[r6];
        String r103 = LocaleController.getString(r102);
        if (r6 != this.currentType) goto L19;
        boolean r12 = true;
    L21:
        if (r6 == (this.typeCell.length - 1)) goto L23;
        boolean r13 = true;
    L24:
        r11.setText(r103, r12, r13);
        this.linearLayout2.addView(this.typeCell[r6], LayoutHelper.createLinear(-1, 50));
        this.typeCell[r6].setOnClickListener(r52);
        r6 = r6 + 1;
        goto L8
    L23:
        r13 = false;
        goto L24
    L19:
        r12 = false;
        goto L21
    L13:
        if (r6 != 1) goto L15;
        r102 = R.string.UseProxyTelegram;
        goto L16
    L15:
        r102 = R.string.UseProxyWeb;
        goto L16
    L25:
        this.sectionCell[0] = new ShadowSectionCell(r25);
        this.linearLayout2.addView(this.sectionCell[0], LayoutHelper.createLinear(-1, 14));
        FrameLayout r53 = new FrameLayout(r25);
        this.inputFieldsSection = r53;
        r53.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), 0);
        this.linearLayout2.addView(this.inputFieldsSection, LayoutHelper.createLinear(-1, -2));
        LinearLayout r54 = new LinearLayout(r25);
        this.inputFieldsContainer = r54;
        r54.setOrientation(1);
        this.inputFieldsContainer.setTag(-33024);
        this.inputFieldsSection.addView(this.inputFieldsContainer, LayoutHelper.createFrame(-1, -2.0f));
        this.sectionCell[3] = new ShadowSectionCell(r25);
        this.linearLayout2.addView(this.sectionCell[3], LayoutHelper.createLinear(-1, 14));
        this.inputFieldContainers = new OutlineTextContainerView[6];
        int r55 = 0;
    L27:
        if (r55 >= r34) goto L60;
        OutlineTextContainerView r14 = new OutlineTextContainerView(r25);
        this.inputFieldContainers[r55] = r14;
        this.inputFields[r55] = new EditTextBoldCursor(r25);
        this.inputFields[r55].setTag(Integer.valueOf(r55));
        this.inputFields[r55].setTextSize(1, 16.0f);
        this.inputFields[r55].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r35 = this.inputFields[r55];
        int r15 = Theme.key_windowBackgroundWhiteBlackText;
        r35.setTextColor(Theme.getColor(r15));
        this.inputFields[r55].setBackground(null);
        this.inputFields[r55].setCursorColor(Theme.getColor(r15));
        this.inputFields[r55].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r55].setCursorWidth(1.5f);
        this.inputFields[r55].setSingleLine(true);
        EditTextBoldCursor r36 = this.inputFields[r55];
        if (LocaleController.isRTL == false) goto L31;
        int r8 = 5;
    L32:
        r36.setGravity(r8);
        if (r55 != 0) goto L35;
        this.inputFields[r55].setInputType(524289);
        this.inputFields[r55].addTextChangedListener(new AnonymousClass2(this));
    L45:
        this.inputFields[r55].setImeOptions(268435461);
        if (r55 == 0) goto L58;
        if (r55 == 1) goto L57;
        if (r55 == 2) goto L56;
        if (r55 == 3) goto L55;
        if (r55 == 4) goto L54;
        if (r55 != 5) goto L59;
        r14.setText(LocaleController.getString(R.string.UseProxySecret));
        this.inputFields[r55].setText(this.currentProxyInfo.settings.getSecret());
    L59:
        EditTextBoldCursor r37 = this.inputFields[r55];
        r37.setSelection(r37.length());
        this.inputFields[r55].setPadding(0, AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f));
        r14.addView(this.inputFields[r55], LayoutHelper.createFrame(-1, -2.0f, 51, 16.0f, 0.0f, 16.0f, 0.0f));
        r14.attachEditText(this.inputFields[r55]);
        this.inputFields[r55].setOnFocusChangeListener(new ProxySettingsActivity$$ExternalSyntheticLambda2(this));
        updateFieldContainerState(r55, false, false);
        this.inputFields[r55].setOnEditorActionListener(new ProxySettingsActivity$$ExternalSyntheticLambda3(this));
        r55 = r55 + 1;
        r34 = 6;
        goto L27
    L54:
        r14.setText(LocaleController.getString(R.string.UseProxyPassword));
        this.inputFields[r55].setText(this.currentProxyInfo.settings.getPassword());
        goto L59
    L55:
        r14.setText(LocaleController.getString(R.string.UseProxyUsername));
        this.inputFields[r55].setText(this.currentProxyInfo.settings.getUser());
        goto L59
    L56:
        r14.setText(LocaleController.getString(R.string.UseProxyPort));
        this.inputFields[r55].setText(_UrlKt.FRAGMENT_ENCODE_SET + this.currentProxyInfo.settings.getPort());
        goto L59
    L57:
        r14.setText(LocaleController.getString(R.string.UseProxyAddress));
        this.inputFields[r55].setText(this.currentProxyInfo.settings.getAddress());
        goto L59
    L58:
        r14.setText(LocaleController.getString(R.string.ProxyRename));
        this.inputFields[r55].setText(ProxyController.getInstance().getName(this.currentProxyInfo));
        goto L59
    L35:
        if (r55 != 1) goto L37;
        this.inputFields[r55].setInputType(524305);
        this.inputFields[r55].addTextChangedListener(new AnonymousClass3(this));
        goto L45
    L37:
        if (r55 != 2) goto L39;
        this.inputFields[r55].setInputType(2);
        this.inputFields[r55].addTextChangedListener(new AnonymousClass4(this));
        goto L45
    L39:
        if (r55 != 4) goto L41;
        this.inputFields[r55].setInputType(129);
        this.inputFields[r55].setTypeface(Typeface.DEFAULT);
        this.inputFields[r55].setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.inputFields[r55].addTextChangedListener(new SimpleFieldTextWatcher(this, 4, null));
        goto L45
    L41:
        EditTextBoldCursor[] r82 = this.inputFields;
        if (r55 != 5) goto L44;
        r82[r55].setInputType(524289);
        this.inputFields[r55].setTypeface(Typeface.DEFAULT);
        this.inputFields[r55].setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.inputFields[r55].addTextChangedListener(new SimpleFieldTextWatcher(this, 5, null));
        goto L45
    L44:
        r82[r55].setInputType(524289);
        this.inputFields[r55].addTextChangedListener(new SimpleFieldTextWatcher(this, r55, null));
        goto L45
    L31:
        r8 = 3;
        goto L32
    L60:
        this.inputFieldsContainer.addView(this.inputFieldContainers[0], LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        LinearLayout r38 = new LinearLayout(r25);
        r38.setOrientation(0);
        this.inputFieldsContainer.addView(r38, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        r38.addView(this.inputFieldContainers[1], LayoutHelper.createLinear(0, -2, 1.0f, 0, 0, 8, 0));
        r38.addView(this.inputFieldContainers[2], LayoutHelper.createLinear(112, -2, 8.0f, 0.0f, 0.0f, 0.0f));
        this.inputFieldsContainer.addView(this.inputFieldContainers[3], LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        this.inputFieldsContainer.addView(this.inputFieldContainers[4], LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        this.inputFieldsContainer.addView(this.inputFieldContainers[5], LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 12.0f));
        int r39 = 0;
    L62:
        if (r39 >= this.inputFieldsContainer.getChildCount()) goto L64;
        this.inputFieldsContainer.getChildAt(r39).setTag(-33024);
        r39 = r39 + 1;
        goto L62
    L64:
        updateActionBarTitle();
        TextSettingsCell r2 = new TextSettingsCell(this.fragmentView.getContext());
        this.pasteCell = r2;
        r2.setBackground(Theme.getSelectorDrawable(true));
        this.pasteCell.setText(LocaleController.getString(R.string.PasteFromClipboard), false);
        TextSettingsCell r22 = this.pasteCell;
        int r310 = Theme.key_windowBackgroundWhiteBlueText4;
        r22.setTextColor(Theme.getColor(r310));
        this.pasteCell.setOnClickListener(new ProxySettingsActivity$$ExternalSyntheticLambda4(this));
        this.linearLayout2.addView(this.pasteCell, 0, LayoutHelper.createLinear(-1, -2));
        this.pasteCell.setVisibility(8);
        this.sectionCell[2] = new ShadowSectionCell(r25);
        this.sectionCell[2].setVisibility(8);
        this.linearLayout2.addView(this.sectionCell[2], 1, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r23 = new TextSettingsCell(r25);
        this.shareCell = r23;
        r23.setBackground(Theme.getSelectorDrawable(true));
        this.shareCell.setText(LocaleController.getString(R.string.ShareFile), false);
        this.shareCell.setTextColor(Theme.getColor(r310));
        this.linearLayout2.addView(this.shareCell, LayoutHelper.createLinear(-1, -2));
        this.shareCell.setOnClickListener(new ProxySettingsActivity$$ExternalSyntheticLambda5(this, r25));
        TextInfoPrivacyCell r24 = new TextInfoPrivacyCell(r25);
        this.sponsorInfoCell = r24;
        r24.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.sponsorInfoCell.setText(LocaleController.getString(R.string.UseProxyTelegramInfo2));
        this.sponsorInfoCell.setVisibility(8);
        this.linearLayout2.addView(this.sponsorInfoCell, LayoutHelper.createLinear(-1, -2));
        this.sectionCell[1] = new ShadowSectionCell(r25);
        this.sectionCell[1].setBackgroundDrawable(Theme.getThemedDrawableByKey(r25, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linearLayout2.addView(this.sectionCell[1], LayoutHelper.createLinear(-1, -2));
        this.clipboardManager = (ClipboardManager) r25.getSystemService("clipboard");
        this.shareDoneEnabled = true;
        this.shareDoneProgress = 1.0f;
        checkShareDone(false);
        this.currentType = -1;
        setProxyType(ProxySettings.typeToInt(this.currentProxyInfo.settings.getType()), false);
        this.pasteType = -1;
        this.pasteString = null;
        updatePasteCell();
        return this.fragmentView;
    L5:
        if (r3.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ProxySettingsActivity$$ExternalSyntheticLambda6 r7 = new ProxySettingsActivity$$ExternalSyntheticLambda6(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        r10.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r13 = this.actionBar;
        int r14 = ThemeDescription.FLAG_BACKGROUND;
        int r19 = Theme.key_actionBarDefault;
        r10.add(new ThemeDescription(r13, r14, null, null, null, null, r19));
        r10.add(new ThemeDescription(this.scrollView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r19));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r10.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r10.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_windowBackgroundWhite));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_listSelector));
        int r192 = Theme.key_windowBackgroundWhiteBlueText4;
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r7, r192));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r7, Theme.key_windowBackgroundWhiteGrayText2));
        r10.add(new ThemeDescription(this.pasteCell, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r192));
        int r11 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.typeCell.length) goto L7;
        r10.add(new ThemeDescription(this.typeCell[r1], 0, new Class[]{RadioCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r10.add(new ThemeDescription(this.typeCell[r1], ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackground));
        r10.add(new ThemeDescription(this.typeCell[r1], ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioCell.class}, new String[]{"radioButton"}, null, null, null, Theme.key_radioBackgroundChecked));
        r1 = r1 + 1;
        goto L4
    L7:
        if (this.inputFields == null) goto L13;
        int r12 = 0;
    L10:
        if (r12 >= this.inputFields.length) goto L12;
        EditTextBoldCursor r132 = this.inputFields[r12];
        int r142 = ThemeDescription.FLAG_TEXTCOLOR;
        int r193 = Theme.key_windowBackgroundWhiteBlackText;
        r10.add(new ThemeDescription(r132, r142, null, null, null, null, r193));
        r10.add(new ThemeDescription(this.inputFields[r12], ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r10.add(new ThemeDescription(this.inputFields[r12], ThemeDescription.FLAG_HINTTEXTCOLOR | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r10.add(new ThemeDescription(this.inputFields[r12], ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r193));
        r12 = r12 + 1;
        goto L10
    L12:
        r10.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_windowBackgroundWhiteHintText));
        r10.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_windowBackgroundWhiteInputField));
        r10.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r10.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_text_RedBold));
    L14:
        r10.add(new ThemeDescription(this.headerCell, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r10.add(new ThemeDescription(this.headerCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
    L15:
        ShadowSectionCell[] r15 = this.sectionCell;
        if (r11 >= r15.length) goto L21;
        if (r15[r11] == null) goto L20;
        r10.add(new ThemeDescription(this.sectionCell[r11], ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
    L20:
        r11 = r11 + 1;
        goto L15
    L21:
        r10.add(new ThemeDescription(this.sponsorInfoCell, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r10.add(new ThemeDescription(this.sponsorInfoCell, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r10.add(new ThemeDescription(this.sponsorInfoCell, ThemeDescription.FLAG_LINKCOLOR, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        return r10;
    L13:
        r10.add(new ThemeDescription(null, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r10.add(new ThemeDescription(null, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.clipboardManager.removePrimaryClipChangedListener(this.clipChangedListener);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        this.clipboardManager.addPrimaryClipChangedListener(this.clipChangedListener);
        updatePasteCell();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L8;
        if (r2 == false) goto L5;
        return;
    L5:
        if (this.addingNewProxy == false) goto L10;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        return;
    L10:
        return;
    }
}
