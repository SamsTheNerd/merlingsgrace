package com.samsthenerd.merlingsgrace;

import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.api.misc.MediaConstants;
import at.petrak.hexcasting.common.casting.actions.spells.OpPotionEffect;
import at.petrak.hexcasting.common.lib.hex.HexActions;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class MerlingsGrace implements ModInitializer {

	public static final String MODID = "merlingsgrace";

    public static final Logger LOGGER = LoggerFactory.getLogger("merlingsgrace");

	@Override
	public void onInitialize() {
		Registry.register(HexActions.REGISTRY, new Identifier(MODID, "merlingsgrace"),
			new ActionRegistryEntry(
				HexPattern.fromAngles("awaaqawqwaqaawa", HexDir.SOUTH_WEST),
				new ActionPredicateWrapper(
					new OpPotionEffect(StatusEffects.DOLPHINS_GRACE, MediaConstants.DUST_UNIT / 3, false, false),
						(CastingEnvironment env) -> {
							if(env.getCastingEntity() instanceof PlayerEntity player && isMerling(player)){
								return Optional.empty();
							}
							return Optional.of(new MishapWrongOrigin(
									List.of(OriginRegistry.get(new Identifier("origins", "merling")))
							));
						}
				)
			)
		);
	}

	public static boolean isMerling(PlayerEntity player){
		OriginComponent originComponent = ModComponents.ORIGIN.get(player);
		OriginLayer oLayer = OriginLayers.getLayer(new Identifier("origins", "origin"));
		Origin origin = originComponent.getOrigin(oLayer);
		LOGGER.info("player " + player.getDisplayName().getString() + " has origin: " + origin.getName().getString() + " (" + origin.getIdentifier() + ")");
		return origin.getIdentifier().equals(new Identifier("origins", "merling"));
	}
}