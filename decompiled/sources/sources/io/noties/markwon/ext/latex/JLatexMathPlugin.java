package io.noties.markwon.ext.latex;

import android.os.Handler;
import android.os.Looper;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.ext.latex.JLatexMathBlockParser;
import io.noties.markwon.ext.latex.JLatexMathBlockParserLegacy;
import io.noties.markwon.ext.latex.JLatexMathTheme;
import io.noties.markwon.image.AsyncDrawableLoader;
import io.noties.markwon.image.ImageSizeResolver;
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.commonmark.parser.Parser;

/* loaded from: classes5.dex */
public class JLatexMathPlugin extends AbstractMarkwonPlugin {
    final Config config;
    private final ImageSizeResolver inlineImageSizeResolver;
    private final JLatexBlockImageSizeResolver jLatexBlockImageSizeResolver;
    private final JLatextAsyncDrawableLoader jLatextAsyncDrawableLoader;

    /* renamed from: io.noties.markwon.ext.latex.JLatexMathPlugin$1, reason: invalid class name */
    abstract class AnonymousClass1 {
    }

    public static class Builder {
        private boolean blocksEnabled;
        private boolean blocksLegacy;
        private ExecutorService executorService;
        private boolean inlinesEnabled;
        private final JLatexMathTheme.Builder theme;

        public Builder(JLatexMathTheme.Builder r2) {
            this.blocksEnabled = true;
            this.theme = r2;
        }

        public static /* synthetic */ JLatexMathTheme.Builder access$000(Builder r0) {
            return r0.theme;
        }

        public static /* synthetic */ boolean access$100(Builder r0) {
            return r0.blocksEnabled;
        }

        public static /* synthetic */ boolean access$200(Builder r0) {
            return r0.blocksLegacy;
        }

        public static /* synthetic */ boolean access$300(Builder r0) {
            return r0.inlinesEnabled;
        }

        public static /* synthetic */ ErrorHandler access$400(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ ExecutorService access$500(Builder r0) {
            return r0.executorService;
        }

        public Config build() {
            return new Config(this);
        }

        public Builder inlinesEnabled(boolean r1) {
            this.inlinesEnabled = r1;
            return this;
        }
    }

    public interface BuilderConfigure {
        void configureBuilder(Builder r1);
    }

    public static class Config {
        final boolean blocksEnabled;
        final boolean blocksLegacy;
        final ExecutorService executorService;
        final boolean inlinesEnabled;
        final JLatexMathTheme theme;

        public Config(Builder r2) {
            this.theme = Builder.access$000(r2).build();
            this.blocksEnabled = Builder.access$100(r2);
            this.blocksLegacy = Builder.access$200(r2);
            this.inlinesEnabled = Builder.access$300(r2);
            Builder.access$400(r2);
            ExecutorService r22 = Builder.access$500(r2);
            if (r22 != null) goto L5;
            r22 = Executors.newCachedThreadPool();
        L5:
            this.executorService = r22;
        }
    }

    public interface ErrorHandler {
    }

    public static class InlineImageSizeResolver extends ImageSizeResolver {
        private InlineImageSizeResolver() {
        }

        public /* synthetic */ InlineImageSizeResolver(AnonymousClass1 r1) {
            this();
        }
    }

    public static class JLatextAsyncDrawableLoader extends AsyncDrawableLoader {
        private final Map<Object, Future<?>> cache;
        private final Config config;
        private final Handler handler;

        public JLatextAsyncDrawableLoader(Config r3) {
            this.handler = new Handler(Looper.getMainLooper());
            this.cache = new HashMap(3);
            this.config = r3;
        }
    }

    public JLatexMathPlugin(Config r2) {
        this.config = r2;
        this.jLatextAsyncDrawableLoader = new JLatextAsyncDrawableLoader(r2);
        this.jLatexBlockImageSizeResolver = new JLatexBlockImageSizeResolver(r2.theme.blockFitCanvas());
        this.inlineImageSizeResolver = new InlineImageSizeResolver(null);
    }

    public static Builder builder(float r1) {
        return new Builder(JLatexMathTheme.builder(r1));
    }

    public static JLatexMathPlugin create(float r0, BuilderConfigure r1) {
        Builder r02 = builder(r0);
        r1.configureBuilder(r02);
        return new JLatexMathPlugin(r02.build());
    }

    public void configure(MarkwonPlugin.Registry r1) {
        if (this.config.inlinesEnabled == false) goto L6;
        ((MarkwonInlineParserPlugin) r1.require(MarkwonInlineParserPlugin.class)).factoryBuilder().addInlineProcessor(new JLatexMathInlineProcessor());
        return;
    }

    public void configureParser(Parser.Builder r2) {
        Config r1 = this.config;
        if (r1.blocksEnabled == true) goto L5;
        return;
    L5:
        if (r1.blocksLegacy == false) goto L8;
        r2.customBlockParserFactory(new JLatexMathBlockParserLegacy.Factory());
        return;
    L8:
        r2.customBlockParserFactory(new JLatexMathBlockParser.Factory());
    }
}
