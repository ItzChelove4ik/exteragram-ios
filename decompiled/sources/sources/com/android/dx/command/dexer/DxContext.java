package com.android.dx.command.dexer;

import com.android.dx.dex.cf.CodeStatistics;
import com.android.dx.dex.cf.OptimizerOptions;
import java.io.OutputStream;
import java.io.PrintStream;

/* loaded from: classes4.dex */
public class DxContext {
    public final CodeStatistics codeStatistics;
    public final PrintStream err;
    final PrintStream noop;
    public final OptimizerOptions optimizerOptions;
    public final PrintStream out;

    /* renamed from: com.android.dx.command.dexer.DxContext$1, reason: invalid class name */
    public class AnonymousClass1 extends OutputStream {
        final /* synthetic */ DxContext this$0;

        public AnonymousClass1(DxContext r1) {
            this.this$0 = r1;
        }

        @Override // java.io.OutputStream
        public void write(int r1) {
        }
    }

    public DxContext() {
        this(System.out, System.err);
    }

    public DxContext(OutputStream r3, OutputStream r4) {
        this.codeStatistics = new CodeStatistics();
        this.optimizerOptions = new OptimizerOptions();
        this.noop = new PrintStream(new AnonymousClass1(this));
        this.out = new PrintStream(r3);
        this.err = new PrintStream(r4);
    }
}
