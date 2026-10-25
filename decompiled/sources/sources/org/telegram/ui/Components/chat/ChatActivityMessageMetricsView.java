package org.telegram.ui.Components.chat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public class ChatActivityMessageMetricsView extends View implements ViewTreeObserver.OnPreDrawListener, ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final RectF tmpRect = null;
    private final boolean DRAW_DEBUG;
    private int currentAccount;
    private long dialogId;
    private final LongSparseArray<RectF> groupedPositions;
    private long lastTime;
    private long lastUserActivityTime;
    private ViewGroup list;
    private ViewTreeObserver observer;
    private Runnable pendingFlush;
    private final ArrayList<TLRPC.TL_inputMessageReadMetric> pendingMetrics;
    private ViewGroup root;
    private final Runnable scheduledCheckRunnable;
    private TextPaint tmpTextPaint;
    private boolean updateInNextDraw;
    private final RectF viewPort;
    private final RectF viewPortInsets;
    private final LongSparseArray<MessageWatcher> watchers;

    public static class MessageWatcher {
        private long activeTime;
        public final long groupId;
        private long lastUpdateMillis;
        private long lastViewMillis;
        private float maxPostTotalHeight;
        private float maxViewPortHeight;
        public final int messageId;
        private final RectF position;
        private float seenBottomPx;
        private float seenTopPx;
        private final long viewId;
        private boolean visible;
        private long visibleTime;

        /* renamed from: -$$Nest$fgetactiveTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m13672$$Nest$fgetactiveTime(MessageWatcher r2) {
            return r2.activeTime;
        }

        /* renamed from: -$$Nest$fgetlastUpdateMillis, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m13673$$Nest$fgetlastUpdateMillis(MessageWatcher r2) {
            return r2.lastUpdateMillis;
        }

        /* renamed from: -$$Nest$fgetlastViewMillis, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m13674$$Nest$fgetlastViewMillis(MessageWatcher r2) {
            return r2.lastViewMillis;
        }

        /* renamed from: -$$Nest$fgetmaxPostTotalHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13675$$Nest$fgetmaxPostTotalHeight(MessageWatcher r0) {
            return r0.maxPostTotalHeight;
        }

        /* renamed from: -$$Nest$fgetmaxViewPortHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13676$$Nest$fgetmaxViewPortHeight(MessageWatcher r0) {
            return r0.maxViewPortHeight;
        }

        /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m13677$$Nest$fgetposition(MessageWatcher r0) {
            return r0.position;
        }

        /* renamed from: -$$Nest$fgetseenBottomPx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13678$$Nest$fgetseenBottomPx(MessageWatcher r0) {
            return r0.seenBottomPx;
        }

        /* renamed from: -$$Nest$fgetseenTopPx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13679$$Nest$fgetseenTopPx(MessageWatcher r0) {
            return r0.seenTopPx;
        }

        /* renamed from: -$$Nest$fgetvisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13680$$Nest$fgetvisible(MessageWatcher r0) {
            return r0.visible;
        }

        /* renamed from: -$$Nest$fgetvisibleTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m13681$$Nest$fgetvisibleTime(MessageWatcher r2) {
            return r2.visibleTime;
        }

        /* renamed from: -$$Nest$fputactiveTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13682$$Nest$fputactiveTime(MessageWatcher r0, long r1) {
            r0.activeTime = r1;
        }

        /* renamed from: -$$Nest$fputlastUpdateMillis, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13683$$Nest$fputlastUpdateMillis(MessageWatcher r0, long r1) {
            r0.lastUpdateMillis = r1;
        }

        /* renamed from: -$$Nest$fputlastViewMillis, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13684$$Nest$fputlastViewMillis(MessageWatcher r0, long r1) {
            r0.lastViewMillis = r1;
        }

        /* renamed from: -$$Nest$fputmaxPostTotalHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13685$$Nest$fputmaxPostTotalHeight(MessageWatcher r0, float r1) {
            r0.maxPostTotalHeight = r1;
        }

        /* renamed from: -$$Nest$fputmaxViewPortHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13686$$Nest$fputmaxViewPortHeight(MessageWatcher r0, float r1) {
            r0.maxViewPortHeight = r1;
        }

        /* renamed from: -$$Nest$fputseenBottomPx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13687$$Nest$fputseenBottomPx(MessageWatcher r0, float r1) {
            r0.seenBottomPx = r1;
        }

        /* renamed from: -$$Nest$fputseenTopPx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13688$$Nest$fputseenTopPx(MessageWatcher r0, float r1) {
            r0.seenTopPx = r1;
        }

        /* renamed from: -$$Nest$fputvisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13689$$Nest$fputvisible(MessageWatcher r0, boolean r1) {
            r0.visible = r1;
        }

        /* renamed from: -$$Nest$fputvisibleTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13690$$Nest$fputvisibleTime(MessageWatcher r0, long r1) {
            r0.visibleTime = r1;
        }

        private MessageWatcher(int r3, long r4) {
            this.position = new RectF();
            this.maxPostTotalHeight = 0.0f;
            this.maxViewPortHeight = 0.0f;
            this.seenTopPx = Float.MAX_VALUE;
            this.seenBottomPx = 0.0f;
            this.messageId = r3;
            this.groupId = r4;
            this.viewId = Utilities.random.nextLong();
        }

        public /* synthetic */ MessageWatcher(int r1, long r2, ChatActivityMessageMetricsViewIA r4) {
            this(r1, r2);
        }

        public TLRPC.TL_inputMessageReadMetric buildMetrics() {
            TLRPC.TL_inputMessageReadMetric r0 = new TLRPC.TL_inputMessageReadMetric();
            r0.msg_id = this.messageId;
            r0.view_id = this.viewId;
            r0.time_in_view_ms = (int) this.visibleTime;
            r0.active_time_in_view_ms = (int) this.activeTime;
            r0.height_to_viewport_ratio_permille = getHeightToViewportRatioPermille();
            r0.seen_range_ratio_permille = getSeenRangeRatioPermille();
            return r0;
        }

        public int getHeightToViewportRatioPermille() {
            float r0 = this.maxViewPortHeight;
            if (r0 != 0.0f) goto L7;
            return 1000;
        L7:
            return Math.round((this.maxPostTotalHeight / r0) * 1000.0f);
        }

        public int getSeenRangeRatioPermille() {
            float r0 = this.maxPostTotalHeight;
            if (r0 == 0.0f) goto L9;
            float r1 = this.seenTopPx;
            float r3 = this.seenBottomPx;
            if (r1 <= r3) goto L8;
            return 0;
        L8:
            return Math.round(((r3 - r1) / r0) * 1000.0f);
        L9:
            return 0;
        }
    }

    public static /* synthetic */ void $r8$lambda$10otza0KlJFPhgKNTXlDuJCiofU(ChatActivityMessageMetricsView r0) {
        r0.flushImpl();
    }

    public static /* synthetic */ void $r8$lambda$AnJ_mtx2yUPCI4eaoyfDVv_eZ7s(ChatActivityMessageMetricsView r0) {
        r0.scheduledCheck();
    }

    public static /* synthetic */ void $r8$lambda$OTgxqPY4rg_4WF2e8sYw8x4osdg(TLRPC.Bool r0, TLRPC.TL_error r1) {
    }

    static {
        tmpRect = new RectF();
    }

    public ChatActivityMessageMetricsView(Context r1) {
        super(r1);
        this.viewPortInsets = new RectF();
        this.viewPort = new RectF();
        this.pendingMetrics = new ArrayList();
        this.watchers = new LongSparseArray();
        this.groupedPositions = new LongSparseArray();
        this.scheduledCheckRunnable = new ChatActivityMessageMetricsView$$ExternalSyntheticLambda1(this);
        this.DRAW_DEBUG = SharedConfig.debugViewMetrics;
    }

    private void flushImpl() {
        Runnable r0 = this.pendingFlush;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.pendingFlush = null;
    L6:
        if (this.pendingMetrics.isEmpty() == true) goto L9;
        TLRPC.TL_messages_reportReadMetrics r02 = new TLRPC.TL_messages_reportReadMetrics();
        r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r02.metrics = new ArrayList(this.pendingMetrics);
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r02, null, new ChatActivityMessageMetricsView$$ExternalSyntheticLambda2());
        this.pendingMetrics.clear();
        return;
    }

    private void invalidateViewPort() {
        RectF r0 = this.viewPort;
        RectF r1 = this.viewPortInsets;
        r0.set(r1.left, r1.top, getMeasuredWidth() - this.viewPortInsets.right, getMeasuredHeight() - this.viewPortInsets.bottom);
    }

    private void processCurrentFrame() {
        if (this.root != null) goto L5;
        return;
    L5:
        if (this.list == null) goto L163;
        long r1 = SystemClock.uptimeMillis();
        long r3 = this.lastTime;
        long r5 = 0;
        if (r3 != 0) goto L10;
        long r32 = 0;
    L11:
        this.lastTime = r1;
        int r7 = this.groupedPositions.size();
        int r9 = 0;
    L13:
        if (r9 >= r7) goto L15;
        this.groupedPositions.valueAt(r9).set(0.0f, 0.0f, 0.0f, 0.0f);
        r9 = r9 + 1;
        goto L13
    L15:
        int r72 = this.list.getChildCount();
        int r92 = 0;
    L17:
        if (r92 >= r72) goto L49;
        View r14 = this.list.getChildAt(r92);
        ViewGroup r15 = this.root;
        long r16 = r5;
        RectF r52 = tmpRect;
        if (ViewPositionWatcher.computeRectInParent(r14, r15, r52) == true) goto L22;
    L20:
        int r18 = r92;
    L48:
        r92 = r18 + 1;
        r5 = r16;
        goto L17
    L22:
        if ((r14 instanceof ChatMessageCell) == false) goto L25;
        MessageObject r6 = ((ChatMessageCell) r14).getMessageObject();
    L27:
        if (r6 == null) goto L20;
        long r142 = r6.getDialogId();
        r18 = r92;
        if (r142 != this.dialogId) goto L48;
        int r8 = r6.getId();
        long r10 = r6.getGroupId();
        if (r142 == r16) goto L48;
        if (r8 <= 0) goto L48;
        long r143 = r8;
        MessageWatcher r62 = this.watchers.get(r143);
        if (r62 != null) goto L41;
        r62 = new MessageWatcher(r8, r10, null);
        this.watchers.put(r143, r62);
        if (BuildVars.LOGS_ENABLED == false) goto L41;
        Log.d("ViewMetrics", r8 + " " + r10 + " in screen");
    L41:
        MessageWatcher.m13677$$Nest$fgetposition(r62).set(r52);
        if (r10 == r16) goto L47;
        RectF r53 = this.groupedPositions.get(r10);
        if (r53 != null) goto L46;
        r53 = new RectF();
        this.groupedPositions.put(r10, r53);
    L46:
        r53.union(MessageWatcher.m13677$$Nest$fgetposition(r62));
    L47:
        MessageWatcher.m13683$$Nest$fputlastUpdateMillis(r62, r1);
        goto L48
    L25:
        if ((r14 instanceof ChatActionCell) == false) goto L20;
        r6 = ((ChatActionCell) r14).getMessageObject();
        goto L27
    L49:
        long r162 = r5;
        RectF r93 = null;
        int r54 = this.groupedPositions.size() - 1;
    L50:
        if (r54 < 0) goto L55;
        if (this.groupedPositions.valueAt(r54).isEmpty() == false) goto L54;
        this.groupedPositions.removeAt(r54);
    L54:
        r54 = r54 - 1;
        goto L50
    L55:
        int r55 = this.watchers.size();
        int r73 = 0;
    L56:
        LongSparseArray<MessageWatcher> r82 = this.watchers;
        if (r73 >= r55) goto L64;
        MessageWatcher r83 = r82.valueAt(r73);
        long r102 = r83.groupId;
        if (r102 == r162) goto L63;
        RectF r103 = this.groupedPositions.get(r102);
        if (r103 == null) goto L63;
        MessageWatcher.m13677$$Nest$fgetposition(r83).set(r103);
    L63:
        r73 = r73 + 1;
        goto L56
    L64:
        int r56 = r82.size() - 1;
    L65:
        if (r56 < 0) goto L129;
        MessageWatcher r74 = this.watchers.valueAt(r56);
        long r104 = r74.messageId;
        long r144 = r74.groupId;
        if (r144 == r162) goto L69;
        RectF r84 = this.groupedPositions.get(r144);
    L71:
        if (MessageWatcher.m13673$$Nest$fgetlastUpdateMillis(r74) == r1) goto L73;
        if (r84 != null) goto L73;
    L80:
        boolean r63 = false;
    L82:
        if (r63 == true) goto L84;
        long r24 = r1;
        long r26 = r32;
        boolean r12 = true;
    L99:
        if (r63 == true) goto L111;
        if (MessageWatcher.m13681$$Nest$fgetvisibleTime(r74) <= r162) goto L111;
        if ((r24 - 300) <= MessageWatcher.m13674$$Nest$fgetlastViewMillis(r74)) goto L111;
        if (MessageWatcher.m13680$$Nest$fgetvisible(r74) == false) goto L107;
        this.pendingMetrics.add(r74.buildMetrics());
    L107:
        this.watchers.removeAt(r56);
        if (BuildVars.LOGS_ENABLED == false) goto L127;
        Log.d("ViewMetrics", r104 + " " + r144 + " out of viewport: " + MessageWatcher.m13681$$Nest$fgetvisibleTime(r74));
    L127:
        r56 = r56 - 1;
        r1 = r24;
        r32 = r26;
        r93 = null;
    L111:
        if (MessageWatcher.m13680$$Nest$fgetvisible(r74) == false) goto L118;
        if (MessageWatcher.m13681$$Nest$fgetvisibleTime(r74) <= 300000) goto L118;
        this.pendingMetrics.add(r74.buildMetrics());
        this.watchers.removeAt(r56);
        if (BuildVars.LOGS_ENABLED == false) goto L127;
        Log.d("ViewMetrics", r104 + " " + r144 + " out of time");
    L118:
        if (MessageWatcher.m13680$$Nest$fgetvisible(r74) == true) goto L127;
        if (MessageWatcher.m13673$$Nest$fgetlastUpdateMillis(r74) == r24) goto L127;
        if (r74.groupId == r162) goto L124;
        if (r84 != null) goto L127;
    L124:
        this.watchers.removeAt(r56);
        if (BuildVars.LOGS_ENABLED == false) goto L127;
        Log.d("ViewMetrics", r104 + " " + r144 + " out of screen");
        goto L127
    L84:
        if (MessageWatcher.m13674$$Nest$fgetlastViewMillis(r74) == r162) goto L88;
        r26 = r32;
        MessageWatcher.m13690$$Nest$fputvisibleTime(r74, MessageWatcher.m13681$$Nest$fgetvisibleTime(r74) + r26);
        if ((r1 - this.lastUserActivityTime) >= 15000) goto L89;
        MessageWatcher.m13682$$Nest$fputactiveTime(r74, MessageWatcher.m13672$$Nest$fgetactiveTime(r74) + r26);
    L89:
        MessageWatcher.m13684$$Nest$fputlastViewMillis(r74, r1);
        float r33 = MessageWatcher.m13677$$Nest$fgetposition(r74).height();
        float r4 = this.viewPort.top - MessageWatcher.m13677$$Nest$fgetposition(r74).top;
        r24 = r1;
        float r13 = r33 - (MessageWatcher.m13677$$Nest$fgetposition(r74).bottom - this.viewPort.bottom);
        MessageWatcher.m13688$$Nest$fputseenTopPx(r74, Math.min(MessageWatcher.m13679$$Nest$fgetseenTopPx(r74), MathUtils.clamp(r4, 0.0f, r33)));
        MessageWatcher.m13687$$Nest$fputseenBottomPx(r74, Math.max(MessageWatcher.m13678$$Nest$fgetseenBottomPx(r74), MathUtils.clamp(r13, 0.0f, r33)));
        MessageWatcher.m13686$$Nest$fputmaxViewPortHeight(r74, Math.max(MessageWatcher.m13676$$Nest$fgetmaxViewPortHeight(r74), this.viewPort.height()));
        MessageWatcher.m13685$$Nest$fputmaxPostTotalHeight(r74, Math.max(MessageWatcher.m13675$$Nest$fgetmaxPostTotalHeight(r74), r33));
        if (MessageWatcher.m13680$$Nest$fgetvisible(r74) == false) goto L92;
    L97:
        r12 = true;
        goto L99
    L92:
        if (MessageWatcher.m13681$$Nest$fgetvisibleTime(r74) <= 300) goto L97;
        if (BuildVars.LOGS_ENABLED == false) goto L96;
        Log.d("ViewMetrics", r104 + " " + r144 + " in viewport");
    L96:
        r12 = true;
        MessageWatcher.m13689$$Nest$fputvisible(r74, true);
        goto L99
    L88:
        r26 = r32;
    L73:
        RectF r94 = this.viewPort;
        if (r84 == null) goto L76;
        RectF r64 = r84;
    L78:
        if (RectF.intersects(r94, r64) == false) goto L80;
        r63 = true;
        goto L82
    L76:
        r64 = MessageWatcher.m13677$$Nest$fgetposition(r74);
        goto L78
    L69:
        r84 = r93;
        goto L71
    L129:
        if (this.pendingMetrics.isEmpty() == true) goto L134;
        if (this.pendingFlush != null) goto L134;
        ChatActivityMessageMetricsView$$ExternalSyntheticLambda0 r17 = new ChatActivityMessageMetricsView$$ExternalSyntheticLambda0(this);
        this.pendingFlush = r17;
        AndroidUtilities.runOnUIThread(r17, 5000);
    L134:
        if (this.DRAW_DEBUG == false) goto L162;
        invalidate();
        return;
    L162:
        return;
    L10:
        r32 = r1 - r3;
        goto L11
    }

    private void scheduledCheck() {
        AndroidUtilities.runOnUIThread(this.scheduledCheckRunnable, 400);
        processCurrentFrame();
    }

    public void finish() {
        int r0 = this.watchers.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        MessageWatcher r2 = this.watchers.valueAt(r1);
        if (MessageWatcher.m13680$$Nest$fgetvisible(r2) == false) goto L7;
        this.pendingMetrics.add(r2.buildMetrics());
    L7:
        r1 = r1 + 1;
        goto L3
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        Log.d("ViewMetrics", "finish");
    L11:
        this.watchers.clear();
        flushImpl();
    }

    public void init(int r1, long r2, ViewGroup r4, ViewGroup r5) {
        this.dialogId = r2;
        this.currentAccount = r1;
        this.root = r4;
        this.list = r5;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lastTime = 0;
        ViewTreeObserver r0 = getViewTreeObserver();
        this.observer = r0;
        r0.addOnPreDrawListener(this);
        this.observer.addOnGlobalLayoutListener(this);
        this.observer.addOnScrollChangedListener(this);
        AndroidUtilities.runOnUIThread(this.scheduledCheckRunnable, 400);
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        Log.d("ViewMetrics", "attach");
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        ViewTreeObserver r0 = this.observer;
        if (r0 != null) goto L5;
    L7:
        this.observer = null;
        this.lastTime = 0;
        AndroidUtilities.cancelRunOnUIThread(this.scheduledCheckRunnable);
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        Log.d("ViewMetrics", "detach");
    L10:
        super.onDetachedFromWindow();
        return;
    L5:
        if (r0.isAlive() == false) goto L7;
        this.observer.removeOnPreDrawListener(this);
        this.observer.removeOnGlobalLayoutListener(this);
        this.observer.removeOnScrollChangedListener(this);
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        if (this.DRAW_DEBUG == true) goto L6;
        return;
    L6:
        if (this.tmpTextPaint != null) goto L8;
        TextPaint r0 = new TextPaint(1);
        this.tmpTextPaint = r0;
        r0.setColor(-16776961);
        this.tmpTextPaint.setTextSize(AndroidUtilities.dp(10.0f));
    L8:
        super.onDraw(r14);
        r14.drawRect(this.viewPort, Theme.DEBUG_RED_STROKE);
        int r02 = this.watchers.size();
        int r2 = 0;
    L9:
        if (r2 >= r02) goto L13;
        MessageWatcher r3 = this.watchers.valueAt(r2);
        r14.drawRect(MessageWatcher.m13677$$Nest$fgetposition(r3), Theme.DEBUG_GREEN_STROKE);
        r14.save();
        r14.translate(MessageWatcher.m13677$$Nest$fgetposition(r3).left, MathUtils.clamp(MathUtils.clamp(MessageWatcher.m13677$$Nest$fgetposition(r3).centerY() - AndroidUtilities.dp(20.0f), this.viewPort.top - AndroidUtilities.dp(40.0f), this.viewPort.bottom), MessageWatcher.m13677$$Nest$fgetposition(r3).top, MessageWatcher.m13677$$Nest$fgetposition(r3).bottom - AndroidUtilities.dp(40.0f)));
        Canvas r7 = r14;
        r7.drawRect(0.0f, 0.0f, MessageWatcher.m13677$$Nest$fgetposition(r3).width(), AndroidUtilities.dp(40.0f), Theme.DEBUG_GREEN_B0);
        r7.translate(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(16.0f));
        r7.save();
        r7.drawText("time_in_view_ms: " + MessageWatcher.m13681$$Nest$fgetvisibleTime(r3), 0.0f, 0.0f, this.tmpTextPaint);
        r7.translate(0.0f, (float) AndroidUtilities.dp(16.0f));
        r7.drawText("active_time_in_view_ms: " + MessageWatcher.m13672$$Nest$fgetactiveTime(r3), 0.0f, 0.0f, this.tmpTextPaint);
        r7.restore();
        r7.save();
        r7.translate(getWidth() / 2.0f, 0.0f);
        r7.drawText("height_to_viewport_ratio_permille: " + r3.getHeightToViewportRatioPermille(), 0.0f, 0.0f, this.tmpTextPaint);
        r7.translate(0.0f, (float) AndroidUtilities.dp(16.0f));
        r7.drawText("seen_range_ratio_permille: " + r3.getSeenRangeRatioPermille(), 0.0f, 0.0f, this.tmpTextPaint);
        r7.restore();
        r7.restore();
        r2 = r2 + 1;
        r14 = r7;
        goto L9
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.updateInNextDraw = true;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        invalidateViewPort();
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (this.updateInNextDraw == false) goto L7;
        processCurrentFrame();
        this.updateInNextDraw = false;
        return true;
    L7:
        return true;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        this.updateInNextDraw = true;
    }

    public void setIsUserActive() {
        this.lastUserActivityTime = SystemClock.uptimeMillis();
    }

    public void setViewportPadding(float r2, float r3, float r4, float r5) {
        this.viewPortInsets.set(r2, r3, r4, r5);
        invalidateViewPort();
    }
}
