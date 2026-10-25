.class public final Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;,
        Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u0007\n\u0002\u0010\u0002\n\u0002\u0008\u0008\n\u0002\u0010\u001e\n\u0002\u0008\u0002\n\u0002\u0010 \n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0011\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0008\u0018\u0000 :2\u00020\u0001:\u00029:B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0013J\u0012\u0010 \u001a\u0004\u0018\u00010\n2\u0008\u0010!\u001a\u0004\u0018\u00010\nJ\u0014\u0010\"\u001a\u00020\u001b2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0$J\u0016\u0010%\u001a\u00020\u001b2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0$H\u0002J\u001e\u0010&\u001a\u00020\u001b2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0\'2\u0006\u0010(\u001a\u00020\nH\u0002J:\u0010)\u001a\u00020\u001b2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0\'2\u0006\u0010(\u001a\u00020\n2\u001a\u0010*\u001a\u0016\u0012\u000c\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\'\u0012\u0004\u0012\u00020\u001b0+H\u0002Je\u0010,\u001a\u00020\u001b2\u0012\u0010-\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020.0\'0\'2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0\'2\u0006\u0010(\u001a\u00020\n2\u000e\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n002\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020.2\u000c\u0010*\u001a\u0008\u0012\u0004\u0012\u00020\u001b03H\u0002\u00a2\u0006\u0002\u00104JW\u00105\u001a\u00020\u001b2\u000c\u00106\u001a\u0008\u0012\u0004\u0012\u00020.0\'2\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020\n0\'2\u0006\u0010(\u001a\u00020\n2\u000e\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n002\u0006\u00101\u001a\u00020\u00132\u000c\u0010*\u001a\u0008\u0012\u0004\u0012\u00020\u001b03H\u0002\u00a2\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u0013H\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\u0008\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\u0008\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000c\u001a\u0012\u0012\u0004\u0012\u00020\n0\rj\u0008\u0012\u0004\u0012\u00020\n`\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\n0\rj\u0008\u0012\u0004\u0012\u00020\n`\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\u0008\u0018\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\u0008\u0019\u0010\u0017\u00a8\u0006;"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;",
        "",
        "<init>",
        "()V",
        "listeners",
        "Ljava/util/ArrayList;",
        "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;",
        "Lkotlin/collections/ArrayList;",
        "translations",
        "Ljava/util/HashMap;",
        "",
        "Lkotlin/collections/HashMap;",
        "pending",
        "Ljava/util/HashSet;",
        "Lkotlin/collections/HashSet;",
        "failed",
        "targetLanguage",
        "detectedLanguage",
        "detecting",
        "",
        "value",
        "enabled",
        "getEnabled",
        "()Z",
        "isLoading",
        "isTranslatable",
        "addListener",
        "",
        "listener",
        "removeListener",
        "resetFailed",
        "setEnabled",
        "text",
        "original",
        "check",
        "texts",
        "",
        "detect",
        "request",
        "",
        "language",
        "translate",
        "done",
        "Lkotlin/Function1;",
        "requestEach",
        "chunks",
        "",
        "results",
        "",
        "retry",
        "position",
        "Lkotlin/Function0;",
        "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V",
        "requestChunk",
        "chunk",
        "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V",
        "notifyListeners",
        "Listener",
        "Companion",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nPluginSettingsTranslation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1#2:244\n777#3:245\n873#3,2:246\n1924#3,3:248\n1924#3,3:251\n1924#3,3:254\n1586#3:257\n1661#3,3:258\n1586#3:261\n1661#3,3:262\n*S KotlinDebug\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation\n*L\n93#1:245\n93#1:246,2\n123#1:248,3\n213#1:251,3\n190#1:254,3\n205#1:257\n205#1:258,3\n209#1:261\n209#1:262,3\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;

.field private static final DETECTION_SAMPLE_LENGTH:I = 0x7d0

.field private static final MAX_TEXTS_PER_REQUEST:I = 0x14

.field private static final TRANSLATION_TAG:J = -0x7fffffffffffffffL

.field private static final instances:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private detectedLanguage:Ljava/lang/String;

.field private detecting:Z

.field private enabled:Z

.field private final failed:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final listeners:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;",
            ">;"
        }
    .end annotation
.end field

.field private final pending:Ljava/util/HashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private targetLanguage:Ljava/lang/String;

.field private final translations:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static $r8$lambda$3KJzwSQq1gHWVma1Zl0vBkVJAcM(Lorg/telegram/tgnet/TLObject;Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 8

    move-object/from16 v0, p8

    .line 187
    instance-of v1, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateResult;

    const/16 v2, 0xa

    if-eqz v1, :cond_9

    .line 188
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 189
    check-cast p0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateResult;

    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateResult;->result:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x0

    if-ne v1, v3, :cond_4

    .line 190
    check-cast p1, Ljava/lang/Iterable;

    .line 1925
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    move v1, v4

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v5, v1, 0x1

    if-gez v1, :cond_0

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_0
    check-cast v3, Ljava/lang/Number;

    invoke-virtual {v3}, Ljava/lang/Number;->intValue()I

    move-result v3

    .line 191
    iget-object v6, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateResult;->result:Ljava/util/ArrayList;

    invoke-virtual {v6, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    if-eqz v1, :cond_1

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;->text:Ljava/lang/String;

    goto :goto_1

    :cond_1
    const/4 v1, 0x0

    :goto_1
    if-eqz v1, :cond_3

    .line 192
    invoke-static {v1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_2

    goto :goto_2

    .line 195
    :cond_2
    aput-object v1, p7, v3

    goto :goto_3

    .line 193
    :cond_3
    :goto_2
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_3
    move v1, v5

    goto :goto_0

    .line 199
    :cond_4
    check-cast p1, Ljava/util/Collection;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    :cond_5
    if-eqz p2, :cond_8

    .line 201
    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_6

    goto :goto_5

    .line 1586
    :cond_6
    new-instance v1, Ljava/util/ArrayList;

    invoke-static {v0, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result p0

    invoke-direct {v1, p0}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    :goto_4
    if-ge v4, p0, :cond_7

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    add-int/lit8 v4, v4, 0x1

    .line 1662
    check-cast p1, Ljava/lang/Number;

    invoke-virtual {p1}, Ljava/lang/Number;->intValue()I

    move-result p1

    .line 205
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    .line 1662
    invoke-interface {v1, p1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_4

    :cond_7
    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v7, p3

    move-object v0, p4

    move-object v2, p5

    move-object v3, p6

    move-object v4, p7

    .line 205
    invoke-direct/range {v0 .. v7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->requestEach(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V

    return-void

    .line 202
    :cond_8
    :goto_5
    invoke-interface {p3}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void

    :cond_9
    if-eqz v0, :cond_b

    .line 206
    const-string p2, "TRANSLATIONS_DISABLED_ALT"

    iget-object p4, v0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 v0, 0x1

    invoke-static {p2, p4, v0}, Lkotlin/text/StringsKt;->equals(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result p2

    if-eqz p2, :cond_b

    .line 209
    move-object p2, p1

    check-cast p2, Ljava/lang/Iterable;

    .line 1586
    new-instance p4, Ljava/util/ArrayList;

    invoke-static {p2, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v0

    invoke-direct {p4, v0}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_6
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_a

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 1662
    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    .line 209
    invoke-interface {p5, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 1662
    invoke-interface {p4, v0}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_6

    .line 211
    :cond_a
    sget-object p0, Lcom/exteragram/messenger/translator/providers/GoogleTranslator;->Companion:Lcom/exteragram/messenger/translator/providers/GoogleTranslator$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/translator/providers/GoogleTranslator$Companion;->getInstance()Lcom/exteragram/messenger/translator/providers/GoogleTranslator;

    move-result-object p0

    .line 207
    new-instance p5, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;

    invoke-direct {p5, p1, p3, p7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;-><init>(Ljava/util/List;Lkotlin/jvm/functions/Function0;[Ljava/lang/String;)V

    const-wide p1, -0x7fffffffffffffffL    # -4.9E-324

    move-object p3, p4

    move-object p4, p0

    move-wide p0, p1

    move-object p2, p3

    move-object p3, p6

    invoke-static/range {p0 .. p5}, Lcom/exteragram/messenger/translator/ChatTranslator;->translate(JLjava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/translator/core/BaseTranslator;Lcom/exteragram/messenger/translator/ChatTranslator$Callback;)V

    return-void

    .line 219
    :cond_b
    invoke-interface {p3}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$3cblPoTowmec5WOLdm2av0zUsqw(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/lang/String;)V
    .locals 1

    .line 105
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda2;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda2;-><init>(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$6sDJjyrsuB15zfDuAj74Z4Tce9A(Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 10

    .line 186
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda8;

    move-object v2, p0

    move v3, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v7, p5

    move-object/from16 v8, p6

    move-object/from16 v1, p7

    move-object/from16 v9, p8

    invoke-direct/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda8;-><init>(Lorg/telegram/tgnet/TLObject;Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static $r8$lambda$BhislwJ_w_bX3GhCIz9I8vwOc8g(Ljava/util/List;Lkotlin/jvm/functions/Function0;[Ljava/lang/String;Ljava/util/List;Lcom/exteragram/messenger/translator/core/TranslationError;)V
    .locals 2

    .line 213
    check-cast p0, Ljava/lang/Iterable;

    .line 1925
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    const/4 p4, 0x0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    add-int/lit8 v1, p4, 0x1

    if-gez p4, :cond_0

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_0
    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    .line 214
    invoke-static {p3, p4}, Lkotlin/collections/CollectionsKt;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p4

    aput-object p4, p2, v0

    move p4, v1

    goto :goto_0

    .line 216
    :cond_1
    invoke-interface {p1}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$D-96Ah5Br8D_rDrlskkOU_mZsZE(Lkotlin/jvm/functions/Function1;Ljava/util/List;Lcom/exteragram/messenger/translator/core/TranslationError;)V
    .locals 0

    .line 143
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public static $r8$lambda$DNIlH7lxxikJOF6XvGqwVcxihjI(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)Lkotlin/Unit;
    .locals 0

    add-int/lit8 p6, p6, 0x1

    .line 166
    invoke-direct/range {p0 .. p7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->requestEach(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V

    .line 167
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$GmVCDGY-KoJLxcaC_JAKQEYoydA(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)Lkotlin/Unit;
    .locals 6

    .line 117
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->pending:Ljava/util/HashSet;

    check-cast p1, Ljava/lang/Iterable;

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/AbstractCollection;->removeAll(Ljava/util/Collection;)Z

    .line 118
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->targetLanguage:Ljava/lang/String;

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    const/4 v0, 0x0

    if-nez p2, :cond_0

    .line 119
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->notifyListeners(Z)V

    .line 120
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 1925
    :cond_0
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    move p2, v0

    move v1, p2

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    const/4 v3, 0x1

    if-eqz v2, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v4, v1, 0x1

    if-gez v1, :cond_1

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_1
    check-cast v2, Ljava/lang/String;

    .line 124
    invoke-static {p3, v1}, Lkotlin/collections/CollectionsKt;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-eqz v1, :cond_3

    .line 125
    invoke-static {v1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_2

    goto :goto_1

    .line 129
    :cond_2
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    invoke-interface {v3, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_2

    .line 126
    :cond_3
    :goto_1
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    invoke-virtual {p2, v2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move p2, v3

    :goto_2
    move v1, v4

    goto :goto_0

    :cond_4
    if-eqz p2, :cond_5

    .line 132
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    invoke-virtual {p1}, Ljava/util/HashMap;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_5

    goto :goto_3

    :cond_5
    move v3, v0

    :goto_3
    if-eqz v3, :cond_6

    .line 134
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    .line 136
    :cond_6
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->notifyListeners(Z)V

    .line 137
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$PTUdpZwtDYtdYwXecXf41YtewTI(Ljava/lang/String;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;)V
    .locals 1

    if-eqz p0, :cond_1

    .line 106
    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const-string v0, "und"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 107
    iput-object p0, p1, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detectedLanguage:Ljava/lang/String;

    const/4 p0, 0x0

    .line 108
    invoke-direct {p1, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->notifyListeners(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method public static $r8$lambda$vM607UZKvUJyPyzilOIBKU_9N4g(Lkotlin/jvm/functions/Function1;[Ljava/lang/String;)Lkotlin/Unit;
    .locals 0

    .line 149
    invoke-static {p1}, Lkotlin/collections/ArraysKt;->toList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->Companion:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;

    .line 236
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->instances:Ljava/util/HashMap;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->listeners:Ljava/util/ArrayList;

    .line 32
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    .line 33
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->pending:Ljava/util/HashSet;

    .line 34
    new-instance v0, Ljava/util/HashSet;

    invoke-direct {v0}, Ljava/util/HashSet;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;-><init>()V

    return-void
.end method

.method public static final synthetic access$getInstances$cp()Ljava/util/HashMap;
    .locals 1

    .line 25
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->instances:Ljava/util/HashMap;

    return-object v0
.end method

.method private final detect(Ljava/util/Collection;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    const/4 v0, 0x1

    .line 102
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detecting:Z

    .line 103
    move-object v1, p1

    check-cast v1, Ljava/lang/Iterable;

    const/16 v8, 0x3e

    const/4 v9, 0x0

    const-string v2, "\n"

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v1 .. v9}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const/16 v0, 0x7d0

    invoke-static {p1, v0}, Lkotlin/text/StringsKt;->take(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    .line 104
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;)V

    const/4 p0, 0x0

    invoke-static {p1, v0, p0}, Lorg/telegram/messenger/LanguageDetector;->detectLanguage(Ljava/lang/String;Lorg/telegram/messenger/LanguageDetector$StringCallback;Lorg/telegram/messenger/LanguageDetector$ExceptionCallback;)V

    return-void
.end method

.method private final notifyListeners(Z)V
    .locals 1

    .line 226
    new-instance v0, Ljava/util/ArrayList;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->listeners:Ljava/util/ArrayList;

    invoke-direct {v0, p0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;

    .line 227
    invoke-interface {v0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;->onTranslationChanged(Z)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static final of(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->Companion:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;->of(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    move-result-object p0

    return-object p0
.end method

.method private final request(Ljava/util/List;Ljava/lang/String;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .line 115
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->pending:Ljava/util/HashSet;

    move-object v1, p1

    check-cast v1, Ljava/util/Collection;

    invoke-virtual {v0, v1}, Ljava/util/AbstractCollection;->addAll(Ljava/util/Collection;)Z

    .line 116
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;)V

    invoke-direct {p0, p1, p2, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translate(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V

    return-void
.end method

.method private final requestChunk(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V
    .locals 13
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/String;",
            "Z",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 178
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;-><init>()V

    .line 179
    iget v1, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;->flags:I

    or-int/lit8 v1, v1, 0x2

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;->flags:I

    .line 180
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    .line 181
    iget-object v3, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;->text:Ljava/util/ArrayList;

    new-instance v4, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    invoke-direct {v4}, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;-><init>()V

    invoke-interface {p2, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    iput-object v2, v4, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;->text:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    move-object/from16 v11, p3

    .line 183
    iput-object v11, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_translateText;->to_lang:Ljava/lang/String;

    .line 185
    sget v1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v1

    new-instance v5, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;

    move-object v9, p0

    move-object v6, p1

    move-object v10, p2

    move-object/from16 v12, p4

    move/from16 v7, p5

    move-object/from16 v8, p6

    invoke-direct/range {v5 .. v12}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;-><init>(Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;)V

    invoke-virtual {v1, v0, v5}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void
.end method

.method private final requestEach(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;>;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/String;",
            "ZI",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    move/from16 v7, p6

    .line 161
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-ne v7, v0, :cond_0

    .line 162
    invoke-interface/range {p7 .. p7}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void

    .line 165
    :cond_0
    invoke-interface {p1, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    move-object v9, v0

    check-cast v9, Ljava/util/List;

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda4;

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v5, p4

    move v6, p5

    move-object/from16 v8, p7

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V

    move-object v7, v0

    move-object v2, v9

    invoke-direct/range {v1 .. v7}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->requestChunk(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V

    return-void
.end method

.method private final translate(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 141
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->getCurrentTranslator()Lcom/exteragram/messenger/translator/core/BaseTranslator;

    move-result-object v0

    .line 142
    instance-of v0, v0, Lcom/exteragram/messenger/translator/core/HttpTranslator;

    if-eqz v0, :cond_0

    .line 143
    new-instance p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda6;

    invoke-direct {p0, p3}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda6;-><init>(Lkotlin/jvm/functions/Function1;)V

    const-wide v0, -0x7fffffffffffffffL    # -4.9E-324

    invoke-static {v0, v1, p1, p2, p0}, Lcom/exteragram/messenger/translator/ChatTranslator;->translate(JLjava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/translator/ChatTranslator$Callback;)V

    return-void

    .line 147
    :cond_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    new-array v5, v0, [Ljava/lang/String;

    .line 148
    move-object v0, p1

    check-cast v0, Ljava/util/Collection;

    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->getIndices(Ljava/util/Collection;)Lkotlin/ranges/IntRange;

    move-result-object v0

    const/16 v1, 0x14

    invoke-static {v0, v1}, Lkotlin/collections/CollectionsKt;->chunked(Ljava/lang/Iterable;I)Ljava/util/List;

    move-result-object v2

    .line 149
    new-instance v8, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda7;

    invoke-direct {v8, p3, v5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda7;-><init>(Lkotlin/jvm/functions/Function1;[Ljava/lang/String;)V

    const/4 v6, 0x1

    const/4 v7, 0x0

    move-object v1, p0

    move-object v3, p1

    move-object v4, p2

    invoke-direct/range {v1 .. v8}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->requestEach(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V

    return-void
.end method


# virtual methods
.method public final addListener(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;)V
    .locals 0

    .line 49
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->listeners:Ljava/util/ArrayList;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public final check(Ljava/util/Collection;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 79
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->getResolvedTargetLanguageCode()Ljava/lang/String;

    move-result-object v0

    .line 80
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->targetLanguage:Ljava/lang/String;

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 81
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->targetLanguage:Ljava/lang/String;

    .line 82
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->clear()V

    .line 83
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    invoke-virtual {v1}, Ljava/util/HashSet;->clear()V

    .line 86
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detectedLanguage:Ljava/lang/String;

    if-nez v1, :cond_1

    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detecting:Z

    if-nez v1, :cond_1

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_1

    invoke-static {}, Lorg/telegram/messenger/LanguageDetector;->hasSupport()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 87
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detect(Ljava/util/Collection;)V

    .line 90
    :cond_1
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    if-nez v1, :cond_2

    goto :goto_1

    .line 93
    :cond_2
    check-cast p1, Ljava/lang/Iterable;

    .line 777
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_3
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Ljava/lang/String;

    .line 94
    invoke-static {v3}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_3

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    invoke-interface {v4, v3}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_3

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->pending:Ljava/util/HashSet;

    invoke-virtual {v4, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_3

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    invoke-virtual {v4, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    .line 873
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 96
    :cond_4
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_5

    .line 97
    invoke-direct {p0, v1, v0}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->request(Ljava/util/List;Ljava/lang/String;)V

    :cond_5
    :goto_1
    return-void
.end method

.method public final getEnabled()Z
    .locals 0

    .line 39
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    return p0
.end method

.method public final isLoading()Z
    .locals 0

    .line 43
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->pending:Ljava/util/HashSet;

    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public final isTranslatable()Z
    .locals 0

    .line 46
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->detectedLanguage:Ljava/lang/String;

    if-eqz p0, :cond_0

    invoke-static {p0}, Lcom/exteragram/messenger/translator/TranslatorUtils;->isRestrictedLanguage(Ljava/lang/String;)Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final removeListener(Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;)V
    .locals 0

    .line 53
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->listeners:Ljava/util/ArrayList;

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    return-void
.end method

.method public final resetFailed()V
    .locals 0

    .line 57
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    invoke-virtual {p0}, Ljava/util/HashSet;->clear()V

    return-void
.end method

.method public final setEnabled(Z)V
    .locals 1

    .line 61
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    if-ne v0, p1, :cond_0

    return-void

    .line 64
    :cond_0
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    if-eqz p1, :cond_1

    .line 66
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->failed:Ljava/util/HashSet;

    invoke-virtual {p1}, Ljava/util/HashSet;->clear()V

    :cond_1
    const/4 p1, 0x0

    .line 68
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->notifyListeners(Z)V

    return-void
.end method

.method public final text(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 72
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->enabled:Z

    if-eqz v0, :cond_2

    if-nez p1, :cond_0

    goto :goto_0

    .line 75
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->translations:Ljava/util/HashMap;

    invoke-virtual {p0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-nez p0, :cond_1

    return-object p1

    :cond_1
    return-object p0

    :cond_2
    :goto_0
    return-object p1
.end method
