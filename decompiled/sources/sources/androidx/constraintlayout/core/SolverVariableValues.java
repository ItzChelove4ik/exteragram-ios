package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static float sEpsilon = 0.001f;
    protected final Cache mCache;
    int mCount;
    private int mHashSize;
    int mHead;
    int[] mKeys;
    int[] mNext;
    int[] mNextKeys;
    private final int mNone;
    int[] mPrevious;
    private final ArrayRow mRow;
    private int mSize;
    float[] mValues;
    int[] mVariables;

    static {
    }

    public SolverVariableValues(ArrayRow r4, Cache r5) {
        this.mNone = -1;
        this.mSize = 16;
        this.mHashSize = 16;
        this.mKeys = new int[16];
        this.mNextKeys = new int[16];
        this.mVariables = new int[16];
        this.mValues = new float[16];
        this.mPrevious = new int[16];
        this.mNext = new int[16];
        this.mCount = 0;
        this.mHead = -1;
        this.mRow = r4;
        this.mCache = r5;
        clear();
    }

    private void addToHashMap(SolverVariable r4, int r5) {
        int r42 = r4.id % this.mHashSize;
        int[] r0 = this.mKeys;
        int r1 = r0[r42];
        if (r1 != (-1)) goto L5;
        r0[r42] = r5;
    L9:
        this.mNextKeys[r5] = -1;
        return;
    L5:
        int[] r43 = this.mNextKeys;
        int r02 = r43[r1];
        if (r02 == (-1)) goto L8;
        r1 = r02;
        goto L5
    L8:
        r43[r1] = r5;
        goto L9
    }

    private void addVariable(int r3, SolverVariable r4, float r5) {
        this.mVariables[r3] = r4.id;
        this.mValues[r3] = r5;
        this.mPrevious[r3] = -1;
        this.mNext[r3] = -1;
        r4.addToRow(this.mRow);
        r4.usageInRowCount++;
        this.mCount++;
    }

    private int findEmptySlot() {
        int r0 = 0;
    L4:
        if (r0 >= this.mSize) goto L9;
        if (this.mVariables[r0] == (-1)) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private void increaseSize() {
        int r0 = this.mSize * 2;
        this.mVariables = Arrays.copyOf(this.mVariables, r0);
        this.mValues = Arrays.copyOf(this.mValues, r0);
        this.mPrevious = Arrays.copyOf(this.mPrevious, r0);
        this.mNext = Arrays.copyOf(this.mNext, r0);
        this.mNextKeys = Arrays.copyOf(this.mNextKeys, r0);
        int r1 = this.mSize;
    L3:
        if (r1 >= r0) goto L5;
        this.mVariables[r1] = -1;
        this.mNextKeys[r1] = -1;
        r1 = r1 + 1;
        goto L3
    L5:
        this.mSize = r0;
    }

    private void insertVariable(int r4, SolverVariable r5, float r6) {
        int r0 = findEmptySlot();
        addVariable(r0, r5, r6);
        int[] r62 = this.mPrevious;
        if (r4 == (-1)) goto L5;
        r62[r0] = r4;
        int[] r63 = this.mNext;
        r63[r0] = r63[r4];
        r63[r4] = r0;
    L9:
        int r42 = this.mNext[r0];
        if (r42 == (-1)) goto L12;
        this.mPrevious[r42] = r0;
    L12:
        addToHashMap(r5, r0);
        return;
    L5:
        r62[r0] = -1;
        int r43 = this.mCount;
        int[] r64 = this.mNext;
        if (r43 <= 0) goto L8;
        r64[r0] = this.mHead;
        this.mHead = r0;
        goto L9
    L8:
        r64[r0] = -1;
        goto L9
    }

    private void removeFromHashMap(SolverVariable r6) {
        int r62 = r6.id;
        int r0 = r62 % this.mHashSize;
        int[] r1 = this.mKeys;
        int r2 = r1[r0];
        if (r2 != (-1)) goto L6;
        return;
    L6:
        if (this.mVariables[r2] != r62) goto L9;
        int[] r5 = this.mNextKeys;
        r1[r0] = r5[r2];
        r5[r2] = -1;
        return;
    L9:
        int[] r02 = this.mNextKeys;
        int r12 = r02[r2];
        if (r12 == (-1)) goto L14;
        if (this.mVariables[r12] == r62) goto L14;
        r2 = r12;
    L14:
        if (r12 != (-1)) goto L16;
        return;
    L16:
        if (this.mVariables[r12] != r62) goto L22;
        r02[r2] = r02[r12];
        r02[r12] = -1;
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable r5, float r6, boolean r7) {
        float r0 = sEpsilon;
        if (r6 > (-r0)) goto L5;
    L7:
        int r02 = indexOf(r5);
        if (r02 != (-1)) goto L11;
        put(r5, r6);
        return;
    L11:
        float[] r1 = this.mValues;
        float r2 = r1[r02] + r6;
        r1[r02] = r2;
        float r62 = sEpsilon;
        if (r2 > (-r62)) goto L14;
        return;
    L14:
        if (r2 >= r62) goto L18;
        r1[r02] = 0.0f;
        remove(r5, r7);
        return;
    L18:
        return;
    L5:
        if (r6 >= r0) goto L7;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void clear() {
        int r0 = this.mCount;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        SolverVariable r3 = getVariable(r2);
        if (r3 == null) goto L7;
        r3.removeFromRow(this.mRow);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        int r02 = 0;
    L10:
        if (r02 >= this.mSize) goto L12;
        this.mVariables[r02] = -1;
        this.mNextKeys[r02] = -1;
        r02 = r02 + 1;
        goto L10
    L12:
        int r03 = 0;
    L14:
        if (r03 >= this.mHashSize) goto L16;
        this.mKeys[r03] = -1;
        r03 = r03 + 1;
        goto L14
    L16:
        this.mCount = 0;
        this.mHead = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable r1) {
        if (indexOf(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void divideByAmount(float r6) {
        int r0 = this.mCount;
        int r1 = this.mHead;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        float[] r3 = this.mValues;
        r3[r1] = r3[r1] / r6;
        r1 = this.mNext[r1];
        if (r1 == (-1)) goto L11;
        r2 = r2 + 1;
        goto L3
    L11:
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float get(SolverVariable r2) {
        int r22 = indexOf(r2);
        if (r22 != (-1)) goto L5;
        return 0.0f;
    L5:
        return this.mValues[r22];
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.mCount;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int r7) {
        int r0 = this.mCount;
        if (r0 != 0) goto L5;
        return null;
    L5:
        int r2 = this.mHead;
        int r3 = 0;
    L6:
        if (r3 >= r0) goto L16;
        if (r3 != r7) goto L12;
        if (r2 == (-1)) goto L12;
        return this.mCache.mIndexedVariables[this.mVariables[r2]];
    L12:
        r2 = this.mNext[r2];
        if (r2 == (-1)) goto L16;
        r3 = r3 + 1;
    L16:
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int r5) {
        int r0 = this.mCount;
        int r1 = this.mHead;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        if (r2 == r5) goto L6;
        r1 = this.mNext[r1];
        if (r1 == (-1)) goto L16;
        r2 = r2 + 1;
        goto L3
    L16:
        return 0.0f;
    L6:
        return this.mValues[r1];
    L11:
        return 0.0f;
    }

    public int indexOf(SolverVariable r4) {
        if (this.mCount == 0) goto L22;
        if (r4 == null) goto L22;
        int r42 = r4.id;
        int r0 = r42 % this.mHashSize;
        int r02 = this.mKeys[r0];
        if (r02 != (-1)) goto L10;
        return -1;
    L10:
        if (this.mVariables[r02] != r42) goto L12;
        return r02;
    L12:
        r02 = this.mNextKeys[r02];
        if (r02 == (-1)) goto L17;
        if (this.mVariables[r02] != r42) goto L12;
    L17:
        if (r02 != (-1)) goto L20;
        return -1;
    L20:
        if (this.mVariables[r02] != r42) goto L22;
        return r02;
    L22:
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int r0 = this.mCount;
        int r1 = this.mHead;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        float[] r3 = this.mValues;
        r3[r1] = r3[r1] * (-1.0f);
        r1 = this.mNext[r1];
        if (r1 == (-1)) goto L11;
        r2 = r2 + 1;
        goto L3
    L11:
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void put(SolverVariable r8, float r9) {
        float r0 = sEpsilon;
        if (r9 > (-r0)) goto L5;
    L8:
        int r1 = 0;
        if (this.mCount != 0) goto L12;
        addVariable(0, r8, r9);
        addToHashMap(r8, 0);
        this.mHead = 0;
        return;
    L12:
        int r02 = indexOf(r8);
        if (r02 == (-1)) goto L17;
        this.mValues[r02] = r9;
        return;
    L17:
        if ((this.mCount + 1) < this.mSize) goto L19;
        increaseSize();
    L19:
        int r03 = this.mCount;
        int r2 = this.mHead;
        int r4 = -1;
    L20:
        if (r1 >= r03) goto L31;
        int r5 = this.mVariables[r2];
        int r6 = r8.id;
        if (r5 == r6) goto L23;
        if (r5 >= r6) goto L27;
        r4 = r2;
    L27:
        r2 = this.mNext[r2];
        if (r2 == (-1)) goto L31;
        r1 = r1 + 1;
        goto L20
    L23:
        this.mValues[r2] = r9;
        return;
    L31:
        insertVariable(r4, r8, r9);
        return;
    L5:
        if (r9 >= r0) goto L8;
        remove(r8, true);
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable r8, boolean r9) {
        int r0 = indexOf(r8);
        if (r0 != (-1)) goto L6;
        return 0.0f;
    L6:
        removeFromHashMap(r8);
        float r2 = this.mValues[r0];
        if (this.mHead != r0) goto L9;
        this.mHead = this.mNext[r0];
    L9:
        this.mVariables[r0] = -1;
        int[] r3 = this.mPrevious;
        int r4 = r3[r0];
        if (r4 == (-1)) goto L12;
        int[] r5 = this.mNext;
        r5[r4] = r5[r0];
    L12:
        int r42 = this.mNext[r0];
        if (r42 == (-1)) goto L15;
        r3[r42] = r3[r0];
    L15:
        this.mCount--;
        r8.usageInRowCount--;
        if (r9 == false) goto L18;
        r8.removeFromRow(this.mRow);
    L18:
        return r2;
    }

    public String toString() {
        String r0 = hashCode() + " { ";
        int r1 = this.mCount;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L18;
        SolverVariable r3 = getVariable(r2);
        if (r3 == null) goto L16;
        String r02 = r0 + r3 + " = " + getVariableValue(r2) + " ";
        int r32 = indexOf(r3);
        String r03 = r02.concat("[p: ");
        if (this.mPrevious[r32] == (-1)) goto L10;
        String r04 = r03 + this.mCache.mIndexedVariables[this.mVariables[this.mPrevious[r32]]];
    L11:
        String r05 = r04.concat(", n: ");
        if (this.mNext[r32] == (-1)) goto L14;
        String r06 = r05 + this.mCache.mIndexedVariables[this.mVariables[this.mNext[r32]]];
    L15:
        r0 = r06.concat("]");
        goto L16
    L14:
        r06 = r05.concat("none");
        goto L15
    L10:
        r04 = r03.concat("none");
    L16:
        r2 = r2 + 1;
        goto L3
    L18:
        return r0 + " }";
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow r8, boolean r9) {
        float r0 = get(r8.mVariable);
        remove(r8.mVariable, r9);
        SolverVariableValues r82 = (SolverVariableValues) r8.variables;
        int r1 = r82.getCurrentSize();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r1) goto L8;
        int r4 = r82.mVariables[r3];
        if (r4 == (-1)) goto L7;
        add(this.mCache.mIndexedVariables[r4], r82.mValues[r3] * r0, r9);
        r2 = r2 + 1;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r0;
    }
}
