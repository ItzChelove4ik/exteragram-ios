package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class Transition implements Cloneable {
    static final boolean DBG = false;
    private static final int[] DEFAULT_MATCH_ORDER = null;
    private static final Animator[] EMPTY_ANIMATOR_ARRAY = null;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private static final PathMotion STRAIGHT_PATH_MOTION = null;
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators;
    private Animator[] mAnimatorCache;
    ArrayList<Animator> mAnimators;
    boolean mCanRemoveViews;
    private Transition mCloneParent;
    ArrayList<Animator> mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    private ArrayList<TransitionValues> mEndValuesList;
    boolean mEnded;
    private EpicenterCallback mEpicenterCallback;
    private TimeInterpolator mInterpolator;
    private ArrayList<TransitionListener> mListeners;
    private TransitionListener[] mListenersCache;
    private int[] mMatchOrder;
    private String mName;
    private ArrayMap<String, String> mNameOverrides;
    int mNumInstances;
    TransitionSet mParent;
    private PathMotion mPathMotion;
    private boolean mPaused;
    TransitionPropagation mPropagation;
    SeekController mSeekController;
    long mSeekOffsetInParent;
    private long mStartDelay;
    private TransitionValuesMaps mStartValues;
    private ArrayList<TransitionValues> mStartValuesList;
    private ArrayList<View> mTargetChildExcludes;
    private ArrayList<View> mTargetExcludes;
    private ArrayList<Integer> mTargetIdChildExcludes;
    private ArrayList<Integer> mTargetIdExcludes;
    ArrayList<Integer> mTargetIds;
    private ArrayList<String> mTargetNameExcludes;
    private ArrayList<String> mTargetNames;
    private ArrayList<Class<?>> mTargetTypeChildExcludes;
    private ArrayList<Class<?>> mTargetTypeExcludes;
    private ArrayList<Class<?>> mTargetTypes;
    ArrayList<View> mTargets;
    long mTotalDuration;

    /* renamed from: androidx.transition.Transition$1, reason: invalid class name */
    public class AnonymousClass1 extends PathMotion {
        public AnonymousClass1() {
        }

        @Override // androidx.transition.PathMotion
        public Path getPath(float r1, float r2, float r3, float r4) {
            Path r0 = new Path();
            r0.moveTo(r1, r2);
            r0.lineTo(r3, r4);
            return r0;
        }
    }

    /* renamed from: androidx.transition.Transition$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ Transition this$0;
        final /* synthetic */ ArrayMap val$runningAnimators;

        public AnonymousClass2(Transition r1, ArrayMap r2) {
            this.this$0 = r1;
            this.val$runningAnimators = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$runningAnimators.remove(r2);
            this.this$0.mCurrentAnimators.remove(r2);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.mCurrentAnimators.add(r1);
        }
    }

    /* renamed from: androidx.transition.Transition$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ Transition this$0;

        public AnonymousClass3(Transition r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.end();
            r2.removeListener(this);
        }
    }

    public static class AnimationInfo {
        Animator mAnimator;
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowId mWindowId;

        public AnimationInfo(View r1, String r2, Transition r3, WindowId r4, TransitionValues r5, Animator r6) {
            this.mView = r1;
            this.mName = r2;
            this.mValues = r5;
            this.mWindowId = r4;
            this.mTransition = r3;
            this.mAnimator = r6;
        }
    }

    public static class ArrayListManager {
        public static <T> ArrayList<T> add(ArrayList<T> r1, T r2) {
            if (r1 != null) goto L5;
            r1 = new ArrayList();
        L5:
            if (r1.contains(r2) == true) goto L7;
            r1.add(r2);
        L7:
            return r1;
        }

        public static <T> ArrayList<T> remove(ArrayList<T> r0, T r1) {
            if (r0 == null) goto L7;
            r0.remove(r1);
            if (r0.isEmpty() == false) goto L8;
            return null;
        L8:
            return r0;
        L7:
            return r0;
        }
    }

    public static abstract class EpicenterCallback {
        public EpicenterCallback() {
        }

        public abstract Rect onGetEpicenter(Transition r1);
    }

    public static class Impl26 {
        public static long getTotalDuration(Animator r2) {
            return r2.getTotalDuration();
        }

        public static void setCurrentPlayTime(Animator r0, long r1) {
            ((AnimatorSet) r0).setCurrentPlayTime(r1);
        }
    }

    public class SeekController extends TransitionListenerAdapter implements TransitionSeekController, DynamicAnimation.OnAnimationUpdateListener {
        private long mCurrentPlayTime;
        private boolean mIsCanceled;
        private boolean mIsReady;
        private Consumer<TransitionSeekController>[] mListenerCache;
        private ArrayList<Consumer<TransitionSeekController>> mOnProgressListeners;
        private ArrayList<Consumer<TransitionSeekController>> mOnReadyListeners;
        private final VelocityTracker1D mVelocityTracker;
        final /* synthetic */ Transition this$0;

        public SeekController(Transition r3) {
            this.this$0 = r3;
            this.mCurrentPlayTime = -1;
            this.mOnReadyListeners = null;
            this.mOnProgressListeners = null;
            this.mListenerCache = null;
            this.mVelocityTracker = new VelocityTracker1D();
        }

        private void callProgressListeners() {
            ArrayList<Consumer<TransitionSeekController>> r0 = this.mOnProgressListeners;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isEmpty() == true) goto L17;
            int r02 = this.mOnProgressListeners.size();
            if (this.mListenerCache != null) goto L10;
            this.mListenerCache = new Consumer[r02];
        L10:
            Consumer<TransitionSeekController>[] r1 = (Consumer[]) this.mOnProgressListeners.toArray(this.mListenerCache);
            this.mListenerCache = null;
            int r3 = 0;
        L11:
            if (r3 >= r02) goto L13;
            r1[r3].accept(this);
            r1[r3] = null;
            r3 = r3 + 1;
            goto L11
        L13:
            this.mListenerCache = r1;
            return;
        }

        public long getDurationMillis() {
            return this.this$0.getTotalDurationMillis();
        }

        public void initPlayTime() {
            long r2 = 0;
            if (getDurationMillis() != 0) goto L5;
            r2 = 1;
        L5:
            this.this$0.setCurrentPlayTimeMillis(r2, this.mCurrentPlayTime);
            this.mCurrentPlayTime = r2;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void onAnimationUpdate(DynamicAnimation r5, float r6, float r7) {
            long r52 = Math.max(-1, Math.min(getDurationMillis() + 1, Math.round(r6)));
            this.this$0.setCurrentPlayTimeMillis(r52, this.mCurrentPlayTime);
            this.mCurrentPlayTime = r52;
            callProgressListeners();
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
            this.mIsCanceled = true;
        }

        public void ready() {
            this.mIsReady = true;
            ArrayList<Consumer<TransitionSeekController>> r0 = this.mOnReadyListeners;
            if (r0 == null) goto L8;
            this.mOnReadyListeners = null;
            int r1 = 0;
        L6:
            if (r1 >= r0.size()) goto L8;
            r0.get(r1).accept(this);
            r1 = r1 + 1;
        L8:
            callProgressListeners();
        }
    }

    public interface TransitionListener {
        void onTransitionCancel(Transition r1);

        void onTransitionEnd(Transition r1);

        default void onTransitionEnd(Transition r1, boolean r2) {
            onTransitionEnd(r1);
        }

        void onTransitionPause(Transition r1);

        void onTransitionResume(Transition r1);

        void onTransitionStart(Transition r1);

        default void onTransitionStart(Transition r1, boolean r2) {
            onTransitionStart(r1);
        }
    }

    public interface TransitionNotification {
        public static final TransitionNotification ON_CANCEL = null;
        public static final TransitionNotification ON_END = null;
        public static final TransitionNotification ON_PAUSE = null;
        public static final TransitionNotification ON_RESUME = null;
        public static final TransitionNotification ON_START = null;

        /* renamed from: $r8$lambda$kcAdrQxAbi3Zbad--zqmSrKWUbs, reason: not valid java name */
        static /* synthetic */ void m965$r8$lambda$kcAdrQxAbi3ZbadzqmSrKWUbs(TransitionListener r0, Transition r1, boolean r2) {
            r0.onTransitionCancel(r1);
        }

        static /* synthetic */ void $r8$lambda$vS0PPzRBsBECqbPtRDoJPVgOd0Y(TransitionListener r0, Transition r1, boolean r2) {
            r0.onTransitionPause(r1);
        }

        static /* synthetic */ void $r8$lambda$vp58gyRnHCGpuF0_VPAWJHF5ero(TransitionListener r0, Transition r1, boolean r2) {
            r0.onTransitionResume(r1);
        }

        static {
            ON_START = new Transition$TransitionNotification$$ExternalSyntheticLambda0();
            ON_END = new Transition$TransitionNotification$$ExternalSyntheticLambda1();
            ON_CANCEL = new Transition$TransitionNotification$$ExternalSyntheticLambda2();
            ON_PAUSE = new Transition$TransitionNotification$$ExternalSyntheticLambda3();
            ON_RESUME = new Transition$TransitionNotification$$ExternalSyntheticLambda4();
        }

        void notifyListener(TransitionListener r1, Transition r2, boolean r3);
    }

    static {
        EMPTY_ANIMATOR_ARRAY = new Animator[0];
        DEFAULT_MATCH_ORDER = new int[]{2, 1, 3, 4};
        STRAIGHT_PATH_MOTION = new AnonymousClass1();
        sRunningAnimators = new ThreadLocal();
    }

    public Transition() {
        this.mName = getClass().getName();
        this.mStartDelay = -1;
        this.mDuration = -1;
        this.mInterpolator = null;
        this.mTargetIds = new ArrayList();
        this.mTargets = new ArrayList();
        this.mTargetNames = null;
        this.mTargetTypes = null;
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetNameExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        this.mStartValues = new TransitionValuesMaps();
        this.mEndValues = new TransitionValuesMaps();
        this.mParent = null;
        this.mMatchOrder = DEFAULT_MATCH_ORDER;
        this.mCanRemoveViews = false;
        this.mCurrentAnimators = new ArrayList();
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mEnded = false;
        this.mCloneParent = null;
        this.mListeners = null;
        this.mAnimators = new ArrayList();
        this.mPathMotion = STRAIGHT_PATH_MOTION;
    }

    public static /* synthetic */ Transition access$000(Transition r0) {
        return r0.mCloneParent;
    }

    public static /* synthetic */ Transition access$002(Transition r0, Transition r1) {
        r0.mCloneParent = r1;
        return r1;
    }

    private void addUnmatched(ArrayMap<View, TransitionValues> r6, ArrayMap<View, TransitionValues> r7) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L10;
        TransitionValues r2 = r6.valueAt(r1);
        if (isValidTarget(r2.view) == false) goto L8;
        this.mStartValuesList.add(r2);
        this.mEndValuesList.add(null);
    L8:
        r1 = r1 + 1;
    L10:
        if (r0 >= r7.size()) goto L15;
        TransitionValues r62 = r7.valueAt(r0);
        if (isValidTarget(r62.view) == false) goto L14;
        this.mEndValuesList.add(r62);
        this.mStartValuesList.add(null);
    L14:
        r0 = r0 + 1;
        goto L10
    }

    private static void addViewValues(TransitionValuesMaps r3, View r4, TransitionValues r5) {
        r3.mViewValues.put(r4, r5);
        int r52 = r4.getId();
        if (r52 < 0) goto L8;
        int r1 = r3.mIdValues.indexOfKey(r52);
        SparseArray<View> r2 = r3.mIdValues;
        if (r1 < 0) goto L7;
        r2.put(r52, null);
        goto L8
    L7:
        r2.put(r52, r4);
    L8:
        String r53 = ViewCompat.getTransitionName(r4);
        if (r53 == null) goto L15;
        boolean r12 = r3.mNameValues.containsKey(r53);
        ArrayMap<String, View> r22 = r3.mNameValues;
        if (r12 == false) goto L13;
        r22.put(r53, null);
        goto L15
    L13:
        r22.put(r53, r4);
    L15:
        if ((r4.getParent() instanceof ListView) == false) goto L26;
        ListView r54 = (ListView) r4.getParent();
        if (r54.getAdapter().hasStableIds() == false) goto L27;
        long r13 = r54.getItemIdAtPosition(r54.getPositionForView(r4));
        if (r3.mItemIdValues.indexOfKey(r13) < 0) goto L24;
        View r42 = r3.mItemIdValues.get(r13);
        if (r42 == null) goto L28;
        r42.setHasTransientState(false);
        r3.mItemIdValues.put(r13, null);
        return;
    L28:
        return;
    L24:
        r4.setHasTransientState(true);
        r3.mItemIdValues.put(r13, r4);
        return;
    L27:
        return;
    }

    private static boolean alreadyContains(int[] r4, int r5) {
        int r0 = r4[r5];
        int r2 = 0;
    L3:
        if (r2 >= r5) goto L9;
        if (r4[r2] == r0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private void captureHierarchy(View r6, boolean r7) {
        if (r6 == null) goto L62;
        int r0 = r6.getId();
        ArrayList<Integer> r1 = this.mTargetIdExcludes;
        if (r1 != null) goto L7;
    L9:
        ArrayList<View> r12 = this.mTargetExcludes;
        if (r12 != null) goto L12;
    L14:
        ArrayList<Class<?>> r13 = this.mTargetTypeExcludes;
        int r2 = 0;
        if (r13 == null) goto L23;
        int r14 = r13.size();
        int r3 = 0;
    L17:
        if (r3 >= r14) goto L23;
        if (this.mTargetTypeExcludes.get(r3).isInstance(r6) == true) goto L67;
        r3 = r3 + 1;
        goto L17
    L67:
        return;
    L23:
        if ((r6.getParent() instanceof ViewGroup) == false) goto L33;
        TransitionValues r15 = new TransitionValues(r6);
        if (r7 == false) goto L27;
        captureStartValues(r15);
    L28:
        r15.mTargetedTransitions.add(this);
        capturePropagationValues(r15);
        if (r7 == false) goto L31;
        addViewValues(this.mStartValues, r6, r15);
        goto L33
    L31:
        addViewValues(this.mEndValues, r6, r15);
        goto L33
    L27:
        captureEndValues(r15);
    L33:
        if ((r6 instanceof ViewGroup) == false) goto L56;
        ArrayList<Integer> r16 = this.mTargetIdChildExcludes;
        if (r16 != null) goto L37;
    L39:
        ArrayList<View> r02 = this.mTargetChildExcludes;
        if (r02 != null) goto L42;
    L44:
        ArrayList<Class<?>> r03 = this.mTargetTypeChildExcludes;
        if (r03 == null) goto L52;
        int r04 = r03.size();
        int r17 = 0;
    L47:
        if (r17 >= r04) goto L52;
        if (this.mTargetTypeChildExcludes.get(r17).isInstance(r6) == true) goto L68;
        r17 = r17 + 1;
        goto L47
    L68:
        return;
    L52:
        ViewGroup r62 = (ViewGroup) r6;
    L54:
        if (r2 >= r62.getChildCount()) goto L69;
        captureHierarchy(r62.getChildAt(r2), r7);
        r2 = r2 + 1;
        goto L54
    L69:
        return;
    L42:
        if (r02.contains(r6) == false) goto L44;
        return;
    L37:
        if (r16.contains(Integer.valueOf(r0)) == false) goto L39;
        return;
    L56:
        return;
    L12:
        if (r12.contains(r6) == false) goto L14;
        return;
    L7:
        if (r1.contains(Integer.valueOf(r0)) == false) goto L9;
        return;
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> r1, int r2, boolean r3) {
        if (r2 <= 0) goto L8;
        if (r3 == false) goto L7;
        return ArrayListManager.add(r1, Integer.valueOf(r2));
    L7:
        return ArrayListManager.remove(r1, Integer.valueOf(r2));
    L8:
        return r1;
    }

    private static <T> ArrayList<T> excludeObject(ArrayList<T> r0, T r1, boolean r2) {
        if (r1 == null) goto L8;
        if (r2 == false) goto L7;
        return ArrayListManager.add(r0, r1);
    L7:
        return ArrayListManager.remove(r0, r1);
    L8:
        return r0;
    }

    private ArrayList<Class<?>> excludeType(ArrayList<Class<?>> r1, Class<?> r2, boolean r3) {
        if (r2 == null) goto L8;
        if (r3 == false) goto L7;
        return ArrayListManager.add(r1, r2);
    L7:
        return ArrayListManager.remove(r1, r2);
    L8:
        return r1;
    }

    private ArrayList<View> excludeView(ArrayList<View> r1, View r2, boolean r3) {
        if (r2 == null) goto L8;
        if (r3 == false) goto L7;
        return ArrayListManager.add(r1, r2);
    L7:
        return ArrayListManager.remove(r1, r2);
    L8:
        return r1;
    }

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> r0 = sRunningAnimators.get();
        if (r0 != null) goto L6;
        ArrayMap<Animator, AnimationInfo> r02 = new ArrayMap();
        sRunningAnimators.set(r02);
        return r02;
    L6:
        return r0;
    }

    private static boolean isValidMatch(int r2) {
        if (r2 >= 1) goto L5;
        return false;
    L5:
        if (r2 > 4) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean isValueChanged(TransitionValues r0, TransitionValues r1, String r2) {
        Object r02 = r0.values.get(r2);
        Object r12 = r1.values.get(r2);
        if (r02 != null) goto L8;
        if (r12 != null) goto L8;
        return false;
    L8:
        if (r02 == null) goto L13;
        if (r12 == null) goto L13;
        return !r02.equals(r12);
    L13:
        return true;
    }

    private void matchIds(ArrayMap<View, TransitionValues> r8, ArrayMap<View, TransitionValues> r9, SparseArray<View> r10, SparseArray<View> r11) {
        int r0 = r10.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L17;
        View r2 = r10.valueAt(r1);
        if (r2 == null) goto L16;
        if (isValidTarget(r2) == false) goto L16;
        View r3 = r11.get(r10.keyAt(r1));
        if (r3 == null) goto L16;
        if (isValidTarget(r3) == false) goto L16;
        TransitionValues r4 = r8.get(r2);
        TransitionValues r5 = r9.get(r3);
        if (r4 == null) goto L16;
        if (r5 == null) goto L16;
        this.mStartValuesList.add(r4);
        this.mEndValuesList.add(r5);
        r8.remove(r2);
        r9.remove(r3);
    L16:
        r1 = r1 + 1;
        goto L3
    }

    private void matchInstances(ArrayMap<View, TransitionValues> r5, ArrayMap<View, TransitionValues> r6) {
        int r0 = r5.size() - 1;
    L3:
        if (r0 < 0) goto L14;
        View r1 = r5.keyAt(r0);
        if (r1 == null) goto L13;
        if (isValidTarget(r1) == false) goto L13;
        TransitionValues r12 = r6.remove(r1);
        if (r12 == null) goto L13;
        if (isValidTarget(r12.view) == false) goto L13;
        this.mStartValuesList.add(r5.removeAt(r0));
        this.mEndValuesList.add(r12);
    L13:
        r0 = r0 - 1;
        goto L3
    }

    private void matchItemIds(ArrayMap<View, TransitionValues> r8, ArrayMap<View, TransitionValues> r9, LongSparseArray<View> r10, LongSparseArray<View> r11) {
        int r0 = r10.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L17;
        View r2 = r10.valueAt(r1);
        if (r2 == null) goto L16;
        if (isValidTarget(r2) == false) goto L16;
        View r3 = r11.get(r10.keyAt(r1));
        if (r3 == null) goto L16;
        if (isValidTarget(r3) == false) goto L16;
        TransitionValues r4 = r8.get(r2);
        TransitionValues r5 = r9.get(r3);
        if (r4 == null) goto L16;
        if (r5 == null) goto L16;
        this.mStartValuesList.add(r4);
        this.mEndValuesList.add(r5);
        r8.remove(r2);
        r9.remove(r3);
    L16:
        r1 = r1 + 1;
        goto L3
    }

    private void matchNames(ArrayMap<View, TransitionValues> r8, ArrayMap<View, TransitionValues> r9, ArrayMap<String, View> r10, ArrayMap<String, View> r11) {
        int r0 = r10.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L17;
        View r2 = r10.valueAt(r1);
        if (r2 == null) goto L16;
        if (isValidTarget(r2) == false) goto L16;
        View r3 = r11.get(r10.keyAt(r1));
        if (r3 == null) goto L16;
        if (isValidTarget(r3) == false) goto L16;
        TransitionValues r4 = r8.get(r2);
        TransitionValues r5 = r9.get(r3);
        if (r4 == null) goto L16;
        if (r5 == null) goto L16;
        this.mStartValuesList.add(r4);
        this.mEndValuesList.add(r5);
        r8.remove(r2);
        r9.remove(r3);
    L16:
        r1 = r1 + 1;
        goto L3
    }

    private void matchStartAndEnd(TransitionValuesMaps r6, TransitionValuesMaps r7) {
        ArrayMap<View, TransitionValues> r0 = new ArrayMap(r6.mViewValues);
        ArrayMap<View, TransitionValues> r1 = new ArrayMap(r7.mViewValues);
        int r2 = 0;
    L3:
        int[] r3 = this.mMatchOrder;
        if (r2 >= r3.length) goto L19;
        int r32 = r3[r2];
        if (r32 != 1) goto L8;
        matchInstances(r0, r1);
    L18:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r32 != 2) goto L10;
        matchNames(r0, r1, r6.mNameValues, r7.mNameValues);
        goto L18
    L10:
        if (r32 != 3) goto L12;
        matchIds(r0, r1, r6.mIdValues, r7.mIdValues);
        goto L18
    L12:
        if (r32 != 4) goto L18;
        matchItemIds(r0, r1, r6.mItemIdValues, r7.mItemIdValues);
        goto L18
    L19:
        addUnmatched(r0, r1);
    }

    private void notifyFromTransition(Transition r6, TransitionNotification r7, boolean r8) {
        Transition r0 = this.mCloneParent;
        if (r0 == null) goto L5;
        r0.notifyFromTransition(r6, r7, r8);
    L5:
        ArrayList<TransitionListener> r02 = this.mListeners;
        if (r02 != null) goto L8;
        return;
    L8:
        if (r02.isEmpty() == true) goto L19;
        int r03 = this.mListeners.size();
        TransitionListener[] r1 = this.mListenersCache;
        if (r1 != null) goto L12;
        r1 = new TransitionListener[r03];
    L12:
        this.mListenersCache = null;
        TransitionListener[] r12 = (TransitionListener[]) this.mListeners.toArray(r1);
        int r3 = 0;
    L13:
        if (r3 >= r03) goto L15;
        r7.notifyListener(r12[r3], r6, r8);
        r12[r3] = null;
        r3 = r3 + 1;
        goto L13
    L15:
        this.mListenersCache = r12;
        return;
    }

    private void runAnimator(Animator r2, ArrayMap<Animator, AnimationInfo> r3) {
        if (r2 == null) goto L5;
        r2.addListener(new AnonymousClass2(this, r3));
        animate(r2);
        return;
    }

    public Transition addListener(TransitionListener r2) {
        if (this.mListeners != null) goto L5;
        this.mListeners = new ArrayList();
    L5:
        this.mListeners.add(r2);
        return this;
    }

    public Transition addTarget(int r2) {
        if (r2 == 0) goto L4;
        this.mTargetIds.add(Integer.valueOf(r2));
    L4:
        return this;
    }

    public Transition addTarget(View r2) {
        this.mTargets.add(r2);
        return this;
    }

    public Transition addTarget(Class<?> r2) {
        if (this.mTargetTypes != null) goto L5;
        this.mTargetTypes = new ArrayList();
    L5:
        this.mTargetTypes.add(r2);
        return this;
    }

    public Transition addTarget(String r2) {
        if (this.mTargetNames != null) goto L5;
        this.mTargetNames = new ArrayList();
    L5:
        this.mTargetNames.add(r2);
        return this;
    }

    public void animate(Animator r5) {
        if (r5 != null) goto L6;
        end();
        return;
    L6:
        if (getDuration() < 0) goto L9;
        r5.setDuration(getDuration());
    L9:
        if (getStartDelay() < 0) goto L12;
        r5.setStartDelay(getStartDelay() + r5.getStartDelay());
    L12:
        if (getInterpolator() == null) goto L14;
        r5.setInterpolator(getInterpolator());
    L14:
        r5.addListener(new AnonymousClass3(this));
        r5.start();
    }

    public void cancel() {
        int r0 = this.mCurrentAnimators.size();
        Animator[] r1 = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        int r02 = r0 - 1;
    L3:
        if (r02 < 0) goto L5;
        Animator r2 = r1[r02];
        r1[r02] = null;
        r2.cancel();
        r02 = r02 - 1;
        goto L3
    L5:
        this.mAnimatorCache = r1;
        notifyListeners(TransitionNotification.ON_CANCEL, false);
    }

    public abstract void captureEndValues(TransitionValues r1);

    public void capturePropagationValues(TransitionValues r1) {
    }

    public abstract void captureStartValues(TransitionValues r1);

    public void captureValues(ViewGroup r6, boolean r7) {
        clearValues(r7);
        int r1 = 0;
        if (this.mTargetIds.size() <= 0) goto L5;
    L6:
        ArrayList<String> r0 = this.mTargetNames;
        if (r0 != null) goto L9;
    L10:
        ArrayList<Class<?>> r02 = this.mTargetTypes;
        if (r02 != null) goto L13;
    L16:
        int r03 = 0;
    L18:
        if (r03 >= this.mTargetIds.size()) goto L30;
        View r2 = r6.findViewById(this.mTargetIds.get(r03).intValue());
        if (r2 == null) goto L29;
        TransitionValues r3 = new TransitionValues(r2);
        if (r7 == false) goto L24;
        captureStartValues(r3);
    L25:
        r3.mTargetedTransitions.add(this);
        capturePropagationValues(r3);
        if (r7 == false) goto L28;
        addViewValues(this.mStartValues, r2, r3);
        goto L29
    L28:
        addViewValues(this.mEndValues, r2, r3);
        goto L29
    L24:
        captureEndValues(r3);
    L29:
        r03 = r03 + 1;
        goto L18
    L30:
        int r62 = 0;
    L32:
        if (r62 >= this.mTargets.size()) goto L42;
        View r04 = this.mTargets.get(r62);
        TransitionValues r22 = new TransitionValues(r04);
        if (r7 == false) goto L36;
        captureStartValues(r22);
    L37:
        r22.mTargetedTransitions.add(this);
        capturePropagationValues(r22);
        if (r7 == false) goto L40;
        addViewValues(this.mStartValues, r04, r22);
    L41:
        r62 = r62 + 1;
        goto L32
    L40:
        addViewValues(this.mEndValues, r04, r22);
        goto L41
    L36:
        captureEndValues(r22);
    L42:
        if (r7 == true) goto L53;
        ArrayMap<String, String> r63 = this.mNameOverrides;
        if (r63 == null) goto L65;
        int r64 = r63.size();
        ArrayList r72 = new ArrayList(r64);
        int r05 = 0;
    L46:
        if (r05 >= r64) goto L48;
        r72.add(this.mStartValues.mNameValues.remove(this.mNameOverrides.keyAt(r05)));
        r05 = r05 + 1;
    L48:
        if (r1 >= r64) goto L66;
        View r06 = (View) r72.get(r1);
        if (r06 == null) goto L52;
        this.mStartValues.mNameValues.put(this.mNameOverrides.valueAt(r1), r06);
    L52:
        r1 = r1 + 1;
        goto L48
    L66:
        return;
    L65:
        return;
    L53:
        return;
    L13:
        if (r02.isEmpty() == true) goto L16;
    L15:
        captureHierarchy(r6, r7);
        goto L42
    L9:
        if (r0.isEmpty() == false) goto L15;
    L5:
        if (this.mTargets.size() <= 0) goto L15;
        goto L6
    }

    public void clearValues(boolean r1) {
        if (r1 == false) goto L5;
        this.mStartValues.mViewValues.clear();
        this.mStartValues.mIdValues.clear();
        this.mStartValues.mItemIdValues.clear();
        return;
    L5:
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    public Transition clone() {
        Transition r1 = (Transition) super.clone();     // Catch: CloneNotSupportedException -> L5
        r1.mAnimators = new ArrayList();     // Catch: CloneNotSupportedException -> L5
        r1.mStartValues = new TransitionValuesMaps();     // Catch: CloneNotSupportedException -> L5
        r1.mEndValues = new TransitionValuesMaps();     // Catch: CloneNotSupportedException -> L5
        r1.mStartValuesList = null;     // Catch: CloneNotSupportedException -> L5
        r1.mEndValuesList = null;     // Catch: CloneNotSupportedException -> L5
        r1.mSeekController = null;     // Catch: CloneNotSupportedException -> L5
        r1.mCloneParent = this;     // Catch: CloneNotSupportedException -> L5
        r1.mListeners = null;     // Catch: CloneNotSupportedException -> L5
        return r1;
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo964clone() {
        return clone();
    }

    public Animator createAnimator(ViewGroup r1, TransitionValues r2, TransitionValues r3) {
        return null;
    }

    public void createAnimators(ViewGroup r19, TransitionValuesMaps r20, TransitionValuesMaps r21, ArrayList<TransitionValues> r22, ArrayList<TransitionValues> r23) {
        Transition r3 = this;
        ArrayMap<Animator, AnimationInfo> r7 = getRunningAnimators();
        SparseIntArray r8 = new SparseIntArray();
        int r9 = r22.size();
        if (r3.getRootTransition().mSeekController == null) goto L5;
        boolean r11 = true;
    L6:
        int r12 = 0;
    L7:
        if (r12 >= r9) goto L63;
        TransitionValues r0 = r22.get(r12);
        TransitionValues r1 = r23.get(r12);
        if (r0 != null) goto L11;
    L13:
        if (r1 != null) goto L15;
    L17:
        if (r0 != null) goto L20;
        if (r1 != null) goto L20;
    L61:
        r12 = r12 + 1;
    L20:
        if (r0 == null) goto L24;
        if (r1 == null) goto L24;
        if (r3.isTransitionRequired(r0, r1) == false) goto L61;
    L24:
        Animator r4 = r3.createAnimator(r19, r0, r1);
        if (r4 == null) goto L61;
        if (r1 == null) goto L53;
        View r02 = r1.view;
        String[] r13 = r3.getTransitionProperties();
        if (r13 != null) goto L30;
    L50:
        Animator r2 = r4;
        TransitionValues r5 = null;
    L51:
        r4 = r2;
    L52:
        View r14 = r02;
        if (r4 == null) goto L60;
        Animator r6 = r4;
        r3 = this;
        AnimationInfo r03 = new AnimationInfo(r14, getName(), r3, r19.getWindowId(), r5, r6);
        if (r11 == false) goto L58;
        AnimatorSet r42 = new AnimatorSet();
        r42.play(r6);
        Animator r43 = r42;
    L59:
        r7.put(r43, r03);
        r3.mAnimators.add(r43);
        goto L61
    L58:
        r43 = r6;
        goto L59
    L60:
        r3 = this;
        goto L61
    L30:
        if (r13.length <= 0) goto L50;
        r5 = new TransitionValues(r02);
        TransitionValues r24 = r21.mViewValues.get(r02);
        if (r24 == null) goto L37;
        int r10 = 0;
    L35:
        if (r10 >= r13.length) goto L37;
        Map<String, Object> r32 = r5.values;
        String[] r16 = r13;
        String r15 = r16[r10];
        r32.put(r15, r24.values.get(r15));
        r10 = r10 + 1;
        r13 = r16;
        r4 = r4;
    L37:
        Animator r17 = r4;
        int r18 = r7.size();
        int r25 = 0;
    L38:
        if (r25 >= r18) goto L49;
        AnimationInfo r33 = r7.get(r7.keyAt(r25));
        if (r33.mValues == null) goto L48;
        if (r33.mView != r02) goto L48;
        if (r33.mName.equals(getName()) == false) goto L48;
        if (r33.mValues.equals(r5) == false) goto L48;
        r2 = null;
    L48:
        r25 = r25 + 1;
        goto L38
    L49:
        r2 = r17;
        goto L51
    L53:
        r02 = r0.view;
        r5 = null;
        goto L52
    L15:
        if (r1.mTargetedTransitions.contains(r3) == true) goto L17;
        r1 = null;
        goto L17
    L11:
        if (r0.mTargetedTransitions.contains(r3) == true) goto L13;
        r0 = null;
        goto L13
    L63:
        if (r8.size() == 0) goto L68;
        int r102 = 0;
    L66:
        if (r102 >= r8.size()) goto L82;
        AnimationInfo r04 = r7.get(r3.mAnimators.get(r8.keyAt(r102)));
        r04.mAnimator.setStartDelay((r8.valueAt(r102) - LongCompanionObject.MAX_VALUE) + r04.mAnimator.getStartDelay());
        r102 = r102 + 1;
        goto L66
    L82:
        return;
    L68:
        return;
    L5:
        r11 = false;
        goto L6
    }

    public TransitionSeekController createSeekController() {
        SeekController r0 = new SeekController(this);
        this.mSeekController = r0;
        addListener(r0);
        return this.mSeekController;
    }

    public void end() {
        int r0 = this.mNumInstances - 1;
        this.mNumInstances = r0;
        if (r0 != 0) goto L26;
        notifyListeners(TransitionNotification.ON_END, false);
        int r02 = 0;
    L6:
        if (r02 >= this.mStartValues.mItemIdValues.size()) goto L11;
        View r3 = this.mStartValues.mItemIdValues.valueAt(r02);
        if (r3 == null) goto L10;
        r3.setHasTransientState(false);
    L10:
        r02 = r02 + 1;
        goto L6
    L11:
        int r03 = 0;
    L13:
        if (r03 >= this.mEndValues.mItemIdValues.size()) goto L18;
        View r32 = this.mEndValues.mItemIdValues.valueAt(r03);
        if (r32 == null) goto L17;
        r32.setHasTransientState(false);
    L17:
        r03 = r03 + 1;
        goto L13
    L18:
        this.mEnded = true;
        return;
    }

    public Transition excludeChildren(int r2, boolean r3) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, r2, r3);
        return this;
    }

    public Transition excludeChildren(View r2, boolean r3) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, r2, r3);
        return this;
    }

    public Transition excludeChildren(Class<?> r2, boolean r3) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, r2, r3);
        return this;
    }

    public Transition excludeTarget(int r2, boolean r3) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, r2, r3);
        return this;
    }

    public Transition excludeTarget(View r2, boolean r3) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, r2, r3);
        return this;
    }

    public Transition excludeTarget(Class<?> r2, boolean r3) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, r2, r3);
        return this;
    }

    public Transition excludeTarget(String r2, boolean r3) {
        this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, r2, r3);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void forceToEnd(ViewGroup r4) {
        ArrayMap<Animator, AnimationInfo> r3 = getRunningAnimators();
        int r0 = r3.size();
        if (r4 == null) goto L14;
        if (r0 == 0) goto L19;
        WindowId r42 = r4.getWindowId();
        ArrayMap r1 = new ArrayMap(r3);
        r3.clear();
        int r02 = r0 - 1;
    L7:
        if (r02 < 0) goto L20;
        AnimationInfo r32 = (AnimationInfo) r1.valueAt(r02);
        if (r32.mView == null) goto L13;
        if (r42.equals(r32.mWindowId) == false) goto L13;
        ((Animator) r1.keyAt(r02)).end();
    L13:
        r02 = r02 - 1;
        goto L7
    L20:
        return;
    L19:
        return;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Rect getEpicenter() {
        EpicenterCallback r0 = this.mEpicenterCallback;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.onGetEpicenter(this);
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public TransitionValues getMatchedTransitionValues(View r6, boolean r7) {
        TransitionSet r0 = this.mParent;
        if (r0 != null) goto L5;
        if (r7 == false) goto L8;
        ArrayList<TransitionValues> r02 = this.mStartValuesList;
    L10:
        if (r02 != null) goto L12;
        return null;
    L12:
        int r2 = r02.size();
        int r3 = 0;
    L13:
        if (r3 >= r2) goto L21;
        TransitionValues r4 = r02.get(r3);
        if (r4 == null) goto L16;
        if (r4.view == r6) goto L22;
        r3 = r3 + 1;
    L22:
        if (r3 < 0) goto L28;
        if (r7 == false) goto L25;
        ArrayList<TransitionValues> r5 = this.mEndValuesList;
    L27:
        return r5.get(r3);
    L25:
        r5 = this.mStartValuesList;
        goto L27
    L28:
        return null;
    L16:
        return null;
    L21:
        r3 = -1;
        goto L22
    L8:
        r02 = this.mEndValuesList;
        goto L10
    L5:
        return r0.getMatchedTransitionValues(r6, r7);
    }

    public String getName() {
        return this.mName;
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public TransitionPropagation getPropagation() {
        return null;
    }

    public final Transition getRootTransition() {
        TransitionSet r0 = this.mParent;
        if (r0 != null) goto L5;
        return this;
    L5:
        return r0.getRootTransition();
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class<?>> getTargetTypes() {
        return this.mTargetTypes;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public final long getTotalDurationMillis() {
        return this.mTotalDuration;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View r2, boolean r3) {
        TransitionSet r0 = this.mParent;
        if (r0 != null) goto L5;
        if (r3 == false) goto L8;
        TransitionValuesMaps r1 = this.mStartValues;
    L10:
        return r1.mViewValues.get(r2);
    L8:
        r1 = this.mEndValues;
        goto L10
    L5:
        return r0.getTransitionValues(r2, r3);
    }

    public boolean hasAnimators() {
        return !this.mCurrentAnimators.isEmpty();
    }

    public boolean isSeekingSupported() {
        return false;
    }

    public boolean isTransitionRequired(TransitionValues r6, TransitionValues r7) {
        if (r6 == null) goto L19;
        if (r7 == null) goto L19;
        String[] r5 = getTransitionProperties();
        if (r5 == null) goto L13;
        int r2 = r5.length;
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L19;
        if (isValueChanged(r6, r7, r5[r3]) == true) goto L11;
        r3 = r3 + 1;
        goto L8
    L11:
        return true;
    L13:
        Iterator<String> r52 = r6.values.keySet().iterator();
    L15:
        if (r52.hasNext() == false) goto L19;
        if (isValueChanged(r6, r7, r52.next()) == false) goto L15;
        return true;
    L19:
        return false;
    }

    public boolean isValidTarget(View r6) {
        int r0 = r6.getId();
        ArrayList<Integer> r1 = this.mTargetIdExcludes;
        if (r1 != null) goto L5;
    L7:
        ArrayList<View> r12 = this.mTargetExcludes;
        if (r12 != null) goto L10;
    L12:
        ArrayList<Class<?>> r13 = this.mTargetTypeExcludes;
        if (r13 == null) goto L21;
        int r14 = r13.size();
        int r3 = 0;
    L15:
        if (r3 >= r14) goto L21;
        if (this.mTargetTypeExcludes.get(r3).isInstance(r6) == true) goto L18;
        r3 = r3 + 1;
        goto L15
    L18:
        return false;
    L21:
        if (this.mTargetNameExcludes == null) goto L28;
        if (ViewCompat.getTransitionName(r6) == null) goto L28;
        if (this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(r6)) == false) goto L28;
        return false;
    L28:
        if (this.mTargetIds.size() != 0) goto L41;
        if (this.mTargets.size() != 0) goto L41;
        ArrayList<Class<?>> r15 = this.mTargetTypes;
        if (r15 != null) goto L34;
    L35:
        ArrayList<String> r16 = this.mTargetNames;
        if (r16 != null) goto L38;
    L39:
        return true;
    L38:
        if (r16.isEmpty() == false) goto L41;
    L34:
        if (r15.isEmpty() == true) goto L35;
    L41:
        if (this.mTargetIds.contains(Integer.valueOf(r0)) == false) goto L43;
    L60:
        return true;
    L43:
        if (this.mTargets.contains(r6) == true) goto L60;
        ArrayList<String> r02 = this.mTargetNames;
        if (r02 == null) goto L51;
        if (r02.contains(ViewCompat.getTransitionName(r6)) == false) goto L51;
        return true;
    L51:
        if (this.mTargetTypes == null) goto L59;
        int r03 = 0;
    L54:
        if (r03 >= this.mTargetTypes.size()) goto L59;
        if (this.mTargetTypes.get(r03).isInstance(r6) == true) goto L57;
        r03 = r03 + 1;
        goto L54
    L57:
        return true;
    L59:
        return false;
    L10:
        if (r12.contains(r6) == false) goto L12;
        return false;
    L5:
        if (r1.contains(Integer.valueOf(r0)) == false) goto L7;
        return false;
    }

    public void notifyListeners(TransitionNotification r1, boolean r2) {
        notifyFromTransition(this, r1, r2);
    }

    public void pause(View r5) {
        if (this.mEnded == true) goto L10;
        int r52 = this.mCurrentAnimators.size();
        Animator[] r0 = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        int r53 = r52 - 1;
    L5:
        if (r53 < 0) goto L7;
        Animator r2 = r0[r53];
        r0[r53] = null;
        r2.pause();
        r53 = r53 - 1;
        goto L5
    L7:
        this.mAnimatorCache = r0;
        notifyListeners(TransitionNotification.ON_PAUSE, false);
        this.mPaused = true;
        return;
    }

    public void playTransition(ViewGroup r11) {
        this.mStartValuesList = new ArrayList();
        this.mEndValuesList = new ArrayList();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        ArrayMap<Animator, AnimationInfo> r0 = getRunningAnimators();
        int r1 = r0.size();
        WindowId r2 = r11.getWindowId();
        int r12 = r1 - 1;
    L3:
        if (r12 < 0) goto L35;
        Animator r4 = r0.keyAt(r12);
        if (r4 == null) goto L34;
        AnimationInfo r5 = r0.get(r4);
        if (r5 == null) goto L34;
        if (r5.mView == null) goto L34;
        if (r2.equals(r5.mWindowId) == false) goto L34;
        TransitionValues r6 = r5.mValues;
        View r7 = r5.mView;
        TransitionValues r8 = getTransitionValues(r7, true);
        TransitionValues r9 = getMatchedTransitionValues(r7, true);
        if (r8 != null) goto L16;
        if (r9 != null) goto L16;
        r9 = this.mEndValues.mViewValues.get(r7);
    L16:
        if (r8 != null) goto L19;
        if (r9 == null) goto L34;
    L19:
        if (r5.mTransition.isTransitionRequired(r6, r9) == false) goto L34;
        Transition r52 = r5.mTransition;
        if (r52.getRootTransition().mSeekController == null) goto L28;
        r4.cancel();
        r52.mCurrentAnimators.remove(r4);
        r0.remove(r4);
        if (r52.mCurrentAnimators.size() != 0) goto L34;
        r52.notifyListeners(TransitionNotification.ON_CANCEL, false);
        if (r52.mEnded == true) goto L34;
        r52.mEnded = true;
        r52.notifyListeners(TransitionNotification.ON_END, false);
        goto L34
    L28:
        if (r4.isRunning() == false) goto L30;
    L33:
        r4.cancel();
        goto L34
    L30:
        if (r4.isStarted() == true) goto L33;
        r0.remove(r4);
    L34:
        r12 = r12 - 1;
        goto L3
    L35:
        createAnimators(r11, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        if (this.mSeekController != null) goto L40;
        runAnimators();
        return;
    L40:
        if (Build.VERSION.SDK_INT < 34) goto L55;
        prepareAnimatorsForSeeking();
        this.mSeekController.initPlayTime();
        this.mSeekController.ready();
        return;
    }

    public void prepareAnimatorsForSeeking() {
        ArrayMap<Animator, AnimationInfo> r0 = getRunningAnimators();
        this.mTotalDuration = 0;
        int r3 = 0;
    L3:
        int r4 = this.mAnimators.size();
        ArrayList<Animator> r5 = this.mAnimators;
        if (r3 >= r4) goto L19;
        Animator r42 = r5.get(r3);
        AnimationInfo r52 = r0.get(r42);
        if (r42 == null) goto L18;
        if (r52 == null) goto L18;
        if (getDuration() < 0) goto L12;
        r52.mAnimator.setDuration(getDuration());
    L12:
        if (getStartDelay() < 0) goto L15;
        r52.mAnimator.setStartDelay(getStartDelay() + r52.mAnimator.getStartDelay());
    L15:
        if (getInterpolator() == null) goto L17;
        r52.mAnimator.setInterpolator(getInterpolator());
    L17:
        this.mCurrentAnimators.add(r42);
        this.mTotalDuration = Math.max(this.mTotalDuration, Impl26.getTotalDuration(r42));
    L18:
        r3 = r3 + 1;
        goto L3
    L19:
        r5.clear();
    }

    public Transition removeListener(TransitionListener r2) {
        ArrayList<TransitionListener> r0 = this.mListeners;
        if (r0 != null) goto L6;
    L13:
        return this;
    L6:
        if (r0.remove(r2) == true) goto L11;
        Transition r02 = this.mCloneParent;
        if (r02 == null) goto L11;
        r02.removeListener(r2);
    L11:
        if (this.mListeners.size() != 0) goto L13;
        this.mListeners = null;
        goto L13
    }

    public Transition removeTarget(int r2) {
        if (r2 == 0) goto L4;
        this.mTargetIds.remove(Integer.valueOf(r2));
    L4:
        return this;
    }

    public Transition removeTarget(View r2) {
        this.mTargets.remove(r2);
        return this;
    }

    public Transition removeTarget(Class<?> r2) {
        ArrayList<Class<?>> r0 = this.mTargetTypes;
        if (r0 == null) goto L5;
        r0.remove(r2);
    L5:
        return this;
    }

    public Transition removeTarget(String r2) {
        ArrayList<String> r0 = this.mTargetNames;
        if (r0 == null) goto L5;
        r0.remove(r2);
    L5:
        return this;
    }

    public void resume(View r5) {
        if (this.mPaused == true) goto L5;
        return;
    L5:
        if (this.mEnded == true) goto L10;
        int r52 = this.mCurrentAnimators.size();
        Animator[] r1 = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        int r53 = r52 - 1;
    L7:
        if (r53 < 0) goto L9;
        Animator r2 = r1[r53];
        r1[r53] = null;
        r2.resume();
        r53 = r53 - 1;
        goto L7
    L9:
        this.mAnimatorCache = r1;
        notifyListeners(TransitionNotification.ON_RESUME, false);
    L10:
        this.mPaused = false;
    }

    public void runAnimators() {
        start();
        ArrayMap<Animator, AnimationInfo> r0 = getRunningAnimators();
        ArrayList<Animator> r1 = this.mAnimators;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L7;
        Animator r4 = r1.get(r3);
        r3 = r3 + 1;
        Animator r42 = r4;
        if (r0.containsKey(r42) == false) goto L3;
        start();
        runAnimator(r42, r0);
        goto L3
    L7:
        this.mAnimators.clear();
        end();
    }

    public void setCanRemoveViews(boolean r1) {
        this.mCanRemoveViews = r1;
    }

    public void setCurrentPlayTimeMillis(long r19, long r21) {
        long r3 = getTotalDurationMillis();
        int r6 = 0;
        if (r19 >= r21) goto L5;
        boolean r5 = true;
    L7:
        if (r21 >= 0) goto L11;
        if (r19 < 0) goto L11;
    L14:
        this.mEnded = false;
        notifyListeners(TransitionNotification.ON_START, r5);
    L15:
        int r11 = this.mCurrentAnimators.size();
        Animator[] r12 = (Animator[]) this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
    L16:
        if (r6 >= r11) goto L18;
        Animator r13 = r12[r6];
        r12[r6] = null;
        Impl26.setCurrentPlayTime(r13, Math.min(Math.max(0, r19), Impl26.getTotalDuration(r13)));
        r6 = r6 + 1;
        r3 = r3;
        goto L16
    L18:
        long r16 = r3;
        this.mAnimatorCache = r12;
        if (r19 <= r16) goto L23;
        if (r21 > r16) goto L23;
    L25:
        if (r19 <= r16) goto L27;
        this.mEnded = true;
    L27:
        notifyListeners(TransitionNotification.ON_END, r5);
        return;
    L23:
        if (r19 >= 0) goto L30;
        if (r21 >= 0) goto L25;
        return;
    L30:
        return;
    L11:
        if (r21 <= r3) goto L15;
        if (r19 > r3) goto L15;
    L5:
        r5 = false;
        goto L7
    }

    public Transition setDuration(long r1) {
        this.mDuration = r1;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback r1) {
        this.mEpicenterCallback = r1;
    }

    public Transition setInterpolator(TimeInterpolator r1) {
        this.mInterpolator = r1;
        return this;
    }

    public void setMatchOrder(int... r3) {
        if (r3 != null) goto L4;
    L20:
        this.mMatchOrder = DEFAULT_MATCH_ORDER;
        return;
    L4:
        if (r3.length == 0) goto L20;
        int r0 = 0;
    L8:
        if (r0 >= r3.length) goto L18;
        if (isValidMatch(r3[r0]) == false) goto L16;
        if (alreadyContains(r3, r0) == true) goto L14;
        r0 = r0 + 1;
        goto L8
    L14:
        f$$ExternalSyntheticBUOutline1.m("matches contains a duplicate value");
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("matches contains invalid value");
        return;
    L18:
        this.mMatchOrder = (int[]) r3.clone();
    }

    public void setPathMotion(PathMotion r1) {
        if (r1 != null) goto L5;
        this.mPathMotion = STRAIGHT_PATH_MOTION;
        return;
    L5:
        this.mPathMotion = r1;
    }

    public void setPropagation(TransitionPropagation r1) {
    }

    public Transition setStartDelay(long r1) {
        this.mStartDelay = r1;
        return this;
    }

    public void start() {
        if (this.mNumInstances != 0) goto L5;
        notifyListeners(TransitionNotification.ON_START, false);
        this.mEnded = false;
    L5:
        this.mNumInstances++;
    }

    public String toString() {
        return toString(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public String toString(String r8) {
        StringBuilder r0 = new StringBuilder(r8);
        r0.append(getClass().getSimpleName());
        r0.append("@");
        r0.append(Integer.toHexString(hashCode()));
        r0.append(": ");
        if (this.mDuration == (-1)) goto L6;
        r0.append("dur(");
        r0.append(this.mDuration);
        r0.append(") ");
    L6:
        if (this.mStartDelay == (-1)) goto L9;
        r0.append("dly(");
        r0.append(this.mStartDelay);
        r0.append(") ");
    L9:
        if (this.mInterpolator == null) goto L12;
        r0.append("interp(");
        r0.append(this.mInterpolator);
        r0.append(") ");
    L12:
        if (this.mTargetIds.size() <= 0) goto L14;
    L15:
        r0.append("tgts(");
        int r2 = 0;
        if (this.mTargetIds.size() <= 0) goto L24;
        int r82 = 0;
    L19:
        if (r82 >= this.mTargetIds.size()) goto L24;
        if (r82 <= 0) goto L22;
        r0.append(", ");
    L22:
        r0.append(this.mTargetIds.get(r82));
        r82 = r82 + 1;
    L24:
        if (this.mTargets.size() > 0) goto L26;
    L30:
        r0.append(")");
    L32:
        return r0.toString();
    L26:
        if (r2 >= this.mTargets.size()) goto L30;
        if (r2 <= 0) goto L29;
        r0.append(", ");
    L29:
        r0.append(this.mTargets.get(r2));
        r2 = r2 + 1;
        goto L26
    L14:
        if (this.mTargets.size() <= 0) goto L32;
        goto L15
    }
}
