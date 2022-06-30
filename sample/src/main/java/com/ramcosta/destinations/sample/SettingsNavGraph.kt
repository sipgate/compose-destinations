package com.ramcosta.destinations.sample

import com.ramcosta.composedestinations.annotation.NavGraph

@MainNavGraph
@NavGraph
annotation class SettingsNavGraph(
    val start: Boolean = false
)