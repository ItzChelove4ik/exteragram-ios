package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class ConstraintHelper extends View {
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds;
    protected HashMap<Integer, String> mMap;
    protected String mReferenceIds;
    protected String mReferenceTags;
    protected boolean mUseViewMeasure;
    private View[] mViews;
    protected Context myContext;

    public ConstraintHelper(Context r3) {
        super(r3);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = r3;
        init(null);
    }

    public ConstraintHelper(Context r2, AttributeSet r3) {
        super(r2, r3);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = r2;
        init(r3);
    }

    public ConstraintHelper(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap();
        this.myContext = r1;
        init(r2);
    }

    private void addID(String r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.length() != 0) goto L7;
        return;
    L7:
        if (this.myContext == null) goto L17;
        String r42 = r4.trim();
        int r0 = findId(r42);
        if (r0 == 0) goto L13;
        this.mMap.put(Integer.valueOf(r0), r42);
        addRscID(r0);
        return;
    L13:
        Log.w("ConstraintHelper", "Could not find id of \"" + r42 + "\"");
        return;
    }

    private void addRscID(int r4) {
        if (r4 != getId()) goto L5;
        return;
    L5:
        int r0 = this.mCount + 1;
        int[] r1 = this.mIds;
        if (r0 <= r1.length) goto L8;
        this.mIds = Arrays.copyOf(r1, r1.length * 2);
    L8:
        int[] r02 = this.mIds;
        int r12 = this.mCount;
        r02[r12] = r4;
        this.mCount = r12 + 1;
    }

    private void addTag(String r8) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.length() != 0) goto L7;
        return;
    L7:
        if (this.myContext == null) goto L35;
        String r82 = r8.trim();
        if ((getParent() instanceof ConstraintLayout) == false) goto L12;
        ConstraintLayout r0 = (ConstraintLayout) getParent();
    L14:
        if (r0 != null) goto L17;
        Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
        return;
    L17:
        int r2 = r0.getChildCount();
        int r3 = 0;
    L18:
        if (r3 >= r2) goto L36;
        View r4 = r0.getChildAt(r3);
        ViewGroup.LayoutParams r5 = r4.getLayoutParams();
        if ((r5 instanceof ConstraintLayout.LayoutParams) == false) goto L27;
        if (r82.equals(((ConstraintLayout.LayoutParams) r5).constraintTag) == false) goto L27;
        if (r4.getId() != (-1)) goto L26;
        Log.w("ConstraintHelper", "to use ConstraintTag view " + r4.getClass().getSimpleName() + " must have an ID");
        goto L27
    L26:
        addRscID(r4.getId());
    L27:
        r3 = r3 + 1;
        goto L18
    L36:
        return;
    L12:
        r0 = null;
        goto L14
    }

    private int findId(ConstraintLayout r7, String r8) {
        if (r8 == null) goto L21;
        if (r7 == null) goto L21;
        Resources r6 = this.myContext.getResources();
        if (r6 != null) goto L9;
        return 0;
    L9:
        int r1 = r7.getChildCount();
        int r2 = 0;
    L10:
        if (r2 >= r1) goto L21;
        View r3 = r7.getChildAt(r2);
        if (r3.getId() == (-1)) goto L20;
        String r4 = r6.getResourceEntryName(r3.getId());     // Catch: Resources.NotFoundException -> L15
    L17:
        if (r8.equals(r4) == false) goto L20;
        return r3.getId();
    L15:
        r4 = null;
    L20:
        r2 = r2 + 1;
    L21:
        return 0;
    }

    private int findId(String r6) {
        if ((getParent() instanceof ConstraintLayout) == false) goto L5;
        ConstraintLayout r0 = (ConstraintLayout) getParent();
    L6:
        int r3 = 0;
        if (isInEditMode() == false) goto L12;
        if (r0 == null) goto L12;
        Object r2 = r0.getDesignInformation(0, r6);
        if ((r2 instanceof Integer) == false) goto L12;
        r3 = ((Integer) r2).intValue();
    L12:
        if (r3 != 0) goto L15;
        if (r0 == null) goto L15;
        r3 = findId(r0, r6);
    L15:
        if (r3 == 0) goto L21;
    L17:
        if (r3 == 0) goto L19;
        return r3;
    L19:
        return this.myContext.getResources().getIdentifier(r6, "id", this.myContext.getPackageName());
    L21:
        r3 = R$id.class.getField(r6).getInt(null);     // Catch: Exception -> L20
        goto L17
    L5:
        r0 = null;
        goto L6
    }

    public void applyLayoutFeatures() {
        ViewParent r0 = getParent();
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof ConstraintLayout) == false) goto L9;
        applyLayoutFeatures((ConstraintLayout) r0);
        return;
    }

    public void applyLayoutFeatures(ConstraintLayout r6) {
        int r0 = getVisibility();
        float r1 = getElevation();
        int r2 = 0;
    L4:
        if (r2 >= this.mCount) goto L11;
        View r3 = r6.getViewById(this.mIds[r2]);
        if (r3 == null) goto L10;
        r3.setVisibility(r0);
        if (r1 <= 0.0f) goto L10;
        r3.setTranslationZ(r3.getTranslationZ() + r1);
    L10:
        r2 = r2 + 1;
        goto L4
    }

    public void applyLayoutFeaturesInConstraintSet(ConstraintLayout r1) {
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void init(AttributeSet r5) {
        if (r5 == null) goto L18;
        TypedArray r52 = getContext().obtainStyledAttributes(r5, R$styleable.ConstraintLayout_Layout);
        int r0 = r52.getIndexCount();
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L12;
        int r2 = r52.getIndex(r1);
        if (r2 != R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) goto L9;
        String r22 = r52.getString(r2);
        this.mReferenceIds = r22;
        setIds(r22);
    L11:
        r1 = r1 + 1;
        goto L4
    L9:
        if (r2 != R$styleable.ConstraintLayout_Layout_constraint_referenced_tags) goto L11;
        String r23 = r52.getString(r2);
        this.mReferenceTags = r23;
        setReferenceTags(r23);
        goto L11
    L12:
        r52.recycle();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String r0 = this.mReferenceIds;
        if (r0 == null) goto L5;
        setIds(r0);
    L5:
        String r02 = this.mReferenceTags;
        if (r02 == null) goto L9;
        setReferenceTags(r02);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.mUseViewMeasure == false) goto L6;
        super.onMeasure(r2, r3);
        return;
    L6:
        setMeasuredDimension(0, 0);
    }

    public abstract void resolveRtl(ConstraintWidget r1, boolean r2);

    public void setIds(String r4) {
        this.mReferenceIds = r4;
        if (r4 != null) goto L5;
        return;
    L5:
        int r0 = 0;
        this.mCount = 0;
    L6:
        int r1 = r4.indexOf(44, r0);
        if (r1 == (-1)) goto L8;
        addID(r4.substring(r0, r1));
        r0 = r1 + 1;
        goto L6
    L8:
        addID(r4.substring(r0));
    }

    public void setReferenceTags(String r4) {
        this.mReferenceTags = r4;
        if (r4 != null) goto L5;
        return;
    L5:
        int r0 = 0;
        this.mCount = 0;
    L6:
        int r1 = r4.indexOf(44, r0);
        if (r1 == (-1)) goto L8;
        addTag(r4.substring(r0, r1));
        r0 = r1 + 1;
        goto L6
    L8:
        addTag(r4.substring(r0));
    }

    public void setReferencedIds(int[] r3) {
        this.mReferenceIds = null;
        int r0 = 0;
        this.mCount = 0;
    L4:
        if (r0 >= r3.length) goto L6;
        addRscID(r3[r0]);
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.view.View
    public void setTag(int r1, Object r2) {
        super.setTag(r1, r2);
        if (r2 == null) goto L5;
        return;
    L5:
        if (this.mReferenceIds != null) goto L9;
        addRscID(r1);
        return;
    }

    public void updatePostLayout(ConstraintLayout r1) {
    }

    public void updatePostMeasure(ConstraintLayout r1) {
    }

    public void updatePreDraw(ConstraintLayout r1) {
    }

    public void updatePreLayout(ConstraintLayout r6) {
        if (isInEditMode() == false) goto L5;
        setIds(this.mReferenceIds);
    L5:
        Helper r0 = this.mHelperWidget;
        if (r0 != null) goto L8;
        return;
    L8:
        r0.removeAllIds();
        int r02 = 0;
    L10:
        if (r02 >= this.mCount) goto L19;
        int r1 = this.mIds[r02];
        View r2 = r6.getViewById(r1);
        if (r2 != null) goto L16;
        String r12 = this.mMap.get(Integer.valueOf(r1));
        int r3 = findId(r6, r12);
        if (r3 == 0) goto L16;
        this.mIds[r02] = r3;
        this.mMap.put(Integer.valueOf(r3), r12);
        r2 = r6.getViewById(r3);
    L16:
        if (r2 == null) goto L18;
        this.mHelperWidget.add(r6.getViewWidget(r2));
    L18:
        r02 = r02 + 1;
        goto L10
    L19:
        this.mHelperWidget.updateConstraints(r6.mLayoutWidget);
    }

    public void validateParams() {
        if (this.mHelperWidget == null) goto L10;
        ViewGroup.LayoutParams r0 = getLayoutParams();
        if ((r0 instanceof ConstraintLayout.LayoutParams) == false) goto L9;
        ConstraintWidget r2 = (ConstraintWidget) this.mHelperWidget;
        ((ConstraintLayout.LayoutParams) r0).mWidget = r2;
        return;
    L9:
        return;
    }
}
