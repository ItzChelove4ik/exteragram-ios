.class public Lorg/telegram/utils/localization/Localization;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/localization/Localization$Builder;
    }
.end annotation


# static fields
.field public static final EMPTY:Lorg/telegram/utils/localization/Localization;

.field private static rawResBindings:Landroid/util/SparseIntArray;


# instance fields
.field private final localizations:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static bridge synthetic -$$Nest$fgetlocalizations(Lorg/telegram/utils/localization/Localization;)Landroid/util/SparseArray;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/localization/Localization;->localizations:Landroid/util/SparseArray;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$smload(Landroid/content/Context;Ljava/lang/String;Landroid/util/SparseArray;)Landroid/util/SparseArray;
    .locals 0

    .line 0
    invoke-static {p0, p1, p2}, Lorg/telegram/utils/localization/Localization;->load(Landroid/content/Context;Ljava/lang/String;Landroid/util/SparseArray;)Landroid/util/SparseArray;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 21
    new-instance v0, Lorg/telegram/utils/localization/Localization;

    invoke-direct {v0}, Lorg/telegram/utils/localization/Localization;-><init>()V

    sput-object v0, Lorg/telegram/utils/localization/Localization;->EMPTY:Lorg/telegram/utils/localization/Localization;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/localization/Localization;->localizations:Landroid/util/SparseArray;

    return-void
.end method

.method private constructor <init>(Lorg/telegram/utils/localization/Localization$Builder;)V
    .locals 1

    .line 29
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 30
    invoke-static {p1}, Lorg/telegram/utils/localization/Localization$Builder;->-$$Nest$fgetlocalizations(Lorg/telegram/utils/localization/Localization$Builder;)Landroid/util/SparseArray;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 31
    invoke-static {p1}, Lorg/telegram/utils/localization/Localization$Builder;->-$$Nest$fgetlocalizations(Lorg/telegram/utils/localization/Localization$Builder;)Landroid/util/SparseArray;

    move-result-object p1

    goto :goto_0

    :cond_0
    new-instance p1, Landroid/util/SparseArray;

    invoke-direct {p1}, Landroid/util/SparseArray;-><init>()V

    :goto_0
    iput-object p1, p0, Lorg/telegram/utils/localization/Localization;->localizations:Landroid/util/SparseArray;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/localization/Localization$Builder;Lorg/telegram/utils/localization/Localization-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/localization/Localization;-><init>(Lorg/telegram/utils/localization/Localization$Builder;)V

    return-void
.end method

.method private static load(Landroid/content/Context;Ljava/lang/String;Landroid/util/SparseArray;)Landroid/util/SparseArray;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;)",
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 114
    new-instance v0, Ljava/io/BufferedInputStream;

    .line 115
    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    .line 116
    :try_start_0
    new-instance p0, Lorg/telegram/tgnet/SerializedData;

    invoke-direct {p0, v0}, Lorg/telegram/tgnet/SerializedData;-><init>(Ljava/io/InputStream;)V

    const/4 p1, 0x1

    .line 117
    invoke-virtual {p0, p1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v1

    const/4 v2, 0x0

    if-nez p2, :cond_0

    .line 119
    new-instance p2, Landroid/util/SparseArray;

    invoke-direct {p2, v1}, Landroid/util/SparseArray;-><init>(I)V

    :goto_0
    if-ge v2, v1, :cond_1

    .line 121
    invoke-virtual {p0, p1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v3

    .line 122
    invoke-virtual {p0, p1}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v4

    .line 123
    invoke-virtual {p2, v3, v4}, Landroid/util/SparseArray;->append(ILjava/lang/Object;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_0
    :goto_1
    if-ge v2, v1, :cond_1

    .line 127
    invoke-virtual {p0, p1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v3

    .line 128
    invoke-virtual {p0, p1}, Lorg/telegram/tgnet/SerializedData;->readString(Z)Ljava/lang/String;

    move-result-object v4

    .line 129
    invoke-virtual {p2, v3, v4}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 133
    :cond_1
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V

    return-object p2

    .line 114
    :goto_2
    :try_start_1
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_3

    :catchall_1
    move-exception p1

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_3
    throw p0
.end method

.method private static loadStringResBindings(Landroid/content/Context;)Landroid/util/SparseIntArray;
    .locals 7

    .line 137
    :try_start_0
    new-instance v0, Ljava/io/BufferedInputStream;

    .line 138
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    const-string v1, "string_resource_ids.bin"

    invoke-virtual {p0, v1}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 139
    :try_start_1
    new-instance p0, Lorg/telegram/tgnet/SerializedData;

    invoke-direct {p0, v0}, Lorg/telegram/tgnet/SerializedData;-><init>(Ljava/io/InputStream;)V

    const/4 v1, 0x1

    .line 140
    invoke-virtual {p0, v1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v2

    .line 142
    new-instance v3, Landroid/util/SparseIntArray;

    invoke-direct {v3, v2}, Landroid/util/SparseIntArray;-><init>(I)V

    const/4 v4, 0x0

    :goto_0
    if-ge v4, v2, :cond_0

    .line 144
    invoke-virtual {p0, v1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v5

    .line 145
    invoke-virtual {p0, v1}, Lorg/telegram/tgnet/SerializedData;->readInt32(Z)I

    move-result v6

    .line 146
    invoke-virtual {v3, v5, v6}, Landroid/util/SparseIntArray;->append(II)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 149
    :cond_0
    :try_start_2
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    return-object v3

    .line 137
    :goto_1
    :try_start_3
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_2

    :catchall_1
    move-exception v0

    :try_start_4
    invoke-virtual {p0, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_2
    throw p0
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0

    :catch_0
    move-exception p0

    .line 150
    invoke-static {p0}, Lokhttp3/HttpUrl$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public get(I)Ljava/lang/String;
    .locals 0

    .line 68
    iget-object p0, p0, Lorg/telegram/utils/localization/Localization;->localizations:Landroid/util/SparseArray;

    invoke-virtual {p0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method public getByResId(Landroid/content/Context;I)Ljava/lang/String;
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_3

    if-nez p2, :cond_0

    goto :goto_0

    .line 39
    :cond_0
    sget-object v1, Lorg/telegram/utils/localization/Localization;->rawResBindings:Landroid/util/SparseIntArray;

    if-nez v1, :cond_1

    .line 40
    invoke-static {p1}, Lorg/telegram/utils/localization/Localization;->loadStringResBindings(Landroid/content/Context;)Landroid/util/SparseIntArray;

    move-result-object p1

    sput-object p1, Lorg/telegram/utils/localization/Localization;->rawResBindings:Landroid/util/SparseIntArray;

    .line 42
    :cond_1
    sget-object p1, Lorg/telegram/utils/localization/Localization;->rawResBindings:Landroid/util/SparseIntArray;

    invoke-virtual {p1, p2}, Landroid/util/SparseIntArray;->get(I)I

    move-result p1

    if-nez p1, :cond_2

    return-object v0

    .line 46
    :cond_2
    invoke-virtual {p0, p1}, Lorg/telegram/utils/localization/Localization;->get(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_3
    :goto_0
    return-object v0
.end method

.method public getByResName(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    if-eqz p1, :cond_0

    .line 51
    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/localization/Localization;->get(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public getByResNameOrResId(Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    .locals 0

    if-eqz p2, :cond_0

    .line 59
    invoke-virtual {p0, p2}, Lorg/telegram/utils/localization/Localization;->getByResName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    :goto_0
    if-nez p2, :cond_1

    if-eqz p3, :cond_1

    .line 62
    invoke-virtual {p0, p1, p3}, Lorg/telegram/utils/localization/Localization;->getByResId(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    return-object p2
.end method
