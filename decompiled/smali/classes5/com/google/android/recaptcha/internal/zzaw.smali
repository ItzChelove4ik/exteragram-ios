.class final synthetic Lcom/google/android/recaptcha/internal/zzaw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzaw;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaw;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzaw;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaw;->zza:Lcom/google/android/recaptcha/internal/zzaw;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaju;

    .line 1
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p0

    .line 2
    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    const-string v0, "REAL"

    const-string v1, "INTEGER"

    packed-switch p1, :pswitch_data_0

    const-string v0, "COLUMN_UNSPECIFIED"

    goto :goto_0

    :pswitch_0
    move-object v0, v1

    goto :goto_0

    .line 1
    :pswitch_1
    const-string v0, "BLOB"

    goto :goto_0

    :pswitch_2
    const-string v0, "INTEGER PRIMARY KEY AUTOINCREMENT"

    .line 2
    :goto_0
    :pswitch_3
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p1

    new-instance v1, Ljava/lang/StringBuilder;

    add-int/lit8 p1, p1, 0x1

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/2addr p1, v2

    .line 1
    invoke-direct {v1, p1}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_3
        :pswitch_3
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_3
        :pswitch_3
        :pswitch_0
    .end packed-switch
.end method
