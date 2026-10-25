package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public boolean graphOptimizer;
    public boolean hasSimpleDefinition;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private int mPoolSize;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private int mTableSize;
    private Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables;
    int mVariablesID;
    public boolean newgraphOptimizer;

    public interface Row {
        void addError(SolverVariable r1);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem r1, boolean[] r2);

        void initFromRow(Row r1);

        boolean isEmpty();
    }

    public static class ValuesRow extends ArrayRow {
        public ValuesRow(Cache r2) {
            this.variables = new SolverVariableValues(this, r2);
        }
    }

    static {
    }

    public LinearSystem() {
        this.mPoolSize = 1000;
        this.hasSimpleDefinition = false;
        this.mVariablesID = 0;
        this.mVariables = null;
        this.mTableSize = 32;
        this.mMaxColumns = 32;
        this.graphOptimizer = false;
        this.newgraphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[32];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = 32;
        this.mPoolVariables = new SolverVariable[1000];
        this.mPoolVariablesCount = 0;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache r0 = new Cache();
        this.mCache = r0;
        this.mGoal = new PriorityGoalRow(r0);
        if (OPTIMIZED_ENGINE == false) goto L6;
        this.mTempGoal = new ValuesRow(r0);
        return;
    L6:
        this.mTempGoal = new ArrayRow(r0);
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type r3, String r4) {
        SolverVariable r0 = this.mCache.mSolverVariablePool.acquire();
        if (r0 != null) goto L5;
        r0 = new SolverVariable(r3, r4);
        r0.setType(r3, r4);
    L6:
        int r32 = this.mPoolVariablesCount;
        int r42 = this.mPoolSize;
        if (r32 < r42) goto L9;
        int r43 = r42 * 2;
        this.mPoolSize = r43;
        this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, r43);
    L9:
        SolverVariable[] r33 = this.mPoolVariables;
        int r44 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = r44 + 1;
        r33[r44] = r0;
        return r0;
    L5:
        r0.reset();
        r0.setType(r3, r4);
        goto L6
    }

    private void addRow(ArrayRow r8) {
        if (SIMPLIFY_SYNONYMS == true) goto L5;
    L7:
        ArrayRow[] r0 = this.mRows;
        int r1 = this.mNumRows;
        r0[r1] = r8;
        SolverVariable r02 = r8.mVariable;
        r02.mDefinitionId = r1;
        this.mNumRows = r1 + 1;
        r02.updateReferencesWithNewDefinition(this, r8);
    L9:
        if (SIMPLIFY_SYNONYMS == true) goto L11;
        return;
    L11:
        if (this.hasSimpleDefinition == false) goto L47;
        int r03 = 0;
    L14:
        if (r03 >= this.mNumRows) goto L37;
        if (this.mRows[r03] != null) goto L18;
        System.out.println("WTF");
    L18:
        ArrayRow r12 = this.mRows[r03];
        if (r12 == null) goto L36;
        if (r12.mIsSimpleDefinition == false) goto L36;
        r12.mVariable.setFinalValue(this, r12.mConstantValue);
        boolean r2 = OPTIMIZED_ENGINE;
        Cache r3 = this.mCache;
        if (r2 == false) goto L25;
        r3.mOptimizedArrayRowPool.release(r12);
    L26:
        this.mRows[r03] = null;
        int r13 = r03 + 1;
        int r32 = r13;
    L27:
        int r4 = this.mNumRows;
        if (r13 >= r4) goto L33;
        ArrayRow[] r33 = this.mRows;
        int r42 = r13 - 1;
        ArrayRow r5 = r33[r13];
        r33[r42] = r5;
        SolverVariable r34 = r5.mVariable;
        if (r34.mDefinitionId != r13) goto L32;
        r34.mDefinitionId = r42;
    L32:
        r32 = r13;
        r13 = r13 + 1;
        goto L27
    L33:
        if (r32 >= r4) goto L35;
        this.mRows[r32] = null;
    L35:
        this.mNumRows = r4 - 1;
        r03 = r03 - 1;
        goto L36
    L25:
        r3.mArrayRowPool.release(r12);
    L36:
        r03 = r03 + 1;
        goto L14
    L37:
        this.hasSimpleDefinition = false;
        return;
    L47:
        return;
    L5:
        if (r8.mIsSimpleDefinition == false) goto L7;
        r8.mVariable.setFinalValue(this, r8.mConstantValue);
        goto L9
    }

    private void computeValues() {
        int r0 = 0;
    L4:
        if (r0 >= this.mNumRows) goto L6;
        ArrayRow r1 = this.mRows[r0];
        SolverVariable r2 = r1.mVariable;
        r2.computedValue = r1.mConstantValue;
        r0 = r0 + 1;
        goto L4
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem r0, SolverVariable r1, SolverVariable r2, float r3) {
        return r0.createRow().createRowDimensionPercent(r1, r2, r3);
    }

    private int enforceBFS(Row r19) {
        int r2 = 0;
    L4:
        if (r2 >= this.mNumRows) goto L66;
        ArrayRow r3 = this.mRows[r2];
        if (r3.mVariable.mType == SolverVariable.Type.UNRESTRICTED) goto L65;
        float r4 = 0.0f;
        if (r3.mConstantValue >= 0.0f) goto L65;
        boolean r22 = false;
        int r32 = 0;
    L11:
        if (r22 == true) goto L64;
        r32 = r32 + 1;
        float r6 = Float.MAX_VALUE;
        int r7 = 0;
        int r8 = -1;
        int r9 = -1;
        int r10 = 0;
    L13:
        int r12 = 1;
        if (r7 >= this.mNumRows) goto L56;
        ArrayRow r11 = this.mRows[r7];
        if (r11.mVariable.mType != SolverVariable.Type.UNRESTRICTED) goto L19;
    L39:
        float r16 = r4;
    L55:
        r7 = r7 + 1;
        r4 = r16;
        goto L13
    L19:
        if (r11.mIsSimpleDefinition == true) goto L39;
        if (r11.mConstantValue >= r4) goto L39;
        if (SKIP_COLUMNS == false) goto L40;
        int r122 = r11.variables.getCurrentSize();
        int r13 = 0;
    L26:
        if (r13 >= r122) goto L39;
        SolverVariable r15 = r11.variables.getVariable(r13);
        float r1 = r11.variables.get(r15);
        if (r1 > r4) goto L30;
        float r162 = r4;
    L38:
        r13 = r13 + 1;
        r4 = r162;
        goto L26
    L30:
        r162 = r4;
        int r42 = 0;
    L31:
        if (r42 >= 9) goto L38;
        float r5 = r15.mStrengthVector[r42] / r1;
        if (r5 >= r6) goto L35;
        if (r42 != r10) goto L35;
    L36:
        r10 = r42;
        r9 = r15.id;
        r8 = r7;
        r6 = r5;
    L37:
        r42 = r42 + 1;
    L35:
        if (r42 <= r10) goto L37;
    L40:
        r16 = r4;
    L42:
        if (r12 >= this.mNumColumns) goto L55;
        SolverVariable r14 = this.mCache.mIndexedVariables[r12];
        float r43 = r11.variables.get(r14);
        if (r43 <= r16) goto L54;
        int r52 = 0;
    L47:
        if (r52 >= 9) goto L54;
        float r132 = r14.mStrengthVector[r52] / r43;
        if (r132 >= r6) goto L51;
        if (r52 != r10) goto L51;
    L52:
        r10 = r52;
        r8 = r7;
        r9 = r12;
        r6 = r132;
    L53:
        r52 = r52 + 1;
    L51:
        if (r52 <= r10) goto L53;
    L54:
        r12 = r12 + 1;
        goto L42
    L56:
        float r163 = r4;
        if (r8 == (-1)) goto L59;
        ArrayRow r44 = this.mRows[r8];
        r44.mVariable.mDefinitionId = -1;
        r44.pivot(this.mCache.mIndexedVariables[r9]);
        SolverVariable r17 = r44.mVariable;
        r17.mDefinitionId = r8;
        r17.updateReferencesWithNewDefinition(this, r44);
    L61:
        if (r32 <= (this.mNumColumns / 2)) goto L63;
        r22 = true;
    L63:
        r4 = r163;
        goto L11
    L59:
        r22 = true;
        goto L61
    L64:
        return r32;
    L65:
        r2 = r2 + 1;
        goto L4
    L66:
        return 0;
    }

    public static Metrics getMetrics() {
        return null;
    }

    private void increaseTableSize() {
        int r0 = this.mTableSize * 2;
        this.mTableSize = r0;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, r0);
        Cache r02 = this.mCache;
        r02.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(r02.mIndexedVariables, this.mTableSize);
        int r03 = this.mTableSize;
        this.mAlreadyTestedCandidates = new boolean[r03];
        this.mMaxColumns = r03;
        this.mMaxRows = r03;
    }

    private int optimize(Row r11, boolean r12) {
        int r0 = 0;
    L4:
        if (r0 >= this.mNumColumns) goto L6;
        this.mAlreadyTestedCandidates[r0] = false;
        r0 = r0 + 1;
        goto L4
    L6:
        boolean r02 = false;
        int r1 = 0;
    L7:
        if (r02 == true) goto L41;
        r1 = r1 + 1;
        if (r1 >= (this.mNumColumns * 2)) goto L18;
        if (r11.getKey() == null) goto L14;
        this.mAlreadyTestedCandidates[r11.getKey().id] = true;
    L14:
        SolverVariable r2 = r11.getPivotCandidate(this, this.mAlreadyTestedCandidates);
        if (r2 == null) goto L20;
        boolean[] r4 = this.mAlreadyTestedCandidates;
        int r5 = r2.id;
        if (r4[r5] == true) goto L18;
        r4[r5] = true;
    L20:
        if (r2 != null) goto L21;
        r02 = true;
        goto L7
    L21:
        float r42 = Float.MAX_VALUE;
        int r52 = 0;
        int r6 = -1;
    L23:
        if (r52 >= this.mNumRows) goto L38;
        ArrayRow r7 = this.mRows[r52];
        if (r7.mVariable.mType == SolverVariable.Type.UNRESTRICTED) goto L37;
        if (r7.mIsSimpleDefinition == true) goto L37;
        if (r7.hasVariable(r2) == false) goto L37;
        float r8 = r7.variables.get(r2);
        if (r8 >= 0.0f) goto L37;
        float r72 = (-r7.mConstantValue) / r8;
        if (r72 >= r42) goto L37;
        r6 = r52;
        r42 = r72;
    L37:
        r52 = r52 + 1;
        goto L23
    L38:
        if (r6 <= (-1)) goto L7;
        ArrayRow r43 = this.mRows[r6];
        r43.mVariable.mDefinitionId = -1;
        r43.pivot(r2);
        SolverVariable r22 = r43.mVariable;
        r22.mDefinitionId = r6;
        r22.updateReferencesWithNewDefinition(this, r43);
    L18:
        return r1;
    L41:
        return r1;
    }

    private void releaseRows() {
        int r2 = 0;
        if (OPTIMIZED_ENGINE == false) goto L11;
    L5:
        if (r2 >= this.mNumRows) goto L16;
        ArrayRow r0 = this.mRows[r2];
        if (r0 == null) goto L9;
        this.mCache.mOptimizedArrayRowPool.release(r0);
    L9:
        this.mRows[r2] = null;
        r2 = r2 + 1;
        goto L5
    L16:
        return;
    L11:
        if (r2 >= this.mNumRows) goto L23;
        ArrayRow r02 = this.mRows[r2];
        if (r02 == null) goto L15;
        this.mCache.mArrayRowPool.release(r02);
    L15:
        this.mRows[r2] = null;
        r2 = r2 + 1;
        goto L11
    }

    public void addCenterPoint(ConstraintWidget r17, ConstraintWidget r18, float r19, int r20) {
        ConstraintAnchor.Type r3 = ConstraintAnchor.Type.LEFT;
        SolverVariable r6 = createObjectVariable(r17.getAnchor(r3));
        ConstraintAnchor.Type r4 = ConstraintAnchor.Type.TOP;
        SolverVariable r8 = createObjectVariable(r17.getAnchor(r4));
        ConstraintAnchor.Type r5 = ConstraintAnchor.Type.RIGHT;
        SolverVariable r13 = createObjectVariable(r17.getAnchor(r5));
        ConstraintAnchor.Type r7 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable r9 = createObjectVariable(r17.getAnchor(r7));
        SolverVariable r1 = createObjectVariable(r18.getAnchor(r3));
        SolverVariable r10 = createObjectVariable(r18.getAnchor(r4));
        SolverVariable r32 = createObjectVariable(r18.getAnchor(r5));
        SolverVariable r11 = createObjectVariable(r18.getAnchor(r7));
        ArrayRow r72 = createRow();
        double r42 = r19;
        double r12 = r20;
        r72.createRowWithAngle(r8, r9, r10, r11, (float) (Math.sin(r42) * r12));
        addConstraint(r72);
        ArrayRow r52 = createRow();
        r52.createRowWithAngle(r6, r13, r1, r32, (float) (Math.cos(r42) * r12));
        addConstraint(r52);
    }

    public void addCentering(SolverVariable r10, SolverVariable r11, int r12, float r13, SolverVariable r14, SolverVariable r15, int r16, int r17) {
        ArrayRow r1 = createRow();
        r1.createRowCentering(r10, r11, r12, r13, r14, r15, r16);
        if (r17 == 8) goto L5;
        r1.addError(this, r17);
    L5:
        addConstraint(r1);
    }

    public void addConstraint(ArrayRow r6) {
        if (r6 == null) goto L42;
        boolean r1 = true;
        if ((this.mNumRows + 1) < this.mMaxRows) goto L7;
    L8:
        increaseTableSize();
    L9:
        boolean r2 = false;
        if (r6.mIsSimpleDefinition == true) goto L38;
        r6.updateFromSystem(this);
        if (r6.isEmpty() == true) goto L43;
        r6.ensurePositiveConstant();
        if (r6.chooseSubject(this) == false) goto L33;
        SolverVariable r0 = createExtraVariable();
        r6.mVariable = r0;
        int r3 = this.mNumRows;
        addRow(r6);
        if (this.mNumRows != (r3 + 1)) goto L33;
        this.mTempGoal.initFromRow(r6);
        optimize(this.mTempGoal, true);
        if (r0.mDefinitionId != (-1)) goto L35;
        if (r6.mVariable != r0) goto L26;
        SolverVariable r02 = r6.pickPivot(r0);
        if (r02 == null) goto L26;
        r6.pivot(r02);
    L26:
        if (r6.mIsSimpleDefinition == true) goto L28;
        r6.mVariable.updateReferencesWithNewDefinition(this, r6);
    L28:
        boolean r03 = OPTIMIZED_ENGINE;
        Cache r22 = this.mCache;
        if (r03 == false) goto L31;
        r22.mOptimizedArrayRowPool.release(r6);
    L32:
        this.mNumRows--;
        goto L35
    L31:
        r22.mArrayRowPool.release(r6);
    L35:
        if (r6.hasKeyVariable() == false) goto L44;
        r2 = r1;
        goto L38
    L44:
        return;
    L33:
        r1 = false;
        goto L35
    L43:
        return;
    L38:
        if (r2 == true) goto L41;
        addRow(r6);
        return;
    L41:
        return;
    L7:
        if ((this.mNumColumns + 1) < this.mMaxColumns) goto L9;
    }

    public ArrayRow addEquality(SolverVariable r4, SolverVariable r5, int r6, int r7) {
        if (USE_BASIC_SYNONYMS == false) goto L11;
        if (r7 != 8) goto L11;
        if (r5.isFinalValue == false) goto L11;
        if (r4.mDefinitionId != (-1)) goto L11;
        r4.setFinalValue(this, r5.computedValue + r6);
        return null;
    L11:
        ArrayRow r0 = createRow();
        r0.createRowEquals(r4, r5, r6);
        if (r7 == 8) goto L14;
        r0.addError(this, r7);
    L14:
        addConstraint(r0);
        return r0;
    }

    public void addEquality(SolverVariable r6, int r7) {
        if (USE_BASIC_SYNONYMS == true) goto L5;
    L18:
        int r0 = r6.mDefinitionId;
        if (r0 == (-1)) goto L30;
        ArrayRow r02 = this.mRows[r0];
        if (r02.mIsSimpleDefinition == false) goto L25;
        r02.mConstantValue = r7;
        return;
    L25:
        if (r02.variables.getCurrentSize() != 0) goto L28;
        r02.mIsSimpleDefinition = true;
        r02.mConstantValue = r7;
        return;
    L28:
        ArrayRow r03 = createRow();
        r03.createRowEquals(r6, r7);
        addConstraint(r03);
        return;
    L30:
        ArrayRow r04 = createRow();
        r04.createRowDefinition(r6, r7);
        addConstraint(r04);
        return;
    L5:
        if (r6.mDefinitionId != (-1)) goto L18;
        float r72 = r7;
        r6.setFinalValue(this, r72);
        int r05 = 0;
    L8:
        if (r05 >= (this.mVariablesID + 1)) goto L17;
        SolverVariable r1 = this.mCache.mIndexedVariables[r05];
        if (r1 == null) goto L16;
        if (r1.mIsSynonym == false) goto L16;
        if (r1.mSynonym != r6.id) goto L16;
        r1.setFinalValue(this, r1.mSynonymDelta + r72);
    L16:
        r05 = r05 + 1;
        goto L8
    }

    public void addGreaterBarrier(SolverVariable r3, SolverVariable r4, int r5, boolean r6) {
        ArrayRow r62 = createRow();
        SolverVariable r0 = createSlackVariable();
        r0.strength = 0;
        r62.createRowGreaterThan(r3, r4, r0, r5);
        addConstraint(r62);
    }

    public void addGreaterThan(SolverVariable r4, SolverVariable r5, int r6, int r7) {
        ArrayRow r0 = createRow();
        SolverVariable r1 = createSlackVariable();
        r1.strength = 0;
        r0.createRowGreaterThan(r4, r5, r1, r6);
        if (r7 == 8) goto L5;
        addSingleError(r0, (int) (r0.variables.get(r1) * (-1.0f)), r7);
    L5:
        addConstraint(r0);
    }

    public void addLowerBarrier(SolverVariable r3, SolverVariable r4, int r5, boolean r6) {
        ArrayRow r62 = createRow();
        SolverVariable r0 = createSlackVariable();
        r0.strength = 0;
        r62.createRowLowerThan(r3, r4, r0, r5);
        addConstraint(r62);
    }

    public void addLowerThan(SolverVariable r4, SolverVariable r5, int r6, int r7) {
        ArrayRow r0 = createRow();
        SolverVariable r1 = createSlackVariable();
        r1.strength = 0;
        r0.createRowLowerThan(r4, r5, r1, r6);
        if (r7 == 8) goto L5;
        addSingleError(r0, (int) (r0.variables.get(r1) * (-1.0f)), r7);
    L5:
        addConstraint(r0);
    }

    public void addRatio(SolverVariable r7, SolverVariable r8, SolverVariable r9, SolverVariable r10, float r11, int r12) {
        ArrayRow r0 = createRow();
        r0.createRowDimensionRatio(r7, r8, r9, r10, r11);
        if (r12 == 8) goto L5;
        r0.addError(this, r12);
    L5:
        addConstraint(r0);
    }

    public void addSingleError(ArrayRow r2, int r3, int r4) {
        r2.addSingleError(createErrorVariable(r4, null), r3);
    }

    public SolverVariable createErrorVariable(int r3, String r4) {
        if ((this.mNumColumns + 1) < this.mMaxColumns) goto L5;
        increaseTableSize();
    L5:
        SolverVariable r42 = acquireSolverVariable(SolverVariable.Type.ERROR, r4);
        int r0 = this.mVariablesID + 1;
        this.mVariablesID = r0;
        this.mNumColumns++;
        r42.id = r0;
        r42.strength = r3;
        this.mCache.mIndexedVariables[r0] = r42;
        this.mGoal.addError(r42);
        return r42;
    }

    public SolverVariable createExtraVariable() {
        if ((this.mNumColumns + 1) < this.mMaxColumns) goto L5;
        increaseTableSize();
    L5:
        SolverVariable r0 = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int r1 = this.mVariablesID + 1;
        this.mVariablesID = r1;
        this.mNumColumns++;
        r0.id = r1;
        this.mCache.mIndexedVariables[r1] = r0;
        return r0;
    }

    public SolverVariable createObjectVariable(Object r4) {
        SolverVariable r0 = null;
        if (r4 != null) goto L6;
        return null;
    L6:
        if ((this.mNumColumns + 1) < this.mMaxColumns) goto L9;
        increaseTableSize();
    L9:
        if ((r4 instanceof ConstraintAnchor) == false) goto L24;
        ConstraintAnchor r42 = (ConstraintAnchor) r4;
        r0 = r42.getSolverVariable();
        if (r0 != null) goto L13;
        r42.resetSolverVariable(this.mCache);
        r0 = r42.getSolverVariable();
    L13:
        int r43 = r0.id;
        if (r43 != (-1)) goto L16;
    L21:
        if (r43 == (-1)) goto L23;
        r0.reset();
    L23:
        int r44 = this.mVariablesID + 1;
        this.mVariablesID = r44;
        this.mNumColumns++;
        r0.id = r44;
        r0.mType = SolverVariable.Type.UNRESTRICTED;
        this.mCache.mIndexedVariables[r44] = r0;
        goto L24
    L16:
        if (r43 > this.mVariablesID) goto L21;
        if (this.mCache.mIndexedVariables[r43] == null) goto L21;
        return r0;
    L24:
        return r0;
    }

    public ArrayRow createRow() {
        boolean r0 = OPTIMIZED_ENGINE;
        Cache r1 = this.mCache;
        if (r0 == false) goto L8;
        ArrayRow r02 = r1.mOptimizedArrayRowPool.acquire();
        if (r02 != null) goto L7;
        r02 = new ValuesRow(this.mCache);
        OPTIMIZED_ARRAY_ROW_CREATION++;
    L12:
        SolverVariable.increaseErrorId();
        return r02;
    L7:
        r02.reset();
        goto L12
    L8:
        r02 = r1.mArrayRowPool.acquire();
        if (r02 != null) goto L11;
        r02 = new ArrayRow(this.mCache);
        ARRAY_ROW_CREATION++;
        goto L12
    L11:
        r02.reset();
        goto L12
    }

    public SolverVariable createSlackVariable() {
        if ((this.mNumColumns + 1) < this.mMaxColumns) goto L5;
        increaseTableSize();
    L5:
        SolverVariable r0 = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int r1 = this.mVariablesID + 1;
        this.mVariablesID = r1;
        this.mNumColumns++;
        r0.id = r1;
        this.mCache.mIndexedVariables[r1] = r0;
        return r0;
    }

    public void fillMetrics(Metrics r1) {
    }

    public Cache getCache() {
        return this.mCache;
    }

    public int getObjectVariableValue(Object r1) {
        SolverVariable r0 = ((ConstraintAnchor) r1).getSolverVariable();
        if (r0 != null) goto L5;
        return 0;
    L5:
        return (int) (r0.computedValue + 0.5f);
    }

    public void minimize() {
        if (this.mGoal.isEmpty() == false) goto L7;
        computeValues();
        return;
    L7:
        if (this.graphOptimizer == false) goto L9;
    L13:
        int r0 = 0;
    L15:
        if (r0 >= this.mNumRows) goto L21;
        if (this.mRows[r0].mIsSimpleDefinition == false) goto L18;
        r0 = r0 + 1;
        goto L15
    L18:
        minimizeGoal(this.mGoal);
        return;
    L21:
        computeValues();
        return;
    L9:
        if (this.newgraphOptimizer == true) goto L13;
        minimizeGoal(this.mGoal);
    }

    public void minimizeGoal(Row r2) {
        enforceBFS(r2);
        optimize(r2, false);
        computeValues();
    }

    public void reset() {
        int r1 = 0;
    L3:
        Cache r2 = this.mCache;
        SolverVariable[] r3 = r2.mIndexedVariables;
        if (r1 >= r3.length) goto L9;
        SolverVariable r22 = r3[r1];
        if (r22 == null) goto L8;
        r22.reset();
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        r2.mSolverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, null);
        HashMap<String, SolverVariable> r12 = this.mVariables;
        if (r12 == null) goto L12;
        r12.clear();
    L12:
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        int r13 = 0;
    L14:
        if (r13 >= this.mNumRows) goto L19;
        ArrayRow r23 = this.mRows[r13];
        if (r23 == null) goto L18;
        r23.mUsed = false;
    L18:
        r13 = r13 + 1;
        goto L14
    L19:
        releaseRows();
        this.mNumRows = 0;
        boolean r0 = OPTIMIZED_ENGINE;
        Cache r14 = this.mCache;
        if (r0 == false) goto L23;
        this.mTempGoal = new ValuesRow(r14);
        return;
    L23:
        this.mTempGoal = new ArrayRow(r14);
    }
}
