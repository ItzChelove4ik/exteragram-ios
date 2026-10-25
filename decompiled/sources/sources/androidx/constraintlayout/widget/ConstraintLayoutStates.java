package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class ConstraintLayoutStates {
    private final ConstraintLayout mConstraintLayout;
    private SparseArray<ConstraintSet> mConstraintSetMap;
    int mCurrentConstraintNumber;
    int mCurrentStateId;
    ConstraintSet mDefaultConstraintSet;
    private SparseArray<State> mStateList;

    public static class State {
        int mConstraintID;
        ConstraintSet mConstraintSet;
        int mId;
        ArrayList<Variant> mVariants;

        public State(Context r6, XmlPullParser r7) {
            this.mVariants = new ArrayList();
            this.mConstraintID = -1;
            TypedArray r72 = r6.obtainStyledAttributes(Xml.asAttributeSet(r7), R$styleable.State);
            int r0 = r72.getIndexCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L13;
            int r2 = r72.getIndex(r1);
            if (r2 != R$styleable.State_android_id) goto L8;
            this.mId = r72.getResourceId(r2, this.mId);
        L12:
            r1 = r1 + 1;
            goto L3
        L8:
            if (r2 != R$styleable.State_constraints) goto L12;
            this.mConstraintID = r72.getResourceId(r2, this.mConstraintID);
            String r22 = r6.getResources().getResourceTypeName(this.mConstraintID);
            r6.getResources().getResourceName(this.mConstraintID);
            if ("layout".equals(r22) == false) goto L12;
            ConstraintSet r23 = new ConstraintSet();
            this.mConstraintSet = r23;
            r23.clone(r6, this.mConstraintID);
            goto L12
        L13:
            r72.recycle();
        }

        public void add(Variant r1) {
            this.mVariants.add(r1);
        }

        public int findMatch(float r3, float r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.mVariants.size()) goto L9;
            if (this.mVariants.get(r0).match(r3, r4) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return -1;
        }
    }

    public static class Variant {
        int mConstraintID;
        ConstraintSet mConstraintSet;
        float mMaxHeight;
        float mMaxWidth;
        float mMinHeight;
        float mMinWidth;

        public Variant(Context r6, XmlPullParser r7) {
            this.mMinWidth = Float.NaN;
            this.mMinHeight = Float.NaN;
            this.mMaxWidth = Float.NaN;
            this.mMaxHeight = Float.NaN;
            this.mConstraintID = -1;
            TypedArray r72 = r6.obtainStyledAttributes(Xml.asAttributeSet(r7), R$styleable.Variant);
            int r0 = r72.getIndexCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L23;
            int r2 = r72.getIndex(r1);
            if (r2 != R$styleable.Variant_constraints) goto L10;
            this.mConstraintID = r72.getResourceId(r2, this.mConstraintID);
            String r22 = r6.getResources().getResourceTypeName(this.mConstraintID);
            r6.getResources().getResourceName(this.mConstraintID);
            if ("layout".equals(r22) == false) goto L22;
            ConstraintSet r23 = new ConstraintSet();
            this.mConstraintSet = r23;
            r23.clone(r6, this.mConstraintID);
        L22:
            r1 = r1 + 1;
            goto L3
        L10:
            if (r2 != R$styleable.Variant_region_heightLessThan) goto L13;
            this.mMaxHeight = r72.getDimension(r2, this.mMaxHeight);
            goto L22
        L13:
            if (r2 != R$styleable.Variant_region_heightMoreThan) goto L16;
            this.mMinHeight = r72.getDimension(r2, this.mMinHeight);
            goto L22
        L16:
            if (r2 != R$styleable.Variant_region_widthLessThan) goto L19;
            this.mMaxWidth = r72.getDimension(r2, this.mMaxWidth);
            goto L22
        L19:
            if (r2 != R$styleable.Variant_region_widthMoreThan) goto L21;
            this.mMinWidth = r72.getDimension(r2, this.mMinWidth);
            goto L22
        L21:
            Log.v("ConstraintLayoutStates", "Unknown tag");
            goto L22
        L23:
            r72.recycle();
        }

        public boolean match(float r3, float r4) {
            if (Float.isNaN(this.mMinWidth) == true) goto L8;
            if (r3 >= this.mMinWidth) goto L8;
            return false;
        L8:
            if (Float.isNaN(this.mMinHeight) == true) goto L13;
            if (r4 >= this.mMinHeight) goto L13;
            return false;
        L13:
            if (Float.isNaN(this.mMaxWidth) == true) goto L18;
            if (r3 <= this.mMaxWidth) goto L18;
            return false;
        L18:
            if (Float.isNaN(this.mMaxHeight) == false) goto L20;
            return true;
        L20:
            if (r4 <= this.mMaxHeight) goto L24;
            return false;
        L24:
            return true;
        }
    }

    public ConstraintLayoutStates(Context r2, ConstraintLayout r3, int r4) {
        this.mCurrentStateId = -1;
        this.mCurrentConstraintNumber = -1;
        this.mStateList = new SparseArray();
        this.mConstraintSetMap = new SparseArray();
        this.mConstraintLayout = r3;
        load(r2, r4);
    }

    private void load(Context r7, int r8) {
        XmlResourceParser r2 = r7.getResources().getXml(r8);
        int r3 = r2.getEventType();     // Catch: IOException -> L17 XmlPullParserException -> L19
        State r4 = null;
    L5:
        if (r3 == 1) goto L50;
        if (r3 != 2) goto L30;
        String r32 = r2.getName();     // Catch: IOException -> L17 XmlPullParserException -> L19
        switch(r32.hashCode()) {
            case -1349929691: goto L28;
            case 80204913: goto L25;
            case 1382829617: goto L23;
            case 1657696882: goto L21;
            case 1901439077: goto L13;
            default: goto L30;
        };     // Catch: IOException -> L17 XmlPullParserException -> L19
    L21:
        String r5 = "layoutDescription";
    L22:
        r32.equals(r5);     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L30
    L23:
        r5 = "StateSet";
        goto L22
    L13:
        if (r32.equals("Variant") == false) goto L30;
        Variant r33 = new Variant(r7, r2);     // Catch: IOException -> L17 XmlPullParserException -> L19
        if (r4 == null) goto L30;
        r4.add(r33);     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L30
    L25:
        if (r32.equals("State") == false) goto L30;
        State r34 = new State(r7, r2);     // Catch: IOException -> L17 XmlPullParserException -> L19
        this.mStateList.put(r34.mId, r34);     // Catch: IOException -> L17 XmlPullParserException -> L19
        r4 = r34;
        goto L30
    L28:
        if (r32.equals("ConstraintSet") == false) goto L30;
        parseConstraintSet(r7, r2);     // Catch: IOException -> L17 XmlPullParserException -> L19
    L30:
        r3 = r2.next();     // Catch: IOException -> L17 XmlPullParserException -> L19
    L50:
        return;
    L17:
        e = move-exception;
        Log.e("ConstraintLayoutStates", "Error parsing resource: " + r8, e);
        return;
    L19:
        e = move-exception;
        Log.e("ConstraintLayoutStates", "Error parsing resource: " + r8, e);
    }

    private void parseConstraintSet(Context r9, XmlPullParser r10) {
        ConstraintSet r0 = new ConstraintSet();
        int r1 = r10.getAttributeCount();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L22;
        String r3 = r10.getAttributeName(r2);
        String r4 = r10.getAttributeValue(r2);
        if (r3 == null) goto L21;
        if (r4 == null) goto L21;
        if ("id".equals(r3) == false) goto L21;
        if (r4.contains("/") == false) goto L13;
        int r12 = r9.getResources().getIdentifier(r4.substring(r4.indexOf(47) + 1), "id", r9.getPackageName());
    L14:
        if (r12 == (-1)) goto L16;
    L19:
        r0.load(r9, r10);
        this.mConstraintSetMap.put(r12, r0);
        return;
    L16:
        if (r4.length() <= 1) goto L18;
        r12 = Integer.parseInt(r4.substring(1));
        goto L19
    L18:
        Log.e("ConstraintLayoutStates", "error in parsing id");
        goto L19
    L13:
        r12 = -1;
    L21:
        r2 = r2 + 1;
        goto L3
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener r1) {
    }

    public void updateConstraints(int r5, float r6, float r7) {
        int r0 = this.mCurrentStateId;
        if (r0 != r5) goto L26;
        SparseArray<State> r2 = this.mStateList;
        if (r5 != (-1)) goto L7;
        State r52 = r2.valueAt(0);
    L8:
        int r02 = this.mCurrentConstraintNumber;
        if (r02 != (-1)) goto L11;
    L13:
        int r62 = r52.findMatch(r6, r7);
        if (this.mCurrentConstraintNumber == r62) goto L39;
        if (r62 != (-1)) goto L18;
        ConstraintSet r72 = this.mDefaultConstraintSet;
    L19:
        if (r62 == (-1)) goto L22;
        int r53 = r52.mVariants.get(r62).mConstraintID;
    L22:
        if (r72 != null) goto L24;
        return;
    L24:
        this.mCurrentConstraintNumber = r62;
        r72.applyTo(this.mConstraintLayout);
        return;
    L18:
        r72 = r52.mVariants.get(r62).mConstraintSet;
        goto L19
    L39:
        return;
    L11:
        if (r52.mVariants.get(r02).match(r6, r7) == false) goto L13;
        return;
    L7:
        r52 = r2.get(r0);
        goto L8
    L26:
        this.mCurrentStateId = r5;
        State r03 = this.mStateList.get(r5);
        int r22 = r03.findMatch(r6, r7);
        if (r22 != (-1)) goto L29;
        ConstraintSet r3 = r03.mConstraintSet;
    L30:
        if (r22 == (-1)) goto L33;
        int r04 = r03.mVariants.get(r22).mConstraintID;
    L33:
        if (r3 != null) goto L36;
        Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + r5 + ", dim =" + r6 + ", " + r7);
        return;
    L36:
        this.mCurrentConstraintNumber = r22;
        r3.applyTo(this.mConstraintLayout);
        return;
    L29:
        r3 = r03.mVariants.get(r22).mConstraintSet;
        goto L30
    }
}
