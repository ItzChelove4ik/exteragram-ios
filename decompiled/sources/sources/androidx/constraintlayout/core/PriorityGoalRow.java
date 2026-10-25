package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class PriorityGoalRow extends ArrayRow {
    GoalVariableAccessor mAccessor;
    private SolverVariable[] mArrayGoals;
    Cache mCache;
    private int mNumGoals;
    private SolverVariable[] mSortArray;
    private int mTableSize;

    /* renamed from: androidx.constraintlayout.core.PriorityGoalRow$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<SolverVariable> {
        final /* synthetic */ PriorityGoalRow this$0;

        public AnonymousClass1(PriorityGoalRow r1) {
            this.this$0 = r1;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(SolverVariable r1, SolverVariable r2) {
            return r1.id - r2.id;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(SolverVariable r1, SolverVariable r2) {
            return compare2(r1, r2);
        }
    }

    public class GoalVariableAccessor {
        PriorityGoalRow mRow;
        SolverVariable mVariable;
        final /* synthetic */ PriorityGoalRow this$0;

        public GoalVariableAccessor(PriorityGoalRow r1, PriorityGoalRow r2) {
            this.this$0 = r1;
            this.mRow = r2;
        }

        public boolean addToGoal(SolverVariable r10, float r11) {
            int r4 = 0;
            boolean r5 = true;
            if (this.mVariable.inGoal == false) goto L14;
            int r0 = 0;
        L5:
            if (r0 >= 9) goto L11;
            float[] r6 = this.mVariable.mGoalStrengthVector;
            float r7 = r6[r0] + (r10.mGoalStrengthVector[r0] * r11);
            r6[r0] = r7;
            if (Math.abs(r7) >= 1.0E-4f) goto L9;
            this.mVariable.mGoalStrengthVector[r0] = 0.0f;
        L10:
            r0 = r0 + 1;
            goto L5
        L9:
            r5 = false;
            goto L10
        L11:
            if (r5 == false) goto L13;
            PriorityGoalRow.access$000(this.this$0, this.mVariable);
        L13:
            return false;
        L14:
            if (r4 >= 9) goto L23;
            float r02 = r10.mGoalStrengthVector[r4];
            if (r02 == 0.0f) goto L21;
            float r03 = r02 * r11;
            if (Math.abs(r03) >= 1.0E-4f) goto L20;
            r03 = 0.0f;
        L20:
            this.mVariable.mGoalStrengthVector[r4] = r03;
        L22:
            r4 = r4 + 1;
            goto L14
        L21:
            this.mVariable.mGoalStrengthVector[r4] = 0.0f;
            goto L22
        L23:
            return true;
        }

        public void init(SolverVariable r1) {
            this.mVariable = r1;
        }

        public final boolean isNegative() {
            int r0 = 8;
        L4:
            if (r0 < 0) goto L13;
            float r2 = this.mVariable.mGoalStrengthVector[r0];
            if (r2 > 0.0f) goto L7;
            if (r2 < 0.0f) goto L10;
            r0 = r0 - 1;
            goto L4
        L10:
            return true;
        L7:
            return false;
        L13:
            return false;
        }

        public final boolean isSmallerThan(SolverVariable r6) {
            int r0 = 8;
        L4:
            if (r0 < 0) goto L12;
            float r2 = r6.mGoalStrengthVector[r0];
            float r3 = this.mVariable.mGoalStrengthVector[r0];
            if (r3 != r2) goto L9;
            r0 = r0 - 1;
            goto L4
        L9:
            if (r3 >= r2) goto L12;
            return true;
        L12:
            return false;
        }

        public void reset() {
            Arrays.fill(this.mVariable.mGoalStrengthVector, 0.0f);
        }

        public String toString() {
            String r1 = "[ ";
            if (this.mVariable == null) goto L9;
            int r0 = 0;
        L6:
            if (r0 >= 9) goto L9;
            r1 = r1 + this.mVariable.mGoalStrengthVector[r0] + " ";
            r0 = r0 + 1;
        L9:
            return r1 + "] " + this.mVariable;
        }
    }

    public PriorityGoalRow(Cache r3) {
        super(r3);
        this.mTableSize = 128;
        this.mArrayGoals = new SolverVariable[128];
        this.mSortArray = new SolverVariable[128];
        this.mNumGoals = 0;
        this.mAccessor = new GoalVariableAccessor(this, this);
        this.mCache = r3;
    }

    public static /* synthetic */ void access$000(PriorityGoalRow r0, SolverVariable r1) {
        r0.removeGoal(r1);
    }

    private void addToGoal(SolverVariable r6) {
        int r0 = this.mNumGoals + 1;
        SolverVariable[] r2 = this.mArrayGoals;
        if (r0 <= r2.length) goto L5;
        SolverVariable[] r02 = (SolverVariable[]) Arrays.copyOf(r2, r2.length * 2);
        this.mArrayGoals = r02;
        this.mSortArray = (SolverVariable[]) Arrays.copyOf(r02, r02.length * 2);
    L5:
        SolverVariable[] r03 = this.mArrayGoals;
        int r22 = this.mNumGoals;
        r03[r22] = r6;
        int r3 = r22 + 1;
        this.mNumGoals = r3;
        if (r3 > 1) goto L8;
    L17:
        r6.inGoal = true;
        r6.addToRow(this);
        return;
    L8:
        if (r03[r22].id <= r6.id) goto L17;
        int r04 = 0;
        int r23 = 0;
    L10:
        int r32 = this.mNumGoals;
        SolverVariable[] r4 = this.mSortArray;
        if (r23 >= r32) goto L13;
        r4[r23] = this.mArrayGoals[r23];
        r23 = r23 + 1;
        goto L10
    L13:
        Arrays.sort(r4, 0, r32, new AnonymousClass1(this));
    L15:
        if (r04 >= this.mNumGoals) goto L17;
        this.mArrayGoals[r04] = this.mSortArray[r04];
        r04 = r04 + 1;
        goto L15
    }

    private void removeGoal(SolverVariable r6) {
        int r1 = 0;
    L4:
        if (r1 >= this.mNumGoals) goto L13;
        if (this.mArrayGoals[r1] == r6) goto L7;
        r1 = r1 + 1;
    L7:
        int r2 = this.mNumGoals;
        if (r1 >= (r2 - 1)) goto L10;
        SolverVariable[] r22 = this.mArrayGoals;
        int r3 = r1 + 1;
        r22[r1] = r22[r3];
        r1 = r3;
        goto L7
    L10:
        this.mNumGoals = r2 - 1;
        r6.inGoal = false;
        return;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void addError(SolverVariable r4) {
        this.mAccessor.init(r4);
        this.mAccessor.reset();
        r4.mGoalStrengthVector[r4.strength] = 1.0f;
        addToGoal(r4);
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.mNumGoals = 0;
        this.mConstantValue = 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem r5, boolean[] r6) {
        int r0 = 0;
        int r1 = -1;
    L4:
        if (r0 >= this.mNumGoals) goto L17;
        SolverVariable r2 = this.mArrayGoals[r0];
        if (r6[r2.id] == true) goto L16;
        this.mAccessor.init(r2);
        GoalVariableAccessor r22 = this.mAccessor;
        if (r1 != (-1)) goto L14;
        if (r22.isNegative() == false) goto L16;
    L15:
        r1 = r0;
        goto L16
    L14:
        if (r22.isSmallerThan(this.mArrayGoals[r1]) == true) goto L15;
    L16:
        r0 = r0 + 1;
        goto L4
    L17:
        if (r1 != (-1)) goto L21;
        return null;
    L21:
        return this.mArrayGoals[r1];
    }

    @Override // androidx.constraintlayout.core.ArrayRow, androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        if (this.mNumGoals != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public String toString() {
        String r0 = " goal -> (" + this.mConstantValue + ") : ";
        int r1 = 0;
    L4:
        if (r1 >= this.mNumGoals) goto L6;
        this.mAccessor.init(this.mArrayGoals[r1]);
        r0 = r0 + this.mAccessor + " ";
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public void updateFromRow(LinearSystem r6, ArrayRow r7, boolean r8) {
        SolverVariable r62 = r7.mVariable;
        if (r62 != null) goto L5;
        return;
    L5:
        ArrayRow.ArrayRowVariables r82 = r7.variables;
        int r0 = r82.getCurrentSize();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        SolverVariable r2 = r82.getVariable(r1);
        float r3 = r82.getVariableValue(r1);
        this.mAccessor.init(r2);
        if (this.mAccessor.addToGoal(r62, r3) == false) goto L10;
        addToGoal(r2);
    L10:
        this.mConstantValue += r7.mConstantValue * r3;
        r1 = r1 + 1;
        goto L6
    L11:
        removeGoal(r62);
    }
}
