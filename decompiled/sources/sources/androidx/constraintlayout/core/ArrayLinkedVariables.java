package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    private static float sEpsilon = 0.001f;
    private int[] mArrayIndices;
    private int[] mArrayNextIndices;
    private float[] mArrayValues;
    protected final Cache mCache;
    private SolverVariable mCandidate;
    int mCurrentSize;
    private boolean mDidFillOnce;
    private int mHead;
    private int mLast;
    private final ArrayRow mRow;
    private int mRowSize;

    static {
    }

    public ArrayLinkedVariables(ArrayRow r4, Cache r5) {
        this.mCurrentSize = 0;
        this.mRowSize = 8;
        this.mCandidate = null;
        this.mArrayIndices = new int[8];
        this.mArrayNextIndices = new int[8];
        this.mArrayValues = new float[8];
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mRow = r4;
        this.mCache = r5;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable r9, float r10, boolean r11) {
        float r0 = sEpsilon;
        if (r10 > (-r0)) goto L5;
    L7:
        int r02 = this.mHead;
        if (r02 != (-1)) goto L15;
        this.mHead = 0;
        this.mArrayValues[0] = r10;
        this.mArrayIndices[0] = r9.id;
        this.mArrayNextIndices[0] = -1;
        r9.usageInRowCount++;
        r9.addToRow(this.mRow);
        this.mCurrentSize++;
        if (this.mDidFillOnce == true) goto L81;
        int r92 = this.mLast + 1;
        this.mLast = r92;
        int[] r102 = this.mArrayIndices;
        if (r92 < r102.length) goto L82;
        this.mDidFillOnce = true;
        this.mLast = r102.length - 1;
        return;
    L82:
        return;
    L81:
        return;
    L15:
        int r4 = 0;
        int r5 = -1;
    L16:
        if (r02 == (-1)) goto L42;
        if (r4 >= this.mCurrentSize) goto L42;
        int r6 = this.mArrayIndices[r02];
        int r7 = r9.id;
        if (r6 == r7) goto L21;
        if (r6 >= r7) goto L41;
        r5 = r02;
    L41:
        r02 = this.mArrayNextIndices[r02];
        r4 = r4 + 1;
        goto L16
    L21:
        float[] r1 = this.mArrayValues;
        float r2 = r1[r02] + r10;
        float r103 = sEpsilon;
        if (r2 > (-r103)) goto L24;
    L26:
        r1[r02] = r2;
        if (r2 != 0.0f) goto L83;
        int r104 = this.mHead;
        int[] r12 = this.mArrayNextIndices;
        if (r02 != r104) goto L31;
        this.mHead = r12[r02];
    L32:
        if (r11 == false) goto L35;
        r9.removeFromRow(this.mRow);
    L35:
        if (this.mDidFillOnce == false) goto L37;
        this.mLast = r02;
    L37:
        r9.usageInRowCount--;
        this.mCurrentSize--;
        return;
    L31:
        r12[r5] = r12[r02];
        goto L32
    L83:
        return;
    L24:
        if (r2 >= r103) goto L26;
        r2 = 0.0f;
    L42:
        int r112 = this.mLast;
        int r03 = r112 + 1;
        if (this.mDidFillOnce == false) goto L48;
        int[] r04 = this.mArrayIndices;
        if (r04[r112] == (-1)) goto L49;
        r112 = r04.length;
    L49:
        int[] r05 = this.mArrayIndices;
        if (r112 >= r05.length) goto L52;
    L60:
        int[] r06 = this.mArrayIndices;
        if (r112 < r06.length) goto L63;
        r112 = r06.length;
        int r07 = this.mRowSize * 2;
        this.mRowSize = r07;
        this.mDidFillOnce = false;
        this.mLast = r112 - 1;
        this.mArrayValues = Arrays.copyOf(this.mArrayValues, r07);
        this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
        this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
    L63:
        this.mArrayIndices[r112] = r9.id;
        this.mArrayValues[r112] = r10;
        int[] r105 = this.mArrayNextIndices;
        if (r5 == (-1)) goto L66;
        r105[r112] = r105[r5];
        r105[r5] = r112;
    L67:
        r9.usageInRowCount++;
        r9.addToRow(this.mRow);
        this.mCurrentSize++;
        if (this.mDidFillOnce == true) goto L70;
        this.mLast++;
    L70:
        int r93 = this.mLast;
        int[] r106 = this.mArrayIndices;
        if (r93 < r106.length) goto L84;
        this.mDidFillOnce = true;
        this.mLast = r106.length - 1;
        return;
    L84:
        return;
    L66:
        r105[r112] = this.mHead;
        this.mHead = r112;
        goto L67
    L52:
        if (this.mCurrentSize >= r05.length) goto L60;
        int r08 = 0;
    L54:
        int[] r42 = this.mArrayIndices;
        if (r08 >= r42.length) goto L60;
        if (r42[r08] == (-1)) goto L58;
        r08 = r08 + 1;
        goto L54
    L58:
        r112 = r08;
        goto L60
    L48:
        r112 = r03;
        goto L49
    L5:
        if (r10 >= r0) goto L7;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void clear() {
        int r0 = this.mHead;
        int r2 = 0;
    L4:
        if (r0 == (-1)) goto L11;
        if (r2 >= this.mCurrentSize) goto L11;
        SolverVariable r3 = this.mCache.mIndexedVariables[this.mArrayIndices[r0]];
        if (r3 == null) goto L10;
        r3.removeFromRow(this.mRow);
    L10:
        r0 = this.mArrayNextIndices[r0];
        r2 = r2 + 1;
    L11:
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mCurrentSize = 0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable r7) {
        int r0 = this.mHead;
        if (r0 != (-1)) goto L5;
        return false;
    L5:
        int r3 = 0;
    L6:
        if (r0 == (-1)) goto L14;
        if (r3 >= this.mCurrentSize) goto L14;
        if (this.mArrayIndices[r0] == r7.id) goto L11;
        r0 = this.mArrayNextIndices[r0];
        r3 = r3 + 1;
        goto L6
    L11:
        return true;
    L14:
        return false;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void divideByAmount(float r5) {
        int r0 = this.mHead;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L8;
        if (r1 >= this.mCurrentSize) goto L11;
        float[] r2 = this.mArrayValues;
        r2[r0] = r2[r0] / r5;
        r0 = this.mArrayNextIndices[r0];
        r1 = r1 + 1;
        goto L4
    L11:
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float get(SolverVariable r5) {
        int r0 = this.mHead;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L12;
        if (r1 >= this.mCurrentSize) goto L17;
        if (this.mArrayIndices[r0] == r5.id) goto L10;
        r0 = this.mArrayNextIndices[r0];
        r1 = r1 + 1;
        goto L4
    L10:
        return this.mArrayValues[r0];
    L17:
        return 0.0f;
    L12:
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.mCurrentSize;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int r4) {
        int r0 = this.mHead;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L11;
        if (r1 >= this.mCurrentSize) goto L16;
        if (r1 == r4) goto L9;
        r0 = this.mArrayNextIndices[r0];
        r1 = r1 + 1;
        goto L4
    L9:
        return this.mCache.mIndexedVariables[this.mArrayIndices[r0]];
    L16:
        return null;
    L11:
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int r4) {
        int r0 = this.mHead;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L11;
        if (r1 >= this.mCurrentSize) goto L16;
        if (r1 == r4) goto L9;
        r0 = this.mArrayNextIndices[r0];
        r1 = r1 + 1;
        goto L4
    L9:
        return this.mArrayValues[r0];
    L16:
        return 0.0f;
    L11:
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int r0 = this.mHead;
        int r1 = 0;
    L4:
        if (r0 == (-1)) goto L8;
        if (r1 >= this.mCurrentSize) goto L11;
        float[] r2 = this.mArrayValues;
        r2[r0] = r2[r0] * (-1.0f);
        r0 = this.mArrayNextIndices[r0];
        r1 = r1 + 1;
        goto L4
    L11:
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final void put(SolverVariable r9, float r10) {
        if (r10 != 0.0f) goto L6;
        remove(r9, true);
        return;
    L6:
        int r0 = this.mHead;
        if (r0 != (-1)) goto L14;
        this.mHead = 0;
        this.mArrayValues[0] = r10;
        this.mArrayIndices[0] = r9.id;
        this.mArrayNextIndices[0] = -1;
        r9.usageInRowCount++;
        r9.addToRow(this.mRow);
        this.mCurrentSize++;
        if (this.mDidFillOnce == true) goto L67;
        int r92 = this.mLast + 1;
        this.mLast = r92;
        int[] r102 = this.mArrayIndices;
        if (r92 < r102.length) goto L68;
        this.mDidFillOnce = true;
        this.mLast = r102.length - 1;
        return;
    L68:
        return;
    L67:
        return;
    L14:
        int r4 = 0;
        int r5 = -1;
    L15:
        if (r0 == (-1)) goto L25;
        if (r4 >= this.mCurrentSize) goto L25;
        int r6 = this.mArrayIndices[r0];
        int r7 = r9.id;
        if (r6 == r7) goto L20;
        if (r6 >= r7) goto L24;
        r5 = r0;
    L24:
        r0 = this.mArrayNextIndices[r0];
        r4 = r4 + 1;
        goto L15
    L20:
        this.mArrayValues[r0] = r10;
        return;
    L25:
        int r02 = this.mLast;
        int r42 = r02 + 1;
        if (this.mDidFillOnce == false) goto L31;
        int[] r43 = this.mArrayIndices;
        if (r43[r02] == (-1)) goto L32;
        r02 = r43.length;
    L32:
        int[] r44 = this.mArrayIndices;
        if (r02 >= r44.length) goto L35;
    L43:
        int[] r45 = this.mArrayIndices;
        if (r02 < r45.length) goto L46;
        r02 = r45.length;
        int r46 = this.mRowSize * 2;
        this.mRowSize = r46;
        this.mDidFillOnce = false;
        this.mLast = r02 - 1;
        this.mArrayValues = Arrays.copyOf(this.mArrayValues, r46);
        this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
        this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
    L46:
        this.mArrayIndices[r02] = r9.id;
        this.mArrayValues[r02] = r10;
        int[] r103 = this.mArrayNextIndices;
        if (r5 == (-1)) goto L49;
        r103[r02] = r103[r5];
        r103[r5] = r02;
    L50:
        r9.usageInRowCount++;
        r9.addToRow(this.mRow);
        int r93 = this.mCurrentSize + 1;
        this.mCurrentSize = r93;
        if (this.mDidFillOnce == true) goto L53;
        this.mLast++;
    L53:
        int[] r104 = this.mArrayIndices;
        if (r93 < r104.length) goto L57;
        this.mDidFillOnce = true;
    L57:
        if (this.mLast < r104.length) goto L69;
        this.mDidFillOnce = true;
        this.mLast = r104.length - 1;
        return;
    L69:
        return;
    L49:
        r103[r02] = this.mHead;
        this.mHead = r02;
        goto L50
    L35:
        if (this.mCurrentSize >= r44.length) goto L43;
        int r47 = 0;
    L37:
        int[] r62 = this.mArrayIndices;
        if (r47 >= r62.length) goto L43;
        if (r62[r47] == (-1)) goto L41;
        r47 = r47 + 1;
        goto L37
    L41:
        r02 = r47;
        goto L43
    L31:
        r02 = r42;
        goto L32
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public final float remove(SolverVariable r9, boolean r10) {
        if (this.mCandidate != r9) goto L5;
        this.mCandidate = null;
    L5:
        int r0 = this.mHead;
        if (r0 != (-1)) goto L8;
        return 0.0f;
    L8:
        int r3 = 0;
        int r4 = -1;
    L9:
        if (r0 == (-1)) goto L26;
        if (r3 >= this.mCurrentSize) goto L26;
        if (this.mArrayIndices[r0] == r9.id) goto L14;
        r3 = r3 + 1;
        r4 = r0;
        r0 = this.mArrayNextIndices[r0];
        goto L9
    L14:
        int r1 = this.mHead;
        int[] r32 = this.mArrayNextIndices;
        if (r0 != r1) goto L17;
        this.mHead = r32[r0];
    L18:
        if (r10 == false) goto L20;
        r9.removeFromRow(this.mRow);
    L20:
        r9.usageInRowCount--;
        this.mCurrentSize--;
        this.mArrayIndices[r0] = -1;
        if (this.mDidFillOnce == false) goto L24;
        this.mLast = r0;
    L24:
        return this.mArrayValues[r0];
    L17:
        r32[r4] = r32[r0];
    L26:
        return 0.0f;
    }

    public String toString() {
        int r0 = this.mHead;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r2 = 0;
    L4:
        if (r0 == (-1)) goto L8;
        if (r2 >= this.mCurrentSize) goto L8;
        r1 = (r1.concat(" -> ") + this.mArrayValues[r0] + " : ") + this.mCache.mIndexedVariables[this.mArrayIndices[r0]];
        r0 = this.mArrayNextIndices[r0];
        r2 = r2 + 1;
    L8:
        return r1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow r6, boolean r7) {
        float r0 = get(r6.mVariable);
        remove(r6.mVariable, r7);
        ArrayRow.ArrayRowVariables r62 = r6.variables;
        int r1 = r62.getCurrentSize();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        SolverVariable r3 = r62.getVariable(r2);
        add(r3, r62.get(r3) * r0, r7);
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }
}
