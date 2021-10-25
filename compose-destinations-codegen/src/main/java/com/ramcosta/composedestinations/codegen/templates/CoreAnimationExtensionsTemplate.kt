package com.ramcosta.composedestinations.codegen.templates

import com.ramcosta.composedestinations.codegen.commons.*

val coreAnimationsExtensionsTemplate = """
package $PACKAGE_NAME

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import $PACKAGE_NAME.spec.DestinationSpec
import $PACKAGE_NAME.spec.DestinationStyle

@ExperimentalAnimationApi
fun NavGraphBuilder.addAnimatedComposable(
    animatedStyle: AnimatedDestinationStyle,
    destination: Destination,
    navController: NavHostController,
    destinationDependenciesProvider: ($GENERATED_DESTINATION) -> MutableMap<Class<*>, Any>
) = with(animatedStyle) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks,
        enterTransition = { i, t -> enterTransition(i.navDestination, t.navDestination) },
        exitTransition = { i, t -> exitTransition(i.navDestination, t.navDestination) },
        popEnterTransition = { i, t -> popEnterTransition(i.navDestination, t.navDestination) },
        popExitTransition = { i, t -> popExitTransition(i.navDestination, t.navDestination) }
    ) { navBackStackEntry ->
        destination.Content(
            navController,
            navBackStackEntry,
            destinationDependenciesProvider(destination).apply {
                this[$ANIMATED_VISIBILITY_SCOPE_SIMPLE_NAME::class.java] = this@composable
            }
        )
    }
}

fun interface DestinationEnterTransition {
    @ExperimentalAnimationApi
    fun AnimatedContentScope<String>.enter(initial: Destination?, target: Destination?) : EnterTransition
}

fun interface DestinationExitTransition {
    @ExperimentalAnimationApi
    fun AnimatedContentScope<String>.exit(initial: Destination?, target: Destination?) : ExitTransition
}

@ExperimentalAnimationApi
class DefaultAnimationParams(
    val contentAlignment: Alignment = Alignment.Center,
    val enterTransition: DestinationEnterTransition? = DestinationEnterTransition { _, _ -> EnterTransition.None },
    val exitTransition: DestinationExitTransition? = DestinationExitTransition { _, _ -> ExitTransition.None },
    val popEnterTransition: DestinationEnterTransition? = enterTransition,
    val popExitTransition: DestinationExitTransition? = exitTransition,
) {

    companion object {
        val ACCOMPANIST_FADING by lazy {
            DefaultAnimationParams(
                enterTransition = { _, _ -> fadeIn(animationSpec = tween(700)) },
                exitTransition = { _, _ -> fadeOut(animationSpec = tween(700)) }
            )
        }
    }
}
""".trimIndent()