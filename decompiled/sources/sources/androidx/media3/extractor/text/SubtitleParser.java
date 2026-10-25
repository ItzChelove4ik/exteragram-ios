package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;
import com.google.common.collect.ImmutableList;
import java.util.Objects;

/* loaded from: classes4.dex */
public interface SubtitleParser {

    public interface Factory {
        public static final Factory UNSUPPORTED = null;

        /* renamed from: androidx.media3.extractor.text.SubtitleParser$Factory$1, reason: invalid class name */
        public class AnonymousClass1 implements Factory {
            public AnonymousClass1() {
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public SubtitleParser create(Format r1) {
                throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public int getCueReplacementBehavior(Format r1) {
                return 1;
            }

            @Override // androidx.media3.extractor.text.SubtitleParser.Factory
            public boolean supportsFormat(Format r1) {
                return false;
            }
        }

        static {
            UNSUPPORTED = new AnonymousClass1();
        }

        SubtitleParser create(Format r1);

        int getCueReplacementBehavior(Format r1);

        boolean supportsFormat(Format r1);
    }

    public static class OutputOptions {
        private static final OutputOptions ALL = null;
        public final boolean outputAllCues;
        public final long startTimeUs;

        static {
            ALL = new OutputOptions(-9223372036854775807L, false);
        }

        private OutputOptions(long r1, boolean r3) {
            this.startTimeUs = r1;
            this.outputAllCues = r3;
        }

        public static /* synthetic */ OutputOptions access$000() {
            return ALL;
        }

        public static OutputOptions allCues() {
            return ALL;
        }

        public static OutputOptions cuesAfterThenRemainingCuesBefore(long r2) {
            return new OutputOptions(r2, true);
        }
    }

    int getCueReplacementBehavior();

    void parse(byte[] r1, int r2, int r3, OutputOptions r4, Consumer<CuesWithTiming> r5);

    default Subtitle parseToLegacySubtitle(byte[] r8, int r9, int r10) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        OutputOptions r5 = OutputOptions.access$000();
        Objects.requireNonNull(r0);
        parse(r8, r9, r10, r5, new SubtitleParser$$ExternalSyntheticLambda0(r0));
        return new CuesWithTimingSubtitle(r0.build());
    }

    default void reset() {
    }
}
