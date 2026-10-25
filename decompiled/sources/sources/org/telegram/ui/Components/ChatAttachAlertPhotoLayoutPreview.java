package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class ChatAttachAlertPhotoLayoutPreview extends ChatAttachAlert.AttachAlertLayout {
    private static HashMap<MediaController.PhotoEntry, Boolean> photoRotate;
    private ValueAnimator draggingAnimator;
    private PreviewGroupsView.PreviewGroupCell.MediaCell draggingCell;
    private float draggingCellFromHeight;
    private float draggingCellFromWidth;
    private float draggingCellGroupY;
    private boolean draggingCellHiding;
    private float draggingCellLeft;
    private float draggingCellTop;
    private float draggingCellTouchX;
    private float draggingCellTouchY;
    private final long durationMultiplier;
    private PreviewGroupsView groupsView;
    public TextView header;
    private ViewPropertyAnimator headerAnimator;
    private boolean ignoreLayout;
    private boolean isPortrait;
    private LinearLayoutManager layoutManager;
    public RecyclerListView listView;
    private int paddingTop;
    private ChatAttachAlertPhotoLayout photoLayout;
    private boolean shown;
    private Theme.ResourcesProvider themeDelegate;
    private UndoView undoView;
    private Drawable videoPlayImage;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBarMenuItem {
        final /* synthetic */ ChatAttachAlertPhotoLayoutPreview this$0;

        public AnonymousClass1(ChatAttachAlertPhotoLayoutPreview r1, Context r2, ActionBarMenu r3, int r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setText(this.this$0.header.getText());
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertPhotoLayoutPreview this$0;

        public AnonymousClass2(ChatAttachAlertPhotoLayoutPreview r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L7;
            return false;
        L7:
            return super.onInterceptTouchEvent(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r4, int r5) {
            this.this$0.invalidate();
            ChatAttachAlertPhotoLayoutPreview r0 = this.this$0;
            r0.parentAlert.updateLayout(r0, true, r5);
            ChatAttachAlertPhotoLayoutPreview.m9789$$Nest$fgetgroupsView(this.this$0).onScroll();
            super.onScrolled(r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.Adapter {
        final /* synthetic */ ChatAttachAlertPhotoLayoutPreview this$0;

        public AnonymousClass3(ChatAttachAlertPhotoLayoutPreview r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return new RecyclerListView.Holder(ChatAttachAlertPhotoLayoutPreview.m9789$$Nest$fgetgroupsView(this.this$0));
        }
    }

    public class GroupCalculator {
        float height;
        private final int maxSizeWidth;
        int maxX;
        int maxY;
        ArrayList<MediaController.PhotoEntry> photos;
        public ArrayList<MessageObject.GroupedMessagePosition> posArray;
        public HashMap<MediaController.PhotoEntry, MessageObject.GroupedMessagePosition> positions;
        final /* synthetic */ ChatAttachAlertPhotoLayoutPreview this$0;
        int width;

        public class MessageGroupedLayoutAttempt {
            public float[] heights;
            public int[] lineCounts;
            final /* synthetic */ GroupCalculator this$1;

            public MessageGroupedLayoutAttempt(GroupCalculator r1, int r2, int r3, float r4, float r5) {
                this.this$1 = r1;
                this.lineCounts = new int[]{r2, r3};
                this.heights = new float[]{r4, r5};
            }

            public MessageGroupedLayoutAttempt(GroupCalculator r1, int r2, int r3, int r4, float r5, float r6, float r7) {
                this.this$1 = r1;
                this.lineCounts = new int[]{r2, r3, r4};
                this.heights = new float[]{r5, r6, r7};
            }

            public MessageGroupedLayoutAttempt(GroupCalculator r1, int r2, int r3, int r4, int r5, float r6, float r7, float r8, float r9) {
                this.this$1 = r1;
                this.lineCounts = new int[]{r2, r3, r4, r5};
                this.heights = new float[]{r6, r7, r8, r9};
            }
        }

        public GroupCalculator(ChatAttachAlertPhotoLayoutPreview r1, ArrayList<MediaController.PhotoEntry> r2) {
            this.this$0 = r1;
            this.posArray = new ArrayList();
            this.positions = new HashMap();
            this.maxSizeWidth = 1000;
            this.photos = r2;
            calculate();
        }

        private float getLeft(MessageObject.GroupedMessagePosition r12, int r13, int r14, int r15) {
            int r0 = (r14 - r13) + 1;
            float[] r1 = new float[r0];
            float r2 = 0.0f;
            Arrays.fill(r1, 0.0f);
            int r3 = this.posArray.size();
            int r4 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r3) goto L12;
            MessageObject.GroupedMessagePosition r6 = this.posArray.get(r5);
            if (r6 == r12) goto L11;
            if (r6.maxX >= r15) goto L11;
            int r7 = Math.min(r6.maxY, r14) - r13;
            int r8 = Math.max(r6.minY - r13, 0);
        L9:
            if (r8 > r7) goto L11;
            r1[r8] = r1[r8] + r6.pw;
            r8 = r8 + 1;
        L11:
            r5 = r5 + 1;
        L12:
            if (r4 >= r0) goto L17;
            float r11 = r1[r4];
            if (r2 >= r11) goto L16;
            r2 = r11;
        L16:
            r4 = r4 + 1;
            goto L12
        L17:
            return r2;
        }

        private float getTop(MessageObject.GroupedMessagePosition r11, int r12) {
            int r0 = this.maxX + 1;
            float[] r1 = new float[r0];
            float r2 = 0.0f;
            Arrays.fill(r1, 0.0f);
            int r3 = this.posArray.size();
            int r4 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r3) goto L13;
            MessageObject.GroupedMessagePosition r6 = this.posArray.get(r5);
            if (r6 == r11) goto L12;
            if (r6.maxY >= r12) goto L12;
            int r7 = r6.minX;
        L10:
            if (r7 > r6.maxX) goto L12;
            r1[r7] = r1[r7] + r6.ph;
            r7 = r7 + 1;
        L12:
            r5 = r5 + 1;
        L13:
            if (r4 >= r0) goto L18;
            float r10 = r1[r4];
            if (r2 >= r10) goto L17;
            r2 = r10;
        L17:
            r4 = r4 + 1;
            goto L13
        L18:
            return r2;
        }

        private float multiHeight(float[] r2, int r3, int r4) {
            float r1 = 0.0f;
        L3:
            if (r3 >= r4) goto L6;
            r1 = r1 + r2[r3];
            r3 = r3 + 1;
            goto L3
        L6:
            return 1000.0f / r1;
        }

        public void calculate() {
            int r10 = this.photos.size();
            this.posArray.clear();
            this.positions.clear();
            int r12 = 0;
            if (r10 != 0) goto L6;
            this.width = 0;
            this.height = 0.0f;
            this.maxX = 0;
            this.maxY = 0;
            return;
        L6:
            this.posArray.ensureCapacity(r10);
            char[] r0 = new char[r10];
            int r3 = 0;
            boolean r5 = false;
            float r4 = 1.0f;
        L8:
            if (r3 >= r10) goto L59;
            MediaController.PhotoEntry r9 = this.photos.get(r3);
            MessageObject.GroupedMessagePosition r15 = new MessageObject.GroupedMessagePosition();
            if (r3 != (r10 - 1)) goto L12;
            boolean r7 = true;
        L13:
            r15.last = r7;
            MediaController.CropState r72 = r9.cropState;
            if (r72 == null) goto L16;
            int r11 = r72.width;
        L17:
            if (r72 == null) goto L20;
            int r73 = r72.height;
        L22:
            if (ChatAttachAlertPhotoLayoutPreview.m9804$$Nest$sfgetphotoRotate().containsKey(r9) == false) goto L241;
            boolean r13 = ((Boolean) ChatAttachAlertPhotoLayoutPreview.m9804$$Nest$sfgetphotoRotate().get(r9)).booleanValue();
        L46:
            if (r13 == false) goto L48;
            int r33 = r11;
            r11 = r73;
            r73 = r33;
        L48:
            float r2 = r11 / r73;
            r15.aspectRatio = r2;
            if (r2 <= 1.2f) goto L52;
            char r6 = 'w';
        L55:
            r0[r3] = r6;
            r4 = r4 + r2;
            if (r2 <= 2.0f) goto L58;
            r5 = true;
        L58:
            this.positions.put(r9, r15);
            this.posArray.add(r15);
            r3 = r3 + 1;
            goto L8
        L52:
            if (r2 >= 0.8f) goto L54;
            r6 = 'n';
            goto L55
        L54:
            r6 = 'q';
            goto L55
        L241:
            if (r9.isVideo == false) goto L36;
            MediaMetadataRetriever r132 = new MediaMetadataRetriever();     // Catch: Exception -> L35
            r132.setDataSource(r9.path);     // Catch: Exception -> L240
            String r22 = r132.extractMetadata(24);     // Catch: Exception -> L240
            if (r22 != null) goto L31;
        L41:
            boolean r23 = false;
        L43:
            r13 = r23;
        L45:
            ChatAttachAlertPhotoLayoutPreview.m9804$$Nest$sfgetphotoRotate().put(r9, Boolean.valueOf(r13));
            goto L46
        L31:
            if (r22.equals("90") == false) goto L33;
        L42:
            r23 = true;
            goto L43
        L33:
            if (r22.equals("270") == false) goto L41;
        L36:
            int r24 = new ExifInterface(r9.path).getAttributeInt("Orientation", 1);     // Catch: Exception -> L240
            if (r24 == 6) goto L42;
            if (r24 == 8) goto L42;
        L44:
            r13 = false;
            goto L45
        L20:
            r73 = r9.height;
            goto L22
        L16:
            r11 = r9.width;
            goto L17
        L12:
            r7 = false;
            goto L13
        L59:
            float r19 = 1.0f;
            String r25 = new String(r0);
            int r32 = AndroidUtilities.dp(120.0f);
            float r02 = AndroidUtilities.dp(120.0f);
            Point r74 = AndroidUtilities.displaySize;
            int r112 = (int) (r02 / (Math.min(r74.x, r74.y) / 1000.0f));
            float r03 = AndroidUtilities.dp(40.0f);
            Point r75 = AndroidUtilities.displaySize;
            int r04 = (int) (r03 / (Math.min(r75.x, r75.y) / 1000.0f));
            float r92 = r4 / r10;
            float r152 = AndroidUtilities.dp(100.0f) / 814.0f;
            if (r10 != 1) goto L62;
            MessageObject.GroupedMessagePosition r05 = this.posArray.get(0);
            int r26 = AndroidUtilities.displaySize.x - (this.this$0.parentAlert.getBackgroundPaddingLeft() * 2);
            Point r34 = AndroidUtilities.displaySize;
            r05.set(0, 0, 0, 0, 800, ((r26 * 0.8f) / r05.aspectRatio) / (Math.max(r34.x, r34.y) * 0.5f), 15);
            int r133 = 2;
            int r20 = 0;
        L225:
            int r76 = r20;
        L226:
            if (r76 >= r10) goto L235;
            MessageObject.GroupedMessagePosition r06 = this.posArray.get(r76);
            if (r06.minX != 0) goto L231;
            r06.spanSize += 200;
        L231:
            if ((r06.flags & r133) == 0) goto L234;
            r06.edge = true;
        L234:
            this.maxX = Math.max(this.maxX, r06.maxX);
            this.maxY = Math.max(this.maxY, r06.maxY);
            r06.left = getLeft(r06, r06.minY, r06.maxY, r06.minX);
            r76 = r76 + 1;
            goto L226
        L235:
            int r122 = r20;
        L236:
            if (r122 >= r10) goto L238;
            MessageObject.GroupedMessagePosition r07 = this.posArray.get(r122);
            r07.top = getTop(r07, r07.minY);
            r122 = r122 + 1;
            goto L236
        L238:
            this.width = getWidth();
            this.height = getHeight();
            return;
        L62:
            int r42 = 4;
            int r62 = 3;
            if (r5 == true) goto L68;
            if (r10 == 2) goto L70;
            if (r10 == 3) goto L70;
            if (r10 != 4) goto L68;
        L70:
            if (r10 != 2) goto L90;
            MessageObject.GroupedMessagePosition r08 = this.posArray.get(0);
            MessageObject.GroupedMessagePosition r35 = this.posArray.get(1);
            if (r25.equals("ww") == false) goto L79;
            if (r92 <= 1.71990168094635d) goto L79;
            float r8 = r08.aspectRatio;
            float r93 = r35.aspectRatio;
            if ((r8 - r93) >= 0.2d) goto L79;
            float r29 = Math.round(Math.min(1000.0f / r8, Math.min(1000.0f / r93, 407.0f))) / 814.0f;
            r08.set(0, 0, 0, 0, 1000, r29, 7);
            r35.set(0, 0, 1, 1, 1000, r29, 11);
        L89:
            r20 = 0;
            r133 = 2;
        L79:
            if (r25.equals("ww") == false) goto L81;
        L88:
            float r292 = Math.round(Math.min(500.0f / r08.aspectRatio, Math.min(500.0f / r35.aspectRatio, 814.0f))) / 814.0f;
            r08.set(0, 0, 0, 0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r292, 13);
            r35.set(1, 1, 0, 0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r292, 14);
            goto L89
        L81:
            if (r25.equals("qq") == true) goto L88;
            float r27 = r08.aspectRatio;
            int r28 = (int) Math.max(400.0f, Math.round((1000.0f / r27) / ((1.0f / r27) + (1.0f / r35.aspectRatio))));
            int r43 = 1000 - r28;
            if (r43 >= r112) goto L86;
            r28 = r28 - (r112 - r43);
        L87:
            float r293 = Math.min(814.0f, Math.round(Math.min(r112 / r08.aspectRatio, r28 / r35.aspectRatio))) / 814.0f;
            r08.set(0, 0, 0, 0, r112, r293, 13);
            r35.set(1, 1, 0, 0, r28, r293, 14);
            goto L89
        L86:
            r112 = r43;
            goto L87
        L90:
            ArrayList<MessageObject.GroupedMessagePosition> r77 = this.posArray;
            if (r10 != 3) goto L100;
            MessageObject.GroupedMessagePosition r36 = r77.get(0);
            MessageObject.GroupedMessagePosition r44 = this.posArray.get(1);
            MessageObject.GroupedMessagePosition r63 = this.posArray.get(2);
            if (r25.charAt(0) != 'n') goto L95;
            float r210 = r44.aspectRatio;
            float r211 = Math.min(407.0f, Math.round((r210 * 1000.0f) / (r63.aspectRatio + r210)));
            int r78 = (int) Math.max(r112, Math.min(500.0f, Math.round(Math.min(r63.aspectRatio * r211, r44.aspectRatio * r5))));
            float r82 = (r36.aspectRatio * 814.0f) + r04;
            int r09 = 1000 - r78;
            r36.set(0, 0, 0, 1, Math.round(Math.min(r82, r09)), 1.0f, 13);
            float r294 = (814.0f - r211) / 814.0f;
            r44.set(1, 1, 0, 0, r78, r294, 6);
            float r295 = r211 / 814.0f;
            r63.set(1, 1, 1, 1, r78, r295, 10);
            r63.spanSize = 1000;
            r36.siblingHeights = new float[]{r295, r294};
            r36.spanSize = r09;
            goto L89
        L95:
            float r296 = Math.round(Math.min(1000.0f / r36.aspectRatio, 537.24005f)) / 814.0f;
            r36.set(0, 1, 0, 0, 1000, r296, 7);
            float r010 = Math.min(814.0f - r296, Math.round(Math.min(500.0f / r44.aspectRatio, 500.0f / r63.aspectRatio))) / 814.0f;
            if (r010 >= r152) goto L98;
            float r297 = r152;
        L99:
            r44.set(0, 0, 1, 1, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r297, 9);
            r63.set(1, 1, 1, 1, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r297, 10);
            goto L89
        L98:
            r297 = r010;
            goto L99
        L100:
            MessageObject.GroupedMessagePosition r52 = r77.get(0);
            MessageObject.GroupedMessagePosition r79 = this.posArray.get(1);
            MessageObject.GroupedMessagePosition r94 = this.posArray.get(2);
            MessageObject.GroupedMessagePosition r134 = this.posArray.get(3);
            if (r25.charAt(0) != 'w') goto L110;
            float r298 = Math.round(Math.min(1000.0f / r52.aspectRatio, 537.24005f)) / 814.0f;
            r52.set(0, 2, 0, 0, 1000, r298, 7);
            float r011 = Math.round(1000.0f / ((r79.aspectRatio + r94.aspectRatio) + r134.aspectRatio));
            float r212 = r112;
            int r37 = (int) Math.max(r212, Math.min(400.0f, r79.aspectRatio * r011));
            int r213 = (int) Math.max(Math.max(r212, 330.0f), r134.aspectRatio * r011);
            int r45 = (1000 - r37) - r213;
            if (r45 >= AndroidUtilities.dp(58.0f)) goto L105;
            int r64 = AndroidUtilities.dp(58.0f) - r45;
            r45 = AndroidUtilities.dp(58.0f);
            int r53 = r64 / 2;
            r37 = r37 - r53;
            r213 = r213 - (r64 - r53);
        L105:
            int r282 = r37;
            float r012 = Math.min(814.0f - r298, r011) / 814.0f;
            if (r012 >= r152) goto L108;
            float r299 = r152;
        L109:
            r79.set(0, 0, 1, 1, r282, r299, 9);
            r94.set(1, 1, 1, 1, r45, r299, 8);
            r134.set(2, 2, 1, 1, r213, r299, 10);
            int r31 = 2;
        L111:
            r20 = 0;
            r133 = r31;
            goto L225
        L108:
            r299 = r012;
            goto L109
        L110:
            int r214 = Math.max(r112, Math.round(814.0f / (((1.0f / r79.aspectRatio) + (1.0f / r94.aspectRatio)) + (1.0f / r134.aspectRatio))));
            float r38 = r32;
            float r46 = r214;
            float r83 = Math.min(0.33f, Math.max(r38, r46 / r79.aspectRatio) / 814.0f);
            float r39 = Math.min(0.33f, Math.max(r38, r46 / r94.aspectRatio) / 814.0f);
            float r47 = (1.0f - r83) - r39;
            float r113 = (r52.aspectRatio * 814.0f) + r04;
            int r013 = 1000 - r214;
            r52.set(0, 0, 0, 2, Math.round(Math.min(r113, r013)), (r83 + r39) + r47, 13);
            r79.set(1, 1, 0, 0, r214, r83, 6);
            r94.set(1, 1, 1, 1, r214, r39, 2);
            r94.spanSize = 1000;
            r134.set(1, 1, 2, 2, r214, r47, 10);
            r134.spanSize = 1000;
            r52.spanSize = r013;
            r31 = 2;
            r52.siblingHeights = new float[]{r83, r39, r47};
        L68:
            int r322 = 1;
            int r710 = 1000;
            int r135 = this.posArray.size();
            float[] r14 = new float[r135];
            int r014 = 0;
        L113:
            if (r014 >= r10) goto L119;
            ArrayList<MessageObject.GroupedMessagePosition> r310 = this.posArray;
            if (r92 <= 1.1f) goto L117;
            float r54 = r19;
            r14[r014] = Math.max(r54, r310.get(r014).aspectRatio);
        L118:
            r14[r014] = Math.max(0.66667f, Math.min(1.7f, r14[r014]));
            r014 = r014 + 1;
            r19 = r54;
            goto L113
        L117:
            r54 = r19;
            r14[r014] = Math.min(r54, r310.get(r014).aspectRatio);
            goto L118
        L119:
            ArrayList r84 = new ArrayList();
            int r215 = 1;
        L120:
            if (r215 >= r135) goto L127;
            int r311 = r135 - r215;
            if (r215 > 3) goto L124;
            if (r311 > 3) goto L124;
            int r192 = r42;
            r84.add(new MessageGroupedLayoutAttempt(this, r215, r311, multiHeight(r14, 0, r215), multiHeight(r14, r215, r135)));
        L126:
            r215 = r215 + 1;
            r42 = r192;
        L124:
            r192 = r42;
            goto L126
        L127:
            int r193 = r42;
            int r216 = 1;
        L129:
            if (r216 >= (r135 - 1)) goto L145;
            int r312 = 1;
        L131:
            int r015 = r135 - r216;
            if (r312 >= r015) goto L144;
            int r48 = r015 - r312;
            if (r216 <= r62) goto L136;
        L141:
            int r123 = r62;
            int r21 = r710;
        L143:
            r312 = r312 + 1;
            r62 = r123;
            r710 = r21;
            r12 = 0;
            goto L131
        L136:
            if (r92 >= 0.85f) goto L138;
            int r016 = r193;
        L139:
            if (r312 > r016) goto L141;
            if (r48 > r62) goto L141;
            int r65 = r216 + r312;
            r21 = r710;
            r123 = 3;
            r84.add(new MessageGroupedLayoutAttempt(this, r216, r312, r48, multiHeight(r14, r12, r216), multiHeight(r14, r216, r65), multiHeight(r14, r65, r135)));
            goto L143
        L138:
            r016 = r62;
            goto L139
        L144:
            r216 = r216 + 1;
            r12 = 0;
            goto L129
        L145:
            int r124 = r62;
            int r217 = r710;
            int r218 = 1;
        L147:
            if (r218 >= (r135 - 2)) goto L164;
            int r313 = 1;
        L149:
            int r017 = r135 - r218;
            if (r313 >= r017) goto L163;
            int r49 = 1;
        L152:
            int r55 = r017 - r313;
            if (r49 >= r55) goto L162;
            int r56 = r55 - r49;
            if (r218 > r124) goto L159;
            if (r313 > r124) goto L159;
            if (r49 > r124) goto L159;
            if (r56 > r124) goto L159;
            float r66 = multiHeight(r14, 0, r218);
            int r711 = r218 + r313;
            float r222 = multiHeight(r14, r218, r711);
            int r125 = r711 + r49;
            float r712 = multiHeight(r14, r711, r125);
            float r126 = multiHeight(r14, r125, r135);
            int r223 = r017;
            ArrayList r127 = r84;
            r127.add(new MessageGroupedLayoutAttempt(this, r218, r313, r49, r56, r66, r222, r712, r126));
        L161:
            r49 = r49 + 1;
            r84 = r127;
            r017 = r223;
            r124 = 3;
        L159:
            r223 = r017;
            r127 = r84;
            goto L161
        L162:
            r313 = r313 + 1;
            r124 = 3;
            goto L149
        L163:
            r218 = r218 + 1;
            r124 = 3;
            goto L147
        L164:
            ArrayList r128 = r84;
            MessageGroupedLayoutAttempt r219 = null;
            float r314 = 0.0f;
            int r713 = 0;
        L166:
            if (r713 >= r128.size()) goto L200;
            MessageGroupedLayoutAttempt r410 = (MessageGroupedLayoutAttempt) r128.get(r713);
            float r57 = Float.MAX_VALUE;
            int r67 = 0;
            float r85 = 0.0f;
        L168:
            float[] r95 = r410.heights;
            if (r67 >= r95.length) goto L174;
            float r96 = r95[r67];
            r85 = r85 + r96;
            if (r96 >= r57) goto L173;
            r57 = r96;
        L173:
            r67 = r67 + 1;
            goto L168
        L174:
            float r68 = Math.abs(r85 - 1332.0f);
            int[] r86 = r410.lineCounts;
            int r136 = r322;
            if (r86.length <= r136) goto L193;
            int r97 = r86[0];
            int r018 = r86[r136];
            if (r97 > r018) goto L190;
            if (r86.length <= 2) goto L185;
            if (r018 > r86[2]) goto L190;
        L185:
            if (r86.length <= 3) goto L193;
            if (r86[2] <= r86[3]) goto L193;
        L190:
            r68 = r68 * 1.2f;
        L193:
            if (r57 >= r112) goto L195;
            r68 = r68 * 1.5f;
        L195:
            if (r219 != null) goto L197;
        L198:
            r219 = r410;
            r314 = r68;
        L199:
            r713 = r713 + 1;
            r322 = 1;
            goto L166
        L197:
            if (r68 >= r314) goto L199;
        L200:
            r133 = 2;
            r20 = 0;
            if (r219 != null) goto L203;
            return;
        L203:
            int r019 = 0;
            int r714 = 0;
        L204:
            int[] r315 = r219.lineCounts;
            if (r714 >= r315.length) goto L225;
            int r316 = r315[r714];
            float r411 = r219.heights[r714];
            int r58 = 0;
            int r69 = r217;
            MessageObject.GroupedMessagePosition r87 = null;
        L207:
            if (r58 >= r316) goto L221;
            int r98 = (int) (r14[r019] * r411);
            r69 = r69 - r98;
            MessageObject.GroupedMessagePosition r232 = this.posArray.get(r019);
            if (r714 != 0) goto L211;
            int r114 = r193;
        L213:
            if (r714 != (r219.lineCounts.length - 1)) goto L215;
            r114 = r114 | 8;
        L215:
            if (r58 != 0) goto L218;
            r114 = r114 | 1;
            r87 = r232;
        L218:
            if (r58 != (r316 - 1)) goto L220;
            r114 = r114 | 2;
            r87 = r232;
        L220:
            int r242 = r58;
            int r262 = r714;
            r232.set(r242, r58, r262, r714, r98, Math.max(r152, r411 / 814.0f), r114);
            r019 = r019 + 1;
            r58 = r242 + 1;
            goto L207
        L211:
            r114 = 0;
            goto L213
        L221:
            int r263 = r714;
            if (r87 == null) goto L224;
            r87.pw += r69;
            r87.spanSize += r69;
        L224:
            r714 = r263 + 1;
            goto L204
        }

        public float getHeight() {
            float[] r1 = new float[10];
            Arrays.fill(r1, 0.0f);
            int r2 = this.posArray.size();
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L9;
            MessageObject.GroupedMessagePosition r5 = this.posArray.get(r4);
            float r6 = r5.ph;
            int r7 = r5.minX;
        L6:
            if (r7 > r5.maxX) goto L8;
            r1[r7] = r1[r7] + r6;
            r7 = r7 + 1;
            goto L6
        L8:
            r4 = r4 + 1;
            goto L3
        L9:
            float r9 = r1[0];
            int r22 = 1;
        L10:
            if (r22 >= 10) goto L15;
            float r3 = r1[r22];
            if (r9 >= r3) goto L14;
            r9 = r3;
        L14:
            r22 = r22 + 1;
            goto L10
        L15:
            return r9;
        }

        public int getWidth() {
            int[] r1 = new int[10];
            Arrays.fill(r1, 0);
            int r3 = this.posArray.size();
            int r4 = 0;
        L3:
            if (r4 >= r3) goto L9;
            MessageObject.GroupedMessagePosition r5 = this.posArray.get(r4);
            int r6 = r5.pw;
            int r7 = r5.minY;
        L6:
            if (r7 > r5.maxY) goto L8;
            r1[r7] = r1[r7] + r6;
            r7 = r7 + 1;
            goto L6
        L8:
            r4 = r4 + 1;
            goto L3
        L9:
            int r9 = r1[0];
            int r2 = 1;
        L10:
            if (r2 >= 10) goto L15;
            int r32 = r1[r2];
            if (r9 >= r32) goto L14;
            r9 = r32;
        L14:
            r2 = r2 + 1;
            goto L10
        L15:
            return r9;
        }
    }

    public class PreviewGroupsView extends ViewGroup {
        private HashMap<Object, Object> deletedPhotos;
        private float draggingT;
        private ArrayList<PreviewGroupCell> groupCells;
        private ChatActionCell hintView;
        private HashMap<MediaController.PhotoEntry, ImageReceiver> images;
        boolean[] lastGroupSeen;
        private int lastMeasuredHeight;
        private int paddingBottom;
        private int paddingTop;
        GroupingPhotoViewerProvider photoViewerProvider;
        HashMap<Object, Object> photosMap;
        List<Map.Entry<Object, Object>> photosMapKeys;
        ArrayList<Object> photosOrder;
        private float savedDragFromX;
        private float savedDragFromY;
        private float savedDraggingT;
        private final Runnable scroller;
        private boolean scrollerStarted;
        HashMap<Object, Object> selectedPhotos;
        PreviewGroupCell tapGroupCell;
        PreviewGroupCell.MediaCell tapMediaCell;
        long tapTime;
        final /* synthetic */ ChatAttachAlertPhotoLayoutPreview this$0;
        private final PointF tmpPoint;
        private int undoViewId;
        float viewBottom;
        float viewTop;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ PreviewGroupsView this$1;

            public AnonymousClass1(PreviewGroupsView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$1.this$0, null);
                ChatAttachAlertPhotoLayoutPreview.m9799$$Nest$fputdraggingCellHiding(this.this$1.this$0, false);
                this.this$1.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ PreviewGroupsView this$1;

            public AnonymousClass2(PreviewGroupsView r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$1.this$0) != null) goto L5;
                return;
            L5:
                if (ChatAttachAlertPhotoLayoutPreview.m9784$$Nest$fgetdraggingCellHiding(this.this$1.this$0) == true) goto L31;
                int r0 = this.this$1.this$0.listView.computeVerticalScrollOffset();
                if ((this.this$1.this$0.listView.computeVerticalScrollExtent() + r0) < ((PreviewGroupsView.m9814$$Nest$mmeasurePureHeight(this.this$1) - PreviewGroupsView.m9811$$Nest$fgetpaddingBottom(this.this$1)) + PreviewGroupsView.m9812$$Nest$fgetpaddingTop(this.this$1))) goto L10;
                boolean r1 = true;
            L11:
                float r2 = Math.max(0.0f, (ChatAttachAlertPhotoLayoutPreview.m9788$$Nest$fgetdraggingCellTouchY(this.this$1.this$0) - Math.max(0, r0 - this.this$1.this$0.getListTopPadding())) - AndroidUtilities.dp(52.0f));
                float r6 = Math.max(0.0f, ((this.this$1.this$0.listView.getMeasuredHeight() - (ChatAttachAlertPhotoLayoutPreview.m9788$$Nest$fgetdraggingCellTouchY(this.this$1.this$0) - r0)) - this.this$1.this$0.getListTopPadding()) - AndroidUtilities.dp(84.0f));
                float r7 = AndroidUtilities.dp(32.0f);
                if (r2 >= r7) goto L17;
                if (r0 <= this.this$1.this$0.getListTopPadding()) goto L17;
                float r02 = (-(1.0f - (r2 / r7))) * AndroidUtilities.dp(6.0f);
            L20:
                int r22 = (int) r02;
                if (Math.abs(r22) > 0) goto L23;
            L28:
                PreviewGroupsView.m9813$$Nest$fputscrollerStarted(this.this$1, true);
                this.this$1.postDelayed(this, 15);
                return;
            L23:
                if (this.this$1.this$0.listView.canScrollVertically(r22) == false) goto L28;
                if (r02 <= 0.0f) goto L27;
                if (r1 == true) goto L28;
            L27:
                ChatAttachAlertPhotoLayoutPreview r12 = this.this$1.this$0;
                ChatAttachAlertPhotoLayoutPreview.m9803$$Nest$fputdraggingCellTouchY(r12, ChatAttachAlertPhotoLayoutPreview.m9788$$Nest$fgetdraggingCellTouchY(r12) + r02);
                this.this$1.this$0.listView.scrollBy(0, r22);
                this.this$1.invalidate();
            L17:
                if (r6 >= r7) goto L19;
                r02 = AndroidUtilities.dp(6.0f) * (1.0f - (r6 / r7));
                goto L20
            L19:
                r02 = 0.0f;
                goto L20
            L10:
                r1 = false;
                goto L11
            }
        }

        public class GroupingPhotoViewerProvider extends PhotoViewer.EmptyPhotoViewerProvider {
            private ArrayList<MediaController.PhotoEntry> photos;
            final /* synthetic */ PreviewGroupsView this$1;

            public GroupingPhotoViewerProvider(PreviewGroupsView r1) {
                this.this$1 = r1;
                this.photos = new ArrayList();
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public boolean cancelButtonPressed() {
                return false;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public int getPhotoIndex(int r3) {
                if (r3 >= 0) goto L5;
            L12:
                return -1;
            L5:
                if (r3 >= this.photos.size()) goto L12;
                MediaController.PhotoEntry r32 = this.photos.get(r3);
                if (r32 != null) goto L11;
                return -1;
            L11:
                return this.this$1.photosOrder.indexOf(Integer.valueOf(r32.imageId));
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r9, TLRPC.FileLocation r10, int r11, boolean r12, boolean r13) {
                PhotoViewer.PlaceProviderObject r92 = null;
                if (r11 >= 0) goto L5;
            L36:
                return r92;
            L5:
                if (r11 >= this.photos.size()) goto L36;
                if (isPhotoChecked(r11) == false) goto L36;
                MediaController.PhotoEntry r102 = this.photos.get(r11);
                if (r102 == null) goto L36;
                int r112 = PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.this$1).size();
                PreviewGroupCell r0 = null;
                PreviewGroupCell.MediaCell r1 = null;
                int r132 = 0;
            L12:
                if (r132 >= r112) goto L30;
                r0 = (PreviewGroupCell) PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.this$1).get(r132);
                if (r0 == null) goto L29;
                ArrayList<PreviewGroupCell.MediaCell> r2 = r0.media;
                if (r2 == null) goto L29;
                int r22 = r2.size();
                int r3 = 0;
            L18:
                if (r3 >= r22) goto L27;
                PreviewGroupCell.MediaCell r4 = r0.media.get(r3);
                if (r4 == null) goto L26;
                if (r4.photoEntry != r102) goto L26;
                if (r4.scale <= 0.5d) goto L26;
                r1 = r0.media.get(r3);
            L26:
                r3 = r3 + 1;
            L27:
                if (r1 != null) goto L30;
            L29:
                r132 = r132 + 1;
            L30:
                if (r0 == null) goto L36;
                if (r1 == null) goto L36;
                r92 = new PhotoViewer.PlaceProviderObject();
                int[] r113 = new int[2];
                this.this$1.getLocationInWindow(r113);
                if (Build.VERSION.SDK_INT >= 26) goto L35;
                r113[0] = r113[0] - this.this$1.this$0.parentAlert.getLeftInset();
            L35:
                r92.viewX = r113[0];
                r92.viewY = r113[1] + ((int) r0.y);
                r92.scale = 1.0f;
                r92.parentView = this.this$1;
                ImageReceiver r114 = r1.image;
                r92.imageReceiver = r114;
                r92.thumb = r114.getBitmapSafe();
                r92.radius = new int[]{(int) r0.left, (int) r0.top, (int) r0.right, (int) r0.bottom};
                RectF r02 = r1.roundRadiuses;
                r92.clipTopAddition = (int) (-this.this$1.getY());
                r92.clipBottomAddition = this.this$1.getHeight() - ((int) (((-this.this$1.getY()) + this.this$1.this$0.listView.getHeight()) - this.this$1.this$0.parentAlert.getClipLayoutBottom()));
                goto L36
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public int getSelectedCount() {
                return this.this$1.photosOrder.size();
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public HashMap<Object, Object> getSelectedPhotos() {
                return this.this$1.photosMap;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public ArrayList<Object> getSelectedPhotosOrder() {
                return this.this$1.photosOrder;
            }

            public void init(ArrayList<MediaController.PhotoEntry> r1) {
                this.photos = r1;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public boolean isPhotoChecked(int r2) {
                if (r2 >= 0) goto L4;
                return false;
            L4:
                if (r2 < this.photos.size()) goto L7;
                return false;
            L7:
                return this.this$1.photosOrder.contains(Integer.valueOf(this.photos.get(r2).imageId));
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public void onClose() {
                this.this$1.fromPhotoArrays();
                PreviewGroupsView r2 = this.this$1;
                r2.toPhotoLayout(ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(r2.this$0), false);
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public int setPhotoChecked(int r4, VideoEditedInfo r5) {
                if (r4 >= 0) goto L5;
            L16:
                return -1;
            L5:
                if (r4 >= this.photos.size()) goto L16;
                Integer r42 = Integer.valueOf(this.photos.get(r4).imageId);
                int r0 = this.this$1.photosOrder.indexOf(r42);
                PreviewGroupsView r1 = this.this$1;
                if (r0 >= 0) goto L10;
                r1.photosOrder.add(r42);
                this.this$1.fromPhotoArrays();
                return this.this$1.photosOrder.size() - 1;
            L10:
                if (r1.photosOrder.size() > 1) goto L12;
                return -1;
            L12:
                this.this$1.photosOrder.remove(r0);
                this.this$1.fromPhotoArrays();
                return r0;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public int setPhotoUnchecked(Object r4) {
                Integer r42 = Integer.valueOf(((MediaController.PhotoEntry) r4).imageId);
                if (this.this$1.photosOrder.size() > 1) goto L5;
                return -1;
            L5:
                int r43 = this.this$1.photosOrder.indexOf(r42);
                if (r43 < 0) goto L9;
                this.this$1.photosOrder.remove(r43);
                this.this$1.fromPhotoArrays();
                return r43;
            L9:
                return -1;
            }

            @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
            public void updatePhotoAtIndex(int r9) {
                if (r9 >= 0) goto L4;
                return;
            L4:
                if (r9 >= this.photos.size()) goto L55;
                MediaController.PhotoEntry r92 = this.photos.get(r9);
                if (r92 == null) goto L56;
                int r0 = r92.imageId;
                this.this$1.invalidate();
                int r2 = 0;
            L10:
                int r3 = PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.this$1).size();
                PreviewGroupsView r4 = this.this$1;
                if (r2 >= r3) goto L40;
                PreviewGroupCell r32 = (PreviewGroupCell) PreviewGroupsView.m9810$$Nest$fgetgroupCells(r4).get(r2);
                if (r32 == null) goto L39;
                if (r32.media == null) goto L39;
                int r42 = 0;
            L18:
                if (r42 >= r32.media.size()) goto L26;
                PreviewGroupCell.MediaCell r5 = r32.media.get(r42);
                if (r5 == null) goto L24;
                if (r5.photoEntry.imageId != r0) goto L24;
                PreviewGroupCell.MediaCell.m9827$$Nest$msetImage(r5, r92);
            L24:
                r42 = r42 + 1;
                goto L18
            L26:
                if (PreviewGroupCell.m9815$$Nest$fgetgroup(r32) != null) goto L28;
            L36:
                boolean r6 = false;
            L37:
                if (r6 == false) goto L39;
                PreviewGroupCell.m9821$$Nest$msetGroup(r32, PreviewGroupCell.m9815$$Nest$fgetgroup(r32), true);
                goto L39
            L28:
                if (PreviewGroupCell.m9815$$Nest$fgetgroup(r32).photos == null) goto L36;
                int r43 = 0;
                r6 = false;
            L31:
                if (r43 >= PreviewGroupCell.m9815$$Nest$fgetgroup(r32).photos.size()) goto L37;
                if (PreviewGroupCell.m9815$$Nest$fgetgroup(r32).photos.get(r43).imageId != r0) goto L35;
                PreviewGroupCell.m9815$$Nest$fgetgroup(r32).photos.set(r43, r92);
                r6 = true;
            L35:
                r43 = r43 + 1;
            L39:
                r2 = r2 + 1;
                goto L10
            L40:
                r4.remeasure();
                this.this$1.invalidate();
                return;
            L56:
                return;
            }
        }

        public class PreviewGroupCell {
            private final MessageDrawable.PathDrawParams backgroundCacheParams;
            private float bottom;
            private Text buttonText;
            private final Paint buttonTextBgPaint;
            private long buttonTextPrice;
            private final RectF buttonTextRect;
            final int gap;
            private GroupCalculator group;
            private float groupHeight;
            private float groupWidth;
            final int halfGap;
            private float height;
            public int indexStart;
            private Interpolator interpolator;
            private long lastMediaUpdate;
            private float left;
            public ArrayList<MediaCell> media;
            private final MessageDrawable messageBackground;
            final int padding;
            private float previousGroupHeight;
            private float previousGroupWidth;
            private float right;
            public long stars;
            final /* synthetic */ PreviewGroupsView this$1;
            private float top;
            private final long updateDuration;
            private float width;
            public float y;

            public class MediaCell {
                private Paint bitmapPaint;
                public ImageReceiver blurredImage;
                private Rect durationIn;
                private Rect durationOut;
                private RectF fromRect;
                public RectF fromRoundRadiuses;
                public float fromScale;
                public PreviewGroupCell groupCell;
                public ImageReceiver image;
                private Bitmap indexBitmap;
                private String indexBitmapText;
                private Rect indexIn;
                private Rect indexOut;
                private long lastUpdate;
                private long lastVisibleTUpdate;
                private Paint paint;
                private Path path;
                public MediaController.PhotoEntry photoEntry;
                private int positionFlags;
                private float[] radii;
                public RectF rect;
                public RectF roundRadiuses;
                public float scale;
                private Bitmap spoilerCrossfadeBitmap;
                private Paint spoilerCrossfadePaint;
                private float spoilerCrossfadeProgress;
                private SpoilerEffect2 spoilerEffect;
                private float spoilerMaxRadius;
                private float spoilerRevealProgress;
                private float spoilerRevealX;
                private float spoilerRevealY;
                private Paint strokePaint;
                private RectF tempRect;
                private TextPaint textPaint;
                final /* synthetic */ PreviewGroupCell this$2;
                private final long updateDuration;
                private Bitmap videoDurationBitmap;
                private String videoDurationBitmapText;
                private String videoDurationText;
                private TextPaint videoDurationTextPaint;
                private float visibleT;
                public boolean wasSpoiler;

                /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$PreviewGroupCell$MediaCell$1, reason: invalid class name */
                public class AnonymousClass1 extends AnimatorListenerAdapter {
                    final /* synthetic */ MediaCell this$3;

                    public AnonymousClass1(MediaCell r1) {
                        this.this$3 = r1;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator r2) {
                        MediaCell r1 = this.this$3;
                        r1.photoEntry.isChatPreviewSpoilerRevealed = true;
                        r1.this$2.this$1.invalidate();
                    }
                }

                public static /* synthetic */ void $r8$lambda$Svn83pyUrc72ZFCpvx_8yb2NOow(MediaCell r0, ValueAnimator r1) {
                    r0.lambda$startRevealMedia$1(r1);
                }

                public static /* synthetic */ void $r8$lambda$vBrPPztxOhW_QVE2L2G8eLqxCqw(MediaCell r0, MediaController.PhotoEntry r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
                    r0.lambda$setImage$0(r1, r2, r3, r4, r5);
                }

                /* renamed from: -$$Nest$fgetlastUpdate, reason: not valid java name */
                public static /* bridge */ /* synthetic */ long m9822$$Nest$fgetlastUpdate(MediaCell r2) {
                    return r2.lastUpdate;
                }

                /* renamed from: -$$Nest$fgetpositionFlags, reason: not valid java name */
                public static /* bridge */ /* synthetic */ int m9823$$Nest$fgetpositionFlags(MediaCell r0) {
                    return r0.positionFlags;
                }

                /* renamed from: -$$Nest$fgetspoilerRevealProgress, reason: not valid java name */
                public static /* bridge */ /* synthetic */ float m9824$$Nest$fgetspoilerRevealProgress(MediaCell r0) {
                    return r0.spoilerRevealProgress;
                }

                /* renamed from: -$$Nest$mlayout, reason: not valid java name */
                public static /* bridge */ /* synthetic */ void m9825$$Nest$mlayout(MediaCell r0, GroupCalculator r1, MessageObject.GroupedMessagePosition r2, boolean r3) {
                    r0.layout(r1, r2, r3);
                }

                /* renamed from: -$$Nest$mlayoutFrom, reason: not valid java name */
                public static /* bridge */ /* synthetic */ void m9826$$Nest$mlayoutFrom(MediaCell r0, MediaCell r1) {
                    r0.layoutFrom(r1);
                }

                /* renamed from: -$$Nest$msetImage, reason: not valid java name */
                public static /* bridge */ /* synthetic */ void m9827$$Nest$msetImage(MediaCell r0, MediaController.PhotoEntry r1) {
                    r0.setImage(r1);
                }

                /* renamed from: -$$Nest$mstartRevealMedia, reason: not valid java name */
                public static /* bridge */ /* synthetic */ void m9828$$Nest$mstartRevealMedia(MediaCell r0, float r1, float r2) {
                    r0.startRevealMedia(r1, r2);
                }

                private MediaCell(PreviewGroupCell r6) {
                    this.this$2 = r6;
                    this.groupCell = r6;
                    this.fromRect = null;
                    this.rect = new RectF();
                    this.lastUpdate = 0;
                    this.updateDuration = 200;
                    this.positionFlags = 0;
                    this.fromScale = 1.0f;
                    this.scale = 0.0f;
                    this.fromRoundRadiuses = null;
                    this.roundRadiuses = new RectF();
                    this.videoDurationText = null;
                    this.path = new Path();
                    this.radii = new float[8];
                    this.spoilerCrossfadeProgress = 1.0f;
                    this.spoilerCrossfadePaint = new Paint(1);
                    this.tempRect = new RectF();
                    this.paint = new Paint(1);
                    this.strokePaint = new Paint(1);
                    this.bitmapPaint = new Paint(1);
                    this.indexBitmap = null;
                    this.indexBitmapText = null;
                    this.videoDurationBitmap = null;
                    this.videoDurationBitmapText = null;
                    this.indexIn = new Rect();
                    this.indexOut = new Rect();
                    this.durationIn = new Rect();
                    this.durationOut = new Rect();
                    this.visibleT = 1.0f;
                    this.lastVisibleTUpdate = 0;
                }

                public /* synthetic */ MediaCell(PreviewGroupCell r1, ChatAttachAlertPhotoLayoutPreviewIA r2) {
                    this(r1);
                }

                private void drawDuration(Canvas r10, float r11, float r12, String r13, float r14, float r15) {
                    if (r13 != null) goto L4;
                    return;
                L4:
                    if (this.videoDurationBitmap == null) goto L10;
                    String r0 = this.videoDurationBitmapText;
                    if (r0 == null) goto L10;
                    if (r0.equals(r13) == false) goto L10;
                L23:
                    int r132 = this.videoDurationBitmap.getWidth();
                    this.durationOut.set((int) r11, (int) (r12 - (this.videoDurationBitmap.getHeight() * r14)), (int) (r11 + (r132 * r14)), (int) r12);
                    this.bitmapPaint.setAlpha((int) (r15 * 255.0f));
                    r10.drawBitmap(this.videoDurationBitmap, this.durationIn, this.durationOut, this.bitmapPaint);
                    return;
                L10:
                    if (this.videoDurationTextPaint != null) goto L12;
                    TextPaint r02 = new TextPaint(1);
                    this.videoDurationTextPaint = r02;
                    r02.setTypeface(AndroidUtilities.bold());
                    this.videoDurationTextPaint.setColor(-1);
                L12:
                    float r03 = AndroidUtilities.dp(12.0f);
                    this.videoDurationTextPaint.setTextSize(r03);
                    float r2 = (ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).getIntrinsicWidth() + this.videoDurationTextPaint.measureText(r13)) + AndroidUtilities.dp(15.0f);
                    float r1 = Math.max(r03, ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).getIntrinsicHeight() + AndroidUtilities.dp(4.0f));
                    int r4 = (int) Math.ceil(r2);
                    int r5 = (int) Math.ceil(r1);
                    Bitmap r6 = this.videoDurationBitmap;
                    if (r6 != null) goto L15;
                L18:
                    Bitmap r62 = this.videoDurationBitmap;
                    if (r62 == null) goto L21;
                    r62.recycle();
                L21:
                    this.videoDurationBitmap = Bitmap.createBitmap(r4, r5, Bitmap.Config.ARGB_8888);
                L22:
                    Canvas r63 = new Canvas(this.videoDurationBitmap);
                    RectF r7 = AndroidUtilities.rectTmp;
                    r7.set(0.0f, 0.0f, r2, r1);
                    r63.drawRoundRect(r7, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_timeBackgroundPaint);
                    int r22 = AndroidUtilities.dp(5.0f);
                    int r16 = (int) ((r1 - ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).getIntrinsicHeight()) / 2.0f);
                    ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).setBounds(r22, r16, ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).getIntrinsicWidth() + r22, ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).getIntrinsicHeight() + r16);
                    ChatAttachAlertPhotoLayoutPreview.m9793$$Nest$fgetvideoPlayImage(this.this$2.this$1.this$0).draw(r63);
                    r63.drawText(r13, AndroidUtilities.dp(18.0f), r03 + AndroidUtilities.dp(-0.7f), this.videoDurationTextPaint);
                    this.durationIn.set(0, 0, r4, r5);
                    this.videoDurationBitmapText = r13;
                    goto L23
                L15:
                    if (r6.getWidth() != r4) goto L18;
                    if (this.videoDurationBitmap.getHeight() == r5) goto L22;
                    goto L18
                }

                private void drawPhotoIndex(Canvas r17, float r18, float r19, String r20, float r21, float r22) {
                    int r2 = AndroidUtilities.dp(12.0f);
                    int r3 = AndroidUtilities.dp(1.2f);
                    int r4 = (r2 + r3) * 2;
                    int r6 = r3 * 4;
                    if (r20 == null) goto L27;
                    if (this.indexBitmap == null) goto L11;
                    String r7 = this.indexBitmapText;
                    if (r7 == null) goto L11;
                    if (r7.equals(r20) == true) goto L27;
                L11:
                    if (this.indexBitmap != null) goto L13;
                    this.indexBitmap = Bitmap.createBitmap(r4, r4, Bitmap.Config.ARGB_8888);
                L13:
                    Canvas r72 = new Canvas(this.indexBitmap);
                    r72.drawColor(0);
                    if (this.textPaint != null) goto L16;
                    TextPaint r9 = new TextPaint(1);
                    this.textPaint = r9;
                    r9.setTypeface(AndroidUtilities.bold());
                L16:
                    TextPaint r92 = this.textPaint;
                    ChatAttachAlertPhotoLayoutPreview r11 = this.this$2.this$1.this$0;
                    int r12 = Theme.key_chat_attachCheckBoxCheck;
                    r92.setColor(r11.getThemedColor(r12));
                    int r93 = r20.length();
                    if (r93 == 0) goto L24;
                    if (r93 == 1) goto L24;
                    if (r93 == 2) goto L24;
                    if (r93 == 3) goto L23;
                    float r5 = 8.0f;
                L25:
                    this.textPaint.setTextSize(AndroidUtilities.dp(r5));
                    float r94 = r4 / 2.0f;
                    this.paint.setColor(this.this$2.this$1.this$0.getThemedColor(Theme.key_chat_attachCheckBoxBackground));
                    float r112 = (int) r94;
                    float r23 = r2;
                    r72.drawCircle(r112, r112, r23, this.paint);
                    this.strokePaint.setColor(AndroidUtilities.getOffsetColor(-1, this.this$2.this$1.this$0.getThemedColor(r12), 1.0f, 1.0f));
                    this.strokePaint.setStyle(Paint.Style.STROKE);
                    this.strokePaint.setStrokeWidth(r3);
                    r72.drawCircle(r112, r112, r23, this.strokePaint);
                    r72.drawText(r20, r94 - (this.textPaint.measureText(r20) / 2.0f), (r94 + AndroidUtilities.dp(1.0f)) + AndroidUtilities.dp(r5 / 4.0f), this.textPaint);
                    this.indexIn.set(0, 0, r4, r4);
                    this.indexBitmapText = r20;
                    goto L27
                L23:
                    r5 = 10.0f;
                L24:
                    r5 = 14.0f;
                L27:
                    if (this.indexBitmap == null) goto L30;
                    float r24 = r4 * r21;
                    float r42 = r6;
                    float r52 = r18 - r42;
                    this.indexOut.set((int) ((r19 - r24) + r42), (int) r52, (int) (r19 + r42), (int) (r52 + r24));
                    this.bitmapPaint.setAlpha((int) (255.0f * r22));
                    r17.drawBitmap(this.indexBitmap, this.indexIn, this.indexOut, this.bitmapPaint);
                    return;
                }

                private /* synthetic */ void lambda$setImage$0(MediaController.PhotoEntry r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
                    if (r3 == false) goto L16;
                    if (r4 == true) goto L17;
                    if (r1 != null) goto L6;
                    return;
                L6:
                    if (r1.hasSpoiler == true) goto L8;
                    return;
                L8:
                    if (this.blurredImage.getBitmap() == null) goto L10;
                    return;
                L10:
                    if (this.blurredImage.getBitmap() != null) goto L12;
                L14:
                    Bitmap r12 = r2.getBitmap();
                    this.blurredImage.setImageBitmap(Utilities.stackBlurBitmapMax(r12));
                    return;
                L12:
                    if (this.blurredImage.getBitmap().isRecycled() == true) goto L14;
                    this.blurredImage.getBitmap().recycle();
                    this.blurredImage.setImageBitmap(null);
                    goto L14
                L17:
                    return;
                }

                private /* synthetic */ void lambda$startRevealMedia$1(ValueAnimator r1) {
                    this.spoilerRevealProgress = ((Float) r1.getAnimatedValue()).floatValue();
                    this.this$2.this$1.invalidate();
                }

                private void layout(GroupCalculator r6, MessageObject.GroupedMessagePosition r7, boolean r8) {
                    if (r6 == null) goto L37;
                    if (r7 == null) goto L37;
                    this.positionFlags = r7.flags;
                    if (r8 == false) goto L14;
                    float r82 = getT();
                    RectF r0 = this.fromRect;
                    if (r0 == null) goto L10;
                    AndroidUtilities.lerp(r0, this.rect, r82, r0);
                L10:
                    RectF r02 = this.fromRoundRadiuses;
                    if (r02 == null) goto L13;
                    AndroidUtilities.lerp(r02, this.roundRadiuses, r82, r02);
                L13:
                    this.fromScale = AndroidUtilities.lerp(this.fromScale, this.scale, r82);
                    this.lastUpdate = SystemClock.elapsedRealtime();
                L14:
                    float r83 = r7.left;
                    int r03 = r6.width;
                    float r84 = r83 / r03;
                    float r1 = r7.top;
                    float r62 = r6.height;
                    float r12 = r1 / r62;
                    float r2 = r7.pw / r03;
                    float r72 = r7.ph / r62;
                    this.scale = 1.0f;
                    this.rect.set(r84, r12, r2 + r84, r72 + r12);
                    float r63 = AndroidUtilities.dp(2.0f);
                    float r73 = AndroidUtilities.dp(SharedConfig.bubbleRadius - 1);
                    RectF r85 = this.roundRadiuses;
                    int r04 = this.positionFlags;
                    if ((r04 & 5) != 5) goto L17;
                    float r13 = r73;
                L19:
                    if ((r04 & 6) != 6) goto L21;
                    float r22 = r73;
                L23:
                    if ((r04 & 10) != 10) goto L25;
                    float r3 = r73;
                L27:
                    if ((r04 & 9) != 9) goto L29;
                    r63 = r73;
                L29:
                    r85.set(r13, r22, r3, r63);
                    if (this.fromRect != null) goto L33;
                    RectF r64 = new RectF();
                    this.fromRect = r64;
                    r64.set(this.rect);
                L33:
                    if (this.fromRoundRadiuses != null) goto L45;
                    RectF r65 = new RectF();
                    this.fromRoundRadiuses = r65;
                    r65.set(this.roundRadiuses);
                    return;
                L45:
                    return;
                L25:
                    r3 = r63;
                    goto L27
                L21:
                    r22 = r63;
                    goto L23
                L17:
                    r13 = r63;
                L37:
                    if (r8 == false) goto L43;
                    long r74 = SystemClock.elapsedRealtime();
                    this.fromScale = AndroidUtilities.lerp(this.fromScale, this.scale, getT());
                    RectF r05 = this.fromRect;
                    if (r05 == null) goto L41;
                    AndroidUtilities.lerp(r05, this.rect, getT(), this.fromRect);
                L41:
                    this.scale = 0.0f;
                    this.lastUpdate = r74;
                    return;
                L43:
                    this.fromScale = 0.0f;
                    this.scale = 0.0f;
                }

                private void layoutFrom(MediaCell r9) {
                    this.fromScale = AndroidUtilities.lerp(r9.fromScale, r9.scale, r9.getT());
                    if (this.fromRect != null) goto L5;
                    this.fromRect = new RectF();
                L5:
                    RectF r0 = new RectF();
                    RectF r1 = this.fromRect;
                    RectF r2 = this.rect;
                    if (r1 != null) goto L8;
                    r0.set(r2);
                L9:
                    RectF r12 = r9.fromRect;
                    if (r12 == null) goto L12;
                    AndroidUtilities.lerp(r12, r9.rect, r9.getT(), this.fromRect);
                    this.fromRect.set(r0.centerX() - (((this.fromRect.width() / 2.0f) * PreviewGroupCell.m9820$$Nest$fgetwidth(r9.groupCell)) / PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2)), r0.centerY() - (((this.fromRect.height() / 2.0f) * PreviewGroupCell.m9816$$Nest$fgetheight(r9.groupCell)) / PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2)), r0.centerX() + (((this.fromRect.width() / 2.0f) * PreviewGroupCell.m9820$$Nest$fgetwidth(r9.groupCell)) / PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2)), r0.centerY() + (((this.fromRect.height() / 2.0f) * PreviewGroupCell.m9816$$Nest$fgetheight(r9.groupCell)) / PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2)));
                L13:
                    this.fromScale = AndroidUtilities.lerp(this.fromScale, this.scale, getT());
                    this.lastUpdate = SystemClock.elapsedRealtime();
                    return;
                L12:
                    this.fromRect.set(r0.centerX() - (((r9.rect.width() / 2.0f) * PreviewGroupCell.m9820$$Nest$fgetwidth(r9.groupCell)) / PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2)), r0.centerY() - (((r9.rect.height() / 2.0f) * PreviewGroupCell.m9816$$Nest$fgetheight(r9.groupCell)) / PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2)), r0.centerX() + (((r9.rect.width() / 2.0f) * PreviewGroupCell.m9820$$Nest$fgetwidth(r9.groupCell)) / PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2)), r0.centerY() + (((r9.rect.height() / 2.0f) * PreviewGroupCell.m9816$$Nest$fgetheight(r9.groupCell)) / PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2)));
                    goto L13
                L8:
                    AndroidUtilities.lerp(r1, r2, getT(), r0);
                    goto L9
                }

                private void setImage(MediaController.PhotoEntry r27) {
                    this.photoEntry = r27;
                    if (r27 != null) goto L5;
                L7:
                    this.videoDurationText = null;
                L9:
                    if (this.image != null) goto L11;
                    this.image = new ImageReceiver(this.this$2.this$1);
                    this.blurredImage = new ImageReceiver(this.this$2.this$1);
                    this.image.setDelegate(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$PreviewGroupCell$MediaCell$$ExternalSyntheticLambda0(this, r27));
                L11:
                    if (r27 == null) goto L26;
                    String r2 = r27.thumbPath;
                    if (r2 == null) goto L17;
                    this.image.setImage(ImageLocation.getForPath(r2), null, null, null, Theme.chat_attachEmptyDrawable, 0, null, null, 0);
                    return;
                L17:
                    if (r27.path == null) goto L24;
                    boolean r22 = r27.isVideo;
                    ImageReceiver r3 = this.image;
                    if (r22 == false) goto L22;
                    r3.setImage(ImageLocation.getForPath("vthumb://" + r27.imageId + ":" + r27.path), null, null, null, Theme.chat_attachEmptyDrawable, 0, null, null, 0);
                    this.image.setAllowStartAnimation(true);
                    return;
                L22:
                    r3.setOrientation(r27.orientation, true);
                    this.image.setImage(ImageLocation.getForPath("thumb://" + r27.imageId + ":" + r27.path), null, null, null, Theme.chat_attachEmptyDrawable, 0, null, null, 0);
                    return;
                L24:
                    this.image.setImageBitmap(Theme.chat_attachEmptyDrawable);
                    return;
                L26:
                    return;
                L5:
                    if (r27.isVideo == false) goto L7;
                    this.videoDurationText = AndroidUtilities.formatShortDuration(r27.duration);
                    goto L9
                }

                private void startRevealMedia(float r5, float r6) {
                    this.spoilerRevealX = r5;
                    this.spoilerRevealY = r6;
                    RectF r52 = drawingRect();
                    this.spoilerMaxRadius = (float) Math.sqrt(Math.pow(r52.width(), 2.0d) + Math.pow(r52.height(), 2.0d));
                    ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) MathUtils.clamp(this.spoilerMaxRadius * 0.3f, 250.0f, 550.0f));
                    r53.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
                    r53.addUpdateListener(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$PreviewGroupCell$MediaCell$$ExternalSyntheticLambda1(this));
                    r53.addListener(new AnonymousClass1(this));
                    r53.start();
                }

                public /* bridge */ /* synthetic */ Object clone() {
                    return m9829clone();
                }

                /* renamed from: clone, reason: collision with other method in class */
                public MediaCell m9829clone() {
                    MediaCell r0 = new MediaCell(this.this$2);
                    r0.rect.set(this.rect);
                    r0.image = this.image;
                    r0.photoEntry = this.photoEntry;
                    return r0;
                }

                public void detach() {
                    SpoilerEffect2 r0 = this.spoilerEffect;
                    if (r0 == null) goto L6;
                    r0.detach(this.this$2.this$1);
                    this.spoilerEffect = null;
                    return;
                }

                public boolean draw(Canvas r2) {
                    return draw(r2, false);
                }

                public boolean draw(Canvas r29, float r30, boolean r31) {
                    if (this.rect == null) goto L85;
                    if (this.image == null) goto L85;
                    if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$2.this$1.this$0) != this) goto L10;
                    float r3 = PreviewGroupsView.m9809$$Nest$fgetdraggingT(this.this$2.this$1);
                L11:
                    float r5 = AndroidUtilities.lerp(this.fromScale, this.scale, r30);
                    if (r5 > 0.0f) goto L14;
                    return false;
                L14:
                    RectF r8 = drawingRect();
                    float r6 = AndroidUtilities.dp(SharedConfig.bubbleRadius - 1);
                    RectF r10 = this.roundRadiuses;
                    float r11 = r10.left;
                    float r12 = r10.top;
                    float r13 = r10.right;
                    float r102 = r10.bottom;
                    if (r30 >= 1.0f) goto L19;
                    RectF r7 = this.fromRoundRadiuses;
                    if (r7 == null) goto L19;
                    r11 = AndroidUtilities.lerp(r7.left, r11, r30);
                    r12 = AndroidUtilities.lerp(this.fromRoundRadiuses.top, r12, r30);
                    r13 = AndroidUtilities.lerp(this.fromRoundRadiuses.right, r13, r30);
                    r102 = AndroidUtilities.lerp(this.fromRoundRadiuses.bottom, r102, r30);
                L19:
                    float r2 = AndroidUtilities.lerp(r11, r6, r3);
                    float r72 = AndroidUtilities.lerp(r12, r6, r3);
                    float r112 = AndroidUtilities.lerp(r13, r6, r3);
                    float r32 = AndroidUtilities.lerp(r102, r6, r3);
                    if (r31 == false) goto L22;
                    r29.save();
                    r29.translate(-r8.centerX(), -r8.centerY());
                L22:
                    int r103 = (int) r2;
                    int r122 = (int) r72;
                    int r132 = (int) r112;
                    int r4 = (int) r32;
                    this.image.setRoundRadius(r103, r122, r132, r4);
                    this.image.setImageCoords(r8.left, r8.top, r8.width(), r8.height());
                    this.image.setAlpha(r5);
                    this.image.draw(r29);
                    MediaController.PhotoEntry r22 = this.photoEntry;
                    if (r22 == null) goto L50;
                    char r23 = 7;
                    if (r22.hasSpoiler == true) goto L27;
                L49:
                    char r24 = 6;
                    float r27 = r72;
                    char r25 = 5;
                    char r26 = 4;
                L51:
                    float r28 = this.spoilerCrossfadeProgress;
                    String r42 = null;
                    if (r28 == 1.0f) goto L57;
                    if (this.spoilerCrossfadeBitmap == null) goto L57;
                    float[] r210 = this.radii;
                    r210[1] = r2;
                    r210[0] = r2;
                    r210[3] = r27;
                    r210[2] = r27;
                    r210[r25] = r112;
                    r210[r26] = r112;
                    r210[r23] = r32;
                    r210[r24] = r32;
                    r29.save();
                    this.path.rewind();
                    this.path.addRoundRect(r8, this.radii, Path.Direction.CW);
                    r29.clipPath(this.path);
                    float r33 = Math.min(1.0f, this.spoilerCrossfadeProgress + (Math.min(16, SystemClock.elapsedRealtime() - this.lastUpdate) / 250.0f));
                    this.spoilerCrossfadeProgress = r33;
                    this.spoilerCrossfadePaint.setAlpha((int) ((1.0f - r33) * 255.0f));
                    r29.drawBitmap(this.spoilerCrossfadeBitmap, r8.left, r8.top, this.spoilerCrossfadePaint);
                    r29.restore();
                    this.this$2.this$1.invalidate();
                L61:
                    PreviewGroupCell r211 = this.this$2;
                    int r34 = r211.indexStart + PreviewGroupCell.m9815$$Nest$fgetgroup(r211).photos.indexOf(this.photoEntry);
                    if (r34 < 0) goto L65;
                    r42 = (r34 + 1) + _UrlKt.FRAGMENT_ENCODE_SET;
                L65:
                    if (this.image.getVisible() == false) goto L67;
                    float r212 = 1.0f;
                L69:
                    if (Math.abs(this.visibleT - r212) <= 0.01f) goto L71;
                    boolean r73 = true;
                L72:
                    if (r73 == false) goto L77;
                    long r9 = Math.min(17, SystemClock.elapsedRealtime() - this.lastVisibleTUpdate);
                    this.lastVisibleTUpdate = SystemClock.elapsedRealtime();
                    float r35 = r9 / 100.0f;
                    float r62 = this.visibleT;
                    if (r212 >= r62) goto L76;
                    this.visibleT = Math.max(0.0f, r62 - r35);
                    goto L77
                L76:
                    this.visibleT = Math.min(1.0f, r62 + r35);
                L77:
                    drawPhotoIndex(r29, r8.top + AndroidUtilities.dp(10.0f), r8.right - AndroidUtilities.dp(10.0f), r42, r5, this.visibleT * r5);
                    drawDuration(r29, r8.left + AndroidUtilities.dp(4.0f), r8.bottom - AndroidUtilities.dp(4.0f), this.videoDurationText, r5, r5 * this.visibleT);
                    if (r31 == false) goto L80;
                    r29.restore();
                L80:
                    if (r30 < 1.0f) goto L82;
                    if (r73 == true) goto L86;
                    return false;
                L86:
                    return true;
                L82:
                    return true;
                L71:
                    r73 = false;
                    goto L72
                L67:
                    r212 = 0.0f;
                L57:
                    if (r28 != 1.0f) goto L61;
                    Bitmap r213 = this.spoilerCrossfadeBitmap;
                    if (r213 == null) goto L61;
                    r213.recycle();
                    this.spoilerCrossfadeBitmap = null;
                    this.this$2.this$1.invalidate();
                    goto L61
                L27:
                    if (r22.isChatPreviewSpoilerRevealed == true) goto L49;
                    if (this.wasSpoiler == true) goto L37;
                    if (this.blurredImage.getBitmap() != null) goto L37;
                    if (this.image.getBitmap() == null) goto L37;
                    this.wasSpoiler = true;
                    this.blurredImage.setImageBitmap(Utilities.stackBlurBitmapMax(this.image.getBitmap()));
                L35:
                    char r214 = 1;
                L41:
                    float[] r36 = this.radii;
                    r36[r214] = r2;
                    r36[0] = r2;
                    r36[3] = r72;
                    r36[2] = r72;
                    r36[5] = r112;
                    r36[4] = r112;
                    r36[7] = r32;
                    r36[6] = r32;
                    r29.save();
                    this.path.rewind();
                    Path r215 = this.path;
                    float[] r37 = this.radii;
                    r24 = 6;
                    Path.Direction r63 = Path.Direction.CW;
                    r215.addRoundRect(r8, r37, r63);
                    r29.clipPath(this.path);
                    if (this.spoilerRevealProgress == 0.0f) goto L44;
                    this.path.rewind();
                    r25 = 5;
                    r26 = 4;
                    r27 = r72;
                    this.path.addCircle(this.spoilerRevealX, this.spoilerRevealY, this.spoilerMaxRadius * this.spoilerRevealProgress, r63);
                    r29.clipPath(this.path, Region.Op.DIFFERENCE);
                L45:
                    this.blurredImage.setRoundRadius(r103, r122, r132, r4);
                    this.blurredImage.setImageCoords(r8.left, r8.top, r8.width(), r8.height());
                    this.blurredImage.setAlpha(r5);
                    this.blurredImage.draw(r29);
                    if (this.spoilerEffect != null) goto L48;
                    this.spoilerEffect = SpoilerEffect2.getInstance(this.this$2.this$1);
                L48:
                    SpoilerEffect2 r216 = this.spoilerEffect;
                    PreviewGroupsView r38 = this.this$2.this$1;
                    r216.draw(r29, r38, r38.getWidth(), this.this$2.this$1.getHeight());
                    r29.restore();
                    this.this$2.this$1.invalidate();
                    this.this$2.this$1.invalidate();
                    goto L51
                L44:
                    r27 = r72;
                    r25 = 5;
                    r26 = 4;
                L37:
                    if (this.wasSpoiler == true) goto L35;
                    if (this.blurredImage.getBitmap() == null) goto L35;
                    r214 = 1;
                    this.wasSpoiler = true;
                    goto L41
                L50:
                    r24 = 6;
                    r27 = r72;
                    r25 = 5;
                    r26 = 4;
                    r23 = 7;
                    goto L51
                L10:
                    r3 = 0.0f;
                L85:
                    return false;
                }

                public boolean draw(Canvas r2, boolean r3) {
                    return draw(r2, getT(), r3);
                }

                public RectF drawingRect() {
                    float r1 = 0.0f;
                    if (this.rect != null) goto L5;
                L14:
                    this.tempRect.set(0.0f, 0.0f, 0.0f, 0.0f);
                    return this.tempRect;
                L5:
                    if (this.image == null) goto L14;
                    if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$2.this$1.this$0) != null) goto L10;
                L12:
                    float r0 = AndroidUtilities.lerp(this.fromScale, this.scale, getT()) * (((1.0f - r1) * 0.2f) + 0.8f);
                    RectF r7 = rect();
                    float r4 = 1.0f - r0;
                    float r02 = r0 + 1.0f;
                    r7.set(r7.left + ((r7.width() * r4) / 2.0f), r7.top + ((r7.height() * r4) / 2.0f), r7.left + ((r7.width() * r02) / 2.0f), r7.top + ((r7.height() * r02) / 2.0f));
                    return r7;
                L10:
                    if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$2.this$1.this$0).photoEntry != this.photoEntry) goto L12;
                    r1 = PreviewGroupsView.m9809$$Nest$fgetdraggingT(this.this$2.this$1);
                    goto L12
                }

                public float getT() {
                    return PreviewGroupCell.m9817$$Nest$fgetinterpolator(this.this$2).getInterpolation(Math.min(1.0f, (SystemClock.elapsedRealtime() - this.lastUpdate) / 200.0f));
                }

                public RectF rect() {
                    return rect(getT());
                }

                public RectF rect(float r8) {
                    if (this.rect != null) goto L5;
                L26:
                    this.tempRect.set(0.0f, 0.0f, 0.0f, 0.0f);
                    return this.tempRect;
                L5:
                    if (this.image == null) goto L26;
                    float r0 = PreviewGroupCell.m9818$$Nest$fgetleft(this.this$2) + (this.rect.left * PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2));
                    float r1 = PreviewGroupCell.m9819$$Nest$fgettop(this.this$2) + (this.rect.top * PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2));
                    float r2 = this.rect.width() * PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2);
                    float r3 = this.rect.height() * PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2);
                    if (r8 < 1.0f) goto L10;
                L12:
                    int r82 = this.positionFlags;
                    if ((r82 & 4) != 0) goto L16;
                    int r4 = this.this$2.halfGap;
                    r1 = r1 + r4;
                    r3 = r3 - r4;
                L16:
                    if ((r82 & 8) != 0) goto L19;
                    r3 = r3 - this.this$2.halfGap;
                L19:
                    if ((r82 & 1) != 0) goto L22;
                    int r42 = this.this$2.halfGap;
                    r0 = r0 + r42;
                    r2 = r2 - r42;
                L22:
                    if ((r82 & 2) != 0) goto L24;
                    r2 = r2 - this.this$2.halfGap;
                L24:
                    this.tempRect.set(r0, r1, r2 + r0, r3 + r1);
                    return this.tempRect;
                L10:
                    if (this.fromRect == null) goto L12;
                    r0 = AndroidUtilities.lerp(PreviewGroupCell.m9818$$Nest$fgetleft(this.this$2) + (this.fromRect.left * PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2)), r0, r8);
                    r1 = AndroidUtilities.lerp(PreviewGroupCell.m9819$$Nest$fgettop(this.this$2) + (this.fromRect.top * PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2)), r1, r8);
                    r2 = AndroidUtilities.lerp(this.fromRect.width() * PreviewGroupCell.m9820$$Nest$fgetwidth(this.this$2), r2, r8);
                    r3 = AndroidUtilities.lerp(this.fromRect.height() * PreviewGroupCell.m9816$$Nest$fgetheight(this.this$2), r3, r8);
                    goto L12
                }

                public void startCrossfade() {
                    RectF r0 = drawingRect();
                    Bitmap r1 = Bitmap.createBitmap(Math.max(1, Math.round(r0.width())), Math.max(1, Math.round(r0.height())), Bitmap.Config.ARGB_8888);
                    Canvas r2 = new Canvas(r1);
                    r2.save();
                    r2.translate(-r0.left, -r0.top);
                    draw(r2);
                    r2.restore();
                    Bitmap r02 = this.spoilerCrossfadeBitmap;
                    if (r02 != null) goto L5;
                L7:
                    this.spoilerCrossfadeBitmap = r1;
                    this.spoilerCrossfadeProgress = 0.0f;
                    this.this$2.this$1.invalidate();
                    return;
                L5:
                    if (r02.isRecycled() == true) goto L7;
                    this.spoilerCrossfadeBitmap.recycle();
                    goto L7
                }
            }

            /* renamed from: -$$Nest$fgetgroup, reason: not valid java name */
            public static /* bridge */ /* synthetic */ GroupCalculator m9815$$Nest$fgetgroup(PreviewGroupCell r0) {
                return r0.group;
            }

            /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m9816$$Nest$fgetheight(PreviewGroupCell r0) {
                return r0.height;
            }

            /* renamed from: -$$Nest$fgetinterpolator, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Interpolator m9817$$Nest$fgetinterpolator(PreviewGroupCell r0) {
                return r0.interpolator;
            }

            /* renamed from: -$$Nest$fgetleft, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m9818$$Nest$fgetleft(PreviewGroupCell r0) {
                return r0.left;
            }

            /* renamed from: -$$Nest$fgettop, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m9819$$Nest$fgettop(PreviewGroupCell r0) {
                return r0.top;
            }

            /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m9820$$Nest$fgetwidth(PreviewGroupCell r0) {
                return r0.width;
            }

            /* renamed from: -$$Nest$msetGroup, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m9821$$Nest$msetGroup(PreviewGroupCell r0, GroupCalculator r1, boolean r2) {
                r0.setGroup(r1, r2);
            }

            private PreviewGroupCell(PreviewGroupsView r4) {
                this.this$1 = r4;
                this.y = 0.0f;
                this.indexStart = 0;
                this.updateDuration = 200;
                this.lastMediaUpdate = 0;
                this.groupWidth = 0.0f;
                this.groupHeight = 0.0f;
                this.previousGroupWidth = 0.0f;
                this.previousGroupHeight = 0.0f;
                this.media = new ArrayList();
                this.interpolator = CubicBezierInterpolator.EASE_BOTH;
                this.padding = AndroidUtilities.dp(4.0f);
                int r0 = AndroidUtilities.dp(2.0f);
                this.gap = r0;
                this.halfGap = r0 / 2;
                this.buttonTextRect = new RectF();
                this.buttonTextBgPaint = new Paint(1);
                this.messageBackground = (MessageDrawable) r4.this$0.getThemedDrawable("drawableMsgOutMedia");
                this.backgroundCacheParams = new MessageDrawable.PathDrawParams();
            }

            public /* synthetic */ PreviewGroupCell(PreviewGroupsView r1, ChatAttachAlertPhotoLayoutPreviewIA r2) {
                this(r1);
            }

            private void setGroup(GroupCalculator r18, boolean r19) {
                this.group = r18;
                if (r18 != null) goto L5;
                return;
            L5:
                r18.calculate();
                long r3 = SystemClock.elapsedRealtime();
                if ((r3 - this.lastMediaUpdate) >= 200) goto L8;
                float r5 = (r3 - r5) / 200.0f;
                this.previousGroupHeight = AndroidUtilities.lerp(this.previousGroupHeight, this.groupHeight, r5);
                this.previousGroupWidth = AndroidUtilities.lerp(this.previousGroupWidth, this.groupWidth, r5);
            L9:
                this.groupWidth = r18.width / 1000.0f;
                this.groupHeight = r18.height;
                if (r19 == false) goto L12;
                long r7 = r3;
            L13:
                this.lastMediaUpdate = r7;
                this.stars = 0;
                ArrayList r52 = new ArrayList(r18.positions.keySet());
                int r6 = r52.size();
                int r8 = 0;
            L15:
                if (r8 >= r6) goto L27;
                MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r52.get(r8);
                MessageObject.GroupedMessagePosition r13 = r18.positions.get(r12);
                int r16 = r8;
                this.stars = Math.max(this.stars, r12.starsAmount);
                int r72 = this.media.size();
                int r82 = 0;
            L17:
                if (r82 >= r72) goto L22;
                MediaCell r14 = this.media.get(r82);
                if (r14.photoEntry == r12) goto L23;
                r82 = r82 + 1;
            L23:
                if (r14 != null) goto L25;
                MediaCell r73 = new MediaCell(this, null);
                MediaCell.m9827$$Nest$msetImage(r73, r12);
                MediaCell.m9825$$Nest$mlayout(r73, r18, r13, r19);
                this.media.add(r73);
            L26:
                r8 = r16 + 1;
                goto L15
            L25:
                MediaCell.m9825$$Nest$mlayout(r14, r18, r13, r19);
                goto L26
            L22:
                r14 = null;
                goto L23
            L27:
                int r53 = this.media.size();
                int r74 = 0;
            L28:
                if (r74 >= r53) goto L38;
                MediaCell r62 = this.media.get(r74);
                if (r18.positions.containsKey(r62.photoEntry) == true) goto L37;
                if (r62.scale <= 0.0f) goto L34;
            L36:
                MediaCell.m9825$$Nest$mlayout(r62, null, null, r19);
                goto L37
            L34:
                if ((MediaCell.m9822$$Nest$fgetlastUpdate(r62) + 200) > r3) goto L36;
                r62.detach();
                this.media.remove(r74);
                r74 = r74 - 1;
                r53 = r53 - 1;
            L37:
                r74 = r74 + 1;
                goto L28
            L38:
                this.this$1.invalidate();
                return;
            L12:
                r7 = 0;
                goto L13
            L8:
                this.previousGroupHeight = this.groupHeight;
                this.previousGroupWidth = this.groupWidth;
                goto L9
            }

            public void detach() {
                int r0 = 0;
            L4:
                if (r0 >= this.media.size()) goto L6;
                this.media.get(r0).detach();
                r0 = r0 + 1;
                goto L4
            }

            public boolean draw(Canvas r21) {
                float r4 = 1.0f;
                float r2 = this.interpolator.getInterpolation(Math.min(1.0f, (SystemClock.elapsedRealtime() - this.lastMediaUpdate) / 200.0f));
                int r6 = 0;
                if (r2 >= 1.0f) goto L5;
                boolean r3 = true;
            L6:
                Point r7 = AndroidUtilities.displaySize;
                float r8 = (AndroidUtilities.lerp(this.previousGroupWidth, this.groupWidth, r2) * this.this$1.getWidth()) * this.this$1.this$0.getPreviewScale();
                float r9 = (AndroidUtilities.lerp(this.previousGroupHeight, this.groupHeight, r2) * (Math.max(r7.x, r7.y) * 0.5f)) * this.this$1.this$0.getPreviewScale();
                if (this.messageBackground == null) goto L15;
                this.top = 0.0f;
                this.left = (this.this$1.getWidth() - Math.max(this.padding, r8)) / 2.0f;
                this.right = (this.this$1.getWidth() + Math.max(this.padding, r8)) / 2.0f;
                this.bottom = Math.max(this.padding * 2, r9);
                this.messageBackground.setTop(0, (int) r8, (int) r9, 0, 0, 0, false, false);
                this.messageBackground.setBounds((int) this.left, (int) this.top, (int) this.right, (int) this.bottom);
                if (this.groupWidth > 0.0f) goto L12;
                r4 = 1.0f - r2;
            L14:
                this.messageBackground.setAlpha((int) (r4 * 255.0f));
                this.messageBackground.drawCached(r21, this.backgroundCacheParams);
                float r22 = this.top;
                int r42 = this.padding;
                this.top = r22 + r42;
                this.left += r42;
                this.bottom -= r42;
                this.right -= r42;
                goto L15
            L12:
                if (this.previousGroupWidth > 0.0f) goto L14;
                r4 = r2;
            L15:
                this.width = this.right - this.left;
                this.height = this.bottom - this.top;
                int r23 = this.media.size();
            L16:
                if (r6 >= r23) goto L29;
                MediaCell r43 = this.media.get(r6);
                if (r43 == null) goto L28;
                if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$1.this$0) == null) goto L26;
                if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$1.this$0).photoEntry == r43.photoEntry) goto L28;
            L26:
                if (r43.draw(r21) == false) goto L28;
                r3 = true;
            L28:
                r6 = r6 + 1;
                goto L16
            L29:
                drawStarsButton(r21);
                return r3;
            L5:
                r3 = false;
                goto L6
            }

            public void drawStarsButton(Canvas r15) {
                long r0 = this.stars;
                if (r0 > 0) goto L6;
                return;
            L6:
                if (this.buttonText != null) goto L8;
            L9:
                this.buttonTextPrice = r0;
                this.buttonText = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContent", (int) r0), 0.7f), 14.0f, AndroidUtilities.bold());
            L10:
                float r02 = AndroidUtilities.dp(28.0f) + this.buttonText.getCurrentWidth();
                float r1 = AndroidUtilities.dp(32.0f);
                RectF r2 = this.buttonTextRect;
                float r4 = this.left;
                float r5 = this.width;
                float r8 = this.top;
                float r9 = this.height;
                r2.set(((r5 - r02) / 2.0f) + r4, ((r9 - r1) / 2.0f) + r8, r4 + ((r5 + r02) / 2.0f), r8 + ((r9 + r1) / 2.0f));
                this.buttonTextBgPaint.setColor(1610612736);
                float r12 = r1 / 2.0f;
                r15.drawRoundRect(this.buttonTextRect, r12, r12, this.buttonTextBgPaint);
                this.buttonText.draw(r15, ((this.left + (this.width / 2.0f)) - (r02 / 2.0f)) + AndroidUtilities.dp(14.0f), this.top + (this.height / 2.0f), -1, 1.0f);
                return;
            L8:
                if (this.buttonTextPrice == r0) goto L10;
                goto L9
            }

            public float getT() {
                return this.interpolator.getInterpolation(Math.min(1.0f, (SystemClock.elapsedRealtime() - this.lastMediaUpdate) / 200.0f));
            }

            public float measure() {
                Point r0 = AndroidUtilities.displaySize;
                return (AndroidUtilities.lerp(this.previousGroupHeight, this.groupHeight, getT()) * (Math.max(r0.x, r0.y) * 0.5f)) * this.this$1.this$0.getPreviewScale();
            }
        }

        /* renamed from: $r8$lambda$IEewyOJY70eMlPGYLx7-pm0qaZA, reason: not valid java name */
        public static /* synthetic */ void m9805$r8$lambda$IEewyOJY70eMlPGYLx7pm0qaZA(PreviewGroupsView r0, ValueAnimator r1) {
            r0.lambda$startDragging$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$LqippLyC2JRnZ_QdXoSxXwmrtQw(PreviewGroupsView r0, int r1) {
            r0.lambda$onTouchEvent$4(r1);
        }

        /* renamed from: $r8$lambda$b8jrnaOIYjc9qs9hUFAT-DfFc9A, reason: not valid java name */
        public static /* synthetic */ void m9806$r8$lambda$b8jrnaOIYjc9qs9hUFATDfFc9A(PreviewGroupsView r0, PreviewGroupCell r1, MediaController.PhotoEntry r2, int r3) {
            r0.lambda$onTouchEvent$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$vXqXvFZRAMm-M_5UUSyli5P3SuM, reason: not valid java name */
        public static /* synthetic */ void m9807$r8$lambda$vXqXvFZRAMmM_5UUSyli5P3SuM(PreviewGroupsView r0, long r1, PreviewGroupCell.MediaCell r3) {
            r0.lambda$onTouchEvent$2(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$wxKp2pmM5UTlMFUtQIUbNe21_7I(PreviewGroupsView r0, ValueAnimator r1) {
            r0.lambda$stopDragging$0(r1);
        }

        /* renamed from: -$$Nest$fgetdeletedPhotos, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m9808$$Nest$fgetdeletedPhotos(PreviewGroupsView r0) {
            return r0.deletedPhotos;
        }

        /* renamed from: -$$Nest$fgetdraggingT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9809$$Nest$fgetdraggingT(PreviewGroupsView r0) {
            return r0.draggingT;
        }

        /* renamed from: -$$Nest$fgetgroupCells, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9810$$Nest$fgetgroupCells(PreviewGroupsView r0) {
            return r0.groupCells;
        }

        /* renamed from: -$$Nest$fgetpaddingBottom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9811$$Nest$fgetpaddingBottom(PreviewGroupsView r0) {
            return r0.paddingBottom;
        }

        /* renamed from: -$$Nest$fgetpaddingTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9812$$Nest$fgetpaddingTop(PreviewGroupsView r0) {
            return r0.paddingTop;
        }

        /* renamed from: -$$Nest$fputscrollerStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9813$$Nest$fputscrollerStarted(PreviewGroupsView r0, boolean r1) {
            r0.scrollerStarted = r1;
        }

        /* renamed from: -$$Nest$mmeasurePureHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9814$$Nest$mmeasurePureHeight(PreviewGroupsView r0) {
            return r0.measurePureHeight();
        }

        public PreviewGroupsView(ChatAttachAlertPhotoLayoutPreview r5, Context r6) {
            this.this$0 = r5;
            super(r6);
            this.groupCells = new ArrayList();
            this.deletedPhotos = new HashMap();
            this.paddingTop = AndroidUtilities.dp(16.0f);
            this.paddingBottom = AndroidUtilities.dp(64.0f);
            this.lastMeasuredHeight = 0;
            this.lastGroupSeen = null;
            this.tapTime = 0;
            this.tapGroupCell = null;
            this.tapMediaCell = null;
            this.draggingT = 0.0f;
            this.tmpPoint = new PointF();
            this.scrollerStarted = false;
            this.scroller = new AnonymousClass2(this);
            this.photoViewerProvider = new GroupingPhotoViewerProvider(this);
            this.undoViewId = 0;
            this.images = new HashMap();
            setWillNotDraw(false);
            ChatActionCell r0 = new ChatActionCell(r6, true, ChatAttachAlertPhotoLayoutPreview.m9791$$Nest$fgetthemeDelegate(r5));
            this.hintView = r0;
            r0.setCustomText(LocaleController.getString(R.string.AttachMediaDragHint));
            addView(this.hintView);
        }

        private boolean[] groupSeen() {
            boolean[] r0 = new boolean[this.groupCells.size()];
            float r1 = this.paddingTop;
            int r2 = this.this$0.listView.computeVerticalScrollOffset();
            int r4 = 0;
            this.viewTop = Math.max(0, r2 - this.this$0.getListTopPadding());
            this.viewBottom = (this.this$0.listView.getMeasuredHeight() - this.this$0.getListTopPadding()) + r2;
            int r22 = this.groupCells.size();
        L3:
            if (r4 >= r22) goto L5;
            float r3 = this.groupCells.get(r4).measure() + r1;
            r0[r4] = isSeen(r1, r3);
            r4 = r4 + 1;
            r1 = r3;
            goto L3
        L5:
            return r0;
        }

        private /* synthetic */ void lambda$onTouchEvent$2(long r3, PreviewGroupCell.MediaCell r5) {
            if (this.this$0.listView.scrollingByUser == false) goto L5;
            return;
        L5:
            if (this.tapTime != r3) goto L16;
            PreviewGroupCell.MediaCell r32 = this.tapMediaCell;
            if (r32 != r5) goto L17;
            startDragging(r32);
            RectF r33 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).rect();
            RectF r4 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).drawingRect();
            ChatAttachAlertPhotoLayoutPreview r52 = this.this$0;
            ChatAttachAlertPhotoLayoutPreview.m9800$$Nest$fputdraggingCellLeft(r52, (((ChatAttachAlertPhotoLayoutPreview.m9787$$Nest$fgetdraggingCellTouchX(r52) - r33.left) / r33.width()) + 0.5f) / 2.0f);
            ChatAttachAlertPhotoLayoutPreview r53 = this.this$0;
            ChatAttachAlertPhotoLayoutPreview.m9801$$Nest$fputdraggingCellTop(r53, (ChatAttachAlertPhotoLayoutPreview.m9788$$Nest$fgetdraggingCellTouchY(r53) - r33.top) / r33.height());
            ChatAttachAlertPhotoLayoutPreview.m9797$$Nest$fputdraggingCellFromWidth(this.this$0, r4.width());
            ChatAttachAlertPhotoLayoutPreview.m9796$$Nest$fputdraggingCellFromHeight(this.this$0, r4.height());
            this.this$0.performHapticFeedback(0, 2);     // Catch: Exception -> L12
            return;
        L18:
            return;
        L17:
            return;
        }

        private /* synthetic */ void lambda$onTouchEvent$3(PreviewGroupCell r3, MediaController.PhotoEntry r4, int r5) {
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0) == null) goto L5;
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).cancel();
        L5:
            ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$0, null);
            this.draggingT = 0.0f;
            pushToGroup(r3, r4, r5);
            updateGroups();
            toPhotoLayout(ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0), false);
        }

        private /* synthetic */ void lambda$onTouchEvent$4(int r2) {
            if (r2 == this.undoViewId) goto L5;
            return;
        L5:
            if (ChatAttachAlertPhotoLayoutPreview.m9792$$Nest$fgetundoView(this.this$0).isShown() == false) goto L9;
            ChatAttachAlertPhotoLayoutPreview.m9792$$Nest$fgetundoView(this.this$0).hide(true, 1);
            return;
        }

        private /* synthetic */ void lambda$startDragging$1(ValueAnimator r1) {
            this.draggingT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$stopDragging$0(ValueAnimator r1) {
            this.draggingT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private int measureHeight() {
            return Math.max(measurePureHeight(), (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(45.0f));
        }

        private int measurePureHeight() {
            int r0 = this.paddingTop + this.paddingBottom;
            int r1 = this.groupCells.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L6;
            r0 = (int) (r0 + this.groupCells.get(r2).measure());
            r2 = r2 + 1;
            goto L3
        L6:
            if (this.hintView.getMeasuredHeight() > 0) goto L9;
            this.hintView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(9999, Integer.MIN_VALUE));
        L9:
            return r0 + this.hintView.getMeasuredHeight();
        }

        private void pushToGroup(PreviewGroupCell r4, MediaController.PhotoEntry r5, int r6) {
            PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.add(Math.min(PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.size(), r6), r5);
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.size() != 11) goto L13;
            MediaController.PhotoEntry r52 = PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.get(10);
            PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.remove(10);
            int r62 = this.groupCells.indexOf(r4);
            if (r62 < 0) goto L13;
            int r63 = r62 + 1;
            if (r63 != this.groupCells.size()) goto L9;
            PreviewGroupCell r64 = null;
        L10:
            if (r64 != null) goto L12;
            PreviewGroupCell r65 = new PreviewGroupCell(this, null);
            ArrayList r1 = new ArrayList();
            r1.add(r52);
            PreviewGroupCell.m9821$$Nest$msetGroup(r65, new GroupCalculator(this.this$0, r1), true);
            invalidate();
            goto L13
        L12:
            pushToGroup(r64, r52, 0);
            goto L13
        L9:
            r64 = this.groupCells.get(r63);
        L13:
            PreviewGroupCell.m9821$$Nest$msetGroup(r4, PreviewGroupCell.m9815$$Nest$fgetgroup(r4), true);
        }

        private void updateGroups() {
            int r0 = this.groupCells.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L13;
            PreviewGroupCell r3 = this.groupCells.get(r2);
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos.size() >= 10) goto L12;
            if (r2 >= (this.groupCells.size() - 1)) goto L12;
            int r5 = 10 - PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos.size();
            PreviewGroupCell r4 = this.groupCells.get(r2 + 1);
            ArrayList r7 = new ArrayList();
            int r52 = Math.min(r5, PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.size());
            int r8 = 0;
        L9:
            if (r8 >= r52) goto L11;
            r7.add(PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.remove(0));
            r8 = r8 + 1;
            goto L9
        L11:
            PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos.addAll(r7);
            PreviewGroupCell.m9821$$Nest$msetGroup(r3, PreviewGroupCell.m9815$$Nest$fgetgroup(r3), true);
            PreviewGroupCell.m9821$$Nest$msetGroup(r4, PreviewGroupCell.m9815$$Nest$fgetgroup(r4), true);
        L12:
            r2 = r2 + 1;
            goto L3
        }

        public void calcPhotoArrays() {
            this.photosMap = ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0).getSelectedPhotos();
            this.photosMapKeys = new ArrayList(this.photosMap.entrySet());
            this.selectedPhotos = new HashMap();
            this.photosOrder = new ArrayList();
            int r0 = this.groupCells.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L33;
            GroupCalculator r3 = PreviewGroupCell.m9815$$Nest$fgetgroup(this.groupCells.get(r2));
            if (r3.photos.size() == 0) goto L32;
            int r4 = r3.photos.size();
            int r5 = 0;
        L8:
            if (r5 >= r4) goto L32;
            MediaController.PhotoEntry r6 = r3.photos.get(r5);
            if (this.deletedPhotos.containsKey(r6) == false) goto L12;
            Object r7 = this.deletedPhotos.get(r6);
            this.selectedPhotos.put(r7, r6);
            this.photosOrder.add(r7);
        L31:
            r5 = r5 + 1;
            goto L8
        L12:
            int r72 = 0;
        L14:
            if (r72 >= this.photosMapKeys.size()) goto L19;
            Map.Entry<Object, Object> r8 = this.photosMapKeys.get(r72);
            Object r9 = r8.getValue();
            if (r9 == r6) goto L17;
            r72 = r72 + 1;
            goto L14
        L17:
            Object r62 = r8.getKey();
            this.selectedPhotos.put(r62, r9);
            this.photosOrder.add(r62);
            goto L31
        L19:
            int r73 = 0;
        L21:
            if (r73 >= this.photosMapKeys.size()) goto L31;
            Map.Entry<Object, Object> r82 = this.photosMapKeys.get(r73);
            Object r92 = r82.getValue();
            if ((r92 instanceof MediaController.PhotoEntry) == false) goto L30;
            String r10 = ((MediaController.PhotoEntry) r92).path;
            if (r10 == null) goto L30;
            if (r6 == null) goto L30;
            if (r10.equals(r6.path) == false) goto L30;
            Object r63 = r82.getKey();
            this.selectedPhotos.put(r63, r92);
            this.photosOrder.add(r63);
        L30:
            r73 = r73 + 1;
        L32:
            r2 = r2 + 1;
            goto L3
        }

        public PointF dragTranslate() {
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) != null) goto L6;
            PointF r9 = this.tmpPoint;
            r9.x = 0.0f;
            r9.y = 0.0f;
            return r9;
        L6:
            boolean r0 = ChatAttachAlertPhotoLayoutPreview.m9784$$Nest$fgetdraggingCellHiding(this.this$0);
            ChatAttachAlertPhotoLayoutPreview r1 = this.this$0;
            if (r0 == true) goto L9;
            RectF r02 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(r1).rect();
            RectF r12 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).rect(1.0f);
            PointF r2 = this.tmpPoint;
            r2.x = AndroidUtilities.lerp(r12.left + (r02.width() / 2.0f), ChatAttachAlertPhotoLayoutPreview.m9787$$Nest$fgetdraggingCellTouchX(this.this$0) - ((ChatAttachAlertPhotoLayoutPreview.m9785$$Nest$fgetdraggingCellLeft(this.this$0) - 0.5f) * ChatAttachAlertPhotoLayoutPreview.m9782$$Nest$fgetdraggingCellFromWidth(this.this$0)), this.draggingT);
            PointF r22 = this.tmpPoint;
            r22.y = AndroidUtilities.lerp((ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell.y + r12.top) + (r02.height() / 2.0f), (ChatAttachAlertPhotoLayoutPreview.m9788$$Nest$fgetdraggingCellTouchY(this.this$0) - ((ChatAttachAlertPhotoLayoutPreview.m9786$$Nest$fgetdraggingCellTop(this.this$0) - 0.5f) * ChatAttachAlertPhotoLayoutPreview.m9781$$Nest$fgetdraggingCellFromHeight(this.this$0))) + ChatAttachAlertPhotoLayoutPreview.m9783$$Nest$fgetdraggingCellGroupY(this.this$0), this.draggingT);
        L11:
            return this.tmpPoint;
        L9:
            RectF r03 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(r1).rect();
            RectF r13 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).rect(1.0f);
            PointF r23 = this.tmpPoint;
            r23.x = AndroidUtilities.lerp(r13.left + (r03.width() / 2.0f), this.savedDragFromX, this.draggingT / this.savedDraggingT);
            PointF r24 = this.tmpPoint;
            r24.y = AndroidUtilities.lerp((ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell.y + r13.top) + (r03.height() / 2.0f), this.savedDragFromY, this.draggingT / this.savedDraggingT);
            goto L11
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return false;
        }

        public void fromPhotoArrays() {
            int r1 = 0;
        L3:
            int r2 = this.groupCells.size();
            ArrayList<PreviewGroupCell> r3 = this.groupCells;
            if (r1 >= r2) goto L6;
            r3.get(r1).detach();
            r1 = r1 + 1;
            goto L3
        L6:
            r3.clear();
            ArrayList r12 = new ArrayList();
            int r22 = this.photosOrder.size();
            int r32 = r22 - 1;
            int r4 = 0;
        L7:
            if (r4 >= r22) goto L13;
            Integer r5 = (Integer) this.photosOrder.get(r4);
            r5.intValue();
            r12.add((MediaController.PhotoEntry) this.photosMap.get(r5));
            if ((r4 % 10) == 9) goto L11;
            if (r4 == r32) goto L11;
        L12:
            r4 = r4 + 1;
        L11:
            PreviewGroupCell r52 = new PreviewGroupCell(this, null);
            PreviewGroupCell.m9821$$Nest$msetGroup(r52, new GroupCalculator(this.this$0, r12), false);
            this.groupCells.add(r52);
            r12 = new ArrayList();
            goto L12
        }

        public void fromPhotoLayout(ChatAttachAlertPhotoLayout r2) {
            this.photosOrder = r2.getSelectedPhotosOrder();
            this.photosMap = r2.getSelectedPhotos();
            fromPhotoArrays();
        }

        public ArrayList<MediaController.PhotoEntry> getPhotos() {
            ArrayList<MediaController.PhotoEntry> r0 = new ArrayList();
            int r1 = this.groupCells.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L12;
            PreviewGroupCell r3 = this.groupCells.get(r2);
            if (r3 == null) goto L11;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r3) == null) goto L11;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos == null) goto L11;
            r0.addAll(PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos);
        L11:
            r2 = r2 + 1;
            goto L3
        L12:
            return r0;
        }

        public int getPhotosCount() {
            int r0 = this.groupCells.size();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r0) goto L12;
            PreviewGroupCell r3 = this.groupCells.get(r1);
            if (r3 == null) goto L11;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r3) == null) goto L11;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos == null) goto L11;
            r2 = r2 + PreviewGroupCell.m9815$$Nest$fgetgroup(r3).photos.size();
        L11:
            r1 = r1 + 1;
            goto L3
        L12:
            return r2;
        }

        @Override // android.view.View
        public void invalidate() {
            int r0 = measureHeight();
            if (this.lastMeasuredHeight == r0) goto L5;
            this.lastMeasuredHeight = r0;
            requestLayout();
        L5:
            super.invalidate();
        }

        public boolean isSeen(float r3, float r4) {
            float r0 = this.viewTop;
            if (r3 < r0) goto L7;
            if (r3 > this.viewBottom) goto L7;
            return true;
        L7:
            if (r4 < r0) goto L11;
            if (r4 > this.viewBottom) goto L11;
            return true;
        L11:
            if (r3 <= r0) goto L13;
            return false;
        L13:
            if (r4 < this.viewBottom) goto L20;
            return true;
        L20:
            return false;
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            float r0 = this.paddingTop;
            int r1 = this.this$0.listView.computeVerticalScrollOffset();
            int r3 = 0;
            this.viewTop = Math.max(0, r1 - this.this$0.getListTopPadding());
            this.viewBottom = (this.this$0.listView.getMeasuredHeight() - this.this$0.getListTopPadding()) + r1;
            r11.save();
            r11.translate(0.0f, this.paddingTop);
            int r12 = this.groupCells.size();
            int r4 = 0;
        L3:
            if (r3 >= r12) goto L20;
            PreviewGroupCell r5 = this.groupCells.get(r3);
            float r6 = r5.measure();
            r5.y = r0;
            r5.indexStart = r4;
            float r7 = this.viewTop;
            if (r0 >= r7) goto L7;
        L8:
            float r8 = r0 + r6;
            if (r8 < r7) goto L13;
            if (r8 > this.viewBottom) goto L13;
        L17:
            if (r5.draw(r11) == false) goto L19;
            invalidate();
        L19:
            r11.translate(0.0f, r6);
            r0 = r0 + r6;
            r4 = r4 + PreviewGroupCell.m9815$$Nest$fgetgroup(r5).photos.size();
            r3 = r3 + 1;
        L13:
            if (r0 > r7) goto L19;
            if (r8 < this.viewBottom) goto L19;
        L7:
            if (r0 <= this.viewBottom) goto L17;
        L20:
            ChatActionCell r13 = this.hintView;
            r13.setVisiblePart(r0, r13.getMeasuredHeight());
            if (this.hintView.hasGradientService() == false) goto L23;
            this.hintView.drawBackground(r11, true);
            this.hintView.drawReactions(r11, true, null);
        L23:
            this.hintView.draw(r11);
            r11.restore();
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L29;
            r11.save();
            PointF r02 = dragTranslate();
            r11.translate(r02.x, r02.y);
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).draw(r11, true) == false) goto L28;
            invalidate();
        L28:
            r11.restore();
        L29:
            super.onDraw(r11);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            ChatActionCell r12 = this.hintView;
            r12.layout(0, 0, r12.getMeasuredWidth(), this.hintView.getMeasuredHeight());
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            this.hintView.measure(r4, View.MeasureSpec.makeMeasureSpec(9999, Integer.MIN_VALUE));
            if (this.lastMeasuredHeight > 0) goto L5;
            this.lastMeasuredHeight = measureHeight();
        L5:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(Math.max(View.MeasureSpec.getSize(r5), this.lastMeasuredHeight), TLObject.FLAG_30));
        }

        public void onScroll() {
            int r1 = 0;
            boolean r2 = true;
            if (this.lastGroupSeen != null) goto L5;
            boolean r0 = true;
        L6:
            if (r0 == true) goto L18;
            boolean[] r3 = groupSeen();
            if (r3.length == this.lastGroupSeen.length) goto L11;
        L17:
            r0 = r2;
        L19:
            if (r0 == false) goto L24;
            invalidate();
            return;
        L24:
            return;
        L11:
            if (r1 >= r3.length) goto L16;
            if (r3[r1] != this.lastGroupSeen[r1]) goto L17;
            r1 = r1 + 1;
            goto L11
        L16:
            r2 = r0;
            goto L17
        L18:
            this.lastGroupSeen = groupSeen();
            goto L19
        L5:
            r0 = false;
            goto L6
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r22) {
            float r1 = r22.getX();
            float r2 = r22.getY();
            int r3 = this.groupCells.size();
            float r5 = 0.0f;
            float r7 = 0.0f;
            int r6 = 0;
        L3:
            if (r6 >= r3) goto L10;
            PreviewGroupCell r9 = this.groupCells.get(r6);
            float r10 = r9.measure();
            if (r2 < r7) goto L9;
            if (r2 > (r7 + r10)) goto L9;
        L11:
            if (r9 == null) goto L20;
            int r62 = r9.media.size();
            int r102 = 0;
        L13:
            if (r102 >= r62) goto L20;
            PreviewGroupCell.MediaCell r11 = r9.media.get(r102);
            if (r11 == null) goto L19;
            if (r11.drawingRect().contains(r1, r2 - r7) == false) goto L19;
        L22:
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L67;
            RectF r63 = ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).rect();
            PointF r13 = dragTranslate();
            RectF r14 = new RectF();
            float r15 = r13.x;
            float r132 = r13.y;
            int r18 = 4;
            r14.set(r15 - (r63.width() / 2.0f), r132 - (r63.height() / 2.0f), r15 + (r63.width() / 2.0f), r132 + (r63.height() / 2.0f));
            float r72 = 0.0f;
            float r133 = 0.0f;
            int r4 = 0;
            PreviewGroupCell r64 = null;
        L24:
            if (r4 >= r3) goto L33;
            PreviewGroupCell r152 = this.groupCells.get(r4);
            float r12 = r72 + r152.measure();
            if (r12 < r14.top) goto L32;
            float r103 = r14.bottom;
            if (r103 < r72) goto L32;
            float r104 = Math.min(r12, r103) - Math.max(r72, r14.top);
            if (r104 <= r133) goto L32;
            r133 = r104;
            r64 = r152;
        L32:
            r4 = r4 + 1;
            r72 = r12;
            goto L24
        L33:
            int r16 = 2;
            int r17 = 1;
            if (r64 == null) goto L65;
            int r32 = r64.media.size();
            float r8 = 0.0f;
            int r42 = 0;
            PreviewGroupCell.MediaCell r73 = null;
        L36:
            if (r42 >= r32) goto L64;
            PreviewGroupCell.MediaCell r105 = r64.media.get(r42);
            if (r105 != null) goto L40;
        L62:
            float r20 = r5;
        L63:
            r42 = r42 + 1;
            r5 = r20;
            goto L36
        L40:
            if (r105 == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0)) goto L62;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r64).photos.contains(r105.photoEntry) == false) goto L62;
            RectF r122 = r105.drawingRect();
            if ((PreviewGroupCell.MediaCell.m9823$$Nest$fgetpositionFlags(r105) & 4) <= 0) goto L47;
            r122.top = r5;
        L47:
            if ((PreviewGroupCell.MediaCell.m9823$$Nest$fgetpositionFlags(r105) & 1) <= 0) goto L50;
            r122.left = r5;
        L50:
            if ((PreviewGroupCell.MediaCell.m9823$$Nest$fgetpositionFlags(r105) & 2) <= 0) goto L53;
            r122.right = getWidth();
        L53:
            if ((PreviewGroupCell.MediaCell.m9823$$Nest$fgetpositionFlags(r105) & 8) <= 0) goto L56;
            r122.bottom = PreviewGroupCell.m9816$$Nest$fgetheight(r64);
        L56:
            if (RectF.intersects(r14, r122) == false) goto L62;
            r20 = r5;
            float r134 = ((Math.min(r122.right, r14.right) - Math.max(r122.left, r14.left)) * (Math.min(r122.bottom, r14.bottom) - Math.max(r122.top, r14.top))) / (r14.width() * r14.height());
            if (r134 <= 0.15f) goto L63;
            if (r134 <= r8) goto L63;
            r73 = r105;
            r8 = r134;
            goto L63
        L64:
            float r202 = r5;
        L68:
            int r33 = r22.getAction();
            if (r33 != 0) goto L87;
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) != null) goto L87;
            ChatAttachAlertPhotoLayoutPreview r82 = this.this$0;
            if (r82.listView.scrollingByUser == true) goto L87;
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(r82) != null) goto L77;
        L80:
            if (r9 == null) goto L87;
            if (r11 == null) goto L87;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r9) == null) goto L87;
            if (PreviewGroupCell.m9815$$Nest$fgetgroup(r9).photos.contains(r11.photoEntry) == false) goto L87;
            this.tapGroupCell = r9;
            this.tapMediaCell = r11;
            ChatAttachAlertPhotoLayoutPreview.m9802$$Nest$fputdraggingCellTouchX(this.this$0, r1);
            ChatAttachAlertPhotoLayoutPreview.m9803$$Nest$fputdraggingCellTouchY(this.this$0, r2);
            ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$0, null);
            long r19 = SystemClock.elapsedRealtime();
            this.tapTime = r19;
            AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$$ExternalSyntheticLambda0(this, r19, this.tapMediaCell), ViewConfiguration.getLongPressTimeout());
            invalidate();
            int r110 = r17;
            int r83 = r110;
        L182:
            if (r33 != r110) goto L184;
        L185:
            this.tapTime = 0;
            removeCallbacks(this.scroller);
            this.scrollerStarted = false;
            if (r83 != 0) goto L189;
            stopDragging();
            return r110;
        L189:
            return r83;
        L184:
            if (r33 != 3) goto L189;
        L77:
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).isRunning() == false) goto L80;
        L87:
            if (r33 == r16) goto L89;
        L96:
            int r84 = r17;
            if (r33 == r84) goto L99;
        L140:
            if (r33 == r84) goto L142;
        L181:
            r110 = 1;
            r83 = 0;
            goto L182
        L142:
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) != null) goto L181;
            PreviewGroupCell.MediaCell r65 = this.tapMediaCell;
            if (r65 == null) goto L181;
            if (this.tapGroupCell == null) goto L181;
            if (r65.wasSpoiler == true) goto L150;
        L152:
            RectF r66 = this.tapMediaCell.drawingRect();
            RectF r74 = AndroidUtilities.rectTmp;
            r74.set(r66.right - AndroidUtilities.dp(36.4f), PreviewGroupCell.m9819$$Nest$fgettop(this.tapGroupCell) + r66.top, r66.right, (PreviewGroupCell.m9819$$Nest$fgettop(this.tapGroupCell) + r66.top) + AndroidUtilities.dp(36.4f));
            if (r74.contains(r1, r2 - this.tapMediaCell.groupCell.y) == true) goto L155;
            calcPhotoArrays();
            ArrayList<MediaController.PhotoEntry> r111 = getPhotos();
            int r85 = r111.indexOf(this.tapMediaCell.photoEntry);
            ChatAttachAlert r23 = this.this$0.parentAlert;
            if (r23.avatarPicker == 0) goto L167;
            int r92 = 1;
        L166:
            ChatActivity r123 = null;
        L171:
            BaseFragment r24 = r23.baseFragment;
            if (r24 != null) goto L175;
            r24 = LaunchActivity.getLastFragment();
        L175:
            if (this.this$0.parentAlert.delegate.needEnterComment() == true) goto L177;
            AndroidUtilities.hideKeyboard(r24.getFragmentView().findFocus());
            AndroidUtilities.hideKeyboard(this.this$0.parentAlert.getContainer().findFocus());
        L177:
            PhotoViewer.getInstance().setParentActivity(r24, this.this$0.resourcesProvider);
            PhotoViewer.getInstance().setParentAlert(this.this$0.parentAlert);
            PhotoViewer r25 = PhotoViewer.getInstance();
            ChatAttachAlert r67 = this.this$0.parentAlert;
            r25.setMaxSelectedPhotos(r67.maxSelectedPhotos, r67.allowOrder);
            this.photoViewerProvider.init(r111);
            PhotoViewer.getInstance().openPhotoForSelect(new ArrayList(r111), r85, r92, false, this.photoViewerProvider, r123);
            if (ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0).captionForAllMedia() == false) goto L180;
            PhotoViewer.getInstance().setCaption(this.this$0.parentAlert.getCommentView().getText());
        L180:
            this.tapMediaCell = null;
            this.tapTime = 0;
            ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$0, null);
            this.draggingT = r202;
        L138:
            r110 = 1;
            r83 = 1;
            goto L182
        L167:
            BaseFragment r68 = r23.baseFragment;
            if ((r68 instanceof ChatActivity) == false) goto L170;
            r123 = (ChatActivity) r68;
            r92 = 0;
            goto L171
        L170:
            r92 = r18;
            goto L166
        L155:
            if (this.this$0.getSelectedItemsCount() <= 1) goto L180;
            MediaController.PhotoEntry r106 = this.tapMediaCell.photoEntry;
            int r112 = PreviewGroupCell.m9815$$Nest$fgetgroup(this.tapGroupCell).photos.indexOf(r106);
            if (r112 < 0) goto L160;
            saveDeletedImageId(r106);
            PreviewGroupCell r26 = this.tapGroupCell;
            PreviewGroupCell.m9815$$Nest$fgetgroup(r26).photos.remove(r112);
            PreviewGroupCell.m9821$$Nest$msetGroup(r26, PreviewGroupCell.m9815$$Nest$fgetgroup(r26), true);
            updateGroups();
            toPhotoLayout(ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0), false);
            int r135 = this.undoViewId + 1;
            this.undoViewId = r135;
            ChatAttachAlertPhotoLayoutPreview.m9792$$Nest$fgetundoView(this.this$0).showWithAction(0, 82, r106, null, new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$$ExternalSyntheticLambda1(this, r26, r106, r112));
            postDelayed(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$$ExternalSyntheticLambda2(this, r135), 4000);
        L160:
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0) == null) goto L180;
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).cancel();
            goto L180
        L150:
            if (PreviewGroupCell.MediaCell.m9824$$Nest$fgetspoilerRevealProgress(r65) != r202) goto L152;
            PreviewGroupCell.MediaCell.m9828$$Nest$mstartRevealMedia(this.tapMediaCell, r22.getX(), r22.getY());
            goto L138
        L99:
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L139;
            if (r9 == null) goto L105;
            if (r11 == null) goto L105;
            if (r11 == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0)) goto L105;
        L113:
            if (r9 == null) goto L137;
            if (r11 == null) goto L137;
            if (r11 == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0)) goto L137;
            int r113 = PreviewGroupCell.m9815$$Nest$fgetgroup(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell).photos.indexOf(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).photoEntry);
            int r27 = PreviewGroupCell.m9815$$Nest$fgetgroup(r9).photos.indexOf(r11.photoEntry);
            if (r113 < 0) goto L120;
            PreviewGroupCell.m9815$$Nest$fgetgroup(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell).photos.remove(r113);
            PreviewGroupCell.m9821$$Nest$msetGroup(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell, PreviewGroupCell.m9815$$Nest$fgetgroup(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell), true);
        L120:
            if (r27 >= 0) goto L122;
        L191:
            this.this$0.performHapticFeedback(7, 2);     // Catch: Exception -> L190
        L136:
            updateGroups();
            toPhotoLayout(ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0), false);
            goto L137
        L122:
            if (this.groupCells.indexOf(r9) <= this.groupCells.indexOf(ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell)) goto L124;
            r27 = r27 + 1;
        L124:
            pushToGroup(r9, ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).photoEntry, r27);
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).groupCell == r9) goto L191;
            int r114 = r9.media.size();
            int r28 = 0;
        L127:
            if (r28 >= r114) goto L132;
            PreviewGroupCell.MediaCell r69 = r9.media.get(r28);
            if (r69.photoEntry == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).photoEntry) goto L130;
            r28 = r28 + 1;
            goto L127
        L130:
            PreviewGroupCell.MediaCell r86 = r69;
        L133:
            if (r86 == null) goto L191;
            remeasure();
            PreviewGroupCell.MediaCell.m9826$$Nest$mlayoutFrom(r86, ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0));
            ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$0, r86);
            r86.groupCell = r9;
            ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).fromScale = 1.0f;
            r86.scale = 1.0f;
            remeasure();
            goto L191
        L132:
            r86 = null;
        L137:
            stopDragging();
        L105:
            if (r64 == null) goto L112;
            if (r73 == null) goto L112;
            if (r73 == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0)) goto L112;
            if (r73.photoEntry == ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0).photoEntry) goto L112;
            r9 = r64;
            r11 = r73;
        L112:
            r9 = null;
            r11 = null;
            goto L113
        L139:
            r84 = 1;
            goto L140
        L89:
            if (ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(this.this$0) == null) goto L96;
            if (ChatAttachAlertPhotoLayoutPreview.m9784$$Nest$fgetdraggingCellHiding(this.this$0) == true) goto L96;
            ChatAttachAlertPhotoLayoutPreview.m9802$$Nest$fputdraggingCellTouchX(this.this$0, r1);
            ChatAttachAlertPhotoLayoutPreview.m9803$$Nest$fputdraggingCellTouchY(this.this$0, r2);
            boolean r87 = r17;
            if (this.scrollerStarted == true) goto L95;
            this.scrollerStarted = r87;
            postDelayed(this.scroller, 16);
        L95:
            invalidate();
            r110 = r87 ? 1 : 0;
            r83 = r87;
            goto L182
        L65:
            r202 = 0.0f;
        L66:
            r73 = null;
            goto L68
        L67:
            r202 = 0.0f;
            r16 = 2;
            r17 = 1;
            r18 = 4;
            r64 = null;
        L19:
            r102 = r102 + 1;
        L20:
            r11 = null;
        L9:
            r7 = r7 + r10;
            r6 = r6 + 1;
            goto L3
        L10:
            r9 = null;
            goto L11
        }

        public void remeasure() {
            float r0 = this.paddingTop;
            int r1 = this.groupCells.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r2 >= r1) goto L5;
            PreviewGroupCell r4 = this.groupCells.get(r2);
            float r5 = r4.measure();
            r4.y = r0;
            r4.indexStart = r3;
            r0 = r0 + r5;
            r3 = r3 + PreviewGroupCell.m9815$$Nest$fgetgroup(r4).photos.size();
            r2 = r2 + 1;
            goto L3
        }

        public void saveDeletedImageId(MediaController.PhotoEntry r5) {
            if (ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0) == null) goto L12;
            ArrayList r1 = new ArrayList(ChatAttachAlertPhotoLayoutPreview.m9790$$Nest$fgetphotoLayout(this.this$0).getSelectedPhotos().entrySet());
            int r0 = r1.size();
            int r2 = 0;
        L6:
            if (r2 >= r0) goto L15;
            if (((Map.Entry) r1.get(r2)).getValue() == r5) goto L9;
            r2 = r2 + 1;
            goto L6
        L9:
            this.deletedPhotos.put(r5, ((Map.Entry) r1.get(r2)).getKey());
            return;
        L15:
            return;
        }

        public void startDragging(PreviewGroupCell.MediaCell r3) {
            ChatAttachAlertPhotoLayoutPreview.m9795$$Nest$fputdraggingCell(this.this$0, r3);
            ChatAttachAlertPhotoLayoutPreview r32 = this.this$0;
            ChatAttachAlertPhotoLayoutPreview.m9798$$Nest$fputdraggingCellGroupY(r32, ChatAttachAlertPhotoLayoutPreview.m9780$$Nest$fgetdraggingCell(r32).groupCell.y);
            ChatAttachAlertPhotoLayoutPreview.m9799$$Nest$fputdraggingCellHiding(this.this$0, false);
            this.draggingT = 0.0f;
            invalidate();
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0) == null) goto L5;
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).cancel();
        L5:
            ChatAttachAlertPhotoLayoutPreview.m9794$$Nest$fputdraggingAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).addUpdateListener(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$$ExternalSyntheticLambda4(this));
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).setDuration(200);
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).start();
        }

        public void stopDragging() {
            if (ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0) == null) goto L5;
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).cancel();
        L5:
            PointF r0 = dragTranslate();
            this.savedDraggingT = this.draggingT;
            this.savedDragFromX = r0.x;
            this.savedDragFromY = r0.y;
            ChatAttachAlertPhotoLayoutPreview.m9799$$Nest$fputdraggingCellHiding(this.this$0, true);
            ChatAttachAlertPhotoLayoutPreview.m9794$$Nest$fputdraggingAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{this.savedDraggingT, 0.0f}));
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).addUpdateListener(new ChatAttachAlertPhotoLayoutPreview$PreviewGroupsView$$ExternalSyntheticLambda3(this));
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).addListener(new AnonymousClass1(this));
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).setDuration(200);
            ChatAttachAlertPhotoLayoutPreview.m9779$$Nest$fgetdraggingAnimator(this.this$0).start();
            invalidate();
        }

        public void toPhotoLayout(ChatAttachAlertPhotoLayout r4, boolean r5) {
            int r0 = r4.getSelectedPhotosOrder().size();
            calcPhotoArrays();
            r4.updateSelected(this.selectedPhotos, this.photosOrder, r5);
            if (r0 == this.photosOrder.size()) goto L6;
            this.this$0.parentAlert.updateCountButton(1);
            return;
        }
    }

    /* renamed from: $r8$lambda$BZpB0ZXRaz8FeL3fRNzpwq9h-34, reason: not valid java name */
    public static /* synthetic */ void m9777$r8$lambda$BZpB0ZXRaz8FeL3fRNzpwq9h34(ChatAttachAlertPhotoLayoutPreview r0) {
        r0.lambda$onShow$1();
    }

    /* renamed from: $r8$lambda$MeFNyNP385Ipf-uwWMA69jrEVJc, reason: not valid java name */
    public static /* synthetic */ void m9778$r8$lambda$MeFNyNP385IpfuwWMA69jrEVJc(ChatAttachAlertPhotoLayoutPreview r0, ChatAttachAlert.AttachAlertLayout r1) {
        r0.lambda$onShow$0(r1);
    }

    /* renamed from: -$$Nest$fgetdraggingAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m9779$$Nest$fgetdraggingAnimator(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingAnimator;
    }

    /* renamed from: -$$Nest$fgetdraggingCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewGroupsView.PreviewGroupCell.MediaCell m9780$$Nest$fgetdraggingCell(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCell;
    }

    /* renamed from: -$$Nest$fgetdraggingCellFromHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9781$$Nest$fgetdraggingCellFromHeight(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellFromHeight;
    }

    /* renamed from: -$$Nest$fgetdraggingCellFromWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9782$$Nest$fgetdraggingCellFromWidth(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellFromWidth;
    }

    /* renamed from: -$$Nest$fgetdraggingCellGroupY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9783$$Nest$fgetdraggingCellGroupY(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellGroupY;
    }

    /* renamed from: -$$Nest$fgetdraggingCellHiding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9784$$Nest$fgetdraggingCellHiding(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellHiding;
    }

    /* renamed from: -$$Nest$fgetdraggingCellLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9785$$Nest$fgetdraggingCellLeft(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellLeft;
    }

    /* renamed from: -$$Nest$fgetdraggingCellTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9786$$Nest$fgetdraggingCellTop(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellTop;
    }

    /* renamed from: -$$Nest$fgetdraggingCellTouchX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9787$$Nest$fgetdraggingCellTouchX(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellTouchX;
    }

    /* renamed from: -$$Nest$fgetdraggingCellTouchY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9788$$Nest$fgetdraggingCellTouchY(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.draggingCellTouchY;
    }

    /* renamed from: -$$Nest$fgetgroupsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewGroupsView m9789$$Nest$fgetgroupsView(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.groupsView;
    }

    /* renamed from: -$$Nest$fgetphotoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlertPhotoLayout m9790$$Nest$fgetphotoLayout(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.photoLayout;
    }

    /* renamed from: -$$Nest$fgetthemeDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m9791$$Nest$fgetthemeDelegate(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.themeDelegate;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView m9792$$Nest$fgetundoView(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fgetvideoPlayImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m9793$$Nest$fgetvideoPlayImage(ChatAttachAlertPhotoLayoutPreview r0) {
        return r0.videoPlayImage;
    }

    /* renamed from: -$$Nest$fputdraggingAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9794$$Nest$fputdraggingAnimator(ChatAttachAlertPhotoLayoutPreview r0, ValueAnimator r1) {
        r0.draggingAnimator = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9795$$Nest$fputdraggingCell(ChatAttachAlertPhotoLayoutPreview r0, PreviewGroupsView.PreviewGroupCell.MediaCell r1) {
        r0.draggingCell = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellFromHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9796$$Nest$fputdraggingCellFromHeight(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellFromHeight = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellFromWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9797$$Nest$fputdraggingCellFromWidth(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellFromWidth = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellGroupY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9798$$Nest$fputdraggingCellGroupY(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellGroupY = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellHiding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9799$$Nest$fputdraggingCellHiding(ChatAttachAlertPhotoLayoutPreview r0, boolean r1) {
        r0.draggingCellHiding = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9800$$Nest$fputdraggingCellLeft(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellLeft = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9801$$Nest$fputdraggingCellTop(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellTop = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellTouchX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9802$$Nest$fputdraggingCellTouchX(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellTouchX = r1;
    }

    /* renamed from: -$$Nest$fputdraggingCellTouchY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9803$$Nest$fputdraggingCellTouchY(ChatAttachAlertPhotoLayoutPreview r0, float r1) {
        r0.draggingCellTouchY = r1;
    }

    /* renamed from: -$$Nest$sfgetphotoRotate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m9804$$Nest$sfgetphotoRotate() {
        return photoRotate;
    }

    static {
        photoRotate = new HashMap();
    }

    public ChatAttachAlertPhotoLayoutPreview(ChatAttachAlert r19, Context r20, Theme.ResourcesProvider r21) {
        super(r19, r20, r21);
        this.durationMultiplier = 1;
        this.draggingCellTouchX = 0.0f;
        this.draggingCellTouchY = 0.0f;
        this.draggingCellTop = 0.0f;
        this.draggingCellLeft = 0.0f;
        this.draggingCellFromWidth = 0.0f;
        this.draggingCellFromHeight = 0.0f;
        this.draggingCell = null;
        this.draggingCellHiding = false;
        this.draggingCellGroupY = 0.0f;
        this.shown = false;
        this.ignoreLayout = false;
        Point r0 = AndroidUtilities.displaySize;
        if (r0.y <= r0.x) goto L5;
        boolean r02 = true;
    L6:
        this.isPortrait = r02;
        this.themeDelegate = r21;
        this.occupyNavigationBar = true;
        setWillNotDraw(false);
        ActionBarMenu r3 = this.parentAlert.actionBar.createMenu();
        this.header = new TextView(r20);
        AnonymousClass1 r03 = new AnonymousClass1(this, r20, r3, 0, 0, this.resourcesProvider);
        ActionBar r32 = this.parentAlert.actionBar;
        if (AndroidUtilities.isTablet() == false) goto L10;
        float r4 = 64.0f;
    L11:
        r32.addView(r03, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r4, 0.0f, 40.0f, 0.0f));
        this.header.setImportantForAccessibility(2);
        this.header.setGravity(3);
        this.header.setSingleLine(true);
        this.header.setLines(1);
        this.header.setMaxLines(1);
        this.header.setEllipsize(TextUtils.TruncateAt.END);
        this.header.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.header.setText(LocaleController.getString(R.string.AttachMediaPreview));
        this.header.setTypeface(AndroidUtilities.bold());
        this.header.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.header.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        this.header.setAlpha(0.0f);
        r03.addView(this.header, LayoutHelper.createFrame(-2, -2.0f, 16, 16.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass2 r04 = new AnonymousClass2(this, r20, this.resourcesProvider);
        this.listView = r04;
        r04.setAdapter(new AnonymousClass3(this));
        RecyclerListView r05 = this.listView;
        LinearLayoutManager r33 = new LinearLayoutManager(r20, 1, false);
        this.layoutManager = r33;
        r05.setLayoutManager(r33);
        this.listView.setClipChildren(false);
        this.listView.setClipToPadding(false);
        this.listView.setOverScrollMode(2);
        this.listView.setVerticalScrollBarEnabled(false);
        PreviewGroupsView r06 = new PreviewGroupsView(this, r20);
        this.groupsView = r06;
        r06.setClipToPadding(true);
        this.groupsView.setClipChildren(true);
        addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.photoLayout = this.parentAlert.getPhotoLayout();
        PreviewGroupsView.m9808$$Nest$fgetdeletedPhotos(this.groupsView).clear();
        this.groupsView.fromPhotoLayout(this.photoLayout);
        UndoView r07 = new UndoView(r20, null, false, this.parentAlert.parentThemeDelegate);
        this.undoView = r07;
        r07.setEnterOffsetMargin(AndroidUtilities.dp(32.0f));
        addView(this.undoView, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 52.0f));
        this.videoPlayImage = r20.getResources().getDrawable(R.drawable.play_mini_video);
        return;
    L10:
        r4 = 56.0f;
        goto L11
    L5:
        r02 = false;
        goto L6
    }

    private /* synthetic */ void lambda$onShow$0(ChatAttachAlert.AttachAlertLayout r3) {
        int r0 = r3.getCurrentItemTop();
        int r32 = r3.getListTopPadding();
        RecyclerListView r2 = this.listView;
        if (r0 <= AndroidUtilities.dp(7.0f)) goto L5;
        r32 = r32 - r0;
    L5:
        r2.scrollBy(0, r32);
    }

    private /* synthetic */ void lambda$onShow$1() {
        if (this.shown == true) goto L5;
        return;
    L5:
        if (this.parentAlert.getPhotoLayout() == null) goto L9;
        this.parentAlert.getPhotoLayout().previewItem.setIcon(R.drawable.ic_ab_back);
        this.parentAlert.getPhotoLayout().previewItem.setText(LocaleController.getString(R.string.Back));
        this.parentAlert.getPhotoLayout().previewItem.setRightIcon(0);
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void applyCaption(CharSequence r1) {
        ChatAttachAlertPhotoLayout r0 = this.photoLayout;
        if (r0 == null) goto L6;
        r0.applyCaption(r1);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        ChatActivity.ThemeDelegate r0 = this.parentAlert.parentThemeDelegate;
        boolean r1 = false;
        if (r0 == null) goto L17;
        Drawable r02 = r0.getWallpaperDrawable();
        if (r02 == null) goto L17;
        int r2 = getCurrentItemTop();
        if (AndroidUtilities.isTablet() == false) goto L9;
        int r3 = 16;
    L14:
        if (r2 >= ActionBar.getCurrentActionBarHeight()) goto L16;
        r2 = r2 - AndroidUtilities.dp((1.0f - (r2 / ActionBar.getCurrentActionBarHeight())) * r3);
    L16:
        int r22 = Math.max(0, r2);
        r7.save();
        r7.clipRect(0, r22, getWidth(), getHeight());
        r02.setBounds(0, r22, getWidth(), AndroidUtilities.displaySize.y + r22);
        r02.draw(r7);
        r1 = true;
        goto L17
    L9:
        Point r32 = AndroidUtilities.displaySize;
        if (r32.x <= r32.y) goto L12;
        r3 = 6;
        goto L14
    L12:
        r3 = 12;
    L17:
        super.dispatchDraw(r7);
        if (r1 == false) goto L21;
        r7.restore();
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        r1.setTopGlowOffset(r1.getPaddingTop());
        return Integer.MAX_VALUE;
    L6:
        View r02 = r1.getChildAt(0);
        RecyclerListView.Holder r12 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r02);
        int r03 = r02.getTop();
        int r3 = AndroidUtilities.dp(8.0f);
        if (r03 < AndroidUtilities.dp(8.0f)) goto L12;
        if (r12 == null) goto L12;
        if (r12.getAdapterPosition() != 0) goto L12;
    L13:
        this.listView.setTopGlowOffset(r03);
        return r03;
    L12:
        r03 = r3;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    public float getPreviewScale() {
        Point r1 = AndroidUtilities.displaySize;
        if (r1.y <= r1.x) goto L6;
        return 0.8f;
    L6:
        return 0.45f;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return this.groupsView.getPhotosCount();
    }

    public Drawable getThemedDrawable(String r1) {
        Theme.ResourcesProvider r0 = this.themeDelegate;
        if (r0 == null) goto L5;
        Drawable r02 = r0.getDrawable(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemeDrawable(r1);
    L5:
        r02 = null;
        goto L6
    }

    public void invalidateGroupsView() {
        this.groupsView.invalidate();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onBackPressed() {
        this.parentAlert.updatePhotoPreview(false);
        return true;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        this.draggingCell = null;
        UndoView r0 = this.undoView;
        if (r0 == null) goto L5;
        r0.hide(false, 0);
    L5:
        ArrayList r8 = PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.groupsView);
        int r02 = r8.size();
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L14;
        Object r3 = r8.get(r2);
        r2 = r2 + 1;
        ArrayList<PreviewGroupsView.PreviewGroupCell.MediaCell> r32 = ((PreviewGroupsView.PreviewGroupCell) r3).media;
        int r4 = r32.size();
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L6;
        PreviewGroupsView.PreviewGroupCell.MediaCell r6 = r32.get(r5);
        r5 = r5 + 1;
        PreviewGroupsView.PreviewGroupCell.MediaCell r62 = r6;
        if (r62.wasSpoiler == false) goto L8;
        MediaController.PhotoEntry r63 = r62.photoEntry;
        if (r63 == null) goto L8;
        r63.isChatPreviewSpoilerRevealed = false;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        this.shown = false;
        ViewPropertyAnimator r0 = this.headerAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ViewPropertyAnimator r02 = this.header.animate().alpha(0.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.EASE_BOTH);
        this.headerAnimator = r02;
        r02.start();
        if (getSelectedItemsCount() > 1) goto L8;
    L10:
        this.groupsView.toPhotoLayout(this.photoLayout, true);
        return;
    L8:
        if (this.parentAlert.getPhotoLayout() == null) goto L10;
        this.parentAlert.getPhotoLayout().previewItem.setIcon(R.drawable.msg_view_file);
        this.parentAlert.getPhotoLayout().previewItem.setText(LocaleController.getString(R.string.AttachMediaPreviewButton));
        this.parentAlert.getPhotoLayout().previewItem.setRightIcon(R.drawable.msg_arrowright);
        goto L10
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        Point r12 = AndroidUtilities.displaySize;
        int r32 = 0;
        if (r12.y <= r12.x) goto L5;
        boolean r13 = true;
    L7:
        if (this.isPortrait == r13) goto L14;
        this.isPortrait = r13;
        int r14 = PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.groupsView).size();
    L9:
        if (r32 >= r14) goto L18;
        PreviewGroupsView.PreviewGroupCell r22 = (PreviewGroupsView.PreviewGroupCell) PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.groupsView).get(r32);
        if (PreviewGroupsView.PreviewGroupCell.m9815$$Nest$fgetgroup(r22).photos.size() != 1) goto L13;
        PreviewGroupsView.PreviewGroupCell.m9821$$Nest$msetGroup(r22, PreviewGroupsView.PreviewGroupCell.m9815$$Nest$fgetgroup(r22), true);
    L13:
        r32 = r32 + 1;
        goto L9
    L18:
        return;
    L14:
        return;
    L5:
        r13 = false;
        goto L7
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onMenuItemClick(int r1) {
        this.parentAlert.getPhotoLayout().onMenuItemClick(r1);     // Catch: Exception -> L4
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r5, int r6) {
        this.ignoreLayout = true;
        if (AndroidUtilities.isTablet() == true) goto L7;
        Point r52 = AndroidUtilities.displaySize;
        if (r52.x <= r52.y) goto L7;
        this.paddingTop = (int) (r6 / 3.5f);
    L8:
        int r53 = this.paddingTop - AndroidUtilities.dp(52.0f);
        this.paddingTop = r53;
        if (r53 >= 0) goto L12;
        this.paddingTop = 0;
    L12:
        if (this.listView.getPaddingTop() == this.paddingTop) goto L14;
    L15:
        RecyclerListView r54 = this.listView;
        r54.setPaddingWithoutRequestLayout(r54.getPaddingLeft(), this.paddingTop, this.listView.getPaddingRight(), this.listPaddingBottom);
        invalidate();
    L16:
        TextView r55 = this.header;
        if (AndroidUtilities.isTablet() == true) goto L21;
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L21;
        float r02 = 18.0f;
    L22:
        r55.setTextSize(r02);
        this.ignoreLayout = false;
        return;
    L21:
        r02 = 20.0f;
        goto L22
    L14:
        if (this.listView.getPaddingBottom() == this.listPaddingBottom) goto L16;
    L7:
        this.paddingTop = (r6 / 5) * 2;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onSelectedItemsCountChanged(int r3) {
        ChatAttachAlert r2 = this.parentAlert;
        if (r3 <= 1) goto L6;
        r2.selectedMenuItem.showSubItem(0);
        return;
    L6:
        r2.selectedMenuItem.hideSubItem(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r5) {
        this.shown = true;
        if ((r5 instanceof ChatAttachAlertPhotoLayout) == false) goto L5;
        this.photoLayout = (ChatAttachAlertPhotoLayout) r5;
        PreviewGroupsView.m9808$$Nest$fgetdeletedPhotos(this.groupsView).clear();
        this.groupsView.fromPhotoLayout(this.photoLayout);
        this.groupsView.requestLayout();
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        ChatAttachAlertPhotoLayoutPreview$$ExternalSyntheticLambda0 r0 = new ChatAttachAlertPhotoLayoutPreview$$ExternalSyntheticLambda0(this, r5);
        this.listView.post(r0);
        postDelayed(new ChatAttachAlertPhotoLayoutPreview$$ExternalSyntheticLambda1(this), 250);
        this.groupsView.toPhotoLayout(this.photoLayout, false);
    L6:
        ViewPropertyAnimator r52 = this.headerAnimator;
        if (r52 == null) goto L9;
        r52.cancel();
    L9:
        ViewPropertyAnimator r53 = this.header.animate().alpha(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.headerAnimator = r53;
        r53.start();
        return;
    L5:
        scrollToTop();
        goto L6
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean shouldHideBottomButtons() {
        return true;
    }

    public void startMediaCrossfade() {
        ArrayList r7 = PreviewGroupsView.m9810$$Nest$fgetgroupCells(this.groupsView);
        int r0 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        Object r3 = r7.get(r2);
        r2 = r2 + 1;
        ArrayList<PreviewGroupsView.PreviewGroupCell.MediaCell> r32 = ((PreviewGroupsView.PreviewGroupCell) r3).media;
        int r4 = r32.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L3;
        PreviewGroupsView.PreviewGroupCell.MediaCell r6 = r32.get(r5);
        r5 = r5 + 1;
        r6.startCrossfade();
        goto L5
    }
}
