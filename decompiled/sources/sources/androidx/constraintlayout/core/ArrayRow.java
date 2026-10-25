package androidx.constraintlayout.core;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* loaded from: classes3.dex */
public class ArrayRow implements LinearSystem.Row {
    float mConstantValue;
    boolean mIsSimpleDefinition;
    boolean mUsed;
    SolverVariable mVariable;
    ArrayList<SolverVariable> mVariablesToUpdate;
    public ArrayRowVariables variables;

    public interface ArrayRowVariables {
        void add(SolverVariable r1, float r2, boolean r3);

        void clear();

        boolean contains(SolverVariable r1);

        void divideByAmount(float r1);

        float get(SolverVariable r1);

        int getCurrentSize();

        SolverVariable getVariable(int r1);

        float getVariableValue(int r1);

        void invert();

        void put(SolverVariable r1, float r2);

        float remove(SolverVariable r1, boolean r2);

        float use(ArrayRow r1, boolean r2);
    }

    public ArrayRow() {
        this.mVariable = null;
        this.mConstantValue = 0.0f;
        this.mUsed = false;
        this.mVariablesToUpdate = new ArrayList();
        this.mIsSimpleDefinition = false;
    }

    public ArrayRow(Cache r3) {
        this.mVariable = null;
        this.mConstantValue = 0.0f;
        this.mUsed = false;
        this.mVariablesToUpdate = new ArrayList();
        this.mIsSimpleDefinition = false;
        this.variables = new ArrayLinkedVariables(this, r3);
    }

    private boolean isNew(SolverVariable r1, LinearSystem r2) {
        if (r1.usageInRowCount > 1) goto L5;
        return true;
    L5:
        return false;
    }

    private SolverVariable pickPivotInVariables(boolean[] r10, SolverVariable r11) {
        int r0 = this.variables.getCurrentSize();
        SolverVariable r2 = null;
        int r3 = 0;
        float r4 = 0.0f;
    L3:
        if (r3 >= r0) goto L19;
        float r5 = this.variables.getVariableValue(r3);
        if (r5 >= 0.0f) goto L18;
        SolverVariable r6 = this.variables.getVariable(r3);
        if (r10 != null) goto L9;
    L10:
        if (r6 == r11) goto L18;
        SolverVariable.Type r7 = r6.mType;
        if (r7 == SolverVariable.Type.SLACK) goto L16;
        if (r7 != SolverVariable.Type.ERROR) goto L18;
    L16:
        if (r5 >= r4) goto L18;
        r4 = r5;
        r2 = r6;
        goto L18
    L9:
        if (r10[r6.id] == false) goto L10;
    L18:
        r3 = r3 + 1;
        goto L3
    L19:
        return r2;
    }

    public ArrayRow addError(LinearSystem r4, int r5) {
        this.variables.put(r4.createErrorVariable(r5, "ep"), 1.0f);
        this.variables.put(r4.createErrorVariable(r5, "em"), -1.0f);
        return this;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void addError(SolverVariable r4) {
        int r0 = r4.strength;
        float r2 = 1.0f;
        if (r0 != 1) goto L6;
    L17:
        this.variables.put(r4, r2);
        return;
    L6:
        if (r0 != 2) goto L9;
        r2 = 1000.0f;
        goto L17
    L9:
        if (r0 != 3) goto L12;
        r2 = 1000000.0f;
        goto L17
    L12:
        if (r0 != 4) goto L15;
        r2 = 1.0E9f;
        goto L17
    L15:
        if (r0 != 5) goto L17;
        r2 = 1.0E12f;
        goto L17
    }

    public ArrayRow addSingleError(SolverVariable r2, int r3) {
        this.variables.put(r2, r3);
        return this;
    }

    public boolean chooseSubject(LinearSystem r3) {
        SolverVariable r32 = chooseSubjectInVariables(r3);
        if (r32 != null) goto L5;
        boolean r33 = true;
    L7:
        if (this.variables.getCurrentSize() != 0) goto L9;
        this.mIsSimpleDefinition = true;
    L9:
        return r33;
    L5:
        pivot(r32);
        r33 = false;
        goto L7
    }

    public SolverVariable chooseSubjectInVariables(LinearSystem r15) {
        int r0 = this.variables.getCurrentSize();
        SolverVariable r1 = null;
        float r7 = 0.0f;
        float r8 = 0.0f;
        int r4 = 0;
        boolean r5 = false;
        boolean r6 = false;
        SolverVariable r3 = null;
    L3:
        if (r4 >= r0) goto L30;
        float r9 = this.variables.getVariableValue(r4);
        SolverVariable r10 = this.variables.getVariable(r4);
        if (r10.mType != SolverVariable.Type.UNRESTRICTED) goto L16;
        if (r1 != null) goto L10;
        boolean r12 = isNew(r10, r15);
    L8:
        r5 = r12;
        r7 = r9;
        r1 = r10;
    L29:
        r4 = r4 + 1;
        goto L3
    L10:
        if (r7 <= r9) goto L12;
        r12 = isNew(r10, r15);
        goto L8
    L12:
        if (r5 == true) goto L29;
        if (isNew(r10, r15) == false) goto L29;
        r7 = r9;
        r1 = r10;
        r5 = true;
        goto L29
    L16:
        if (r1 != null) goto L29;
        if (r9 >= 0.0f) goto L29;
        if (r3 != null) goto L23;
        boolean r32 = isNew(r10, r15);
    L21:
        r6 = r32;
        r8 = r9;
        r3 = r10;
        goto L29
    L23:
        if (r8 <= r9) goto L25;
        r32 = isNew(r10, r15);
        goto L21
    L25:
        if (r6 == true) goto L29;
        if (isNew(r10, r15) == false) goto L29;
        r8 = r9;
        r3 = r10;
        r6 = true;
        goto L29
    L30:
        if (r1 == null) goto L32;
        return r1;
    L32:
        return r3;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void clear() {
        this.variables.clear();
        this.mVariable = null;
        this.mConstantValue = 0.0f;
    }

    public ArrayRow createRowCentering(SolverVariable r6, SolverVariable r7, int r8, float r9, SolverVariable r10, SolverVariable r11, int r12) {
        if (r7 != r10) goto L7;
        this.variables.put(r6, 1.0f);
        this.variables.put(r11, 1.0f);
        this.variables.put(r7, -2.0f);
        return this;
    L7:
        if (r9 != 0.5f) goto L14;
        this.variables.put(r6, 1.0f);
        this.variables.put(r7, -1.0f);
        this.variables.put(r10, -1.0f);
        this.variables.put(r11, 1.0f);
        if (r8 > 0) goto L11;
        if (r12 > 0) goto L11;
    L25:
        return this;
    L11:
        this.mConstantValue = (-r8) + r12;
        return this;
    L14:
        if (r9 > 0.0f) goto L17;
        this.variables.put(r6, -1.0f);
        this.variables.put(r7, 1.0f);
        this.mConstantValue = r8;
        return this;
    L17:
        ArrayRowVariables r3 = this.variables;
        if (r9 < 1.0f) goto L21;
        r3.put(r11, -1.0f);
        this.variables.put(r10, 1.0f);
        this.mConstantValue = -r12;
        return this;
    L21:
        float r1 = 1.0f - r9;
        r3.put(r6, r1 * 1.0f);
        this.variables.put(r7, r1 * (-1.0f));
        this.variables.put(r10, (-1.0f) * r9);
        this.variables.put(r11, 1.0f * r9);
        if (r8 > 0) goto L26;
        if (r12 <= 0) goto L25;
    L26:
        this.mConstantValue = ((-r8) * r1) + (r12 * r9);
        return this;
    }

    public ArrayRow createRowDefinition(SolverVariable r1, int r2) {
        this.mVariable = r1;
        float r22 = r2;
        r1.computedValue = r22;
        this.mConstantValue = r22;
        this.mIsSimpleDefinition = true;
        return this;
    }

    public ArrayRow createRowDimensionPercent(SolverVariable r3, SolverVariable r4, float r5) {
        this.variables.put(r3, -1.0f);
        this.variables.put(r4, r5);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable r3, SolverVariable r4, SolverVariable r5, SolverVariable r6, float r7) {
        this.variables.put(r3, -1.0f);
        this.variables.put(r4, 1.0f);
        this.variables.put(r5, r7);
        this.variables.put(r6, -r7);
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float r5, float r6, float r7, SolverVariable r8, SolverVariable r9, SolverVariable r10, SolverVariable r11) {
        this.mConstantValue = 0.0f;
        if (r6 != 0.0f) goto L5;
    L17:
        this.variables.put(r8, 1.0f);
        this.variables.put(r9, -1.0f);
        this.variables.put(r11, 1.0f);
        this.variables.put(r10, -1.0f);
        return this;
    L5:
        if (r5 == r7) goto L17;
        if (r5 != 0.0f) goto L11;
        this.variables.put(r8, 1.0f);
        this.variables.put(r9, -1.0f);
        return this;
    L11:
        ArrayRowVariables r1 = this.variables;
        if (r7 != 0.0f) goto L15;
        r1.put(r10, 1.0f);
        this.variables.put(r11, -1.0f);
        return this;
    L15:
        float r52 = (r5 / r6) / (r7 / r6);
        r1.put(r8, 1.0f);
        this.variables.put(r9, -1.0f);
        this.variables.put(r11, r52);
        this.variables.put(r10, -r52);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable r2, int r3) {
        if (r3 >= 0) goto L5;
        this.mConstantValue = r3 * (-1);
        this.variables.put(r2, 1.0f);
        return this;
    L5:
        this.mConstantValue = r3;
        this.variables.put(r2, -1.0f);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable r4, SolverVariable r5, int r6) {
        boolean r0 = false;
        if (r6 == 0) goto L7;
        if (r6 >= 0) goto L6;
        r6 = r6 * (-1);
        r0 = true;
    L6:
        this.mConstantValue = r6;
    L7:
        ArrayRowVariables r62 = this.variables;
        if (r0 == true) goto L11;
        r62.put(r4, -1.0f);
        this.variables.put(r5, 1.0f);
        return this;
    L11:
        r62.put(r4, 1.0f);
        this.variables.put(r5, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable r4, SolverVariable r5, SolverVariable r6, int r7) {
        boolean r0 = false;
        if (r7 == 0) goto L7;
        if (r7 >= 0) goto L6;
        r7 = r7 * (-1);
        r0 = true;
    L6:
        this.mConstantValue = r7;
    L7:
        ArrayRowVariables r72 = this.variables;
        if (r0 == true) goto L11;
        r72.put(r4, -1.0f);
        this.variables.put(r5, 1.0f);
        this.variables.put(r6, 1.0f);
        return this;
    L11:
        r72.put(r4, 1.0f);
        this.variables.put(r5, -1.0f);
        this.variables.put(r6, -1.0f);
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable r4, SolverVariable r5, SolverVariable r6, int r7) {
        boolean r0 = false;
        if (r7 == 0) goto L7;
        if (r7 >= 0) goto L6;
        r7 = r7 * (-1);
        r0 = true;
    L6:
        this.mConstantValue = r7;
    L7:
        ArrayRowVariables r72 = this.variables;
        if (r0 == true) goto L11;
        r72.put(r4, -1.0f);
        this.variables.put(r5, 1.0f);
        this.variables.put(r6, -1.0f);
        return this;
    L11:
        r72.put(r4, 1.0f);
        this.variables.put(r5, -1.0f);
        this.variables.put(r6, 1.0f);
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable r3, SolverVariable r4, SolverVariable r5, SolverVariable r6, float r7) {
        this.variables.put(r5, 0.5f);
        this.variables.put(r6, 0.5f);
        this.variables.put(r3, -0.5f);
        this.variables.put(r4, -0.5f);
        this.mConstantValue = -r7;
        return this;
    }

    public void ensurePositiveConstant() {
        float r0 = this.mConstantValue;
        if (r0 >= 0.0f) goto L6;
        this.mConstantValue = r0 * (-1.0f);
        this.variables.invert();
        return;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getKey() {
        return this.mVariable;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem r1, boolean[] r2) {
        return pickPivotInVariables(r2, null);
    }

    public boolean hasKeyVariable() {
        SolverVariable r0 = this.mVariable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.mType != SolverVariable.Type.UNRESTRICTED) goto L7;
        return true;
    L7:
        if (this.mConstantValue < 0.0f) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean hasVariable(SolverVariable r1) {
        return this.variables.contains(r1);
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public void initFromRow(LinearSystem.Row r6) {
        if ((r6 instanceof ArrayRow) == false) goto L8;
        ArrayRow r62 = (ArrayRow) r6;
        this.mVariable = null;
        this.variables.clear();
        int r0 = 0;
    L6:
        if (r0 >= r62.variables.getCurrentSize()) goto L10;
        SolverVariable r1 = r62.variables.getVariable(r0);
        float r2 = r62.variables.getVariableValue(r0);
        this.variables.add(r1, r2, true);
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    @Override // androidx.constraintlayout.core.LinearSystem.Row
    public boolean isEmpty() {
        if (this.mVariable == null) goto L5;
        return false;
    L5:
        if (this.mConstantValue == 0.0f) goto L7;
        return false;
    L7:
        if (this.variables.getCurrentSize() != 0) goto L13;
        return true;
    L13:
        return false;
    }

    public SolverVariable pickPivot(SolverVariable r2) {
        return pickPivotInVariables(null, r2);
    }

    public void pivot(SolverVariable r4) {
        SolverVariable r0 = this.mVariable;
        if (r0 == null) goto L5;
        this.variables.put(r0, -1.0f);
        this.mVariable.mDefinitionId = -1;
        this.mVariable = null;
    L5:
        float r02 = this.variables.remove(r4, true) * (-1.0f);
        this.mVariable = r4;
        if (r02 != 1.0f) goto L8;
        return;
    L8:
        this.mConstantValue /= r02;
        this.variables.divideByAmount(r02);
    }

    public void reset() {
        this.mVariable = null;
        this.variables.clear();
        this.mConstantValue = 0.0f;
        this.mIsSimpleDefinition = false;
    }

    public String toReadableString() {
        if (this.mVariable != null) goto L5;
        String r0 = MVEL.VERSION_SUB;
    L6:
        String r02 = r0.concat(" = ");
        int r3 = 0;
        if (this.mConstantValue == 0.0f) goto L9;
        r02 = r02 + this.mConstantValue;
        boolean r1 = true;
    L10:
        int r5 = this.variables.getCurrentSize();
    L11:
        if (r3 >= r5) goto L33;
        SolverVariable r6 = this.variables.getVariable(r3);
        if (r6 == null) goto L32;
        float r7 = this.variables.getVariableValue(r3);
        if (r7 == 0.0f) goto L32;
        String r62 = r6.toString();
        if (r1 == false) goto L21;
        if (r7 <= 0.0f) goto L26;
        r02 = r02 + " + ";
    L28:
        if (r7 != 1.0f) goto L30;
        r02 = r02 + r62;
    L31:
        r1 = true;
        goto L32
    L30:
        r02 = r02 + r7 + " " + r62;
        goto L31
    L26:
        r02 = r02 + " - ";
    L23:
        r7 = r7 * (-1.0f);
        goto L28
    L21:
        if (r7 >= 0.0f) goto L28;
        r02 = r02 + "- ";
    L32:
        r3 = r3 + 1;
        goto L11
    L33:
        if (r1 == false) goto L35;
        return r02;
    L35:
        return r02 + "0.0";
    L9:
        r1 = false;
        goto L10
    L5:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET + this.mVariable;
        goto L6
    }

    public String toString() {
        return toReadableString();
    }

    public void updateFromFinalVariable(LinearSystem r4, SolverVariable r5, boolean r6) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.isFinalValue == false) goto L18;
        this.mConstantValue += r5.computedValue * this.variables.get(r5);
        this.variables.remove(r5, r6);
        if (r6 == false) goto L10;
        r5.removeFromRow(this);
    L10:
        if (LinearSystem.SIMPLIFY_SYNONYMS == true) goto L12;
        return;
    L12:
        if (this.variables.getCurrentSize() != 0) goto L17;
        this.mIsSimpleDefinition = true;
        r4.hasSimpleDefinition = true;
        return;
    L17:
        return;
    }

    public void updateFromRow(LinearSystem r4, ArrayRow r5, boolean r6) {
        this.mConstantValue += r5.mConstantValue * this.variables.use(r5, r6);
        if (r6 == false) goto L6;
        r5.mVariable.removeFromRow(this);
    L6:
        if (LinearSystem.SIMPLIFY_SYNONYMS == true) goto L8;
        return;
    L8:
        if (this.mVariable != null) goto L10;
        return;
    L10:
        if (this.variables.getCurrentSize() != 0) goto L15;
        this.mIsSimpleDefinition = true;
        r4.hasSimpleDefinition = true;
        return;
    }

    public void updateFromSynonymVariable(LinearSystem r4, SolverVariable r5, boolean r6) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.mIsSynonym == false) goto L18;
        float r0 = this.variables.get(r5);
        this.mConstantValue += r5.mSynonymDelta * r0;
        this.variables.remove(r5, r6);
        if (r6 == false) goto L9;
        r5.removeFromRow(this);
    L9:
        this.variables.add(r4.mCache.mIndexedVariables[r5.mSynonym], r0, r6);
        if (LinearSystem.SIMPLIFY_SYNONYMS == true) goto L12;
        return;
    L12:
        if (this.variables.getCurrentSize() != 0) goto L17;
        this.mIsSimpleDefinition = true;
        r4.hasSimpleDefinition = true;
        return;
    L17:
        return;
    }

    public void updateFromSystem(LinearSystem r9) {
        if (r9.mRows.length == 0) goto L57;
        boolean r1 = false;
    L7:
        if (r1 == true) goto L34;
        int r3 = this.variables.getCurrentSize();
        int r4 = 0;
    L9:
        if (r4 >= r3) goto L18;
        SolverVariable r5 = this.variables.getVariable(r4);
        if (r5.mDefinitionId == (-1)) goto L13;
    L16:
        this.mVariablesToUpdate.add(r5);
    L17:
        r4 = r4 + 1;
        goto L9
    L13:
        if (r5.isFinalValue == true) goto L16;
        if (r5.mIsSynonym == false) goto L17;
    L18:
        int r32 = this.mVariablesToUpdate.size();
        if (r32 > 0) goto L20;
        r1 = true;
        goto L7
    L20:
        int r42 = 0;
    L21:
        ArrayList<SolverVariable> r52 = this.mVariablesToUpdate;
        if (r42 >= r32) goto L31;
        SolverVariable r53 = r52.get(r42);
        if (r53.isFinalValue == false) goto L27;
        updateFromFinalVariable(r9, r53, true);
    L30:
        r42 = r42 + 1;
        goto L21
    L27:
        if (r53.mIsSynonym == false) goto L29;
        updateFromSynonymVariable(r9, r53, true);
        goto L30
    L29:
        updateFromRow(r9, r9.mRows[r53.mDefinitionId], true);
        goto L30
    L31:
        r52.clear();
        goto L7
    L34:
        if (LinearSystem.SIMPLIFY_SYNONYMS == true) goto L36;
        return;
    L36:
        if (this.mVariable != null) goto L38;
        return;
    L38:
        if (this.variables.getCurrentSize() != 0) goto L56;
        this.mIsSimpleDefinition = true;
        r9.hasSimpleDefinition = true;
        return;
    L56:
        return;
    }
}
