package androidx.core.view.accessibility;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.R$id;
import androidx.core.os.BuildCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {
    private static int sClickableSpanId;
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId;
    private int mVirtualDescendantId;

    /* loaded from: classes3.dex */
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = null;
        public static final AccessibilityActionCompat ACTION_CLICK = null;
        public static final AccessibilityActionCompat ACTION_COLLAPSE = null;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK = null;
        public static final AccessibilityActionCompat ACTION_COPY = null;
        public static final AccessibilityActionCompat ACTION_CUT = null;
        public static final AccessibilityActionCompat ACTION_DISMISS = null;
        public static final AccessibilityActionCompat ACTION_DRAG_CANCEL = null;
        public static final AccessibilityActionCompat ACTION_DRAG_DROP = null;
        public static final AccessibilityActionCompat ACTION_DRAG_START = null;
        public static final AccessibilityActionCompat ACTION_EXPAND = null;
        public static final AccessibilityActionCompat ACTION_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP = null;
        public static final AccessibilityActionCompat ACTION_IME_ENTER = null;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = null;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW = null;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = null;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN = null;
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_UP = null;
        public static final AccessibilityActionCompat ACTION_PASTE = null;
        public static final AccessibilityActionCompat ACTION_PRESS_AND_HOLD = null;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = null;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_IN_DIRECTION = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP = null;
        public static final AccessibilityActionCompat ACTION_SELECT = null;
        public static final AccessibilityActionCompat ACTION_SET_EXTENDED_SELECTION = null;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS = null;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = null;
        public static final AccessibilityActionCompat ACTION_SET_TEXT = null;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN = null;
        public static final AccessibilityActionCompat ACTION_SHOW_TEXT_SUGGESTIONS = null;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP = null;
        final Object mAction;
        protected final AccessibilityViewCommand mCommand;
        private final int mId;
        private final Class<? extends AccessibilityViewCommand.CommandArguments> mViewCommandArgumentClass;

        static {
            AccessibilityNodeInfo.AccessibilityAction r2 = null;
            ACTION_FOCUS = new AccessibilityActionCompat(1, null);
            ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
            ACTION_SELECT = new AccessibilityActionCompat(4, null);
            ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
            ACTION_CLICK = new AccessibilityActionCompat(16, null);
            ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
            ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
            ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
            ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
            ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null, AccessibilityViewCommand.MoveAtGranularityArguments.class);
            ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null, AccessibilityViewCommand.MoveHtmlArguments.class);
            ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null, AccessibilityViewCommand.MoveHtmlArguments.class);
            ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
            ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
            ACTION_COPY = new AccessibilityActionCompat(16384, null);
            ACTION_PASTE = new AccessibilityActionCompat(32768, null);
            ACTION_CUT = new AccessibilityActionCompat(65536, null);
            ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null, AccessibilityViewCommand.SetSelectionArguments.class);
            ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
            ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
            ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
            ACTION_SET_TEXT = new AccessibilityActionCompat(TLObject.FLAG_21, null, AccessibilityViewCommand.SetTextArguments.class);
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
            ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 29) goto L5;
            AccessibilityNodeInfo.AccessibilityAction r5 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline0.m();
        L6:
            ACTION_PAGE_UP = new AccessibilityActionCompat(r5, R.id.accessibilityActionPageUp, null, null, null);
            if (r0 < 29) goto L9;
            AccessibilityNodeInfo.AccessibilityAction r6 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline4.m();
        L10:
            ACTION_PAGE_DOWN = new AccessibilityActionCompat(r6, R.id.accessibilityActionPageDown, null, null, null);
            if (r0 < 29) goto L13;
            AccessibilityNodeInfo.AccessibilityAction r7 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline5.m();
        L14:
            ACTION_PAGE_LEFT = new AccessibilityActionCompat(r7, R.id.accessibilityActionPageLeft, null, null, null);
            if (r0 < 29) goto L17;
            AccessibilityNodeInfo.AccessibilityAction r8 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline6.m();
        L18:
            ACTION_PAGE_RIGHT = new AccessibilityActionCompat(r8, R.id.accessibilityActionPageRight, null, null, null);
            ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            if (r0 < 26) goto L21;
            AccessibilityNodeInfo.AccessibilityAction r4 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline7.m();
        L22:
            ACTION_MOVE_WINDOW = new AccessibilityActionCompat(r4, R.id.accessibilityActionMoveWindow, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
            if (r0 < 28) goto L25;
            AccessibilityNodeInfo.AccessibilityAction r52 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline8.m();
        L26:
            ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat(r52, R.id.accessibilityActionShowTooltip, null, null, null);
            if (r0 < 28) goto L29;
            AccessibilityNodeInfo.AccessibilityAction r62 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline9.m();
        L30:
            ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(r62, R.id.accessibilityActionHideTooltip, null, null, null);
            if (r0 < 30) goto L33;
            AccessibilityNodeInfo.AccessibilityAction r72 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline10.m();
        L34:
            ACTION_PRESS_AND_HOLD = new AccessibilityActionCompat(r72, R.id.accessibilityActionPressAndHold, null, null, null);
            if (r0 < 30) goto L37;
            AccessibilityNodeInfo.AccessibilityAction r82 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline11.m();
        L38:
            ACTION_IME_ENTER = new AccessibilityActionCompat(r82, R.id.accessibilityActionImeEnter, null, null, null);
            if (r0 < 32) goto L41;
            AccessibilityNodeInfo.AccessibilityAction r9 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline12.m();
        L42:
            ACTION_DRAG_START = new AccessibilityActionCompat(r9, R.id.accessibilityActionDragStart, null, null, null);
            if (r0 < 32) goto L45;
            AccessibilityNodeInfo.AccessibilityAction r10 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline1.m();
        L46:
            ACTION_DRAG_DROP = new AccessibilityActionCompat(r10, R.id.accessibilityActionDragDrop, null, null, null);
            if (r0 < 32) goto L49;
            AccessibilityNodeInfo.AccessibilityAction r42 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline2.m();
        L50:
            ACTION_DRAG_CANCEL = new AccessibilityActionCompat(r42, R.id.accessibilityActionDragCancel, null, null, null);
            if (r0 < 33) goto L53;
            AccessibilityNodeInfo.AccessibilityAction r53 = AccessibilityNodeInfoCompat$AccessibilityActionCompat$$ExternalSyntheticApiModelOutline3.m();
        L54:
            ACTION_SHOW_TEXT_SUGGESTIONS = new AccessibilityActionCompat(r53, R.id.accessibilityActionShowTextSuggestions, null, null, null);
            if (r0 < 34) goto L57;
            AccessibilityNodeInfo.AccessibilityAction r63 = Api34Impl.getActionScrollInDirection();
        L58:
            ACTION_SCROLL_IN_DIRECTION = new AccessibilityActionCompat(r63, R.id.accessibilityActionScrollInDirection, null, null, null);
            if (BuildCompat.isAtLeastB_1() == false) goto L61;
            r2 = Api36MinorImpl.getActionSetExtendedSelection();
        L61:
            ACTION_SET_EXTENDED_SELECTION = new AccessibilityActionCompat(r2, R.id.ALT, null, null, null);
            return;
        L57:
            r63 = null;
            goto L58
        L53:
            r53 = null;
            goto L54
        L49:
            r42 = null;
            goto L50
        L45:
            r10 = null;
            goto L46
        L41:
            r9 = null;
            goto L42
        L37:
            r82 = null;
            goto L38
        L33:
            r72 = null;
            goto L34
        L29:
            r62 = null;
            goto L30
        L25:
            r52 = null;
            goto L26
        L21:
            r4 = null;
            goto L22
        L17:
            r8 = null;
            goto L18
        L13:
            r7 = null;
            goto L14
        L9:
            r6 = null;
            goto L10
        L5:
            r5 = null;
            goto L6
        }

        public AccessibilityActionCompat(int r7, CharSequence r8) {
            this(null, r7, r8, null, null);
        }

        public AccessibilityActionCompat(int r7, CharSequence r8, AccessibilityViewCommand r9) {
            this(null, r7, r8, r9, null);
        }

        private AccessibilityActionCompat(int r7, CharSequence r8, Class<? extends AccessibilityViewCommand.CommandArguments> r9) {
            this(null, r7, r8, null, r9);
        }

        public AccessibilityActionCompat(Object r7) {
            this(r7, 0, null, null, null);
        }

        public AccessibilityActionCompat(Object r1, int r2, CharSequence r3, AccessibilityViewCommand r4, Class<? extends AccessibilityViewCommand.CommandArguments> r5) {
            this.mId = r2;
            this.mCommand = r4;
            if (r1 != null) goto L5;
            this.mAction = new AccessibilityNodeInfo.AccessibilityAction(r2, r3);
        L6:
            this.mViewCommandArgumentClass = r5;
            return;
        L5:
            this.mAction = r1;
            goto L6
        }

        public AccessibilityActionCompat createReplacementAction(CharSequence r7, AccessibilityViewCommand r8) {
            return new AccessibilityActionCompat(null, this.mId, r7, r8, this.mViewCommandArgumentClass);
        }

        public boolean equals(Object r3) {
            if (r3 != null) goto L6;
            return false;
        L6:
            if ((r3 instanceof AccessibilityActionCompat) == true) goto L8;
            return false;
        L8:
            Object r2 = this.mAction;
            Object r32 = ((AccessibilityActionCompat) r3).mAction;
            if (r2 != null) goto L13;
            if (r32 == null) goto L15;
            return false;
        L15:
            return true;
        L13:
            if (r2.equals(r32) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public int getId() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
        }

        public CharSequence getLabel() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
        }

        public int hashCode() {
            Object r0 = this.mAction;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.hashCode();
        }

        public boolean perform(View r4, Bundle r5) {
            if (this.mCommand == null) goto L17;
            Class<? extends AccessibilityViewCommand.CommandArguments> r52 = this.mViewCommandArgumentClass;
            if (r52 == null) goto L16;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r52.getDeclaredConstructor(null).newInstance(null));     // Catch: Exception -> L9
            throw null;     // Catch: Exception -> L9
        L9:
            e = move-exception;
            Class<? extends AccessibilityViewCommand.CommandArguments> r1 = this.mViewCommandArgumentClass;
            if (r1 != null) goto L13;
            String r12 = "null";
        L14:
            Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: ".concat(r12), e);
            goto L16
        L13:
            r12 = r1.getName();
        L16:
            return this.mCommand.perform(r4, null);
        L17:
            return false;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("AccessibilityActionCompat: ");
            String r1 = AccessibilityNodeInfoCompat.getActionSymbolicName(this.mId);
            if (r1.equals("ACTION_UNKNOWN") == true) goto L5;
        L7:
            r0.append(r1);
            return r0.toString();
        L5:
            if (getLabel() == null) goto L7;
            r1 = getLabel().toString();
            goto L7
        }
    }

    public static class Api30Impl {
        public static CharSequence getStateDescription(AccessibilityNodeInfo r0) {
            return r0.getStateDescription();
        }

        public static void setStateDescription(AccessibilityNodeInfo r0, CharSequence r1) {
            r0.setStateDescription(r1);
        }
    }

    /* loaded from: classes3.dex */
    public static class Api33Impl {
        public static String getUniqueId(AccessibilityNodeInfo r0) {
            return r0.getUniqueId();
        }

        public static boolean isTextSelectable(AccessibilityNodeInfo r0) {
            return r0.isTextSelectable();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api34Impl {
        public static AccessibilityNodeInfo.AccessibilityAction getActionScrollInDirection() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
        }

        public static void getBoundsInWindow(AccessibilityNodeInfo r0, Rect r1) {
            r0.getBoundsInWindow(r1);
        }

        public static CharSequence getContainerTitle(AccessibilityNodeInfo r0) {
            return r0.getContainerTitle();
        }

        public static boolean isAccessibilityDataSensitive(AccessibilityNodeInfo r0) {
            return r0.isAccessibilityDataSensitive();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api36Impl {
        public static /* synthetic */ int access$000(AccessibilityNodeInfo r0) {
            return getChecked(r0);
        }

        private static int getChecked(AccessibilityNodeInfo r0) {
            return r0.getChecked();
        }

        public static int getExpandedState(AccessibilityNodeInfo r0) {
            return r0.getExpandedState();
        }

        public static CharSequence getSupplementalDescription(AccessibilityNodeInfo r0) {
            return r0.getSupplementalDescription();
        }

        public static boolean isFieldRequired(AccessibilityNodeInfo r0) {
            return r0.isFieldRequired();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api36MinorImpl {
        public static AccessibilityNodeInfo.AccessibilityAction getActionSetExtendedSelection() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_EXTENDED_SELECTION;
        }
    }

    public static class CollectionInfoCompat {
        final Object mInfo;

        public CollectionInfoCompat(Object r1) {
            this.mInfo = r1;
        }

        public static CollectionInfoCompat obtain(int r1, int r2, boolean r3) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(r1, r2, r3));
        }

        public static CollectionInfoCompat obtain(int r1, int r2, boolean r3, int r4) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(r1, r2, r3, r4));
        }
    }

    public static class CollectionItemInfoCompat {
        final Object mInfo;

        public CollectionItemInfoCompat(Object r1) {
            this.mInfo = r1;
        }

        public static CollectionItemInfoCompat obtain(int r1, int r2, int r3, int r4, boolean r5) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(r1, r2, r3, r4, r5));
        }

        public static CollectionItemInfoCompat obtain(int r1, int r2, int r3, int r4, boolean r5, boolean r6) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(r1, r2, r3, r4, r5, r6));
        }

        public int getColumnIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnIndex();
        }

        public int getColumnSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnSpan();
        }

        public int getRowIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowIndex();
        }

        public int getRowSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowSpan();
        }

        @Deprecated
        public boolean isHeading() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).isHeading();
        }
    }

    /* loaded from: classes3.dex */
    public static class RangeInfoCompat {
        final Object mInfo;

        public RangeInfoCompat(Object r1) {
            this.mInfo = r1;
        }

        public static RangeInfoCompat obtain(int r1, float r2, float r3, float r4) {
            return new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(r1, r2, r3, r4));
        }
    }

    static {
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo r2) {
        this.mParentVirtualDescendantId = -1;
        this.mVirtualDescendantId = -1;
        this.mInfo = r2;
    }

    private void addSpanLocationToExtras(ClickableSpan r3, Spanned r4, int r5) {
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(r4.getSpanStart(r3)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(r4.getSpanEnd(r3)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(r4.getSpanFlags(r3)));
        extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(r5));
    }

    private void clearExtrasSpans() {
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
    }

    private List<Integer> extrasIntList(String r2) {
        ArrayList<Integer> r0 = this.mInfo.getExtras().getIntegerArrayList(r2);
        if (r0 != null) goto L6;
        ArrayList<Integer> r02 = new ArrayList();
        this.mInfo.getExtras().putIntegerArrayList(r2, r02);
        return r02;
    L6:
        return r0;
    }

    public static String getActionSymbolicName(int r1) {
        if (r1 != 1) goto L5;
        return "ACTION_FOCUS";
    L5:
        if (r1 == 2) goto L95;
        switch(r1) {
            case 4: goto L93;
            case 8: goto L91;
            case 16: goto L89;
            case 32: goto L87;
            case 64: goto L85;
            case 128: goto L83;
            case 256: goto L81;
            case 512: goto L79;
            case 1024: goto L77;
            case 2048: goto L75;
            case 4096: goto L73;
            case 8192: goto L71;
            case 16384: goto L69;
            case 32768: goto L67;
            case 65536: goto L65;
            case 131072: goto L63;
            case 262144: goto L61;
            case 524288: goto L59;
            case 2097152: goto L57;
            case 16908354: goto L55;
            default: goto L7;
        };
    L7:
        switch(r1) {
            case 16908342: goto L53;
            case 16908343: goto L51;
            case 16908344: goto L49;
            case 16908345: goto L47;
            case 16908346: goto L45;
            case 16908347: goto L43;
            case 16908348: goto L41;
            case 16908349: goto L39;
            default: goto L8;
        };
    L8:
        switch(r1) {
            case 16908356: goto L37;
            case 16908357: goto L35;
            case 16908358: goto L33;
            case 16908359: goto L31;
            case 16908360: goto L29;
            case 16908361: goto L27;
            case 16908362: goto L25;
            default: goto L9;
        };
    L9:
        switch(r1) {
            case 16908372: goto L23;
            case 16908373: goto L21;
            case 16908374: goto L19;
            case 16908375: goto L17;
            default: goto L10;
        };
    L10:
        switch(r1) {
            case 16908382: goto L15;
            case 16908383: goto L13;
            default: goto L11;
        };
    L11:
        return "ACTION_UNKNOWN";
    L13:
        return "ACTION_SET_EXTENDED_SELECTION";
    L15:
        return "ACTION_SCROLL_IN_DIRECTION";
    L17:
        return "ACTION_DRAG_CANCEL";
    L19:
        return "ACTION_DRAG_DROP";
    L21:
        return "ACTION_DRAG_START";
    L23:
        return "ACTION_IME_ENTER";
    L25:
        return "ACTION_PRESS_AND_HOLD";
    L27:
        return "ACTION_PAGE_RIGHT";
    L29:
        return "ACTION_PAGE_LEFT";
    L31:
        return "ACTION_PAGE_DOWN";
    L33:
        return "ACTION_PAGE_UP";
    L35:
        return "ACTION_HIDE_TOOLTIP";
    L37:
        return "ACTION_SHOW_TOOLTIP";
    L39:
        return "ACTION_SET_PROGRESS";
    L41:
        return "ACTION_CONTEXT_CLICK";
    L43:
        return "ACTION_SCROLL_RIGHT";
    L45:
        return "ACTION_SCROLL_DOWN";
    L47:
        return "ACTION_SCROLL_LEFT";
    L49:
        return "ACTION_SCROLL_UP";
    L51:
        return "ACTION_SCROLL_TO_POSITION";
    L53:
        return "ACTION_SHOW_ON_SCREEN";
    L55:
        return "ACTION_MOVE_WINDOW";
    L57:
        return "ACTION_SET_TEXT";
    L59:
        return "ACTION_COLLAPSE";
    L61:
        return "ACTION_EXPAND";
    L63:
        return "ACTION_SET_SELECTION";
    L65:
        return "ACTION_CUT";
    L67:
        return "ACTION_PASTE";
    L69:
        return "ACTION_COPY";
    L71:
        return "ACTION_SCROLL_BACKWARD";
    L73:
        return "ACTION_SCROLL_FORWARD";
    L75:
        return "ACTION_PREVIOUS_HTML_ELEMENT";
    L77:
        return "ACTION_NEXT_HTML_ELEMENT";
    L79:
        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
    L81:
        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
    L83:
        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
    L85:
        return "ACTION_ACCESSIBILITY_FOCUS";
    L87:
        return "ACTION_LONG_CLICK";
    L89:
        return "ACTION_CLICK";
    L91:
        return "ACTION_CLEAR_SELECTION";
    L93:
        return "ACTION_SELECT";
    L95:
        return "ACTION_CLEAR_FOCUS";
    }

    private boolean getBooleanProperty(int r3) {
        Bundle r2 = getExtras();
        if (r2 != null) goto L6;
        return false;
    L6:
        if ((r2.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & r3) != r3) goto L9;
        return true;
    L9:
        return false;
    }

    private String getCheckedString() {
        int r1 = getChecked();
        if (r1 != 1) goto L7;
        return "TRUE";
    L7:
        if (r1 != 2) goto L10;
        return "PARTIAL";
    L10:
        return "FALSE";
    }

    public static ClickableSpan[] getClickableSpans(CharSequence r3) {
        if ((r3 instanceof Spanned) == true) goto L5;
        return null;
    L5:
        return (ClickableSpan[]) ((Spanned) r3).getSpans(0, r3.length(), ClickableSpan.class);
    }

    public static String getExpandedStateSymbolicName(int r1) {
        if (r1 != 0) goto L4;
        return "UNDEFINED";
    L4:
        if (r1 != 1) goto L6;
        return "COLLAPSED";
    L6:
        if (r1 != 2) goto L8;
        return "PARTIAL";
    L8:
        if (r1 == 3) goto L11;
        return "UNKNOWN";
    L11:
        return "FULL";
    }

    private SparseArray<WeakReference<ClickableSpan>> getOrCreateSpansFromViewTags(View r2) {
        SparseArray<WeakReference<ClickableSpan>> r1 = getSpansFromViewTags(r2);
        if (r1 != null) goto L6;
        SparseArray<WeakReference<ClickableSpan>> r12 = new SparseArray();
        r2.setTag(R$id.tag_accessibility_clickable_spans, r12);
        return r12;
    L6:
        return r1;
    }

    private SparseArray<WeakReference<ClickableSpan>> getSpansFromViewTags(View r1) {
        return (SparseArray) r1.getTag(R$id.tag_accessibility_clickable_spans);
    }

    private boolean hasSpans() {
        return !extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    private int idForClickableSpan(ClickableSpan r2, SparseArray<WeakReference<ClickableSpan>> r3) {
        if (r3 == null) goto L11;
        int r1 = 0;
    L5:
        if (r1 >= r3.size()) goto L11;
        if (r2.equals(r3.valueAt(r1).get()) == true) goto L9;
        r1 = r1 + 1;
        goto L5
    L9:
        return r3.keyAt(r1);
    L11:
        int r12 = sClickableSpanId;
        sClickableSpanId = r12 + 1;
        return r12;
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(View r0) {
        return wrap(AccessibilityNodeInfo.obtain(r0));
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat r0) {
        return wrap(AccessibilityNodeInfo.obtain(r0.mInfo));
    }

    private void removeCollectedSpans(View r4) {
        SparseArray<WeakReference<ClickableSpan>> r3 = getSpansFromViewTags(r4);
        if (r3 == null) goto L14;
        ArrayList r42 = new ArrayList();
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r1 >= r3.size()) goto L12;
        if (r3.valueAt(r1).get() != null) goto L10;
        r42.add(Integer.valueOf(r1));
    L10:
        r1 = r1 + 1;
    L12:
        if (r0 >= r42.size()) goto L19;
        r3.remove(((Integer) r42.get(r0)).intValue());
        r0 = r0 + 1;
        goto L12
    L19:
        return;
    }

    private void setBooleanProperty(int r5, boolean r6) {
        Bundle r4 = getExtras();
        if (r4 == null) goto L10;
        int r2 = r4.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~r5);
        if (r6 == true) goto L8;
        r5 = 0;
    L8:
        r4.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", r5 | r2);
        return;
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo r1) {
        return new AccessibilityNodeInfoCompat(r1);
    }

    public void addAction(int r1) {
        this.mInfo.addAction(r1);
    }

    public void addAction(AccessibilityActionCompat r1) {
        this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) r1.mAction);
    }

    public void addChild(View r1, int r2) {
        this.mInfo.addChild(r1, r2);
    }

    public void addSpansToExtras(CharSequence r6, View r7) {
        if (Build.VERSION.SDK_INT >= 26) goto L12;
        clearExtrasSpans();
        removeCollectedSpans(r7);
        ClickableSpan[] r0 = getClickableSpans(r6);
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.length <= 0) goto L15;
        getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> r72 = getOrCreateSpansFromViewTags(r7);
        int r1 = 0;
    L10:
        if (r1 >= r0.length) goto L16;
        int r2 = idForClickableSpan(r0[r1], r72);
        r72.put(r2, new WeakReference(r0[r1]));
        addSpanLocationToExtras(r0[r1], (Spanned) r6, r2);
        r1 = r1 + 1;
        goto L10
    L16:
        return;
    L15:
        return;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if ((r5 instanceof AccessibilityNodeInfoCompat) == true) goto L11;
        return false;
    L11:
        AccessibilityNodeInfoCompat r52 = (AccessibilityNodeInfoCompat) r5;
        AccessibilityNodeInfo r2 = this.mInfo;
        AccessibilityNodeInfo r3 = r52.mInfo;
        if (r2 != null) goto L16;
        if (r3 == null) goto L19;
        return false;
    L19:
        if (this.mVirtualDescendantId == r52.mVirtualDescendantId) goto L22;
        return false;
    L22:
        if (this.mParentVirtualDescendantId == r52.mParentVirtualDescendantId) goto L24;
        return false;
    L24:
        return true;
    L16:
        if (r2.equals(r3) == true) goto L19;
        return false;
    }

    public List<AccessibilityActionCompat> getActionList() {
        List<AccessibilityNodeInfo.AccessibilityAction> r5 = this.mInfo.getActionList();
        ArrayList r0 = new ArrayList();
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(new AccessibilityActionCompat(r5.get(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    @Deprecated
    public int getActions() {
        return this.mInfo.getActions();
    }

    @Deprecated
    public void getBoundsInParent(Rect r1) {
        this.mInfo.getBoundsInParent(r1);
    }

    public void getBoundsInScreen(Rect r1) {
        this.mInfo.getBoundsInScreen(r1);
    }

    public void getBoundsInWindow(Rect r4) {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r3 = this.mInfo;
        if (r0 < 34) goto L6;
        Api34Impl.getBoundsInWindow(r3, r4);
        return;
    L6:
        Rect r32 = (Rect) r3.getExtras().getParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY");
        if (r32 == null) goto L10;
        r4.set(r32.left, r32.top, r32.right, r32.bottom);
        return;
    }

    public int getChecked() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r1 = this.mInfo;
        if (r0 < 36) goto L7;
        return Api36Impl.access$000(r1);
    L7:
        return r1.getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.CHECKED_KEY", this.mInfo.isChecked() ? 1 : 0);
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        AccessibilityNodeInfo.CollectionItemInfo r1 = this.mInfo.getCollectionItemInfo();
        if (r1 != null) goto L5;
        return null;
    L5:
        return new CollectionItemInfoCompat(r1);
    }

    public CharSequence getContainerTitle() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 34) goto L7;
        return Api34Impl.getContainerTitle(r2);
    L7:
        return r2.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY");
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public CharSequence getError() {
        return this.mInfo.getError();
    }

    public int getExpandedState() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 36) goto L7;
        return Api36Impl.getExpandedState(r2);
    L7:
        return r2.getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.EXPANDED_STATE_KEY", 0);
    }

    public Bundle getExtras() {
        return this.mInfo.getExtras();
    }

    public int getMaxTextLength() {
        return this.mInfo.getMaxTextLength();
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public CharSequence getStateDescription() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 30) goto L7;
        return Api30Impl.getStateDescription(r2);
    L7:
        return r2.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY");
    }

    public CharSequence getSupplementalDescription() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 36) goto L7;
        return Api36Impl.getSupplementalDescription(r2);
    L7:
        return r2.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.SUPPLEMENTAL_DESCRIPTION_KEY");
    }

    public CharSequence getText() {
        if (hasSpans() == false) goto L10;
        List<Integer> r0 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> r1 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> r2 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> r3 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        int r7 = 0;
        SpannableString r4 = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
    L6:
        if (r7 >= r0.size()) goto L8;
        r4.setSpan(new AccessibilityClickableSpanCompat(r3.get(r7).intValue(), this, getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), r0.get(r7).intValue(), r1.get(r7).intValue(), r2.get(r7).intValue());
        r7 = r7 + 1;
        goto L6
    L8:
        return r4;
    L10:
        return this.mInfo.getText();
    }

    public CharSequence getTooltipText() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 28) goto L7;
        return r2.getTooltipText();
    L7:
        return r2.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY");
    }

    public String getUniqueId() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 33) goto L7;
        return Api33Impl.getUniqueId(r2);
    L7:
        return r2.getExtras().getString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY");
    }

    public String getViewIdResourceName() {
        return this.mInfo.getViewIdResourceName();
    }

    public int hashCode() {
        AccessibilityNodeInfo r0 = this.mInfo;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.hashCode();
    }

    public boolean isAccessibilityDataSensitive() {
        if (Build.VERSION.SDK_INT < 34) goto L7;
        return Api34Impl.isAccessibilityDataSensitive(this.mInfo);
    L7:
        return getBooleanProperty(64);
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    @Deprecated
    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public boolean isContextClickable() {
        return this.mInfo.isContextClickable();
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFieldRequired() {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 36) goto L7;
        return Api36Impl.isFieldRequired(r2);
    L7:
        return r2.getExtras().getBoolean("androidx.view.accessibility.AccessibilityNodeInfoCompat.IS_REQUIRED_KEY");
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isGranularScrollingSupported() {
        return getBooleanProperty(67108864);
    }

    public boolean isImportantForAccessibility() {
        return this.mInfo.isImportantForAccessibility();
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public boolean isShowingHintText() {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return this.mInfo.isShowingHintText();
    L7:
        return getBooleanProperty(4);
    }

    public boolean isTextSelectable() {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return Api33Impl.isTextSelectable(this.mInfo);
    L7:
        return getBooleanProperty(8388608);
    }

    public boolean isVisibleToUser() {
        return this.mInfo.isVisibleToUser();
    }

    public boolean performAction(int r1, Bundle r2) {
        return this.mInfo.performAction(r1, r2);
    }

    @Deprecated
    public void recycle() {
    }

    public boolean removeAction(AccessibilityActionCompat r1) {
        return this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) r1.mAction);
    }

    public void setAccessibilityFocused(boolean r1) {
        this.mInfo.setAccessibilityFocused(r1);
    }

    @Deprecated
    public void setBoundsInParent(Rect r1) {
        this.mInfo.setBoundsInParent(r1);
    }

    public void setBoundsInScreen(Rect r1) {
        this.mInfo.setBoundsInScreen(r1);
    }

    public void setCheckable(boolean r1) {
        this.mInfo.setCheckable(r1);
    }

    @Deprecated
    public void setChecked(boolean r1) {
        this.mInfo.setChecked(r1);
    }

    public void setClassName(CharSequence r1) {
        this.mInfo.setClassName(r1);
    }

    public void setClickable(boolean r1) {
        this.mInfo.setClickable(r1);
    }

    public void setCollectionInfo(Object r1) {
        AccessibilityNodeInfo r0 = this.mInfo;
        if (r1 != null) goto L5;
        AccessibilityNodeInfo.CollectionInfo r12 = null;
    L6:
        r0.setCollectionInfo(r12);
        return;
    L5:
        r12 = (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) r1).mInfo;
        goto L6
    }

    public void setCollectionItemInfo(Object r1) {
        AccessibilityNodeInfo r0 = this.mInfo;
        if (r1 != null) goto L5;
        AccessibilityNodeInfo.CollectionItemInfo r12 = null;
    L6:
        r0.setCollectionItemInfo(r12);
        return;
    L5:
        r12 = (AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) r1).mInfo;
        goto L6
    }

    public void setContentDescription(CharSequence r1) {
        this.mInfo.setContentDescription(r1);
    }

    public void setDismissable(boolean r1) {
        this.mInfo.setDismissable(r1);
    }

    public void setEnabled(boolean r1) {
        this.mInfo.setEnabled(r1);
    }

    public void setError(CharSequence r1) {
        this.mInfo.setError(r1);
    }

    public void setFocusable(boolean r1) {
        this.mInfo.setFocusable(r1);
    }

    public void setFocused(boolean r1) {
        this.mInfo.setFocused(r1);
    }

    public void setHeading(boolean r3) {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        this.mInfo.setHeading(r3);
        return;
    L6:
        setBooleanProperty(2, r3);
    }

    public void setHintText(CharSequence r3) {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 26) goto L6;
        r2.setHintText(r3);
        return;
    L6:
        r2.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", r3);
    }

    public void setLabelFor(View r1) {
        this.mInfo.setLabelFor(r1);
    }

    public void setMaxTextLength(int r1) {
        this.mInfo.setMaxTextLength(r1);
    }

    public void setPackageName(CharSequence r1) {
        this.mInfo.setPackageName(r1);
    }

    public void setPaneTitle(CharSequence r3) {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 28) goto L6;
        r2.setPaneTitle(r3);
        return;
    L6:
        r2.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", r3);
    }

    public void setParent(View r2) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(r2);
    }

    public void setParent(View r1, int r2) {
        this.mParentVirtualDescendantId = r2;
        this.mInfo.setParent(r1, r2);
    }

    public void setRangeInfo(RangeInfoCompat r1) {
        this.mInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo) r1.mInfo);
    }

    public void setRoleDescription(CharSequence r2) {
        this.mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", r2);
    }

    public void setScreenReaderFocusable(boolean r3) {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        this.mInfo.setScreenReaderFocusable(r3);
        return;
    L6:
        setBooleanProperty(1, r3);
    }

    public void setScrollable(boolean r1) {
        this.mInfo.setScrollable(r1);
    }

    public void setShowingHintText(boolean r3) {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        this.mInfo.setShowingHintText(r3);
        return;
    L6:
        setBooleanProperty(4, r3);
    }

    public void setSource(View r1, int r2) {
        this.mVirtualDescendantId = r2;
        this.mInfo.setSource(r1, r2);
    }

    public void setStateDescription(CharSequence r3) {
        int r0 = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo r2 = this.mInfo;
        if (r0 < 30) goto L6;
        Api30Impl.setStateDescription(r2, r3);
        return;
    L6:
        r2.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", r3);
    }

    public void setText(CharSequence r1) {
        this.mInfo.setText(r1);
    }

    public void setTraversalAfter(View r1) {
        this.mInfo.setTraversalAfter(r1);
    }

    public void setVisibleToUser(boolean r1) {
        this.mInfo.setVisibleToUser(r1);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(super.toString());
        Rect r1 = new Rect();
        getBoundsInParent(r1);
        r0.append("; boundsInParent: " + r1);
        getBoundsInScreen(r1);
        r0.append("; boundsInScreen: " + r1);
        getBoundsInWindow(r1);
        r0.append("; boundsInWindow: " + r1);
        r0.append("; packageName: ");
        r0.append(getPackageName());
        r0.append("; className: ");
        r0.append(getClassName());
        r0.append("; text: ");
        r0.append(getText());
        r0.append("; error: ");
        r0.append(getError());
        r0.append("; maxTextLength: ");
        r0.append(getMaxTextLength());
        r0.append("; stateDescription: ");
        r0.append(getStateDescription());
        r0.append("; contentDescription: ");
        r0.append(getContentDescription());
        r0.append("; supplementalDescription: ");
        r0.append(getSupplementalDescription());
        r0.append("; tooltipText: ");
        r0.append(getTooltipText());
        r0.append("; viewIdResName: ");
        r0.append(getViewIdResourceName());
        r0.append("; uniqueId: ");
        r0.append(getUniqueId());
        r0.append("; checkable: ");
        r0.append(isCheckable());
        r0.append("; checked: ");
        r0.append(getCheckedString());
        r0.append("; fieldRequired: ");
        r0.append(isFieldRequired());
        r0.append("; focusable: ");
        r0.append(isFocusable());
        r0.append("; focused: ");
        r0.append(isFocused());
        r0.append("; selected: ");
        r0.append(isSelected());
        r0.append("; clickable: ");
        r0.append(isClickable());
        r0.append("; longClickable: ");
        r0.append(isLongClickable());
        r0.append("; contextClickable: ");
        r0.append(isContextClickable());
        r0.append("; expandedState: ");
        r0.append(getExpandedStateSymbolicName(getExpandedState()));
        r0.append("; enabled: ");
        r0.append(isEnabled());
        r0.append("; password: ");
        r0.append(isPassword());
        r0.append("; scrollable: " + isScrollable());
        r0.append("; containerTitle: ");
        r0.append(getContainerTitle());
        r0.append("; granularScrollingSupported: ");
        r0.append(isGranularScrollingSupported());
        r0.append("; importantForAccessibility: ");
        r0.append(isImportantForAccessibility());
        r0.append("; visible: ");
        r0.append(isVisibleToUser());
        r0.append("; isTextSelectable: ");
        r0.append(isTextSelectable());
        r0.append("; accessibilityDataSensitive: ");
        r0.append(isAccessibilityDataSensitive());
        r0.append("; [");
        List<AccessibilityActionCompat> r5 = getActionList();
        int r12 = 0;
    L4:
        if (r12 >= r5.size()) goto L14;
        AccessibilityActionCompat r2 = r5.get(r12);
        String r3 = getActionSymbolicName(r2.getId());
        if (r3.equals("ACTION_UNKNOWN") == true) goto L8;
    L10:
        r0.append(r3);
        if (r12 == (r5.size() - 1)) goto L13;
        r0.append(", ");
    L13:
        r12 = r12 + 1;
        goto L4
    L8:
        if (r2.getLabel() == null) goto L10;
        r3 = r2.getLabel().toString();
        goto L10
    L14:
        r0.append("]");
        return r0.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }
}
