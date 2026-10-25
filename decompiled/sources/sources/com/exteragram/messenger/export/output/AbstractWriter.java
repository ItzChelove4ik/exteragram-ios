package com.exteragram.messenger.export.output;

import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import com.exteragram.messenger.export.output.htmlAndJson.HtmlAndJsonWriter;
import com.exteragram.messenger.export.output.json.JsonWriter;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class AbstractWriter {

    /* renamed from: com.exteragram.messenger.export.output.AbstractWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$output$AbstractWriter$Format = null;

        static {
            int[] r0 = new int[Format.values().length];
            $SwitchMap$com$exteragram$messenger$export$output$AbstractWriter$Format = r0;
            r0[Format.Html.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$com$exteragram$messenger$export$output$AbstractWriter$Format[Format.Json.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$exteragram$messenger$export$output$AbstractWriter$Format[Format.HtmlAndJson.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public enum Format extends Enum<Format> {
        private static final /* synthetic */ Format[] $VALUES = null;
        public static final Format Html = null;
        public static final Format HtmlAndJson = null;
        public static final Format Json = null;

        private static /* synthetic */ Format[] $values() {
            return new Format[]{Html, Json, HtmlAndJson};
        }

        static {
            Html = new Format("Html", 0);
            Json = new Format("Json", 1);
            HtmlAndJson = new Format("HtmlAndJson", 2);
            $VALUES = $values();
        }

        Format(String r1, int r2) {
        }

        public static Format valueOf(String r1) {
            return (Format) Enum.valueOf(Format.class, r1);
        }

        public static Format[] values() {
            return (Format[]) $VALUES.clone();
        }
    }

    public static class Result {
        String path;
        Type type;

        public enum Type extends Enum<Type> {
            private static final /* synthetic */ Type[] $VALUES = null;
            public static final Type Error = null;
            public static final Type FatalError = null;
            public static final Type Success = null;

            private static /* synthetic */ Type[] $values() {
                return new Type[]{Success, Error, FatalError};
            }

            static {
                Success = new Type("Success", 0);
                Error = new Type("Error", 1);
                FatalError = new Type("FatalError", 2);
                $VALUES = $values();
            }

            Type(String r1, int r2) {
            }

            public static Type valueOf(String r1) {
                return (Type) Enum.valueOf(Type.class, r1);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }
        }

        public Result(Type r1, String r2) {
            this.type = r1;
            this.path = r2;
        }

        public static Result Error() {
            return new Result(Type.Error, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        public static Result Success() {
            return new Result(Type.Success, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        public boolean isSuccess() {
            if (this.type != Type.Success) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public AbstractWriter() {
    }

    public static AbstractWriter CreateWriter(Format r1) {
        int r12 = AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$output$AbstractWriter$Format[r1.ordinal()];
        if (r12 == 1) goto L15;
        if (r12 == 2) goto L13;
        if (r12 != 3) goto L11;
        return new HtmlAndJsonWriter();
    L11:
        throw new IncompatibleClassChangeError();
    L13:
        return new JsonWriter();
    L15:
        return new HtmlWriter();
    }

    public abstract Result finish();

    public abstract String mainFilePath();

    public abstract Result start(ExportSettings r1, OutputFile.Stats r2);

    public abstract Result writeContactsList(ApiWrap$ContactsList r1);

    public abstract Result writeDialogEnd();

    public abstract Result writeDialogSlice(ApiWrap$MessagesSlice r1);

    public abstract Result writeDialogStart(ApiWrap$DialogInfo r1);

    public abstract Result writeDialogsEnd();

    public abstract Result writeDialogsStart(ApiWrap$DialogsInfo r1);

    public abstract Result writeOtherData(ApiWrap$File r1);

    public abstract Result writePersonal(ApiWrap$ExportPersonalInfo r1);

    public abstract Result writeSessionsList(ApiWrap$SessionsList r1);

    public abstract Result writeStoriesEnd();

    public abstract Result writeStoriesSlice(ApiWrap$StoriesSlice r1);

    public abstract Result writeStoriesStart(int r1);

    public abstract Result writeUserpicsEnd();

    public abstract Result writeUserpicsSlice(ArrayList<HtmlWriter.Photo> r1);

    public abstract Result writeUserpicsStart(ApiWrap$UserpicsInfo r1);
}
