package org.chromium.net.impl;

import com.google.android.gms.cast.MediaError;
import java.util.concurrent.Executor;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.chromium.net.InlineExecutionProhibitedException;

/* loaded from: classes5.dex */
public abstract class JavaUrlRequestUtils {

    public interface CheckedRunnable {
        void run();
    }

    public static final class DirectPreventingExecutor implements Executor {
        private final Executor mDelegate;

        public static final class InlineCheckingRunnable implements Runnable {
            private Thread mCallingThread;
            private final Runnable mCommand;
            private InlineExecutionProhibitedException mExecutedInline;

            /* renamed from: -$$Nest$fgetmExecutedInline, reason: not valid java name */
            public static /* bridge */ /* synthetic */ InlineExecutionProhibitedException m3843$$Nest$fgetmExecutedInline(InlineCheckingRunnable r0) {
                return r0.mExecutedInline;
            }

            /* renamed from: -$$Nest$fputmCallingThread, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m3844$$Nest$fputmCallingThread(InlineCheckingRunnable r0, Thread r1) {
                r0.mCallingThread = r1;
            }

            private InlineCheckingRunnable(Runnable r1, Thread r2) {
                this.mCommand = r1;
                this.mCallingThread = r2;
            }

            public /* synthetic */ InlineCheckingRunnable(Runnable r1, Thread r2, JavaUrlRequestUtilsIA r3) {
                this(r1, r2);
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Thread.currentThread() != this.mCallingThread) goto L6;
                this.mExecutedInline = new InlineExecutionProhibitedException();
                return;
            L6:
                this.mCommand.run();
            }
        }

        public DirectPreventingExecutor(Executor r1) {
            this.mDelegate = r1;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r4) {
            InlineCheckingRunnable r1 = new InlineCheckingRunnable(r4, Thread.currentThread(), null);
            this.mDelegate.execute(r1);
            if (InlineCheckingRunnable.m3843$$Nest$fgetmExecutedInline(r1) != null) goto L7;
            InlineCheckingRunnable.m3844$$Nest$fputmCallingThread(r1, null);
            return;
        L7:
            throw InlineCheckingRunnable.m3843$$Nest$fgetmExecutedInline(r1);
        }
    }

    public static String stateToString(int r1) {
        switch(r1) {
            case 0: goto L21;
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L7;
            case 8: goto L5;
            default: goto L3;
        };
    L3:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown state ", r1);
        return null;
    L5:
        return "CANCELLED";
    L7:
        return "COMPLETE";
    L9:
        return MediaError.ERROR_TYPE_ERROR;
    L11:
        return "READING";
    L13:
        return "AWAITING_READ";
    L15:
        return "AWAITING_FOLLOW_REDIRECT";
    L17:
        return "REDIRECT_RECEIVED";
    L19:
        return "STARTED";
    L21:
        return "NOT_STARTED";
    }
}
