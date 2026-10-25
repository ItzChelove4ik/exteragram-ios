package org.telegram.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.CameraScanActivity;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes6.dex */
public class ActionIntroActivity extends BaseFragment implements LocationController.LocationFetchCallback {
    private TextView buttonTextView;
    private int[] colors;
    private String currentGroupCreateAddress;
    private String currentGroupCreateDisplayAddress;
    private Location currentGroupCreateLocation;
    private final int currentType;
    private LinearLayout descriptionLayout;
    private final TextView[] descriptionLines;
    private TextView descriptionText;
    private TextView descriptionText2;
    private boolean flickerButton;
    private RLottieImageView imageView;
    private Runnable openedSettings;
    private ActionIntroQRLoginDelegate qrLoginDelegate;
    private boolean showingAsBottomSheet;
    private GradientDrawable startMessagingButtonBackground;
    private TextView subtitleTextView;
    private TextView titleTextView;

    /* renamed from: org.telegram.ui.ActionIntroActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ActionIntroActivity this$0;

        public AnonymousClass1(ActionIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionIntroActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewGroup {
        final /* synthetic */ ActionIntroActivity this$0;

        public AnonymousClass2(ActionIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r18, int r19, int r20, int r21, int r22) {
            if (ActionIntroActivity.access$200(this.this$0) == null) goto L5;
            ActionIntroActivity.access$400(this.this$0).layout(0, 0, r21, ActionIntroActivity.access$300(this.this$0).getMeasuredHeight());
        L5:
            int r3 = r21 - r19;
            int r5 = r22 - r20;
            int r6 = ActionIntroActivity.m5842$$Nest$fgetcurrentType(this.this$0);
            if (r6 != 0) goto L8;
            ActionIntroActivity r62 = this.this$0;
            if (r21 <= r22) goto L44;
            int r1 = (r5 - ActionIntroActivity.m5846$$Nest$fgetimageView(r62).getMeasuredHeight()) / 2;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(0, r1, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r1);
            float r12 = r3;
            float r9 = 0.4f * r12;
            int r2 = (int) r9;
            float r32 = r5;
            int r4 = (int) (0.22f * r32);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(r2, r4, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth() + r2, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r4);
            int r42 = (int) (0.39f * r32);
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(r2, r42, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth() + r2, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r42);
            int r13 = (int) (r9 + (((r12 * 0.6f) - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2.0f));
            int r23 = (int) (r32 * 0.69f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r13, r23, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r13, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r23);
            return;
        L44:
            float r14 = r5;
            int r24 = (int) (0.188f * r14);
            ActionIntroActivity.m5846$$Nest$fgetimageView(r62).layout(0, r24, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r24);
            int r25 = (int) (0.651f * r14);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r25, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r25);
            int r26 = (int) (0.731f * r14);
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(0, r26, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r26);
            int r33 = (r3 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r15 = (int) (r14 * 0.853f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r33, r15, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r33, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r15);
            return;
        L8:
            if (r6 != 3) goto L10;
            ActionIntroActivity r63 = this.this$0;
            if (r21 <= r22) goto L38;
            float r16 = r5;
            int r27 = ((int) ((0.95f * r16) - ActionIntroActivity.m5846$$Nest$fgetimageView(r63).getMeasuredHeight())) / 2;
            int r43 = (int) ((getWidth() * 0.35f) - ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth());
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(r43, r27, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth() + r43, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r27);
            float r28 = r3;
            float r92 = 0.4f * r28;
            int r34 = (int) r92;
            int r44 = (int) (0.12f * r16);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(r34, r44, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth() + r34, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r44);
            int r45 = (int) (0.24f * r16);
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(r34, r45, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth() + r34, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r45);
            float r29 = r28 * 0.6f;
            int r35 = (int) (((r29 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2.0f) + r92);
            int r17 = (int) (r16 * 0.8f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r35, r17, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r35, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r17);
            int r210 = (int) (r92 + ((r29 - ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredWidth()) / 2.0f));
            int r110 = r17 - (ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(16.0f));
            ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).layout(r210, r110, ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredWidth() + r210, ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredHeight() + r110);
            return;
        L38:
            int r111 = (int) (r5 * 0.3f);
            int r211 = (r3 - ActionIntroActivity.m5846$$Nest$fgetimageView(r63).getMeasuredWidth()) / 2;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(r211, r111, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth() + r211, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r111);
            int r112 = r111 + (ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(24.0f));
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r112, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r112);
            int r113 = (int) (r112 + (ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getTextSize() + AndroidUtilities.dp(16.0f)));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(0, r113, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r113);
            int r114 = (r3 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r52 = (r5 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r114, r52, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r114, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r52);
            int r36 = (r3 - ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredWidth()) / 2;
            int r53 = r52 - (ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(32.0f));
            ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).layout(r36, r53, ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredWidth() + r36, ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).getMeasuredHeight() + r53);
            return;
        L10:
            if (r6 == 5) goto L21;
            if (r6 == 6) goto L14;
            return;
        L14:
            ActionIntroActivity r64 = this.this$0;
            if (r21 <= r22) goto L18;
            int r115 = (r5 - ActionIntroActivity.m5846$$Nest$fgetimageView(r64).getMeasuredHeight()) / 2;
            float r212 = r3;
            int r37 = ((int) ((0.5f * r212) - ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth())) / 2;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(r37, r115, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth() + r37, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r115);
            float r93 = 0.4f * r212;
            int r116 = (int) r93;
            float r38 = r5;
            int r46 = (int) (0.14f * r38);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(r116, r46, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth() + r116, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r46);
            int r47 = (int) (0.31f * r38);
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(r116, r47, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth() + r116, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r47);
            int r117 = (int) (r93 + (((r212 * 0.6f) - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2.0f));
            int r213 = (int) (r38 * 0.78f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r117, r213, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r117, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r213);
            return;
        L18:
            int r118 = (int) (r5 * 0.3f);
            int r214 = (r3 - ActionIntroActivity.m5846$$Nest$fgetimageView(r64).getMeasuredWidth()) / 2;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(r214, r118, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth() + r214, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r118);
            int r119 = r118 + (ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(24.0f));
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r119, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r119);
            int r120 = (int) (r119 + (ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getTextSize() + AndroidUtilities.dp(16.0f)));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).layout(0, r120, ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r120);
            int r39 = (r3 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r54 = (r5 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r39, r54, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r39, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r54);
            return;
        L21:
            if (ActionIntroActivity.m5848$$Nest$fgetshowingAsBottomSheet(this.this$0) == false) goto L24;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(0, 0, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight());
            float r121 = r5;
            int r215 = (int) (0.403f * r121);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r215, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r215);
            int r216 = (int) (0.631f * r121);
            int r48 = (getMeasuredWidth() - ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth()) / 2;
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).layout(r48, r216, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth() + r48, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredHeight() + r216);
            int r310 = (r3 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r122 = (int) (r121 * 0.853f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r310, r122, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r310, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r122);
            return;
        L24:
            if (r21 <= r22) goto L27;
            int r123 = (r5 - ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight()) / 2;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).layout(0, r123, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r123);
            float r124 = r3;
            float r94 = 0.4f * r124;
            int r217 = (int) r94;
            float r311 = r5;
            int r49 = (int) (0.08f * r311);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(r217, r49, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth() + r217, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r49);
            float r125 = r124 * 0.6f;
            int r218 = (int) (((r125 - ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth()) / 2.0f) + r94);
            int r410 = (int) (0.25f * r311);
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).layout(r218, r410, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth() + r218, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredHeight() + r410);
            int r126 = (int) (r94 + ((r125 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2.0f));
            int r219 = (int) (r311 * 0.78f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r126, r219, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r126, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r219);
            return;
        L27:
            int r127 = AndroidUtilities.displaySize.y;
            ActionIntroActivity r220 = this.this$0;
            if (r127 >= 1800) goto L31;
            float r128 = r5;
            int r65 = (int) (0.06f * r128);
            ActionIntroActivity.m5846$$Nest$fgetimageView(r220).layout(0, r65, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r65);
            int r221 = (int) (0.463f * r128);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r221, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r221);
            float r129 = r128 * 0.543f;
        L30:
            int r130 = (int) r129;
            int r222 = (getMeasuredWidth() - ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth()) / 2;
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).layout(r222, r130, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredWidth() + r222, ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredHeight() + r130);
            int r312 = (r3 - ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r131 = (int) (r5 * 0.853f);
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).layout(r312, r131, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r312, ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r131);
            return;
        L31:
            float r132 = r5;
            int r66 = (int) (0.148f * r132);
            ActionIntroActivity.m5846$$Nest$fgetimageView(r220).layout(0, r66, ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r66);
            int r223 = (int) (0.551f * r132);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).layout(0, r223, ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r223);
            r129 = r132 * 0.631f;
            goto L30
        }

        @Override // android.view.View
        public void onMeasure(int r12, int r13) {
            int r122 = View.MeasureSpec.getSize(r12);
            int r0 = View.MeasureSpec.getSize(r13);
            if (ActionIntroActivity.access$000(this.this$0) == null) goto L5;
            ActionIntroActivity.access$100(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), r13);
        L5:
            int r132 = ActionIntroActivity.m5842$$Nest$fgetcurrentType(this.this$0);
            if (r132 != 0) goto L8;
            ActionIntroActivity r133 = this.this$0;
            if (r122 <= r0) goto L39;
            float r1 = r122;
            ActionIntroActivity.m5846$$Nest$fgetimageView(r133).measure(View.MeasureSpec.makeMeasureSpec((int) (0.45f * r1), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.68f), TLObject.FLAG_30));
            int r14 = (int) (r1 * 0.6f);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r14, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r14, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r14, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        L40:
            setMeasuredDimension(r122, r0);
            return;
        L39:
            ActionIntroActivity.m5846$$Nest$fgetimageView(r133).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.399f), TLObject.FLAG_30));
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122 - AndroidUtilities.dp(72.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L8:
            if (r132 != 3) goto L10;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(150.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(150.0f), TLObject.FLAG_30));
            ActionIntroActivity r134 = this.this$0;
            if (r122 <= r0) goto L35;
            float r15 = r122;
            ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(r134).measure(View.MeasureSpec.makeMeasureSpec((int) (0.45f * r15), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            int r16 = (int) (r15 * 0.6f);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r16, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r16, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r16, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L35:
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(r134).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5850$$Nest$fgetsubtitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122 - AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L10:
            if (r132 == 5) goto L26;
            if (r132 != 6) goto L40;
            int r135 = ActionIntroActivity.m5842$$Nest$fgetcurrentType(this.this$0);
            ActionIntroActivity r3 = this.this$0;
            if (r135 != 6) goto L17;
            ActionIntroActivity.m5846$$Nest$fgetimageView(r3).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(140.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(140.0f), TLObject.FLAG_30));
        L18:
            ActionIntroActivity r136 = this.this$0;
            if (r122 <= r0) goto L21;
            int r17 = (int) (r122 * 0.6f);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(r136).measure(View.MeasureSpec.makeMeasureSpec(r17, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r17, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r17, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L21:
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(r136).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5844$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            int r137 = ActionIntroActivity.m5842$$Nest$fgetcurrentType(this.this$0);
            ActionIntroActivity r32 = this.this$0;
            if (r137 != 6) goto L24;
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(r32).measure(View.MeasureSpec.makeMeasureSpec(r122 - AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L24:
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(r32).measure(View.MeasureSpec.makeMeasureSpec(r122 - AndroidUtilities.dp(72.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L17:
            ActionIntroActivity.m5846$$Nest$fgetimageView(r3).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), TLObject.FLAG_30));
            goto L18
        L26:
            if (ActionIntroActivity.m5848$$Nest$fgetshowingAsBottomSheet(this.this$0) == false) goto L28;
            ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.32f), TLObject.FLAG_30));
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            r0 = ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + ((((ActionIntroActivity.m5846$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight()) + AndroidUtilities.dp(20.0f)) + ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight()) + ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).getMeasuredHeight());
            goto L40
        L28:
            ActionIntroActivity r138 = this.this$0;
            if (r122 <= r0) goto L31;
            float r18 = r122;
            ActionIntroActivity.m5846$$Nest$fgetimageView(r138).measure(View.MeasureSpec.makeMeasureSpec((int) (0.45f * r18), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.68f), TLObject.FLAG_30));
            int r19 = (int) (r18 * 0.6f);
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r19, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r19, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r19, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        L31:
            ActionIntroActivity.m5846$$Nest$fgetimageView(r138).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.399f), TLObject.FLAG_30));
            ActionIntroActivity.m5851$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5843$$Nest$fgetdescriptionLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r0, 0));
            ActionIntroActivity.m5841$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r122, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            goto L40
        }
    }

    /* renamed from: org.telegram.ui.ActionIntroActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends TextView {
        private final CellFlickerDrawable cellFlickerDrawable;
        final /* synthetic */ ActionIntroActivity this$0;

        public AnonymousClass3(ActionIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            CellFlickerDrawable r12 = new CellFlickerDrawable();
            this.cellFlickerDrawable = r12;
            r12.drawFrame = false;
            r12.repeatProgress = 2.0f;
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            ActionIntroActivity.m5849$$Nest$fgetstartMessagingButtonBackground(this.this$0).draw(r2);
            super.draw(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            if (ActionIntroActivity.m5845$$Nest$fgetflickerButton(this.this$0) == false) goto L6;
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.cellFlickerDrawable.draw(r5, r0, getMeasuredHeight() / 2.0f, null);
            invalidate();
            return;
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            ActionIntroActivity.m5849$$Nest$fgetstartMessagingButtonBackground(this.this$0).setBounds(0, 0, r1, r2);
            ActionIntroActivity.m5849$$Nest$fgetstartMessagingButtonBackground(this.this$0).setCornerRadius(Math.min(r1, r2) / 2.0f);
            this.cellFlickerDrawable.setParentWidth(r1);
        }
    }

    /* renamed from: org.telegram.ui.ActionIntroActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements CameraScanActivity.CameraScanActivityDelegate {
        final /* synthetic */ ActionIntroActivity this$0;

        public AnonymousClass4(ActionIntroActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.CameraScanActivity.CameraScanActivityDelegate
        public void didFindQr(String r3) {
            this.this$0.finishFragment(false);
            ActionIntroActivity.m5847$$Nest$fgetqrLoginDelegate(this.this$0).didFindQRCode(r3);
        }
    }

    public interface ActionIntroQRLoginDelegate {
        void didFindQRCode(String r1);
    }

    public static /* synthetic */ void $r8$lambda$0W_D23CAOK43aXjhiheop9irU9U(ActionIntroActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$2AWmHvf9fwcrKzJ0tM5V1VtYjwg(ActionIntroActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$2RLFI0sle7U9co1HUhkw7funmqE(ActionIntroActivity r0) {
        r0.updateColors();
    }

    /* renamed from: $r8$lambda$BYtI1cHO7zR1cIm7s8-6c2saLp8, reason: not valid java name */
    public static /* synthetic */ void m5839$r8$lambda$BYtI1cHO7zR1cIm7s86c2saLp8(ActionIntroActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$S7SPeGtwY693T05I0_9VlLYRJ84(ActionIntroActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$SamJWdYl9lPGaCorXTiRCQP35lI(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$ctiW8_eL0KIxPnRMWxb8zD_p7l8(ActionIntroActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultFragment$6(r1, r2);
    }

    /* renamed from: $r8$lambda$j5QD5MHEIN_Xz-K3iwtXjz-qRjg, reason: not valid java name */
    public static /* synthetic */ void m5840$r8$lambda$j5QD5MHEIN_XzK3iwtXjzqRjg(ActionIntroActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: -$$Nest$fgetbuttonTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5841$$Nest$fgetbuttonTextView(ActionIntroActivity r0) {
        return r0.buttonTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5842$$Nest$fgetcurrentType(ActionIntroActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdescriptionLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m5843$$Nest$fgetdescriptionLayout(ActionIntroActivity r0) {
        return r0.descriptionLayout;
    }

    /* renamed from: -$$Nest$fgetdescriptionText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5844$$Nest$fgetdescriptionText(ActionIntroActivity r0) {
        return r0.descriptionText;
    }

    /* renamed from: -$$Nest$fgetflickerButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5845$$Nest$fgetflickerButton(ActionIntroActivity r0) {
        return r0.flickerButton;
    }

    /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m5846$$Nest$fgetimageView(ActionIntroActivity r0) {
        return r0.imageView;
    }

    /* renamed from: -$$Nest$fgetqrLoginDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionIntroQRLoginDelegate m5847$$Nest$fgetqrLoginDelegate(ActionIntroActivity r0) {
        return r0.qrLoginDelegate;
    }

    /* renamed from: -$$Nest$fgetshowingAsBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5848$$Nest$fgetshowingAsBottomSheet(ActionIntroActivity r0) {
        return r0.showingAsBottomSheet;
    }

    /* renamed from: -$$Nest$fgetstartMessagingButtonBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientDrawable m5849$$Nest$fgetstartMessagingButtonBackground(ActionIntroActivity r0) {
        return r0.startMessagingButtonBackground;
    }

    /* renamed from: -$$Nest$fgetsubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5850$$Nest$fgetsubtitleTextView(ActionIntroActivity r0) {
        return r0.subtitleTextView;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m5851$$Nest$fgettitleTextView(ActionIntroActivity r0) {
        return r0.titleTextView;
    }

    public ActionIntroActivity(int r2) {
        this.descriptionLines = new TextView[6];
        this.currentType = r2;
    }

    public static /* synthetic */ ActionBar access$000(ActionIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ActionIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(ActionIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(ActionIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(ActionIntroActivity r0) {
        return r0.actionBar;
    }

    private /* synthetic */ void lambda$createView$1(AlertDialog r1, int r2) {
        presentFragment(new LoginActivity().changePhoneNumber(), true);
    }

    private /* synthetic */ void lambda$createView$2(View r4) {
        if (getParentActivity() == null) goto L32;
        int r42 = this.currentType;
        if (r42 != 0) goto L8;
        Bundle r43 = new Bundle();
        r43.putInt("step", 0);
        presentFragment(new ChannelCreateActivity(r43), true);
        return;
    L8:
        if (r42 != 3) goto L10;
        AlertDialog.Builder r44 = new AlertDialog.Builder(getParentActivity());
        r44.setTitle(LocaleController.getString(R.string.PhoneNumberChangeTitle));
        r44.setMessage(LocaleController.getString(R.string.PhoneNumberAlert));
        r44.setPositiveButton(LocaleController.getString(R.string.Change), new ActionIntroActivity$$ExternalSyntheticLambda7(this));
        r44.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r44.create());
        return;
    L10:
        if (r42 == 5) goto L19;
        if (r42 != 6) goto L33;
        presentFragment(new PasscodeActivity(1), true);
        Runnable r45 = this.openedSettings;
        if (r45 == null) goto L20;
        AndroidUtilities.runOnUIThread(r45);
        this.openedSettings = null;
        return;
    L20:
        return;
    L33:
        return;
    L19:
        if (getParentActivity() != null) goto L22;
        return;
    L22:
        if (getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L25;
        getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 34);
        return;
    L25:
        processOpenQrReader();
        return;
    }

    private /* synthetic */ void lambda$createView$3(View r2) {
        if (this.imageView.getAnimatedDrawable().isRunning() == true) goto L6;
        this.imageView.getAnimatedDrawable().setCurrentFrame(0, false);
        this.imageView.playAnimation();
        return;
    }

    private /* synthetic */ void lambda$createView$4(View r2) {
        if (this.imageView.getAnimatedDrawable().isRunning() == true) goto L6;
        this.imageView.getAnimatedDrawable().setCurrentFrame(0, false);
        this.imageView.playAnimation();
        return;
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        getParentLayout().closeLastFragment(true);
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultFragment$6(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        getParentActivity().startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private void processOpenQrReader() {
        CameraScanActivity.showAsSheet(this, false, 1, new AnonymousClass4(this));
    }

    private void updateColors() {
        GradientDrawable r0 = this.startMessagingButtonBackground;
        int r1 = Theme.key_featuredStickers_addButton;
        r0.setColors(new int[]{getThemedColor(r1), getThemedColor(Theme.key_featuredStickers_addButton2)});
        this.buttonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), 0, Theme.getColor(Theme.key_featuredStickers_addButtonPressed)));
        int[] r02 = this.colors;
        if (r02 != null) goto L5;
        return;
    L5:
        if (this.imageView == null) goto L10;
        r02[0] = 3355443;
        r02[1] = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText);
        int[] r03 = this.colors;
        r03[2] = 16777215;
        int r2 = Theme.key_windowBackgroundWhite;
        r03[3] = Theme.getColor(r2);
        int[] r04 = this.colors;
        r04[4] = 5285866;
        r04[5] = Theme.getColor(r1);
        int[] r05 = this.colors;
        r05[6] = 2170912;
        r05[7] = Theme.getColor(r2);
        this.imageView.replaceColors(this.colors);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r30) {
        ActionBar r2 = this.actionBar;
        if (r2 == null) goto L5;
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_actionBarDefaultIcon), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSelector), false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
    L5:
        AnonymousClass2 r22 = new AnonymousClass2(this, r30);
        this.fragmentView = r22;
        r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        ViewGroup r23 = (ViewGroup) this.fragmentView;
        r23.setOnTouchListener(new ActionIntroActivity$$ExternalSyntheticLambda1());
        ActionBar r4 = this.actionBar;
        if (r4 == null) goto L8;
        r23.addView(r4);
    L8:
        RLottieImageView r42 = new RLottieImageView(r30);
        this.imageView = r42;
        r23.addView(r42);
        TextView r43 = new TextView(r30);
        this.titleTextView = r43;
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r43.setTextColor(Theme.getColor(r5));
        this.titleTextView.setGravity(1);
        this.titleTextView.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        float r8 = 24.0f;
        this.titleTextView.setTextSize(1, 24.0f);
        r23.addView(this.titleTextView);
        TextView r44 = new TextView(r30);
        this.subtitleTextView = r44;
        int r10 = 3;
        if (this.currentType != 3) goto L11;
        r5 = Theme.key_featuredStickers_addButton;
    L11:
        r44.setTextColor(Theme.getColor(r5));
        this.subtitleTextView.setGravity(1);
        float r52 = 15.0f;
        this.subtitleTextView.setTextSize(1, 15.0f);
        this.subtitleTextView.setSingleLine(true);
        this.subtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.subtitleTextView.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.subtitleTextView.setVisibility(8);
        r23.addView(this.subtitleTextView);
        TextView r45 = new TextView(r30);
        this.descriptionText = r45;
        r45.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        this.descriptionText.setGravity(1);
        float r11 = 2.0f;
        this.descriptionText.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.descriptionText.setTextSize(1, 15.0f);
        int r46 = this.currentType;
        if (r46 == 6) goto L16;
        if (r46 == 3) goto L16;
        this.descriptionText.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
    L17:
        r23.addView(this.descriptionText);
        if (this.currentType != 5) goto L60;
        LinearLayout r47 = new LinearLayout(r30);
        this.descriptionLayout = r47;
        r47.setOrientation(1);
        float r16 = 32.0f;
        this.descriptionLayout.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        LinearLayout r48 = this.descriptionLayout;
        if (LocaleController.isRTL == false) goto L22;
        int r7 = 5;
    L23:
        r48.setGravity(r7);
        r23.addView(this.descriptionLayout);
        int r49 = 0;
    L24:
        if (r49 >= r10) goto L59;
        LinearLayout r72 = new LinearLayout(r30);
        r72.setOrientation(0);
        LinearLayout r15 = this.descriptionLayout;
        float r17 = r8;
        if (r49 == 2) goto L29;
        float r82 = 7.0f;
    L30:
        r15.addView(r72, LayoutHelper.createLinear(-2, -2, 0.0f, 0.0f, 0.0f, r82));
        int r152 = r49 * 2;
        float r18 = r11;
        this.descriptionLines[r152] = new TextView(r30);
        TextView r83 = this.descriptionLines[r152];
        int r112 = Theme.key_windowBackgroundWhiteBlackText;
        r83.setTextColor(Theme.getColor(r112));
        TextView r84 = this.descriptionLines[r152];
        if (LocaleController.isRTL == false) goto L33;
        int r12 = 5;
    L34:
        r84.setGravity(r12);
        this.descriptionLines[r152].setTextSize(1, r52);
        TextView r85 = this.descriptionLines[r152];
        if (LocaleController.isRTL == false) goto L37;
        String r122 = ".%d";
    L38:
        int r20 = r49 + 1;
        r85.setText(String.format(r122, new Object[]{Integer.valueOf(r20)}));
        this.descriptionLines[r152].setTypeface(AndroidUtilities.bold());
        int r102 = r152 + 1;
        this.descriptionLines[r102] = new TextView(r30);
        this.descriptionLines[r102].setTextColor(Theme.getColor(r112));
        TextView r86 = this.descriptionLines[r102];
        if (LocaleController.isRTL == false) goto L41;
        int r113 = 5;
    L42:
        r86.setGravity(r113);
        this.descriptionLines[r102].setTextSize(1, r52);
        if (r49 != 0) goto L50;
        this.descriptionLines[r102].setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.descriptionLines[r102].setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection));
        String r410 = LocaleController.getString(R.string.AuthAnotherClientInfo1);
        SpannableStringBuilder r87 = new SpannableStringBuilder(r410);
        int r123 = r410.indexOf(42);
        int r411 = r410.lastIndexOf(42);
        if (r123 == (-1)) goto L49;
        if (r411 == (-1)) goto L49;
        if (r123 == r411) goto L49;
        this.descriptionLines[r102].setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r87.replace(r411, r411 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r87.replace(r123, r123 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
        r87.setSpan(new URLSpanNoUnderline(LocaleController.getString(R.string.AuthAnotherClientDownloadClientUrl)), r123, r411 - 1, 33);
    L49:
        this.descriptionLines[r102].setText(r87);
    L55:
        if (LocaleController.isRTL == false) goto L57;
        r72.setGravity(5);
        r72.addView(this.descriptionLines[r102], LayoutHelper.createLinear(0, -2, 1.0f));
        r72.addView(this.descriptionLines[r152], LayoutHelper.createLinear(-2, -2, 4.0f, 0.0f, 0.0f, 0.0f));
    L58:
        r8 = r17;
        r11 = r18;
        r49 = r20;
        r52 = 15.0f;
        r10 = 3;
        goto L24
    L57:
        r72.addView(this.descriptionLines[r152], LayoutHelper.createLinear(-2, -2, 0.0f, 0.0f, 4.0f, 0.0f));
        r72.addView(this.descriptionLines[r102], LayoutHelper.createLinear(-2, -2));
        goto L58
    L50:
        TextView[] r53 = this.descriptionLines;
        if (r49 != 1) goto L53;
        r53[r102].setText(LocaleController.getString(R.string.AuthAnotherClientInfo2));
        goto L55
    L53:
        r53[r102].setText(LocaleController.getString(R.string.AuthAnotherClientInfo3));
        goto L55
    L41:
        r113 = 3;
        goto L42
    L37:
        r122 = "%d.";
        goto L38
    L33:
        r12 = r10;
        goto L34
    L29:
        r82 = 0.0f;
        goto L30
    L59:
        float r172 = r8;
        float r182 = r11;
        this.descriptionText.setVisibility(8);
    L61:
        TextView r412 = new TextView(r30);
        this.descriptionText2 = r412;
        r412.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        this.descriptionText2.setGravity(1);
        this.descriptionText2.setLineSpacing(AndroidUtilities.dp(r182), 1.0f);
        this.descriptionText2.setTextSize(1, 13.0f);
        this.descriptionText2.setVisibility(8);
        this.descriptionText2.setPadding(AndroidUtilities.dp(r16), 0, AndroidUtilities.dp(r16), 0);
        r23.addView(this.descriptionText2);
        this.startMessagingButtonBackground = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        AnonymousClass3 r413 = new AnonymousClass3(this, r30);
        this.buttonTextView = r413;
        ScaleStateListAnimator.apply(r413, 0.02f, 1.2f);
        this.buttonTextView.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setTextSize(1, 14.0f);
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.buttonTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(r172), 0, Theme.getColor(Theme.key_featuredStickers_addButtonPressed)));
        r23.addView(this.buttonTextView);
        this.buttonTextView.setOnClickListener(new ActionIntroActivity$$ExternalSyntheticLambda2(this));
        int r1 = this.currentType;
        if (r1 != 0) goto L64;
        this.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.imageView.setAnimation(R.raw.channel_create, 200, 200);
        this.titleTextView.setText(LocaleController.getString(R.string.ChannelAlertTitle));
        this.descriptionText.setText(LocaleController.getString(R.string.ChannelAlertText));
        this.buttonTextView.setText(LocaleController.getString(R.string.ChannelAlertCreate2));
        this.imageView.playAnimation();
        this.flickerButton = true;
    L79:
        if (this.flickerButton == false) goto L81;
        this.buttonTextView.setPadding(AndroidUtilities.dp(34.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(34.0f), AndroidUtilities.dp(8.0f));
        this.buttonTextView.setTextSize(1, 15.0f);
    L81:
        updateColors();
        return this.fragmentView;
    L64:
        if (r1 == 3) goto L71;
        if (r1 != 5) goto L67;
        int[] r13 = new int[8];
        this.colors = r13;
        this.imageView.setAnimation(R.raw.qr_login, 334, 334, r13);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.titleTextView.setText(LocaleController.getString(R.string.AuthAnotherClient));
        this.buttonTextView.setText(LocaleController.getString(R.string.AuthAnotherClientScan));
        this.imageView.playAnimation();
        goto L79
    L67:
        if (r1 != 6) goto L79;
        this.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.imageView.setAnimation(R.raw.utyan_passcode, 200, 200);
        this.imageView.setFocusable(false);
        this.imageView.setOnClickListener(new ActionIntroActivity$$ExternalSyntheticLambda3(this));
        this.titleTextView.setText(LocaleController.getString(R.string.Passcode));
        this.descriptionText.setText(LocaleController.getString(R.string.ChangePasscodeInfoShort));
        this.buttonTextView.setText(LocaleController.getString(R.string.EnablePasscode));
        this.imageView.playAnimation();
        this.flickerButton = true;
        goto L79
    L71:
        this.subtitleTextView.setVisibility(0);
        this.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.imageView.setAnimation(R.raw.utyan_change_number, 200, 200);
        this.imageView.setOnClickListener(new ActionIntroActivity$$ExternalSyntheticLambda4(this));
        UserConfig r14 = getUserConfig();
        TLRPC.User r24 = getMessagesController().getUser(Long.valueOf(r14.clientUserId));
        if (r24 != null) goto L74;
        r24 = r14.getCurrentUser();
    L74:
        if (r24 == null) goto L76;
        this.subtitleTextView.setText(LocaleController.formatString("PhoneNumberKeepButton", R.string.PhoneNumberKeepButton, new Object[]{PhoneFormat.getInstance().format("+" + r24.phone)}));
    L76:
        this.subtitleTextView.setOnClickListener(new ActionIntroActivity$$ExternalSyntheticLambda5(this));
        this.titleTextView.setText(LocaleController.getString(R.string.PhoneNumberChange2));
        this.descriptionText.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PhoneNumberHelp)));
        this.buttonTextView.setText(LocaleController.getString(R.string.PhoneNumberChange2));
        this.imageView.playAnimation();
        this.flickerButton = true;
        goto L79
    L22:
        r7 = 3;
        goto L23
    L60:
        r16 = 32.0f;
        r172 = 24.0f;
        r182 = 2.0f;
    L16:
        this.descriptionText.setPadding(AndroidUtilities.dp(48.0f), 0, AndroidUtilities.dp(48.0f), 0);
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ActionIntroActivity$$ExternalSyntheticLambda6 r8 = new ActionIntroActivity$$ExternalSyntheticLambda6(this);
        View r3 = this.fragmentView;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r16 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r3, r4, null, null, null, r8, r16));
        if (this.actionBar == null) goto L5;
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
    L5:
        TextView r32 = this.titleTextView;
        int r42 = ThemeDescription.FLAG_TEXTCOLOR;
        int r162 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r32, r42, null, null, null, r8, r162));
        r1.add(new ThemeDescription(this.subtitleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText6));
        r1.add(new ThemeDescription(this.descriptionLines[0], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionLines[1], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionLines[1], ThemeDescription.FLAG_LINKCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r1.add(new ThemeDescription(this.descriptionLines[2], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionLines[3], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionLines[4], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.descriptionLines[5], ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.messenger.LocationController.LocationFetchCallback
    public void onLocationAddressAvailable(String r1, String r2, TLRPC.TL_messageMediaVenue r3, TLRPC.TL_messageMediaVenue r4, Location r5) {
        TextView r32 = this.subtitleTextView;
        if (r32 != null) goto L5;
        return;
    L5:
        r32.setText(r1);
        this.currentGroupCreateAddress = r1;
        this.currentGroupCreateDisplayAddress = r2;
        this.currentGroupCreateLocation = r5;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r2, String[] r3, int[] r4) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (r2 == 34) goto L8;
        return;
    L8:
        if (r4.length > 0) goto L10;
    L13:
        new AlertDialog.Builder(getParentActivity()).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.QRCodePermissionNoCameraWithHint))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new ActionIntroActivity$$ExternalSyntheticLambda0(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).setTopAnimation(R.raw.permission_request_camera, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).show();
        return;
    L10:
        if (r4[0] != 0) goto L13;
        processOpenQrReader();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
    }

    public void setOnOpenedSettings(Runnable r1) {
        this.openedSettings = r1;
    }

    public void setQrLoginDelegate(ActionIntroQRLoginDelegate r1) {
        this.qrLoginDelegate = r1;
    }
}
