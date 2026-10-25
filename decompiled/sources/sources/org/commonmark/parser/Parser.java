package org.commonmark.parser;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.commonmark.Extension;
import org.commonmark.internal.DocumentParser;
import org.commonmark.internal.InlineParserContextImpl;
import org.commonmark.internal.InlineParserImpl;
import org.commonmark.node.Block;
import org.commonmark.node.Node;
import org.commonmark.parser.block.BlockParserFactory;
import org.commonmark.parser.delimiter.DelimiterProcessor;

/* loaded from: classes5.dex */
public class Parser {
    private final List<BlockParserFactory> blockParserFactories;
    private final List<DelimiterProcessor> delimiterProcessors;
    private final InlineParserFactory inlineParserFactory;
    private final List<Object> postProcessors;

    /* renamed from: org.commonmark.parser.Parser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Builder {
        private final List<BlockParserFactory> blockParserFactories;
        private final List<DelimiterProcessor> delimiterProcessors;
        private Set<Class<? extends Block>> enabledBlockTypes;
        private InlineParserFactory inlineParserFactory;
        private final List<Object> postProcessors;

        /* renamed from: org.commonmark.parser.Parser$Builder$1, reason: invalid class name */
        public class AnonymousClass1 implements InlineParserFactory {
            final /* synthetic */ Builder this$0;

            public AnonymousClass1(Builder r1) {
                this.this$0 = r1;
            }

            @Override // org.commonmark.parser.InlineParserFactory
            public InlineParser create(InlineParserContext r1) {
                return new InlineParserImpl(r1);
            }
        }

        public Builder() {
            this.blockParserFactories = new ArrayList();
            this.delimiterProcessors = new ArrayList();
            this.postProcessors = new ArrayList();
            this.enabledBlockTypes = DocumentParser.getDefaultBlockParserTypes();
        }

        public static /* synthetic */ List access$000(Builder r0) {
            return r0.blockParserFactories;
        }

        public static /* synthetic */ Set access$100(Builder r0) {
            return r0.enabledBlockTypes;
        }

        public static /* synthetic */ InlineParserFactory access$200(Builder r0) {
            return r0.getInlineParserFactory();
        }

        public static /* synthetic */ List access$300(Builder r0) {
            return r0.postProcessors;
        }

        public static /* synthetic */ List access$400(Builder r0) {
            return r0.delimiterProcessors;
        }

        private InlineParserFactory getInlineParserFactory() {
            InlineParserFactory r0 = this.inlineParserFactory;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return new AnonymousClass1(this);
        }

        public Parser build() {
            return new Parser(this, null);
        }

        public Builder customBlockParserFactory(BlockParserFactory r2) {
            if (r2 == null) goto L5;
            this.blockParserFactories.add(r2);
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("blockParserFactory must not be null");
            return null;
        }

        public Builder customDelimiterProcessor(DelimiterProcessor r2) {
            if (r2 == null) goto L5;
            this.delimiterProcessors.add(r2);
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("delimiterProcessor must not be null");
            return null;
        }

        public Builder extensions(Iterable<? extends Extension> r3) {
            if (r3 == null) goto L10;
            Iterator<? extends Extension> r32 = r3.iterator();
        L5:
            if (r32.hasNext() == false) goto L9;
            Extension r0 = r32.next();
            if ((r0 instanceof ParserExtension) == false) goto L5;
            ((ParserExtension) r0).extend(this);
            goto L5
        L9:
            return this;
        L10:
            f$$ExternalSyntheticBUOutline2.m("extensions must not be null");
            return null;
        }

        public Builder inlineParserFactory(InlineParserFactory r1) {
            this.inlineParserFactory = r1;
            return this;
        }
    }

    public interface ParserExtension extends Extension {
        void extend(Builder r1);
    }

    private Parser(Builder r3) {
        this.blockParserFactories = DocumentParser.calculateBlockParserFactories(Builder.access$000(r3), Builder.access$100(r3));
        InlineParserFactory r0 = Builder.access$200(r3);
        this.inlineParserFactory = r0;
        this.postProcessors = Builder.access$300(r3);
        List<DelimiterProcessor> r32 = Builder.access$400(r3);
        this.delimiterProcessors = r32;
        r0.create(new InlineParserContextImpl(r32, Collections.EMPTY_MAP));
    }

    public /* synthetic */ Parser(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static Builder builder() {
        return new Builder();
    }

    private DocumentParser createDocumentParser() {
        return new DocumentParser(this.blockParserFactories, this.inlineParserFactory, this.delimiterProcessors);
    }

    private Node postProcess(Node r2) {
        Iterator<Object> r1 = this.postProcessors.iterator();
        if (r1.hasNext() == true) goto L5;
        return r2;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    public Node parse(String r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("input must not be null");
        return null;
    L4:
        return postProcess(createDocumentParser().parse(r2));
    }
}
