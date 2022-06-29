package com.ramcosta.destinations.sample

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@MainNavGraph(start = true)
@NavGraph
annotation class TaskNavGraph(
    val start: Boolean = false
)
