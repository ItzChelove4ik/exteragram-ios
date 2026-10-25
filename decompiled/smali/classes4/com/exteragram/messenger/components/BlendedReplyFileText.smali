.class public final Lcom/exteragram/messenger/components/BlendedReplyFileText;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;,
        Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J&\u0010\u0008\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J \u0010\u0011\u001a\u00020\t2\u0006\u0010\u000c\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"
    }
    d2 = {
        "Lcom/exteragram/messenger/components/BlendedReplyFileText;",
        "",
        "<init>",
        "()V",
        "icons",
        "Ljava/util/EnumMap;",
        "Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;",
        "Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;",
        "build",
        "",
        "context",
        "Landroid/content/Context;",
        "reply",
        "Lorg/telegram/messenger/MessageObject;",
        "type",
        "fontMetrics",
        "Landroid/graphics/Paint$FontMetricsInt;",
        "formatCaption",
        "caption",
        "createIcon",
        "res",
        "",
        "AttachType",
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
        "SMAP\nBlendedReplySpans.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,172:1\n410#2,7:173\n1#3:180\n*S KotlinDebug\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText\n*L\n143#1:173,7\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;


# instance fields
.field private final icons:Ljava/util/EnumMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/EnumMap<",
            "Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;",
            "Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/components/BlendedReplyFileText;->Companion:Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 81
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 97
    new-instance v0, Ljava/util/EnumMap;

    const-class v1, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    invoke-direct {v0, v1}, Ljava/util/EnumMap;-><init>(Ljava/lang/Class;)V

    iput-object v0, p0, Lcom/exteragram/messenger/components/BlendedReplyFileText;->icons:Ljava/util/EnumMap;

    return-void
.end method

.method private final createIcon(Landroid/content/Context;I)Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;
    .locals 0

    .line 169
    new-instance p0, Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;

    invoke-static {p1, p2}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;-><init>(Landroid/graphics/drawable/Drawable;)V

    const p1, 0x3f333333    # 0.7f

    .line 170
    invoke-virtual {p0, p1, p1}, Lorg/telegram/ui/Components/ColoredImageSpan;->setScale(FF)V

    return-object p0
.end method

.method private final formatCaption(Lorg/telegram/messenger/MessageObject;Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;)Ljava/lang/CharSequence;
    .locals 7

    .line 159
    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    const/16 v0, 0x96

    invoke-static {p0, v0}, Lkotlin/text/StringsKt;->take(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v1

    const/4 v5, 0x4

    const/4 v6, 0x0

    const/16 v2, 0xa

    const/16 v3, 0x20

    const/4 v4, 0x0

    invoke-static/range {v1 .. v6}, Lkotlin/text/StringsKt;->replace$default(Ljava/lang/String;CCZILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x1

    invoke-static {p0, p3, v0}, Lorg/telegram/messenger/Emoji;->replaceEmoji(Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;Z)Ljava/lang/CharSequence;

    move-result-object p0

    .line 160
    iget-object p1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-eqz p1, :cond_1

    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    if-nez p1, :cond_0

    goto :goto_0

    .line 161
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1, p1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 162
    invoke-static {p0, v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->parseCustomEmojis(Ljava/lang/CharSequence;Ljava/util/ArrayList;)Z

    .line 163
    invoke-static {p0, v1, p3, v0}, Lorg/telegram/messenger/MessageObject;->replaceAnimatedEmoji(Ljava/lang/CharSequence;Ljava/util/ArrayList;Landroid/graphics/Paint$FontMetricsInt;Z)Landroid/text/Spannable;

    move-result-object p0

    .line 164
    invoke-static {p1, p2, p0}, Lorg/telegram/messenger/MediaDataController;->addTextStyleRuns(Ljava/util/ArrayList;Ljava/lang/CharSequence;Landroid/text/Spannable;)V

    :cond_1
    :goto_0
    return-object p0
.end method

.method public static final getType(Lorg/telegram/messenger/MessageObject;)Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/components/BlendedReplyFileText;->Companion:Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType(Lorg/telegram/messenger/MessageObject;)Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final build(Landroid/content/Context;Lorg/telegram/messenger/MessageObject;Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;Landroid/graphics/Paint$FontMetricsInt;)Ljava/lang/CharSequence;
    .locals 3

    .line 143
    iget-object v0, p0, Lcom/exteragram/messenger/components/BlendedReplyFileText;->icons:Ljava/util/EnumMap;

    .line 410
    invoke-interface {v0, p3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    if-nez v1, :cond_0

    .line 143
    invoke-virtual {p3}, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->getIconRes()I

    move-result v1

    invoke-direct {p0, p1, v1}, Lcom/exteragram/messenger/components/BlendedReplyFileText;->createIcon(Landroid/content/Context;I)Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;

    move-result-object v1

    .line 413
    invoke-interface {v0, p3, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 143
    :cond_0
    check-cast v1, Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;

    .line 145
    sget-object p1, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->MUSIC:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    if-ne p3, p1, :cond_1

    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->getMusicAuthor()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->getMusicTitle()Ljava/lang/String;

    move-result-object p3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " \u2013 "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 146
    :cond_1
    invoke-virtual {p3}, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->getStringRes()Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_2

    invoke-virtual {p3}, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->getStringRes()Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 147
    :cond_2
    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->getDocumentName()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_4

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p3

    if-lez p3, :cond_3

    goto :goto_0

    :cond_3
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_5

    :cond_4
    sget p1, Lorg/telegram/messenger/R$string;->AttachDocument:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    .line 150
    :cond_5
    :goto_1
    new-instance p3, Landroid/text/SpannableStringBuilder;

    const-string v0, "d "

    invoke-direct {p3, v0}, Landroid/text/SpannableStringBuilder;-><init>(Ljava/lang/CharSequence;)V

    const/4 v0, 0x0

    invoke-static {p1, p4, v0}, Lorg/telegram/messenger/Emoji;->replaceEmoji(Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;Z)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p3, p1}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object p1

    const/4 p3, 0x1

    const/16 v2, 0x21

    .line 151
    invoke-virtual {p1, v1, v0, p3, v2}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 152
    new-instance p3, Lcom/exteragram/messenger/components/BlendedReplyColorSpan;

    invoke-direct {p3}, Lcom/exteragram/messenger/components/BlendedReplyColorSpan;-><init>()V

    invoke-virtual {p1}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v1

    invoke-virtual {p1, p3, v0, v1, v2}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 154
    iget-object p3, p2, Lorg/telegram/messenger/MessageObject;->caption:Ljava/lang/CharSequence;

    if-eqz p3, :cond_7

    invoke-interface {p3}, Ljava/lang/CharSequence;->length()I

    move-result p3

    if-nez p3, :cond_6

    goto :goto_2

    .line 155
    :cond_6
    const-string p3, ", "

    invoke-virtual {p1, p3}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object p1

    iget-object p3, p2, Lorg/telegram/messenger/MessageObject;->caption:Ljava/lang/CharSequence;

    invoke-direct {p0, p2, p3, p4}, Lcom/exteragram/messenger/components/BlendedReplyFileText;->formatCaption(Lorg/telegram/messenger/MessageObject;Ljava/lang/CharSequence;Landroid/graphics/Paint$FontMetricsInt;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p1, p0}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    move-result-object p0

    return-object p0

    :cond_7
    :goto_2
    return-object p1
.end method
