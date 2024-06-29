package com.samsthenerd.merlingsgrace;

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.mishaps.Mishap;
import at.petrak.hexcasting.api.pigment.FrozenPigment;
import io.github.apace100.origins.origin.Origin;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MishapWrongOrigin extends Mishap {

    private final List<Origin> validOrigins;

    public MishapWrongOrigin(List<Origin> validOrigins){
        this.validOrigins = validOrigins;
    }

    @NotNull
    @Override
    public FrozenPigment accentColor(@NotNull CastingEnvironment castingEnvironment, @NotNull Mishap.Context context) {
        return dyeColor(DyeColor.LIGHT_BLUE);
    }

    @Override
    public void execute(@NotNull CastingEnvironment castingEnvironment, @NotNull Mishap.Context context, @NotNull List<Iota> list) {
        // don't think we need to do anything ?
    }

    @Nullable
    @Override
    protected Text errorMessage(@NotNull CastingEnvironment castingEnvironment, @NotNull Mishap.Context context) {
        if(validOrigins.size() == 1){
            return error("wrong_origin.single", validOrigins.get(0).getName());
        }
        return error("wrong_origin.multi"); // TODO: make this proper
    }
}
