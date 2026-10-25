.class public abstract Lcom/exteragram/messenger/appicons/GeneratedAppIcons;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static create()[Lcom/exteragram/messenger/appicons/AppIcon;
    .locals 46

    .line 12
    sget v3, Lorg/telegram/messenger/R$string;->AppIconDefault:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconDefaultInfo:I

    sget v10, Lorg/telegram/messenger/R$color;->ic_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 13
    const-string v0, "default"

    const-string v1, "com.exteragram.messenger.DefaultIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "exteraGram"

    const/4 v9, 0x0

    const/4 v11, 0x1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v14

    sget v3, Lorg/telegram/messenger/R$string;->AppIconWinter:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconWinterInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_winter_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 14
    const-string v0, "winter"

    const-string v1, "com.exteragram.messenger.WinterIcon"

    const-string v2, "winter"

    const-string v8, "exteraGram"

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v15

    sget v3, Lorg/telegram/messenger/R$string;->AppIconMonet:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconMonetInfo:I

    sget v10, Lorg/telegram/messenger/R$color;->ic_background_monet:I

    sget v12, Lorg/telegram/messenger/R$drawable;->ic_foreground_monet:I

    const/4 v13, 0x0

    .line 15
    const-string v0, "monet"

    const-string v1, "com.exteragram.messenger.MonetIcon"

    const-string v2, "monet"

    const-string v8, "exteraGram"

    const/4 v11, 0x1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v16

    sget v3, Lorg/telegram/messenger/R$string;->AppIconOrbit:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconOrbitInfo:I

    sget v10, Lorg/telegram/messenger/R$color;->ic_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_orbit_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_orbit:I

    .line 16
    const-string v0, "orbit"

    const-string v1, "com.exteragram.messenger.OrbitIcon"

    const-string v2, "always"

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v17

    sget v3, Lorg/telegram/messenger/R$string;->AppIconEditor:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconEditorInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_editor_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_editor_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 17
    const-string v0, "editor"

    const-string v1, "com.exteragram.messenger.EditorIcon"

    const-string v2, "always"

    const-string v8, "exteraGram"

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v18

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_mole_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_mole_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 18
    const-string v0, "mole"

    const-string v1, "com.exteragram.messenger.MoleIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Mole"

    const/4 v5, 0x0

    const-string v8, "whatwherewhywhen"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v19

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_echpochmak_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_echpochmak_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 19
    const-string v0, "echpochmak"

    const-string v1, "com.exteragram.messenger.EchpochmakIcon"

    const-string v2, "always"

    const-string v4, "Echpochmak"

    const-string v8, "whatwherewhywhen"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v20

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_avatar_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_avatar_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 20
    const-string v0, "avatar"

    const-string v1, "com.exteragram.messenger.AvatarIcon"

    const-string v2, "always"

    const-string v4, "Avatar"

    const-string v8, "cIick_cIack"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v21

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_candy_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_candy_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_candy:I

    .line 21
    const-string v0, "candy"

    const-string v1, "com.exteragram.messenger.CandyIcon"

    const-string v2, "always"

    const-string v4, "Candy"

    const-string v8, "impulz3"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v22

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_guitar_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_guitar_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 22
    const-string v0, "guitar"

    const-string v1, "com.exteragram.messenger.GuitarIcon"

    const-string v2, "always"

    const-string v4, "Guitar"

    const-string v8, "nekto_nkt"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v23

    sget v3, Lorg/telegram/messenger/R$string;->AppIconSus:I

    sget v10, Lorg/telegram/messenger/R$color;->ic_background_sus:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_sus_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 23
    const-string v0, "sus"

    const-string v1, "com.exteragram.messenger.SusIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "MilesBlooper"

    const/4 v11, 0x1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v24

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_frog_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_frog_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 24
    const-string v0, "frog"

    const-string v1, "com.exteragram.messenger.FrogIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Frog"

    const-string v8, "cIick_cIack"

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v25

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_hamster_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_hamster_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 25
    const-string v0, "hamster"

    const-string v1, "com.exteragram.messenger.HamsterIcon"

    const-string v2, "always"

    const-string v4, "Hamster"

    const-string v8, "whatwherewhywhen"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v26

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_sponge_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_sponge_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 26
    const-string v0, "sponge"

    const-string v1, "com.exteragram.messenger.SpongeIcon"

    const-string v2, "always"

    const-string v4, "Sponge"

    const-string v8, "nneeuurr"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v27

    sget v3, Lorg/telegram/messenger/R$string;->AppIconInvincible:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconInvincibleInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_invincible_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_invincible_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 27
    const-string v0, "invincible"

    const-string v1, "com.exteragram.messenger.InvincibleIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v28

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_flappy_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_flappy_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 28
    const-string v0, "flappy"

    const-string v1, "com.exteragram.messenger.FlappyIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Flappy"

    const/4 v5, 0x0

    const-string v8, "nneeuurr"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v29

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_nyan_cat_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_nyan_cat_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 29
    const-string v0, "nyan_cat"

    const-string v1, "com.exteragram.messenger.NyanCatIcon"

    const-string v2, "always"

    const-string v4, "Nyan Cat"

    const-string v8, "n1ksly"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v30

    sget v3, Lorg/telegram/messenger/R$string;->AppIconIceAge:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconIceAgeInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_ice_age_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_ice_age_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 30
    const-string v0, "ice_age"

    const-string v1, "com.exteragram.messenger.IceAgeIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v31

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_fish_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_fish_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 31
    const-string v0, "fish"

    const-string v1, "com.exteragram.messenger.FishIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Fish"

    const/4 v5, 0x0

    const-string v8, "whatwherewhywhen"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v32

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_psychosis_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_psychosis_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 32
    const-string v0, "psychosis"

    const-string v1, "com.exteragram.messenger.PsychosisIcon"

    const-string v2, "always"

    const-string v4, "Psychosis"

    const-string v8, "cIick_cIack"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v33

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_xp_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_xp_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 33
    const-string v0, "xp"

    const-string v1, "com.exteragram.messenger.XpIcon"

    const-string v2, "always"

    const-string v4, "XP"

    const-string v8, "weizoqq"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v34

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_nasa_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_nasa_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_nasa:I

    .line 34
    const-string v0, "nasa"

    const-string v1, "com.exteragram.messenger.NasaIcon"

    const-string v2, "always"

    const-string v4, "NASA"

    const-string v8, "cIick_cIack"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v35

    sget v3, Lorg/telegram/messenger/R$string;->AppIconCyberpunk:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconCyberpunkInfo:I

    sget v10, Lorg/telegram/messenger/R$color;->ic_background_cyberpunk:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_cyberpunk_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_cyberpunk:I

    .line 35
    const-string v0, "cyberpunk"

    const-string v1, "com.exteragram.messenger.CyberpunkIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "exteraGram"

    const/4 v11, 0x1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v36

    sget v3, Lorg/telegram/messenger/R$string;->AppIconSpace:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconSpaceInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_space_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_space_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_space:I

    .line 36
    const-string v0, "space"

    const-string v1, "com.exteragram.messenger.SpaceIcon"

    const-string v2, "always"

    const-string v8, "exteraGram"

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v37

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_jellyfish_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_jellyfish_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 37
    const-string v0, "jellyfish"

    const-string v1, "com.exteragram.messenger.JellyfishIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Jellyfish"

    const/4 v5, 0x0

    const-string v8, "whatwherewhywhen"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v38

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_human_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_human_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 38
    const-string v0, "human"

    const-string v1, "com.exteragram.messenger.HumanIcon"

    const-string v2, "always"

    const-string v4, "Human"

    const-string v8, "cIick_cIack"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v39

    sget v3, Lorg/telegram/messenger/R$string;->AppIconSunset:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconSunsetInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_sunset_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_sunset_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 39
    const-string v0, "sunset"

    const-string v1, "com.exteragram.messenger.SunsetIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v40

    sget v3, Lorg/telegram/messenger/R$string;->AppIconAmethyst:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconAmethystInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_amethyst_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_amethyst_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 40
    const-string v0, "amethyst"

    const-string v1, "com.exteragram.messenger.AmethystIcon"

    const-string v2, "always"

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v41

    sget v3, Lorg/telegram/messenger/R$string;->AppIconAurora:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconAuroraInfo:I

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_aurora_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_aurora_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 41
    const-string v0, "aurora"

    const-string v1, "com.exteragram.messenger.AuroraIcon"

    const-string v2, "always"

    const-string v8, "exteraGram"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v42

    sget v10, Lorg/telegram/messenger/R$mipmap;->ic_launcher_osu_background:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_osu_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 42
    const-string v0, "osu"

    const-string v1, "com.exteragram.messenger.OsuIcon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "osu!"

    const/4 v5, 0x0

    const-string v8, "ataeshi"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v43

    sget v3, Lorg/telegram/messenger/R$string;->AppIconGoogle:I

    sget v5, Lorg/telegram/messenger/R$string;->AppIconGoogleInfo:I

    sget v10, Lorg/telegram/messenger/R$color;->white:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_google_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome_google:I

    .line 43
    const-string v0, "google"

    const-string v1, "com.exteragram.messenger.GoogleIcon"

    const-string v2, "always"

    const/4 v4, 0x0

    const-string v8, "exteraGram"

    const/4 v11, 0x1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v44

    sget v10, Lorg/telegram/messenger/R$color;->white:I

    sget v12, Lorg/telegram/messenger/R$mipmap;->ic_launcher_google26_foreground:I

    sget v13, Lorg/telegram/messenger/R$drawable;->ic_monochrome:I

    .line 44
    const-string v0, "google26"

    const-string v1, "com.exteragram.messenger.Google26Icon"

    const-string v2, "always"

    const/4 v3, 0x0

    const-string v4, "Google \u201926"

    const/4 v5, 0x0

    const-string v8, "Slucket"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/appicons/AppIcon;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;IZII)Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v45

    filled-new-array/range {v14 .. v45}, [Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    return-object v0
.end method
