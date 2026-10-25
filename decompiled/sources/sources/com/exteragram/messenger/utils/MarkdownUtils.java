package com.exteragram.messenger.utils;

import android.text.SpannableString;
import android.text.TextUtils;
import com.exteragram.messenger.components.PreformattedScrollView;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes4.dex */
public abstract class MarkdownUtils {
    private static final String[] MARKDOWN_MIME_PREFIXES = null;
    private static final String[] MARKDOWN_TEXT_EXTENSIONS = null;
    private static final HashMap<String, String> PREFORMATTED_EXTENSION_LANGUAGES = null;
    private static final HashMap<String, String> PREFORMATTED_FILENAMES = null;

    public static final class HighlightSource {
        private SpannableString highlighted;
        final String language;
        final PreformattedScrollView.Group scrollGroup;
        final String text;

        public HighlightSource(String r2, String r3) {
            this.scrollGroup = new PreformattedScrollView.Group();
            this.text = r2;
            this.language = r3;
        }

        public SpannableString getHighlighted() {
            if (this.highlighted != null) goto L6;
            this.highlighted = CodeHighlighting.getHighlighted(this.text, this.language);
        L6:
            return this.highlighted;
        }
    }

    public static final class PreformattedChunk extends TL_iv.pageBlockPreformatted {
        private final int end;
        private CharSequence highlightedText;
        public final boolean joinsNext;
        public final boolean joinsPrevious;
        private final String plainText;
        public final PreformattedScrollView.Group scrollGroup;
        private final HighlightSource source;
        private final int start;

        private PreformattedChunk(HighlightSource r4, int r5, int r6) {
            this.source = r4;
            this.start = r5;
            this.end = r6;
            String r0 = r4.text.substring(r5, r6);
            this.plainText = r0;
            boolean r1 = false;
            if (r5 <= 0) goto L5;
            boolean r52 = true;
        L6:
            this.joinsPrevious = r52;
            if (r6 >= r4.text.length()) goto L9;
            r1 = true;
        L9:
            this.joinsNext = r1;
            this.scrollGroup = r4.scrollGroup;
            this.text = MarkdownUtils.m1766$$Nest$smplain(r0);
            this.language = r4.language;
            return;
        L5:
            r52 = false;
            goto L6
        }

        public /* synthetic */ PreformattedChunk(HighlightSource r1, int r2, int r3, MarkdownUtilsIA r4) {
            this(r1, r2, r3);
        }

        public CharSequence getHighlightedText() {
            if (this.highlightedText != null) goto L12;
            SpannableString r0 = this.source.getHighlighted();
            if ((r0 instanceof CodeHighlighting.LockedSpannableString) == true) goto L7;
        L10:
            this.highlightedText = r0.subSequence(this.start, this.end);
            goto L12
        L7:
            if (((CodeHighlighting.LockedSpannableString) r0).ready == true) goto L10;
            return this.plainText;
        L12:
            return this.highlightedText;
        }
    }

    /* renamed from: -$$Nest$smplain, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichText m1766$$Nest$smplain(String r0) {
        return plain(r0);
    }

    static {
        MARKDOWN_TEXT_EXTENSIONS = new String[]{"txt", "text"};
        MARKDOWN_MIME_PREFIXES = new String[]{"text/plain", "text/x-diff", "text/x-patch", "text/csv", "text/xml", "text/yaml", "text/x-yaml", "text/css", "text/javascript", "application/json", "application/ld+json", "application/json5", "application/xml", "application/yaml", "application/x-yaml", "application/javascript", "application/x-javascript", "application/x-sh"};
        PREFORMATTED_EXTENSION_LANGUAGES = new HashMap();
        PREFORMATTED_FILENAMES = new HashMap();
        addLanguage("plain", new String[]{"log"});
        addLanguage("diff", new String[]{"diff", "patch"});
        addLanguage("json", new String[]{"json", "webmanifest"});
        addLanguage("json5", new String[]{"json5"});
        addLanguage("xml", new String[]{"xml", "rss", "atom"});
        addLanguage("svg", new String[]{"svg"});
        addLanguage("html", new String[]{"html", "htm", "xhtml"});
        addLanguage("css", new String[]{"css"});
        addLanguage("scss", new String[]{"scss"});
        addLanguage("sass", new String[]{"sass"});
        addLanguage("less", new String[]{"less"});
        addLanguage("javascript", new String[]{"js", "mjs", "cjs"});
        addLanguage("jsx", new String[]{"jsx"});
        addLanguage("typescript", new String[]{HlsSegmentFormat.TS});
        addLanguage("tsx", new String[]{"tsx"});
        addLanguage("java", new String[]{"java"});
        addLanguage("kotlin", new String[]{"kt", "kts"});
        addLanguage("gradle", new String[]{"gradle"});
        addLanguage("groovy", new String[]{"groovy"});
        addLanguage(PluginsConstants.PYTHON, new String[]{"py", "pyw", "plugin"});
        addLanguage("bash", new String[]{"sh", "bash", "zsh", "fish", "shell"});
        addLanguage("powershell", new String[]{"ps1", "psm1", "psd1"});
        addLanguage("batch", new String[]{"bat", "cmd"});
        addLanguage("sql", new String[]{"sql"});
        addLanguage("yaml", new String[]{"yaml", "yml"});
        addLanguage("ini", new String[]{"ini", "toml", "properties", "props", "conf", "cfg", "config", "env", "dotenv"});
        addLanguage("csv", new String[]{"csv", "tsv"});
        addLanguage("docker", new String[]{"dockerfile"});
        addLanguage("makefile", new String[]{"make", "mk", "mak"});
        addLanguage("cmake", new String[]{"cmake"});
        addLanguage("go", new String[]{"go"});
        addLanguage("rust", new String[]{"rs"});
        addLanguage("swift", new String[]{"swift"});
        addLanguage("dart", new String[]{"dart"});
        addLanguage("php", new String[]{"php", "phtml"});
        addLanguage("ruby", new String[]{"rb", "gemspec"});
        addLanguage("c", new String[]{"c"});
        addLanguage("cpp", new String[]{"h", "hh", "hpp", "hxx", "cpp", "cc", "cxx"});
        addLanguage("csharp", new String[]{"cs"});
        addLanguage("fsharp", new String[]{"fs", "fsx"});
        addLanguage("visual-basic", new String[]{"vb", "vba"});
        addLanguage("lua", new String[]{"lua"});
        addLanguage("perl", new String[]{"pl", "pm"});
        addLanguage("r", new String[]{"r"});
        addLanguage("scala", new String[]{"scala"});
        addLanguage("haskell", new String[]{"hs"});
        addLanguage("elixir", new String[]{"ex", "exs"});
        addLanguage("erlang", new String[]{"erl", "hrl"});
        addLanguage("protobuf", new String[]{"proto", "protobuf"});
        addLanguage("graphql", new String[]{"graphql", "gql"});
        addLanguage("glsl", new String[]{"glsl", "vert", "frag", "geom", "comp"});
        addLanguage("http", new String[]{"http"});
        addFilename("docker", new String[]{"Dockerfile"});
        addFilename("makefile", new String[]{"Makefile", "GNUmakefile"});
        addFilename("cmake", new String[]{"CMakeLists.txt"});
        addFilename("git", new String[]{".gitignore", ".gitattributes", ".gitmodules"});
        addFilename("docker", new String[]{".dockerignore"});
        addFilename("ini", new String[]{".editorconfig", ".env"});
    }

    private static void addFilename(String r5, String... r6) {
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        String r2 = r6[r1];
        PREFORMATTED_FILENAMES.put(r2.toLowerCase(Locale.ROOT), r5);
        r1 = r1 + 1;
        goto L3
    }

    private static void addLanguage(String r4, String... r5) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        String r2 = r5[r1];
        PREFORMATTED_EXTENSION_LANGUAGES.put(r2, r4);
        r1 = r1 + 1;
        goto L3
    }

    public static void appendPreformattedBlocks(List<TL_iv.PageBlock> r4, String r5, String r6, int r7) {
        int r72 = Math.max(1, r7);
        if (TextUtils.isEmpty(r5) == false) goto L10;
        TL_iv.pageBlockPreformatted r52 = new TL_iv.pageBlockPreformatted();
        r52.text = plain(_UrlKt.FRAGMENT_ENCODE_SET);
        if (r6 != null) goto L7;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        r52.language = r6;
        r4.add(r52);
        return;
    L10:
        if (r6 != null) goto L12;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        HighlightSource r0 = new HighlightSource(r5, r6);
        int r62 = 0;
    L14:
        if (r62 >= r5.length()) goto L21;
        int r1 = Math.min(r5.length(), r62 + r72);
        if (r1 >= r5.length()) goto L20;
        int r2 = r5.lastIndexOf(10, r1 - 1);
        if (r2 <= r62) goto L20;
        r1 = r2 + 1;
    L20:
        r4.add(new PreformattedChunk(r0, r62, r1, null));
        r62 = r1;
        goto L14
    }

    private static String getBaseName(String r2) {
        int r0 = Math.max(r2.lastIndexOf(47), r2.lastIndexOf(92));
        if (r0 >= 0) goto L5;
        return r2;
    L5:
        return r2.substring(r0 + 1);
    }

    private static String getExtensionFromFileName(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r32 = getBaseName(r3);
        int r0 = r32.lastIndexOf(46);
        if (r0 > 0) goto L8;
    L12:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        if (r0 >= (r32.length() - 1)) goto L12;
        return normalizeExtension(r32.substring(r0 + 1));
    }

    public static String getPreformattedLanguage(String r2, String r3, String r4) {
        String r0 = getPreformattedLanguageByFileName(r2);
        if (TextUtils.isEmpty(r0) == true) goto L5;
        return r0;
    L5:
        String r32 = normalizeExtension(r3);
        if (TextUtils.isEmpty(r32) == false) goto L8;
        r32 = getExtensionFromFileName(r2);
    L8:
        String r22 = PREFORMATTED_EXTENSION_LANGUAGES.get(r32);
        if (TextUtils.isEmpty(r22) == true) goto L12;
        return r22;
    L12:
        if (TextUtils.isEmpty(r4) == true) goto L70;
        String r23 = r4.toLowerCase(Locale.ROOT);
        if (r23.startsWith("text/x-diff") == false) goto L16;
        return "diff";
    L16:
        if (r23.startsWith("text/x-patch") == false) goto L19;
        return "diff";
    L19:
        if (r23.startsWith("text/csv") == false) goto L23;
        return "csv";
    L23:
        if (r23.startsWith("text/xml") == false) goto L25;
        return "xml";
    L25:
        if (r23.startsWith("application/xml") == false) goto L28;
        return "xml";
    L28:
        if (r23.startsWith("application/json5") == false) goto L32;
        return "json5";
    L32:
        if (r23.startsWith("application/json") == false) goto L34;
        return "json";
    L34:
        if (r23.startsWith("application/ld+json") == false) goto L37;
        return "json";
    L37:
        if (r23.startsWith("text/yaml") == false) goto L39;
        return "yaml";
    L39:
        if (r23.startsWith("text/x-yaml") == false) goto L41;
        return "yaml";
    L41:
        if (r23.startsWith("application/yaml") == false) goto L43;
        return "yaml";
    L43:
        if (r23.startsWith("application/x-yaml") == false) goto L46;
        return "yaml";
    L46:
        if (r23.startsWith("text/css") == false) goto L50;
        return "css";
    L50:
        if (r23.startsWith("text/javascript") == false) goto L52;
        return "javascript";
    L52:
        if (r23.startsWith("application/javascript") == false) goto L54;
        return "javascript";
    L54:
        if (r23.startsWith("application/x-javascript") == false) goto L57;
        return "javascript";
    L57:
        if (r23.startsWith("application/x-sh") == false) goto L80;
        return "bash";
    L80:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L70:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static String getPreformattedLanguageByFileName(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r32 = getBaseName(r3).toLowerCase(Locale.ROOT);
        String r0 = PREFORMATTED_FILENAMES.get(r32);
        if (TextUtils.isEmpty(r0) == true) goto L9;
        return r0;
    L9:
        if (r32.startsWith("dockerfile.") == false) goto L13;
        return "docker";
    L13:
        if (r32.startsWith("makefile.") == false) goto L17;
        return "makefile";
    L17:
        if (r32.startsWith(".env.") == false) goto L20;
        return "ini";
    L20:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static boolean isExteraMarkdown(MessageObject r3) {
        if (r3 != null) goto L5;
    L17:
        return false;
    L5:
        if (isTheme(r3) == true) goto L17;
        String r1 = FileLoader.getDocumentFileName(r3.getDocument());
        if (isExteraMarkdownExtension(r3.getExtension()) == false) goto L10;
        return true;
    L10:
        if (isExteraMarkdownMime(r3.getMimeType()) == false) goto L12;
        return true;
    L12:
        if (TextUtils.isEmpty(getPreformattedLanguage(r1, r3.getExtension(), r3.getMimeType())) == false) goto L19;
        return false;
    L19:
        return true;
    }

    public static boolean isExteraMarkdownExtension(String r2) {
        if (isMarkdownTextExtension(r2) == false) goto L6;
        return true;
    L6:
        return !TextUtils.isEmpty(getPreformattedLanguage(null, r2, null));
    }

    public static boolean isExteraMarkdownMime(String r5) {
        if (TextUtils.isEmpty(r5) == false) goto L5;
        return false;
    L5:
        String r52 = r5.toLowerCase(Locale.ROOT);
        String[] r0 = MARKDOWN_MIME_PREFIXES;
        int r2 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L12;
        if (r52.startsWith(r0[r3]) == true) goto L9;
        r3 = r3 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static boolean isMarkdownTextExtension(String r5) {
        String r52 = normalizeExtension(r5);
        String[] r0 = MARKDOWN_TEXT_EXTENSIONS;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L9;
        if (r0[r3].equals(r52) == true) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static boolean isTheme(MessageObject r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        String r22 = FileLoader.getDocumentFileName(r2.getDocument());
        if (TextUtils.isEmpty(r22) == false) goto L8;
    L11:
        return false;
    L8:
        if (r22.toLowerCase(Locale.ROOT).endsWith("attheme") == false) goto L11;
        return true;
    }

    private static String normalizeExtension(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r12 = r1.trim();
    L8:
        if (r12.startsWith(".") == false) goto L11;
        r12 = r12.substring(1);
        goto L8
    L11:
        return r12.toLowerCase(Locale.ROOT);
    }

    private static TL_iv.RichText plain(String r1) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.text = r1;
        return r0;
    }
}
