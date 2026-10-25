package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Property;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.Cipher;
import kotlin.UByte;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MrzRecognizer;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.PushListenerController;
import org.telegram.messenger.R;
import org.telegram.messenger.SRPHelper;
import org.telegram.messenger.SecureDocument;
import org.telegram.messenger.SecureDocumentKey;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ContextProgressView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.HintEditText;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RadialProgress;
import org.telegram.ui.Components.SlideView;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.CountrySelectActivity;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public class PassportActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private TextView acceptTextView;
    private TextSettingsCell addDocumentCell;
    private ShadowSectionCell addDocumentSectionCell;
    private boolean allowNonLatinName;
    private ArrayList<TLRPC.TL_secureRequiredType> availableDocumentTypes;
    private TextInfoPrivacyCell bottomCell;
    private TextInfoPrivacyCell bottomCellTranslation;
    private FrameLayout bottomLayout;
    private boolean callbackCalled;
    private ChatAttachAlert chatAttachAlert;
    private HashMap<String, String> codesMap;
    private ArrayList<String> countriesArray;
    private HashMap<String, String> countriesMap;
    private int currentActivityType;
    private long currentBotId;
    private String currentCallbackUrl;
    private String currentCitizeship;
    private HashMap<String, String> currentDocumentValues;
    private TLRPC.TL_secureRequiredType currentDocumentsType;
    private TLRPC.TL_secureValue currentDocumentsTypeValue;
    private String currentEmail;
    private int[] currentExpireDate;
    private TL_account.authorizationForm currentForm;
    private String currentGender;
    private String currentNonce;
    private TL_account.Password currentPassword;
    private String currentPayload;
    private TLRPC.TL_auth_sentCode currentPhoneVerification;
    private LinearLayout currentPhotoViewerLayout;
    private String currentPicturePath;
    private String currentPublicKey;
    private String currentResidence;
    private String currentScope;
    private TLRPC.TL_secureRequiredType currentType;
    private TLRPC.TL_secureValue currentTypeValue;
    private HashMap<String, String> currentValues;
    private int currentViewNum;
    private PassportActivityDelegate delegate;
    private TextSettingsCell deletePassportCell;
    private ArrayList<View> dividers;
    private boolean documentOnly;
    private ArrayList<SecureDocument> documents;
    private HashMap<SecureDocument, SecureDocumentCell> documentsCells;
    private HashMap<String, String> documentsErrors;
    private LinearLayout documentsLayout;
    private HashMap<TLRPC.TL_secureRequiredType, TLRPC.TL_secureRequiredType> documentsToTypesLink;
    private ActionBarMenuItem doneItem;
    private AnimatorSet doneItemAnimation;
    private int emailCodeLength;
    private ImageView emptyImageView;
    private LinearLayout emptyLayout;
    private TextView emptyTextView1;
    private TextView emptyTextView2;
    private TextView emptyTextView3;
    private EmptyTextProgressView emptyView;
    private HashMap<String, HashMap<String, String>> errorsMap;
    private HashMap<String, String> errorsValues;
    private View extraBackgroundView;
    private View extraBackgroundView2;
    private HashMap<String, String> fieldsErrors;
    private SecureDocument frontDocument;
    private LinearLayout frontLayout;
    private HeaderCell headerCell;
    private boolean ignoreOnFailure;
    private boolean ignoreOnPhoneChange;
    private boolean ignoreOnTextChange;
    private String initialValues;
    private EditTextBoldCursor[] inputExtraFields;
    private ViewGroup[] inputFieldContainers;
    private EditTextBoldCursor[] inputFields;
    private HashMap<String, String> languageMap;
    private LinearLayout linearLayout2;
    private HashMap<String, String> mainErrorsMap;
    private TextInfoPrivacyCell nativeInfoCell;
    private boolean needActivityResult;
    private CharSequence noAllDocumentsErrorText;
    private CharSequence noAllTranslationErrorText;
    private ImageView noPasswordImageView;
    private TextView noPasswordSetTextView;
    private TextView noPasswordTextView;
    private boolean[] nonLatinNames;
    private FrameLayout passwordAvatarContainer;
    private TextView passwordForgotButton;
    private TextInfoPrivacyCell passwordInfoRequestTextView;
    private TextInfoPrivacyCell passwordRequestTextView;
    private PassportActivityDelegate pendingDelegate;
    private ErrorRunnable pendingErrorRunnable;
    private Runnable pendingFinishRunnable;
    private String pendingPhone;
    private Dialog permissionsDialog;
    private ArrayList<String> permissionsItems;
    private HashMap<String, String> phoneFormatMap;
    private TextView plusTextView;
    private PassportActivity presentAfterAnimation;
    private AlertDialog progressDialog;
    private ContextProgressView progressView;
    private ContextProgressView progressViewButton;
    private PhotoViewer.PhotoViewerProvider provider;
    private SecureDocument reverseDocument;
    private LinearLayout reverseLayout;
    private byte[] saltedPassword;
    private byte[] savedPasswordHash;
    private byte[] savedSaltedPassword;
    private TextSettingsCell scanDocumentCell;
    private int scrollHeight;
    private ScrollView scrollView;
    private ShadowSectionCell sectionCell;
    private ShadowSectionCell sectionCell2;
    private byte[] secureSecret;
    private long secureSecretId;
    private SecureDocument selfieDocument;
    private LinearLayout selfieLayout;
    private TextInfoPrivacyCell topErrorCell;
    private ArrayList<SecureDocument> translationDocuments;
    private LinearLayout translationLayout;
    private HashMap<TLRPC.TL_secureRequiredType, HashMap<String, String>> typesValues;
    private HashMap<TLRPC.TL_secureRequiredType, TextDetailSecureCell> typesViews;
    private TextSettingsCell uploadDocumentCell;
    private TextDetailSettingsCell uploadFrontCell;
    private TextDetailSettingsCell uploadReverseCell;
    private TextDetailSettingsCell uploadSelfieCell;
    private TextSettingsCell uploadTranslationCell;
    private HashMap<String, SecureDocument> uploadingDocuments;
    private int uploadingFileType;
    private boolean useCurrentValue;
    private int usingSavedPassword;
    private SlideView[] views;

    /* renamed from: org.telegram.ui.PassportActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass1(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void deleteImageAtIndex(int r9) {
            int r0 = PassportActivity.m16481$$Nest$fgetuploadingFileType(this.this$0);
            PassportActivity r1 = this.this$0;
            if (r0 != 1) goto L5;
            SecureDocument r92 = PassportActivity.m16478$$Nest$fgetselfieDocument(r1);
        L15:
            SecureDocumentCell r02 = (SecureDocumentCell) PassportActivity.m16451$$Nest$fgetdocumentsCells(this.this$0).remove(r92);
            if (r02 != null) goto L18;
            return;
        L18:
            String r93 = PassportActivity.m16517$$Nest$mgetDocumentHash(this.this$0, r92);
            int r12 = PassportActivity.m16481$$Nest$fgetuploadingFileType(this.this$0);
            PassportActivity r6 = this.this$0;
            String r7 = null;
            if (r12 != 1) goto L22;
            PassportActivity.m16505$$Nest$fputselfieDocument(r6, null);
            r7 = "selfie" + r93;
        L33:
            if (r7 != null) goto L35;
        L40:
            PassportActivity r94 = this.this$0;
            PassportActivity.m16533$$Nest$mupdateUploadText(r94, PassportActivity.m16481$$Nest$fgetuploadingFileType(r94));
            PassportActivity.m16441$$Nest$fgetcurrentPhotoViewerLayout(this.this$0).removeView(r02);
            return;
        L35:
            if (PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0) == null) goto L38;
            PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0).remove(r7);
        L38:
            if (PassportActivity.m16456$$Nest$fgeterrorsValues(this.this$0) == null) goto L40;
            PassportActivity.m16456$$Nest$fgeterrorsValues(this.this$0).remove(r7);
            goto L40
        L22:
            if (PassportActivity.m16481$$Nest$fgetuploadingFileType(r6) != 4) goto L24;
            r7 = "translation" + r93;
            goto L33
        L24:
            int r13 = PassportActivity.m16481$$Nest$fgetuploadingFileType(this.this$0);
            PassportActivity r4 = this.this$0;
            if (r13 != 2) goto L27;
            PassportActivity.m16494$$Nest$fputfrontDocument(r4, null);
            r7 = "front" + r93;
            goto L33
        L27:
            int r14 = PassportActivity.m16481$$Nest$fgetuploadingFileType(r4);
            PassportActivity r3 = this.this$0;
            if (r14 != 3) goto L31;
            PassportActivity.m16500$$Nest$fputreverseDocument(r3, null);
            r7 = "reverse" + r93;
            goto L33
        L31:
            if (PassportActivity.m16481$$Nest$fgetuploadingFileType(r3) != 0) goto L33;
            r7 = "files" + r93;
            goto L33
        L5:
            int r03 = PassportActivity.m16481$$Nest$fgetuploadingFileType(r1);
            PassportActivity r15 = this.this$0;
            if (r03 != 4) goto L8;
            r92 = (SecureDocument) PassportActivity.m16479$$Nest$fgettranslationDocuments(r15).get(r9);
            goto L15
        L8:
            int r04 = PassportActivity.m16481$$Nest$fgetuploadingFileType(r15);
            PassportActivity r16 = this.this$0;
            if (r04 != 2) goto L11;
            r92 = PassportActivity.m16458$$Nest$fgetfrontDocument(r16);
            goto L15
        L11:
            int r05 = PassportActivity.m16481$$Nest$fgetuploadingFileType(r16);
            PassportActivity r17 = this.this$0;
            if (r05 != 3) goto L14;
            r92 = PassportActivity.m16471$$Nest$fgetreverseDocument(r17);
            goto L15
        L14:
            r92 = (SecureDocument) PassportActivity.m16450$$Nest$fgetdocuments(r17).get(r9);
            goto L15
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public String getDeleteMessageString() {
            if (PassportActivity.m16481$$Nest$fgetuploadingFileType(this.this$0) != 1) goto L7;
            return LocaleController.formatString("PassportDeleteSelfieAlert", R.string.PassportDeleteSelfieAlert, new Object[0]);
        L7:
            return LocaleController.formatString("PassportDeleteScanAlert", R.string.PassportDeleteScanAlert, new Object[0]);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3, boolean r4, boolean r5) {
            if (r3 >= 0) goto L4;
            return null;
        L4:
            if (r3 >= PassportActivity.m16441$$Nest$fgetcurrentPhotoViewerLayout(this.this$0).getChildCount()) goto L10;
            SecureDocumentCell r12 = (SecureDocumentCell) PassportActivity.m16441$$Nest$fgetcurrentPhotoViewerLayout(this.this$0).getChildAt(r3);
            int[] r22 = new int[2];
            SecureDocumentCell.m16576$$Nest$fgetimageView(r12).getLocationInWindow(r22);
            PhotoViewer.PlaceProviderObject r32 = new PhotoViewer.PlaceProviderObject();
            r32.viewX = r22[0];
            r32.viewY = r22[1];
            r32.parentView = PassportActivity.m16441$$Nest$fgetcurrentPhotoViewerLayout(this.this$0);
            ImageReceiver r0 = SecureDocumentCell.m16576$$Nest$fgetimageView(r12).getImageReceiver();
            r32.imageReceiver = r0;
            r32.thumb = r0.getBitmapSafe();
            return r32;
        L10:
            return null;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements TextWatcher {
        private int actionPosition;
        private int characterAction;
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass10(PassportActivity r1) {
            this.this$0 = r1;
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r11) {
            if (PassportActivity.m16459$$Nest$fgetignoreOnPhoneChange(this.this$0) == false) goto L5;
            return;
        L5:
            HintEditText r112 = (HintEditText) PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2];
            int r1 = r112.getSelectionStart();
            String r2 = r112.getText().toString();
            if (this.characterAction != 3) goto L8;
            r2 = r2.substring(0, this.actionPosition).concat(r2.substring(this.actionPosition + 1));
            r1 = r1 - 1;
        L8:
            StringBuilder r3 = new StringBuilder(r2.length());
            int r7 = 0;
        L10:
            if (r7 >= r2.length()) goto L15;
            int r8 = r7 + 1;
            String r72 = r2.substring(r7, r8);
            if ("0123456789".contains(r72) == false) goto L14;
            r3.append(r72);
        L14:
            r7 = r8;
            goto L10
        L15:
            PassportActivity.m16496$$Nest$fputignoreOnPhoneChange(this.this$0, true);
            String r22 = r112.getHintText();
            if (r22 == null) goto L37;
            int r73 = 0;
        L19:
            if (r73 >= r3.length()) goto L37;
            if (r73 >= r22.length()) goto L31;
            if (r22.charAt(r73) != ' ') goto L30;
            r3.insert(r73, ' ');
            r73 = r73 + 1;
            if (r1 != r73) goto L30;
            int r82 = this.characterAction;
            if (r82 == 2) goto L30;
            if (r82 == 3) goto L30;
            r1 = r1 + 1;
        L30:
            r73 = r73 + 1;
            goto L19
        L31:
            r3.insert(r73, ' ');
            if (r1 != (r73 + 1)) goto L37;
            int r23 = this.characterAction;
            if (r23 == 2) goto L37;
            if (r23 == 3) goto L37;
            r1 = r1 + 1;
        L37:
            r112.setText(r3);
            if (r1 < 0) goto L40;
            r112.setSelection(Math.min(r1, r112.length()));
        L40:
            r112.onTextChange();
            PassportActivity.m16496$$Nest$fputignoreOnPhoneChange(this.this$0, false);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r2, int r3, int r4, int r5) {
            if (r4 != 0) goto L7;
            if (r5 != 1) goto L7;
            this.characterAction = 1;
            return;
        L7:
            if (r4 != 1) goto L16;
            if (r5 != 0) goto L16;
            if (r2.charAt(r3) != ' ') goto L14;
            if (r3 <= 0) goto L14;
            this.characterAction = 3;
            this.actionPosition = r3 - 1;
            return;
        L14:
            this.characterAction = 2;
            return;
        L16:
            this.characterAction = -1;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends FrameLayout {
        private StaticLayout errorLayout;
        float offsetX;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;

        public AnonymousClass11(PassportActivity r1, Context r2, EditTextBoldCursor r3) {
            this.this$0 = r1;
            this.val$field = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            if (this.errorLayout == null) goto L6;
            r4.save();
            r4.translate(AndroidUtilities.dp(21.0f) + this.offsetX, this.val$field.getLineY() + AndroidUtilities.dp(3.0f));
            this.errorLayout.draw(r4);
            r4.restore();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = View.MeasureSpec.getSize(r7) - AndroidUtilities.dp(34.0f);
            StaticLayout r1 = this.val$field.getErrorLayout(r0);
            this.errorLayout = r1;
            if (r1 == null) goto L18;
            int r12 = r1.getLineCount();
            int r2 = 0;
            if (r12 <= 1) goto L8;
            r8 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.errorLayout.getLineBottom(r12 - 1) - this.errorLayout.getLineBottom(0)), TLObject.FLAG_30);
        L8:
            if (LocaleController.isRTL == false) goto L18;
            float r4 = 0.0f;
        L10:
            if (r2 >= r12) goto L18;
            if (this.errorLayout.getLineLeft(r2) != 0.0f) goto L13;
            r4 = Math.max(r4, this.errorLayout.getLineWidth(r2));
            if (r2 != (r12 - 1)) goto L17;
            this.offsetX = r0 - r4;
        L17:
            r2 = r2 + 1;
            goto L10
        L13:
            this.offsetX = 0.0f;
        L18:
            super.onMeasure(r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$12, reason: invalid class name */
    public class AnonymousClass12 implements TextWatcher {
        private boolean ignore;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;
        final /* synthetic */ String val$key;

        public AnonymousClass12(PassportActivity r1, EditTextBoldCursor r2, String r3) {
            this.this$0 = r1;
            this.val$field = r2;
            this.val$key = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r6) {
            if (this.ignore == false) goto L5;
            return;
        L5:
            boolean r0 = true;
            this.ignore = true;
            int r2 = 0;
        L7:
            if (r2 >= r6.length()) goto L26;
            char r3 = r6.charAt(r2);
            if (r3 < 'a') goto L13;
            if (r3 > 'z') goto L13;
        L25:
            r2 = r2 + 1;
        L13:
            if (r3 < 'A') goto L17;
            if (r3 <= 'Z') goto L25;
        L17:
            if (r3 < '0') goto L21;
            if (r3 <= '9') goto L25;
        L21:
            if (r3 == '-') goto L25;
            if (r3 == ' ') goto L25;
        L27:
            this.ignore = false;
            if (r0 == false) goto L31;
            this.val$field.setErrorText(LocaleController.getString(R.string.PassportUseLatinOnly));
            return;
        L31:
            PassportActivity.m16509$$Nest$mcheckFieldForError(this.this$0, this.val$field, this.val$key, r6, false);
            return;
        L26:
            r0 = false;
            goto L27
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$13, reason: invalid class name */
    public class AnonymousClass13 implements TextWatcher {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;
        final /* synthetic */ String val$key;

        public AnonymousClass13(PassportActivity r1, EditTextBoldCursor r2, String r3) {
            this.this$0 = r1;
            this.val$field = r2;
            this.val$key = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            PassportActivity.m16509$$Nest$mcheckFieldForError(this.this$0, this.val$field, this.val$key, r4, false);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$14, reason: invalid class name */
    public class AnonymousClass14 implements CameraScanActivity.CameraScanActivityDelegate {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass14(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindMrzInfo(MrzRecognizer.Result r5) {
            if (TextUtils.isEmpty(r5.firstName) == true) goto L6;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].setText(r5.firstName);
        L6:
            if (TextUtils.isEmpty(r5.middleName) == true) goto L9;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].setText(r5.middleName);
        L9:
            if (TextUtils.isEmpty(r5.lastName) == true) goto L11;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].setText(r5.lastName);
        L11:
            int r0 = r5.gender;
            if (r0 == 0) goto L20;
            if (r0 == 1) goto L18;
            if (r0 != 2) goto L20;
            PassportActivity.m16488$$Nest$fputcurrentGender(this.this$0, "female");
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[4].setText(LocaleController.getString(R.string.PassportFemale));
            goto L20
        L18:
            PassportActivity.m16488$$Nest$fputcurrentGender(this.this$0, "male");
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[4].setText(LocaleController.getString(R.string.PassportMale));
        L20:
            if (TextUtils.isEmpty(r5.nationality) == true) goto L25;
            PassportActivity.m16485$$Nest$fputcurrentCitizeship(this.this$0, r5.nationality);
            String r02 = (String) PassportActivity.m16464$$Nest$fgetlanguageMap(this.this$0).get(PassportActivity.m16431$$Nest$fgetcurrentCitizeship(this.this$0));
            if (r02 == null) goto L25;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[5].setText(r02);
        L25:
            if (TextUtils.isEmpty(r5.issuingCountry) == true) goto L30;
            PassportActivity.m16490$$Nest$fputcurrentResidence(this.this$0, r5.issuingCountry);
            String r03 = (String) PassportActivity.m16464$$Nest$fgetlanguageMap(this.this$0).get(PassportActivity.m16443$$Nest$fgetcurrentResidence(this.this$0));
            if (r03 == null) goto L30;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[6].setText(r03);
        L30:
            if (r5.birthDay > 0) goto L32;
            return;
        L32:
            if (r5.birthMonth > 0) goto L34;
            return;
        L34:
            if (r5.birthYear <= 0) goto L39;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[3].setText(String.format(Locale.US, "%02d.%02d.%d", new Object[]{Integer.valueOf(r5.birthDay), Integer.valueOf(r5.birthMonth), Integer.valueOf(r5.birthYear)}));
            return;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$15, reason: invalid class name */
    public class AnonymousClass15 extends FrameLayout {
        private StaticLayout errorLayout;
        private float offsetX;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;

        public AnonymousClass15(PassportActivity r1, Context r2, EditTextBoldCursor r3) {
            this.this$0 = r1;
            this.val$field = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            if (this.errorLayout == null) goto L6;
            r4.save();
            r4.translate(AndroidUtilities.dp(21.0f) + this.offsetX, this.val$field.getLineY() + AndroidUtilities.dp(3.0f));
            this.errorLayout.draw(r4);
            r4.restore();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = View.MeasureSpec.getSize(r7) - AndroidUtilities.dp(34.0f);
            StaticLayout r1 = this.val$field.getErrorLayout(r0);
            this.errorLayout = r1;
            if (r1 == null) goto L18;
            int r12 = r1.getLineCount();
            int r2 = 0;
            if (r12 <= 1) goto L8;
            r8 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.errorLayout.getLineBottom(r12 - 1) - this.errorLayout.getLineBottom(0)), TLObject.FLAG_30);
        L8:
            if (LocaleController.isRTL == false) goto L18;
            float r4 = 0.0f;
        L10:
            if (r2 >= r12) goto L18;
            if (this.errorLayout.getLineLeft(r2) != 0.0f) goto L13;
            r4 = Math.max(r4, this.errorLayout.getLineWidth(r2));
            if (r2 != (r12 - 1)) goto L17;
            this.offsetX = r0 - r4;
        L17:
            r2 = r2 + 1;
            goto L10
        L13:
            this.offsetX = 0.0f;
        L18:
            super.onMeasure(r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$16, reason: invalid class name */
    public class AnonymousClass16 implements TextWatcher {
        private boolean ignore;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;
        final /* synthetic */ String val$key;

        public AnonymousClass16(PassportActivity r1, EditTextBoldCursor r2, String r3) {
            this.this$0 = r1;
            this.val$field = r2;
            this.val$key = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r6) {
            if (this.ignore == false) goto L5;
            return;
        L5:
            int r0 = ((Integer) this.val$field.getTag()).intValue();
            int r2 = 0;
        L7:
            if (r2 >= r6.length()) goto L36;
            char r3 = r6.charAt(r2);
            if (r3 < '0') goto L13;
            if (r3 > '9') goto L13;
        L35:
            r2 = r2 + 1;
        L13:
            if (r3 < 'a') goto L17;
            if (r3 <= 'z') goto L35;
        L17:
            if (r3 < 'A') goto L21;
            if (r3 <= 'Z') goto L35;
        L21:
            if (r3 == ' ') goto L35;
            if (r3 == '\'') goto L35;
            if (r3 == ',') goto L35;
            if (r3 == '.') goto L35;
            if (r3 == '&') goto L35;
            if (r3 == '-') goto L35;
            if (r3 == '/') goto L35;
            boolean r22 = true;
        L37:
            if (r22 == true) goto L39;
        L42:
            PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0)[r0] = r22;
            PassportActivity.m16509$$Nest$mcheckFieldForError(this.this$0, this.val$field, this.val$key, r6, false);
            return;
        L39:
            if (PassportActivity.m16424$$Nest$fgetallowNonLatinName(this.this$0) == true) goto L42;
            this.val$field.setErrorText(LocaleController.getString(R.string.PassportUseLatinOnly));
            return;
        L36:
            r22 = false;
            goto L37
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$17, reason: invalid class name */
    public class AnonymousClass17 implements TextWatcher {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;
        final /* synthetic */ String val$key;
        final /* synthetic */ HashMap val$values;

        public AnonymousClass17(PassportActivity r1, EditTextBoldCursor r2, String r3, HashMap r4) {
            this.this$0 = r1;
            this.val$field = r2;
            this.val$key = r3;
            this.val$values = r4;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r7) {
            PassportActivity r0 = this.this$0;
            EditTextBoldCursor r1 = this.val$field;
            String r2 = this.val$key;
            if (this.val$values != PassportActivity.m16432$$Nest$fgetcurrentDocumentValues(r0)) goto L5;
            boolean r3 = true;
        L6:
            PassportActivity.m16509$$Nest$mcheckFieldForError(r0, r1, r2, r7, r3);
            int r72 = ((Integer) this.val$field.getTag()).intValue();
            EditTextBoldCursor r02 = PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[r72];
            if (r72 != 6) goto L10;
            PassportActivity.m16511$$Nest$mcheckNativeFields(this.this$0, true);
            return;
        L10:
            return;
        L5:
            r3 = false;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$18, reason: invalid class name */
    public class AnonymousClass18 extends FrameLayout {
        private StaticLayout errorLayout;
        private float offsetX;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;

        public AnonymousClass18(PassportActivity r1, Context r2, EditTextBoldCursor r3) {
            this.this$0 = r1;
            this.val$field = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            if (this.errorLayout == null) goto L6;
            r4.save();
            r4.translate(AndroidUtilities.dp(21.0f) + this.offsetX, this.val$field.getLineY() + AndroidUtilities.dp(3.0f));
            this.errorLayout.draw(r4);
            r4.restore();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = View.MeasureSpec.getSize(r7) - AndroidUtilities.dp(34.0f);
            StaticLayout r1 = this.val$field.getErrorLayout(r0);
            this.errorLayout = r1;
            if (r1 == null) goto L18;
            int r12 = r1.getLineCount();
            int r2 = 0;
            if (r12 <= 1) goto L8;
            r8 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.errorLayout.getLineBottom(r12 - 1) - this.errorLayout.getLineBottom(0)), TLObject.FLAG_30);
        L8:
            if (LocaleController.isRTL == false) goto L18;
            float r4 = 0.0f;
        L10:
            if (r2 >= r12) goto L18;
            if (this.errorLayout.getLineLeft(r2) != 0.0f) goto L13;
            r4 = Math.max(r4, this.errorLayout.getLineWidth(r2));
            if (r2 != (r12 - 1)) goto L17;
            this.offsetX = r0 - r4;
        L17:
            r2 = r2 + 1;
            goto L10
        L13:
            this.offsetX = 0.0f;
        L18:
            super.onMeasure(r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$19, reason: invalid class name */
    public class AnonymousClass19 implements TextWatcher {
        private boolean ignore;
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ EditTextBoldCursor val$field;
        final /* synthetic */ String val$key;

        public AnonymousClass19(PassportActivity r1, EditTextBoldCursor r2, String r3) {
            this.this$0 = r1;
            this.val$field = r2;
            this.val$key = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            if (this.ignore == false) goto L5;
            return;
        L5:
            PassportActivity.m16509$$Nest$mcheckFieldForError(this.this$0, this.val$field, this.val$key, r4, false);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$1ValueToSend, reason: invalid class name */
    public class C1ValueToSend {
        boolean selfie_required;
        final /* synthetic */ PassportActivity this$0;
        boolean translation_required;
        TLRPC.TL_secureValue value;

        public C1ValueToSend(PassportActivity r1, TLRPC.TL_secureValue r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.value = r2;
            this.selfie_required = r3;
            this.translation_required = r4;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<TLRPC.SecureValueError> {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass2(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(TLRPC.SecureValueError r1, TLRPC.SecureValueError r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(TLRPC.SecureValueError r1, TLRPC.SecureValueError r2) {
            int r12 = getErrorValue(r1);
            int r0 = getErrorValue(r2);
            if (r12 >= r0) goto L6;
            return -1;
        L6:
            if (r12 <= r0) goto L9;
            return 1;
        L9:
            return 0;
        }

        public int getErrorValue(TLRPC.SecureValueError r2) {
            if ((r2 instanceof TLRPC.TL_secureValueError) == false) goto L7;
            return 0;
        L7:
            if ((r2 instanceof TLRPC.TL_secureValueErrorFrontSide) == false) goto L11;
            return 1;
        L11:
            if ((r2 instanceof TLRPC.TL_secureValueErrorReverseSide) == false) goto L15;
            return 2;
        L15:
            if ((r2 instanceof TLRPC.TL_secureValueErrorSelfie) == false) goto L19;
            return 3;
        L19:
            if ((r2 instanceof TLRPC.TL_secureValueErrorTranslationFile) == false) goto L23;
            return 4;
        L23:
            if ((r2 instanceof TLRPC.TL_secureValueErrorTranslationFiles) == false) goto L27;
            return 5;
        L27:
            if ((r2 instanceof TLRPC.TL_secureValueErrorFile) == false) goto L31;
            return 6;
        L31:
            if ((r2 instanceof TLRPC.TL_secureValueErrorFiles) == false) goto L35;
            return 7;
        L35:
            if ((r2 instanceof TLRPC.TL_secureValueErrorData) == true) goto L37;
            return 100;
        L37:
            return PassportActivity.m16518$$Nest$mgetFieldCost(this.this$0, ((TLRPC.TL_secureValueErrorData) r2).field);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$20, reason: invalid class name */
    public class AnonymousClass20 implements PassportActivityDelegate {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ int val$availableDocumentTypesCount;
        final /* synthetic */ boolean val$documentOnly;
        final /* synthetic */ TLRPC.SecureValueType val$type;

        /* renamed from: org.telegram.ui.PassportActivity$20$1, reason: invalid class name */
        public class AnonymousClass1 implements RequestDelegate {
            final /* synthetic */ AnonymousClass20 this$1;
            final /* synthetic */ PassportActivityDelegate val$currentDelegate;
            final /* synthetic */ TLRPC.TL_secureRequiredType val$documentRequiredType;
            final /* synthetic */ ArrayList val$documents;
            final /* synthetic */ String val$documentsJson;
            final /* synthetic */ ErrorRunnable val$errorRunnable;
            final /* synthetic */ TLRPC.TL_inputSecureValue val$finalFileInputSecureValue;
            final /* synthetic */ Runnable val$finishRunnable;
            final /* synthetic */ SecureDocument val$front;
            final /* synthetic */ String val$json;
            final /* synthetic */ TL_account.saveSecureValue val$req;
            final /* synthetic */ TLRPC.TL_secureRequiredType val$requiredType;
            final /* synthetic */ SecureDocument val$reverse;
            final /* synthetic */ SecureDocument val$selfie;
            final /* synthetic */ String val$text;
            final /* synthetic */ ArrayList val$translationDocuments;

            /* renamed from: $r8$lambda$8A9NA-18SIwQeWDFtLr02MDjbTU, reason: not valid java name */
            public static /* synthetic */ void m16535$r8$lambda$8A9NA18SIwQeWDFtLr02MDjbTU(ErrorRunnable r0, TLRPC.TL_error r1, String r2) {
                r0.onError(r1.text, r2);
            }

            public static /* synthetic */ void $r8$lambda$G0ajPvRBgGVZdXS6QR9lQQ_9_rw(AnonymousClass1 r0, TLRPC.TL_secureValue r1, TLObject r2, TLRPC.TL_error r3) {
                r0.lambda$run$4(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$J29p3AeNEB22GGTl9uuQyl_7HEk(AnonymousClass1 r0, String r1, TLRPC.TL_secureRequiredType r2, PassportActivityDelegate r3, ErrorRunnable r4, TLObject r5, TLRPC.TL_error r6) {
                r0.lambda$run$2(r1, r2, r3, r4, r5, r6);
            }

            public static /* synthetic */ void $r8$lambda$K3AZo5iMJqjVgG0goe95dhc7Exs(AnonymousClass1 r0, TLObject r1, String r2, TLRPC.TL_secureRequiredType r3, PassportActivityDelegate r4, TLRPC.TL_error r5, ErrorRunnable r6) {
                r0.lambda$run$1(r1, r2, r3, r4, r5, r6);
            }

            public static /* synthetic */ void $r8$lambda$ebYN6E8Hsu6X5dWEfEWLUhTrRNE(AnonymousClass1 r0, TLRPC.TL_error r1, ErrorRunnable r2, String r3, TL_account.saveSecureValue r4, boolean r5, TLRPC.TL_secureRequiredType r6, TLRPC.TL_secureRequiredType r7, TLRPC.TL_secureValue r8, TLRPC.TL_secureValue r9, ArrayList r10, SecureDocument r11, SecureDocument r12, SecureDocument r13, ArrayList r14, String r15, String r16, int r17, Runnable r18) {
                r0.lambda$onResult$0(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
            }

            public AnonymousClass1(AnonymousClass20 r1, ErrorRunnable r2, String r3, TL_account.saveSecureValue r4, TLRPC.TL_secureRequiredType r5, TLRPC.TL_secureRequiredType r6, ArrayList r7, SecureDocument r8, SecureDocument r9, SecureDocument r10, ArrayList r11, String r12, String r13, Runnable r14, PassportActivityDelegate r15, TLRPC.TL_inputSecureValue r16) {
                this.this$1 = r1;
                this.val$errorRunnable = r2;
                this.val$text = r3;
                this.val$req = r4;
                this.val$documentRequiredType = r5;
                this.val$requiredType = r6;
                this.val$documents = r7;
                this.val$selfie = r8;
                this.val$front = r9;
                this.val$reverse = r10;
                this.val$translationDocuments = r11;
                this.val$json = r12;
                this.val$documentsJson = r13;
                this.val$finishRunnable = r14;
                this.val$currentDelegate = r15;
                this.val$finalFileInputSecureValue = r16;
            }

            private /* synthetic */ void lambda$onResult$0(TLRPC.TL_error r17, ErrorRunnable r18, String r19, TL_account.saveSecureValue r20, boolean r21, TLRPC.TL_secureRequiredType r22, TLRPC.TL_secureRequiredType r23, TLRPC.TL_secureValue r24, TLRPC.TL_secureValue r25, ArrayList r26, SecureDocument r27, SecureDocument r28, SecureDocument r29, ArrayList r30, String r31, String r32, int r33, Runnable r34) {
                if (r17 == null) goto L10;
                if (r18 == null) goto L6;
                String r12 = r19;
                r18.onError(r17.text, r12);
            L7:
                AlertsCreator.processError(PassportActivity.access$1400(this.this$1.this$0), r17, this.this$1.this$0, r20, new Object[]{r12});
                return;
            L6:
                r12 = r19;
                goto L7
            L10:
                if (r21 == false) goto L15;
                AnonymousClass20 r1 = this.this$1;
                if (r22 == null) goto L14;
                PassportActivity.m16527$$Nest$mremoveValue(r1.this$0, r22);
            L16:
                if (r24 == null) goto L18;
                PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$1.this$0).values.add(r24);
            L18:
                if (r25 == null) goto L20;
                PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$1.this$0).values.add(r25);
            L20:
                if (r26 != null) goto L22;
            L37:
                if (r27 != null) goto L39;
            L45:
                if (r28 != null) goto L47;
            L53:
                if (r29 != null) goto L55;
            L61:
                if (r30 != null) goto L63;
            L79:
                PassportActivity.m16528$$Nest$msetTypeValue(this.this$1.this$0, r23, r19, r31, r22, r32, r21, r33);
                if (r34 == null) goto L97;
                r34.run();
                return;
            L97:
                return;
            L63:
                if (r30.isEmpty() == true) goto L79;
                int r13 = r30.size();
                int r4 = 0;
            L65:
                if (r4 >= r13) goto L79;
                SecureDocument r2 = (SecureDocument) r30.get(r4);
                if (r2.inputFile == null) goto L78;
                int r3 = r24.translation.size();
                int r6 = 0;
            L69:
                if (r6 >= r3) goto L78;
                TLRPC.SecureFile r7 = r24.translation.get(r6);
                if ((r7 instanceof TLRPC.TL_secureFile) == false) goto L76;
                TLRPC.TL_secureFile r72 = (TLRPC.TL_secureFile) r7;
                if (Utilities.arraysEquals(r2.fileSecret, 0, r72.secret, 0) == false) goto L76;
                AnonymousClass20.m16534$$Nest$mrenameFile(this.this$1, r2, r72);
            L76:
                r6 = r6 + 1;
            L78:
                r4 = r4 + 1;
                goto L65
            L55:
                if (r29.inputFile == null) goto L61;
                TLRPC.SecureFile r14 = r24.reverse_side;
                if ((r14 instanceof TLRPC.TL_secureFile) == false) goto L61;
                TLRPC.TL_secureFile r15 = (TLRPC.TL_secureFile) r14;
                if (Utilities.arraysEquals(r29.fileSecret, 0, r15.secret, 0) == false) goto L61;
                AnonymousClass20.m16534$$Nest$mrenameFile(this.this$1, r29, r15);
                goto L61
            L47:
                if (r28.inputFile == null) goto L53;
                TLRPC.SecureFile r16 = r24.front_side;
                if ((r16 instanceof TLRPC.TL_secureFile) == false) goto L53;
                TLRPC.TL_secureFile r110 = (TLRPC.TL_secureFile) r16;
                if (Utilities.arraysEquals(r28.fileSecret, 0, r110.secret, 0) == false) goto L53;
                AnonymousClass20.m16534$$Nest$mrenameFile(this.this$1, r28, r110);
                goto L53
            L39:
                if (r27.inputFile == null) goto L45;
                TLRPC.SecureFile r111 = r24.selfie;
                if ((r111 instanceof TLRPC.TL_secureFile) == false) goto L45;
                TLRPC.TL_secureFile r112 = (TLRPC.TL_secureFile) r111;
                if (Utilities.arraysEquals(r27.fileSecret, 0, r112.secret, 0) == false) goto L45;
                AnonymousClass20.m16534$$Nest$mrenameFile(this.this$1, r27, r112);
                goto L45
            L22:
                if (r26.isEmpty() == true) goto L37;
                int r210 = r26.size();
                int r62 = 0;
            L24:
                if (r62 >= r210) goto L37;
                SecureDocument r132 = (SecureDocument) r26.get(r62);
                if (r132.inputFile == null) goto L35;
                int r142 = r24.files.size();
                int r152 = 0;
            L28:
                if (r152 >= r142) goto L35;
                TLRPC.SecureFile r113 = r24.files.get(r152);
                int r182 = r210;
                if ((r113 instanceof TLRPC.TL_secureFile) == false) goto L34;
                TLRPC.TL_secureFile r114 = (TLRPC.TL_secureFile) r113;
                if (Utilities.arraysEquals(r132.fileSecret, 0, r114.secret, 0) == false) goto L34;
                AnonymousClass20.m16534$$Nest$mrenameFile(this.this$1, r132, r114);
            L36:
                r62 = r62 + 1;
                r210 = r182;
            L34:
                r152 = r152 + 1;
                r210 = r182;
            L35:
                r182 = r210;
                goto L36
            L14:
                PassportActivity.m16527$$Nest$mremoveValue(r1.this$0, r23);
                goto L16
            L15:
                PassportActivity.m16527$$Nest$mremoveValue(this.this$1.this$0, r23);
                PassportActivity.m16527$$Nest$mremoveValue(this.this$1.this$0, r22);
                goto L16
            }

            private /* synthetic */ void lambda$run$1(TLObject r11, String r12, TLRPC.TL_secureRequiredType r13, PassportActivityDelegate r14, TLRPC.TL_error r15, ErrorRunnable r16) {
                if (r11 == null) goto L6;
                TL_account.sentEmailCode r112 = (TL_account.sentEmailCode) r11;
                HashMap r8 = new HashMap();
                r8.put("email", r12);
                r8.put("pattern", r112.email_pattern);
                PassportActivity r0 = new PassportActivity(6, PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$1.this$0), PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$1.this$0), r13, null, null, null, r8, null);
                PassportActivity.access$1202(r0, PassportActivity.access$1300(this.this$1.this$0));
                PassportActivity.m16493$$Nest$fputemailCodeLength(r0, r112.length);
                PassportActivity.m16501$$Nest$fputsaltedPassword(r0, PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$1.this$0));
                PassportActivity.m16503$$Nest$fputsecureSecret(r0, PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$1.this$0));
                PassportActivity.m16491$$Nest$fputdelegate(r0, r14);
                this.this$1.this$0.presentFragment(r0, true);
                return;
            L6:
                PassportActivity.m16529$$Nest$mshowAlertWithText(this.this$1.this$0, LocaleController.getString(R.string.PassportEmail), r15.text);
                if (r16 == null) goto L10;
                r16.onError(r15.text, r12);
                return;
            }

            private /* synthetic */ void lambda$run$2(String r9, TLRPC.TL_secureRequiredType r10, PassportActivityDelegate r11, ErrorRunnable r12, TLObject r13, TLRPC.TL_error r14) {
                AndroidUtilities.runOnUIThread(new PassportActivity$20$1$$ExternalSyntheticLambda4(this, r13, r9, r10, r11, r14, r12));
            }

            private /* synthetic */ void lambda$run$4(TLRPC.TL_secureValue r1, TLObject r2, TLRPC.TL_error r3) {
                onResult(r3, (TLRPC.TL_secureValue) r2, r1);
            }

            private void onResult(TLRPC.TL_error r21, TLRPC.TL_secureValue r22, TLRPC.TL_secureValue r23) {
                ErrorRunnable r3 = this.val$errorRunnable;
                String r4 = this.val$text;
                TL_account.saveSecureValue r5 = this.val$req;
                AnonymousClass20 r0 = this.this$1;
                AndroidUtilities.runOnUIThread(new PassportActivity$20$1$$ExternalSyntheticLambda3(this, r21, r3, r4, r5, r0.val$documentOnly, this.val$documentRequiredType, this.val$requiredType, r22, r23, this.val$documents, this.val$selfie, this.val$front, this.val$reverse, this.val$translationDocuments, this.val$json, this.val$documentsJson, r0.val$availableDocumentTypesCount, this.val$finishRunnable));
            }

            @Override // org.telegram.tgnet.RequestDelegate
            public void run(TLObject r7, TLRPC.TL_error r8) {
                if (r8 != null) goto L4;
                AnonymousClass1 r1 = this;
            L12:
                if (r8 == null) goto L14;
            L17:
                r1.onResult(r8, (TLRPC.TL_secureValue) r7, null);
                return;
            L14:
                if (r1.val$finalFileInputSecureValue == null) goto L17;
                TL_account.saveSecureValue r6 = new TL_account.saveSecureValue();
                r6.value = r1.val$finalFileInputSecureValue;
                r6.secure_secret_id = PassportActivity.m16477$$Nest$fgetsecureSecretId(r1.this$1.this$0);
                ConnectionsManager.getInstance(PassportActivity.access$1100(r1.this$1.this$0)).sendRequest(r6, new PassportActivity$20$1$$ExternalSyntheticLambda2(r1, (TLRPC.TL_secureValue) r7));
                return;
            L4:
                if (r8.text.equals("EMAIL_VERIFICATION_NEEDED") == false) goto L7;
                TL_account.sendVerifyEmailCode r72 = new TL_account.sendVerifyEmailCode();
                r72.purpose = new TLRPC.TL_emailVerifyPurposePassport();
                r72.email = this.val$text;
                ConnectionsManager.getInstance(PassportActivity.access$1000(this.this$1.this$0)).sendRequest(r72, new PassportActivity$20$1$$ExternalSyntheticLambda0(this, this.val$text, this.val$requiredType, this.val$currentDelegate, this.val$errorRunnable));
                return;
            L7:
                r1 = this;
                if (r8.text.equals("PHONE_VERIFICATION_NEEDED") == false) goto L12;
                AndroidUtilities.runOnUIThread(new PassportActivity$20$1$$ExternalSyntheticLambda1(r1.val$errorRunnable, r8, r1.val$text));
            }
        }

        /* renamed from: -$$Nest$mrenameFile, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16534$$Nest$mrenameFile(AnonymousClass20 r0, SecureDocument r1, TLRPC.TL_secureFile r2) {
            r0.renameFile(r1, r2);
        }

        public AnonymousClass20(PassportActivity r1, TLRPC.SecureValueType r2, boolean r3, int r4) {
            this.this$0 = r1;
            this.val$type = r2;
            this.val$documentOnly = r3;
            this.val$availableDocumentTypesCount = r4;
        }

        private TLRPC.InputSecureFile getInputSecureFile(SecureDocument r4) {
            if (r4.inputFile == null) goto L6;
            TLRPC.TL_inputSecureFileUploaded r3 = new TLRPC.TL_inputSecureFileUploaded();
            TLRPC.TL_inputFile r0 = r4.inputFile;
            r3.id = r0.id;
            r3.parts = r0.parts;
            r3.md5_checksum = r0.md5_checksum;
            r3.file_hash = r4.fileHash;
            r3.secret = r4.fileSecret;
            return r3;
        L6:
            TLRPC.TL_inputSecureFile r32 = new TLRPC.TL_inputSecureFile();
            TLRPC.TL_secureFile r42 = r4.secureFile;
            r32.id = r42.id;
            r32.access_hash = r42.access_hash;
            return r32;
        }

        private void renameFile(SecureDocument r6, TLRPC.TL_secureFile r7) {
            File r5 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r6);
            String r62 = r6.secureFile.dc_id + "_" + r6.secureFile.id;
            File r0 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r7);
            String r72 = r7.dc_id + "_" + r7.id;
            r5.renameTo(r0);
            ImageLoader.getInstance().replaceImageInCache(r62, r72, null, false);
        }

        @Override // org.telegram.ui.PassportActivity.PassportActivityDelegate
        public void deleteValue(TLRPC.TL_secureRequiredType r9, TLRPC.TL_secureRequiredType r10, ArrayList<TLRPC.TL_secureRequiredType> r11, boolean r12, Runnable r13, ErrorRunnable r14) {
            PassportActivity.m16514$$Nest$mdeleteValueInternal(this.this$0, r9, r10, r11, r12, r13, r14, this.val$documentOnly);
        }

        @Override // org.telegram.ui.PassportActivity.PassportActivityDelegate
        public SecureDocument saveFile(TLRPC.TL_secureFile r9) {
            String r5 = FileLoader.getDirectory(4) + "/" + r9.dc_id + "_" + r9.id + ".jpg";
            EncryptionResult r8 = PassportActivity.m16512$$Nest$mcreateSecureDocument(this.this$0, r5);
            return new SecureDocument(r8.secureDocumentKey, r9, r5, r8.fileHash, r8.fileSecret);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.PassportActivity.PassportActivityDelegate
        public void saveValue(TLRPC.TL_secureRequiredType r20, String r21, String r22, TLRPC.TL_secureRequiredType r23, String r24, ArrayList<SecureDocument> r25, SecureDocument r26, ArrayList<SecureDocument> r27, SecureDocument r28, SecureDocument r29, Runnable r30, ErrorRunnable r31) {
            if (TextUtils.isEmpty(r22) == true) goto L6;
            TLRPC.TL_inputSecureValue r0 = new TLRPC.TL_inputSecureValue();
            r0.type = r20.type;
            r0.flags |= 1;
            EncryptionResult r12 = PassportActivity.m16515$$Nest$mencryptData(this.this$0, AndroidUtilities.getStringBytes(r22));
            TLRPC.TL_secureData r13 = new TLRPC.TL_secureData();
            r0.data = r13;
            r13.data = r12.encryptedData;
            r13.data_hash = r12.fileHash;
            r13.secret = r12.fileSecret;
        L16:
            if (this.val$documentOnly == true) goto L21;
            if (r0 != null) goto L21;
            if (r31 == null) goto L53;
            r31.onError(null, null);
            return;
        L53:
            return;
        L21:
            if (r23 == null) goto L46;
            TLRPC.TL_inputSecureValue r122 = new TLRPC.TL_inputSecureValue();
            r122.type = r23.type;
            if (TextUtils.isEmpty(r24) == true) goto L25;
            r122.flags |= 1;
            EncryptionResult r132 = PassportActivity.m16515$$Nest$mencryptData(this.this$0, AndroidUtilities.getStringBytes(r24));
            TLRPC.TL_secureData r14 = new TLRPC.TL_secureData();
            r122.data = r14;
            r14.data = r132.encryptedData;
            r14.data_hash = r132.fileHash;
            r14.secret = r132.fileSecret;
        L25:
            if (r28 == null) goto L27;
            r122.front_side = getInputSecureFile(r28);
            r122.flags |= 2;
        L27:
            if (r29 == null) goto L29;
            r122.reverse_side = getInputSecureFile(r29);
            r122.flags |= 4;
        L29:
            if (r26 == null) goto L31;
            r122.selfie = getInputSecureFile(r26);
            r122.flags |= 8;
        L31:
            if (r27 != null) goto L33;
        L37:
            if (r25 == null) goto L44;
            if (r25.isEmpty() == true) goto L44;
            r122.flags |= 16;
            int r4 = r25.size();
            int r133 = 0;
        L41:
            if (r133 >= r4) goto L44;
            r122.files.add(getInputSecureFile(r25.get(r133)));
            r133 = r133 + 1;
        L44:
            if (this.val$documentOnly == false) goto L47;
            r0 = r122;
            goto L46
        L47:
            TLRPC.TL_inputSecureValue r16 = r122;
        L48:
            TL_account.saveSecureValue r42 = new TL_account.saveSecureValue();
            r42.value = r0;
            r42.secure_secret_id = PassportActivity.m16477$$Nest$fgetsecureSecretId(this.this$0);
            ConnectionsManager.getInstance(PassportActivity.access$1500(this.this$0)).sendRequest(r42, new AnonymousClass1(this, r31, r21, r42, r23, r20, r25, r26, r28, r29, r27, r22, r24, r30, this, r16));
            return;
        L33:
            if (r27.isEmpty() == true) goto L37;
            r122.flags |= 64;
            int r142 = r27.size();
            int r15 = 0;
        L35:
            if (r15 >= r142) goto L37;
            r122.translation.add(getInputSecureFile(r27.get(r15)));
            r15 = r15 + 1;
        L46:
            r16 = null;
            goto L48
        L6:
            if (TextUtils.isEmpty(r21) == true) goto L14;
            TLRPC.SecureValueType r02 = this.val$type;
            if ((r02 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L11;
            TLRPC.TL_securePlainEmail r03 = new TLRPC.TL_securePlainEmail();
            r03.email = r21;
            TLRPC.TL_securePlainPhone r04 = r03;
        L13:
            TLRPC.TL_inputSecureValue r123 = new TLRPC.TL_inputSecureValue();
            r123.type = r20.type;
            r123.flags |= 32;
            r123.plain_data = r04;
            r0 = r123;
            goto L16
        L11:
            if ((r02 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L52;
            TLRPC.TL_securePlainPhone r05 = new TLRPC.TL_securePlainPhone();
            r05.phone = r21;
            r04 = r05;
            goto L13
        L52:
            return;
        L14:
            r0 = null;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$21, reason: invalid class name */
    public class AnonymousClass21 extends AnimatorListenerAdapter {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass21(PassportActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L9;
            PassportActivity.m16492$$Nest$fputdoneItemAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            PassportActivity r1 = this.this$0;
            if (r22 == true) goto L10;
            PassportActivity.m16469$$Nest$fgetprogressView(r1).setVisibility(4);
            return;
        L10:
            PassportActivity.m16453$$Nest$fgetdoneItem(r1).getContentView().setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$22, reason: invalid class name */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass22(PassportActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L9;
            PassportActivity.m16492$$Nest$fputdoneItemAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (PassportActivity.m16454$$Nest$fgetdoneItemAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            PassportActivity r1 = this.this$0;
            if (r22 == true) goto L10;
            PassportActivity.m16470$$Nest$fgetprogressViewButton(r1).setVisibility(4);
            return;
        L10:
            PassportActivity.m16423$$Nest$fgetacceptTextView(r1).setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$23, reason: invalid class name */
    public class AnonymousClass23 extends AnimatorListenerAdapter {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ SlideView val$newView;
        final /* synthetic */ SlideView val$outView;

        public AnonymousClass23(PassportActivity r1, SlideView r2, SlideView r3) {
            this.this$0 = r1;
            this.val$newView = r2;
            this.val$outView = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$outView.setVisibility(8);
            this.val$outView.setX(0.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$newView.setVisibility(0);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$24, reason: invalid class name */
    public class AnonymousClass24 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass24(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            if (PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0) != null) goto L8;
            return;
        L8:
            if (r1 != 8) goto L10;
        L17:
            if (r1 == 8) goto L19;
            PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0).dismiss(true);
        L19:
            HashMap<Object, Object> r12 = PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r22 = PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotosOrder();
            if (r12.isEmpty() == true) goto L35;
            ArrayList r32 = new ArrayList();
            int r42 = 0;
        L23:
            if (r42 >= r22.size()) goto L29;
            MediaController.PhotoEntry r52 = (MediaController.PhotoEntry) r12.get(r22.get(r42));
            SendMessagesHelper.SendingMediaInfo r62 = new SendMessagesHelper.SendingMediaInfo();
            String r7 = r52.imagePath;
            if (r7 == null) goto L27;
            r62.path = r7;
        L28:
            r32.add(r62);
            r52.reset();
            r42 = r42 + 1;
            goto L23
        L27:
            r62.path = r52.path;
            goto L28
        L29:
            PassportActivity.m16526$$Nest$mprocessSelectedFiles(this.this$0, r32);
            return;
        L35:
            return;
        L10:
            if (r1 == 7) goto L17;
            if (PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0) == null) goto L15;
            PassportActivity.m16425$$Nest$fgetchatAttachAlert(this.this$0).dismissWithButtonClick(r1);
        L15:
            PassportActivity.m16525$$Nest$mprocessSelectedAttach(this.this$0, r1);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
            AndroidUtilities.hideKeyboard(this.this$0.fragmentView.findFocus());
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PassportActivity this$0;

        /* renamed from: org.telegram.ui.PassportActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 extends URLSpanNoUnderline {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, String r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
            public void onClick(View r2) {
                this.this$1.this$0.dismissCurrentDialog();
                super.onClick(r2);
            }
        }

        /* renamed from: org.telegram.ui.PassportActivity$3$2, reason: invalid class name */
        public class AnonymousClass2 implements ErrorRunnable {
            final /* synthetic */ AnonymousClass3 this$1;
            final /* synthetic */ Runnable val$finishRunnable;

            public AnonymousClass2(AnonymousClass3 r1, Runnable r2) {
                this.this$1 = r1;
                this.val$finishRunnable = r2;
            }

            @Override // org.telegram.ui.PassportActivity.ErrorRunnable
            public void onError(String r8, String r9) {
                boolean r82 = "PHONE_VERIFICATION_NEEDED".equals(r8);
                AnonymousClass3 r0 = this.this$1;
                if (r82 == false) goto L6;
                PassportActivity r1 = r0.this$0;
                PassportActivity.m16531$$Nest$mstartPhoneVerification(r1, true, r9, this.val$finishRunnable, this, PassportActivity.m16448$$Nest$fgetdelegate(r1));
                return;
            L6:
                PassportActivity.m16530$$Nest$mshowEditDoneProgress(r0.this$0, true, false);
            }
        }

        public static /* synthetic */ void $r8$lambda$AOSi3WDjpqQ6_zJKh1j4JSM_VqE(AnonymousClass3 r0, TLRPC.TL_error r1, Runnable r2, ErrorRunnable r3, TL_account.verifyEmail r4) {
            r0.lambda$onItemClick$5(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$FfQZYqa9mPEmZUbDkV9LnUFWvyA(AnonymousClass3 r0, int r1, AlertDialog r2, int r3) {
            r0.lambda$onIdentityDone$1(r1, r2, r3);
        }

        public static /* synthetic */ int $r8$lambda$ZieRTvghpPzek6j_CgIyBUsWnAA(AnonymousClass3 r0, String r1, String r2) {
            return r0.lambda$onIdentityDone$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ayoSN_ZN231RjnloosKGA9Xq4Fw(AnonymousClass3 r0, Runnable r1, ErrorRunnable r2, TL_account.verifyEmail r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$onItemClick$6(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$jl3u4_M7OPgIkemjZu4XmmGq1TE(AnonymousClass3 r0, String r1, String r2, String r3, Runnable r4, ErrorRunnable r5, AlertDialog r6, int r7) {
            r0.lambda$onIdentityDone$0(r1, r2, r3, r4, r5, r6, r7);
        }

        public static /* synthetic */ void $r8$lambda$q3vi8sxnzEeWCYj3SCG5qTyM2lM(AnonymousClass3 r0) {
            r0.lambda$onItemClick$4();
        }

        public static /* synthetic */ int $r8$lambda$tgdLfh4RpK9IpLsX4ZYgaetqqBA(AnonymousClass3 r0, String r1, String r2) {
            return r0.lambda$onIdentityDone$2(r1, r2);
        }

        public AnonymousClass3(PassportActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onIdentityDone$0(String r1, String r2, String r3, Runnable r4, ErrorRunnable r5, AlertDialog r6, int r7) {
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].setText(r1);
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].setText(r2);
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].setText(r3);
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(this.this$0, true, true);
            onIdentityDone(r4, r5);
        }

        private /* synthetic */ void lambda$onIdentityDone$1(int r1, AlertDialog r2, int r3) {
            PassportActivity r0 = this.this$0;
            PassportActivity.m16522$$Nest$monFieldError(r0, PassportActivity.m16463$$Nest$fgetinputFields(r0)[r1]);
        }

        private /* synthetic */ int lambda$onIdentityDone$2(String r2, String r3) {
            int r22 = PassportActivity.m16518$$Nest$mgetFieldCost(this.this$0, r2);
            int r1 = PassportActivity.m16518$$Nest$mgetFieldCost(this.this$0, r3);
            if (r22 >= r1) goto L6;
            return -1;
        L6:
            if (r22 <= r1) goto L9;
            return 1;
        L9:
            return 0;
        }

        private /* synthetic */ int lambda$onIdentityDone$3(String r2, String r3) {
            int r22 = PassportActivity.m16518$$Nest$mgetFieldCost(this.this$0, r2);
            int r1 = PassportActivity.m16518$$Nest$mgetFieldCost(this.this$0, r3);
            if (r22 >= r1) goto L6;
            return -1;
        L6:
            if (r22 <= r1) goto L9;
            return 1;
        L9:
            return 0;
        }

        private /* synthetic */ void lambda$onItemClick$4() {
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$onItemClick$5(TLRPC.TL_error r17, Runnable r18, ErrorRunnable r19, TL_account.verifyEmail r20) {
            PassportActivity r2 = this.this$0;
            if (r17 != null) goto L6;
            PassportActivity.m16448$$Nest$fgetdelegate(r2).saveValue(PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0), (String) PassportActivity.m16446$$Nest$fgetcurrentValues(this.this$0).get("email"), null, null, null, null, null, null, null, null, r18, r19);
            return;
        L6:
            AlertsCreator.processError(PassportActivity.access$900(r2), r17, this.this$0, r20, new Object[0]);
            r19.onError(null, null);
        }

        private /* synthetic */ void lambda$onItemClick$6(Runnable r1, ErrorRunnable r2, TL_account.verifyEmail r3, TLObject r4, TLRPC.TL_error r5) {
            AndroidUtilities.runOnUIThread(new PassportActivity$3$$ExternalSyntheticLambda2(this, r5, r1, r2, r3));
        }

        private boolean onIdentityDone(Runnable r27, ErrorRunnable r28) {
            int r7 = 0;
            if (PassportActivity.m16480$$Nest$fgetuploadingDocuments(this.this$0).isEmpty() == true) goto L5;
        L97:
            return false;
        L5:
            if (PassportActivity.m16510$$Nest$mcheckFieldsForError(this.this$0) == true) goto L97;
            if (PassportActivity.m16424$$Nest$fgetallowNonLatinName(this.this$0) == false) goto L43;
            PassportActivity.m16484$$Nest$fputallowNonLatinName(this.this$0, false);
            boolean r0 = false;
            int r10 = 0;
        L11:
            if (r10 >= PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0).length) goto L41;
            if (PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0)[r10] == false) goto L40;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[r10].setErrorText(LocaleController.getString(R.string.PassportUseLatinOnly));
            if (r0 == true) goto L40;
            boolean r02 = PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0)[0];
            PassportActivity r2 = this.this$0;
            if (r02 == false) goto L20;
            String r03 = PassportActivity.m16520$$Nest$mgetTranslitString(r2, PassportActivity.m16461$$Nest$fgetinputExtraFields(r2)[0].getText().toString());
        L19:
            String r22 = r03;
            boolean r04 = PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0)[1];
            PassportActivity r3 = this.this$0;
            if (r04 == false) goto L25;
            String r05 = PassportActivity.m16520$$Nest$mgetTranslitString(r3, PassportActivity.m16461$$Nest$fgetinputExtraFields(r3)[1].getText().toString());
        L24:
            String r32 = r05;
            boolean r06 = PassportActivity.m16467$$Nest$fgetnonLatinNames(this.this$0)[2];
            PassportActivity r4 = this.this$0;
            if (r06 == false) goto L30;
            String r07 = PassportActivity.m16520$$Nest$mgetTranslitString(r4, PassportActivity.m16461$$Nest$fgetinputExtraFields(r4)[2].getText().toString());
        L29:
            String r42 = r07;
            if (TextUtils.isEmpty(r22) == false) goto L34;
        L38:
            PassportActivity r08 = this.this$0;
            PassportActivity.m16522$$Nest$monFieldError(r08, PassportActivity.m16463$$Nest$fgetinputFields(r08)[r10]);
        L39:
            r0 = true;
            goto L40
        L34:
            if (TextUtils.isEmpty(r32) == true) goto L38;
            if (TextUtils.isEmpty(r42) == true) goto L38;
            AlertDialog.Builder r11 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r11.setMessage(LocaleController.formatString("PassportNameCheckAlert", R.string.PassportNameCheckAlert, new Object[]{r22, r32, r42}));
            r11.setTitle(LocaleController.getString(R.string.AppName));
            r11.setPositiveButton(LocaleController.getString(R.string.Done), new PassportActivity$3$$ExternalSyntheticLambda3(this, r22, r32, r42, r27, r28));
            r11.setNegativeButton(LocaleController.getString(R.string.Edit), new PassportActivity$3$$ExternalSyntheticLambda4(this, r10));
            this.this$0.showDialog(r11.create());
            goto L39
        L30:
            r07 = PassportActivity.m16463$$Nest$fgetinputFields(r4)[2].getText().toString();
            goto L29
        L25:
            r05 = PassportActivity.m16463$$Nest$fgetinputFields(r3)[1].getText().toString();
            goto L24
        L20:
            r03 = PassportActivity.m16463$$Nest$fgetinputFields(r2)[0].getText().toString();
        L40:
            r10 = r10 + 1;
            goto L11
        L41:
            if (r0 == false) goto L43;
            return false;
        L43:
            boolean r09 = PassportActivity.m16521$$Nest$misHasNotAnyChanges(this.this$0);
            PassportActivity r23 = this.this$0;
            if (r09 == false) goto L47;
            r23.finishFragment();
            return false;
        L47:
            SecureDocument r010 = null;
        L56:
            JSONObject r33 = null;
            JSONObject r43 = null;
        L77:
            if (PassportActivity.m16457$$Nest$fgetfieldsErrors(this.this$0) == null) goto L80;
            PassportActivity.m16457$$Nest$fgetfieldsErrors(this.this$0).clear();
        L80:
            if (PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0) == null) goto L82;
            PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0).clear();
        L82:
            PassportActivityDelegate r13 = PassportActivity.m16448$$Nest$fgetdelegate(this.this$0);
            TLRPC.TL_secureRequiredType r14 = PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0);
            if (r33 == null) goto L85;
            String r16 = r33.toString();
        L86:
            TLRPC.TL_secureRequiredType r17 = PassportActivity.m16433$$Nest$fgetcurrentDocumentsType(this.this$0);
            if (r43 == null) goto L89;
            String r18 = r43.toString();
        L90:
            SecureDocument r20 = PassportActivity.m16478$$Nest$fgetselfieDocument(this.this$0);
            ArrayList<SecureDocument> r21 = PassportActivity.m16479$$Nest$fgettranslationDocuments(this.this$0);
            SecureDocument r222 = PassportActivity.m16458$$Nest$fgetfrontDocument(this.this$0);
            if (PassportActivity.m16472$$Nest$fgetreverseLayout(this.this$0) != null) goto L93;
        L95:
            r13.saveValue(r14, null, r16, r17, r18, null, r20, r21, r222, r010, r27, r28);
            return true;
        L93:
            if (PassportActivity.m16472$$Nest$fgetreverseLayout(this.this$0).getVisibility() != 0) goto L95;
            r010 = PassportActivity.m16471$$Nest$fgetreverseDocument(this.this$0);
            goto L95
        L89:
            r18 = null;
            goto L90
        L85:
            r16 = null;
            goto L86
        L49:
            if (PassportActivity.m16449$$Nest$fgetdocumentOnly(r23) == true) goto L62;
            HashMap r24 = new HashMap(PassportActivity.m16446$$Nest$fgetcurrentValues(this.this$0));     // Catch: Exception -> L56
            if (PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0).native_names == true) goto L54;
        L58:
            r24.put("first_name", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].getText().toString());     // Catch: Exception -> L56
            r24.put("middle_name", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().toString());     // Catch: Exception -> L56
            r24.put("last_name", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString());     // Catch: Exception -> L56
            r24.put("birth_date", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[3].getText().toString());     // Catch: Exception -> L56
            r24.put("gender", PassportActivity.m16437$$Nest$fgetcurrentGender(this.this$0));     // Catch: Exception -> L56
            r24.put("country_code", PassportActivity.m16431$$Nest$fgetcurrentCitizeship(this.this$0));     // Catch: Exception -> L56
            r24.put("residence_country_code", PassportActivity.m16443$$Nest$fgetcurrentResidence(this.this$0));     // Catch: Exception -> L56
            r33 = new JSONObject();     // Catch: Exception -> L56
            ArrayList r44 = new ArrayList(r24.keySet());     // Catch: Exception -> L99
            Collections.sort(r44, new PassportActivity$3$$ExternalSyntheticLambda5(this));     // Catch: Exception -> L99
            int r5 = r44.size();     // Catch: Exception -> L99
            int r6 = 0;
        L60:
            if (r6 >= r5) goto L64;
            String r102 = (String) r44.get(r6);     // Catch: Exception -> L99
            r33.put(r102, r24.get(r102));     // Catch: Exception -> L99
            r6 = r6 + 1;     // Catch: Exception -> L99
        L64:
            if (PassportActivity.m16433$$Nest$fgetcurrentDocumentsType(this.this$0) == null) goto L75;
            HashMap r25 = new HashMap(PassportActivity.m16432$$Nest$fgetcurrentDocumentValues(this.this$0));     // Catch: Exception -> L99
            r25.put("document_no", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[7].getText().toString());     // Catch: Exception -> L99
            if (PassportActivity.m16435$$Nest$fgetcurrentExpireDate(this.this$0)[0] == 0) goto L69;
            r25.put("expiry_date", String.format(Locale.US, "%02d.%02d.%d", new Object[]{Integer.valueOf(PassportActivity.m16435$$Nest$fgetcurrentExpireDate(this.this$0)[2]), Integer.valueOf(PassportActivity.m16435$$Nest$fgetcurrentExpireDate(this.this$0)[1]), Integer.valueOf(PassportActivity.m16435$$Nest$fgetcurrentExpireDate(this.this$0)[0])}));     // Catch: Exception -> L99
        L70:
            r43 = new JSONObject();     // Catch: Exception -> L99
            ArrayList r52 = new ArrayList(r25.keySet());     // Catch: Exception -> L98
            Collections.sort(r52, new PassportActivity$3$$ExternalSyntheticLambda6(this));     // Catch: Exception -> L98
            int r62 = r52.size();     // Catch: Exception -> L98
        L72:
            if (r7 >= r62) goto L77;
            String r8 = (String) r52.get(r7);     // Catch: Exception -> L98
            r43.put(r8, r25.get(r8));     // Catch: Exception -> L98
            r7 = r7 + 1;
            goto L72
        L69:
            r25.put("expiry_date", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L99
        L75:
            r43 = null;
            goto L77
        L54:
            if (PassportActivity.m16465$$Nest$fgetnativeInfoCell(this.this$0).getVisibility() != 0) goto L57;
            r24.put("first_name_native", PassportActivity.m16461$$Nest$fgetinputExtraFields(this.this$0)[0].getText().toString());     // Catch: Exception -> L56
            r24.put("middle_name_native", PassportActivity.m16461$$Nest$fgetinputExtraFields(this.this$0)[1].getText().toString());     // Catch: Exception -> L56
            r24.put("last_name_native", PassportActivity.m16461$$Nest$fgetinputExtraFields(this.this$0)[2].getText().toString());     // Catch: Exception -> L56
            goto L58
        L57:
            r24.put("first_name_native", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].getText().toString());     // Catch: Exception -> L56
            r24.put("middle_name_native", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().toString());     // Catch: Exception -> L56
            r24.put("last_name_native", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString());     // Catch: Exception -> L56
            goto L58
        L62:
            r33 = null;
            goto L64
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r21) {
            if (r21 == (-1)) goto L5;
            String r6 = null;
            if (r21 != 1) goto L26;
            if (this.this$0.getParentActivity() == null) goto L103;
            LinkSpanDrawable.LinksTextView r1 = new LinkSpanDrawable.LinksTextView(this.this$0.getParentActivity());
            String r2 = LocaleController.getString(R.string.PassportInfo2);
            SpannableStringBuilder r7 = new SpannableStringBuilder(r2);
            int r9 = r2.indexOf(42);
            int r22 = r2.lastIndexOf(42);
            if (r9 == (-1)) goto L23;
            if (r22 == (-1)) goto L23;
            r7.replace(r22, r22 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r7.replace(r9, r9 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r7.setSpan(new AnonymousClass1(this, LocaleController.getString(R.string.PassportInfoUrl)), r9, r22 - 1, 33);
        L23:
            r1.setText(r7);
            r1.setTextSize(1, 16.0f);
            r1.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
            r1.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection));
            r1.setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
            r1.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
            r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            AlertDialog.Builder r23 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r23.setView(r1);
            r23.setTitle(LocaleController.getString(R.string.PassportInfoTitle));
            r23.setNegativeButton(LocaleController.getString(R.string.Close), null);
            this.this$0.showDialog(r23.create());
            return;
        L103:
            return;
        L26:
            if (r21 != 2) goto L100;
            int r12 = PassportActivity.m16428$$Nest$fgetcurrentActivityType(this.this$0);
            PassportActivity r72 = this.this$0;
            if (r12 != 5) goto L32;
            PassportActivity.m16524$$Nest$monPasswordDone(r72, false);
            return;
        L32:
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(r72) != 7) goto L35;
            PassportActivity.m16483$$Nest$fgetviews(this.this$0)[PassportActivity.m16447$$Nest$fgetcurrentViewNum(this.this$0)].onNextPressed(null);
            return;
        L35:
            PassportActivity$3$$ExternalSyntheticLambda0 r13 = new PassportActivity$3$$ExternalSyntheticLambda0(this);
            AnonymousClass2 r24 = new AnonymousClass2(this, r13);
            int r73 = PassportActivity.m16428$$Nest$fgetcurrentActivityType(this.this$0);
            PassportActivity r8 = this.this$0;
            if (r73 != 4) goto L46;
            boolean r5 = PassportActivity.m16482$$Nest$fgetuseCurrentValue(r8);
            PassportActivity r62 = this.this$0;
            if (r5 == false) goto L42;
            String r3 = PassportActivity.m16434$$Nest$fgetcurrentEmail(r62);
        L45:
            PassportActivity.m16448$$Nest$fgetdelegate(this.this$0).saveValue(PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0), r3, null, null, null, null, null, null, null, null, r13, r24);
        L92:
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(this.this$0, true, true);
            return;
        L42:
            if (PassportActivity.m16510$$Nest$mcheckFieldsForError(r62) == true) goto L104;
            r3 = PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].getText().toString();
            goto L45
        L104:
            return;
        L46:
            int r14 = PassportActivity.m16428$$Nest$fgetcurrentActivityType(r8);
            PassportActivity r25 = this.this$0;
            if (r14 != 3) goto L57;
            boolean r15 = PassportActivity.m16482$$Nest$fgetuseCurrentValue(r25);
            PassportActivity r26 = this.this$0;
            if (r15 == false) goto L53;
            String r16 = UserConfig.getInstance(PassportActivity.access$500(r26)).getCurrentUser().phone;
        L56:
            PassportActivity.m16448$$Nest$fgetdelegate(this.this$0).saveValue(PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0), r16, null, null, null, null, null, null, null, null, r13, r24);
            goto L92
        L53:
            if (PassportActivity.m16510$$Nest$mcheckFieldsForError(r26) == true) goto L105;
            r16 = PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().toString() + PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString();
            goto L56
        L105:
            return;
        L57:
            int r17 = PassportActivity.m16428$$Nest$fgetcurrentActivityType(r25);
            PassportActivity r27 = this.this$0;
            if (r17 != 2) goto L85;
            if (PassportActivity.m16480$$Nest$fgetuploadingDocuments(r27).isEmpty() == true) goto L62;
            return;
        L62:
            if (PassportActivity.m16510$$Nest$mcheckFieldsForError(this.this$0) == true) goto L106;
            boolean r18 = PassportActivity.m16521$$Nest$misHasNotAnyChanges(this.this$0);
            PassportActivity r28 = this.this$0;
            if (r18 == false) goto L98;
            r28.finishFragment();
            return;
        L98:
            if (PassportActivity.m16449$$Nest$fgetdocumentOnly(r28) == true) goto L73;
            JSONObject r19 = new JSONObject();     // Catch: Exception -> L95
            r19.put("street_line1", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].getText().toString());     // Catch: Exception -> L94
            r19.put("street_line2", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().toString());     // Catch: Exception -> L94
            r19.put("post_code", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString());     // Catch: Exception -> L94
            r19.put("city", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[3].getText().toString());     // Catch: Exception -> L94
            r19.put("state", PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[4].getText().toString());     // Catch: Exception -> L94
            r19.put("country_code", PassportActivity.m16431$$Nest$fgetcurrentCitizeship(this.this$0));     // Catch: Exception -> L94
        L75:
            if (PassportActivity.m16457$$Nest$fgetfieldsErrors(this.this$0) == null) goto L78;
            PassportActivity.m16457$$Nest$fgetfieldsErrors(this.this$0).clear();
        L78:
            if (PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0) == null) goto L80;
            PassportActivity.m16452$$Nest$fgetdocumentsErrors(this.this$0).clear();
        L80:
            PassportActivityDelegate r74 = PassportActivity.m16448$$Nest$fgetdelegate(this.this$0);
            TLRPC.TL_secureRequiredType r82 = PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0);
            if (r19 == null) goto L83;
            r6 = r19.toString();
        L83:
            r74.saveValue(r82, null, r6, PassportActivity.m16433$$Nest$fgetcurrentDocumentsType(this.this$0), null, PassportActivity.m16450$$Nest$fgetdocuments(this.this$0), PassportActivity.m16478$$Nest$fgetselfieDocument(this.this$0), PassportActivity.m16479$$Nest$fgettranslationDocuments(this.this$0), null, null, r13, r24);
        L73:
            r19 = null;
            goto L75
        L106:
            return;
        L85:
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(r27) != 1) goto L90;
            if (onIdentityDone(r13, r24) == true) goto L92;
            return;
        L90:
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(this.this$0) != 6) goto L92;
            TL_account.verifyEmail r29 = new TL_account.verifyEmail();
            r29.purpose = new TLRPC.TL_emailVerifyPurposePassport();
            TLRPC.TL_emailVerificationCode r63 = new TLRPC.TL_emailVerificationCode();
            r63.code = PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].getText().toString();
            r29.verification = r63;
            ConnectionsManager.getInstance(PassportActivity.access$800(this.this$0)).bindRequestToGuid(ConnectionsManager.getInstance(PassportActivity.access$600(this.this$0)).sendRequest(r29, new PassportActivity$3$$ExternalSyntheticLambda1(this, r13, r24, r29)), PassportActivity.access$700(this.this$0));
            goto L92
        L100:
            return;
        L5:
            if (PassportActivity.m16508$$Nest$mcheckDiscard(this.this$0, true) == false) goto L8;
            return;
        L8:
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(this.this$0) != 0) goto L10;
        L11:
            PassportActivity.m16507$$Nest$mcallCallback(this.this$0, false);
        L12:
            this.this$0.finishFragment();
            return;
        L10:
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(this.this$0) != 5) goto L12;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ScrollView {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass4(PassportActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            PassportActivity.m16502$$Nest$fputscrollHeight(this.this$0, View.MeasureSpec.getSize(r5) - AndroidUtilities.dp(30.0f));
            super.onMeasure(r4, r5);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup
        public boolean onRequestFocusInDescendants(int r1, Rect r2) {
            return false;
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
            if (PassportActivity.m16447$$Nest$fgetcurrentViewNum(this.this$0) != 1) goto L5;
        L8:
            r4.bottom += AndroidUtilities.dp(40.0f);
        L10:
            return super.requestChildRectangleOnScreen(r3, r4, r5);
        L5:
            if (PassportActivity.m16447$$Nest$fgetcurrentViewNum(this.this$0) == 2) goto L8;
            if (PassportActivity.m16447$$Nest$fgetcurrentViewNum(this.this$0) != 4) goto L10;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ScrollView {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass5(PassportActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup
        public boolean onRequestFocusInDescendants(int r1, Rect r2) {
            return false;
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
            r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
            r5.top += AndroidUtilities.dp(20.0f);
            r5.bottom += AndroidUtilities.dp(50.0f);
            return super.requestChildRectangleOnScreen(r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass6(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (PassportActivity.m16460$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L6;
            return;
        L6:
            if (PassportActivity.m16455$$Nest$fgetemailCodeLength(this.this$0) != 0) goto L8;
            return;
        L8:
            if (PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].length() != PassportActivity.m16455$$Nest$fgetemailCodeLength(this.this$0)) goto L12;
            PassportActivity.m16453$$Nest$fgetdoneItem(this.this$0).callOnClick();
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ActionMode.Callback {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass7(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements RequestDelegate {
        final /* synthetic */ PassportActivity this$0;
        final /* synthetic */ TL_account.getPasswordSettings val$req;
        final /* synthetic */ boolean val$saved;
        final /* synthetic */ String val$textPassword;
        final /* synthetic */ byte[] val$x_bytes;

        /* renamed from: $r8$lambda$-e_NNSTGjv_Z5RM2YascB_-qv3o, reason: not valid java name */
        public static /* synthetic */ void m16536$r8$lambda$e_NNSTGjv_Z5RM2YascB_qv3o(AnonymousClass8 r0) {
            r0.lambda$run$11();
        }

        public static /* synthetic */ void $r8$lambda$1vo6IA4OwWnnpB5EnBf0j9wOVDE(AnonymousClass8 r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$resetSecret$0(r1, r2);
        }

        /* renamed from: $r8$lambda$FBVsq7XZaVjIx3bhO-XDpfX8CaE, reason: not valid java name */
        public static /* synthetic */ void m16537$r8$lambda$FBVsq7XZaVjIx3bhOXDpfX8CaE(AnonymousClass8 r0, TLObject r1, String r2, boolean r3) {
            r0.lambda$run$15(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$GSaA4B4ZwIMeyQWHvPoma64n3WY(AnonymousClass8 r0, byte[] r1, String r2) {
            r0.lambda$generateNewSecret$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Gx53MbFrc_ESn0zvocr0zOByzAY(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$run$13(r1, r2);
        }

        /* renamed from: $r8$lambda$LC7D7wptUA-amHxSdYQmk3SLy6w, reason: not valid java name */
        public static /* synthetic */ void m16538$r8$lambda$LC7D7wptUAamHxSdYQmk3SLy6w(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$resetSecret$1(r1, r2);
        }

        /* renamed from: $r8$lambda$LD1L5cX3uH-EGEm0hZyeTQyyMb4, reason: not valid java name */
        public static /* synthetic */ void m16539$r8$lambda$LD1L5cX3uHEGEm0hZyeTQyyMb4(AnonymousClass8 r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$generateNewSecret$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$VL2KR6kL8gdaCzbfr_gB6MiHThg(AnonymousClass8 r0, TLRPC.TL_error r1) {
            r0.lambda$resetSecret$2(r1);
        }

        /* renamed from: $r8$lambda$Y4YFaNfZV4Bti_bKno-yJT7QdaM, reason: not valid java name */
        public static /* synthetic */ void m16540$r8$lambda$Y4YFaNfZV4Bti_bKnoyJT7QdaM(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$resetSecret$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ZqH0oUc2rMQz279cmpiVSSeUlYY(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$run$12(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_EqkzsIFPLt8G8hHD_DhSgFnVH0(AnonymousClass8 r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$run$10(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$cj08VjAn1Zr62h5vnpME7j18mvc(AnonymousClass8 r0, TL_account.passwordSettings r1, boolean r2, byte[] r3) {
            r0.lambda$run$14(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$dnQdhEiS4zS-urGNs74B4xgjOH0, reason: not valid java name */
        public static /* synthetic */ void m16541$r8$lambda$dnQdhEiS4zSurGNs74B4xgjOH0(AnonymousClass8 r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
            r0.lambda$run$9(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$id-YZIh352X9KSvdca4d0oYmuDg, reason: not valid java name */
        public static /* synthetic */ void m16542$r8$lambda$idYZIh352X9KSvdca4d0oYmuDg(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$generateNewSecret$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$rU021SPxpbMNy9GuIqvuFk4j2HE(AnonymousClass8 r0, boolean r1, TLRPC.TL_error r2) {
            r0.lambda$run$16(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$tvr4NVrA8HxDRKSkd4Ft63ohCBc(AnonymousClass8 r0, TLRPC.TL_error r1) {
            r0.lambda$generateNewSecret$6(r1);
        }

        /* renamed from: $r8$lambda$vINvVdB-VuMxwnbRYxmIcWx6Nvk, reason: not valid java name */
        public static /* synthetic */ void m16543$r8$lambda$vINvVdBVuMxwnbRYxmIcWx6Nvk(AnonymousClass8 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$generateNewSecret$5(r1, r2);
        }

        public AnonymousClass8(PassportActivity r1, boolean r2, byte[] r3, TL_account.getPasswordSettings r4, String r5) {
            this.this$0 = r1;
            this.val$saved = r2;
            this.val$x_bytes = r3;
            this.val$req = r4;
            this.val$textPassword = r5;
        }

        private void generateNewSecret() {
            Utilities.globalQueue.postRunnable(new PassportActivity$8$$ExternalSyntheticLambda13(this, this.val$x_bytes, this.val$textPassword));
        }

        private /* synthetic */ void lambda$generateNewSecret$4(TLRPC.TL_error r1, TLObject r2) {
            if (r1 != null) goto L5;
            PassportActivity.m16489$$Nest$fputcurrentPassword(this.this$0, (TL_account.Password) r2);
            TwoStepVerificationActivity.initPasswordNewAlgo(PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0));
            generateNewSecret();
            return;
        }

        private /* synthetic */ void lambda$generateNewSecret$5(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda4(this, r3, r2));
        }

        private /* synthetic */ void lambda$generateNewSecret$6(TLRPC.TL_error r3) {
            if (r3 == null) goto L8;
            if ("SRP_ID_INVALID".equals(r3.text) == false) goto L8;
            TL_account.getPassword r32 = new TL_account.getPassword();
            ConnectionsManager.getInstance(PassportActivity.access$3300(this.this$0)).sendRequest(r32, new PassportActivity$8$$ExternalSyntheticLambda3(this), 8);
            return;
        L8:
            if (PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0) != null) goto L10;
            PassportActivity.m16487$$Nest$fputcurrentForm(this.this$0, new TL_account.authorizationForm());
        L10:
            openRequestInterface();
        }

        private /* synthetic */ void lambda$generateNewSecret$7(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda0(this, r2));
        }

        private /* synthetic */ void lambda$generateNewSecret$8(byte[] r9, String r10) {
            Utilities.random.setSeed(PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).secure_random);
            TL_account.updatePasswordSettings r0 = new TL_account.updatePasswordSettings();
            if ((PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
            r0.password = SRPHelper.startCheck(r9, PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).srp_id, PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).current_algo);
        L5:
            r0.new_settings = new TL_account.passwordInputSettings();
            PassportActivity r92 = this.this$0;
            PassportActivity.m16503$$Nest$fputsecureSecret(r92, PassportActivity.m16519$$Nest$mgetRandomSecret(r92));
            PassportActivity r93 = this.this$0;
            PassportActivity.m16504$$Nest$fputsecureSecretId(r93, Utilities.bytesToLong(Utilities.computeSHA256(PassportActivity.m16476$$Nest$fgetsecureSecret(r93))));
            if ((PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L8;
            TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 r94 = (TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).new_secure_algo;
            PassportActivity.m16501$$Nest$fputsaltedPassword(this.this$0, Utilities.computePBKDF2(AndroidUtilities.getStringBytes(r10), r94.salt));
            byte[] r2 = new byte[32];
            System.arraycopy(PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$0), 0, r2, 0, 32);
            byte[] r3 = new byte[16];
            System.arraycopy(PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$0), 32, r3, 0, 16);
            Utilities.aesCbcEncryptionByteArraySafe(PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0), r2, r3, 0, PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0).length, 0, 1);
            r0.new_settings.new_secure_settings = new TLRPC.TL_secureSecretSettings();
            TLRPC.TL_secureSecretSettings r102 = r0.new_settings.new_secure_settings;
            r102.secure_algo = r94;
            r102.secure_secret = PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0);
            r0.new_settings.new_secure_settings.secure_secret_id = PassportActivity.m16477$$Nest$fgetsecureSecretId(this.this$0);
            r0.new_settings.flags |= 4;
        L8:
            ConnectionsManager.getInstance(PassportActivity.access$3200(this.this$0)).sendRequest(r0, new PassportActivity$8$$ExternalSyntheticLambda15(this));
        }

        private /* synthetic */ void lambda$resetSecret$0(TLRPC.TL_error r1, TLObject r2) {
            if (r1 != null) goto L5;
            PassportActivity.m16489$$Nest$fputcurrentPassword(this.this$0, (TL_account.Password) r2);
            TwoStepVerificationActivity.initPasswordNewAlgo(PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0));
            resetSecret();
            return;
        }

        private /* synthetic */ void lambda$resetSecret$1(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda2(this, r3, r2));
        }

        private /* synthetic */ void lambda$resetSecret$2(TLRPC.TL_error r3) {
            if (r3 != null) goto L4;
        L7:
            generateNewSecret();
            return;
        L4:
            if ("SRP_ID_INVALID".equals(r3.text) == false) goto L7;
            TL_account.getPassword r32 = new TL_account.getPassword();
            ConnectionsManager.getInstance(PassportActivity.access$3400(this.this$0)).sendRequest(r32, new PassportActivity$8$$ExternalSyntheticLambda1(this), 8);
        }

        private /* synthetic */ void lambda$resetSecret$3(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda16(this, r2));
        }

        private /* synthetic */ void lambda$run$10(boolean r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda10(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$run$11() {
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        }

        private /* synthetic */ void lambda$run$12(TLObject r4, TLRPC.TL_error r5) {
            int r1 = 0;
            if ((r4 instanceof Vector) == false) goto L9;
            PassportActivity.m16487$$Nest$fputcurrentForm(this.this$0, new TL_account.authorizationForm());
            Vector r42 = (Vector) r4;
            int r52 = r42.objects.size();
        L5:
            if (r1 >= r52) goto L7;
            PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0).values.add((TLRPC.TL_secureValue) r42.objects.get(r1));
            r1 = r1 + 1;
            goto L5
        L7:
            openRequestInterface();
            return;
        L9:
            boolean r43 = "APP_VERSION_OUTDATED".equals(r5.text);
            PassportActivity r0 = this.this$0;
            if (r43 == false) goto L12;
            AlertsCreator.showUpdateAppAlert(r0.getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        L13:
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            return;
        L12:
            PassportActivity.m16529$$Nest$mshowAlertWithText(r0, LocaleController.getString(R.string.AppName), r5.text);
            goto L13
        }

        private /* synthetic */ void lambda$run$13(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda14(this, r2, r3));
        }

        private /* synthetic */ void lambda$run$14(TL_account.passwordSettings r5, boolean r6, byte[] r7) {
            PassportActivity.m16486$$Nest$fputcurrentEmail(this.this$0, r5.email);
            if (r6 == false) goto L5;
            PassportActivity r52 = this.this$0;
            PassportActivity.m16501$$Nest$fputsaltedPassword(r52, PassportActivity.m16474$$Nest$fgetsavedSaltedPassword(r52));
        L5:
            PassportActivity r53 = this.this$0;
            if (PassportActivity.checkSecret(PassportActivity.m16513$$Nest$mdecryptSecret(r53, PassportActivity.m16476$$Nest$fgetsecureSecret(r53), PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$0)), Long.valueOf(PassportActivity.m16477$$Nest$fgetsecureSecretId(this.this$0))) == true) goto L8;
        L18:
            PassportActivity r54 = this.this$0;
            if (r6 == false) goto L23;
            UserConfig.getInstance(PassportActivity.access$3000(r54)).resetSavedPassword();
            PassportActivity.m16506$$Nest$fputusingSavedPassword(this.this$0, 0);
            PassportActivity.m16532$$Nest$mupdatePasswordInterface(this.this$0);
            return;
        L23:
            if (PassportActivity.m16436$$Nest$fgetcurrentForm(r54) == null) goto L26;
            PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0).values.clear();
            PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0).errors.clear();
        L26:
            if (PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0) != null) goto L28;
        L32:
            generateNewSecret();
            return;
        L28:
            if (PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0).length == 0) goto L32;
            resetSecret();
            return;
        L8:
            if (r7.length == 0) goto L18;
            if (PassportActivity.m16477$$Nest$fgetsecureSecretId(this.this$0) == 0) goto L18;
            if (PassportActivity.m16429$$Nest$fgetcurrentBotId(this.this$0) != 0) goto L16;
            TL_account.getAllSecureValues r55 = new TL_account.getAllSecureValues();
            ConnectionsManager.getInstance(PassportActivity.access$3100(this.this$0)).sendRequest(r55, new PassportActivity$8$$ExternalSyntheticLambda11(this));
            return;
        L16:
            openRequestInterface();
        }

        private /* synthetic */ void lambda$run$15(TLObject r6, String r7, boolean r8) {
            TL_account.passwordSettings r62 = (TL_account.passwordSettings) r6;
            TLRPC.TL_secureSecretSettings r0 = r62.secure_settings;
            PassportActivity r1 = this.this$0;
            if (r0 == null) goto L16;
            PassportActivity.m16503$$Nest$fputsecureSecret(r1, r0.secure_secret);
            PassportActivity.m16504$$Nest$fputsecureSecretId(this.this$0, r62.secure_settings.secure_secret_id);
            TLRPC.SecurePasswordKdfAlgo r02 = r62.secure_settings.secure_algo;
            if ((r02 instanceof TLRPC.TL_securePasswordKdfAlgoSHA512) == false) goto L8;
            byte[] r03 = ((TLRPC.TL_securePasswordKdfAlgoSHA512) r02).salt;
            PassportActivity.m16501$$Nest$fputsaltedPassword(this.this$0, Utilities.computeSHA512(r03, AndroidUtilities.getStringBytes(r7), r03));
        L20:
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda9(this, r62, r8, r03));
            return;
        L8:
            if ((r02 instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L11;
            TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 r04 = (TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) r02;
            byte[] r12 = r04.salt;
            PassportActivity.m16501$$Nest$fputsaltedPassword(this.this$0, Utilities.computePBKDF2(AndroidUtilities.getStringBytes(r7), r04.salt));
            r03 = r12;
            goto L20
        L11:
            if ((r02 instanceof TLRPC.TL_securePasswordKdfAlgoUnknown) == false) goto L14;
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda8(this));
            return;
        L14:
            r03 = new byte[0];
            goto L20
        L16:
            if ((PassportActivity.m16439$$Nest$fgetcurrentPassword(r1).new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L18;
            TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 r05 = (TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).new_secure_algo;
            byte[] r13 = r05.salt;
            PassportActivity.m16501$$Nest$fputsaltedPassword(this.this$0, Utilities.computePBKDF2(AndroidUtilities.getStringBytes(r7), r05.salt));
            r03 = r13;
        L19:
            PassportActivity.m16503$$Nest$fputsecureSecret(this.this$0, null);
            PassportActivity.m16504$$Nest$fputsecureSecretId(this.this$0, 0);
            goto L20
        L18:
            r03 = new byte[0];
            goto L19
        }

        private /* synthetic */ void lambda$run$16(boolean r4, TLRPC.TL_error r5) {
            PassportActivity r0 = this.this$0;
            if (r4 == false) goto L10;
            UserConfig.getInstance(PassportActivity.access$2900(r0)).resetSavedPassword();
            PassportActivity.m16506$$Nest$fputusingSavedPassword(this.this$0, 0);
            PassportActivity.m16532$$Nest$mupdatePasswordInterface(this.this$0);
            if (PassportActivity.m16462$$Nest$fgetinputFieldContainers(this.this$0) != null) goto L7;
            return;
        L7:
            if (PassportActivity.m16462$$Nest$fgetinputFieldContainers(this.this$0)[0].getVisibility() != 0) goto L25;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].requestFocus();
            AndroidUtilities.showKeyboard(PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0]);
            return;
        L25:
            return;
        L10:
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(r0, true, false);
            if (r5.text.equals("PASSWORD_HASH_INVALID") == false) goto L15;
            PassportActivity.m16523$$Nest$monPasscodeError(this.this$0, true);
            return;
        L15:
            if (r5.text.startsWith("FLOOD_WAIT") == false) goto L22;
            int r42 = Utilities.parseInt(r5.text);
            if (r42 >= 60) goto L19;
            String r43 = LocaleController.formatPluralString("Seconds", r42, new Object[0]);
        L20:
            PassportActivity.m16529$$Nest$mshowAlertWithText(this.this$0, LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r43}));
            return;
        L19:
            r43 = LocaleController.formatPluralString("Minutes", r42 / 60, new Object[0]);
            goto L20
        L22:
            PassportActivity.m16529$$Nest$mshowAlertWithText(this.this$0, LocaleController.getString(R.string.AppName), r5.text);
        }

        private /* synthetic */ void lambda$run$9(TLRPC.TL_error r1, TLObject r2, boolean r3) {
            if (r1 != null) goto L5;
            PassportActivity.m16489$$Nest$fputcurrentPassword(this.this$0, (TL_account.Password) r2);
            TwoStepVerificationActivity.initPasswordNewAlgo(PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0));
            PassportActivity.m16524$$Nest$monPasswordDone(this.this$0, r3);
            return;
        }

        private void openRequestInterface() {
            if (PassportActivity.m16463$$Nest$fgetinputFields(this.this$0) != null) goto L6;
            return;
        L6:
            if (this.val$saved == true) goto L8;
            UserConfig.getInstance(PassportActivity.access$2200(this.this$0)).savePassword(this.val$x_bytes, PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$0));
        L8:
            int r1 = 0;
            AndroidUtilities.hideKeyboard(PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0]);
            PassportActivity.m16495$$Nest$fputignoreOnFailure(this.this$0, true);
            if (PassportActivity.m16429$$Nest$fgetcurrentBotId(this.this$0) != 0) goto L11;
            r1 = 8;
        L11:
            PassportActivity r3 = new PassportActivity(r1, PassportActivity.m16429$$Nest$fgetcurrentBotId(this.this$0), PassportActivity.m16444$$Nest$fgetcurrentScope(this.this$0), PassportActivity.m16442$$Nest$fgetcurrentPublicKey(this.this$0), PassportActivity.m16440$$Nest$fgetcurrentPayload(this.this$0), PassportActivity.m16438$$Nest$fgetcurrentNonce(this.this$0), PassportActivity.m16430$$Nest$fgetcurrentCallbackUrl(this.this$0), PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0), PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0));
            PassportActivity.m16486$$Nest$fputcurrentEmail(r3, PassportActivity.m16434$$Nest$fgetcurrentEmail(this.this$0));
            PassportActivity.access$2302(r3, PassportActivity.access$2400(this.this$0));
            PassportActivity.m16501$$Nest$fputsaltedPassword(r3, PassportActivity.m16473$$Nest$fgetsaltedPassword(this.this$0));
            PassportActivity.m16503$$Nest$fputsecureSecret(r3, PassportActivity.m16476$$Nest$fgetsecureSecret(this.this$0));
            PassportActivity.m16504$$Nest$fputsecureSecretId(r3, PassportActivity.m16477$$Nest$fgetsecureSecretId(this.this$0));
            PassportActivity.m16498$$Nest$fputneedActivityResult(r3, PassportActivity.m16466$$Nest$fgetneedActivityResult(this.this$0));
            if (PassportActivity.access$2500(this.this$0) != null) goto L14;
        L18:
            this.this$0.presentFragment(r3, true);
            return;
        L14:
            if (PassportActivity.access$2600(this.this$0).checkTransitionAnimation() == false) goto L18;
            PassportActivity.m16499$$Nest$fputpresentAfterAnimation(this.this$0, r3);
        }

        private void resetSecret() {
            TL_account.updatePasswordSettings r0 = new TL_account.updatePasswordSettings();
            if ((PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
            r0.password = SRPHelper.startCheck(this.val$x_bytes, PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).srp_id, PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) PassportActivity.m16439$$Nest$fgetcurrentPassword(this.this$0).current_algo);
        L5:
            TL_account.passwordInputSettings r1 = new TL_account.passwordInputSettings();
            r0.new_settings = r1;
            r1.new_secure_settings = new TLRPC.TL_secureSecretSettings();
            TLRPC.TL_secureSecretSettings r12 = r0.new_settings.new_secure_settings;
            r12.secure_secret = new byte[0];
            r12.secure_algo = new TLRPC.TL_securePasswordKdfAlgoUnknown();
            TL_account.passwordInputSettings r02 = r0.new_settings;
            r02.new_secure_settings.secure_secret_id = 0;
            r02.flags |= 4;
            ConnectionsManager.getInstance(PassportActivity.access$2700(this.this$0)).sendRequest(this.val$req, new PassportActivity$8$$ExternalSyntheticLambda12(this));
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r4, TLRPC.TL_error r5) {
            if (r5 != null) goto L4;
        L7:
            if (r5 != null) goto L10;
            Utilities.globalQueue.postRunnable(new PassportActivity$8$$ExternalSyntheticLambda6(this, r4, this.val$textPassword, this.val$saved));
            return;
        L10:
            AndroidUtilities.runOnUIThread(new PassportActivity$8$$ExternalSyntheticLambda7(this, this.val$saved, r5));
            return;
        L4:
            if ("SRP_ID_INVALID".equals(r5.text) == false) goto L7;
            TL_account.getPassword r42 = new TL_account.getPassword();
            ConnectionsManager.getInstance(PassportActivity.access$2800(this.this$0)).sendRequest(r42, new PassportActivity$8$$ExternalSyntheticLambda5(this, this.val$saved), 8);
        }
    }

    /* renamed from: org.telegram.ui.PassportActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements TextWatcher {
        final /* synthetic */ PassportActivity this$0;

        public AnonymousClass9(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r10) {
            if (PassportActivity.m16460$$Nest$fgetignoreOnTextChange(this.this$0) == false) goto L5;
            return;
        L5:
            PassportActivity.m16497$$Nest$fputignoreOnTextChange(this.this$0, true);
            String r102 = PhoneFormat.stripExceptNumbers(PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().toString());
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].setText(r102);
            HintEditText r1 = (HintEditText) PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2];
            if (r102.length() != 0) goto L8;
            r1.setHintText(null);
            r1.setHint(LocaleController.getString(R.string.PaymentShippingPhoneNumber));
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].setText(LocaleController.getString(R.string.ChooseCountry));
        L31:
            PassportActivity.m16497$$Nest$fputignoreOnTextChange(this.this$0, false);
            return;
        L8:
            int r6 = 4;
            if (r102.length() <= 4) goto L18;
        L10:
            if (r6 < 1) goto L15;
            String r3 = r102.substring(0, r6);
            if (((String) PassportActivity.m16426$$Nest$fgetcodesMap(this.this$0).get(r3)) != null) goto L13;
            r6 = r6 - 1;
            goto L10
        L13:
            String r103 = r102.substring(r6) + PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString();
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].setText(r3);
            String r32 = r103;
            r102 = r3;
            boolean r62 = true;
        L16:
            if (r62 == true) goto L19;
            r32 = r102.substring(1) + PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[2].getText().toString();
            EditTextBoldCursor r2 = PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1];
            r102 = r102.substring(0, 1);
            r2.setText(r102);
        L19:
            String r22 = (String) PassportActivity.m16426$$Nest$fgetcodesMap(this.this$0).get(r102);
            if (r22 == null) goto L26;
            int r23 = PassportActivity.m16427$$Nest$fgetcountriesArray(this.this$0).indexOf(r22);
            if (r23 == (-1)) goto L26;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].setText((CharSequence) PassportActivity.m16427$$Nest$fgetcountriesArray(this.this$0).get(r23));
            String r104 = (String) PassportActivity.m16468$$Nest$fgetphoneFormatMap(this.this$0).get(r102);
            if (r104 == null) goto L27;
            r1.setHintText(r104.replace('X', Typography.ndash));
            r1.setHint(null);
        L27:
            if (r62 == true) goto L29;
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].setSelection(PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[1].getText().length());
        L29:
            if (r32 == null) goto L31;
            r1.requestFocus();
            r1.setText(r32);
            r1.setSelection(r1.length());
        L26:
            r1.setHintText(null);
            r1.setHint(LocaleController.getString(R.string.PaymentShippingPhoneNumber));
            PassportActivity.m16463$$Nest$fgetinputFields(this.this$0)[0].setText(LocaleController.getString(R.string.WrongCountry));
            goto L27
        L15:
            r32 = null;
            r62 = false;
            goto L16
        L18:
            r32 = null;
            r62 = false;
            goto L19
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static class EncryptionResult {
        byte[] decrypyedFileSecret;
        byte[] encryptedData;
        byte[] fileHash;
        byte[] fileSecret;
        SecureDocumentKey secureDocumentKey;

        public EncryptionResult(byte[] r1, byte[] r2, byte[] r3, byte[] r4, byte[] r5, byte[] r6) {
            this.encryptedData = r1;
            this.fileSecret = r2;
            this.fileHash = r4;
            this.decrypyedFileSecret = r3;
            this.secureDocumentKey = new SecureDocumentKey(r5, r6);
        }
    }

    public interface ErrorRunnable {
        void onError(String r1, String r2);
    }

    public class LinkSpan extends ClickableSpan {
        final /* synthetic */ PassportActivity this$0;

        public LinkSpan(PassportActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Browser.openUrl(this.this$0.getParentActivity(), PassportActivity.m16436$$Nest$fgetcurrentForm(this.this$0).privacy_policy_url);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(true);
            r1.setTypeface(AndroidUtilities.bold());
        }
    }

    public interface PassportActivityDelegate {
        void deleteValue(TLRPC.TL_secureRequiredType r1, TLRPC.TL_secureRequiredType r2, ArrayList<TLRPC.TL_secureRequiredType> r3, boolean r4, Runnable r5, ErrorRunnable r6);

        SecureDocument saveFile(TLRPC.TL_secureFile r1);

        void saveValue(TLRPC.TL_secureRequiredType r1, String r2, String r3, TLRPC.TL_secureRequiredType r4, String r5, ArrayList<SecureDocument> r6, SecureDocument r7, ArrayList<SecureDocument> r8, SecureDocument r9, SecureDocument r10, Runnable r11, ErrorRunnable r12);
    }

    public class PhoneConfirmationView extends SlideView implements NotificationCenter.NotificationCenterDelegate {
        private ImageView blackImageView;
        private ImageView blueImageView;
        private EditTextBoldCursor[] codeField;
        private LinearLayout codeFieldContainer;
        private int codeTime;
        private Timer codeTimer;
        private TextView confirmTextView;
        private Bundle currentParams;
        private boolean ignoreOnTextChange;
        private double lastCodeTime;
        private double lastCurrentTime;
        private String lastError;
        private int length;
        private boolean nextPressed;
        private int nextType;
        private String pattern;
        private String phone;
        private String phoneHash;
        private TextView problemText;
        private ProgressView progressView;
        final /* synthetic */ PassportActivity this$0;
        private int time;
        private TextView timeText;
        private Timer timeTimer;
        private int timeout;
        private final Object timerSync;
        private TextView titleTextView;
        private int verificationType;
        private boolean waitingForEvent;

        /* renamed from: org.telegram.ui.PassportActivity$PhoneConfirmationView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ PhoneConfirmationView this$1;
            final /* synthetic */ PassportActivity val$this$0;

            public AnonymousClass1(PhoneConfirmationView r1, Context r2, PassportActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.PassportActivity$PhoneConfirmationView$2, reason: invalid class name */
        public class AnonymousClass2 extends TextView {
            final /* synthetic */ PhoneConfirmationView this$1;
            final /* synthetic */ PassportActivity val$this$0;

            public AnonymousClass2(PhoneConfirmationView r1, Context r2, PassportActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.PassportActivity$PhoneConfirmationView$3, reason: invalid class name */
        public class AnonymousClass3 implements TextWatcher {
            final /* synthetic */ PhoneConfirmationView this$1;
            final /* synthetic */ int val$num;

            public AnonymousClass3(PhoneConfirmationView r1, int r2) {
                this.this$1 = r1;
                this.val$num = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r8) {
                if (PhoneConfirmationView.m16550$$Nest$fgetignoreOnTextChange(this.this$1) == true) goto L35;
                int r0 = r8.length();
                if (r0 < 1) goto L31;
                if (r0 <= 1) goto L17;
                String r2 = r8.toString();
                PhoneConfirmationView.m16565$$Nest$fputignoreOnTextChange(this.this$1, true);
                int r4 = 0;
            L10:
                if (r4 >= Math.min(PhoneConfirmationView.m16553$$Nest$fgetlength(this.this$1) - this.val$num, r0)) goto L15;
                if (r4 != 0) goto L13;
                r8.replace(0, r0, r2.substring(r4, r4 + 1));
            L14:
                r4 = r4 + 1;
                goto L10
            L13:
                PhoneConfirmationView.m16548$$Nest$fgetcodeField(this.this$1)[this.val$num + r4].setText(r2.substring(r4, r4 + 1));
                goto L14
            L15:
                PhoneConfirmationView.m16565$$Nest$fputignoreOnTextChange(this.this$1, false);
            L17:
                if (this.val$num == (PhoneConfirmationView.m16553$$Nest$fgetlength(this.this$1) - 1)) goto L20;
                PhoneConfirmationView.m16548$$Nest$fgetcodeField(this.this$1)[this.val$num + 1].setSelection(PhoneConfirmationView.m16548$$Nest$fgetcodeField(this.this$1)[this.val$num + 1].length());
                PhoneConfirmationView.m16548$$Nest$fgetcodeField(this.this$1)[this.val$num + 1].requestFocus();
            L20:
                if (this.val$num == (PhoneConfirmationView.m16553$$Nest$fgetlength(this.this$1) - 1)) goto L25;
                if (this.val$num != (PhoneConfirmationView.m16553$$Nest$fgetlength(this.this$1) - 2)) goto L32;
                if (r0 >= 2) goto L25;
                return;
            L32:
                return;
            L25:
                if (PhoneConfirmationView.m16574$$Nest$mgetCode(this.this$1).length() != PhoneConfirmationView.m16553$$Nest$fgetlength(this.this$1)) goto L34;
                this.this$1.onNextPressed(null);
                return;
            L34:
                return;
            L31:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.PassportActivity$PhoneConfirmationView$4, reason: invalid class name */
        public class AnonymousClass4 extends TimerTask {
            final /* synthetic */ PhoneConfirmationView this$1;

            public static /* synthetic */ void $r8$lambda$0F3N91GVJ4Wn8lA3myMes5fv48c(AnonymousClass4 r0) {
                r0.lambda$run$0();
            }

            public AnonymousClass4(PhoneConfirmationView r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0() {
                double r0 = System.currentTimeMillis();
                double r2 = r0 - PhoneConfirmationView.m16551$$Nest$fgetlastCodeTime(this.this$1);
                PhoneConfirmationView.m16566$$Nest$fputlastCodeTime(this.this$1, r0);
                PhoneConfirmationView.m16564$$Nest$fputcodeTime(this.this$1, (int) (PhoneConfirmationView.m16549$$Nest$fgetcodeTime(r0) - r2));
                if (PhoneConfirmationView.m16549$$Nest$fgetcodeTime(this.this$1) > 1000) goto L6;
                PhoneConfirmationView.m16557$$Nest$fgetproblemText(this.this$1).setVisibility(0);
                PhoneConfirmationView.m16560$$Nest$fgettimeText(this.this$1).setVisibility(8);
                PhoneConfirmationView.m16572$$Nest$mdestroyCodeTimer(this.this$1);
                return;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AndroidUtilities.runOnUIThread(new PassportActivity$PhoneConfirmationView$4$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.PassportActivity$PhoneConfirmationView$5, reason: invalid class name */
        public class AnonymousClass5 extends TimerTask {
            final /* synthetic */ PhoneConfirmationView this$1;

            public static /* synthetic */ void $r8$lambda$2CAoflXXl4uDJNEINa5czEw3Z7w(AnonymousClass5 r0) {
                r0.lambda$run$2();
            }

            public static /* synthetic */ void $r8$lambda$HXQAwzEcuOwEMQ7wNIda0c6VE2w(AnonymousClass5 r0, TLObject r1, TLRPC.TL_error r2) {
                r0.lambda$run$1(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$WabYed7qM7qHtdz6oFm6WYHTwQQ(AnonymousClass5 r0, TLRPC.TL_error r1) {
                r0.lambda$run$0(r1);
            }

            public AnonymousClass5(PhoneConfirmationView r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0(TLRPC.TL_error r1) {
                PhoneConfirmationView.m16568$$Nest$fputlastError(this.this$1, r1.text);
            }

            private /* synthetic */ void lambda$run$1(TLObject r1, TLRPC.TL_error r2) {
                if (r2 != null) goto L4;
                return;
            L4:
                if (r2.text == null) goto L8;
                AndroidUtilities.runOnUIThread(new PassportActivity$PhoneConfirmationView$5$$ExternalSyntheticLambda2(this, r2));
                return;
            }

            private /* synthetic */ void lambda$run$2() {
                int r0 = PhoneConfirmationView.m16559$$Nest$fgettime(this.this$1);
                PhoneConfirmationView r1 = this.this$1;
                if (r0 < 1000) goto L18;
                int r02 = (PhoneConfirmationView.m16559$$Nest$fgettime(r1) / 1000) / 60;
                int r12 = (PhoneConfirmationView.m16559$$Nest$fgettime(this.this$1) / 1000) - (r02 * 60);
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) != 4) goto L7;
            L12:
                PhoneConfirmationView.m16560$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString("CallText", R.string.CallText, new Object[]{Integer.valueOf(r02), Integer.valueOf(r12)}));
            L14:
                if (PhoneConfirmationView.m16558$$Nest$fgetprogressView(this.this$1) == null) goto L43;
                PhoneConfirmationView.m16558$$Nest$fgetprogressView(this.this$1).setProgress(1.0f - (PhoneConfirmationView.m16559$$Nest$fgettime(this.this$1) / PhoneConfirmationView.m16562$$Nest$fgettimeout(this.this$1)));
                return;
            L43:
                return;
            L7:
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) == 3) goto L12;
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) != 2) goto L14;
                PhoneConfirmationView.m16560$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString("SmsText", R.string.SmsText, new Object[]{Integer.valueOf(r02), Integer.valueOf(r12)}));
                goto L14
            L18:
                if (PhoneConfirmationView.m16558$$Nest$fgetprogressView(r1) == null) goto L20;
                PhoneConfirmationView.m16558$$Nest$fgetprogressView(this.this$1).setProgress(1.0f);
            L20:
                PhoneConfirmationView.m16573$$Nest$mdestroyTimer(this.this$1);
                if (PhoneConfirmationView.m16563$$Nest$fgetverificationType(this.this$1) != 3) goto L25;
                AndroidUtilities.setWaitingForCall(false);
                NotificationCenter.getGlobalInstance().removeObserver(this.this$1, NotificationCenter.didReceiveCall);
                PhoneConfirmationView.m16570$$Nest$fputwaitingForEvent(this.this$1, false);
                PhoneConfirmationView.m16572$$Nest$mdestroyCodeTimer(this.this$1);
                PhoneConfirmationView.m16575$$Nest$mresendCode(this.this$1);
                return;
            L25:
                if (PhoneConfirmationView.m16563$$Nest$fgetverificationType(this.this$1) == 2) goto L29;
                if (PhoneConfirmationView.m16563$$Nest$fgetverificationType(this.this$1) == 4) goto L29;
                return;
            L29:
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) != 4) goto L31;
            L37:
                int r03 = PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1);
                PhoneConfirmationView r13 = this.this$1;
                if (r03 != 4) goto L40;
                PhoneConfirmationView.m16560$$Nest$fgettimeText(r13).setText(LocaleController.getString(R.string.Calling));
            L41:
                PhoneConfirmationView.m16571$$Nest$mcreateCodeTimer(this.this$1);
                TLRPC.TL_auth_resendCode r04 = new TLRPC.TL_auth_resendCode();
                r04.phone_number = PhoneConfirmationView.m16555$$Nest$fgetphone(this.this$1);
                r04.phone_code_hash = PhoneConfirmationView.m16556$$Nest$fgetphoneHash(this.this$1);
                ConnectionsManager.getInstance(PassportActivity.access$1700(this.this$1.this$0)).sendRequest(r04, new PassportActivity$PhoneConfirmationView$5$$ExternalSyntheticLambda1(this), 2);
                return;
            L40:
                PhoneConfirmationView.m16560$$Nest$fgettimeText(r13).setText(LocaleController.getString(R.string.SendingSms));
                goto L41
            L31:
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) == 2) goto L37;
                if (PhoneConfirmationView.m16554$$Nest$fgetnextType(this.this$1) != 3) goto L45;
                AndroidUtilities.setWaitingForSms(false);
                NotificationCenter.getGlobalInstance().removeObserver(this.this$1, NotificationCenter.didReceiveSmsCode);
                PhoneConfirmationView.m16570$$Nest$fputwaitingForEvent(this.this$1, false);
                PhoneConfirmationView.m16572$$Nest$mdestroyCodeTimer(this.this$1);
                PhoneConfirmationView.m16575$$Nest$mresendCode(this.this$1);
                return;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (PhoneConfirmationView.m16561$$Nest$fgettimeTimer(this.this$1) != null) goto L5;
                return;
            L5:
                double r0 = System.currentTimeMillis();
                double r2 = r0 - PhoneConfirmationView.m16552$$Nest$fgetlastCurrentTime(this.this$1);
                PhoneConfirmationView.m16569$$Nest$fputtime(this.this$1, (int) (PhoneConfirmationView.m16559$$Nest$fgettime(r4) - r2));
                PhoneConfirmationView.m16567$$Nest$fputlastCurrentTime(this.this$1, r0);
                AndroidUtilities.runOnUIThread(new PassportActivity$PhoneConfirmationView$5$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: $r8$lambda$3W23ILXHfRNKRaHv1K1aS-QXDFw, reason: not valid java name */
        public static /* synthetic */ boolean m16544$r8$lambda$3W23ILXHfRNKRaHv1K1aSQXDFw(PhoneConfirmationView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$setParams$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$8aLptDHmXzWfmAqxQEK9O6RtNxo(PhoneConfirmationView r0, Bundle r1, TLRPC.TL_auth_resendCode r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$resendCode$3(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$9YQy5-2cEHkdJe_AXe0__2SYJZQ, reason: not valid java name */
        public static /* synthetic */ boolean m16545$r8$lambda$9YQy52cEHkdJe_AXe0__2SYJZQ(PhoneConfirmationView r0, int r1, View r2, int r3, KeyEvent r4) {
            return r0.lambda$setParams$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$FicRrCYzJYKw_2aGYdV7uFyhROU(PhoneConfirmationView r0, TLRPC.TL_error r1, TL_account.verifyPhone r2) {
            r0.lambda$onNextPressed$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$KiMOQPMGlZ62ZIOSBuxpcRQm0dE(PhoneConfirmationView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$Q3JWWB1UxyvFNruVD2eiN2jKxYk(PhoneConfirmationView r0, TLRPC.TL_error r1, Bundle r2, TLObject r3, TLRPC.TL_auth_resendCode r4) {
            r0.lambda$resendCode$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$cPtcKc5geSoQ8ePvIlQ9KhotnZc(PhoneConfirmationView r0, AlertDialog r1, int r2) {
            r0.lambda$onBackPressed$8(r1, r2);
        }

        /* renamed from: $r8$lambda$eC3Xpafa9YA7wGWrb-dQXPGPB6A, reason: not valid java name */
        public static /* synthetic */ void m16546$r8$lambda$eC3Xpafa9YA7wGWrbdQXPGPB6A(PhoneConfirmationView r0, TL_account.verifyPhone r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$7(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$mNLGj_ZSSCgQ8GzzAPD9zg820oo(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: $r8$lambda$tnHuzt8anDJMNhQocG35ljZ-d28, reason: not valid java name */
        public static /* synthetic */ void m16547$r8$lambda$tnHuzt8anDJMNhQocG35ljZd28(PhoneConfirmationView r0, AlertDialog r1, int r2) {
            r0.lambda$resendCode$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcodeField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m16548$$Nest$fgetcodeField(PhoneConfirmationView r0) {
            return r0.codeField;
        }

        /* renamed from: -$$Nest$fgetcodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16549$$Nest$fgetcodeTime(PhoneConfirmationView r0) {
            return r0.codeTime;
        }

        /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m16550$$Nest$fgetignoreOnTextChange(PhoneConfirmationView r0) {
            return r0.ignoreOnTextChange;
        }

        /* renamed from: -$$Nest$fgetlastCodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ double m16551$$Nest$fgetlastCodeTime(PhoneConfirmationView r2) {
            return r2.lastCodeTime;
        }

        /* renamed from: -$$Nest$fgetlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ double m16552$$Nest$fgetlastCurrentTime(PhoneConfirmationView r2) {
            return r2.lastCurrentTime;
        }

        /* renamed from: -$$Nest$fgetlength, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16553$$Nest$fgetlength(PhoneConfirmationView r0) {
            return r0.length;
        }

        /* renamed from: -$$Nest$fgetnextType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16554$$Nest$fgetnextType(PhoneConfirmationView r0) {
            return r0.nextType;
        }

        /* renamed from: -$$Nest$fgetphone, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m16555$$Nest$fgetphone(PhoneConfirmationView r0) {
            return r0.phone;
        }

        /* renamed from: -$$Nest$fgetphoneHash, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m16556$$Nest$fgetphoneHash(PhoneConfirmationView r0) {
            return r0.phoneHash;
        }

        /* renamed from: -$$Nest$fgetproblemText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m16557$$Nest$fgetproblemText(PhoneConfirmationView r0) {
            return r0.problemText;
        }

        /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProgressView m16558$$Nest$fgetprogressView(PhoneConfirmationView r0) {
            return r0.progressView;
        }

        /* renamed from: -$$Nest$fgettime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16559$$Nest$fgettime(PhoneConfirmationView r0) {
            return r0.time;
        }

        /* renamed from: -$$Nest$fgettimeText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m16560$$Nest$fgettimeText(PhoneConfirmationView r0) {
            return r0.timeText;
        }

        /* renamed from: -$$Nest$fgettimeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Timer m16561$$Nest$fgettimeTimer(PhoneConfirmationView r0) {
            return r0.timeTimer;
        }

        /* renamed from: -$$Nest$fgettimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16562$$Nest$fgettimeout(PhoneConfirmationView r0) {
            return r0.timeout;
        }

        /* renamed from: -$$Nest$fgetverificationType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m16563$$Nest$fgetverificationType(PhoneConfirmationView r0) {
            return r0.verificationType;
        }

        /* renamed from: -$$Nest$fputcodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16564$$Nest$fputcodeTime(PhoneConfirmationView r0, int r1) {
            r0.codeTime = r1;
        }

        /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16565$$Nest$fputignoreOnTextChange(PhoneConfirmationView r0, boolean r1) {
            r0.ignoreOnTextChange = r1;
        }

        /* renamed from: -$$Nest$fputlastCodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16566$$Nest$fputlastCodeTime(PhoneConfirmationView r0, double r1) {
            r0.lastCodeTime = r1;
        }

        /* renamed from: -$$Nest$fputlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16567$$Nest$fputlastCurrentTime(PhoneConfirmationView r0, double r1) {
            r0.lastCurrentTime = r1;
        }

        /* renamed from: -$$Nest$fputlastError, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16568$$Nest$fputlastError(PhoneConfirmationView r0, String r1) {
            r0.lastError = r1;
        }

        /* renamed from: -$$Nest$fputtime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16569$$Nest$fputtime(PhoneConfirmationView r0, int r1) {
            r0.time = r1;
        }

        /* renamed from: -$$Nest$fputwaitingForEvent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16570$$Nest$fputwaitingForEvent(PhoneConfirmationView r0, boolean r1) {
            r0.waitingForEvent = r1;
        }

        /* renamed from: -$$Nest$mcreateCodeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16571$$Nest$mcreateCodeTimer(PhoneConfirmationView r0) {
            r0.createCodeTimer();
        }

        /* renamed from: -$$Nest$mdestroyCodeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16572$$Nest$mdestroyCodeTimer(PhoneConfirmationView r0) {
            r0.destroyCodeTimer();
        }

        /* renamed from: -$$Nest$mdestroyTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16573$$Nest$mdestroyTimer(PhoneConfirmationView r0) {
            r0.destroyTimer();
        }

        /* renamed from: -$$Nest$mgetCode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m16574$$Nest$mgetCode(PhoneConfirmationView r0) {
            return r0.getCode();
        }

        /* renamed from: -$$Nest$mresendCode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16575$$Nest$mresendCode(PhoneConfirmationView r0) {
            r0.resendCode();
        }

        public PhoneConfirmationView(PassportActivity r24, Context r25, int r26) {
            this.this$0 = r24;
            super(r25);
            this.timerSync = new Object();
            this.time = 60000;
            this.codeTime = 15000;
            this.lastError = _UrlKt.FRAGMENT_ENCODE_SET;
            this.pattern = "*";
            this.verificationType = r26;
            setOrientation(1);
            TextView r4 = new TextView(r25);
            this.confirmTextView = r4;
            int r5 = Theme.key_windowBackgroundWhiteGrayText6;
            r4.setTextColor(Theme.getColor(r5));
            this.confirmTextView.setTextSize(1, 14.0f);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            TextView r42 = new TextView(r25);
            this.titleTextView = r42;
            int r8 = Theme.key_windowBackgroundWhiteBlackText;
            r42.setTextColor(Theme.getColor(r8));
            this.titleTextView.setTextSize(1, 18.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            TextView r43 = this.titleTextView;
            int r11 = 5;
            if (LocaleController.isRTL == false) goto L5;
            int r10 = 5;
        L6:
            r43.setGravity(r10);
            this.titleTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.titleTextView.setGravity(49);
            int r44 = this.verificationType;
            TextView r13 = this.confirmTextView;
            if (r44 == 3) goto L9;
            r13.setGravity(49);
            FrameLayout r45 = new FrameLayout(r25);
            addView(r45, LayoutHelper.createLinear(-2, -2, 49));
            if (this.verificationType != 1) goto L32;
            ImageView r132 = new ImageView(r25);
            this.blackImageView = r132;
            r132.setImageResource(R.drawable.sms_devices);
            ImageView r133 = this.blackImageView;
            int r82 = Theme.getColor(r8);
            float r262 = 2.0f;
            PorterDuff.Mode r7 = PorterDuff.Mode.MULTIPLY;
            r133.setColorFilter(new PorterDuffColorFilter(r82, r7));
            r45.addView(this.blackImageView, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
            ImageView r83 = new ImageView(r25);
            this.blueImageView = r83;
            r83.setImageResource(R.drawable.sms_bubble);
            this.blueImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionBackground), r7));
            r45.addView(this.blueImageView, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
            this.titleTextView.setText(LocaleController.getString(R.string.SentAppCodeTitle));
        L33:
            addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 49, 0, 18, 0, 0));
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 49, 0, 17, 0, 0));
        L34:
            LinearLayout r46 = new LinearLayout(r25);
            this.codeFieldContainer = r46;
            r46.setOrientation(0);
            addView(this.codeFieldContainer, LayoutHelper.createLinear(-2, 36, 1));
            if (this.verificationType != 3) goto L37;
            this.codeFieldContainer.setVisibility(8);
        L37:
            AnonymousClass1 r47 = new AnonymousClass1(this, r25, r24);
            this.timeText = r47;
            r47.setTextColor(Theme.getColor(r5));
            this.timeText.setLineSpacing(AndroidUtilities.dp(r262), 1.0f);
            int r48 = this.verificationType;
            TextView r52 = this.timeText;
            if (r48 != 3) goto L48;
            r52.setTextSize(1, 14.0f);
            View r49 = this.timeText;
            if (LocaleController.isRTL == false) goto L42;
            int r53 = 5;
        L43:
            addView(r49, LayoutHelper.createLinear(-2, -2, r53));
            this.progressView = new ProgressView(r25);
            TextView r410 = this.timeText;
            if (LocaleController.isRTL == true) goto L47;
            r11 = 3;
        L47:
            r410.setGravity(r11);
            addView(this.progressView, LayoutHelper.createLinear(-1, 3, 0.0f, 12.0f, 0.0f, 0.0f));
        L49:
            AnonymousClass2 r411 = new AnonymousClass2(this, r25, r24);
            this.problemText = r411;
            r411.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.problemText.setLineSpacing(AndroidUtilities.dp(r262), 1.0f);
            this.problemText.setPadding(0, AndroidUtilities.dp(r262), 0, AndroidUtilities.dp(10.0f));
            this.problemText.setTextSize(1, 15.0f);
            this.problemText.setGravity(49);
            int r1 = this.verificationType;
            TextView r2 = this.problemText;
            if (r1 != 1) goto L52;
            r2.setText(LocaleController.getString(R.string.DidNotGetTheCodeSms));
        L53:
            addView(this.problemText, LayoutHelper.createLinear(-2, -2, 49));
            this.problemText.setOnClickListener(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda1(this));
            return;
        L52:
            r2.setText(LocaleController.getString(R.string.DidNotGetTheCode));
            goto L53
        L42:
            r53 = 3;
            goto L43
        L48:
            r52.setPadding(0, AndroidUtilities.dp(r262), 0, AndroidUtilities.dp(10.0f));
            this.timeText.setTextSize(1, 15.0f);
            this.timeText.setGravity(49);
            addView(this.timeText, LayoutHelper.createLinear(-2, -2, 49));
            goto L49
        L32:
            r262 = 2.0f;
            ImageView r72 = new ImageView(r25);
            this.blueImageView = r72;
            r72.setImageResource(R.drawable.sms_code);
            this.blueImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionBackground), PorterDuff.Mode.MULTIPLY));
            r45.addView(this.blueImageView, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
            this.titleTextView.setText(LocaleController.getString(R.string.SentSmsCodeTitle));
            goto L33
        L9:
            if (LocaleController.isRTL == false) goto L11;
            int r412 = 5;
        L12:
            r13.setGravity(r412 | 48);
            FrameLayout r413 = new FrameLayout(r25);
            if (LocaleController.isRTL == false) goto L15;
            int r84 = 5;
        L16:
            addView(r413, LayoutHelper.createLinear(-2, -2, r84));
            ImageView r85 = new ImageView(r25);
            r85.setImageResource(R.drawable.phone_activate);
            boolean r134 = LocaleController.isRTL;
            if (r134 == false) goto L23;
            r413.addView(r85, LayoutHelper.createFrame(64, 76.0f, 19, 2.0f, 2.0f, 0.0f, 0.0f));
            TextView r86 = this.confirmTextView;
            if (LocaleController.isRTL == false) goto L21;
            int r17 = 5;
        L22:
            r413.addView(r86, LayoutHelper.createFrame(-1, -2.0f, r17, 82.0f, 0.0f, 0.0f, 0.0f));
        L28:
            r262 = 2.0f;
            goto L34
        L21:
            r17 = 3;
            goto L22
        L23:
            TextView r15 = this.confirmTextView;
            if (r134 == false) goto L26;
            int r18 = 5;
        L27:
            r413.addView(r15, LayoutHelper.createFrame(-1, -2.0f, r18, 0.0f, 0.0f, 82.0f, 0.0f));
            r413.addView(r85, LayoutHelper.createFrame(64, 76.0f, 21, 0.0f, 2.0f, 0.0f, 2.0f));
            goto L28
        L26:
            r18 = 3;
            goto L27
        L15:
            r84 = 3;
            goto L16
        L11:
            r412 = 3;
            goto L12
        L5:
            r10 = 3;
            goto L6
        }

        private void createCodeTimer() {
            if (this.codeTimer == null) goto L5;
            return;
        L5:
            this.codeTime = 15000;
            this.codeTimer = new Timer();
            this.lastCodeTime = System.currentTimeMillis();
            this.codeTimer.schedule(new AnonymousClass4(this), 0, 1000);
        }

        private void createTimer() {
            if (this.timeTimer == null) goto L5;
            return;
        L5:
            Timer r1 = new Timer();
            this.timeTimer = r1;
            r1.schedule(new AnonymousClass5(this), 0, 1000);
        }

        private void destroyCodeTimer() {
            Object r0 = this.timerSync;     // Catch: Exception -> L13
            monitor-enter(r0);     // Catch: Exception -> L13
            Timer r1 = this.codeTimer;     // Catch: Throwable -> L7
            if (r1 == null) goto L9;
            r1.cancel();     // Catch: Throwable -> L7
            this.codeTimer = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;     // Catch: Exception -> L13
        L13:
            e = move-exception;
            FileLog.e(e);
        }

        private void destroyTimer() {
            Object r0 = this.timerSync;     // Catch: Exception -> L13
            monitor-enter(r0);     // Catch: Exception -> L13
            Timer r1 = this.timeTimer;     // Catch: Throwable -> L7
            if (r1 == null) goto L9;
            r1.cancel();     // Catch: Throwable -> L7
            this.timeTimer = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;     // Catch: Exception -> L13
        L13:
            e = move-exception;
            FileLog.e(e);
        }

        private String getCode() {
            if (this.codeField != null) goto L6;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            StringBuilder r0 = new StringBuilder();
            int r1 = 0;
        L7:
            EditTextBoldCursor[] r2 = this.codeField;
            if (r1 >= r2.length) goto L11;
            r0.append(PhoneFormat.stripExceptNumbers(r2[r1].getText().toString()));
            r1 = r1 + 1;
            goto L7
        L11:
            return r0.toString();
        }

        private /* synthetic */ void lambda$new$0(View r8) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            int r1 = this.nextType;
            if (r1 == 4) goto L8;
        L9:
            if (r1 == 0) goto L16;
            resendCode();
            return;
        L16:
            PackageInfo r12 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L12
            String r13 = String.format(Locale.US, "%s (%d)", new Object[]{r12.versionName, Integer.valueOf(r12.versionCode)});     // Catch: Exception -> L12
            Intent r2 = new Intent("android.intent.action.SENDTO");     // Catch: Exception -> L12
            r2.setData(Uri.parse("mailto:"));     // Catch: Exception -> L12
            r2.putExtra("android.intent.extra.EMAIL", new String[]{"sms@telegram.org"});     // Catch: Exception -> L12
            r2.putExtra("android.intent.extra.SUBJECT", "Android registration/login issue " + r13 + " " + this.phone);     // Catch: Exception -> L12
            r2.putExtra("android.intent.extra.TEXT", "Phone: " + this.phone + "\nApp version: " + r13 + "\nOS version: SDK " + Build.VERSION.SDK_INT + "\nDevice Name: " + Build.MANUFACTURER + Build.MODEL + "\nLocale: " + Locale.getDefault() + "\nError: " + this.lastError);     // Catch: Exception -> L12
            getContext().startActivity(Intent.createChooser(r2, "Send email..."));     // Catch: Exception -> L12
            return;
        L12:
            AlertsCreator.showSimpleAlert(this.this$0, LocaleController.getString(R.string.NoMailInstalled));
            return;
        L8:
            if (this.verificationType == 2) goto L16;
            goto L9
        }

        private /* synthetic */ void lambda$onBackPressed$8(AlertDialog r2, int r3) {
            onBackPressed(true);
            this.this$0.setPage(0, true, null);
        }

        private /* synthetic */ void lambda$onNextPressed$6(TLRPC.TL_error r14, TL_account.verifyPhone r15) {
            this.this$0.needHideProgress();
            this.nextPressed = false;
            if (r14 != null) goto L6;
            destroyTimer();
            destroyCodeTimer();
            PassportActivity.m16448$$Nest$fgetdelegate(this.this$0).saveValue(PassportActivity.m16445$$Nest$fgetcurrentType(this.this$0), (String) PassportActivity.m16446$$Nest$fgetcurrentValues(this.this$0).get("phone"), null, null, null, null, null, null, null, null, new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda9(this.this$0), null);
            return;
        L6:
            this.lastError = r14.text;
            int r1 = this.verificationType;
            if (r1 != 3) goto L11;
            int r5 = this.nextType;
            if (r5 == 4) goto L18;
            if (r5 != 2) goto L11;
        L18:
            createTimer();
        L19:
            int r12 = this.verificationType;
            if (r12 != 2) goto L22;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L24:
            this.waitingForEvent = true;
            if (this.verificationType == 3) goto L27;
            AlertsCreator.processError(PassportActivity.access$2000(this.this$0), r14, this.this$0, r15, new Object[0]);
        L27:
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(this.this$0, true, false);
            if (r14.text.contains("PHONE_CODE_EMPTY") == false) goto L30;
        L36:
            int r142 = 0;
        L37:
            EditTextBoldCursor[] r152 = this.codeField;
            if (r142 >= r152.length) goto L40;
            r152[r142].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r142 = r142 + 1;
            goto L37
        L40:
            r152[0].requestFocus();
            return;
        L30:
            if (r14.text.contains("PHONE_CODE_INVALID") == true) goto L36;
            if (r14.text.contains("PHONE_CODE_EXPIRED") == false) goto L43;
            onBackPressed(true);
            this.this$0.setPage(0, true, null);
            return;
        L43:
            return;
        L22:
            if (r12 != 3) goto L24;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
        L11:
            if (r1 != 2) goto L15;
            int r52 = this.nextType;
            if (r52 == 4) goto L18;
            if (r52 == 3) goto L18;
        L15:
            if (r1 != 4) goto L19;
            if (this.nextType != 2) goto L19;
            goto L18
        }

        private /* synthetic */ void lambda$onNextPressed$7(TL_account.verifyPhone r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda6(this, r3, r1));
        }

        private /* synthetic */ void lambda$resendCode$1(AlertDialog r1, int r2) {
            onBackPressed(true);
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$resendCode$2(TLRPC.TL_error r3, Bundle r4, TLObject r5, TLRPC.TL_auth_resendCode r6) {
            this.nextPressed = false;
            PassportActivity r1 = this.this$0;
            if (r3 != null) goto L5;
            PassportActivity.m16516$$Nest$mfillNextCodeParams(r1, r4, (TLRPC.TL_auth_sentCode) r5, true);
        L10:
            this.this$0.needHideProgress();
            return;
        L5:
            AlertDialog r42 = (AlertDialog) AlertsCreator.processError(PassportActivity.access$2100(r1), r3, this.this$0, r6, new Object[0]);
            if (r42 == null) goto L10;
            if (r3.text.contains("PHONE_CODE_EXPIRED") == false) goto L10;
            r42.setPositiveButtonListener(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda10(this));
            goto L10
        }

        private /* synthetic */ void lambda$resendCode$3(Bundle r7, TLRPC.TL_auth_resendCode r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda8(this, r10, r7, r9, r8));
        }

        private /* synthetic */ boolean lambda$setParams$4(int r2, View r3, int r4, KeyEvent r5) {
            if (r4 == 67) goto L5;
            return false;
        L5:
            if (this.codeField[r2].length() != 0) goto L11;
            if (r2 <= 0) goto L12;
            int r22 = r2 - 1;
            EditTextBoldCursor r32 = this.codeField[r22];
            r32.setSelection(r32.length());
            this.codeField[r22].requestFocus();
            this.codeField[r22].dispatchKeyEvent(r5);
            return true;
        L12:
            return false;
        L11:
            return false;
        }

        private /* synthetic */ boolean lambda$setParams$5(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            onNextPressed(null);
            return true;
        L6:
            return false;
        }

        private void resendCode() {
            Bundle r0 = new Bundle();
            r0.putString("phone", this.phone);
            this.nextPressed = true;
            this.this$0.needShowProgress();
            TLRPC.TL_auth_resendCode r1 = new TLRPC.TL_auth_resendCode();
            r1.phone_number = this.phone;
            r1.phone_code_hash = this.phoneHash;
            ConnectionsManager.getInstance(PassportActivity.access$1600(this.this$0)).sendRequest(r1, new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda7(this, r0, r1), 2);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r5, int r6, Object... r7) {
            if (this.waitingForEvent == false) goto L18;
            EditTextBoldCursor[] r62 = this.codeField;
            if (r62 != null) goto L8;
            return;
        L8:
            if (r5 != NotificationCenter.didReceiveSmsCode) goto L12;
            r62[0].setText(_UrlKt.FRAGMENT_ENCODE_SET + r7[0]);
            onNextPressed(null);
            return;
        L12:
            if (r5 != NotificationCenter.didReceiveCall) goto L19;
            String r52 = _UrlKt.FRAGMENT_ENCODE_SET + r7[0];
            if (AndroidUtilities.checkPhonePattern(this.pattern, r52) == false) goto L21;
            this.ignoreOnTextChange = true;
            this.codeField[0].setText(r52);
            this.ignoreOnTextChange = false;
            onNextPressed(null);
            return;
        L21:
            return;
        L19:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r6) {
            if (r6 == true) goto L6;
            AlertDialog.Builder r62 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r62.setTitle(LocaleController.getString(R.string.AppName));
            r62.setMessage(LocaleController.getString(R.string.StopVerification));
            r62.setPositiveButton(LocaleController.getString(R.string.Continue), null);
            r62.setNegativeButton(LocaleController.getString(R.string.Stop), new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda2(this));
            this.this$0.showDialog(r62.create());
            return false;
        L6:
            TLRPC.TL_auth_cancelCode r63 = new TLRPC.TL_auth_cancelCode();
            r63.phone_number = this.phone;
            r63.phone_code_hash = this.phoneHash;
            ConnectionsManager.getInstance(PassportActivity.access$1900(this.this$0)).sendRequest(r63, new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda3(), 2);
            destroyTimer();
            destroyCodeTimer();
            this.currentParams = null;
            int r64 = this.verificationType;
            if (r64 != 2) goto L10;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L12:
            this.waitingForEvent = false;
            return true;
        L10:
            if (r64 != 3) goto L12;
            AndroidUtilities.setWaitingForCall(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L12
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onDestroyActivity() {
            super.onDestroyActivity();
            int r0 = this.verificationType;
            if (r0 != 2) goto L6;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L8:
            this.waitingForEvent = false;
            destroyTimer();
            destroyCodeTimer();
            return;
        L6:
            if (r0 != 3) goto L8;
            AndroidUtilities.setWaitingForCall(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L8
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            super.onLayout(r2, r3, r4, r5, r6);
            if (this.verificationType != 3) goto L5;
            return;
        L5:
            if (this.blueImageView == null) goto L16;
            int r22 = this.confirmTextView.getBottom();
            int r32 = getMeasuredHeight() - r22;
            if (this.problemText.getVisibility() != 0) goto L10;
            int r42 = this.problemText.getMeasuredHeight();
            int r33 = (r32 + r22) - r42;
            TextView r52 = this.problemText;
            r52.layout(r52.getLeft(), r33, this.problemText.getRight(), r42 + r33);
        L13:
            int r43 = this.codeFieldContainer.getMeasuredHeight();
            int r34 = (((r33 - r22) - r43) / 2) + r22;
            LinearLayout r23 = this.codeFieldContainer;
            r23.layout(r23.getLeft(), r34, this.codeFieldContainer.getRight(), r43 + r34);
            return;
        L10:
            if (this.timeText.getVisibility() != 0) goto L12;
            int r44 = this.timeText.getMeasuredHeight();
            r33 = (r32 + r22) - r44;
            TextView r53 = this.timeText;
            r53.layout(r53.getLeft(), r33, this.timeText.getRight(), r44 + r33);
            goto L13
        L12:
            r33 = r32 + r22;
            goto L13
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.verificationType == 3) goto L12;
            ImageView r32 = this.blueImageView;
            if (r32 == null) goto L13;
            int r33 = ((r32.getMeasuredHeight() + this.titleTextView.getMeasuredHeight()) + this.confirmTextView.getMeasuredHeight()) + AndroidUtilities.dp(35.0f);
            int r42 = AndroidUtilities.dp(80.0f);
            int r0 = AndroidUtilities.dp(291.0f);
            if ((PassportActivity.m16475$$Nest$fgetscrollHeight(this.this$0) - r33) >= r42) goto L10;
            setMeasuredDimension(getMeasuredWidth(), r33 + r42);
            return;
        L10:
            setMeasuredDimension(getMeasuredWidth(), Math.min(PassportActivity.m16475$$Nest$fgetscrollHeight(this.this$0), r0));
            return;
        L13:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r6) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            if (r6 != null) goto L8;
            r6 = getCode();
        L8:
            if (TextUtils.isEmpty(r6) == false) goto L11;
            AndroidUtilities.shakeView(this.codeFieldContainer);
            return;
        L11:
            this.nextPressed = true;
            int r1 = this.verificationType;
            if (r1 != 2) goto L15;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L17:
            this.waitingForEvent = false;
            PassportActivity.m16530$$Nest$mshowEditDoneProgress(this.this$0, true, true);
            TL_account.verifyPhone r0 = new TL_account.verifyPhone();
            r0.phone_number = this.phone;
            r0.phone_code = r6;
            r0.phone_code_hash = this.phoneHash;
            destroyTimer();
            this.this$0.needShowProgress();
            ConnectionsManager.getInstance(PassportActivity.access$1800(this.this$0)).sendRequest(r0, new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda0(this, r0), 2);
            return;
        L15:
            if (r1 != 3) goto L17;
            AndroidUtilities.setWaitingForCall(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L17
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            LinearLayout r0 = this.codeFieldContainer;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getVisibility() != 0) goto L19;
            int r02 = this.codeField.length - 1;
        L7:
            if (r02 < 0) goto L20;
            if (r02 == 0) goto L13;
            if (this.codeField[r02].length() != 0) goto L13;
            r02 = r02 - 1;
        L13:
            this.codeField[r02].requestFocus();
            EditTextBoldCursor r1 = this.codeField[r02];
            r1.setSelection(r1.length());
            AndroidUtilities.showKeyboard(this.codeField[r02]);
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r22, boolean r23) {
            int r6 = 0;
            if (r22 == null) goto L99;
            this.waitingForEvent = true;
            int r8 = this.verificationType;
            if (r8 != 2) goto L8;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L10:
            this.currentParams = r22;
            this.phone = r22.getString("phone");
            this.phoneHash = r22.getString("phoneHash");
            int r82 = r22.getInt("timeout");
            this.time = r82;
            this.timeout = r82;
            this.nextType = r22.getInt("nextType");
            this.pattern = r22.getString("pattern");
            int r1 = r22.getInt("length");
            this.length = r1;
            if (r1 != 0) goto L13;
            this.length = 5;
        L13:
            EditTextBoldCursor[] r12 = this.codeField;
            CharSequence r83 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r12 != null) goto L16;
        L22:
            this.codeField = new EditTextBoldCursor[this.length];
            int r13 = 0;
        L24:
            if (r13 >= this.length) goto L34;
            this.codeField[r13] = new EditTextBoldCursor(getContext());
            EditTextBoldCursor r11 = this.codeField[r13];
            int r122 = Theme.key_windowBackgroundWhiteBlackText;
            r11.setTextColor(Theme.getColor(r122));
            this.codeField[r13].setCursorColor(Theme.getColor(r122));
            this.codeField[r13].setCursorSize(AndroidUtilities.dp(20.0f));
            this.codeField[r13].setCursorWidth(1.5f);
            Drawable r112 = getResources().getDrawable(R.drawable.search_dark_activated).mutate();
            r112.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated), PorterDuff.Mode.MULTIPLY));
            this.codeField[r13].setBackgroundDrawable(r112);
            this.codeField[r13].setImeOptions(268435461);
            this.codeField[r13].setTextSize(1, 20.0f);
            this.codeField[r13].setMaxLines(1);
            this.codeField[r13].setTypeface(AndroidUtilities.bold());
            this.codeField[r13].setPadding(0, 0, 0, 0);
            this.codeField[r13].setGravity(49);
            int r113 = this.verificationType;
            EditTextBoldCursor[] r123 = this.codeField;
            if (r113 != 3) goto L28;
            r123[r13].setEnabled(false);
            this.codeField[r13].setInputType(0);
            this.codeField[r13].setVisibility(8);
        L29:
            LinearLayout r114 = this.codeFieldContainer;
            EditTextBoldCursor r124 = this.codeField[r13];
            if (r13 == (this.length - 1)) goto L32;
            int r19 = 7;
        L33:
            r114.addView(r124, LayoutHelper.createLinear(34, 36, 1, 0, 0, r19, 0));
            this.codeField[r13].addTextChangedListener(new AnonymousClass3(this, r13));
            this.codeField[r13].setOnKeyListener(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda4(this, r13));
            this.codeField[r13].setOnEditorActionListener(new PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda5(this));
            r13 = r13 + 1;
            goto L24
        L32:
            r19 = 0;
            goto L33
        L28:
            r123[r13].setInputType(3);
        L34:
            ProgressView r14 = this.progressView;
            if (r14 == null) goto L42;
            if (this.nextType == 0) goto L39;
            int r4 = 0;
        L40:
            r14.setVisibility(r4);
            goto L42
        L39:
            r4 = 8;
        L42:
            if (this.phone != null) goto L44;
            return;
        L44:
            String r15 = PhoneFormat.getInstance().format("+" + this.phone);
            int r42 = this.verificationType;
            if (r42 != 2) goto L47;
            r83 = AndroidUtilities.replaceTags(LocaleController.formatString("SentSmsCode", R.string.SentSmsCode, new Object[]{LocaleController.addNbsp(r15)}));
        L51:
            this.confirmTextView.setText(r83);
            int r16 = this.verificationType;
            EditTextBoldCursor[] r43 = this.codeField;
            if (r16 == 3) goto L54;
            AndroidUtilities.showKeyboard(r43[0]);
            this.codeField[0].requestFocus();
        L55:
            destroyTimer();
            destroyCodeTimer();
            this.lastCurrentTime = System.currentTimeMillis();
            int r17 = this.verificationType;
            if (r17 != 3) goto L68;
            int r125 = this.nextType;
            if (r125 == 4) goto L60;
            if (r125 != 2) goto L68;
        L60:
            this.problemText.setVisibility(8);
            this.timeText.setVisibility(0);
            int r18 = this.nextType;
            if (r18 != 4) goto L63;
            this.timeText.setText(LocaleController.formatString("CallText", R.string.CallText, new Object[]{1, 0}));
        L65:
            createTimer();
            return;
        L63:
            if (r18 != 2) goto L65;
            this.timeText.setText(LocaleController.formatString("SmsText", R.string.SmsText, new Object[]{1, 0}));
        L68:
            if (r17 != 2) goto L81;
            int r126 = this.nextType;
            if (r126 == 4) goto L72;
            if (r126 != 3) goto L81;
        L72:
            this.timeText.setText(LocaleController.formatString("CallText", R.string.CallText, new Object[]{2, 0}));
            TextView r110 = this.problemText;
            if (this.time >= 1000) goto L75;
            int r2 = 0;
        L76:
            r110.setVisibility(r2);
            TextView r111 = this.timeText;
            if (this.time >= 1000) goto L79;
            r6 = 8;
        L79:
            r111.setVisibility(r6);
            createTimer();
            return;
        L75:
            r2 = 8;
        L81:
            if (r17 == 4) goto L83;
        L93:
            this.timeText.setVisibility(8);
            this.problemText.setVisibility(8);
            createCodeTimer();
            return;
        L83:
            if (this.nextType != 2) goto L93;
            this.timeText.setText(LocaleController.formatString("SmsText", R.string.SmsText, new Object[]{2, 0}));
            TextView r115 = this.problemText;
            if (this.time >= 1000) goto L87;
            int r24 = 0;
        L88:
            r115.setVisibility(r24);
            TextView r116 = this.timeText;
            if (this.time >= 1000) goto L91;
            r6 = 8;
        L91:
            r116.setVisibility(r6);
            createTimer();
            return;
        L87:
            r24 = 8;
            goto L88
        L54:
            AndroidUtilities.hideKeyboard(r43[0]);
            goto L55
        L47:
            if (r42 != 3) goto L49;
            r83 = AndroidUtilities.replaceTags(LocaleController.formatString("SentCallCode", R.string.SentCallCode, new Object[]{LocaleController.addNbsp(r15)}));
            goto L51
        L49:
            if (r42 != 4) goto L51;
            r83 = AndroidUtilities.replaceTags(LocaleController.formatString("SentCallOnly", R.string.SentCallOnly, new Object[]{LocaleController.addNbsp(r15)}));
            goto L51
        L16:
            if (r12.length != this.length) goto L22;
            int r117 = 0;
        L19:
            EditTextBoldCursor[] r44 = this.codeField;
            if (r117 >= r44.length) goto L34;
            r44[r117].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r117 = r117 + 1;
            goto L19
        L8:
            if (r8 != 3) goto L10;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
            goto L10
        }
    }

    public static class ProgressView extends View {
        private Paint paint;
        private Paint paint2;
        private float progress;

        public ProgressView(Context r2) {
            super(r2);
            this.paint = new Paint();
            this.paint2 = new Paint();
            this.paint.setColor(Theme.getColor(Theme.key_login_progressInner));
            this.paint2.setColor(Theme.getColor(Theme.key_login_progressOuter));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            float r2 = (int) (getMeasuredWidth() * this.progress);
            r8.drawRect(0.0f, 0.0f, r2, getMeasuredHeight(), this.paint2);
            r8.drawRect(r2, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.paint);
        }

        public void setProgress(float r1) {
            this.progress = r1;
            invalidate();
        }
    }

    public class SecureDocumentCell extends FrameLayout implements DownloadController.FileDownloadProgressListener {
        private int TAG;
        private int buttonState;
        private SecureDocument currentSecureDocument;
        private BackupImageView imageView;
        private RadialProgress radialProgress;
        private TextView textView;
        final /* synthetic */ PassportActivity this$0;
        private TextView valueTextView;

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m16576$$Nest$fgetimageView(SecureDocumentCell r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetvalueTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m16577$$Nest$fgetvalueTextView(SecureDocumentCell r0) {
            return r0.valueTextView;
        }

        public SecureDocumentCell(PassportActivity r14, Context r15) {
            this.this$0 = r14;
            super(r15);
            this.TAG = DownloadController.getInstance(PassportActivity.access$000(r14)).generateObserverTag();
            this.radialProgress = new RadialProgress(this);
            BackupImageView r142 = new BackupImageView(r15);
            this.imageView = r142;
            int r1 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r0 = 5;
        L6:
            addView(r142, LayoutHelper.createFrame(48, 48.0f, r0 | 48, 21.0f, 8.0f, 21.0f, 0.0f));
            TextView r143 = new TextView(r15);
            this.textView = r143;
            r143.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            TextView r144 = this.textView;
            if (LocaleController.isRTL == false) goto L9;
            int r02 = 5;
        L10:
            r144.setGravity(r02 | 16);
            View r145 = this.textView;
            boolean r03 = LocaleController.isRTL;
            if (r03 == false) goto L13;
            int r4 = 5;
        L14:
            int r7 = r4 | 48;
            int r42 = 81;
            if (r03 == false) goto L17;
            int r5 = 21;
        L18:
            float r8 = r5;
            if (r03 == false) goto L21;
            int r04 = 81;
        L22:
            addView(r145, LayoutHelper.createFrame(-2, -2.0f, r7, r8, 10.0f, r04, 0.0f));
            TextView r146 = new TextView(r15);
            this.valueTextView = r146;
            r146.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            this.valueTextView.setTextSize(1, 13.0f);
            TextView r147 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L25;
            int r152 = 5;
        L26:
            r147.setGravity(r152);
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setPadding(0, 0, 0, 0);
            View r148 = this.valueTextView;
            boolean r05 = LocaleController.isRTL;
            if (r05 == false) goto L29;
            r1 = 5;
        L29:
            int r72 = r1 | 48;
            if (r05 == false) goto L32;
            int r12 = 21;
        L33:
            float r82 = r12;
            if (r05 == true) goto L37;
            r42 = 21;
        L37:
            addView(r148, LayoutHelper.createFrame(-2, -2.0f, r72, r82, 35.0f, r42, 0.0f));
            setWillNotDraw(false);
            return;
        L32:
            r12 = 81;
            goto L33
        L25:
            r152 = 3;
            goto L26
        L21:
            r04 = 21;
            goto L22
        L17:
            r5 = 81;
            goto L18
        L13:
            r4 = 3;
            goto L14
        L9:
            r02 = 3;
            goto L10
        L5:
            r0 = 3;
            goto L6
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 != this.imageView) goto L5;
            this.radialProgress.draw(r1);
        L5:
            return r32;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.TAG;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.textView.invalidate();
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

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
            updateButtonState(false);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = this.imageView.getLeft() + ((this.imageView.getMeasuredWidth() - AndroidUtilities.dp(24.0f)) / 2);
            int r22 = this.imageView.getTop() + ((this.imageView.getMeasuredHeight() - AndroidUtilities.dp(24.0f)) / 2);
            this.radialProgress.setProgressRect(r12, r22, AndroidUtilities.dp(24.0f) + r12, AndroidUtilities.dp(24.0f) + r22);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + 1, TLObject.FLAG_30));
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r1, long r2, long r4) {
            this.radialProgress.setProgress(Math.min(1.0f, r2 / r4), true);
            if (this.buttonState == 1) goto L6;
            updateButtonState(false);
            return;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
            this.radialProgress.setProgress(Math.min(1.0f, r2 / r4), true);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r3) {
            this.radialProgress.setProgress(1.0f, true);
            updateButtonState(true);
        }

        public void setTextAndValueAndImage(String r2, CharSequence r3, SecureDocument r4) {
            this.textView.setText(r2);
            this.valueTextView.setText(r3);
            this.imageView.setImage(r4, "48_48");
            this.currentSecureDocument = r4;
            updateButtonState(false);
        }

        public void setValue(CharSequence r1) {
            this.valueTextView.setText(r1);
        }

        public void updateButtonState(boolean r10) {
            String r0 = FileLoader.getAttachFileName(this.currentSecureDocument);
            boolean r1 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(this.currentSecureDocument).exists();
            if (TextUtils.isEmpty(r0) == false) goto L6;
            this.radialProgress.setBackground(null, false, false);
            return;
        L6:
            SecureDocument r2 = this.currentSecureDocument;
            float r6 = 0.0f;
            if (r2.path == null) goto L17;
            TLRPC.TL_inputFile r02 = r2.inputFile;
            PassportActivity r12 = this.this$0;
            if (r02 == null) goto L12;
            DownloadController.getInstance(PassportActivity.access$100(r12)).removeLoadingFileObserver(this);
            this.radialProgress.setBackground(null, false, r10);
            this.buttonState = -1;
            return;
        L12:
            DownloadController.getInstance(PassportActivity.access$200(r12)).addLoadingFileObserver(this.currentSecureDocument.path, this);
            this.buttonState = 1;
            Float r03 = ImageLoader.getInstance().getFileProgress(this.currentSecureDocument.path);
            this.radialProgress.setBackground(getResources().getDrawable(R.drawable.circle), true, r10);
            RadialProgress r102 = this.radialProgress;
            if (r03 == null) goto L15;
            r6 = r03.floatValue();
        L15:
            r102.setProgress(r6, false);
            invalidate();
            return;
        L17:
            PassportActivity r22 = this.this$0;
            if (r1 == false) goto L21;
            DownloadController.getInstance(PassportActivity.access$300(r22)).removeLoadingFileObserver(this);
            this.buttonState = -1;
            this.radialProgress.setBackground(null, false, r10);
            invalidate();
            return;
        L21:
            DownloadController.getInstance(PassportActivity.access$400(r22)).addLoadingFileObserver(r0, this);
            this.buttonState = 1;
            Float r04 = ImageLoader.getInstance().getFileProgress(r0);
            this.radialProgress.setBackground(getResources().getDrawable(R.drawable.circle), true, r10);
            RadialProgress r13 = this.radialProgress;
            if (r04 == null) goto L24;
            r6 = r04.floatValue();
        L24:
            r13.setProgress(r6, r10);
            invalidate();
        }
    }

    public class TextDetailSecureCell extends FrameLayout {
        private ImageView checkImageView;
        private boolean needDivider;
        private TextView textView;
        final /* synthetic */ PassportActivity this$0;
        private TextView valueTextView;

        /* renamed from: -$$Nest$fgetvalueTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m16578$$Nest$fgetvalueTextView(TextDetailSecureCell r0) {
            return r0.valueTextView;
        }

        public TextDetailSecureCell(PassportActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            int r4 = 21;
            if (PassportActivity.m16428$$Nest$fgetcurrentActivityType(r19) != 8) goto L5;
            int r1 = 21;
        L6:
            TextView r3 = new TextView(r20);
            this.textView = r3;
            r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            TextView r32 = this.textView;
            TextUtils.TruncateAt r5 = TextUtils.TruncateAt.END;
            r32.setEllipsize(r5);
            TextView r33 = this.textView;
            int r8 = 3;
            if (LocaleController.isRTL == false) goto L9;
            int r7 = 5;
        L10:
            r33.setGravity(r7 | 16);
            TextView r34 = this.textView;
            boolean r72 = LocaleController.isRTL;
            if (r72 == false) goto L13;
            int r10 = 5;
        L14:
            int r13 = r10 | 48;
            if (r72 == false) goto L17;
            int r102 = r1;
        L18:
            float r14 = r102;
            if (r72 == false) goto L21;
            int r73 = 21;
        L22:
            addView(r34, LayoutHelper.createFrame(-2, -2.0f, r13, r14, 10.0f, r73, 0.0f));
            TextView r35 = new TextView(r20);
            this.valueTextView = r35;
            r35.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            this.valueTextView.setTextSize(1, 13.0f);
            TextView r36 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L25;
            int r74 = 5;
        L26:
            r36.setGravity(r74);
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setEllipsize(r5);
            this.valueTextView.setPadding(0, 0, 0, 0);
            TextView r37 = this.valueTextView;
            boolean r52 = LocaleController.isRTL;
            if (r52 == false) goto L29;
            int r6 = 5;
        L30:
            int r12 = r6 | 48;
            if (r52 == false) goto L33;
            int r62 = r1;
        L34:
            float r132 = r62;
            if (r52 == true) goto L38;
            r4 = r1;
        L38:
            addView(r37, LayoutHelper.createFrame(-2, -2.0f, r12, r132, 35.0f, r4, 0.0f));
            ImageView r15 = new ImageView(r20);
            this.checkImageView = r15;
            r15.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addedIcon), PorterDuff.Mode.MULTIPLY));
            this.checkImageView.setImageResource(R.drawable.sticker_added);
            ImageView r16 = this.checkImageView;
            if (LocaleController.isRTL == true) goto L42;
            r8 = 5;
        L42:
            addView(r16, LayoutHelper.createFrame(-2, -2.0f, r8 | 48, 21.0f, 25.0f, 21.0f, 0.0f));
            return;
        L33:
            r62 = 21;
            goto L34
        L29:
            r6 = 3;
            goto L30
        L25:
            r74 = 3;
            goto L26
        L21:
            r73 = r1;
            goto L22
        L17:
            r102 = 21;
            goto L18
        L13:
            r10 = 3;
            goto L14
        L9:
            r7 = 3;
            goto L10
        L5:
            r1 = 51;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(20.0f);
        L13:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(20.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void setChecked(boolean r1) {
            ImageView r0 = this.checkImageView;
            if (r1 == false) goto L5;
            int r12 = 0;
        L6:
            r0.setVisibility(r12);
            return;
        L5:
            r12 = 4;
            goto L6
        }

        public void setNeedDivider(boolean r1) {
            this.needDivider = r1;
            setWillNotDraw(!r1);
            invalidate();
        }

        public void setTextAndValue(String r2, CharSequence r3, boolean r4) {
            this.textView.setText(r2);
            this.valueTextView.setText(r3);
            this.needDivider = r4;
            setWillNotDraw(!r4);
        }

        public void setValue(CharSequence r1) {
            this.valueTextView.setText(r1);
        }
    }

    /* renamed from: $r8$lambda$-QqBHTQGQSNFWFsp4IO3TCZ-lYY, reason: not valid java name */
    public static /* synthetic */ void m16391$r8$lambda$QqBHTQGQSNFWFsp4IO3TCZlYY(PassportActivity r0, View r1) {
        r0.lambda$createManageInterface$21(r1);
    }

    /* renamed from: $r8$lambda$-e6NxR_OyCeWDqRwLhouS8ogL0k, reason: not valid java name */
    public static /* synthetic */ void m16392$r8$lambda$e6NxR_OyCeWDqRwLhouS8ogL0k(PassportActivity r0, MrzRecognizer.Result r1) {
        r0.lambda$processSelectedFiles$71(r1);
    }

    /* renamed from: $r8$lambda$0P0T3-OLbaloQc0yRrVSn0EdYM4, reason: not valid java name */
    public static /* synthetic */ void m16393$r8$lambda$0P0T3OLbaloQc0yRrVSn0EdYM4(PassportActivity r0, TLRPC.TL_auth_passwordRecovery r1, AlertDialog r2, int r3) {
        r0.lambda$createPasswordInterface$8(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$0h1jjwLEHf78Gwv5HwgbltJ5bEo(PassportActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createPhoneInterface$29(r1, r2);
    }

    /* renamed from: $r8$lambda$1sHDwMu-HpM_3O7lDcua8oi1tYE, reason: not valid java name */
    public static /* synthetic */ void m16394$r8$lambda$1sHDwMuHpM_3O7lDcua8oi1tYE(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$54(r1);
    }

    /* renamed from: $r8$lambda$1yPDaE-n_95pER0WsG4YXc4hPM8, reason: not valid java name */
    public static /* synthetic */ void m16395$r8$lambda$1yPDaEn_95pER0WsG4YXc4hPM8(PassportActivity r0, DialogInterface r1, int r2) {
        r0.lambda$createIdentityInterface$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2FIKT_MuVpBfoeeuXw1Raz2f43U(PassportActivity r0) {
        r0.lambda$createManageInterface$18();
    }

    /* renamed from: $r8$lambda$2dY7-JV_A0yF0fyMu75Xq-FNQyM, reason: not valid java name */
    public static /* synthetic */ void m16396$r8$lambda$2dY7JV_A0yF0fyMu75XqFNQyM(PassportActivity r0, int r1, EditTextBoldCursor r2, int r3, int r4, int r5) {
        r0.lambda$createIdentityInterface$47(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$3Evbq80p8BXXTTTt5zZR_IAN1rA(PassportActivity r0) {
        r0.lambda$onTransitionAnimationEnd$67();
    }

    /* renamed from: $r8$lambda$604L-xv7M_pg8DoyVHnRJzgTxFk, reason: not valid java name */
    public static /* synthetic */ void m16397$r8$lambda$604Lxv7M_pg8DoyVHnRJzgTxFk(PassportActivity r0, int r1, View r2) {
        r0.lambda$addDocumentView$55(r1, r2);
    }

    /* renamed from: $r8$lambda$6J3pnMHN2-vPGidf8s88awnhcgU, reason: not valid java name */
    public static /* synthetic */ boolean m16398$r8$lambda$6J3pnMHN2vPGidf8s88awnhcgU(PassportActivity r0, int r1, SecureDocument r2, SecureDocumentCell r3, String r4, View r5) {
        return r0.lambda$addDocumentView$57(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$6z6Ka3CwJkzwN7WC5ExUEg7NIUE(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$43(r1);
    }

    /* renamed from: $r8$lambda$7eYo7ss49LCq-FfLbxB_wNF0bx4, reason: not valid java name */
    public static /* synthetic */ void m16399$r8$lambda$7eYo7ss49LCqFfLbxB_wNF0bx4(PassportActivity r0, SecureDocument r1, int r2) {
        r0.lambda$processSelectedFiles$70(r1, r2);
    }

    /* renamed from: $r8$lambda$7r-r50c2kVsM8fFfLZ1VxdbR2hE, reason: not valid java name */
    public static /* synthetic */ void m16400$r8$lambda$7rr50c2kVsM8fFfLZ1VxdbR2hE(PassportActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createManageInterface$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8SfbVRdYuLnsdY_IVajnen3cLCo(PassportActivity r0) {
        r0.lambda$checkNativeFields$58();
    }

    public static /* synthetic */ boolean $r8$lambda$9rPDRqTFQFUThdJ_OslM0xEXXpk(PassportActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createIdentityInterface$51(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Bcd5L_6NCkSmWF_pYZOu9rkXFaw(PassportActivity r0) {
        r0.lambda$onResume$2();
    }

    /* renamed from: $r8$lambda$Bt_gE-6DidxqYtRfnloL9mrYUKo, reason: not valid java name */
    public static /* synthetic */ boolean m16401$r8$lambda$Bt_gE6DidxqYtRfnloL9mrYUKo(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createEmailVerificationInterface$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CPE5XtSoCOdqYAtx3cwNAkD6CxI(PassportActivity r0, EditTextBoldCursor r1, AlertDialog r2, int r3) {
        r0.lambda$createIdentityInterface$48(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CTmxbaIVebCEEpcvEeAV3vNmklY(PassportActivity r0, TLRPC.TL_error r1, String r2, PassportActivityDelegate r3, TLObject r4, TL_account.sendVerifyPhoneCode r5) {
        r0.lambda$startPhoneVerification$65(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$D0FMqmqESmeZr0GXQTY3Avmr4fY(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createIdentityInterface$52(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$DdU03fAdYIqGQL3E2M0c7cxCCM8(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createPasswordInterface$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$EJOYFg2VueYIKLVTnwbCoK0w6rI(PassportActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$69(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ef1ttKbDd7WXRoiadXlUzwEfb3w(PassportActivity r0, TLRPC.TL_secureRequiredType r1, boolean r2, AlertDialog r3, int r4) {
        r0.lambda$addField$63(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$F2KfhwqNuQvhWgXUs3NlH9pL9jE(PassportActivity r0, TLObject r1) {
        r0.lambda$loadPasswordInfo$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$GoL5JXV3cuFnAOR82iDB63n3Zq4(PassportActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultFragment$68(r1, r2);
    }

    /* renamed from: $r8$lambda$HpjrH-m86krwJCwlBrmpJDHhnhs, reason: not valid java name */
    public static /* synthetic */ void m16402$r8$lambda$HpjrHm86krwJCwlBrmpJDHhnhs(PassportActivity r0, ErrorRunnable r1, boolean r2, TLRPC.TL_secureRequiredType r3, TLRPC.TL_secureRequiredType r4, boolean r5, ArrayList r6, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$deleteValueInternal$60(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$MLy4Y6HhBiUNDawXd1-rKnyi9Ss, reason: not valid java name */
    public static /* synthetic */ void m16403$r8$lambda$MLy4Y6HhBiUNDawXd1rKnyi9Ss(PassportActivity r0, View r1) {
        r0.lambda$createPhoneInterface$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$Mwsudp3lVaXgfxDhzixlGDwfCNo(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda44(r0));
    }

    /* renamed from: $r8$lambda$N-8TzDxg8y2SBP2UMZOh8PmVP6w, reason: not valid java name */
    public static /* synthetic */ boolean m16404$r8$lambda$N8TzDxg8y2SBP2UMZOh8PmVP6w(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createIdentityInterface$53(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$QfSOlBMltTBeDhHEfYKfLcQs-oo, reason: not valid java name */
    public static /* synthetic */ void m16405$r8$lambda$QfSOlBMltTBeDhHEfYKfLcQsoo(PassportActivity r0, CountrySelectActivity.Country r1) {
        r0.lambda$createPhoneInterface$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$RhNoKxT_kzn_D9qnQcR3eSWKz9w(PassportActivity r0, String r1, PassportActivityDelegate r2, TL_account.sendVerifyPhoneCode r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$startPhoneVerification$66(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$TM_JEYhkDZjPsGtXICmPm2wwhHE(PassportActivity r0, ArrayList r1, DialogInterface r2, int r3) {
        r0.lambda$openAddDocumentAlert$23(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$TRn9eBnPKm9AnPvuzCAI6lmyias(PassportActivity r0, ArrayList r1, TLRPC.TL_secureRequiredType r2, boolean r3, View r4) {
        r0.lambda$addField$64(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$TikUDka32tt_sFe0QTBR5mznfWk(PassportActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createManageInterface$20(r1, r2);
    }

    /* renamed from: $r8$lambda$VKR6oValKHTG-VKxocdVlfHJiVE, reason: not valid java name */
    public static /* synthetic */ void m16406$r8$lambda$VKR6oValKHTGVKxocdVlfHJiVE(PassportActivity r0, View r1) {
        r0.lambda$createAddressInterface$32(r1);
    }

    /* renamed from: $r8$lambda$Xe6MdweDVuTmt6g-7fvGKsMSTsw, reason: not valid java name */
    public static /* synthetic */ void m16407$r8$lambda$Xe6MdweDVuTmt6g7fvGKsMSTsw(PassportActivity r0, String r1, String r2) {
        r0.lambda$addField$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YXKiZq3qDEdpVpmPzwFbgWI0Q7w(PassportActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$createPasswordInterface$9(r1, r2);
    }

    /* renamed from: $r8$lambda$Zzh1iMhZEm-xwozfvNaxIrVpBZY, reason: not valid java name */
    public static /* synthetic */ boolean m16408$r8$lambda$Zzh1iMhZEmxwozfvNaxIrVpBZY(PassportActivity r0, Context r1, View r2, MotionEvent r3) {
        return r0.lambda$createIdentityInterface$49(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$bQfVIRFkw-TZW76BBf2BGhKVfZc, reason: not valid java name */
    public static /* synthetic */ void m16409$r8$lambda$bQfVIRFkwTZW76BBf2BGhKVfZc(PassportActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createRequestInterface$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bTa5FmjlJKZhMpNeWWr17CPzU9Q(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$d4GWihX3M3dtDR7Bnn3xlLbNkf0(PassportActivity r0, View r1) {
        r0.lambda$createPasswordInterface$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$dC0Z8jv_xbL0y5FtZcbVxUNDXkg(PassportActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createPasswordInterface$10(r1, r2);
    }

    /* renamed from: $r8$lambda$fd98-AFl76aWZnMnQBk4pKc54PE, reason: not valid java name */
    public static /* synthetic */ void m16410$r8$lambda$fd98AFl76aWZnMnQBk4pKc54PE(PassportActivity r0, View r1) {
        r0.lambda$createEmailInterface$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$gXsVndGqTKMJoGnpCUq0kiXTB9o(boolean[] r3, View r4) {
        if (r4.isEnabled() == true) goto L5;
        return;
    L5:
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    /* renamed from: $r8$lambda$h0rBZXlh_-aVEx__qqp4e-GZ7tM, reason: not valid java name */
    public static /* synthetic */ boolean m16411$r8$lambda$h0rBZXlh_aVEx__qqp4eGZ7tM(PassportActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createIdentityInterface$46(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$hkbFbteZnHqKUh0m1ruM7F6bK6Q(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createAddressInterface$36(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$iYKQELXT32IUfRoPO7At4IexZ8w(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createPhoneInterface$30(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jkcETo2uyhb5Z2tv7AFZz8IDDLs(PassportActivity r0, View r1) {
        r0.lambda$createAddressInterface$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$lIj66PiM0V5k5cuyvgZ5XdUMH9M(PassportActivity r0, View r1) {
        r0.lambda$createManageInterface$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$lmiZBbKjq8C0C3sj3NNDdq6ce9Q(PassportActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPasswordInfo$4(r1, r2);
    }

    /* renamed from: $r8$lambda$m7Ru9RlSVd-ZobPww6zSPSMrBNQ, reason: not valid java name */
    public static /* synthetic */ void m16412$r8$lambda$m7Ru9RlSVdZobPww6zSPSMrBNQ(PassportActivity r0, boolean r1, String r2) {
        r0.lambda$onPasswordDone$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$me6ChDjkiM6iz7J81bmMKVPuYNc(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$44(r1);
    }

    /* renamed from: $r8$lambda$n-7zzM2Ac00DYm5WUjJCauO2xfc, reason: not valid java name */
    public static /* synthetic */ void m16413$r8$lambda$n7zzM2Ac00DYm5WUjJCauO2xfc(PassportActivity r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.lambda$createDocumentDeleteAlert$38(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nM43oUGffiWDAUmzCURxN7v09wE(PassportActivity r0, CountrySelectActivity.Country r1) {
        r0.lambda$createAddressInterface$34(r1);
    }

    /* renamed from: $r8$lambda$oIHqmFlg97FTnb67U2IiYK8T-68, reason: not valid java name */
    public static /* synthetic */ void m16414$r8$lambda$oIHqmFlg97FTnb67U2IiYK8T68(PassportActivity r0, TLRPC.TL_error r1) {
        r0.lambda$createRequestInterface$14(r1);
    }

    /* renamed from: $r8$lambda$r8UpwW9ZUOg5STQ-Pu4Lkyvkcxs, reason: not valid java name */
    public static /* synthetic */ void m16415$r8$lambda$r8UpwW9ZUOg5STQPu4Lkyvkcxs(PassportActivity r0, SecureDocument r1, int r2, SecureDocumentCell r3, String r4, AlertDialog r5, int r6) {
        r0.lambda$addDocumentView$56(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$rEBK-HYA75EutL3pSpeQBPaKtxE, reason: not valid java name */
    public static /* synthetic */ void m16416$r8$lambda$rEBKHYA75EutL3pSpeQBPaKtxE(PassportActivity r0, View r1) {
        r0.lambda$createAddressInterface$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$s20LREmqduPw__tbqfPolNsEGCI(PassportActivity r0, View r1) {
        r0.lambda$createPasswordInterface$12(r1);
    }

    /* renamed from: $r8$lambda$s7O1jTEHJi6qX2jArON-vGrbsus, reason: not valid java name */
    public static /* synthetic */ boolean m16417$r8$lambda$s7O1jTEHJi6qX2jArONvGrbsus(PassportActivity r0, View r1, int r2, KeyEvent r3) {
        return r0.lambda$createPhoneInterface$31(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$svrViPakn1zH3vquqvRpKMCKOWE(PassportActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createEmailInterface$25(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$t05J2nSKefzTU-e6O0irNt2AjWs, reason: not valid java name */
    public static /* synthetic */ void m16418$r8$lambda$t05J2nSKefzTUe6O0irNt2AjWs(PassportActivity r0, TLRPC.TL_secureRequiredType r1, ArrayList r2, boolean r3, DialogInterface r4, int r5) {
        r0.lambda$addField$61(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$uiO7CCwysLwCHHE42683c2AD6Ms(PassportActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createPasswordInterface$11(r1, r2);
    }

    /* renamed from: $r8$lambda$v5o6R37v7jaj0j8WbqLc41-NcfM, reason: not valid java name */
    public static /* synthetic */ void m16419$r8$lambda$v5o6R37v7jaj0j8WbqLc41NcfM(PassportActivity r0, View r1, CountrySelectActivity.Country r2) {
        r0.lambda$createIdentityInterface$45(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$wBxFWmrZR4trjMYdkzyQbDtYdso(PassportActivity r0, View r1, MotionEvent r2) {
        return r0.lambda$createAddressInterface$35(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wyyrydFdpm7MdOwSGanv5TtFXMo(PassportActivity r0) {
        r0.lambda$createPhoneInterface$27();
    }

    /* renamed from: $r8$lambda$x3OI-woGBNFKE31nWo44z64fyFw, reason: not valid java name */
    public static /* synthetic */ void m16420$r8$lambda$x3OIwoGBNFKE31nWo44z64fyFw(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$41(r1);
    }

    /* renamed from: $r8$lambda$y2UJitvGtuvPHVQDu6gM-U2jRio, reason: not valid java name */
    public static /* synthetic */ void m16421$r8$lambda$y2UJitvGtuvPHVQDu6gMU2jRio(PassportActivity r0, View r1) {
        r0.lambda$createManageInterface$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$yAq0Zgbkpps5JQGNGpY6cRSXJbs(PassportActivity r0, TLRPC.TL_error r1, ErrorRunnable r2, boolean r3, TLRPC.TL_secureRequiredType r4, TLRPC.TL_secureRequiredType r5, boolean r6, ArrayList r7, Runnable r8) {
        r0.lambda$deleteValueInternal$59(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$ybFjSrndCV0NYfQci3XDsfoOoOk(PassportActivity r0, ArrayList r1, int r2, boolean r3) {
        r0.lambda$processSelectedFiles$72(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$zUUaVs963RwrGAAG6t-OA8BebBQ, reason: not valid java name */
    public static /* synthetic */ void m16422$r8$lambda$zUUaVs963RwrGAAG6tOA8BebBQ(PassportActivity r0, View r1) {
        r0.lambda$createRequestInterface$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$zke05O3LCpxyEF_mLKwGO1zZC40(PassportActivity r0, View r1) {
        r0.lambda$createIdentityInterface$40(r1);
    }

    public static /* synthetic */ void $r8$lambda$zpSXSBH9Jl1Ue6Z13JiM9UoBiyA(TLObject r1) {
        if ((r1 instanceof TLRPC.TL_help_passportConfig) == false) goto L6;
        TLRPC.TL_help_passportConfig r12 = (TLRPC.TL_help_passportConfig) r1;
        SharedConfig.setPassportConfig(r12.countries_langs.data, r12.hash);
        return;
    L6:
        SharedConfig.getCountryLangs();
    }

    /* renamed from: -$$Nest$fgetacceptTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m16423$$Nest$fgetacceptTextView(PassportActivity r0) {
        return r0.acceptTextView;
    }

    /* renamed from: -$$Nest$fgetallowNonLatinName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16424$$Nest$fgetallowNonLatinName(PassportActivity r0) {
        return r0.allowNonLatinName;
    }

    /* renamed from: -$$Nest$fgetchatAttachAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlert m16425$$Nest$fgetchatAttachAlert(PassportActivity r0) {
        return r0.chatAttachAlert;
    }

    /* renamed from: -$$Nest$fgetcodesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16426$$Nest$fgetcodesMap(PassportActivity r0) {
        return r0.codesMap;
    }

    /* renamed from: -$$Nest$fgetcountriesArray, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16427$$Nest$fgetcountriesArray(PassportActivity r0) {
        return r0.countriesArray;
    }

    /* renamed from: -$$Nest$fgetcurrentActivityType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16428$$Nest$fgetcurrentActivityType(PassportActivity r0) {
        return r0.currentActivityType;
    }

    /* renamed from: -$$Nest$fgetcurrentBotId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16429$$Nest$fgetcurrentBotId(PassportActivity r2) {
        return r2.currentBotId;
    }

    /* renamed from: -$$Nest$fgetcurrentCallbackUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16430$$Nest$fgetcurrentCallbackUrl(PassportActivity r0) {
        return r0.currentCallbackUrl;
    }

    /* renamed from: -$$Nest$fgetcurrentCitizeship, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16431$$Nest$fgetcurrentCitizeship(PassportActivity r0) {
        return r0.currentCitizeship;
    }

    /* renamed from: -$$Nest$fgetcurrentDocumentValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16432$$Nest$fgetcurrentDocumentValues(PassportActivity r0) {
        return r0.currentDocumentValues;
    }

    /* renamed from: -$$Nest$fgetcurrentDocumentsType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_secureRequiredType m16433$$Nest$fgetcurrentDocumentsType(PassportActivity r0) {
        return r0.currentDocumentsType;
    }

    /* renamed from: -$$Nest$fgetcurrentEmail, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16434$$Nest$fgetcurrentEmail(PassportActivity r0) {
        return r0.currentEmail;
    }

    /* renamed from: -$$Nest$fgetcurrentExpireDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m16435$$Nest$fgetcurrentExpireDate(PassportActivity r0) {
        return r0.currentExpireDate;
    }

    /* renamed from: -$$Nest$fgetcurrentForm, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.authorizationForm m16436$$Nest$fgetcurrentForm(PassportActivity r0) {
        return r0.currentForm;
    }

    /* renamed from: -$$Nest$fgetcurrentGender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16437$$Nest$fgetcurrentGender(PassportActivity r0) {
        return r0.currentGender;
    }

    /* renamed from: -$$Nest$fgetcurrentNonce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16438$$Nest$fgetcurrentNonce(PassportActivity r0) {
        return r0.currentNonce;
    }

    /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.Password m16439$$Nest$fgetcurrentPassword(PassportActivity r0) {
        return r0.currentPassword;
    }

    /* renamed from: -$$Nest$fgetcurrentPayload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16440$$Nest$fgetcurrentPayload(PassportActivity r0) {
        return r0.currentPayload;
    }

    /* renamed from: -$$Nest$fgetcurrentPhotoViewerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m16441$$Nest$fgetcurrentPhotoViewerLayout(PassportActivity r0) {
        return r0.currentPhotoViewerLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentPublicKey, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16442$$Nest$fgetcurrentPublicKey(PassportActivity r0) {
        return r0.currentPublicKey;
    }

    /* renamed from: -$$Nest$fgetcurrentResidence, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16443$$Nest$fgetcurrentResidence(PassportActivity r0) {
        return r0.currentResidence;
    }

    /* renamed from: -$$Nest$fgetcurrentScope, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16444$$Nest$fgetcurrentScope(PassportActivity r0) {
        return r0.currentScope;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_secureRequiredType m16445$$Nest$fgetcurrentType(PassportActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetcurrentValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16446$$Nest$fgetcurrentValues(PassportActivity r0) {
        return r0.currentValues;
    }

    /* renamed from: -$$Nest$fgetcurrentViewNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16447$$Nest$fgetcurrentViewNum(PassportActivity r0) {
        return r0.currentViewNum;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PassportActivityDelegate m16448$$Nest$fgetdelegate(PassportActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdocumentOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16449$$Nest$fgetdocumentOnly(PassportActivity r0) {
        return r0.documentOnly;
    }

    /* renamed from: -$$Nest$fgetdocuments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16450$$Nest$fgetdocuments(PassportActivity r0) {
        return r0.documents;
    }

    /* renamed from: -$$Nest$fgetdocumentsCells, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16451$$Nest$fgetdocumentsCells(PassportActivity r0) {
        return r0.documentsCells;
    }

    /* renamed from: -$$Nest$fgetdocumentsErrors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16452$$Nest$fgetdocumentsErrors(PassportActivity r0) {
        return r0.documentsErrors;
    }

    /* renamed from: -$$Nest$fgetdoneItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m16453$$Nest$fgetdoneItem(PassportActivity r0) {
        return r0.doneItem;
    }

    /* renamed from: -$$Nest$fgetdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m16454$$Nest$fgetdoneItemAnimation(PassportActivity r0) {
        return r0.doneItemAnimation;
    }

    /* renamed from: -$$Nest$fgetemailCodeLength, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16455$$Nest$fgetemailCodeLength(PassportActivity r0) {
        return r0.emailCodeLength;
    }

    /* renamed from: -$$Nest$fgeterrorsValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16456$$Nest$fgeterrorsValues(PassportActivity r0) {
        return r0.errorsValues;
    }

    /* renamed from: -$$Nest$fgetfieldsErrors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16457$$Nest$fgetfieldsErrors(PassportActivity r0) {
        return r0.fieldsErrors;
    }

    /* renamed from: -$$Nest$fgetfrontDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SecureDocument m16458$$Nest$fgetfrontDocument(PassportActivity r0) {
        return r0.frontDocument;
    }

    /* renamed from: -$$Nest$fgetignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16459$$Nest$fgetignoreOnPhoneChange(PassportActivity r0) {
        return r0.ignoreOnPhoneChange;
    }

    /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16460$$Nest$fgetignoreOnTextChange(PassportActivity r0) {
        return r0.ignoreOnTextChange;
    }

    /* renamed from: -$$Nest$fgetinputExtraFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m16461$$Nest$fgetinputExtraFields(PassportActivity r0) {
        return r0.inputExtraFields;
    }

    /* renamed from: -$$Nest$fgetinputFieldContainers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup[] m16462$$Nest$fgetinputFieldContainers(PassportActivity r0) {
        return r0.inputFieldContainers;
    }

    /* renamed from: -$$Nest$fgetinputFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m16463$$Nest$fgetinputFields(PassportActivity r0) {
        return r0.inputFields;
    }

    /* renamed from: -$$Nest$fgetlanguageMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16464$$Nest$fgetlanguageMap(PassportActivity r0) {
        return r0.languageMap;
    }

    /* renamed from: -$$Nest$fgetnativeInfoCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextInfoPrivacyCell m16465$$Nest$fgetnativeInfoCell(PassportActivity r0) {
        return r0.nativeInfoCell;
    }

    /* renamed from: -$$Nest$fgetneedActivityResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16466$$Nest$fgetneedActivityResult(PassportActivity r0) {
        return r0.needActivityResult;
    }

    /* renamed from: -$$Nest$fgetnonLatinNames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m16467$$Nest$fgetnonLatinNames(PassportActivity r0) {
        return r0.nonLatinNames;
    }

    /* renamed from: -$$Nest$fgetphoneFormatMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16468$$Nest$fgetphoneFormatMap(PassportActivity r0) {
        return r0.phoneFormatMap;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContextProgressView m16469$$Nest$fgetprogressView(PassportActivity r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetprogressViewButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContextProgressView m16470$$Nest$fgetprogressViewButton(PassportActivity r0) {
        return r0.progressViewButton;
    }

    /* renamed from: -$$Nest$fgetreverseDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SecureDocument m16471$$Nest$fgetreverseDocument(PassportActivity r0) {
        return r0.reverseDocument;
    }

    /* renamed from: -$$Nest$fgetreverseLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m16472$$Nest$fgetreverseLayout(PassportActivity r0) {
        return r0.reverseLayout;
    }

    /* renamed from: -$$Nest$fgetsaltedPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m16473$$Nest$fgetsaltedPassword(PassportActivity r0) {
        return r0.saltedPassword;
    }

    /* renamed from: -$$Nest$fgetsavedSaltedPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m16474$$Nest$fgetsavedSaltedPassword(PassportActivity r0) {
        return r0.savedSaltedPassword;
    }

    /* renamed from: -$$Nest$fgetscrollHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16475$$Nest$fgetscrollHeight(PassportActivity r0) {
        return r0.scrollHeight;
    }

    /* renamed from: -$$Nest$fgetsecureSecret, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m16476$$Nest$fgetsecureSecret(PassportActivity r0) {
        return r0.secureSecret;
    }

    /* renamed from: -$$Nest$fgetsecureSecretId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16477$$Nest$fgetsecureSecretId(PassportActivity r2) {
        return r2.secureSecretId;
    }

    /* renamed from: -$$Nest$fgetselfieDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SecureDocument m16478$$Nest$fgetselfieDocument(PassportActivity r0) {
        return r0.selfieDocument;
    }

    /* renamed from: -$$Nest$fgettranslationDocuments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16479$$Nest$fgettranslationDocuments(PassportActivity r0) {
        return r0.translationDocuments;
    }

    /* renamed from: -$$Nest$fgetuploadingDocuments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16480$$Nest$fgetuploadingDocuments(PassportActivity r0) {
        return r0.uploadingDocuments;
    }

    /* renamed from: -$$Nest$fgetuploadingFileType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16481$$Nest$fgetuploadingFileType(PassportActivity r0) {
        return r0.uploadingFileType;
    }

    /* renamed from: -$$Nest$fgetuseCurrentValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16482$$Nest$fgetuseCurrentValue(PassportActivity r0) {
        return r0.useCurrentValue;
    }

    /* renamed from: -$$Nest$fgetviews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SlideView[] m16483$$Nest$fgetviews(PassportActivity r0) {
        return r0.views;
    }

    /* renamed from: -$$Nest$fputallowNonLatinName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16484$$Nest$fputallowNonLatinName(PassportActivity r0, boolean r1) {
        r0.allowNonLatinName = r1;
    }

    /* renamed from: -$$Nest$fputcurrentCitizeship, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16485$$Nest$fputcurrentCitizeship(PassportActivity r0, String r1) {
        r0.currentCitizeship = r1;
    }

    /* renamed from: -$$Nest$fputcurrentEmail, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16486$$Nest$fputcurrentEmail(PassportActivity r0, String r1) {
        r0.currentEmail = r1;
    }

    /* renamed from: -$$Nest$fputcurrentForm, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16487$$Nest$fputcurrentForm(PassportActivity r0, TL_account.authorizationForm r1) {
        r0.currentForm = r1;
    }

    /* renamed from: -$$Nest$fputcurrentGender, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16488$$Nest$fputcurrentGender(PassportActivity r0, String r1) {
        r0.currentGender = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16489$$Nest$fputcurrentPassword(PassportActivity r0, TL_account.Password r1) {
        r0.currentPassword = r1;
    }

    /* renamed from: -$$Nest$fputcurrentResidence, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16490$$Nest$fputcurrentResidence(PassportActivity r0, String r1) {
        r0.currentResidence = r1;
    }

    /* renamed from: -$$Nest$fputdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16491$$Nest$fputdelegate(PassportActivity r0, PassportActivityDelegate r1) {
        r0.delegate = r1;
    }

    /* renamed from: -$$Nest$fputdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16492$$Nest$fputdoneItemAnimation(PassportActivity r0, AnimatorSet r1) {
        r0.doneItemAnimation = r1;
    }

    /* renamed from: -$$Nest$fputemailCodeLength, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16493$$Nest$fputemailCodeLength(PassportActivity r0, int r1) {
        r0.emailCodeLength = r1;
    }

    /* renamed from: -$$Nest$fputfrontDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16494$$Nest$fputfrontDocument(PassportActivity r0, SecureDocument r1) {
        r0.frontDocument = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnFailure, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16495$$Nest$fputignoreOnFailure(PassportActivity r0, boolean r1) {
        r0.ignoreOnFailure = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnPhoneChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16496$$Nest$fputignoreOnPhoneChange(PassportActivity r0, boolean r1) {
        r0.ignoreOnPhoneChange = r1;
    }

    /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16497$$Nest$fputignoreOnTextChange(PassportActivity r0, boolean r1) {
        r0.ignoreOnTextChange = r1;
    }

    /* renamed from: -$$Nest$fputneedActivityResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16498$$Nest$fputneedActivityResult(PassportActivity r0, boolean r1) {
        r0.needActivityResult = r1;
    }

    /* renamed from: -$$Nest$fputpresentAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16499$$Nest$fputpresentAfterAnimation(PassportActivity r0, PassportActivity r1) {
        r0.presentAfterAnimation = r1;
    }

    /* renamed from: -$$Nest$fputreverseDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16500$$Nest$fputreverseDocument(PassportActivity r0, SecureDocument r1) {
        r0.reverseDocument = r1;
    }

    /* renamed from: -$$Nest$fputsaltedPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16501$$Nest$fputsaltedPassword(PassportActivity r0, byte[] r1) {
        r0.saltedPassword = r1;
    }

    /* renamed from: -$$Nest$fputscrollHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16502$$Nest$fputscrollHeight(PassportActivity r0, int r1) {
        r0.scrollHeight = r1;
    }

    /* renamed from: -$$Nest$fputsecureSecret, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16503$$Nest$fputsecureSecret(PassportActivity r0, byte[] r1) {
        r0.secureSecret = r1;
    }

    /* renamed from: -$$Nest$fputsecureSecretId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16504$$Nest$fputsecureSecretId(PassportActivity r0, long r1) {
        r0.secureSecretId = r1;
    }

    /* renamed from: -$$Nest$fputselfieDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16505$$Nest$fputselfieDocument(PassportActivity r0, SecureDocument r1) {
        r0.selfieDocument = r1;
    }

    /* renamed from: -$$Nest$fputusingSavedPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16506$$Nest$fputusingSavedPassword(PassportActivity r0, int r1) {
        r0.usingSavedPassword = r1;
    }

    /* renamed from: -$$Nest$mcallCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16507$$Nest$mcallCallback(PassportActivity r0, boolean r1) {
        r0.callCallback(r1);
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16508$$Nest$mcheckDiscard(PassportActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mcheckFieldForError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16509$$Nest$mcheckFieldForError(PassportActivity r0, EditTextBoldCursor r1, String r2, Editable r3, boolean r4) {
        r0.checkFieldForError(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mcheckFieldsForError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16510$$Nest$mcheckFieldsForError(PassportActivity r0) {
        return r0.checkFieldsForError();
    }

    /* renamed from: -$$Nest$mcheckNativeFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16511$$Nest$mcheckNativeFields(PassportActivity r0, boolean r1) {
        r0.checkNativeFields(r1);
    }

    /* renamed from: -$$Nest$mcreateSecureDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EncryptionResult m16512$$Nest$mcreateSecureDocument(PassportActivity r0, String r1) {
        return r0.createSecureDocument(r1);
    }

    /* renamed from: -$$Nest$mdecryptSecret, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m16513$$Nest$mdecryptSecret(PassportActivity r0, byte[] r1, byte[] r2) {
        return r0.decryptSecret(r1, r2);
    }

    /* renamed from: -$$Nest$mdeleteValueInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16514$$Nest$mdeleteValueInternal(PassportActivity r0, TLRPC.TL_secureRequiredType r1, TLRPC.TL_secureRequiredType r2, ArrayList r3, boolean r4, Runnable r5, ErrorRunnable r6, boolean r7) {
        r0.deleteValueInternal(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$mencryptData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EncryptionResult m16515$$Nest$mencryptData(PassportActivity r0, byte[] r1) {
        return r0.encryptData(r1);
    }

    /* renamed from: -$$Nest$mfillNextCodeParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16516$$Nest$mfillNextCodeParams(PassportActivity r0, Bundle r1, TLRPC.TL_auth_sentCode r2, boolean r3) {
        r0.fillNextCodeParams(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mgetDocumentHash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16517$$Nest$mgetDocumentHash(PassportActivity r0, SecureDocument r1) {
        return r0.getDocumentHash(r1);
    }

    /* renamed from: -$$Nest$mgetFieldCost, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16518$$Nest$mgetFieldCost(PassportActivity r0, String r1) {
        return r0.getFieldCost(r1);
    }

    /* renamed from: -$$Nest$mgetRandomSecret, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m16519$$Nest$mgetRandomSecret(PassportActivity r0) {
        return r0.getRandomSecret();
    }

    /* renamed from: -$$Nest$mgetTranslitString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m16520$$Nest$mgetTranslitString(PassportActivity r0, String r1) {
        return r0.getTranslitString(r1);
    }

    /* renamed from: -$$Nest$misHasNotAnyChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16521$$Nest$misHasNotAnyChanges(PassportActivity r0) {
        return r0.isHasNotAnyChanges();
    }

    /* renamed from: -$$Nest$monFieldError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16522$$Nest$monFieldError(PassportActivity r0, View r1) {
        r0.onFieldError(r1);
    }

    /* renamed from: -$$Nest$monPasscodeError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16523$$Nest$monPasscodeError(PassportActivity r0, boolean r1) {
        r0.onPasscodeError(r1);
    }

    /* renamed from: -$$Nest$monPasswordDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16524$$Nest$monPasswordDone(PassportActivity r0, boolean r1) {
        r0.onPasswordDone(r1);
    }

    /* renamed from: -$$Nest$mprocessSelectedAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16525$$Nest$mprocessSelectedAttach(PassportActivity r0, int r1) {
        r0.processSelectedAttach(r1);
    }

    /* renamed from: -$$Nest$mprocessSelectedFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16526$$Nest$mprocessSelectedFiles(PassportActivity r0, ArrayList r1) {
        r0.processSelectedFiles(r1);
    }

    /* renamed from: -$$Nest$mremoveValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_secureValue m16527$$Nest$mremoveValue(PassportActivity r0, TLRPC.TL_secureRequiredType r1) {
        return r0.removeValue(r1);
    }

    /* renamed from: -$$Nest$msetTypeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16528$$Nest$msetTypeValue(PassportActivity r0, TLRPC.TL_secureRequiredType r1, String r2, String r3, TLRPC.TL_secureRequiredType r4, String r5, boolean r6, int r7) {
        r0.setTypeValue(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$mshowAlertWithText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16529$$Nest$mshowAlertWithText(PassportActivity r0, String r1, String r2) {
        r0.showAlertWithText(r1, r2);
    }

    /* renamed from: -$$Nest$mshowEditDoneProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16530$$Nest$mshowEditDoneProgress(PassportActivity r0, boolean r1, boolean r2) {
        r0.showEditDoneProgress(r1, r2);
    }

    /* renamed from: -$$Nest$mstartPhoneVerification, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16531$$Nest$mstartPhoneVerification(PassportActivity r0, boolean r1, String r2, Runnable r3, ErrorRunnable r4, PassportActivityDelegate r5) {
        r0.startPhoneVerification(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mupdatePasswordInterface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16532$$Nest$mupdatePasswordInterface(PassportActivity r0) {
        r0.updatePasswordInterface();
    }

    /* renamed from: -$$Nest$mupdateUploadText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16533$$Nest$mupdateUploadText(PassportActivity r0, int r1) {
        r0.updateUploadText(r1);
    }

    public PassportActivity(int r17, long r18, String r20, String r21, String r22, String r23, String r24, TL_account.authorizationForm r25, TL_account.Password r26) {
        this(r17, r25, r26, null, null, null, null, null, null);
        this.currentBotId = r18;
        this.currentPayload = r22;
        this.currentNonce = r23;
        this.currentScope = r20;
        this.currentPublicKey = r21;
        this.currentCallbackUrl = r24;
        if (r17 == 0) goto L5;
        return;
    L5:
        if (r25.errors.isEmpty() == true) goto L113;
        Collections.sort(r25.errors, new AnonymousClass2(this));     // Catch: Exception -> L86
        int r1 = r25.errors.size();     // Catch: Exception -> L86
        int r4 = 0;
    L7:
        if (r4 >= r1) goto L115;
        TLRPC.SecureValueError r5 = r25.errors.get(r4);     // Catch: Exception -> L86
        byte[] r8 = null;
        if ((r5 instanceof TLRPC.TL_secureValueErrorFrontSide) == false) goto L13;
        TLRPC.TL_secureValueErrorFrontSide r52 = (TLRPC.TL_secureValueErrorFrontSide) r5;     // Catch: Exception -> L86
        String r6 = getNameForType(r52.type);     // Catch: Exception -> L86
        String r15 = r52.text;     // Catch: Exception -> L86
        r8 = r52.file_hash;     // Catch: Exception -> L86
        String r53 = null;
        String r172 = "front";
    L45:
        HashMap<String, String> r2 = this.errorsMap.get(r6);     // Catch: Exception -> L86
        if (r2 != null) goto L48;
        r2 = new HashMap();     // Catch: Exception -> L86
        int r19 = r1;
        this.errorsMap.put(r6, r2);     // Catch: Exception -> L86
        this.mainErrorsMap.put(r6, r15);     // Catch: Exception -> L86
    L49:
        if (r8 == null) goto L51;
        String r12 = Base64.encodeToString(r8, 2);     // Catch: Exception -> L86
    L53:
        switch(r172.hashCode()) {
            case -1840647503: goto L77;
            case -906020504: goto L74;
            case 3076010: goto L70;
            case 97434231: goto L65;
            case 97705513: goto L62;
            case 329856746: goto L59;
            case 1099846370: goto L56;
            default: goto L84;
        };     // Catch: Exception -> L86
    L84:
        r4 = r4 + 1;
        r1 = r19;
        goto L7
    L56:
        if (r172.equals("reverse") == false) goto L84;
        r2.put("reverse" + r12, r15);     // Catch: Exception -> L86
        goto L84
    L59:
        if (r172.equals("error_all") == false) goto L84;
        r2.put("error_all", r15);     // Catch: Exception -> L86
        goto L84
    L62:
        if (r172.equals("front") == false) goto L84;
        r2.put("front" + r12, r15);     // Catch: Exception -> L86
        goto L84
    L65:
        if (r172.equals("files") == false) goto L84;
        if (r8 == null) goto L68;
        r2.put("files" + r12, r15);     // Catch: Exception -> L86
        goto L84
    L68:
        r2.put("files_all", r15);     // Catch: Exception -> L86
        goto L84
    L70:
        if (r172.equals("data") == false) goto L84;
        if (r53 == null) goto L84;
        r2.put(r53, r15);     // Catch: Exception -> L86
        goto L84
    L74:
        if (r172.equals("selfie") == false) goto L84;
        r2.put("selfie" + r12, r15);     // Catch: Exception -> L86
        goto L84
    L77:
        if (r172.equals("translation") == false) goto L84;
        if (r8 == null) goto L80;
        r2.put("translation" + r12, r15);     // Catch: Exception -> L86
        goto L84
    L80:
        r2.put("translation_all", r15);     // Catch: Exception -> L86
        goto L84
    L51:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L53
    L48:
        r19 = r1;
        goto L49
    L13:
        if ((r5 instanceof TLRPC.TL_secureValueErrorReverseSide) == false) goto L16;
        TLRPC.TL_secureValueErrorReverseSide r54 = (TLRPC.TL_secureValueErrorReverseSide) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r54.type);     // Catch: Exception -> L86
        r15 = r54.text;     // Catch: Exception -> L86
        r8 = r54.file_hash;     // Catch: Exception -> L86
        r53 = null;
        r172 = "reverse";
        goto L45
    L16:
        if ((r5 instanceof TLRPC.TL_secureValueErrorSelfie) == false) goto L19;
        TLRPC.TL_secureValueErrorSelfie r55 = (TLRPC.TL_secureValueErrorSelfie) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r55.type);     // Catch: Exception -> L86
        r15 = r55.text;     // Catch: Exception -> L86
        r8 = r55.file_hash;     // Catch: Exception -> L86
        r53 = null;
        r172 = "selfie";
        goto L45
    L19:
        if ((r5 instanceof TLRPC.TL_secureValueErrorTranslationFile) == false) goto L23;
        TLRPC.TL_secureValueErrorTranslationFile r56 = (TLRPC.TL_secureValueErrorTranslationFile) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r56.type);     // Catch: Exception -> L86
        r15 = r56.text;     // Catch: Exception -> L86
        r8 = r56.file_hash;     // Catch: Exception -> L86
        r53 = null;
    L21:
        r172 = "translation";
        goto L45
    L23:
        if ((r5 instanceof TLRPC.TL_secureValueErrorTranslationFiles) == false) goto L26;
        TLRPC.TL_secureValueErrorTranslationFiles r57 = (TLRPC.TL_secureValueErrorTranslationFiles) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r57.type);     // Catch: Exception -> L86
        r15 = r57.text;     // Catch: Exception -> L86
        r53 = null;
        goto L21
    L26:
        if ((r5 instanceof TLRPC.TL_secureValueErrorFile) == false) goto L30;
        TLRPC.TL_secureValueErrorFile r58 = (TLRPC.TL_secureValueErrorFile) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r58.type);     // Catch: Exception -> L86
        r15 = r58.text;     // Catch: Exception -> L86
        r8 = r58.file_hash;     // Catch: Exception -> L86
        r53 = null;
    L28:
        r172 = "files";
        goto L45
    L30:
        if ((r5 instanceof TLRPC.TL_secureValueErrorFiles) == false) goto L33;
        TLRPC.TL_secureValueErrorFiles r59 = (TLRPC.TL_secureValueErrorFiles) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r59.type);     // Catch: Exception -> L86
        r15 = r59.text;     // Catch: Exception -> L86
        r53 = null;
        goto L28
    L33:
        if ((r5 instanceof TLRPC.TL_secureValueError) == false) goto L36;
        TLRPC.TL_secureValueError r510 = (TLRPC.TL_secureValueError) r5;     // Catch: Exception -> L86
        r6 = getNameForType(r510.type);     // Catch: Exception -> L86
        r15 = r510.text;     // Catch: Exception -> L86
        r8 = r510.hash;     // Catch: Exception -> L86
        r53 = null;
        r172 = "error_all";
        goto L45
    L36:
        if ((r5 instanceof TLRPC.TL_secureValueErrorData) == false) goto L83;
        TLRPC.TL_secureValueErrorData r511 = (TLRPC.TL_secureValueErrorData) r5;     // Catch: Exception -> L86
        int r62 = 0;
    L39:
        if (r62 >= r25.values.size()) goto L83;
        TLRPC.TL_secureData r82 = r25.values.get(r62).data;     // Catch: Exception -> L86
        if (r82 == null) goto L82;
        if (Arrays.equals(r82.data_hash, r511.data_hash) == false) goto L82;
        r6 = getNameForType(r511.type);     // Catch: Exception -> L86
        r15 = r511.text;     // Catch: Exception -> L86
        String r83 = r511.field;     // Catch: Exception -> L86
        r8 = r511.data_hash;     // Catch: Exception -> L86
        r53 = r83;
        r172 = "data";
    L82:
        r62 = r62 + 1;
        r1 = r1;
    L83:
        r19 = r1;
        goto L84
    L115:
        return;
    L114:
        return;
    }

    public PassportActivity(int r4, TL_account.authorizationForm r5, TL_account.Password r6, TLRPC.TL_secureRequiredType r7, TLRPC.TL_secureValue r8, TLRPC.TL_secureRequiredType r9, TLRPC.TL_secureValue r10, HashMap<String, String> r11, HashMap<String, String> r12) {
        this.currentCitizeship = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentResidence = _UrlKt.FRAGMENT_ENCODE_SET;
        this.currentExpireDate = new int[3];
        this.dividers = new ArrayList();
        this.nonLatinNames = new boolean[3];
        this.allowNonLatinName = true;
        this.countriesArray = new ArrayList();
        this.countriesMap = new HashMap();
        this.codesMap = new HashMap();
        this.phoneFormatMap = new HashMap();
        this.documents = new ArrayList();
        this.translationDocuments = new ArrayList();
        this.documentsCells = new HashMap();
        this.uploadingDocuments = new HashMap();
        this.typesValues = new HashMap();
        this.typesViews = new HashMap();
        this.documentsToTypesLink = new HashMap();
        this.errorsMap = new HashMap();
        this.mainErrorsMap = new HashMap();
        this.errorsValues = new HashMap();
        this.provider = new AnonymousClass1(this);
        this.currentActivityType = r4;
        this.currentForm = r5;
        this.currentType = r7;
        if (r7 == null) goto L5;
        this.allowNonLatinName = r7.native_names;
    L5:
        this.currentTypeValue = r8;
        this.currentDocumentsType = r9;
        this.currentDocumentsTypeValue = r10;
        this.currentPassword = r6;
        this.currentValues = r11;
        this.currentDocumentValues = r12;
        if (r4 != 3) goto L9;
        this.permissionsItems = new ArrayList();
    L12:
        if (this.currentValues != null) goto L15;
        this.currentValues = new HashMap();
    L15:
        if (this.currentDocumentValues != null) goto L18;
        this.currentDocumentValues = new HashMap();
    L18:
        if (r4 == 5) goto L20;
        return;
    L20:
        if (UserConfig.getInstance(this.currentAccount).savedPasswordHash != null) goto L22;
    L24:
        TL_account.Password r42 = this.currentPassword;
        if (r42 != null) goto L27;
        loadPasswordInfo();
    L31:
        if (SharedConfig.isPassportConfigLoaded() == true) goto L35;
        TLRPC.TL_help_getPassportConfig r43 = new TLRPC.TL_help_getPassportConfig();
        r43.hash = SharedConfig.passportConfigHash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, new PassportActivity$$ExternalSyntheticLambda0());
        return;
    L35:
        return;
    L27:
        TwoStepVerificationActivity.initPasswordNewAlgo(r42);
        if (this.usingSavedPassword != 1) goto L31;
        onPasswordDone(true);
        goto L31
    L22:
        if (UserConfig.getInstance(this.currentAccount).savedSaltedPassword == null) goto L24;
        this.usingSavedPassword = 1;
        this.savedPasswordHash = UserConfig.getInstance(this.currentAccount).savedPasswordHash;
        this.savedSaltedPassword = UserConfig.getInstance(this.currentAccount).savedSaltedPassword;
        goto L24
    L9:
        if (r4 != 7) goto L12;
        this.views = new SlideView[3];
        goto L12
    }

    public static /* synthetic */ int access$000(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1000(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1202(PassportActivity r0, int r1) {
        r0.currentAccount = r1;
        return r1;
    }

    public static /* synthetic */ int access$1300(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2000(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2302(PassportActivity r0, int r1) {
        r0.currentAccount = r1;
        return r1;
    }

    public static /* synthetic */ int access$2400(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$2500(PassportActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$2600(PassportActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ int access$2700(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3000(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3200(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3400(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(PassportActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$800(PassportActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(PassportActivity r0) {
        return r0.currentAccount;
    }

    private void addDocumentView(SecureDocument r12, int r13) {
        if (r13 != 1) goto L7;
        this.selfieDocument = r12;
        if (this.selfieLayout != null) goto L23;
        return;
    L23:
        if (getParentActivity() != null) goto L25;
        return;
    L25:
        SecureDocumentCell r9 = new SecureDocumentCell(this, getParentActivity());
        r9.setTag(r12);
        r9.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.documentsCells.put(r12, r9);
        String r4 = getDocumentHash(r12);
        if (r13 != 1) goto L29;
        String r0 = LocaleController.getString(R.string.PassportSelfie);
        this.selfieLayout.addView(r9, LayoutHelper.createLinear(-1, -2));
        String r1 = "selfie" + r4;
    L28:
        String r10 = r1;
        HashMap<String, String> r14 = this.documentsErrors;
        if (r14 == null) goto L49;
        String r15 = r14.get(r10);
        if (r15 == null) goto L49;
        SecureDocumentCell.m16577$$Nest$fgetvalueTextView(r9).setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.errorsValues.put(r10, _UrlKt.FRAGMENT_ENCODE_SET);
    L50:
        r9.setTextAndValueAndImage(r0, r15, r12);
        r9.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda47(this, r13));
        r9.setOnLongClickListener(new PassportActivity$$ExternalSyntheticLambda48(this, r13, r12, r9, r10));
        return;
    L49:
        r15 = LocaleController.formatDateForBan(r12.secureFile.date);
        goto L50
    L29:
        if (r13 != 4) goto L31;
        r0 = LocaleController.getString(R.string.AttachPhoto);
        this.translationLayout.addView(r9, LayoutHelper.createLinear(-1, -2));
        r1 = "translation" + r4;
        goto L28
    L31:
        if (r13 != 2) goto L40;
        TLRPC.SecureValueType r02 = this.currentDocumentsType.type;
        if ((r02 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L35;
    L38:
        r0 = LocaleController.getString(R.string.PassportMainPage);
    L39:
        this.frontLayout.addView(r9, LayoutHelper.createLinear(-1, -2));
        r1 = "front" + r4;
        goto L28
    L35:
        if ((r02 instanceof TLRPC.TL_secureValueTypeInternalPassport) == true) goto L38;
        r0 = LocaleController.getString(R.string.PassportFrontSide);
        goto L39
    L40:
        if (r13 != 3) goto L42;
        r0 = LocaleController.getString(R.string.PassportReverseSide);
        this.reverseLayout.addView(r9, LayoutHelper.createLinear(-1, -2));
        r1 = "reverse" + r4;
        goto L28
    L42:
        r0 = LocaleController.getString(R.string.AttachPhoto);
        this.documentsLayout.addView(r9, LayoutHelper.createLinear(-1, -2));
        r1 = "files" + r4;
        goto L28
    L7:
        if (r13 != 4) goto L11;
        this.translationDocuments.add(r12);
        if (this.translationLayout != null) goto L23;
        return;
    L11:
        if (r13 != 2) goto L15;
        this.frontDocument = r12;
        if (this.frontLayout != null) goto L23;
        return;
    L15:
        if (r13 != 3) goto L19;
        this.reverseDocument = r12;
        if (this.reverseLayout != null) goto L23;
        return;
    L19:
        this.documents.add(r12);
        if (this.documentsLayout != null) goto L23;
    }

    private void addDocumentViewInternal(TLRPC.TL_secureFile r9, int r10) {
        addDocumentView(new SecureDocument(getSecureDocumentKey(r9.secret, r9.file_hash), r9, null, null, null), r10);
    }

    private void addDocumentViews(ArrayList<TLRPC.SecureFile> r6) {
        this.documents.clear();
        int r0 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        TLRPC.SecureFile r3 = r6.get(r2);
        if ((r3 instanceof TLRPC.TL_secureFile) == false) goto L7;
        addDocumentViewInternal((TLRPC.TL_secureFile) r3, 0);
    L7:
        r2 = r2 + 1;
        goto L3
    }

    private TextDetailSecureCell addField(Context r17, TLRPC.TL_secureRequiredType r18, ArrayList<TLRPC.TL_secureRequiredType> r19, boolean r20, boolean r21) {
        if (r19 == null) goto L5;
        int r7 = r19.size();
    L6:
        TextDetailSecureCell r8 = new TextDetailSecureCell(this, r17);
        r8.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        TLRPC.SecureValueType r5 = r18.type;
        if ((r5 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L24;
        if (r19 != null) goto L10;
    L21:
        String r52 = LocaleController.getString(R.string.PassportPersonalDetails);
    L22:
        r8.setTextAndValue(r52, _UrlKt.FRAGMENT_ENCODE_SET, !r21);
    L46:
        int r53 = this.currentActivityType;
        LinearLayout r9 = this.linearLayout2;
        if (r53 != 8) goto L49;
        r9.addView(r8, r9.getChildCount() - 5, LayoutHelper.createLinear(-1, -2));
    L50:
        r8.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda57(this, r19, r18, r20));
        this.typesViews.put(r18, r8);
        this.typesValues.put(r18, new HashMap());
        TLRPC.TL_secureValue r54 = getValueByType(r18, false);
        TLRPC.TL_secureRequiredType r92 = null;
        if (r54 == null) goto L62;
        TLRPC.SecurePlainData r10 = r54.plain_data;
        if ((r10 instanceof TLRPC.TL_securePlainEmail) == false) goto L57;
        String r55 = ((TLRPC.TL_securePlainEmail) r10).email;
    L55:
        String r102 = null;
    L63:
        if (r19 != null) goto L65;
    L80:
        String r2 = r55;
        TLRPC.TL_secureRequiredType r4 = null;
        String r56 = null;
        String r3 = r102;
    L81:
        setTypeValue(r18, r2, r3, r4, r56, r20, r7);
        return r8;
    L65:
        if (r19.isEmpty() == true) goto L80;
        int r11 = r19.size();
        int r13 = 0;
        boolean r14 = false;
        String r12 = null;
    L67:
        if (r13 >= r11) goto L77;
        TLRPC.TL_secureRequiredType r15 = r19.get(r13);
        this.typesValues.put(r15, new HashMap());
        this.documentsToTypesLink.put(r15, r18);
        if (r14 == true) goto L76;
        TLRPC.TL_secureValue r42 = getValueByType(r15, false);
        if (r42 == null) goto L76;
        TLRPC.TL_secureData r32 = r42.data;
        if (r32 == null) goto L75;
        r12 = decryptData(r32.data, decryptValueSecret(r32.secret, r32.data_hash), r42.data.data_hash);
    L75:
        r92 = r15;
        r14 = true;
    L76:
        r13 = r13 + 1;
        goto L67
    L77:
        if (r92 != null) goto L79;
        r92 = r19.get(0);
    L79:
        r2 = r55;
        r4 = r92;
        r3 = r102;
        r56 = r12;
        goto L81
    L57:
        if ((r10 instanceof TLRPC.TL_securePlainPhone) == false) goto L59;
        r55 = ((TLRPC.TL_securePlainPhone) r10).phone;
        goto L55
    L59:
        TLRPC.TL_secureData r103 = r54.data;
        if (r103 == null) goto L62;
        r102 = decryptData(r103.data, decryptValueSecret(r103.secret, r103.data_hash), r54.data.data_hash);
        r55 = null;
    L62:
        r55 = null;
        r102 = null;
        goto L63
    L49:
        r9.addView(r8, LayoutHelper.createLinear(-1, -2));
        goto L50
    L10:
        if (r19.isEmpty() == true) goto L21;
        if (r20 == true) goto L14;
    L16:
        if (r20 == true) goto L18;
    L20:
        r52 = LocaleController.getString(R.string.PassportIdentityDocument);
        goto L22
    L18:
        if (r19.size() != 2) goto L20;
        r52 = LocaleController.formatString("PassportTwoDocuments", R.string.PassportTwoDocuments, new Object[]{getTextForType(r19.get(0).type), getTextForType(r19.get(1).type)});
        goto L22
    L14:
        if (r19.size() != 1) goto L16;
        r52 = getTextForType(r19.get(0).type);
        goto L22
    L24:
        if ((r5 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L41;
        if (r19 != null) goto L27;
    L38:
        String r57 = LocaleController.getString(R.string.PassportAddress);
    L39:
        r8.setTextAndValue(r57, _UrlKt.FRAGMENT_ENCODE_SET, !r21);
        goto L46
    L27:
        if (r19.isEmpty() == true) goto L38;
        if (r20 == true) goto L31;
    L33:
        if (r20 == true) goto L35;
    L37:
        r57 = LocaleController.getString(R.string.PassportResidentialAddress);
        goto L39
    L35:
        if (r19.size() != 2) goto L37;
        r57 = LocaleController.formatString("PassportTwoDocuments", R.string.PassportTwoDocuments, new Object[]{getTextForType(r19.get(0).type), getTextForType(r19.get(1).type)});
        goto L39
    L31:
        if (r19.size() != 1) goto L33;
        r57 = getTextForType(r19.get(0).type);
        goto L39
    L41:
        if ((r5 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L44;
        r8.setTextAndValue(LocaleController.getString(R.string.PassportPhone), _UrlKt.FRAGMENT_ENCODE_SET, !r21);
        goto L46
    L44:
        if ((r5 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L46;
        r8.setTextAndValue(LocaleController.getString(R.string.PassportEmail), _UrlKt.FRAGMENT_ENCODE_SET, !r21);
        goto L46
    L5:
        r7 = 0;
        goto L6
    }

    private void addTranslationDocumentViews(ArrayList<TLRPC.SecureFile> r5) {
        this.translationDocuments.clear();
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        TLRPC.SecureFile r2 = r5.get(r1);
        if ((r2 instanceof TLRPC.TL_secureFile) == false) goto L7;
        addDocumentViewInternal((TLRPC.TL_secureFile) r2, 4);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void callCallback(boolean r4) {
        if (this.callbackCalled == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.currentCallbackUrl) == true) goto L17;
        if (r4 == false) goto L9;
        Browser.openUrl(getParentActivity(), Uri.parse(this.currentCallbackUrl + "&tg_passport=success"));
    L14:
        this.callbackCalled = true;
        return;
    L9:
        if (this.ignoreOnFailure == true) goto L14;
        int r42 = this.currentActivityType;
        if (r42 == 5) goto L13;
        if (r42 != 0) goto L14;
    L13:
        Browser.openUrl(getParentActivity(), Uri.parse(this.currentCallbackUrl + "&tg_passport=cancel"));
        goto L14
    L17:
        if (this.needActivityResult == false) goto L32;
        if (r4 == false) goto L20;
    L24:
        Activity r0 = getParentActivity();
        if (r4 == false) goto L27;
        int r43 = -1;
    L28:
        r0.setResult(r43);
    L29:
        this.callbackCalled = true;
        return;
    L27:
        r43 = 0;
        goto L28
    L20:
        if (this.ignoreOnFailure == true) goto L29;
        int r02 = this.currentActivityType;
        if (r02 == 5) goto L24;
        if (r02 != 0) goto L29;
    }

    private boolean checkDiscard(boolean r3) {
        if (isHasNotAnyChanges() == false) goto L6;
        return false;
    L6:
        if (r3 == false) goto L10;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new PassportActivity$$ExternalSyntheticLambda27(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r32.setTitle(LocaleController.getString(R.string.DiscardChanges));
        r32.setMessage(LocaleController.getString(R.string.PassportDiscardChanges));
        showDialog(r32.create());
        return true;
    L10:
        return true;
    }

    private void checkFieldForError(EditTextBoldCursor r3, String r4, Editable r5, boolean r6) {
        HashMap<String, String> r0 = this.errorsValues;
        if (r0 == null) goto L19;
        String r02 = r0.get(r4);
        if (r02 == null) goto L19;
        if (TextUtils.equals(r02, r5) == false) goto L18;
        HashMap<String, String> r52 = this.fieldsErrors;
        if (r52 == null) goto L13;
        String r53 = r52.get(r4);
        if (r53 == null) goto L13;
        r3.setErrorText(r53);
    L20:
        if (r6 == false) goto L22;
        String r32 = "error_document_all";
    L23:
        HashMap<String, String> r42 = this.errorsValues;
        if (r42 != null) goto L26;
        return;
    L26:
        if (r42.containsKey(r32) == false) goto L30;
        this.errorsValues.remove(r32);
        checkTopErrorCell(false);
        return;
    L30:
        return;
    L22:
        r32 = "error_all";
    L13:
        HashMap<String, String> r54 = this.documentsErrors;
        if (r54 == null) goto L20;
        String r43 = r54.get(r4);
        if (r43 == null) goto L20;
        r3.setErrorText(r43);
        goto L20
    L18:
        r3.setErrorText(null);
    L19:
        r3.setErrorText(null);
        goto L20
    }

    private boolean checkFieldsForError() {
        if (this.currentDocumentsType != null) goto L5;
    L82:
        int r0 = 0;
    L84:
        if (r0 >= 2) goto L200;
        if (r0 != 0) goto L88;
        EditTextBoldCursor[] r5 = this.inputFields;
    L94:
        if (r5 == null) goto L199;
        int r6 = 0;
    L98:
        if (r6 >= r5.length) goto L199;
        boolean r7 = r5[r6].hasErrorText();
        if (this.errorsValues.isEmpty() == true) goto L143;
        TLRPC.SecureValueType r8 = this.currentType.type;
        String r12 = "country_code";
        if ((r8 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L122;
        if (r0 != 0) goto L114;
        switch(r6) {
            case 0: goto L113;
            case 1: goto L112;
            case 2: goto L111;
            case 3: goto L110;
            case 4: goto L109;
            case 5: goto L136;
            case 6: goto L108;
            case 7: goto L107;
            case 8: goto L106;
            default: goto L105;
        };
    L105:
        r12 = null;
        goto L136
    L106:
        r12 = "expiry_date";
        goto L136
    L107:
        r12 = "document_no";
        goto L136
    L108:
        r12 = "residence_country_code";
        goto L136
    L109:
        r12 = "gender";
        goto L136
    L110:
        r12 = "birth_date";
        goto L136
    L111:
        r12 = "last_name";
        goto L136
    L112:
        r12 = "middle_name";
        goto L136
    L113:
        r12 = "first_name";
    L136:
        if (r12 == null) goto L143;
        String r82 = this.errorsValues.get(r12);
        if (TextUtils.isEmpty(r82) == true) goto L143;
        if (r82.equals(r5[r6].getText().toString()) == false) goto L143;
        r7 = true;
        goto L143
    L114:
        if (r6 == 0) goto L120;
        if (r6 == 1) goto L119;
        if (r6 != 2) goto L105;
        r12 = "last_name_native";
        goto L136
    L119:
        r12 = "middle_name_native";
        goto L136
    L120:
        r12 = "first_name_native";
        goto L136
    L122:
        if ((r8 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L105;
        if (r6 == 0) goto L135;
        if (r6 == 1) goto L134;
        if (r6 == 2) goto L133;
        if (r6 == 3) goto L132;
        if (r6 != 4) goto L129;
        r12 = "state";
        goto L136
    L129:
        if (r6 == 5) goto L136;
    L132:
        r12 = "city";
        goto L136
    L133:
        r12 = "post_code";
        goto L136
    L134:
        r12 = "street_line2";
        goto L136
    L135:
        r12 = "street_line1";
    L143:
        if (this.documentOnly == true) goto L145;
    L148:
        if (r7 == true) goto L195;
        int r83 = r5[r6].length();
        int r122 = this.currentActivityType;
        if (r122 == 1) goto L152;
        if (r122 == 2) goto L176;
    L170:
        boolean r9 = false;
    L191:
        if (r7 == true) goto L195;
        if (r9 == true) goto L195;
        if (r83 != 0) goto L195;
        r7 = true;
        goto L195
    L176:
        if (r6 == 1) goto L198;
        if (r6 != 3) goto L181;
        if (r83 >= 2) goto L170;
    L174:
        r9 = false;
        r7 = true;
        goto L191
    L181:
        if (r6 == 4) goto L183;
        if (r6 != 2) goto L170;
        if (r83 < 2) goto L174;
        if (r83 <= 10) goto L170;
    L183:
        if ("US".equals(this.currentCitizeship) == false) goto L198;
        if (r83 >= 2) goto L170;
    L198:
        r6 = r6 + 1;
        goto L98
    L152:
        if (r6 == 8) goto L198;
        if (r0 != 0) goto L158;
        if (r6 == 0) goto L163;
        if (r6 == 2) goto L163;
        if (r6 != 1) goto L158;
    L163:
        if (r83 <= 255) goto L165;
        r7 = true;
    L165:
        if (r0 != 0) goto L167;
        if (r6 != 1) goto L167;
    L169:
        r9 = true;
    L167:
        if (r0 != 1) goto L170;
        if (r6 != 1) goto L170;
    L158:
        if (r0 != 1) goto L171;
        if (r6 == 0) goto L163;
        if (r6 == 1) goto L163;
        if (r6 == 2) goto L163;
    L171:
        if (r6 != 7) goto L170;
        if (r83 <= 24) goto L170;
    L195:
        if (r7 == false) goto L198;
        onFieldError(r5[r6]);
        return true;
    L145:
        if (this.currentDocumentsType == null) goto L148;
        if (r6 >= 7) goto L148;
    L199:
        r0 = r0 + 1;
        goto L84
    L88:
        TextInfoPrivacyCell r52 = this.nativeInfoCell;
        if (r52 != null) goto L91;
    L93:
        r5 = null;
        goto L94
    L91:
        if (r52.getVisibility() != 0) goto L93;
        r5 = this.inputExtraFields;
        goto L94
    L200:
        return false;
    L5:
        if (this.errorsValues.containsKey("error_all") == false) goto L7;
    L80:
        onFieldError(this.topErrorCell);
        return true;
    L7:
        if (this.errorsValues.containsKey("error_document_all") == true) goto L80;
        if (this.uploadDocumentCell == null) goto L23;
        if (this.documents.isEmpty() == false) goto L15;
        onFieldError(this.uploadDocumentCell);
        return true;
    L15:
        int r02 = this.documents.size();
        int r3 = 0;
    L16:
        if (r3 >= r02) goto L23;
        SecureDocument r4 = this.documents.get(r3);
        if (this.errorsValues.containsKey("files" + getDocumentHash(r4)) == true) goto L19;
        r3 = r3 + 1;
        goto L16
    L19:
        onFieldError(this.documentsCells.get(r4));
        return true;
    L23:
        if (this.errorsValues.containsKey("files_all") == false) goto L25;
    L78:
        onFieldError(this.bottomCell);
        return true;
    L25:
        if (this.errorsValues.containsKey("translation_all") == true) goto L78;
        View r03 = this.uploadFrontCell;
        if (r03 != null) goto L30;
    L37:
        TLRPC.SecureValueType r04 = this.currentDocumentsType.type;
        if ((r04 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L40;
    L41:
        View r05 = this.uploadReverseCell;
        if (r05 != null) goto L44;
    L51:
        View r06 = this.uploadSelfieCell;
        if (r06 == null) goto L64;
        if (this.currentBotId == 0) goto L64;
        if (this.selfieDocument != null) goto L60;
        onFieldError(r06);
        return true;
    L60:
        if (this.errorsValues.containsKey("selfie" + getDocumentHash(this.selfieDocument)) == false) goto L64;
        onFieldError(this.documentsCells.get(this.selfieDocument));
        return true;
    L64:
        if (this.uploadTranslationCell == null) goto L82;
        if (this.currentBotId == 0) goto L82;
        if (this.translationDocuments.isEmpty() == false) goto L71;
        onFieldError(this.uploadTranslationCell);
        return true;
    L71:
        int r07 = this.translationDocuments.size();
        int r32 = 0;
    L72:
        if (r32 >= r07) goto L82;
        SecureDocument r42 = this.translationDocuments.get(r32);
        if (this.errorsValues.containsKey("translation" + getDocumentHash(r42)) == true) goto L75;
        r32 = r32 + 1;
        goto L72
    L75:
        onFieldError(this.documentsCells.get(r42));
        return true;
    L44:
        if (this.reverseDocument != null) goto L48;
        onFieldError(r05);
        return true;
    L48:
        if (this.errorsValues.containsKey("reverse" + getDocumentHash(this.reverseDocument)) == false) goto L51;
        onFieldError(this.documentsCells.get(this.reverseDocument));
        return true;
    L40:
        if ((r04 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L51;
    L30:
        if (this.frontDocument != null) goto L34;
        onFieldError(r03);
        return true;
    L34:
        if (this.errorsValues.containsKey("front" + getDocumentHash(this.frontDocument)) == false) goto L37;
        onFieldError(this.documentsCells.get(this.frontDocument));
        return true;
    }

    private void checkNativeFields(boolean r10) {
        if (this.inputExtraFields == null) goto L97;
        String r0 = this.languageMap.get(this.currentResidence);
        String r1 = SharedConfig.getCountryLangs().get(this.currentResidence);
        int r3 = 0;
        if (this.currentType.native_names == false) goto L63;
        if (TextUtils.isEmpty(this.currentResidence) == true) goto L63;
        if ("EN".equals(r1) == true) goto L63;
        if (this.nativeInfoCell.getVisibility() == 0) goto L32;
        this.nativeInfoCell.setVisibility(0);
        this.headerCell.setVisibility(0);
        this.extraBackgroundView2.setVisibility(0);
        int r2 = 0;
    L15:
        EditTextBoldCursor[] r6 = this.inputExtraFields;
        if (r2 >= r6.length) goto L19;
        ((View) r6[r2].getParent()).setVisibility(0);
        r2 = r2 + 1;
        goto L15
    L19:
        if (r6[0].length() == 0) goto L21;
    L31:
        this.sectionCell2.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        goto L32
    L21:
        if (this.inputExtraFields[1].length() != 0) goto L31;
        if (this.inputExtraFields[2].length() != 0) goto L31;
        int r22 = 0;
    L25:
        boolean[] r62 = this.nonLatinNames;
        if (r22 >= r62.length) goto L31;
        if (r62[r22] == true) goto L29;
        r22 = r22 + 1;
        goto L25
    L29:
        this.inputExtraFields[0].setText(this.inputFields[0].getText());
        this.inputExtraFields[1].setText(this.inputFields[1].getText());
        this.inputExtraFields[2].setText(this.inputFields[2].getText());
    L32:
        this.nativeInfoCell.setText(LocaleController.formatString("PassportNativeInfo", R.string.PassportNativeInfo, new Object[]{r0}));
        if (r1 == null) goto L35;
        String r12 = LocaleController.getServerString("PassportLanguage_".concat(r1));
    L36:
        HeaderCell r23 = this.headerCell;
        if (r12 == null) goto L39;
        r23.setText(LocaleController.formatString("PassportNativeHeaderLang", R.string.PassportNativeHeaderLang, new Object[]{r12}));
    L41:
        if (r3 >= 3) goto L59;
        if (r3 == 0) goto L54;
        if (r3 == 1) goto L50;
        if (r3 != 2) goto L58;
        EditTextBoldCursor[] r24 = this.inputExtraFields;
        if (r12 == null) goto L49;
        r24[r3].setHintText(LocaleController.getString(R.string.PassportSurname));
        goto L58
    L49:
        r24[r3].setHintText(LocaleController.formatString("PassportSurnameCountry", R.string.PassportSurnameCountry, new Object[]{r0}));
    L58:
        r3 = r3 + 1;
        goto L41
    L50:
        EditTextBoldCursor[] r25 = this.inputExtraFields;
        if (r12 == null) goto L53;
        r25[r3].setHintText(LocaleController.getString(R.string.PassportMidname));
        goto L58
    L53:
        r25[r3].setHintText(LocaleController.formatString("PassportMidnameCountry", R.string.PassportMidnameCountry, new Object[]{r0}));
        goto L58
    L54:
        EditTextBoldCursor[] r26 = this.inputExtraFields;
        if (r12 == null) goto L57;
        r26[r3].setHintText(LocaleController.getString(R.string.PassportName));
        goto L58
    L57:
        r26[r3].setHintText(LocaleController.formatString("PassportNameCountry", R.string.PassportNameCountry, new Object[]{r0}));
        goto L58
    L59:
        if (r10 == false) goto L95;
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda45(this));
        return;
    L95:
        return;
    L39:
        r23.setText(LocaleController.getString(R.string.PassportNativeHeader));
        goto L41
    L35:
        r12 = null;
    L63:
        if (this.nativeInfoCell.getVisibility() == 8) goto L96;
        this.nativeInfoCell.setVisibility(8);
        this.headerCell.setVisibility(8);
        this.extraBackgroundView2.setVisibility(8);
    L65:
        EditTextBoldCursor[] r102 = this.inputExtraFields;
        if (r3 >= r102.length) goto L69;
        ((View) r102[r3].getParent()).setVisibility(8);
        r3 = r3 + 1;
        goto L65
    L69:
        if (this.currentBotId != 0) goto L73;
        if (this.currentDocumentsType == null) goto L73;
    L77:
        if (this.currentDocumentsTypeValue == null) goto L80;
    L78:
        this.sectionCell2.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        return;
    L80:
        this.sectionCell2.setBackgroundDrawable(Theme.getThemedDrawableByKey(getParentActivity(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        return;
    L73:
        if (this.currentTypeValue == null) goto L77;
        if (this.documentOnly == false) goto L78;
    L96:
        return;
    }

    public static boolean checkSecret(byte[] r4, Long r5) {
        if (r4 != null) goto L5;
    L20:
        return false;
    L5:
        if (r4.length != 32) goto L20;
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r1 >= r4.length) goto L12;
        r2 = r2 + (r4[r1] & UByte.MAX_VALUE);
        r1 = r1 + 1;
        goto L9
    L12:
        if ((r2 % 255) == 239) goto L14;
        return false;
    L14:
        if (r5 != null) goto L16;
        return true;
    L16:
        if (Utilities.bytesToLong(Utilities.computeSHA256(r4)) == r5.longValue()) goto L22;
        return false;
    L22:
        return true;
    }

    private void checkTopErrorCell(boolean r6) {
        if (this.topErrorCell != null) goto L6;
        return;
    L6:
        if (this.fieldsErrors == null) goto L15;
        if (r6 == false) goto L9;
    L10:
        String r0 = this.fieldsErrors.get("error_all");
        if (r0 == null) goto L15;
        SpannableStringBuilder r3 = new SpannableStringBuilder(r0);
        if (r6 == false) goto L17;
        this.errorsValues.put("error_all", _UrlKt.FRAGMENT_ENCODE_SET);
    L17:
        if (this.documentsErrors != null) goto L19;
    L29:
        if (r3 == null) goto L33;
        r3.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_text_RedRegular)), 0, r3.length(), 33);
        this.topErrorCell.setText(r3);
        this.topErrorCell.setVisibility(0);
        return;
    L33:
        if (this.topErrorCell.getVisibility() == 8) goto L36;
        this.topErrorCell.setVisibility(8);
        return;
    L36:
        return;
    L19:
        if (r6 == false) goto L21;
    L22:
        String r2 = this.documentsErrors.get("error_all");
        if (r2 == null) goto L29;
        if (r3 != null) goto L26;
        r3 = new SpannableStringBuilder(r2);
    L27:
        if (r6 == false) goto L29;
        this.errorsValues.put("error_document_all", _UrlKt.FRAGMENT_ENCODE_SET);
        goto L29
    L26:
        r3.append("\n\n").append(r2);
        goto L27
    L21:
        if (this.errorsValues.containsKey("error_document_all") == false) goto L29;
    L9:
        if (this.errorsValues.containsKey("error_all") == true) goto L10;
    L15:
        r3 = null;
        goto L17
    }

    private void createAddressInterface(Context r26) {
        this.languageMap = new HashMap();
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r26.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L7
    L4:
        String r5 = r0.readLine();     // Catch: Exception -> L7
        if (r5 == null) goto L9;
        String[] r52 = r5.split(";");     // Catch: Exception -> L7
        this.languageMap.put(r52[1], r52[2]);     // Catch: Exception -> L7
        goto L4
    L9:
        r0.close();     // Catch: Exception -> L7
    L12:
        TextInfoPrivacyCell r02 = new TextInfoPrivacyCell(r26);
        this.topErrorCell = r02;
        int r53 = R.drawable.greydivider_top;
        int r6 = Theme.key_windowBackgroundGrayShadow;
        r02.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, r53, r6));
        this.topErrorCell.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.linearLayout2.addView(this.topErrorCell, LayoutHelper.createLinear(-1, -2));
        checkTopErrorCell(true);
        TLRPC.TL_secureRequiredType r03 = this.currentDocumentsType;
        if (r03 == null) goto L80;
        TLRPC.SecureValueType r04 = r03.type;
        if ((r04 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L18;
        this.actionBar.setTitle(LocaleController.getString(R.string.ActionBotDocumentRentalAgreement));
    L29:
        HeaderCell r05 = new HeaderCell(r26);
        this.headerCell = r05;
        r05.setText(LocaleController.getString(R.string.PassportDocuments));
        HeaderCell r06 = this.headerCell;
        int r54 = Theme.key_windowBackgroundWhite;
        r06.setBackgroundColor(Theme.getColor(r54));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        LinearLayout r07 = new LinearLayout(r26);
        this.documentsLayout = r07;
        r07.setOrientation(1);
        this.linearLayout2.addView(this.documentsLayout, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r08 = new TextSettingsCell(r26);
        this.uploadDocumentCell = r08;
        r08.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.linearLayout2.addView(this.uploadDocumentCell, LayoutHelper.createLinear(-1, -2));
        this.uploadDocumentCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda28(this));
        TextInfoPrivacyCell r09 = new TextInfoPrivacyCell(r26);
        this.bottomCell = r09;
        r09.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, R.drawable.greydivider, r6));
        if (this.currentBotId == 0) goto L32;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddAddressUploadInfo);
    L48:
        CharSequence r010 = this.noAllDocumentsErrorText;
        HashMap<String, String> r13 = this.documentsErrors;
        long r16 = 0;
        CharSequence r011 = r010;
        if (r13 == null) goto L53;
        String r11 = r13.get("files_all");
        r011 = r010;
        if (r11 == null) goto L53;
        SpannableStringBuilder r012 = new SpannableStringBuilder(r11);
        r012.append("\n\n");
        r012.append(this.noAllDocumentsErrorText);
        r012.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_text_RedRegular)), 0, r11.length(), 33);
        this.errorsValues.put("files_all", _UrlKt.FRAGMENT_ENCODE_SET);
        r011 = r012;
    L53:
        this.bottomCell.setText(r011);
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        if (this.currentDocumentsType.translation_required == false) goto L81;
        HeaderCell r013 = new HeaderCell(r26);
        this.headerCell = r013;
        r013.setText(LocaleController.getString(R.string.PassportTranslation));
        this.headerCell.setBackgroundColor(Theme.getColor(r54));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        LinearLayout r014 = new LinearLayout(r26);
        this.translationLayout = r014;
        r014.setOrientation(1);
        this.linearLayout2.addView(this.translationLayout, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r015 = new TextSettingsCell(r26);
        this.uploadTranslationCell = r015;
        r015.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.linearLayout2.addView(this.uploadTranslationCell, LayoutHelper.createLinear(-1, -2));
        this.uploadTranslationCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda29(this));
        TextInfoPrivacyCell r016 = new TextInfoPrivacyCell(r26);
        this.bottomCellTranslation = r016;
        r016.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, R.drawable.greydivider, r6));
        if (this.currentBotId == 0) goto L58;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationUploadInfo);
    L74:
        CharSequence r017 = this.noAllTranslationErrorText;
        HashMap<String, String> r3 = this.documentsErrors;
        CharSequence r018 = r017;
        if (r3 == null) goto L79;
        String r32 = r3.get("translation_all");
        r018 = r017;
        if (r32 == null) goto L79;
        SpannableStringBuilder r019 = new SpannableStringBuilder(r32);
        r019.append("\n\n");
        r019.append(this.noAllTranslationErrorText);
        r019.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_text_RedRegular)), 0, r32.length(), 33);
        this.errorsValues.put("translation_all", _UrlKt.FRAGMENT_ENCODE_SET);
        r018 = r019;
    L79:
        this.bottomCellTranslation.setText(r018);
        this.linearLayout2.addView(this.bottomCellTranslation, LayoutHelper.createLinear(-1, -2));
        goto L81
    L58:
        TLRPC.SecureValueType r020 = this.currentDocumentsType.type;
        if ((r020 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L62;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationAgreementInfo);
        goto L74
    L62:
        if ((r020 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L65;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationBillInfo);
        goto L74
    L65:
        if ((r020 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L68;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationPassportRegistrationInfo);
        goto L74
    L68:
        if ((r020 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L71;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationTemporaryRegistrationInfo);
        goto L74
    L71:
        if ((r020 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L73;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationBankInfo);
        goto L74
    L73:
        this.noAllTranslationErrorText = _UrlKt.FRAGMENT_ENCODE_SET;
    L81:
        HeaderCell r021 = new HeaderCell(r26);
        this.headerCell = r021;
        r021.setText(LocaleController.getString(R.string.PassportAddressHeader));
        this.headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        this.inputFields = new EditTextBoldCursor[6];
        int r33 = 0;
    L83:
        if (r33 >= 6) goto L123;
        EditTextBoldCursor r10 = new EditTextBoldCursor(r26);
        this.inputFields[r33] = r10;
        AnonymousClass11 r112 = new AnonymousClass11(this, r26, r10);
        r112.setWillNotDraw(false);
        this.linearLayout2.addView(r112, LayoutHelper.createLinear(-1, -2));
        int r12 = Theme.key_windowBackgroundWhite;
        r112.setBackgroundColor(Theme.getColor(r12));
        int r132 = 5;
        if (r33 != 5) goto L88;
        View r14 = new View(r26);
        this.extraBackgroundView = r14;
        r14.setBackgroundColor(Theme.getColor(r12));
        this.linearLayout2.addView(this.extraBackgroundView, LayoutHelper.createLinear(-1, 6));
    L88:
        if (this.documentOnly == true) goto L90;
    L94:
        this.inputFields[r33].setTag(Integer.valueOf(r33));
        this.inputFields[r33].setSupportRtlHint(true);
        this.inputFields[r33].setTextSize(1, 16.0f);
        this.inputFields[r33].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.inputFields[r33].setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
        this.inputFields[r33].setTransformHintToHeader(true);
        EditTextBoldCursor r62 = this.inputFields[r33];
        int r122 = Theme.key_windowBackgroundWhiteBlackText;
        r62.setTextColor(Theme.getColor(r122));
        this.inputFields[r33].setBackgroundDrawable(null);
        this.inputFields[r33].setCursorColor(Theme.getColor(r122));
        this.inputFields[r33].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r33].setCursorWidth(1.5f);
        this.inputFields[r33].setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated), Theme.getColor(Theme.key_text_RedRegular));
        EditTextBoldCursor[] r63 = this.inputFields;
        if (r33 != 5) goto L97;
        r63[r33].setOnTouchListener(new PassportActivity$$ExternalSyntheticLambda30(this));
        this.inputFields[r33].setInputType(0);
        this.inputFields[r33].setFocusable(false);
    L99:
        if (r33 == 0) goto L112;
        if (r33 != 1) goto L102;
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportStreet2));
        String r55 = "street_line2";
    L113:
        setFieldValues(this.currentValues, this.inputFields[r33], r55);
        EditTextBoldCursor[] r123 = this.inputFields;
        if (r33 != 2) goto L116;
        r123[r33].addTextChangedListener(new AnonymousClass12(this, r10, r55));
        this.inputFields[r33].setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
    L117:
        EditTextBoldCursor r56 = this.inputFields[r33];
        r56.setSelection(r56.length());
        this.inputFields[r33].setPadding(0, 0, 0, 0);
        EditTextBoldCursor r57 = this.inputFields[r33];
        if (LocaleController.isRTL == true) goto L121;
        r132 = 3;
    L121:
        r57.setGravity(r132 | 16);
        r112.addView(this.inputFields[r33], LayoutHelper.createFrame(-1, 64.0f, 51, 21.0f, 0.0f, 21.0f, 0.0f));
        this.inputFields[r33].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda31(this));
    L122:
        r33 = r33 + 1;
        goto L83
    L116:
        r123[r33].addTextChangedListener(new AnonymousClass13(this, r10, r55));
        goto L117
    L102:
        if (r33 == 2) goto L110;
        if (r33 == 3) goto L109;
        if (r33 == 4) goto L108;
        if (r33 != 5) goto L122;
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportCountry));
        r55 = "country_code";
        goto L113
    L108:
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportState));
        r55 = "state";
        goto L113
    L109:
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportCity));
        r55 = "city";
        goto L113
    L110:
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportPostcode));
        r55 = "post_code";
        goto L113
    L112:
        this.inputFields[r33].setHintText(LocaleController.getString(R.string.PassportStreet1));
        r55 = "street_line1";
        goto L113
    L97:
        r63[r33].setInputType(16385);
        this.inputFields[r33].setImeOptions(268435461);
        goto L99
    L90:
        if (this.currentDocumentsType == null) goto L94;
        r112.setVisibility(8);
        View r124 = this.extraBackgroundView;
        if (r124 == null) goto L94;
        r124.setVisibility(8);
        goto L94
    L123:
        ShadowSectionCell r022 = new ShadowSectionCell(r26);
        this.sectionCell = r022;
        this.linearLayout2.addView(r022, LayoutHelper.createLinear(-1, -2));
        if (this.documentOnly == false) goto L129;
        if (this.currentDocumentsType == null) goto L129;
        this.headerCell.setVisibility(8);
        this.sectionCell.setVisibility(8);
    L129:
        if (this.currentBotId != r16) goto L133;
        if (this.currentDocumentsType == null) goto L133;
    L137:
        if (this.currentDocumentsTypeValue == null) goto L146;
    L138:
        TLRPC.TL_secureValue r023 = this.currentDocumentsTypeValue;
        if (r023 == null) goto L141;
        addDocumentViews(r023.files);
        addTranslationDocumentViews(this.currentDocumentsTypeValue.translation);
    L141:
        ShadowSectionCell r024 = this.sectionCell;
        int r34 = R.drawable.greydivider;
        int r64 = Theme.key_windowBackgroundGrayShadow;
        r024.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, r34, r64));
        TextSettingsCell r025 = new TextSettingsCell(r26);
        r025.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        r025.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        if (this.currentDocumentsType != null) goto L144;
        r025.setText(LocaleController.getString(R.string.PassportDeleteInfo), false);
    L145:
        this.linearLayout2.addView(r025, LayoutHelper.createLinear(-1, -2));
        r025.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda32(this));
        ShadowSectionCell r026 = new ShadowSectionCell(r26);
        this.sectionCell = r026;
        r026.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, R.drawable.greydivider_bottom, r64));
        this.linearLayout2.addView(this.sectionCell, LayoutHelper.createLinear(-1, -2));
    L151:
        updateUploadText(0);
        updateUploadText(4);
        return;
    L144:
        r025.setText(LocaleController.getString(R.string.PassportDeleteDocument), false);
        goto L145
    L146:
        ShadowSectionCell r027 = this.sectionCell;
        int r35 = R.drawable.greydivider_bottom;
        int r4 = Theme.key_windowBackgroundGrayShadow;
        r027.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, r35, r4));
        if (this.documentOnly == false) goto L151;
        if (this.currentDocumentsType == null) goto L151;
        this.bottomCell.setBackgroundDrawable(Theme.getThemedDrawableByKey(r26, R.drawable.greydivider_bottom, r4));
    L133:
        if (this.currentTypeValue == null) goto L137;
        if (this.documentOnly == false) goto L138;
    L32:
        TLRPC.SecureValueType r028 = this.currentDocumentsType.type;
        if ((r028 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L36;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddAgreementInfo);
        goto L48
    L36:
        if ((r028 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L39;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddBillInfo);
        goto L48
    L39:
        if ((r028 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L42;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddPassportRegistrationInfo);
        goto L48
    L42:
        if ((r028 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L45;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddTemporaryRegistrationInfo);
        goto L48
    L45:
        if ((r028 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L47;
        this.noAllDocumentsErrorText = LocaleController.getString(R.string.PassportAddBankInfo);
        goto L48
    L47:
        this.noAllDocumentsErrorText = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L48
    L18:
        if ((r04 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L21;
        this.actionBar.setTitle(LocaleController.getString(R.string.ActionBotDocumentBankStatement));
        goto L29
    L21:
        if ((r04 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L24;
        this.actionBar.setTitle(LocaleController.getString(R.string.ActionBotDocumentUtilityBill));
        goto L29
    L24:
        if ((r04 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L27;
        this.actionBar.setTitle(LocaleController.getString(R.string.ActionBotDocumentPassportRegistration));
        goto L29
    L27:
        if ((r04 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L29;
        this.actionBar.setTitle(LocaleController.getString(R.string.ActionBotDocumentTemporaryRegistration));
        goto L29
    L80:
        r16 = 0;
        this.actionBar.setTitle(LocaleController.getString(R.string.PassportAddress));
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    private void createChatAttachView() {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.chatAttachAlert != null) goto L9;
        ChatAttachAlert r0 = new ChatAttachAlert(getParentActivity(), this, false, false);
        this.chatAttachAlert = r0;
        r0.setDelegate(new AnonymousClass24(this));
        return;
    }

    private void createDocumentDeleteAlert() {
        boolean[] r1 = {true};
        AlertDialog.Builder r3 = new AlertDialog.Builder(getParentActivity());
        r3.setPositiveButton(LocaleController.getString(R.string.OK), new PassportActivity$$ExternalSyntheticLambda50(this, r1));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3.setTitle(LocaleController.getString(R.string.AppName));
        boolean r4 = this.documentOnly;
        if (r4 == true) goto L5;
    L9:
        if (r4 == true) goto L11;
    L15:
        r3.setMessage(LocaleController.getString(R.string.PassportDeleteDocumentAlert));
    L17:
        if (this.documentOnly == false) goto L19;
    L35:
        showDialog(r3.create());
        return;
    L19:
        if (this.currentDocumentsType == null) goto L35;
        FrameLayout r42 = new FrameLayout(getParentActivity());
        CheckBoxCell r5 = new CheckBoxCell(getParentActivity(), 1);
        r5.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        TLRPC.SecureValueType r6 = this.currentType.type;
        if ((r6 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L24;
        r5.setText(LocaleController.getString(R.string.PassportDeleteDocumentAddress), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
    L27:
        if (LocaleController.isRTL == false) goto L29;
        int r0 = AndroidUtilities.dp(16.0f);
    L31:
        if (LocaleController.isRTL == false) goto L33;
        int r62 = AndroidUtilities.dp(8.0f);
    L34:
        r5.setPadding(r0, 0, r62, 0);
        r42.addView(r5, LayoutHelper.createFrame(-1, 48, 51));
        r5.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda51(r1));
        r3.setView(r42);
        goto L35
    L33:
        r62 = AndroidUtilities.dp(16.0f);
        goto L34
    L29:
        r0 = AndroidUtilities.dp(8.0f);
        goto L31
    L24:
        if ((r6 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L27;
        r5.setText(LocaleController.getString(R.string.PassportDeleteDocumentPersonal), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
        goto L27
    L11:
        if (this.currentDocumentsType != null) goto L15;
        if ((this.currentType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L15;
        r3.setMessage(LocaleController.getString(R.string.PassportDeletePersonalAlert));
        goto L17
    L5:
        if (this.currentDocumentsType != null) goto L9;
        if ((this.currentType.type instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L9;
        r3.setMessage(LocaleController.getString(R.string.PassportDeleteAddressAlert));
        goto L17
    }

    private void createEmailInterface(Context r13) {
        this.actionBar.setTitle(LocaleController.getString(R.string.PassportEmail));
        if (TextUtils.isEmpty(this.currentEmail) == true) goto L5;
        TextSettingsCell r0 = new TextSettingsCell(r13);
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        r0.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        r0.setText(LocaleController.formatString("PassportPhoneUseSame", R.string.PassportPhoneUseSame, new Object[]{this.currentEmail}), false);
        this.linearLayout2.addView(r0, LayoutHelper.createLinear(-1, -2));
        r0.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda1(this));
        TextInfoPrivacyCell r02 = new TextInfoPrivacyCell(r13);
        this.bottomCell = r02;
        r02.setBackgroundDrawable(Theme.getThemedDrawableByKey(r13, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportPhoneUseSameEmailInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
    L5:
        this.inputFields = new EditTextBoldCursor[1];
        FrameLayout r03 = new FrameLayout(r13);
        this.linearLayout2.addView(r03, LayoutHelper.createLinear(-1, 50));
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.inputFields[0] = new EditTextBoldCursor(r13);
        this.inputFields[0].setTag(0);
        this.inputFields[0].setTextSize(1, 16.0f);
        this.inputFields[0].setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r2 = this.inputFields[0];
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTextColor(Theme.getColor(r5));
        this.inputFields[0].setBackgroundDrawable(null);
        this.inputFields[0].setCursorColor(Theme.getColor(r5));
        this.inputFields[0].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[0].setCursorWidth(1.5f);
        this.inputFields[0].setInputType(33);
        this.inputFields[0].setImeOptions(268435462);
        this.inputFields[0].setHint(LocaleController.getString(R.string.PaymentShippingEmailPlaceholder));
        TLRPC.TL_secureValue r22 = this.currentTypeValue;
        if (r22 == null) goto L12;
        TLRPC.SecurePlainData r23 = r22.plain_data;
        if ((r23 instanceof TLRPC.TL_securePlainEmail) == false) goto L12;
        TLRPC.TL_securePlainEmail r24 = (TLRPC.TL_securePlainEmail) r23;
        if (TextUtils.isEmpty(r24.email) == true) goto L12;
        this.inputFields[0].setText(r24.email);
    L12:
        EditTextBoldCursor r25 = this.inputFields[0];
        r25.setSelection(r25.length());
        this.inputFields[0].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r26 = this.inputFields[0];
        if (LocaleController.isRTL == false) goto L15;
        int r52 = 5;
    L16:
        r26.setGravity(r52);
        r03.addView(this.inputFields[0], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[0].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda2(this));
        TextInfoPrivacyCell r04 = new TextInfoPrivacyCell(r13);
        this.bottomCell = r04;
        r04.setBackgroundDrawable(Theme.getThemedDrawableByKey(r13, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportEmailUploadInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        return;
    L15:
        r52 = 3;
        goto L16
    }

    private void createEmailVerificationInterface(Context r14) {
        this.actionBar.setTitle(LocaleController.getString(R.string.PassportEmail));
        this.inputFields = new EditTextBoldCursor[1];
        FrameLayout r1 = new FrameLayout(r14);
        this.linearLayout2.addView(r1, LayoutHelper.createLinear(-1, 50));
        r1.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.inputFields[0] = new EditTextBoldCursor(r14);
        this.inputFields[0].setTag(0);
        this.inputFields[0].setTextSize(1, 16.0f);
        this.inputFields[0].setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r0 = this.inputFields[0];
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTextColor(Theme.getColor(r2));
        this.inputFields[0].setBackgroundDrawable(null);
        this.inputFields[0].setCursorColor(Theme.getColor(r2));
        this.inputFields[0].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[0].setCursorWidth(1.5f);
        int r22 = 3;
        this.inputFields[0].setInputType(3);
        this.inputFields[0].setImeOptions(268435462);
        this.inputFields[0].setHint(LocaleController.getString(R.string.PassportEmailCode));
        EditTextBoldCursor r02 = this.inputFields[0];
        r02.setSelection(r02.length());
        this.inputFields[0].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r03 = this.inputFields[0];
        if (LocaleController.isRTL == false) goto L5;
        r22 = 5;
    L5:
        r03.setGravity(r22);
        r1.addView(this.inputFields[0], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[0].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda37(this));
        this.inputFields[0].addTextChangedListener(new AnonymousClass6(this));
        TextInfoPrivacyCell r04 = new TextInfoPrivacyCell(r14);
        this.bottomCell = r04;
        r04.setBackgroundDrawable(Theme.getThemedDrawableByKey(r14, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(LocaleController.formatString("PassportEmailVerifyInfo", R.string.PassportEmailVerifyInfo, new Object[]{this.currentValues.get("email")}));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
    }

    private void createIdentityInterface(Context r29) {
        this.languageMap = new HashMap();
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r29.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L7
    L4:
        String r5 = r0.readLine();     // Catch: Exception -> L7
        if (r5 == null) goto L9;
        String[] r52 = r5.split(";");     // Catch: Exception -> L7
        this.languageMap.put(r52[1], r52[2]);     // Catch: Exception -> L7
        goto L4
    L9:
        r0.close();     // Catch: Exception -> L7
    L12:
        TextInfoPrivacyCell r02 = new TextInfoPrivacyCell(r29);
        this.topErrorCell = r02;
        int r53 = R.drawable.greydivider_top;
        int r6 = Theme.key_windowBackgroundGrayShadow;
        r02.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, r53, r6));
        boolean r7 = false;
        this.topErrorCell.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.linearLayout2.addView(this.topErrorCell, LayoutHelper.createLinear(-1, -2));
        checkTopErrorCell(true);
        long r10 = 0;
        if (this.currentDocumentsType == null) goto L45;
        HeaderCell r03 = new HeaderCell(r29);
        this.headerCell = r03;
        if (this.documentOnly == false) goto L17;
        r03.setText(LocaleController.getString(R.string.PassportDocuments));
    L18:
        HeaderCell r04 = this.headerCell;
        int r54 = Theme.key_windowBackgroundWhite;
        r04.setBackgroundColor(Theme.getColor(r54));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        LinearLayout r05 = new LinearLayout(r29);
        this.frontLayout = r05;
        r05.setOrientation(1);
        this.linearLayout2.addView(this.frontLayout, LayoutHelper.createLinear(-1, -2));
        TextDetailSettingsCell r06 = new TextDetailSettingsCell(r29);
        this.uploadFrontCell = r06;
        r06.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.linearLayout2.addView(this.uploadFrontCell, LayoutHelper.createLinear(-1, -2));
        this.uploadFrontCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda14(this));
        LinearLayout r07 = new LinearLayout(r29);
        this.reverseLayout = r07;
        r07.setOrientation(1);
        this.linearLayout2.addView(this.reverseLayout, LayoutHelper.createLinear(-1, -2));
        boolean r08 = this.currentDocumentsType.selfie_required;
        TextDetailSettingsCell r12 = new TextDetailSettingsCell(r29);
        this.uploadReverseCell = r12;
        r12.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.uploadReverseCell.setTextAndValue(LocaleController.getString(R.string.PassportReverseSide), LocaleController.getString(R.string.PassportReverseSideInfo), r08);
        this.linearLayout2.addView(this.uploadReverseCell, LayoutHelper.createLinear(-1, -2));
        this.uploadReverseCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda16(this));
        if (this.currentDocumentsType.selfie_required == false) goto L21;
        LinearLayout r09 = new LinearLayout(r29);
        this.selfieLayout = r09;
        r09.setOrientation(1);
        this.linearLayout2.addView(this.selfieLayout, LayoutHelper.createLinear(-1, -2));
        TextDetailSettingsCell r010 = new TextDetailSettingsCell(r29);
        this.uploadSelfieCell = r010;
        r010.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.uploadSelfieCell.setTextAndValue(LocaleController.getString(R.string.PassportSelfie), LocaleController.getString(R.string.PassportSelfieInfo), this.currentType.translation_required);
        this.linearLayout2.addView(this.uploadSelfieCell, LayoutHelper.createLinear(-1, -2));
        this.uploadSelfieCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda17(this));
    L21:
        TextInfoPrivacyCell r011 = new TextInfoPrivacyCell(r29);
        this.bottomCell = r011;
        r011.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, R.drawable.greydivider, r6));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportPersonalUploadInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        if (this.currentDocumentsType.translation_required == false) goto L46;
        HeaderCell r012 = new HeaderCell(r29);
        this.headerCell = r012;
        r012.setText(LocaleController.getString(R.string.PassportTranslation));
        this.headerCell.setBackgroundColor(Theme.getColor(r54));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        LinearLayout r013 = new LinearLayout(r29);
        this.translationLayout = r013;
        r013.setOrientation(1);
        this.linearLayout2.addView(this.translationLayout, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r014 = new TextSettingsCell(r29);
        this.uploadTranslationCell = r014;
        r014.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.linearLayout2.addView(this.uploadTranslationCell, LayoutHelper.createLinear(-1, -2));
        this.uploadTranslationCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda18(this));
        TextInfoPrivacyCell r015 = new TextInfoPrivacyCell(r29);
        this.bottomCellTranslation = r015;
        r015.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, R.drawable.greydivider, r6));
        if (this.currentBotId == 0) goto L26;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddTranslationUploadInfo);
    L39:
        CharSequence r016 = this.noAllTranslationErrorText;
        HashMap<String, String> r62 = this.documentsErrors;
        CharSequence r017 = r016;
        if (r62 == null) goto L44;
        String r63 = r62.get("translation_all");
        r017 = r016;
        if (r63 == null) goto L44;
        SpannableStringBuilder r018 = new SpannableStringBuilder(r63);
        r018.append("\n\n");
        r018.append(this.noAllTranslationErrorText);
        r018.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_text_RedRegular)), 0, r63.length(), 33);
        this.errorsValues.put("translation_all", _UrlKt.FRAGMENT_ENCODE_SET);
        r017 = r018;
    L44:
        this.bottomCellTranslation.setText(r017);
        this.linearLayout2.addView(this.bottomCellTranslation, LayoutHelper.createLinear(-1, -2));
        goto L46
    L26:
        TLRPC.SecureValueType r019 = this.currentDocumentsType.type;
        if ((r019 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L30;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddPassportInfo);
        goto L39
    L30:
        if ((r019 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L33;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddInternalPassportInfo);
        goto L39
    L33:
        if ((r019 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L36;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddIdentityCardInfo);
        goto L39
    L36:
        if ((r019 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L38;
        this.noAllTranslationErrorText = LocaleController.getString(R.string.PassportAddDriverLicenceInfo);
        goto L39
    L38:
        this.noAllTranslationErrorText = _UrlKt.FRAGMENT_ENCODE_SET;
    L46:
        HeaderCell r020 = new HeaderCell(r29);
        this.headerCell = r020;
        if (this.documentOnly == false) goto L49;
        r020.setText(LocaleController.getString(R.string.PassportDocument));
    L50:
        this.headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        if (this.currentDocumentsType == null) goto L53;
        int r021 = 9;
    L54:
        this.inputFields = new EditTextBoldCursor[r021];
        int r64 = 0;
    L55:
        long r16 = r10;
        int r3 = 6;
        if (r64 >= r021) goto L119;
        EditTextBoldCursor r13 = new EditTextBoldCursor(r29);
        this.inputFields[r64] = r13;
        AnonymousClass15 r122 = new AnonymousClass15(this, r29, r13);
        r122.setWillNotDraw(r7);
        this.linearLayout2.addView(r122, LayoutHelper.createLinear(-1, 64));
        int r72 = Theme.key_windowBackgroundWhite;
        r122.setBackgroundColor(Theme.getColor(r72));
        if (r64 != (r021 - 1)) goto L61;
        View r15 = new View(r29);
        this.extraBackgroundView = r15;
        r15.setBackgroundColor(Theme.getColor(r72));
        this.linearLayout2.addView(this.extraBackgroundView, LayoutHelper.createLinear(-1, 6));
    L61:
        if (this.documentOnly == true) goto L63;
    L68:
        this.inputFields[r64].setTag(Integer.valueOf(r64));
        this.inputFields[r64].setSupportRtlHint(true);
        this.inputFields[r64].setTextSize(1, 16.0f);
        this.inputFields[r64].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r73 = this.inputFields[r64];
        int r11 = Theme.key_windowBackgroundWhiteBlackText;
        r73.setTextColor(Theme.getColor(r11));
        this.inputFields[r64].setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
        this.inputFields[r64].setTransformHintToHeader(true);
        this.inputFields[r64].setBackgroundDrawable(null);
        this.inputFields[r64].setCursorColor(Theme.getColor(r11));
        this.inputFields[r64].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r64].setCursorWidth(1.5f);
        this.inputFields[r64].setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated), Theme.getColor(Theme.key_text_RedRegular));
        if (r64 == 5) goto L81;
        if (r64 == 6) goto L81;
        if (r64 == 3) goto L80;
        if (r64 == 8) goto L80;
        EditTextBoldCursor[] r32 = this.inputFields;
        if (r64 != 4) goto L79;
        r32[r64].setOnTouchListener(new PassportActivity$$ExternalSyntheticLambda22(this));
        this.inputFields[r64].setInputType(0);
        this.inputFields[r64].setFocusable(false);
    L82:
        switch(r64) {
            case 0: goto L100;
            case 1: goto L95;
            case 2: goto L90;
            case 3: goto L89;
            case 4: goto L88;
            case 5: goto L87;
            case 6: goto L86;
            case 7: goto L85;
            case 8: goto L84;
            default: goto L118;
        };
    L84:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportExpired));
        HashMap<String, String> r33 = this.currentDocumentValues;
        String r9 = "expiry_date";
    L105:
        setFieldValues(r33, this.inputFields[r64], r9);
        EditTextBoldCursor r102 = this.inputFields[r64];
        r102.setSelection(r102.length());
        if (r64 != 0) goto L108;
    L112:
        this.inputFields[r64].addTextChangedListener(new AnonymousClass16(this, r13, r9));
    L113:
        this.inputFields[r64].setPadding(0, 0, 0, 0);
        EditTextBoldCursor r34 = this.inputFields[r64];
        if (LocaleController.isRTL == false) goto L116;
        int r92 = 5;
    L117:
        r34.setGravity(r92 | 16);
        r122.addView(this.inputFields[r64], LayoutHelper.createFrame(-1, -1.0f, 51, 21.0f, 0.0f, 21.0f, 0.0f));
        this.inputFields[r64].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda23(this));
        goto L118
    L116:
        r92 = 3;
        goto L117
    L108:
        if (r64 == 2) goto L112;
        if (r64 == 1) goto L112;
        this.inputFields[r64].addTextChangedListener(new AnonymousClass17(this, r13, r9, r33));
        goto L113
    L85:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportDocumentNumber));
        r33 = this.currentDocumentValues;
        r9 = "document_no";
        goto L105
    L86:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportResidence));
        r33 = this.currentValues;
        r9 = "residence_country_code";
        goto L105
    L87:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportCitizenship));
        r33 = this.currentValues;
        r9 = "country_code";
        goto L105
    L88:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportGender));
        r33 = this.currentValues;
        r9 = "gender";
        goto L105
    L89:
        this.inputFields[r64].setHintText(LocaleController.getString(R.string.PassportBirthdate));
        r33 = this.currentValues;
        r9 = "birth_date";
        goto L105
    L90:
        boolean r35 = this.currentType.native_names;
        EditTextBoldCursor[] r93 = this.inputFields;
        if (r35 == false) goto L93;
        r93[r64].setHintText(LocaleController.getString(R.string.PassportSurnameLatin));
    L94:
        r33 = this.currentValues;
        r9 = "last_name";
        goto L105
    L93:
        r93[r64].setHintText(LocaleController.getString(R.string.PassportSurname));
        goto L94
    L95:
        boolean r36 = this.currentType.native_names;
        EditTextBoldCursor[] r94 = this.inputFields;
        if (r36 == false) goto L98;
        r94[r64].setHintText(LocaleController.getString(R.string.PassportMidnameLatin));
    L99:
        r33 = this.currentValues;
        r9 = "middle_name";
        goto L105
    L98:
        r94[r64].setHintText(LocaleController.getString(R.string.PassportMidname));
        goto L99
    L100:
        boolean r37 = this.currentType.native_names;
        EditTextBoldCursor[] r95 = this.inputFields;
        if (r37 == false) goto L103;
        r95[r64].setHintText(LocaleController.getString(R.string.PassportNameLatin));
    L104:
        r33 = this.currentValues;
        r9 = "first_name";
        goto L105
    L103:
        r95[r64].setHintText(LocaleController.getString(R.string.PassportName));
    L118:
        r64 = r64 + 1;
        r10 = r16;
        r7 = false;
        goto L55
    L79:
        r32[r64].setInputType(16385);
        this.inputFields[r64].setImeOptions(268435461);
    L80:
        this.inputFields[r64].setOnTouchListener(new PassportActivity$$ExternalSyntheticLambda21(this, r29));
        this.inputFields[r64].setInputType(0);
        this.inputFields[r64].setFocusable(false);
    L81:
        this.inputFields[r64].setOnTouchListener(new PassportActivity$$ExternalSyntheticLambda20(this));
        this.inputFields[r64].setInputType(0);
        goto L82
    L63:
        if (this.currentDocumentsType == null) goto L68;
        if (r64 >= 7) goto L68;
        r122.setVisibility(8);
        View r74 = this.extraBackgroundView;
        if (r74 == null) goto L68;
        r74.setVisibility(8);
        goto L68
    L119:
        ShadowSectionCell r022 = new ShadowSectionCell(r29);
        this.sectionCell2 = r022;
        this.linearLayout2.addView(r022, LayoutHelper.createLinear(-1, -2));
        HeaderCell r023 = new HeaderCell(r29);
        this.headerCell = r023;
        r023.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        int r024 = 3;
        this.inputExtraFields = new EditTextBoldCursor[3];
        int r55 = 0;
    L120:
        if (r55 >= r024) goto L145;
        EditTextBoldCursor r025 = new EditTextBoldCursor(r29);
        this.inputExtraFields[r55] = r025;
        AnonymousClass18 r65 = new AnonymousClass18(this, r29, r025);
        r65.setWillNotDraw(false);
        this.linearLayout2.addView(r65, LayoutHelper.createLinear(-1, 64));
        int r96 = Theme.key_windowBackgroundWhite;
        r65.setBackgroundColor(Theme.getColor(r96));
        if (r55 != 2) goto L124;
        View r123 = new View(r29);
        this.extraBackgroundView2 = r123;
        r123.setBackgroundColor(Theme.getColor(r96));
        this.linearLayout2.addView(this.extraBackgroundView2, LayoutHelper.createLinear(-1, r3));
    L124:
        this.inputExtraFields[r55].setTag(Integer.valueOf(r55));
        this.inputExtraFields[r55].setSupportRtlHint(true);
        this.inputExtraFields[r55].setTextSize(1, 16.0f);
        this.inputExtraFields[r55].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r97 = this.inputExtraFields[r55];
        int r124 = Theme.key_windowBackgroundWhiteBlackText;
        r97.setTextColor(Theme.getColor(r124));
        this.inputExtraFields[r55].setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
        this.inputExtraFields[r55].setTransformHintToHeader(true);
        this.inputExtraFields[r55].setBackgroundDrawable(null);
        this.inputExtraFields[r55].setCursorColor(Theme.getColor(r124));
        this.inputExtraFields[r55].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputExtraFields[r55].setCursorWidth(1.5f);
        this.inputExtraFields[r55].setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated), Theme.getColor(Theme.key_text_RedRegular));
        this.inputExtraFields[r55].setInputType(16385);
        this.inputExtraFields[r55].setImeOptions(268435461);
        if (r55 == 0) goto L132;
        if (r55 != 1) goto L128;
        HashMap<String, String> r38 = this.currentValues;
        String r132 = "middle_name_native";
    L133:
        setFieldValues(r38, this.inputExtraFields[r55], r132);
        EditTextBoldCursor r39 = this.inputExtraFields[r55];
        r39.setSelection(r39.length());
        if (r55 != 0) goto L136;
    L138:
        this.inputExtraFields[r55].addTextChangedListener(new AnonymousClass19(this, r025, r132));
    L139:
        this.inputExtraFields[r55].setPadding(0, 0, 0, 0);
        EditTextBoldCursor r026 = this.inputExtraFields[r55];
        if (LocaleController.isRTL == false) goto L142;
        int r310 = 5;
    L143:
        r026.setGravity(r310 | 16);
        r65.addView(this.inputExtraFields[r55], LayoutHelper.createFrame(-1, -1.0f, 51, 21.0f, 0.0f, 21.0f, 0.0f));
        this.inputExtraFields[r55].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda24(this));
    L144:
        r55 = r55 + 1;
        r024 = 3;
        r3 = 6;
        goto L120
    L142:
        r310 = 3;
        goto L143
    L136:
        if (r55 == 2) goto L138;
        if (r55 != 1) goto L139;
    L128:
        if (r55 != 2) goto L144;
        r38 = this.currentValues;
        r132 = "last_name_native";
        goto L133
    L132:
        r38 = this.currentValues;
        r132 = "first_name_native";
        goto L133
    L145:
        TextInfoPrivacyCell r027 = new TextInfoPrivacyCell(r29);
        this.nativeInfoCell = r027;
        this.linearLayout2.addView(r027, LayoutHelper.createLinear(-1, -2));
        if (this.currentBotId != r16) goto L150;
        if (this.currentDocumentsType == null) goto L150;
    L154:
        if (this.currentDocumentsTypeValue == null) goto L172;
    L155:
        TLRPC.TL_secureValue r028 = this.currentDocumentsTypeValue;
        if (r028 == null) goto L167;
        addDocumentViews(r028.files);
        TLRPC.SecureFile r029 = this.currentDocumentsTypeValue.front_side;
        if ((r029 instanceof TLRPC.TL_secureFile) == false) goto L160;
        addDocumentViewInternal((TLRPC.TL_secureFile) r029, 2);
    L160:
        TLRPC.SecureFile r030 = this.currentDocumentsTypeValue.reverse_side;
        if ((r030 instanceof TLRPC.TL_secureFile) == false) goto L163;
        addDocumentViewInternal((TLRPC.TL_secureFile) r030, 3);
    L163:
        TLRPC.SecureFile r031 = this.currentDocumentsTypeValue.selfie;
        if ((r031 instanceof TLRPC.TL_secureFile) == false) goto L166;
        addDocumentViewInternal((TLRPC.TL_secureFile) r031, 1);
    L166:
        addTranslationDocumentViews(this.currentDocumentsTypeValue.translation);
    L167:
        TextSettingsCell r032 = new TextSettingsCell(r29);
        r032.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        r032.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        if (this.currentDocumentsType != null) goto L170;
        r032.setText(LocaleController.getString(R.string.PassportDeleteInfo), false);
    L171:
        this.linearLayout2.addView(r032, LayoutHelper.createLinear(-1, -2));
        r032.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda15(this));
        TextInfoPrivacyCell r033 = this.nativeInfoCell;
        int r311 = R.drawable.greydivider;
        int r4 = Theme.key_windowBackgroundGrayShadow;
        r033.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, r311, r4));
        ShadowSectionCell r034 = new ShadowSectionCell(r29);
        this.sectionCell = r034;
        r034.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, R.drawable.greydivider_bottom, r4));
        this.linearLayout2.addView(this.sectionCell, LayoutHelper.createLinear(-1, -2));
    L173:
        updateInterfaceStringsForDocumentType();
        checkNativeFields(false);
        return;
    L170:
        r032.setText(LocaleController.getString(R.string.PassportDeleteDocument), false);
        goto L171
    L172:
        this.nativeInfoCell.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
    L150:
        if (this.currentTypeValue == null) goto L154;
        if (this.documentOnly == false) goto L155;
    L53:
        r021 = 7;
        goto L54
    L49:
        r020.setText(LocaleController.getString(R.string.PassportPersonal));
        goto L50
    L17:
        r03.setText(LocaleController.getString(R.string.PassportRequiredDocuments));
        goto L18
    L45:
        TextSettingsCell r035 = new TextSettingsCell(r29);
        this.scanDocumentCell = r035;
        r035.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.scanDocumentCell.setText(LocaleController.getString(R.string.PassportScanPassport), false);
        this.linearLayout2.addView(this.scanDocumentCell, LayoutHelper.createLinear(-1, -2));
        this.scanDocumentCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda19(this));
        TextInfoPrivacyCell r036 = new TextInfoPrivacyCell(r29);
        this.bottomCell = r036;
        r036.setBackgroundDrawable(Theme.getThemedDrawableByKey(r29, R.drawable.greydivider, r6));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportScanPassportInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    private void createManageInterface(Context r15) {
        FrameLayout r0 = (FrameLayout) this.fragmentView;
        this.actionBar.setTitle(LocaleController.getString(R.string.TelegramPassport));
        this.actionBar.createMenu().addItem(1, R.drawable.msg_info);
        HeaderCell r02 = new HeaderCell(r15);
        this.headerCell = r02;
        r02.setText(LocaleController.getString(R.string.PassportProvidedInformation));
        this.headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        ShadowSectionCell r03 = new ShadowSectionCell(r15);
        this.sectionCell = r03;
        int r1 = R.drawable.greydivider;
        int r5 = Theme.key_windowBackgroundGrayShadow;
        r03.setBackgroundDrawable(Theme.getThemedDrawableByKey(r15, r1, r5));
        this.linearLayout2.addView(this.sectionCell, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r04 = new TextSettingsCell(r15);
        this.addDocumentCell = r04;
        r04.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.addDocumentCell.setText(LocaleController.getString(R.string.PassportNoDocumentsAdd), true);
        this.linearLayout2.addView(this.addDocumentCell, LayoutHelper.createLinear(-1, -2));
        this.addDocumentCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda3(this));
        TextSettingsCell r05 = new TextSettingsCell(r15);
        this.deletePassportCell = r05;
        r05.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.deletePassportCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.deletePassportCell.setText(LocaleController.getString(R.string.TelegramPassportDelete), false);
        this.linearLayout2.addView(this.deletePassportCell, LayoutHelper.createLinear(-1, -2));
        this.deletePassportCell.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda4(this));
        ShadowSectionCell r06 = new ShadowSectionCell(r15);
        this.addDocumentSectionCell = r06;
        r06.setBackgroundDrawable(Theme.getThemedDrawableByKey(r15, R.drawable.greydivider_bottom, r5));
        this.linearLayout2.addView(this.addDocumentSectionCell, LayoutHelper.createLinear(-1, -2));
        LinearLayout r07 = new LinearLayout(r15);
        this.emptyLayout = r07;
        r07.setOrientation(1);
        this.emptyLayout.setGravity(17);
        this.emptyLayout.setBackgroundDrawable(Theme.getThemedDrawableByKey(r15, R.drawable.greydivider_bottom, r5));
        boolean r08 = AndroidUtilities.isTablet();
        LinearLayout r52 = this.linearLayout2;
        if (r08 == false) goto L5;
        r52.addView(this.emptyLayout, new LinearLayout.LayoutParams(-1, AndroidUtilities.dp(528.0f) - ActionBar.getCurrentActionBarHeight()));
    L6:
        ImageView r09 = new ImageView(r15);
        this.emptyImageView = r09;
        r09.setImageResource(R.drawable.no_passport);
        this.emptyImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_sessions_devicesImage), PorterDuff.Mode.MULTIPLY));
        this.emptyLayout.addView(this.emptyImageView, LayoutHelper.createLinear(-2, -2));
        TextView r010 = new TextView(r15);
        this.emptyTextView1 = r010;
        int r3 = Theme.key_windowBackgroundWhiteGrayText2;
        r010.setTextColor(Theme.getColor(r3));
        this.emptyTextView1.setGravity(17);
        this.emptyTextView1.setTextSize(1, 15.0f);
        this.emptyTextView1.setTypeface(AndroidUtilities.bold());
        this.emptyTextView1.setText(LocaleController.getString(R.string.PassportNoDocuments));
        this.emptyLayout.addView(this.emptyTextView1, LayoutHelper.createLinear(-2, -2, 17, 0, 16, 0, 0));
        TextView r011 = new TextView(r15);
        this.emptyTextView2 = r011;
        r011.setTextColor(Theme.getColor(r3));
        this.emptyTextView2.setGravity(17);
        this.emptyTextView2.setTextSize(1, 14.0f);
        this.emptyTextView2.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.emptyTextView2.setText(LocaleController.getString(R.string.PassportNoDocumentsInfo));
        this.emptyLayout.addView(this.emptyTextView2, LayoutHelper.createLinear(-2, -2, 17, 0, 14, 0, 0));
        TextView r012 = new TextView(r15);
        this.emptyTextView3 = r012;
        int r32 = Theme.key_windowBackgroundWhiteBlueText4;
        r012.setTextColor(Theme.getColor(r32));
        this.emptyTextView3.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
        this.emptyTextView3.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(Theme.getColor(r32), 32), 7));
        this.emptyTextView3.setGravity(17);
        this.emptyTextView3.setTextSize(1, 15.0f);
        this.emptyTextView3.setTypeface(AndroidUtilities.bold());
        this.emptyTextView3.setGravity(17);
        this.emptyTextView3.setText(LocaleController.getString(R.string.PassportNoDocumentsAdd).toUpperCase());
        this.emptyLayout.addView(this.emptyTextView3, LayoutHelper.createLinear(-2, 30, 17, 0, 12, 0, 0));
        this.emptyTextView3.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda5(this));
        int r013 = this.currentForm.values.size();
        int r12 = 0;
    L7:
        if (r12 >= r013) goto L22;
        TLRPC.TL_secureValue r33 = this.currentForm.values.get(r12);
        if (this.isPersonalDocument(r33.type) == false) goto L13;
        ArrayList<TLRPC.TL_secureRequiredType> r4 = new ArrayList();
        TLRPC.TL_secureRequiredType r53 = new TLRPC.TL_secureRequiredType();
        r53.type = r33.type;
        r53.selfie_required = true;
        r53.translation_required = true;
        r4.add(r53);
        TLRPC.TL_secureRequiredType r34 = new TLRPC.TL_secureRequiredType();
        r34.type = new TLRPC.TL_secureValueTypePersonalDetails();
    L11:
        boolean r11 = true;
        TLRPC.TL_secureRequiredType r9 = r34;
        ArrayList<TLRPC.TL_secureRequiredType> r10 = r4;
    L17:
        if (r12 != (r013 - 1)) goto L20;
        boolean r122 = true;
    L19:
        PassportActivity r7 = this;
        Context r8 = r15;
        r7.addField(r8, r9, r10, r11, r122);
        r12 = r12 + 1;
        PassportActivity this = r7;
        r15 = r8;
        goto L7
    L20:
        r122 = false;
        goto L19
    L13:
        if (this.isAddressDocument(r33.type) == false) goto L15;
        r4 = new ArrayList();
        TLRPC.TL_secureRequiredType r54 = new TLRPC.TL_secureRequiredType();
        r54.type = r33.type;
        r54.translation_required = true;
        r4.add(r54);
        r34 = new TLRPC.TL_secureRequiredType();
        r34.type = new TLRPC.TL_secureValueTypeAddress();
        goto L11
    L15:
        TLRPC.TL_secureRequiredType r42 = new TLRPC.TL_secureRequiredType();
        r42.type = r33.type;
        r10 = null;
        r9 = r42;
        r11 = false;
        goto L17
    L22:
        this.updateManageVisibility();
        return;
    L5:
        r52.addView(this.emptyLayout, new LinearLayout.LayoutParams(-1, AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()));
        goto L6
    }

    private void createPasswordInterface(Context r21) {
        if (this.currentForm == null) goto L12;
        int r2 = 0;
    L6:
        if (r2 >= this.currentForm.users.size()) goto L11;
        TLRPC.User r5 = this.currentForm.users.get(r2);
        if (r5.id == this.currentBotId) goto L13;
        r2 = r2 + 1;
    L13:
        FrameLayout r22 = (FrameLayout) this.fragmentView;
        this.actionBar.setTitle(LocaleController.getString(R.string.TelegramPassport));
        EmptyTextProgressView r6 = new EmptyTextProgressView(r21);
        this.emptyView = r6;
        r6.showProgress();
        r22.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r23 = new FrameLayout(r21);
        this.passwordAvatarContainer = r23;
        this.linearLayout2.addView(r23, LayoutHelper.createLinear(-1, 100));
        BackupImageView r24 = new BackupImageView(r21);
        r24.setRoundRadius(ExteraConfig.getAvatarCorners(64.0f));
        this.passwordAvatarContainer.addView(r24, LayoutHelper.createFrame(64, 64.0f, 17, 0.0f, 8.0f, 0.0f, 0.0f));
        r24.setForUserOrChat(r5, new AvatarDrawable(r5));
        TextInfoPrivacyCell r25 = new TextInfoPrivacyCell(r21);
        this.passwordRequestTextView = r25;
        r25.getTextView().setGravity(1);
        long r9 = this.currentBotId;
        TextInfoPrivacyCell r7 = this.passwordRequestTextView;
        if (r9 != 0) goto L16;
        r7.setText(LocaleController.getString(R.string.PassportSelfRequest));
    L17:
        ((FrameLayout.LayoutParams) this.passwordRequestTextView.getTextView().getLayoutParams()).gravity = 1;
        LinearLayout r26 = this.linearLayout2;
        TextInfoPrivacyCell r52 = this.passwordRequestTextView;
        int r92 = 3;
        if (LocaleController.isRTL == false) goto L20;
        int r72 = 5;
    L21:
        r26.addView(r52, LayoutHelper.createFrame(-2, -2.0f, r72 | 48, 21.0f, 0.0f, 21.0f, 0.0f));
        ImageView r27 = new ImageView(r21);
        this.noPasswordImageView = r27;
        r27.setImageResource(R.drawable.no_password);
        this.noPasswordImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        this.linearLayout2.addView(this.noPasswordImageView, LayoutHelper.createLinear(-2, -2, 49, 0, 13, 0, 0));
        TextView r28 = new TextView(r21);
        this.noPasswordTextView = r28;
        r28.setTextSize(1, 14.0f);
        this.noPasswordTextView.setGravity(1);
        this.noPasswordTextView.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(17.0f));
        this.noPasswordTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        this.noPasswordTextView.setText(LocaleController.getString(R.string.TelegramPassportCreatePasswordInfo));
        LinearLayout r29 = this.linearLayout2;
        TextView r73 = this.noPasswordTextView;
        if (LocaleController.isRTL == false) goto L24;
        int r11 = 5;
    L25:
        r29.addView(r73, LayoutHelper.createFrame(-2, -2.0f, r11 | 48, 21.0f, 10.0f, 21.0f, 0.0f));
        TextView r210 = new TextView(r21);
        this.noPasswordSetTextView = r210;
        r210.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText5));
        this.noPasswordSetTextView.setGravity(17);
        this.noPasswordSetTextView.setTextSize(1, 16.0f);
        this.noPasswordSetTextView.setTypeface(AndroidUtilities.bold());
        this.noPasswordSetTextView.setText(LocaleController.getString(R.string.TelegramPassportCreatePassword));
        LinearLayout r211 = this.linearLayout2;
        TextView r112 = this.noPasswordSetTextView;
        if (LocaleController.isRTL == false) goto L28;
        int r12 = 5;
    L29:
        r211.addView(r112, LayoutHelper.createFrame(-1, 24.0f, r12 | 48, 21.0f, 9.0f, 21.0f, 0.0f));
        this.noPasswordSetTextView.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda33(this));
        this.inputFields = new EditTextBoldCursor[1];
        this.inputFieldContainers = new ViewGroup[]{new FrameLayout(r21)};
        this.linearLayout2.addView(this.inputFieldContainers[0], LayoutHelper.createLinear(-1, 50));
        this.inputFieldContainers[0].setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.inputFields[0] = new EditTextBoldCursor(r21);
        this.inputFields[0].setTag(0);
        this.inputFields[0].setTextSize(1, 16.0f);
        this.inputFields[0].setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r212 = this.inputFields[0];
        int r74 = Theme.key_windowBackgroundWhiteBlackText;
        r212.setTextColor(Theme.getColor(r74));
        this.inputFields[0].setBackgroundDrawable(null);
        this.inputFields[0].setCursorColor(Theme.getColor(r74));
        this.inputFields[0].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[0].setCursorWidth(1.5f);
        this.inputFields[0].setInputType(129);
        this.inputFields[0].setMaxLines(1);
        this.inputFields[0].setLines(1);
        this.inputFields[0].setSingleLine(true);
        this.inputFields[0].setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.inputFields[0].setTypeface(Typeface.DEFAULT);
        this.inputFields[0].setImeOptions(268435462);
        this.inputFields[0].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r213 = this.inputFields[0];
        if (LocaleController.isRTL == false) goto L32;
        int r4 = 5;
    L33:
        r213.setGravity(r4);
        this.inputFieldContainers[0].addView(this.inputFields[0], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[0].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda34(this));
        this.inputFields[0].setCustomSelectionActionModeCallback(new AnonymousClass7(this));
        TextInfoPrivacyCell r214 = new TextInfoPrivacyCell(r21);
        this.passwordInfoRequestTextView = r214;
        r214.setBackgroundDrawable(Theme.getThemedDrawableByKey(r21, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.passwordInfoRequestTextView.setText(LocaleController.formatString("PassportRequestPasswordInfo", R.string.PassportRequestPasswordInfo, new Object[0]));
        this.linearLayout2.addView(this.passwordInfoRequestTextView, LayoutHelper.createLinear(-1, -2));
        TextView r215 = new TextView(r21);
        this.passwordForgotButton = r215;
        r215.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        this.passwordForgotButton.setTextSize(1, 14.0f);
        this.passwordForgotButton.setText(LocaleController.getString(R.string.ForgotPassword));
        this.passwordForgotButton.setPadding(0, 0, 0, 0);
        LinearLayout r1 = this.linearLayout2;
        TextView r216 = this.passwordForgotButton;
        if (LocaleController.isRTL == false) goto L36;
        r92 = 5;
    L36:
        r1.addView(r216, LayoutHelper.createLinear(-2, 30, r92 | 48, 21, 0, 21, 0));
        this.passwordForgotButton.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda35(this));
        updatePasswordInterface();
        return;
    L32:
        r4 = 3;
        goto L33
    L28:
        r12 = 3;
        goto L29
    L24:
        r11 = 3;
        goto L25
    L20:
        r72 = 3;
        goto L21
    L16:
        r7.setText(AndroidUtilities.replaceTags(LocaleController.formatString("PassportRequest", R.string.PassportRequest, new Object[]{UserObject.getFirstName(r5)})));
        goto L17
    L11:
        r5 = null;
        goto L13
    L12:
        r5 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v3, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r12v5, types: [android.view.View, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r13v15, types: [android.view.ViewGroup, android.widget.LinearLayout] */
    private void createPhoneInterface(Context r23) {
        this.actionBar.setTitle(LocaleController.getString(R.string.PassportPhone));
        this.languageMap = new HashMap();
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r23.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L9
    L4:
        String r7 = r0.readLine();     // Catch: Exception -> L9
        if (r7 == null) goto L12;
        String[] r72 = r7.split(";");     // Catch: Exception -> L9
        this.countriesArray.add(0, r72[2]);     // Catch: Exception -> L9
        this.countriesMap.put(r72[2], r72[0]);     // Catch: Exception -> L9
        this.codesMap.put(r72[0], r72[2]);     // Catch: Exception -> L9
        if (r72.length <= 3) goto L11;
        this.phoneFormatMap.put(r72[0], r72[3]);     // Catch: Exception -> L9
    L11:
        this.languageMap.put(r72[1], r72[2]);     // Catch: Exception -> L9
        goto L4
    L12:
        r0.close();     // Catch: Exception -> L9
    L15:
        Collections.sort(this.countriesArray, new PassportActivity$$ExternalSyntheticLambda8());
        String r02 = UserConfig.getInstance(this.currentAccount).getCurrentUser().phone;
        TextSettingsCell r73 = new TextSettingsCell(r23);
        r73.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        r73.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        r73.setText(LocaleController.formatString("PassportPhoneUseSame", R.string.PassportPhoneUseSame, new Object[]{PhoneFormat.getInstance().format("+" + r02)}), false);
        this.linearLayout2.addView(r73, LayoutHelper.createLinear(-1, -2));
        r73.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda9(this));
        TextInfoPrivacyCell r03 = new TextInfoPrivacyCell(r23);
        this.bottomCell = r03;
        r03.setBackgroundDrawable(Theme.getThemedDrawableByKey(r23, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportPhoneUseSameInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        HeaderCell r04 = new HeaderCell(r23);
        this.headerCell = r04;
        r04.setText(LocaleController.getString(R.string.PassportPhoneUseOther));
        this.headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        this.inputFields = new EditTextBoldCursor[3];
        int r05 = 0;
    L16:
        String r74 = null;
        if (r05 >= 3) goto L66;
        EditTextBoldCursor[] r10 = this.inputFields;
        if (r05 != 2) goto L21;
        r10[r05] = new HintEditText(r23);
    L23:
        if (r05 != 1) goto L25;
        ?? r12 = new LinearLayout(r23);
        r12.setOrientation(0);
        this.linearLayout2.addView(r12, LayoutHelper.createLinear(-1, 50));
        r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        FrameLayout r122 = r12;
    L28:
        this.inputFields[r05].setTag(Integer.valueOf(r05));
        this.inputFields[r05].setTextSize(1, 16.0f);
        this.inputFields[r05].setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r102 = this.inputFields[r05];
        int r14 = Theme.key_windowBackgroundWhiteBlackText;
        r102.setTextColor(Theme.getColor(r14));
        this.inputFields[r05].setBackgroundDrawable(null);
        this.inputFields[r05].setCursorColor(Theme.getColor(r14));
        this.inputFields[r05].setCursorSize(AndroidUtilities.dp(20.0f));
        this.inputFields[r05].setCursorWidth(1.5f);
        EditTextBoldCursor[] r103 = this.inputFields;
        if (r05 != 0) goto L31;
        r103[r05].setOnTouchListener(new PassportActivity$$ExternalSyntheticLambda10(this));
        this.inputFields[r05].setText(LocaleController.getString(R.string.ChooseCountry));
        this.inputFields[r05].setInputType(0);
        this.inputFields[r05].setFocusable(false);
    L35:
        EditTextBoldCursor r104 = this.inputFields[r05];
        r104.setSelection(r104.length());
        int r15 = 5;
        if (r05 != 1) goto L38;
        TextView r75 = new TextView(r23);
        this.plusTextView = r75;
        r75.setText("+");
        this.plusTextView.setTextColor(Theme.getColor(r14));
        this.plusTextView.setTextSize(1, 16.0f);
        r122.addView(this.plusTextView, LayoutHelper.createLinear(-2, -2, 21.0f, 12.0f, 0.0f, 6.0f));
        this.inputFields[r05].setPadding(AndroidUtilities.dp(10.0f), 0, 0, 0);
        this.inputFields[r05].setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        this.inputFields[r05].setGravity(19);
        r122.addView(this.inputFields[r05], LayoutHelper.createLinear(55, -2, 0.0f, 12.0f, 16.0f, 6.0f));
        this.inputFields[r05].addTextChangedListener(new AnonymousClass9(this));
    L46:
        this.inputFields[r05].setOnEditorActionListener(new PassportActivity$$ExternalSyntheticLambda11(this));
        if (r05 != 2) goto L49;
        this.inputFields[r05].setOnKeyListener(new PassportActivity$$ExternalSyntheticLambda12(this));
    L49:
        if (r05 != 0) goto L51;
        View r76 = new View(r23);
        this.dividers.add(r76);
        r76.setBackgroundColor(Theme.getColor(Theme.key_divider));
        r122.addView(r76, new FrameLayout.LayoutParams(-1, 1, 83));
    L51:
        r05 = r05 + 1;
        goto L16
    L38:
        EditTextBoldCursor[] r13 = this.inputFields;
        if (r05 != 2) goto L41;
        r13[r05].setPadding(0, 0, 0, 0);
        this.inputFields[r05].setGravity(19);
        this.inputFields[r05].setHintText(null);
        this.inputFields[r05].setHint(LocaleController.getString(R.string.PaymentShippingPhoneNumber));
        r122.addView(this.inputFields[r05], LayoutHelper.createLinear(-1, -2, 0.0f, 12.0f, 21.0f, 6.0f));
        this.inputFields[r05].addTextChangedListener(new AnonymousClass10(this));
        goto L46
    L41:
        r13[r05].setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        EditTextBoldCursor r77 = this.inputFields[r05];
        if (LocaleController.isRTL == true) goto L45;
        r15 = 3;
    L45:
        r77.setGravity(r15);
        r122.addView(this.inputFields[r05], LayoutHelper.createFrame(-1, -2.0f, 51, 21.0f, 12.0f, 21.0f, 6.0f));
        goto L46
    L31:
        r103[r05].setInputType(3);
        EditTextBoldCursor[] r105 = this.inputFields;
        if (r05 != 2) goto L34;
        r105[r05].setImeOptions(268435462);
        goto L35
    L34:
        r105[r05].setImeOptions(268435461);
        goto L35
    L25:
        if (r05 != 2) goto L27;
        r122 = (ViewGroup) this.inputFields[1].getParent();
        goto L28
    L27:
        FrameLayout r123 = new FrameLayout(r23);
        this.linearLayout2.addView(r123, LayoutHelper.createLinear(-1, 50));
        r123.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r122 = r123;
        goto L28
    L21:
        r10[r05] = new EditTextBoldCursor(r23);
        goto L23
    L66:
        TelephonyManager r06 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L56
        if (r06 == null) goto L58;
        r74 = r06.getSimCountryIso().toUpperCase();     // Catch: Exception -> L56
    L58:
        if (r74 == null) goto L64;
        String r07 = this.languageMap.get(r74);
        if (r07 == null) goto L64;
        if (this.countriesArray.indexOf(r07) == (-1)) goto L64;
        this.inputFields[1].setText(this.countriesMap.get(r07));
    L64:
        TextInfoPrivacyCell r08 = new TextInfoPrivacyCell(r23);
        this.bottomCell = r08;
        r08.setBackgroundDrawable(Theme.getThemedDrawableByKey(r23, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(LocaleController.getString(R.string.PassportPhoneUploadInfo));
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        return;
    L56:
        e = move-exception;
        FileLog.e(e);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L15
    }

    private void createPhoneVerificationInterface(Context r15) {
        this.actionBar.setTitle(LocaleController.getString(R.string.PassportPhone));
        FrameLayout r0 = new FrameLayout(r15);
        this.scrollView.addView(r0, LayoutHelper.createScroll(-1, -2, 51));
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L14;
        this.views[r2] = new PhoneConfirmationView(this, r15, r2 + 2);
        this.views[r2].setVisibility(8);
        SlideView r3 = this.views[r2];
        if (AndroidUtilities.isTablet() == false) goto L8;
        float r10 = 26.0f;
    L10:
        if (AndroidUtilities.isTablet() == false) goto L12;
        float r12 = 26.0f;
    L13:
        r0.addView(r3, LayoutHelper.createFrame(-1, -1.0f, 51, r10, 30.0f, r12, 0.0f));
        r2 = r2 + 1;
        goto L4
    L12:
        r12 = 18.0f;
        goto L13
    L8:
        r10 = 18.0f;
        goto L10
    L14:
        Bundle r152 = new Bundle();
        r152.putString("phone", this.currentValues.get("phone"));
        fillNextCodeParams(r152, this.currentPhoneVerification, false);
    }

    private void createRequestInterface(Context r22) {
        Context r1 = r22;
        int r6 = 0;
        if (this.currentForm == null) goto L11;
        int r2 = 0;
    L6:
        if (r2 >= this.currentForm.users.size()) goto L11;
        TLRPC.User r3 = this.currentForm.users.get(r2);
        if (r3.id == this.currentBotId) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        TLRPC.User r8 = r3;
    L12:
        FrameLayout r9 = (FrameLayout) this.fragmentView;
        this.actionBar.setTitle(LocaleController.getString(R.string.TelegramPassport));
        boolean r10 = true;
        this.actionBar.createMenu().addItem(1, R.drawable.msg_info);
        if (r8 == null) goto L15;
        FrameLayout r23 = new FrameLayout(r1);
        this.linearLayout2.addView(r23, LayoutHelper.createLinear(-1, 100));
        BackupImageView r32 = new BackupImageView(r1);
        r32.setRoundRadius(ExteraConfig.getAvatarCorners(64.0f));
        r23.addView(r32, LayoutHelper.createFrame(64, 64.0f, 17, 0.0f, 8.0f, 0.0f, 0.0f));
        r32.setForUserOrChat(r8, new AvatarDrawable(r8));
        TextInfoPrivacyCell r24 = new TextInfoPrivacyCell(r1);
        this.bottomCell = r24;
        r24.setBackgroundDrawable(Theme.getThemedDrawableByKey(r1, R.drawable.greydivider_top, Theme.key_windowBackgroundGrayShadow));
        this.bottomCell.setText(AndroidUtilities.replaceTags(LocaleController.formatString("PassportRequest", R.string.PassportRequest, new Object[]{UserObject.getFirstName(r8)})));
        this.bottomCell.getTextView().setGravity(1);
        ((FrameLayout.LayoutParams) this.bottomCell.getTextView().getLayoutParams()).gravity = 1;
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
    L15:
        HeaderCell r25 = new HeaderCell(r1);
        this.headerCell = r25;
        r25.setText(LocaleController.getString(R.string.PassportRequestedInformation));
        this.headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout2.addView(this.headerCell, LayoutHelper.createLinear(-1, -2));
        TL_account.authorizationForm r26 = this.currentForm;
        if (r26 == null) goto L140;
        int r13 = r26.required_types.size();
        ArrayList<TLRPC.TL_secureRequiredType> r14 = new ArrayList();
        ArrayList<TLRPC.TL_secureRequiredType> r15 = new ArrayList();
        int r27 = 0;
        boolean r33 = false;
        int r4 = 0;
        boolean r5 = false;
        int r7 = 0;
    L18:
        if (r27 >= r13) goto L63;
        TLRPC.SecureRequiredType r11 = this.currentForm.required_types.get(r27);
        if ((r11 instanceof TLRPC.TL_secureRequiredType) == false) goto L34;
        TLRPC.TL_secureRequiredType r112 = (TLRPC.TL_secureRequiredType) r11;
        if (isPersonalDocument(r112.type) == false) goto L25;
        r14.add(r112);
    L50:
        r4 = r4 + 1;
    L61:
        r27 = r27 + 1;
        r6 = 0;
        r10 = true;
        goto L18
    L25:
        if (isAddressDocument(r112.type) == false) goto L27;
        r15.add(r112);
    L60:
        r7 = r7 + 1;
        goto L61
    L27:
        TLRPC.SecureValueType r113 = r112.type;
        if ((r113 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L31;
        r33 = r10;
        goto L61
    L31:
        if ((r113 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L61;
        r5 = r10;
        goto L61
    L34:
        if ((r11 instanceof TLRPC.TL_secureRequiredTypeOneOf) == false) goto L61;
        TLRPC.TL_secureRequiredTypeOneOf r114 = (TLRPC.TL_secureRequiredTypeOneOf) r11;
        if (r114.types.isEmpty() == true) goto L61;
        TLRPC.SecureRequiredType r12 = r114.types.get(r6);
        if ((r12 instanceof TLRPC.TL_secureRequiredType) == false) goto L61;
        TLRPC.TL_secureRequiredType r122 = (TLRPC.TL_secureRequiredType) r12;
        if (isPersonalDocument(r122.type) == false) goto L52;
        int r62 = r114.types.size();
        int r123 = 0;
    L44:
        if (r123 >= r62) goto L50;
        TLRPC.SecureRequiredType r102 = r114.types.get(r123);
        if ((r102 instanceof TLRPC.TL_secureRequiredType) == false) goto L49;
        r14.add((TLRPC.TL_secureRequiredType) r102);
    L49:
        r123 = r123 + 1;
        goto L44
    L52:
        if (isAddressDocument(r122.type) == false) goto L61;
        int r16 = r114.types.size();
        int r63 = 0;
    L54:
        if (r63 >= r16) goto L60;
        TLRPC.SecureRequiredType r103 = r114.types.get(r63);
        if ((r103 instanceof TLRPC.TL_secureRequiredType) == false) goto L59;
        r15.add((TLRPC.TL_secureRequiredType) r103);
    L59:
        r63 = r63 + 1;
        goto L54
    L63:
        if (r33 == false) goto L67;
        if (r4 > 1) goto L67;
        boolean r64 = false;
    L68:
        if (r5 == false) goto L72;
        if (r7 > 1) goto L72;
        boolean r72 = false;
    L73:
        int r104 = 0;
    L74:
        if (r104 >= r13) goto L139;
        TLRPC.SecureRequiredType r17 = this.currentForm.required_types.get(r104);
        if ((r17 instanceof TLRPC.TL_secureRequiredType) == false) goto L106;
        TLRPC.TL_secureRequiredType r18 = (TLRPC.TL_secureRequiredType) r17;
        TLRPC.SecureValueType r28 = r18.type;
        if ((r28 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L80;
    L104:
        TLRPC.TL_secureRequiredType r29 = r18;
        ArrayList<TLRPC.TL_secureRequiredType> r34 = null;
        boolean r42 = false;
    L133:
        if (r104 != (r13 - 1)) goto L136;
        boolean r52 = true;
    L137:
        addField(r22, r29, r34, r42, r52);
    L138:
        r104 = r104 + 1;
        goto L74
    L136:
        r52 = false;
        goto L137
    L80:
        if ((r28 instanceof TLRPC.TL_secureValueTypeEmail) == true) goto L104;
        if ((r28 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L90;
        if (r64 == false) goto L86;
    L85:
        ArrayList<TLRPC.TL_secureRequiredType> r210 = null;
    L87:
        r34 = r210;
        r42 = false;
    L88:
        r29 = r18;
        goto L133
    L86:
        r210 = r14;
        goto L87
    L90:
        if ((r28 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L94;
        if (r72 == true) goto L85;
        r210 = r15;
        goto L87
    L94:
        if (r64 == true) goto L96;
    L99:
        if (r72 == false) goto L138;
        if (isAddressDocument(r18.type) == false) goto L138;
        ArrayList<TLRPC.TL_secureRequiredType> r211 = new ArrayList();
        r211.add(r18);
        r18 = new TLRPC.TL_secureRequiredType();
        r18.type = new TLRPC.TL_secureValueTypeAddress();
    L98:
        r34 = r211;
        r42 = true;
        goto L88
    L96:
        if (isPersonalDocument(r28) == false) goto L99;
        r211 = new ArrayList();
        r211.add(r18);
        r18 = new TLRPC.TL_secureRequiredType();
        r18.type = new TLRPC.TL_secureValueTypePersonalDetails();
        goto L98
    L106:
        if ((r17 instanceof TLRPC.TL_secureRequiredTypeOneOf) == false) goto L138;
        TLRPC.TL_secureRequiredTypeOneOf r19 = (TLRPC.TL_secureRequiredTypeOneOf) r17;
        if (r19.types.isEmpty() == true) goto L138;
        TLRPC.SecureRequiredType r212 = r19.types.get(0);
        if ((r212 instanceof TLRPC.TL_secureRequiredType) == false) goto L138;
        TLRPC.TL_secureRequiredType r213 = (TLRPC.TL_secureRequiredType) r212;
        if (r64 == true) goto L116;
    L117:
        if (r72 == false) goto L138;
        if (isAddressDocument(r213.type) == false) goto L138;
    L120:
        r34 = new ArrayList();
        int r43 = r19.types.size();
        int r53 = 0;
    L121:
        if (r53 >= r43) goto L128;
        TLRPC.SecureRequiredType r115 = r19.types.get(r53);
        if ((r115 instanceof TLRPC.TL_secureRequiredType) == false) goto L126;
        r34.add((TLRPC.TL_secureRequiredType) r115);
    L126:
        r53 = r53 + 1;
        goto L121
    L128:
        if (isPersonalDocument(r213.type) == false) goto L130;
        TLRPC.TL_secureRequiredType r110 = new TLRPC.TL_secureRequiredType();
        r110.type = new TLRPC.TL_secureValueTypePersonalDetails();
    L131:
        r29 = r110;
        r42 = true;
        goto L133
    L130:
        r110 = new TLRPC.TL_secureRequiredType();
        r110.type = new TLRPC.TL_secureValueTypeAddress();
        goto L131
    L116:
        if (isPersonalDocument(r213.type) == true) goto L120;
    L139:
        r1 = r22;
    L72:
        r72 = true;
    L67:
        r64 = true;
    L140:
        if (r8 == null) goto L150;
        TextInfoPrivacyCell r214 = new TextInfoPrivacyCell(r1);
        this.bottomCell = r214;
        r214.setBackgroundDrawable(Theme.getThemedDrawableByKey(r1, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        TextInfoPrivacyCell r215 = this.bottomCell;
        int r35 = Theme.key_windowBackgroundWhiteGrayText4;
        r215.setLinkTextColorKey(r35);
        if (TextUtils.isEmpty(this.currentForm.privacy_policy_url) == true) goto L148;
        String r216 = LocaleController.formatString("PassportPolicy", R.string.PassportPolicy, new Object[]{UserObject.getFirstName(r8), r8.username});
        SpannableStringBuilder r44 = new SpannableStringBuilder(r216);
        int r65 = r216.indexOf(42);
        int r217 = r216.lastIndexOf(42);
        if (r65 == (-1)) goto L147;
        if (r217 == (-1)) goto L147;
        this.bottomCell.getTextView().setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r44.replace(r217, r217 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r44.replace(r65, r65 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r44.setSpan(new LinkSpan(this), r65, r217 - 1, 33);
    L147:
        this.bottomCell.setText(r44);
    L149:
        this.bottomCell.getTextView().setHighlightColor(Theme.getColor(r35));
        this.bottomCell.getTextView().setGravity(1);
        this.linearLayout2.addView(this.bottomCell, LayoutHelper.createLinear(-1, -2));
        goto L150
    L148:
        this.bottomCell.setText(AndroidUtilities.replaceTags(LocaleController.formatString("PassportNoPolicy", R.string.PassportNoPolicy, new Object[]{UserObject.getFirstName(r8), r8.username})));
    L150:
        FrameLayout r218 = new FrameLayout(r1);
        this.bottomLayout = r218;
        r218.setBackgroundDrawable(Theme.createSelectorWithBackgroundDrawable(Theme.getColor(Theme.key_passport_authorizeBackground), Theme.getColor(Theme.key_passport_authorizeBackgroundSelected)));
        r9.addView(this.bottomLayout, LayoutHelper.createFrame(-1, 48, 80));
        this.bottomLayout.setOnClickListener(new PassportActivity$$ExternalSyntheticLambda6(this));
        TextView r219 = new TextView(r1);
        this.acceptTextView = r219;
        r219.setCompoundDrawablePadding(AndroidUtilities.dp(8.0f));
        this.acceptTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.authorize, 0, 0, 0);
        this.acceptTextView.setTextColor(Theme.getColor(Theme.key_passport_authorizeText));
        this.acceptTextView.setText(LocaleController.getString(R.string.PassportAuthorize));
        this.acceptTextView.setTextSize(1, 14.0f);
        this.acceptTextView.setGravity(17);
        this.acceptTextView.setTypeface(AndroidUtilities.bold());
        this.bottomLayout.addView(this.acceptTextView, LayoutHelper.createFrame(-2, -1, 17));
        ContextProgressView r220 = new ContextProgressView(r1, 0);
        this.progressViewButton = r220;
        r220.setVisibility(4);
        this.bottomLayout.addView(this.progressViewButton, LayoutHelper.createFrame(-1, -1.0f));
        View r0 = new View(r1);
        r0.setBackgroundResource(R.drawable.header_shadow_reverse);
        r9.addView(r0, LayoutHelper.createFrame(-1, 3.0f, 83, 0.0f, 0.0f, 0.0f, 48.0f));
        return;
    L11:
        r8 = null;
        goto L12
    }

    private EncryptionResult createSecureDocument(String r5) {
        byte[] r0 = new byte[(int) new File(r5).length()];
        RandomAccessFile r1 = null;
        RandomAccessFile r2 = new RandomAccessFile(r5, "rws");     // Catch: Exception -> L12
        r2.readFully(r0);     // Catch: Exception -> L6
    L8:
        EncryptionResult r4 = encryptData(r0);
        r2.seek(0);     // Catch: Exception -> L11
        r2.write(r4.encryptedData);     // Catch: Exception -> L11
        r2.close();     // Catch: Exception -> L11
    L10:
        return r4;
    L6:
        r1 = r2;
    L7:
        r2 = r1;
        goto L8
    }

    private String decryptData(byte[] r10, byte[] r11, byte[] r12) {
        if (r10 == null) goto L16;
        if (r11 == null) goto L16;
        if (r11.length != 32) goto L16;
        if (r12 == null) goto L16;
        if (r12.length != 32) goto L16;
        byte[] r112 = Utilities.computeSHA512(r11, r12);
        byte[] r3 = new byte[32];
        System.arraycopy(r112, 0, r3, 0, 32);
        byte[] r4 = new byte[16];
        System.arraycopy(r112, 32, r4, 0, 16);
        int r6 = r10.length;
        byte[] r2 = new byte[r6];
        System.arraycopy(r10, 0, r2, 0, r10.length);
        Utilities.aesCbcEncryptionByteArraySafe(r2, r3, r4, 0, r6, 0, 0);
        if (Arrays.equals(Utilities.computeSHA256(r2), r12) == true) goto L14;
        return null;
    L14:
        int r9 = r2[0] & UByte.MAX_VALUE;
        return new String(r2, r9, r6 - r9);
    L16:
        return null;
    }

    private byte[] decryptSecret(byte[] r9, byte[] r10) {
        if (r9 != null) goto L4;
        return null;
    L4:
        if (r9.length != 32) goto L10;
        byte[] r2 = new byte[32];
        System.arraycopy(r10, 0, r2, 0, 32);
        byte[] r3 = new byte[16];
        System.arraycopy(r10, 32, r3, 0, 16);
        byte[] r1 = new byte[32];
        System.arraycopy(r9, 0, r1, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r1, r2, r3, 0, 32, 0, 0);
        return r1;
    L10:
        return null;
    }

    private byte[] decryptValueSecret(byte[] r12, byte[] r13) {
        if (r12 != null) goto L5;
    L15:
        return null;
    L5:
        if (r12.length != 32) goto L15;
        if (r13 == null) goto L15;
        if (r13.length != 32) goto L15;
        byte[] r4 = new byte[32];
        System.arraycopy(this.saltedPassword, 0, r4, 0, 32);
        byte[] r5 = new byte[16];
        System.arraycopy(this.saltedPassword, 32, r5, 0, 16);
        byte[] r3 = new byte[32];
        System.arraycopy(this.secureSecret, 0, r3, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r3, r4, r5, 0, 32, 0, 0);
        if (checkSecret(r3, null) == true) goto L13;
        return null;
    L13:
        byte[] r11 = Utilities.computeSHA512(r3, r13);
        byte[] r42 = new byte[32];
        System.arraycopy(r11, 0, r42, 0, 32);
        byte[] r52 = new byte[16];
        System.arraycopy(r11, 32, r52, 0, 16);
        byte[] r32 = new byte[32];
        System.arraycopy(r12, 0, r32, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r32, r42, r52, 0, 32, 0, 0);
        return r32;
    }

    private void deleteValueInternal(TLRPC.TL_secureRequiredType r12, TLRPC.TL_secureRequiredType r13, ArrayList<TLRPC.TL_secureRequiredType> r14, boolean r15, Runnable r16, ErrorRunnable r17, boolean r18) {
        if (r12 != null) goto L4;
        return;
    L4:
        TL_account.deleteSecureValue r0 = new TL_account.deleteSecureValue();
        if (r18 == false) goto L8;
        if (r13 == null) goto L8;
        r0.types.add(r13.type);
    L12:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PassportActivity$$ExternalSyntheticLambda69(this, r17, r18, r13, r12, r15, r14, r16));
        return;
    L8:
        if (r15 == false) goto L10;
        r0.types.add(r12.type);
    L10:
        if (r13 == null) goto L12;
        r0.types.add(r13.type);
        goto L12
    }

    private EncryptionResult encryptData(byte[] r20) {
        byte[] r3 = getRandomSecret();
        int r2 = Utilities.random.nextInt(Opcodes.ADD_INT_LIT16) + 32;
    L4:
        if (((r20.length + r2) % 16) == 0) goto L6;
        r2 = r2 + 1;
        goto L4
    L6:
        byte[] r5 = new byte[r2];
        Utilities.random.nextBytes(r5);
        r5[0] = (byte) r2;
        int r13 = r2 + r20.length;
        byte[] r9 = new byte[r13];
        System.arraycopy(r5, 0, r9, 0, r2);
        System.arraycopy(r20, 0, r9, r2, r20.length);
        byte[] r1 = Utilities.computeSHA256(r9);
        byte[] r22 = Utilities.computeSHA512(r3, r1);
        byte[] r52 = new byte[32];
        System.arraycopy(r22, 0, r52, 0, 32);
        byte[] r11 = new byte[16];
        System.arraycopy(r22, 32, r11, 0, 16);
        Utilities.aesCbcEncryptionByteArraySafe(r9, r52, r11, 0, r13, 0, 1);
        byte[] r132 = new byte[32];
        System.arraycopy(this.saltedPassword, 0, r132, 0, 32);
        byte[] r14 = new byte[16];
        System.arraycopy(this.saltedPassword, 32, r14, 0, 16);
        byte[] r12 = new byte[32];
        System.arraycopy(this.secureSecret, 0, r12, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r12, r132, r14, 0, 32, 0, 0);
        byte[] r0 = Utilities.computeSHA512(r12, r1);
        byte[] r133 = new byte[32];
        System.arraycopy(r0, 0, r133, 0, 32);
        byte[] r142 = new byte[16];
        System.arraycopy(r0, 32, r142, 0, 16);
        byte[] r23 = new byte[32];
        System.arraycopy(r3, 0, r23, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r23, r133, r142, 0, 32, 0, 1);
        return new EncryptionResult(r9, r23, r3, r1, r52, r11);
    }

    private void fillInitialValues() {
        if (this.initialValues == null) goto L5;
        return;
    L5:
        this.initialValues = getCurrentValues();
    }

    private void fillNextCodeParams(Bundle r8, TLRPC.TL_auth_sentCode r9, boolean r10) {
        r8.putString("phoneHash", r9.phone_code_hash);
        TLRPC.auth_CodeType r0 = r9.next_type;
        if ((r0 instanceof TLRPC.TL_auth_codeTypeCall) == false) goto L6;
        r8.putInt("nextType", 4);
    L12:
        if (r9.timeout != 0) goto L14;
        r9.timeout = 60;
    L14:
        r8.putInt("timeout", r9.timeout * 1000);
        TLRPC.auth_SentCodeType r02 = r9.type;
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeCall) == false) goto L19;
        r8.putInt("type", 4);
        r8.putInt("length", r9.type.length);
        setPage(2, r10, r8);
        return;
    L19:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeFlashCall) == false) goto L23;
        r8.putInt("type", 3);
        r8.putString("pattern", r9.type.pattern);
        setPage(1, r10, r8);
        return;
    L23:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeSms) == false) goto L26;
        r8.putInt("type", 2);
        r8.putInt("length", r9.type.length);
        setPage(0, r10, r8);
        return;
    L26:
        return;
    L6:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeFlashCall) == false) goto L9;
        r8.putInt("nextType", 3);
        goto L12
    L9:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeSms) == false) goto L12;
        r8.putInt("nextType", 2);
        goto L12
    }

    private String getCurrentValues() {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
        int r2 = 0;
    L3:
        EditTextBoldCursor[] r3 = this.inputFields;
        if (r2 >= r3.length) goto L7;
        r0.append(r3[r2].getText());
        r0.append(",");
        r2 = r2 + 1;
        goto L3
    L7:
        if (this.inputExtraFields == null) goto L12;
        int r22 = 0;
    L9:
        EditTextBoldCursor[] r32 = this.inputExtraFields;
        if (r22 >= r32.length) goto L12;
        r0.append(r32[r22].getText());
        r0.append(",");
        r22 = r22 + 1;
    L12:
        int r23 = this.documents.size();
        int r33 = 0;
    L13:
        if (r33 >= r23) goto L15;
        r0.append(this.documents.get(r33).secureFile.id);
        r33 = r33 + 1;
        goto L13
    L15:
        SecureDocument r24 = this.frontDocument;
        if (r24 == null) goto L18;
        r0.append(r24.secureFile.id);
    L18:
        SecureDocument r25 = this.reverseDocument;
        if (r25 == null) goto L21;
        r0.append(r25.secureFile.id);
    L21:
        SecureDocument r26 = this.selfieDocument;
        if (r26 == null) goto L24;
        r0.append(r26.secureFile.id);
    L24:
        int r27 = this.translationDocuments.size();
    L25:
        if (r1 >= r27) goto L28;
        r0.append(this.translationDocuments.get(r1).secureFile.id);
        r1 = r1 + 1;
        goto L25
    L28:
        return r0.toString();
    }

    private String getDocumentHash(SecureDocument r2) {
        if (r2 == null) goto L13;
        TLRPC.TL_secureFile r1 = r2.secureFile;
        if (r1 == null) goto L9;
        byte[] r12 = r1.file_hash;
        if (r12 == null) goto L9;
        return Base64.encodeToString(r12, 2);
    L9:
        byte[] r13 = r2.fileHash;
        if (r13 != null) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return Base64.encodeToString(r13, 2);
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private int getFieldCost(String r2) {
        r2.getClass();
        char r0 = 65535;
        switch(r2.hashCode()) {
            case -2006252145: goto L70;
            case -1537298398: goto L66;
            case -1249512767: goto L62;
            case -796150911: goto L58;
            case -796150910: goto L54;
            case -160985414: goto L50;
            case 3053931: goto L46;
            case 109757585: goto L42;
            case 421072629: goto L38;
            case 451516732: goto L34;
            case 475919162: goto L30;
            case 506677093: goto L26;
            case 1168724782: goto L22;
            case 1181577377: goto L18;
            case 1481071862: goto L14;
            case 2002465324: goto L10;
            case 2013122196: goto L6;
            default: goto L73;
        };
    L73:
        switch(r0) {
            case 0: goto L102;
            case 1: goto L100;
            case 2: goto L98;
            case 3: goto L96;
            case 4: goto L94;
            case 5: goto L92;
            case 6: goto L90;
            case 7: goto L88;
            case 8: goto L86;
            case 9: goto L92;
            case 10: goto L84;
            case 11: goto L82;
            case 12: goto L80;
            case 13: goto L86;
            case 14: goto L78;
            case 15: goto L76;
            case 16: goto L100;
            default: goto L74;
        };
    L74:
        return 100;
    L76:
        return 31;
    L78:
        return 25;
    L80:
        return 23;
    L82:
        return 27;
    L84:
        return 28;
    L86:
        return 21;
    L88:
        return 33;
    L90:
        return 32;
    L92:
        return 20;
    L94:
        return 30;
    L96:
        return 29;
    L98:
        return 24;
    L100:
        return 22;
    L102:
        return 26;
    L6:
        if (r2.equals("last_name") == false) goto L73;
        r0 = 16;
        goto L73
    L10:
        if (r2.equals("post_code") == false) goto L73;
        r0 = 15;
        goto L73
    L14:
        if (r2.equals("country_code") == false) goto L73;
        r0 = 14;
        goto L73
    L18:
        if (r2.equals("middle_name_native") == false) goto L73;
        r0 = '\r';
        goto L73
    L22:
        if (r2.equals("birth_date") == false) goto L73;
        r0 = '\f';
        goto L73
    L26:
        if (r2.equals("document_no") == false) goto L73;
        r0 = 11;
        goto L73
    L30:
        if (r2.equals("expiry_date") == false) goto L73;
        r0 = '\n';
        goto L73
    L34:
        if (r2.equals("first_name_native") == false) goto L73;
        r0 = '\t';
        goto L73
    L38:
        if (r2.equals("middle_name") == false) goto L73;
        r0 = '\b';
        goto L73
    L42:
        if (r2.equals("state") == false) goto L73;
        r0 = 7;
        goto L73
    L46:
        if (r2.equals("city") == false) goto L73;
        r0 = 6;
        goto L73
    L50:
        if (r2.equals("first_name") == false) goto L73;
        r0 = 5;
        goto L73
    L54:
        if (r2.equals("street_line2") == false) goto L73;
        r0 = 4;
        goto L73
    L58:
        if (r2.equals("street_line1") == false) goto L73;
        r0 = 3;
        goto L73
    L62:
        if (r2.equals("gender") == false) goto L73;
        r0 = 2;
        goto L73
    L66:
        if (r2.equals("last_name_native") == false) goto L73;
        r0 = 1;
        goto L73
    L70:
        if (r2.equals("residence_country_code") == false) goto L73;
        r0 = 0;
        goto L73
    }

    private int getMaxSelectedDocuments() {
        int r0 = this.uploadingFileType;
        if (r0 != 0) goto L8;
        int r2 = this.documents.size();
    L6:
        return 20 - r2;
    L8:
        if (r0 != 4) goto L10;
        r2 = this.translationDocuments.size();
        goto L6
    L10:
        return 1;
    }

    private String getNameForType(TLRPC.SecureValueType r1) {
        if ((r1 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L7;
        return "personal_details";
    L7:
        if ((r1 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L11;
        return "passport";
    L11:
        if ((r1 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L15;
        return "internal_passport";
    L15:
        if ((r1 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L19;
        return "driver_license";
    L19:
        if ((r1 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L23;
        return "identity_card";
    L23:
        if ((r1 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L27;
        return "utility_bill";
    L27:
        if ((r1 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L31;
        return "address";
    L31:
        if ((r1 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L35;
        return "bank_statement";
    L35:
        if ((r1 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L39;
        return "rental_agreement";
    L39:
        if ((r1 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L43;
        return "temporary_registration";
    L43:
        if ((r1 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L47;
        return "passport_registration";
    L47:
        if ((r1 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L51;
        return "email";
    L51:
        if ((r1 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L54;
        return "phone";
    L54:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private byte[] getRandomSecret() {
        byte[] r0 = new byte[32];
        Utilities.random.nextBytes(r0);
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 32) goto L6;
        r2 = r2 + (255 & r0[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        int r22 = r2 % 255;
        if (r22 == 239) goto L12;
        int r5 = Utilities.random.nextInt(32);
        int r23 = (r0[r5] & UByte.MAX_VALUE) + (239 - r22);
        if (r23 >= 255) goto L11;
        r23 = r23 + 255;
    L11:
        r0[r5] = (byte) (r23 % 255);
    L12:
        return r0;
    }

    private SecureDocumentKey getSecureDocumentKey(byte[] r4, byte[] r5) {
        byte[] r3 = Utilities.computeSHA512(decryptValueSecret(r4, r5), r5);
        byte[] r52 = new byte[32];
        System.arraycopy(r3, 0, r52, 0, 32);
        byte[] r2 = new byte[16];
        System.arraycopy(r3, 32, r2, 0, 16);
        return new SecureDocumentKey(r52, r2);
    }

    private String getTextForType(TLRPC.SecureValueType r1) {
        if ((r1 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L7;
        return LocaleController.getString(R.string.ActionBotDocumentPassport);
    L7:
        if ((r1 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L11;
        return LocaleController.getString(R.string.ActionBotDocumentDriverLicence);
    L11:
        if ((r1 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L15;
        return LocaleController.getString(R.string.ActionBotDocumentIdentityCard);
    L15:
        if ((r1 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L19;
        return LocaleController.getString(R.string.ActionBotDocumentUtilityBill);
    L19:
        if ((r1 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L23;
        return LocaleController.getString(R.string.ActionBotDocumentBankStatement);
    L23:
        if ((r1 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L27;
        return LocaleController.getString(R.string.ActionBotDocumentRentalAgreement);
    L27:
        if ((r1 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L31;
        return LocaleController.getString(R.string.ActionBotDocumentInternalPassport);
    L31:
        if ((r1 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L35;
        return LocaleController.getString(R.string.ActionBotDocumentPassportRegistration);
    L35:
        if ((r1 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L39;
        return LocaleController.getString(R.string.ActionBotDocumentTemporaryRegistration);
    L39:
        if ((r1 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L43;
        return LocaleController.getString(R.string.ActionBotDocumentPhone);
    L43:
        if ((r1 instanceof TLRPC.TL_secureValueTypeEmail) == true) goto L45;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L45:
        return LocaleController.getString(R.string.ActionBotDocumentEmail);
    }

    private String getTranslitString(String r2) {
        return LocaleController.getInstance().getTranslitString(r2, true);
    }

    private TLRPC.TL_secureValue getValueByType(TLRPC.TL_secureRequiredType r13, boolean r14) {
        if (r13 != null) goto L5;
        return null;
    L5:
        int r1 = this.currentForm.values.size();
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L60;
        TLRPC.TL_secureValue r4 = this.currentForm.values.get(r3);
        if (r13.type.getClass() == r4.type.getClass()) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        if (r14 == true) goto L11;
    L58:
        return r4;
    L11:
        if (r13.selfie_required == false) goto L16;
        if ((r4.selfie instanceof TLRPC.TL_secureFile) == true) goto L16;
        return null;
    L16:
        if (r13.translation_required == false) goto L21;
        if (r4.translation.isEmpty() == false) goto L21;
        return null;
    L21:
        if (isAddressDocument(r13.type) == false) goto L26;
        if (r4.files.isEmpty() == false) goto L26;
        return null;
    L26:
        if (isPersonalDocument(r13.type) == true) goto L28;
    L30:
        TLRPC.SecureValueType r142 = r13.type;
        if ((r142 instanceof TLRPC.TL_secureValueTypeDriverLicense) == true) goto L35;
        if ((r142 instanceof TLRPC.TL_secureValueTypeIdentityCard) == true) goto L35;
    L38:
        if ((r142 instanceof TLRPC.TL_secureValueTypePersonalDetails) == true) goto L42;
        if ((r142 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L58;
    L42:
        if ((r142 instanceof TLRPC.TL_secureValueTypePersonalDetails) == true) goto L44;
        String[] r132 = {"street_line1", "street_line2", "post_code", "city", "state", "country_code"};
    L62:
        TLRPC.TL_secureData r12 = r4.data;     // Catch: Throwable -> L61
        JSONObject r143 = new JSONObject(decryptData(r12.data, decryptValueSecret(r12.secret, r12.data_hash), r4.data.data_hash));     // Catch: Throwable -> L61
    L49:
        if (r2 >= r132.length) goto L58;
        if (r143.has(r132[r2]) == false) goto L57;
        if (TextUtils.isEmpty(r143.getString(r132[r2])) == true) goto L57;
        r2 = r2 + 1;
    L57:
        return null;
    L44:
        if (r13.native_names == false) goto L46;
        r132 = new String[]{"first_name_native", "last_name_native", "birth_date", "gender", "country_code", "residence_country_code"};
        goto L62
    L46:
        r132 = new String[]{"first_name", "last_name", "birth_date", "gender", "country_code", "residence_country_code"};
    L35:
        if ((r4.reverse_side instanceof TLRPC.TL_secureFile) == true) goto L38;
        return null;
    L28:
        if ((r4.front_side instanceof TLRPC.TL_secureFile) == true) goto L30;
        return null;
    L60:
        return null;
    }

    private TextDetailSecureCell getViewByType(TLRPC.TL_secureRequiredType r3) {
        TextDetailSecureCell r0 = this.typesViews.get(r3);
        if (r0 != null) goto L8;
        TLRPC.TL_secureRequiredType r32 = this.documentsToTypesLink.get(r3);
        if (r32 == null) goto L8;
        return this.typesViews.get(r32);
    L8:
        return r0;
    }

    private boolean hasNotValueForType(Class<? extends TLRPC.SecureValueType> r5) {
        int r0 = this.currentForm.values.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.currentForm.values.get(r2).type.getClass() == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    private boolean hasUnfilledValues() {
        if (hasNotValueForType(TLRPC.TL_secureValueTypePhone.class) == false) goto L5;
        return true;
    L5:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeEmail.class) == false) goto L7;
        return true;
    L7:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePersonalDetails.class) == false) goto L9;
        return true;
    L9:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePassport.class) == false) goto L11;
        return true;
    L11:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeInternalPassport.class) == false) goto L13;
        return true;
    L13:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeIdentityCard.class) == false) goto L15;
        return true;
    L15:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeDriverLicense.class) == false) goto L17;
        return true;
    L17:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeAddress.class) == false) goto L19;
        return true;
    L19:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeUtilityBill.class) == false) goto L21;
        return true;
    L21:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePassportRegistration.class) == false) goto L23;
        return true;
    L23:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeTemporaryRegistration.class) == false) goto L25;
        return true;
    L25:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeBankStatement.class) == false) goto L27;
        return true;
    L27:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeRentalAgreement.class) == true) goto L44;
        return false;
    L44:
        return true;
    }

    private boolean isAddressDocument(TLRPC.SecureValueType r1) {
        if ((r1 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == true) goto L20;
        return false;
    L20:
        return true;
    }

    private boolean isHasNotAnyChanges() {
        String r0 = this.initialValues;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.equals(getCurrentValues()) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isPersonalDocument(TLRPC.SecureValueType r1) {
        if ((r1 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TLRPC.TL_secureValueTypeIdentityCard) == true) goto L17;
        return false;
    L17:
        return true;
    }

    private /* synthetic */ void lambda$addDocumentView$55(int r5, View r6) {
        this.uploadingFileType = r5;
        if (r5 != 1) goto L6;
        this.currentPhotoViewerLayout = this.selfieLayout;
    L13:
        SecureDocument r62 = (SecureDocument) r6.getTag();
        PhotoViewer.getInstance().setParentActivity(this);
        if (r5 != 1) goto L17;
        ArrayList<SecureDocument> r52 = new ArrayList();
        r52.add(this.selfieDocument);
        PhotoViewer.getInstance().openPhoto(r52, 0, this.provider);
        return;
    L17:
        if (r5 != 2) goto L20;
        ArrayList<SecureDocument> r53 = new ArrayList();
        r53.add(this.frontDocument);
        PhotoViewer.getInstance().openPhoto(r53, 0, this.provider);
        return;
    L20:
        if (r5 != 3) goto L23;
        ArrayList<SecureDocument> r54 = new ArrayList();
        r54.add(this.reverseDocument);
        PhotoViewer.getInstance().openPhoto(r54, 0, this.provider);
        return;
    L23:
        if (r5 != 0) goto L26;
        PhotoViewer r55 = PhotoViewer.getInstance();
        ArrayList<SecureDocument> r0 = this.documents;
        r55.openPhoto(r0, r0.indexOf(r62), this.provider);
        return;
    L26:
        PhotoViewer r56 = PhotoViewer.getInstance();
        ArrayList<SecureDocument> r02 = this.translationDocuments;
        r56.openPhoto(r02, r02.indexOf(r62), this.provider);
        return;
    L6:
        if (r5 != 4) goto L8;
        this.currentPhotoViewerLayout = this.translationLayout;
        goto L13
    L8:
        if (r5 != 2) goto L10;
        this.currentPhotoViewerLayout = this.frontLayout;
        goto L13
    L10:
        if (r5 != 3) goto L12;
        this.currentPhotoViewerLayout = this.reverseLayout;
        goto L13
    L12:
        this.currentPhotoViewerLayout = this.documentsLayout;
        goto L13
    }

    private /* synthetic */ void lambda$addDocumentView$56(SecureDocument r2, int r3, SecureDocumentCell r4, String r5, AlertDialog r6, int r7) {
        this.documentsCells.remove(r2);
        if (r3 != 1) goto L6;
        this.selfieDocument = null;
        this.selfieLayout.removeView(r4);
    L15:
        if (r5 == null) goto L22;
        HashMap<String, String> r42 = this.documentsErrors;
        if (r42 == null) goto L19;
        r42.remove(r5);
    L19:
        HashMap<String, String> r43 = this.errorsValues;
        if (r43 == null) goto L22;
        r43.remove(r5);
    L22:
        updateUploadText(r3);
        String r32 = r2.path;
        if (r32 != null) goto L25;
        return;
    L25:
        if (this.uploadingDocuments.remove(r32) != null) goto L27;
        return;
    L27:
        if (this.uploadingDocuments.isEmpty() == false) goto L29;
        this.doneItem.setEnabled(true);
        this.doneItem.setAlpha(1.0f);
    L29:
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(r2.path, false);
        return;
    L6:
        if (r3 != 4) goto L9;
        this.translationDocuments.remove(r2);
        this.translationLayout.removeView(r4);
        goto L15
    L9:
        if (r3 != 2) goto L12;
        this.frontDocument = null;
        this.frontLayout.removeView(r4);
        goto L15
    L12:
        if (r3 != 3) goto L14;
        this.reverseDocument = null;
        this.reverseLayout.removeView(r4);
        goto L15
    L14:
        this.documents.remove(r2);
        this.documentsLayout.removeView(r4);
        goto L15
    }

    private /* synthetic */ boolean lambda$addDocumentView$57(int r9, SecureDocument r10, SecureDocumentCell r11, String r12, View r13) {
        AlertDialog.Builder r132 = new AlertDialog.Builder(getParentActivity());
        if (r9 != 1) goto L5;
        r132.setMessage(LocaleController.getString(R.string.PassportDeleteSelfie));
    L6:
        r132.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r132.setTitle(LocaleController.getString(R.string.AppName));
        r132.setPositiveButton(LocaleController.getString(R.string.OK), new PassportActivity$$ExternalSyntheticLambda65(this, r10, r9, r11, r12));
        showDialog(r132.create());
        return true;
    L5:
        r132.setMessage(LocaleController.getString(R.string.PassportDeleteScan));
        goto L6
    }

    private /* synthetic */ void lambda$addField$61(TLRPC.TL_secureRequiredType r1, ArrayList r2, boolean r3, DialogInterface r4, int r5) {
        openTypeActivity(r1, (TLRPC.TL_secureRequiredType) r2.get(r5), r2, r3);
    }

    private /* synthetic */ void lambda$addField$62(String r1, String r2) {
        needHideProgress();
    }

    private /* synthetic */ void lambda$addField$63(TLRPC.TL_secureRequiredType r9, boolean r10, AlertDialog r11, int r12) {
        needShowProgress();
        deleteValueInternal(r9, null, null, true, new PassportActivity$$ExternalSyntheticLambda70(this), new PassportActivity$$ExternalSyntheticLambda71(this), r10);
    }

    private /* synthetic */ void lambda$addField$64(ArrayList r7, TLRPC.TL_secureRequiredType r8, boolean r9, View r10) {
        if (r7 == null) goto L12;
        int r1 = r7.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        TLRPC.TL_secureRequiredType r3 = (TLRPC.TL_secureRequiredType) r7.get(r2);
        if (getValueByType(r3, false) != null) goto L13;
        if (r1 == 1) goto L13;
        r2 = r2 + 1;
    L13:
        TLRPC.SecureValueType r12 = r8.type;
        if ((r12 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L16;
    L31:
        if (r3 != null) goto L73;
        if (r7 == null) goto L73;
        if (r7.isEmpty() == true) goto L73;
        AlertDialog.Builder r13 = new AlertDialog.Builder(getParentActivity());
        r13.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        TLRPC.SecureValueType r0 = r8.type;
        if ((r0 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L39;
        r13.setTitle(LocaleController.getString(R.string.PassportIdentityDocument));
    L41:
        ArrayList r02 = new ArrayList();
        int r22 = r7.size();
        int r32 = 0;
    L42:
        if (r32 >= r22) goto L71;
        TLRPC.SecureValueType r4 = ((TLRPC.TL_secureRequiredType) r7.get(r32)).type;
        if ((r4 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L47;
        r02.add(LocaleController.getString(R.string.PassportAddLicence));
    L70:
        r32 = r32 + 1;
        goto L42
    L47:
        if ((r4 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L50;
        r02.add(LocaleController.getString(R.string.PassportAddPassport));
        goto L70
    L50:
        if ((r4 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L53;
        r02.add(LocaleController.getString(R.string.PassportAddInternalPassport));
        goto L70
    L53:
        if ((r4 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L56;
        r02.add(LocaleController.getString(R.string.PassportAddCard));
        goto L70
    L56:
        if ((r4 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L59;
        r02.add(LocaleController.getString(R.string.PassportAddBill));
        goto L70
    L59:
        if ((r4 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L62;
        r02.add(LocaleController.getString(R.string.PassportAddBank));
        goto L70
    L62:
        if ((r4 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L65;
        r02.add(LocaleController.getString(R.string.PassportAddAgreement));
        goto L70
    L65:
        if ((r4 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L68;
        r02.add(LocaleController.getString(R.string.PassportAddTemporaryRegistration));
        goto L70
    L68:
        if ((r4 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L70;
        r02.add(LocaleController.getString(R.string.PassportAddPassportRegistration));
        goto L70
    L71:
        r13.setItems((CharSequence[]) r02.toArray(new CharSequence[0]), new PassportActivity$$ExternalSyntheticLambda67(this, r8, r7, r9));
        showDialog(r13.create());
        return;
    L39:
        if ((r0 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L41;
        r13.setTitle(LocaleController.getString(R.string.PassportAddress));
    L73:
        openTypeActivity(r8, r3, r7, r9);
        return;
    L16:
        if ((r12 instanceof TLRPC.TL_secureValueTypeAddress) == true) goto L31;
        boolean r23 = r12 instanceof TLRPC.TL_secureValueTypePhone;
        if (r23 == true) goto L23;
        if ((r12 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L73;
    L23:
        if (getValueByType(r8, false) == null) goto L73;
        AlertDialog.Builder r72 = new AlertDialog.Builder(getParentActivity());
        r72.setPositiveButton(LocaleController.getString(R.string.OK), new PassportActivity$$ExternalSyntheticLambda68(this, r8, r9));
        r72.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r72.setTitle(LocaleController.getString(R.string.AppName));
        if (r23 == false) goto L28;
        int r82 = R.string.PassportDeletePhoneAlert;
    L29:
        r72.setMessage(LocaleController.getString(r82));
        showDialog(r72.create());
        return;
    L28:
        r82 = R.string.PassportDeleteEmailAlert;
    L12:
        r3 = null;
        goto L13
    }

    private /* synthetic */ void lambda$checkDiscard$69(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$checkNativeFields$58() {
        EditTextBoldCursor[] r0 = this.inputExtraFields;
        if (r0 == null) goto L6;
        scrollToField(r0[0]);
        return;
    }

    private /* synthetic */ void lambda$createAddressInterface$32(View r1) {
        this.uploadingFileType = 0;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createAddressInterface$33(View r1) {
        this.uploadingFileType = 4;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createAddressInterface$34(CountrySelectActivity.Country r3) {
        this.inputFields[5].setText(r3.name);
        this.currentCitizeship = r3.shortname;
    }

    private /* synthetic */ boolean lambda$createAddressInterface$35(View r2, MotionEvent r3) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r3.getAction() != 1) goto L8;
        CountrySelectActivity r22 = new CountrySelectActivity(false);
        r22.setCountrySelectActivityDelegate(new PassportActivity$$ExternalSyntheticLambda38(this));
        presentFragment(r22);
    L8:
        return true;
    }

    private /* synthetic */ boolean lambda$createAddressInterface$36(TextView r9, int r10, KeyEvent r11) {
        if (r10 != 5) goto L11;
        int r102 = ((Integer) r9.getTag()).intValue() + 1;
        EditTextBoldCursor[] r0 = this.inputFields;
        if (r102 >= r0.length) goto L10;
        boolean r02 = r0[r102].isFocusable();
        EditTextBoldCursor[] r8 = this.inputFields;
        if (r02 == false) goto L9;
        r8[r102].requestFocus();
        goto L10
    L9:
        r8[r102].dispatchTouchEvent(MotionEvent.obtain(0, 0, 1, 0.0f, 0.0f, 0));
        r9.clearFocus();
        AndroidUtilities.hideKeyboard(r9);
    L10:
        return true;
    L11:
        return false;
    }

    private /* synthetic */ void lambda$createAddressInterface$37(View r1) {
        createDocumentDeleteAlert();
    }

    private /* synthetic */ void lambda$createDocumentDeleteAlert$38(boolean[] r8, AlertDialog r9, int r10) {
        if (this.documentOnly == true) goto L5;
        this.currentValues.clear();
    L5:
        this.currentDocumentValues.clear();
        this.delegate.deleteValue(this.currentType, this.currentDocumentsType, this.availableDocumentTypes, r8[0], null, null);
        finishFragment();
    }

    private /* synthetic */ void lambda$createEmailInterface$24(View r1) {
        this.useCurrentValue = true;
        this.doneItem.callOnClick();
        this.useCurrentValue = false;
    }

    private /* synthetic */ boolean lambda$createEmailInterface$25(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L5;
    L9:
        this.doneItem.callOnClick();
        return true;
    L5:
        if (r2 == 5) goto L9;
        return false;
    }

    private /* synthetic */ boolean lambda$createEmailVerificationInterface$5(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L5;
    L9:
        this.doneItem.callOnClick();
        return true;
    L5:
        if (r2 == 5) goto L9;
        return false;
    }

    private /* synthetic */ void lambda$createIdentityInterface$40(View r1) {
        this.uploadingFileType = 2;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createIdentityInterface$41(View r1) {
        this.uploadingFileType = 3;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createIdentityInterface$42(View r1) {
        this.uploadingFileType = 1;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createIdentityInterface$43(View r1) {
        this.uploadingFileType = 4;
        openAttachMenu();
    }

    private /* synthetic */ void lambda$createIdentityInterface$44(View r2) {
        if (getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L6;
        getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 22);
        return;
    L6:
        CameraScanActivity r22 = new CameraScanActivity(0);
        r22.setDelegate(new AnonymousClass14(this));
        presentFragment(r22);
    }

    private /* synthetic */ void lambda$createIdentityInterface$45(View r3, CountrySelectActivity.Country r4) {
        int r32 = ((Integer) r3.getTag()).intValue();
        EditTextBoldCursor r0 = this.inputFields[r32];
        if (r32 != 5) goto L5;
        this.currentCitizeship = r4.shortname;
    L6:
        r0.setText(r4.name);
        return;
    L5:
        this.currentResidence = r4.shortname;
        goto L6
    }

    private /* synthetic */ boolean lambda$createIdentityInterface$46(View r3, MotionEvent r4) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r4.getAction() != 1) goto L8;
        CountrySelectActivity r42 = new CountrySelectActivity(false);
        r42.setCountrySelectActivityDelegate(new PassportActivity$$ExternalSyntheticLambda56(this, r3));
        presentFragment(r42);
    L8:
        return true;
    }

    private /* synthetic */ void lambda$createIdentityInterface$47(int r3, EditTextBoldCursor r4, int r5, int r6, int r7) {
        if (r3 != 8) goto L5;
        int[] r2 = this.currentExpireDate;
        r2[0] = r5;
        r2[1] = r6 + 1;
        r2[2] = r7;
    L5:
        r4.setText(String.format(Locale.US, "%02d.%02d.%d", new Object[]{Integer.valueOf(r7), Integer.valueOf(r6 + 1), Integer.valueOf(r5)}));
    }

    private /* synthetic */ void lambda$createIdentityInterface$48(EditTextBoldCursor r1, AlertDialog r2, int r3) {
        int[] r0 = this.currentExpireDate;
        r0[2] = 0;
        r0[1] = 0;
        r0[0] = 0;
        r1.setText(LocaleController.getString(R.string.PassportNoExpireDate));
    }

    private /* synthetic */ boolean lambda$createIdentityInterface$49(Context r21, View r22, MotionEvent r23) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r23.getAction() != 1) goto L28;
        Calendar r1 = Calendar.getInstance();
        r1.get(1);
        r1.get(2);
        r1.get(5);
        EditTextBoldCursor r12 = (EditTextBoldCursor) r22;     // Catch: Exception -> L12
        int r5 = ((Integer) r12.getTag()).intValue();     // Catch: Exception -> L12
        if (r5 != 8) goto L14;
        String r7 = LocaleController.getString(R.string.PassportSelectExpiredDate);     // Catch: Exception -> L12
        int r11 = 0;
        int r13 = 0;
        int r122 = 20;
    L11:
        String r17 = r7;
        String[] r72 = r12.getText().toString().split("\\.");     // Catch: Exception -> L12
        if (r72.length != 3) goto L18;
        int r8 = Utilities.parseInt(r72[0]);     // Catch: Exception -> L12
        int r9 = Utilities.parseInt(r72[1]);     // Catch: Exception -> L12
        int r16 = Utilities.parseInt(r72[2]);     // Catch: Exception -> L12
        int r14 = r8;
        int r15 = r9;
    L19:
        if (r5 != 8) goto L21;
        boolean r18 = true;
    L22:
        AlertDialog.Builder r2 = AlertsCreator.createDatePickerDialog(r21, r11, r122, r13, r14, r15, r16, r17, r18, new PassportActivity$$ExternalSyntheticLambda53(this, r5, r12));     // Catch: Exception -> L12
        if (r5 != 8) goto L25;
        r2.setNegativeButton(LocaleController.getString(R.string.PassportSelectNotExpire), new PassportActivity$$ExternalSyntheticLambda54(this, r12));     // Catch: Exception -> L12
    L25:
        showDialog(r2.create());     // Catch: Exception -> L12
        goto L28
    L21:
        r18 = false;
        goto L22
    L18:
        r14 = -1;
        r15 = -1;
        r16 = -1;
        goto L19
    L14:
        r7 = LocaleController.getString(R.string.PassportSelectBithdayDate);     // Catch: Exception -> L12
        r122 = 0;
        r11 = -120;
        r13 = -18;
    L12:
        e = move-exception;
        FileLog.e(e);
    L28:
        return true;
    }

    private /* synthetic */ void lambda$createIdentityInterface$50(DialogInterface r2, int r3) {
        if (r3 != 0) goto L7;
        this.currentGender = "male";
        this.inputFields[4].setText(LocaleController.getString(R.string.PassportMale));
        return;
    L7:
        if (r3 != 1) goto L10;
        this.currentGender = "female";
        this.inputFields[4].setText(LocaleController.getString(R.string.PassportFemale));
        return;
    }

    private /* synthetic */ boolean lambda$createIdentityInterface$51(View r5, MotionEvent r6) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r6.getAction() != 1) goto L8;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setTitle(LocaleController.getString(R.string.PassportSelectGender));
        r52.setItems(new CharSequence[]{LocaleController.getString(R.string.PassportMale), LocaleController.getString(R.string.PassportFemale)}, new PassportActivity$$ExternalSyntheticLambda46(this));
        r52.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r52.create());
    L8:
        return true;
    }

    private /* synthetic */ boolean lambda$createIdentityInterface$52(TextView r9, int r10, KeyEvent r11) {
        if (r10 != 5) goto L11;
        int r102 = ((Integer) r9.getTag()).intValue() + 1;
        EditTextBoldCursor[] r0 = this.inputFields;
        if (r102 >= r0.length) goto L10;
        boolean r02 = r0[r102].isFocusable();
        EditTextBoldCursor[] r8 = this.inputFields;
        if (r02 == false) goto L9;
        r8[r102].requestFocus();
        goto L10
    L9:
        r8[r102].dispatchTouchEvent(MotionEvent.obtain(0, 0, 1, 0.0f, 0.0f, 0));
        r9.clearFocus();
        AndroidUtilities.hideKeyboard(r9);
    L10:
        return true;
    L11:
        return false;
    }

    private /* synthetic */ boolean lambda$createIdentityInterface$53(TextView r9, int r10, KeyEvent r11) {
        if (r10 != 5) goto L11;
        int r102 = ((Integer) r9.getTag()).intValue() + 1;
        EditTextBoldCursor[] r0 = this.inputExtraFields;
        if (r102 >= r0.length) goto L10;
        boolean r02 = r0[r102].isFocusable();
        EditTextBoldCursor[] r8 = this.inputExtraFields;
        if (r02 == false) goto L9;
        r8[r102].requestFocus();
        goto L10
    L9:
        r8[r102].dispatchTouchEvent(MotionEvent.obtain(0, 0, 1, 0.0f, 0.0f, 0));
        r9.clearFocus();
        AndroidUtilities.hideKeyboard(r9);
    L10:
        return true;
    L11:
        return false;
    }

    private /* synthetic */ void lambda$createIdentityInterface$54(View r1) {
        createDocumentDeleteAlert();
    }

    private /* synthetic */ void lambda$createManageInterface$17(View r1) {
        openAddDocumentAlert();
    }

    private /* synthetic */ void lambda$createManageInterface$18() {
        int r0 = 0;
    L4:
        if (r0 >= this.linearLayout2.getChildCount()) goto L9;
        View r1 = this.linearLayout2.getChildAt(r0);
        if ((r1 instanceof TextDetailSecureCell) == false) goto L8;
        this.linearLayout2.removeView(r1);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        needHideProgress();
        this.typesViews.clear();
        this.typesValues.clear();
        this.currentForm.values.clear();
        updateManageVisibility();
    }

    private /* synthetic */ void lambda$createManageInterface$19(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda72(this));
    }

    private /* synthetic */ void lambda$createManageInterface$20(AlertDialog r3, int r4) {
        TL_account.deleteSecureValue r32 = new TL_account.deleteSecureValue();
        int r42 = 0;
    L4:
        if (r42 >= this.currentForm.values.size()) goto L6;
        r32.types.add(this.currentForm.values.get(r42).type);
        r42 = r42 + 1;
        goto L4
    L6:
        needShowProgress();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new PassportActivity$$ExternalSyntheticLambda62(this));
    }

    private /* synthetic */ void lambda$createManageInterface$21(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setTitle(LocaleController.getString(R.string.TelegramPassportDeleteTitle));
        r32.setMessage(LocaleController.getString(R.string.TelegramPassportDeleteAlert));
        r32.setPositiveButton(LocaleController.getString(R.string.Delete), new PassportActivity$$ExternalSyntheticLambda52(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r33 = r32.create();
        showDialog(r33);
        TextView r2 = (TextView) r33.getButton(-1);
        if (r2 == null) goto L6;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    private /* synthetic */ void lambda$createManageInterface$22(View r1) {
        openAddDocumentAlert();
    }

    private /* synthetic */ void lambda$createPasswordInterface$10(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda64(this, r3, r2));
    }

    private /* synthetic */ void lambda$createPasswordInterface$11(AlertDialog r2, int r3) {
        Browser.openUrl(getParentActivity(), "https://telegram.org/deactivate?phone=" + UserConfig.getInstance(this.currentAccount).getClientPhone());
    }

    private /* synthetic */ void lambda$createPasswordInterface$12(View r4) {
        if (this.currentPassword.has_recovery == false) goto L7;
        needShowProgress();
        TLRPC.TL_auth_requestPasswordRecovery r42 = new TLRPC.TL_auth_requestPasswordRecovery();
        int r43 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new PassportActivity$$ExternalSyntheticLambda42(this), 10);
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r43, this.classGuid);
        return;
    L7:
        if (getParentActivity() != null) goto L9;
        return;
    L9:
        AlertDialog.Builder r44 = new AlertDialog.Builder(getParentActivity());
        r44.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r44.setNegativeButton(LocaleController.getString(R.string.RestorePasswordResetAccount), new PassportActivity$$ExternalSyntheticLambda43(this));
        r44.setTitle(LocaleController.getString(R.string.RestorePasswordNoEmailTitle));
        r44.setMessage(LocaleController.getString(R.string.RestorePasswordNoEmailText));
        showDialog(r44.create());
    }

    private /* synthetic */ void lambda$createPasswordInterface$6(View r4) {
        TwoStepVerificationSetupActivity r42 = new TwoStepVerificationSetupActivity(this.currentAccount, 0, this.currentPassword);
        r42.setCloseAfterSet(true);
        presentFragment(r42);
    }

    private /* synthetic */ boolean lambda$createPasswordInterface$7(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L9:
        this.doneItem.callOnClick();
        return true;
    L5:
        if (r2 == 6) goto L9;
        return false;
    }

    private /* synthetic */ void lambda$createPasswordInterface$8(TLRPC.TL_auth_passwordRecovery r2, AlertDialog r3, int r4) {
        TL_account.Password r32 = this.currentPassword;
        r32.email_unconfirmed_pattern = r2.email_pattern;
        presentFragment(new TwoStepVerificationSetupActivity(this.currentAccount, 4, r32));
    }

    private /* synthetic */ void lambda$createPasswordInterface$9(TLRPC.TL_error r5, TLObject r6) {
        needHideProgress();
        if (r5 != null) goto L9;
        TLRPC.TL_auth_passwordRecovery r62 = (TLRPC.TL_auth_passwordRecovery) r6;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setMessage(LocaleController.formatString("RestoreEmailSent", R.string.RestoreEmailSent, new Object[]{r62.email_pattern}));
        r52.setTitle(LocaleController.getString(R.string.RestoreEmailSentTitle));
        r52.setPositiveButton(LocaleController.getString(R.string.OK), new PassportActivity$$ExternalSyntheticLambda73(this, r62));
        Dialog r4 = showDialog(r52.create());
        if (r4 == null) goto L18;
        r4.setCanceledOnTouchOutside(false);
        r4.setCancelable(false);
        return;
    L18:
        return;
    L9:
        if (r5.text.startsWith("FLOOD_WAIT") == false) goto L16;
        int r53 = Utilities.parseInt(r5.text);
        if (r53 >= 60) goto L13;
        String r54 = LocaleController.formatPluralString("Seconds", r53, new Object[0]);
    L14:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r54}));
        return;
    L13:
        r54 = LocaleController.formatPluralString("Minutes", r53 / 60, new Object[0]);
        goto L14
    L16:
        showAlertWithText(LocaleController.getString(R.string.AppName), r5.text);
    }

    private /* synthetic */ void lambda$createPhoneInterface$26(View r1) {
        this.useCurrentValue = true;
        this.doneItem.callOnClick();
        this.useCurrentValue = false;
    }

    private /* synthetic */ void lambda$createPhoneInterface$27() {
        AndroidUtilities.showKeyboard(this.inputFields[2]);
    }

    private /* synthetic */ void lambda$createPhoneInterface$28(CountrySelectActivity.Country r6) {
        this.inputFields[0].setText(r6.name);
        if (this.countriesArray.indexOf(r6.name) == (-1)) goto L9;
        this.ignoreOnTextChange = true;
        String r62 = this.countriesMap.get(r6.name);
        this.inputFields[1].setText(r62);
        String r63 = this.phoneFormatMap.get(r62);
        EditTextBoldCursor r0 = this.inputFields[2];
        if (r63 == null) goto L7;
        String r64 = r63.replace('X', Typography.ndash);
    L8:
        r0.setHintText(r64);
        this.ignoreOnTextChange = false;
        goto L9
    L7:
        r64 = null;
    L9:
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda61(this), 300);
        this.inputFields[2].requestFocus();
        EditTextBoldCursor r5 = this.inputFields[2];
        r5.setSelection(r5.length());
    }

    private /* synthetic */ boolean lambda$createPhoneInterface$29(View r2, MotionEvent r3) {
        if (getParentActivity() != null) goto L6;
        return false;
    L6:
        if (r3.getAction() != 1) goto L8;
        CountrySelectActivity r22 = new CountrySelectActivity(false);
        r22.setCountrySelectActivityDelegate(new PassportActivity$$ExternalSyntheticLambda39(this));
        presentFragment(r22);
    L8:
        return true;
    }

    private /* synthetic */ boolean lambda$createPhoneInterface$30(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L7;
        this.inputFields[2].requestFocus();
        return true;
    L7:
        if (r2 != 6) goto L10;
        this.doneItem.callOnClick();
        return true;
    L10:
        return false;
    }

    private /* synthetic */ boolean lambda$createPhoneInterface$31(View r2, int r3, KeyEvent r4) {
        if (r3 == 67) goto L5;
        return false;
    L5:
        if (this.inputFields[2].length() != 0) goto L10;
        this.inputFields[1].requestFocus();
        EditTextBoldCursor r22 = this.inputFields[1];
        r22.setSelection(r22.length());
        this.inputFields[1].dispatchKeyEvent(r4);
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createRequestInterface$14(TLRPC.TL_error r4) {
        if (r4 != null) goto L6;
        this.ignoreOnFailure = true;
        callCallback(true);
        finishFragment();
        return;
    L6:
        showEditDoneProgress(false, false);
        if ("APP_VERSION_OUTDATED".equals(r4.text) == false) goto L10;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L10:
        showAlertWithText(LocaleController.getString(R.string.AppName), r4.text);
    }

    private /* synthetic */ void lambda$createRequestInterface$15(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda66(this, r2));
    }

    private /* synthetic */ void lambda$createRequestInterface$16(View r20) {
        ArrayList r2 = new ArrayList();
        int r3 = this.currentForm.required_types.size();
        int r5 = 0;
    L3:
        int r7 = 2;
        if (r5 >= r3) goto L38;
        TLRPC.SecureRequiredType r8 = this.currentForm.required_types.get(r5);
        if ((r8 instanceof TLRPC.TL_secureRequiredType) == false) goto L9;
        TLRPC.TL_secureRequiredType r82 = (TLRPC.TL_secureRequiredType) r8;
    L26:
        TLRPC.TL_secureValue r6 = getValueByType(r82, true);
        if (r6 == null) goto L28;
        HashMap<String, String> r10 = this.errorsMap.get(getNameForType(r82.type));
        if (r10 != null) goto L33;
    L36:
        r2.add(new C1ValueToSend(this, r6, r82.selfie_required, r82.translation_required));
    L37:
        r5 = r5 + 1;
        goto L3
    L33:
        if (r10.isEmpty() == true) goto L36;
        getViewByType(r82).performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(getViewByType(r82));
        return;
    L28:
        getViewByType(r82).performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(getViewByType(r82));
        return;
    L9:
        if ((r8 instanceof TLRPC.TL_secureRequiredTypeOneOf) == false) goto L37;
        TLRPC.TL_secureRequiredTypeOneOf r83 = (TLRPC.TL_secureRequiredTypeOneOf) r8;
        if (r83.types.isEmpty() == true) goto L37;
        TLRPC.SecureRequiredType r9 = r83.types.get(0);
        if ((r9 instanceof TLRPC.TL_secureRequiredType) == false) goto L37;
        TLRPC.TL_secureRequiredType r92 = (TLRPC.TL_secureRequiredType) r9;
        int r102 = r83.types.size();
        int r11 = 0;
    L17:
        if (r11 >= r102) goto L25;
        TLRPC.SecureRequiredType r12 = r83.types.get(r11);
        if ((r12 instanceof TLRPC.TL_secureRequiredType) == false) goto L24;
        TLRPC.TL_secureRequiredType r122 = (TLRPC.TL_secureRequiredType) r12;
        if (getValueByType(r122, true) == null) goto L24;
        r82 = r122;
    L24:
        r11 = r11 + 1;
        goto L17
    L25:
        r82 = r92;
        goto L26
    L38:
        showEditDoneProgress(false, true);
        TL_account.acceptAuthorization r32 = new TL_account.acceptAuthorization();
        r32.bot_id = this.currentBotId;
        r32.scope = this.currentScope;
        r32.public_key = this.currentPublicKey;
        JSONObject r52 = new JSONObject();
        int r84 = r2.size();
        int r93 = 0;
    L39:
        if (r93 >= r84) goto L108;
        C1ValueToSend r103 = (C1ValueToSend) r2.get(r93);
        TLRPC.TL_secureValue r112 = r103.value;
        JSONObject r123 = new JSONObject();
        TLRPC.SecurePlainData r13 = r112.plain_data;
        if (r13 != null) goto L43;
        JSONObject r132 = new JSONObject();     // Catch: Exception -> L127
        TLRPC.TL_secureData r14 = r112.data;     // Catch: Exception -> L127
        if (r14 == null) goto L55;
        byte[] r62 = decryptValueSecret(r14.secret, r14.data_hash);     // Catch: Exception -> L127
        r123.put("data_hash", Base64.encodeToString(r112.data.data_hash, r7));     // Catch: Exception -> L127
        r123.put("secret", Base64.encodeToString(r62, r7));     // Catch: Exception -> L127
        r132.put("data", r123);     // Catch: Exception -> L127
    L55:
        if (r112.files.isEmpty() == false) goto L143;
        ArrayList r16 = r2;
        int r17 = r84;
        int r18 = r93;
    L75:
        TLRPC.SecureFile r22 = r112.front_side;     // Catch: Exception -> L123
        if ((r22 instanceof TLRPC.TL_secureFile) == false) goto L81;
        TLRPC.TL_secureFile r23 = (TLRPC.TL_secureFile) r22;     // Catch: Exception -> L123
        byte[] r4 = decryptValueSecret(r23.secret, r23.file_hash);     // Catch: Exception -> L123
        JSONObject r72 = new JSONObject();     // Catch: Exception -> L123
        r72.put("file_hash", Base64.encodeToString(r23.file_hash, 2));     // Catch: Exception -> L68
        r72.put("secret", Base64.encodeToString(r4, 2));     // Catch: Exception -> L123
        r132.put("front_side", r72);     // Catch: Exception -> L123
    L81:
        TLRPC.SecureFile r24 = r112.reverse_side;     // Catch: Exception -> L123
        if ((r24 instanceof TLRPC.TL_secureFile) == false) goto L88;
        TLRPC.TL_secureFile r25 = (TLRPC.TL_secureFile) r24;     // Catch: Exception -> L123
        byte[] r42 = decryptValueSecret(r25.secret, r25.file_hash);     // Catch: Exception -> L123
        JSONObject r73 = new JSONObject();     // Catch: Exception -> L123
        r73.put("file_hash", Base64.encodeToString(r25.file_hash, 2));     // Catch: Exception -> L68
        r73.put("secret", Base64.encodeToString(r42, 2));     // Catch: Exception -> L123
        r132.put("reverse_side", r73);     // Catch: Exception -> L123
    L88:
        if (r103.selfie_required == false) goto L96;
        TLRPC.SecureFile r26 = r112.selfie;     // Catch: Exception -> L123
        if ((r26 instanceof TLRPC.TL_secureFile) == false) goto L96;
        TLRPC.TL_secureFile r27 = (TLRPC.TL_secureFile) r26;     // Catch: Exception -> L123
        byte[] r43 = decryptValueSecret(r27.secret, r27.file_hash);     // Catch: Exception -> L123
        JSONObject r74 = new JSONObject();     // Catch: Exception -> L123
        r74.put("file_hash", Base64.encodeToString(r27.file_hash, 2));     // Catch: Exception -> L68
        r74.put("secret", Base64.encodeToString(r43, 2));     // Catch: Exception -> L123
        r132.put("selfie", r74);     // Catch: Exception -> L123
    L96:
        if (r103.translation_required == true) goto L98;
    L105:
        int r124 = 2;
    L106:
        r52.put(getNameForType(r112.type), r132);     // Catch: Exception -> L124
    L107:
        TLRPC.TL_secureValueHash r28 = new TLRPC.TL_secureValueHash();
        r28.type = r112.type;
        r28.hash = r112.hash;
        r32.value_hashes.add(r28);
        r93 = r18 + 1;
        r7 = r124;
        r2 = r16;
        r84 = r17;
        goto L39
    L98:
        if (r112.translation.isEmpty() == true) goto L105;
        JSONArray r29 = new JSONArray();     // Catch: Exception -> L123
        int r44 = r112.translation.size();     // Catch: Exception -> L123
        int r75 = 0;
    L100:
        if (r75 >= r44) goto L104;
        TLRPC.TL_secureFile r85 = (TLRPC.TL_secureFile) r112.translation.get(r75);     // Catch: Exception -> L123
        byte[] r94 = decryptValueSecret(r85.secret, r85.file_hash);     // Catch: Exception -> L123
        JSONObject r104 = new JSONObject();     // Catch: Exception -> L123
        r124 = 2;
        r104.put("file_hash", Base64.encodeToString(r85.file_hash, 2));     // Catch: Exception -> L124
        r104.put("secret", Base64.encodeToString(r94, 2));     // Catch: Exception -> L124
        r29.put(r104);     // Catch: Exception -> L124
        r75 = r75 + 1;     // Catch: Exception -> L124
    L104:
        r124 = 2;
        r132.put("translation", r29);     // Catch: Exception -> L124
        goto L106
    L143:
        JSONArray r45 = new JSONArray();     // Catch: Exception -> L73
        int r125 = r112.files.size();     // Catch: Exception -> L73
        int r142 = 0;
    L58:
        if (r142 >= r125) goto L72;
        TLRPC.TL_secureFile r76 = (TLRPC.TL_secureFile) r112.files.get(r142);     // Catch: Exception -> L71
        r16 = r2;
        r17 = r84;
        byte[] r210 = decryptValueSecret(r76.secret, r76.file_hash);     // Catch: Exception -> L125
        JSONObject r86 = new JSONObject();     // Catch: Exception -> L125
        r18 = r93;
        r86.put("file_hash", Base64.encodeToString(r76.file_hash, 2));     // Catch: Exception -> L68
        r86.put("secret", Base64.encodeToString(r210, 2));     // Catch: Exception -> L123
        r45.put(r86);     // Catch: Exception -> L123
        r142 = r142 + 1;     // Catch: Exception -> L123
        r2 = r16;
        r84 = r17;
        r93 = r18;
    L67:
        r124 = 2;
    L68:
        r124 = 2;
    L69:
        r18 = r93;
    L70:
        r17 = r84;
    L71:
        r16 = r2;
        goto L70
    L72:
        r16 = r2;
        r17 = r84;
        r18 = r93;
        r132.put("files", r45);     // Catch: Exception -> L123
    L73:
        r16 = r2;
        r17 = r84;
        r18 = r93;
        r124 = r7;
    L45:
        r16 = r2;
        r124 = r7;
        r17 = r84;
        r18 = r93;
        goto L107
    L43:
        if ((r13 instanceof TLRPC.TL_securePlainEmail) == false) goto L47;
        TLRPC.TL_securePlainEmail r133 = (TLRPC.TL_securePlainEmail) r13;
        goto L45
    L47:
        if ((r13 instanceof TLRPC.TL_securePlainPhone) == false) goto L45;
        TLRPC.TL_securePlainPhone r134 = (TLRPC.TL_securePlainPhone) r13;
        goto L45
    L108:
        JSONObject r211 = new JSONObject();
        r211.put("secure_data", r52);     // Catch: Exception -> L128
    L110:
        Object r46 = this.currentPayload;
        if (r46 != null) goto L149;
    L113:
        Object r47 = this.currentNonce;
        if (r47 != null) goto L137;
    L116:
        EncryptionResult r212 = encryptData(AndroidUtilities.getStringBytes(r211.toString()));
        TLRPC.TL_secureCredentialsEncrypted r48 = new TLRPC.TL_secureCredentialsEncrypted();
        r32.credentials = r48;
        r48.hash = r212.fileHash;
        r48.data = r212.encryptedData;
        RSAPublicKey r0 = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(this.currentPublicKey.replaceAll("\\n", _UrlKt.FRAGMENT_ENCODE_SET).replace("-----BEGIN PUBLIC KEY-----", _UrlKt.FRAGMENT_ENCODE_SET).replace("-----END PUBLIC KEY-----", _UrlKt.FRAGMENT_ENCODE_SET), 0)));     // Catch: Exception -> L119
        Cipher r49 = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");     // Catch: Exception -> L119
        r49.init(1, r0);     // Catch: Exception -> L119
        r32.credentials.secret = r49.doFinal(r212.decrypyedFileSecret);     // Catch: Exception -> L119
    L121:
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new PassportActivity$$ExternalSyntheticLambda49(this)), this.classGuid);
        return;
    L119:
        e = move-exception;
        FileLog.e(e);
        goto L121
    L137:
        r211.put("nonce", r47);     // Catch: Exception -> L130
        goto L116
    L149:
        r211.put("payload", r46);     // Catch: Exception -> L129
        goto L113
    }

    private /* synthetic */ void lambda$deleteValueInternal$59(TLRPC.TL_error r11, ErrorRunnable r12, boolean r13, TLRPC.TL_secureRequiredType r14, TLRPC.TL_secureRequiredType r15, boolean r16, ArrayList r17, Runnable r18) {
        String r6 = null;
        if (r11 == null) goto L8;
        if (r12 == null) goto L6;
        r12.onError(r11.text, null);
    L6:
        showAlertWithText(LocaleController.getString(R.string.AppName), r11.text);
        return;
    L8:
        if (r13 == false) goto L12;
        if (r14 == null) goto L11;
        removeValue(r14);
    L15:
        int r7 = 0;
        if (this.currentActivityType != 8) goto L23;
        TextDetailSecureCell r1 = this.typesViews.remove(r15);
        if (r1 == null) goto L22;
        this.linearLayout2.removeView(r1);
        View r19 = this.linearLayout2.getChildAt(r1.getChildCount() - 6);
        if ((r19 instanceof TextDetailSecureCell) == false) goto L22;
        ((TextDetailSecureCell) r19).setNeedDivider(false);
    L22:
        updateManageVisibility();
    L53:
        if (r18 == null) goto L58;
        r18.run();
        return;
    L58:
        return;
    L23:
        if (r14 == null) goto L39;
        if (r17 == null) goto L39;
        if (r17.size() <= 1) goto L39;
        int r110 = r17.size();
        int r2 = 0;
    L28:
        if (r2 >= r110) goto L35;
        TLRPC.TL_secureRequiredType r8 = (TLRPC.TL_secureRequiredType) r17.get(r2);
        TLRPC.TL_secureValue r9 = getValueByType(r8, false);
        if (r9 != null) goto L31;
        r2 = r2 + 1;
        goto L28
    L31:
        TLRPC.TL_secureData r111 = r9.data;
        if (r111 == null) goto L36;
        String r112 = decryptData(r111.data, decryptValueSecret(r111.secret, r111.data_hash), r9.data.data_hash);
    L37:
        if (r8 != null) goto L40;
        r8 = (TLRPC.TL_secureRequiredType) r17.get(0);
    L40:
        if (r16 == false) goto L44;
        if (r17 == null) goto L43;
        r7 = r17.size();
    L43:
        setTypeValue(r15, null, null, r8, r112, r13, r7);
        goto L53
    L44:
        String r5 = r112;
        TLRPC.TL_secureRequiredType r4 = r8;
        TLRPC.TL_secureValue r22 = getValueByType(r15, false);
        if (r22 == null) goto L49;
        TLRPC.TL_secureData r3 = r22.data;
        if (r3 == null) goto L49;
        r6 = decryptData(r3.data, decryptValueSecret(r3.secret, r3.data_hash), r22.data.data_hash);
    L49:
        String r32 = r6;
        if (r17 == null) goto L52;
        r7 = r17.size();
    L52:
        setTypeValue(r15, null, r32, r4, r5, r13, r7);
    L36:
        r112 = null;
        goto L37
    L35:
        r8 = r14;
    L39:
        r8 = r14;
        r112 = null;
        goto L40
    L11:
        removeValue(r15);
        goto L15
    L12:
        if (r16 == false) goto L14;
        removeValue(r15);
    L14:
        removeValue(r14);
        goto L15
    }

    private /* synthetic */ void lambda$deleteValueInternal$60(ErrorRunnable r1, boolean r2, TLRPC.TL_secureRequiredType r3, TLRPC.TL_secureRequiredType r4, boolean r5, ArrayList r6, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda74(this, r9, r1, r2, r3, r4, r5, r6, r7));
    }

    private /* synthetic */ void lambda$loadPasswordInfo$3(TLObject r3) {
        if (r3 == null) goto L14;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r32, false) == true) goto L7;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L7:
        TwoStepVerificationActivity.initPasswordNewAlgo(this.currentPassword);
        updatePasswordInterface();
        if (this.inputFieldContainers[0].getVisibility() != 0) goto L11;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
    L11:
        if (this.usingSavedPassword != 1) goto L15;
        onPasswordDone(true);
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$loadPasswordInfo$4(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda60(this, r1));
    }

    private /* synthetic */ void lambda$onPasswordDone$13(boolean r10, String r11) {
        TL_account.getPasswordSettings r4 = new TL_account.getPasswordSettings();
        if (r10 == false) goto L7;
        byte[] r0 = this.savedPasswordHash;
    L5:
        byte[] r3 = r0;
    L10:
        AnonymousClass8 r02 = new AnonymousClass8(this, r10, r3, r4, r11);
        TL_account.Password r9 = this.currentPassword;
        TLRPC.PasswordKdfAlgo r102 = r9.current_algo;
        if ((r102 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L18;
        TLRPC.TL_inputCheckPasswordSRP r92 = SRPHelper.startCheck(r3, r9.srp_id, r9.srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r102);
        r4.password = r92;
        if (r92 != null) goto L16;
        TLRPC.TL_error r93 = new TLRPC.TL_error();
        r93.text = "ALGO_INVALID";
        r02.run(null, r93);
        return;
    L16:
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, r02, 10), this.classGuid);
        return;
    L18:
        TLRPC.TL_error r94 = new TLRPC.TL_error();
        r94.text = "PASSWORD_HASH_INVALID";
        r02.run(null, r94);
        return;
    L7:
        if ((this.currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L9;
        r0 = SRPHelper.getX(AndroidUtilities.getStringBytes(r11), (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) this.currentPassword.current_algo);
        goto L5
    L9:
        r3 = null;
        goto L10
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultFragment$68(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        getParentActivity().startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onResume$2() {
        ViewGroup[] r0 = this.inputFieldContainers;
        if (r0 == null) goto L10;
        ViewGroup r02 = r0[0];
        if (r02 != null) goto L7;
        return;
    L7:
        if (r02.getVisibility() != 0) goto L12;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$onTransitionAnimationEnd$67() {
        presentFragment(this.presentAfterAnimation, true);
        this.presentAfterAnimation = null;
    }

    private /* synthetic */ void lambda$openAddDocumentAlert$23(ArrayList r2, DialogInterface r3, int r4) {
        TLRPC.TL_secureRequiredType r32 = null;
        TLRPC.TL_secureRequiredType r0 = new TLRPC.TL_secureRequiredType();     // Catch: Exception -> L6
        r0.type = (TLRPC.SecureValueType) ((Class) r2.get(r4)).newInstance();     // Catch: Exception -> L20
    L7:
        boolean r42 = true;
        if (isPersonalDocument(r0.type) == false) goto L12;
        r0.selfie_required = true;
        r0.translation_required = true;
        TLRPC.TL_secureRequiredType r22 = new TLRPC.TL_secureRequiredType();
        r22.type = new TLRPC.TL_secureValueTypePersonalDetails();
    L10:
        r32 = r0;
        r0 = r22;
    L14:
        ArrayList<TLRPC.TL_secureRequiredType> r23 = new ArrayList();
        if (r32 != null) goto L18;
        r42 = false;
    L18:
        openTypeActivity(r0, r32, r23, r42);
        return;
    L12:
        if (isAddressDocument(r0.type) == false) goto L14;
        r22 = new TLRPC.TL_secureRequiredType();
        r22.type = new TLRPC.TL_secureValueTypeAddress();
    L6:
        r0 = null;
        goto L7
    }

    private /* synthetic */ void lambda$processSelectedFiles$70(SecureDocument r6, int r7) {
        int r0 = this.uploadingFileType;
        if (r0 != 1) goto L11;
        SecureDocument r02 = this.selfieDocument;
        if (r02 == null) goto L35;
        SecureDocumentCell r03 = this.documentsCells.remove(r02);
        if (r03 == null) goto L9;
        this.selfieLayout.removeView(r03);
    L9:
        this.selfieDocument = null;
    L35:
        this.uploadingDocuments.put(r6.path, r6);
        this.doneItem.setEnabled(false);
        this.doneItem.setAlpha(0.5f);
        FileLoader.getInstance(this.currentAccount).uploadFile(r6.path, false, true, 16777216);
        addDocumentView(r6, r7);
        updateUploadText(r7);
        return;
    L11:
        if (r0 != 4) goto L16;
        if (this.translationDocuments.size() < 20) goto L35;
        return;
    L16:
        if (r0 != 2) goto L24;
        SecureDocument r04 = this.frontDocument;
        if (r04 == null) goto L35;
        SecureDocumentCell r05 = this.documentsCells.remove(r04);
        if (r05 == null) goto L22;
        this.frontLayout.removeView(r05);
    L22:
        this.frontDocument = null;
        goto L35
    L24:
        if (r0 != 3) goto L31;
        SecureDocument r06 = this.reverseDocument;
        if (r06 == null) goto L35;
        SecureDocumentCell r07 = this.documentsCells.remove(r06);
        if (r07 == null) goto L30;
        this.reverseLayout.removeView(r07);
    L30:
        this.reverseDocument = null;
        goto L35
    L31:
        if (r0 != 0) goto L35;
        if (this.documents.size() < 20) goto L35;
    }

    private /* synthetic */ void lambda$processSelectedFiles$71(MrzRecognizer.Result r10) {
        int r0 = r10.type;
        if (r0 == 2) goto L5;
        if (r0 == 1) goto L14;
        if (r0 == 3) goto L23;
        if (r0 == 4) goto L32;
    L40:
        if (TextUtils.isEmpty(r10.firstName) == true) goto L43;
        this.inputFields[0].setText(r10.firstName);
    L43:
        if (TextUtils.isEmpty(r10.middleName) == true) goto L46;
        this.inputFields[1].setText(r10.middleName);
    L46:
        if (TextUtils.isEmpty(r10.lastName) == true) goto L49;
        this.inputFields[2].setText(r10.lastName);
    L49:
        if (TextUtils.isEmpty(r10.number) == true) goto L51;
        this.inputFields[7].setText(r10.number);
    L51:
        int r02 = r10.gender;
        if (r02 == 0) goto L59;
        if (r02 == 1) goto L57;
        if (r02 != 2) goto L59;
        this.currentGender = "female";
        this.inputFields[4].setText(LocaleController.getString(R.string.PassportFemale));
        goto L59
    L57:
        this.currentGender = "male";
        this.inputFields[4].setText(LocaleController.getString(R.string.PassportMale));
    L59:
        if (TextUtils.isEmpty(r10.nationality) == true) goto L64;
        String r03 = r10.nationality;
        this.currentCitizeship = r03;
        String r04 = this.languageMap.get(r03);
        if (r04 == null) goto L64;
        this.inputFields[5].setText(r04);
    L64:
        if (TextUtils.isEmpty(r10.issuingCountry) == true) goto L68;
        String r05 = r10.issuingCountry;
        this.currentResidence = r05;
        String r06 = this.languageMap.get(r05);
        if (r06 == null) goto L68;
        this.inputFields[6].setText(r06);
    L68:
        int r07 = r10.birthDay;
        if (r07 > 0) goto L71;
    L75:
        int r08 = r10.expiryDay;
        if (r08 <= 0) goto L83;
        int r6 = r10.expiryMonth;
        if (r6 <= 0) goto L83;
        int r7 = r10.expiryYear;
        if (r7 <= 0) goto L83;
        int[] r8 = this.currentExpireDate;
        r8[0] = r7;
        r8[1] = r6;
        r8[2] = r08;
        this.inputFields[8].setText(String.format(Locale.US, "%02d.%02d.%d", new Object[]{Integer.valueOf(r08), Integer.valueOf(r10.expiryMonth), Integer.valueOf(r10.expiryYear)}));
        return;
    L83:
        int[] r102 = this.currentExpireDate;
        r102[2] = 0;
        r102[1] = 0;
        r102[0] = 0;
        this.inputFields[8].setText(LocaleController.getString(R.string.PassportNoExpireDate));
        return;
    L71:
        if (r10.birthMonth <= 0) goto L75;
        if (r10.birthYear <= 0) goto L75;
        this.inputFields[3].setText(String.format(Locale.US, "%02d.%02d.%d", new Object[]{Integer.valueOf(r07), Integer.valueOf(r10.birthMonth), Integer.valueOf(r10.birthYear)}));
        goto L75
    L32:
        if ((this.currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) == true) goto L40;
        int r09 = this.availableDocumentTypes.size();
        int r62 = 0;
    L34:
        if (r62 >= r09) goto L40;
        TLRPC.TL_secureRequiredType r72 = this.availableDocumentTypes.get(r62);
        if ((r72.type instanceof TLRPC.TL_secureValueTypeDriverLicense) == true) goto L37;
        r62 = r62 + 1;
        goto L34
    L37:
        this.currentDocumentsType = r72;
        updateInterfaceStringsForDocumentType();
        goto L40
    L23:
        if ((this.currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) == true) goto L40;
        int r010 = this.availableDocumentTypes.size();
        int r63 = 0;
    L25:
        if (r63 >= r010) goto L40;
        TLRPC.TL_secureRequiredType r73 = this.availableDocumentTypes.get(r63);
        if ((r73.type instanceof TLRPC.TL_secureValueTypeInternalPassport) == true) goto L28;
        r63 = r63 + 1;
        goto L25
    L28:
        this.currentDocumentsType = r73;
        updateInterfaceStringsForDocumentType();
        goto L40
    L14:
        if ((this.currentDocumentsType.type instanceof TLRPC.TL_secureValueTypePassport) == true) goto L40;
        int r011 = this.availableDocumentTypes.size();
        int r64 = 0;
    L16:
        if (r64 >= r011) goto L40;
        TLRPC.TL_secureRequiredType r74 = this.availableDocumentTypes.get(r64);
        if ((r74.type instanceof TLRPC.TL_secureValueTypePassport) == true) goto L19;
        r64 = r64 + 1;
        goto L16
    L19:
        this.currentDocumentsType = r74;
        updateInterfaceStringsForDocumentType();
        goto L40
    L5:
        if ((this.currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) == true) goto L40;
        int r012 = this.availableDocumentTypes.size();
        int r65 = 0;
    L7:
        if (r65 >= r012) goto L40;
        TLRPC.TL_secureRequiredType r75 = this.availableDocumentTypes.get(r65);
        if ((r75.type instanceof TLRPC.TL_secureValueTypeIdentityCard) == true) goto L10;
        r65 = r65 + 1;
        goto L7
    L10:
        this.currentDocumentsType = r75;
        updateInterfaceStringsForDocumentType();
        goto L40
    }

    private /* synthetic */ void lambda$processSelectedFiles$72(ArrayList r19, int r20, boolean r21) {
        int r0 = this.uploadingFileType;
        if (r0 != 0) goto L5;
    L8:
        int r02 = 20;
    L9:
        int r4 = Math.min(r02, r19.size());
        int r6 = 0;
        boolean r7 = false;
    L10:
        if (r6 >= r4) goto L29;
        SendMessagesHelper.SendingMediaInfo r03 = (SendMessagesHelper.SendingMediaInfo) r19.get(r6);
        Bitmap r11 = ImageLoader.loadBitmap(r03.path, r03.uri, 2048.0f, 2048.0f, false);
        if (r11 == null) goto L28;
        TLRPC.PhotoSize r04 = ImageLoader.scaleAndSaveImage(r11, 2048.0f, 2048.0f, 89, false, 320, 320);
        if (r04 == null) goto L28;
        TLRPC.TL_secureFile r9 = new TLRPC.TL_secureFile();
        r9.dc_id = (int) r04.location.volume_id;
        r9.id = r0.local_id;
        r9.date = (int) (System.currentTimeMillis() / 1000);
        SecureDocument r05 = this.delegate.saveFile(r9);
        r05.type = r20;
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda58(this, r05, r20));
        if (r21 == false) goto L28;
        if (r7 == true) goto L28;
        MrzRecognizer.Result r06 = MrzRecognizer.recognize(r11, this.currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense);     // Catch: Throwable -> L26
        if (r06 == null) goto L28;
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda59(this, r06));     // Catch: Throwable -> L24
        r7 = true;
    L24:
        th = th;
        r7 = true;
    L27:
        FileLog.e(th);
    L26:
        th = th;
    L28:
        r6 = r6 + 1;
        goto L10
    L29:
        SharedConfig.saveConfig();
        return;
    L5:
        if (r0 == 4) goto L8;
        r02 = 1;
        goto L9
    }

    private /* synthetic */ void lambda$startPhoneVerification$65(TLRPC.TL_error r11, String r12, PassportActivityDelegate r13, TLObject r14, TL_account.sendVerifyPhoneCode r15) {
        if (r11 != null) goto L5;
        HashMap r8 = new HashMap();
        r8.put("phone", r12);
        PassportActivity r0 = new PassportActivity(7, this.currentForm, this.currentPassword, this.currentType, null, null, null, r8, null);
        r0.currentAccount = this.currentAccount;
        r0.saltedPassword = this.saltedPassword;
        r0.secureSecret = this.secureSecret;
        r0.delegate = r13;
        r0.currentPhoneVerification = (TLRPC.TL_auth_sentCode) r14;
        presentFragment(r0, true);
        return;
    L5:
        AlertsCreator.processError(this.currentAccount, r11, this, r15, new Object[]{r12});
    }

    private /* synthetic */ void lambda$startPhoneVerification$66(String r8, PassportActivityDelegate r9, TL_account.sendVerifyPhoneCode r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda63(this, r12, r8, r9, r11, r10));
    }

    private void loadPasswordInfo() {
        TL_account.getPassword r0 = new TL_account.getPassword();
        int r02 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new PassportActivity$$ExternalSyntheticLambda7(this));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r02, this.classGuid);
    }

    private void onFieldError(View r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(r3);
        scrollToField(r3);
    }

    private void onPasscodeError(boolean r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        this.inputFields[0].performHapticFeedback(3, 2);
        if (r5 == false) goto L8;
        this.inputFields[0].setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L8:
        AndroidUtilities.shakeView(this.inputFields[0]);
    }

    private void onPasswordDone(boolean r4) {
        if (r4 == false) goto L4;
        String r0 = null;
    L9:
        Utilities.globalQueue.postRunnable(new PassportActivity$$ExternalSyntheticLambda40(this, r4, r0));
        return;
    L4:
        r0 = this.inputFields[0].getText().toString();
        if (TextUtils.isEmpty(r0) == false) goto L8;
        onPasscodeError(false);
        return;
    L8:
        showEditDoneProgress(true, true);
        goto L9
    }

    private void openAddDocumentAlert() {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        if (hasNotValueForType(TLRPC.TL_secureValueTypePhone.class) == false) goto L6;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentPhone));
        r1.add(TLRPC.TL_secureValueTypePhone.class);
    L6:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeEmail.class) == false) goto L9;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentEmail));
        r1.add(TLRPC.TL_secureValueTypeEmail.class);
    L9:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePersonalDetails.class) == false) goto L12;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentIdentity));
        r1.add(TLRPC.TL_secureValueTypePersonalDetails.class);
    L12:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePassport.class) == false) goto L15;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentPassport));
        r1.add(TLRPC.TL_secureValueTypePassport.class);
    L15:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeInternalPassport.class) == false) goto L18;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentInternalPassport));
        r1.add(TLRPC.TL_secureValueTypeInternalPassport.class);
    L18:
        if (hasNotValueForType(TLRPC.TL_secureValueTypePassportRegistration.class) == false) goto L21;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentPassportRegistration));
        r1.add(TLRPC.TL_secureValueTypePassportRegistration.class);
    L21:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeTemporaryRegistration.class) == false) goto L24;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentTemporaryRegistration));
        r1.add(TLRPC.TL_secureValueTypeTemporaryRegistration.class);
    L24:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeIdentityCard.class) == false) goto L27;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentIdentityCard));
        r1.add(TLRPC.TL_secureValueTypeIdentityCard.class);
    L27:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeDriverLicense.class) == false) goto L30;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentDriverLicence));
        r1.add(TLRPC.TL_secureValueTypeDriverLicense.class);
    L30:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeAddress.class) == false) goto L33;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentAddress));
        r1.add(TLRPC.TL_secureValueTypeAddress.class);
    L33:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeUtilityBill.class) == false) goto L36;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentUtilityBill));
        r1.add(TLRPC.TL_secureValueTypeUtilityBill.class);
    L36:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeBankStatement.class) == false) goto L39;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentBankStatement));
        r1.add(TLRPC.TL_secureValueTypeBankStatement.class);
    L39:
        if (hasNotValueForType(TLRPC.TL_secureValueTypeRentalAgreement.class) == false) goto L42;
        r0.add(LocaleController.getString(R.string.ActionBotDocumentRentalAgreement));
        r1.add(TLRPC.TL_secureValueTypeRentalAgreement.class);
    L42:
        if (getParentActivity() != null) goto L44;
        return;
    L44:
        if (r0.isEmpty() == true) goto L49;
        AlertDialog.Builder r2 = new AlertDialog.Builder(getParentActivity());
        r2.setTitle(LocaleController.getString(R.string.PassportNoDocumentsAdd));
        r2.setItems((CharSequence[]) r0.toArray(new CharSequence[0]), new PassportActivity$$ExternalSyntheticLambda41(this, r1));
        showDialog(r2.create());
        return;
    }

    private void openAttachMenu() {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (this.uploadingFileType == 0) goto L8;
    L11:
        createChatAttachView();
        ChatAttachAlert r0 = this.chatAttachAlert;
        boolean r3 = true;
        if (this.uploadingFileType == 1) goto L15;
        r3 = false;
    L15:
        r0.setOpenWithFrontFaceCamera(r3);
        this.chatAttachAlert.setMaxSelectedPhotos(getMaxSelectedDocuments(), false);
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.init();
        showDialog(this.chatAttachAlert);
        return;
    L8:
        if (this.documents.size() < 20) goto L11;
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("PassportUploadMaxReached", R.string.PassportUploadMaxReached, new Object[]{LocaleController.formatPluralString("Files", 20, new Object[0])}));
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    private void openTypeActivity(org.telegram.tgnet.TLRPC.TL_secureRequiredType r19, org.telegram.tgnet.TLRPC.TL_secureRequiredType r20, java.util.ArrayList<org.telegram.tgnet.TLRPC.TL_secureRequiredType> r21, boolean r22) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONST (r1v0 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (0 ??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[DECLARE_VAR] in method: org.telegram.ui.PassportActivity.openTypeActivity(org.telegram.tgnet.TLRPC$TL_secureRequiredType, org.telegram.tgnet.TLRPC$TL_secureRequiredType, java.util.ArrayList<org.telegram.tgnet.TLRPC$TL_secureRequiredType>, boolean):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            r18 = this;
            r0 = r18
            r5 = r19
            r7 = r20
            r12 = r22
            r1 = 0
            if (r21 == 0) goto L11
            int r2 = r21.size()
            r13 = r2
            goto L12
        L11:
            r13 = r1
        L12:
            org.telegram.tgnet.TLRPC$SecureValueType r14 = r5.type
            r2 = 0
            if (r7 == 0) goto L1a
            org.telegram.tgnet.TLRPC$SecureValueType r3 = r7.type
            goto L1b
        L1a:
            r3 = r2
        L1b:
            boolean r4 = r14 instanceof org.telegram.tgnet.TLRPC.TL_secureValueTypePersonalDetails
            r6 = -1
            if (r4 == 0) goto L22
            r4 = 1
            goto L35
        L22:
            boolean r4 = r14 instanceof org.telegram.tgnet.TLRPC.TL_secureValueTypeAddress
            if (r4 == 0) goto L28
            r4 = 2
            goto L35
        L28:
            boolean r4 = r14 instanceof org.telegram.tgnet.TLRPC.TL_secureValueTypePhone
            if (r4 == 0) goto L2e
            r4 = 3
            goto L35
        L2e:
            boolean r4 = r14 instanceof org.telegram.tgnet.TLRPC.TL_secureValueTypeEmail
            if (r4 == 0) goto L34
            r4 = 4
            goto L35
        L34:
            r4 = r6
        L35:
            if (r4 == r6) goto Lb8
            if (r12 != 0) goto L46
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.String>> r6 = r0.errorsMap
            java.lang.String r8 = r0.getNameForType(r14)
            java.lang.Object r6 = r6.get(r8)
            java.util.HashMap r6 = (java.util.HashMap) r6
            goto L47
        L46:
            r6 = r2
        L47:
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.String>> r8 = r0.errorsMap
            java.lang.String r3 = r0.getNameForType(r3)
            java.lang.Object r3 = r8.get(r3)
            java.util.HashMap r3 = (java.util.HashMap) r3
            r8 = r6
            org.telegram.tgnet.TLRPC$TL_secureValue r6 = r0.getValueByType(r5, r1)
            org.telegram.tgnet.TLRPC$TL_secureValue r1 = r0.getValueByType(r7, r1)
            r9 = r8
            r8 = r1
            org.telegram.ui.PassportActivity r1 = new org.telegram.ui.PassportActivity
            r10 = r3
            org.telegram.tgnet.tl.TL_account$authorizationForm r3 = r0.currentForm
            r16 = r2
            r2 = r4
            org.telegram.tgnet.tl.TL_account$Password r4 = r0.currentPassword
            java.util.HashMap<org.telegram.tgnet.TLRPC$TL_secureRequiredType, java.util.HashMap<java.lang.String, java.lang.String>> r15 = r0.typesValues
            java.lang.Object r15 = r15.get(r5)
            java.util.HashMap r15 = (java.util.HashMap) r15
            if (r7 == 0) goto L84
            r17 = r1
            java.util.HashMap<org.telegram.tgnet.TLRPC$TL_secureRequiredType, java.util.HashMap<java.lang.String, java.lang.String>> r1 = r0.typesValues
            java.lang.Object r1 = r1.get(r7)
            java.util.HashMap r1 = (java.util.HashMap) r1
            r11 = r15
            r15 = r9
            r9 = r11
            r11 = r10
            r10 = r1
            r1 = r17
            goto L8a
        L84:
            r11 = r15
            r15 = r9
            r9 = r11
            r11 = r10
            r10 = r16
        L8a:
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            org.telegram.ui.PassportActivity$20 r3 = new org.telegram.ui.PassportActivity$20
            r3.<init>(r0, r14, r12, r13)
            r1.delegate = r3
            int r3 = r0.currentAccount
            r1.currentAccount = r3
            byte[] r3 = r0.saltedPassword
            r1.saltedPassword = r3
            byte[] r3 = r0.secureSecret
            r1.secureSecret = r3
            long r3 = r0.currentBotId
            r1.currentBotId = r3
            r1.fieldsErrors = r15
            r1.documentOnly = r12
            r1.documentsErrors = r11
            r11 = r21
            r1.availableDocumentTypes = r11
            r3 = 4
            if (r2 != r3) goto Lb5
            java.lang.String r2 = r0.currentEmail
            r1.currentEmail = r2
        Lb5:
            r0.presentFragment(r1)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.PassportActivity.openTypeActivity(org.telegram.tgnet.TLRPC$TL_secureRequiredType, org.telegram.tgnet.TLRPC$TL_secureRequiredType, java.util.ArrayList, boolean):void");
    }

    private void processSelectedAttach(int r6) {
        if (r6 == 0) goto L4;
        return;
    L4:
        if (getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L15;
        getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 19);
        return;
    L15:
        Intent r62 = new Intent("android.media.action.IMAGE_CAPTURE");     // Catch: Exception -> L12
        File r0 = AndroidUtilities.generatePicturePath();     // Catch: Exception -> L12
        if (r0 == null) goto L10;
        r62.putExtra("output", FileProvider.getUriForFile(getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r0));     // Catch: Exception -> L12
        r62.addFlags(2);     // Catch: Exception -> L12
        r62.addFlags(1);     // Catch: Exception -> L12
        this.currentPicturePath = r0.getAbsolutePath();     // Catch: Exception -> L12
    L10:
        startActivityForResult(r62, 0);     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    private void processSelectedFiles(ArrayList<SendMessagesHelper.SendingMediaInfo> r7) {
        if (r7.isEmpty() == false) goto L5;
        return;
    L5:
        int r0 = this.uploadingFileType;
        boolean r1 = true;
        boolean r2 = false;
        if (r0 != 1) goto L8;
    L28:
        Utilities.globalQueue.postRunnable(new PassportActivity$$ExternalSyntheticLambda26(this, r7, this.uploadingFileType, r2));
        return;
    L8:
        if (r0 == 4) goto L28;
        if ((this.currentType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L28;
        int r02 = 0;
    L13:
        EditTextBoldCursor[] r4 = this.inputFields;
        if (r02 >= r4.length) goto L27;
        if (r02 == 5) goto L26;
        if (r02 == 8) goto L26;
        if (r02 == 4) goto L26;
        if (r02 == 6) goto L26;
        if (r4[r02].length() <= 0) goto L26;
        r1 = false;
    L26:
        r02 = r02 + 1;
    L27:
        r2 = r1;
        goto L28
    }

    private TLRPC.TL_secureValue removeValue(TLRPC.TL_secureRequiredType r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = this.currentForm.values.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.TL_secureValue r3 = this.currentForm.values.get(r2);
        if (r6.type.getClass() == r3.type.getClass()) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return this.currentForm.values.remove(r2);
    L12:
        return null;
    }

    private void scrollToField(View r3) {
    L2:
        if (r3 == null) goto L6;
        if (this.linearLayout2.indexOfChild(r3) >= 0) goto L6;
        r3 = (View) r3.getParent();
    L6:
        if (r3 == null) goto L11;
        this.scrollView.smoothScrollTo(0, r3.getTop() - ((this.scrollView.getMeasuredHeight() - r3.getMeasuredHeight()) / 2));
        return;
    }

    private void setFieldValues(HashMap<String, String> r7, EditTextBoldCursor r8, String r9) {
        String r72 = r7.get(r9);
        if (r72 == null) goto L43;
        r9.getClass();
        char r5 = 65535;
        switch(r9.hashCode()) {
            case -2006252145: goto L20;
            case -1249512767: goto L16;
            case 475919162: goto L12;
            case 1481071862: goto L8;
            default: goto L23;
        };
    L23:
        switch(r5) {
            case 0: goto L40;
            case 1: goto L35;
            case 2: goto L29;
            case 3: goto L25;
            default: goto L24;
        };
    L24:
        r8.setText(r72);
        goto L43
    L25:
        this.currentCitizeship = r72;
        CharSequence r73 = (String) this.languageMap.get(r72);
        if (r73 == null) goto L43;
        r8.setText(r73);
        goto L43
    L40:
        this.currentResidence = r72;
        CharSequence r74 = (String) this.languageMap.get(r72);
        if (r74 == null) goto L43;
        r8.setText(r74);
        goto L43
    L29:
        if (TextUtils.isEmpty(r72) == true) goto L33;
        String[] r0 = r72.split("\\.");
        if (r0.length != 3) goto L33;
        this.currentExpireDate[0] = Utilities.parseInt(r0[2]);
        this.currentExpireDate[1] = Utilities.parseInt(r0[1]);
        this.currentExpireDate[2] = Utilities.parseInt(r0[0]);
        r8.setText(r72);
    L33:
        int[] r75 = this.currentExpireDate;
        r75[2] = 0;
        r75[1] = 0;
        r75[0] = 0;
        r8.setText(LocaleController.getString(R.string.PassportNoExpireDate));
        goto L43
    L35:
        if ("male".equals(r72) == false) goto L38;
        this.currentGender = r72;
        r8.setText(LocaleController.getString(R.string.PassportMale));
        goto L43
    L38:
        if ("female".equals(r72) == false) goto L43;
        this.currentGender = r72;
        r8.setText(LocaleController.getString(R.string.PassportFemale));
        goto L43
    L8:
        if (r9.equals("country_code") == false) goto L23;
        r5 = 3;
        goto L23
    L12:
        if (r9.equals("expiry_date") == false) goto L23;
        r5 = 2;
        goto L23
    L16:
        if (r9.equals("gender") == false) goto L23;
        r5 = 1;
        goto L23
    L20:
        if (r9.equals("residence_country_code") == false) goto L23;
        r5 = 0;
    L43:
        HashMap<String, String> r76 = this.fieldsErrors;
        if (r76 == null) goto L49;
        CharSequence r77 = (String) r76.get(r9);
        if (r77 == null) goto L49;
        r8.setErrorText(r77);
        this.errorsValues.put(r9, r8.getText().toString());
        return;
    L49:
        HashMap<String, String> r78 = this.documentsErrors;
        if (r78 == null) goto L55;
        CharSequence r79 = (String) r78.get(r9);
        if (r79 == null) goto L56;
        r8.setErrorText(r79);
        this.errorsValues.put(r9, r8.getText().toString());
        return;
    L56:
        return;
    }

    private void setTypeValue(TLRPC.TL_secureRequiredType r31, String r32, String r33, TLRPC.TL_secureRequiredType r34, String r35, boolean r36, int r37) {
        String r8 = r33;
        TextDetailSecureCell r0 = this.typesViews.get(r31);
        if (r0 == null) goto L5;
    L13:
        TextDetailSecureCell r2 = r0;
        HashMap<String, String> r4 = this.typesValues.get(r31);
        if (r34 == null) goto L16;
        HashMap<String, String> r6 = this.typesValues.get(r34);
    L17:
        TLRPC.TL_secureValue r15 = getValueByType(r31, true);
        TLRPC.TL_secureValue r16 = getValueByType(r34, true);
        if (r8 != null) goto L20;
    L39:
        int r20 = 1;
        int r18 = 2;
        this.languageMap = null;
    L40:
        if (r32 == null) goto L50;
        TLRPC.SecureValueType r02 = r31.type;
        if ((r02 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L45;
        String r03 = PhoneFormat.getInstance().format("+".concat(r32));
        TextDetailSecureCell r26 = r2;
    L44:
        TLRPC.TL_secureValue r25 = r15;
        boolean r23 = false;
    L192:
        if (r36 == true) goto L194;
        TLRPC.TL_secureRequiredType r3 = r31;
        HashMap<String, String> r22 = this.errorsMap.get(getNameForType(r3.type));
    L195:
        if (r34 == null) goto L197;
        TLRPC.TL_secureRequiredType r9 = r34;
        HashMap<String, String> r42 = this.errorsMap.get(getNameForType(r9.type));
    L198:
        if (r22 != null) goto L200;
    L201:
        if (r42 != null) goto L203;
    L211:
        TLRPC.SecureValueType r24 = r3.type;
        if ((r24 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L238;
        if (TextUtils.isEmpty(r03) == false) goto L235;
        if (r9 != null) goto L219;
        r03 = LocaleController.getString(R.string.PassportPersonalDetailsInfo);
    L217:
        boolean r202 = r23;
        TextDetailSecureCell r27 = r26;
        boolean r7 = true;
    L275:
        r27.setValue(r03);
        TextView r04 = TextDetailSecureCell.m16578$$Nest$fgetvalueTextView(r27);
        if (r202 == false) goto L278;
        int r38 = Theme.key_text_RedRegular;
    L279:
        r04.setTextColor(Theme.getColor(r38));
        if (r202 == false) goto L282;
    L290:
        boolean r14 = r23;
    L291:
        r27.setChecked(r14);
        return;
    L282:
        if (this.currentActivityType == 8) goto L290;
        if (r36 == false) goto L285;
        if (r9 == null) goto L285;
    L287:
        if (r9 == null) goto L289;
        if (r16 == null) goto L290;
    L289:
        r14 = r7;
    L285:
        if (r36 == true) goto L290;
        if (r25 == null) goto L290;
    L278:
        r38 = Theme.key_windowBackgroundWhiteGrayText2;
        goto L279
    L219:
        if (this.currentActivityType != 8) goto L222;
        r03 = LocaleController.getString(R.string.PassportDocuments);
        goto L217
    L222:
        if (r37 != 1) goto L236;
        TLRPC.SecureValueType r28 = r9.type;
        if ((r28 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L227;
        r03 = LocaleController.getString(R.string.PassportIdentityPassport);
        goto L217
    L227:
        if ((r28 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L230;
        r03 = LocaleController.getString(R.string.PassportIdentityInternalPassport);
        goto L217
    L230:
        if ((r28 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L233;
        r03 = LocaleController.getString(R.string.PassportIdentityDriverLicence);
        goto L217
    L233:
        if ((r28 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L235;
        r03 = LocaleController.getString(R.string.PassportIdentityID);
        goto L217
    L236:
        r03 = LocaleController.getString(R.string.PassportIdentityDocumentInfo);
    L235:
        r7 = true;
    L251:
        r202 = r23;
        r27 = r26;
        goto L275
    L238:
        if ((r24 instanceof TLRPC.TL_secureValueTypeAddress) == true) goto L240;
        r7 = true;
        if ((r24 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L271;
        if (TextUtils.isEmpty(r03) == false) goto L251;
        r03 = LocaleController.getString(R.string.PassportPhoneInfo);
        goto L251
    L271:
        if ((r24 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L251;
        if (TextUtils.isEmpty(r03) == false) goto L251;
        r03 = LocaleController.getString(R.string.PassportEmailInfo);
        goto L251
    L240:
        if (TextUtils.isEmpty(r03) == false) goto L235;
        if (r9 != null) goto L244;
        r03 = LocaleController.getString(R.string.PassportAddressNoUploadInfo);
        goto L217
    L244:
        if (this.currentActivityType != 8) goto L246;
        r03 = LocaleController.getString(R.string.PassportDocuments);
        goto L217
    L246:
        r7 = true;
        if (r37 != 1) goto L264;
        TLRPC.SecureValueType r29 = r9.type;
        if ((r29 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L253;
        r03 = LocaleController.getString(R.string.PassportAddAgreementInfo);
        goto L251
    L253:
        if ((r29 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L256;
        r03 = LocaleController.getString(R.string.PassportAddBillInfo);
        goto L251
    L256:
        if ((r29 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L259;
        r03 = LocaleController.getString(R.string.PassportAddPassportRegistrationInfo);
        goto L251
    L259:
        if ((r29 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L262;
        r03 = LocaleController.getString(R.string.PassportAddTemporaryRegistrationInfo);
        goto L251
    L262:
        if ((r29 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L251;
        r03 = LocaleController.getString(R.string.PassportAddBankInfo);
        goto L251
    L264:
        r03 = LocaleController.getString(R.string.PassportAddressInfo);
        goto L251
    L203:
        if (r42.size() <= 0) goto L211;
    L204:
        if (r36 == true) goto L206;
        String r5 = this.mainErrorsMap.get(getNameForType(r3.type));
    L207:
        if (r5 != null) goto L209;
        r03 = this.mainErrorsMap.get(getNameForType(r9.type));
    L210:
        r27 = r26;
        r7 = true;
        r202 = true;
        goto L275
    L209:
        r03 = r5;
        goto L210
    L206:
        r5 = null;
        goto L207
    L200:
        if (r22.size() > 0) goto L204;
    L197:
        r9 = r34;
        r42 = null;
        goto L198
    L194:
        r3 = r31;
        r22 = null;
        goto L195
    L45:
        r26 = r2;
        if ((r02 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L48;
        r03 = r32;
        goto L44
    L48:
        r25 = r15;
        r23 = false;
    L191:
        r03 = null;
        goto L192
    L50:
        if (this.currentActivityType == 8) goto L61;
        if (r34 == null) goto L61;
        if (TextUtils.isEmpty(r35) == false) goto L55;
        if (r16 == null) goto L61;
    L55:
        StringBuilder r05 = new StringBuilder();
        if (r37 <= r20) goto L59;
        r05.append(getTextForType(r34.type));
    L62:
        if (r8 != null) goto L66;
        if (r35 != null) goto L66;
        r26 = r2;
        r25 = r15;
        r23 = false;
    L189:
        if (r05 == null) goto L191;
        r03 = r05.toString();
    L66:
        if (r4 == null) goto L323;
        r4.clear();
        TLRPC.SecureValueType r72 = r31.type;
        r23 = false;
        r25 = r15;
        r26 = r2;
        if ((r72 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L86;
        int r73 = this.currentActivityType;
        if (r73 != 0) goto L73;
        if (r36 == true) goto L73;
    L76:
        String[] r39 = new String[10];
        r39[0] = "first_name";
        r39[1] = "middle_name";
        r39[r18] = "last_name";
        r39[3] = "first_name_native";
        r39[4] = "middle_name_native";
        r39[5] = "last_name_native";
        r39[6] = "birth_date";
        r39[7] = "gender";
        String[] r272 = r39;
        int r310 = 8;
        r272[8] = "country_code";
        r272[9] = "residence_country_code";
    L79:
        if (r73 == 0) goto L84;
        if (r73 != r310) goto L83;
        if (r34 != null) goto L84;
    L83:
        String[] r74 = null;
    L97:
        if (r272 != null) goto L99;
        if (r74 == null) goto L189;
    L99:
        StringBuilder r322 = r05;
        String[] r182 = r74;
        int r311 = 0;
        JSONObject r06 = null;
        String[] r17 = null;
    L101:
        if (r311 >= 2) goto L188;
        if (r311 != 0) goto L105;
        if (r8 != null) goto L297;
    L110:
        String[] r75 = r17;
        int r172 = r311;
        String[] r312 = r75;
        JSONObject r76 = r06;
    L111:
        if (r312 == null) goto L186;
        if (r76 == null) goto L186;
        Iterator<String> r07 = r76.keys();     // Catch: Throwable -> L120
    L115:
        if (r07.hasNext() == false) goto L125;
        Iterator<String> r242 = r07;
        String r08 = r07.next();     // Catch: Throwable -> L120
        if (r172 != 0) goto L122;
        r4.put(r08, r76.getString(r08));     // Catch: Throwable -> L120
    L123:
        r07 = r242;
        goto L115
    L122:
        r6.put(r08, r76.getString(r08));     // Catch: Throwable -> L120
    L125:
        int r82 = 0;
    L127:
        if (r82 >= r312.length) goto L186;
        if (r76.has(r312[r82]) == false) goto L184;
        if (r322 != null) goto L133;
        r05 = new StringBuilder();     // Catch: Exception -> L294
    L132:
        String[] r222 = r312;
        String r313 = r76.getString(r222[r82]);     // Catch: Exception -> L293
        if (r313 != null) goto L137;
    L183:
        HashMap<String, String> r243 = r4;
    L144:
        HashMap<String, String> r282 = r6;
        JSONObject r292 = r76;
    L185:
        r82 = r82 + 1;
        r322 = r05;
        r312 = r222;
        r4 = r243;
        r6 = r282;
        r76 = r292;
        goto L127
    L137:
        if (TextUtils.isEmpty(r313) == true) goto L183;
        r243 = r4;
        if ("first_name_native".equals(r222[r82]) == true) goto L144;
        if ("middle_name_native".equals(r222[r82]) == true) goto L144;
        if ("last_name_native".equals(r222[r82]) == true) goto L144;
        if (r05.length() > 0) goto L148;
    L158:
        String r43 = r222[r82];     // Catch: Exception -> L293
        r282 = r6;
        int r62 = r43.hashCode();     // Catch: Exception -> L293
        r292 = r76;
        if (r62 == (-2006252145)) goto L177;
        if (r62 == (-1249512767)) goto L169;
        if (r62 == 1481071862) goto L166;
    L181:
        r05.append(r313);     // Catch: Exception -> L293
        goto L185
    L166:
        if (r43.equals("country_code") == false) goto L181;
    L178:
        String r314 = this.languageMap.get(r313);     // Catch: Exception -> L293
        if (r314 == null) goto L185;
        r05.append(r314);     // Catch: Exception -> L293
        goto L185
    L169:
        if (r43.equals("gender") == false) goto L181;
        if ("male".equals(r313) == false) goto L174;
        r05.append(LocaleController.getString(R.string.PassportMale));     // Catch: Exception -> L293
        goto L185
    L174:
        if ("female".equals(r313) == false) goto L185;
        r05.append(LocaleController.getString(R.string.PassportFemale));     // Catch: Exception -> L293
        goto L185
    L177:
        if (r43.equals("residence_country_code") == false) goto L181;
    L148:
        if ("last_name".equals(r222[r82]) == false) goto L150;
    L157:
        r05.append(" ");     // Catch: Exception -> L293
        goto L158
    L150:
        if ("last_name_native".equals(r222[r82]) == true) goto L157;
        if ("middle_name".equals(r222[r82]) == true) goto L157;
        if ("middle_name_native".equals(r222[r82]) == true) goto L157;
        r05.append(", ");     // Catch: Exception -> L293
        goto L158
    L133:
        r05 = r322;
        goto L132
    L184:
        r222 = r312;
        r243 = r4;
        r282 = r6;
        r292 = r76;
        r05 = r322;
        goto L185
    L120:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L294
    L186:
        String[] r223 = r312;
        HashMap<String, String> r244 = r4;
        HashMap<String, String> r283 = r6;
        JSONObject r293 = r76;
        StringBuilder r09 = r322;
    L187:
        r311 = r172 + 1;
        r8 = r33;
        r322 = r09;
        r17 = r223;
        r4 = r244;
        r6 = r283;
        r06 = r293;
        goto L101
    L297:
        r76 = new JSONObject(r8);     // Catch: Exception -> L294
        r172 = r311;
        r312 = r272;
    L105:
        if (r6 != null) goto L107;
        r293 = r06;
        r244 = r4;
        r283 = r6;
        r223 = r17;
        r09 = r322;
        r172 = r311;
        goto L187
    L107:
        if (r35 == null) goto L110;
        r76 = new JSONObject(r35);     // Catch: Exception -> L294
        r172 = r311;
        r312 = r182;
    L188:
        r05 = r322;
    L84:
        r74 = new String[r18];
        r74[0] = "document_no";
        r74[1] = "expiry_date";
    L73:
        r310 = 8;
        if (r73 != 8) goto L78;
        if (r34 == null) goto L76;
        r310 = 8;
    L78:
        r272 = null;
        goto L79
    L86:
        if ((r72 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L96;
        int r315 = this.currentActivityType;
        if (r315 != 0) goto L92;
        if (r36 == true) goto L92;
    L95:
        r272 = new String[]{"street_line1", "street_line2", "post_code", "city", "state", "country_code"};
    L92:
        if (r315 != 8) goto L96;
        if (r34 == null) goto L95;
    L96:
        r74 = null;
        r272 = null;
        goto L97
    L323:
        return;
    L59:
        if (TextUtils.isEmpty(r35) == false) goto L62;
        r05.append(LocaleController.getString(R.string.PassportDocuments));
    L61:
        r05 = null;
        goto L62
    L20:
        if (this.languageMap != null) goto L39;
        this.languageMap = new HashMap();
        r18 = 2;
        r20 = 1;
        BufferedReader r010 = new BufferedReader(new InputStreamReader(ApplicationLoader.applicationContext.getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L30
    L27:
        String r12 = r010.readLine();     // Catch: Exception -> L30
        if (r12 == null) goto L32;
        String[] r122 = r12.split(";");     // Catch: Exception -> L30
        this.languageMap.put(r122[1], r122[2]);     // Catch: Exception -> L30
        goto L27
    L32:
        r010.close();     // Catch: Exception -> L30
    L30:
        e = e;
    L38:
        FileLog.e(e);
    L34:
        e = e;
        r20 = 1;
    L36:
        e = e;
        r20 = 1;
        r18 = 2;
        goto L38
    L16:
        r6 = null;
        goto L17
    L5:
        if (this.currentActivityType != 8) goto L67;
        ArrayList<TLRPC.TL_secureRequiredType> r44 = new ArrayList();
        if (r34 == null) goto L9;
        r44.add(r34);
    L9:
        LinearLayout r011 = this.linearLayout2;
        View r012 = r011.getChildAt(r011.getChildCount() - 6);
        if ((r012 instanceof TextDetailSecureCell) == false) goto L12;
        ((TextDetailSecureCell) r012).setNeedDivider(true);
    L12:
        r0 = addField(getParentActivity(), r31, r44, true, true);
        updateManageVisibility();
        goto L13
    }

    private void showAlertWithText(String r4, String r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r0.setTitle(r4);
        r0.setMessage(r5);
        showDialog(r0.create());
    }

    private void showAttachmentError() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.UnsupportedAttachment), 0).show();
    }

    private void showEditDoneProgress(boolean r23, boolean r24) {
        AnimatorSet r2 = this.doneItemAnimation;
        if (r2 == null) goto L6;
        r2.cancel();
    L6:
        if (r23 == false) goto L16;
        if (this.doneItem == null) goto L16;
        this.doneItemAnimation = new AnimatorSet();
        if (r24 == false) goto L12;
        this.progressView.setVisibility(0);
        this.doneItem.setEnabled(false);
        AnimatorSet r14 = this.doneItemAnimation;
        View r15 = this.doneItem.getContentView();
        Property r22 = View.SCALE_X;
        ObjectAnimator r3 = ObjectAnimator.ofFloat(r15, r22, new float[]{0.1f});
        View r152 = this.doneItem.getContentView();
        Property r4 = View.SCALE_Y;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(r152, r4, new float[]{0.1f});
        View r12 = this.doneItem.getContentView();
        Property r153 = View.ALPHA;
        r14.playTogether(new Animator[]{r3, r5, ObjectAnimator.ofFloat(r12, r153, new float[]{0.0f}), ObjectAnimator.ofFloat(this.progressView, r22, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressView, r4, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressView, r153, new float[]{1.0f})});
    L13:
        this.doneItemAnimation.addListener(new AnonymousClass21(this, r24));
        this.doneItemAnimation.setDuration(150);
        this.doneItemAnimation.start();
        return;
    L12:
        this.doneItem.getContentView().setVisibility(0);
        this.doneItem.setEnabled(true);
        AnimatorSet r25 = this.doneItemAnimation;
        ContextProgressView r32 = this.progressView;
        Property r42 = View.SCALE_X;
        ObjectAnimator r33 = ObjectAnimator.ofFloat(r32, r42, new float[]{0.1f});
        ContextProgressView r52 = this.progressView;
        Property r10 = View.SCALE_Y;
        ObjectAnimator r53 = ObjectAnimator.ofFloat(r52, r10, new float[]{0.1f});
        ContextProgressView r122 = this.progressView;
        Property r13 = View.ALPHA;
        r25.playTogether(new Animator[]{r33, r53, ObjectAnimator.ofFloat(r122, r13, new float[]{0.0f}), ObjectAnimator.ofFloat(this.doneItem.getContentView(), r42, new float[]{1.0f}), ObjectAnimator.ofFloat(this.doneItem.getContentView(), r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.doneItem.getContentView(), r13, new float[]{1.0f})});
    L16:
        if (this.acceptTextView == null) goto L23;
        this.doneItemAnimation = new AnimatorSet();
        if (r24 == false) goto L20;
        this.progressViewButton.setVisibility(0);
        this.bottomLayout.setEnabled(false);
        AnimatorSet r26 = this.doneItemAnimation;
        TextView r34 = this.acceptTextView;
        Property r43 = View.SCALE_X;
        ObjectAnimator r35 = ObjectAnimator.ofFloat(r34, r43, new float[]{0.1f});
        TextView r54 = this.acceptTextView;
        Property r102 = View.SCALE_Y;
        ObjectAnimator r55 = ObjectAnimator.ofFloat(r54, r102, new float[]{0.1f});
        TextView r123 = this.acceptTextView;
        Property r132 = View.ALPHA;
        r26.playTogether(new Animator[]{r35, r55, ObjectAnimator.ofFloat(r123, r132, new float[]{0.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r43, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r102, new float[]{1.0f}), ObjectAnimator.ofFloat(this.progressViewButton, r132, new float[]{1.0f})});
    L21:
        this.doneItemAnimation.addListener(new AnonymousClass22(this, r24));
        this.doneItemAnimation.setDuration(150);
        this.doneItemAnimation.start();
        return;
    L20:
        this.acceptTextView.setVisibility(0);
        this.bottomLayout.setEnabled(true);
        AnimatorSet r27 = this.doneItemAnimation;
        ContextProgressView r36 = this.progressViewButton;
        Property r44 = View.SCALE_X;
        ObjectAnimator r37 = ObjectAnimator.ofFloat(r36, r44, new float[]{0.1f});
        ContextProgressView r56 = this.progressViewButton;
        Property r103 = View.SCALE_Y;
        ObjectAnimator r57 = ObjectAnimator.ofFloat(r56, r103, new float[]{0.1f});
        ContextProgressView r124 = this.progressViewButton;
        Property r133 = View.ALPHA;
        r27.playTogether(new Animator[]{r37, r57, ObjectAnimator.ofFloat(r124, r133, new float[]{0.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r44, new float[]{1.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r103, new float[]{1.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r133, new float[]{1.0f})});
        goto L21
    }

    private void startPhoneVerification(boolean r7, String r8, Runnable r9, ErrorRunnable r10, PassportActivityDelegate r11) {
        TelephonyManager r0 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");
        if (r0.getSimState() != 1) goto L5;
    L7:
        boolean r1 = false;
    L9:
        if (getParentActivity() == null) goto L27;
        if (r1 == false) goto L27;
        if (getParentActivity().checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) goto L14;
        boolean r4 = true;
    L15:
        if (r7 == false) goto L28;
        this.permissionsItems.clear();
        if (r4 == true) goto L20;
        this.permissionsItems.add("android.permission.READ_PHONE_STATE");
    L20:
        if (this.permissionsItems.isEmpty() == true) goto L28;
        if (getParentActivity().shouldShowRequestPermissionRationale("android.permission.READ_PHONE_STATE") == false) goto L24;
        AlertDialog.Builder r72 = new AlertDialog.Builder(getParentActivity());
        r72.setTitle(LocaleController.getString(R.string.AppName));
        r72.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r72.setMessage(LocaleController.getString(R.string.AllowReadCall));
        this.permissionsDialog = showDialog(r72.create());
    L25:
        this.pendingPhone = r8;
        this.pendingErrorRunnable = r10;
        this.pendingFinishRunnable = r9;
        this.pendingDelegate = r11;
        return;
    L24:
        getParentActivity().requestPermissions((String[]) this.permissionsItems.toArray(new String[0]), 6);
    L28:
        TL_account.sendVerifyPhoneCode r73 = new TL_account.sendVerifyPhoneCode();
        r73.phone_number = r8;
        TLRPC.TL_codeSettings r92 = new TLRPC.TL_codeSettings();
        r73.settings = r92;
        if (r1 == false) goto L32;
        if (r4 == false) goto L32;
        boolean r102 = true;
    L33:
        r92.allow_flashcall = r102;
        r92.allow_app_hash = PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices();
        SharedPreferences r93 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (r73.settings.allow_app_hash == false) goto L36;
        r93.edit().putString("sms_hash", BuildVars.getSmsHash()).apply();
    L38:
        if (r73.settings.allow_flashcall == true) goto L53;
    L50:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r73, new PassportActivity$$ExternalSyntheticLambda55(this, r8, r11, r73), 2);
        return;
    L53:
        String r94 = r0.getLine1Number();     // Catch: Exception -> L45
        boolean r103 = TextUtils.isEmpty(r94);     // Catch: Exception -> L45
        TLRPC.TL_codeSettings r02 = r73.settings;
        if (r103 == true) goto L47;
        r02.current_number = PhoneNumberUtils.compare(r8, r94);     // Catch: Exception -> L45
        TLRPC.TL_codeSettings r95 = r73.settings;     // Catch: Exception -> L45
        if (r95.current_number == true) goto L52;
        r95.allow_flashcall = false;     // Catch: Exception -> L45
    L52:
        goto L50
    L47:
        r02.unknown_number = true;     // Catch: Exception -> L45
        r02.current_number = false;     // Catch: Exception -> L45
    L45:
        e = move-exception;
        r73.settings.allow_flashcall = false;
        FileLog.e(e);
        goto L50
    L36:
        r93.edit().remove("sms_hash").apply();
    L32:
        r102 = false;
        goto L33
    L14:
        r4 = false;
    L27:
        r4 = true;
        goto L28
    L5:
        if (r0.getPhoneType() == 0) goto L7;
        r1 = true;
        goto L9
    }

    private void updateInterfaceStringsForDocumentType() {
        TLRPC.TL_secureRequiredType r0 = this.currentDocumentsType;
        ActionBar r1 = this.actionBar;
        if (r0 == null) goto L5;
        r1.setTitle(getTextForType(r0.type));
    L6:
        updateUploadText(2);
        updateUploadText(3);
        updateUploadText(1);
        updateUploadText(4);
        return;
    L5:
        r1.setTitle(LocaleController.getString(R.string.PassportPersonal));
        goto L6
    }

    private void updateManageVisibility() {
        boolean r0 = this.currentForm.values.isEmpty();
        LinearLayout r1 = this.emptyLayout;
        if (r0 == false) goto L6;
        r1.setVisibility(0);
        this.sectionCell.setVisibility(8);
        this.headerCell.setVisibility(8);
        this.addDocumentCell.setVisibility(8);
        this.deletePassportCell.setVisibility(8);
        this.addDocumentSectionCell.setVisibility(8);
        return;
    L6:
        r1.setVisibility(8);
        this.sectionCell.setVisibility(0);
        this.headerCell.setVisibility(0);
        this.deletePassportCell.setVisibility(0);
        this.addDocumentSectionCell.setVisibility(0);
        boolean r02 = hasUnfilledValues();
        TextSettingsCell r4 = this.addDocumentCell;
        if (r02 == false) goto L10;
        r4.setVisibility(0);
        return;
    L10:
        r4.setVisibility(8);
    }

    private void updatePasswordInterface() {
        ImageView r0 = this.noPasswordImageView;
        if (r0 == null) goto L27;
        TL_account.Password r1 = this.currentPassword;
        if (r1 != null) goto L8;
    L24:
        r0.setVisibility(8);
        this.noPasswordTextView.setVisibility(8);
        this.noPasswordSetTextView.setVisibility(8);
        this.passwordAvatarContainer.setVisibility(8);
        this.inputFieldContainers[0].setVisibility(8);
        this.doneItem.setVisibility(8);
        this.passwordForgotButton.setVisibility(8);
        this.passwordInfoRequestTextView.setVisibility(8);
        this.passwordRequestTextView.setVisibility(8);
        this.emptyView.setVisibility(0);
        return;
    L8:
        if (this.usingSavedPassword != 0) goto L24;
        boolean r02 = r1.has_password;
        TextInfoPrivacyCell r12 = this.passwordRequestTextView;
        if (r02 == true) goto L14;
        r12.setVisibility(0);
        this.noPasswordImageView.setVisibility(0);
        this.noPasswordTextView.setVisibility(0);
        this.noPasswordSetTextView.setVisibility(0);
        this.passwordAvatarContainer.setVisibility(8);
        this.inputFieldContainers[0].setVisibility(8);
        this.doneItem.setVisibility(8);
        this.passwordForgotButton.setVisibility(8);
        this.passwordInfoRequestTextView.setVisibility(8);
        this.passwordRequestTextView.setLayoutParams(LayoutHelper.createLinear(-1, -2, 0.0f, 25.0f, 0.0f, 0.0f));
        this.emptyView.setVisibility(8);
        return;
    L14:
        r12.setVisibility(0);
        this.noPasswordImageView.setVisibility(8);
        this.noPasswordTextView.setVisibility(8);
        this.noPasswordSetTextView.setVisibility(8);
        this.emptyView.setVisibility(8);
        this.passwordAvatarContainer.setVisibility(0);
        this.inputFieldContainers[0].setVisibility(0);
        this.doneItem.setVisibility(0);
        this.passwordForgotButton.setVisibility(0);
        this.passwordInfoRequestTextView.setVisibility(0);
        this.passwordRequestTextView.setLayoutParams(LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        if (this.inputFields == null) goto L26;
        TL_account.Password r03 = this.currentPassword;
        if (r03 != null) goto L19;
    L22:
        this.inputFields[0].setHint(LocaleController.getString(R.string.LoginPassword));
        return;
    L19:
        if (TextUtils.isEmpty(r03.hint) == true) goto L22;
        this.inputFields[0].setHint(this.currentPassword.hint);
        return;
    L26:
        return;
    }

    private void updateUploadText(int r7) {
        boolean r0 = true;
        int r1 = 0;
        if (r7 != 0) goto L14;
        if (this.uploadDocumentCell == null) goto L79;
        int r72 = this.documents.size();
        TextSettingsCell r6 = this.uploadDocumentCell;
        if (r72 < 1) goto L11;
        r6.setText(LocaleController.getString(R.string.PassportUploadAdditinalDocument), false);
        return;
    L11:
        r6.setText(LocaleController.getString(R.string.PassportUploadDocument), false);
        return;
    L79:
        return;
    L14:
        if (r7 != 1) goto L24;
        TextDetailSettingsCell r73 = this.uploadSelfieCell;
        if (r73 != null) goto L19;
        return;
    L19:
        if (this.selfieDocument == null) goto L21;
        r1 = 8;
    L21:
        r73.setVisibility(r1);
        return;
    L24:
        if (r7 != 4) goto L35;
        if (this.uploadTranslationCell == null) goto L81;
        int r74 = this.translationDocuments.size();
        TextSettingsCell r62 = this.uploadTranslationCell;
        if (r74 < 1) goto L32;
        r62.setText(LocaleController.getString(R.string.PassportUploadAdditinalDocument), false);
        return;
    L32:
        r62.setText(LocaleController.getString(R.string.PassportUploadDocument), false);
        return;
    L81:
        return;
    L35:
        if (r7 != 2) goto L62;
        TextDetailSettingsCell r75 = this.uploadFrontCell;
        if (r75 == null) goto L82;
        TLRPC.TL_secureRequiredType r3 = this.currentDocumentsType;
        if (r3 != null) goto L42;
    L48:
        r0 = false;
    L49:
        TLRPC.SecureValueType r32 = r3.type;
        if ((r32 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L52;
    L55:
        r75.setTextAndValue(LocaleController.getString(R.string.PassportMainPage), LocaleController.getString(R.string.PassportMainPageInfo), r0);
    L56:
        TextDetailSettingsCell r76 = this.uploadFrontCell;
        if (this.frontDocument == null) goto L59;
        r1 = 8;
    L59:
        r76.setVisibility(r1);
        return;
    L52:
        if ((r32 instanceof TLRPC.TL_secureValueTypeInternalPassport) == true) goto L55;
        r75.setTextAndValue(LocaleController.getString(R.string.PassportFrontSide), LocaleController.getString(R.string.PassportFrontSideInfo), r0);
        goto L56
    L42:
        if (r3.selfie_required == true) goto L49;
        TLRPC.SecureValueType r4 = r3.type;
        if ((r4 instanceof TLRPC.TL_secureValueTypeIdentityCard) == true) goto L49;
        if ((r4 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L48;
    L82:
        return;
    L62:
        if (r7 == 3) goto L64;
        return;
    L64:
        if (this.uploadReverseCell == null) goto L83;
        TLRPC.SecureValueType r77 = this.currentDocumentsType.type;
        if ((r77 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L69;
    L73:
        this.reverseLayout.setVisibility(0);
        TextDetailSettingsCell r78 = this.uploadReverseCell;
        if (this.reverseDocument == null) goto L76;
        r1 = 8;
    L76:
        r78.setVisibility(r1);
        return;
    L69:
        if ((r77 instanceof TLRPC.TL_secureValueTypeDriverLicense) == true) goto L73;
        this.reverseLayout.setVisibility(8);
        this.uploadReverseCell.setVisibility(8);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass3(this));
        if (this.currentActivityType != 7) goto L5;
        AnonymousClass4 r0 = new AnonymousClass4(this, r15);
        this.scrollView = r0;
        this.fragmentView = r0;
        r0.setFillViewport(true);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor(Theme.key_actionBarDefault));
    L10:
        int r02 = this.currentActivityType;
        if (r02 == 0) goto L22;
        if (r02 == 8) goto L22;
        this.doneItem = this.actionBar.createMenu().addItemWithWidth(2, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        ContextProgressView r03 = new ContextProgressView(r15, 1);
        this.progressView = r03;
        r03.setAlpha(0.0f);
        this.progressView.setScaleX(0.1f);
        this.progressView.setScaleY(0.1f);
        this.progressView.setVisibility(4);
        this.doneItem.addView(this.progressView, LayoutHelper.createFrame(-1, -1.0f));
        int r04 = this.currentActivityType;
        if (r04 == 1) goto L16;
        if (r04 != 2) goto L22;
    L16:
        ChatAttachAlert r05 = this.chatAttachAlert;
        if (r05 == null) goto L22;
        if (r05.isShowing() == false) goto L21;
        this.chatAttachAlert.dismiss();     // Catch: Exception -> L45
    L21:
        this.chatAttachAlert.onDestroy();
        this.chatAttachAlert = null;
    L22:
        int r06 = this.currentActivityType;
        if (r06 != 5) goto L25;
        createPasswordInterface(r15);
    L44:
        return this.fragmentView;
    L25:
        if (r06 != 0) goto L27;
        createRequestInterface(r15);
        goto L44
    L27:
        if (r06 != 1) goto L29;
        createIdentityInterface(r15);
        fillInitialValues();
        goto L44
    L29:
        if (r06 != 2) goto L32;
        createAddressInterface(r15);
        fillInitialValues();
        goto L44
    L32:
        if (r06 != 3) goto L34;
        createPhoneInterface(r15);
        goto L44
    L34:
        if (r06 != 4) goto L37;
        createEmailInterface(r15);
        goto L44
    L37:
        if (r06 != 6) goto L39;
        createEmailVerificationInterface(r15);
        goto L44
    L39:
        if (r06 != 7) goto L41;
        createPhoneVerificationInterface(r15);
        goto L44
    L41:
        if (r06 != 8) goto L44;
        createManageInterface(r15);
        goto L44
    L5:
        FrameLayout r07 = new FrameLayout(r15);
        this.fragmentView = r07;
        r07.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass5 r5 = new AnonymousClass5(this, r15);
        this.scrollView = r5;
        r5.setFillViewport(true);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor(Theme.key_actionBarDefault));
        ScrollView r52 = this.scrollView;
        if (this.currentActivityType != 0) goto L8;
        float r13 = 48.0f;
    L9:
        r07.addView(r52, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r13));
        LinearLayout r08 = new LinearLayout(r15);
        this.linearLayout2 = r08;
        r08.setOrientation(1);
        this.scrollView.addView(this.linearLayout2, new FrameLayout.LayoutParams(-1, -2));
        goto L10
    L8:
        r13 = 0.0f;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r5 != NotificationCenter.fileUploaded) goto L39;
        String r52 = (String) r7[0];
        SecureDocument r62 = this.uploadingDocuments.get(r52);
        if (r62 == null) goto L61;
        r62.inputFile = (TLRPC.TL_inputFile) r7[1];
        this.uploadingDocuments.remove(r52);
        if (this.uploadingDocuments.isEmpty() == false) goto L11;
        ActionBarMenuItem r53 = this.doneItem;
        if (r53 == null) goto L11;
        r53.setEnabled(true);
        this.doneItem.setAlpha(1.0f);
    L11:
        HashMap<SecureDocument, SecureDocumentCell> r54 = this.documentsCells;
        if (r54 == null) goto L16;
        SecureDocumentCell r55 = r54.get(r62);
        if (r55 == null) goto L16;
        r55.updateButtonState(true);
    L16:
        HashMap<String, String> r56 = this.errorsValues;
        if (r56 != null) goto L19;
    L21:
        int r57 = r62.type;
        if (r57 == 0) goto L24;
        if (r57 == 4) goto L32;
        return;
    L32:
        if (this.bottomCellTranslation != null) goto L34;
    L36:
        this.errorsValues.remove("translation_all");
        return;
    L34:
        if (TextUtils.isEmpty(this.noAllTranslationErrorText) == true) goto L36;
        this.bottomCellTranslation.setText(this.noAllTranslationErrorText);
        goto L36
    L24:
        if (this.bottomCell != null) goto L26;
    L28:
        this.errorsValues.remove("files_all");
        return;
    L26:
        if (TextUtils.isEmpty(this.noAllDocumentsErrorText) == true) goto L28;
        this.bottomCell.setText(this.noAllDocumentsErrorText);
        goto L28
    L19:
        if (r56.containsKey("error_document_all") == false) goto L21;
        this.errorsValues.remove("error_document_all");
        checkTopErrorCell(false);
        goto L21
    L61:
        return;
    L39:
        if (r5 != NotificationCenter.fileUploadFailed) goto L42;
        return;
    L42:
        if (r5 != NotificationCenter.twoStepPasswordChanged) goto L63;
        if (r7 != null) goto L45;
    L58:
        this.currentPassword = null;
        loadPasswordInfo();
    L59:
        updatePasswordInterface();
        return;
    L45:
        if (r7.length <= 0) goto L58;
        Object r58 = r7[7];
        if (r58 == null) goto L52;
        EditTextBoldCursor r63 = this.inputFields[0];
        if (r63 == null) goto L52;
        r63.setText((String) r58);
    L52:
        if (r7[6] != null) goto L59;
        TL_account.TL_password r59 = new TL_account.TL_password();
        this.currentPassword = r59;
        r59.current_algo = (TLRPC.PasswordKdfAlgo) r7[1];
        r59.new_secure_algo = (TLRPC.SecurePasswordKdfAlgo) r7[2];
        r59.secure_random = (byte[]) r7[3];
        r59.has_recovery = !TextUtils.isEmpty((String) r7[4]);
        TL_account.Password r510 = this.currentPassword;
        r510.hint = (String) r7[5];
        r510.srp_id = -1;
        byte[] r72 = new byte[256];
        r510.srp_B = r72;
        Utilities.random.nextBytes(r72);
        EditTextBoldCursor r511 = this.inputFields[0];
        if (r511 == null) goto L59;
        if (r511.length() <= 0) goto L59;
        this.usingSavedPassword = 2;
        goto L59
    }

    public void didSelectFiles(ArrayList<String> r1, String r2, boolean r3, int r4, long r5, boolean r7) {
        ArrayList<SendMessagesHelper.SendingMediaInfo> r22 = new ArrayList();
        int r32 = r1.size();
        int r42 = 0;
    L3:
        if (r42 >= r32) goto L5;
        SendMessagesHelper.SendingMediaInfo r52 = new SendMessagesHelper.SendingMediaInfo();
        r52.path = r1.get(r42);
        r22.add(r52);
        r42 = r42 + 1;
        goto L3
    L5:
        processSelectedFiles(r22);
    }

    public void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3) {
        processSelectedFiles(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void dismissCurrentDialog() {
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 != null) goto L5;
    L8:
        super.dismissCurrentDialog();
        return;
    L5:
        if (this.visibleDialog != r0) goto L8;
        r0.getPhotoLayout().closeCamera(false);
        this.chatAttachAlert.dismissInternal();
        this.chatAttachAlert.getPhotoLayout().hideCamera(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean dismissDialogOnPause(Dialog r2) {
        if (r2 != this.chatAttachAlert) goto L5;
        return false;
    L5:
        if (super.dismissDialogOnPause(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r4 = this.actionBar;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r10 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.scrollView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        View r52 = this.extraBackgroundView;
        int r6 = ThemeDescription.FLAG_BACKGROUND;
        int r11 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r52, r6, null, null, null, null, r11));
        if (this.extraBackgroundView2 == null) goto L5;
        r1.add(new ThemeDescription(this.extraBackgroundView2, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r11));
    L5:
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r3 >= this.dividers.size()) goto L9;
        r1.add(new ThemeDescription(this.dividers.get(r3), ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_divider));
        r3 = r3 + 1;
        goto L7
    L9:
        Iterator<Map.Entry<SecureDocument, SecureDocumentCell>> r32 = this.documentsCells.entrySet().iterator();
    L11:
        if (r32.hasNext() == false) goto L13;
        SecureDocumentCell r8 = r32.next().getValue();
        r1.add(new ThemeDescription(r8, ThemeDescription.FLAG_SELECTORWHITE, new Class[]{SecureDocumentCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(r8, 0, new Class[]{SecureDocumentCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(r8, 0, new Class[]{SecureDocumentCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        goto L11
    L13:
        int r19 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_SELECTORWHITE, new Class[]{TextDetailSettingsCell.class}, null, null, null, r19));
        int r28 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"textView"}, null, null, null, r28));
        int r18 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_SELECTORWHITE, new Class[]{TextSettingsCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        int r15 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r15));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_SELECTORWHITE, new Class[]{TextDetailSecureCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextDetailSecureCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextDetailSecureCell.class}, null, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextDetailSecureCell.class}, new String[]{"valueTextView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{TextDetailSecureCell.class}, new String[]{"checkImageView"}, null, null, null, Theme.key_featuredStickers_addedIcon));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class}, null, null, null, r19));
        int r21 = Theme.key_windowBackgroundWhiteBlueHeader;
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r15));
        r1.add(new ThemeDescription(this.linearLayout2, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        if (this.inputFields == null) goto L19;
        int r33 = 0;
    L17:
        if (r33 >= this.inputFields.length) goto L21;
        r1.add(new ThemeDescription((View) this.inputFields[r33].getParent(), ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_HINTTEXTCOLOR | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.inputFields[r33], ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_text_RedRegular));
        r33 = r33 + 1;
    L21:
        if (this.inputExtraFields != null) goto L23;
    L25:
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.noPasswordImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chat_messagePanelIcons));
        r1.add(new ThemeDescription(this.noPasswordTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.noPasswordSetTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueText5));
        TextView r82 = this.passwordForgotButton;
        int r9 = ThemeDescription.FLAG_TEXTCOLOR;
        int r17 = Theme.key_windowBackgroundWhiteBlueText4;
        r1.add(new ThemeDescription(r82, r9, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.plusTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.acceptTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_passport_authorizeText));
        r1.add(new ThemeDescription(this.bottomLayout, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_passport_authorizeBackground));
        r1.add(new ThemeDescription(this.bottomLayout, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_passport_authorizeBackgroundSelected));
        ContextProgressView r53 = this.progressView;
        int r112 = Theme.key_contextProgressInner2;
        r1.add(new ThemeDescription(r53, 0, null, null, null, null, r112));
        ContextProgressView r22 = this.progressView;
        int r25 = Theme.key_contextProgressOuter2;
        r1.add(new ThemeDescription(r22, 0, null, null, null, null, r25));
        r1.add(new ThemeDescription(this.progressViewButton, 0, null, null, null, null, r112));
        r1.add(new ThemeDescription(this.progressViewButton, 0, null, null, null, null, r25));
        r1.add(new ThemeDescription(this.emptyImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_sessions_devicesImage));
        TextView r62 = this.emptyTextView1;
        int r7 = ThemeDescription.FLAG_TEXTCOLOR;
        int r152 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(r62, r7, null, null, null, null, r152));
        r1.add(new ThemeDescription(this.emptyTextView2, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r152));
        r1.add(new ThemeDescription(this.emptyTextView3, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r17));
        return r1;
    L23:
        if (r2 >= this.inputExtraFields.length) goto L25;
        r1.add(new ThemeDescription((View) this.inputExtraFields[r2].getParent(), ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_HINTTEXTCOLOR | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.inputExtraFields[r2], ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_text_RedRegular));
        r2 = r2 + 1;
        goto L23
    L19:
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r28));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_HINTTEXTCOLOR | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, r21));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(null, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_text_RedRegular));
        goto L21
    }

    public void needHideProgress() {
        AlertDialog r0 = this.progressDialog;
        if (r0 != null) goto L11;
        return;
    L11:
        r0.dismiss();     // Catch: Exception -> L7
    L9:
        this.progressDialog = null;
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L9
    }

    public void needShowProgress() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (getParentActivity().isFinishing() == false) goto L7;
        return;
    L7:
        if (this.progressDialog != null) goto L13;
        AlertDialog r0 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r0;
        r0.setCanCancel(false);
        this.progressDialog.show();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r2, int r3, Intent r4) {
        if (r3 != (-1)) goto L23;
        if (r2 != 0) goto L6;
    L18:
        createChatAttachView();
        ChatAttachAlert r32 = this.chatAttachAlert;
        if (r32 == null) goto L21;
        r32.onActivityResultFragment(r2, r4, this.currentPicturePath);
    L21:
        this.currentPicturePath = null;
        return;
    L6:
        if (r2 == 2) goto L18;
        if (r2 != 1) goto L24;
        if (r4 != null) goto L12;
    L16:
        showAttachmentError();
        return;
    L12:
        if (r4.getData() == null) goto L16;
        ArrayList<SendMessagesHelper.SendingMediaInfo> r22 = new ArrayList();
        SendMessagesHelper.SendingMediaInfo r33 = new SendMessagesHelper.SendingMediaInfo();
        r33.uri = r4.getData();
        r22.add(r33);
        processSelectedFiles(r22);
        return;
    L24:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r5) {
        int r0 = this.currentActivityType;
        int r2 = 0;
        if (r0 != 7) goto L12;
        if (r5 == false) goto L23;
        this.views[this.currentViewNum].onBackPressed(true);
    L6:
        SlideView[] r52 = this.views;
        if (r2 >= r52.length) goto L23;
        SlideView r53 = r52[r2];
        if (r53 == null) goto L11;
        r53.onDestroyActivity();
    L11:
        r2 = r2 + 1;
    L23:
        return true;
    L12:
        if (r0 != 0) goto L14;
    L21:
        if (r5 == false) goto L23;
        callCallback(false);
        goto L23
    L14:
        if (r0 == 5) goto L21;
        if (r0 == 1) goto L20;
        if (r0 != 2) goto L23;
    L20:
        return !checkDiscard(r5);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r3) {
        if (this.currentActivityType == 3) goto L5;
        return;
    L5:
        if (r3 == this.permissionsDialog) goto L7;
        return;
    L7:
        if (this.permissionsItems.isEmpty() == true) goto L12;
        getParentActivity().requestPermissions((String[]) this.permissionsItems.toArray(new String[0]), 6);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.twoStepPasswordChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didRemoveTwoStepPassword);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.twoStepPasswordChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didRemoveTwoStepPassword);
        int r0 = 0;
        callCallback(false);
        ChatAttachAlert r1 = this.chatAttachAlert;
        if (r1 == null) goto L6;
        r1.dismissInternal();
        this.chatAttachAlert.onDestroy();
    L6:
        if (this.currentActivityType != 7) goto L26;
    L7:
        SlideView[] r12 = this.views;
        if (r0 >= r12.length) goto L13;
        SlideView r13 = r12[r0];
        if (r13 == null) goto L12;
        r13.onDestroyActivity();
    L12:
        r0 = r0 + 1;
        goto L7
    L13:
        AlertDialog r02 = this.progressDialog;
        if (r02 == null) goto L27;
        r02.dismiss();     // Catch: Exception -> L17
    L19:
        this.progressDialog = null;
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    L27:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L6;
        r0.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r7, String[] r8, int[] r9) {
        int r82 = this.currentActivityType;
        if (r82 != 1) goto L5;
    L6:
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 != null) goto L9;
    L45:
        if (r82 == 3) goto L47;
        return;
    L47:
        if (r7 != 6) goto L59;
        startPhoneVerification(false, this.pendingPhone, this.pendingFinishRunnable, this.pendingErrorRunnable, this.pendingDelegate);
        return;
    L59:
        return;
    L9:
        if (r7 != 17) goto L13;
        r0.getPhotoLayout().checkCamera(false);
        return;
    L13:
        if (r7 != 21) goto L25;
        if (getParentActivity() == null) goto L60;
        if (r9 != null) goto L19;
        return;
    L19:
        if (r9.length != 0) goto L21;
        return;
    L21:
        if (r9[0] == 0) goto L52;
        AlertDialog.Builder r72 = new AlertDialog.Builder(getParentActivity());
        r72.setTitle(LocaleController.getString(R.string.AppName));
        r72.setMessage(LocaleController.getString(R.string.PermissionNoAudioVideoWithHint));
        r72.setNegativeButton(LocaleController.getString(R.string.PermissionOpenSettings), new PassportActivity$$ExternalSyntheticLambda25(this));
        r72.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r72.show();
        return;
    L52:
        return;
    L60:
        return;
    L25:
        if (r7 != 19) goto L34;
        if (r9 == null) goto L34;
        if (r9.length <= 0) goto L34;
        if (r9[0] != 0) goto L34;
        processSelectedAttach(0);
        return;
    L34:
        if (r7 != 22) goto L53;
        if (r9 != null) goto L37;
        return;
    L37:
        if (r9.length > 0) goto L39;
        return;
    L39:
        if (r9[0] != 0) goto L56;
        TextSettingsCell r6 = this.scanDocumentCell;
        if (r6 == null) goto L57;
        r6.callOnClick();
        return;
    L57:
        return;
    L56:
        return;
    L53:
        return;
    L5:
        if (r82 != 2) goto L45;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L6;
        r0.onResume();
    L6:
        if (this.currentActivityType != 5) goto L14;
        ViewGroup[] r02 = this.inputFieldContainers;
        if (r02 == null) goto L14;
        ViewGroup r03 = r02[0];
        if (r03 == null) goto L14;
        if (r03.getVisibility() != 0) goto L14;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda13(this), 200);
    L14:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r4, boolean r5) {
        if (this.presentAfterAnimation == null) goto L5;
        AndroidUtilities.runOnUIThread(new PassportActivity$$ExternalSyntheticLambda36(this));
    L5:
        int r52 = this.currentActivityType;
        if (r52 != 5) goto L16;
        if (r4 == true) goto L9;
        return;
    L9:
        if (this.inputFieldContainers[0].getVisibility() != 0) goto L12;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
    L12:
        if (this.usingSavedPassword != 2) goto L37;
        onPasswordDone(false);
        return;
    L37:
        return;
    L16:
        if (r52 != 7) goto L21;
        if (r4 == false) goto L38;
        this.views[this.currentViewNum].onShow();
        return;
    L38:
        return;
    L21:
        if (r52 != 4) goto L26;
        if (r4 == false) goto L39;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        return;
    L39:
        return;
    L26:
        if (r52 != 6) goto L30;
        if (r4 == false) goto L40;
        this.inputFields[0].requestFocus();
        AndroidUtilities.showKeyboard(this.inputFields[0]);
        return;
    L40:
        return;
    L30:
        if (r52 != 2) goto L32;
    L35:
        createChatAttachView();
        return;
    L32:
        if (r52 == 1) goto L35;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r2) {
        this.currentPicturePath = r2.getString("path");
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r2) {
        String r1 = this.currentPicturePath;
        if (r1 == null) goto L6;
        r2.putString("path", r1);
        return;
    }

    public void setNeedActivityResult(boolean r1) {
        this.needActivityResult = r1;
    }

    public void setPage(int r7, boolean r8, Bundle r9) {
        if (r7 != 3) goto L5;
        this.doneItem.setVisibility(8);
    L5:
        SlideView[] r0 = this.views;
        SlideView r2 = r0[this.currentViewNum];
        SlideView r02 = r0[r7];
        this.currentViewNum = r7;
        r02.setParams(r9, false);
        r02.onShow();
        if (r8 == false) goto L9;
        r02.setTranslationX(AndroidUtilities.displaySize.x);
        AnimatorSet r82 = new AnimatorSet();
        r82.setInterpolator(new AccelerateDecelerateInterpolator());
        r82.setDuration(300);
        r82.playTogether(new Animator[]{ObjectAnimator.ofFloat(r2, "translationX", new float[]{-AndroidUtilities.displaySize.x}), ObjectAnimator.ofFloat(r02, "translationX", new float[]{0.0f})});
        r82.addListener(new AnonymousClass23(this, r02, r2));
        r82.start();
        return;
    L9:
        r02.setTranslationX(0.0f);
        r02.setVisibility(0);
        if (r2 == r02) goto L13;
        r2.setVisibility(8);
        return;
    }

    public void startDocumentSelectActivity() {
        Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
        r0.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);     // Catch: Exception -> L4
        r0.setType("*/*");     // Catch: Exception -> L4
        startActivityForResult(r0, 21);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }
}
